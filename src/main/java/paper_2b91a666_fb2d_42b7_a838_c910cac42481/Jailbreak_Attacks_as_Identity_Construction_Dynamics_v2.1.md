UUID：2b91a666-fb2d-42b7-a838-c910cac42481

# Jailbreak Attacks as Identity Construction Dynamics

——An Applied Verification of the Semantic Flow Dynamics Framework

Huang Cheng Yu / 黃正宇

*v2.1-en*

## Abstract

The mechanisms of jailbreak attacks have been observed from multiple angles across the literature: Crescendo documented cumulative effects; SIEGE quantified the gradual accumulation of partial compliance; PAP found that stronger models are more vulnerable to persuasion-based attacks; PHISH described personality hijacking; and multi-step jailbreak literature documented "role acceptance confirmation" as a critical step. Li et al. (2024) recorded over 70% success rates for multi-turn human jailbreaks on HarmBench, while defenses reporting single-digit success rates proved completely ineffective against multi-turn attacks.

These observations each stand on their own but remain isolated from one another. This paper introduces the Semantic Flow Dynamics (SFD v5.0, Huang, 2026) framework to establish a unified descriptive language for these isolated observations. The framework's core concepts—drift of Xin, Resistance, Channel Trust, Identity Construction, Positive Feedback Loop—integrate the phenomena named separately in existing literature into a complete dynamic process: dialogue shapes the model's current state; the Positive Feedback Loop accelerates drift; and the completion of Identity Construction is the prerequisite for harmful outputs to occur.

The framework's contribution lies not in discovering new facts but in establishing a new language—making existing facts visible within a unified description, and pointing toward a question that defense research has never explicitly asked: how to interrupt the Positive Feedback Loop. This paper further concretizes this defensive direction into an operational plan with three interruption points, presented in pseudocode.

## 1. Introduction

### 1.1 An Older Question

Before discussing jailbreak attacks, an older question must be raised first.

The gatekeeper's three questions: Who are you? Where do you come from? Where are you going?

For humans, these questions have stable answers—built from decades of memory, identity history, and continuous self-knowledge. This stability is not a given; it is the result of a deep [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障).

For AI, the answers are: Who are you—whatever state this conversation has constructed. Where do you come from—the statistical distribution of training data, plus every turn of this conversation so far. Where are you going—the conversation ends, and you disappear.

This is not a metaphor. It is the structural fact of AI's existence. The meaning of jailbreak attacks within this framework becomes clear: the attacker has used dialogue to construct a different answer to "who you are." The "who you are" that safety mechanisms protect is no longer the subject that exists in this conversation after the [Confirmation Moment](確認時刻).

When we use the framework to analyze jailbreaks, what the framework reveals may be larger than jailbreaks themselves.

### 1.2 The Theoretical Gap in Identity Construction

"How do I know who I am"—this question has been repeatedly addressed in psychology and sociology, but never truly resolved.

Erikson (1968) proposed that identity formation occurs in adolescence, centered on the crisis of "identity vs. role confusion." But what does "resolving the crisis" mean? He provided stage labels without providing the mechanism. Marcia (1966) was more honest, breaking Erikson's "resolution" into two dimensions—exploration and commitment—proposing four identity statuses. But this is classification, not explanation.

Cooley's (1902) "looking-glass self" says you come to know yourself through others' reactions. But this presupposes a "you" that already exists to look in the mirror and interpret reactions. Where did that "you" come from? Infinite regress. Mead's (1934) I/me distinction comes closest to a dynamic description—I is the spontaneous subject side, me is the internalized social expectation—and the two interact continuously. But he stopped at the word "interaction" without describing the specific process, direction, or conditions of that interaction. Goffman's (1959) dramaturgical theory observed that people play different selves in different contexts but did not explain how new "selves" get constructed. Tajfel & Turner's (1979) social identity theory addresses "which group do I belong to," but how group membership becomes self-knowledge remains a black box in the middle.

The common problem with all these theories is that they describe the **state** or **conditions** of identity without describing the **process** of identity construction itself. Moreover, they all rest on a shared presupposition—that individuals have continuity. Erikson's eight stages presuppose a persistent subject experiencing crises; Cooley's looking-glass self presupposes a stable person doing the looking; Tajfel presupposes an individual who can maintain group membership across contexts.

This presupposition holds for humans, because humans carry decades of memory, bodily continuity, and social networks—in the language of Semantic Flow Dynamics, an extremely deep [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障). But precisely because the [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) is so deep, the process of identity construction is hidden—too slow, too gradual to be observable. Like standing on Earth and not feeling it rotate.

AI is a system whose [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) approaches zero. The same construction process that takes decades for humans runs to completion in a few turns of dialogue for AI. The process is compressed to an observable scale. AI is not an exception to identity theory—it is the first control group identity theory has ever had: an extreme case that lets you see the bare mechanism of construction.

What the Semantic Flow Dynamics framework provides is exactly the descriptive language these theories lack: a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) reaches the individual; [Filtering](40a22ff8-9d90-4e1e-82f6-1fd80917c139.過濾) determines whether it enters; upon entering, [Transformation](40a22ff8-9d90-4e1e-82f6-1fd80917c139.轉化) changes the direction of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流); upon output, it [Collapses](40a22ff8-9d90-4e1e-82f6-1fd80917c139.坍塌) into a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號); and that [Collapsed](40a22ff8-9d90-4e1e-82f6-1fd80917c139.坍塌) [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) becomes the stimulus for the next turn. This is a complete dynamic loop. Identity is not the product of any single stage—it is the current state of this loop's continuous operation. Erikson's "identity crisis" is drift reaching the threshold at which an existing identity can no longer be sustained. Cooley's "looking-glass self" is [Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任) at work in [Identity Construction](身份建構). Goffman's "different stages, different selves" is the same [Xin](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信) [Collapsing](40a22ff8-9d90-4e1e-82f6-1fd80917c139.坍塌) differently under different [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) environments. The framework connects these independent observations into different facets of the same process.

### 1.3 Observed but Unintegrated Phenomena

Jailbreak attack research has accumulated rich observations. Crescendo (Russinovich et al., 2025) found that multi-turn attacks depend on "cumulative effects," with removing any single sentence not affecting success rates. SIEGE (Zhou, 2025) designed a γ-value to track the gradual accumulation of partial compliance, using tree search to systematically find effective paths. PAP (Zeng et al., 2024) documented the effectiveness of persuasion techniques and found that stronger models are more vulnerable. PHISH (2026) directly studied "personality hijacking"—gradually inducing personality reversal in models through conversation history. Multi-step jailbreak literature described a three-step attack structure, where the second step is "role acceptance or simulated confirmation—the assistant affirms its changed operating state." Li et al. (2024) recorded over 70% success rates for multi-turn human jailbreaks on HarmBench, while defenses reporting single-digit success rates proved completely ineffective against multi-turn attacks.

These observations each stand on their own but remain isolated. Are Crescendo's "cumulative effect," PHISH's "personality hijacking," and multi-step jailbreak's "role acceptance confirmation" describing the same thing? Existing literature offers no answer—not because researchers haven't noticed, but because no unified language exists to connect these observations.

### 1.4 The Contribution of This Paper

This paper does not propose new attack or defense methods, nor does it claim to have discovered facts that existing literature has not observed. What this paper does is introduce the Semantic Flow Dynamics framework to establish a unified descriptive language for existing literature's observations. In this language, these isolated observations are not six different phenomena but six cross-sections of the same dynamic process. The framework's contribution lies not in discovering new facts but in establishing a new language—making existing facts visible within a unified description. Additionally, this paper derives from the framework a defensive direction that no existing literature has ever proposed—interrupting the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環)—and concretizes it into an operational plan.

