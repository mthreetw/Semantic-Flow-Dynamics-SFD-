package tools;

import java.io.IOException;

/**
 * 整合執行器——依序執行以下工具：
 *
 *   0. FormalizationValidator  → 純格式檢查所有 formalization.md（不通過會列出清單）
 *   1. FormalizationCollector  → formalizations/formalization_xxx.md
 *   2. PaperTreeGenerator      → PaperTree.html
 *   3. StarMapGenerator        → starmap.html
 *   4. DatasetExporter         → 資料集匯出
 *
 * 用法：
 *   java tools.ToolRunner           ← 全部執行
 *   java tools.ToolRunner validate  ← 只執行 FormalizationValidator
 *   java tools.ToolRunner collect   ← 只執行 FormalizationCollector
 *   java tools.ToolRunner tree      ← 只執行 PaperTreeGenerator
 *   java tools.ToolRunner star      ← 只執行 StarMapGenerator
 *   java tools.ToolRunner export    ← 只執行 DatasetExporter
 */
public class ToolRunner {

	public static void main(String[] args) throws IOException {
        // 統一在最前面設定 UTF-8 輸出，避免中文在某些系統預設編碼下變亂碼
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));

        boolean runAll      = args.length == 0;
        boolean runValidate = runAll || contains(args, "validate");  // 新增
        boolean runCollect  = runAll || contains(args, "collect");
        boolean runTree     = runAll || contains(args, "tree");
        boolean runStar     = runAll || contains(args, "star");
        boolean runExport   = runAll || contains(args, "export");   // 新增

        if (runValidate) { section("FormalizationValidator");    FormalizationValidator.main(new String[0]); } // 新增
        if (runCollect)  { section("FormalizationCollector"); FormalizationCollector.main(new String[0]); }
        if (runTree)     { section("PaperTreeGenerator");     PaperTreeGenerator.main(new String[0]); }
        if (runStar)     { section("StarMapGenerator");       StarMapGenerator.main(new String[0]); }
        if (runExport)   { section("DatasetExporter");        DatasetExporter.main(new String[0]); } // 新增

        System.out.println("\n✓ 完成");
    }

    private static void section(String name) {
        System.out.println("\n── " + name + " ─────────────────────────────");
    }

    private static boolean contains(String[] args, String target) {
        for (String a : args)
            if (a.equalsIgnoreCase(target)) return true;
        return false;
    }
}