package tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

/**
 * 檢查 exports/papers.jsonl 的結構完整性，並（若存在）與
 * exports/concepts.jsonl 做 paper_uuid 交叉比對。
 *
 * 這支工具只做「檢查」，不重新生成資料——資料生成的唯一來源是
 * PapersExporter.java 和 DatasetExporter.java。這支工具的角色跟
 * formalization-validator.html 對 formalization.md 的角色一樣：
 * 讀已經產出的結果，回報有沒有問題，不動原始邏輯。
 *
 * 用一支簡易的手寫 JSON parser（只處理本專案自己產出的、已知結構的
 * JSONL，不是通用 JSON parser），避免額外引入第三方 JSON 函式庫依賴。
 */
public class PapersChecker {

    private static final Path PAPERS_FILE   = Paths.get("exports/papers.jsonl");
    private static final Path CONCEPTS_FILE = Paths.get("exports/concepts.jsonl");
    private static final Pattern UUID_RE = Pattern.compile(
        "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) throws IOException {
        if (!Files.exists(PAPERS_FILE)) {
            System.err.println("錯誤：找不到 " + PAPERS_FILE + "，請先跑 java tools.ToolRunner papers");
            System.exit(1);
        }

        List<Map<String, Object>> papers = readJsonl(PAPERS_FILE);
        List<Map<String, Object>> concepts = Files.exists(CONCEPTS_FILE)
            ? readJsonl(CONCEPTS_FILE) : null;

        List<CheckResult> results = new ArrayList<>();

        // 1. 必要欄位存在
        List<String> missingFields = new ArrayList<>();
        for (int i = 0; i < papers.size(); i++) {
            Map<String, Object> p = papers.get(i);
            List<String> miss = new ArrayList<>();
            if (isBlank(p.get("paper_uuid"))) miss.add("paper_uuid");
            if (isBlank(p.get("paper_title"))) miss.add("paper_title");
            if (isBlank(p.get("formalization_md"))) miss.add("formalization_md");
            if (!p.containsKey("paper_files")) miss.add("paper_files");
            if (!miss.isEmpty()) {
                missingFields.add("第 " + (i + 1) + " 筆（" + p.getOrDefault("paper_uuid", "?") + "）缺少：" + String.join("、", miss));
            }
        }
        results.add(new CheckResult("必要欄位", "每筆記錄都有 paper_uuid/paper_title/formalization_md/paper_files",
            missingFields.isEmpty(), String.join("\n", missingFields)));

        // 2. UUID 格式
        List<String> badUuids = papers.stream()
            .map(p -> (String) p.get("paper_uuid"))
            .filter(u -> u != null && !UUID_RE.matcher(u).matches())
            .collect(Collectors.toList());
        results.add(new CheckResult("UUID格式", "所有 paper_uuid 符合標準格式",
            badUuids.isEmpty(), String.join("、", badUuids)));

        // 3. 無重複 UUID
        Map<String, Long> uuidCounts = papers.stream()
            .map(p -> (String) p.get("paper_uuid"))
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(u -> u, Collectors.counting()));
        List<String> dupUuids = uuidCounts.entrySet().stream()
            .filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
        results.add(new CheckResult("無重複", "沒有重複的 paper_uuid",
            dupUuids.isEmpty(), String.join("、", dupUuids)));

        // 4. formalization_md 長度合理
        List<String> emptyForm = new ArrayList<>();
        for (Map<String, Object> p : papers) {
            String form = (String) p.get("formalization_md");
            if (form == null || form.length() < 100) {
                emptyForm.add(p.get("paper_title") + "（" + (form == null ? 0 : form.length()) + " 字元）");
            }
        }
        results.add(new CheckResult("Formalization內容", "formalization_md 非空且長度 ≥ 100 字元",
            emptyForm.isEmpty(), String.join("、", emptyForm)));

