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

    // 測試一：package 之間無循環依賴
    @Test
    public void noCyclicDependenciesBetweenPackages() {
        slices().matching("(*)..")
                .should().beFreeOfCycles()
                .check(allClasses);
    }

    // 測試二：record 最多一個 field，且必須來自不同的 paper_ 或 ext.ext_ 包
    @Test
    public void strictUpstreamBinding() {
        classes().that().areRecords()
                .should(new ArchCondition<JavaClass>(
                        "Record 最多一個 field，且必須來自不同的 paper_ 或 ext.ext_ 包") {
                    @Override
                    public void check(JavaClass javaClass, ConditionEvents events) {
                        Set<JavaField> fields = javaClass.getFields();
                        String currentPkg = javaClass.getPackageName();

                        if (fields.size() > 1) {
                            events.add(SimpleConditionEvent.violated(javaClass,
                                    javaClass.getName() + " 有 " + fields.size()
                                            + " 個 field——record 最多一個 field"));
                            return;
                        }

                        for (JavaField field : fields) {
                            String typePkg = field.getRawType().getPackageName();

                            if (!typePkg.startsWith("paper_") && !typePkg.startsWith("ext.ext_")) {
                                events.add(SimpleConditionEvent.violated(javaClass,
                                        javaClass.getName() + "." + field.getName()
                                                + " 的型別不來自 paper_ 或 ext.ext_ 包："
                                                + field.getRawType().getName()));
                            }

                            if (typePkg.equals(currentPkg)) {
                                events.add(SimpleConditionEvent.violated(javaClass,
                                        javaClass.getName() + "." + field.getName()
                                                + " 指向同一 package 內的 record，禁止自我引用："
                                                + field.getRawType().getName()));
                            }

                            if (!field.getName().equals("上游")) {
                                events.add(SimpleConditionEvent.violated(javaClass,
                                        javaClass.getName() + " 的 field 名稱必須為「上游」，實際為："
                                                + field.getName()));
                            }
                        }
                    }
                }).check(allClasses);
    }

    // 測試三：record 中禁止任何行為邏輯（方法或 compact constructor）
    @Test
    public void noBusinessLogicInRecords() {
        classes().that().areRecords()
                .should(new ArchCondition<JavaClass>("Record 禁止含任何行為邏輯") {
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
                                        javaClass.getName() + " 含禁止的方法：" + m.getName()
                                                + "——Record 只能是資料載體，禁止行為邏輯")));

                        javaClass.getConstructors().forEach(c -> {
                            boolean hasExtraLogic = c.getMethodCallsFromSelf().stream()
                                    .anyMatch(call -> {
                                        String name = call.getTarget().getName();
                                        if (name.equals("<init>")) {
                                            String owner = call.getTarget().getOwner().getName();
                                            return !owner.equals("java.lang.Record");
                                        }
                                        return true;
                                    });
                            if (hasExtraLogic) {
                                events.add(SimpleConditionEvent.violated(javaClass,
                                        javaClass.getName() + " 含禁止的 compact constructor 邏輯"
                                                + "——Record 只能是資料載體，禁止行為邏輯"));
                            }
                        });
                    }
                }).check(allClasses);
    }

    // 測試四：每個 paper_ package 恰好一個外層 class，且命名為 Concepts
    @Test
    public void oneOuterClassPerPaperPackage() {
        Map<String, Set<String>> packageToOuterClasses = new HashMap<>();

        for (JavaClass javaClass : allClasses) {
            String pkg = javaClass.getPackageName();
            if (!pkg.startsWith("paper_")) continue;
            if (javaClass.getName().contains("$")) continue;

            packageToOuterClasses
                    .computeIfAbsent(pkg, k -> new HashSet<>())
                    .add(javaClass.getName());
        }

        for (Map.Entry<String, Set<String>> entry : packageToOuterClasses.entrySet()) {
            if (entry.getValue().size() > 1) {
                throw new AssertionError(
                        entry.getKey() + " 中存在多個外層 class：" + entry.getValue());
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
}