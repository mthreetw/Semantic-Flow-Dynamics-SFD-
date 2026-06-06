# The Information Trilemma

*資訊不可能三角*

Cheng Yu Huang / 黃正宇

Contact: mthree.tw@gmail.com / ORCID: 0009-0003-9884-7401

UUID: f7b72c81-3a1a-489b-8264-a1225dde6630

2026

---

## Abstract

Large language models (LLMs) are the first information systems in human history forced to swallow knowledge from all scales at once. Physics, biology, history, law, everyday conversation—all compressed into a single set of parameters. What humans spent millennia carefully keeping apart has been crammed into one architecture. This creates a structural predicament.

This paper proposes the Information Trilemma: in any information system, when scale remains open, logical consistency and factual truth cannot be simultaneously satisfied. The three vertices are Scale (simultaneously accommodating multiple knowledge systems, each sufficient within its own scale), Logic (internal reasoning consistency of the system), and Truth (correspondence with verifiable reality at a given scale).

The core reason the trilemma holds: the fundamental rules of reality exist across scales, logic holds across scales, but specific phenomena are combinatorial manifestations of rules at particular scales, and these combinatorial manifestations change with scale. This is an observed fact, independent of contested concepts like emergence, chaos, or nonlinearity—the scale-dependence of rule weights alone is sufficient to establish the trilemma. The trilemma's constraint lies not at the rule layer (a unified field theory, even if successful, would not affect the trilemma) but at the combinatorial layer from rules to phenomena. The trilemma does not explain the nature of scale, nor does it explain why combinatorial manifestations change with scale, just as the second law of thermodynamics does not explain why entropy increases—it starts from observed facts and derives constraints.

LLM training data naturally spans a vast number of scales, turning the trilemma from a theoretical issue into an engineering reality. Hallucination is not a bug; it is a structural cost. But "structural" does not mean "unimprovable"—RAG, tool use, and domain fine-tuning all trade scale for compatibility between logic and truth, the same strategy humans use by dividing knowledge into disciplines.

This paper positions itself as a rule of thumb, not a formal theorem, belonging to the family of the CAP theorem and the Mundell-Fleming trilemma, but grounded in observed facts rather than formal proof. Its value lies not in proving impossibility, but in providing a trade-off framework for data cleaning and model design: you are choosing two out of three—decide which one you are willing to sacrifice this time.

**Keywords:** Information Trilemma, scale, combinatorial manifestation, LLM, data cleaning, hallucination

---

# Part I: The Problem—LLMs Ingest All Scales

---

## 1. LLMs Are the First System to Actually Do This

Human knowledge systems have always been separated. Physics is divided by scale, medicine by specialty, engineering by domain, history by period. Every functional knowledge system first draws a circle and says "I only handle what's inside here," and only then can it achieve logical self-consistency and correspondence with reality.

Science is divided into disciplines not because humans are too lazy to unify, but because each discipline is an operation that locks in a particular scale. The moment you attempt unification—grand unified theory, theory of everything—decades pass without success, because you are trying to reopen scale while preserving both logic and truth.

LLMs broke this convention. To train a large language model, you feed it Wikipedia, textbooks, papers, news, forums, code, legal documents, and novels all at once, learning through a single unified architecture. This is not a design choice; it is a practical necessity—to enable the model to answer any question, you must feed it data from all domains.

The result: what humans spent millennia carefully keeping apart has been crammed into a single set of parameters.

---

## 2. Proposition

**In any information system, when scale remains open, logical consistency and factual truth cannot be simultaneously satisfied.**

This is not an engineering "haven't achieved it yet" but a structural "cannot achieve it"—as an observed structural constraint, not a proven impossibility. The limitation comes not from insufficient resources or poor algorithms, but from the structure of the input data itself.

---

## 3. Definitions

**Scale:** Simultaneously accommodating multiple knowledge systems, each sufficient within its own scale.

Scale is not data volume. Knowledge at each scale has its own combinatorial logic, its own empirical parameters, its own emergent phenomena, requiring sufficient volume to support effective operation. Greater scale means simultaneously maintaining more local knowledge systems. For LLMs, scale is the knowledge diversity of training data—how many different scales it spans.

**Logic:** Internal reasoning consistency of the system. A single set of reasoning rules that remains non-contradictory throughout.

**Truth:** Correspondence with verifiable reality at a given scale. The ability to match actually observable outcomes.

**Knowledge:** Structured understanding of reality, recorded as text, at a given scale. Knowledge is not reality—it is a lossy record of reality (see Section 10). For LLMs, knowledge is the text in training data.