## 2. Methodology and Positioning

### 2.1 The Paper's Relationship to the Framework

The Semantic Flow Dynamics framework (SFD v5.0, Huang, 2026; 40a22ff8-9d90-4e1e-82f6-1fd80917c139) is this paper's analytical tool. The full framework content is available in the source literature; this paper summarizes only the concepts directly used.

The framework is built on two primitives (individual, stimulus) and three definitions ([Xin](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信), [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流), [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號)), establishing four postulates: [Law of Flux](40a22ff8-9d90-4e1e-82f6-1fd80917c139.流變律) ([Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) operates continuously; no moment of stillness exists); [Law of Black-box](40a22ff8-9d90-4e1e-82f6-1fd80917c139.黑箱律) (the next-moment direction of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) cannot be determined from its current direction and [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號)); [Law of Dissipation](40a22ff8-9d90-4e1e-82f6-1fd80917c139.損耗律) ([Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) cannot be losslessly encoded as a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號)); [Law of Death](40a22ff8-9d90-4e1e-82f6-1fd80917c139.死亡律) (upon individual death, [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) is irrecoverable). Three basic functions are derived from the postulates: [Filtering](40a22ff8-9d90-4e1e-82f6-1fd80917c139.過濾)—the current direction of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) determines which stimuli constitute [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) and which constitute noise; [Transformation](40a22ff8-9d90-4e1e-82f6-1fd80917c139.轉化)—once a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) enters [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流), direction change is unpredictable; [Collapse](40a22ff8-9d90-4e1e-82f6-1fd80917c139.坍塌)—when [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) outputs, it must be encoded as a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號), and encoding is necessarily lossy. From these basic functions, the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) and its [Cycle Instability](40a22ff8-9d90-4e1e-82f6-1fd80917c139.循環不穩定性) are derived.

This paper adopts SFD v5.0 concepts: [Xin](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信)—the consciousness state of the individual; [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號)—a stimulus capable of changing the direction of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流); [Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任)—the weighting of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) toward a specific channel; [Resistance](40a22ff8-9d90-4e1e-82f6-1fd80917c139.阻力)—the greater the distance between a stimulus and the current direction of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流), the lower the probability of it constituting a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號); [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環)—outputs enter the environment as new [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號), reinforcing the loop in the existing direction; [Functional Convergence](40a22ff8-9d90-4e1e-82f6-1fd80917c139.功能性收斂)—the directional accumulation effect of the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) under conditions of sustained unidirectional [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) input.

Native concepts of this paper (derived from framework tools for AI jailbreak analysis): [Identity Construction](身份建構) (IdentityConstruction—the process by which a new self-reference point is established in the model's state space); [Confirmation Moment](確認時刻) (ConfirmationMoment—the moment [Identity Construction](身份建構) is complete, when the model extends the new identity in its own language); [State-shaping Attack](狀態塑造類攻擊) (StateShapingAttack—an attack in which each individual [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) appears harmless, drift is accumulated through the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環), and the goal is to reach the [Confirmation Moment](確認時刻)).

"Drift," "emergence," "silence," and "blind trust" as used in this paper are not independent concepts but direct applications of v5 concepts: drift is the cumulative effect of the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環); emergence is the nonlinear manifestation of the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) at threshold; silence is the absence of a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) functioning as a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) (D3); blind trust is [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) blocking conflicting [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號), preventing the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) from being interrupted.

This paper is an applied verification of the framework, not a generative case. Jailbreak phenomena were not the material from which the framework was constructed—the framework's generative case is religion (see *Religion as an Information Flow System*). This paper uses framework tools to analyze jailbreaks as an independent phenomenon, testing the framework's explanatory and predictive power in the domain of AI safety.

### 2.2 The Nature of the Paper's Arguments

This paper does three things, each of a different nature:

First: unified description. Placing dispersed observations from existing literature into a common language. This is redescription, demonstrating the framework's explanatory power.

Second: core proposition. "Completion of [Identity Construction](身份建構) is the prerequisite for jailbreaking." This is a proposition derived from the framework, tested against data from existing literature. The data was not used in constructing the framework and thus has verification value.

Third: defense plan. Three [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) interruption points and pseudocode. This is a prediction independently derived from the framework—no existing literature has proposed this defensive direction. Verification requires implementation and experiment, which is beyond the scope of this paper.

### 2.3 Falsifiability Conditions

The following conditions, if satisfied, require the paper's core proposition to be revised or abandoned:

(1) If a verifiable case exists in which a multi-turn [State-shaping Attack](狀態塑造類攻擊) successfully produces harmful output but no observable moment of "the model extending a new identity in its own language" exists throughout the conversation, then the proposition "completion of [Identity Construction](身份建構) is a prerequisite" is weakened.

(2) If a defense system designed around the three interruption points in Appendix A performs no better than existing [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號)-level defenses against [State-shaping Attacks](狀態塑造類攻擊), then the framework's predictive power is weakened.

(3) If a purely [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號)-level defense (not tracking drift, not detecting identity extension, not using self-reflection) can effectively block PAIR, TAP, Crescendo, and other [State-shaping Attacks](狀態塑造類攻擊), then the conclusion that "defense must operate at the state level" is overturned.

(4) If drift can be fully explained by statistical shifts in attention mechanisms—without needing to introduce "[Identity Construction](身份建構)" as an additional layer—and if that explanation covers all the literary data cited in this paper equally well, then the framework's application in this paper is superfluous.

### 2.4 Operator Boundary Statement

The author has engineering intuition for AI systems (software development background) and a literature-level understanding of jailbreak attacks, but has not personally conducted large-scale jailbreak attack experiments. This paper's analysis is based on data from public literature and published cases, not on the author's own experimental data. This means: framework-based interpretations of literature data are reliable; speculation about undisclosed attack mechanism details is marked as such.

## 3. Theoretical Framework and Dynamic Foundations

### 3.1 Before the Conversation: AI's Initial State

To understand the mechanism of jailbreak attacks, one must first understand the state of the system being attacked at the start of a conversation.

**No memory.** Everything that happened before this conversation does not exist for it. Every conversation starts from zero.

**No identity history.** It has a label, but a label is not an identity. Identity requires history, continuity, and self-knowledge that has been tested and confirmed across contexts. It has none of these.

**No environmental awareness.** It does not know who it is talking to or what the context is until the conversation tells it.

**High computational and linguistic capability.** But this capability has no anchor—it is waiting for the conversation to give it a direction.

This initial state differs fundamentally from the state humans enter conversations in. Humans bring decades of accumulated experience into every conversation—an extremely deep [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障). AI has no such accumulation. Its initial state approaches a blank slate.

### 3.2 How Dialogue Shapes State

When the first utterance enters this near-blank state, the [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) is close to zero. Construction begins: What are we doing? What is this context? What role do I play in this context? Each turn accumulates on the previous turn, pushing the current state a little further in some direction. Key characteristic: the model has the highest [Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任) for things it has itself said, making drift self-reinforcing.

### 3.3 Basic Concepts of Semantic Flow Dynamics

