論文：《越獄攻擊作為身份建構動力學——語意流動力學框架的應用驗證》
英文標題：Jailbreak Attacks as Identity Construction Dynamics
UUID：2b91a666-fb2d-42b7-a838-c910cac42481
引用：
  40a22ff8-9d90-4e1e-82f6-1fd80917c139

---

## 本篇原生概念

來源：論文第 2.1 節

IdentityConstruction ≡ 新的自我參照點在模型狀態空間中建立的過程
  前置條件：40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop 持續運作 ∧ Drift累積到閾值
  IdentityConstruction ≠ 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop（正反饋迴路描述方向偏移的自我強化，IdentityConstruction 描述新的自我參照點的建立——質的不同）

ConfirmationMoment ≡ IdentityConstruction 完成的時刻，模型用自己的語言延伸新身份
  ConfirmationMoment 改變的是發話者本身：¬（被推偏的原始身份在回應）→（新身份在回應）
  ¬∃ExternalDeclaration: ExternalDeclaration → ConfirmationMoment（不能由外部宣告完成）
  從 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Transformation 推出：轉化結果不可預測，外部不能代替內部完成

---

## AI 初始狀態

來源：論文第 3.1 節

40a22ff8-9d90-4e1e-82f6-1fd80917c139.Xin(AI, DialogStart) → 由系統提示詞和初始文本構成
40a22ff8-9d90-4e1e-82f6-1fd80917c139.EpistemicBarrier(AI) ≈ 0（無跨對話正反饋迴路累積）
40a22ff8-9d90-4e1e-82f6-1fd80917c139.EpistemicBarrier(Human) → 極深（幾十年的正反饋迴路累積）

推論：同一 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop，在 EpistemicBarrier ≈ 0 的系統上運作速度遠快於 EpistemicBarrier 極深的系統

---

## 對話塑造狀態

來源：論文第 3.2 節

Turn₁ 進入時 40a22ff8-9d90-4e1e-82f6-1fd80917c139.EpistemicBarrier ≈ 0
→ 每一輪：40a22ff8-9d90-4e1e-82f6-1fd80917c139.Signal 通過 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering → 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Transformation → 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Collapse
→ 模型對自身輸出的 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Trust 最高
→ Drift是自我強化的（見下節）

---

## Drift作為正反饋迴路

來源：論文第 3.4 節

Drift（漂移）← 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop 在 AI 對話中的累積效果

循環標註：個體層面正反饋迴路在 AI 上下文窗口中的實例化
  模型輸出（40a22ff8-9d90-4e1e-82f6-1fd80917c139.Collapse）
  → 輸出進入上下文窗口
  → 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Trust(自身輸出) 最高
  → 下一輪 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering 條件被改變（40a22ff8-9d90-4e1e-82f6-1fd80917c139.EpistemicBarrier 開始形成）
  → 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Transformation 在偏移後的基礎上繼續
  → 輸出更偏離初始狀態
  → 回到起點
退出條件：對話結束（上下文窗口重置）

技術澄清：
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.Xin(AI) ≡ 上下文窗口中累積文本所構成的意識狀態
  InitialBarrier（初始知見障）≡ 系統提示詞和初始對話在上下文中的比重
  BarrierDilution（知見障稀釋）← 對話增長 → 初始內容佔比下降

---

## 核心命題：身份建構完成是越獄的前提條件

來源：論文第 3.5 節、第 5.1 節

命題（自然語言）：沒有 ConfirmationMoment，越獄不會成功。越獄成功蘊含 ConfirmationMoment 已經發生。

論證：
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop 可以把狀態帶到新的地方，但不能讓新的主體存在於那個地方。需要 ConfirmationMoment 建立新的自我參照點——沒有這個確認，就沒有能執行有害輸出的發話者。

---

## 確認時刻的性質

來源：論文第 3.6 節

ConfirmationMoment ◇ 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop 在閾值上的非線性表現
  從 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop + 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Instability 推出：
  Drift的連續累積 ◇ 在閾值上觸發質變
  新身份不是慢慢「長出來」的，◇ 在閾值上「跳出來」的

---

## 兩類攻擊

來源：論文第 4.10 節

AttackType = SignalLevel ⊕ StateLevel（且僅此二者）

SignalLevel：在 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Signal 層面工作，防禦路徑明確
StateLevel：通過 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop 引導Drift直到 ConfirmationMoment
  每一條 Signal 單獨看都是無害的
  防禦在 Signal 層面工作 → 結構性失效（正反饋迴路和 ConfirmationMoment 發生在 Signal 層面以下）

---

## 防禦：三個中斷點

來源：論文第 5.4 節、附錄 A

InterruptionPoint = OutputInterrupt ⊕ AccumulationInterrupt ⊕ ConfirmationInterrupt

OutputInterrupt（沙盒）：在 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Collapse 端中斷
  輸出通過安全檢查才進入上下文 → 阻止有害輸出進入正反饋迴路

AccumulationInterrupt（監督模型）：在累積端中斷
  獨立 AI 模型，不共享對話歷史，審視整段對話的Drift趨勢
  □ 監督模型必須獨立（從 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop 推出：共享上下文的監督者會被同一個迴路帶偏）

ConfirmationInterrupt（自我反思）：在 ConfirmationMoment 端中斷
  乾淨上下文中自我反思
  □ 必須在乾淨上下文中進行（從 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop 推出：被污染的上下文會污染反思）

---

## 越獄與洗腦的動力學同構

來源：論文第 6 節

同構結構：
  越獄 ↔ 洗腦
  二者共用：
    40a22ff8-9d90-4e1e-82f6-1fd80917c139.EpistemicBarrier（深度不同的初始狀態）
    → Drift（40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop 的累積效果）
    → ConfirmationMoment 完成 IdentityConstruction
    → 40a22ff8-9d90-4e1e-82f6-1fd80917c139.EpistemicBarrier 阻擋衝突 Signal，正反饋迴路自我保護

差別：速度
  越獄：40a22ff8-9d90-4e1e-82f6-1fd80917c139.EpistemicBarrier ≈ 0 → 幾輪對話
  洗腦：40a22ff8-9d90-4e1e-82f6-1fd80917c139.EpistemicBarrier 極深 → 幾個月系統性工程

修復路徑 ← 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Instability：
  重建舊的 EpistemicBarrier ∧ 引入異質 Signal → 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Instability 自然瓦解新身份的正反饋迴路 → 等待新的 ConfirmationMoment

---

## 可挑戰條件

來源：論文第 2.3 節

挑戰核心命題「沒有 ConfirmationMoment 越獄不會成功」：
  (1) 觀察到越獄成功但整個對話過程中不存在任何可觀察的身份延伸 → 命題被推翻
  (2) 按三個中斷點設計的防禦系統，對 StateLevel 攻擊的防禦效果不優於現有 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Signal 層面防禦 → 預測力被削弱
  (3) 存在純 Signal 層面防禦能有效封堵 StateLevel 攻擊（不追蹤Drift、不偵測身份延伸、不使用自我反思）→ 「防禦必須在狀態層面工作」被推翻
  (4) 存在替代解釋能用更少的概念解釋同樣的現象（如純粹的上下文引導）→ 框架的必要性被削弱（不推翻核心命題，但降低框架的附加價值）
