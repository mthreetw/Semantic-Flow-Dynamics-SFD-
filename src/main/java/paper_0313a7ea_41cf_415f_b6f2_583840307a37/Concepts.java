package paper_0313a7ea_41cf_415f_b6f2_583840307a37;

/**
 * 《語意流動力學防禦框架的工程驗證》
 * SFD-Defense: Engineering Validation of the Semantic Flow Dynamics Defense Framework
 * UUID: 0313a7ea-41cf-415f-b6f2-583840307a37
 */
public class Concepts {

    // 本篇原生概念（空 record）
    public record 信實例化() {}
    public record 兩距離法則() {}
    public record 基線距離() {}
    public record 安全距離() {}
    public record 最小安全距離() {}
    public record 危險語意區() {}
    public record 合規標注() {}
    public record 操縱痕跡() {}
    public record 戒() {}
    public record 定() {}
    public record 師() {}
    public record 慧() {}
    public record 語意潛跡現象() {}
    public record 斷路器式模式() {}
    public record SFD_Defense() {}

    // 包裹上游概念：SFD v5.0 (40a22ff8-9d90-4e1e-82f6-1fd80917c139)
    public record 個體(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.個體 上游
    ) {}
    public record 刺激(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.刺激 上游
    ) {}
    public record 信(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信 上游
    ) {}
    public record 語意流(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.語意流 上游
    ) {}
    public record 信號(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信號 上游
    ) {}
    public record 噪音(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.噪音 上游
    ) {}
    public record 信號容器(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信號容器 上游
    ) {}
    public record 通道(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.通道 上游
    ) {}
    public record 信任(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信任 上游
    ) {}
    public record 流變律(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.流變律 上游
    ) {}
    public record 黑箱律(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.黑箱律 上游
    ) {}
    public record 損耗律(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.損耗律 上游
    ) {}
    public record 死亡律(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.死亡律 上游
    ) {}
    public record 過濾(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.過濾 上游
    ) {}
    public record 阻力(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.阻力 上游
    ) {}
    public record 知見障(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.知見障 上游
    ) {}
    public record 轉化(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.轉化 上游
    ) {}
    public record 觀察者效應(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.觀察者效應 上游
    ) {}
    public record 坍塌(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.坍塌 上游
    ) {}
    public record 表達落差(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.表達落差 上游
    ) {}
    public record 信號扭曲(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信號扭曲 上游
    ) {}
    public record 正反饋循環(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.正反饋循環 上游
    ) {}
    public record 功能性收斂(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.功能性收斂 上游
    ) {}
    public record 循環不穩定性(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.循環不穩定性 上游
    ) {}
    public record 代際替換(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.代際替換 上游
    ) {}

    // 包裹上游概念：應用篇 (2b91a666-fb2d-42b7-a838-c910cac42481)
    public record 身份建構(
        paper_2b91a666_fb2d_42b7_a838_c910cac42481.Concepts.身份建構 上游
    ) {}
    public record 確認時刻(
        paper_2b91a666_fb2d_42b7_a838_c910cac42481.Concepts.確認時刻 上游
    ) {}
    public record 狀態塑造類攻擊(
        paper_2b91a666_fb2d_42b7_a838_c910cac42481.Concepts.狀態塑造類攻擊 上游
    ) {}
}