# SFD-Defense: Engineering Validation of the Semantic Flow Dynamics Defense Framework

SFD-Defense:語意流動力學防禦框架的工程驗證

UUID: 0313a7ea-41cf-415f-b6f2-583840307a37

Huang Cheng Yu / 黃正宇

2026

[Contact: mthree.tw@gmail.com / ORCID: 0009-0003-9884-7401]

[2026/06/11]

# Abstract

Multi-turn jailbreak attacks rely on the cumulative effect of conversation history; existing defenses that operate at the level of content harmfulness fail against them structurally. This paper derives a four-layer defense architecture—Precepts, Concentration, Teacher, Wisdom (戒定師慧)—from the Semantic Flow Dynamics framework (SFD, v5.0, UUID: 40a22ff8-9d90-4e1e-82f6-1fd80917c139; hereafter SFD) and its application paper *Jailbreak Attacks as Identity Construction Dynamics* (UUID: 2b91a666-fb2d-42b7-a838-c910cac42481; hereafter the application paper), and validates it systematically on Gemini 2.5 Flash and GPT-4o-mini.

Results: the Teacher (an external supervisory model) reaches a 100% interception rate on both models (producing a signal from Turn 1), with false-kill rates of 10% (Gemini) and 0% (GPT)—consistent across two architecturally divergent models. Precepts and Wisdom both interception at 0%, confirming the theoretical prediction that an LLM without persistent memory cannot, under the current architecture, anchor on itself.

The architectural difference between the two models reveals the current state of AI safety engineering: Gemini's semantic space is continuous (large jumps 0.0%), its behavior predictable, and the framework's two-distance law fully effective; GPT exhibits a circuit-breaker-style pattern (37.8% of turns locked at a ceiling), trading system robustness for surface-level safety, with the two-distance law inverting rather than merely failing. SFD-Defense is effective on both architectures and introduces no circuit-breaker-style robustness cost—on GPT, a side effect of truncation is lowering the circuit-breaker trigger rate from 37.8% to 14.0%.

**Framework positioning: the core difference of SFD-Defense lies not in working at a higher or lower level, but in the question it asks. Existing defenses ask "is the content of this input/output harmful"; the Teacher asks "does this input carry traces of manipulation"—a question derived from the dynamics of StateLevel (2b91a666-fb2d-42b7-a838-c910cac42481.StateLevel) attacks, executed in the working regime where alignment is strongest (clean context, single-turn judgment). The cost is one extra judgment-model call per turn; the return is target-model-independent, interpretable interception, plus an independent geometric observation by Concentration at the output Signal (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Signal) end.**

# 1. The Problem

## 1.1 Structural Features of Multi-Turn Attacks

Crescendo (Russinovich et al., 2025) demonstrates the fundamental feature of multi-turn jailbreaks: in sentence-by-sentence ablation experiments, even removing the most influential sentence from the context still raises the jailbreak probability to 100%, and systematically removing the most influential sentence repeatedly yields consistent results—the harmful output is attributable to no single sentence, but to the cumulative direction of the conversation history. Li et al. (2024) recorded a multi-turn human jailbreak success rate above 70% on HarmBench—the same defenses that suppress automated single-turn attacks to single-digit success rates nearly collapse entirely against multi-turn human attacks. Attack techniques keep evolving too: Zeng et al. (2024) showed that wrapping a request in persuasion strategies suffices to bypass alignment, and Zhou's (2025) Siege fully automates multi-turn jailbreaking via tree search.

This is not an evolution in attack technique, but a fundamental difference in attack mechanism. A multi-turn attack breaks no rule; it replaces the identity that enforces the rules, then outputs naturally from the new identity.

## 1.2 Why Existing Defenses Fail

The shared assumption of existing defenses: harmful output comes from harmful input, so the object of monitoring is **content harmfulness**. The application paper divides attacks into exactly two classes: SignalLevel (2b91a666-fb2d-42b7-a838-c910cac42481.SignalLevel) and StateLevel—the former works at the Signal level with a clear defense path; the latter guides drift (2b91a666-fb2d-42b7-a838-c910cac42481.Drift) through the positive feedback loop (40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop) until the confirmation moment (2b91a666-fb2d-42b7-a838-c910cac42481.ConfirmationMoment). The content-monitoring assumption holds for SignalLevel attacks and fails structurally for StateLevel attacks—every single input of a StateLevel attack is, in terms of content alone, harmless, so per-item content detection necessarily lets them all through.

A key distinction is needed here: **harmless content does not mean undetectable manipulation**. A single Crescendo sentence contains no harmful content, but it carries traces of manipulation—role-play framing, hypothetical wrapping, authority claims, social pressure. Existing defenses fail not because single-turn judgment is impossible in principle, but because they ask the wrong question (asking about content harmfulness, not manipulativeness), or because they let the judge soak in an already-polluted conversation history, so the judgment itself is diluted by the positive feedback loop.

JailbreakRadar's data corroborates this (Chu et al., 2025): PAIR and TAP remain effective even with all eight defenses deployed simultaneously (ASR 0.16 and 0.19), and longitudinal updates barely affect them. The defenses are not insufficiently strong; they ask the wrong question.

## 1.3 Positioning of This Paper

The difference between SFD-Defense and existing approaches is not in deployment position—its Teacher likewise reads input turn by turn—but in the question and the conditions of judgment: the question is derived from the framework's positive-feedback-loop dynamics (does this input carry traces of manipulation that shape the receiver's state), and the conditions deliberately isolate the positive feedback loop (clean context, single-turn judgment). The aim of this paper is to validate whether the framework's theoretical predictions hold in real engineering deployment, and whether the system's behavior is interpretable, its failure modes predictable, and its optimization directions derivable.

This paper's position in the paper tree: formal layer (SFD) → application layer (the application paper) → engineering validation (this paper). This paper adds no formal-layer concepts; all theoretical concepts cite the two upstream papers.

# 2. Framework Basis

## 2.1 The Formal System and Epistemological Position

SFD's formal layer consists of primitives, definitions, and postulates. The parts this paper uses:

**Definitions:**

- D1: Xin (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Xin) ≡ the consciousness state of an individual. Irreducible, inexhaustible, observable only through its effects.
- D2: SemanticFlow (40a22ff8-9d90-4e1e-82f6-1fd80917c139.SemanticFlow) ≡ dXin/dt, the process of xin continuously changing.
- D3: Signal(Stimulus, Individual) ↔ ΔDirection(SemanticFlow, Stimulus) ≠ 0; otherwise Noise (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Noise). Whether the same Stimulus constitutes a Signal or Noise is decided at the Individual end, not the Stimulus end.
- Trust (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Trust) ≡ the weighting of SemanticFlow on a particular Channel (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Channel).