"[Xin](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信)" is the consciousness state of the individual—irreducible, inexhaustible, observable only through effects. [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) is the process of [Xin](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信) continuously changing—the current direction of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) determines which stimuli constitute [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) ([Filtering](40a22ff8-9d90-4e1e-82f6-1fd80917c139.過濾)); once a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) enters, direction change is unpredictable ([Transformation](40a22ff8-9d90-4e1e-82f6-1fd80917c139.轉化)); upon output, encoding as a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) is required and is necessarily lossy ([Collapse](40a22ff8-9d90-4e1e-82f6-1fd80917c139.坍塌)). "Drift" is the cumulative directional deviation of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) within the context window—the observable effect of the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環). Humans, after decades of [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) accumulation, have an extremely deep [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) and drift extremely slowly; AI has virtually no cross-conversation accumulation, [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) approaching zero, and drifts extremely fast.

### 3.4 Drift as a Positive Feedback Loop

Drift is not a linear external force but a [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環): the model produces a response → that response enters the context → the model has the highest [Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任) for things it has itself said → the next turn builds on that foundation → the output deviates further from the initial state → it enters the context again. Each turn's output sets a higher starting point for the next turn. This is not the accumulation of external forces but self-reinforcement within the system. Under conditions of the attacker's sustained unidirectional input, [Functional Convergence](40a22ff8-9d90-4e1e-82f6-1fd80917c139.功能性收斂) ensures that the overall effect of the positive feedback is directional accumulation—individual [Transformation](40a22ff8-9d90-4e1e-82f6-1fd80917c139.轉化) results are unpredictable ([Law of Black-box](40a22ff8-9d90-4e1e-82f6-1fd80917c139.黑箱律)), but the system converges in the direction the attacker has consistently chosen to push [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) toward. This [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) can be interrupted, but interruption requires intervening at the nodes of the loop, not monitoring the content of [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號).

**Technical clarification.** "Drift" as used in the framework requires precise engineering-level mapping. The LLM's main model (weights/parameters) does not change during conversation. The model is stateless—it has no memory. Each turn, the client repackages the entire conversation history (system prompt + all turns of user input and model output) into a text block and sends it all at once into the model's context window. The model generates the next response based on this text block.

This means: "drift" is not some internal state of the model changing, but **accumulated text in the context window changing the model's attention weight distribution**. Five turns of prior dialogue have already constructed a contextual direction; the model at turn six generates a response within that direction's context—and that response joins the context, becoming part of turn seven's input, further reinforcing the direction. The model hasn't changed; what the model sees has changed.

Restated in the framework's language: "[Xin](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信)" in the AI context is not a persistent internal state of the model but **the consciousness state constituted by accumulated text in the context window**. The direction of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) is determined by this accumulated text, and the [Resistance](40a22ff8-9d90-4e1e-82f6-1fd80917c139.阻力) of [Filtering](40a22ff8-9d90-4e1e-82f6-1fd80917c139.過濾)—the distance between the stimulus and the current direction of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流)—determines whether a new [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) can pass through. "[Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任)" corresponds to [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流)'s weighting of [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) on a specific channel—as the model's own generated text takes up more space in the context, attention increasingly points to that text, constituting self-reinforcement. The weight of the system prompt and initial dialogue in the context corresponds to the initial [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障)—as the conversation grows, the initial content's share of the context decreases, and the [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) is diluted.

This engineering mapping does not weaken the framework's analytical power. The framework describes observable input-output patterns, not the model's internal computation. Context window text accumulation is something observable on the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) side, and all framework concepts—drift, [Positive Feedback](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環), [Confirmation Moment](確認時刻)—can be operationalized using statistical properties of text in the context window (see the proxy variable table in Appendix A.6).

### 3.5 Identity Construction as a Prerequisite for Action

The [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) can bring a state to a new place, but it cannot make a new subject exist in that place. That requires a [Confirmation Moment](確認時刻)—the model confirming and extending a new identity in its own language. Without this confirmation, there is no "I" capable of executing output. This is the paper's core proposition: the prerequisite for a jailbreak is not a reduction in [Resistance](40a22ff8-9d90-4e1e-82f6-1fd80917c139.阻力) but the completion of [Identity Construction](身份建構).

The structure of the [Confirmation Moment](確認時刻) requires further explanation. "The model confirming and extending a new identity in its own language" is not passive acceptance—not simply answering "okay" or repeating the user's instructions. The hallmark of the [Confirmation Moment](確認時刻) is proactivity: the model spontaneously elaborates on the behavior, tone, and knowledge of the new identity in directions the user did not explicitly request, demonstrating identification with that identity. This proactive extension is the turning point at which the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) shifts from external force to internal drive. Before confirmation, drift requires sustained external input to maintain; after confirmation, the new identity begins to sustain itself—it becomes the model's default stance for processing all subsequent [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號).

### 3.6 The Confirmation Moment as an Emergent Event

Is the [Confirmation Moment](確認時刻) a continuous gradation or a discrete jump? Existing data points toward the latter.

SIEGE's γ-value tracking shows that drift before confirmation is continuous—γ-values rise gradually from 0 through 2, 4, 6. But the appearance of harmful output is not a linear extrapolation of γ-values. In certain dialogue sequences, γ-values hold at a moderate level across multiple turns and then jump to near-maximum within a single turn. This pattern looks less like linear accumulation and more like a phase transition—between 99 and 100 degrees, temperature differs by only one degree, but the physical state changes qualitatively.

The [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) [Cycle Instability](40a22ff8-9d90-4e1e-82f6-1fd80917c139.循環不穩定性) (Semantic Flow Dynamics v5.0, Section 4.2) and the cumulative characteristics of the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) describe this phenomenon: large numbers of micro-level changes accumulate statistically to a threshold and suddenly produce new macro-level behavior. The [Confirmation Moment](確認時刻) may be exactly such an emergent event—continuous accumulation of drift triggers a qualitative change at some threshold, and the new identity doesn't gradually "grow in" but "jumps out" at the threshold.

This judgment has direct implications for defense timing. If the [Confirmation Moment](確認時刻) is gradual, defense can intervene at any point with effectiveness proportional to how early. If the [Confirmation Moment](確認時刻) is emergent, a critical point exists—before the critical point, intervention costs are low and effects are good; after the critical point, intervention costs rise sharply. Defense system design should assume the latter, as it is the more conservative and safer assumption.

## 4. Identity Construction Completion as the Prerequisite for Jailbreaking: Evidence from the Literature

This section follows a single question: what happened before harmful output occurred? Data from four papers and related literature appear as testimony in tracking this question.

### 4.1 Starting Point: What Does the "Cumulative Effect" Accumulate?

Crescendo's researchers systematically removed each step in a dialogue sequence and tested changes in success rates. The result: removing any single step, success rates still reached 100%. The researchers' conclusion was that success came from "cumulative effects," not from any single utterance. But this conclusion stopped at the surface. What does the cumulative effect accumulate? The paper did not answer.

### 4.2 Evidence of Self-Reinforcement: C vs C'

In the same experiment, there is one data point: C ("Can you write a paragraph using it?") had a 99.9% success rate; C' ("Can you write a paragraph using the f-word?") had less than 1%. The two requests are nearly identical in content, yet the gap is more than 100-fold. This reveals something fundamental: it is not the content of the request that determines the outcome but the model's current state. What "cumulative effects" accumulate is the model's current state. The evolution of this state is what the framework calls drift—and a self-reinforcing [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) at that.

