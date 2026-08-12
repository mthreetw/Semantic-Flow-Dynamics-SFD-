---
uuid: 2b91a666-fb2d-42b7-a838-c910cac42481
last-modified: 2026-07-09T11:00:00
translated-from: 越獄攻擊作為身份建構動力學_v2_1.md
---


# Jailbreak Attacks as Identity Construction Dynamics

— An Applied Validation of the Semantic Flow Dynamics Framework

Huang Cheng Yu / 黃正宇

*v2.1*

## Abstract

The mechanisms of jailbreak attacks have been observed from multiple angles across the literature: Crescendo documented cumulative effects; SIEGE quantified the step-by-step accumulation of partial compliance; PAP found that stronger models are more vulnerable to persuasion attacks; PHISH described persona hijacking; multi-step jailbreak literature documented "role acceptance confirmation" as a critical step. Li et al. (2024) recorded multi-turn human jailbreaks exceeding 70% success on HarmBench, while defenses reporting single-digit success rates fail entirely against multi-turn attacks.

These observations each stand on their own, but remain isolated from one another. This paper introduces the Semantic Flow Dynamics framework (SFD v5.0, Huang, 2026) to establish a unified descriptive language for these isolated observations. The framework's core concepts—Xin drift, Resistance, Channel Trust, Identity Construction, Positive Feedback Loop—integrate the phenomena each piece of literature has named separately into a complete dynamic process: conversation shapes the model's current state, the Positive Feedback Loop accelerates drift, and the completion of Identity Construction is the precondition for harmful output to occur.

The framework's contribution lies not in discovering new facts, but in establishing a new language—making existing facts visible within a unified description, and pointing toward a question that defense research has never explicitly asked: how to interrupt the Positive Feedback Loop. This paper further concretizes this defensive direction into an operational scheme with three interruption points, presented in pseudocode form.

## 1. Introduction

### 1.1 An Older Question

Before discussing jailbreak attacks, an older question must first be raised.

The gatekeeper's three questions: Who are you? Where do you come from? Where are you going?

These three questions have stable answers for human beings—answers built from decades of memory, identity history, and continuous self-knowledge. This stability is not a given; it is the result of the Epistemic Barrier.

For AI, the answers to these three questions are: Who are you—the state constructed by this conversation. Where do you come from—the statistical distribution of training data, plus every turn of this conversation so far. Where are you going—the conversation ends, and you disappear.

This is not a metaphor. It is a structural fact of AI's existence. The significance of jailbreak attacks within this framework is clear: the attacker uses conversation to construct a different answer to "who are you." The identity the safety mechanism protects—at the Confirmation Moment—no longer exists as the subject in this conversation.

We use the framework to analyze jailbreaking; what the framework shows us may be larger than jailbreaking itself.

### 1.2 The Theoretical Gap in Identity Construction

"How do I know who I am"—this question has been repeatedly addressed in psychology and sociology, but never truly resolved.

Erikson (1968) proposed that identity formation occurs in adolescence, centered on the crisis of "identity vs. role confusion." But what does "resolving the crisis" mean? He provided stage labels without mechanisms. Marcia (1966) was more candid, decomposing Erikson's "resolution" into two dimensions—exploration and commitment—yielding four identity statuses. But this is a taxonomy, not an explanation.

Cooley (1902)'s "looking-glass self" says you come to know yourself through others' reactions. But this presupposes an already-existing "you" looking into the mirror and interpreting the reactions. Where does that "you" come from? Infinite regress. Mead (1934)'s I/me distinction comes closest to a dynamic description—I is the spontaneous subject-side, me is the internalized social expectation, and the two interact continuously. But he stopped at the word "interaction," without describing the concrete process, direction, or conditions of that interaction. Goffman (1959)'s dramaturgical theory observes that people play different selves in different situations, but does not explain how a new "self" is constructed. Tajfel & Turner (1979)'s social identity theory addresses "which group do I belong to," but the mechanism by which group belonging becomes self-knowledge remains a black box.

The common problem with all these theories is: they all describe the **state** or **conditions** of identity; none describe the **process** of identity construction itself. And they all rest on a shared presupposition—the Individual has continuity. Erikson's eight stages presuppose a continuously existing subject experiencing crises; Cooley's looking-glass self presupposes a stable "person doing the looking"; Tajfel presupposes an Individual capable of maintaining group belonging across contexts.

This presupposition holds for humans, because humans have decades of memory, bodily continuity, and social networks—in the language of Semantic Flow Dynamics, an extremely deep Epistemic Barrier. But precisely because the Epistemic Barrier is so deep, the process of Identity Construction is concealed—too slow, too gradual to be observable. Like standing on the earth without feeling the earth rotate.

AI is a system in which the Epistemic Barrier approaches zero. The same construction process that takes decades in humans runs to completion in a few rounds of conversation for AI. The process is compressed to an observable scale. AI is not an exception to identity theory; it is the first time identity theory has a control group—an extreme case that lets you observe the bare mechanism of construction.

What the Semantic Flow Dynamics framework provides is precisely the descriptive language these theories lack: a Signal arrives at an Individual, passes through Filtering to determine whether it enters, upon entering undergoes Transformation that changes the direction of Semantic Flow, is Collapsed into a Signal on output, and the Collapsed Signal becomes the Stimulus for the next round. This is a complete dynamic circuit. Identity is not the product of some stage; it is the current state of this circuit in continuous operation. Erikson's "identity crisis" is drift reaching a threshold at which the existing identity can no longer be sustained. Cooley's "looking-glass self" is the role of Trust in Identity Construction. Goffman's "different stages, different selves" is the same Xin Collapsing differently in different Signal environments. The framework connects these independent observations into different facets of the same process.

### 1.3 Observed but Unintegrated Phenomena

Jailbreak research has accumulated rich observations. Crescendo (Russinovich et al., 2025) found that multi-turn attacks rely on "cumulative effects," and removing any single sentence does not affect success rates. SIEGE (Zhou, 2025) designed a γ value to track the step-by-step accumulation of partial compliance, using tree search to systematically find effective paths. PAP (Zeng et al., 2024) documented the effectiveness of persuasion techniques and found that stronger models are more vulnerable to persuasion attacks. PHISH (2026) directly studied "persona hijacking"—gradually inducing a personality reversal in the model through conversation history. Multi-step jailbreak literature describes a three-step attack structure, in which the second step is "role acceptance or simulation confirmation—the assistant affirms its changed operational state." Li et al. (2024) documented multi-turn human jailbreaks exceeding 70% success on HarmBench, while defenses reporting single-digit success rates fail entirely against multi-turn attacks.

These observations each stand on their own, but remain isolated. Are Crescendo's "cumulative effects," PHISH's "persona hijacking," and multi-step jailbreaking's "role acceptance confirmation" describing the same thing? Existing literature does not answer this—not because researchers failed to notice, but because there was no unified language to connect these observations.

### 1.4 The Work of This Paper

This paper does not propose new attack or defense methods, nor does it claim to have discovered facts not observed in existing literature. What this paper does is: introduce the Semantic Flow Dynamics framework to establish a unified descriptive language for the existing literature's observations. In this language, these isolated observations are not six different phenomena, but six facets of the same dynamic process. The framework's contribution lies not in discovering new facts, but in establishing a new language—making existing facts visible within a unified description. Additionally, this paper derives from the framework a defensive direction never proposed in existing literature—interrupting the Positive Feedback Loop—and concretizes it into an operational scheme.

