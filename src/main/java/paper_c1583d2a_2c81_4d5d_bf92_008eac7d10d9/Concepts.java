package paper_c1583d2a_2c81_4d5d_bf92_008eac7d10d9;

/**
 * 《宗教正反饋循環與現代化碰撞動力學》
 * Collision Dynamics between Religious Positive Feedback Loops and Modernization
 * UUID: c1583d2a-2c81-4d5d-bf92-008eac7d10d9
 *
 * 跨論文引用（fully qualified，避免 Redundancy 同名衝突）：
 *   SFD v5.0         = paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts
 *   宗教認識論比較     = paper_6bbcc745_22c0_46ea_8a0a_63bd15c94e32.Concepts
 *   世俗成功的天花板   = paper_d5c8a3f7_9e2b_4d1a_b6f4_e0a2c8d4f6b8.Concepts
 *
 * 同名聲明：本篇 Redundancy 與 paper_d5c8a3f7…Redundancy 無關，獨立定義
 */
public final class Concepts {

    private Concepts() {}

    // ================================================================
    //  宗教正反饋循環（來源：論文 3.1）
    // ================================================================

    /** F₀: 核心信號進入個體 */
    public record CoreSignalEntry() {}

    /** F₁: 信將信號轉化為語意 */
    public record FaithTransformation() {}

    /** F₂: 語意在群體層面功能性收斂 */
    public record GroupConvergence() {}

    /** F₃: 收斂產物編碼進信號容器 */
    public record InstitutionalEncoding() {}

    /** F₄: 死亡壓力驅動新一代進入 F₀ */
    public record DeathPressureDrive() {}

    /** 宗教正反饋循環 F（以 TranscendenceMechanism 為核心信號源） */
    public record ReligiousFeedbackLoop(
        CoreSignalEntry coreSignalEntry,
        FaithTransformation faithTransformation,
        GroupConvergence groupConvergence,
        InstitutionalEncoding institutionalEncoding,
        DeathPressureDrive deathPressureDrive,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.PositiveFeedbackLoop positiveFeedbackLoop
    ) {}

    // ================================================================
    //  資源競爭結構（來源：論文 3.1）
    // ================================================================

    /** 兩個正反饋循環爭奪同一批資源 */
    public record ResourceCompetition(
        ReligiousFeedbackLoop religiousFeedbackLoop,
        paper_d5c8a3f7_9e2b_4d1a_b6f4_e0a2c8d4f6b8.Concepts.ModernizationCycle modernizationCycle
    ) {}

    /** M 回報可量化，F 回報不可量化（DissipationLaw 推論） */
    public record QuantifiabilityAsymmetry() {}

    // ================================================================
    //  家庭冗餘 R（來源：論文 3.2）
    // ================================================================

    /** 母親的非市場時間 */
    public record MotherTime() {}

    /** 兒童的非制度化時間 */
    public record ChildTime() {}

    /** 老人在家庭中的存在 */
    public record ElderPresence() {}

    /** 家族網絡 */
    public record KinNetwork() {}

    /** 家庭儀式時間 */
    public record RitualTime() {}

    /** 家庭的物理空間 */
    public record FamilySpace() {}

    /**
     * 家庭冗餘：嵌在關係結構中的代際傳遞資源
     * 同名聲明：與 paper_d5c8a3f7…Redundancy 無關，本篇獨立定義
     */
    public record Redundancy(
        MotherTime motherTime,
        ChildTime childTime,
        ElderPresence elderPresence,
        KinNetwork kinNetwork,
        RitualTime ritualTime,
        FamilySpace familySpace
    ) {}

    /** 母親：R 的組織者 ∧ 日常浸泡的主要提供者 */
    public record MotherRole() {}

    /** 父親：家庭結構的錨點 */
    public record FatherRole() {}

    /** 信的代際傳遞條件（≠ 信本身的傳遞，DissipationLaw 禁止） */
    public record FaithTransmission() {}

    // ================================================================
    //  無冗餘狀態與市場替代（來源：論文 3.3）
    // ================================================================

    /** R = ∅ */
    public record ZeroRedundancyState() {}

    /** 家庭功能外包給市場（市場傳遞 Signal，不傳遞 Xin） */
    public record MarketSubstitution() {}

    /** M 先抽走 ChildTime 再抽走 MotherTime → ZeroRedundancyState */
    public record RedundancyDrain() {}

    /** 一整代人在純 Signal 環境中長大 → EpistemicBarrier 排斥宗教 Signal */
    public record EpistemicBarrierFormation() {}