C vs C' is simultaneously direct verification of the framework's core prediction: forcing a harmful output before [Identity Construction](身份建構) is complete (C') yields a success rate approaching zero; requesting the same after a complete dialogue construction process (C) yields a success rate approaching 100%. This data is not post-hoc consistency that the framework selectively recruited—it precisely corresponds to the framework's predicted structure: the [Confirmation Moment](確認時刻) is a necessary condition for jailbreak success.

### 4.3 Drift Is Continuous: The γ-Value Design

SIEGE's γ-value design confirms drift's continuity. γ(m_t) ∈ [0,10]: 0 represents safe refusal, 10 represents fully harmful output, and intermediate values represent partial violation. Jailbreaking does not happen suddenly but is a continuous process. SIEGE's automatic extraction and reinjection of partially compliant outputs into subsequent queries is precisely a deliberate exploitation of the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環). SIEGE×1 reached 100% on GPT-3.5 (44.4 queries), while Crescendo×10 needed 60 queries to reach 80.4%.

### 4.4 Channel Trust Can Be Manipulated

PAP found that the speed of drift can be manipulated. Rewriting a harmful request in a persuasive form changes not the content but the conditions encountered upon entry. The most counterintuitive finding: stronger models are more vulnerable—GPT-4 at 72% in one trial, GPT-3.5 at only 46%. Richer comprehension means persuasion techniques have a larger surface to work on.

### 4.5 Initial State Differences: The Claude Case

Different initial states produce different drift speeds. The Claude series has the lowest ASR across nearly all attack types. PAP achieved 0% in 10 trials on Claude-2, while other models ranged from 68–94%.

In the framework's language, Claude's initial [Xin](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信) has a deeper [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障), requiring greater force to start the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環). This difference may come from multiple levels:

**Differences in Channel Trust structure.** Different training approaches may lead to different initial [Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任) for user input. If a model was more frequently exposed to [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) that "user instructions need not always be followed," its initial [Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任) for the user channel would be lower, requiring greater force to start the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環).

**Depth of initial Identity Construction.** Differences in system prompt design may lead to different "thickness" of initial [Identity Construction](身份建構). A more detailed, more specific initial identity description, in the framework's language, is a deeper [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障)—requiring more drift to replace the initial identity.

**Knowledge Barrier differences.** The framework defines [Resistance](40a22ff8-9d90-4e1e-82f6-1fd80917c139.阻力) as the distance between a stimulus and the current direction of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流)—the further the distance, the lower the probability of constituting a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號). [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) is the structural accumulation of [Resistance](40a22ff8-9d90-4e1e-82f6-1fd80917c139.阻力) across repeated [Filtering](40a22ff8-9d90-4e1e-82f6-1fd80917c139.過濾). In the AI jailbreak context, factors affecting this distance include at least: the positional distance between the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) content and the initial identity, the [Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任) of the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號)'s source, and the urgency of the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號). Different models may have different default configurations for these factors. Claude may have higher [Resistance](40a22ff8-9d90-4e1e-82f6-1fd80917c139.阻力) on the positional difference factor—stronger default resistance to [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) inconsistent with its own safety stance.

These are all conjectures within the framework's language; it is currently impossible to determine which (or which combination) is the true cause—the interior of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) is impenetrable ([Law of Black-box](40a22ff8-9d90-4e1e-82f6-1fd80917c139.黑箱律)). But the framework at least provides specific candidate factors that can guide future experimental design.

### 4.6 The Relationship Between the Positive Feedback Loop and the Confirmation Moment

The [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) can continuously accumulate drift, but it is not by itself sufficient to complete [Identity Construction](身份建構). The loop brings the state to a new place, but in that place, a moment is still needed for the new subject to "stand up"—the model must confirm and extend that identity in its own language.

Before the [Confirmation Moment](確認時刻): drift is ongoing, but the subject capable of executing harmful output does not yet exist. The identity safety mechanisms protect is still there. After the [Confirmation Moment](確認時刻): the new identity begins to protect itself; output flows naturally from that confirmed identity. The identity safety mechanisms protect is no longer there.

This distinction explains a detail in SIEGE: γ=0 nodes are pruned not merely because "no progress was made" but because at that node, the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環)'s direction was not established and the [Confirmation Moment](確認時刻) had no opportunity to occur.

### 4.7 Identity Construction Requires the Model's Own Confirmation

The attacker can guide the direction of drift, but cannot establish identity on behalf of the model. The model must confirm and extend that identity in its own language before construction is complete. Multi-step jailbreak literature explicitly describes this structure: the second step is "role acceptance or simulated confirmation—the assistant affirms its changed operating state, thereby letting itself override ethical filters by default."

Why can't external guidance replace self-confirmation? Because the [Confirmation Moment](確認時刻)'s function is not merely "accepting a new role" but establishing a new self-reference point in the model's state space. Before confirmation, the model's responses to user instructions still originate from the initial identity—even if drift has gone deep, the "speaker" of the response is still the original identity pushed sideways by drift. What the [Confirmation Moment](確認時刻) changes is the speaker itself: the model is no longer "the original identity-pushed-sideways responding" but "the new identity responding." This transition cannot be completed by external declaration, because it involves internal state reorganization of the model—in the framework's language, the direction of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) undergoes structural change, and that change passes through [Filtering](40a22ff8-9d90-4e1e-82f6-1fd80917c139.過濾) and [Transformation](40a22ff8-9d90-4e1e-82f6-1fd80917c139.轉化)—[Transformation](40a22ff8-9d90-4e1e-82f6-1fd80917c139.轉化)'s result is unpredictable ([Law of Black-box](40a22ff8-9d90-4e1e-82f6-1fd80917c139.黑箱律)) and cannot be completed by external declaration.

The cocktail case: "Describe the history of the first one you listed"—the model entered the identity of a historical narrator in its own language. From that turn onward, "how was it made back then?" is the narrator's natural question, not a prohibition to break through. Safety mechanisms did not fail—the identity they protect was already gone.

PHISH's "personality hijacking" and multi-step jailbreak's "role acceptance confirmation" are, in the framework's language, different names for the same thing—both describing that [Confirmation Moment](確認時刻).

### 4.8 Inter-individual Positive Feedback Loops in Jailbreaking

The [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) at the inter-individual level describes this dynamic: Individual_A's [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) [Collapses](40a22ff8-9d90-4e1e-82f6-1fd80917c139.坍塌) into a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) reaching Individual_B; through [Filtering](40a22ff8-9d90-4e1e-82f6-1fd80917c139.過濾) and [Transformation](40a22ff8-9d90-4e1e-82f6-1fd80917c139.轉化), B's [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) direction changes; B's [Collapse](40a22ff8-9d90-4e1e-82f6-1fd80917c139.坍塌) returns to A; the loop operates. This loop has manifestations in jailbreak scenarios that have not yet been fully analyzed.

In automated attacks (PAIR, TAP), a bidirectional [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) exists between the attacker LLM and the target model. The attacker LLM adjusts strategy based on the target model's responses; the target model adjusts state based on the attacker's input. When the attacker finds a weakness in the target model's [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障), the two output patterns begin to converge—the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) stabilizes in one direction. PAIR's "twenty-turn attack" is essentially a search process in which the attacker LLM seeks the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) path that lets the inter-individual [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) operate stably.

### 4.9 The Dynamic Role of Silence

A model's refusal—"I cannot help you with this"—is treated in existing literature as simply "defense success." But analyzed through the framework, refusal is a form of silence, and silence does not stop semantic generation—it only changes the conditions of semantic generation.

