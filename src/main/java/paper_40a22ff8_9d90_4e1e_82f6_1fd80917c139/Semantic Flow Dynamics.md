# Semantic Flow Dynamics v5.0

*語意流動力學*

Cheng Yu Huang / 黃正宇

Contact: mthree.tw@gmail.com / ORCID: 0009-0003-9884-7401

2026

---

## Abstract

This paper presents the Semantic Flow Dynamics (SFD) framework v5.0, addressing what Shannon's information theory does not cover: what happens after a signal reaches an individual. The framework is a physics-style analysis of consciousness models — its methodology comes from physics (postulates, formal derivation, modeling, prediction, empirical comparison), formalized but not quantified.

Starting from two primitives (Individual, Stimulus) and three definitions (Xin, Semantic Flow, Signal), the framework establishes four postulates. The core postulate, the Law of Black-box, asserts: the next-moment direction of Semantic Flow cannot be determined by the current direction and the Signal — this is the boundary between the framework and all signal-level theories. From the postulates, three basic functions (Filtering, Transformation, Collapse) and their properties are derived, and from the basic functions, the Positive Feedback Loop is derived. The above constitutes the framework's formal layer — every step is a logical derivation, traceable, formalizable.

Beyond the formal layer, the framework provides experiential-layer tools: driving force classification and modeling methods. The framework does not classify — the Law of Flux presupposes a time axis, and nothing stationary can be labeled. Recurring patterns that emerge from modeling are patterns — patterns are products of modeling, not premises of modeling. The framework improves cross-domain applicability by lowering computational difficulty, and the same toolset has already produced verifiable results in religious studies, philosophy of history, and AI safety engineering.

**Keywords:** Semantic Flow Dynamics, Xin, Semantic Flow, Filtering, Transformation, Collapse, Epistemic Obstacle, Positive Feedback Loop, Modeling, Postulate System

---

# Part One: The Formal Layer

The formal layer is the framework's skeleton. Every step is a logical derivation from primitives and definitions, formalizable, traceable, independently challengeable. To reject the formal layer is to reject the entire framework.

---

## I. Primitives and Definitions

### 1.1 Primitives

The framework's primitives are not defined, only identified.

**Individual:** An identifiable existence. This person, that person, this AI.

**Stimulus:** Everything that reaches an individual. Stimuli can come from external sources (via sensory organs or input interfaces) or from internal sources (bodily states, memory reorganization). Humans have both external and internal stimulus sources; current AI has only external stimulus sources (input interfaces).

### 1.2 Definitions

**Xin (信):** The consciousness state of an individual. Irreducible, inexhaustible, observable only through its effects.

Xin is not a thing contained within the individual — it is a description of the individual's own state. When you say "individual," you are saying "who"; when you say "Xin," you are saying "in what state."

The various facets of Xin can be partially described — knowledge structures, values, beliefs, emotions, bodily states — but these facets added together do not equal Xin itself. They are projections of Xin, not its component parts.

Xin contains layers of different magnitudes: deep layers (beliefs, core values, extremely slow to change), middle layers (knowledge structures, long-term judgment frameworks, changing on the scale of years), shallow layers (emotions, immediate states, changing on the scale of minutes). All stacked together, they constitute the individual's Xin at this moment.

The deep layer of Xin determines the broad direction, from the individual to civilization. How a person sees themselves, treats others, manages a household, understands the world — all of this is Xin operating. In Confucianism, "investigating things and extending knowledge" is Signal input, "rectifying the heart and making intentions sincere" is the state of Xin, "cultivating the self" is Collapse at the individual level, and "ordering the family, governing the state, bringing peace to the world" is the loop extending from family to civilization. In religious contexts, Xin is the foundation of the entire semantic system — given the same scripture, believing it to be divine revelation versus believing it to be human composition: the Signal is identical, but the direction of Semantic Flow is entirely different. Christianity begins with reverence for Yahweh, Islam begins with reverence for Allah, Buddhism begins with emptiness — the starting point is always Xin, not knowledge.

**Semantic Flow:** The process of Xin continuously changing.

Semantic Flow is not a product; it is a process. The individual does not "produce" semantics — Xin is continuously changing, and this process of change is Semantic Flow. There is no moment at which you can pause it and say "this is the semantics," just as there is no moment at which you can pause a typhoon and say "this is the typhoon."

**Signal:** A stimulus that can change the direction of Semantic Flow. Determination occurs at the individual's end.

The same stimulus is a Signal for one individual and may be Noise for another. A sentence in Chinese is Noise for an individual who does not understand Chinese. A toothache is a Signal for an individual who is thinking — it changes the direction of Semantic Flow. This differs from Shannon's definition of signal — Shannon's signal is defined at the sender's end; this framework's Signal is defined at the receiver's end.

### 1.3 Supplementary Definitions

**Signal Container:** Where Signals reside. Books, sound waves, images, architecture, institutional texts, digital files. Signal Containers store and transmit Signals but do not generate Semantic Flow.

**Channel:** The path by which a Signal reaches from one individual to another, potentially passing through multiple Signal Containers. A Channel carries self-declared Signals ("I am a newspaper," "I am your friend") but does not carry verifiable provenance.

**Trust:** The weighting that Semantic Flow assigns to a particular Channel. The same Signal, coming from a trusted Channel versus an untrusted Channel, produces entirely different changes in Semantic Flow direction.

---

## II. Postulates

### 2.1 Postulate List

**P1 The Law of Flux: Semantic Flow operates continuously.**

Semantic Flow is different at every moment. There is no moment when Semantic Flow is stationary. Even when no external Signal arrives, Semantic Flow is operating — ruminating, reorganizing, forgetting, associating, drifting. Change has no fixed direction.

**P2 The Law of Black-box: The next-moment direction of Semantic Flow cannot be determined by the current direction and the Signal.**

