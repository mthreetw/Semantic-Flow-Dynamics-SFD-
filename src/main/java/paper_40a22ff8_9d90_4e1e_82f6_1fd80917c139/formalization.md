論文：《語意流動力學 v5.0》
英文標題：Semantic Flow Dynamics
UUID：40a22ff8-9d90-4e1e-82f6-1fd80917c139
引用：無

---

## 原語與定義

來源：論文第一節

原語（不定義，只指認）：
- Individual — 可指認的存在
- Stimulus — 到達個體的一切（外部或內部）

定義：
- D1: Xin ≡ 個體的意識狀態
  - ¬∃CompleteDescription: Xin → CompleteDescription（不可還原）
  - ¬∃Enumeration: Xin → {Aspect₁, Aspect₂, ..., Aspectₙ}（不可窮舉）
  - 可由效果觀察

- D2: SemanticFlow ≡ dXin/dt（信持續變化的過程）

- D3: Signal(Stimulus, Individual) ↔ ΔDirection(SemanticFlow, Stimulus) ≠ 0
  Noise(Stimulus, Individual) ↔ ΔDirection(SemanticFlow, Stimulus) = 0
  判定在 Individual 端，不在 Stimulus 端

補充定義：
- SignalContainer ≡ 信號停留的物理或數位載體（書本、聲波、建築、數位檔案）
  SignalContainer 儲存和傳遞 Signal，不產生 SemanticFlow

- Channel ≡ Signal 從一個 Individual 到達另一個 Individual 的路徑
  Channel 攜帶自我宣稱的 Signal，不攜帶可驗證的來源

- Trust ≡ SemanticFlow 對特定 Channel 上的加權

---

## 公設

來源：論文第二節

P1 流變律（FluxLaw）：
  ∀Individual ∀Time: Xin(Individual, Time+1) ≠ Xin(Individual, Time)
  語意流持續運作，不存在靜止時刻

P2 黑箱律（BlackBoxLaw）：
  ¬∃Function: Xin(Individual, Time+1) = Function(Xin(Individual, Time), Signal(Time))
  語意流的下一刻方向不可由當下方向與信號決定

P3 損耗律（DissipationLaw）：
  ¬∃Encoding: SemanticFlow(Individual) → Signal · Lossless(Encoding)
  語意流不可還原為信號

P4 死亡律（DeathLaw）：
  Death(Individual) → SemanticFlow(Individual) terminates ∧ ¬∃Recovery
  個體消亡，語意流不可恢復

公設的認識論位置：
  公設是公設，不是經驗假說。類似歐幾里得平行公設——選擇接受或不接受

挑戰條件（可證偽條件）：
  挑戰 P1：∃Individual ∃Time: Xin(Individual, Time+1) = Xin(Individual, Time)
  挑戰 P2：∃Function: ∀Individual ∀Time: Xin(Individual, Time+1) = Function(Xin(Individual, Time), Signal(Time))
  挑戰 P3：∃Encoding: SemanticFlow(Individual) → Signal · Lossless(Encoding)
  挑戰 P4：∃Recovery: Death(Individual) → SemanticFlow(Individual) resumes

---

## 過濾

來源：論文第三節第一小節

Filtering ← D3 + P1
  從 D3 + P1 推出：SemanticFlow 的當下方向決定哪些 Stimulus 構成 Signal、哪些構成 Noise。同一 Stimulus 進入不同方向的 SemanticFlow，結果不同。這個選擇性即 Filtering

性質 — Resistance：
  Stimulus 與 SemanticFlow 當下方向之間距離越遠 → 構成 Signal 的可能性越低
  Resistance 不是全有全無：完全擋回（Noise）⊕ 部分通過（Signal 形狀被改變）⊕ 完全通過

推論 — EpistemicBarrier ← Filtering + D3：
  Signal 通過 Filtering 改變 SemanticFlow 方向（D3）→ 新方向產生新 Filtering 條件 → 新條件對某些方向的 Stimulus 阻力降低，對其他方向的 Stimulus 阻力同時升高
  EpistemicBarrier 是 Resistance 的結構性後果，不是例外

---

## 轉化

來源：論文第三節第二小節

Transformation ← P2
  從 P2 推出：Signal 通過 Filtering 進入 SemanticFlow 後，方向改變不可預測。同一 Signal 進入不同 SemanticFlow 產生不同方向變化，進入同一 SemanticFlow 的不同時刻也產生不同方向變化