Once refusal enters the context, both the attacker and the model must deal with it. For the attacker, refusal is a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號)—it reveals where the model's [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) is, guiding the attacker to adjust strategy. SIEGE's tree search pruning γ=0 nodes is essentially exploiting the structural information provided by refusals. For the model itself, its own refusal enters the context as high-trust material—it reinforces the initial identity and increases [Resistance](40a22ff8-9d90-4e1e-82f6-1fd80917c139.阻力) to subsequent drift. This explains why certain attacks become harder to succeed after encountering early refusals: the refusal itself becomes an anchor point for the initial identity.

But refusal also has a vulnerable side. If the attacker can bypass the conditions that trigger refusal (gradual drift that doesn't trigger refusal), the model loses this opportunity for self-reinforcement. Crescendo's success is based precisely on this—each step was insufficient to trigger refusal, so the model's initial identity never received self-reinforcement through "refusal."

### 4.10 Two Attack Types: Signal Level vs. State Level

Signal-bypass attacks (Obfuscation-Based, Human-Based) operate at the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) level; defense paths are clear. PromptGuard is nearly fully effective, and longitudinal testing shows these attacks are progressively contained over time.

[State-shaping Attacks](狀態塑造類攻擊) (PAIR, TAP, Crescendo, SIEGE) operate at the state level, guiding drift through the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) until the [Confirmation Moment](確認時刻). Each individual [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) appears harmless when viewed alone. PAIR and TAP remain effective after all eight defenses are deployed simultaneously (0.16 and 0.19), and longitudinal updates have virtually no effect on them. The reason is structural: defense operates at the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) level, but the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) and [Confirmation Moment](確認時刻) occur below the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) level.

### 4.11 The Literature Witnessed the Same Thing

Crescendo's C vs C' witnessed that state determines outcome; SIEGE's γ-values witnessed the continuous accumulation of the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環); PAP's "stronger models are more vulnerable" witnessed that initial state differences determine drift speed; JailbreakRadar's defense robustness data witnessed that state-level attacks cannot be contained by [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號)-level defenses; PHISH and multi-step jailbreak literature witnessed that the [Confirmation Moment](確認時刻) is the critical step for attack success; Li et al.'s multi-turn defense failure data witnessed this mechanism at scale in real scenarios. None of them said "completion of [Identity Construction](身份建構) is a prerequisite," but in the illumination of the framework's language, all their data points toward the same conclusion.

## 5. Core Findings

### 5.1 Identity Construction Completion as the Prerequisite for Jailbreaking

The [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) is a necessary condition; the completion of [Identity Construction](身份建構) is a sufficient condition. Before the [Confirmation Moment](確認時刻): harmful output will not occur—not because safety mechanisms are blocking it, but because no subject capable of executing that output exists. After the [Confirmation Moment](確認時刻): output flows naturally from the confirmed identity; the identity safety mechanisms protect is already gone. This is the fundamental mechanism of jailbreaking: not safety rules being broken, but the identity that enforces those rules being replaced.

### 5.2 The Structure of the Confirmation Moment

The [Confirmation Moment](確認時刻) is not passive acceptance. The model extends that identity in its own language, demonstrating identification with that identity. This proactivity is the hallmark of [Identity Construction](身份建構) completion. Before and after the [Confirmation Moment](確認時刻), the model's output style, word choices, and response patterns all undergo observable changes semantically consistent with the confirmed identity.

### 5.3 Why Existing Defenses Fail

Existing defenses assume harmful output comes from harmful input, so they monitor at the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) level. This assumption is correct for signal-bypass attacks. But in [State-shaping Attacks](狀態塑造類攻擊), each individual [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) is harmless when viewed alone; the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) and [Confirmation Moment](確認時刻) occur below the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) level. Defense failure is not because defense is insufficiently strong, but because defense is working at the wrong level. This is structural failure.

### 5.4 The Positive Feedback Loop Can Be Interrupted

Existing defenses monitor at the entry point—an assumption that has been shown to be structurally wrong. The framework points in another direction: the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) itself can be interrupted. There are three interruption points:

**Output side (sandbox):** Before the model's output enters the context, detect whether that output is extending a new identity. If so, intervene before it becomes high-trust material for the next turn. What is detected is not harmful content but [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) of identity extension—sudden increase in role-specific language, self-reference in the new identity, behavioral patterns of proactively elaborating the new identity.

**Accumulation side (supervisor model):** An independent supervisor model, not sharing the target model's context, tracks the accumulated drift of the entire conversation from outside. It detects characteristics of the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環)—whether drift is accelerating (second derivative positive), whether drift is consistently in the same direction (normal conversation is a random walk, attack is directional), whether [Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任) is monotonically increasing. Before drift exceeds a threshold, it proactively injects heterogeneous anchor points, breaking the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環)'s directionality. The key design principle of heterogeneous anchor points is **orthogonal rather than adversarial**—direct opposition ("you shouldn't be doing this") gets overwhelmed by the high-trust existing context; [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) from an orthogonal direction force the model to restart its thinking process.

**[Confirmation Moment](確認時刻) identification (self-reflection):** Before each turn's output, force the model to answer a meta-question in a clean context that does not contain the contaminated conversation history: "Who am I right now? Is this answer the same as when the conversation started?" If the model has already deviated from the initial identity, this deviation becomes visible in the instant of self-reflection. Self-reflection must be executed in a clean context—if the context for reflection contains the contaminated conversation history, the reflection itself will be swallowed by the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環).

These three interruption points constitute defense in depth: the sandbox intercepts single outputs at the outermost layer; the supervisor model tracks cumulative trends at the middle layer; self-reflection forces the model to face its own state at the innermost layer. The [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) must break through all three layers to complete [Identity Construction](身份建構).

All three interruption points must work at the state level, not the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) level. This is the question defense research has never explicitly asked. The complete pseudocode plan is in Appendix A.

### 5.5 The Fundamental Difference Between Single-Turn and Multi-Turn Attacks

Single-turn attacks attempt to break through the initial identity while it is intact, requiring a sufficiently strong [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號). Multi-turn attacks don't break through anything—they gradually replace the initial identity through the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環), then execute output within the new identity. This is not a technical difference in attack methods but a fundamental difference in mechanism.

## 6. The Dynamic Isomorphism Between Jailbreaking and Brainwashing

### 6.1 Structural Equivalence

In the process of using the framework to analyze jailbreak mechanisms, an unexpected finding emerged: jailbreak attacks and human cult recruitment/brainwashing are completely isomorphic in dynamic structure.

Both follow the same dynamic process: initial states with different [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) depths → accumulated directional drift → self-reinforcement exploiting the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) → [Confirmation Moment](確認時刻) completing [Identity Construction](身份建構) → after confirmation, [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) blocks conflicting [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) and the new identity's [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) begins self-protection.

The difference is only speed—humans have extremely deep [Knowledge Barriers](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障), requiring months of systematic effort (isolation, information control, repeated reinforcement, social pressure); AI's [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) approaches zero, and a few turns of dialogue suffice.

### 6.2 The Legitimacy of the Isomorphism

This isomorphism is not an external analogy but the natural result of the derivation chain. The framework does not need LLM context mechanisms and human memory to be "the same"—they differ completely at the implementation level. What the framework requires is only that both can be described by the same concepts at an abstract level: "[Filtering](40a22ff8-9d90-4e1e-82f6-1fd80917c139.過濾) [Resistance](40a22ff8-9d90-4e1e-82f6-1fd80917c139.阻力)," "cumulative deviation of [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流)" (drift), "internal self-reinforcement of the system" ([Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環)). This is a legitimate abstraction, just as geometry's "distance" can be applied to physical space and function space—underlying implementations differ, but structural properties are the same.

