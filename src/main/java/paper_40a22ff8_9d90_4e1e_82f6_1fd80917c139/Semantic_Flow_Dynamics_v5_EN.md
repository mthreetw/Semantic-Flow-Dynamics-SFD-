# Semantic Flow Dynamics v5.0

*語意流動力學*

Cheng Yu Huang / 黃正宇

Contact: mthree.tw@gmail.com / ORCID: 0009-0003-9884-7401

2026

---

## Abstract

This paper proposes Semantic Flow Dynamics framework v5.0, addressing what Shannon's information theory does not cover: what happens after a signal reaches an individual. The framework is a physics-style analysis of consciousness models—its methodology comes from physics (postulates, formal derivation, modeling, prediction, comparison with observation), formalized but not quantified.

The framework starts from two primitives (individual, stimulus) and three definitions (xin, semantic flow, signal), and establishes four postulates. The core postulate, the Law of Black-box, asserts that the next-moment direction of semantic flow cannot be determined by its current direction and incoming signals—this is the dividing line between the framework and all signal-level theories. From the postulates, three basic functions (filtering, transformation, collapse) and their properties are derived, and from the basic functions, positive feedback loops are derived. The above constitutes the framework's formal layer—every step is a logical derivation, traceable and formalizable.

Beyond the formal layer, the framework provides an empirical layer with practical tools: driving force classification and modeling methods. The framework does not classify—the Law of Flux presupposes a time axis; nothing stands still long enough to be labeled. Recurrent patterns that emerge from modeling are called patterns; patterns are products of modeling, not its premises. The framework increases cross-domain applicability by reducing computational difficulty. The same toolkit has already produced verifiable results in religious studies, philosophy of history, and AI safety engineering.

**Keywords:** Semantic Flow Dynamics, xin, semantic flow, filtering, transformation, collapse, epistemic barrier, positive feedback loop, modeling, postulate system

---

# Part One: The Formal Layer

The formal layer is the skeleton of the framework. Every step is a logical derivation from primitives and definitions—formalizable, traceable, independently challengeable. Reject the formal layer and you reject the entire framework.

---

## I. Primitives and Definitions

### 1.1 Primitives

The framework's primitives are not defined but identified.

**Individual:** An identifiable entity. This person, that person, this AI.

**Stimulus:** Everything that reaches an individual. Stimuli may come from outside (via sensory organs or input interfaces) or from inside (bodily states, memory reorganization). Humans have both external and internal stimulus sources; current AI has only external stimulus sources (input interfaces).

### 1.2 Definitions

**Xin (信):** The consciousness state of an individual. Irreducible, inexhaustible, observable only through its effects.

Xin is not a thing inside an individual; it is a description of the individual's state itself. When you say "individual," you are saying "who"; when you say "xin," you are saying "in what state."

The various facets of xin can be partially described—knowledge structures, values, beliefs, emotions, bodily states—but these facets added together do not equal xin itself. They are projections of xin, not its component parts.

Xin contains layers of different magnitudes: deep (beliefs, core values, changing extremely slowly), middle (knowledge structures, long-term judgment frameworks, changing on the scale of years), shallow (emotions, immediate states, changing on the scale of minutes). All layered together, they constitute the individual's xin at this moment.

The deep layer of xin determines the overall direction, from individual to civilization. How a person views oneself, treats others, manages a household, understands the world—all of this is xin's state at work. In the religious context, xin is the foundation of the entire semantic system—the same scripture, received with xin that it is divine revelation versus xin that it is human composition, produces completely different directions of semantic flow even though the signal is identical. Christianity begins with fearing the Lord, Islam begins with fearing Allah, Buddhism begins with saying emptiness—the starting point is always xin, not knowledge.

**Semantic Flow:** The ongoing process of xin's continuous change.

Semantic flow is not a product; it is a process. The individual does not "produce" semantics; xin is continuously changing, and that process of change is semantic flow. There is no moment at which you can pause it and say "this is semantics," just as there is no moment at which you can pause a typhoon and say "this is the typhoon."

**Signal:** A stimulus capable of changing the direction of semantic flow. Determination occurs at the individual's end.

The same stimulus may be a signal for one individual and noise for another. A sentence in Chinese is noise for an individual who does not understand Chinese. A toothache is a signal for an individual in the middle of thinking—it changes the direction of semantic flow. This differs from Shannon's definition of signal—Shannon's signal is defined at the sender's end; this framework's signal is defined at the receiver's end.

### 1.3 Supplementary Definitions

**Signal Container:** A place where signals reside. Books, sound waves, images, architecture, institutional texts, digital files. Signal containers store and transmit signals but do not produce semantic flow.

**Channel:** The path by which a signal travels from one individual to another, potentially passing through multiple signal containers. Channels carry self-declared signals ("I am a newspaper," "I am your friend") but do not carry verifiable provenance.

**Trust:** The weighting that semantic flow assigns to a specific channel. The same signal, coming from a trusted channel versus an untrusted one, produces completely different changes in the direction of semantic flow.

---

## II. Postulates

### 2.1 Postulate List

**P1 The Law of Flux: Semantic flow operates continuously.**