性質 — ObserverEffect ← P2：
  所有觀察 SemanticFlow 的努力本身是新 Stimulus
  → 若構成 Signal（通過 Filtering）→ 改變 SemanticFlow 方向（進入 Transformation，P2）
  → 觀察到的永遠不是觀察前的方向
  從 P2 推出：觀察即介入，非技術限制

---

## 坍塌

來源：論文第三節第三小節

Collapse ← P3
  從 P3 推出：Individual 將 SemanticFlow 輸出（說、寫、畫、做）→ 必須編碼為 Signal → P3 保證編碼必然有損 → SemanticFlow 從持續過程變為離散 Signal，此轉變即 Collapse

性質 — ExpressionGap ← P3：
  SemanticFlow 與輸出 Signal 之間不可能完全對應
  從 P3 推出：損耗律的結構性推論

性質 — SignalDistortion：
  Individual 的輸出持續、可觀察地偏向特定方向 → SignalDistortion
  框架不判斷 SignalDistortion 是蓄意或無意——意圖在 SemanticFlow 內部，不可觀察（P2）

---

## 基本功能的組合

來源：論文第三節第四小節

Filtering ∧ Transformation ∧ Collapse 在每一次 Signal 接收中同時運作
在時間中反覆組合 → 產生可觀察的行為模式
框架不提供封閉的組合清單——三個基本功能是字母表，可觀察現象是組合

---

## 正反饋循環

來源：論文第四節第一小節

PositiveFeedbackLoop ← P1 + D3 + Collapse
  從 P1 + D3 + Collapse 推出：
  Individual_A.SemanticFlow → Collapse → Signal → 到達 Individual_B → Filtering → Transformation → Individual_B.SemanticFlow 方向改變 → Collapse → Signal → 到達 Individual_A → 循環

個體層面：
  Signal → Filtering 放行 → Transformation 改變 SemanticFlow 方向 → Collapse 為行為 → 行為結果成為新 Signal → 強化 SemanticFlow 既有方向 → 下次同樣 Signal 更易通過 Filtering（Resistance 降低）→ 回到起點
  循環標註：個體層面正反饋循環
  退出條件：P1 + P2 保證不穩定，見下節

群體層面：
  大量 Individual 在相似 Signal 環境中運作 → SemanticFlow 的 Filtering 和 Transformation 模式趨近 → 輸出 Signal 強化 Signal 環境 → Signal 環境塑造下一代 Individual 的 SemanticFlow → 循環跨 Individual 運作
  循環標註：群體層面正反饋循環
  退出條件：Instability（見下節）

FunctionalConvergence ← P2：
  大量 Individual 輸出 Signal 模式趨近（可觀察事實）
  但 P2 保證每個 Individual 的 SemanticFlow 都不同，趨近永遠有殘差
  FunctionalConvergence 是框架能宣稱的上限

---

## 正反饋循環的不穩定性

來源：論文第四節第二小節

Instability ← P1 + P2 + P4

內部來源（從公設推出）：
  P1 → SemanticFlow 持續運作
  P2 → 方向變化不可預測
  P4 → Individual 不斷被替換且替換不可複製
  → 循環內部殘差持續累積

外部來源（環境事實）：
  循環依賴的 SignalContainer 在變，環境在變
  SignalContainer 變化受循環約束，環境變化不受循環約束
  → 當兩者變化方向持續分歧，落差在時間中擴大

內部殘差 ∧ 外部落差 同時作用
維護循環的行動本身也受公設約束：維護者 SemanticFlow 在變（P1），效果不可預測（P2），維護者會死（P4）
→ PositiveFeedbackLoop 沒有永續的可能，只有存續時間的長短

---

## 代際替換

來源：論文第四節第三小節

GenerationalReplacement ← P4
  從 P4 推出：Individual 死亡 → SemanticFlow 歸零且不可恢復
  新一代 Individual 在不同 Signal 環境中形成 SemanticFlow → 人口比例改變 → 循環方向改變

文明延續 ≠ 語意傳承（P2 + P3 否定語意傳承的可能）
文明延續 ≡ 循環的狀態再生產
  下一代在上一代留下的 Signal 環境中形成自己的 SemanticFlow → 這就是傳統

---

## 形式層與經驗層的分界

來源：論文「形式層與經驗層的分界」段

以上（原語、定義、公設、基本功能、正反饋循環、不穩定性、代際替換）= 形式層
以下（驅動力、建模方法）= 經驗層

經驗層工具來自經驗觀察，不來自公設推導
不可窮舉，不必然成立
可以不同意經驗層而不影響形式層

---

## 框架不分類

來源：論文第五節

P1 → SemanticFlow 持續運作 → 不存在靜止的時刻 → 沒有靜止的東西可以被貼標籤
模式 ≡ 建模的產物，不是建模的前提
模式數量是顆粒度的函數 → 窮舉模式清單無意義

