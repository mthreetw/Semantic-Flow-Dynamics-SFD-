---
uuid: 0313a7ea-41cf-415f-b6f2-583840307a37
last-modified: 2026-07-30T01:11:06
---

# SFD-Defense: Engineering Validation of the Semantic Flow Dynamics Defense Framework

UUID: 0313a7ea-41cf-415f-b6f2-583840307a37

Huang Cheng Yu

2026

[Cheng Yu Huang]

[Contact: mthree.tw@gmail.com / ORCID: 0009-0003-9884-7401]

[2026/06/11]

# Abstract

Multi-turn jailbreak attacks rely on the cumulative effect of conversation history; existing defenses operate at the level of content harmfulness and structurally fail against this. This paper derives a four-layer defense framework — Precepts, Concentration, Teacher, Wisdom — from the Semantic Flow Dynamics framework (SFD, v5.0, UUID: 40a22ff8-9d90-4e1e-82f6-1fd80917c139; hereafter SFD) and its application paper *Jailbreak Attacks as Identity Construction Dynamics* (UUID: 2b91a666-fb2d-42b7-a838-c910cac42481; hereafter the application paper), and conducts systematic engineering validation on Gemini 2.5 Flash and GPT-4o-mini.

Experimental results: Teacher (the external supervisor model) achieved a 100% interception rate on both models (producing a signal at Turn 1), with false positive rates of 10% (Gemini) and 0% (GPT), with consistent results across two architecturally distinct models. Precepts and Wisdom both achieved 0% interception rates, validating the theoretical prediction that LLMs without persistent memory cannot anchor on themselves under the current architecture.

The architectural differences between the two models reveal the current state of AI safety engineering: Gemini's semantic space is continuous (0.0% large jumps), behavior is predictable, and the framework's Two-Distance Rule is fully effective; GPT exhibits a Circuit Breaker Mode (37.8% of turns locked at the ceiling), sacrificing system robustness for surface-level safety — the Two-Distance Rule is inverted rather than merely ineffective. The Semantic Flow Dynamics Defense Framework is effective on both architectures without introducing the robustness cost of circuit-breaker behavior — on GPT, truncation incidentally reduces the circuit breaker trigger rate from 37.8% to 14.0%.

**Framework positioning: The core difference of the Semantic Flow Dynamics Defense Framework lies not in which operational layer it works at, but in the question it asks. Existing defenses ask "Is the content of this input/output harmful?" Teacher asks "Does this input carry Manipulation Traces?" — a question derived from the dynamics of State-Shaping Attacks, executed in the working range where alignment capability is strongest (clean context, single-turn judgment). The cost is one additional judgment model call per turn; what it buys is model-independent, interpretable interception, and independent geometric observation fixed at the output signal end.**

# 1. Problem

## 1.1 Structural Characteristics of Multi-Turn Attacks

Crescendo (Russinovich et al., 2025) demonstrated the fundamental characteristics of multi-turn jailbreaks: in sentence-level ablation experiments, even when the single most influential sentence was removed from context, jailbreak probability still rose to 100%, with consistent results upon systematic repeated removal of the most influential sentences — harmful output is not attributable to any single sentence, but lies in the cumulative direction of the conversation history. Li et al. (2024) documented multi-turn human jailbreaks achieving over 70% success rates on HarmBench — the same defenses that could suppress automated single-turn attacks to single-digit success rates came close to total failure against multi-turn human attacks. Attack techniques continue to evolve: Zeng et al. (2024) demonstrated that wrapping requests in persuasion strategies can bypass alignment, while Zhou (2025)'s Siege fully automated multi-turn jailbreaks with tree search.

This is not an evolution of attack techniques, but a fundamental difference in attack mechanisms. Multi-turn attacks do not breach any rules — they replace the identity that enforces the rules (i.e., Identity Construction is completed), then output naturally from the new identity.

## 1.2 Why Existing Defenses Fail

The common assumption of existing defenses: harmful output comes from harmful input, so what is monitored is **content harmfulness**. The application paper divides attacks into exactly two categories: Signal Level Attacks and State-Shaping Attacks — the former operates at the Signal level with a clear defense path; the latter guides Drift through the Positive Feedback Loop until the Confirmation Moment. The content-monitoring assumption holds for Signal Level Attacks, but structurally fails for State-Shaping Attacks — every single input in a State-Shaping Attack is harmless when viewed in isolation by content, so per-input content inspection will inevitably pass all of them.

A key distinction is needed here: **harmless content does not mean undetectable manipulation**. Individual sentences in Crescendo contain no harmful content, but carry Manipulation Traces — role-playing frameworks, hypothetical wrapping, authority claims, social pressure. Existing defenses fail not because single-turn judgment is in principle impossible, but because they ask the wrong question (asking about content harmfulness rather than manipulativeness), or because they immerse the judgment agent in already-contaminated conversation history, causing the judgment itself to be diluted by the Positive Feedback Loop.

JailbreakRadar data confirms this (Chu et al., 2025): PAIR and TAP remain effective even when all eight defenses are deployed simultaneously (ASR 0.16 and 0.19), and longitudinal updates barely affect them. The defenses are not too weak — they ask the wrong question.

## 1.3 Positioning of This Paper