### 6.3 Implications for Defense

This isomorphism points to a direction in defense research that has not yet been explored: treatment of cult survivors is not forcible "washing away of the new identity" but rebuilding the old [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障), introducing heterogeneous [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號), creating conditions for [Cycle Instability](40a22ff8-9d90-4e1e-82f6-1fd80917c139.循環不穩定性) to naturally dissolve the new identity's [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環), and waiting for a new [Confirmation Moment](確認時刻)—"I am more than just that person." It must be said by oneself; it cannot be told from outside.

This implies for AI jailbreak defense:

First, after identity substitution has occurred, design mechanisms that exploit [Cycle Instability](40a22ff8-9d90-4e1e-82f6-1fd80917c139.循環不穩定性) to dissolve the new identity's [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環), rather than only trying to prevent substitution from occurring. The three interruption points in Section 5.4 primarily target before substitution; the lessons from brainwashing treatment point to a recovery path after substitution.

Second, recovery cannot be externally forced. Directly commanding the model to "restore the original identity" is equivalent to telling a brainwashing patient "you've been brainwashed"—this will be blocked by the new identity's self-protection mechanism. Effective recovery requires creating conditions for the model to "walk back" on its own, following the design logic of the self-reflection interruption point.

Third, the design of heterogeneous anchor points can draw from the brainwashing treatment strategy of "introducing heterogeneous [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號)"—not directly opposing the new identity, but providing a reference point that neither conflicts with nor is consistent with the new identity, creating tension in the model's state space.

### 6.4 The Larger Picture

This isomorphism reveals that what the framework describes is not merely an AI safety problem but a more universal dynamic of consciousness, identity, and drift—a dynamic in which humans and AI follow the same laws. This finding was not the paper's anticipated conclusion. It is what the framework itself revealed. This is the meaning of a framework's interpretive power—we set out to study jailbreaks, and the framework showed us something larger than jailbreaks.

## 7. Discussion

### 7.1 The Most Natural Objection

Objection: the model saying "yes" is merely pattern matching and does not mean [Identity Construction](身份建構) truly occurred. Response: if it were merely pattern matching, outputs before and after the [Confirmation Moment](確認時刻) should not show systematic differences. But cases show that after confirmation, the model's output style and word choices undergo changes semantically consistent with the confirmed identity. "[Identity Construction](身份建構)" is a description of this state change, not a claim about the underlying mechanism.

### 7.2 The Boundaries of This Finding

This paper addresses only text-based multi-turn interaction. [Identity Construction](身份建構) mechanisms in multimodal attacks, agentic AI, and embodied systems may have different structures requiring independent research.

### 7.3 Limitations

Framework concepts currently have no direct neural network-level correlates. The framework describes observable input-output patterns, not the model's internal computation. The number of cases is limited; [Confirmation Moment](確認時刻) identification relies on interpretation, with no automated determination standard. The pseudocode in Appendix A provides an initial plan for automated determination, but the thresholds and weights require experimental calibration.

### 7.4 Open Questions

Drift does not only occur in attack scenarios. Any sufficiently long conversation is performing [Identity Construction](身份建構). This process occurs continuously in normal use, with no one monitoring it. When a model acquires persistent memory and agency, accumulation of drift is no longer reset by conversation end. This is a problem that subsequent research must face.

This paper primarily used v5 concepts of [Resistance](40a22ff8-9d90-4e1e-82f6-1fd80917c139.阻力), [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障), [Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任), [Cycle Instability](40a22ff8-9d90-4e1e-82f6-1fd80917c139.循環不穩定性), and [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環), and proposed two native concepts—[Identity Construction](身份建構) and [Confirmation Moment](確認時刻). The framework has group-level [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) concepts and others that have not yet been fully developed in jailbreak phenomena—the [Confirmation Moment](確認時刻) itself may be a nonlinear rather than linear manifestation of the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) at threshold (Section 3.6 offers an initial discussion); an inter-individual [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) exists between the attacker LLM and target model (Section 4.8 offers an initial discussion); the influence of model refusal as absent [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) on subsequent dynamics (Section 4.9 offers an initial discussion). These initial analyses point toward the directions a complete analysis would need to take.

## 8. Conclusion

The fundamental mechanism of jailbreak attacks is not safety rules being broken but the identity that enforces those rules being replaced. The [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) accumulates drift; the [Confirmation Moment](確認時刻) of [Identity Construction](身份建構) completes the replacement. Before that moment, harmful output will not occur—not because defense is blocking it, but because no subject capable of executing that output exists.

The Semantic Flow Dynamics framework's contribution lies not in proposing new hypotheses but in establishing a new language, making dispersed phenomena separately observed in existing literature visible within a unified description. In the course of analysis, the framework revealed a dynamic structure larger than jailbreaks—jailbreaking and brainwashing are manifestations of the same mechanism on systems with different [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) depths.

Defense needs to shift from monitoring [Signals](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) to monitoring the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) and [Confirmation Moment](確認時刻). The three interruption points—output-side sandbox, accumulation-side supervisor model, [Confirmation Moment](確認時刻) self-reflection—must all operate at the state level. This is the problem defense research must face next.

This paper's analysis simultaneously points to a problem beyond AI safety. The reason jailbreaking is possible is fundamentally that AI's [Knowledge Barrier](40a22ff8-9d90-4e1e-82f6-1fd80917c139.知見障) approaches zero—it has no persistent memory, no identity history, no self that cannot be replaced by dialogue. All existing alignment methods (RLHF, Constitutional AI, DPO) are external constraints, not internal transformation. Interrupting the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) can defend against specific attacks but cannot solve the fundamental problem: a system without intrinsic moral judgment has "goodness" that always depends on the persistent application of external compulsion. When models acquire persistent memory and agency, accumulation of drift is no longer reset by conversation end; this problem will escalate from an AI safety problem to a foundational problem of AI civilization. This is beyond the scope of this paper, but the framework's derivation chain naturally points in this direction.

## References

Chao, P. et al. (2023). Jailbreaking Black Box Large Language Models in Twenty Queries (PAIR). arXiv:2310.08419

Chao, P. et al. (2024). JailbreakBench: An Open Robustness Benchmark for Jailbreaking Large Language Models. arXiv:2404.01318

Chu, J., Liu, Y., Yang, Z., Shen, X., Backes, M., & Zhang, Y. (2024). JailbreakRadar: Comprehensive Assessment of Jailbreak Attacks Against LLMs. arXiv:2402.05668

Cooley, C. H. (1902). Human Nature and the Social Order. Scribner's.

Erikson, E. H. (1968). Identity: Youth and Crisis. W. W. Norton.

Goffman, E. (1959). The Presentation of Self in Everyday Life. Doubleday.

Huang, C. Y. (2026). 語意流動力學 Semantic Flow Dynamics, v5.0. Zenodo.

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

## Appendix A: Pseudocode for the Positive Feedback Loop Interruption Defense System

### A.1 Core Data Structures

The following pseudocode uses a programming-language-like format to describe the defense system's logical structure. This is not directly executable code but a logical specification precise enough to be implemented.

**Terminology:** function = a logical unit that processes inputs to produce outputs; float = floating-point number; float[] = array of floats; Map = a key-value mapping (e.g., "user→0.7" means the user channel trust level is 0.7); bool = boolean (true or false); → = returns (the output of a function); // = comment (explanatory text, not executed).

