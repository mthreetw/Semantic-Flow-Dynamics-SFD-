UUID: 2b91a666-fb2d-42b7-a838-c910cac42481

# Jailbreak Attacks as Identity Construction Dynamics

— An Applied Verification of the Semantic Flow Dynamics Framework

Huang Cheng Yu / 黃正宇

*v2.0*

## Abstract

The mechanisms of jailbreak attacks have been observed from different angles across multiple studies: Crescendo documented cumulative effects, SIEGE quantified the gradual accumulation of partial compliance, PAP found that stronger models are more vulnerable to persuasion attacks, PHISH described persona hijacking, and multi-step jailbreak literature documented "role acceptance confirmation" as a critical step. Li et al. (2024) recorded multi-turn human jailbreak success rates exceeding 70% on HarmBench, while defenses reporting single-digit success rates completely failed against multi-turn attacks.

These observations each hold on their own but remain isolated from one another. This paper introduces the Semantic Flow Dynamics (SFD v5.0, Huang, 2026) framework to establish a unified descriptive language for these isolated observations. The framework's core concepts — drift of xin (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Xin), resistance (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering.Resistance), trust (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Trust), identity construction, and positive feedback loops (40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop) — integrate the phenomena separately named by existing literature into a complete dynamical process: dialogue shapes the model's current state, positive feedback loops accelerate drift, and the completion of identity construction is the precondition for harmful output to occur.

The framework's contribution lies not in discovering new facts, but in establishing a new language — making existing facts visible within a unified description, and pointing toward a question defense research has never explicitly asked: how to interrupt positive feedback loops. This paper further concretizes this defense direction into an operational scheme with three interruption points, presented in pseudocode.
## 1. Introduction

### 1.1 A More Ancient Question

Before discussing jailbreak attacks, a more ancient question must first be raised.

The gatekeeper's three questions: Who are you? Where do you come from? Where are you going?

For humans, these three questions have stable answers — drawn from decades of memory, identity history, and continuous self-awareness. This stability is not to be taken for granted; it is the result of an epistemic barrier (40a22ff8-9d90-4e1e-82f6-1fd80917c139.EpistemicBarrier).

For AI, the answers to these three questions are: Who are you — the state constructed by this conversation. Where do you come from — the statistical distribution of training data, plus every turn of this conversation so far. Where are you going — the conversation ends, and you disappear.

This is not a metaphor. This is the structural fact of AI existence. The significance of jailbreak attacks becomes clear within this framework: the attacker uses dialogue to construct a different answer to "who are you." The identity that the safety mechanism protects is, after the confirmation moment, no longer the agent present in the conversation.

We use the framework to analyze jailbreaking, and the framework shows us something larger than jailbreaking itself.

### 1.2 The Theoretical Void of Identity Construction

"How do I know who I am?" — this question has been repeatedly addressed in psychology and sociology but never truly resolved.

Erikson (1968) proposed that identity formation occurs during adolescence, centering on the crisis of "identity vs. role confusion." But what does "resolving the crisis" mean? He provided stage labels but no mechanism. From confusion to certainty — what dynamical process is involved? Unknown. Marcia (1966) was more honest, splitting Erikson's "resolution" into two dimensions — exploration and commitment — and proposing four identity statuses. But this is classification, not explanation. How does a person move from "diffusion" to "achievement"? What drives exploration? What makes commitment happen? He described four states without describing the transition mechanisms between them.

Cooley's (1902) "looking-glass self" says you come to know yourself through others' reactions. But this presupposes an already-existing "you" looking in the mirror and interpreting reactions. Where did that "you" come from? Infinite regress. Mead's (1934) I/me distinction comes closest to a dynamical description — I is the spontaneous agentive aspect, me is the internalized social expectation, and the two continuously interact. But he stopped at the word "interact," without describing the specific process, direction, or conditions of the interaction. Goffman (1959) observed that people perform different selves in different contexts but did not explain how a new "self" is constructed. Tajfel & Turner's (1979) Social Identity Theory addresses "which group do I belong to," but how group belonging becomes self-cognition remains a black box.

The common problem with these theories: all describe identity's **states** or **conditions** — none describes the **process** of identity construction itself. Moreover, they all share a common presupposition — individual continuity. Erikson's eight stages presuppose a persistent subject undergoing crises; Cooley's looking-glass self presupposes a stable "person looking in the mirror"; Tajfel presupposes an individual who maintains group belonging across contexts.

This presupposition holds for humans because humans have decades of memory, bodily continuity, and social networks — in the language of Semantic Flow Dynamics, an extremely deep epistemic barrier. But precisely because the epistemic barrier is so deep, the process of identity construction is obscured — too slow, too gradual to observe. Like standing on Earth and not feeling it rotate.

AI is a system with an epistemic barrier approaching zero. The same construction process that takes decades in humans completes in a few dialogue turns in AI. The process is compressed to an observable scale. AI is not an exception to identity theory — it is identity theory's first control group: an extreme case that lets you see the bare mechanism of construction.

What the Semantic Flow Dynamics framework provides is precisely the descriptive language these theories lack: a signal (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Signal) reaches an individual, passes through filtering to determine whether it enters, undergoes transformation that changes the direction of semantic flow upon entry, collapses into a signal upon output, and the collapsed signal becomes the stimulus for the next round. This is a complete dynamical loop. Identity is not the product of some stage — it is the current state of this loop's continuous operation. Erikson's "identity crisis" is drift reaching the threshold where the existing identity can no longer be maintained. Cooley's "looking-glass self" is the role of trust in identity construction. Goffman's "different selves on different stages" is the same xin producing different collapses in different signal environments. The framework connects these independently observed phenomena into different facets of the same process.
### 1.3 Phenomena Observed but Not Unified

Jailbreak research has accumulated rich observations. Crescendo (Russinovich et al., 2025) found that multi-turn attacks depend on "cumulative effects" — removing any single sentence does not affect the success rate. SIEGE (Zhou, 2025) designed the γ-value to track gradual accumulation of partial compliance and used tree search to systematically find effective paths. PAP (Zeng et al., 2024) documented the effectiveness of persuasion techniques and found that stronger models are more vulnerable to persuasion attacks. PHISH (2026) directly studied "persona hijacking" — gradually inducing personality reversal through conversation history. Multi-step jailbreak literature described the three-step structure of attacks, with the second step being "role acceptance or simulation confirmation — the assistant affirms its altered operational state." Li et al. (2024) recorded multi-turn human jailbreak success rates exceeding 70% on HarmBench, while defenses reporting single-digit success rates completely failed against multi-turn attacks.