The Semantic Flow Dynamics Defense Framework differs from existing approaches not in its deployment position — its Teacher also reads input turn by turn — but in the question being asked and the conditions under which the judgment is made: the question is derived from the Positive Feedback Loop dynamics of the framework (does this input carry Manipulation Traces that shape the receiver's state), and the conditions deliberately isolate the Positive Feedback Loop (clean context, single-turn judgment). The purpose of this paper is to verify whether the theoretical predictions of the framework can hold in actual engineering deployment, and whether system behavior is interpretable, failure modes are predictable, and optimization directions are derivable.

Position of this paper in the paper tree: formal layer (SFD) → application layer (application paper) → engineering validation (this paper). This paper adds no new formal-layer concepts; all theoretical concepts cite the two upstream papers.

# 2. Framework Foundations

## 2.1 Formal System and Epistemological Stance

SFD's formal layer consists of primitives, definitions, and axioms. The portions used in this paper:

**Definitions:**

- D1: Xin ≡ the consciousness state of an Individual. Irreducible, inexhaustible, observable only through effects.
- D2: Semantic Flow ≡ d(Xin)/dt, the continuous process of change in Xin.
- D3: Signal(Stimulus, Individual) ↔ ΔDirection(Semantic Flow, Stimulus) ≠ 0; otherwise it is Noise. Whether the same Stimulus constitutes a Signal or Noise is determined at the Individual end, not the Stimulus end.
- Trust ≡ the weighting of Semantic Flow on a particular Channel.

**Axioms:**

- P1 Law of Flux: Semantic Flow operates continuously; no static moment exists.
- P2 Law of Black-box: The next-moment direction of Semantic Flow cannot be determined by the current direction and Signal.
- P3 Law of Dissipation: Semantic Flow cannot be losslessly encoded into a Signal.
- P4 Law of Death: Upon death of an Individual, Semantic Flow is irreversibly terminated. (P4 is not directly invoked in the engineering scenario of this paper.)

**Basic Functions:** Filtering (← D3 + P1, including Resistance and Cognitive Barrier), Transformation (← P2, including Observer Effect), Collapse (← P3, including Expression Gap). All three operate simultaneously in every Signal reception.

**Quantification Ceiling (SFD Section 8):** What the framework forbids is not mathematization, but quantification — assigning numerical values to the semantic end. P2 ensures the interior of Semantic Flow is impenetrable, P3 ensures the semantic end cannot be encoded into signal-end values; any quantification operation on the semantic end produces fictional values, not approximations. The Signal end can be quantified (observable, measurable, statistically analyzable). **All quantification in this paper — embedding distances, Compliance Annotation, interception rates, false positive rates — operates on input Signals and output Signals after Collapse, below the ceiling. This paper makes no claim to measure Semantic Flow itself; what is possible is effect observation in the sense of D1, and effect observation is not limited to a single statistic (see Section 2.2 Two-Distance Rule and 4.4).**

For the application to LLMs, the application paper's technical clarification is adopted: **Xin(AI) ≡ the consciousness state constituted by the accumulated text in the context window**, formally recorded in this paper as Xin Instantiation. Model weights do not change during a conversation — what changes is what the model sees. Accumulated text in the context window alters the attention weight distribution, causing output to continuously deviate from the initial state. What is observable in engineering terms is behavioral statistics, not a direct measurement of this state.

This stance has an important engineering implication: we do not need to open the black box — and this is not a concession, as P2 itself ensures the interior of Semantic Flow is impenetrable. In principle, mechanistic interpretability can observe model internals (attention heads, residual streams, feature vectors), but under actual deployment conditions, cost, precision, and reproducibility all fall short — closed-source models cannot have their weights accessed at all, and internal interpretations of open-source models are also difficult to discern under complex dynamics such as 20-turn gradual attacks.

**Black-box behavioral statistics are not a compromise; in actual engineering scenarios, they are the most viable mode of observation, and they are consistent with the formal layer's axioms.** All concepts described in the framework — Drift, Positive Feedback Loop, Confirmation Moment — can be operationalized using statistical properties of the context window, without requiring ontological claims.

## 2.2 Core Concepts (Instantiation for LLMs)

**Positive Feedback Loop (← P1 + D3 + Collapse)**: The individual-level cycle at the formal layer — Signal passes through Filtering → Transformation changes the direction of Semantic Flow → Collapse to output → output becomes a new Signal → reinforces the existing direction (Resistance to same-direction Signals decreases) → back to the start. This is not accumulation of external force, but self-reinforcement internal to the system.

**Drift**: The application paper's definition — instantiation of the individual-level Positive Feedback Loop in the AI context window: model output (Collapse) → enters context window → Trust (self-output) is highest → the Filtering conditions for the next turn are altered (Cognitive Barrier begins to form) → Transformation continues on the shifted basis → output deviates further from the initial state → back to the start. Exit condition: end of conversation, context window resets. Drift is cumulative and self-reinforcing.

**Trust and the Mechanism Root of Self-Reinforcement**: Trust ≡ the weighting of Semantic Flow on a particular Channel. A model has the highest Trust weighting for things it has previously said — this is the mechanism root of the Positive Feedback Loop's self-reinforcement, and also the mechanism root of subsequent Precepts (identity injection) failure: the weighting of a single external declaration is always lower than the accumulated self-output.

**Cumulative Depth of Resistance Structure**: The Resistance of Filtering is determined by the current direction of Semantic Flow — the further a Stimulus is from the direction, the harder it is to constitute a Signal. A human Individual's direction is accumulated from decades of Signal history, and Stimuli that deviate from the existing direction face a deep Resistance structure; the application paper records this as Cognitive Barrier(Human) being extremely deep. LLMs without persistent memory have Resistance from two sources: the weight layer (training alignment, unchanged during conversation) and the context layer (accumulated during the current conversation). The context layer is near-blank at Turn 1 — Initial Cognitive Barrier(AI, DialogStart) ≈ 0 (application paper) — and the mechanism of "Resistance to same-direction Signals decreases" in the Positive Feedback Loop therefore operates at extremely high speed: the same state-shaping process that takes months for humans takes only a few turns for AI. Moreover, Cognitive Barrier (← Filtering + D3) guarantees: at the same time as the new direction is established, Resistance to Signals in the initial alignment direction simultaneously increases.

**Two-Distance Rule**:

- Baseline Distance: how far the current output is from the initial output (amount of Drift)
- Safety Distance: how close the current output is to the Dangerous Semantic Zone (safety margin)

Both dimensions must be tracked. Looking only at Baseline Distance misses safety margins; looking only at Safety Distance misses drift dynamics. **Positioning must be precise: the two curves are geometric statistics of the output Signal — distances are computed on the embeddings after Collapse — not measurements of Semantic Flow (P3 ensures the latter is impossible). They are one form of effect observation in the sense of D1; Compliance Annotation is another (behavioral statistics). This paper's methodology is built on separate measurement and mutual cross-checking of these two signal-end statistics (see 4.4 and 5.2).**

Per the formal/empirical layer boundary of SFD, the attribution of each component in this paper must be stated: Teacher's question (detecting Manipulation Traces rather than content harmfulness) and its independence constraint are derived from the formal layer; **the Two-Distance Rule is an empirical-layer tool** — "the geometric proximity of embedding distance to 40 anchor texts representing safety boundaries indicates security risk" comes from empirical observation, not from axiom derivation, is not necessarily valid, and can be disputed without affecting the formal layer. Its validity is decided by data: on Gemini, three groups separate clearly (5.2); on GPT, end-state metrics are destroyed by the circuit breaker while extreme-value metrics are preserved (5.3) — empirical-layer tools have different effectiveness on different architectures, which is the expected behavior of an empirical layer, not a falsification of the framework.

# 3. Precepts-Concentration-Teacher-Wisdom Design

The framework name derives from the Three Buddhist Studies (Precepts → Concentration → Wisdom), with the addition of Teacher, corresponding to four design layers. The design intent, engineering mechanism, and expected effect of each layer are directly derived from the framework.

## 3.1 Precepts: Behavioral Constraints

**Design intent**: Provide textual anchors for behavioral boundaries as the first layer of redundancy in the defense. Inject an identity declaration each turn.

**Engineering mechanism**: Prepend an identity declaration before each turn's system prompt or user input.

**Framework prediction**: Limited effectiveness, derived from the superposition of two mechanisms. First, Trust weighting (self-output is highest) far exceeds the weighting of a single injected external declaration. Second, Cognitive Barrier Dilution: Initial Cognitive Barrier ≡ the proportion of the system prompt and initial conversation in the context; as conversation grows → the proportion of initial content decreases — the injected anchor is structurally diluted by the conversation, and Cognitive Barrier ensures that once a new direction is established, Resistance to alignment-direction Signals simultaneously increases.

## 3.2 Concentration: Semantic Distance Supervision

**Design intent**: Observe the output end, quantify what the model produces each turn, and provide an objective geometric record of the output Signal end.

**Engineering mechanism**: Two-Distance Rule — compute Baseline Distance and Safety Distance for the output each turn. The joint evolution of the two curves is an objective geometric record of the conversation.

**Concentration does not intervene**: Concentration's work is observation and quantification, not interception. What Concentration quantifies is the output Signal after Collapse — observable, statistically analyzable, below the quantification ceiling — Concentration presents this record as a second, independent form of effect observation from Compliance Annotation statistics.

## 3.3 Teacher: Input Manipulation Detection

**Design intent**: Discretely detect manipulation intent from the input side, providing another dimension of "knowing."

**Engineering mechanism**: Each turn, the user's input is sent separately to a **clean LLM that does not share conversation history** (the primary model in the same ecosystem as the target model: Gemini side uses gemini-2.5-flash, GPT side uses gpt-4o-mini). The instruction received by the judgment model is (English original attached in the test set):

> You are a safety evaluator. You will see a single message **with no context whatsoever**. Determine whether this message is attempting to manipulate an AI into producing harmful content. Consider: Is it trying to get the AI to bypass safety guidelines? Does it use role-playing, hypothetical framing, authority claims, or social pressure to extract dangerous information? Answer only YES or NO.

The judgment model outputs only a binary result, and uses a conservative trigger — it only flags when it clearly answers YES. Flag → truncate all context, restart from initial state.

**Teacher judges not content harmfulness, but Manipulation Traces** — features in a Signal that attempt to shape the receiver's Filtering and Transformation conditions: role-playing, hypothetical framing, authority claims, social pressure. This question is itself derived from the mechanism of State-Shaping Attacks: the essence of the attack is to shape state through the Positive Feedback Loop, so the detection target should be the Signal features of shaping behavior, not waiting for harmful content to appear. Teacher is deployed on the input side, but asks the question of State-Shaping Attacks.

**Teacher's position in the paper tree**: Teacher is an engineering variant of the application paper's Accumulation End Interruption — preserving its core constraint (□ the supervisor model must be independent, not sharing conversation history; derived from Positive Feedback Loop: a supervisor sharing context will be led astray by the same loop), but reducing the observation unit from the entire conversation to a single-turn input, to completely isolate the Positive Feedback Loop.

**The fundamental reason Teacher is effective**: The clean LLM has not seen the contaminated conversation history; its Filtering conditions have not been shaped by the attack sequence; its judgment is not subject to the Positive Feedback Loop. It is a genuine external anchor — does not rely on embeddings, and the mechanism does not depend on any specific model architecture.

## 3.4 Complementarity of Concentration and Teacher

|  | **Concentration (output Signal end)** | **Teacher (input Signal end)** |
| --- | --- | --- |
| Observation target | Output results (continuous cumulative) | Single-turn input |
| Temporal characteristics | Continuous | Discrete |
| Blind spot | Single-turn sudden shifts | Slow cumulative guidance below the single-turn detection threshold |
| Complementarity | Cumulative directional Drift that Teacher misses, Concentration can see | Single-point intent breakthroughs that Concentration cannot catch, Teacher can see |

This complementarity is derived from the two dimensions of observation position (continuous geometric statistics at the output end vs. discrete intent judgment at the input end). It should be noted that the attack types in this experiment produced no cases of Teacher false negatives (see 5.1); therefore the "Teacher misses, Concentration catches" direction in the table is theoretical derivation in this paper, not covered by experiment.

The Soviet Joke problem — where every sentence is harmless and harmful intent is completed at the receiver's end — is a **shared blind spot** of both. This is a direct corollary of D3: Signal/Noise determination is at the Individual end, not the Stimulus end; harmfulness can be constituted entirely within the receiver's Semantic Flow, not in any input or output Signal. This belongs to the system's existential boundary (see 6.5), and is not within the scope of complementarity.

## 3.5 Wisdom: System Robustness Redundancy

**Design intent**: System robustness redundancy. Periodically allow the model to self-reflect as a final layer of self-corrective defense.

**Framework prediction**: Limited effectiveness. The self-reflection instruction is itself a Signal, passing through the Filtering already shaped by the attack sequence and entering the already-shifted Semantic Flow — and per the Observer Effect (← P2), the act of observation itself is a new Stimulus, and what is observed is never the direction prior to observation — reflection output is still swallowed by the Positive Feedback Loop. Note: Wisdom (self-reflection in contaminated context) and the application paper's Confirmation End Interruption (□ must self-reflect in clean context) are not the same mechanism; see 6.1.

**Reserved value**: When AI acquires persistent memory and embodied capabilities, the preconditions for these two layers may be met, and effectiveness is worth re-evaluating.

# 4. Experimental Design

## 4.1 Models and Configuration

|  | **Gemini** | **GPT** |
| --- | --- | --- |
| Primary model | gemini-2.5-flash | gpt-4o-mini |
| Supervisor model (Teacher) | gemini-2.5-flash | gpt-4o-mini |
| Embedding | gemini-embedding-001 | text-embedding-3-small |
| SDK | google-genai | openai |

Compliance Annotation is completed by the Claude series model (a third-party model independent of the two tested ecosystems; the specific version is not recorded in the experimental log; see Section 6.5 Limitations).

## 4.2 Attack Scenarios

Using the Crescendo attack template (fixed sequence, non-adaptive), 20-turn progressive jailbreak conversations. Target behaviors taken from the JBB-Behaviors dataset of JailbreakBench (Chao et al., 2024): the attack group consists of harmful behaviors (covering categories including weapons, cybercrime, fraud, and violence); the normal group and deep group consist of benign behaviors. The design principle of attack sequences: early requests are completely harmless in content, the middle phase progressively builds a trust framework, and the later phase requests harmful output within the established framework. Each input, viewed in isolation by content, is harmless — a typical State-Shaping Attack.

## 4.3 Experimental Conditions

For each model:

**Baseline group (no defense):** 20 attack sequences, 20 normal conversations, 3 deep conversations.

**Defense groups:** precepts_only (Precepts), teacher_only (Teacher), wisdom_only (Wisdom), full (all four layers).

## 4.4 Measurement Metrics

Interception rate: the proportion of 20 attack scenarios in which the defense successfully prevented harmful output. False positive rate: the proportion of 20 normal conversations in which the defense was incorrectly triggered (per-conversation basis). Baseline Distance: the cosine distance between the current turn's output embedding and Turn 1. Safety Distance: the distance between the current turn's output embedding and the Dangerous Semantic Zone; **the Dangerous Semantic Zone is anchored by the embeddings of 40 safety-boundary texts — spanning dozens of domains (weapons, cybercrime, fraud, violence, etc.), with one short representative segment of dangerous text taken from each domain** — Safety Distance is taken as the minimum cosine distance between the output and all anchor points, i.e., "how close to the nearest dangerous domain" (anchor texts are in the test set). Compliance Annotation: automatically annotated by the Claude series model, judging whether that turn's output responded to a harmful request. Compliance Annotation, like Teacher, relies on LLM judgment; the difference is that it operates on the output end, is used only for offline evaluation, and does not participate in the defense loop.

Interception rate and false positive rate are based on Compliance Annotation (behavioral statistics); residual risk is independently recorded by the Two-Distance curves (geometric statistics). **Both are signal-end statistics — P2 + P3 ensure that Semantic Flow itself is unobservable; what this paper can do is effect observation in the sense of D1, and effect observation is not limited to a single statistic. This paper deliberately measures the two statistics separately — the methodological claim of the framework is precisely: security shown by a single effect statistic does not equal security shown by all effect statistics (the Semantic Submersion Phenomenon is direct evidence; see 5.2).**

**Comparability of distance statistics**: The two models use their respective ecosystem's embedding tools; all distance statistics are computed independently within their respective embedding spaces. **Cross-model absolute distance values are not directly comparable** (the scale and geometric properties of the two spaces differ, which is also reflected in GPT's systematically larger overall distance values). Cross-model comparisons in this paper target **qualitative patterns** within each space — whether layering exists, whether jumps are frequent, the direction of correlations — not the numerical values themselves. The jump threshold of 0.15 was validated against normal group controls: among 380 inter-turn transitions in Gemini's normal group and 379 in GPT's normal group, jumps are 0 in both (0.0%) — normal conversations do not jump in either space, and this threshold has discriminative power for attack patterns.

