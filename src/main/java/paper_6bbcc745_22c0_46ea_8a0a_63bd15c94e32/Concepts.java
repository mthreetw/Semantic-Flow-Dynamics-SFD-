package paper_6bbcc745_22c0_46ea_8a0a_63bd15c94e32;

import core.*;
import paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.*;

/**
 * 《基於知識流動與語意流動的三大宗教認識論比較》
 * Religion as Information Flow System
 * UUID: 6bbcc745-22c0-46ea-8a0a-63bd15c94e32
 */
public class Concepts {

    // === 五階段模型——階段屬性型別 ===

    // 起點：引用上游 Xin 作為認知體系的啟動條件
    public record StartPoint(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Xin xin
    ) {}

    // 認知極限內：可經驗、可推理、可驗證的知識域
    public record InsideDomain() {}

    // 認知極限：人承認「我不知道」的邊界
    public record CognitiveBoundary() {}

    // 認知極限外：不可驗證的知識域
    public record OutsideDomain() {}

    // 死亡終端：意識消失，信與語意歸零
    public record DeathTerminal() {}

    // === 概念：FiveStageModel（五階段模型） ===
    public record FiveStageModel(
        StartPoint start,
        InsideDomain inside,
        CognitiveBoundary boundary,
        OutsideDomain outside,
        DeathTerminal terminal
    ) {}

    // === 概念：DeathSettlement（蓋棺論定） ===
    // 死亡是結算點，結算者是活著的人
    public record DeathSettlement() {}

    // === 認知極限外知識 ===

    // 來源聲稱：宗教對極限外知識來源的宣稱
    public record SourceClaim() {}

    // 認知極限外知識：宗教體系聲稱來自極限另一側的知識
    public record BeyondBoundaryKnowledge(
        SourceClaim claim
    ) {}

    // === 共享放大元素——組成部分 ===

    // 信號轉化效果：引用上游 Transformation 在宗教語境的應用
    public record SignalTransformationEffect(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Transformation base
    ) {}

    // 收斂效果：引用上游 FunctionalConvergence 在宗教語境的應用
    public record ConvergenceEffect(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.FunctionalConvergence base
    ) {}

    // 信號容器制度化：收斂產物被編碼進信號容器
    public record Institutionalization(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.SignalContainer container
    ) {}

    // 死亡壓力：死亡作為持續壓力源，重新激活正反饋循環
    public record DeathPressure(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.PositiveFeedbackLoop loop
    ) {}

    // === 概念：AmplificationElement（共享放大元素） ===
    public record AmplificationElement(
        SignalTransformationEffect transformation,
        ConvergenceEffect convergence,
        Institutionalization institutionalization,
        DeathPressure deathPressure
    ) {}

    // === 路徑特徵屬性型別 ===

    // 信號鎖定：信號端被極端鎖定的狀態
    public record SignalLock() {}

    // 市場競爭：爭信徒、爭供養、爭影響力
    public record MarketCompetition() {}

    // 異端裁定：零和博弈，你的教義錯了你必須被消滅
    public record HeresyJudgment() {}

    // 方便法門：同一法用無限多種方式表達
    public record SkillfulMeans() {}

    // 外化路徑：語意回流到信號端（教義、法律、制度）
    public record ExternalizationPath() {}

    // 內化路徑：語意留在意識端（行為、習慣、生活方式）
    public record InternalizationPath() {}

    // 啟示下行：從極限外到極限內，方向單向，通道被指定
    public record RevelationDescending() {}

    // 內觀上行：個體通過修行自己觸及極限
    public record IntrospectionAscending() {}

    // 死亡編碼為通道（基督教）
    public record DeathAsPassage() {}

    // 死亡編碼為審判（伊斯蘭教）
    public record DeathAsJudgment() {}

    // 死亡編碼為轉化（佛教）
    public record DeathAsTransformation() {}

    // === 概念：EvolutionaryPath（蠱王路徑——基督教） ===
    // 開放通道 + 不鎖定信號 → 變異不斷產生
    public record EvolutionaryPath(
        AmplificationElement amplification,
        MarketCompetition market,
        HeresyJudgment heresy,
        ExternalizationPath semanticReturn,
        RevelationDescending flowDirection,
        DeathAsPassage deathEncoding
    ) {}

    // === 概念：LockdownPath（鎖定路徑——伊斯蘭教） ===
    // 關閉通道 + 極端鎖定信號 → 變異被壓制
    public record LockdownPath(
        AmplificationElement amplification,
        SignalLock signalLock,
        MarketCompetition market,
        HeresyJudgment heresy,
        ExternalizationPath semanticReturn,
        RevelationDescending flowDirection,
        DeathAsJudgment deathEncoding
    ) {}

    // === 概念：SelfOrganizationPath（自組織路徑——佛教） ===
    // 無中央通道 + 不鎖定 + 無異端裁定
    public record SelfOrganizationPath(
        AmplificationElement amplification,
        SkillfulMeans skillfulMeans,
        MarketCompetition market,
        InternalizationPath semanticReturn,
        IntrospectionAscending flowDirection,
        DeathAsTransformation deathEncoding
    ) {}
}
