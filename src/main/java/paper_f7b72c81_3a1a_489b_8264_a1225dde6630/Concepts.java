package paper_f7b72c81_3a1a_489b_8264_a1225dde6630;

import core.*;

/**
 * 《資訊不可能三角》
 * The Information Trilemma
 * UUID: f7b72c81-3a1a-489b-8264-a1225dde6630
 */
public class Concepts {

    // === 核心定義（論文第三節） ===

    public record Knowledge() {}

    public record Scale(Knowledge knowledge) {}

    public record Logic() {}

    public record Truth() {}

    // === 機制概念（論文第四節） ===

    public record Rule() {}

    public record CombinatorialManifestation(Rule rule) {}

    // === 核心結構（論文第二節） ===

    public record InformationTrilemma(
        Scale scale,
        Logic logic,
        Truth truth
    ) {}

    // === 加重條件（論文第十、十一節） ===

    public record KnowledgeLoss() {}

    public record IrreproducibleExperiment() {}

    public record UnexplainedParameter() {}

    public record ContradictoryConclusion() {}

    public record PostHocRationalization() {}

    // === LLM 約束（論文第十二、十四節） ===

    public record TrainingData(Knowledge knowledge) {}

    public record UnifiedParameters() {}

    public record Hallucination() {}

    public record AvoidanceStrategy() {}

    // === 邊界條件（論文第十六節） ===

    public record Interaction() {}

    // === 公設 ===

    public static final Postulate F1_RULE_CROSS_SCALE = new Postulate(
        "f7b72c81-3a1a-489b-8264-a1225dde6630_RuleCrossScale",
        "基本規則跨尺度存在"
    );

    public static final Postulate F2_PHENOMENON_AS_COMBINATORIAL_MANIFESTATION = new Postulate(
        "f7b72c81-3a1a-489b-8264-a1225dde6630_PhenomenonAsCombinatorialManifestation",
        "具體現象 = 規則在特定尺度下的組合表現"
    );

    public static final Postulate F3_COMBINATORIAL_MANIFESTATION_SCALE_DEPENDENCE = new Postulate(
        "f7b72c81-3a1a-489b-8264-a1225dde6630_CombinatorialManifestationScaleDependence",
        "組合表現隨尺度改變：C(Rule, s₁) ≠ C(Rule, s₂) 當 s₁ ≠ s₂"
    );

    public static final Postulate F4_KNOWLEDGE_LOSS_OBSERVATION = new Postulate(
        "f7b72c81-3a1a-489b-8264-a1225dde6630_KnowledgeLossObservation",
        "知識不完整反映現實"
    );
}