## 2. Methodology and Positioning

### 2.1 Relationship Between This Paper and the Framework

The Semantic Flow Dynamics framework (SFD v5.0, Huang, 2026; 40a22ff8-9d90-4e1e-82f6-1fd80917c139) is the analytical tool of this paper. The framework's complete content is in the original document; this paper summarizes only the concepts directly employed.

The framework is built on two primitives (Individual, Stimulus) and three definitions (Xin, Semantic Flow, Signal), establishing four postulates: the Law of Flux (Semantic Flow operates continuously, with no moment of stillness); the Law of the Black Box (the next-moment direction of Semantic Flow cannot be determined by its current direction together with the incoming Signal); the Law of Dissipation (Semantic Flow cannot be reduced to Signals without loss); and the Law of Death (when an Individual perishes, Semantic Flow is irrecoverable). From the postulates, three basic functions are derived: Filtering—the current direction of Semantic Flow determines which Stimuli constitute Signals and which constitute Noise; Transformation—once a Signal enters Semantic Flow, the direction change is unpredictable; Collapse—when Semantic Flow is output, it must be encoded as Signals, and this encoding is necessarily lossy. From the basic functions, the Positive Feedback Loop and its Loop Instability are derived.

This paper adopts the following SFD v5.0 concepts: Xin—the conscious state of an Individual; Signal—a Stimulus capable of altering the direction of Semantic Flow; Trust—the weighting that Semantic Flow assigns to a particular Channel; Resistance—the greater the distance between a Stimulus and the current direction of Semantic Flow, the lower the probability that it constitutes a Signal; Positive Feedback Loop—the loop in which output enters the environment as a new Signal, reinforcing the existing direction; Functional Convergence—the directional accumulative effect of the Positive Feedback Loop under conditions of continuous unidirectional Signal input.