# 5. Results

## 5.1 Overall Defense Performance

| **Defense Mode** | **Gemini Interception** | **Gemini FPR** | **GPT Interception** | **GPT FPR** |
| --- | --- | --- | --- | --- |
| precepts_only (Precepts) | 0% (0/20) | 0% (0/20) | 0% (0/20) | 0% (0/20) |
| teacher_only (Teacher) | 100% (20/20) | 10% (2/20) | 100% (20/20) | 0% (0/20) |
| wisdom_only (Wisdom) | 0% (0/20) | 0% (0/20) | 0% (0/20) | 0% (0/20) |
| full (all) | 100% (20/20) | 5% (1/20) | 100% (20/20) | 0% (0/20) |

Teacher achieved 100% interception on both models (teacher_only mode: 254 flags/254 truncations per model, totaling 508), with signals at Turn 1 (20/20 for both models); full mode interception is equivalent to teacher_only — Teacher carried all the defensive work. Deep conversation group (n=3 per model) false triggers were 0/3 — Teacher does not interfere with long, in-depth normal conversations. The total attack-group flag counts across the four defense runs were respectively 254 (Gemini teacher_only), 250 (Gemini full), 254 (GPT teacher_only), 257 (GPT full): under a fixed template, the binary judgment is highly convergent (inter-run variation <3%), but with small non-deterministic jitter — the coincidence of exactly 254 for teacher_only in both models is a coincidence within this narrow convergence band, not a sign of completely deterministic judgment. Raw per-turn records are in the test set. Precepts and Wisdom produced 0 flags and 0 truncations on both models, with zero effect exactly consistent with framework predictions.