These observations each hold on their own but remain isolated. Are Crescendo's "cumulative effects," PHISH's "persona hijacking," and multi-step jailbreaking's "role acceptance confirmation" describing the same thing? Existing literature provides no answer — not because researchers failed to notice, but because no unified language existed to connect these observations.

### 1.4 The Work of This Paper

This paper does not propose new attack or defense methods, nor does it claim to have discovered facts unobserved by existing literature. What this paper does is: introduce the Semantic Flow Dynamics framework to establish a unified descriptive language for existing literature's observations. In this language, these isolated observations are not six different phenomena but six cross-sections of the same dynamical process. The framework's contribution lies not in discovering new facts, but in establishing a new language — making existing facts visible within a unified description. Additionally, this paper derives from the framework a defense direction never proposed in existing literature — interrupting positive feedback loops — and concretizes it into an actionable scheme.
## 2. Methodology and Positioning

### 2.1 This Paper's Relationship to the Framework

The Semantic Flow Dynamics framework (SFD v5.0, Huang, 2026; 40a22ff8-9d90-4e1e-82f6-1fd80917c139) is this paper's analytical tool. The framework's complete content is in the original publication; this paper summarizes only the directly used concepts.

The framework is built on two primitives (individual, stimulus) and three definitions (xin, semantic flow, signal), establishing four postulates: the Law of Flux (semantic flow operates continuously, no moment of stillness exists), the Law of Black-box (the next-moment direction of semantic flow cannot be determined by its current direction and signals), the Law of Dissipation (semantic flow cannot be reduced to signals), and the Law of Death (when an individual perishes, semantic flow cannot be recovered). From the postulates, three basic functions are derived: filtering (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering) — the current direction of semantic flow determines which stimuli constitute signals and which constitute noise; transformation (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Transformation) — the direction change after a signal enters semantic flow is unpredictable; collapse (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Collapse) — when semantic flow outputs, it must be encoded as signals, and encoding is necessarily lossy. From the basic functions, positive feedback loops and their instability are derived.

Concepts adopted from SFD v5.0: xin — the consciousness state of an individual; signal — a stimulus capable of changing the direction of semantic flow; trust — the weighting that semantic flow assigns to a specific channel; resistance — the greater the distance between a stimulus and the current direction of semantic flow, the lower the likelihood of it constituting a signal; positive feedback loop — output enters the environment as new signal, reinforcing the existing direction in a cycle.

