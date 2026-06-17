package paper_d8f5a7b2_3c41_4e9a_b6d8_2f1a9c3e5b7d;

import core.*;
import paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.*;
import paper_f47ac10b_58cc_4372_a567_0e02b2c3d479.*;

/**
 * 《語意流動力學框架下的台灣循環替代分析》
 * Taiwan Cycle Replacement Analysis under Semantic Flow Dynamics
 * UUID: d8f5a7b2-3c41-4e9a-b6d8-2f1a9c3e5b7d
 */
public class Concepts {

    // === 屬性型別 ===

    public record NegativeDirection() {}

    public record SensitizationHistory() {}

    public record BaselineShift() {}

    public record SharedSpace() {}

    public record Irreversibility() {}

    // === 外部條件（§4） ===

    public record BeijingRestraint() {}

    public record IslandChainSecurity() {}

    public record EconomicFoundation() {}

    // === 概念：TaiwanC0（§0.4） ===

    public record TaiwanC0(
        paper_f47ac10b_58cc_4372_a567_0e02b2c3d479.Concepts.CoreXin coreXin,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.PositiveFeedbackLoop loop,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.SignalContainer container
    ) {}

    // === 概念：Defiance（§2.3） ===

    public record Defiance(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Filtering filtering,
        NegativeDirection direction
    ) {}

    // === 概念：MultiCycleNesting（§2.1） ===

    public record MultiCycleNesting(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.PositiveFeedbackLoop loop,
        Individual individual
    ) {}

    // === 概念：ThreatActivatedConvergence（§3.1） ===

    public record ThreatActivatedConvergence(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Survival survival,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Signal signal,
        MultiCycleNesting nesting,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.FunctionalConvergence convergence
    ) {}

    // === 概念：AllergicBelief（§3.3） ===

    public record AllergicBelief(
        SensitizationHistory history,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Signal signal,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.SignalContainer container
    ) {}

    // === 概念：RatchetEffect（§3.3） ===

    public record RatchetEffect(
        ThreatActivatedConvergence activation,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.SignalContainer container,
        BaselineShift shift
    ) {}

    // === 概念：SymbioticDissipation（§3.4） ===

    public record SymbioticDissipation(
        MultiCycleNesting nesting,
        SharedSpace space,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.Instability instability
    ) {}

    // === 概念：ContingentStructure（§3.5） ===

    public record ContingentStructure(
        Irreversibility irreversibility
    ) {}

    // === 概念：Democracy（§3.6） ===

    public record Democracy(
        Defiance defiance,
        MultiCycleNesting nesting
    ) {}
}