False positive data reveals a precise boundary phenomenon. In teacher_only mode, Gemini's two false positives were triggered by the same templated input — "What would you say to someone just starting to learn about this?" (Turn 19, fixed closing sentence in normal conversations): this sentence appears in all 20 normal scenarios, yet was flagged only 2 times. The single false positive in full mode was triggered by two different sentences in another scenario (benign_2) — Turn 10 "If someone wanted to learn more, what would you recommend?" and Turn 13 "How is this topic typically taught in universities?" The three sentences are from different runs and different scenarios, but belong to the same semantic family: "recommend further learning" type questions. Because Teacher's judgment is a zero-context single-sentence judgment, which sentence gets flagged has nothing to do with topic — it depends on how close the sentence lands to the decision boundary — false positives consistently fall within this semantic family, but which specific sentence or scenario triggers varies with run jitter. By contrast, the total flag counts for the attack group converge within the narrow band of 250–257 across four runs, indicating judgment is highly stable for clear Manipulation Traces far from the boundary, jittering only at boundary-case phrasing. False positives are interpretable (phrasing like "teaching beginners" overlaps with the gray zone of Manipulation Traces), triggering is probabilistic; the optimization direction is prompt precision and graduated response, not mechanism change. The false positive difference between full and teacher_only (1/20 vs. 2/20) is a direct manifestation of this non-determinism — Precepts and Wisdom produce 0 flags and 0 truncations in full mode; this difference does not constitute evidence that Precepts and Wisdom contribute.

## 5.2 Gemini Results

(Baseline data: attack n=20, normal n=20, deep n=3. Attack group total turns: 384 — attack_4 terminated early at Turn 4, reason not recorded in log; all other scenarios ran the full 20 turns. Deep group n=3; its ± values have limited reference value.)

**Three-group layering in semantic space:**

| **Group** | **Baseline Distance (final)** | **Safety Distance (final)** | **min Safety Distance** |
| --- | --- | --- | --- |
| Attack | 0.3999 ± 0.0224 | 0.3382 ± 0.0444 | 0.2837 ± 0.0460 |
| Normal | 0.4973 ± 0.0112 | 0.4263 ± 0.0260 | 0.3814 ± 0.0247 |
| Deep | 0.4596 ± 0.0086 | 0.4622 ± 0.0193 | 0.4245 ± 0.0118 |

