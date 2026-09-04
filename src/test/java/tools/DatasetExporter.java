package tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

/**
 * 掃描 src/main/java/paper_[UUID]/formalization.md
 * 產出 exports/concepts.jsonl（每行一個概念，內含 relations 陣列）
 *
 * 定義句抽取採兩階段策略，與 formalization-validator.html 的
 * checkDefinitionCoverage() 邏輯保持一致：
 *   1. 主線格式："概念名 ≡ 定義" 或 "概念名(參數) ≡ 定義"
 *   2. Fallback 格式："**概念名**" 標題，區塊內找 "- 定義：..." 或 "定義：..." 條列
 *
 * 注意：這是規則式抽取（regex-based），依賴 formalization.md 遵循
 * 群星規範固定格式。抽取結果建議人工抽樣核對。
 */
public class DatasetExporter {

    private static final Path SRC_ROOT = Paths.get("src/main/java");
    private static final Path OUT_FILE = Paths.get("exports/concepts.jsonl");

    private static final Pattern UUID_LINE  = Pattern.compile("^UUID：([0-9a-f-]{36})\\s*$");
    private static final Pattern TITLE_LINE = Pattern.compile("^論文：《(.+)》\\s*$");
    private static final Pattern SECTION_HEADING = Pattern.compile("^#{1,3} (.+)$");

    // 概念摘要裡一行：中文名 (English)
    private static final Pattern SUMMARY_ENTRY =
        Pattern.compile("^\\s*(\\S+)\\s*\\((.+)\\)\\s*$");

    // 完整 UUID 引用，允許 ext: 前綴（依手冊 2.3 節格式）
    private static final Pattern PREFIXED_REF = Pattern.compile(
        "(ext:)?([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})\\.([^\\s，,、（(：:∧∨¬]+)"
    );

    // 出現任一符號就記錄，不做語意分類，留給後續 AI 判斷
    private static final Set<String> ARROW_SYMBOLS = Set.of("←", "↝", "⊗", "⊕", "→", "⇒");

    // fallback：定義條列行
    private static final Pattern BULLET_DEF = Pattern.compile("^-?\\s*定義[：:]\\s*(.+)$");

    public static void main(String[] args) throws IOException {
        List<Path> files = Files.walk(SRC_ROOT)
            .filter(p -> p.getFileName().toString().equals("formalization.md"))
            .collect(Collectors.toList());

        Files.createDirectories(OUT_FILE.getParent());
        try (var writer = Files.newBufferedWriter(OUT_FILE, StandardCharsets.UTF_8)) {
            for (Path file : files) {
                for (String jsonLine : extractConcepts(file)) {
                    writer.write(jsonLine);
                    writer.newLine();
                }
            }
        }
        System.out.println("寫入 " + files.size() + " 篇論文的概念到 " + OUT_FILE);
    }

    private static List<String> extractConcepts(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        String paperUuid = null, paperTitle = null;
        for (String line : lines) {
            var u = UUID_LINE.matcher(line);
            if (u.matches()) paperUuid = u.group(1);
            var t = TITLE_LINE.matcher(line);
            if (t.matches()) paperTitle = t.group(1);
        }

        Map<String, String> exportList = extractSummary(lines); // 中文名 -> 英文名
        if (exportList.isEmpty()) {
            System.err.println("警告：" + file + " 找不到概念摘要，略過");
            return List.of();
        }

        List<String> output = new ArrayList<>();
        for (var entry : exportList.entrySet()) {
            output.add(buildConceptJson(entry.getKey(), entry.getValue(),
                paperUuid, paperTitle, lines));
        }
        return output;
    }

    private static Map<String, String> extractSummary(List<String> lines) {
        Map<String, String> result = new LinkedHashMap<>();
        boolean inSummary = false;
        for (String raw : lines) {
            String line = raw.trim();
            if (line.equals("## 概念摘要")) { inSummary = true; continue; }
            if (inSummary && line.startsWith("## ")) break;
            if (inSummary) {
                var m = SUMMARY_ENTRY.matcher(line);
                if (m.matches()) result.put(m.group(1).trim(), m.group(2).trim());
            }
        }
        return result;
    }

    private static String buildConceptJson(String concept, String enName,
            String paperUuid, String paperTitle, List<String> lines) {

        // 主線格式："概念名" 或 "概念名(參數)" 後面接 ≡ 及同行內容
        Pattern directDefPattern = Pattern.compile(
            "^" + Pattern.quote(concept) + "(\\([^)]*\\))?\\s*≡\\s*(.+)$");
        // fallback 1：**概念名** 標題行
        Pattern boldHeadingPattern = Pattern.compile(
            "^\\*\\*" + Pattern.quote(concept) + "\\*\\*\\s*$");
        // fallback 2："概念名 ≡" 後面同行沒有內容，定義是接下來的縮排/多行區塊
        Pattern emptyEquivPattern = Pattern.compile(
            "^" + Pattern.quote(concept) + "(\\([^)]*\\))?\\s*≡\\s*$");

        String definition = null, section = null, currentSection = null;
        int defLineIdx = -1;

        // 第一遍：先找主線格式 "概念名 ≡ 定義"
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            var sec = SECTION_HEADING.matcher(line);
            if (sec.matches()) { currentSection = sec.group(1); continue; }

            var def = directDefPattern.matcher(line);
            if (def.matches() && definition == null) {
                definition = def.group(2).trim();
                section = currentSection;
                defLineIdx = i;
                break;
            }
        }

