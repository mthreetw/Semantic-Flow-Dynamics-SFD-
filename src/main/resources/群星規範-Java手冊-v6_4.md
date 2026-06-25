# 群星規範——Java 手冊

Java 手冊版本：v6.4

*形式化文件 → Java 翻譯規則與結構測試。AI 執行 Java 翻譯任務時讀此文件，不讀其他三份。*

---

## 一、命名規則

### 1.1 論文 package 命名

每篇論文生成一個 UUID（v4）作為唯一識別碼。package 名由 UUID 機械式轉換：

1. 前綴 `paper_`
2. UUID 中所有 `-` 替換為 `_`

| UUID | Java package |
|---|---|
| `550e8400-e29b-41d4-a716-446655440000` | `paper_550e8400_e29b_41d4_a716_446655440000` |

### 1.2 外層 class 命名

所有論文的外層 class 統一命名為 `Concepts`，Java 檔名統一為 `Concepts.java`。

外層 class 的 Javadoc 必須包含三行：中文標題、英文標題、UUID：

```java
/**
 * 《中文標題》
 * English Title
 * UUID: 550e8400-e29b-41d4-a716-446655440000
 */
```

### 1.3 每個 `paper_` package 恰好一個外層 class

一個 `paper_` package 只能有一個外層 class，且必須命名為 `Concepts`。原因：ArchUnit 的 DAG 測試按外層 class 的 nested record 檢查循環引用。多個外層 class 會讓跨 class 引用跳過測試。

---

## 二、Java 層

### 2.1 唯一規則：record 只有兩種

Java 層只做一件事：追蹤概念是否存在、是否依賴直接上游概念。

**所有 record 只有兩種形式：**

```java
// 形式一：空 record（本篇原生概念、公設、原語）
public record 冗餘() {}
public record 流變律() {}
public record 個體() {}

// 形式二：單一 field record（包裹直接上游概念）
public record 市場(
    paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.市場 上游
) {}
```

規則：
- field 名稱統一為「上游」
- field 型別必須是直接上游論文（references 中列出的 UUID）的對應 record
- 每個 record 最多一個 field
- 禁止跨越一跳：C 不可直接包裹 A，必須通過 B

概念的內部組合（冗餘由哪些子概念構成）在形式邏輯層表達，不翻譯到 Java。Java 只追蹤「這個概念依賴哪個直接上游概念」。

### 2.2 允許的 Java 元素（白名單）

Java 文件中只允許以下元素，未列出的一律禁止：

- `public record`（空，或恰好一個 field）
- field 型別必須來自直接上游 `paper_` 包或 `ext.ext_` 包中的 record
- 引用方向必須是 DAG（無雙向、無循環）

**絕對禁止：** 基本型別（int, double, boolean, String）、`java.util.*`、陣列、interface、sealed interface、extends class、多個 field、任何方法或構造函數邏輯。

### 2.3 與 Java 層的銜接約束

約束條件：
- C6.7a：形式化文件中每個概念，翻譯為一個 record（空或單一 field）
- C6.7b：形式化文件中每個上游包裹關係，翻譯為單一 field 指向直接上游 record，field 名稱為「上游」
- C6.7c：形式化文件中所有其他關係（包含、蘊含、推導、循環、封閉分類等），不翻譯到 Java

### 2.4 文件結構模板

```java
package paper_550e8400_e29b_41d4_a716_446655440000;

import paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.*;
import paper_a1b2c3d4_e5f6_7890_abcd_ef1234567890.*;

/**
 * 《宗教正反饋循環與現代化碰撞動力學》
 * Religious Positive Feedback Loop and Modernization Collision Dynamics
 * UUID: 550e8400-e29b-41d4-a716-446655440000
 */
public class Concepts {

    // 本篇原生概念（空 record）
    public record 冗餘() {}
    public record 母親時間() {}
    public record 現代化碰撞() {}
    public record 冗餘結構性公設() {}

    // 包裹上游概念（單一 field）
    public record 個體(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.個體 上游
    ) {}
    public record 正反饋循環(
        paper_a1b2c3d4_e5f6_7890_abcd_ef1234567890.Concepts.正反饋循環 上游
    ) {}
}
```

---

## 三、結構測試

測四件事：

```java
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
        .importClasspath();

    // 測試一：package 之間無循環依賴
    @Test
    public void noCyclicDependenciesBetweenPackages() {
        slices().matching("(*)..")
            .should().beFreeOfCycles()
            .check(allClasses);
    }

    // 測試二：record 最多一個 field，且必須來自不同的 paper_ 或 ext.ext_ 包
    // （同時覆蓋原測試二的內部循環場景：field 不得指向同一 package 內的 record）
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
                    // 檢查顯式方法（排除 record 自動生成的 accessor、equals、hashCode、toString）
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

                    // 檢查 compact constructor 邏輯
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
            if (javaClass.getEnclosingClass().isPresent()) continue;

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
```

---

## 四、約束索引

| 編號 | 約束內容 | 檢查類型 |
|---|---|---|
| C6.7a | 每個概念翻譯為一個 record（空或單一 field） | 銜接 |
| C6.7b | 上游包裹關係翻譯為單一 field 指向直接上游 record，field 名稱為「上游」 | 銜接 |
| C6.7c | 所有其他關係不翻譯到 Java | 銜接 |
