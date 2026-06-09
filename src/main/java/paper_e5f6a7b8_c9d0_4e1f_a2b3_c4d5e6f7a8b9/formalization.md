論文：《蓄壓模型：中央集權文明的動力學》
英文標題：Pressure Accumulation Model: Dynamics of Centralized Civilizations
UUID：e5f6a7b8-c9d0-4e1f-a2b3-c4d5e6f7a8b9
引用：
  40a22ff8-9d90-4e1e-82f6-1fd80917c139
  f47ac10b-58cc-4372-a567-0e02b2c3d479
同名聲明：
  AccumulationModel：取代 f47ac10b-58cc-4372-a567-0e02b2c3d479.AccumulationModel，本篇為通用版，母論文版本為本篇在特定信模條件下的特例
  Accumulation：隨 AccumulationModel 一併取代
  SawThrough：隨 AccumulationModel 一併取代
  Rupture：隨 AccumulationModel 一併取代
  Warring：隨 AccumulationModel 一併取代
  Reconstruction：隨 AccumulationModel 一併取代

---

## 引用概念清單

來源：論文 §一

本文引用的 SFD 概念（40a22ff8-9d90-4e1e-82f6-1fd80917c139，全部引用不修改）：
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.Individual（原語）
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.Stimulus（原語）
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.Xin
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.SemanticFlow
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.Signal
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.Noise
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.FluxLaw（公設）
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.BlackBoxLaw（公設）
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.DissipationLaw（公設）
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.DeathLaw（公設）
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.Transformation
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.Collapse
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.SignalContainer
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.GenerationalReplacement

本文引用的母論文概念（f47ac10b-58cc-4372-a567-0e02b2c3d479，全部引用不修改）：
  f47ac10b-58cc-4372-a567-0e02b2c3d479.ReproductionMechanism
  f47ac10b-58cc-4372-a567-0e02b2c3d479.FunctionalDeath
  f47ac10b-58cc-4372-a567-0e02b2c3d479.Silencing

---

## 前置條件

來源：論文 §2.1

C₀: 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop，穩定運作（中央集權）

ResourceConcentration ≡ C₀ 的維持需要資源向中心集中
  中央集權的結構性要求——中心要維持對全域的控制，必須掌握資源

LocalAgentRequirement ≡ C₀ 的規模需要地方代理人執行控制
  規模的結構性要求——中心無法直接管到每一個 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Individual，必須依賴地方代理人

---

## 蓄壓模型

來源：論文 §2.2

AccumulationModel ← ResourceConcentration ∧ LocalAgentRequirement
  取代 f47ac10b-58cc-4372-a567-0e02b2c3d479.AccumulationModel
  母論文版本以特定信模為前提，本篇從 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop 結構直接推導

五階段循環：

循環標註：Accumulation → SawThrough → Rupture → Warring → Reconstruction → Accumulation

Accumulation（蓄壓）：
  C₀ 穩定運作
  ResourceConcentration → PressureLine₁
    從 ResourceConcentration 推出：資源集中是正反饋，中心越強抽取能力越強，底層資源持續減少
  LocalAgentRequirement → PressureLine₂
    從 LocalAgentRequirement 推出：中心給代理人權力，代理人向獨立節點演化，獨立性隨時間累積
  互相餵養（從 PressureLine₁ ∧ PressureLine₂ 的結構推出）：
    壓制 PressureLine₁ 依賴地方代理人，因此加速 PressureLine₂
    壓制 PressureLine₂ 需要集中資源，因此加速 PressureLine₁
  PressureLine₁ ∧ PressureLine₂ → ContainerExhaustion
    從 PressureLine₁ ∧ PressureLine₂ 互相餵養推出

SawThrough（鋸）：
  ContainerExhaustion → Rupture
  從 Accumulation 推出：最後的崩潰 ¬ 因為最後一次衝擊特別強，因為容器已被鋸至一層皮

Rupture（斷裂）：
  C₀ 的中心消失
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.SignalContainer 存活
  C₀ 的方向未被替代

Warring（混戰）：
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.SignalContainer 方向統一指向唯一中心，所有代理人爭奪同一個位置
  混戰軌跡取決於 BranchCondition（見分支條件段）

Reconstruction（重建）：
  勝出者重建 C₀，循環回到 Accumulation

退出條件：f47ac10b-58cc-4372-a567-0e02b2c3d479.FunctionalDeath（循環方向被替代 ∨ 承載者被物理消滅）

---

## 至高王權

來源：論文 §2.3

SupremeKingship ← C₀ ∧ ResourceConcentration
  從 C₀ + ResourceConcentration 推出：
  任何獨立權威節點分流 C₀ 的資源與權力，C₀ 通過 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering 結構性地排斥該節點
  SupremeKingship 是 40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop 的結構性行為，¬ 需要策略 ∧ ¬ 需要意識判斷

SupremeKingship 的成本累積餵進 Accumulation

---

## 免疫反應

來源：論文 §2.4

ImmuneResponse ← SupremeKingship
  SupremeKingship 的存在意味著歷史上必然出現 TranscendentAuthority 的嘗試與排斥
  [論文跳步：從 SupremeKingship 的存在到「歷史上必然出現嘗試」的推導未展開]

ImmuneResponse = AccumulationPhaseImmunity ⊕ ReconstructionMomentImmunity（且僅此二者）

AccumulationPhaseImmunity ≡ C₀ 穩定運作時，中心掌握壓倒性資源，排斥 TranscendentAuthority
  從 SupremeKingship + C₀ 穩定 推出
  信號端判據：Center 掌握的資源 > TranscendentAuthority 掌握的資源