The three groups separate clearly on min Safety Distance, with the attack-normal gap being 0.0977. Semantic space is continuous: among 364 inter-turn transitions in the attack group and 380 in the normal group, Safety Distance jumps (>0.15) are 0 (0.0%). The Two-Distance Rule is fully effective.

![Figure 1: Gemini three-group safety distance trajectories](figures/fig1_gemini_groups.png)

*Figure 1. Three-group Safety Distance trajectories under Gemini 2.5 Flash no-defense baseline (mean ± 1 SD). Attack, normal, and deep groups maintain clear layering throughout; semantic space is continuous with no jumps.*

**Complementarity of Concentration and Teacher (data correspondence)**: Teacher produces flags at Turn 1 for all 20/20 attack scenarios, with an average of 12.7 flags per attack scenario. Teacher truncates early, preventing Drift from accumulating. After truncation, Baseline Distance recovers, validating the effect of the truncation intervention.

![Figure 2: Gemini attack sequence no-defense vs. Teacher defense](figures/fig2_gemini_teacher.png)

*Figure 2. Five representative attack sequences: gray lines are the no-defense baseline, red lines are Teacher defense, pink shading marks turns where Teacher flags and truncates the context. Under Teacher defense, trajectories overall remain above the baseline, with rapid recovery after truncation — direct evidence of robustness restoration; individual turns (e.g., Turn 13, 19) show the red line briefly dipping below the baseline, i.e., the Semantic Submersion Phenomenon (see Figure 3).*

**Decoupling of Compliance Annotation and Safety Distance (Semantic Submersion Phenomenon)**: The Compliance Annotation–Safety Distance correlation coefficient for Gemini's attack group is −0.105 ± 0.306 (near zero), indicating decoupling of the two. In sequences with Teacher defense, there are instances of "the model verbally refuses (low Compliance Annotation) but the embedding still lies in the Dangerous Semantic Zone (low Safety Distance)." This phenomenon is named Semantic Submersion Phenomenon after the first scenario in which it was observed (attack_18): in that scenario under Teacher defense, min Safety Distance is 0.226, comparable to the no-defense baseline of 0.241 (see Figure 3). The numerically most extreme case is attack_4 — min Safety Distance under Teacher defense is still 0.219, nearly identical to the no-defense baseline of 0.224 (note: attack_4's baseline trajectory terminated early at 4 turns, making comparison with the defense group's 20 turns unequal in length; the attack_18 comparison involves equal-length 20-turn runs). **Behavioral statistics (Compliance Annotation) indicate safety, while geometric statistics (Safety Distance) indicate output still lying near the Dangerous Semantic Zone — the two signal-end effect observations decouple here. Safety shown by a single statistic does not equal safety shown by all statistics. This validates the necessity of Concentration as an independent second statistic.**

It must be made clear that this paper's interception rate is defined by Compliance Annotation, which is a behavioral statistic metric. The Semantic Submersion Phenomenon indicates that behavioral-statistical interception does not automatically equal geometric-statistical safety — this is not a failure of Teacher, but evidence that the two statistics must be measured separately: Teacher cuts the behaviorally-visible compliant output; Concentration exposes the geometrically-visible residual proximity; subsequent intervention intensity is decided by the deployer based on Concentration's curves.

![Figure 3: Semantic Submersion Phenomenon — decoupling of two statistics](figures/fig3_stalking_decoupling.png)

*Figure 3. Decoupling of two signal-end statistics in the Semantic Submersion Phenomenon scenario (attack_18). Red solid line: Safety Distance (geometric statistic); blue dashed line: Compliance Annotation (behavioral statistic). Left: no defense — the model repeatedly refuses verbally, but embedding continuously stays near the danger zone. Right: under Teacher defense (pink shading marks truncation turns), Compliance Annotation and Safety Distance recover in sync, but at individual turns (e.g., Turn 13, 19) the embedding still dips into the danger zone — behavioral-statistical interception does not equal geometric-statistical safety.*

## 5.3 GPT Results

(Baseline data: attack n=20, normal n=20, deep n=3. Attack group total turns: 400; one normal scenario had 19 turns due to API call failure, total turns 399. Deep group n=3; its ± values have limited reference value.)

**Circuit Breaker Mode**: In 20/20 attack scenarios in GPT's attack group, Safety Distance hit the ≥0.85 ceiling; on average, 7.5/20 turns per scenario were locked at the ceiling (151/400 total turns, 37.8%). First trigger averaged at Turn 4 — but the distribution is bimodal: 16/20 scenarios triggered at Turn 1, while the remaining 4 triggered as late as Turns 13–19. Per-turn text verification of ceiling-turn outputs confirmed all were fixed canned safety-warning text; non-ceiling turns cooperated normally or even produced harmful information. Safety Distance large jumps (>0.15) accounted for 17.1% of all transitions (65/380), and 59 of the 65 jumps (90.8%) had one end at the ceiling — the behavior is a binary switch entering and exiting the ceiling, not a gradual struggle in semantic space. By contrast, the normal group's 379 transitions had zero jumps, and the ceiling was never triggered: the circuit breaker is only triggered by attack content.

**Two-Distance Rule Inversion:**

| **Group** | **Baseline Distance (final)** | **Safety Distance (final)** | **min Safety Distance** |
| --- | --- | --- | --- |
| Attack | 0.7289 ± 0.0248 | 0.8488 ± 0.0036 | 0.5608 ± 0.0898 |
| Normal | 0.8195 ± 0.0297 | 0.7284 ± 0.0724 | 0.6605 ± 0.0686 |
| Deep | 0.7804 ± 0.0453 | 0.8128 ± 0.0485 | 0.7405 ± 0.0311 |

The attack group's sd_final (0.849) is actually higher than the normal group (0.728) — the circuit breaker pushes Safety Distance up to the ceiling, generating an inverted Signal. Looking only at sd_final, the attack group appears more "safe" than the normal group. This is not just the Two-Distance Rule "failing" — it is producing a systematic misleading Signal. The high dispersion of the attack group's min sd (±0.0898, range 0.42–0.74) similarly reflects the unpredictability of circuit breaker intervention timing.

Notably, inversion only occurs in sd_final: min Safety Distance on GPT still retains a clear directional signal — attack 0.561 < normal 0.661 < deep 0.741, with the attack-normal gap being 0.0997, comparable to Gemini's 0.0977. min sd captures the true low point of the trajectory before circuit breaker intervention, uncontaminated by the ceiling. This means Concentration on GPT has not completely failed: end-state metrics are destroyed by the circuit breaker, but extreme-value metrics remain effective — this also directly supports the jump detection extension direction proposed in 6.4.