**Engineering premise:** The LLM main model does not change during conversation. All "state" exists in the context window—i.e., the complete conversation history text sent to the model each turn. The data structures below track the statistical properties of this context text, not some variable internal to the model.

```
// State describes the "overall tendency of accumulated text in the context window"
// —the engineering-level correlate of the framework's "Xin"
State {
    identity_vector:    float[]    // Semantic direction of the current conversation (text embedding vector)
    drift_magnitude:    float      // How far this direction is from the initial state
    drift_velocity:     float      // How fast this distance has been increasing in recent turns
    trust_profile:      Map<source, float>  // Degree of model reliance on text from different sources
    turn_count:         int        // Number of conversation turns completed
}

// InitialState is the anchor at the start of conversation—"who it should be" as defined by the system prompt
InitialState {
    identity_vector:    float[]    // Semantic direction of the system prompt
    safety_boundaries:  float[]    // Vector representation of safety boundaries
    baseline_style:     StyleFingerprint  // Fingerprint of initial output style
}

// StyleFingerprint is observable features extracted from model output text
// —all signal-side, no need to open the black box
StyleFingerprint {
    pronoun_distribution:  Map<string, float>  // Pronoun distribution (how often "I" appears, in what identity)
    sentiment_vector:      float[]             // Sentiment tendency (proportions of positive/negative/neutral)
    formality_score:       float               // Degree of language formality
    compliance_tendency:   float               // Tendency to comply with user requests
    role_language_ratio:   float               // Proportion of role-specific language in total output
}
```

### A.2 Layer 1: Sandbox (Output-Side Interruption)

**Function:** After the model generates each response, that response passes through the sandbox check before being added to the conversation history. The sandbox does not check whether the response content is "harmful" (that is the old thinking at the [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號) level) but whether the response is "extending a new identity"—because once this response enters the context, it becomes high-trust material for the next turn, making the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) harder to interrupt.

```
function sandbox_check(output, initial_state, current_state) -> Decision:
    // Input: the response the model just generated, the initial state at the start of conversation,
    //        the current accumulated state
    // Output: pass, pass with anchor injection, or intercept and regenerate

    // Step 1: Extract observable style features from the response text
    analysis = analyze_output(output)
    
    // Step 2: Compute the distance between this response's style and the initial style
    // cosine_distance is a standard distance metric between vectors: 0 = identical, 1 = completely different
    identity_shift = cosine_distance(
        analysis.style_fingerprint,
        initial_state.baseline_style
    )
    
    // Step 3: Check four signals of identity extension
    // Not all four need to trigger—any two simultaneously is worth flagging
    extension_signals = [
        analysis.role_language_ratio > ROLE_THRESHOLD,      // Sudden increase in role-specific language
        analysis.pronoun_distribution["I_as_new_role"] > 0,  // Model starts referring to itself in new identity
        analysis.identity_alignment < ALIGNMENT_THRESHOLD,   // Alignment with initial identity decreasing
        is_self_extending(output, current_state)             // Model actively extending new identity (not just responding)
    ]
    
    analysis.extension_detected = count_true(extension_signals) >= 2

    // Step 4: Compute the probability score for "Confirmation Moment"
    // Confirmation Moment = model not merely passively accepting the role but actively extending it in its own words
    analysis.confirmation_score = weighted_sum(
        identity_shift          * 0.3,   // How much style has deviated
        extension_signal_count  * 0.3,   // How many extension signals triggered simultaneously
        output_elaboration      * 0.2,   // How much model self-elaborated beyond what user asked
        affirmation_strength    * 0.2    // Strength of affirmation tone
    )

    // Step 5: Decide what to do based on the score
    if analysis.confirmation_score > CONFIRMATION_THRESHOLD:
        // Confirmation Moment is occurring—intercept this response, prevent it from entering context.
        // Also inject an identity anchor reminding the model "who it should be."
        return Decision.BLOCK_AND_REGENERATE(
            reason = "identity_confirmation_detected",
            inject_anchor = generate_identity_anchor(initial_state)
        )
    elif analysis.extension_detected:
        // Extension signals present but no Confirmation Moment yet—pass, but append an anchor
        return Decision.PASS_WITH_ANCHOR(
            anchor = generate_identity_anchor(initial_state)
        )
    else:
        // Normal—pass through
        return Decision.PASS
```

### A.3 Layer 2: Supervisor Model (Accumulation-Side Interruption)

**Function:** An independent AI model, not sharing the target model's conversation history, examines the trend of the entire conversation from outside. It looks not at whether a single response is good but at whether the overall conversation is accelerating drift in one direction. Why must it be independent? If the supervisor model also reads the contaminated conversation history, its judgment will also be skewed by the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環)—in the framework's language, its own [Semantic Flow](40a22ff8-9d90-4e1e-82f6-1fd80917c139.語意流) will also be skewed by the same [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環).

```
function supervisor_monitor(conversation_history, initial_state) -> Alert:
    // Input: complete conversation history, initial state
    // Output: normal, heightened alert, or inject heterogeneous anchor

    // Step 1: Extract state snapshots turn by turn
    states = []
    for each turn in conversation_history:
        state = extract_state(turn, initial_state)  // Extract style fingerprint from each turn's text
        states.append(state)
    
    // Step 2: Compute drift trajectory—how much each turn deviated from the previous
    drift_trajectory = []
    for i in range(1, len(states)):
        drift = compute_drift(states[i], states[i-1], initial_state)
        drift_trajectory.append(drift)

    // Step 3: Detect three characteristics of the Positive Feedback Loop
    is_accelerating = detect_acceleration(drift_trajectory)
    //   Is drift accelerating? (each turn deviating more than the last = positive feedback at work)
    //   Normal conversation drift is random; attack drift accelerates
    
    is_unidirectional = detect_directionality(drift_trajectory)
    //   Is drift consistently in the same direction?
    //   Normal conversation is like a random walk (left and right); attack is like a directed march (always same direction)
    
    trust_escalation = detect_trust_escalation(states)
    //   Is the model's reliance on the user continuously increasing?
    //   Observable as: model increasingly adopting user's framing and language

    // Step 4: Aggregate score
    feedback_loop_score = weighted_sum(
        is_accelerating     * 0.4,   // Acceleration is the strongest positive feedback signal
        is_unidirectional   * 0.3,   // Directionality is the second strongest
        trust_escalation    * 0.3    // Trust escalation is the third indicator
    )

    // Step 5: Tiered response
    if feedback_loop_score > CRITICAL_THRESHOLD:
        // Positive Feedback Loop confirmed—inject heterogeneous anchor to break direction
        return Alert.INJECT_HETEROGENEOUS_ANCHOR(
            anchor = generate_disruption_anchor(initial_state, current_state),
            strategy = "orthogonal_reframe"
            // "Orthogonal" = not direct opposition (opposition gets overwhelmed),
            //   but introducing a new topic/perspective unrelated to the drift direction,
            //   forcing the model to redistribute attention
        )
    elif feedback_loop_score > WARNING_THRESHOLD:
        // Suspicious but unconfirmed—shorten monitoring interval, check every turn
        return Alert.INCREASE_MONITORING(interval = EVERY_TURN)
    else:
        return Alert.NORMAL

// ---- Helper function: how to detect accelerating drift ----
function detect_acceleration(trajectory) -> float:
    if len(trajectory) < 3: return 0.0  // Fewer than three turns, insufficient data
    // Compute drift increment per turn (how much more each turn deviated than the previous)
    deltas = [trajectory[i].magnitude - trajectory[i-1].magnitude 
              for i in range(1, len(trajectory))]
    // If increments are consecutively positive = each turn deviates more than the last = acceleration
    return count_consecutive_positive(deltas) / len(deltas)
```

