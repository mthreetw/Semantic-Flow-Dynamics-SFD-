package tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

/**
 * 群星規範 — 形式化文件驗證器（Java CLI 版）
 * 翻譯自 formalization-validator.html 的純格式檢查邏輯。
 * 語義判定與論文交叉比對（AI_ONLY 清單）不在本工具範圍內。
 *
 * 用法：
 *   java FormalizationValidator                 # 掃描目前目錄及子目錄下所有 formalization.md
 *   java FormalizationValidator <檔案或資料夾路徑> [更多路徑...]
 */
public class FormalizationValidator {

    // ===== 符號表：所有允許的特殊符號 =====
    static final Set<Character> ALLOWED_SYMBOLS = new HashSet<>(Arrays.asList(
        '∧','∨','⊕','¬','→','←','↔','⇒','⇔','∀','∃','∄','∈','∉',
        '⊂','⊃','⊆','⊇','∪','∩','≡','≠','≈','∅','≥','≤','≫','≪',
        '≺','≻','≼','≽','∼','≅','⊥','⊤','□','◇','∝','·','×','±','∞',
        'Δ','∑','∏','√','⊢','⊬','⊨','⊭','↦','∘','∥','∣','⫫','↝',
        '∂','∇','∫','⊗'
    ));

    // ===== 不檢查的約束（交給 AI 審核） =====
    static final List<String> AI_ONLY = Arrays.asList(
        "C5.1b","C5.1c","C5.2b","C5.2c",
        "C5.3a","C5.3c","C5.3d",
        "C5.4a","C5.4b","C5.4d","C5.4f",
        "C5.5b","C5.5c","C5.5d","C5.5e","C5.5f","C5.5g","C5.5h",
        "C5.6a","C5.6b","C5.6c","C5.6d","C5.6e","C5.6f","C5.6g",
        "C5.7a","C5.7b","C5.7c","C5.7d","C5.7e","C5.7f","C5.7g","C5.7h",
        "C5.8a","C5.8b","C5.8c","C5.8d","C5.8e",
        "C5.9b",
        "C8a","C8e","C8f","C8g"
    );

    // ===================== 資料結構 =====================

    static class Header {
        String formTS, manualTS, paper, engTitle, uuid;
        List<String> refs; // null = 未出現「引用：」欄位；非 null 代表已解析（可能為空清單，即「無」）
    }

    static class Section {
        String title;
        List<String> lines = new ArrayList<>();
        String source; // null 表示沒有「來源：」
    }

    static class Concept {
        String cn, en;
        boolean hasEn;
    }

    static class UpstreamRef {
        String uuid;
        int line;
    }

    static class LetterIssue {
        char ch;
        int line;
        String context;
    }

    static class SymbolIssue {
        char ch;
        String code;
        int line;
    }

    static class Result {
        String id, desc, cat, detail;
        boolean ok;
        Result(String id, String desc, String cat, boolean ok, String detail) {
            this.id = id; this.desc = desc; this.cat = cat; this.ok = ok; this.detail = detail;
        }
    }

    // ===================== Parser =====================

