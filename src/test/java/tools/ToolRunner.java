package tools;

import java.io.IOException;

/**
 * 整合執行器——依序執行三個工具：
 *
 *   1. FormalizationCollector  → formalizations/formalization_xxx.md
 *   2. PaperTreeGenerator      → PaperTree.html
 *   3. StarMapGenerator        → starmap.html
 *
 * 用法：
 *   java tools.ToolRunner           ← 全部執行
 *   java tools.ToolRunner collect   ← 只執行 FormalizationCollector
 *   java tools.ToolRunner tree      ← 只執行 PaperTreeGenerator
 *   java tools.ToolRunner star      ← 只執行 StarMapGenerator
 */
public class ToolRunner {

    public static void main(String[] args) throws IOException {

        boolean runAll      = args.length == 0;
        boolean runCollect  = runAll || contains(args, "collect");
        boolean runTree     = runAll || contains(args, "tree");
        boolean runStar     = runAll || contains(args, "star");

        if (runCollect) {
            section("FormalizationCollector");
            FormalizationCollector.main(new String[0]);
        }

        if (runTree) {
            section("PaperTreeGenerator");
            PaperTreeGenerator.main(new String[0]);
        }

        if (runStar) {
            section("StarMapGenerator");
            StarMapGenerator.main(new String[0]);
        }

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