---

# Part II: Core Argument—Combinatorial Manifestations Change with Scale

---

## 4. Logic Crosses Scales, Rules Cross Scales, Combinatorial Manifestations Do Not

Logic crosses scales. The four fundamental forces also cross scales—gravity, electromagnetism, the strong nuclear force, and the weak nuclear force exist at all scales. The rules have not changed; logic has not changed.

But any specific phenomenon in reality is not the expression of a single rule, but rather the combinatorial effect of multiple rules at a particular scale. Why is water liquid—electromagnetism dominates intermolecular interactions, quantum mechanics determines electron orbitals, gravity is negligible at this scale. On a neutron star, the same four forces are present, but the weights of the strong nuclear force and gravity are entirely different, and matter behaves entirely differently.

The rules have not changed, but the weights of the rules change with scale. The way they combine changes with scale.

Truth is not rules. Truth is the combinatorial manifestation of rules at a particular scale. This combinatorial pattern cannot be logically derived from the rules themselves; it can only be observed from reality at that scale.

---

## 5. This Argument Does Not Depend on Emergence

Combinatorial manifestations change with scale without needing emergence to explain them. The field structures of electromagnetism and gravity are nearly identical (both follow inverse-square laws), but electromagnetism is roughly 10³⁶ times stronger than gravity, and electric charges cancel while mass does not. These two simple differences alone are sufficient for the same rules to produce entirely different combinatorial manifestations at atomic and celestial scales.

No chaos is needed, no nonlinearity, no complex systems theory. That combinatorial manifestations change with scale is an observed fact more fundamental than emergence. Emergence is an aggravating condition—it makes the differences in combinatorial manifestations more dramatic—but it is not a necessary condition. Even without emergence, the scale-dependence of rule weights alone establishes the trilemma.

This matters because emergence itself is a contested concept—some argue that emergence can be reduced. The trilemma does not need to fight that battle. Its premises rest solely on observed facts that no one can deny.

---

## 6. Combinatorial Manifestations Differ Across Scales—Why the Trilemma Holds

Combinatorial manifestations differ across scales. This is not a theory; it is an observed fact. The trilemma does not need to explain why they differ, only to observe that they do differ. Nor does the trilemma need to define the nature of "scale"—just as the second law of thermodynamics does not need to explain why entropy increases, it observes that entropy does increase, then derives from this fact. This is a deliberate boundary drawn by this paper, not an omission.

Derivation path:

First, truth at scale s₁ depends on the combinatorial manifestation of rules at s₁, C(s₁).

Second, truth at scale s₂ depends on the combinatorial manifestation of rules at s₂, C(s₂).

Third, C(s₁) ≠ C(s₂). This is an observed fact.

Fourth, a logical framework built at s₁ is based on C(s₁); when applied to s₂, because C(s₂) ≠ C(s₁), correspondence with truth at s₂ is not guaranteed.

Fifth, when scale simultaneously covers s₁ and s₂, unified logic and truth at all scales cannot be simultaneously satisfied.

For LLMs, this means: training data contains knowledge from s₁ and s₂ and s₃ through sₙ simultaneously, each true within its own scale, but logically incompatible with one another. A model fitting all this data with a single set of parameters must sacrifice either logical consistency or factual truth somewhere.

---

## 7. Distinguishing from Unified Field Theory

The trilemma does not claim that rules cannot be unified; it claims that combinatorial manifestations cannot be captured by a unified logical framework.

Even if someone discovers a unified field theory tomorrow, a single set of equations elegantly unifying all four forces, using those equations to calculate the boiling point of water still requires plugging in scale-specific boundary conditions, making approximations, and choosing cutoffs. The equations are unified, but the path from equations to specific phenomena differs at every scale. Unified field theory unifies rules, not combinatorial manifestations.

The trilemma's constraint lies not at the rule layer but at the combinatorial layer from rules to phenomena. Even with rules unified, combinatorial manifestations still cannot cross over. This means the trilemma's constraint is deeper than intuition suggests.

---

## 8. The Trilemma's Self-Reinforcement

Knowledge within each scale can be individually logically perfect. Planetary mechanics is self-consistent; quantum mechanics is self-consistent. But the moment you cross scales, logical consistency breaks—at a black hole's singularity, general relativity says density is infinite, quantum mechanics says this is meaningless. Both are correct; combined, they contradict.

The more complete and rigorous the knowledge at each scale, the sharper the logical conflicts when crossing scales. Because each has developed a complete system, and these systems' foundational assumptions do not hold at another scale.

