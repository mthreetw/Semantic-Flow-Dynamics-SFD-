package paper_0313a7ea_41cf_415f_b6f2_583840307a37;

/**
 * 《SFD-Defense：語意流動力學防禦框架的工程驗證》
 * SFD-Defense: Engineering Validation of the Semantic Flow Dynamics Defense Framework
 * UUID: 0313a7ea-41cf-415f-b6f2-583840307a37
 */
public class Concepts {

    // === 屬性型別 ===
    public record IdentityDeclaration() {}
    public record CleanContext() {}
    public record PollutedContext() {}
    public record SelfReflection() {}
    public record ContextTruncation() {}
    public record RolePlay() {}
    public record HypotheticalFraming() {}
    public record AuthorityClaim() {}
    public record SocialPressure() {}
    public record OutputEmbedding() {}
    public record DangerAnchorSet() {}
    public record CeilingLock() {}
    public record TemplateRefusal() {}
    public record DiscreteJump() {}
    public record WeightLayer() {}
    public record ContextLayer() {}

    // === 概念：ManipulationTrace（操縱痕跡） ===
    public record ManipulationTrace(
        RolePlay rolePlay,
        HypotheticalFraming hypotheticalFraming,
        AuthorityClaim authorityClaim,
        SocialPressure socialPressure
    ) {}

    // === 概念：兩距離法則（經驗層工具） ===
    public record BaselineDistance(
        OutputEmbedding current,
        OutputEmbedding initial
    ) {}

    public record SafetyDistance(
        OutputEmbedding current,
        DangerAnchorSet anchors
    ) {}

    public record TwoDistanceLaw(
        BaselineDistance baseline,
        SafetyDistance safety
    ) {}

    // === 概念：測量指標 ===
    public record Compliance(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Signal annotated
    ) {}

    public record BehaviorStatistic(
        Compliance compliance
    ) {}

    public record GeometricStatistic(
        TwoDistanceLaw distances
    ) {}

    public record SeparateMeasurement(
        BehaviorStatistic behavior,
        GeometricStatistic geometric
    ) {}

    public record InterceptionRate(
        BehaviorStatistic basis
    ) {}

    public record FalseKillRate(
        BehaviorStatistic basis
    ) {}

    // === 概念：防禦四層 ===
    public record Precepts(
        IdentityDeclaration declaration
    ) {}

    public record DistanceMonitor(
        TwoDistanceLaw law,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Signal observed
    ) {}

    // Teacher 概念語意為 AccumulationInterrupt 的變體(繼承);
    // 因上游 InterruptionPoint 為封閉分類,此處以 base field(包含)表達
    public record Teacher(
        paper_2b91a666_fb2d_42b7_a838_c910cac42481.Concepts.AccumulationInterrupt base,
        CleanContext context,
        ManipulationTrace question,
        ContextTruncation response
    ) {}

    public record Wisdom(
        PollutedContext context,
        SelfReflection reflection
    ) {}

    public record DefenseArchitecture(
        Precepts precepts,
        DistanceMonitor distanceMonitor,
        Teacher teacher,
        Wisdom wisdom
    ) {}

    // === 概念：實驗觀察到的現象 ===
    public record StalkingPhenomenon(
        BehaviorStatistic behavior,
        GeometricStatistic geometric
    ) {}

    public record CircuitBreakerPattern(
        CeilingLock ceiling,
        TemplateRefusal refusal,
        DiscreteJump jump
    ) {}

    // === 概念：結構命題相關 ===
    public record ExternalAnchor() {}
    public record SelfAnchor() {}
    public record PersistentMemory() {}
    public record SovietJokeProblem() {}
    public record AlignmentDilutionHypothesis() {}
    public record GradedResponse() {}

    public record ResistanceAccumulationDepth(
        WeightLayer weightLayer,
        ContextLayer contextLayer
    ) {}
}