Semantic flow is different at every moment. There is no moment at which semantic flow stands still. Even when no external signal arrives, semantic flow is operating—rumination, reorganization, forgetting, association, drifting. Change has no fixed direction.

**P2 The Law of Black-box: The next-moment direction of semantic flow cannot be determined by its current direction and incoming signals.**

This is the framework's core postulate. The Law of Black-box is the extension of structural undecidability into the domain of consciousness—Gödel encountered it in formal systems (incompleteness theorems), Heisenberg encountered it at the particle level (uncertainty principle), Turing encountered it at the computational level (halting problem), and the framework encounters it at the consciousness level. The same boundary, different domains.

In the physical world: State(t+1) = f(State(t), Force(t)); function f exists. In the world of semantic flow: no function f exists such that SemanticFlow(t+1) = f(SemanticFlow(t), Signal(t)). The difference lies in thinking—thinking is what makes f nonexistent. Thinking is not computation. The output of computation can be determined by its input; the output of thinking cannot be determined by its input.

The Law of Black-box is the dividing line between the framework and all signal-level theories. Without the Law of Black-box, semantic flow would be an ordinary physical process, and physics alone would suffice.

**P3 The Law of Dissipation: Semantic flow cannot be reduced to signals.**

The process of semantic flow cannot be losslessly encoded as signals. When an individual outputs semantic flow to the outside—speaking, writing, painting, doing—it must be encoded as signals, and encoding is necessarily lossy.

**P4 The Law of Death: When an individual perishes, semantic flow cannot be recovered.**

When an individual dies, semantic flow terminates. Not pauses—terminates. It cannot be backed up, cannot be rerun, cannot be reproduced in another individual. Because semantic flow was never a function (Law of Black-box), what cannot be functionalized cannot be replicated.

### 2.2 Formalization