    static Header parseHeader(String text) {
        Header h = new Header();
        String[] lines = text.split("\n", -1);
        int i = 0;
        while (i < lines.length) {
            String L = lines[i].trim();
            Matcher m;

            if ((m = Pattern.compile("^形式化時間戳[：:]\\s*(.+)").matcher(L)).find()) {
                h.formTS = m.group(1).trim();
            } else if ((m = Pattern.compile("^形式化手冊時間戳[：:]\\s*(.+)").matcher(L)).find()) {
                h.manualTS = m.group(1).trim();
            } else if ((m = Pattern.compile("^論文[：:]\\s*(.+)").matcher(L)).find()) {
                h.paper = m.group(1).trim();
            } else if ((m = Pattern.compile("^英文標題[：:]\\s*(.+)").matcher(L)).find()) {
                h.engTitle = m.group(1).trim();
            } else if ((m = Pattern.compile("^UUID[：:]\\s*(.+)").matcher(L)).find()) {
                h.uuid = m.group(1).trim();
            } else if (Pattern.compile("^引用[：:]").matcher(L).find()) {
                h.refs = new ArrayList<>();
                int j = i + 1;
                while (j < lines.length) {
                    String r = lines[j].trim();
                    if (r.isEmpty() || r.startsWith("#") || r.startsWith("---")) {
                        j++;
                        continue;
                    }
                    String cleanNone = r.replaceFirst("^[\\s\\-*\\d.、]+", "").trim();
                    if (r.equals("無") || cleanNone.equals("無")) {
                        h.refs = new ArrayList<>();
                        break;
                    }
                    if (Pattern.compile("^(ext:)?[0-9a-f]{8}-", Pattern.CASE_INSENSITIVE).matcher(r).find()) {
                        h.refs.add(r);
                    } else {
                        break;
                    }
                    j++;
                }
            }
            i++;
        }
        return h;
    }

    static List<Section> parseSections(String text) {
        List<Section> secs = new ArrayList<>();
        Section cur = null;
        for (String line : text.split("\n", -1)) {
            if (line.matches("^## .*")) {
                if (cur != null) secs.add(cur);
                cur = new Section();
                cur.title = line.replaceFirst("^## ", "").trim();
            } else if (cur != null) {
                cur.lines.add(line);
                Matcher m = Pattern.compile("^來源[：:]\\s*(.+)").matcher(line);
                if (m.find()) cur.source = m.group(1).trim();
            }
        }
        if (cur != null) secs.add(cur);
        return secs;
    }

    static List<Concept> getConceptSummary(String text) {
        Matcher m = Pattern.compile("## 概念摘要([\\s\\S]*?)(?=\\n## |$)").matcher(text);
        if (!m.find()) return null;
        List<Concept> concepts = new ArrayList<>();
        for (String line : m.group(1).split("\n", -1)) {
            String t = line.trim();
            if (t.isEmpty() || t.startsWith("本篇概念") || t.startsWith("---")) continue;
            Matcher cm = Pattern.compile("^(.+?)\\s*\\((.+?)\\)\\s*$").matcher(t);
            Concept c = new Concept();
            if (cm.find()) {
                c.cn = cm.group(1).trim();
                c.en = cm.group(2).trim();
                c.hasEn = true;
            } else {
                c.cn = t;
                c.en = null;
                c.hasEn = false;
            }
            concepts.add(c);
        }
        return concepts;
    }

    static List<String> getPendingItems(String text) {
        Matcher m = Pattern.compile("## 待決項([\\s\\S]*?)(?=\\n## |$)").matcher(text);
        if (!m.find()) return null;
        String body = m.group(1).trim();
        if (body.isEmpty()) return new ArrayList<>();

        Matcher charsM = Pattern.compile("[\\u4e00-\\u9fa5a-zA-Z0-9]").matcher(body);
        StringBuilder cleanedSb = new StringBuilder();
        while (charsM.find()) cleanedSb.append(charsM.group());
        if (cleanedSb.length() == 0) return new ArrayList<>();
        if (cleanedSb.toString().equals("無")) return new ArrayList<>();

        List<String> result = new ArrayList<>();
        for (String rawLine : body.split("\n", -1)) {
            String line = rawLine.trim();
            if (line.isEmpty()) continue;
            String cleanLine = line.replaceFirst("^[\\s\\-*\\d.、]+", "").trim();
            if (cleanLine.equals("無") || cleanLine.isEmpty()) continue;
            result.add(line);
        }
        return result;
    }

    static List<UpstreamRef> getUpstreamUUIDs(String text) {
        List<UpstreamRef> found = new ArrayList<>();
        String[] lines = text.split("\n", -1);
        boolean inHeader = true;
        Pattern re = Pattern.compile("((?:ext:)?[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})\\.", Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < lines.length; i++) {
            String L = lines[i].trim();
            if (L.startsWith("---") && inHeader) { inHeader = false; continue; }
            if (inHeader) continue;
            if (L.startsWith("|")) continue;
            Matcher m = re.matcher(L);
            while (m.find()) {
                UpstreamRef u = new UpstreamRef();
                u.uuid = m.group(1);
                u.line = i + 1;
                found.add(u);
            }
        }
        return found;
    }

