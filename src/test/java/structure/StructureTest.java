package structure;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

public class StructureTest {

	private final JavaClasses allClasses = new ClassFileImporter()
		    .importPaths("target/classes");

    // 預建：外層類名 → 其所有巢狀類（供測試二使用，O(n) 建表，O(1) 查詢）
    private final Map<String, Set<JavaClass>> innerMap = buildInnerMap();

    private Map<String, Set<JavaClass>> buildInnerMap() {
        Map<String, Set<JavaClass>> map = new HashMap<>();
        for (JavaClass c : allClasses) {
            int idx = c.getName().indexOf('$');
            if (idx > 0) {
                String outer = c.getName().substring(0, idx);
                map.computeIfAbsent(outer, k -> new HashSet<>()).add(c);
            }
        }
        return map;
    }

    // 測試一：package 之間無循環依賴
    @Test
    public void noCyclicDependenciesBetweenPackages() {
        slices().matching("(*)..")
            .should().beFreeOfCycles()
            .check(allClasses);
    }

    // 測試二：同一 class 內部 nested record 和 sealed interface 之間無循環引用（DAG）
    @Test
    public void noInternalCyclicReferences() {
        classes().should(new ArchCondition<JavaClass>(
            "內嵌 Record / sealed interface 之間的引用必須是 DAG"
        ) {
            @Override
            public void check(JavaClass outerClass, ConditionEvents events) {
                Set<JavaClass> nested = innerMap.getOrDefault(outerClass.getName(), Set.of());
                if (nested.isEmpty()) return;

                Set<String> nestedNames = new HashSet<>();
                for (JavaClass n : nested) nestedNames.add(n.getName());

                Map<String, Set<String>> graph = new HashMap<>();
                for (JavaClass n : nested) {
                    Set<String> deps = new HashSet<>();
                    // 邊類型一：field 型別依賴
                    for (JavaField field : n.getFields()) {
                        String fieldType = field.getRawType().getName();
                        if (nestedNames.contains(fieldType)) deps.add(fieldType);
                    }
                    // 邊類型二：sealed interface → permits 的實作類
                    if (n.isInterface()) {
                        for (JavaClass candidate : nested) {
                            if (candidate.getInterfaces().stream()
                                .anyMatch(iface -> iface.getName().equals(n.getName()))) {
                                deps.add(candidate.getName());
                            }
                        }
                    }
                    graph.put(n.getName(), deps);
                }

                Set<String> visited = new HashSet<>();
                Set<String> inStack = new HashSet<>();
                for (String node : graph.keySet()) {
                    if (hasCycle(node, graph, visited, inStack)) {
                        events.add(SimpleConditionEvent.violated(outerClass,
                            outerClass.getName() + " 內部存在循環引用（含 sealed interface 展開）"));
                        return;
                    }
                }
            }

            private boolean hasCycle(String node, Map<String, Set<String>> graph,
                                     Set<String> visited, Set<String> inStack) {
                if (inStack.contains(node)) return true;
                if (visited.contains(node)) return false;
                visited.add(node);
                inStack.add(node);
                for (String dep : graph.getOrDefault(node, Set.of())) {
                    if (hasCycle(dep, graph, visited, inStack)) return true;
                }
                inStack.remove(node);
                return false;
            }
        }).check(allClasses);
    }

    // 測試三：record 中禁止聲明方法
    @Test
    public void noMethodsInRecords() {
        classes().that().areRecords()
            .should(new ArchCondition<JavaClass>("Record 中禁止聲明方法") {
                @Override
                public void check(JavaClass javaClass, ConditionEvents events) {
                    Set<String> fieldNames = new HashSet<>();
                    for (JavaField field : javaClass.getFields()) {
                        fieldNames.add(field.getName());
                    }

                    javaClass.getMethods().stream()
                        .filter(m -> !m.getName().equals("toString")
                                  && !m.getName().equals("hashCode")
                                  && !m.getName().equals("equals")
                                  && !fieldNames.contains(m.getName()))
                        .forEach(m -> events.add(SimpleConditionEvent.violated(javaClass,
                            javaClass.getName() + " 中存在禁止的方法：" + m.getName())));
                }
            }).check(allClasses);
    }

