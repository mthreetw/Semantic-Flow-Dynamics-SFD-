package tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

/**
 * 掃描 src/main/java/paper_[UUID]/ 資料夾
 * 產出 exports/papers.jsonl（每行一篇論文，含 formalization 全文 + 論文原文全文）
 *
 * 用途：讓 HF dataset 使用者可以只下載/載入論文全文（不含結構化概念圖），
 * 跟 concepts.jsonl 是互補的兩個 config，顆粒度不同：
 *   concepts.jsonl — 一行一個概念，給結構化查詢/embedding 用
 *   papers.jsonl   — 一行一篇論文，給 RAG / 人工閱讀 / 原文核對用
 */
public class PapersExporter {

    private static final Path SRC_ROOT = Paths.get("src/main/java");
    private static final Path OUT_FILE = Paths.get("exports/papers.jsonl");

    private static final Pattern UUID_LINE  = Pattern.compile("^UUID：([0-9a-f-]{36})\\s*$");
    private static final Pattern TITLE_LINE = Pattern.compile("^論文：《(.+)》\\s*$");
    private static final Pattern ENG_TITLE_LINE = Pattern.compile("^英文標題：(.+)\\s*$");

    public static void main(String[] args) throws IOException {
        List<Path> formalizationFiles = Files.walk(SRC_ROOT)
            .filter(p -> p.getFileName().toString().equals("formalization.md"))
            .collect(Collectors.toList());

        Files.createDirectories(OUT_FILE.getParent());
        int count = 0;
        try (var writer = Files.newBufferedWriter(OUT_FILE, StandardCharsets.UTF_8)) {
            for (Path formFile : formalizationFiles) {
                String json = extractPaper(formFile);
                if (json != null) {
                    writer.write(json);
                    writer.newLine();
                    count++;
                }
            }
        }
        System.out.println("寫入 " + count + " 篇論文全文到 " + OUT_FILE);
    }

    private static String extractPaper(Path formFile) throws IOException {
        Path paperDir = formFile.getParent();
        String formText = Files.readString(formFile, StandardCharsets.UTF_8);

        String paperUuid = null, paperTitle = null, engTitle = null;
        for (String line : formText.split("\n")) {
            var u = UUID_LINE.matcher(line.trim());
            if (u.matches()) paperUuid = u.group(1);
            var t = TITLE_LINE.matcher(line.trim());
            if (t.matches()) paperTitle = t.group(1);
            var e = ENG_TITLE_LINE.matcher(line.trim());
            if (e.matches()) engTitle = e.group(1).trim();
        }

        if (paperUuid == null) {
            System.err.println("警告：" + formFile + " 找不到 UUID，略過");
            return null;
        }

        // 收集同資料夾內除了 formalization.md 之外的所有 .md 檔（論文原文，可能有中英文兩份）
        List<Path> otherMdFiles;
        try (var stream = Files.list(paperDir)) {
            otherMdFiles = stream
                .filter(p -> p.getFileName().toString().endsWith(".md"))
                .filter(p -> !p.getFileName().toString().equals("formalization.md"))
                .sorted()
                .collect(Collectors.toList());
        }

        StringBuilder sb = new StringBuilder("{");
        sb.append("\"paper_uuid\":").append(json(paperUuid)).append(",");
        sb.append("\"paper_title\":").append(json(paperTitle)).append(",");
        sb.append("\"paper_title_en\":").append(json(engTitle)).append(",");
        sb.append("\"formalization_md\":").append(json(formText)).append(",");
        sb.append("\"paper_files\":[");
        for (int i = 0; i < otherMdFiles.size(); i++) {
            if (i > 0) sb.append(",");
            Path p = otherMdFiles.get(i);
            String content = Files.readString(p, StandardCharsets.UTF_8);
            sb.append("{\"filename\":").append(json(p.getFileName().toString()))
              .append(",\"content\":").append(json(content)).append("}");
        }
        sb.append("]}");
        return sb.toString();
    }

    private static String json(String s) {
        if (s == null) return "null";
        return "\"" + s.replace("\\", "\\\\")
                       .replace("\"", "\\\"")
                       .replace("\n", "\\n")
                       .replace("\r", "") + "\"";
    }
}