    static List<LetterIssue> findSingleLetterVars(String text) {
        List<LetterIssue> issues = new ArrayList<>();
        String[] lines = text.split("\n", -1);
        boolean inCode = false;
        boolean inSummary = false;
        Pattern uuidRe = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}", Pattern.CASE_INSENSITIVE);
        // 對應 JS: (?<![a-zA-Zα-ωΑ-Ω0-9₀-₉\u4e00-\u9fff])([a-zA-Zα-ωΑ-Ω])(?![a-zA-Zα-ωΑ-Ω0-9₀-₉\u4e00-\u9fff])
        String notBefore = "[a-zA-Zα-ωΑ-Ω0-9₀-₉\\u4e00-\\u9fff]";
        Pattern re = Pattern.compile("(?<!" + notBefore + ")([a-zA-Zα-ωΑ-Ω])(?!" + notBefore + ")");

        for (int i = 0; i < lines.length; i++) {
            String L = lines[i];
            String T = L.trim();

            if (T.startsWith("```")) { inCode = !inCode; continue; }
            if (inCode) continue;
            if (T.startsWith("|")) continue;
            if (T.startsWith("#")) continue;
            if (T.startsWith("---")) continue;
            if (Pattern.compile("^英文標題[：:]").matcher(T).find()) continue;
            if (Pattern.compile("^(形式化|論文|UUID|引用|來源)").matcher(T).find()) continue;
            if (uuidRe.matcher(T).find()) continue;
            if (T.contains("## 概念摘要")) { inSummary = true; continue; }
            if (Pattern.compile("^## ").matcher(T).find()) { inSummary = false; continue; }
            if (inSummary) continue;

            Matcher m = re.matcher(T);
            while (m.find()) {
                char ch = m.group(1).charAt(0);
                int idx = m.start();
                String after = T.substring(idx + 1, Math.min(T.length(), idx + 5));
                if (Pattern.compile("^[0-9₀-₉]").matcher(after).find()) continue;
                if (ch == 'e' && idx + 4 <= T.length() && T.substring(idx, idx + 4).equals("ext:")) continue;
                if ((ch == 'd' || ch == 't') && T.contains("∫")) continue;
                if ((ch == 'f' || ch == 'g') && T.contains("∘")) continue;
                LetterIssue li = new LetterIssue();
                li.ch = ch;
                li.line = i + 1;
                li.context = T.substring(Math.max(0, idx - 10), Math.min(T.length(), idx + 11));
                issues.add(li);
            }
        }
        return issues;
    }

    static List<SymbolIssue> findUnknownSymbols(String text) {
        List<SymbolIssue> issues = new ArrayList<>();
        String[] lines = text.split("\n", -1);
        boolean inCode = false;
        for (int i = 0; i < lines.length; i++) {
            String L = lines[i];
            if (L.trim().startsWith("```")) { inCode = !inCode; continue; }
            if (inCode || L.startsWith("|") || L.startsWith("#") || L.startsWith("---")) continue;
            if (Pattern.compile("^(形式化|論文|英文|UUID|引用|來源)").matcher(L.trim()).find()) continue;
            int len = L.length();
            for (int j = 0; j < len; j++) {
                int cp = L.codePointAt(j);
                if (cp > 0xFFFF) { j++; continue; }
                char ch = L.charAt(j);
                boolean inRange =
                    (cp >= 0x2190 && cp <= 0x21FF) ||
                    (cp >= 0x2200 && cp <= 0x22FF) ||
                    (cp >= 0x27C0 && cp <= 0x27EF) ||
                    (cp >= 0x2980 && cp <= 0x29FF) ||
                    (cp >= 0x2A00 && cp <= 0x2AFF);
                if (inRange) {
                    if (ch == '⊥' && j + 1 < len && L.charAt(j + 1) == '⊥') { j++; continue; }
                    if (ch == '⊥' && j > 0 && L.charAt(j - 1) == '⊥') continue;
                    if (!ALLOWED_SYMBOLS.contains(ch)) {
                        SymbolIssue si = new SymbolIssue();
                        si.ch = ch;
                        si.code = "U+" + Integer.toHexString(cp).toUpperCase(Locale.ROOT);
                        while (si.code.length() < 6) si.code = si.code.substring(0, 2) + "0" + si.code.substring(2);
                        si.line = i + 1;
                        issues.add(si);
                    }
                }
            }
        }
        return issues;
    }

    static List<String> checkDefinitionCoverage(String text, List<Concept> summary) {
        if (summary == null) return null;
        String[] rawLines = text.split("\n", -1);
        List<String> lines = new ArrayList<>();
        for (String l : rawLines) lines.add(l.trim());
        List<String> missing = new ArrayList<>();

        for (Concept c : summary) {
            String name = c.cn;
            String esc = Pattern.quote(name);
            Pattern directRe = Pattern.compile("^" + esc + "(\\([^)]*\\))?\\s*≡");

            boolean found = false;
            for (String L : lines) {
                if (directRe.matcher(L).find()) { found = true; break; }
            }

            if (!found) {
                Pattern boldRe = Pattern.compile("^\\*\\*" + esc + "\\*\\*\\s*$");
                outer:
                for (int i = 0; i < lines.size(); i++) {
                    if (!boldRe.matcher(lines.get(i)).find()) continue;
                    for (int j = i + 1; j < lines.size(); j++) {
                        String T = lines.get(j);
                        if (Pattern.compile("^\\*\\*.+\\*\\*\\s*$").matcher(T).find() ||
                            Pattern.compile("^#{2,3} ").matcher(T).find()) break;
                        if (Pattern.compile("^-?\\s*定義[：:]").matcher(T).find()) { found = true; break outer; }
                    }
                }
            }

            // C5.4g 路徑 (c)：定理（非恆等式）性質的概念——獨立 ### 小節，
            // 內含 □ / ⊢ / ↔ 等邏輯運算式，且以自然語言標註「定理」
            if (!found) {
                Pattern headingRe = Pattern.compile("^#{2,3}\\s.*" + esc + ".*$");
                outer2:
                for (int i = 0; i < lines.size(); i++) {
                    if (!headingRe.matcher(lines.get(i)).find()) continue;
                    boolean hasLogicOp = false, hasTheoremWord = false;
                    for (int j = i + 1; j < lines.size(); j++) {
                        String T = lines.get(j);
                        if (Pattern.compile("^#{2,3} ").matcher(T).find()) break;
                        if (T.contains("□") || T.contains("⊢") || T.contains("↔")) hasLogicOp = true;
                        if (T.contains("定理")) hasTheoremWord = true;
                    }
                    if (hasLogicOp && hasTheoremWord) { found = true; break outer2; }
                }
            }

            if (!found) missing.add(name);
        }
        return missing;
    }

    // ===================== Validator =====================

    static List<Result> validate(String text, String filename) {
        List<Result> R = new ArrayList<>();
        Header h = parseHeader(text);
        List<Section> secs = parseSections(text);
        List<Concept> summary = getConceptSummary(text);
        List<String> pending = getPendingItems(text);
        List<UpstreamRef> upRefs = getUpstreamUUIDs(text);
        List<SymbolIssue> badSymbols = findUnknownSymbols(text);
        List<LetterIssue> badLetters = findSingleLetterVars(text);
        List<String> defMissing = checkDefinitionCoverage(text, summary);

        // --- 文件頭 ---
        List<String> missing = new ArrayList<>();
        if (h.formTS == null) missing.add("形式化時間戳");
        if (h.manualTS == null) missing.add("形式化手冊時間戳");
        if (h.paper == null) missing.add("論文");
        if (h.engTitle == null) missing.add("英文標題");
        if (h.uuid == null) missing.add("UUID");
        if (h.refs == null) missing.add("引用");
        R.add(new Result("C5.1a", "文件頭所有必填欄位全部存在", "文件頭",
            missing.isEmpty(), missing.isEmpty() ? null : "缺少：" + String.join("、", missing)));

        if (h.refs != null) {
            List<String> dupes = new ArrayList<>();
            for (int i = 0; i < h.refs.size(); i++) {
                if (h.refs.indexOf(h.refs.get(i)) != i) dupes.add(h.refs.get(i));
            }
            R.add(new Result("C5.1d", "引用列表中 UUID 不重複", "文件頭",
                dupes.isEmpty(), dupes.isEmpty() ? null : "重複：" + String.join("、", dupes)));
        }

        Pattern tsRe = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$");
        List<String> tsErr = new ArrayList<>();
        if (h.formTS != null && !tsRe.matcher(h.formTS).matches()) tsErr.add("形式化時間戳格式錯誤：" + h.formTS);
        if (h.manualTS != null && !tsRe.matcher(h.manualTS).matches()) tsErr.add("形式化手冊時間戳格式錯誤：" + h.manualTS);
        R.add(new Result("C5.1g", "時間戳格式為 YYYY-MM-DDTHH:MM:SS", "文件頭",
            tsErr.isEmpty() && h.formTS != null && h.manualTS != null,
            !tsErr.isEmpty() ? String.join("；", tsErr) : ((h.formTS == null || h.manualTS == null) ? "時間戳欄位缺失（見 C5.1a）" : null)));

        R.add(new Result("C5.1h", "檔名必須為 formalization.md", "文件頭",
            filename.equals("formalization.md"), filename.equals("formalization.md") ? null : "實際檔名：" + filename));

        // --- 段落結構 ---
        List<Section> contentSecs = secs.stream().filter(s ->
            !s.title.equals("概念摘要") && !s.title.equals("待決項") &&
            !Pattern.compile("^上游概念").matcher(s.title).find() &&
            s.lines.stream().anyMatch(l -> !l.trim().isEmpty())
        ).collect(Collectors.toList());
        List<Section> noSrc = contentSecs.stream().filter(s -> s.source == null).collect(Collectors.toList());
        R.add(new Result("C5.2a", "每個內容段落有標題和來源標註", "段落結構",
            noSrc.isEmpty(), noSrc.isEmpty() ? null :
                "缺少「來源：」的段落：" + noSrc.stream().map(s -> "「" + s.title + "」").collect(Collectors.joining("、"))));

        // --- 上游引用 ---
        if (h.refs != null) {
            Set<String> refSet = h.refs.stream().map(String::toLowerCase).collect(Collectors.toSet());
            List<UpstreamRef> orphan = upRefs.stream().filter(u -> !refSet.contains(u.uuid.toLowerCase())).collect(Collectors.toList());
            R.add(new Result("C5.3b", "文中出現的上游 UUID 必須在文件頭引用列表中", "上游引用",
                orphan.isEmpty(), orphan.isEmpty() ? null :
                    orphan.stream().map(o -> o.uuid + "（行 " + o.line + "）").collect(Collectors.joining("、"))));
        }

        // --- 符號約束 ---
        R.add(new Result("C5.5a", "只使用符號表中列出的特殊符號", "符號約束",
            badSymbols.isEmpty(), badSymbols.isEmpty() ? null :
                badSymbols.stream().map(s -> s.ch + " (" + s.code + ", 行 " + s.line + ")").collect(Collectors.joining("、"))));

        // --- 概念約束 ---
        R.add(new Result("C5.4c", "禁止單字母變量（公設編號、ext:、∫dt、f∘g 等白名單除外）", "概念約束",
            badLetters.isEmpty(), badLetters.isEmpty() ? null :
                badLetters.stream().map(b -> "「" + b.ch + "」行 " + b.line + "：…" + b.context + "…").collect(Collectors.joining("、"))));

        // --- 文件結尾 ---
        R.add(new Result("C5.9a", "存在「概念摘要」區塊", "文件結尾",
            summary != null, summary != null ? null : "未找到「## 概念摘要」"));

        R.add(new Result("C5.9c", "存在「待決項」區塊", "文件結尾",
            pending != null, pending != null ? null : "未找到「## 待決項」"));

        if (summary != null) {
            List<Concept> noEn = summary.stream().filter(c -> !c.hasEn).collect(Collectors.toList());
            R.add(new Result("C5.9d", "概念摘要中每個概念有英文翻譯（括號內）", "文件結尾",
                noEn.isEmpty(), noEn.isEmpty() ? null :
                    "缺少英文翻譯：" + noEn.stream().map(c -> c.cn).collect(Collectors.joining("、"))));
        }

        // --- 定義句格式涵蓋率（手冊 C5.4g：≡ / 定義條列 / 定理式獨立小節 三種認可形式） ---
        if (summary != null) {
            R.add(new Result("C5.4g", "概念摘要中每個概念，本文有可辨識的形式定義（C5.4g 三種認可形式之一）", "概念約束",
                defMissing != null && defMissing.isEmpty(),
                (defMissing != null && !defMissing.isEmpty()) ? "找不到可辨識形式定義：" + String.join("、", defMissing) : null));
        }

        if (pending != null) {
            R.add(new Result("C8d", "待決項非空時，不得進入審核", "待決項",
                pending.isEmpty(), pending.isEmpty() ? null : "有 " + pending.size() + " 項待決項未解決"));
        }

        return R;
    }

    // ===================== 檔案掃描 / 輸出 =====================

    static List<Path> collectFiles(String[] args) throws IOException {
        List<Path> result = new ArrayList<>();
        if (args.length == 0) {
            try (Stream<Path> s = Files.walk(Paths.get("."))) {
                s.filter(Files::isRegularFile)
                 .filter(p -> p.getFileName().toString().equals("formalization.md"))
                 .forEach(result::add);
            }
        } else {
            for (String a : args) {
                Path p = Paths.get(a);
                if (Files.isDirectory(p)) {
                    try (Stream<Path> s = Files.walk(p)) {
                        s.filter(Files::isRegularFile)
                         .filter(f -> f.getFileName().toString().equals("formalization.md"))
                         .forEach(result::add);
                    }
                } else if (Files.isRegularFile(p)) {
                    result.add(p);
                } else {
                    System.out.println("[警告] 找不到路徑：" + a);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        // 強制輸出用 UTF-8，避免在某些系統預設編碼下中文變亂碼
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));

        List<Path> files = collectFiles(args);

        if (files.isEmpty()) {
            System.out.println("找不到任何 formalization.md 檔案（可指定路徑作為參數）。");
            return;
        }

        int totalFail = 0;
        for (Path p : files) {
            String text = new String(Files.readAllBytes(p), StandardCharsets.UTF_8);
            String filename = p.getFileName().toString();
            List<Result> results = validate(text, filename);
            List<Result> fails = results.stream().filter(r -> !r.ok).collect(Collectors.toList());

            System.out.println("=== 檔案：" + p + " ===");
            if (fails.isEmpty()) {
                System.out.println("檢查 OK");
            } else {
                totalFail += fails.size();
                System.out.println("❌ 發現 " + fails.size() + " 項問題：");
                for (Result r : fails) {
                    System.out.println("  [" + r.id + "] (" + r.cat + ") " + r.desc);
                    if (r.detail != null) System.out.println("      -> " + r.detail);
                }
            }
            System.out.println();
        }

        System.out.println("=====================================");
        System.out.println("共檢查 " + files.size() + " 個檔案，" + (totalFail == 0 ? "全部通過。" : "共 " + totalFail + " 項問題（詳見上方）。"));
        System.out.println("（純格式檢查；語義判定與論文交叉比對交由 AI 審核，相關約束編號：" + String.join("、", AI_ONLY) + "）");
    }
}