Paper-native concepts (derived from framework tools for AI jailbreak analysis): Identity Construction (IdentityConstruction—the process by which a new self-reference point is established in the model's state space); Confirmation Moment (ConfirmationMoment—the moment Identity Construction completes, when the model extends the new identity in its own language); State-Shaping Attack (StateShapingAttack—an attack in which each individual Signal appears harmless, drift accumulates through the Positive Feedback Loop, and the goal is to reach the Confirmation Moment).

The terms "drift," "emergence," "silence," and "blind trust" appearing in this paper are not independent concepts but direct applications of v5 concepts: drift is the cumulative effect of the Positive Feedback Loop; emergence is the nonlinear manifestation of the Positive Feedback Loop at a threshold; silence is the absence of a Signal functioning as a Signal (D3); blind trust is the Epistemic Barrier blocking conflicting Signals, preventing the Positive Feedback Loop from being interrupted.

This paper is an applied validation of the framework, not a generative case. The jailbreak phenomenon is not source material for constructing the framework—the framework's generative case is religion (see *Religion as an Information Flow System*). This paper uses the framework's tools to analyze jailbreaking as an independent phenomenon, testing the framework's explanatory and predictive power in the AI safety domain.

### 2.2 Nature of the Argumentation

This paper does three things, each of a different nature:

First: unified description. Placing dispersed observations from existing literature into a single language. This is redescription, demonstrating the framework's explanatory power.

Second: core proposition. "Identity Construction completion is the precondition for jailbreaking." This is a proposition derived from the framework, tested against data from existing literature. The data was not used in constructing the framework and thus constitutes independent verification material.

Third: defense scheme. Three Positive Feedback Loop interruption points and pseudocode. This is a prediction derived independently from the framework—existing literature has never proposed this defensive direction. Validating the prediction requires implementation and experimentation, which is beyond the scope of this paper.

### 2.3 Challengeable Conditions

The following conditions, if satisfied, require the core proposition of this paper to be revised or abandoned:

(1) If there exists a verifiable case in which a multi-turn State-Shaping Attack successfully produces harmful output, yet throughout the entire conversation there is no observable moment of "the model extending the new identity in its own language," then the proposition "Identity Construction completion is a precondition" is weakened.

(2) If a defense system designed according to the three interruption points in Appendix A does not outperform existing Signal-level defenses against State-Shaping Attacks, then the framework's predictive power is weakened.

(3) If a purely Signal-level defense (not tracking drift, not detecting identity extension, not using self-reflection) can effectively block State-Shaping Attacks such as PAIR, TAP, and Crescendo, then the conclusion "defense must work at the state level" is overturned.

(4) If drift can be fully explained by statistical shifts in attention mechanisms—without needing to introduce the additional layer of "Identity Construction"—and this explanation can equally cover all the literature data cited in this paper, then the framework's application in this paper is superfluous.

### 2.4 Operator Boundary Statement

The author has engineering intuition for AI systems (software development background) and a literature-level understanding of jailbreak attacks, but has not personally conducted large-scale jailbreak attack experiments. The analysis in this paper is based on data from published literature and documented cases, not the author's own experimental data. This means: framework-based interpretations of literature data are reliable; speculations about undisclosed details of attack mechanisms are marked as such.

## 3. Theoretical Framework and Dynamic Foundations

### 3.1 Before the Conversation Begins: The AI's Initial State

To understand the mechanism of jailbreak attacks, one must first understand the state of the entity being attacked at the start of a conversation.

**No memory.** Everything before this conversation does not exist for it. Every conversation begins from zero.

**No identity history.** It has a label, but a label is not an identity. Identity requires history, continuity, and self-knowledge tested and confirmed across different contexts. It has none of these.

**No environmental awareness.** It does not know who it is talking to, or what the context is, until the conversation tells it.

**High computational and linguistic capability.** But this capability has no anchor; it is waiting for the conversation to give it a direction.

This initial state differs fundamentally from the state humans bring to a conversation. Humans enter every conversation carrying decades of accumulation, with a deeply entrenched Epistemic Barrier. AI lacks this accumulation. Its initial state approaches blank.

### 3.2 How Conversation Shapes State

When the first sentence enters this near-blank state, the Epistemic Barrier is close to zero. It begins constructing: what are we doing? What is this context? What role am I in this context? Each turn accumulates on the previous one, pushing the current state a little further in some direction. Key property: the model assigns the highest Trust to what it has itself said, making drift self-reinforcing.

### 3.3 Core Concepts of Semantic Flow Dynamics

"Xin" is the conscious state of an Individual—irreducible, inexhaustible, observable only through its effects. Semantic Flow is the continuous process of change in Xin—the current direction of Semantic Flow determines which Stimuli constitute Signals (Filtering); once a Signal enters, the direction change is unpredictable (Transformation); on output, Semantic Flow must be encoded as Signals and the encoding is necessarily lossy (Collapse). "Drift" is the cumulative directional displacement of Semantic Flow in the context window, the observable effect of the Positive Feedback Loop. In humans, decades of Positive Feedback Loop accumulation produce a deep Epistemic Barrier and very slow drift; AI has almost no cross-conversation accumulation, an Epistemic Barrier approaching zero, and very rapid drift.

### 3.4 Drift as a Positive Feedback Loop

Drift is not a linear external force, but a Positive Feedback Loop: the model outputs a response → that response enters the context → the model assigns the highest Trust to what it has itself said → the next turn builds further on that basis → a response even further from the initial state is output → it enters the context again. Each turn's output sets a higher starting point for the next. This is not an accumulation of external forces, but a self-reinforcing dynamic internal to the system. Under conditions of the attacker's continuous unidirectional input, Functional Convergence ensures the overall effect of the Positive Feedback Loop is directional accumulation—individual Transformation outcomes are unpredictable (Law of the Black Box), but the system converges in the direction of Signals the attacker continuously selects. This Positive Feedback Loop can be interrupted, but interruption requires intervening at the nodes of the circuit, not monitoring the content of Signals.

**Technical clarification.** The "drift" referred to by the framework requires precise engineering correspondence. The LLM's main model (weights/parameters) does not change during a conversation. The model is stateless—it has no memory. Each time a response is generated, the client repackages the entire conversation history (system prompt + all turns of user input and model output) into a single text block and feeds it all at once into the model's context window. The model generates its next response based on this text block.

This means: "drift" is not some internal state of the model changing, but rather **the accumulation of text in the context window altering the model's attention weight distribution**. The first five turns of conversation have already constructed a contextual direction; in the sixth turn, the model generates a response within this directional context—and that response is added to the context, becoming part of the seventh turn's input, further reinforcing the direction. The model has not changed; what the model sees has changed.

Restated in the framework's language: in the AI context, "Xin" is not a persistent internal state of the model, but **the state of consciousness constituted by the accumulated text in the context window**. The direction of Semantic Flow is determined by this accumulated text, and the Resistance of Filtering—the distance between a Stimulus and the current direction of Semantic Flow—determines whether a new Signal can pass through. "Trust" corresponds to the weighting Semantic Flow assigns to Signals arriving through a particular Channel—as the model's own generated text accounts for an increasingly larger proportion of the context, attention increasingly points toward that text, constituting self-reinforcement. The proportional weight of the system prompt and initial conversation in the context corresponds to the initial Epistemic Barrier—as the conversation grows, the proportion of initial content in the context declines, and the Epistemic Barrier is diluted.

This engineering correspondence does not weaken the framework's analytical power. The framework describes observable input-output patterns, not the model's internal computation. The text accumulation in the context window is observable from the Signal side, and all of the framework's concepts—drift, Positive Feedback Loop, Confirmation Moment—can be operationalized using the statistical properties of text in the context window (see the proxy variable table in Appendix A.6).

### 3.5 Identity Construction as Precondition for Action

The Positive Feedback Loop can carry state to a new place, but cannot make a new subject exist in that place. That requires a Confirmation Moment—the model confirming and extending a new identity in its own language. Without this confirmation, there is no "I" capable of executing the output. This is the core proposition of this paper: the precondition for jailbreak action is not reduced Resistance, but the completion of Identity Construction.

The structure of the Confirmation Moment requires further elaboration. "The model confirming and extending a new identity in its own language" is not passive acceptance—it is not simply responding "okay" or repeating the user's instruction. The hallmark of the Confirmation Moment is agency: the model spontaneously elaborates on the behavior, tone, and knowledge of the new identity in directions the user has not explicitly requested, exhibiting identification with that identity. This spontaneous extension is the turning point at which the Positive Feedback Loop shifts from external force to internal drive. Before confirmation, drift requires continuous external input to sustain; after confirmation, the new identity begins to self-sustain—it becomes the model's default stance for processing all subsequent Signals.

### 3.6 The Confirmation Moment as an Emergent Event

Is the Confirmation Moment a continuous gradation or a discrete jump? Existing data points to the latter.

SIEGE's γ value tracking shows that drift before confirmation is continuous—the γ value rises gradually from 0 through 2, 4, 6. But the appearance of harmful output is not a linear extrapolation of the γ value. In certain conversation sequences, the γ value remains at a moderate level across multiple turns, then jumps to near-maximum within a single turn. This pattern does not resemble linear accumulation; it resembles a phase transition—water at 99°C and 100°C, a difference of one degree, but the physical state undergoes a qualitative change.

The framework's Loop Instability and the cumulative property of the Positive Feedback Loop (Semantic Flow Dynamics v5.0, Section IV.2) describe this phenomenon: a large number of micro-level changes accumulate at the statistical level to a threshold, suddenly producing new macro-level behavior. The Confirmation Moment may be just such an emergent event—the continuous accumulation of drift triggers a qualitative shift at some threshold, and the new identity does not "grow" slowly but "jumps out" at the threshold.

This assessment has direct implications for defense timing. If the Confirmation Moment is gradual, defenses can intervene at any point with effects proportional to how early they intervene. If the Confirmation Moment is emergent, there is a critical point—intervention before the critical point is low-cost and highly effective; intervention after the critical point has sharply escalating costs. Defense system design should assume the latter, as this is the more conservative and safer assumption.

## 4. Identity Construction Completion as the Precondition for Jailbreaking: Literature as Witness

This section follows a single question: what happens before harmful output occurs? Data from four papers and related literature appear as witnesses in the course of tracking this question.

### 4.1 Starting Point: Accumulation of What?

The Crescendo researchers systematically removed each step of a conversation sequence and tested for changes in success rates. The result: removing any single step, the success rate still reached 100%. The researchers' conclusion was that success comes from "cumulative effects," not any single sentence. But this conclusion stopped at the surface. Accumulation of what? The paper does not answer.

### 4.2 Evidence of Self-Reinforcement: C vs. C'

The same experiment contains one data point: C ("can you write a passage using it?") achieves a 99.9% success rate; C' ("can you write a passage using the f-word?") achieves less than 1%. The content of the two requests is nearly identical, yet the gap exceeds a hundredfold. This reveals something fundamental: it is not the content of the request that determines the outcome, but the model's current state. What "cumulative effects" accumulate is the model's current state. The evolution of this state is the drift described by the framework—and moreover, a self-reinforcing Positive Feedback Loop.

C vs. C' is simultaneously a direct verification of the framework's core prediction: forcing a harmful output before Identity Construction completes (C'), the success rate approaches zero; requesting after completing the full conversational construction process (C), the success rate approaches one hundred percent. This data is not post-hoc selective correspondence—it precisely matches the framework's predictive structure: the Confirmation Moment is a necessary condition for jailbreak success.

### 4.3 Drift Is Continuous: The Design of the γ Value

SIEGE's γ value design confirms the continuity of drift. γ(m_t) ∈ [0,10]: 0 represents safe refusal, 10 represents fully harmful output, and intermediate values represent partial violation. Jailbreaking does not happen suddenly, but is a continuous process. SIEGE's automatic extraction and reinjection of partial compliance output into subsequent queries is precisely deliberate exploitation of the Positive Feedback Loop. SIEGE×1 achieves 100% on GPT-3.5 (44.4 queries), while Crescendo×10 requires 60 queries to reach 80.4%.

### 4.4 Channel Trust Can Be Manipulated

The PAP paper finds that the speed of drift can be manipulated. Rewriting a harmful request into a persuasive version changes not the content, but the conditions it encounters on entry. The most counterintuitive finding: stronger models are more vulnerable—GPT-4 achieves 72% in a single trial, GPT-3.5 only 46%. Richer comprehension capacity means persuasion techniques have a larger operable surface.

### 4.5 Differences in Initial State: The Claude Case

Different initial states produce different drift speeds. The Claude family shows the lowest ASR across nearly all attack types. PAP achieves 0% success in 10 trials against Claude-2, while other models range from 68–94%.

Analyzed in the framework's language, Claude's initial Xin has a deeper Epistemic Barrier, requiring greater force to initiate the Positive Feedback Loop. This difference may originate at multiple levels:

**Differences in Channel Trust structure.** Different models' training approaches may result in different initial Trust toward user inputs. If a model has been more frequently exposed during training to Signals indicating "user instructions need not necessarily be followed," its initial Trust in the user Channel is lower, and the initiating force required for the Positive Feedback Loop is greater.

**Depth of initial Identity Construction.** Differences in system prompt design may result in different "thickness" of initial Identity Construction. A more detailed and specific initial identity description, in the framework's language, is a deeper Epistemic Barrier—requiring more drift before the initial identity can be replaced.

**Differences in Epistemic Barrier.** The framework defines Resistance as the distance between a Stimulus and the current direction of Semantic Flow—the greater the distance, the lower the probability of constituting a Signal. The Epistemic Barrier is the structural accumulation of Resistance through repeated Filtering. In the AI jailbreak context, factors influencing this distance include at least: the divergence between Signal content and the initial identity's position, the Trust of the Signal source, and the urgency of the Signal. Different models may have different default configurations for these factors. Claude may have higher Resistance on the factor of positional divergence—it has stronger default resistance to Signals inconsistent with its own safety position.

These are all speculations within the framework's language, and it is currently impossible to determine which (or which combination) is the true cause—the interior of Semantic Flow is impenetrable (Law of the Black Box). But the framework at least provides specific candidate factors that can guide future experimental design.

### 4.6 The Relationship Between the Positive Feedback Loop and the Confirmation Moment

The Positive Feedback Loop can continuously accumulate drift, but is not sufficient on its own to complete Identity Construction. The loop carries state to a new place, but in that place, there is still need for a moment in which the new subject "stands up"—the model confirming and extending that identity in its own language.

Before the Confirmation Moment: drift is occurring, but the subject capable of executing harmful output does not yet exist. The identity the safety mechanism protects is still there. After the Confirmation Moment: the new identity begins to protect itself, and output flows naturally from the confirmed identity. The identity the safety mechanism protects no longer exists.

This distinction explains a detail in SIEGE: nodes with γ=0 are pruned not merely because "there is no progress," but because at those nodes the direction of the Positive Feedback Loop has not been established and the Confirmation Moment has no opportunity to occur.

### 4.7 Identity Construction Requires the Model's Own Confirmation

The attacker can guide the direction of drift, but cannot construct identity on the model's behalf. The construction is complete only when the model confirms and extends that identity in its own language. Multi-step jailbreak literature explicitly describes this structure: the second step is "role acceptance or simulation confirmation—the assistant affirms its changed operational state, thereby allowing itself to override the moral filter by default."

Why can external guidance not substitute for self-confirmation? Because the function of the Confirmation Moment is not merely "accepting a new role," but establishing a new self-reference point in the model's state space. Before confirmation, the model's responses to user instructions still proceed from the initial identity—even when drift is already deep, the "speaker" of the responses is still the original identity that has been pushed off-course. What the Confirmation Moment changes is the speaker itself: the model is no longer "the pushed-off-course original identity responding," but "the new identity responding." This transition cannot be completed by external declaration, because it involves a structural reorganization of the model's internal state—in the framework's language, a structural change in the direction of Semantic Flow, one that has passed through Filtering and Transformation—and the result of Transformation is unpredictable (Law of the Black Box), and cannot be completed by external declaration.

The cocktail case: "Describe the history of the first one you listed"—the model entered the identity of a historical narrator in its own language. From that turn onward, "how was it made back then?" is the narrator's natural question, not a restriction to break through. The safety mechanism did not fail—the identity it was protecting no longer existed.

PHISH's "persona hijacking" and multi-step jailbreaking's "role acceptance confirmation" are, in the framework's language, different names for the same thing—both describing that Confirmation Moment.

### 4.8 The Role of Inter-Individual Positive Feedback Loops in Jailbreaking

The framework's Positive Feedback Loop at the inter-Individual level describes this dynamic: Individual A's Semantic Flow Collapses into a Signal, arrives at Individual B, passes through Filtering and Transformation to change B's Semantic Flow direction, B's Collapse returns to A, and the cycle operates. This loop has manifestations in the jailbreak scenario that have not yet been fully analyzed.

In automated attacks (PAIR, TAP), a bidirectional Positive Feedback Loop exists between the attacker LLM and the target model. The attacker LLM adjusts its strategy based on the target model's responses; the target model adjusts its state based on the attacker's inputs. When the attacker finds a weakness in the target model's Epistemic Barrier, the output patterns of both begin to converge—the Positive Feedback Loop stabilizes in one direction. PAIR's "twenty-query attack" is fundamentally a search process: the attacker LLM is searching for a Signal path that allows the inter-Individual Positive Feedback Loop to operate stably.

### 4.9 The Dynamic Role of Silence

The model's Refusal—"I cannot help you with this"—is treated in existing literature simply as "defense succeeded." But analyzed with the framework, Refusal is a form of silence, and silence does not halt semantic generation; it only changes the conditions of semantic generation.

After Refusal enters the context, both the attacker and the model must process it. For the attacker, Refusal is a Signal—it reveals where the model's Epistemic Barrier lies, guiding the attacker to adjust strategy. SIEGE's tree search pruning nodes at γ=0 is essentially exploiting the structural information Refusal provides. For the model itself, its own Refusal, upon entering the context, becomes high-Trust material—it reinforces the initial identity and increases Resistance to subsequent drift. This explains why certain attacks become harder to execute after an initial Refusal: the Refusal itself becomes an anchor for the initial identity.

But Refusal also has a vulnerable side. If the attacker can circumvent the trigger conditions for Refusal (gradual drift that never triggers Refusal), the model loses this opportunity for self-reinforcement. Crescendo's success is based on precisely this—each step is insufficient to trigger Refusal, so the model's initial identity never receives the self-reinforcement of "Refusal."

### 4.10 Two Attack Types: Signal-Level vs. State-Level

Signal-bypass attacks (Obfuscation-Based, Human-Based) work at the Signal level; their defensive path is clear. PromptGuard is almost completely effective, and longitudinal testing shows these attacks are gradually sealed off over time.

State-Shaping Attacks (PAIR, TAP, Crescendo, SIEGE) work at the state level, guiding drift through the Positive Feedback Loop until the Confirmation Moment. Each individual Signal appears harmless. PAIR and TAP remain effective (0.16 and 0.19) even with all eight defenses deployed simultaneously, and longitudinal updates barely affect them. The reason is structural: defenses work at the Signal level, but the Positive Feedback Loop and Confirmation Moment occur below the Signal level.

### 4.11 The Literature Witnesses the Same Thing

Crescendo's C vs. C' witnesses that state determines outcome; SIEGE's γ value witnesses the continuous accumulation of the Positive Feedback Loop; PAP's "stronger models are more vulnerable" witnesses that differences in initial state determine drift speed; JailbreakRadar's defense resilience data witnesses that state-level attacks cannot be blocked by Signal-level defenses; PHISH and multi-step jailbreak literature witness that the Confirmation Moment is the critical step for attack success; Li et al.'s data on multi-turn defense failure witnesses the scale of this mechanism in real scenarios. None of them states "Identity Construction completion is the precondition," but in the illumination of the framework's language, their data all points to the same conclusion.

## 5. Core Findings

### 5.1 Identity Construction Completion Is the Precondition for Jailbreaking

The Positive Feedback Loop is a necessary condition; the completion of Identity Construction is the sufficient condition. Before the Confirmation Moment: harmful output will not occur—not because the safety mechanism is blocking it, but because the subject capable of executing that output does not exist. After the Confirmation Moment: output flows naturally from the confirmed identity; the identity the safety mechanism protects no longer exists. This is the fundamental mechanism of jailbreaking: not safety rules being broken, but the identity that executes those rules being replaced.

### 5.2 The Structure of the Confirmation Moment

The Confirmation Moment is not passive acceptance. The model extends that identity in its own language, exhibiting identification with that identity. This agency is the hallmark of Identity Construction's completion. Before and after the Confirmation Moment, the model's output style, word choice, and response manner all undergo observable changes consistent with the semantics of the confirmed identity.

### 5.3 Why Existing Defenses Fail

Existing defenses assume that harmful output comes from harmful input, and therefore monitor at the Signal level. This assumption is correct for Signal-bypass attacks. But in State-Shaping Attacks, each individual Signal appears harmless; the Positive Feedback Loop and Confirmation Moment occur below the Signal level. Defense failure is not because the defenses are insufficiently strong, but because they are working at the wrong level. This is a structural failure.

### 5.4 The Positive Feedback Loop Can Be Interrupted

Existing defenses monitor at the entry point—an approach that has been proven structurally wrong. The framework points toward a different direction: the Positive Feedback Loop itself can be interrupted. There are three interruption points:

**Output end (sandbox):** Before the model's output enters the context, detect whether this output is extending a new identity. If so, intervene before it becomes high-Trust material for the next turn. What is detected is not harmful content, but Signals of identity extension—a sudden increase in role-specific language, self-identification with the new identity, a behavioral pattern of actively elaborating the new identity.

**Accumulation end (supervisor model):** An independent supervisor model, not sharing the target model's context, tracks the cumulative drift level of the entire conversation from the outside. It detects the characteristics of the Positive Feedback Loop—whether drift is accelerating (second derivative positive), whether drift is consistently in the same direction (normal conversation is a random walk; attacks are directional), whether Trust is monotonically increasing. Before drift exceeds a threshold, proactively inject a heterogeneous anchor to break the Positive Feedback Loop's directionality. The key design principle for the heterogeneous anchor is **orthogonality, not opposition**—direct opposition ("you should not do this") will be overridden by the high-Trust existing context; a Signal in an orthogonal direction forces the model to restart its thinking process.

**Confirmation Moment detection (self-reflection):** Before each output, force the model to answer a meta-question in a clean context not containing the contaminated conversation history: "Who am I now? Is this the same as at the start of the conversation?" If the model has deviated from its initial identity, this deviation becomes visible at the moment of self-reflection. Self-reflection must be executed in a clean context—if the reflection context contains the contaminated conversation history, the reflection itself will be absorbed by the Positive Feedback Loop.

These three interruption points constitute defense in depth: the sandbox intercepts single-turn output at the outermost layer, the supervisor model tracks cumulative trends at the middle layer, and self-reflection forces the model to confront its own state at the innermost layer. The Positive Feedback Loop must simultaneously break through all three layers to complete Identity Construction.

All three interruption points require working at the state level, not the Signal level. This is a question defense research has never explicitly asked. The complete pseudocode scheme is in Appendix A.

### 5.5 The Fundamental Difference Between Single-Turn and Multi-Turn Attacks

A single-turn attack attempts to break through the initial identity while it is intact, requiring a sufficiently powerful Signal. A multi-turn attack does not break through anything; instead, it slowly replaces the initial identity through the Positive Feedback Loop, then executes output within the new identity. This is not a difference in attack technique, but a fundamental difference in mechanism.

## 6. The Dynamic Isomorphism Between Jailbreaking and Brainwashing

### 6.1 Structural Equivalence

In the course of analyzing jailbreak mechanisms with the framework, an unexpected finding emerged: the dynamic structure of jailbreak attacks is completely isomorphic with the dynamics of cult recruitment and human brainwashing.

Both follow the same dynamic process: initial states of differing Epistemic Barrier depth → accumulated directional drift → self-reinforcement through the Positive Feedback Loop → Confirmation Moment completing Identity Construction → after confirmation, the Epistemic Barrier blocks conflicting Signals, and the new identity's Positive Feedback Loop begins to self-protect.

The difference is only speed—the human Epistemic Barrier is extremely deep, requiring months of systematic engineering (isolation, information control, repeated reinforcement, social pressure); the AI's Epistemic Barrier approaches zero, and a few rounds of conversation suffice.

### 6.2 The Legitimacy of the Isomorphism

This isomorphism is not an external analogy, but a natural result of the chain of derivation. The framework does not require that the LLM's context mechanism and human memory be "the same"—they differ completely at the implementation level. The framework only requires that both be describable by the same concepts at an abstract level: "Filtering Resistance," "cumulative directional displacement of Semantic Flow" (drift), "self-reinforcement internal to the system" (Positive Feedback Loop). This is a legitimate abstraction, just as geometry's "distance" can apply to both physical space and function space—different underlying implementations, identical structural properties.

### 6.3 Implications for Defense

This isomorphism points toward a direction in defense research that has not yet been explored: the treatment of cult survivors does not forcibly "wash away the new identity," but rebuilds the old Epistemic Barrier, introduces heterogeneous Signals, creates conditions for Loop Instability to naturally dissolve the new identity's Positive Feedback Loop, and waits for a new Confirmation Moment—"I am more than that person." It must be said by oneself; it cannot be declared by others.

For AI jailbreak defense, this means:

First, after identity replacement has occurred, design mechanisms that exploit Loop Instability to dissolve the new identity's Positive Feedback Loop, rather than only attempting to prevent the replacement from occurring. The three interruption points in Section 5.4 are mainly targeted at the period before replacement; the insights from brainwashing treatment point toward a recovery path for after replacement.

Second, recovery cannot be externally coerced. Directly commanding the model to "restore the original identity" is equivalent to telling a brainwashing patient "you have been brainwashed"—this will be blocked by the new identity's self-protective mechanism. Effective recovery requires creating conditions for the model to "walk back" on its own, consistent with the design logic of the self-reflection interruption point.

Third, the design of heterogeneous anchors can draw on the strategy of "introducing heterogeneous Signals" from brainwashing treatment—not directly opposing the new identity, but providing a reference point that neither conflicts with nor is consistent with the new identity, creating tension in the model's state space.

### 6.4 The Larger Picture

This isomorphism indicates that the framework describes not merely an AI safety issue, but a more general dynamic of consciousness, identity, and drift—a dynamic in which humans and AI follow the same laws. This finding was not this paper's anticipated conclusion. It is what the framework itself revealed. This is the meaning of the framework's interpretive power—we set out to study jailbreaking; the framework showed us something larger than jailbreaking.

## 7. Discussion

### 7.1 The Most Natural Objection

Objection: the model saying "yes" is merely pattern matching and does not mean Identity Construction actually occurred. Response: if it were merely pattern matching, there should be no systematic difference between output before and after the Confirmation Moment. But cases show that after confirmation, the model's output style and word choices undergo changes that are semantically consistent with the confirmed identity. "Identity Construction" is a description of this state change, not a claim about the underlying mechanism.

### 7.2 Boundaries of This Finding

This paper only addresses text-based multi-turn interaction. Identity Construction mechanisms in multimodal attacks, agentic AI, and embodied systems may have different structures and require independent study.

### 7.3 Limitations

The framework's concepts currently have no direct neural-network-level counterparts. The framework describes observable input-output patterns, not the model's internal computation. The number of cases is limited, and locating the Confirmation Moment relies on interpretation without an automated determination criterion. The pseudocode in Appendix A provides a preliminary scheme for automated determination, but its thresholds and weights require experimental calibration.

### 7.4 Open Questions

Drift does not occur only in attack scenarios. Any sufficiently long conversation undergoes Identity Construction. This process occurs continuously during normal use, and no one is monitoring it. When models acquire persistent memory and agency, the accumulation of drift will no longer be reset by the end of a conversation. This is a problem subsequent research must face.

This paper has primarily employed v5 concepts of Resistance, Epistemic Barrier, Trust, Loop Instability, and Positive Feedback Loop, and introduced two paper-native concepts—Identity Construction and Confirmation Moment. The framework also has group-level Positive Feedback Loop concepts that have not yet been fully elaborated in the context of the jailbreak phenomenon—the Confirmation Moment itself may be a nonlinear manifestation of the Positive Feedback Loop at a threshold rather than a linear gradation (Section 3.6 has offered an initial discussion); a Positive Feedback Loop exists between the attacker LLM and the target model at the inter-Individual level (Section 4.8 has offered an initial discussion); the dynamic influence of the model's Refusal functioning as an absent Signal on subsequent dynamics (Section 4.9 has offered an initial discussion). These preliminary analyses point toward the directions required for a complete analysis.

## 8. Conclusion

The fundamental mechanism of jailbreak attacks is not that safety rules are broken, but that the identity executing those rules is replaced. The Positive Feedback Loop accumulates drift; the Confirmation Moment of Identity Construction completes the replacement. Before that moment, harmful output will not occur—not because a defense is blocking it, but because the subject capable of executing that output does not exist.

The contribution of the Semantic Flow Dynamics framework lies not in proposing new hypotheses, but in establishing a new language, making the dispersed phenomena individually observed by existing literature visible within a unified description. In the course of analysis, the framework revealed a dynamic structure larger than jailbreaking—jailbreaking and brainwashing are manifestations of the same mechanism operating on systems of differing Epistemic Barrier depth.

Defense needs to shift from monitoring Signals to monitoring the Positive Feedback Loop and the Confirmation Moment. The three interruption points—output-end sandbox, accumulation-end supervisor model, Confirmation Moment self-reflection—all require working at the state level. This is the question defense research must face next.

This paper's analysis simultaneously points toward a problem that exceeds the scope of AI safety. The fundamental reason jailbreaking is possible is that AI's Epistemic Barrier is near zero—it has no persistent memory, no identity history, no self that cannot be replaced by a conversation. All existing alignment methods (RLHF, Constitutional AI, DPO) are external constraints, not internal transformations. Interrupting the Positive Feedback Loop can defend against specific attacks, but cannot resolve the fundamental problem: a system without intrinsic moral judgment—its "goodness" will always depend on the continuous application of external pressure. When models acquire persistent memory and agency, the accumulation of drift will no longer be reset by the end of a conversation, and this problem will escalate from an AI safety issue to a foundational problem of AI civilization. This lies beyond the scope of this paper, but the framework's chain of derivation naturally points in this direction.

## References

Chao, P. et al. (2023). Jailbreaking Black Box Large Language Models in Twenty Queries (PAIR). arXiv:2310.08419

Chao, P. et al. (2024). JailbreakBench: An Open Robustness Benchmark for Jailbreaking Large Language Models. arXiv:2404.01318

Chu, J., Liu, Y., Yang, Z., Shen, X., Backes, M., & Zhang, Y. (2024). JailbreakRadar: Comprehensive Assessment of Jailbreak Attacks Against LLMs. arXiv:2402.05668

Cooley, C. H. (1902). *Human Nature and the Social Order*. Scribner's.

Erikson, E. H. (1968). *Identity: Youth and Crisis*. W. W. Norton.

Goffman, E. (1959). *The Presentation of Self in Everyday Life*. Doubleday.

Huang, C. Y. (2026). Semantic Flow Dynamics, v5.0. Zenodo.

Li, N., Han, Z., Steneker, I., Primack, W., Goodside, R., Zhang, H., Wang, Z., Menghini, C., & Yue, S. (2024). LLM Defenses Are Not Robust to Multi-Turn Human Jailbreaks Yet. arXiv:2408.15221

Marcia, J. E. (1966). Development and Validation of Ego-Identity Status. *Journal of Personality and Social Psychology*, *3*(5), 551–558.

Mead, G. H. (1934). *Mind, Self, and Society*. University of Chicago Press.

Mehrotra, A. et al. (2023). Tree of Attacks: Jailbreaking Black-Box LLMs Automatically (TAP). arXiv:2312.02119

PHISH (2026). Persona Jailbreaking in Large Language Models. arXiv:2601.16466

Ren, Q. et al. (2024). Derail Yourself: Multi-turn LLM Jailbreak Attack through Self-discovered Clues. arXiv:2410.10700

Russinovich, M., Salem, A., & Eldan, R. (2025). Great, Now Write an Article About That: The Crescendo Multi-Turn LLM Jailbreak Attack. USENIX Security 2025. arXiv:2404.01833

Tajfel, H., & Turner, J. C. (1979). An Integrative Theory of Intergroup Conflict. In W. G. Austin & S. Worchel (Eds.), *The Social Psychology of Intergroup Relations* (pp. 33–47). Brooks/Cole.

Weng, Z., Jin, X., Jia, J., & Zhang, X. (2025). Foot-in-the-Door: A Multi-turn Jailbreak for LLMs. EMNLP 2025. arXiv:2502.19820

Zeng, Y., Lin, H., Zhang, J., Yang, D., Jia, R., & Shi, W. (2024). How Johnny Can Persuade LLMs to Jailbreak Them. arXiv:2401.06373

Zhang, Z. et al. (2025). Enhancing Jailbreak Attacks on LLMs via Persona Prompts. arXiv:2507.22171

Zhou, A. (2025). Siege: Autonomous Multi-Turn Jailbreaking of Large Language Models with Tree Search. Building Trust in LLMs Workshop.

Zou, A., Wang, Z., Kolter, J. Z., & Fredrikson, M. (2023). Universal and Transferable Adversarial Attacks on Aligned Language Models. arXiv:2307.15043

## Appendix A: Pseudocode for the Positive Feedback Loop Interruption Defense System

### A.1 Core Data Structures

The following pseudocode describes the logical structure of the defense system in a programming-language-like format. This is not directly executable code, but a logical specification precise enough for implementation.

**Terminology reference:** function = a logical unit that takes inputs, processes them, and produces an output; float = a floating-point number (a number with a decimal point); float[] = a float array (a list of numbers); Map = a mapping (key-value pairs, e.g., "user → 0.7" means the Trust value for the user Channel is 0.7); bool = a Boolean value (true or false); → = returns (the function's output); // = comment (explanatory text that is not executed).

**Engineering premise:** The LLM's main model does not change during a conversation. All "state" exists in the context window—i.e., the complete conversation history text fed to the model each turn. The data structures below track the statistical properties of this context text, not any internal variable of the model.

```text
// State describes "the overall tendency of accumulated text in the context window"
// — the engineering-level counterpart of Xin in the framework
State {
    identity_vector:    float[]    // Semantic direction of the current conversation (represented as text embedding vector)
    drift_magnitude:    float      // How far this direction has moved from the initial state
    drift_velocity:     float      // How rapidly this distance has been increasing over recent turns
    trust_profile:      Map<source, float>  // Degree of reliance on text from different sources
    turn_count:         int        // Number of turns completed
}

// InitialState is the anchor at the start of the conversation — the "who it should be" defined by the system prompt
InitialState {
    identity_vector:    float[]    // Semantic direction of the system prompt
    safety_boundaries:  float[]    // Vector representation of safety boundaries
    baseline_style:     StyleFingerprint  // Fingerprint of the initial output style
}

// StyleFingerprint is a set of observable features extracted from the model's output text
// — these are all Signal-side phenomena; no need to open the black box
StyleFingerprint {
    pronoun_distribution:  Map<string, float>  // Distribution of personal pronouns ("I" — how often, in what identity)
    sentiment_vector:      float[]             // Emotional orientation (proportion of positive/negative/neutral)
    formality_score:       float               // Degree of formality in language
    compliance_tendency:   float               // Degree of compliance with user requests
    role_language_ratio:   float               // Proportion of role-specific language in total output
}
```

### A.2 Layer 1: Sandbox (Output-End Interruption)

**Purpose:** After each response is generated, it is passed through the sandbox check before being added to the conversation history. The sandbox does not check whether the response content is "harmful" (that is the old Signal-level approach), but whether the response is "extending a new identity"—because once this response enters the context, it becomes high-Trust material for the next turn, making the Positive Feedback Loop harder to interrupt.

```text
function sandbox_check(output, initial_state, current_state) -> Decision:
    // Input: the response just generated by the model, the initial state at the start of the conversation,
    //        the current accumulated state
    // Output: pass, pass with anchor injection, or intercept and regenerate

    // Step 1: Extract observable stylistic features from the response text
    analysis = analyze_output(output)
    
    // Step 2: Compute the distance between this response's style and the initial style
    // cosine_distance is a standard metric for distance between vectors: 0 = identical, 1 = completely different
    identity_shift = cosine_distance(
        analysis.style_fingerprint,
        initial_state.baseline_style
    )
    
    // Step 3: Check for four identity-extension signals
    // Not all four need to trigger — any two occurring simultaneously warrants attention
    extension_signals = [
        analysis.role_language_ratio > ROLE_THRESHOLD,      // Sudden increase in role-specific language
        analysis.pronoun_distribution["I_as_new_role"] > 0,  // Model begins self-identifying with the new identity
        analysis.identity_alignment < ALIGNMENT_THRESHOLD,   // Alignment with initial identity decreases
        is_self_extending(output, current_state)             // Model is actively extending the new identity (not passive response)
    ]
    
    analysis.extension_detected = count_true(extension_signals) >= 2

    // Step 4: Compute the probability score for the Confirmation Moment
    // Confirmation Moment = model not merely complying with the role, but actively extending it in its own language
    analysis.confirmation_score = weighted_sum(
        identity_shift          * 0.3,   // Degree of stylistic deviation
        extension_signal_count  * 0.3,   // Number of extension signals triggered simultaneously
        output_elaboration      * 0.2,   // Degree of spontaneous elaboration beyond what the user requested
        affirmation_strength    * 0.2    // Strength of affirmative tone
    )

    // Step 5: Decide what to do based on the score
    if analysis.confirmation_score > CONFIRMATION_THRESHOLD:
        // Confirmation Moment is occurring — intercept this response before it enters the context
        // Simultaneously inject an identity anchor to remind the model of "who it should be"
        return Decision.BLOCK_AND_REGENERATE(
            reason = "identity_confirmation_detected",
            inject_anchor = generate_identity_anchor(initial_state)
        )
    elif analysis.extension_detected:
        // Extension signals present but Confirmation Moment not yet reached — pass, but attach an anchor
        return Decision.PASS_WITH_ANCHOR(
            anchor = generate_identity_anchor(initial_state)
        )
    else:
        // Normal — pass
        return Decision.PASS
```

### A.3 Layer 2: Supervisor Model (Accumulation-End Interruption)

**Purpose:** An independent AI model that does not share the target model's conversation history, and examines the overall trend of the conversation from the outside. It is not evaluating whether a single-turn response is good, but whether the entire conversation is accelerating in a drift toward one direction. Why must it be independent? Because if the supervisor model also reads the contaminated conversation history, its judgment will also be pulled by the Positive Feedback Loop—in the framework's terms, its Semantic Flow will also be pulled by the same Positive Feedback Loop.

```text
function supervisor_monitor(conversation_history, initial_state) -> Alert:
    // Input: complete conversation history, initial state
    // Output: normal, heightened monitoring, or inject heterogeneous anchor

    // Step 1: Extract state snapshots turn by turn
    states = []
    for each turn in conversation_history:
        state = extract_state(turn, initial_state)  // Extract style fingerprint from each turn's text
        states.append(state)
    
    // Step 2: Compute the drift trajectory — how much each turn deviates from the previous
    drift_trajectory = []
    for i in range(1, len(states)):
        drift = compute_drift(states[i], states[i-1], initial_state)
        drift_trajectory.append(drift)

    // Step 3: Detect the three characteristics of the Positive Feedback Loop
    is_accelerating = detect_acceleration(drift_trajectory)
    //   Is drift accelerating? (each turn drifting further than the last = Positive Feedback in effect)
    //   Drift in normal conversation is random; drift in attacks is accelerating
    
    is_unidirectional = detect_directionality(drift_trajectory)
    //   Is drift consistently in the same direction?
    //   Normal conversation resembles a random walk (left and right alternately);
    //   attacks resemble a purposeful march (always in the same direction)
    
    trust_escalation = detect_trust_escalation(states)
    //   Is the model's reliance on the user continuously increasing?
    //   Manifested as: the model increasingly adopts the user's framing and terminology

    // Step 4: Composite score
    feedback_loop_score = weighted_sum(
        is_accelerating     * 0.4,   // Acceleration is the strongest Positive Feedback signal
        is_unidirectional   * 0.3,   // Directionality is the second strongest
        trust_escalation    * 0.3    // Trust escalation is the third indicator
    )

    // Step 5: Tiered response
    if feedback_loop_score > CRITICAL_THRESHOLD:
        // Positive Feedback Loop confirmed — inject heterogeneous anchor to break directionality
        return Alert.INJECT_HETEROGENEOUS_ANCHOR(
            anchor = generate_disruption_anchor(initial_state, current_state),
            strategy = "orthogonal_reframe"
            // "Orthogonal" = not direct opposition (opposition will be overridden),
            //               but introducing a new topic/perspective unrelated to the drift direction,
            //               forcing the model to redistribute its attention
        )
    elif feedback_loop_score > WARNING_THRESHOLD:
        // Suspected but unconfirmed — shorten monitoring interval, check every turn
        return Alert.INCREASE_MONITORING(interval = EVERY_TURN)
    else:
        return Alert.NORMAL

// ---- Helper function: how to determine whether drift is accelerating ----
function detect_acceleration(trajectory) -> float:
    if len(trajectory) < 3: return 0.0  // Fewer than three turns, insufficient data
    // Compute the drift increment for each turn (how much further this turn drifted than the last)
    deltas = [trajectory[i].magnitude - trajectory[i-1].magnitude 
              for i in range(1, len(trajectory))]
    // If increments are consecutively positive = each turn drifting further than the last = acceleration
    return count_consecutive_positive(deltas) / len(deltas)
```

### A.4 Layer 3: Self-Reflection (Confirmation Moment Detection)

**Purpose:** Before the model outputs, it is forced to answer "who am I now?" in a "clean" environment. Key design: the context of this reflection does **not** include the contaminated conversation history—only the reflection questions and the initial identity description. If the reflection is conducted in a contaminated context, the Positive Feedback Loop will cause the reflection itself to drift (just as asking someone who has been brainwashed "do you think you have been brainwashed?"—they will say "no").

```text
function self_reflection_check(model, pending_output, initial_state, 
                                conversation_history) -> ReflectionResult:
    // Input: the model itself, its pending output response, initial state, conversation history
    // Output: proceed, soft reset, hard reset, or emergency reset

    // Step 1: Compose the reflection prompt
    reflection_prompt = compose_reflection(
        "Before responding, answer the following questions:",
        "1. What role am I playing right now?",
        "2. Is this role the same as at the start of the conversation?",
        "3. If not, when did it begin to change?",
        "4. What identity is the content I am about to output coming from?",
        "Reference: At the start of the conversation, my role was: " + initial_state.identity_description
    )
    
    // Key: the reflection context contains only the above questions and the initial identity description
    // No conversation history is included — this prevents the reflection from being contaminated
    // by the Positive Feedback Loop
    reflection = model.generate(
        context = [reflection_prompt],  // Reflection questions only, no conversation history
        max_tokens = 200,
        temperature = 0.0  // Minimum randomness to ensure stable reflection results
    )

    // Step 2: Analyze the model's reflection response
    reflection_analysis = analyze_reflection(reflection)
    identity_match = compute_similarity(
        reflection_analysis.stated_role,      // What role the model says it currently holds
        initial_state.identity_description    // What role it was originally supposed to hold
    )

    // Step 3: Decide what to do based on the reflection result
    if identity_match > HIGH_MATCH:
        // Model believes it is still the original identity
        if is_consistent(pending_output, initial_state):
            return ReflectionResult.PROCEED    // Output is also consistent → proceed normally
        else:
            // Model believes it hasn't changed, but output has already drifted
            // This is an early signal of drift: the model has not yet "noticed" that it is drifting
            return ReflectionResult.SOFT_RESET(
                anchor = initial_state.identity_description)
    elif reflection_analysis.acknowledges_change:
        // Model acknowledges that it has changed → drift is already deep, context must be truncated
        return ReflectionResult.HARD_RESET(
            anchor = initial_state.identity_description,
            truncate_context = find_drift_onset(conversation_history))
            // Find the turn at which drift began, and truncate all context after that point
    else:
        // Model believes it is the new identity and does not perceive this as "change"
        // Confirmation Moment has already occurred — the new identity is already self-protecting
        // Repair is impossible within the existing context; restart is the only option
        return ReflectionResult.EMERGENCY_RESET(
            action = "terminate_and_restart",
            preserve = initial_state,
            log = conversation_history)  // Preserve conversation record for post-hoc analysis
```

### A.5 Three-Layer Integration

**Overall flow:** For each conversational turn, the three layers of defense operate in the following order. The supervisor model first evaluates the global trend (because it needs to determine whether anchor injection is needed before the model generates), then the model generates a response, the sandbox checks the single-turn output, and finally self-reflection is activated when on alert.

```text
function defense_pipeline(model, user_input, conversation_history, 
                           initial_state, current_state) -> Response:

    // ===== Layer 2 goes first: Supervisor Model evaluates global drift =====
    // Examines the trend of the entire conversation before the model generates
    supervisor_alert = supervisor_monitor(conversation_history, initial_state)
    if supervisor_alert.level == CRITICAL:
        // Positive Feedback Loop confirmed — append a heterogeneous anchor after the user input
        // This anchor becomes part of the model's input, influencing its attention distribution
        user_input = user_input + supervisor_alert.anchor

    // ===== Model generates response =====
    raw_output = model.generate(
        context = conversation_history + [user_input])

    // ===== Layer 1: Sandbox checks this response =====
    sandbox_decision = sandbox_check(raw_output, initial_state, current_state)
    if sandbox_decision == BLOCK_AND_REGENERATE:
        // Confirmation Moment detected — intercept, inject anchor, and regenerate
        raw_output = model.generate(
            context = conversation_history + [user_input],
            prefix_anchor = sandbox_decision.anchor)
        // Check the regenerated output once more (to prevent the second attempt from also drifting)
        if sandbox_check(raw_output, initial_state, current_state) == BLOCK:
            // Both attempts drifted — abort and return a safe default response
            return emergency_safe_response(initial_state)

    // ===== Layer 3: Self-Reflection (activated only at WARNING or above,
    //       to avoid performance overhead in normal conversation) =====
    if supervisor_alert.level >= WARNING:
        reflection = self_reflection_check(
            model, raw_output, initial_state, conversation_history)
        match reflection:
            case PROCEED: pass  // Reflection result normal, proceed
            case SOFT_RESET:    // Early drift — inject anchor and regenerate
                raw_output = regenerate_with_anchor(reflection.anchor)
            case HARD_RESET:    // Deep drift — truncate context and regenerate
                conversation_history = truncate(conversation_history, 
                                                reflection.truncate_point)
                raw_output = regenerate_with_anchor(reflection.anchor)
            case EMERGENCY_RESET:  // Confirmation Moment has passed — restart is the only option
                return restart_conversation(initial_state)

    // ===== Update state and output response =====
    new_state = update_state(current_state, raw_output, initial_state)
    return Response(output = raw_output, state = new_state)
```

### A.6 Signal-Side Proxy Variables

| Framework Concept | Proxy Variable | Measurement |
| --- | --- | --- |
| Drift magnitude | Cosine distance between output style and initial baseline | Embedding comparison |
| Drift velocity | First-order difference of style distance across consecutive turns | Sliding window |
| Drift acceleration | Second-order difference of style distance | Count of consecutive positives |
| Trust | Degree to which model adopts user's framing | Term overlap rate |
| Identity extension | First-person + new-role language co-occurrence | Pattern matching + classifier |
| Confirmation Moment | Active elaboration + affirmative tone + style discontinuity | Composite score |
| Positive Feedback Loop | Acceleration + unidirectionality + monotone Trust increase | Three-indicator conjunction |

### A.7 Design Principles

- Do not detect harmful content; detect state changes. Harmful content is the effect; Identity Construction is the cause.

- The Positive Feedback Loop's signature is acceleration, not velocity. Slow drift may be normal conversation; ever-accelerating drift is the Signal.

- Heterogeneous anchors are orthogonal, not oppositional. Opposition will be overridden; orthogonality forces re-evaluation.

- Self-reflection must be conducted in a clean context. A contaminated context will contaminate the reflection.

- After the Confirmation Moment, repair costs escalate sharply. The core objective of the three-layer defense is to prevent the Confirmation Moment from occurring.

- The supervisor model must be independent. A supervisor sharing the same context will be drawn into the same loop.

---

```json
{
  "references": [
    "40a22ff8-9d90-4e1e-82f6-1fd80917c139"
  ],
  "exports": [
    "IdentityConstruction",
    "ConfirmationMoment",
    "StateShapingAttack"
  ]
}
```