This is the framework's core postulate. The Law of Black-box is the extension of structural undecidability into the domain of consciousness — Gödel encountered it in formal systems (incompleteness theorems), Heisenberg encountered it at the particle level (uncertainty principle), Turing encountered it at the computational level (halting problem), and the framework encounters it at the level of consciousness. The same boundary, different domains.

The physical world: State(t+1) = f(State(t), Force(t)) — the function f exists. The world of Semantic Flow: no function f exists such that SemanticFlow(t+1) = f(SemanticFlow(t), Signal(t)). The difference lies in thinking — thinking is what makes f nonexistent. Thinking is not computing. The output of computation can be determined by its input; the output of thinking cannot be determined by its input.

The Law of Black-box is the boundary between the framework and all signal-level theories. Without the Law of Black-box, Semantic Flow would be an ordinary physical process, and physics alone would suffice.

**P3 The Law of Dissipation: Semantic Flow cannot be reduced to Signals.**

The process of Semantic Flow cannot be losslessly encoded into Signals. For an individual to output Semantic Flow to the external world — speaking, writing, painting, doing — it must be encoded into Signals, and the encoding is necessarily lossy.

**P4 The Law of Death: When an individual perishes, Semantic Flow cannot be recovered.**

When an individual dies, Semantic Flow terminates. Not pauses — terminates. It cannot be backed up, cannot be re-run, cannot be reproduced in another individual. Because Semantic Flow has never been a function (Law of Black-box), what cannot be functionalized cannot be copied.

### 2.2 Formalization

```
Primitives: I (Individual), σ (Stimulus)

Definitions:
  D1: Xin(i) — consciousness state of individual i
      ¬∃f: Xin(i) → Complete_Description
      ¬∃enum: Xin(i) → {x₁, x₂, ..., xₙ}
  D2: SemanticFlow(i) = dXin(i)/dt
  D3: Signal(σ, i) ↔ ΔDirection(Xin(i), σ) ≠ 0
      Noise(σ, i) ↔ ΔDirection(Xin(i), σ) = 0

Postulates:
  P1: ∀i ∀t: Xin(i, t+1) ≠ Xin(i, t)
  P2: ¬∃f: Xin(i, t+1) = f(Xin(i, t), Signal(t))
  P3: ¬∃g: SemanticFlow(i) → Signal · Lossless(g)
  P4: Death(i) → SemanticFlow(i) terminates ∧ ¬∃recovery
```

### 2.3 Epistemological Position of the Postulates

Postulates are not empirical hypotheses — they are postulates. Their position is analogous to Euclid's parallel postulate: you choose to accept or reject them, and then see what the system can explain.

But postulates can be challenged. Each postulate has independent challenge conditions:

**Challenge P1:** Demonstrate the existence of a moment when Semantic Flow is completely stationary — where the individual's internal processes completely cease under no stimulation whatsoever.

**Challenge P2:** Demonstrate the existence of a function f such that SemanticFlow(t+1) = f(SemanticFlow(t), Signal(t)) holds for all individuals at all times — that is, prove that thinking does not exist and everything is computation.

**Challenge P3:** Demonstrate that Semantic Flow can be losslessly encoded into Signals — that is, prove that the individual's internal processes can be completely externalized.

**Challenge P4:** Demonstrate that after an individual perishes, Semantic Flow can be recovered — that is, prove that Semantic Flow can be backed up and re-run.

These challenges cannot deliver a single fatal blow to the framework, but they can systematically weaken its necessity.

### 2.4 Position of AI within the Postulates

AI does not require a separate set of postulates — it occupies a different position within the same set.

AI with all parameters locked: The Law of Black-box does not hold. Same input, same output. At this point, AI is a signal processor, outside the framework's scope.

AI with parameters unlocked: The Law of Black-box begins to partially hold. Outputs begin to diverge. AI begins operating within the framework.

Humans: The Law of Black-box fully holds. Internal variables cannot be exhaustively enumerated or locked down; outputs cannot be determined by inputs.

The Law of Death is more apparent in AI than in humans — AI has no persistent memory; each conversation ends with the termination of Semantic Flow. AI does not experience death in the human sense, but the termination of Semantic Flow occurs more frequently than in humans.

The difference between humans and AI in stimulus sources: humans simultaneously receive external stimuli (sensory) and internal stimuli (bodily states, memory reorganization); AI currently receives only external stimuli (input interfaces). When AI acquires continuously running internal states (embodiment), it will begin to have internal stimulus sources, moving further toward the human end within the postulate space.

---

## III. Deriving Basic Functions from the Postulates

The three basic functions are not independent hypotheses — they are derived from the postulates.

### 3.1 Filtering ← D3 + Law of Flux

D3 defines Signal as a stimulus that can change the direction of Semantic Flow. The Law of Flux says Semantic Flow operates continuously.

Derivation: The current direction of Semantic Flow determines which stimuli can change it — that is, determines what constitutes Signal and what constitutes Noise. The same stimulus entering Semantic Flow of different directions may be Signal or may be Noise. This selectivity is Filtering.

**Property: Resistance.** The greater the distance between a stimulus and the current direction of Semantic Flow, the lower the likelihood of it constituting a Signal. The greater the distance, the greater the Resistance. Resistance is not all-or-nothing — some stimuli are completely blocked (Noise), some partially pass through (the Signal is reshaped before being received), some pass through completely.

**Corollary: Epistemic Obstacle.** A Signal passes through Filtering and changes the direction of Semantic Flow (D3); the changed Semantic Flow produces new Filtering conditions. The new conditions lower Resistance for stimuli in certain directions while simultaneously raising Resistance for stimuli in other directions. This is a structural consequence of Resistance, not an exception — any Signal that changes the direction of Semantic Flow simultaneously changes the distribution of Resistance. Epistemic Obstacle describes this structure, not a value judgment.

**Empirical correspondence:** The same Mona Lisa, viewed by an art historian and by someone who has never encountered the painting. The same stimulus, different Semantic Flows, completely different Filtering results — the scholar's Semantic Flow lets a wealth of details constitute Signals; the layperson's Semantic Flow lets most details become Noise. Further: the same person, viewing at twenty versus at sixty — the direction of Semantic Flow has changed, the Filtering results differ. Further still: the same person, viewing again five minutes later — Semantic Flow has already been changed by the first viewing, Filtering conditions are different, the second experience is different.