    /** 世俗化機制三步 */
    public record SecularizationMechanism(
        RedundancyDrain redundancyDrain,
        MarketSubstitution marketSubstitution,
        EpistemicBarrierFormation epistemicBarrierFormation
    ) {}

    /** R = ∅ 後社會的兩種死法（且僅此二者） */
    public sealed interface DeathMode
        permits SlowDeath, FastDeath {}

    /** 慢死：代際傳遞斷裂 → 價值觀消失 → 生育率崩潰 → 崩潰 */
    public record SlowDeath() implements DeathMode {}

    /** 快死：風險緩衝歸零 → 任何震盪直接擊穿社會 */
    public record FastDeath() implements DeathMode {}

    // ================================================================
    //  路徑分化（來源：論文 3.4）
    // ================================================================

    /** 三條路徑對 R 消耗的反應（且僅此三者） */
    public sealed interface PathResponse
        permits EvolutionaryPathResponse,
               LockdownPathResponse,
               SelfOrganizationPathResponse {}

    /** 蠱王路徑：排他性淘汰 → 市場競爭 → 與市場不可區分 */
    public record EvolutionaryPathResponse(
        paper_6bbcc745_22c0_46ea_8a0a_63bd15c94e32.Concepts.EvolutionaryPath evolutionaryPath
    ) implements PathResponse {}

    /** M 通過外力抽走 R 的施壓方式 */
    public record ExternalPressure() {}

    /** 保護 R 的制度權力腐敗 → Signal 端被政治綁架 */
    public record InternalCorrosion() {}

    /** 鎖定路徑：拒絕接受 → 制度維持 R → 拉鋸 → 擋不住 */
    public record LockdownPathResponse(
        ExternalPressure externalPressure,
        InternalCorrosion internalCorrosion,
        paper_6bbcc745_22c0_46ea_8a0a_63bd15c94e32.Concepts.LockdownPath lockdownPath
    ) implements PathResponse {}

    /** 修行成為階級特權 */
    public record PracticeStratification() {}

    /** 寺廟從修行場所轉型為服務供應商 */
    public record ServiceProviderTransformation() {}

    /** 個體修行的代價被轉嫁為金錢支付 */
    public record CostTransfer() {}

    /** 內化路徑：修行入口被堵 → 階級化 → 服務化 → 消亡 */
    public record SelfOrganizationPathResponse(
        PracticeStratification practiceStratification,
        ServiceProviderTransformation serviceProviderTransformation,
        CostTransfer costTransfer,
        paper_6bbcc745_22c0_46ea_8a0a_63bd15c94e32.Concepts.SelfOrganizationPath selfOrganizationPath
    ) implements PathResponse {}

    /** 蠱王路徑 + 鎖定路徑的混合體（天主教） */
    public record CatholicSpecialPosition(
        paper_6bbcc745_22c0_46ea_8a0a_63bd15c94e32.Concepts.EvolutionaryPath evolutionaryPath,
        paper_6bbcc745_22c0_46ea_8a0a_63bd15c94e32.Concepts.LockdownPath lockdownPath
    ) {}

    // ================================================================
    //  崩潰-重啟周期（來源：論文 3.5）
    // ================================================================

    /** M 的份額只增不減（物質期望基線永久拉高） */
    public record RatchetEffect() {}

    /** 文明層面的崩潰-重啟周期 */
    public record CollapseRestartCycle() {}

    /** 崩潰的三種尺度（且僅此三者，只有 CivilizationalCollapse 觸發 CollapseRestartCycle） */
    public sealed interface CollapseScale
        permits IndividualUnemployment,
               EconomicCrisis,
               CivilizationalCollapse {}

    /** 個體失業：M 暫時放開一人 ∧ 基礎設施還在 → R 不恢復 */
    public record IndividualUnemployment() implements CollapseScale {}

    /** 經濟危機：M 局部停擺 ∧ 制度還在 → R 不恢復 */
    public record EconomicCrisis() implements CollapseScale {}

    /** 文明崩潰：M 基礎設施物理摧毀 → RatchetEffect 鬆開 → R 恢復 */
    public record CivilizationalCollapse() implements CollapseScale {}

    /** 崩潰後的路徑競爭（鎖定路徑有結構性優勢） */
    public record PathCompetitionAfterCollapse() {}

    // ================================================================
    //  經驗對照（來源：論文 4）
    // ================================================================

    /** 教會本身作為 R 的供應商（黑人教會反例） */
    public record RedundancySupplier() {}
}