    // 測試四：record 的 field 禁止使用基本型別、java.util.* 和陣列
    @Test
    public void noForbiddenFieldTypes() {
        Set<String> forbiddenExact = Set.of(
            "int", "double", "boolean", "float", "long", "short", "byte", "char",
            "java.lang.String", "java.lang.Integer", "java.lang.Double",
            "java.lang.Boolean", "java.lang.Long"
        );

        classes().that().areRecords()
            .should(new ArchCondition<JavaClass>("Record 的 field 禁止使用基本型別、java.util.* 和陣列") {
                @Override
                public void check(JavaClass javaClass, ConditionEvents events) {
                    if (javaClass.getPackageName().equals("core")
                        && javaClass.getSimpleName().equals("Postulate")) return;

                    for (JavaField field : javaClass.getFields()) {
                        String typeName = field.getRawType().getName();
                        if (forbiddenExact.contains(typeName)) {
                            events.add(SimpleConditionEvent.violated(javaClass,
                                javaClass.getName() + "." + field.getName()
                                + " 使用了禁止的型別：" + typeName));
                        }
                        if (typeName.startsWith("java.util.")) {
                            events.add(SimpleConditionEvent.violated(javaClass,
                                javaClass.getName() + "." + field.getName()
                                + " 使用了禁止的 java.util 型別：" + typeName
                                + "（泛型擦除後 DAG 檢查失效）"));
                        }
                        if (typeName.startsWith("[")) {
                            events.add(SimpleConditionEvent.violated(javaClass,
                                javaClass.getName() + "." + field.getName()
                                + " 使用了禁止的陣列型別"));
                        }
                    }
                }
            }).check(allClasses);
    }

    // 測試五：record 中禁止 compact constructor（構造函數邏輯）
    @Test
    public void noCompactConstructorsInRecords() {
        classes().that().areRecords()
            .should(new ArchCondition<JavaClass>("Record 中禁止 compact constructor") {
                @Override
                public void check(JavaClass javaClass, ConditionEvents events) {
                    javaClass.getConstructors().forEach(c -> {
                        if (c.getMethodCallsFromSelf().stream()
                            .anyMatch(call -> !call.getTarget().getName().equals("<init>"))) {
                            events.add(SimpleConditionEvent.violated(javaClass,
                                javaClass.getName() + " 中存在禁止的 compact constructor 邏輯"));
                        }
                    });
                }
            }).check(allClasses);
    }

    // 測試六：每個 paper_ package 恰好一個外層 class，且命名為 Concepts
    @Test
    public void oneOuterClassPerPaperPackage() {
        Map<String, Set<String>> packageToOuterClasses = new HashMap<>();

        for (JavaClass javaClass : allClasses) {
            String pkg = javaClass.getPackageName();
            if (!pkg.startsWith("paper_")) continue;
            // 巢狀類的 JVM 名稱含 $，跳過
            if (javaClass.getName().contains("$")) continue;

            packageToOuterClasses
                .computeIfAbsent(pkg, k -> new HashSet<>())
                .add(javaClass.getName());
        }

        for (Map.Entry<String, Set<String>> entry : packageToOuterClasses.entrySet()) {
            if (entry.getValue().size() > 1) {
                throw new AssertionError(
                    entry.getKey() + " 中存在多個外層 class：" + entry.getValue()
                    + "。每個 paper_ package 恰好一個外層 class。");
            }
            for (String className : entry.getValue()) {
                if (!className.endsWith(".Concepts")) {
                    throw new AssertionError(
                        entry.getKey() + " 的外層 class 名為 " + className
                        + "，必須命名為 Concepts。");
                }
            }
        }
    }

    // 測試七：core 包禁止引用任何 paper_ 包
    @Test
    public void coreDoesNotDependOnPaper() {
        classes().that().resideInAPackage("core")
            .should(new ArchCondition<JavaClass>("core 包禁止引用 paper_ 包") {
                @Override
                public void check(JavaClass javaClass, ConditionEvents events) {
                    javaClass.getDirectDependenciesFromSelf().forEach(dep -> {
                        if (dep.getTargetClass().getPackageName().startsWith("paper_")) {
                            events.add(SimpleConditionEvent.violated(javaClass,
                                "core." + javaClass.getSimpleName()
                                + " 引用了 " + dep.getTargetClass().getName()
                                + "——core 禁止引用任何 paper_ 包"));
                        }
                    });
                }
            }).check(allClasses);
    }
}