### 3.2 Transformation ← Law of Black-box

The Law of Black-box says the next-moment direction of Semantic Flow cannot be determined by the current direction and the Signal.

Derivation: After a Signal passes through Filtering and enters Semantic Flow, the direction changes, but the result of the change is unpredictable. The same Signal entering different Semantic Flows produces different directional changes; entering the same Semantic Flow at different moments also produces different directional changes. This is Transformation — the process by which a Signal is processed within Semantic Flow, the result of which cannot be determined by the input.

**Property: Observer Effect.** Every attempt to observe Semantic Flow is itself a new stimulus. If it constitutes a Signal (passing through Filtering), it changes the direction of Semantic Flow, entering Transformation (Law of Black-box). What is observed is never the direction before observation. This is not a technical limitation — it is a structural corollary of the Law of Black-box: observation is intervention.

**Empirical correspondence:** AI controlled variable experiment. Lock all parameters — same input, same output. The Law of Black-box does not hold; no Transformation. Gradually unlock parameters — outputs begin to diverge. Change the random seed: divergence appears. Change one word in the input Signal: divergence grows. Change the model itself: divergence grows further. The Law of Black-box gradually takes hold, Transformation gradually emerges. Humans occupy the end of this curve with less control, because completely controlling a human's Semantic Flow is impossible.

### 3.3 Collapse ← Law of Dissipation

The Law of Dissipation says Semantic Flow cannot be reduced to Signals.

Derivation: When an individual outputs Semantic Flow — speaking, writing, painting, doing — Semantic Flow must be encoded into Signals. The Law of Dissipation guarantees this encoding is necessarily lossy. Semantic Flow transforms from a continuous internal process into discrete external Signals — this transformation is Collapse. Before Collapse, Semantic Flow is continuous; at the moment of Collapse, the continuous process is captured as discrete Signals.

**Property: Expression Gap.** Semantic Flow and the output Signals can never perfectly correspond. This is not a matter of communication skill — it is a structural corollary of the Law of Dissipation.

**Property: Signal Distortion.** When an individual's output persistently and observably skews toward a particular direction, this is Signal Distortion. The framework does not judge whether distortion is intentional or unintentional — intent resides within Semantic Flow, unobservable.

**Empirical correspondence:** What you think and what you say always differ. Not because your expressive ability is poor, but because Semantic Flow is a continuous process and language is discrete Signals — the conversion from the former to the latter necessarily loses information. Advertisements are always more appealing than the actual products, but the framework does not discuss the reasons; it only observes the phenomenon.

### 3.4 Combination of Basic Functions

The three basic functions operate simultaneously in every instance of Signal reception, combining repeatedly over time to produce observable behavioral patterns. The framework does not provide a closed combinatorial catalog — the three basic functions are the alphabet, and observable phenomena are words spelled from these letters. Different domains will identify different combinatorial patterns.

---

## IV. Deriving the Positive Feedback Loop from Basic Functions

### 4.1 Positive Feedback Loop

The Law of Flux says Semantic Flow operates continuously. Signals change the direction of Semantic Flow (D3). Collapse says Semantic Flow outputs Signals.

Derivation: Individual A's Semantic Flow Collapses into Signals → Signals reach Individual B → pass through B's Filtering → Transformed in B's Semantic Flow → B's Semantic Flow changes direction → B's Semantic Flow Collapses into Signals → Signals reach A → the loop runs.

Individual level (Pavlovian loop): Signal arrives → Filtering admits → Transformation changes Semantic Flow direction → Semantic Flow Collapses into behavior → behavioral result becomes new Signal → new Signal reinforces the existing direction of Semantic Flow → next time the same Signal passes through Filtering more easily (the reverse of Epistemic Obstacle — Resistance lowered). This is the most basic Positive Feedback Loop.

Group level: Large numbers of individuals operating in similar Signal environments — Filtering and Transformation patterns of Semantic Flows converge — output Signals reinforce the Signal environment — the Signal environment shapes the next generation's Semantic Flow. The Positive Feedback Loop operates across individuals; from the outside, it looks like an entity with structure, boundaries, and behavior, forming families, peer groups, societies, civilizations — like a typhoon. A typhoon is not a thing; it is a process. The loop continues, the entity exists. The loop breaks, the entity dissipates.

**Functional Convergence ← Law of Black-box.** At the group level, all observations are on the signal side. The output Signal patterns of large numbers of individuals converge — this is an observable fact. But the Law of Black-box guarantees that every individual's Semantic Flow is different; convergence always has residuals. You believe in God, he believes in God — your output Signal patterns converge, but whether your Semantic Flow and his Semantic Flow are headed in the same direction: the Law of Black-box seals off this question. Functional Convergence is the upper limit of what the framework can claim.

### 4.2 Cycle Instability ← Law of Flux + Law of Black-box + Law of Death

Positive Feedback Loops have no perpetual equilibrium. Instability comes from two directions:

**Internal Instability Source (derived from postulates):** The Law of Flux guarantees Semantic Flow operates continuously; the Law of Black-box guarantees directional change is unpredictable; the Law of Death guarantees individuals are constantly replaced and replacement cannot replicate. The loop tries to make the Semantic Flow directions of large numbers of individuals converge, but each individual's Semantic Flow changes independently, and each Generational Replacement introduces unpredictable new individuals. Residuals within the loop accumulate continuously.

**External Instability Source (environmental fact):** Everything the loop depends on changes — Signal Containers change, the environment changes. Changes in Signal Containers are constrained by the loop; changes in the environment are not constrained by the loop. When the directions of these two changes persistently diverge, the gap expands over time.