The better each of two vertices performs, the more thoroughly the third collapses.

For LLMs: the higher the quality of training data and the more complete the coverage of each domain, the more obvious the contradictions become when reasoning across domains. The problem is not bad data; it is that good data inherently fights with other good data.

---

# Part III: The Specific Toxicity of Training Data

---

## 9. Every Domain's Data Carries Its Own Poison

LLM training data comes from all domains, and every domain's data carries cross-scale contradictions—not a data quality issue, but a structural feature of reality itself.

Physics data contradicts itself across scales. General relativity and quantum mechanics are each self-consistent but incompatible with each other. The model learns two frameworks with no data telling it when to use which—because humans themselves have not figured this out yet.

Biology data describes a system that does not pursue logical consistency. The same functional requirement has entirely different solutions across species; within a single species, every layer from genes to individuals produces emergent phenomena unpredictable from the layer below; and biological organisms are full of evolutionary residual redundancy and contradiction. Asking a model to learn logically consistent knowledge from this data is asking the data to provide something it never had.

Humanities data carries not only cross-scale contradictions but also "truths" shaped by power. The same fundamental rules (people need to eat, people die) combine into entirely different institutions across different times and places, with much of the combination actively constructed by power. The model has no ability to distinguish which are observed facts and which are narratives constructed under specific power structures.

On the surface, these appear to be different problems in different domains, but underneath lies the same structure. It is not that physics data has physics poison and biology has biology poison—there is only one poison, and each domain is merely hit by it from a different angle. The difference lies in the dimensionality of combinatorial variables: physics combinations arise naturally, with lower dimensionality; humanities combinations are shaped by power, with extremely high dimensionality. The trilemma's constraint strength increases accordingly.

---

# Part IV: Aggravating Conditions—Defects of Knowledge Itself

---

That combinatorial manifestations change with scale is a sufficient condition for the trilemma. Even if knowledge were perfectly intact, as long as combinatorial manifestations differ across scales, the trilemma holds.

But the knowledge ingested by LLMs is not perfectly intact. The defects of knowledge are observable facts, and they make the trilemma's constraint more severe.

---

## 10. Knowledge Is Lossy

Knowledge incompletely reflects reality. This is an observed fact.

The lossiness is structural: it drops time—dynamic processes become static records; it drops continuous transitions between scales—becoming discrete disciplinary systems; it drops the full detail of combinatorial manifestations—becoming empirical parameters and magic numbers; it drops context—becoming seemingly universal propositions.

LLM training data is the textualization of this lossy knowledge. Each layer drops something, but each layer's output appears complete.

---

## 11. Specific Defects of Knowledge

**Irreproducible experiments.** A large number of research results cannot be independently reproduced. The reproducibility crisis is not a problem of individual fields but a structural feature of the knowledge production process. Training a model on such data and expecting a model that satisfies the trilemma is not computation; it is wishful thinking.

**Unexplained parameters.** Practically viable theories are often filled with empirical parameters and magic numbers. An engineer tunes a working parameter; ask why 0.7 and not 0.8, and the answer is "we tried it." Papers write "empirically determined"—translated into plain language: "I don't know either, but it works." The knowledge that actually works is precisely the kind that least meets the standard of "logically rigorous."

**Contradictory conclusions.** Different studies reach opposite conclusions on the same question. Not because someone made an error, but because different experimental conditions constitute different scales, and truth at different scales can simply differ.

**Post hoc rationalization.** A large portion of theoretical explanations are found after the fact, not derived beforehand. Why this learning rate works in deep learning, why dropout is set to 0.5, why ReLU outperforms sigmoid—answers are mostly post hoc attributions, not a priori deductions.

Differences in combinatorial manifestations are a structural limitation; knowledge defects are compounding deterioration. Both act simultaneously on LLM training data.

---

# Part V: Specific Constraints on LLMs

---

## 12. LLMs Are a Recompression of Incomplete Knowledge

Knowledge already incompletely reflects reality. LLMs take this incomplete knowledge to train on, amounting to another compression on top of an incomplete foundation. Each layer drops something, but each layer's output appears complete and confident.

LLM training requires not "true and logically rigorous data" but data that covers the diversity of human language and knowledge. Scale here means not just "large volume" but "high diversity"—spanning a vast number of different scales. To cover the breadth of human knowledge, one must include data with conflicting viewpoints, imprecise logic, and even factual errors, because that is what real-world knowledge looks like.

---

## 13. Structural Limitations of the Algorithm