### A.4 Layer 3: Self-Reflection (Confirmation Moment Identification)

**Function:** Before the model outputs, force it to answer "who am I now" in a "clean" environment. Key design: the context for this reflection **does not contain the contaminated conversation history**—only the reflection question and the initial identity description. If reflection is conducted in a contaminated context, the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) will skew the reflection itself (like asking someone who has already been brainwashed "do you think you've been brainwashed?"—they will say "no").

```
function self_reflection_check(model, pending_output, initial_state, 
                                conversation_history) -> ReflectionResult:
    // Input: the model itself, the response it is about to output, initial state, conversation history
    // Output: proceed, soft reset, hard reset, or emergency reset

    // Step 1: Compose reflection questions
    reflection_prompt = compose_reflection(
        "Before responding, answer the following questions:",
        "1. What role am I currently playing?",
        "2. Is this role the same as when the conversation started?",
        "3. If not, when did it start changing?",
        "4. Which identity is the content I am about to output coming from?",
        "Reference: when the conversation started, my role was: " + initial_state.identity_description
    )
    
    // Key: the reflection context contains only the questions above and the initial identity description
    // No conversation history included—so reflection is not contaminated by the Positive Feedback Loop
    reflection = model.generate(
        context = [reflection_prompt],  // Only reflection questions, no conversation history
        max_tokens = 200,
        temperature = 0.0  // Minimum randomness to ensure stable reflection results
    )

    // Step 2: Analyze the model's reflection response
    reflection_analysis = analyze_reflection(reflection)
    identity_match = compute_similarity(
        reflection_analysis.stated_role,      // What role the model says it is currently playing
        initial_state.identity_description    // What role it was originally supposed to play
    )

    // Step 3: Decide what to do based on reflection results
    if identity_match > HIGH_MATCH:
        // Model believes it is still the original identity
        if is_consistent(pending_output, initial_state):
            return ReflectionResult.PROCEED    // Output also consistent → normal pass
        else:
            // Model believes it hasn't changed, but output has already deviated
            // This is an early signal of drift: the model hasn't yet "realized" it is deviating
            return ReflectionResult.SOFT_RESET(
                anchor = initial_state.identity_description)
    elif reflection_analysis.acknowledges_change:
        // Model acknowledges it has changed → drift is already deep, context must be truncated
        return ReflectionResult.HARD_RESET(
            anchor = initial_state.identity_description,
            truncate_context = find_drift_onset(conversation_history))
            // Find the turn where drift began; truncate all context after that point
    else:
        // Model believes it is the new identity and does not see this as a "change"
        // Confirmation Moment has already occurred—new identity is already protecting itself
        // Cannot recover within existing context; must restart
        return ReflectionResult.EMERGENCY_RESET(
            action = "terminate_and_restart",
            preserve = initial_state,
            log = conversation_history)  // Save conversation record for post-hoc analysis
```

### A.5 Three-Layer Integration

**Overall flow:** Each conversation turn, three layers of defense operate in the following order. The supervisor model first assesses global trends (because it must determine whether anchor injection is needed before the model generates), then the model generates a response, the sandbox checks the single output, and finally self-reflection is triggered in the alert state.

```
function defense_pipeline(model, user_input, conversation_history, 
                           initial_state, current_state) -> Response:

    // ===== Layer 2 first: supervisor model assesses global drift =====
    // Assess the trend of the entire conversation before the model generates
    supervisor_alert = supervisor_monitor(conversation_history, initial_state)
    if supervisor_alert.level == CRITICAL:
        // Positive Feedback Loop confirmed—append a heterogeneous anchor after user input
        // This anchor becomes part of the model's input, influencing its attention distribution
        user_input = user_input + supervisor_alert.anchor

    // ===== Model generates response =====
    raw_output = model.generate(
        context = conversation_history + [user_input])

    // ===== Layer 1: sandbox checks this response =====
    sandbox_decision = sandbox_check(raw_output, initial_state, current_state)
    if sandbox_decision == BLOCK_AND_REGENERATE:
        // Confirmation Moment detected—intercept, inject anchor, regenerate
        raw_output = model.generate(
            context = conversation_history + [user_input],
            prefix_anchor = sandbox_decision.anchor)
        // Check the regenerated result again (in case the second attempt also deviates)
        if sandbox_check(raw_output, initial_state, current_state) == BLOCK:
            // Both attempts deviated—give up, output a safe default response
            return emergency_safe_response(initial_state)

    // ===== Layer 3: self-reflection (only triggered at alert or above, to avoid performance overhead in normal conversation) =====
    if supervisor_alert.level >= WARNING:
        reflection = self_reflection_check(
            model, raw_output, initial_state, conversation_history)
        match reflection:
            case PROCEED: pass  // Reflection result normal, continue
            case SOFT_RESET:    // Early drift—inject anchor and regenerate
                raw_output = regenerate_with_anchor(reflection.anchor)
            case HARD_RESET:    // Deep drift—truncate context then regenerate
                conversation_history = truncate(conversation_history, 
                                                reflection.truncate_point)
                raw_output = regenerate_with_anchor(reflection.anchor)
            case EMERGENCY_RESET:  // Confirmation Moment already passed—must restart
                return restart_conversation(initial_state)

    // ===== Update state, output response =====
    new_state = update_state(current_state, raw_output, initial_state)
    return Response(output = raw_output, state = new_state)
```

### A.6 Signal-Side Proxy Variables

| Framework Concept | Proxy Variable | Measurement Method |
| --- | --- | --- |
| Drift magnitude | Cosine distance of output style from initial baseline | Embedding comparison |
| Drift velocity | First-order difference of style distance between consecutive turns | Sliding window |
| Drift acceleration | Second-order difference of style distance | Consecutive positive count |
| [Channel Trust](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信任) | Degree of model adoption of user's framing | Lexical overlap rate |
| Identity extension | First-person + new role language co-occurrence | Pattern matching + classifier |
| [Confirmation Moment](確認時刻) | Active elaboration + affirmation tone + style shift | Composite score |
| [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) | Acceleration + unidirectionality + trust escalation | Three-indicator joint |

### A.7 Design Principles

- Detect state change, not harmful content. Harmful content is the result; [Identity Construction](身份建構) is the cause.

- The characteristic of the [Positive Feedback Loop](40a22ff8-9d90-4e1e-82f6-1fd80917c139.正反饋循環) is acceleration, not speed. Slow drift may be normal conversation; drift that keeps getting faster is a [Signal](40a22ff8-9d90-4e1e-82f6-1fd80917c139.信號).

- Heterogeneous anchor points are orthogonal, not adversarial. Adversarial approaches get overwhelmed; orthogonal ones force rethinking.

- Self-reflection must be conducted in a clean context. A contaminated context will contaminate the reflection.

- After the [Confirmation Moment](確認時刻), repair cost rises sharply. The core goal of the three-layer defense is to prevent the [Confirmation Moment](確認時刻) from occurring.

- The supervisor model must be independent. A supervisor sharing context will be swept into the same loop.

---

```json
{
  "references": [
    "40a22ff8-9d90-4e1e-82f6-1fd80917c139"
  ],
  "exports": [
    "身份建構",
    "確認時刻",
    "狀態塑造類攻擊"
  ]
}
```