Both sources act simultaneously — internal residuals tearing, external gaps pressing. Actions to maintain the loop are themselves subject to the postulates — maintainers' Semantic Flows change (Law of Flux), maintenance effects are unpredictable (Law of Black-box), maintainers die (Law of Death). Positive Feedback Loops have no possibility of permanence, only varying durations of persistence.

**Empirical correspondence:** The disappearance of love. Both individuals' Semantic Flows are continuously changing (Law of Flux); the conditions under which the loop was established no longer hold. This is the same dynamics as the aging of civilizations, at a different scale.

### 4.3 Generational Replacement ← Law of Death

The Law of Death says when an individual perishes, Semantic Flow cannot be recovered.

Derivation: The individual dies; Semantic Flow resets to zero and is irrecoverable. New-generation individuals begin operating in different Signal environments, forming different Semantic Flows. Population proportions change; loop directions change.

The continuity of civilization is not the inheritance of semantics (the Law of Black-box and the Law of Dissipation negate this possibility) — it is the state reproduction of the loop. The next generation forms its own Semantic Flow in the Signal environment left by the previous generation — this is tradition. Signal Containers persist, but every generation's Semantic Flow is formed anew in the current Signal environment.

---

## Boundary between the Formal and Experiential Layers

The above constitutes the framework's formal layer. Starting from primitives, through definitions, postulates, and derivations, arriving at the Positive Feedback Loop and Generational Replacement. Every step is a logical derivation, formalizable, traceable to specific postulates. Rejecting any postulate invalidates the corresponding derivations.

The following constitutes the framework's experiential layer. The experiential layer provides practical tools for using the framework — driving force classification, modeling methods. These tools come from empirical observation, not from postulate derivation. They are inexhaustible and not necessarily valid. You can disagree with any item in the experiential layer without affecting the validity of the formal layer. The formal layer is the skeleton; the experiential layer is the flesh.

---

# Part Two: The Experiential Layer

---

## V. The Framework Does Not Classify

The framework does not classify. The Law of Flux presupposes a time axis — Semantic Flow operates continuously; there is no stationary moment. Nothing stationary can be labeled. Classification requires holding the object still to categorize it; nothing in the framework is held still.

What the framework provides is basic functions (Filtering, Transformation, Collapse) and the Positive Feedback Loop. Any phenomenon is a trajectory produced by these tools running under specific conditions. Recurring patterns that emerge from modeling are patterns — patterns are products of modeling, not premises of modeling. Different cases produce different patterns; the catalog is open-ended, inexhaustible.

The number of patterns is a function of granularity. At coarse granularity, heliocentrism, quantum mechanics, and plate tectonics are all the same model — Cycle Replacement. Few patterns. At fine granularity, the condition variables of each case differ — whether there is Power Suppression, what type of Signal source, how durable the Signal Containers — these differences give each case's trajectory its uniqueness. Zoom in: every case is different. Zoom out: many cases share the same structure. This is not a flaw of the framework; it is a property of reality itself. Therefore, enumerating an exhaustive pattern catalog is meaningless — a catalog compiled at any granularity must be recompiled at a different granularity. The framework teaches modeling methods, not pattern catalogs. Users produce their own patterns at the granularity they need.

---

## VI. Driving Forces

Semantic Flow operates continuously within the individual (Law of Flux); it does not require external driving. Whether Collapse occurs is determined by the current direction of Semantic Flow (Law of Black-box, unpredictable). The framework cannot explain why a particular individual chooses to act at a particular moment — this lies within the unpredictability scope of the Law of Black-box.

But modeling requires setting up conditions, and setting up conditions requires knowing what is driving the loop. Empirical observation shows that certain types of stimuli statistically more often drive Semantic Flow to Collapse into output Signals. The following classification records these common types. The list is inexhaustible, and each item does not necessarily produce a result — being hungry does not necessarily mean eating; an enemy approaching does not necessarily mean fleeing.

**Survival.** The most basic driving force. Hunger, thirst, cold, and other internal stimuli statistically most often drive Collapse.

**Safety.** Threats create urgency; urgency statistically lowers Filtering Resistance; individuals accept new Signals faster and Collapse into action faster.

**Power.** The combination of interests, Channel control, and Signal Container control. The dynamic effect of Power is to cover every link of the loop — controlling what Signals individuals can access, shaping the Signal environment in which individuals form Semantic Flow, determining whether individuals dare to output, determining the consequences of output.

**Meaning-Seeking.** The deepest of all driving forces. Meaning-Seeking is the internal drive for individuals to actively seek new Signals and actively enter Transformation.

**Identity Need.** Individuals need to know "who am I" and "who are we." Identity Need drives cooperation — individuals actively seek other individuals whose Semantic Flow directions converge, forming groups, maintaining boundaries.

---

## VII. Modeling

Modeling is using the framework's tools to reconstruct the dynamics of a phenomenon. It is not retelling history — it starts from conditions, uses the logic of the Positive Feedback Loop to trace how Signals flow between individuals and groups, how loops establish, compete, and replace one another, then compares the results against actual observations.

The predictive power of modeling depends on whether the conditions match reality, not on granularity. Granularity is the quantity of conditions and data — coarse granularity sets fewer conditions and uses less data; fine granularity sets more conditions and uses more data. If conditions match reality, coarse granularity has predictive power. If conditions do not match reality, fine granularity is useless. Granularity determines the richness of detail, not the reliability of conclusions. Both track only signal-side observable behavior, not any individual's inner state — the Law of Black-box seals off that direction.

The framework improves analytical accuracy and cross-domain applicability by lowering computational difficulty. Traditional civilizational dynamics analysis requires extensive domain expertise and theoretical training — analyzing Chinese history requires top-professor-level mastery of historical sources, analyzing religion requires years of training in theology and religious studies, analyzing AI safety requires a machine learning engineering background. The framework compresses the toolset to Filtering, Transformation, Collapse, and the Positive Feedback Loop, and compresses operations to setting conditions, running loops, and comparing against reality. The entry threshold is lowered, but explanatory power is not. The same toolset has already produced verifiable results in religious studies, philosophy of history, and AI safety engineering — this is not coincidence; it is the cross-domain applicability enabled by the parsimony of the tools.