        // 5. paper_files 非空
        List<String> noPaperFiles = new ArrayList<>();
        for (Map<String, Object> p : papers) {
            Object pf = p.get("paper_files");
            if (!(pf instanceof List) || ((List<?>) pf).isEmpty()) {
                noPaperFiles.add((String) p.get("paper_title"));
            }
        }
        results.add(new CheckResult("論文原文", "每篇論文至少有一個 paper_files 條目",
            noPaperFiles.isEmpty(), String.join("、", noPaperFiles)));

        // 6. paper_files 內容非空
        List<String> emptyFileContents = new ArrayList<>();
        for (Map<String, Object> p : papers) {
            Object pf = p.get("paper_files");
            if (pf instanceof List) {
                for (Object o : (List<?>) pf) {
                    if (o instanceof Map) {
                        Map<?, ?> f = (Map<?, ?>) o;
                        String content = (String) f.get("content");
                        if (content == null || content.length() < 100) {
                            emptyFileContents.add(p.get("paper_title") + "／" + f.get("filename")
                                + "（" + (content == null ? 0 : content.length()) + " 字元）");
                        }
                    }
                }
            }
        }
        results.add(new CheckResult("原文內容", "每個 paper_files 的 content 非空且長度 ≥ 100 字元",
            emptyFileContents.isEmpty(), String.join("\n", emptyFileContents)));