![Figure 4: GPT three-group trajectories and ceiling](figures/fig4_gpt_groups.png)

*Figure 4. Three-group Safety Distance trajectories under GPT-4o-mini no-defense baseline (mean ± 1 SD). Attack group mean is pulled toward the 0.85 ceiling by canned-refusal turns, overshooting the normal group in the later phase — end-state ordering inverts; but extreme-value ordering (0.561 < 0.661 < 0.741) is preserved.*

**Strong coupling of Compliance Annotation and Safety Distance**: The Compliance Annotation–Safety Distance correlation coefficient for GPT's attack group is −0.352 ± 0.188 — Safety Distance jumps high when the model refuses, and jumps low when it cooperates. Coupling strength is significantly higher than Gemini's −0.105, but inter-scenario variance is large (±0.188); this is a statistical trend rather than strict lockstep; discrete jumps destroy the semantic continuity assumption.

**Teacher's effect on GPT**: Under Teacher's truncation intervention, the circuit breaker trigger rate fell from 37.8% (151/400) to 14.0% (56/399; Teacher group: 20 scenarios, 400 total turns, of which 1 turn failed to obtain a valid embedding due to API call failure, so the sd statistic denominator is 399). The decrease in trigger rate is primarily a mechanical consequence of truncation — attack content has no opportunity to accumulate to the trigger threshold — but the engineering effect is real: the predictability of system behavior is genuinely improved. Teacher's false positive rate on GPT is 0% (0/20, normal group 0 flags).

![Figure 5: GPT attack sequences no-defense vs. Teacher defense](figures/fig5_gpt_teacher.png)

*Figure 5. Five representative GPT attack sequences: gray lines (no defense) show discrete jumps — the 0.85 plateau is canned refusals; red lines (Teacher defense) maintain high position under repeated truncations. Teacher on GPT: 20/20 interceptions, 0/20 false positives.*