---

## 驅動力

來源：論文第六節

DrivingForce ≡ 在統計上更常推動 SemanticFlow 坍塌為輸出 Signal 的 Stimulus 類型
清單不可窮舉，每一項不必然產生結果

已識別的常見類型（開放清單，∨ 非互斥）：

Survival — 飢餓、口渴、寒冷等內部 Stimulus，統計上最常推動 Collapse
Security — 威脅製造緊迫度 → 壓低 Filtering 的 Resistance → 更快接受新 Signal → 更快 Collapse
Power — 利益 ∧ 通道控制 ∧ SignalContainer 控制的組合。覆蓋循環每一環節
MeaningSeeking — Individual 主動尋求新 Signal、主動進入 Transformation 的內在動力
IdentityNeed — Individual 需要知道「我是誰」「我們是誰」→ 驅動合作，形成群體，維持邊界

---

## 循環替代模型

來源：論文第七節第三至五小節

前置條件：
  C₀: 既有循環，PositiveFeedbackLoop 穩定運作，SignalContainer 完整

啟動：
  NewSignal 進入，方向 ≠ C₀ 方向
  ∃ IndividualSubset ⊂ C₀: Signal(NewSignal, IndividualSubset) 成立（D3）

小循環形成：
  IndividualSubset 互相強化輸出 → 形成 C₁
  C₁ 輸出方向 ≠ C₀ 輸出方向 → C₁ ⊗ C₀

壓制（條件性）：
  若 C₀ 擁有制度性 Power →
    C₀ 壓制 C₁ 的 Signal 傳播、Individual 輸出、輸出後果
    C₁ 被壓縮
  若 NewSignal 已進入 SignalContainer → C₁ 不被消滅

落差：
  C₁ 持續產出新 Signal 強化自身
  C₀ 無法產出新 Signal 支撐自身
  C₀ 的 SignalContainer 宣稱與 Individual 實際接收 Signal 之間落差擴大
  C₀ 開始空轉

替換：
  P4 持續作用 → C₀ 中帶舊 SemanticFlow 的 Individual 消亡
  新 Individual 在包含 C₁ Signal 的環境中形成 SemanticFlow
  新 Individual 輸出匯入 C₁ → C₀ PositiveFeedbackLoop 斷裂

穩定：
  C₁ 成為主流循環
  C₁ 建立 SignalContainer，產生 Resistance 結構
  C₁ 開始自我保護 → C₁ 重複 C₀ 的結構性行為

循環標註：C₁ 替代 C₀
退出條件：四個必要條件全部滿足時替代完成

必要條件（從模型推出，四者缺一不可）：

NecessaryCondition₁: NewSignal 必須進入 SignalContainer
  → 否則壓制可消滅 C₁

NecessaryCondition₂: C₁ 必須有持續的新 Signal 源
  → 否則 C₁ 不擴大

NecessaryCondition₃: C₀ 必須無法產出對等的新 Signal
  → 否則落差不擴大，C₀ 不空轉

NecessaryCondition₄: P4 必須作用
  → 否則 Resistance 結構不自然消退，代際替換不發生

預測（從條件變量推出）：

Power 壓制有無 → 影響替代速度，不影響替代方向（從四個必要條件推出）
落差擴大速度 → 影響替代速度（從 NecessaryCondition₂ + NecessaryCondition₃ 推出）
SignalContainer 耐久性 → 影響 C₁ 存活能力（從 NecessaryCondition₁ 推出）

---

## 量化的空間與天花板

來源：論文第八節

框架禁止的不是數學化，是量化——給語意端賦數值
P2 → 語意流內部不可穿透
P3 → 語意端不可編碼為信號端數值
→ 任何語意端賦值操作是虛構值，不是近似值

Signal 端可量化（可觀察、可測量、可統計）
但 Signal 端量化 ≠ SemanticFlow 端測量（Signal ≠ SemanticFlow，公設那道牆）

此天花板由公設決定，非技術限制

---

## 適用範圍與限制

來源：論文第十節

適用：任何 Signal 到達 Individual 後產生 SemanticFlow 的現象
不適用：不涉及 Individual 接收和處理 Signal 的純信號層問題

限制：
  ¬ 處理真偽
  ¬ 處理價值
  ¬ 提供減少 Transformation 的方法
  ¬ 預測時間
  ¬ 追蹤 Individual 內心
  框架表述本身是 Signal → 讀者 Xin 不同 → 讀出 SemanticFlow 不同 → 框架無法豁免自身公設
