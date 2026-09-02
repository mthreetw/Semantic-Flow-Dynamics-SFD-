package tools;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Scans all paper_* packages under src/main/java, reads each formalization.md,
 * extracts the paper name from the "論文：《xxx》" header line, and copies
 * every file to a single output folder as "formalization_xxx.md".
 *
 * Usage:
 *   java util.FormalizationCollector [srcRoot] [outputDir]
 *
 * Defaults:
 *   srcRoot   = src/main/java
 *   outputDir = formalizations
 */
public class FormalizationCollector {

    private static final Pattern PAPER_DIR   = Pattern.compile("paper_[0-9a-f_]+");
    private static final Pattern TITLE_LINE  = Pattern.compile("^論文：《(.+?)》");

    public static void main(String[] args) throws IOException {

        Path srcRoot   = args.length > 0 ? Paths.get(args[0]) : Paths.get("src/main/java");
        Path outputDir = args.length > 1 ? Paths.get(args[1]) : Paths.get("formalizations");

        if (!Files.isDirectory(srcRoot)) {
            System.err.println("Error: srcRoot not found: " + srcRoot.toAbsolutePath());
            System.exit(1);
        }

        Files.createDirectories(outputDir);

        int copied  = 0;
        int skipped = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(srcRoot)) {
            for (Path dir : stream) {

                // Only process paper_* directories
                if (!Files.isDirectory(dir)) continue;
                if (!PAPER_DIR.matcher(dir.getFileName().toString()).matches()) continue;

                Path formalization = dir.resolve("formalization.md");
                if (!Files.exists(formalization)) {
                    System.out.printf("  [skip] no formalization.md in %s%n", dir.getFileName());
                    skipped++;
                    continue;
                }

                String paperName = extractPaperName(formalization);
                if (paperName == null) {
                    System.err.printf("  [warn] 論文 header not found in %s%n", formalization);
                    skipped++;
                    continue;
                }

                Path dest = outputDir.resolve("formalization_" + paperName + ".md");
                Files.copy(formalization, dest, StandardCopyOption.REPLACE_EXISTING);
                System.out.printf("  [ok]   %s → formalization_%s.md%n",
                        dir.getFileName(), paperName);
                copied++;
            }
        }

        System.out.printf("%nDone: %d copied, %d skipped.  Output: %s%n",
                copied, skipped, outputDir.toAbsolutePath());
    }

    /**
     * Reads the file and returns the paper name from the "論文：《xxx》" header line,
     * or null if the line is not found.
     */
    private static String extractPaperName(Path file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher m = TITLE_LINE.matcher(line.trim());
                if (m.find()) {
                    return m.group(1);
                }
            }
        }
        return null;
    }
}