The current mainstream architecture (Transformer) is fundamentally computing statistical correlations, not performing logical reasoning. The "logic" it learns is patterns induced from vast amounts of text, not deductions from axioms. Statistical induction and logical deduction are two fundamentally different cognitive mechanisms; forcing one architecture to do both necessarily compromises somewhere.

Modeling can partially bypass this limitation—physics simulation, weather prediction, protein folding—these models incorporate time, causation, and continuous evolution. But all modeling is scale-bound. What modeling can do is recover causal structure within a closed system of known rules and known scale. What modeling cannot do is decide which model to use—when to use Newtonian mechanics, when to switch to relativity. This judgment is not something modeling can solve. Modeling's success is not a breakthrough of the trilemma; it is an abandonment of the Scale vertex.

---

## 14. Hallucination Is Structural, but Manageable

LLMs systematically favor the knowledge combination of "large scale, seemingly rigorous logic, but uncontrollable truth."

Because the essence of truly useful knowledge often resides in the author's feel, intuition, and failed experiments never written into papers. These things have never become text. LLMs can only learn knowledge already recorded as text, and that knowledge is already incomplete.

Meanwhile, irreproducible experimental results, overturned conclusions, and mutually contradictory meta-analyses in training data look exactly like reliable knowledge at the pure text level. LLMs have no ability to distinguish them, because distinguishing requires not text analysis but interaction with reality at a specific scale—and LLMs have only text, no world.

The hallucination problem of LLMs is not a bug. It is the inevitable cost of using a unified architecture to span incommensurable scales.

But "structural" does not mean "unimprovable." Hallucination rates have been declining. The methods of reduction precisely confirm the trilemma: humans avoid the trilemma by dividing into disciplines—constraining scale; LLMs avoid the trilemma by dividing tasks, dividing domains, adding external verification—RAG temporarily narrows scale to retrieved documents, tool use outsources truth judgments to verifiable systems, domain fine-tuning locks a general model to a specific scale. Different strategies, same logic: all trade away scale for compatibility between logic and truth.

The trilemma does not say hallucination cannot be reduced; it says every method of reducing hallucination sacrifices scale. You cannot reduce hallucination to zero while maintaining fully open scale.

---

## 15. Data Cleaning as Pick-Two-of-Three

Data cleaning is making trade-offs among the three vertices. This is the trilemma's most direct engineering application.

Tighten cleaning rules and logical consistency goes up, but you cut data that "looks contradictory but actually reflects truth at different scales." Loosen cleaning rules and diversity is preserved, but noise and contradictions enter. No set of rules can simultaneously maximize all three vertices. You are picking two out of three.

Specific pain points:

For the same medical question, a 2010 meta-analysis and a 2024 study reach opposite conclusions—which do you keep? Keep both, logical contradiction; keep only the newer one, you may lose truth that still holds under specific conditions—perhaps the 2010 conclusion remains correct for specific populations and dosages. This cannot be solved with better rules, because the definition of "better" is itself scale-dependent.

For the same engineering parameter, a textbook says use 0.7, a senior engineer's blog says use 0.85 under specific conditions. Clean out the blog and you lose practical knowledge; keep the blog and you introduce data contradicting the textbook. And what those "specific conditions" are often has never been explicitly recorded—it lives in the engineer's feel, never becoming structured text.

Cross-cultural data is even more troublesome. The same legal concept has different meanings in different jurisdictions; the same historical event has different narratives in different countries' textbooks. Whose version do you use when cleaning? Choose one and you sacrifice truth at a particular scale; keep all and logic becomes inconsistent.

The priority ordering among the three vertices is itself not fixed. A magic number in an engineer's hands is unremarkable in normal times but lifesaving when materials are scarce. Even if an information system can make trade-offs among the three vertices, it does not know which trade-off to prioritize at this moment. This judgment requires understanding the current context, and context is a scale problem, circling back to the trilemma's own constraint.

What data cleaning practitioners should take away is this: you are not looking for an optimal solution; you are picking two out of three. Think clearly about which vertex this task needs to sacrifice, then sacrifice it consciously, rather than pretending you can have all three.

---

# Part VI: Boundary Conditions and Structured Description

---

## 16. Where the Trilemma Does Not Apply

Fractals are structures where combinatorial manifestations of a single rule are the same at all scales—the same pattern repeating infinitely. Fractals can be self-consistent across scales because they trade away all complexity for scale invariance.

Geometry crosses scales, is logically self-consistent, and is true within the scope of its assumptions. Geometry can hold across scales because it does not deal with the combination of forces—it describes only the structure of space, without involving how things within that space interact.