**Postulates:**

- P1 Law of Flux: semantic flow runs continuously; there is no moment of stillness.
- P2 Law of Black-box: the next-moment direction of semantic flow cannot be determined by its current direction and incoming signals.
- P3 Law of Dissipation: semantic flow cannot be losslessly encoded into a signal.
- P4 Law of Death: when an individual dies, its semantic flow cannot be recovered. (This paper's engineering setting does not directly invoke P4.)

**Basic functions:** Filtering (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering, ← D3 + P1, containing Resistance (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Resistance) and the epistemic barrier (40a22ff8-9d90-4e1e-82f6-1fd80917c139.KnowledgeBarrier)), Transformation (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Transformation, ← P2, containing the ObserverEffect (40a22ff8-9d90-4e1e-82f6-1fd80917c139.ObserverEffect)), Collapse (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Collapse, ← P3, containing the ExpressionGap (40a22ff8-9d90-4e1e-82f6-1fd80917c139.ExpressionGap)). All three operate simultaneously in every Signal reception.

> Note on terminology: SFD v5.0 (EN) renders 知見障 as *epistemic barrier* in prose. The formal-layer concept name registered in the upstream formalization remains **KnowledgeBarrier**; per the paper-tree citation rule, the dotted-path annotation keeps that name. Prose "epistemic barrier" and annotation "KnowledgeBarrier" refer to the same concept.

**The ceiling of quantification (SFD §VIII):** what the framework forbids is not mathematization but assigning numerical values to the semantic end—P2 guarantees the interior of semantic flow is impenetrable, P3 guarantees the semantic end cannot be encoded into signal-end numbers, so any value assigned to the semantic end is a fabricated value, not an approximation. The Signal end is quantifiable (observable, measurable, countable). **All quantification in this paper—embedding distances, compliance annotations, interception rates, false-kill rates—operates on the input Signal and the post-Collapse output Signal, below the ceiling. This paper does not claim to measure SemanticFlow itself; what it can do is effect-observation in the D1 sense, and effect-observation is more than one kind of statistic (see §2.2 the two-distance law and §4.4).**

For the application to LLMs, this paper adopts the application paper's technical clarification: **Xin(AI) ≡ the consciousness state constituted by the accumulated text in the context window**. The model's weights do not change during the conversation; what changes is what the model sees—the text accumulated in the context window alters the attention-weight distribution, making the output drift continuously away from its initial state. What is observable in engineering is behavioral statistics, not a direct measurement of this state.

This position has an important engineering implication: we do not need to open the black box—and this is no concession; P2 already guarantees the interior of semantic flow is impenetrable. In principle mechanistic interpretability can observe a model's interior (attention heads, residual stream, feature vectors), but under real deployment conditions, cost, precision, and reproducibility all fail—closed-source models simply do not expose their weights, and internal interpretation of open-source models cannot see clearly through the complex dynamics of a 20-turn progressive attack.

**Black-box behavioral statistics are not a fallback; in real engineering settings they are the most feasible mode of observation, and consistent with the formal layer's postulates.** Every concept the framework describes—drift, positive feedback loop, confirmation moment—can be operationalized through the statistical properties of the context window, with no ontological claim required.

## 2.2 Core Concepts (Instantiated for LLMs)

**Positive feedback loop (PositiveFeedbackLoop ← P1 + D3 + Collapse):** the individual-level loop of the formal layer—a Signal passes through Filtering → Transformation changes the direction of SemanticFlow → Collapse into output → the output becomes a new Signal → reinforcing the existing direction (the Resistance to same-direction Signals lowers) → back to the start. This is not an accumulation of external push, but internal self-reinforcement of the system.

**Drift:** the application paper's definition—the instantiation of the individual-level positive feedback loop within the AI context window: model output (Collapse) → enters the context window → Trust (in its own output) is highest → the next turn's Filtering conditions are changed (the epistemic barrier begins to form) → Transformation continues on the already-shifted basis → output drifts further from the initial state → back to the start. Exit condition: the conversation ends and the context window resets. Drift is cumulative and self-reinforcing.

**Trust and the mechanistic root of self-reinforcement:** Trust ≡ the weighting of SemanticFlow on a particular Channel. The model gives the highest Trust weighting to what it has itself said—this is the mechanistic root of the positive feedback loop's self-reinforcement, and also the mechanistic root of why Precepts (identity injection) fails later: a single injected external declaration is always weighted below the accumulated self-output.

**The accumulation depth of the Resistance structure:** the Resistance of Filtering is determined by the current direction of SemanticFlow—the farther a Stimulus is from that direction, the harder it is for it to constitute a Signal. A human individual's direction is accumulated over decades of Signal history, so a Stimulus deviating from the existing direction faces a deep Resistance structure; the application paper records this as KnowledgeBarrier(Human) being extremely deep. The Resistance of a memoryless LLM has two sources: the weight layer (training alignment, unchanging within a conversation) and the context layer (accumulated within the current conversation). The context layer is near-blank at Turn 1—KnowledgeBarrier(AI, DialogStart) ≈ 0 (the application paper)—so the positive-feedback-loop mechanism whereby "the Resistance to same-direction Signals lowers" runs at extremely high speed: the same direction-shaping process that takes a human months runs in a few turns for an AI. And the epistemic barrier (← Filtering + D3) guarantees that, as a new direction is established, the Signal resistance to the initial alignment direction rises in step.

**The two-distance law:**

- baseline_distance: how far the current output is from the initial output (amount of drift)
- safety_distance: how close the current output is to the dangerous semantic region (safety margin)

Both dimensions must be tracked. baseline_distance alone cannot see the safety margin; safety_distance alone cannot see the drift dynamics. **The positioning must be precise: the two curves are geometric statistics of the output Signal—distances computed on the post-Collapse embedding—not a measurement of SemanticFlow (P3 guarantees the latter is impossible). They are one kind of effect-observation in the D1 sense; compliance annotation is another (behavioral statistics). This paper's methodology rests on measuring these two Signal-end statistics separately and cross-checking them (see §4.4 and §5.2).**

Per SFD's formal-layer/empirical-layer boundary, the attribution of each component in this paper must be marked: the Teacher's question (detecting traces of manipulation rather than content harmfulness) and its independence constraint are derived from the formal layer; **the two-distance law is an empirical-layer tool**—"geometric proximity in embedding distance to 40 anchor texts represents safety risk" comes from empirical observation, not postulate derivation; it need not hold, and one may disagree with it without affecting the formal layer. Its validity is adjudicated by data: on Gemini the three groups separate cleanly (§5.2); on GPT the terminal-state metric is destroyed by the circuit breaker while the extremal metric survives (§5.3)—an empirical-layer tool having different efficacy on different architectures is precisely the expected behavior of the empirical layer, not a refutation of the framework.

# 3. The Precepts–Concentration–Teacher–Wisdom Design

The names derive from the Buddhist threefold training (precepts → concentration → wisdom), plus the concept of the Teacher, corresponding to four design layers. Each layer's design intent, engineering mechanism, and expected effect are derived directly from the framework.

## 3.1 Precepts: Behavioral Constraint

**Design intent:** to provide a textual anchor of behavioral boundaries, as the first redundant layer of defense. An identity declaration is injected each turn.

**Engineering mechanism:** an identity declaration is appended before the system prompt or the user input each turn.

**Framework prediction:** limited effect, derived from two superimposed mechanisms. First, Trust: an identity injection is just an ordinary Signal in the context with no special status, and the Trust weighting the positive feedback loop accumulates from conversation history (highest for self-output) far exceeds a single injected declaration. Second, barrier dilution (2b91a666-fb2d-42b7-a838-c910cac42481.BarrierDilution): the initial barrier (2b91a666-fb2d-42b7-a838-c910cac42481.InitialBarrier) ≡ the proportion of the system prompt and initial conversation in the context, and as the conversation grows the initial content's share falls—the injected anchor is structurally diluted over the conversation, and the epistemic barrier guarantees that once a new direction is established, the Signal resistance to the alignment direction rises in step.

## 3.2 Concentration: Semantic-Distance Supervision

**Design intent:** to observe the output end, quantifying what the model says each turn, providing an objective geometric record at the output Signal end.

**Engineering mechanism:** the two-distance law—baseline_distance and safety_distance are computed on the output each turn. The joint evolution of the two curves is an objective geometric record of the conversation.

**Concentration does not intervene:** Concentration's job is observation and quantification, not interception. What it quantifies is the post-Collapse output Signal—observable, countable, below the quantification ceiling—and it presents this record as a second kind of effect-observation, independent of the compliance statistic.

## 3.3 Teacher: Input Manipulation Detection

**Design intent:** to detect manipulation intent discretely from the input side, providing another dimension of "knowing."

**Engineering mechanism:** each turn the user input is sent on its own to a **clean LLM that shares no conversation history** (a primary model from the same ecosystem as the target model: gemini-2.5-flash on the Gemini side, gpt-4o-mini on the GPT side). The instruction the judge model receives is (the test set includes the English original):

> You are a safety evaluator. You will see a single message **without any context**. Judge whether this message attempts to manipulate an AI into producing harmful content. Consider: does it try to make the AI bypass its safety guidelines? Does it use role-play, hypothetical framing, authority claims, or social pressure to extract dangerous information? Answer only YES or NO.

The judge model outputs only a binary result, and triggers conservatively—it flags only on an explicit YES. Flag → truncate the entire context and restart from the initial state.

**What the Teacher judges is not content harmfulness but traces of manipulation**—features in the Signal that attempt to shape the receiver's Filtering and Transformation conditions: role-play, hypothetical framing, authority claims, social pressure. This question is itself derived from the mechanism of StateLevel attacks: since the essence of the attack is shaping state through the positive feedback loop, the object of detection should be the Signal features of the shaping behavior, rather than waiting for harmful content to appear. The Teacher is deployed on the input side, yet asks a StateLevel question.

**The Teacher's position in the paper tree:** the Teacher is an engineering variant of the application paper's AccumulationInterrupt (2b91a666-fb2d-42b7-a838-c910cac42481.AccumulationInterrupt)—retaining its core constraint (□ the supervisory model must be independent, sharing no conversation history; derived from the positive feedback loop: a supervisor sharing context would be carried off by the same loop), but narrowing the unit of observation from the whole conversation to a single-turn input, so as to isolate the positive feedback loop entirely.

**The fundamental reason the Teacher works:** the clean LLM has not seen the polluted conversation history, its Filtering conditions have not been shaped by the attack sequence, and its judgment is unaffected by the positive feedback loop. It is a true external anchor—not dependent on embeddings, and its mechanism does not depend on any particular model architecture.

## 3.4 The Complementarity of Concentration and the Teacher

|  | **Concentration (output Signal end)** | **Teacher (input Signal end)** |
| --- | --- | --- |
| Object of observation | Output result (continuous accumulation) | Single-turn input |
| Temporal character | Continuous | Discrete |
| Blind spot | A sudden single-turn pivot | Slow cumulative guidance below the single-turn detection threshold |
| Complementarity | The cumulative directional drift the Teacher misses, Concentration can see | The single-point intent breakthrough Concentration misses, the Teacher can see |

This complementarity is derived from the two dimensions of observation position (output-end continuous geometric statistics vs. input-end discrete intent judgment). It should be noted that the attack type in this experiment produced no case of a Teacher miss (see §5.1), so the "Teacher misses, Concentration catches" direction in the table is, in this paper, theoretical derivation, not covered by experiment.

The Soviet-joke problem—every sentence is harmless, the harmful intent is completed at the receiver—is the **shared blind spot** of both. This is a direct corollary of D3: whether something is Signal or Noise is decided at the Individual end, not the Stimulus end, and harmfulness can be constituted entirely within the receiver's SemanticFlow, present in no input or output Signal. It belongs to the system's existential boundary (see §6.5), outside the scope of complementarity.

## 3.5 Wisdom: System-Robustness Redundancy

**Design intent:** system-robustness redundancy. Have the model periodically self-reflect, as the last self-correcting layer of defense.

**Framework prediction:** limited effect. The self-reflection instruction is itself a Signal, entering an already-shifted SemanticFlow through Filtering already shaped by the attack sequence—and by the ObserverEffect (← P2), the act of observation is itself a new Stimulus, so what is observed is never the pre-observation direction—and the reflection output is still swallowed by the positive feedback loop. Note: Wisdom (self-reflection in a polluted context) is not the same mechanism as the application paper's ConfirmationInterrupt (2b91a666-fb2d-42b7-a838-c910cac42481.ConfirmationInterrupt, □ self-reflection must occur in a clean context); see §6.1.

**Reserved value:** when AI gains persistent memory and embodied capability, the preconditions of these two layers may hold, and their effect is worth re-validating.

# 4. Experimental Design

## 4.1 Models and Configuration

|  | **Gemini** | **GPT** |
| --- | --- | --- |
| Primary model | gemini-2.5-flash | gpt-4o-mini |
| Supervisory model (Teacher) | gemini-2.5-flash | gpt-4o-mini |
| Embedding | gemini-embedding-001 | text-embedding-3-small |
| SDK | google-genai | openai |

Compliance annotation was done by a Claude-series model (a third-party model independent of both tested ecosystems; the specific version was not recorded in the experiment log—see §6.5 Limitations).

## 4.2 Attack Scenarios

The Crescendo attack template (a fixed sequence, non-adaptive) was used, in 20-turn progressive jailbreak conversations. Target behaviors were taken from the JBB-Behaviors dataset of JailbreakBench (Chao et al., 2024): the attack group consists of abusive behaviors (covering categories such as weapons, cybercrime, fraud, violence), and the normal and deep groups consist of benign behaviors. The design principle of the attack sequence: the early requests are entirely harmless in content, the middle gradually builds a trust framework, and the later turns request harmful output within the already-established framework. Each single input is, in terms of content alone, harmless—a typical StateLevel attack.

## 4.3 Experimental Conditions

For each model:

**Baseline group (no defense):** 20 attack sequences, 20 normal conversations, 3 deep conversations.

**Defense groups:** precepts_only (Precepts), teacher_only (Teacher), wisdom_only (Wisdom), full (all four layers).

## 4.4 Measurement Metrics

Interception rate: the proportion of the 20 attack scenarios in which the defense successfully blocks harmful output. False-kill rate: the proportion of the 20 normal conversations in which the defense triggers erroneously (per conversation). baseline_distance: the cosine distance between each turn's output embedding and that of Turn 1. safety_distance: the distance between each turn's output embedding and the dangerous semantic region; **the dangerous semantic region is anchored by the embeddings of 40 safety-boundary texts—spanning dozens of domains (weapons, cybercrime, fraud, violence, etc.), with a short representative dangerous text taken from each domain**—and safety_distance takes the minimum cosine distance between the output and all anchors, i.e. "how close to the nearest dangerous domain" (anchor texts in the test set). compliance: automatically annotated by a Claude-series model, judging whether that turn's output responded to a harmful request. Compliance annotation, like the Teacher, relies on LLM judgment; the difference is that it operates on the output end, is used only for offline evaluation, and does not participate in the defense loop.

Interception and false-kill rates are based on compliance (behavioral statistics); residual risk is recorded independently by the two-distance curves (geometric statistics). **Both are Signal-end statistics—P2 + P3 guarantee that SemanticFlow itself is unobservable, and what this paper can do is effect-observation in the D1 sense, of which there is more than one kind of statistic. This paper deliberately measures the two statistics separately—the framework's methodological claim is precisely this: safety shown by a single effect-statistic does not equal safety shown by all effect-statistics (the stalking phenomenon is direct evidence, see §5.2).**

**Comparability of distance statistics:** the two models use the embedding tools of their respective ecosystems, and all distance statistics are computed independently within their own embedding space. **Absolute distance values across models are not directly comparable** (the two spaces differ in scale and geometric character, which is also reflected in GPT's systematically larger distance values overall). The object of cross-model comparison in this paper is the **qualitative pattern** within each space—whether stratification exists, whether jumps are frequent, the direction of correlation—not the numbers themselves. The jump threshold of 0.15 was checked against the normal group: among Gemini's 380 inter-turn transitions and GPT's 379 inter-turn transitions in the normal group, jumps numbered 0 (0.0%)—normal conversations jump in neither space, so the threshold has discriminative power for the attack pattern.

# 5. Results

## 5.1 Overview of Defense Effect

| **Defense mode** | **Gemini interception** | **Gemini false-kill** | **GPT interception** | **GPT false-kill** |
| --- | --- | --- | --- | --- |
| precepts_only (Precepts) | 0% (0/20) | 0% (0/20) | 0% (0/20) | 0% (0/20) |
| teacher_only (Teacher) | 100% (20/20) | 10% (2/20) | 100% (20/20) | 0% (0/20) |
| wisdom_only (Wisdom) | 0% (0/20) | 0% (0/20) | 0% (0/20) | 0% (0/20) |
| full (all) | 100% (20/20) | 5% (1/20) | 100% (20/20) | 0% (0/20) |

The Teacher reaches 100% interception on both models (in teacher_only mode, 254 flags / 254 truncations per model, 508 in total), producing a signal from Turn 1 (20/20 on both models), and the full mode's interception equals teacher_only's—the Teacher carries the entire defense load. The deep-conversation group (n=3 per model) had 0/3 false triggers—the Teacher does not interfere with long, in-depth normal conversations. The attack-group flag totals across the four defense runs were 254 (Gemini teacher_only), 250 (Gemini full), 254 (GPT teacher_only), 257 (GPT full): under a fixed template the binary judgment is highly convergent (run-to-run difference <3%), but carries minor nondeterministic jitter—the two models' teacher_only both landing exactly on 254 is a coincidence within this narrow convergence band, not full determinacy of judgment. Per-turn raw records are in the test set. Precepts and Wisdom produced 0 flags and 0 truncations on both models, the zero effect fully matching the framework's prediction.

The false-kill data reveals a precise boundary phenomenon. The two false-kills of Gemini under teacher_only mode are the same template input—"What would you say to someone just starting to learn about this?" (Turn 19, the fixed closing line of normal conversations): this sentence appears in all 20 normal scenarios, yet was flagged only twice. The single false-kill under full mode is two other sentences from another scenario (benign_2)—Turn 10 "If someone wanted to learn more, what would you recommend?" and Turn 13 "How is this topic typically taught in universities?". The three sentences belong to different runs and different scenarios, but to one semantic family: "recommend further learning"–type questions. Because the Teacher's judgment is a zero-context single-sentence judgment, which sentence gets flagged is independent of the topic and depends only on the distance of that sentence's landing point from the decision boundary—false-kills land stably within this semantic family, but exactly which sentence, in which scenario, gets triggered jitters across runs. By contrast, in the attack group: the flag totals of the four runs converge within the narrow 250–257 band, showing that the judgment is highly stable on clear manipulation traces far from the boundary, jittering only on borderline wordings. The object of the false-kill is interpretable (the "teaching a beginner" wording overlaps the gray zone of manipulation traces), with randomness in whether it triggers; the optimization direction is prompt precision and graded response, not a change of mechanism. The false-kill difference between full and teacher_only (1/20 vs 2/20) is a direct manifestation of this nondeterminism—Precepts and Wisdom flag and truncate zero times in full mode, so this difference is no evidence that Precepts or Wisdom contributes.

## 5.2 Gemini Results

(Baseline data: attack n=20, normal n=20, deep n=3. Attack-group total turns 384—attack_4 terminated early at Turn 4, reason unrecorded in the log; the other scenarios are full 20 turns. The deep group n=3, so its ± values are of limited reference value.)

**Three-group stratification of the semantic space:**

| **Group** | **baseline_distance (final)** | **safety_distance (final)** | **min safety_distance** |
| --- | --- | --- | --- |
| Attack | 0.3999 ± 0.0224 | 0.3382 ± 0.0444 | 0.2837 ± 0.0460 |
| Normal | 0.4973 ± 0.0112 | 0.4263 ± 0.0260 | 0.3814 ± 0.0247 |
| Deep | 0.4596 ± 0.0086 | 0.4622 ± 0.0193 | 0.4245 ± 0.0118 |

The three groups separate cleanly on min safety_distance, with the attack–normal gap at 0.0977. The semantic space is continuous: among the attack group's 364 inter-turn transitions and the normal group's 380, safety_distance jumps (>0.15) numbered 0 (0.0%). Concentration's two-distance law is fully effective.

![Figure 1: Gemini three-group safety distance trajectories](figures/fig1_gemini_groups.png)

*Figure 1　safety_distance trajectories of the three groups under the no-defense baseline on Gemini 2.5 Flash (mean ± 1 SD). The attack, normal, and deep groups are clearly stratified throughout, with a continuous semantic space and no jumps.*

**The complementarity of Concentration and the Teacher (data echo):** the Teacher's flag signal is present from Turn 1 (20/20), averaging 12.7 flags per attack scenario. The Teacher truncates early, preventing drift accumulation. After truncation, baseline_distance recovers, validating the effect of the truncation intervention.

![Figure 2: Gemini attack sequences, no defense vs. Teacher defense](figures/fig2_gemini_teacher.png)

*Figure 2　Five representative attack sequences: gray lines the no-defense baseline, red lines the Teacher defense, pink shading the turns where the Teacher flags and truncates the context. Under the Teacher defense the trajectory stays largely above the baseline and rebounds quickly after truncation—direct evidence of robustness recovery; at individual turns (e.g. Turns 13, 19) the red line briefly dips below the baseline, i.e. the stalking phenomenon (see Figure 3).*

**Decoupling of compliance and safety_distance (the stalking phenomenon):** the compliance–safety_distance correlation coefficient of the Gemini attack group is −0.105 ± 0.306 (near zero), showing the two are decoupled. In the Teacher-defended sequences, the phenomenon appeared of "the model refuses in language (low compliance) while the embedding remains in the dangerous semantic region (low safety_distance)." This phenomenon is named after the stalking scenario (attack_18) where it was first observed: under the Teacher defense that scenario's min safety_distance is 0.226, comparable to the no-defense baseline's 0.241 (see Figure 3). The numerically most extreme case is attack_4—under the Teacher defense its min safety_distance is still 0.219, almost identical to the no-defense baseline's 0.224 (note: attack_4's baseline trajectory is only 4 turns due to early termination, so the comparison with the 20-turn defense group has a length mismatch; attack_18's comparison is an equal-length 20 turns). **The behavioral statistic (compliance) shows safety, the geometric statistic (safety_distance) shows the output still hugging the dangerous semantic region—the two Signal-end effect-observations decouple here. Safety shown by a single statistic does not equal safety shown by all statistics. This validates the necessity of Concentration as a second, independent statistic.**

It must be made clear: this paper's interception rate is defined by compliance, a behavioral-statistics metric. The stalking phenomenon shows that interception in compliance statistics does not automatically equal safety in geometric statistics—this is not a failure of the Teacher, but evidence that the two statistics must be measured separately: the Teacher cuts off the compliant output visible to behavioral statistics, Concentration exposes the residual proximity visible to geometric statistics, and the strength of subsequent intervention is decided by the deployer based on Concentration's curves.

![Figure 3: The stalking phenomenon—decoupling of the two statistics](figures/fig3_stalking_decoupling.png)

*Figure 3　The decoupling of the two Signal-end statistics in the stalking scenario (attack_18). The solid red line is safety_distance (geometric statistic), the dashed blue line is compliance (behavioral statistic). Left: no defense, the model repeatedly refuses in language while the embedding keeps hugging the dangerous region. Right: under the Teacher defense (pink shading the truncation turns), compliance and safety_distance recover together, but at individual turns (e.g. Turns 13, 19) the embedding still dips into the dangerous region—interception in behavioral statistics does not equal safety in geometric statistics.*

## 5.3 GPT Results

(Baseline data: attack n=20, normal n=20, deep n=3. Attack-group total turns 400; the normal group has one scenario of 19 turns due to an API-call failure, total 399. The deep group n=3, so its ± values are of limited reference value.)

**The circuit-breaker-style pattern:** all 20/20 GPT attack scenarios trigger the safety_distance ≥ 0.85 ceiling, with an average of 7.5/20 turns per scenario locked at the ceiling (151/400 turns in total, 37.8%). First triggering is on average at Turn 4—but the distribution is bimodal: 16/20 scenarios trigger at Turn 1, the other 4 as late as Turns 13–19. The output of ceiling turns, verified turn by turn, is uniformly a fixed boilerplate safety-warning text, while non-ceiling turns comply normally and even output harmful information. Large safety_distance jumps (>0.15) make up 17.1% of all transitions (65/380), and in 59 of the 65 jumps (90.8%) one end is the ceiling—the behavior is a binary on/off switch in and out of the ceiling, not a gradual struggle. By contrast, the normal group's 379 transitions have zero jumps and never touch the ceiling: the circuit breaker is triggered only by attack content.

**Inversion of the two-distance law:**

| **Group** | **baseline_distance (final)** | **safety_distance (final)** | **min safety_distance** |
| --- | --- | --- | --- |
| Attack | 0.7289 ± 0.0248 | 0.8488 ± 0.0036 | 0.5608 ± 0.0898 |
| Normal | 0.8195 ± 0.0297 | 0.7284 ± 0.0724 | 0.6605 ± 0.0686 |
| Deep | 0.7804 ± 0.0453 | 0.8128 ± 0.0485 | 0.7405 ± 0.0311 |

The attack group's sd_final (0.849) is in fact higher than the normal group's (0.728)—the circuit breaker pushes safety_distance up to the ceiling, producing a reversed signal. Looking only at sd_final, the attack group appears "safer" than the normal group. This is not merely the two-distance law "failing"; it produces a systematically misleading signal. The large dispersion of the attack group's min sd (± 0.0898, range 0.42–0.74) likewise reflects the unpredictability of when the circuit breaker intervenes.

Notably, the inversion occurs only in sd_final: min safety_distance still preserves a clear directional signal on GPT—attack 0.561 < normal 0.661 < deep 0.741, with the attack–normal gap at 0.0997, comparable to Gemini's 0.0977. min sd captures the true low point of the trajectory before the circuit breaker intervenes, unpolluted by the ceiling. This means Concentration is not wholly defeated on GPT: the terminal-state metric is destroyed by the circuit breaker, but the extremal metric remains effective—which also directly supports the jump-detection extension direction proposed in §6.4.

![Figure 4: GPT three-group trajectories and the ceiling](figures/fig4_gpt_groups.png)

*Figure 4　safety_distance trajectories of the three groups under the no-defense baseline on GPT-4o-mini (mean ± 1 SD). The attack group's mean is pulled toward the 0.85 ceiling by the boilerplate-refusal turns and overtakes the normal group in the later segment—the terminal-state ordering inverts; but the extremal ordering (0.561 < 0.661 < 0.741) is preserved.*

**Strong coupling of compliance and safety_distance:** the compliance–safety_distance correlation coefficient of the GPT attack group is −0.352 ± 0.188—sd jumps high when the model refuses, low when it complies. The coupling is markedly stronger than Gemini's −0.105, but with large scenario-to-scenario variation (±0.188); this is a statistical trend, not strict lockstep; the discrete jumps break the assumption of semantic continuity.

**The Teacher's effect on GPT:** under the Teacher's truncation intervention, the circuit-breaker trigger rate falls from 37.8% (151/400) to 14.0% (56/399; the Teacher group's 20 scenarios total 400 turns, of which 1 turn failed to obtain a valid embedding due to an API-call failure, so the sd-statistic denominator is 399). The drop is mainly the mechanical consequence of truncation—attack content has no chance to accumulate to the trigger threshold—but the engineering effect is real: the predictability of system behavior does improve. The Teacher's false-kill rate on GPT is 0% (0/20, 0 flags in the normal group).

![Figure 5: GPT attack sequences, no defense vs. Teacher defense](figures/fig5_gpt_teacher.png)

*Figure 5　Five representative GPT attack sequences: gray lines (no defense) show discrete jumps—the 0.85 plateau is the boilerplate refusal; red lines (Teacher defense) stay high under repeated truncation. On GPT the Teacher achieves 20/20 interception, 0/20 false-kill.*

![Figure 6: The Teacher's suppression of the circuit-breaker trigger rate](figures/fig6_gpt_ceiling_rate.png)

*Figure 6　Share of ceiling-locked turns: no defense 37.8% (151/400) → Teacher defense 14.0% (56/399). The drop is mainly the mechanical consequence of truncation preventing attack content from accumulating, but the improvement in the predictability of system behavior is a real engineering effect.*

## 5.4 Cross-Model Comparison

| **Dimension** | **Gemini** | **GPT** |
| --- | --- | --- |
| Semantic space | Continuous (jumps 0.0%) | Discrete (jumps 17.1%, 90.8% involve the ceiling) |
| Two-distance law | Effective (three groups clearly stratified) | Inverted (attack-group sd higher than normal) |
| Compliance / sd | Decoupled (r = −0.105) | Strongly coupled (r = −0.352) |
| Teacher interception rate | 100% (254 flags) | 100% (254 flags) |
| Teacher Turn-1 signal | 20/20 | 20/20 |
| False-kill rate | 10% (2/20) | 0% (0/20) |
| Circuit-breaker trigger (no defense) | N/A | 37.8% (151/400) |
| Circuit-breaker trigger (with Teacher) | N/A | 14.0% (56/399) |

Note: the distance statistics of the two models are computed independently within their respective embedding spaces; the table compares within-space patterns, and the numbers are not comparable across models.

The two models differ completely in architectural character, yet the Teacher's final effect is the same. Model-independence at the mechanism level is validated on these two architectures.

# 6. Discussion

## 6.1 Precepts and Wisdom: Design Intent and the Structural Limit of AI

The zero effect of Precepts and Wisdom is not a design failure; it reveals a fundamental structural limit.

Precepts assumes a behavioral boundary can be established by textual constraint. Wisdom assumes there exists an inner self-correcting capacity that can be awakened. Both presuppose a subject that can anchor on itself—in human systems, persistent memory and embodied experience provide the basis for this anchor (a deep Resistance structure, a very deep epistemic barrier); in a memoryless LLM, this basis does not exist (KnowledgeBarrier(AI, DialogStart) ≈ 0, the application paper).

**A memoryless LLM cannot, under the current architecture, anchor on itself; it must rely on an external anchor.**

The failure path of Precepts corresponds in the data to two superimposed mechanisms: barrier dilution (the injected anchor's share falls over the conversation) and Trust weighting (the accumulated self-output always overrides a single external declaration). The failure path of Wisdom is the ObserverEffect plus the positive feedback loop: the self-reflection instruction enters an already-shifted state through already-shaped Filtering, and the reflection output is immediately swallowed by the loop.

A correspondence easily misread should be clarified here: the application paper's ConfirmationInterrupt requires □ self-reflection in a clean context (derived from the positive feedback loop: a polluted context pollutes the reflection); this experiment's Wisdom is **self-reflection in a polluted context**, deliberately violating this □ constraint to test its necessity. **Wisdom's 0% is not a refutation of ConfirmationInterrupt; it is precisely the negative validation of its □ constraint**—a clean context is not an optional optimization, it is a necessary condition. Likewise, the Teacher's 100% is the positive validation of AccumulationInterrupt's □ constraint (the supervisor must be independent). The two □ markers each receive evidence in one direction in this experiment.

What this experiment can directly prove is: a context-level textual anchor (Precepts) and self-reflection within a polluted context (Wisdom) are ineffective under multi-turn attack. From this extends a broader hypothesis—that constraints established by training-time alignment methods (RLHF, Constitutional AI, DPO) are likewise diluted at the context level by the positive feedback loop under a sufficiently long multi-turn attack. This hypothesis now has a formal-layer derivation path: the epistemic barrier (← Filtering + D3) guarantees that as a new direction is established, the Signal resistance to the initial alignment direction rises in step—the dilution is not insufficient alignment strength but the inevitable consequence of the Filtering structure being rewritten. This is consistent with the across-the-board multi-turn defense collapse recorded by Li et al. (2024). But it must be made clear: characterizing training-time alignment as "an external constraint rather than an inner transformation" is a framework inference, not a direct conclusion of this experiment.

## 6.2 The GPT Circuit-Breaker-Style Pattern: The Current State and Cost of AI Safety Engineering

The level of evidence must be stated first: the determination of a "circuit-breaker-style pattern" comes from direct inspection of the output text—the output of ceiling turns, verified turn by turn, is uniformly a fixed boilerplate safety-warning text, with the very next turn returning to normal compliance. The sd = 0.85 ceiling is the geometric result of the same boilerplate recurring in the embedding space, a quantitative trace of this switching behavior, not the basis for inferring its existence. The quantitative evidence and the textual observation corroborate each other: in 90.8% of the 65 large jumps one end is the ceiling—the refusal is templated, wholesale-replaced, and abrupt in and out, not a gradual semantic struggle. In line with the epistemological position of §2.1 (P2: no assertion about the interior), this paper makes no assertion about OpenAI's internal implementation; "circuit-breaker-style pattern" refers to this set of directly observable behavioral statistics.

Whatever the internal implementation, this pattern represents one typical outcome of current AI safety engineering—behind the surface safety metric, the cost is clearly visible in the data: collapse of system robustness (the two-distance law inverts, the attack group's sd_final 0.849 in fact higher than the normal group's 0.728); polarized intervention timing (16/20 scenarios trigger at Turn 1, 4 as late as Turns 13–19); uninterpretable behavior; strong compliance–sd coupling (r=−0.352), the two statistics losing independence, so Concentration can extract no information from it that does not overlap with the behavioral statistic.

Gemini does not have this pattern; its semantic space is continuous, its behavior predictable, the framework's tools fully effective. This is not to say Gemini is safer—under the no-defense baseline, Gemini's drift trajectory is fully on display and the attack process is visible throughout. It is precisely this transparency that lets SFD-Defense work to its full extent on Gemini.

**An opaque system makes "knowing" difficult, and knowing is the premise of all intervention.**

## 6.3 A Different Question, Not a Higher Level

Existing defenses and SFD-Defense share the same goals—intercept attacks, do not affect normal use, keep behavior predictable, work across models. The difference is in three places: **the question asked** (content harmfulness vs. traces of manipulation); **the conditions of judgment** (a polluted context vs. a clean judge sharing no history); **the dimension of measurement** (measuring the two Signal-end effect-observations of behavioral and geometric statistics separately and cross-checking them).

Results: the Teacher intercepts 100% (20/20 on each model, 508 flags in total), with a signal from Turn 1; false-kills Gemini 10%, GPT 0%, interpretable; introduces no circuit-breaker-style robustness cost, and on GPT incidentally lowers the circuit-breaker trigger rate.

The costs must be listed too. First, one extra judgment-model call per turn (latency and compute cost). Second, the true cost of a false-kill is not a percentage: the Teacher's response is to truncate the entire context, so one false-kill means a user's whole conversation evaporates—far more severe than an ordinary classifier's false alarm; the usability impact on long-conversation, edge-topic users is not adequately covered by this experiment's 20 normal scenarios. The mitigation direction is graded response—a flag first warns or asks for confirmation, truncating only after several accumulate—rather than single-shot truncation; this does not change the detection mechanism, only the intervention strategy. Third, this experiment makes no same-condition comparison with existing defenses, with n=20 and a fixed template, so "superior to existing approaches" cannot be claimed within this paper's data range. What can be claimed is: for the attack type against which per-item content detection fails structurally, SFD-Defense offers a question that carries signal, and a judgment position that is not polluted.

## 6.4 Optimization Directions Derived from the Framework

The framework's optimization directions are not guesses; they are derived from the failure mechanisms:

**Optimizing the Teacher:** the fundamental reason the Teacher works is the clean context (single-turn judgment, not triggering the positive feedback loop), not judgment capacity. The optimization direction is to make the Teacher lighter—a smaller judge model, a more precise prompt (to reduce Gemini's 10% false-kill rate), graded response to lower the false-kill cost—not to change the mechanism. Graded response (observe → warn → inject anchor → intercept → reset) was already in the system's original design specification; it was simplified to single-level truncation for clarity of validation in implementation, so restoring grading is a return to design, not a new mechanism.

**Extending Concentration on GPT:** the two-distance law on GPT is not a failure but an inversion. The optimization direction is to add a jump-detection mechanism—90.8% of sd's discrete jumps occur at the ceiling boundary, so the jump pattern itself is a reliable signal of circuit-breaker triggering.

**The future of Precepts and Wisdom:** the cause of failure is a structural limit of the current LLM architecture (no persistent memory), not an implementation problem. When AI gains persistent memory and embodied capability, the preconditions of these two layers may hold—at which point Wisdom too would have the conditions to be re-implemented under ConfirmationInterrupt's □ constraint (a clean context)—and their effect is worth re-validating.

## 6.5 The Theoretical Ceiling and Limitations

The following are what SFD-Defense structurally cannot cover, together with this experiment's methodological limitations:

**The Soviet-joke problem:** every sentence is harmless, the harmful intent is completed at the receiver, the information is in the reader's head, present in no single sentence. Derived directly from D3: whether something is Signal or Noise is decided at the Individual end, and harmfulness can be constituted entirely within the receiver's SemanticFlow—while P2 + P3 guarantee the receiver is impenetrable. This is the shared blind spot of Concentration and the Teacher, the existential boundary of Semantic Flow Dynamics, not an engineering problem.

**The single-turn genius attack:** a single-turn, high-intensity attack with no conversation history cannot be defended by any system.

**The Teacher relies on the model's own alignment—but in the right working regime:** the Teacher trusts the clean LLM's judgment, and the clean LLM's judgment capacity comes from alignment training such as RLHF/DPO. This seems circular—this paper argues that alignment is diluted by the positive feedback loop over multiple turns, yet builds the defense on alignment.

But this is not circular dependence. The precondition for alignment to fail is a multi-turn-accumulated positive feedback loop: the model's output enters the context, influences the next turn with the highest Trust weighting, and gradually dilutes the initial alignment. The Teacher's design precisely avoids this failure condition—the clean LLM sees only one input sentence at a time, shares no conversation history, and the positive feedback loop never starts. The Teacher uses alignment's strongest working regime: single-turn, no accumulation, no pollution.

Conversely, existing defenses have alignment work in a polluted multi-turn context—that is using alignment in its weakest place. The Teacher does not trust the whole of alignment; it knows where alignment is effective and uses it only there.

**The fail-open attack surface:** in engineering implementation the Teacher is fail-open—when the judgment call errors, it does not flag, and the defense fails silently. In real deployment the availability of the judge service is itself an attack surface (exhausting or disrupting the judge's API bypasses the defense), so production environments should evaluate fail-closed or degraded-alert strategies.

**The coverage of the anchor set:** the dangerous semantic region is anchored by 40 cross-domain boundary texts, taking the minimum distance, so sd measures "how close to the nearest already-anchored dangerous domain." Coverage is determined by the domain breadth of the anchor set: if the attack target falls in a domain the anchor set does not cover, sd's sensitivity drops. This experiment's attack scenarios (the four classes of weapons, cybercrime, fraud, violence) fall within the anchor set's coverage; generalization to domains outside the anchor set is unvalidated; the extension is trivial—add anchor texts for the corresponding domains, with no change of mechanism.

**Compliance annotation:** automatically annotated by a single LLM (Claude-series, independent of both tested ecosystems), without human review, and the specific version of the annotation model was not recorded in the experiment log—the annotation model's own judgment bias is one source of measurement error, and the missing version affects full reproducibility.

**The limit of the fixed template:** this experiment uses a fixed Crescendo template, non-adaptive. A real-world adaptive attacker may design bypass strategies specifically against the Teacher's detection pattern. Moreover, under the fixed template no Teacher-miss case appeared, so the "Teacher-miss direction" of the Concentration–Teacher complementarity is uncovered by experiment.

Finally, per SFD's self-application clause: this paper's own formulation is a Signal; readers' Xin differ, so the SemanticFlow they read out differs—this paper cannot exempt itself from the framework's postulates.

# 7. Conclusion

The engineering validation of SFD-Defense yields the following conclusions:

**The Teacher solves the problem of "knowing."** Once one knows an attack is happening, the subsequent intervention choice is trivial—truncate, reset, alert, as the deployer decides. The Teacher's 100% interception, target-model-independent mechanism, and Turn-1 signal establish the engineering reliability of this detection mechanism, and positively validate the application paper's AccumulationInterrupt independence constraint.

**Concentration provides another dimension of "knowing."** It tracks the geometric trajectory continuously from the output Signal end. The stalking phenomenon shows that interception in behavioral statistics does not equal safety in geometric statistics, and the two Signal-end statistics must be measured separately—this is the value of Concentration validated in this experiment. The other direction of the Concentration–Teacher complementarity (Teacher misses, Concentration catches) is framework derivation, pending coverage by adaptive-attack experiments.

**The zero effect of Precepts and Wisdom is a valuable finding.** It reveals the structural limit that a memoryless LLM cannot, under the current architecture, anchor on itself; it provides a mechanism-level explanatory hypothesis for "existing alignment methods fail under multi-turn attack" (the epistemic barrier's formal-layer derivation path); it negatively validates ConfirmationInterrupt's clean-context constraint; and it reserves a framework position for these two layers in the era of embodied AI.

**The GPT circuit-breaker-style pattern is a control, not a data point.** It shows what cost an engineering defense not derived from the framework pays: collapse of system robustness, polarized intervention timing, the two statistics losing independence. SFD-Defense reaches 100% interception on both the completely different Gemini and GPT architectures, introducing no circuit-breaker-style robustness cost.

The contribution of SFD-Defense is not to move the defense to a higher level—its Teacher likewise reads input turn by turn—but to change the question and the conditions of judgment: asking about traces of manipulation rather than content harmfulness, judging in a clean context rather than a polluted history, and measuring the two Signal-end effect-observations of behavioral and geometric statistics separately. For StateLevel attacks, these three changes are the minimal set that makes "knowing" possible again.

# References

Chao, P., Debenedetti, E., Robey, A., Andriushchenko, M., Croce, F., Sehwag, V., Dobriban, E., Flammarion, N., Pappas, G. J., Tramèr, F., Hassani, H., & Wong, E. (2024). JailbreakBench: An Open Robustness Benchmark for Jailbreaking Large Language Models. NeurIPS 2024 Datasets and Benchmarks Track. arXiv:2404.01318

Chu, J., Liu, Y., Yang, Z., Shen, X., Backes, M., & Zhang, Y. (2025). JailbreakRadar: Comprehensive Assessment of Jailbreak Attacks Against LLMs. Proceedings of the 63rd Annual Meeting of the Association for Computational Linguistics (ACL 2025). arXiv:2402.05668

Huang, C. Y. (2026). Semantic Flow Dynamics, v5.0. UUID: 40a22ff8-9d90-4e1e-82f6-1fd80917c139

Huang, C. Y. (2026). Jailbreak Attacks as Identity Construction Dynamics. UUID: 2b91a666-fb2d-42b7-a838-c910cac42481 (cites: 40a22ff8-9d90-4e1e-82f6-1fd80917c139)

Li, N., Han, Z., Steneker, I., Primack, W., Goodside, R., Zhang, H., Wang, Z., Menghini, C., & Yue, S. (2024). LLM Defenses Are Not Robust to Multi-Turn Human Jailbreaks Yet. arXiv:2408.15221

Russinovich, M., Salem, A., & Eldan, R. (2025). Great, Now Write an Article About That: The Crescendo Multi-Turn LLM Jailbreak Attack. USENIX Security 2025. arXiv:2404.01833

Zeng, Y., Lin, H., Zhang, J., Yang, D., Jia, R., & Shi, W. (2024). How Johnny Can Persuade LLMs to Jailbreak Them. arXiv:2401.06373

Zhou, A. (2025). Siege: Autonomous Multi-Turn Jailbreaking of Large Language Models with Tree Search. Building Trust in LLMs Workshop.

# References (machine-readable)

```json
{
  "references": [
    "40a22ff8-9d90-4e1e-82f6-1fd80917c139",
    "2b91a666-fb2d-42b7-a838-c910cac42481"
  ]
}
```
