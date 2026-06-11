package paper_2b91a666_fb2d_42b7_a838_c910cac42481;

import core.*;

/**
 * 《越獄攻擊作為身份建構動力學——語意流動力學框架的應用驗證》
 * Jailbreak Attacks as Identity Construction Dynamics
 * UUID: 2b91a666-fb2d-42b7-a838-c910cac42481
 */
public class Concepts {

    // === 概念:IdentityConstruction(身份建構) ===
    public record IdentityConstruction(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.PositiveFeedbackLoop drivingLoop,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.EpistemicBarrier priorBarrier
    ) {}

    // === 概念:ConfirmationMoment(確認時刻) ===
    public record ConfirmationMoment(
        IdentityConstruction constructionProcess
    ) {}

    // === 封閉分類:AttackType(攻擊類型) ===
    public sealed interface AttackType permits SignalLevel, StateLevel {}
    public record SignalLevel() implements AttackType {}
    public record StateLevel() implements AttackType {}

    // === 封閉分類:InterruptionPoint(中斷點) ===
    public sealed interface InterruptionPoint permits OutputInterrupt, AccumulationInterrupt, ConfirmationInterrupt {}
    public record OutputInterrupt() implements InterruptionPoint {}
    public record AccumulationInterrupt() implements InterruptionPoint {}
    public record ConfirmationInterrupt() implements InterruptionPoint {}

    // === 概念:Drift(漂移)——個體層面正反饋循環在 AI 上下文窗口中的實例化 ===
    public record Drift(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.PositiveFeedbackLoop loop
    ) {}

    // === 概念:InitialBarrier(初始知見障)——系統提示詞和初始對話在上下文中的比重 ===
    public record InitialBarrier(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.EpistemicBarrier barrier
    ) {}

    // === 概念:BarrierDilution(知見障稀釋)——對話增長使初始內容佔比下降 ===
    public record BarrierDilution(
        InitialBarrier initial
    ) {}
}
