package structure;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StructureTest {

    private final JavaClasses allClasses = new ClassFileImporter()
        .importPaths("src/main/java");

    // 測試一：package 之間無循環依賴
    @Test
    public void noCyclicDependenciesBetweenPackages() {
        slices().matching("(*)..")
            .should().beFreeOfCycles()
            .allowEmptyShould(true)
            .check(allClasses);
    }

    // 測試二：同一 class 內部 nested record 之間無循環引用（DAG）
    @Test
    public void noInternalCyclicReferences() {
        classes().should(new ArchCondition<JavaClass>(
            "內嵌 Record 之間的引用必須是 DAG"
        ) {
            @Override
            public void check(JavaClass outerClass, ConditionEvents events) {
                Set<JavaClass> nested = outerClass.getAllSubclasses();
                if (nested.isEmpty()) return;

                Set<String> nestedNames = new HashSet<>();
                for (JavaClass n : nested) nestedNames.add(n.getName());

                Map<String, Set<String>> graph = new HashMap<>();
                for (JavaClass n : nested) {
                    Set<String> deps = new HashSet<>();
                    for (JavaField field : n.getFields()) {
                        String fieldType = field.getRawType().getName();
                        if (nestedNames.contains(fieldType)) deps.add(fieldType);
                    }
                    graph.put(n.getName(), deps);
                }

                Set<String> visited = new HashSet<>();
                Set<String> inStack = new HashSet<>();
                for (String node : graph.keySet()) {
                    if (hasCycle(node, graph, visited, inStack)) {
                        events.add(SimpleConditionEvent.violated(outerClass,
                            outerClass.getName() + " 內部存在 Record 循環引用"));
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
        }).allowEmptyShould(true).check(allClasses);
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
            }).allowEmptyShould(true).check(allClasses);
    }

    // 測試四：record 的 field 禁止使用基本型別、泛型集合和陣列
    @Test
    public void noForbiddenFieldTypes() {
        Set<String> forbidden = Set.of(
            "int", "double", "boolean", "float", "long", "short", "byte", "char",
            "java.lang.String", "java.lang.Integer", "java.lang.Double",
            "java.lang.Boolean", "java.lang.Long",
            "java.util.List", "java.util.Set", "java.util.Map", "java.util.Collection"
        );

        classes().that().areRecords()
            .should(new ArchCondition<JavaClass>("Record 的 field 禁止使用基本型別和泛型集合") {
                @Override
                public void check(JavaClass javaClass, ConditionEvents events) {
                    if (javaClass.getPackageName().equals("core")
                        && javaClass.getSimpleName().equals("Postulate")) return;

                    for (JavaField field : javaClass.getFields()) {
                        String typeName = field.getRawType().getName();
                        if (forbidden.contains(typeName)) {
                            events.add(SimpleConditionEvent.violated(javaClass,
                                javaClass.getName() + "." + field.getName()
                                + " 使用了禁止的型別：" + typeName));
                        }
                        if (typeName.startsWith("[")) {
                            events.add(SimpleConditionEvent.violated(javaClass,
                                javaClass.getName() + "." + field.getName()
                                + " 使用了禁止的陣列型別"));
                        }
                    }
                }
            }).allowEmptyShould(true).check(allClasses);
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
            }).allowEmptyShould(true).check(allClasses);
    }

    // 測試六：每個 paper_ package 恰好一個外層 class
    @Test
    public void oneOuterClassPerPaperPackage() {
        Map<String, Set<String>> packageToOuterClasses = new HashMap<>();

        for (JavaClass javaClass : allClasses) {
            String pkg = javaClass.getPackageName();
            if (!pkg.startsWith("paper_")) continue;
            if (javaClass.getEnclosingClass().isPresent()) continue;

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
            }).allowEmptyShould(true).check(allClasses);
    }
}