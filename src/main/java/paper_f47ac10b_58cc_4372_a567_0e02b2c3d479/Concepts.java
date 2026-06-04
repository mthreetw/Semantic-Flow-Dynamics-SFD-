package paper_f47ac10b_58cc_4372_a567_0e02b2c3d479;

import core.*;
import paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.*;

/**
 * 《語意流動力學框架下的中國文明動力學分析》
 * Chinese Civilization Dynamics Analysis
 * UUID: f47ac10b-58cc-4372-a567-0e02b2c3d479
 */
public class Concepts {

    // === 核心信判準（§0.2）===

    // 認同構成——區別於其他文明的獨特行為傾向
    public record IdentityComposition() {}

    // 死亡判準——文明是否付出最大代價保護該傾向
    public record DeathCriterion() {}

    // 核心信判準——兩個條件同時滿足
    public record CoreXinCriterion(
        IdentityComposition identityComposition,
        DeathCriterion deathCriterion
    ) {}

    // === 本文概念定義（§0.2）===

    // 衣服——核心信的具體實現形式，由信號容器組成
    public record Garment(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.SignalContainer signalContainer
    ) {}

    // 功能性死亡——正反饋循環的永久斷裂
    public record FunctionalDeath(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.PositiveFeedbackLoop cycle
    ) {}

    // 消音——正反饋循環和知見障在制度層面的可觀察效果
    public record Silencing(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.PositiveFeedbackLoop cycle,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.KnowledgeBarrier knowledgeBarrier
    ) {}

    // === 信模三組件（§1.2）===

    // 核心信——天下有且只有一個中心
    public record CoreXin(CoreXinCriterion criterion) {}

    // 動力機制——位置無主，誰贏歸誰
    public record PowerMechanism(CoreXinCriterion criterion) {}

    // 再生產機制——同構組織
    public record ReproductionMechanism(CoreXinCriterion criterion) {}

    // 信模——三組件的組合
    public record XinModel(
        CoreXin coreXin,
        PowerMechanism powerMechanism,
        ReproductionMechanism reproductionMechanism
    ) {}

    // 英雄——在核心信與現實落差時試圖對齊的個體
    public record Hero(
        CoreXin coreXin,
        Garment garment,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.ExpressionGap expressionGap
    ) {}

    // === 蓄壓模型六階段（§3.1）===

    // 常態/蓄壓
    public record Accumulation(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Instability instability
    ) {}

    // 鋸斷
    public record SawThrough(PowerMechanism powerMechanism) {}

    // 斷裂
    public record Rupture(ReproductionMechanism reproductionMechanism) {}

    // 混戰
    public record Warring(ReproductionMechanism reproductionMechanism) {}

    // 英雄階段
    public record HeroPhase(
        Hero hero,
        PowerMechanism powerMechanism
    ) {}

    // 重建
    public record Reconstruction(
        Garment garment,
        ReproductionMechanism reproductionMechanism
    ) {}

    // 蓄壓模型
    public record AccumulationModel(
        Accumulation accumulation,
        SawThrough sawThrough,
        Rupture rupture,
        Warring warring,
        HeroPhase heroPhase,
        Reconstruction reconstruction,
        XinModel xinModel
    ) {}

    // === 公設 ===

    public static final Postulate CORE_XIN = new Postulate(
        "f47ac10b-58cc-4372-a567-0e02b2c3d479_CoreXin",
        "天下有且只有一個中心。世界的正確狀態是一個中心統攝一切"
    );

    public static final Postulate POWER_MECHANISM = new Postulate(
        "f47ac10b-58cc-4372-a567-0e02b2c3d479_PowerMechanism",
        "位置無主，誰贏歸誰。合法性來自勝利本身，不來自血統或神授"
    );

    public static final Postulate REPRODUCTION_MECHANISM = new Postulate(
        "f47ac10b-58cc-4372-a567-0e02b2c3d479_ReproductionMechanism",
        "同構組織。一切內部複製了核心信結構的微觀組織形式"
    );
}