ReconstructionMomentImmunity ≡ C₀ 重建後 SupremeKingship 立即啟動，排斥混戰中存活的 TranscendentAuthority
  從 SupremeKingship + Reconstruction 推出
  即使 SurvivingTranscendentAuthority 是把勝出者送上位置的力量

免疫失敗條件：
  ∃(SurvivingTranscendentAuthority ∧ IndependentExecutiveBody) at Reconstruction
  → BranchCondition 不成立

---

## 分支條件

來源：論文 §2.5

BranchCondition ← SupremeKingship ∧ ImmuneResponse
  動態狀態，SupremeKingship 持續維護
  判定時點：Reconstruction 完成瞬間

BranchCondition 成立 ↔ ¬∃(SurvivingTranscendentAuthority ∧ IndependentExecutiveBody) at Reconstruction
  40a22ff8-9d90-4e1e-82f6-1fd80917c139.SignalContainer 方向統一指向唯一中心，所有代理人爭奪同一個位置，勝出者重建 C₀

BranchCondition 不成立 ↔ ∃(SurvivingTranscendentAuthority ∧ IndependentExecutiveBody) at Reconstruction
  多個政治中心可合法並存，¬ 重建 C₀

---

## 獨立執行機構

來源：論文 §2.6

IndependentExecutiveBody ← SignalContainerReformation 的邏輯對偶
  從 SignalContainerReformation（§2.7）推出：
  循環能改造一切純 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Signal 層面的東西，純觀念沒有自我保護能力
  能抗住改造的必須 ¬ 純 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Signal，必須有物質性 ∧ 組織性的執行結構

TranscendentAuthority ≡ 宣稱高於政治中心的權威

IndependentExecutiveBody 判定標準：
  ∃Structure ∈ 40a22ff8-9d90-4e1e-82f6-1fd80917c139.SignalContainer:
    宣稱高於政治中心的權威（超越性）
    ∧ 執行能力不依賴政治中心授權（獨立性）
    ∧ 政治中心無法單方面關閉（不可消滅性）

SurvivingTranscendentAuthority ≡ TranscendentAuthority ∧ IndependentExecutiveBody
  在 Warring 中存活且新循環無法排斥的實體

---

## 信號容器的改造

來源：論文 §2.7

SignalContainerReformation ← C₀ ∧ 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering ∧ 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Transformation
  從 C₀ 的結構推出：
  循環 ¬ 只被 40a22ff8-9d90-4e1e-82f6-1fd80917c139.SignalContainer 維持，循環主動改造 40a22ff8-9d90-4e1e-82f6-1fd80917c139.SignalContainer
  任何被引入的信號材料經過 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering ∧ 40a22ff8-9d90-4e1e-82f6-1fd80917c139.Transformation，被改造成與 C₀ 方向相容的形狀，然後作為 40a22ff8-9d90-4e1e-82f6-1fd80917c139.SignalContainer 強化 C₀

因果方向：C₀ 改造信號材料，¬ 信號材料決定 C₀ 方向
結果：斷裂後 40a22ff8-9d90-4e1e-82f6-1fd80917c139.SignalContainer 帶的方向統一

---

## 同構組織的雙重功能

來源：論文 §2.8

IsomorphicOrganization ≡ 內部複製了中央集權結構的微觀組織
  = f47ac10b-58cc-4372-a567-0e02b2c3d479.ReproductionMechanism 的同一實體，本文識別其第二功能

Function₁: f47ac10b-58cc-4372-a567-0e02b2c3d479.ReproductionMechanism（母論文已論證）
  斷裂後重建同構容器

Function₂: DistributedImmunity（本文新增）
  循環運作期間阻止 TranscendentAuthority 的生長
  微觀結構中沒有給 TranscendentAuthority 留位置，從微觀層面排斥超越性宣稱

SupremeKingship = CentralizedImmunity ∧ DistributedImmunity

CentralizedImmunity ≡ 中心直接排斥獨立權威節點
DistributedImmunity ≡ IsomorphicOrganization 從微觀層面排斥超越性宣稱

---

## 馴服與崩潰的時序

來源：論文 §2.9

Domestication ≡ SupremeKingship 對 TranscendentAuthority 的持續壓制，使 TranscendentAuthority ¬ 宣稱高於政治中心
  Domestication ¬ 消滅 TranscendentAuthority
  Domestication 需要 C₀ 持續運作來維持

C₀ 持續運作時，Domestication 持續（從 SupremeKingship + C₀ 推出）

C₀ 斷裂時，Domestication 失效——Domestication 的維持力來自 C₀，C₀ 斷裂則維持力消失

¬Domestication ∧ ∃SurvivingTranscendentAuthority → BranchCondition 不成立
  TranscendentAuthority 從被馴服的狀態翻身為獨立權威

---

## 可證偽條件

來源：論文 §5.2

能推翻 BranchCondition 的預測力：
  (1) BranchCondition 成立的文明，斷裂後穩定地 ¬ 重建 C₀，且 ¬ 外部力量介入
  (2) BranchCondition 不成立的文明，斷裂後穩定地重建 C₀，且 ¬ SurvivingTranscendentAuthority 被收編

能推翻蓄壓動力學：
  (3) ∃ 中央集權文明: ¬(PressureLine₁ ∧ PressureLine₂ 互相餵養)
    即 ResourceConcentration ¬→ PressureLine₁
    ∨ LocalAgentRequirement ¬→ PressureLine₂

能質疑框架區分力：
  (4) 對另一文明用同方法跑模型，結構與本模型矛盾