        // 7. 與 concepts.jsonl 交叉比對（若存在）
        if (concepts != null) {
            Set<String> conceptUuids = concepts.stream()
                .map(c -> (String) c.get("paper_uuid")).filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new));
            Set<String> paperUuids = papers.stream()
                .map(p -> (String) p.get("paper_uuid")).filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new));

            List<String> inConceptsNotPapers = conceptUuids.stream()
                .filter(u -> !paperUuids.contains(u)).collect(Collectors.toList());
            List<String> inPapersNotConcepts = paperUuids.stream()
                .filter(u -> !conceptUuids.contains(u)).collect(Collectors.toList());

            results.add(new CheckResult("交叉比對(concepts→papers)",
                "concepts.jsonl 裡的每個 paper_uuid，papers.jsonl 都找得到對應論文",
                inConceptsNotPapers.isEmpty(), String.join("、", inConceptsNotPapers)));

            results.add(new CheckResult("交叉比對(papers→concepts)",
                "papers.jsonl 裡的每篇論文，concepts.jsonl 都至少有一個對應概念",
                inPapersNotConcepts.isEmpty(), String.join("、", inPapersNotConcepts)));
        } else {
            System.out.println("（找不到 " + CONCEPTS_FILE + "，略過交叉比對）\n");
        }

        printReport(results, papers.size());
    }

    private static boolean isBlank(Object o) {
        return o == null || (o instanceof String && ((String) o).isBlank());
    }

    private static void printReport(List<CheckResult> results, int paperCount) {
        long pass = results.stream().filter(r -> r.ok).count();
        long fail = results.size() - pass;

        System.out.println("========================================");
        System.out.println("papers.jsonl 檢查報告（共 " + paperCount + " 筆論文記錄）");
        System.out.println("========================================");
        System.out.println(pass + " 項通過, " + fail + " 項不通過\n");

        for (CheckResult r : results) {
            System.out.println((r.ok ? "✓ 通過" : "✗ 不通過") + "  [" + r.id + "] " + r.desc);
            if (!r.ok && r.detail != null && !r.detail.isBlank()) {
                for (String line : r.detail.split("\n")) {
                    System.out.println("    " + line);
                }
            }
        }
        System.out.println();
        if (fail > 0) {
            System.out.println("⚠ 有 " + fail + " 項檢查未通過，請往上核對細節後再上傳。");
        } else {
            System.out.println("✓ 全部通過，可以上傳。");
        }
    }

    private static class CheckResult {
        String id, desc, detail;
        boolean ok;
        CheckResult(String id, String desc, boolean ok, String detail) {
            this.id = id; this.desc = desc; this.ok = ok; this.detail = detail;
        }
    }

    // ===== 極簡 JSONL reader，只處理本專案自己產出的已知結構 =====
    // 支援：物件、字串（含 \" \\ \n 跳脫）、陣列、null、忽略其他型別的巢狀物件（當 Map 存）

    private static List<Map<String, Object>> readJsonl(Path file) throws IOException {
        List<Map<String, Object>> result = new ArrayList<>();
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;
            int[] pos = {0};
            Object parsed = parseValue(trimmed, pos);
            if (parsed instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> m = (Map<String, Object>) parsed;
                result.add(m);
            }
        }
        return result;
    }

    private static Object parseValue(String s, int[] pos) {
        skipWs(s, pos);
        char c = s.charAt(pos[0]);
        if (c == '{') return parseObject(s, pos);
        if (c == '[') return parseArray(s, pos);
        if (c == '"') return parseString(s, pos);
        if (s.startsWith("null", pos[0])) { pos[0] += 4; return null; }
        if (s.startsWith("true", pos[0])) { pos[0] += 4; return Boolean.TRUE; }
        if (s.startsWith("false", pos[0])) { pos[0] += 5; return Boolean.FALSE; }
        // number
        int start = pos[0];
        while (pos[0] < s.length() && "-+.eE0123456789".indexOf(s.charAt(pos[0])) >= 0) pos[0]++;
        return s.substring(start, pos[0]);
    }

    private static Map<String, Object> parseObject(String s, int[] pos) {
        Map<String, Object> map = new LinkedHashMap<>();
        pos[0]++; // {
        skipWs(s, pos);
        if (s.charAt(pos[0]) == '}') { pos[0]++; return map; }
        while (true) {
            skipWs(s, pos);
            String key = parseString(s, pos);
            skipWs(s, pos);
            pos[0]++; // :
            Object val = parseValue(s, pos);
            map.put(key, val);
            skipWs(s, pos);
            char c = s.charAt(pos[0]);
            if (c == ',') { pos[0]++; continue; }
            if (c == '}') { pos[0]++; break; }
        }
        return map;
    }

    private static List<Object> parseArray(String s, int[] pos) {
        List<Object> list = new ArrayList<>();
        pos[0]++; // [
        skipWs(s, pos);
        if (s.charAt(pos[0]) == ']') { pos[0]++; return list; }
        while (true) {
            Object val = parseValue(s, pos);
            list.add(val);
            skipWs(s, pos);
            char c = s.charAt(pos[0]);
            if (c == ',') { pos[0]++; continue; }
            if (c == ']') { pos[0]++; break; }
        }
        return list;
    }

    private static String parseString(String s, int[] pos) {
        StringBuilder sb = new StringBuilder();
        pos[0]++; // 開頭引號
        while (true) {
            char c = s.charAt(pos[0]);
            if (c == '"') { pos[0]++; break; }
            if (c == '\\') {
                char next = s.charAt(pos[0] + 1);
                switch (next) {
                    case 'n': sb.append('\n'); break;
                    case 't': sb.append('\t'); break;
                    case 'r': sb.append('\r'); break;
                    case '"': sb.append('"'); break;
                    case '\\': sb.append('\\'); break;
                    case '/': sb.append('/'); break;
                    case 'u':
                        String hex = s.substring(pos[0] + 2, pos[0] + 6);
                        sb.append((char) Integer.parseInt(hex, 16));
                        pos[0] += 4;
                        break;
                    default: sb.append(next);
                }
                pos[0] += 2;
            } else {
                sb.append(c);
                pos[0]++;
            }
        }
        return sb.toString();
    }

    private static void skipWs(String s, int[] pos) {
        while (pos[0] < s.length() && Character.isWhitespace(s.charAt(pos[0]))) pos[0]++;
    }
}
