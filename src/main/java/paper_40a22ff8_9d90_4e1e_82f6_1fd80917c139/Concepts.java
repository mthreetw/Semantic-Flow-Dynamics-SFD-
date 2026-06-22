package paper_40a22ff8_9d90_4e1e_82f6_1fd80917c139;

import core.*;

/**
 * 《語意流動力學》
 * Semantic Flow Dynamics
 * UUID: 40a22ff8-9d90-4e1e-82f6-1fd80917c139
 */
public class Concepts {

    // === 公設 ===

    public static final Postulate 流變律 = new Postulate(
        "流變律",
        "∀個體 ∀t: 信(個體, t+1) ≠ 信(個體, t)。語意流持續運作，不存在靜止的時刻。"
    );

    public static final Postulate 黑箱律 = new Postulate(
        "黑箱律",
        "¬∃f: 信(個體, t+1) = f(信(個體, t), 信號(t))。語意流的下一刻方向不可由當下方向與信號決定。"
    );

    public static final Postulate 損耗律 = new Postulate(
        "損耗律",
        "¬∃g: 語意流(個體) → 信號 · Lossless(g)。語意流不可還原為信號。"
    );

    public static final Postulate 死亡律 = new Postulate(
        "死亡律",
        "死亡(個體) → 語意流(個體) terminates ∧ ¬∃recovery。個體消亡，語意流不可恢復。"
    );

    // === 定義層 ===

    public record 信(個體 主體) {}

    public record 語意流(信 狀態) {}

    public record 信號(刺激 源刺激) {}

    public record 噪音(刺激 源刺激) {}

    public record 信號容器(信號 儲存信號) {}

    public record 通道(信號 攜帶信號, 信號容器 中介) {}

    public record 信任(語意流 加權語意流, 通道 目標通道) {}

    // === 基本功能層：屬性型別 ===

    public record 阻力() {}

    public record 信號扭曲() {}

    // === 基本功能層：過濾 ===

    public record 過濾(語意流 當前語意流, 阻力 過濾阻力) {}

    public record 知見障(過濾 源過濾, 阻力 結構性阻力) {}

    // === 基本功能層：轉化 ===

    public record 轉化(語意流 處理語意流) {}

    public record 觀察者效應(轉化 觀察轉化) {}

    // === 基本功能層：坍塌 ===

    public record 坍塌(語意流 輸出語意流, 信號 輸出信號) {}

    public record 表達落差(坍塌 源坍塌) {}

    // === 正反饋循環層 ===

    public record 正反饋循環(過濾 循環過濾, 轉化 循環轉化, 坍塌 循環坍塌) {}

    public record 功能性收斂(正反饋循環 收斂循環) {}

    // === 循環不穩定性 ===

    public record 不穩定性內部來源() {}

    public record 不穩定性外部來源(信號容器 變化容器) {}

    public record 循環不穩定性(
        正反饋循環 不穩定循環,
        不穩定性內部來源 內部,
        不穩定性外部來源 外部
    ) {}

    // === 代際替換 ===

    public record 代際替換(死亡 死亡約束) {}

    // === 驅動力 ===

    public record 生存與物質利益(刺激 內部刺激) {}

    public record 安全與威脅(刺激 威脅刺激) {}

    public record 權力(通道 控制通道, 信號容器 控制容器) {}

    public record 意義追尋(信號 尋求信號, 轉化 主動轉化) {}

    public record 認同需求(語意流 趨近語意流) {}

    // === 循環替代模型：階段 ===

    public record 循環形成(正反饋循環 新循環, 信號 啟動信號) {}

    public record 壓制(權力 壓制權力, 信號 壓制信號) {}

    public record 落差擴大(信號容器 宣稱容器, 信號 新信號) {}

    public record 替換(代際替換 世代替換) {}

    public record 穩定(正反饋循環 主流循環, 信號容器 新容器, 阻力 新阻力) {}

    public record 循環替代(
        循環形成 形成,
        壓制 壓制階段,
        落差擴大 落差,
        替換 替換階段,
        穩定 穩定階段
    ) {}

    // === 必要條件 ===

    public record 信號容器進入(信號 新信號, 信號容器 目標容器) {}

    public record 持續新信號源(信號 持續信號) {}

    public record 對等信號缺失(信號 缺失信號) {}

    public record 死亡壓力(死亡 死亡約束) {}
}