```
Primitives: I (Individual), σ (Stimulus)

Definitions:
  D1: Xin(i) — the consciousness state of individual i
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

### 2.3 The Epistemological Position of the Postulates

The postulates are not empirical hypotheses; they are postulates. Their position is analogous to Euclid's parallel postulate—you choose to accept or not accept them, then see what this system can explain.

But the postulates can be challenged. Each postulate has an independent challenge condition:

**Challenge P1:** Demonstrate the existence of a moment when semantic flow is completely still—when an individual, under no stimuli whatsoever, has internal processes that completely cease to operate.

**Challenge P2:** Demonstrate the existence of a function f such that SemanticFlow(t+1) = f(SemanticFlow(t), Signal(t)) holds for all individuals at all moments—that is, demonstrate that thinking does not exist and everything is computation.

**Challenge P3:** Demonstrate that semantic flow can be losslessly encoded as signals—that is, demonstrate that an individual's internal processes can be fully externalized.

**Challenge P4:** Demonstrate that semantic flow can be recovered after an individual perishes—that is, demonstrate that semantic flow can be backed up and rerun.

These challenges cannot fatally overturn the framework in a single stroke but can systematically weaken its necessity.

### 2.4 AI's Position Within the Postulates

AI does not require a separate set of postulates; it occupies a different position within the same set.

AI with all parameters locked: The Law of Black-box does not hold. Same input, same output. The AI is a signal processor and falls outside the framework's scope.

AI with parameters released: The Law of Black-box begins to partially hold. Outputs begin to diverge. The AI begins to operate within the framework.

Humans: The Law of Black-box holds fully. Internal variables are inexhaustible and cannot be locked; output cannot be determined by input.

The Law of Death is more conspicuous for AI than for humans—AI has no persistent memory; at the end of each conversation, semantic flow terminates. AI does not die in the human sense, but its semantic flow terminates more frequently than a human's.

The difference between humans and AI in stimulus sources: humans simultaneously receive external stimuli (sensory organs) and internal stimuli (bodily states, memory reorganization); AI currently receives only external stimuli (input interfaces). When AI acquires a continuously running internal state (embodiment), it will begin to possess internal stimulus sources, and its position within the postulates will shift further toward the human end.

---

## III. Deriving Basic Functions from the Postulates

The three basic functions are not independent hypotheses; they are derived from the postulates.

### 3.1 Filtering ← D3 + The Law of Flux

D3 defines a signal as a stimulus capable of changing the direction of semantic flow. The Law of Flux says semantic flow operates continuously.

Derivation: The current direction of semantic flow determines which stimuli can change it—that is, determines what constitutes a signal and what constitutes noise. The same stimulus, entering semantic flow moving in different directions, may be a signal or may be noise. This selectivity is filtering.

**Property: Resistance.** The greater the distance between a stimulus and the current direction of semantic flow, the lower the likelihood that it constitutes a signal. Greater distance means greater resistance. Resistance is not all-or-nothing—some stimuli are entirely blocked (noise), some partially pass through (the signal's shape is altered before it is received), and some pass through entirely.

**Corollary: Epistemic Barrier.** A signal that passes through filtering changes the direction of semantic flow (D3), and the changed semantic flow produces new filtering conditions. The new conditions lower resistance to stimuli in certain directions while simultaneously raising resistance to stimuli in other directions. This is a structural consequence of resistance, not an exception—any signal that changes the direction of semantic flow simultaneously changes the distribution of resistance. Epistemic barrier describes this structure; it is not a value judgment.

**Empirical correspondence:** The same Mona Lisa, with an art historian and someone completely unfamiliar with the painting standing before it. Same stimulus, different semantic flows, completely different filtering results—the art historian's semantic flow lets a massive number of details constitute signals, while the uninformed viewer's semantic flow lets most details become noise. Further: the same person viewing it at twenty and at sixty—the direction of semantic flow has already changed, and the filtering results differ. Further still: the same person, five minutes after viewing, looks again—semantic flow has already been changed by the first viewing, the filtering conditions are different, and the second experience is different.

### 3.2 Transformation ← The Law of Black-box

The Law of Black-box says the next-moment direction of semantic flow cannot be determined by its current direction and incoming signals.

Derivation: After a signal passes through filtering and enters semantic flow, the direction of semantic flow changes, but the result of the change is unpredictable. The same signal entering different semantic flows produces different directional changes; the same signal entering the same semantic flow at different moments also produces different directional changes. This is transformation—the process by which a signal is processed within semantic flow, whose outcome cannot be determined by the input.

**Property: Observer Effect.** All efforts to observe semantic flow are themselves new stimuli. If they constitute signals (passing through filtering), they change the direction of semantic flow and enter transformation (Law of Black-box). What you observe is never the direction as it was before observation. This is not a technical limitation but a structural corollary of the Law of Black-box—observation is intervention.

**Empirical correspondence:** The AI control variable experiment. Lock all parameters—same input, same output. The Law of Black-box does not hold; no transformation. Gradually release parameters—outputs begin to diverge. Change the random seed: divergence appears. Change one word in the input signal: divergence increases. Change the model itself: divergence expands further. The Law of Black-box gradually takes hold; transformation gradually appears. Humans occupy the end of this curve where the degree of control is minimal, because fully controlling a human's semantic flow is impossible.

### 3.3 Collapse ← The Law of Dissipation

The Law of Dissipation says semantic flow cannot be reduced to signals.

Derivation: When an individual outputs semantic flow—speaking, writing, painting, doing—the semantic flow must be encoded as signals. The Law of Dissipation guarantees that this encoding is necessarily lossy. Semantic flow transitions from a continuous internal process to discrete external signals; this transition is collapse. Before collapse, semantic flow is continuous; at the moment of collapse, the continuous process is captured as discrete signals.

**Property: Expression Gap.** Semantic flow and the output signal can never fully correspond. This is not a matter of communication skill but a structural corollary of the Law of Dissipation.

**Property: Signal Distortion.** When an individual's output persistently and observably skews in a particular direction, this is called signal distortion. The framework does not judge whether distortion is deliberate or inadvertent—intent is inside semantic flow and is unobservable.

**Empirical correspondence:** What you think and what you say are never the same. Not because your expressive ability is poor, but because semantic flow is a continuous process and language is a discrete signal; the conversion from the former to the latter necessarily loses information. Advertisements are always more beautiful than the actual product, but the framework does not discuss the reasons—it only observes the phenomenon.

### 3.4 Combination of the Basic Functions

The three basic functions operate simultaneously in every instance of signal reception and combine repeatedly over time, producing observable behavioral patterns. The framework does not provide a closed list of combinations—the three basic functions are an alphabet, and observable phenomena are words spelled from these letters. Different domains will identify different combinatory patterns.

---

## IV. Deriving Positive Feedback Loops from the Basic Functions

### 4.1 Positive Feedback Loops

The Law of Flux says semantic flow operates continuously. Signals change the direction of semantic flow (D3). Collapse says semantic flow outputs signals.

Derivation: Individual A's semantic flow collapses into signals → signals reach Individual B → pass through B's filtering → transform within B's semantic flow → B's semantic flow changes direction → B's semantic flow collapses into signals → signals reach A → the loop begins to run.

At the individual level (the Pavlovian loop): Signal arrives → filtering lets it pass → transformation changes the direction of semantic flow → semantic flow collapses into behavior → the consequence of the behavior becomes a new signal → the new signal reinforces the existing direction of semantic flow → next time the same signal passes through filtering more easily (the flip side of epistemic barrier—resistance is lowered). This is the most basic positive feedback loop.

At the group level: Large numbers of individuals operating in similar signal environments develop convergent filtering and transformation patterns; output signals reinforce the signal environment; the signal environment shapes the next generation's semantic flow. The positive feedback loop operates across individuals. From the outside, this looks like a structured, bounded, behaving entity—family, peer group, society, civilization, much like a typhoon. A typhoon is not a thing; it is a process. As long as the loop continues, the entity exists. When the loop breaks, the entity dissipates.

**Functional Convergence ← The Law of Black-box.** All observation at the group level is on the signal side. The output signal patterns of large numbers of individuals converge—this is an observable fact. But the Law of Black-box guarantees that every individual's semantic flow is different; convergence always has a residual. You believe in God, he believes in God, your output signal patterns converge, but whether your semantic flow and his semantic flow point in the same direction—the Law of Black-box seals off that question. Functional convergence is the upper limit of what the framework can claim.

### 4.2 Instability of Positive Feedback Loops ← The Law of Flux + The Law of Black-box + The Law of Death

Positive feedback loops have no permanently sustainable equilibrium. Instability comes from two directions:

**Internal source (derived from the postulates):** The Law of Flux guarantees that semantic flow operates continuously; the Law of Black-box guarantees that directional change is unpredictable; the Law of Death guarantees that individuals are constantly being replaced, and replacement cannot replicate what was lost. The loop tries to make the semantic flow directions of large numbers of individuals converge, but each individual's semantic flow is changing on its own, and each generational replacement introduces unpredictable new individuals. Residuals within the loop accumulate continuously.

**External source (environmental fact):** Everything the loop depends on is changing—signal containers are changing, the environment is changing. Changes in signal containers are constrained by the loop; changes in the environment are not. When the two change in persistently divergent directions, the gap widens over time.

Both sources act simultaneously—internal residuals are pulling things apart, external gaps are pressing down. The very actions taken to maintain the loop are themselves subject to the postulates—the maintainers' semantic flow is changing (Law of Flux), the effects of maintenance are unpredictable (Law of Black-box), and the maintainers will die (Law of Death). Positive feedback loops cannot be permanently sustained; only the duration of their persistence varies.

**Empirical correspondence:** The fading of love. Both individuals' semantic flows are continuously changing (Law of Flux); the conditions under which the loop was established no longer hold. This is the same dynamics as civilizational aging, at a different scale.

### 4.3 Generational Replacement ← The Law of Death

The Law of Death says that when an individual perishes, semantic flow cannot be recovered.

Derivation: When an individual dies, semantic flow resets to zero and cannot be recovered. The next generation of individuals begins operating in a different signal environment, forming different semantic flows. Population proportions change; the loop's direction changes.

The continuity of a civilization is not the transmission of semantics (the Law of Black-box and the Law of Dissipation deny this possibility) but the state reproduction of the loop. The next generation forms its own semantic flow within the signal environment left behind by the previous generation—this is tradition. Signal containers persist, but each generation's semantic flow is formed anew within the current signal environment.

---

## The Boundary Between the Formal Layer and the Empirical Layer

The above constitutes the framework's formal layer. Starting from primitives, through definitions, postulates, and derivations, it arrives at positive feedback loops and generational replacement. Every step is a logical derivation, formalizable, traceable to a specific postulate. Reject any single postulate, and the corresponding derivations lose their force.

The following constitutes the framework's empirical layer. The empirical layer provides practical tools for using the framework—driving force classification and modeling methods. These tools come from empirical observation, not from postulate derivation. They cannot be exhaustively enumerated and do not necessarily hold. You can disagree with any item in the empirical layer without affecting the validity of the formal layer. The formal layer is the skeleton; the empirical layer is the flesh.

---

# Part Two: The Empirical Layer

---

## V. The Framework Does Not Classify

The framework does not classify. The Law of Flux presupposes a time axis—semantic flow operates continuously; there is no moment of stillness. Nothing stands still long enough to be labeled. Classification requires pinning the object in place to categorize it; nothing in the framework is pinned in place.

What the framework provides are basic functions (filtering, transformation, collapse) and positive feedback loops. Any phenomenon is a trajectory produced by these tools running under specific conditions. Recurrent patterns that emerge from modeling are patterns—patterns are products of modeling, not its premises. Different cases produce different patterns; the list is open and cannot be exhaustively enumerated.

The number of patterns is a function of granularity. At coarse granularity, heliocentrism, quantum mechanics, and plate tectonics are all the same model—loop replacement. The number of patterns is small. At fine granularity, the condition variables of each case differ—whether there is institutional suppression, what type the signal source is, how durable the signal containers are—these differences give each case's trajectory its own uniqueness. Zoom in and every case is different; zoom out and many cases share the same structure. This is not a defect of the framework; it is a property of reality itself. Therefore, listing an exhaustive catalog of patterns is meaningless—any list compiled at one granularity must be recompiled at another. The framework teaches modeling methods, not pattern catalogs. Users run their own patterns at whatever granularity they need.

---

## VI. Driving Forces

Semantic flow operates continuously within the individual (Law of Flux) and needs no external drive. Whether collapse occurs is determined by the current direction of semantic flow (Law of Black-box; unpredictable). The framework cannot explain why a specific individual at a specific moment chooses to act—this falls within the unpredictability range of the Law of Black-box.

But modeling requires setting up conditions, and setting up conditions requires knowing what is driving the loop. Empirical observation shows that certain types of stimuli more commonly, in a statistical sense, push semantic flow to collapse into output signals. The following classification records these common types. The list cannot be exhaustively enumerated, and no single item necessarily produces a result—being hungry does not necessarily mean eating; an enemy approaching does not necessarily mean running.

**Survival and material interest.** The most basic driving force. Internal stimuli such as hunger, thirst, and cold most commonly, in a statistical sense, push collapse.

**Security and threat.** Threat creates urgency; urgency statistically lowers the resistance of filtering; individuals accept new signals more quickly and collapse into action more quickly.

**Power.** A combination of interest, channel control, and signal container control. The dynamical effect of power is to cover every link in the loop—controlling what signals individuals can access, shaping the signal environment in which individuals form their semantic flow, determining whether individuals dare to output, and determining what consequences follow from output.

**The pursuit of meaning.** The deepest of all driving forces. The pursuit of meaning is the internal drive by which an individual actively seeks new signals and actively enters transformation.

**The need for identity.** Individuals need to know "who am I" and "who are we." The need for identity drives cooperation—individuals actively seek other individuals whose semantic flow directions converge, forming groups and maintaining boundaries.

---

## VII. Modeling

Modeling is the use of the framework's tools to reconstruct the dynamics of a phenomenon. It is not recounting history; it starts from conditions, uses the logic of positive feedback loops to trace how signals flow between individuals and groups, how loops establish, oppose, and replace one another, and then compares the results against actual observation.

The predictive power of a model depends on whether its conditions match reality, not on granularity. Granularity is the quantity of conditions and data—coarse granularity sets up fewer conditions and uses less data; fine granularity sets up more conditions and uses more data. If the conditions match reality, coarse granularity has predictive power. If the conditions do not match reality, fine granularity is useless either. Granularity determines the richness of detail, not the reliability of conclusions. Both levels track only observable behavior on the signal side, never any individual's inner life—the Law of Black-box seals off that direction.

The framework increases analytical accuracy and cross-domain applicability by reducing computational difficulty. Traditional civilizational dynamics analysis requires extensive domain expertise and theoretical training—analyzing Chinese history requires top-professor-level mastery of historical sources; analyzing religion requires years of training in theology and religious studies; analyzing AI safety requires an engineering background in machine learning. The framework compresses the toolkit down to filtering, transformation, collapse, and positive feedback loops, and compresses operations down to setting up conditions, running loops, and comparing against reality. The entry barrier is lowered, but explanatory power is not. The same toolkit has already produced verifiable results in religious studies, philosophy of history, and AI safety engineering—this is not coincidence but the cross-domain applicability that comes from the toolkit's parsimony.

Reducing computational difficulty is not laziness; it is a hallmark of theoretical maturity. Newton replaced the complex geometry of epicycles and deferents with F=ma, cutting computational difficulty by orders of magnitude while actually increasing predictive power. Darwin explained the diversity of all species with a single mechanism—natural selection—without needing to know every species of beetle in order to understand why there are so many. Being able to explain the same range of phenomena with fewer tools is parsimony. If the difficulty of using the framework were as high as that of traditional methods, it would provide no additional value.

The following are modeling demonstrations.

### 7.1 Demonstration: Heliocentrism

**Conditions.** Geocentrism is a positive feedback loop that had been operating for over a thousand years. Signal containers (Aristotle's works, Church doctrine, university curricula) were stably in place; the outputs of large numbers of individuals continuously reinforced the loop. The loop was self-sustaining, with its resistance structure intact.

**New signal enters.** In 1543, Copernicus published the heliocentric model. The signal's distance from the existing loop was extreme. For most individuals within the loop, this stimulus did not constitute a signal—it was directly noise. A small number of scholars' semantic flows let this signal pass through filtering, and it began to transform within their respective semantic flows.

**Small loop forms.** The small number of individuals who accepted heliocentrism began to reinforce each other's outputs, forming a small loop. The output signals of the small loop pointed in a different direction from those of the large loop; opposition arose between the two loops.

**The large loop's response.** The Church's power covered every link in the loop—controlling what signals individuals could access, shaping the signal environment in which individuals formed their semantic flow, determining whether individuals dared to output, and determining what consequences followed from output. The trial of Galileo and the declaration of heliocentrism as heresy were manifestations of power operating across all links simultaneously. The suppression of signals fed back into the large loop, reinforcing existing resistance. Supporters' output was suppressed—some fell silent, some continued to spread ideas privately. The small loop was compressed but not eliminated, because the signals had already entered signal containers (the books had already been printed, the observational data had already been recorded).

**The gap widens.** Individuals in the small loop continued to produce new observational signals with telescopes—the moons of Jupiter, the phases of Venus—each observation reinforcing the small loop. The large loop had no way to produce new supporting signals—reality did not provide new data supporting geocentrism. The gap between the claims of signal containers and the signals individuals actually received continued to widen. The large loop began to idle.

**Generational replacement.** Individuals carrying deep geocentric semantic flow continued to perish (Law of Death). The next generation of individuals formed their semantic flow in an environment already containing heliocentric signals—their signal environment included both old signal containers and new observational data; their filtering conditions differed from the previous generation's, and their resistance structure was different. Increasing numbers of new individuals' output fed into the small loop. Two hundred years was not the time of persuasion but the time of replacement.

**Loop replacement.** The positive feedback of the large loop broke down. The individual outputs that had sustained it no longer reinforced the loop; signal containers (the geocentric portions of Church doctrine) lost their driving force. The small loop expanded into the mainstream loop, began building its own signal containers (textbooks, academic institutions, scientific methodology), produced its own resistance structure, and began to protect itself.

Once the heliocentric loop stabilized, it became the new orthodoxy. The resistance structure encountered by the next challenging signal (later, relativity's challenge to Newtonian mechanics) was the same dynamics that heliocentrism had faced. The loop replaced its content but not its structure.

### 7.2 Comparison with Observation

What the model produced that matches actual history: resistance was maintained for roughly two hundred years; power covered every link of the loop but did not eliminate the signal; the large loop was not defeated by the small loop but was hollowed out by the gap with reality; the eventual breakthrough relied not on the persuasiveness of argument but on generational replacement plus a continuously widening gap; the new loop, once stable, repeated the self-protective behavior of the old loop.

What the model cannot produce: why two hundred years instead of one hundred or three hundred—time prediction is beyond the framework's capability. Why Copernicus and not someone else first output this signal—the specific behavior of individuals is sealed off by the Law of Black-box and is unpredictable.

### 7.3 The Loop Replacement Model

The dynamical structure extracted from the heliocentric modeling, formalized as follows:

```
Preconditions:
  C₀: Existing loop, positive feedback stably operating, signal containers intact

