package paper_e5f6a7b8_c9d0_4e1f_a2b3_c4d5e6f7a8b9;

import core.*;

/**
 * 《蓄壓模型：中央集權文明的動力學》
 * Pressure Accumulation Model: Dynamics of Centralized Civilizations
 * UUID: e5f6a7b8-c9d0-4e1f-a2b3-c4d5e6f7a8b9
 */
public class Concepts {

    // === 前置條件 ===

    /** C₀ 的維持需要資源向中心集中 */
    public record ResourceConcentration() {}

    /** C₀ 需要地方代理人執行控制 */
    public record LocalAgentRequirement() {}

    // === 壓力線 ===

    /** 底層資源減少（PressureLine₁） */
    public record PressureLine1() {}

    /** 代理人獨立性累積（PressureLine₂） */
    public record PressureLine2() {}

    /** 容器耗竭：兩條壓力線同時作用的結果 */
    public record ContainerExhaustion(
        PressureLine1 bottomPressure,
        PressureLine2 agentPressure
    ) {}

    // === 循環階段 ===

    /** 蓄壓：C₀ 穩定運作，底層壓力與代理人獨立性同時累積 */
    public record Accumulation() {}

    /** 鋸：容器被反覆衝擊至耗竭 */
    public record SawThrough() {}

    /** 斷裂：容器碎裂，中心消失，信號容器存活 */
    public record Rupture() {}

    /** 混戰：所有人搶同一個位置 */
    public record Warring() {}

    /** 重建：勝出者重建中心，循環重啟 */
    public record Reconstruction() {}

    // === 超越性權威與獨立執行機構 ===

    /** 宣稱高於政治中心的權威 */
    public record TranscendentAuthority() {}

    /** 執行能力不依賴政治中心授權且政治中心無法單方面關閉的結構 */
    public record IndependentExecutiveBody() {}

    /** 活過混戰且新循環砍不掉的超越性權威 */
    public record SurvivingTranscendentAuthority(
        TranscendentAuthority transcendence,
        IndependentExecutiveBody executiveBody
    ) {}

    // === 免疫 ===

    /** 中心執行的免疫（集中式） */
    public record CentralizedImmunity() {}

    /** 同構組織執行的免疫（分布式） */
    public record DistributedImmunity() {}

    /** 免疫反應：至高王權的結構性行為，僅此二者 */
    public sealed interface ImmuneResponse permits AccumulationPhaseImmunity, ReconstructionMomentImmunity {}

    /** 蓄壓期免疫：循環穩定時中心有壓倒性資源 */
    public record AccumulationPhaseImmunity() implements ImmuneResponse {}

    /** 重建瞬間免疫：新循環啟動後立即排斥存活的超越性權威 */
    public record ReconstructionMomentImmunity() implements ImmuneResponse {}

    // === 至高王權 ===

    /** C₀ 結構性地排斥任何宣稱高於政治中心的權威 */
    public record SupremeKingship(
        CentralizedImmunity centralizedImmunity,
        DistributedImmunity distributedImmunity
    ) {}

    // === 分支條件 ===

    /** 重建完成瞬間是否存在活過混戰且新循環砍不掉的獨立超越性權威 */
    public record BranchCondition(
        SurvivingTranscendentAuthority target
    ) {}

    // === 信號容器改造 ===

    /** 循環主動改造信號容器，使之與循環方向相容 */
    public record SignalContainerReformation(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Filtering filtering,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Transformation transformation
    ) {}

    // === 同構組織 ===

    /** 內部複製了中央集權結構的微觀組織，具有再生產與分布式免疫雙重功能 */
    public record IsomorphicOrganization(
        paper_f47ac10b_58cc_4372_a567_0e02b2c3d479.Concepts.ReproductionMechanism reproduction,
        DistributedImmunity distributedImmunity
    ) {}

    // === 馴服 ===

    /** 至高王權對超越性權威的持續壓制，需要 C₀ 持續運作 */
    public record Domestication() {}

    // === 蓄壓模型（主概念） ===

    /** 中央集權文明的通用動力學模型 */
    public record AccumulationModel(
        ResourceConcentration resourceConcentration,
        LocalAgentRequirement localAgentRequirement,
        Accumulation accumulation,
        SawThrough sawThrough,
        Rupture rupture,
        Warring warring,
        Reconstruction reconstruction,
        SupremeKingship supremeKingship,
        BranchCondition branchCondition,
        SignalContainerReformation signalContainerReformation,
        IsomorphicOrganization isomorphicOrganization,
        Domestication domestication,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.PositiveFeedbackLoop cycle,
        paper_f47ac10b_58cc_4372_a567_0e02b2c3d479.Concepts.FunctionalDeath exitCondition
    ) {}
}