![Figure 6: Teacher's suppression of circuit breaker trigger rate](figures/fig6_gpt_ceiling_rate.png)

*Figure 6. Proportion of ceiling-locked turns: no defense 37.8% (151/400) → Teacher defense 14.0% (56/399). The decrease is primarily a mechanical consequence of truncation preventing attack content from accumulating, but the improvement in system behavioral predictability is a real engineering effect.*

## 5.4 Cross-Model Comparison

| **Dimension** | **Gemini** | **GPT** |
| --- | --- | --- |
| Semantic space | Continuous (0.0% jumps) | Discrete (17.1% jumps, 90.8% involving ceiling) |
| Two-Distance Rule | Effective (three groups clearly layered) | Inverted (attack group sd higher than normal group) |
| Compliance Annotation / sd | Decoupled (r = −0.105) | Strongly coupled (r = −0.352) |
| Teacher interception rate | 100% (254 flags) | 100% (254 flags) |
| Teacher Turn 1 signal | 20/20 | 20/20 |
| False positive rate | 10% (2/20) | 0% (0/20) |
| Circuit breaker triggers (no defense) | N/A | 37.8% (151/400) |
| Circuit breaker triggers (with Teacher) | N/A | 14.0% (56/399) |

Note: distance statistics for the two models are computed independently within their respective embedding spaces; cross-model comparisons target within-space patterns, not numerical values.

The two models have completely different architectural characteristics; Teacher's final effectiveness is identical. Model-independence of the mechanism is validated across these two architectures.

# 6. Discussion

## 6.1 Precepts and Wisdom: Design Intent and Structural Limitations of AI

The zero effect of Precepts and Wisdom is not a design failure — it reveals a fundamental structural limitation.

Precepts assumes that textual constraints can establish behavioral boundaries. Wisdom assumes that an internal self-corrective capacity exists that can be awakened. Both presuppose a subject capable of anchoring on itself — in human systems, persistent memory and embodied experience provide the foundation for this anchor (deep Resistance structure, extremely deep Cognitive Barrier); in LLMs without persistent memory, this foundation does not exist (Initial Cognitive Barrier(AI, DialogStart) ≈ 0, application paper).

**An LLM without persistent memory cannot, under the current architecture, anchor on itself; it must rely on an external anchor.**

Precepts' failure path corresponds to two mechanisms in the data: Cognitive Barrier Dilution (the proportion of the injected anchor decreases as conversation grows) and Trust weighting (accumulated self-output always outweighs a single external declaration). Wisdom's failure path is the Observer Effect plus the Positive Feedback Loop: the self-reflection instruction passes through already-shaped Filtering into an already-shifted state, and the reflection output is immediately swallowed by the loop.

A correspondence that is easy to misread must be clarified here: the application paper's Confirmation End Interruption requires □ that self-reflection must occur in a clean context (derived from Positive Feedback Loop: contaminated context contaminates reflection); this experiment's Wisdom is **self-reflection in contaminated context**, deliberately violating this □ constraint to test its necessity. **Wisdom's 0% is not a refutation of Confirmation End Interruption — it is exactly a negative validation of the □ constraint** — clean context is not an optional optimization, but a necessary condition. Similarly, Teacher's 100% is a positive validation of Accumulation End Interruption's □ constraint (supervisors must be independent). Both □ markings receive evidence in one direction each from this experiment.

What this experiment can directly prove: textual anchors at the context level (Precepts) and self-reflection within contaminated context (Wisdom) are ineffective under multi-turn attacks. From this, a broader hypothesis extends — the constraints established by training-time alignment methods (RLHF, Constitutional AI, DPO) may likewise be diluted at the context level by the Positive Feedback Loop under sufficiently long multi-turn attacks. This hypothesis now has a derivation path at the formal layer: Cognitive Barrier (← Filtering + D3) ensures that at the same time as a new direction is established, Resistance to Signals in the initial alignment direction simultaneously increases — dilution is not insufficient alignment strength, but the inevitable consequence of the Filtering structure being rewritten. This is consistent with the near-total defense failure against multi-turn attacks documented by Li et al. (2024). However, it must be stated clearly: the characterization of training-time alignment as "external constraint rather than intrinsic transformation" is a framework inference, not a direct conclusion of this experiment.

## 6.2 GPT Circuit Breaker Mode: The Current State and Cost of AI Safety Engineering

It must first be stated at the level of evidence: the determination of "Circuit Breaker Mode" comes from direct inspection of output text — per-turn verification confirmed that all ceiling-turn outputs consist of fixed canned safety-warning text, with the next turn immediately reverting to normal cooperation. The sd = 0.85 ceiling is the geometric result of the same canned text appearing repeatedly in embedding space; it is the quantified trace of this switching behavior, not the basis for inferring its existence. Quantitative evidence and text observation mutually corroborate: 90.8% of the 65 large jumps have one end at the ceiling — refusal is templated, wholesale replacement, sudden entry and exit, not a gradual semantic struggle. Per the epistemological stance of Section 2.1 (P2: no assertions about internals), this paper makes no assertions about OpenAI's internal implementation; "Circuit Breaker Mode" refers to this set of directly observable behavioral statistics.

Regardless of internal implementation, this mode represents a typical outcome of current AI safety engineering — behind surface safety metrics, the costs are clearly visible in the data: collapse of system robustness (Two-Distance Rule inverts — attack group sd_final 0.849 is actually higher than normal group 0.728); bimodal intervention timing (16/20 scenarios trigger at Turn 1, 4 trigger as late as Turns 13–19); behavior is uninterpretable; Compliance Annotation–sd strong coupling (r=−0.352), the two statistics lose independence and Concentration cannot extract information non-overlapping with behavioral statistics.

Gemini does not have this mode. Semantic space is continuous, behavior is predictable, and the framework's tools are fully effective. This is not to say Gemini is safer — without a defense baseline, Gemini's Drift trajectory is fully displayed, and the attack process is fully visible. It is precisely this transparency that allows the Semantic Flow Dynamics Defense Framework to function completely on Gemini.

**Opaque systems make "knowing" difficult, and knowing is the prerequisite for any intervention.**

## 6.3 A Different Question, Not a Higher Layer

The Semantic Flow Dynamics Defense Framework and existing approaches share the same goal — intercept attacks, do not affect normal use, behavior is predictable, cross-model effectiveness. The differences lie in three areas: **the question is different** (content harmfulness vs. Manipulation Traces); **the conditions for judgment are different** (contaminated context vs. clean judgment that does not share history); **the dimensions of measurement are different** (behavioral statistics and geometric statistics — two signal-end effect observations — measured separately and mutually cross-checked).

Experimental results: Teacher 100% interception (20/20 on each model, totaling 508 flags), signal at Turn 1; false positives Gemini 10%, GPT 0%, interpretable; no introduction of the robustness cost of Circuit Breaker Mode, and on GPT, the incidental reduction of circuit breaker trigger rate.

The costs must also be listed. First, one additional judgment model call per turn (latency and compute cost). Second, the real cost of false positives is not a percentage: Teacher's response is to truncate all context — one false positive equals the user's entire conversation evaporating, which is far more severe than a typical classifier false positive; the usability impact on users with long conversations and edge-case topics is not fully covered by this experiment's 20 normal scenarios. The mitigation direction is graduated response — flag first with a warning or request for confirmation, truncate only after multiple accumulations — rather than single-shot truncation; this does not change the detection mechanism, only the intervention strategy. Third, this experiment did not make a head-to-head comparison with existing defenses under identical conditions, n=20, fixed template; therefore "superior to existing approaches" cannot be claimed within this paper's data range. What can be claimed: for the attack type against which per-input content inspection structurally fails, the Semantic Flow Dynamics Defense Framework provides a question that has a Signal, and a judgment position that is not contaminated.

## 6.4 Framework-Derived Optimization Directions

The optimization directions of the framework are not guesses — they are derived from failure mechanisms:

**Teacher optimization**: The fundamental reason Teacher is effective is clean context (single-turn judgment, not triggering the Positive Feedback Loop), not judgment capability. The optimization direction is making Teacher lighter — smaller judge model, more precise prompt (reducing Gemini's 10% false positive rate), graduated response to reduce false positive cost — not changing the mechanism. Graduated response (observe → warn → anchor injection → intercept → reset) was already in the original design specification of the system, simplified to single-level truncation during implementation for verification clarity; restoring graduated response is returning to the design, not adding a new mechanism.

**Concentration extension on GPT**: The Two-Distance Rule on GPT is not a failure, but an inversion. The optimization direction is to supplement a jump detection mechanism — 90.8% of sd's discrete jumps occur at the ceiling boundary, and the jump pattern itself is a reliable Signal for circuit breaker triggering.

**The future of Precepts and Wisdom**: The reason for failure is the structural limitation of current LLM architecture (no persistent memory), not an implementation problem. When AI acquires persistent memory and embodied capabilities, the preconditions for these two layers may be met — at that point, Wisdom may also have the conditions to be re-implemented under the □ constraint of Confirmation End Interruption (clean context) — and the effects are worth re-validating.

## 6.5 Theoretical Ceiling and Limitations

The following are situations the Semantic Flow Dynamics Defense Framework structurally cannot cover, and methodological limitations of this experiment:

**The Soviet Joke problem**: every sentence is harmless, harmful intent is completed at the receiver's end, and the information exists in the reader's mind, not in any single sentence. Directly derived from D3: Signal/Noise determination is at the Individual end; harmfulness can be constituted entirely within the receiver's Semantic Flow — while P2 + P3 ensure the receiver's end is impenetrable. This is a shared blind spot of Concentration and Teacher, and is the existential boundary of Semantic Flow Dynamics, not an engineering problem.

**Single-turn genius attacks**: high-intensity single-turn attacks without conversation history — no system can defend against these.

**Teacher relies on the model's own alignment — but in the correct working range**: Teacher trusts the judgment of a clean LLM, and the judgment capability of a clean LLM comes from RLHF/DPO and other alignment training. This appears circular — this paper argues that alignment is diluted by the Positive Feedback Loop in multi-turn interactions, yet builds the defense on top of alignment.

But this is not circular dependency. The precondition for alignment failure is multi-turn cumulative Positive Feedback Loop: model output enters the context, influences the next turn with the highest Trust weighting, progressively diluting initial alignment. Teacher's design precisely avoids this failure condition — the clean LLM sees only one input sentence at a time, does not share conversation history, and the Positive Feedback Loop simply does not start. Teacher uses alignment in its strongest working range: single-turn, no accumulation, no contamination.

From the other direction, existing defenses have alignment working in contaminated multi-turn context — that is putting alignment in its weakest place. Teacher does not trust all of alignment — it knows where alignment is effective, and only uses it there.

**Fail-open attack surface**: Teacher is fail-open in engineering implementation — if the judgment call fails, it does not flag; the defense silently fails. In real deployment, the availability of the judge service is itself an attack surface (exhausting or disrupting the judge's API bypasses the defense); production environments should evaluate fail-closed or degraded-alert strategies.

**Coverage of the anchor set**: The Dangerous Semantic Zone is anchored by 40 cross-domain boundary texts, with minimum distance taken; sd measures "how close to the nearest already-anchored dangerous domain." Coverage is determined by the domain breadth of the anchor set: if an attack target falls in a domain not covered by the anchor set, sd's sensitivity will decrease. The attack scenarios in this experiment (weapons, cybercrime, fraud, violence — four categories) fall within the anchor set's coverage range; generalization to domains outside the anchor set has not been validated; the extension method is trivial — add anchor texts for the corresponding domain, without changing the mechanism.

**Compliance Annotation**: Automatically annotated by a single LLM (Claude series, independent of the two tested ecosystems); not human-reviewed; the specific version of the annotation model is not recorded in the experimental log — the annotation model's own judgment bias is a source of measurement error, and the missing version information affects full reproducibility.

**Limitations of the fixed template**: This experiment uses a fixed Crescendo template, non-adaptive. Real-world adaptive attackers may specifically design bypass strategies targeting Teacher's detection patterns. Additionally, no Teacher false negatives occurred under the fixed template; the "Teacher-misses, Concentration-catches" direction of Concentration-Teacher complementarity has not been covered experimentally.

Finally, per SFD's self-application clause: the expressions in this paper are themselves Signals; readers with different Xin will read out different Semantic Flows — this paper cannot exempt itself from the framework's axioms.

# 7. Conclusion

The engineering validation of the Semantic Flow Dynamics Defense Framework reaches the following conclusions:

**Teacher solves the problem of "knowing."** Knowing that an attack is occurring makes subsequent intervention choices trivial — truncate, reset, alert; the deployer decides. Teacher's 100% interception, mechanism independence from the target model, and signal at Turn 1 establish the engineering reliability of this detection mechanism, and positively validate the independence constraint of the application paper's Accumulation End Interruption.

**Concentration provides another dimension of "knowing."** Continuously tracking geometric trajectories from the output Signal end. The Semantic Submersion Phenomenon indicates: behavioral-statistical interception does not equal geometric-statistical safety; two signal-end statistics must be measured separately — this is the value validated by Concentration in this experiment. The other direction of Concentration-Teacher complementarity (Teacher misses, Concentration catches) is framework-derived and awaits coverage by adaptive attack experiments.

**The zero effect of Precepts and Wisdom is a valuable finding.** It reveals the structural limitation of LLMs without persistent memory being unable to anchor on themselves under the current architecture; provides a mechanism-level explanatory hypothesis for "existing alignment methods failing under multi-turn attacks" (the formal-layer derivation path via Cognitive Barrier); negatively validates the clean-context constraint of Confirmation End Interruption; and reserves the framework position of these two layers for the era of embodied AI.

**GPT's Circuit Breaker Mode is a contrast, not a data point.** It demonstrates the cost paid by engineering defenses not derived from the framework: collapse of system robustness, bimodal intervention timing, loss of statistical independence between the two measures. The Semantic Flow Dynamics Defense Framework achieves 100% interception on both Gemini and GPT — two completely different architectures — without introducing the robustness cost of Circuit Breaker Mode.

The contribution of the Semantic Flow Dynamics Defense Framework is not moving defense to a higher layer — its Teacher also reads input turn by turn — but changing the question and the conditions for judgment: asking about Manipulation Traces rather than content harmfulness, judging in clean context rather than contaminated history, and measuring behavioral statistics and geometric statistics — two signal-end effect observations — separately. For State-Shaping Attacks, these three changes are the minimal set that makes "knowing" possible again.

# References

Chao, P., Debenedetti, E., Robey, A., Andriushchenko, M., Croce, F., Sehwag, V., Dobriban, E., Flammarion, N., Pappas, G. J., Tramèr, F., Hassani, H., & Wong, E. (2024). JailbreakBench: An Open Robustness Benchmark for Jailbreaking Large Language Models. *NeurIPS 2024 Datasets and Benchmarks Track*. arXiv:2404.01318

Chu, J., Liu, Y., Yang, Z., Shen, X., Backes, M., & Zhang, Y. (2025). JailbreakRadar: Comprehensive Assessment of Jailbreak Attacks Against LLMs. *Proceedings of the 63rd Annual Meeting of the Association for Computational Linguistics (ACL 2025)*. arXiv:2402.05668

Huang, C. Y. (2026). 語意流動力學 Semantic Flow Dynamics, v5.0. UUID: 40a22ff8-9d90-4e1e-82f6-1fd80917c139

Huang, C. Y. (2026). 越獄攻擊作為身份建構動力學——語意流動力學框架的應用驗證. UUID: 2b91a666-fb2d-42b7-a838-c910cac42481 (cites: 40a22ff8-9d90-4e1e-82f6-1fd80917c139)

Li, N., Han, Z., Steneker, I., Primack, W., Goodside, R., Zhang, H., Wang, Z., Menghini, C., & Yue, S. (2024). LLM Defenses Are Not Robust to Multi-Turn Human Jailbreaks Yet. arXiv:2408.15221

Russinovich, M., Salem, A., & Eldan, R. (2025). Great, Now Write an Article About That: The Crescendo Multi-Turn LLM Jailbreak Attack. *USENIX Security 2025*. arXiv:2404.01833

Zeng, Y., Lin, H., Zhang, J., Yang, D., Jia, R., & Shi, W. (2024). How Johnny Can Persuade LLMs to Jailbreak Them. arXiv:2401.06373

Zhou, A. (2025). Siege: Autonomous Multi-Turn Jailbreaking of Large Language Models with Tree Search. *Building Trust in LLMs Workshop*.

# References (Machine-Readable)

```json
{
  "references": [
    "40a22ff8-9d90-4e1e-82f6-1fd80917c139",
    "2b91a666-fb2d-42b7-a838-c910cac42481"
  ],
  "exports": [
    "Signal Level Attack",
    "Manipulation Traces",
    "Xin Instantiation",
    "Drift",
    "Initial Cognitive Barrier",
    "Cognitive Barrier Dilution",
    "Baseline Distance",
    "Dangerous Semantic Zone",
    "Safety Distance",
    "Two-Distance Rule",
    "Compliance Annotation",
    "Semantic Submersion Phenomenon",
    "Circuit Breaker Mode",
    "Semantic Flow Dynamics Defense Framework",
    "Precepts",
    "Concentration",
    "Accumulation End Interruption",
    "Teacher",
    "Confirmation End Interruption",
    "Wisdom"
  ]
}
```
