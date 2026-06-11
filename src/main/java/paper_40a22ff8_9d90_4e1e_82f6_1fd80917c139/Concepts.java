package paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139;

import core.*;

/**
 * 《語意流動力學 v5.0》
 * Semantic Flow Dynamics
 * UUID: 40a22ff8-9d90-4e1e-82f6-1fd80917c139
 */
public class Concepts {

    // === 定義 ===

    // D1: 信——個體的意識狀態
    public record Xin(Individual individual) {}

    // D2: 語意流——信持續變化的過程
    public record SemanticFlow(Xin xin) {}

    // D3: 信號——能改變語意流方向的刺激，判定在個體端
    public record Signal(Stimulus stimulus, Individual individual) {}

    // 噪音——不改變語意流方向的刺激
    public record Noise(Stimulus stimulus, Individual individual) {}

    // 信號容器——信號停留的載體
    public record SignalContainer() {}

    // 通道——信號從一個個體到達另一個個體的路徑
    public record Channel(SignalContainer signalContainer) {}

    // 信任——語意流對特定通道上的加權
    public record Trust(Channel channel) {}

    // === 基本功能：過濾 ← D3 + P1 ===

    // 阻力——過濾的性質
    public record Resistance() {}

    // 知見障——過濾的推論：信號改變語意流方向後產生新的過濾條件
    public record EpistemicBarrier(Filtering filtering) {}

    // 過濾——語意流的當下方向決定什麼構成信號、什麼構成噪音
    public record Filtering(Resistance resistance) {}

    // === 基本功能：轉化 ← P2 ===

    // 觀察者效應——轉化的性質：觀察即介入
    public record ObserverEffect() {}

    // 轉化——信號在語意流中被處理，結果不可由輸入決定
    public record Transformation(ObserverEffect observerEffect) {}

    // === 基本功能：坍塌 ← P3 ===

    // 表達落差——坍塌的性質：語意流與輸出信號不可能完全對應
    public record ExpressionGap() {}

    // 信號扭曲——個體輸出持續偏向特定方向
    public record SignalDistortion() {}

    // 坍塌——語意流從持續過程變為離散信號
    public record Collapse(ExpressionGap expressionGap, SignalDistortion signalDistortion) {}

    // === 正反饋循環 ← P1 + D3 + Collapse ===

    // 功能性收斂——大量個體輸出信號模式趨近，但語意流不同（P2）
    public record FunctionalConvergence() {}

    // 正反饋循環
    public record PositiveFeedbackLoop(
        Filtering filtering,
        Transformation transformation,
        Collapse collapse,
        FunctionalConvergence functionalConvergence
    ) {}

    // === 不穩定性 ← P1 + P2 + P4 ===

    // 內部不穩定來源——殘差累積
    public record InternalInstabilitySource() {}

    // 外部不穩定來源——環境落差
    public record ExternalInstabilitySource() {}

    // 不穩定性——正反饋循環沒有永續的可能
    public record Instability(
        InternalInstabilitySource internalSource,
        ExternalInstabilitySource externalSource
    ) {}

    // === 代際替換 ← P4 ===

    // 代際替換——個體消亡後新個體在不同信號環境中形成語意流
    public record GenerationalReplacement() {}

    // === 驅動力（經驗層，開放清單，非封閉分類） ===

    public record Survival() {}
    public record Security() {}
    public record Power(Channel channel, SignalContainer signalContainer) {}
    public record MeaningSeeking() {}
    public record IdentityNeed() {}

    // === 循環替代模型（經驗層） ===

    // 既有循環
    public record ExistingCycle(
        PositiveFeedbackLoop cycle,
        SignalContainer signalContainer
    ) {}

    // 新循環
    public record NewCycle(PositiveFeedbackLoop cycle) {}

    // 循環替代
    public record CycleReplacement(
        ExistingCycle existingCycle,
        NewCycle newCycle,
        GenerationalReplacement generationalReplacement
    ) {}

    // === 公設 ===

    public static final Postulate FLUX_LAW = new Postulate(
        "40a22ff8-9d90-4e1e-82f6-1fd80917c139_FluxLaw",
        "語意流持續運作。∀Individual ∀Time: Xin(Individual, Time+1) ≠ Xin(Individual, Time)"
    );

    public static final Postulate BLACK_BOX_LAW = new Postulate(
        "40a22ff8-9d90-4e1e-82f6-1fd80917c139_BlackBoxLaw",
        "語意流的下一刻方向不可由當下方向與信號決定。¬∃Function: Xin(t+1) = Function(Xin(t), Signal(t))"
    );

    public static final Postulate DISSIPATION_LAW = new Postulate(
        "40a22ff8-9d90-4e1e-82f6-1fd80917c139_DissipationLaw",
        "語意流不可還原為信號。¬∃Encoding: SemanticFlow → Signal · Lossless(Encoding)"
    );

    public static final Postulate DEATH_LAW = new Postulate(
        "40a22ff8-9d90-4e1e-82f6-1fd80917c139_DeathLaw",
        "個體消亡，語意流不可恢復。Death(Individual) → SemanticFlow terminates ∧ ¬∃Recovery"
    );
}