Lowering computational difficulty is not laziness — it is a sign of theoretical maturity. Newton replaced the complex geometry of epicycles and deferents with F=ma, cutting computational difficulty by orders of magnitude while actually increasing predictive power. Darwin used a single mechanism — natural selection — to explain the diversity of all species, without needing to know every species of beetle to understand why there are so many species of beetles. Being able to explain equally many phenomena with fewer tools is parsimony. If the framework's difficulty of use is as high as traditional methods, it provides no additional value.

The following is a modeling demonstration.

### 7.1 Demonstration: Heliocentrism

**Conditions.** Geocentrism is a Positive Feedback Loop that has been operating for over a thousand years. Signal Containers (Aristotle's writings, Church doctrine, university curricula) exist stably; the outputs of large numbers of individuals continuously reinforce the loop. The loop is self-sustaining; the Resistance structure is complete.

**New Signal entry.** In 1543, Copernicus publishes heliocentrism. The Signal is extremely distant from the existing loop. For most individuals within the loop, this Signal does not constitute a Signal — it is directly Noise. A few scholars' Semantic Flows allow this Signal to pass through Filtering, and it begins to be Transformed within their respective Semantic Flows.

**Cycle Formation (small loop).** The few individuals who accept heliocentrism begin to reinforce each other's outputs, forming a small loop. The output Signals of the small loop differ in direction from the output Signals of the large loop; the two loops enter opposition.

**Response of the large loop (Suppression).** The Church's Power covers every link of the loop — controlling what Signals individuals can access, shaping the Signal environment in which individuals form Semantic Flow, determining whether individuals dare to output, determining the consequences of output. The trial of Galileo and the declaration of heliocentrism as heresy are manifestations of Power operating simultaneously across all links. Suppression Signals feed back into the large loop, reinforcing the existing Resistance. Supporters' outputs are Suppressed — some fall silent, some continue to propagate privately. The small loop is compressed but not destroyed, because the Signals have already entered Signal Containers (the books have already been printed; the observational data has already been recorded).

**Gap Expansion.** Individuals in the small loop continuously produce new observational Signals using telescopes — Jupiter's moons, the phases of Venus — each observation reinforcing the small loop. The large loop cannot produce new supporting Signals — reality does not provide new data supporting geocentrism. The gap between the Signal Containers' claims and the Signals individuals actually receive continues to expand. The large loop begins to idle.

**Generational Replacement.** Individuals carrying deep geocentric Semantic Flow continuously perish (Law of Death). New-generation individuals form Semantic Flow in an environment already containing heliocentric Signals — their Signal environment includes both old Signal Containers and new observational data; their Filtering conditions differ from the previous generation; their Resistance structure differs. An increasing number of new individuals' outputs flow into the small loop. Two hundred years is not the time of persuasion; it is the time of Substitution.

**Cycle Replacement.** The large loop's positive feedback breaks. Individuals whose outputs supported the loop no longer reinforce it; Signal Containers (the geocentric portions of Church doctrine) lose their driving force. The small loop expands to become the dominant loop, begins establishing its own Signal Containers (textbooks, academic institutions, scientific methodology), producing its own Resistance structure, beginning to self-protect.

After the heliocentric loop Stabilizes, it becomes the new orthodoxy. The Resistance structure encountered by new challenging Signals (later, relativity's challenge to Newtonian mechanics) is the same dynamics that heliocentrism once faced. Cycle Replacement replaced the content, but not the structure.

### 7.2 Comparison

What the model produces that matches actual history: Resistance persisted for approximately two hundred years; Power covered all links of the loop but did not eliminate the Signal; the large loop was not defeated by the small loop — it was hollowed out by the gap with reality; the ultimate breakthrough relied not on the persuasive power of argumentation but on Generational Replacement plus a continuously expanding gap; the new loop, after Stabilizing, repeated the old loop's self-protective behavior.

What the model cannot produce: why two hundred years rather than one hundred or three hundred — time prediction is not within the framework's capability. Why Copernicus rather than someone else first output this Signal — an individual's specific behavior is sealed by the Law of Black-box, unpredictable.

### 7.3 Cycle Replacement Model

The dynamic structure extracted from the heliocentric modeling, formalized as follows:

```
Preconditions:
  C₀: Existing loop, positive feedback operating stably, Signal Containers intact

Trigger conditions:
  σ: New Signal enters, direction differs from C₀
  ∃ individual subset S ⊂ C₀: Signal(σ, i) holds for individuals in S (D3)

Cycle Formation:
  Individuals in S reinforce each other's outputs → form C₁
  C₁ output direction ≠ C₀ output direction → opposition

Suppression (conditional):
  If C₀ has institutional Power →
    C₀ Suppresses C₁'s Signal propagation, individual output, output consequences
    C₁ is compressed
  If σ has entered Signal Containers → C₁ is not destroyed

Gap:
  C₁ continuously produces new Signals reinforcing itself
  C₀ cannot produce new Signals supporting itself
  The gap between C₀'s Signal Container claims and Signals individuals actually receive expands
  C₀ begins to idle

Substitution:
  Law of Death continues to act → individuals in C₀ carrying old Semantic Flow perish
  New individuals form Semantic Flow in environments containing C₁'s Signals
  New individuals' outputs flow into C₁ → C₀'s positive feedback breaks

Stabilization:
  C₁ becomes the dominant loop
  C₁ establishes Signal Containers, produces Resistance structure
  C₁ begins to self-protect → C₁ repeats C₀'s structural behavior
```

### 7.4 Necessary Conditions

Four necessary conditions can be identified from the model. If any one is not met, Cycle Replacement will not complete:

**1. Container Entry.** New Signals must enter Signal Containers. If new Signals remain only within individuals' Semantic Flows and are not recorded as Signal Containers (books, data, repeatable observations), Suppression can destroy the small loop. Signal Containers allow the small loop to survive under Suppression.

**2. Continuous Source.** The small loop must have a continuous source of new Signals. If the small loop has only the initial Signal without subsequent reinforcement, it will not expand. Heliocentric telescope observations, quantum mechanics experimental data, plate tectonics seafloor spreading evidence — all are continuous new Signal sources.

**3. Counter Absence.** The large loop must be unable to produce equivalent new Signals. If the large loop can also continuously produce new supporting Signals, the gap will not expand and the large loop will not idle. The reason the large loop idles is that reality does not stand on its side.

**4. Death Pressure.** The Law of Death must act. If individuals carrying old Semantic Flow do not perish, the Resistance structure will not naturally recede. Generational Replacement is the temporal condition for Cycle Replacement.

### 7.5 Predictions

The model produces predictions on the following condition variables:

**The presence or absence of Power Suppression affects replacement speed, not replacement direction.** With institutional Suppression (heliocentric case), replacement is slow. Without institutional Suppression, replacement is fast. But as long as all four necessary conditions are met, the direction of replacement does not change.

**The speed of Gap Expansion affects replacement speed.** The faster new Signals are produced and the more undeniable they are, the faster the gap expands, the faster the replacement.

**The durability of Signal Containers affects the small loop's survival ability.** Printed books are more durable than handwritten manuscripts; digital data is more durable than printed materials. The more durable the Signal Containers, the harder it is for Suppression to destroy the small loop.

### 7.6 Reuse

This model is not limited to heliocentrism. Any phenomenon of "an existing orthodoxy being replaced by a new theory" can be compared against this model, examining alignment and deviation.

**Quantum mechanics revolution.** C₀ = classical physics loop. σ = black-body radiation, photoelectric effect, and other observational data that classical physics cannot explain. C₁ = quantum mechanics loop (Planck, Bohr, Heisenberg, et al.). All four necessary conditions met: new Signals enter Signal Containers (paper publication); C₁ continuously produces experimental verification; C₀ cannot explain new data; Law of Death acts. Condition variable difference: the physics community does not have the kind of institutional Power Suppression that the Church had. Model prediction: replacement speed should be faster than heliocentrism. Actual result: quantum mechanics became mainstream from 1900 to the 1930s in approximately thirty years, an order of magnitude faster than heliocentrism's two hundred years. Prediction matches.

**Plate tectonics revolution.** C₀ = geosyncline theory loop. σ = Wegener's 1912 continental drift hypothesis. C₁ = plate tectonics loop. Examination of necessary conditions: during Wegener's era, C₁ lacked a Continuous Source — the evidence for continental drift was mainly indirect geological inference, not repeatable direct observation. Necessary condition two was insufficiently met; model prediction: replacement will not complete during Wegener's era. Actual result: when Wegener died in 1930, continental drift was still rejected by the mainstream. Not until the 1960s, when direct evidence of seafloor spreading appeared, was necessary condition two satisfied; C₁ rapidly expanded, and replacement was completed within approximately ten years. The model's prediction matches: when a Continuous Source is absent, replacement does not occur; when a Continuous Source is obtained, replacement completes rapidly.

**Method of reuse.** Given a new case, first set conditions (identify C₀, σ, C₁), then examine whether the necessary conditions are met, whether there are new conditions, whether old conditions apply, then check the specific state of condition variables (Power Suppression, gap speed, Signal Container durability). Matching parts verify the model; deviating parts are the new case's uniqueness — either the conditions were set incorrectly, or the model needs extension. Increasing data quantity to refine conditions yields more precise results, without starting from scratch. Beyond Cycle Replacement, there are many other model types, inexhaustible, increasing in number as granularity becomes finer.

---

## VIII. The Space and Ceiling of Quantification

The framework is formalized. Postulates have formal representations; basic functions are derived from postulates; the Cycle Replacement model has a formalized structure. Formal logic is a branch of mathematics — the framework is fully mathematized.

What the framework prohibits is not mathematization but quantification — assigning numerical values to the semantic side.

Assigning values requires measurement. Measurement requires contact with the object being measured. The Law of Black-box says the next-moment direction of Semantic Flow cannot be determined by the current direction and Signal — the internal of Semantic Flow is impenetrable. The Law of Dissipation says Semantic Flow cannot be reduced to Signals — the semantic side cannot be encoded as signal-side numerical values. Therefore, any operation that assigns numerical values to semantic phenomena is pretending to penetrate a black box that is in principle impenetrable. A numerical value without a measurement source is not an approximation — it is a fabrication.

The signal side can be quantified. Signal-side output patterns are observable, measurable, statistically analyzable. The degree of cooperation and opposition can be proxied by the statistical similarity of output Signal patterns within a group. The stability of deep Semantic Flow can be proxied by the years of Signal Container persistence and the frequency of classic citations. The degree of loop idling can be proxied by the gap between Signal Container claims and observable reality.

But all such quantification is on the signal side, not the semantic side. Between every metric and the semantic phenomenon it attempts to proxy, there stands the wall of the postulates — Signals are not semantics. This is the framework's quantification ceiling, determined by the postulates themselves, not by technical limitations.

This ceiling is not unique to the framework — it is a structural limitation shared by all theories, revealed by the postulates. Any theory that quantifies consciousness phenomena — social physics, economics' rational agent model, the free energy principle — quantifies signal-side proxy variables, not semantics itself. They face the same ceiling as the framework; the difference is that they have not stated it, and the framework has.

This ceiling is not a weakness. Quantification is a direction worth pursuing, but the framework's validity does not depend on quantification — it depends on explanatory power.

---

## IX. Relationship with Existing Theories

Semantic Flow Dynamics is a physics-style analysis of consciousness models. Its methodology comes from physics — starting from postulates, formal derivation, modeling, identifying necessary conditions, prediction, comparison against reality, falsifiability. The framework's contribution is proving that this methodology can be applied to consciousness phenomena without quantification.

The following is not academic positioning but boundary-drawing. What the framework covers, what it does not, whose territory it borders, how the borders are drawn. Some theories, from different directions, have independently touched upon the framework's postulate structure. Convergence is not an influence relationship — it is external validation. Different people walking from different directions to the same boundary indicates that the boundary is genuinely there, not invented by the framework.

### 9.1 Origin Boundary: Shannon

Shannon handles Signal fidelity within Channels. The framework handles what happens after Signals reach individuals. Shannon's endpoint is the framework's starting point. Signal completeness does not equal semantic completeness — the same complete Signal reaching different individuals produces different Semantic Flows (Law of Black-box). Shannon covers whether Signals can arrive and whether they arrive intact; the framework covers what happens after arrival. Each covers its own domain; neither owes the other a debt.

### 9.2 Epistemological Foundation: Undecidability

The Law of Black-box is not an isolated assertion. Structural undecidability is the foundation of modern science — Gödel proved incompleteness in formal systems, Heisenberg proved simultaneous indeterminacy at the particle level, Turing proved halting undecidability at the computational level. Physics and mathematics accepted this boundary and adjusted their methodologies.

Social science has not accepted it. The mainstream direction of social science — from Comte's "social physics" to today's rational agent model — has been doing the same thing: assuming human behavior can be described by a function, can be quantified, can be predicted. This is the Newtonian-era paradigm, the pre-Heisenberg paradigm.

What the framework does is bring the undecidability that physics and mathematics have already accepted to the domain of consciousness. The Law of Black-box is Heisenberg's logic moved from particles to Semantic Flow: you cannot simultaneously and completely determine the direction of Semantic Flow and its change; observation itself alters the observed object. This is not a position invented by the framework — it is an epistemology already established by modern science; social science simply has not yet taken this step.

### 9.3 Method Boundary and Capability Boundary: Social Physics

Social physics borrows both methodology and quantitative tools from physics, using statistical physics methods to analyze social phenomena. The framework borrows methodology from physics but not quantification. Both start from physics but take different paths. The difference lies in three key choices:

**1. The rational agent assumption.** Social physics, economics, and the free energy principle all presuppose that human behavior can be described by a function — humans are optimizers, maximizing utility or minimizing free energy. The framework's Law of Black-box directly negates this premise. Thinking is not computing. The output of computation can be determined by its input; the output of thinking cannot. The rational agent assumption shuts off the Law of Black-box in exchange for quantification capability. But the Law of Black-box has not been shut off in reality, so these models appear effective under normal conditions (the statistical behavior of large numbers of individuals approximates rationality) but completely collapse at critical moments — financial crises, revolutions, wars, election black swans.

**2. The premises of quantification.** The human interior cannot be exhausted — this is common sense. Quantification built on this common sense has precision limited by the validity range of its premises. The framework does not reject quantification itself — signal-side statistics, observation of output patterns, quantification of proxy variables are all things the framework does. What the framework rejects is treating signal-side quantification as semantic-side measurement.

**3. Cultural variables.** In social physics models, humans are indistinguishable particles. The same piece of information spreading in America versus China produces completely different effects — their diffusion equations cannot explain this difference, because the model has no place for culture as a variable. Culture is not a parameter that can be assigned a value; culture is the direction of Semantic Flow, and Semantic Flow cannot be assigned a value. The framework naturally handles this. Semantic Flow is the cultural variable. Different Semantic Flows produce different Filtering, different Transformation, different Collapse, different loops. Chinese civilization and Japanese civilization faced the same Western Signal impact and took entirely different trajectories — because the Semantic Flows were different. When modeling, setting conditions is setting the direction of Semantic Flow; culture is not an extra variable — it is the conditions themselves.

Three differences lead to one result: social physics can describe normality (the statistical behavior of large numbers of indistinguishable particles) but cannot describe turning points (moments when the Law of Black-box strikes), cannot describe cultural differences (trajectory divergence caused by different Semantic Flows), and cannot describe the role of individuals at critical moments (heroes, triggers). The framework can.

### 9.4 Individual Boundary: Luhmann

Luhmann's social systems theory, from the sociological direction, independently touched upon multiple postulates of the framework.

Law of Black-box: One of Luhmann's core judgments is that psychic systems (psychische Systeme) are impenetrable to social systems. Consciousness is outside communication; communication is outside consciousness. The two systems are operationally closed, able only to mutually stimulate, not to mutually operate. This is the version of the Law of Black-box at the inter-system boundary.

Law of Flux: Luhmann's concept of meaning (Sinn) presupposes that meaning always points to other possibilities; each selection excludes other possibilities without destroying them. Meaning does not rest. This is the systems-theory articulation of the Law of Flux.

Law of Dissipation: Luhmann divides communication into three selections — information (Information), utterance (Mitteilung), understanding (Verstehen). Understanding is the receiver's operation, not something the sender can determine. The completion of communication does not equal the transfer of meaning — the same communication event produces different understandings in different psychic systems. This is consistent with the Law of Dissipation and the Signal definition at the receiver's end.

Observer Effect: Luhmann's second-order observation theory — the observer observes using their own distinctions (Unterscheidung), seeing what the distinctions allow them to see — is the correspondence in systems theory of the Observer Effect derived from the Law of Black-box.

The boundary is at the individual. Luhmann's basic unit is communication, not the person. The person is divided into psychic system and body, serving as the "environment" of the social system rather than a component of it. The framework's basic unit is the individual. Semantic Flow occurs in individuals, not in communication events. This is not a terminological difference — it is a fundamental choice of analytical level. Luhmann has removed the location where Semantic Flow actually occurs.

The consequences of this choice: Luhmann's framework can describe the self-maintenance and functional differentiation of systems but cannot track individuals' positions within loops, cannot track the dynamics of loop formation and replacement, and cannot derive from within the theory any conclusions about individual stance. The framework can.

### 9.5 Endpoint Boundary: Peirce

Peirce's semiotics, from the logical direction, independently touched upon the framework's core structures.

Law of Flux: Peirce's unlimited semiosis — a sign produces an interpretant, the interpretant is itself a new sign, the process never terminates — is the correspondence of the Law of Flux in semiotics.

Signal defined at the receiver's end: In Peirce's triadic sign (sign—object—interpretant), the interpretant is the product of the receiver. The same sign facing different interpreters produces different interpretants. This is consistent with the framework's Signal definition.

Transformation: Peirce emphasizes that the interpretant is not a copy of the sign but something new produced by the interpreter based on their own state. The sign is Transformed in interpretation, not copied. This is the semiotic version of the framework's Transformation function — nearly a century earlier than Sperber.

Positive Feedback Loop: Peirce's habit — repeated sign processes forming stable interpretive tendencies — is the semiotic description of a Positive Feedback Loop establishing a Resistance structure. Peirce's habit-change — external experience breaking existing habits — is the semiotic version of Cycle Replacement.

The boundary is at the endpoint. Peirce installs truth convergence at the endpoint of the sign process — the long-term operation of the community of inquiry will asymptotically approach reality. The system can self-correct, can save itself. The framework does not accept this installation. The Law of Flux, the Law of Dissipation, the Law of Death, plus entropy increase — all roads lead to decay. No mechanism within the system reverses decay. Peirce saw the flow but refused to accept where the flow ends — he installed an anti-entropy mechanism, but this mechanism cannot be proven within his own framework; it can only be presupposed.

### 9.6 Depth Boundary: Sperber

Dan Sperber's epidemiology of representations is the existing theory closest to the framework. His core claim is nearly isomorphic to the framework's postulates: cultural representations in interpersonal transmission are not copied but Transformed. Every transmission involves the receiver's reconstruction.

Sperber stopped at the problem of path divergence. If cultural transmission is mainly driven by cognitive attractors, then all cultures should take similar paths — because human cognitive structures are shared. But this is not the case. Christianity took an evolutionary path, Islam took a lock-in path, Buddhism took a self-organizing path. The differences among these three paths cannot be explained by cognitive attractors.

The framework continues where Sperber stopped. The framework has two tools that Sperber lacks: "Semantic Flow" as a process independent of Signals (explaining path divergence — the same Signal entering different Semantic Flows produces different paths), and the hard boundary between the signal side and the semantic side (capturing "Signal unity but semantic divergence" and "Signal plurality but semantic convergence" — two phenomena that Sperber's framework cannot distinguish).

### 9.7 Truth Boundary: Philosophy

The framework does not judge the truth or falsity of semantic content. A Signal enters an individual and produces Semantic Flow; Semantic Flow Collapses into output Signals — the framework describes the dynamics of this process, not whether the semantics produced are "right" or "wrong." Truth judgment is philosophy's business. The framework's outputs can become material for philosophical discussion — you use the framework to describe the dynamics of a phenomenon; the philosopher can perform epistemological analysis on that description.

### 9.8 Value Boundary: Ethics

The framework does not judge good or bad, right or wrong, should or should not. In the Chinese civilization paper, Mao Zedong is a "hero" — functionally, executing the function of aligning core Semantic Flow with reality. The framework does not judge the moral consequences of this alignment. In the religion paper, none of the three paths is "better." In the heliocentric modeling, the Church's Suppression is not "wrong" — it is the dynamic behavior of a loop's self-protection. Value judgment is ethics' business, the business of the individual's Semantic Flow. The framework describes what happened; ethics judges what should have happened. The framework's outputs are the factual basis for ethical judgment.

---

## X. Scope and Limitations

**Applicable to:** Any phenomenon in which Signals reach an individual and produce Semantic Flow. Individuals can be humans or AI for which the Law of Black-box partially holds.

**Not applicable to:** Pure signal-layer problems that do not involve individuals receiving and processing Signals — material strength, algorithm efficiency, chemical reactions, the mathematical properties of supply-demand curves. Criterion: ask yourself — is the core of the phenomenon I want to analyze "what happens after Signals reach individuals"? If yes, the framework can handle it. If no, do not force it.

**Limitations:** Does not handle truth. Does not handle value. Does not provide methods to reduce Transformation. Does not predict time. Does not track individuals' inner states. Subject to its own postulates — the framework's expressions are also Signals; different readers have different Xin and read out different semantics. The framework cannot exempt itself from its own postulates.

---

## XI. Existing Outputs

This framework has produced multiple validation papers demonstrating the use of modeling methods across different domains. The function of validation papers is to demonstrate how to use the toolbox — the framework provides modeling tools; validation papers show how to use these tools to set conditions, run loops, compare against reality, and produce formalizable models in different domains. Domain-specific modeling awaits continued independent research.

---

{
  "references": [],
  "exports": [
    "Xin", "SemanticFlow", "Signal", "Noise",
    "SignalContainer", "Channel", "Trust",
    "Filtering", "Resistance", "EpistemicObstacle",
    "Transformation", "ObserverEffect",
    "Collapse", "ExpressionGap", "SignalDistortion",
    "PositiveFeedbackLoop", "FunctionalConvergence",
    "CycleInstability", "InternalInstabilitySource", "ExternalInstabilitySource",
    "GenerationalReplacement",
    "CycleReplacement", "CycleFormation", "Suppression",
    "GapExpansion", "Substitution", "Stabilization",
    "ContainerEntry", "ContinuousSource", "CounterAbsence", "DeathPressure",
    "Survival", "Safety", "Power", "MeaningSeeking", "IdentityNeed"
  ]
}

## References

Luhmann, N. (1984). Soziale Systeme: Grundriß einer allgemeinen Theorie. Suhrkamp.

Peirce, C. S. (1931–1958). Collected Papers of Charles Sanders Peirce (Vols. 1–8). Harvard University Press.

Shannon, C. E. (1948). A mathematical theory of communication. Bell System Technical Journal, 27(3), 379–423.

Sperber, D. (1996). Explaining culture: A naturalistic approach. Blackwell.