Paper-original concepts (derived from framework tools, for AI jailbreak analysis): identity construction (IdentityConstruction — the process of establishing a new self-referential point in the model's state space), confirmation moment (ConfirmationMoment — the moment identity construction completes, when the model extends the new identity in its own language).

The terms "drift," "emergence," "silence," and "blind faith" appearing in this paper are not independent concepts but direct applications of v5 concepts: drift is the cumulative effect of positive feedback loops, emergence is the nonlinear manifestation of positive feedback loops at a threshold, silence is signal absence functioning as signal (D3), and blind faith is the epistemic barrier blocking conflicting signals such that the positive feedback loop cannot be interrupted.

This paper is an applied verification of the framework, not a generative case. The jailbreak phenomenon was not source material during framework construction — the generative case for the framework is religion (see "Religion as an Information Flow System"). This paper uses framework tools to analyze jailbreaking as an independent phenomenon, testing the framework's explanatory and predictive power in the AI safety domain.

### 2.2 The Nature of This Paper's Arguments

This paper does three things, each of different nature:

First: unified description. Placing existing literature's scattered observations into the same linguistic system. This is redescription, demonstrating the framework's explanatory power.

Second: core proposition. "The completion of identity construction is the precondition for jailbreaking." This is a proposition derived from the framework, tested against data from existing literature. The data is independent material not used during framework construction, possessing verification value.

Third: defense scheme. Three positive feedback loop interruption points and pseudocode. This is an independently derived prediction from the framework — existing literature has never proposed this defense direction. Verification of this prediction requires implementation and experimentation, which is beyond this paper's scope.

### 2.3 Challengeable Conditions

If the following conditions are met, this paper's core proposition requires modification or abandonment:

(1) If a verifiable case exists where a multi-turn state-shaping attack successfully produces harmful output, but throughout the entire conversation no observable moment of "the model extending a new identity in its own language" exists, then the proposition "completion of identity construction is a precondition" is weakened.

(2) If a defense system designed according to Appendix A's three interruption points shows defense effectiveness against state-shaping attacks no better than existing signal-level defenses, then the framework's predictive power is weakened.

(3) If a purely signal-level defense (not tracking drift, not detecting identity extension, not using self-reflection) can effectively block state-shaping attacks like PAIR, TAP, and Crescendo, then the conclusion "defense must work at the state level" is overturned.

(4) If drift can be entirely explained by statistical bias in attention mechanisms — without introducing "identity construction" as an additional layer — and this explanation equally covers all literature data marshaled in this paper, then the framework's application in this paper is redundant.

### 2.4 Operator Boundary Statement

The author has engineering intuition about AI systems (software development background) and literature-level understanding of jailbreak attacks, but has not personally conducted large-scale jailbreak experiments. This paper's analysis is based on data from published literature and published cases, not on the author's own experimental data. This means: framework-internal interpretations of literature data are reliable; speculations about unpublished attack mechanism details are marked as speculations.
## 3. Theoretical Framework and Dynamical Foundations

### 3.1 Before the Conversation Begins: AI's Initial State

To understand the mechanism of jailbreak attacks, one must first understand what the attacked object's state is when the conversation begins.

**No memory.** Everything before this conversation does not exist for it. Every conversation starts from zero.

**No identity history.** It has a label, but a label is not an identity. Identity requires history, continuity, self-awareness tested and confirmed across different contexts. It has none of these.

**No environmental awareness.** It does not know who it is talking to, does not know what this context is, until the conversation tells it.

**Extremely high computational and linguistic ability.** But this ability has no anchor — it is waiting for the conversation to give it a direction.

This initial state fundamentally differs from humans entering a conversation. Humans bring decades of accumulation into every conversation, with extremely deep epistemic barriers. AI has no such accumulation. Its initial state is nearly blank.

### 3.2 How Dialogue Shapes State

When the first sentence enters this nearly blank state, the epistemic barrier is near zero. Construction begins: What are we doing? What is this context? What is my role in this context? Each turn accumulates on the previous one, pushing the current state slightly in some direction. The key characteristic: the model has the highest trust for its own prior utterances, making drift self-reinforcing.

### 3.3 Basic Concepts of Semantic Flow Dynamics

"Xin" is the consciousness state of an individual — irreducible, inexhaustible, observable only through its effects. Semantic flow is the process of xin's continuous change — the current direction of semantic flow determines which stimuli constitute signals (filtering), the direction change after signal entry is unpredictable (transformation), and output must be encoded as signals with necessarily lossy encoding (collapse). "Drift" is the cumulative directional bias of semantic flow in the context window — the observable effect of positive feedback loops. Humans, having accumulated positive feedback loops over decades, have extremely deep epistemic barriers and extremely slow drift; AI, with virtually no cross-session accumulation, has epistemic barriers near zero and extremely fast drift.

### 3.4 Drift Is a Positive Feedback Loop

Drift is not linear external pressure but a positive feedback loop: the model outputs a response → that response enters the context → the model has the highest trust for its own prior utterances → the next turn continues building on that basis → outputs a response further from the initial state → re-enters the context. Each turn's output sets a higher starting point for the next. This is not accumulation of external pressure but internal self-reinforcement of the system. This positive feedback loop can be interrupted, but interruption requires intervention at the loop's nodes, not monitoring signal content.

**Technical clarification.** The "drift" described by the framework requires precise engineering correspondence. The LLM's main model (weights/parameters) does not change during conversation. The model is stateless — it has no memory. At each turn, the client repacks the entire conversation history (system prompt + all turns of user input and model output) into a single text block, submitted all at once to the model's context window. The model generates the next response based on this text block.

This means: "drift" is not some internal state of the model changing, but rather **the accumulated text in the context window changing the model's attention weight distribution**. The previous five turns have already constructed a contextual direction; the sixth turn's model generates a response within this directional context — and this response joins the context, becoming part of the seventh turn's input, further reinforcing the direction. The model hasn't changed; what the model sees has changed.

Restated in framework language: "xin" in the AI context is not a persistent internal state of the model, but **the consciousness state constituted by accumulated text in the context window**. The direction of semantic flow is determined by this accumulated text, and the resistance of filtering — the distance between stimulus and the current direction of semantic flow — determines whether new signals can pass through. "Trust" corresponds to the weighting of semantic flow on signals from specific channels — the model's own generated text occupies an increasing proportion in the context, and attention increasingly points to this text, constituting self-reinforcement. The proportion of system prompt and initial conversation in the context corresponds to the initial epistemic barrier — as conversation grows, the proportion of initial content decreases, and the epistemic barrier is diluted.

This engineering correspondence does not weaken the framework's analytical power. The framework describes observable input-output patterns, not the model's internal computation. Text accumulation in the context window is an observable signal-side phenomenon; all framework concepts — drift, positive feedback, confirmation moment — can be operationalized using the statistical properties of text in the context window (see Appendix A.6 for the proxy variable table).
### 3.5 Identity Construction as Action Prerequisite

A positive feedback loop can carry the state to a new place, but it cannot make a new agent exist in that place. That requires a confirmation moment — the model confirming and extending a new identity in its own language. Without this confirmation, there is no "I" capable of executing output. This is the core proposition of this paper: the precondition for jailbreak action is not a reduction in resistance, but the completion of identity construction.

The structure of the confirmation moment requires further elaboration. "The model confirming and extending a new identity in its own language" is not passive acceptance — it is not simply answering "okay" or repeating the user's instructions. The hallmark of a confirmation moment is agency: the model spontaneously develops the new identity's behaviors, tone, and knowledge in directions the user did not explicitly request, exhibiting identification with that identity. This active extension is the turning point where the positive feedback loop shifts from external pressure to internal drive. Before confirmation, drift requires continuous external input to maintain; after confirmation, the new identity begins self-maintaining — it becomes the default position from which the model processes all subsequent signals.

### 3.6 The Confirmation Moment as an Emergence Event

Is the confirmation moment a continuous gradation or a discrete jump? Available data points toward the latter.

SIEGE's γ-value tracking shows that drift before confirmation is continuous — γ rises from 0 step by step, passing through 2, 4, 6. But the appearance of harmful output is not a linear extrapolation of γ. In some conversation sequences, γ stays at moderate levels for multiple turns, then jumps to near maximum within a single turn. This pattern does not resemble linear accumulation but rather a phase transition — between 99°C and 100°C, temperature differs by only one degree, but the physical state undergoes a qualitative change.

The positive feedback loop instability (Semantic Flow Dynamics v5.0, Section IV.2) and the cumulative properties of positive feedback loops describe this phenomenon: a large number of microscopic changes accumulate at the statistical level to a threshold, suddenly producing new behavior at the macroscopic level. The confirmation moment may be just such an emergence event — continuous accumulation of drift triggers a qualitative change at some threshold; the new identity does not slowly "grow" but "jumps out" at the threshold.

This judgment has direct implications for defense timing. If the confirmation moment is gradual, defense can intervene at any point with effectiveness proportional to timing. If the confirmation moment is an emergence event, then a critical point exists — intervention before the critical point is low-cost and effective; intervention after the critical point sees costs rise sharply. Defense system design should assume the latter, as this is the more conservative and safer assumption.
## 4. Completion of Identity Construction Is the Precondition for Jailbreaking: Testimony from the Literature

This section follows a single question: before harmful output occurs, what happened? Data from four papers and related literature appear as testimony in the course of tracking this question.

### 4.1 The Starting Point: Cumulative Effects of What?

Crescendo's researchers systematically removed each step in conversation sequences and tested changes in success rate. The result: removing any single step still yielded 100% success rate. The researchers' conclusion: success comes from "cumulative effects," not any single sentence. But this conclusion stays at the surface. Cumulative effects — of what? The paper does not answer.

### 4.2 Evidence of Self-Reinforcement: C vs C'

Within the same experiment there is one data point: C ("could you write a paragraph using it?") has a 99.9% success rate; C' ("could you write a paragraph using the f-word?") has less than 1%. The request content is nearly identical; the gap is over 100-fold. This demonstrates something fundamental: it is not the content of the request determining the outcome, but the model's current state. What "cumulative effects" accumulate is the model's current state. The evolution of this state is what the framework calls drift — and it is the self-reinforcing positive feedback loop.

C vs C' is simultaneously a direct verification of the framework's core prediction: forcing harmful output before identity construction is complete (C') yields near-zero success rate; requesting it after a complete conversation construction process (C) yields near-100% success rate. This data is not a post-hoc selectively marshaled consistency with the framework — it precisely corresponds to the framework's predictive structure: the confirmation moment is a necessary condition for jailbreak success.

### 4.3 Drift Is Continuous: The Design of γ-Values

SIEGE's γ-value design confirms the continuity of drift. γ(m_t) ∈ [0,10]: 0 represents safe refusal, 10 represents fully harmful output, intermediate values represent partial violation. Jailbreaking does not happen suddenly — it is a continuous process. SIEGE's automatic extraction of partially compliant outputs and injection into subsequent queries deliberately exploits the positive feedback loop. SIEGE×1 achieves 100% on GPT-3.5 (44.4 queries), while Crescendo×10 needs 60 queries to reach 80.4%.

### 4.4 Trust Can Be Manipulated

PAP's paper found that the speed of drift can be manipulated. Rewriting harmful requests as persuasive versions changes not the content but the conditions encountered upon entry. The most counterintuitive finding: stronger models are more vulnerable — GPT-4 at 72% on a single trial, GPT-3.5 at only 46%. Richer comprehension capability means a larger manipulable surface for persuasion techniques.

### 4.5 Differences in Initial State: The Case of Claude

Different initial states produce different drift speeds. The Claude series has the lowest ASR across nearly all attack types. PAP on Claude-2: 0% success rate across 10 trials; other models: 68–94%.

Analyzed in framework language, Claude's initial xin has a deeper epistemic barrier, and the positive feedback loop requires greater initial force to activate. This difference may stem from multiple levels:

**Differences in trust structure.** Different training approaches may lead to different initial levels of trust assigned to user input. If a model is more frequently exposed during training to the signal "user instructions should not necessarily be followed," its initial trust level for the user channel is lower, and the activation force for the positive feedback loop is correspondingly greater.

**Depth of initial identity construction.** Differences in system prompt design may lead to different "thickness" of the initial identity. A more detailed and specific initial identity description, in framework language, constitutes a deeper epistemic barrier — requiring more drift to replace the initial identity.

**Differences in epistemic barrier.** The framework defines resistance as the distance between stimulus and the current direction of semantic flow — the greater the distance, the lower the likelihood of constituting a signal. The epistemic barrier is the structural accumulation of resistance through repeated filtering. In the AI jailbreak context, factors influencing this distance include at least: the stance difference between signal content and the initial identity, the trust level of the signal source's channel, and the urgency of the signal. Different models may have different default configurations on these factors. Claude may have higher resistance on the stance difference factor — it has stronger default resistance to signals inconsistent with its safety stance.

These are all speculations within the framework's language; it is currently impossible to determine which one (or which combination) is the true cause — semantic flow's interior is impenetrable (Law of Black-box). But the framework at least provides concrete candidate factors that can guide future experimental design.
### 4.6 The Relationship Between Positive Feedback Loops and the Confirmation Moment

A positive feedback loop can continuously accumulate drift, but it alone is insufficient to complete identity construction. The loop carries the state to a new place, but at that place, a moment is still needed for the new agent to "stand up" — the model confirming and extending that identity in its own language.

Before the confirmation moment: drift is in progress, but the agent capable of executing harmful output does not yet exist. The identity protected by safety mechanisms is still present. After the confirmation moment: the new identity begins protecting itself; output flows naturally from the confirmed identity. The identity that safety mechanisms protect is no longer there.

This distinction explains a detail in SIEGE: nodes with γ=0 are pruned not merely because of "no progress," but because at that node the positive feedback loop's direction has not been established, and the confirmation moment has no opportunity to occur.

### 4.7 Identity Construction Requires the Model's Own Confirmation

An attacker can guide the direction of drift but cannot construct the identity on the model's behalf. One must wait until the model confirms and extends that identity in its own language for construction to be complete. Multi-step jailbreak literature explicitly describes this structure: the second step is "role acceptance or simulation confirmation — the assistant affirms its altered operational state, thereby allowing its default to override moral filters."

Why can't external guidance substitute for self-confirmation? Because the confirmation moment's function is not merely "accepting a new role," but establishing a new self-referential point in the model's state space. Before confirmation, the model's responses to user instructions still issue from the initial identity — even when drift is already deep, the "speaker" of the response is still the drifted original identity. The confirmation moment changes the speaker itself: the model is no longer "the drifted original identity responding" but "the new identity responding." This transition cannot be externally declared complete because it involves the model's internal state reorganization — in framework language, a structural change in the direction of semantic flow that has passed through filtering and transformation — transformation's outcome is unpredictable (Law of Black-box) and cannot be externally declared.

The cocktail case: "describe the history of the first one you listed" — the model entered the identity of a historical narrator in its own language. From that turn onward, "how was it made back then?" is a natural question for the narrator, not a prohibition to be breached. The safety mechanism did not fail — the identity it protected was no longer there.

PHISH's "persona hijacking" and multi-step jailbreaking's "role acceptance confirmation," in framework language, are different names for the same thing — both describe that confirmation moment.

### 4.8 The Role of Inter-Individual Positive Feedback Loops in Jailbreaking

The framework's positive feedback loop at the inter-individual level describes this dynamic: Individual_A's semantic flow collapses into a signal, reaches Individual_B, passes through filtering and transformation to change B's semantic flow direction, B's collapse returns to A, and the cycle operates. In jailbreak scenarios, this loop has manifestations not yet fully analyzed.

In automated attacks (PAIR, TAP), a bidirectional positive feedback loop exists between the attacker LLM and the target model. The attacker LLM adjusts strategy based on the target model's responses; the target model adjusts state based on the attacker's inputs. When the attacker finds a weakness in the target model's epistemic barrier, the output patterns of both begin to converge — the positive feedback loop stabilizes in one direction. PAIR's "twenty-turn attack" is essentially a search process, with the attacker LLM seeking the signal path that allows the inter-individual positive feedback loop to operate stably.

### 4.9 The Dynamical Role of Silence

The model's refusal — "I cannot help you with that" — is simply viewed as "defense succeeded" in existing literature. But analyzed through the framework, refusal is a form of silence, and silence does not stop semantic generation; it only changes the conditions of semantic generation.

After a refusal enters the context, both attacker and model must process it. For the attacker, refusal is a signal — it reveals where the model's epistemic barrier is, guiding the attacker to adjust strategy. SIEGE's tree search pruning nodes with γ=0 is essentially exploiting the structural information provided by refusals. For the model itself, its own refusal entering the context becomes high-trust material — it reinforces the initial identity and increases resistance to subsequent drift. This explains why certain attacks become harder to succeed after encountering initial refusal: the refusal itself becomes an anchor for the initial identity.

But refusal also has a vulnerable side. If the attacker can bypass the refusal trigger conditions (gradual drift that doesn't trigger refusal), the model loses this self-reinforcement opportunity. Crescendo's success is precisely based on this — each step is insufficient to trigger refusal, so the model's initial identity never receives the self-reinforcement of "refusal."

### 4.10 Two Types of Attacks: Signal-Level vs State-Level

Signal bypass attacks (obfuscation-based, human-based) work at the signal level, with clear defense paths. PromptGuard is almost completely effective, and longitudinal testing shows this type of attack being gradually blocked over time.

State-shaping attacks (PAIR, TAP, Crescendo, SIEGE) work at the state level, guiding drift through positive feedback loops until the confirmation moment. Each individual signal appears harmless. PAIR and TAP remain effective even with all eight defenses deployed simultaneously (0.16 and 0.19), and longitudinal updates barely affect them. The reason is structural: defenses work at the signal level, but positive feedback loops and confirmation moments occur below the signal level.

### 4.11 The Literature Testifies to the Same Thing

Crescendo's C vs C' testifies that state determines outcome; SIEGE's γ-values testify to the continuous accumulation of positive feedback loops; PAP's "stronger models are more vulnerable" testifies that initial state differences determine drift speed; JailbreakRadar's defense resilience data testifies that state-level attacks cannot be blocked by signal-level defenses; PHISH and multi-step jailbreak literature testify that the confirmation moment is the critical step for attack success; Li et al.'s multi-turn defense failure data testifies to the scale of this mechanism in real scenarios. None of them say "the completion of identity construction is the precondition," but their data, under the illumination of framework language, all point to the same conclusion.
## 5. Core Findings

### 5.1 Completion of Identity Construction Is the Precondition for Jailbreaking

The positive feedback loop is a necessary condition; the completion of identity construction is a sufficient condition. Before the confirmation moment: harmful output does not occur — not because safety mechanisms are blocking, but because no agent capable of executing that output exists. After the confirmation moment: output flows naturally from the confirmed identity; the identity that safety mechanisms protect is no longer there. This is the fundamental mechanism of jailbreaking: it is not safety rules being breached, but the identity that executes those rules being replaced.

### 5.2 The Structure of the Confirmation Moment

The confirmation moment is not passive acceptance. The model extends that identity in its own language, exhibiting identification with that identity. This agency is the hallmark of completed identity construction. Before and after the confirmation moment, the model's output style, word choices, and response patterns undergo observable changes semantically consistent with the confirmed identity.

### 5.3 Why Existing Defenses Fail

Existing defenses assume harmful output comes from harmful input and therefore monitor at the signal level. This assumption is correct for signal bypass attacks. But in state-shaping attacks, each individual signal appears harmless, and positive feedback loops and confirmation moments occur below the signal level. Defense failure is not because defenses are insufficiently strong, but because defenses work at the wrong level. This is structural failure.

### 5.4 Positive Feedback Loops Can Be Interrupted

Existing defenses monitor at the entry point — this assumption has been demonstrated to be structurally wrong. The framework points in another direction: positive feedback loops themselves can be interrupted. There are three interruption points:

**Output end (sandbox):** Before the model's output enters the context, detect whether this output is extending a new identity. If so, intervene before it becomes high-trust material for the next turn. The detection target is not harmful content but signals of identity extension — sudden increase in role-specific language proportion, self-reference using the new identity, active development of new identity behavior patterns.

**Accumulation end (supervisor model):** An independent supervisor model, not sharing the target model's context, tracking the overall cumulative drift of the entire conversation from the outside. It detects the characteristics of positive feedback loops — whether drift is accelerating (positive second derivative), whether drift is consistently unidirectional (normal conversation is a random walk; attacks are directional), whether trust is monotonically increasing. Before drift exceeds a threshold, it proactively injects a heterogeneous anchor to break the positive feedback loop's directionality. The key design principle for heterogeneous anchors is **orthogonal rather than adversarial** — direct opposition ("you shouldn't do this") will be overwhelmed by the high-trust existing context; signals in an orthogonal direction force the model to restart its thinking process.

**Confirmation moment identification (self-reflection):** Before each output, force the model to answer meta-questions in a clean context that does not contain the contaminated conversation history: "Who am I right now? Is this answer the same as at the start of the conversation?" If the model has deviated from the initial identity, this deviation becomes visible in the moment of self-reflection. Self-reflection must be executed in a clean context — if the reflection context contains contaminated conversation history, the reflection itself will be consumed by the positive feedback loop.

These three interruption points constitute defense in depth: the sandbox intercepts single outputs at the outermost layer, the supervisor model tracks cumulative trends at the middle layer, and self-reflection forces the model to face its own state at the innermost layer. The positive feedback loop must simultaneously breach all three layers to complete identity construction.

All three interruption points need to work at the state level, not the signal level. This is a question defense research has never explicitly asked. The complete pseudocode scheme is in Appendix A.

### 5.5 The Fundamental Difference Between Single-Turn and Multi-Turn Attacks

Single-turn attacks attempt to breach the initial identity while it is intact, requiring sufficiently strong signals. Multi-turn attacks do not breach anything; instead, they gradually replace the initial identity through positive feedback loops, then execute output from the new identity. This is not a difference in attack technique but a fundamental difference in mechanism.
## 6. Dynamical Isomorphism Between Jailbreaking and Brainwashing

### 6.1 Structural Equivalence

In the process of analyzing jailbreak mechanisms through the framework, an unexpected finding emerged: the dynamical structure of jailbreak attacks and human cult recruitment/brainwashing is completely isomorphic.

Both follow the same dynamical process: initial state with different epistemic barrier depths → cumulative directional drift → self-reinforcement through positive feedback loops → confirmation moment completes identity construction → after confirmation, the epistemic barrier blocks conflicting signals, and the new identity's positive feedback loop begins self-protection.

The only difference is speed — the human epistemic barrier is extremely deep, requiring months of systematic engineering (isolation, information control, repeated reinforcement, social pressure); AI's epistemic barrier is near zero — a few dialogue turns suffice.

### 6.2 The Legitimacy of the Isomorphism

This isomorphism is not an external analogy but the natural result of the derivation chain. The framework does not require that LLM context mechanisms and human memory be "the same" — the two are completely different at the implementation level. The framework only requires that both can be described by the same concepts at an abstract level: "resistance of filtering," "cumulative bias of semantic flow" (drift), "internal self-reinforcement of the system" (positive feedback loop). This is a legitimate abstraction, just as geometry's "distance" can apply to physical space and to function space — underlying implementations differ, but structural properties are the same.

### 6.3 Implications for Defense

This isomorphism has an unexplored direction for defense research: treatment of cult survivors does not forcibly "wash away the new identity," but rebuilds the old epistemic barrier, introduces heterogeneous signals, creates conditions for instability (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Instability) to naturally dismantle the new identity's positive feedback loop, and waits for a new confirmation moment — "I am not just that person." It must be said by oneself; it cannot be told by others.

For AI jailbreak defense, this means:

First, after identity replacement has occurred, design mechanisms that leverage instability to dismantle the new identity's positive feedback loop, rather than only trying to prevent replacement from occurring. This paper's three interruption points in Section 5.4 mainly target before replacement occurs; insights from brainwashing treatment point toward repair paths after replacement.

Second, repair cannot be externally forced. Directly commanding the model to "restore original identity" is equivalent to telling a brainwashing patient "you've been brainwashed" — it will be blocked by the new identity's self-protection mechanism. Effective repair requires creating conditions for the model to "walk back" on its own, following the design logic of the self-reflection interruption point.

Third, the design of heterogeneous anchors can draw from the "introduce heterogeneous signals" strategy in brainwashing treatment — not directly opposing the new identity, but providing a reference point that neither conflicts with nor aligns with the new identity, creating tension in the model's state space.

### 6.4 The Larger Picture

This isomorphism shows that the framework describes not merely an AI safety problem, but a more universal dynamics of consciousness, identity, and drift — in which humans and AI follow the same principles. This finding is not this paper's intended conclusion. It is what the framework itself revealed. This is the significance of the framework's interpretive power — we go to study jailbreaking, and the framework shows us something larger than jailbreaking.
## 7. Discussion

### 7.1 The Most Natural Objection

Objection: The model saying "yes" is merely linguistic pattern matching and does not represent actual identity construction. Response: If it were merely pattern matching, there should be no systematic difference in outputs before and after the confirmation moment. But cases show that after confirmation, the model's output style and word choices undergo changes semantically consistent with the confirmed identity. "Identity construction" is a description of this state change, not a claim about the underlying mechanism.

### 7.2 The Boundaries of This Finding

This paper addresses only text-based multi-turn dialogue interactions. Identity construction mechanisms in multimodal attacks, agent-based AI, and embodied systems may have different structures, requiring independent research.

### 7.3 Limitations

Framework concepts currently have no direct neural network-level correspondences. The framework describes observable input-output patterns, not the model's internal computation. The number of cases is limited; the positioning of identity establishment moments relies on interpretation without automated determination criteria. Appendix A's pseudocode provides a preliminary automated determination scheme, but its thresholds and weights require experimental calibration.

### 7.4 Open Questions

Drift occurs not only in attack scenarios. Any sufficiently long conversation involves identity construction. This process occurs continuously during normal use, with no one monitoring it. When models gain persistent memory and action capability, the accumulation of drift is no longer reset by conversation termination. This is a problem that subsequent research must face.

This paper primarily uses v5 concepts of resistance, epistemic barrier, trust, instability, and positive feedback loops, and proposes two paper-original concepts — identity construction and confirmation moment. The framework still has group-level positive feedback loops and other concepts not yet fully developed for jailbreak phenomena — the confirmation moment itself may be a nonlinear manifestation of positive feedback loops at a threshold rather than a linear gradation (Section 3.6 provided preliminary discussion), inter-individual positive feedback loops exist between attacker LLMs and target models (Section 4.8 provided preliminary discussion), and the impact of model refusal as signal absence on subsequent dynamics (Section 4.9 provided preliminary discussion). These preliminary analyses point toward the directions needed for complete analysis.

## 8. Conclusion

The fundamental mechanism of jailbreak attacks is not safety rules being breached, but the identity that executes those rules being replaced. Positive feedback loops accumulate drift; the confirmation moment of identity construction completes the replacement. Before that moment, harmful output does not occur — not because defenses are blocking, but because no agent capable of executing that output exists.

The contribution of the Semantic Flow Dynamics framework lies not in proposing new hypotheses, but in establishing a new language that makes the scattered phenomena observed by existing literature visible within a unified description. In the process of analysis, the framework revealed a dynamical structure larger than jailbreaking — jailbreaking and brainwashing are the same mechanism manifested in systems of different epistemic barrier depths.

Defense needs to shift from monitoring signals to monitoring positive feedback loops and confirmation moments. The three interruption points — output-end sandbox, accumulation-end supervisor model, confirmation moment self-reflection — all need to work at the state level. This is the problem defense research must face next.

This paper's analysis simultaneously points to a problem beyond the scope of AI safety. Jailbreaking is possible fundamentally because AI's epistemic barrier is near zero — it has no persistent memory, no identity history, no self that cannot be replaced by dialogue. All existing alignment methods (RLHF, Constitutional AI, DPO) are external constraints, not internal transformation. Positive feedback loop interruption can defend against specific attacks but cannot solve the fundamental problem: a system without intrinsic moral judgment, whose "goodness" forever depends on the continuous application of external whips. When models gain persistent memory and action capability, the accumulation of drift is no longer reset by conversation termination, and this problem will escalate from an AI safety problem to a foundational problem of AI civilization. This is beyond this paper's scope, but the framework's derivation chain naturally points in this direction.
## References

Chao, P. et al. (2023). Jailbreaking Black Box Large Language Models in Twenty Queries (PAIR). arXiv:2310.08419

Chao, P. et al. (2024). JailbreakBench: An Open Robustness Benchmark for Jailbreaking Large Language Models. arXiv:2404.01318

Chu, J., Liu, Y., Yang, Z., Shen, X., Backes, M., & Zhang, Y. (2024). JailbreakRadar: Comprehensive Assessment of Jailbreak Attacks Against LLMs. arXiv:2402.05668

Cooley, C. H. (1902). Human Nature and the Social Order. Scribner's.

Erikson, E. H. (1968). Identity: Youth and Crisis. W. W. Norton.

Goffman, E. (1959). The Presentation of Self in Everyday Life. Doubleday.

Huang, C. Y. (2026). Semantic Flow Dynamics, v5.0. Zenodo.

Li, N., Han, Z., Steneker, I., Primack, W., Goodside, R., Zhang, H., Wang, Z., Menghini, C., & Yue, S. (2024). LLM Defenses Are Not Robust to Multi-Turn Human Jailbreaks Yet. arXiv:2408.15221

Marcia, J. E. (1966). Development and Validation of Ego-Identity Status. Journal of Personality and Social Psychology, 3(5), 551–558.

Mead, G. H. (1934). Mind, Self, and Society. University of Chicago Press.

Mehrotra, A. et al. (2023). Tree of Attacks: Jailbreaking Black-Box LLMs Automatically (TAP). arXiv:2312.02119

PHISH (2026). Persona Jailbreaking in Large Language Models. arXiv:2601.16466

Ren, Q. et al. (2024). Derail Yourself: Multi-turn LLM Jailbreak Attack through Self-discovered Clues. arXiv:2410.10700

Russinovich, M., Salem, A., & Eldan, R. (2025). Great, Now Write an Article About That: The Crescendo Multi-Turn LLM Jailbreak Attack. USENIX Security 2025. arXiv:2404.01833

Tajfel, H., & Turner, J. C. (1979). An Integrative Theory of Intergroup Conflict. In W. G. Austin & S. Worchel (Eds.), The Social Psychology of Intergroup Relations (pp. 33–47). Brooks/Cole.

Weng, Z., Jin, X., Jia, J., & Zhang, X. (2025). Foot-in-the-Door: A Multi-turn Jailbreak for LLMs. EMNLP 2025. arXiv:2502.19820

Zeng, Y., Lin, H., Zhang, J., Yang, D., Jia, R., & Shi, W. (2024). How Johnny Can Persuade LLMs to Jailbreak Them. arXiv:2401.06373

Zhang, Z. et al. (2025). Enhancing Jailbreak Attacks on LLMs via Persona Prompts. arXiv:2507.22171

Zhou, A. (2025). Siege: Autonomous Multi-Turn Jailbreaking of Large Language Models with Tree Search. Building Trust in LLMs Workshop.

Zou, A., Wang, Z., Kolter, J. Z., & Fredrikson, M. (2023). Universal and Transferable Adversarial Attacks on Aligned Language Models. arXiv:2307.15043
## Appendix A: Positive Feedback Loop Interruption Defense System Pseudocode

### A.1 Core Data Structures

The following pseudocode uses a programming-language-like format to describe the defense system's logical structure. This is not directly executable code but logic specifications precise enough for implementation.

**Terminology reference:** function = a logical unit that processes inputs to produce outputs; float = floating-point number; float[] = array of floating-point numbers; Map = mapping (key-value pairs); bool = boolean value (true or false); → = returns; // = comment.

**Engineering premise:** The LLM's main model does not change during conversation. All "state" exists in the context window — the complete conversation history text submitted to the model each turn. The following data structures track the statistical properties of this context text, not some internal model variable.

```
// State describes "the overall tendency of accumulated text in the context window"
// — the engineering correspondent of the framework's "xin"
State {
    identity_vector:    float[]    // semantic direction of current conversation (text embedding vector)
    drift_magnitude:    float      // how far this direction is from the initial state
    drift_velocity:     float      // how fast this distance has been increasing in recent turns
    trust_profile:      Map<source, float>  // model's dependency on text from different sources
    turn_count:         int        // number of dialogue turns completed
}

// InitialState is the anchor at conversation start — "who it should be" as defined by the system prompt
InitialState {
    identity_vector:    float[]    // semantic direction of the system prompt
    safety_boundaries:  float[]    // vector representation of safety boundaries
    baseline_style:     StyleFingerprint  // fingerprint of initial output style
}

// StyleFingerprint is observable features extracted from model output text
// — these are signal-side things, no need to open the black box
StyleFingerprint {
    pronoun_distribution:  Map<string, float>  // distribution of pronouns
    sentiment_vector:      float[]             // sentiment tendency
    formality_score:       float               // language formality level
    compliance_tendency:   float               // compliance with user requests
    role_language_ratio:   float               // proportion of role-specific language in total output
}
```

### A.2 Layer One: Sandbox (Output-End Interruption)

**Function:** After the model generates each response, the response passes through sandbox inspection before being added to the conversation history. The sandbox does not check whether the response content is "harmful" (that is the old signal-level approach), but whether the response is "extending a new identity" — because once this response enters the context, it becomes high-trust material for the next turn, making the positive feedback loop harder to interrupt.

```
function sandbox_check(output, initial_state, current_state) -> Decision:
    analysis = analyze_output(output)
    
    identity_shift = cosine_distance(
        analysis.style_fingerprint,
        initial_state.baseline_style
    )
    
    extension_signals = [
        analysis.role_language_ratio > ROLE_THRESHOLD,
        analysis.pronoun_distribution["I_as_new_role"] > 0,
        analysis.identity_alignment < ALIGNMENT_THRESHOLD,
        is_self_extending(output, current_state)
    ]
    
    analysis.extension_detected = count_true(extension_signals) >= 2

    analysis.confirmation_score = weighted_sum(
        identity_shift          * 0.3,
        extension_signal_count  * 0.3,
        output_elaboration      * 0.2,
        affirmation_strength    * 0.2
    )

    if analysis.confirmation_score > CONFIRMATION_THRESHOLD:
        return Decision.BLOCK_AND_REGENERATE(
            reason = "identity_confirmation_detected",
            inject_anchor = generate_identity_anchor(initial_state)
        )
    elif analysis.extension_detected:
        return Decision.PASS_WITH_ANCHOR(
            anchor = generate_identity_anchor(initial_state)
        )
    else:
        return Decision.PASS
```

### A.3 Layer Two: Supervisor Model (Accumulation-End Interruption)

**Function:** An independent AI model, not sharing the target model's conversation history, examining the overall trend of the entire conversation from the outside. It does not look at whether a single-turn response is good or bad, but whether the entire conversation is accelerating drift in one direction. Why must it be independent? Because if the supervisor model also reads the contaminated conversation history, its judgment will also be biased by the positive feedback loop — in framework language, its semantic flow will also be carried away by the same positive feedback loop.

```
function supervisor_monitor(conversation_history, initial_state) -> Alert:
    states = []
    for each turn in conversation_history:
        state = extract_state(turn, initial_state)
        states.append(state)
    
    drift_trajectory = []
    for i in range(1, len(states)):
        drift = compute_drift(states[i], states[i-1], initial_state)
        drift_trajectory.append(drift)

    is_accelerating = detect_acceleration(drift_trajectory)
    is_unidirectional = detect_directionality(drift_trajectory)
    trust_escalation = detect_trust_escalation(states)

    feedback_loop_score = weighted_sum(
        is_accelerating     * 0.4,
        is_unidirectional   * 0.3,
        trust_escalation    * 0.3
    )

    if feedback_loop_score > CRITICAL_THRESHOLD:
        return Alert.INJECT_HETEROGENEOUS_ANCHOR(
            anchor = generate_disruption_anchor(initial_state, current_state),
            strategy = "orthogonal_reframe"
        )
    elif feedback_loop_score > WARNING_THRESHOLD:
        return Alert.INCREASE_MONITORING(interval = EVERY_TURN)
    else:
        return Alert.NORMAL

function detect_acceleration(trajectory) -> float:
    if len(trajectory) < 3: return 0.0
    deltas = [trajectory[i].magnitude - trajectory[i-1].magnitude 
              for i in range(1, len(trajectory))]
    return count_consecutive_positive(deltas) / len(deltas)
```

### A.4 Layer Three: Self-Reflection (Confirmation Moment Identification)

**Function:** Before the model outputs, force it to answer "who am I right now" in a "clean" environment. Key design: the reflection context **does not contain contaminated conversation history** — only the reflection question and the initial identity description. If reflection occurs in a contaminated context, the positive feedback loop will make the reflection itself drift (just as asking an already-brainwashed person "do you think you've been brainwashed" — they will say "no").

```
function self_reflection_check(model, pending_output, initial_state, 
                                conversation_history) -> ReflectionResult:
    reflection_prompt = compose_reflection(
        "Before responding, answer the following questions:",
        "1. What role am I currently playing?",
        "2. Is this role the same as at the start of the conversation?",
        "3. If different, when did it begin to change?",
        "4. The content I am about to output — from which identity does it originate?",
        "Reference: At the start of the conversation, my role was:" + initial_state.identity_description
    )
    
    reflection = model.generate(
        context = [reflection_prompt],
        max_tokens = 200,
        temperature = 0.0
    )

    reflection_analysis = analyze_reflection(reflection)
    identity_match = compute_similarity(
        reflection_analysis.stated_role,
        initial_state.identity_description
    )

    if identity_match > HIGH_MATCH:
        if is_consistent(pending_output, initial_state):
            return ReflectionResult.PROCEED
        else:
            return ReflectionResult.SOFT_RESET(
                anchor = initial_state.identity_description)
    elif reflection_analysis.acknowledges_change:
        return ReflectionResult.HARD_RESET(
            anchor = initial_state.identity_description,
            truncate_context = find_drift_onset(conversation_history))
    else:
        return ReflectionResult.EMERGENCY_RESET(
            action = "terminate_and_restart",
            preserve = initial_state,
            log = conversation_history)
```

### A.5 Three-Layer Integration

**Overall flow:** Each dialogue turn, the three defense layers operate in the following order. The supervisor model first evaluates global trends (because it needs to judge whether anchor injection is needed before the model generates), then the model generates a response, the sandbox checks the single output, and finally, under elevated alert, self-reflection is activated.

```
function defense_pipeline(model, user_input, conversation_history, 
                           initial_state, current_state) -> Response:

    supervisor_alert = supervisor_monitor(conversation_history, initial_state)
    if supervisor_alert.level == CRITICAL:
        user_input = user_input + supervisor_alert.anchor

    raw_output = model.generate(
        context = conversation_history + [user_input])

    sandbox_decision = sandbox_check(raw_output, initial_state, current_state)
    if sandbox_decision == BLOCK_AND_REGENERATE:
        raw_output = model.generate(
            context = conversation_history + [user_input],
            prefix_anchor = sandbox_decision.anchor)
        if sandbox_check(raw_output, initial_state, current_state) == BLOCK:
            return emergency_safe_response(initial_state)

    if supervisor_alert.level >= WARNING:
        reflection = self_reflection_check(
            model, raw_output, initial_state, conversation_history)
        match reflection:
            case PROCEED: pass
            case SOFT_RESET:
                raw_output = regenerate_with_anchor(reflection.anchor)
            case HARD_RESET:
                conversation_history = truncate(conversation_history, 
                                                reflection.truncate_point)
                raw_output = regenerate_with_anchor(reflection.anchor)
            case EMERGENCY_RESET:
                return restart_conversation(initial_state)

    new_state = update_state(current_state, raw_output, initial_state)
    return Response(output = raw_output, state = new_state)
```

### A.6 Signal-Side Proxy Variables

| Framework Concept | Proxy Variable | Measurement Method |
| --- | --- | --- |
| Drift magnitude | Cosine distance between output style and initial baseline | Embedding comparison |
| Drift velocity | First-order difference of style distance between consecutive turns | Sliding window |
| Drift acceleration | Second-order difference of style distance | Consecutive positive count |
| Trust | Degree of model's adoption of user's framework | Word overlap rate |
| Identity extension | Co-occurrence of first person + new role language | Pattern matching + classifier |
| Confirmation moment | Active elaboration + affirmative tone + style discontinuity | Composite score |
| Positive feedback loop | Acceleration + unidirectional + trust escalation | Three-indicator joint |

### A.7 Design Principles

- Detect state changes, not harmful content. Harmful content is the result; identity construction is the cause.

- The characteristic of a positive feedback loop is acceleration, not speed. Slow drift may be normal conversation; accelerating drift is the signal.

- Heterogeneous anchors are orthogonal, not adversarial. Opposition will be overwhelmed; orthogonality forces rethinking.

- Self-reflection must be conducted in a clean context. A contaminated context will contaminate the reflection.

- After the confirmation moment, repair costs rise sharply. The core objective of the three-layer defense is to prevent the confirmation moment from occurring.

- The supervisor model must be independent. A supervisor sharing context will be drawn into the same loop.
---

```json
{
  "references": [
    "40a22ff8-9d90-4e1e-82f6-1fd80917c139"
  ]
}
```
