package paper_6bbcc745_22c0_46ea_8a0a_63bd15c94e32;

import core.*;

/**
 * 《基於知識流動與語意流動的三大宗教認識論比較》
 * Epistemological Comparison of Three Major Religions Based on Knowledge Flow and Semantic Flow Dynamics
 * UUID: 6bbcc745-22c0-46ea-8a0a-63bd15c94e32
 */
public class Concepts {

    // === 屬性型別：空概念（認知拓撲節點）===

    public record 認知極限() {}

    public record 認知極限內() {}

    public record 認知極限外() {}

    public record 蓋棺論定() {}

    // === 來源聲稱體系 ===

    public record 來源聲稱(
        認知極限外 域,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.通道 通道
    ) {}

    public record 認知極限外知識(
        來源聲稱 聲稱,
        認知極限外 域
    ) {}

    // === 五階段模型 ===

    public record 五階段模型(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信 起點,
        認知極限內 極限內,
        認知極限 極限,
        認知極限外 極限外,
        死亡 終端
    ) {}

    // === 通道狀態（開放／關閉／無中央）===

    public record 開放通道(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.通道 通道
    ) {}

    public record 關閉通道(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.通道 通道
    ) {}

    public record 無中央通道(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.通道 通道
    ) {}

    // === 共享放大元素 ===

    public record 信號容器制度化(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信號容器 容器,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.功能性收斂 收斂
    ) {}

    public record 漂移(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.正反饋循環 循環,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.功能性收斂 收斂,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信號 創始信號
    ) {}

    // === 三條動力學路徑 ===

    public record 蠱王路徑(
        開放通道 通道狀態,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.轉化 轉化,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.正反饋循環 循環,
        漂移 語意漂移
    ) {}

    public record 鎖定路徑(
        關閉通道 通道狀態,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信號 鎖定信號,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.轉化 轉化,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.正反饋循環 循環
    ) {}

    public record 自組織路徑(
        無中央通道 通道狀態,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.轉化 轉化,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.正反饋循環 循環
    ) {}

    // === 佛教內部流動模式 ===

    public record 上行模式(
        認知極限 目標,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信 信
    ) {}

    public record 準下行模式(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.通道 通道,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信號 信號
    ) {}

    public record 通道指定模式(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.通道 通道
    ) {}

    // === 知識流動方向模式 ===

    public record 下行模式(
        認知極限外 來源,
        認知極限內 目標,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.通道 通道
    ) {}

    // === 語意回流路徑 ===

    public record 外化路徑(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信號 信號,
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.信號容器 信號容器
    ) {}

    public record 內化路徑(
        paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139.Concepts.語意流 語意流
    ) {}
}