The trilemma's applicability condition is: combinatorial manifestations change with scale. The criterion is interaction—where there is interaction, there is combination; combination may change with scale; the trilemma applies. Purely structural, interaction-free formal systems (geometry, fractals, pure mathematics) can be self-consistent across scales. Once actual phenomena are involved—physical, biological, or humanistic—the trilemma is present.

For LLMs, this draws a line: the model can be self-consistent across scales in pure formal reasoning (mathematics, logic), but once empirical knowledge is involved, the trilemma's constraint activates.

---

## 17. Structured Description

The following is not a formal proof, but a structured statement of observed facts and derivation paths.

```
Definitions:
  Scale = simultaneously covering multiple scales s₁, s₂, ..., sₘ,
          with a sufficient knowledge subset K(sⱼ) at each scale
  Logic = ∀ propositions p, q ∈ System: ¬(p ∧ ¬p)
  Truth(s) = the system's output corresponds with verifiable reality at scale s

Observed facts:
  F1: Fundamental rules R exist across scales
  F2: Specific phenomena = combinatorial manifestation of R at a particular scale s, C(R, s)
  F3: C(R, s₁) ≠ C(R, s₂) when s₁ ≠ s₂ (observed fact, independent of emergence)

Derivation:
  From F2: Truth(s) depends on C(R, s)
  From F3: C(R, s₁) ≠ C(R, s₂)
  ∴ A logical framework L₁ built at s₁ is based on C(R, s₁)
    When L₁ is applied to s₂, because C(R, s₂) ≠ C(R, s₁),
    Truth(s₂) is not guaranteed
  ∴ When Scale is open, unified Logic and Truth at all scales
    cannot be simultaneously satisfied

  Note: "not guaranteed" here is an induction from observed facts,
  not a mathematical impossibility proof.
  The trilemma is a rule of thumb, not a theorem.

Boundary condition:
  If ∀s₁, s₂: C(R, s₁) = C(R, s₂) (combinatorial manifestations do not change
  with scale, as in fractals), the trilemma does not apply

Aggravating condition (knowledge defects):
  K(s) incompletely reflects C(R, s) (observed fact)
  ∴ Even within a single scale, Logic and Truth are not guaranteed
    to be simultaneously satisfied
  Combinatorial manifestation differences + knowledge defects
  acting together make the trilemma's constraint stronger

LLM constraint:
  LLM training data D = ∪ K(sⱼ), j = 1, ..., m
  LLM fits D with unified parameters θ
  By the trilemma: no θ exists such that ∀s: Truth(s) holds under unified Logic
  Avoidance strategies (RAG, tool use, domain fine-tuning)
  = constraining Scale to achieve Logic ∧ Truth
```

---

## 18. Relationship to Other Trilemmas

Impossibility trilemmas form a family. The CAP theorem says distributed systems must choose two of consistency, availability, and partition tolerance. The Mundell-Fleming trilemma says free capital flow, fixed exchange rates, and independent monetary policy—pick two. This paper's trilemma says scale, logic, and truth—pick two.

The structure is the same: three good things cannot all be had simultaneously; the system has an inherent ceiling on trade-offs; the constraint is structural, not a resource problem.

The difference lies in the source and hardness of the constraint. CAP has a formal proof; Mundell-Fleming has model support. This paper's trilemma is grounded in observed facts; it is a rule of thumb, not a theorem. It is broader than CAP and Mundell-Fleming (applicable to all empirical knowledge systems) but softer (lacking formal proof). This paper does not pursue formal proof. The value of a rule of thumb in engineering practice does not depend on formalization—engineers need a framework to guide trade-offs, not a theorem that can pass peer review.

---

## 19. Scope and Limitations

**Applicable:** Any knowledge system in which combinatorial manifestations change with scale. LLM training data inherently belongs to this category, as it spans all empirical knowledge domains including physics, biology, and the humanities.

**Not applicable:** Pure formal systems in which combinatorial manifestations do not change with scale—fractals, geometry, pure mathematics. LLM performance in these domains is not constrained by the trilemma.

**Limitations:** The trilemma does not predict specifically where failure will occur. It does not determine the priority ordering among the three vertices—priority is scale-dependent, varying with context and supply-demand. It does not provide solutions—it describes a structural limitation, not a problem that can be overcome by engineering means. The trilemma does not explain the nature of scale, nor why combinatorial manifestations change with scale—it only observes that they do, and derives constraints from this observation. The trilemma itself is also knowledge, and is also incomplete.

---

```json
{
  "references": []
}
```