Trigger:
  σ: New signal enters, direction differs from C₀
  ∃ individual subset S ⊂ C₀: Signal(σ, i) holds for individuals in S (D3)

Loop formation:
  Individuals in S reinforce each other's output → C₁ forms
  C₁ output direction ≠ C₀ output direction → opposition

Suppression (conditional):
  If C₀ possesses institutional power →
    C₀ suppresses C₁'s signal dissemination, individual output, and output consequences
    C₁ is compressed
  If σ has already entered signal containers → C₁ is not eliminated

Gap:
  C₁ continuously produces new signals reinforcing itself
  C₀ cannot produce new signals supporting itself
  The gap between C₀'s signal container claims and signals individuals actually receive widens
  C₀ begins to idle

Replacement:
  The Law of Death continuously operates → individuals carrying old semantic flow in C₀ perish
  New individuals form semantic flow in an environment containing C₁'s signals
  New individuals' output feeds into C₁ → C₀'s positive feedback breaks down

Stabilization:
  C₁ becomes the mainstream loop
  C₁ builds signal containers, produces resistance structure
  C₁ begins self-protection → C₁ repeats C₀'s structural behavior
```

### 7.4 Necessary Conditions

Four necessary conditions can be identified from the model. If any one is not met, loop replacement will not complete:

**One: The new signal must enter signal containers.** If the new signal remains only in individuals' semantic flow without being recorded as signal containers (books, data, repeatable observations), suppression can eliminate the small loop. Signal containers enable the small loop to survive under suppression.

**Two: The small loop must have a continuous source of new signals.** If the small loop has only the initial signal without subsequent reinforcement, it will not expand. The telescope observations supporting heliocentrism, the experimental data supporting quantum mechanics, the seafloor-spreading evidence supporting plate tectonics—all are continuous sources of new signals.

**Three: The large loop must be unable to produce comparable new signals.** If the large loop can also continuously produce new supporting signals, the gap will not widen and the large loop will not idle. The large loop idles because reality does not stand on its side.

**Four: The Law of Death must operate.** If individuals carrying the old semantic flow do not perish, the resistance structure will not naturally recede. Generational replacement is the temporal condition for loop replacement.

### 7.5 Predictions

The model generates predictions for the following condition variables:

**The presence or absence of institutional suppression affects replacement speed, not replacement direction.** With institutional suppression (the heliocentric case), replacement is slow. Without institutional suppression, replacement is fast. But as long as all four necessary conditions are met, the direction of replacement is unchanged.

**The speed at which the gap widens affects replacement speed.** The faster and more undeniable the production of new signals, the faster the gap widens and the faster the replacement.

**The durability of signal containers affects the small loop's survival capability.** Printed books are more durable than manuscripts; digital data is more durable than printed materials. The more durable the signal containers, the harder suppression is to eliminate the small loop.

### 7.6 Reuse

This model is not limited to heliocentrism. Any phenomenon of "an established orthodoxy being replaced by a new theory" can be compared against this model, examining where it matches and where it departs.

**The quantum mechanics revolution.** C₀ = the classical physics loop. σ = observational data that classical physics could not explain, such as black-body radiation and the photoelectric effect. C₁ = the quantum mechanics loop (Planck, Bohr, Heisenberg, etc.). All four necessary conditions are met: new signals entered signal containers (papers were published), C₁ continuously produced experimental verification, C₀ could not explain the new data, and the Law of Death operated. Difference in condition variables: the physics community lacked the kind of institutional power suppression the Church wielded. Model prediction: replacement speed should be faster than for heliocentrism. Actual result: quantum mechanics became mainstream in roughly thirty years from 1900 to the 1930s—an order of magnitude faster than the two hundred years for heliocentrism. Prediction matches.

**The plate tectonics revolution.** C₀ = the geosyncline theory loop. σ = Wegener's proposal of the continental drift hypothesis in 1912. C₁ = the plate tectonics loop. Verification of necessary conditions: during Wegener's era, C₁ lacked a continuous source of new signals—the evidence for continental drift consisted mainly of indirect geological inference, not repeatable direct observation. Necessary condition two was insufficiently met. Model prediction: replacement would not complete during Wegener's era. Actual result: Wegener died in 1930 with continental drift still rejected by the mainstream. It was not until the 1960s, when direct evidence of seafloor spreading appeared, that necessary condition two was met, C₁ expanded rapidly, and replacement was completed within roughly a decade. The model's prediction matches: replacement does not occur when a continuous source of new signals is lacking; replacement completes rapidly once such a source is obtained.

**The method of reuse.** Given a new case, first set up conditions (identify C₀, σ, C₁), then verify whether the necessary conditions are met, whether there are new conditions, whether the old conditions apply, and then check the specific state of the condition variables (institutional suppression, gap speed, signal container durability). Points of match verify the model; points of departure represent the new case's uniqueness—either the conditions were not set up correctly, or the model needs to be extended. Increasing the amount of data and refining the conditions yields more precise results without starting from scratch. Beyond loop replacement, there are many other types of models; they cannot be exhaustively enumerated, and the finer the granularity, the greater their number.

---

## VIII. The Space and Ceiling of Quantification

The framework is formalized. The postulates have formal expressions, the basic functions are derived from the postulates, and the loop replacement model has a formalized structure. Formal logic is a branch of mathematics—the framework is fully mathematized.

What the framework prohibits is not mathematization but quantification—assigning numerical values to the semantics side.

Assigning values requires measurement. Measurement requires contact with the object being measured. The Law of Black-box says the next-moment direction of semantic flow cannot be determined by its current direction and incoming signals—the interior of semantic flow is impenetrable. The Law of Dissipation says semantic flow cannot be reduced to signals—the semantics side cannot be encoded as numerical values on the signal side. Therefore, any operation that assigns numerical values to semantic phenomena is pretending to have penetrated a box that is in principle impenetrable. A numerical value with no measurement source is not an approximation—it is a fabrication.

The signal side can be quantified. Output patterns on the signal side are observable, measurable, and statisticable. The degree of cooperation and opposition can be proxied by the statistical similarity of output signal patterns within a group. The stability of deep semantic flow can be proxied by the number of years signal containers have persisted and the frequency with which canonical texts are cited. The degree of loop idling can be proxied by the gap between signal container claims and observable reality.

But all of this quantification is on the signal side, not the semantics side. Between every indicator and the semantic phenomenon it attempts to proxy stands the wall of the postulates—signal does not equal semantics. This is the framework's quantification ceiling, determined by the postulates themselves, not by technical limitations.

This ceiling is not unique to the framework; it is a structural limitation shared by all theories, revealed by the postulates. Any theory that quantifies consciousness phenomena—social physics, economics' rational agent model, the free energy principle—quantifies proxy variables on the signal side, not semantics itself. They face the same ceiling as the framework; the difference is that they have not said so, and the framework has.

This ceiling is not a weakness. Quantification is a direction worth pursuing, but the framework's validity does not depend on quantification—it depends on explanatory power.

---

## IX. Relationship with Existing Theories

Semantic Flow Dynamics is a physics-style analysis of consciousness models. Its methodology comes from physics—starting from postulates, formal derivation, modeling, identifying necessary conditions, prediction, comparison with reality, and falsifiability. The framework's contribution is demonstrating that this methodology can be applied to consciousness phenomena without quantification.

The following is not academic positioning but boundary-drawing. What the framework covers, what it does not, whose territory borders it, and how those borders are drawn.

### 9.1 Starting-Point Boundary: Shannon

Shannon handles the fidelity of signals in channels. The framework handles what happens after signals reach an individual. Shannon's endpoint is the framework's starting point. Signal integrity does not equal semantic integrity—the same intact signal reaching different individuals produces different semantic flows (Law of Black-box). Shannon governs whether signals can arrive and whether they arrive intact; the framework governs what happens to the individual after they arrive. Each governs its own domain; neither owes the other a debt.

### 9.2 Epistemological Foundation: Undecidability

The Law of Black-box is not an isolated claim. Structural undecidability is a foundation of modern science—Gödel proved incompleteness in formal systems, Heisenberg proved that simultaneous determination is impossible at the particle level, and Turing proved that halting is undecidable at the computational level. Physics and mathematics accepted this boundary and adjusted their methodologies.

The social sciences have not accepted it. The mainstream direction of the social sciences—from Comte's "social physics" to today's rational agent models—has been doing the same thing all along: assuming that human behavior can be described by functions, quantified, and predicted. This is a Newtonian-era paradigm, a pre-Heisenberg paradigm.

What the framework does is bring the undecidability already accepted by physics and mathematics into the domain of consciousness. The Law of Black-box is the logic of Heisenberg transported from particles to semantic flow: you cannot simultaneously and fully determine the direction of semantic flow and its change; observation itself alters the observed object. This is not a position invented by the framework; it is an epistemology already established by modern science that the social sciences have yet to adopt.

### 9.3 Method Boundary and Capability Boundary: Social Physics

Social physics borrowed both methodology and quantification tools from physics, using statistical physics methods to analyze social phenomena. The framework borrowed methodology from physics but not quantification. Both depart from physics and take different paths. The difference lies in three key choices:

**One: The rational agent assumption.** Social physics, economics, and the free energy principle all presuppose that human behavior can be described by a function—humans are optimizers, maximizing utility or minimizing free energy. The framework's Law of Black-box directly negates this premise. Thinking is not computation. The output of computation can be determined by its input; the output of thinking cannot be determined by its input. The rational agent assumption turns off the Law of Black-box in exchange for quantification capability. But the Law of Black-box has not been turned off in reality, which is why these models appear effective under normal conditions (statistical behavior of large numbers of individuals approximates rationality) but collapse entirely at critical moments—financial crises, revolutions, wars, electoral black swans.

**Two: The premise of quantification.** The inner life of humans cannot be exhausted—this is common sense. Quantification built on this common sense is limited in precision to the effective range of its premise. The framework does not reject quantification itself—statistics on the signal side, observation of output patterns, and quantification of proxy variables are all things the framework does. What the framework rejects is treating quantification on the signal side as measurement on the semantics side.

**Three: Cultural variables.** In social physics models, people are undifferentiated particles. The same piece of information spreads with completely different effects in the United States and in China—their diffusion equations cannot explain this difference, because the model has no place for a cultural variable. Culture is not a parameter that can be assigned a value; culture is the direction of semantic flow, and semantic flow cannot be assigned a value. The framework naturally handles this problem. Semantic flow is the cultural variable. Different semantic flows produce different filtering, different transformation, different collapse, different loops. Chinese civilization and Japanese civilization faced the same Western signal impact and followed completely different trajectories—because their semantic flows were different. Setting up conditions during modeling is setting up the direction of semantic flow; culture is not an extra variable but the condition itself.

These three differences lead to one result: social physics can describe the normal state (statistical behavior of large numbers of undifferentiated particles) but cannot describe turning points (moments when the Law of Black-box asserts itself), cannot describe cultural differences (trajectory divergences caused by different semantic flows), and cannot describe the role of individuals at critical moments (heroes, triggers). The framework can.

### 9.4 Depth Boundary: Sperber

Dan Sperber's epidemiology of representations is the existing theory closest to the framework. His core claim is nearly isomorphic to the framework's postulates: cultural representations are not replicated in person-to-person transmission but are transformed. Every transmission involves reconstruction by the receiver.

Sperber stopped at the problem of path divergence. If cultural transmission is primarily driven by cognitive attractors, then all cultures should follow similar paths—because human cognitive architecture is shared. But the facts say otherwise. Christianity followed an evolutionary path, Islam followed a lock-in path, and Buddhism followed a self-organization path. The differences among these three paths cannot be explained by cognitive attractors.

The framework continues where Sperber stopped. The framework has two tools that Sperber lacks: "semantic flow" as a process independent of signals (explaining path divergence—the same signal entering different semantic flows produces different paths) and the hard boundary between the signal side and the semantics side (capturing the phenomena of "signal unity but semantic divergence" and "signal plurality but semantic convergence," which Sperber's framework cannot distinguish).

### 9.5 Truth Boundary: Philosophy

The framework does not judge the truth or falsity of semantic content. A signal enters an individual and produces semantic flow; semantic flow collapses into output signals—the framework describes the dynamics of this process without judging whether the semantics produced along the way is "right" or "wrong." Truth-value judgment is philosophy's business. The framework's output can become material for philosophical discussion—you use the framework to describe the dynamics of a phenomenon, and philosophers can do epistemological analysis on top of that description.

### 9.6 Value Boundary: Ethics

The framework does not judge good or bad, right or wrong, should or should not. In the paper on Chinese civilization, Mao Zedong is a "hero"—functionally so, having performed the function of aligning the core semantic flow with reality. The framework does not judge the moral consequences of that alignment. In the religion paper, none of the three paths is "better." In the heliocentric modeling, the Church's suppression is not "wrong"—it is the dynamical behavior of a loop protecting itself. Value judgment is the business of ethics and of the individual's own semantic flow. The framework describes what happened; ethics judges what should have happened. The framework's output is the factual basis for ethical judgment.

---

## X. Scope and Limitations

**Applicable:** Any phenomenon in which signals reach an individual and produce semantic flow. The individual may be a person or an AI for which the Law of Black-box partially holds.

**Not applicable:** Pure signal-level problems that do not involve individuals receiving and processing signals—material strength, algorithmic efficiency, chemical reactions, the mathematical properties of supply-and-demand curves. The criterion: ask yourself—is the core of the phenomenon I am analyzing "what happens after signals reach an individual"? If yes, the framework applies. If not, do not force it.

**Limitations:** Does not address truth or falsity. Does not address values. Does not provide methods for reducing transformation. Does not predict time. Does not track individuals' inner lives. Is itself subject to the postulates—the framework's own expression is also a signal, and readers with different xin will read different semantics. The framework cannot exempt itself from its own postulates.

---

## XI. Existing Outputs

This framework has produced multiple verification papers demonstrating the use of modeling methods across different domains. The function of verification papers is to demonstrate how the toolkit is used—the framework provides modeling tools, and the verification papers show how to use these tools in different domains to set up conditions, run loops, compare against reality, and produce formalizable models. Detailed modeling in each domain awaits continued independent research.

---

## References

Shannon, C. E. (1948). A mathematical theory of communication. Bell System Technical Journal, 27(3), 379–423.

Sperber, D. (1996). Explaining culture: A naturalistic approach. Blackwell.