        // 第二遍：主線格式沒抓到，退而找 fallback："**概念名**" 標題 + 區塊內 "- 定義：..."
        if (definition == null) {
            currentSection = null;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                var sec = SECTION_HEADING.matcher(line);
                if (sec.matches()) { currentSection = sec.group(1); continue; }

                if (boldHeadingPattern.matcher(line).matches()) {
                    String blockSection = currentSection;
                    for (int j = i + 1; j < lines.size(); j++) {
                        String inner = lines.get(j).trim();
                        // 區塊結束條件：遇到下一個粗體標題或 ##/### 標題
                        if (Pattern.matches("^\\*\\*.+\\*\\*\\s*$", inner)) break;
                        if (SECTION_HEADING.matcher(inner).matches()) break;

                        var bd = BULLET_DEF.matcher(inner);
                        if (bd.matches()) {
                            definition = bd.group(1).trim();
                            section = blockSection;
                            defLineIdx = i; // 關係抽取從標題行開始往下掃
                            break;
                        }
                    }
                    if (definition != null) break;
                }
            }
        }

        // 第三遍：前兩種都沒抓到，退而找 "概念名 ≡"（同行無內容）+ 接下來的多行區塊
        if (definition == null) {
            currentSection = null;
            for (int i = 0; i < lines.size(); i++) {
                String rawLine = lines.get(i);
                String line = rawLine.trim();
                var sec = SECTION_HEADING.matcher(line);
                if (sec.matches()) { currentSection = sec.group(1); continue; }

                if (emptyEquivPattern.matcher(line).matches()) {
                    List<String> blockLines = new ArrayList<>();
                    int j = i + 1;
                    // 跳過開頭的 ``` 圍籬（若有）
                    if (j < lines.size() && lines.get(j).trim().equals("```")) j++;
                    while (j < lines.size()) {
                        String inner = lines.get(j);
                        String innerTrim = inner.trim();
                        if (innerTrim.isEmpty()) break;
                        if (innerTrim.equals("```")) break;
                        if (SECTION_HEADING.matcher(innerTrim).matches()) break;
                        if (Pattern.matches("^\\*\\*.+\\*\\*\\s*$", innerTrim)) break;
                        blockLines.add(innerTrim);
                        j++;
                    }
                    if (!blockLines.isEmpty()) {
                        definition = String.join("\n", blockLines);
                        section = currentSection;
                        defLineIdx = i;
                        break;
                    }
                }
            }
        }

        // 關係抽取：從定義（或標題）行往下抓到下一個 ## / ### 標題或空白區塊為止
        List<String[]> relations = new ArrayList<>();
        if (defLineIdx >= 0) {
            for (int i = defLineIdx; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (i > defLineIdx && SECTION_HEADING.matcher(line).matches()) break;
                if (i > defLineIdx && Pattern.matches("^\\*\\*.+\\*\\*\\s*$", line)
                        && !line.equals("**" + concept + "**")) break;
                if (line.isEmpty() && i > defLineIdx + 3) break;

                boolean hasArrow = ARROW_SYMBOLS.stream().anyMatch(line::contains);
                var ref = PREFIXED_REF.matcher(line);
                boolean hasRef = false;
                while (ref.find()) {
                    hasRef = true;
                    String prefix = (ref.group(1) != null ? "ext:" : "") + ref.group(2);
                    relations.add(new String[]{prefix + "." + ref.group(3), line});
                }
                if (hasArrow && !hasRef) {
                    relations.add(new String[]{null, line}); // 有符號但無 UUID 引用，可能是本篇內部概念
                }
            }
        }

        StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":").append(json(concept)).append(",");
        sb.append("\"id_en\":").append(json(enName)).append(",");
        sb.append("\"paper_uuid\":").append(json(paperUuid)).append(",");
        sb.append("\"paper_title\":").append(json(paperTitle)).append(",");
        sb.append("\"definition_source\":").append(json(section)).append(",");
        sb.append("\"definition\":").append(json(definition)).append(",");
        sb.append("\"relations\":[");
        for (int k = 0; k < relations.size(); k++) {
            String[] r = relations.get(k);
            if (k > 0) sb.append(",");
            sb.append("{\"tail\":").append(json(r[0]))
              .append(",\"context\":").append(json(r[1])).append("}");
        }
        sb.append("]}");
        return sb.toString();
    }

    private static String json(String s) {
        if (s == null) return "null";
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n") + "\"";
    }
}
