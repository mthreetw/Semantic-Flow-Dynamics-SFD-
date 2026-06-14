# The Ceiling of Secular Success: Meta-Values and Civilizational Dynamics

*世俗成功的天花板——元價值觀與文明動力學*

Cheng Yu Huang / 黃正宇

2026

v3.5 (Revised: methodological statements, direction gate, independent operationalization of redundancy and M, time-varying determination, Singapore ethnic stratification test, prediction ledger)

UUID: d5c8a3f7-9e2b-4d1a-b6f4-e0a2c8d4f6b8

---

## Abstract

East Asian societies (South Korea, Taiwan, Japan, China) exhibit a highly similar set of symptoms: extreme academic pressure, extreme working hours, extremely low fertility rates, high suicide rates, and the coexistence of lying flat and involution. Mainstream academic explanations—the developmental state thesis, compressed modernity, and the Confucian culture thesis—each account for part of the trajectory but cannot explain why the Philippines and Indonesia, with similar conditions, did not follow the same path.

This paper proposes a dynamical model. Modernization itself is a positive feedback loop: work → income → improved quality of life → higher expectations → more work. Once this loop is activated, it passes through three stages (initiation → absorption → saturation), continuously absorbing all available resources—including family, fertility, health, and meaning. Whether the loop grinds people down depends on whether a transcendence mechanism exists—a signal source the loop cannot absorb, pointing to something outside the loop's operational range. Among all human institutions, only religion has its core operational logic outside the economic system's operational range—every other institution can be resolved with money; religion cannot.

Using the formal tools of Semantic Flow Dynamics, this paper defines the dynamical properties of the transcendence mechanism, constructs a formal model of the modernization positive feedback loop, derives three conditional paths (transcendence mechanism exists with direction ≠ loop, does not exist, direction = loop), and tests against empirical data from seventeen economies in East and Southeast Asia. Results: 16/17 match, zero counterexamples, plus one prospective test case (Mongolia, symptom splitting, linked to a mismatch condition via redundancy indicators). Malaysia provides the strongest single piece of evidence—same country, same institutions, Malay (Islamic) fertility rate 2.1, Chinese (no transcendence mechanism) fertility rate 0.8; Singapore replicates the same differentiation without Bumiputera affirmative action (Malay 1.58 vs. Chinese 0.83), and within the Indian community, a discriminatory test is carved out by religion.

This paper is the superordinate paper to Chinese Education Critique (Huang, 2026). The reason the four forces analyzed in the education critique can couple is that the meta-values allow it—there is nothing above secular success pressing down on it.

**Keywords:** Semantic Flow Dynamics, positive feedback loop, transcendence mechanism, modernization, involution, meta-values, fertility rate

---

## I. The Problem

East Asian societies exhibit a highly similar set of symptoms. South Korea's fertility rate is 0.72 (2023), the world's lowest, with a suicide rate of 28.6 per 100,000, among the world's highest. Taiwan's fertility rate is 0.86. Japan's fertility rate is 1.2, with examination hell (juken jigoku). China's fertility rate is 1.18, with the 996 work regime, gaokao pressure, and the lying flat (tangping) movement. Singapore's fertility rate is 1.04. Vietnam has 1.16 million people competing in the national college entrance exam, with a 30% acceptance rate; exam season is called suicide season.

Mainstream academic explanations each contribute but each has blind spots.

**The developmental state thesis** (Johnson, Amsden, Wade) explains how East Asian states drove industrialization through state power but cannot explain why the Philippines, which also experienced authoritarian development, did not follow the same path.

**Compressed modernity** (Chang Kyung-Sup) points out that East Asia traversed in decades the modernization process that took the West centuries, and that compression produces distortion. But "compression" is a speed variable, not a direction variable—why does compression result in involution rather than something else?

**The Confucian culture thesis** is intuitively the closest but too crude. South Korea's Christian population is nearly 30%, yet its involution is more extreme than Taiwan's, which has stronger Confucian coloring—a direct refutation of the simple causal claim "Confucian → involution." Vietnam has deep Confucian influence but is not part of the traditionally defined Confucian sphere, yet follows nearly the same trajectory.

The shared limitation of these explanations is that they seek causes at the institutional and cultural layers, but institutions and culture are themselves shaped by something deeper. A deeper explanatory variable is needed.

This paper's question: What determines whether a society grinds its people down in the process of modernization?

---

## II. Toolkit Declaration

This paper uses the formal-layer tools of Semantic Flow Dynamics v5.0 (40a22ff8-9d90-4e1e-82f6-1fd80917c139): primitives (individual (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Individual), stimulus (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Stimulus)), definitions (xin (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Xin), semantic flow (40a22ff8-9d90-4e1e-82f6-1fd80917c139.SemanticFlow), signal (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Signal)), postulates (the Law of Flux (40a22ff8-9d90-4e1e-82f6-1fd80917c139.FluxLaw), the Law of Black-box (40a22ff8-9d90-4e1e-82f6-1fd80917c139.BlackBoxLaw), the Law of Dissipation (40a22ff8-9d90-4e1e-82f6-1fd80917c139.DissipationLaw), the Law of Death (40a22ff8-9d90-4e1e-82f6-1fd80917c139.DeathLaw)), basic functions (filtering (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering), transformation (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Transformation), collapse (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Collapse)), and positive feedback loops (40a22ff8-9d90-4e1e-82f6-1fd80917c139.PositiveFeedbackLoop). The formal layer introduces no presuppositions from outside the framework; this paper's cycle model (M) and transcendence mechanism (T) are empirical-layer constructs, with epistemological positions stated in 3.1 and 3.4 respectively.

The framework itself contains no value judgments. But the object this paper models is a system driven by values; values in this paper are variables of the model, not footnotes. The framework provides dynamical tools; values provide conditions and driving forces (40a22ff8-9d90-4e1e-82f6-1fd80917c139.DrivingForce).

---

## III. The Model

### 3.1 The Modernization Positive Feedback Loop

Modernization is not an event; it is a positive feedback loop.

**Methodological statement.** The cycle model M is an empirical generalization of observable signal patterns, not a derivation from postulates. The framework provides two things here: a formal guarantee—the possibility of group-level positive feedback structures, their necessary instability, their impossibility of perpetual sustenance, all underwritten by the postulates; and a vocabulary constraint—this paper makes claims only on the signal side (working hours, expenditure, fertility rates, attendance rates) and does not assign values to the interior of semantic flow. Per the framework's boundary between the formal and empirical layers, M occupies the same tier as the framework's driving force (40a22ff8-9d90-4e1e-82f6-1fd80917c139.DrivingForce) list: cannot be exhaustively enumerated, does not necessarily hold, and readers may reject M without affecting the formal layer.

```
M₀: Work → Income
M₁: Income → Quality-of-life improvement (material conditions improve)
M₂: Quality-of-life improvement → Higher expectations (hedonic adaptation)
M₃: Higher expectations → More work (maintain or surpass current level)
M₄: → Return to M₀
```

Dynamical properties of the positive feedback loop:

**Self-reinforcement.** Each round of the loop raises expectations, and the raised expectations become the starting point for the next round. Expectations do not statistically regress—hedonic adaptation is a statistical regularity at the group level (independently supported by the behavioral economics literature on hedonic adaptation and the Easterlin paradox), not a formal necessity; the Law of Black-box forbids function-form claims about the next-moment direction of semantic flow, and this paper makes no such claim—every link in the loop is read as a statistical tendency, and residuals always exist. In statistical terms: yesterday's luxury is today's necessity; today's necessity is tomorrow's baseline.

**Resource absorption.** The loop operates continuously; its absorptive power expands continuously. First it absorbs leisure time, then family time, then fertility decisions, then health, then the pursuit of meaning. Every resource absorbed is converted into the loop's fuel—family time is converted into overtime, fertility is postponed to maintain competitiveness, health is consumed to sustain output.

**Irreversibility.** Absorbed resources do not spontaneously release. Once the fertility window has passed, it has passed; once health is consumed, it does not spontaneously recover; once the sense of meaning is displaced, it does not spontaneously return.

In the framework's language: the modernization positive feedback loop is a group-level positive feedback structure. Large numbers of individuals' semantic flows operate in similar signal environments—the visible returns of income growth constitute high-frequency signals, reinforcing the "more work → better life" direction of semantic flow; output signals (consumption behavior, work investment, educational investment) further reinforce the signal environment; the loop sustains itself.

**Activation condition: Absorption of redundancy.** An individual's time and energy are not entirely available for work. Family, children, parents, neighbors, social obligations—these occupy the individual's resources and prevent the individual from pouring everything into the loop. These are redundancy. Modernization is a value transformation: it tells you these things are burdens—discard them, go to work, exchange for money, buy efficiency. This value transformation takes time. While the transformation is incomplete, an individual who cannot cope can fall back on tradition—go home for meals, parents catch you, neighbors support you. Once the transformation is complete, the fallback disappears—the traditional safety net no longer exists; if you cannot adapt, you are discarded; if you cannot cope, you die. The full activation of the positive feedback loop is not the moment modernization begins, but the moment redundancy has been absorbed and the fallback has disappeared.

### 3.2 Stages of the Loop

The modernization positive feedback loop does not operate at full speed immediately upon activation. It has stages, and stages determine how symptoms manifest.

**Initiation.** The loop begins operating, but traditional redundancy is abundant. Family structures are intact, community networks function, and individuals who cannot cope have a fallback. The loop's direction is already set (secular success begins to occupy the top of the value hierarchy), but its absorptive power has not yet fully deployed. Symptoms have not yet fully manifested—academic pressure may already be in place, but suicide rates are still low, because the fallback remains. Vietnam is currently at this stage.

**Absorption.** The loop absorbs redundancy on a large scale. Family structures erode (nuclear families replace extended families), community networks dissolve (urbanization), and traditional safety nets gradually disappear. Symptoms begin to appear on a large scale, but some individuals can still survive on residual redundancy. China is currently in the transition zone from this stage to the next—the 996 regime is already widespread, but fallbacks such as the rural hometown and parental support have not yet fully disappeared.

**Saturation.** Redundancy is exhausted; fallbacks disappear. The loop operates without resistance, absorbing all available resources. Full-blown symptoms erupt: extremely low fertility, high suicide rates, pervasive psychological crisis. South Korea and Japan are at this stage.

**Compression effect.** Stages can be compressed. The more drastic the compression, the more violent the symptoms—because individuals cannot build new coping mechanisms before the old ones are destroyed. But compression is not uniform: the loop may advance along some symptom channels first (educational competition, unemployment, suicide) while redundancy reserves still protect other channels (family, fertility). Mongolia is a typical case of symptom splitting (see 4.2 Mongolia note).

The stage concept resolves a determination problem: Vietnam's academic pressure is already in place but its suicide rate is still low—not because the pressure is low, but because it is still in the transition from initiation to absorption; the direction is set, but redundancy has not yet been exhausted.

**Independent operationalization of redundancy.** Stage theory has a loophole that must be plugged. If "what stage a society is at" is determined by reverse-engineering from symptoms (symptoms incomplete → therefore at an early stage → therefore incomplete symptoms are normal), then any counterexample can be digested by "still on the way," and stage theory degenerates into an unfalsifiable free parameter. The fix: stages must be determined by variables other than symptoms. The definition of stages is already redundancy reserves—how much fallback remains—so measure the fallback directly.

The fallback consists of two things, each corresponding to a ready-made indicator. First, whether anyone catches you when you fall: the Gallup World Poll annually asks respondents in each country "If you were in trouble, do you have relatives or friends you can count on to help you whenever you need them?" (yes/no, national average, published in the World Happiness Report)—a direct measure of the safety-net mechanism. Second, whether parents' home is still there: the multigenerational co-residence rate (UN Database on Household Size and Composition)—if co-residing, one can retreat when unable to cope; if separated, the fallback physically does not exist.

Usage rule: whenever making a stage determination for a case, the case's redundancy indicator values must be listed first, and the determination is permissible only if the indicators support it; stages may not be reverse-engineered from missing symptoms.

Filled values (Gallup social support, most recently available year). First the comparison rule: this question asks whether someone **can** help you; the answer simultaneously depends on the willingness and the resource capacity of the kinship network—in impoverished societies, relatives are willing to help but cannot afford to, depressing the value without implying the absence of family safety-net structure. Therefore a single cross-development-level ranking is meaningless; the correct usage is horizontal comparison within comparable clusters, and tracking changes over time within the same country. The cluster on which the redundancy gate does explanatory work is "T ineffective and M operating"—intra-cluster values: Mongolia 0.938 (reaching 0.951 in 2022, the world's highest tier, on par with Iceland) > Japan 0.842 > Hong Kong 0.803 > China 0.797 > South Korea 0.759 (OECD same-source data, 2016)—South Korea is at the bottom of its cluster, consistent with its most severe symptoms; Vietnam pending, expected higher than China. Values for T-effective, low-M societies are listed for reference (Indonesia 0.781, Malaysia 0.779, Cambodia 0.738, Myanmar 0.685, Laos 0.678) but do not participate in the redundancy-gate argument—in these societies low involution is borne by T and low M, and their values are contaminated by the poverty-end capacity-depression effect and are not directly comparable with wealthy societies. Directional evidence on multigenerational co-residence: in Japan, Taiwan, and South Korea, intergenerational co-residence has continuously declined; in China and Vietnam, decades of rapid development have left intergenerational co-residence nearly unchanged; in Vietnam's 2020 national survey, only 8.6% of elderly live alone, and 74% wish to live with or near their children; South Korean households containing elderly average only 2.4 persons, among the lowest globally.

The discriminatory power of this measurement can be demonstrated with two endpoints: Mongolia 0.94 versus South Korea 0.76—both societies in which the transcendence mechanism has been destroyed or is ineffective, with fertility rates of 2.7 and 0.7 respectively. The difference is not in T (neither has it), but in fallbacks: one society in which everyone knows that in the worst case they can return to the steppe and herd livestock without starving, and another society in which falling has no bottom. The redundancy indicator quantifies this difference. This paper expects the redundancy ranking to be Vietnam > China > South Korea, consistent with the stage determinations for these three.

This operationalization simultaneously generates a falsification condition: if a society's redundancy dual-indicators are already below the East Asian mean (fallbacks have disappeared) while involution symptoms persistently fail to appear, stage theory is wrong and 3.2 must be revised (see Section VII ledger F5).

**Independent operationalization of M strength.** Likewise, "the loop has not activated" (North Korea, Mao-era China) cannot remain at the narrative level. M.strength is proxied by three ready-made statistics: gross tertiary enrollment rate, private education expenditure as a share of total, and wage employment as a share of the labor force. The determination rule is the same as for redundancy: fill in the indicators first, then make the "M not activated" determination; each use of this exemption must be accompanied by indicator evidence.

### 3.3 The Internal Logic of the Loop: Why It Does Not Stop on Its Own

A positive feedback loop has no built-in stopping mechanism.

Material needs have a saturation point—once you are full, you do not want to eat more. But what the modernization loop drives is not material needs themselves but the continuous rise of expectations. Expectations are the direction of semantic flow, not material conditions—semantic flow has no saturation point (the Law of Flux: semantic flow operates continuously; there is no moment of stillness).

An individual can feel "enough," but this feeling is itself absorbed by the loop. You feel "enough," but your children need tutoring fees, your mortgage needs paying, your colleagues are working overtime and you fall behind if you do not—the "enough" signal is drowned by signals produced by the loop. When the individual's semantic flow encounters the loop's signal environment, the result of filtering (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering) is: resistance (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Filtering.Resistance) to "enough" is extremely high; resistance to "not enough" is extremely low.

The loop does not stop on its own. It stops only in two cases: being interrupted by an external force, or running out of fuel and collapsing. Lying flat is collapse, not stopping—after being ground down, a person exits the loop, but the loop itself keeps running, merely with less fuel from that one person.

Framework alignment note: "does not stop on its own" means the loop has no built-in braking mechanism; it does not claim permanence. The framework's instability (40a22ff8-9d90-4e1e-82f6-1fd80917c139.Instability) corollary (the Law of Flux + the Law of Black-box + the Law of Death) guarantees that residuals within the loop accumulate continuously—no positive feedback loop can be permanently sustained; only the duration of its persistence varies. This section claims the absence of braking; the framework guarantees the impossibility of permanence; the two are compatible.

### 3.4 The Transcendence Mechanism

**Methodological statement.** This paper does not study religion. This paper studies whether the modernization positive feedback loop is interrupted, by what, and the dynamical mechanism of the interruption. Empirically, the things capable of interrupting the loop are almost always religious in nature—this is an empirical finding, not a definitional presupposition. The definition of the transcendence mechanism is purely dynamical: a signal source the loop cannot absorb. Whether it happens to be Islam's Five Pillars or some non-religious institution, the framework does not care. The framework cares only about three operational questions (see the judgment procedure below).

**Definition.** The transcendence mechanism is a signal source the loop cannot absorb. It points to something outside the loop's operational range—the loop cannot convert it into fuel; upon encountering it, the loop can only stop.

**Why almost only religion can do this.** The modernization positive feedback loop absorbs resources by converting them into variables within the economic system. This means: anything whose operational logic is within the economic system can ultimately be absorbed by the loop. Laws can be lobbied to change, circumvented, or purchased. Unions can be bought out or dismantled. Welfare systems run on tax revenue and can be cut under fiscal pressure. Labor laws can be gutted by the 996 regime. The operational logic of these institutions is resource allocation, and what the loop does best is reallocate resources—money can enter the operational logic of these institutions and convert them into fuel or bypass them.

Religion is different not because it is "nobler" but because its operational logic is not within the economic system. You cannot buy "the state of your soul before Allah" with money. You cannot substitute overtime for fasting. You cannot cancel the Sabbath with GDP growth. The loop encounters it and finds no conversion path—the two sides do not use the same currency. This is the structural foundation of inabsorbability: among all human institutions, only religion has its core operational logic outside the economic system's operational range.

**Clarification for readers familiar with Chinese folk religion.** The transcendence mechanism as used in this paper is not the folk worship (baibai) of the Chinese religious landscape. Baibai is a transaction—I burn incense, the god blesses me, if it works I keep praying, if it does not work I switch to another god. The operational logic of this religion is demand-driven; the loop can absorb it, because both sides use the same logic. The supernatural is not the same as transcendence. Praying to Mazu for wealth is supernatural but not transcendent—its direction points within the loop.

What this paper calls a transcendence mechanism is the kind of thing before which the very top of the secular power hierarchy must bow. European kings knelt before God. Indonesia's prime minister stops a meeting mid-session because prayer time has arrived and kneels to pray. This is not personal piety; it is structure—the ceiling of secular success is not an abstract concept but a physical action. When the most powerful people in a society must stop before something, that something is not within the loop's operational range. The loop cannot absorb it, because the loop's strongest forces (power, money) encounter it and must stop.

Dynamical properties:

**Inabsorbability.** The loop absorbs resources by converting them into variables in the "more work → better life" equation. Family can be converted (investing in children's education → future income), health can be converted (the body is a tool for work), but "the state of your soul before God" cannot be converted into a function of income. It is outside the loop's operational range. The loop encounters it and has no available conversion path.

**Periodicity.** The interruption effect of the transcendence mechanism depends on frequency. Islam's Five Pillars are performed five times a day—five times a day, pulled out of the loop, reminded that "your relationship with Allah is more important than your work." Catholic Mass is once a week. Theravada Buddhism's monastic tradition is at least once in a lifetime, with low-frequency daily temple interaction. The higher the frequency, the more frequently the loop is interrupted, and the lower the degree to which resources are absorbed.

**Directionality.** Whether the transcendence mechanism truly interrupts the loop depends on whether its direction differs from the loop's direction. If the transcendence mechanism's signal direction coincides with secular success—"believe in God and you will get rich" (prosperity theology), "striving for the nation is the highest value" (state ideology occupying the transcendence position)—then it not only fails to interrupt the loop but provides additional driving force for it. This is worse than the absence of a transcendence mechanism—because it simultaneously occupies the transcendence position (preventing genuine transcendence signals from entering) and bestows transcendence authority upon secular competition.

**Costliness.** Interrupting the loop is not free. When the transcendence mechanism pulls people out of the loop, the loop has less fuel, and secular efficiency declines. This is a structural trade-off, not a side effect. The Islamic world pays for the Five Pillars with productivity loss—stopping everything five times a day to pray is a direct sacrifice to the modernization loop. The Philippines pays for Catholicism with lagging economic development and large numbers of overseas foreign workers. If you want the ceiling to press down on secular success, you must bear the consequences of secular success being pressed down.

This is the logic of sacrifice. Every society is sacrificing—the question is to whom. You sacrifice modernization efficiency to the transcendence mechanism: the loop is interrupted, people are not ground down, but the economic cost is real. You sacrifice the transcendence mechanism to the modernization loop: the loop runs without resistance, the economy grows rapidly, but people are ground down. You cannot give to neither—the loop and the transcendence mechanism compete for the same resources (people's time, energy, attention); no equilibrium exists in which neither side pays a cost. You cannot cheat—prosperity theology tries to have both ("believe in God and you will get rich"), with the result that it sacrifices transcendence (the direction is twisted) without interrupting the loop, and the cost is borne by believers. You cannot play both sides—the failure of Korean Christianity lies precisely here, but the precise form of the failure must be stated clearly: Korean churches do not extract a low cost (5 a.m. morning prayer services, strict tithing norms, multiple weekly gatherings); the problem is the direction of the cost—the content of prayers points toward loop objectives (the 100-day gaokao prayer meeting as the iconic form), morning prayers do not reduce tutoring hours, tithes do not squeeze educational arms spending, and the cost is converted into investment in the loop rather than deducted from secular success. The precise form of the sacrifice criterion is thus established: not "whether it exacts a cost" but "whether the cost it exacts is deducted from secular success." A mechanism that channels religious fervor into competition is an afterburner for involution, not a ceiling.

**Judgment procedure.** For any concrete case, determining the state of the transcendence mechanism requires answering only three operational questions:

One: **Is there a signal source periodically pulling people out of the loop?** Islam's Five Pillars: yes, five times a day. Catholic Mass: yes, once a week. Taiwanese baibai: no—the timing and frequency of baibai are individually flexible, do not constitute systematic interruption, and individuals can choose not to attend with no social consequences.

Two: **Does the direction of this signal point outside the loop?** Ramadan—stopping work to face Allah: yes, direction ≠ loop. Prosperity theology telling you that believing in God will make you rich: no, direction = loop. Praying to Mazu for wealth: no, direction = loop.

Three: **Can the loop convert it into its own fuel?** The Five Pillars cannot be skipped for overtime (community pressure and religious obligation): cannot be absorbed. Japanese shrine visits can be compressed to once a year at New Year: can be absorbed. Funerary Buddhism appears only when someone dies: has already been absorbed.

The answer combinations correspond to three path states. All three affirmative (periodic interruption exists, direction ≠ loop, cannot be absorbed): the transcendence mechanism exists and is effective. Any one negative: does not exist or is ineffective. Direction = loop: Path Three.

**Why East Asian faith traditions do not constitute transcendence mechanisms.** East Asia is not without faith; rather, the major faith traditions of East Asia fail all three operational questions.

Japanese Buddhism (Mahayana): After the Meiji Restoration, the temple system was co-opted as part of the state apparatus; monks were permitted to marry, eat meat, and inherit abbotships. Today, the primary function of Japanese Buddhism is funerals (funerary Buddhism)—it appears only when someone dies and does not constitute periodic interruption. The loop absorbed it long ago as a service industry.

Japanese Shinto: Shrine visits are individually flexible—one may go once a year (hatsumode) or not at all. The content of visits is mostly secular wishes (passing exams, business prosperity, traffic safety). Direction = loop, and it does not constitute systematic interruption.

Taiwanese folk religion: The frequency of worshipping Mazu or the Earth God varies by individual and does not constitute institutionalized periodic interruption. More importantly, the core content of faith is the supernaturalization of secular needs—praying for safety, for wealth, for exam success. This is not a signal pointing outside the loop but a use of supernatural form to express needs within the loop. The loop can not only absorb it but convert it into fuel ("praying for exam success" directly serves academic competition).

Confucianism: Whether Confucianism possesses transcendence has been debated in academia for decades ("heaven," "the Way," "benevolence"—do these point beyond the secular?). This paper does not enter this debate. This paper's criterion is dynamical: Confucianism has no institutionalized high-frequency interruption practice (no Mass, no Five Pillars, no Sabbath); the individual's daily life is never periodically pulled out of the loop by any Confucian mechanism. Moreover, Confucianism's core values (cultivating the self, regulating the family, governing the state, bringing peace to all under heaven) are highly coincident with secular success in their dynamical effect—it does not interrupt the loop; it provides moral legitimacy for the loop.

China's situation is more complex: Buddhism, Daoism, and folk religion all historically existed, but the consecutive shocks of the twentieth century (the May Fourth Movement's anti-traditionalism, the Cultural Revolution's systematic destruction, and the post-reform materialism) cleared away whatever residual transcendence mechanisms remained. China's current ideology ("the China Dream," "the great rejuvenation of the Chinese nation") occupies the transcendence position, but its direction = loop—state objectives and secular success coincide. This is Path Three.

### 3.5 Three Conditional Paths

Three paths are derived from the model:

**Path One: Transcendence mechanism exists, direction ≠ loop direction.**

The modernization positive feedback loop is running, but the transcendence mechanism periodically pulls individuals out of the loop. The individual's semantic flow is acted upon simultaneously by two sets of signals—loop signals ("work → income → better life") and transcendence signals ("your value does not lie in how much you earn"). The frequency and intensity of the transcendence signal determine the degree of interruption. The loop is not destroyed but constrained—it cannot absorb all resources and cannot occupy the highest position in the value hierarchy.

Prediction: Low involution. Higher fertility. Lower suicide rates. Academic pressure does not constitute society-wide crushing.

**Path Two: Transcendence mechanism does not exist.**

The modernization positive feedback loop is running, with nothing interrupting it. Secular success occupies the highest position in the value hierarchy by default. The loop runs without resistance, absorbing all available resources. In societies with exam-screening traditions and industrialization pressure, the loop couples with the education system, producing signal matching (a3d7c1e5-4b8f-4e2a-9c6d-f0b5a8d2e7c4.SignalMatching)-type positive feedback (see the four forces (a3d7c1e5-4b8f-4e2a-9c6d-f0b5a8d2e7c4.ForceCoupling) coupling analysis in Huang, Chinese Education Critique).

Prediction: High involution. Extremely low fertility. Higher suicide rates. Academic pressure constitutes society-wide crushing.

**Path Three: Transcendence position is occupied, but direction = loop direction.**

The transcendence position is not empty, but the signal occupying it coincides in direction with secular success. Prosperity theology: "God wants you to succeed; your success is evidence of being blessed"—secular success is sacralized, and the loop receives the authoritative endorsement of transcendence. State ideology occupying the transcendence position: "Striving for the nation/revolution is the highest value"—secular competition is transformed into patriotic duty.

The dynamical effect is worse than Path Two. In Path Two, the transcendence position is empty, and there is at least the possibility of it being filled—new transcendence signals can enter. In Path Three, the position is occupied, and new transcendence signals encounter higher resistance—something is already sitting there, and it must be removed before entry. Moreover, the occupant wraps secular competition in the language of transcendence, making it harder for individuals to recognize the nature of the loop.

Prediction: Highest involution. All symptoms more severe than Path Two.

### 3.6 Formalization

```
Modernization Positive Feedback Loop M:
  M: Work → Income → QoL↑ → Expectation↑ → Work↑ → ...
  Properties: self-reinforcing, resource-absorbing, irreversible

  M.stage ∈ {Initiation, Absorption, Saturation}
  M.stage determined by redundancy reserves:
    Initiation: redundancy ample, fallback exists
    Absorption: redundancy being absorbed on large scale, fallback disappearing
    Saturation: redundancy exhausted, fallback gone, loop runs without resistance

Transcendence Mechanism T:
  T is a signal source satisfying: ¬∃ conversion path such that T → variable of M
  (the loop cannot convert T into fuel)

Condition variables:
  T.existence ∈ {exists, does not exist}
  T.direction ∈ {≠M, =M}
  T.frequency: interruption frequency (times/unit time)
  T.intensity: signal strength (social binding force, operationalized as
    "social consequences of absence";
    cross-country measurement via Pew religious commitment index:
    religious importance + attendance frequency + prayer frequency + belief certainty)

Direction gate (v3.5 patch):
  T.gate = 1 if T.direction ≠ M, else 0
  T.effective = T.gate × T.frequency × T.intensity
  Direction is a gating variable, not a continuous variable—Korea proves that
  frequency × intensity, however high, yields zero protection when direction = loop
  (gate closed); Thailand proves that protection attenuates when the gate is open
  but frequency is low. The two are control groups on two axes of the function.

Path function:
  If T.existence = exists ∧ T.direction ≠ M:
    → Involution = f(M.strength / T.effective)
    → Involution positively correlated with M strength, negatively with T effective strength

  If T.existence = does not exist ∨ T.effective = 0 (gate closed, no amplification):
    → Involution = f(M.strength)
    → Involution limited only by M's own conditions (development level, demographic structure, etc.)

  If T.existence = exists ∧ T.direction = M:
    → Involution = f(M.strength × T.amplification)
    → T accelerates M; involution exceeds Path Two
```

### 3.7 Interface with the Education Critique

This model is the prequel to the Chinese Education Critique (Huang, 2026).

The education critique analyzed how the four forces of the Chinese education system (industrial standardization, Confucian culture, imperial exam residue, poverty) couple at the point of signal matching, and how S₁ (a3d7c1e5-4b8f-4e2a-9c6d-f0b5a8d2e7c4.S₁; economic demand) and S₂ (a3d7c1e5-4b8f-4e2a-9c6d-f0b5a8d2e7c4.S₂; power demand) drive the loop.

This paper answers the more upstream question: Why can the four forces couple? Why does S₂ have no ceiling?

Answer: Because the transcendence mechanism does not exist. The coupling point of the four forces is secular success (signal matching → credentials → income), and the precondition for coupling is that secular success occupies the highest position in the value hierarchy. If a transcendence mechanism presses down from above, secular success cannot occupy the highest position, and the coupling force of the four forces is weakened. The Philippines also has poverty, a standardized American-style education system, and exams, but the four forces did not couple at the same point—because Catholic Mass pushes the rank of secular success down once a week.

The fractal root of the education critique lies here: the same meta-condition—the absence of a transcendence mechanism—projects the same structure at every level: family, school, workplace, society.

---

## IV. Empirical Verification

### 4.1 Method

Seventeen economies (six in East Asia, eleven in Southeast Asia), with two variables determined for each:

**Transcendence mechanism status.** Determined by the three operational questions in Section 3.4: (1) Is there periodic systematic interruption (institutionalized, not individually flexible)? (2) Does the interruption signal's direction point outside the loop (transcendence object vs. supernaturalization of secular needs)? (3) Can the loop absorb it as fuel (whether religious practice can be compressed, skipped, or converted into a tool serving secular success)? All three affirmative: transcendence mechanism exists and is effective. Direction = loop: Path Three. Otherwise: does not exist or is ineffective.

**Involution level.** Majority decision among three indicators: (1) Does a national high-pressure exam dominate education? (2) Is working-hour pressure significantly above the level expected for that development stage? (3) Is the suicide rate above the regional average? Two or more out of three affirmative: high involution.

### 4.2 Results

**East Asia** (T determination is a snapshot from the early 2020s; T is a time-varying variable; period determinations appear in individual notes)

| Economy | Transcendence Mechanism | Exam Pressure | Work-hour Pressure | High Suicide Rate (/100k) | Involution | Match |
|---------|------------------------|---------------|--------------------|--------------------------|------------|-------|
| South Korea | Ineffective (prosperity theology: direction = loop; mainstream churches: high frequency, same direction) | ✓ | ✓ | ✓ (28.6) | High | ✓ |
| Taiwan | Does not exist (secular projection) | ✓ | ✓ | ✓ | High | ✓ |
| Japan | Position vacant since 1946 (scarred; see note) | ✓ | ✓ | ✓ (15.3) | High | ✓ |
| China | Time-varying: suppression period / vacancy period / re-occupation period (see note) | ✓ | ✓ | ? (8.1*) | High | ✓ |
| Mongolia | Time-varying: destroyed in 1930s, partial revival after 1990 | ✓ | ? | ✓ (17.9) | Symptom splitting | Under test |
| North Korea | Direction = loop (Juche ideology) | ✗ | ✗ | ✗ | Low | ✓ |

North Korea's low involution is not because a transcendence mechanism is interrupting the loop, but because the modernization positive feedback loop itself has not activated—no market economy exists; the "work → income → quality-of-life improvement" positive feedback path is monopolized by the state. North Korea verifies the model's precondition: without a modernization positive feedback loop, involution cannot occur. Per the rule of Section 3.2, this exemption is accompanied by M indicator evidence: wage employment and private education market, both proxy variables, approach zero.

*China's suicide rate of 8.1 per 100,000 (WHO 2019) is subject to serious underreporting. Rural suicides go unreported, deaths from psychiatric illness are not classified as suicide, and local governments have incentives to depress figures—all are known data-quality problems. Juxtaposing this figure with the adolescent depression screening rate of 24.6% (Chinese Academy of Sciences Institute of Psychology, 2021), the adolescent non-suicidal self-injury rate of 27.4%, and the prevalence of the 996 work regime, the credibility of 8.1 is extremely low. This paper marks it with "?" in the table and does not use this datum for negative determination.

**South Korea note.** South Korea's Christian population is nearly 30%, the only case in the sample of "strong religious presence + extreme involution" coexisting; a complete determination must be given. Determination: two parts fail in two different ways, both falling at Question Two of the judgment procedure. The prosperity theology portion: faith content directly teaches that wealth is evidence of blessing—position occupied, direction = loop, Path Three (Yoido evidence in 6.1). The non-prosperity-theology mainstream churches: high frequency, high cost, but same direction—prayer content points toward loop objectives (the gaokao prayer meeting as the iconic form); the cost extracted is not deducted from secular success but injected into competition (see the sacrifice criterion in 3.4). The composite determination does not depend on proportion estimates between the two portions: regardless of what fraction of the Christian population belongs to prosperity theology, both portions are independently ineffective, and the composite is necessarily ineffective—this determination is immune to granularity. Flip condition: if a mainstream denomination appears in South Korea that demands substantive secular cost (Sabbath observance refusing overtime, tithes squeezing tutoring expenditure) and its members' involution indicators diverge significantly from the national mean, this determination must be revised. Corroboration: the youth exodus (cited literature)—young people are leaving precisely the churches that offer no interruption.

**China note (period determination).** The original binary notation "does not exist / direction = loop" has a fatal problem: both values predict the same outcome (high involution), meaning this cell can never be wrong regardless of reality—a determination that can never be wrong has no right to count as a match. The fix is not choosing one or the other, because China's T actually changed states three times over seventy years; an honest determination can only be given by period, with each period carrying its own testable prediction.

Suppression period (1949–1976). State power systematically cleared existing faiths—temples shut down, religious organizations dissolved, the Cultural Revolution's thoroughgoing destruction. This proves the "can destroy" clause of Section V: institutions can indeed destroy a transcendence mechanism. But this period had no involution, not because something was protecting people but because the loop itself was not running: a shortage economy, no labor market, no private education market, the "work → income → quality of life" chain was monopolized by the state. Per the rule of Section 3.2, this exemption must be accompanied by M indicator evidence—wage employment share and private education expenditure, both proxy variables, approach zero in this period, same pattern as North Korea. Prediction: low involution. Match.

Vacancy period (1978–2012). Marketization ignited the loop, and the transcendence position was empty—nothing had come in to fill what the previous period had cleared out. The loop therefore ran without interruption: educational arms racing, working-hour escalation, fertility-rate nosedive—all took shape and accelerated within this window, with a time trajectory consistent with the model. This period also serves an argumentative function: Section V needs a clean demonstration of "position cleared → loop runs without resistance," which Mongolia cannot provide (its loop has not fully activated), but China's thirty-four-year period can.

Re-occupation period (2012 onward). Ideology re-seated itself in the transcendence position—national rejuvenation, strong-nation objectives. But its direction coincides with secular success: striving for the nation and personal success are the same track. Direction = loop, Path Three. Note that this determination is not an empirical interpretation of propaganda content but a direct application of the structural derivation in Section V: a mechanism created by institutional power derives its authority from institutional power itself; its direction necessarily collapses back to the loop. Two predictions follow: first, involution stays locked at a high level; second, once the position is occupied, any new signal pointing elsewhere must first remove the occupant before it can enter, and resistance is structurally elevated—the observable form is pressure on the spread of foreign faiths and difficulty in institutionalizing domestic alternative signals.

Net effect: this cell goes from one that can never be wrong to three periods each breakable by data.

**Japan note (time demarcation).** Japan's determination as "position vacant" requires time demarcation. Japan from 1868 to 1945 was not position vacant but the extreme form of Path Three: the living god (arahitogami) welded the transcendence position and the apex of the secular power hierarchy into a single node—the very object identified as transcendent was itself the highest point of power within the loop; the occupant's distance from the loop was zero. Prosperity theology's object is still outside the loop (God), state ideology's object is an abstraction within the loop (the nation), the living god's object is a concrete living individual atop the loop—ranked by the occupant's distance from the loop, this is the lower bound of Path Three. This structure had its own mode of collapse built in: the god is an individual, individuals are subject to the Law of Death; and its authority came from institutional power—per Section V's derivation, the path of creation is the path of absorption—the 1946 Humanity Declaration, a single rescript, abolished divine status. The signal source personally falsified its own signal; this is structurally impossible in Abrahamic systems but built into the living-god system. Contemporary Japan is therefore not a simple Path Two: the position was cleared in 1946, but per the epistemic barrier (40a22ff8-9d90-4e1e-82f6-1fd80917c139.EpistemicBarrier) corollary of filtering, a position scorched by an extreme-form occupant carries structural scarring—when the occupant collapses, it contaminates the position itself, and societal resistance to all strong transcendence claims is structurally elevated. Postwar Japan's pervasive allergy to grand belief is not a cultural accident but the collapse cost of the previous occupant. Shrine visits compressed to once a year at New Year and Buddhism's degeneration into a funeral service industry are both the result of the loop absorbing residual religious practice under resistanceless conditions and the observable manifestation of this scar.

**Mongolia note (prospective test case).** Mongolia serves one function and is relieved of another in this framework. What it serves: historical proof of the "can destroy" clause of Section V—institutional power can destroy a transcendence mechanism (the Soviet purge of Tibetan Buddhism in the 1930s). What it is relieved of: the demonstration of "position cleared → loop runs without resistance"—that demonstration is handed to China's vacancy period, because Mongolia's symptoms are split. The shape of the split: suicide rate high (17.9, surging after the 1990s transition shock), educational competition present, university graduate unemployment rate exceeding 20%; but total fertility rate 2.7 (2023), the highest in the entire sample, well above replacement level. The model's explanation of the split is the redundancy gate: Mongolia's Gallup social support value is 0.938 (reaching 0.951 in 2022), the world's highest tier; 30–40% of the population retains a pastoral fallback, and this fallback has a record of being actually exercised—during the 1990s economic collapse, net urban-rural migration doubled, with urban populations returning to the steppe en masse to survive on livestock. In a society where everyone knows "worst case, go back to the steppe and herd sheep—you will not starve," M₄ (the cost of not running = falling) short-circuits: falling has a bottom, and the fertility and family channels are protected, while transition shock still penetrates through the suicide channel. The control group is China—equally post-socialist, equally with T destroyed, but with fallbacks sealed off by the hukou and land systems—the differentiation between the two countries isolates the redundancy variable precisely. Time-varying note: after 1990, Tibetan Buddhism partially revived; T is no longer zero. Mismatch condition (this cell's falsifiable commitment): if Mongolia's redundancy indicators (social support value, pastoral population share) are in the future found to be at East Asian-level lows while fertility remains above 2.5 long-term, this model fails in this cell. Reverse prediction: as Ulaanbaatar concentration increases and pastoral regions shrink due to climate degradation, redundancy will be exhausted, and involution indicators will rise with generational replacement (40a22ff8-9d90-4e1e-82f6-1fd80917c139.GenerationalReplacement)—the leading indicator is the emergence and expansion of a tutoring market in Ulaanbaatar. This paper does not count Mongolia as a match; it is listed separately as a prospective test case.

**Southeast Asia**

| Economy | Transcendence Mechanism | Exam Pressure | Work-hour Pressure | High Suicide Rate (/100k) | Involution | Match |
|---------|------------------------|---------------|--------------------|--------------------------|------------|-------|
| Vietnam | Does not exist (ancestor worship: flexible, direction = loop) | ✓ | ✓ | ✗ (7.5) | High | ✓ |
| Singapore | Stratified determination (see note): Malay Muslims effective; Chinese (75%) none; Indian mixed | ✓ | ✓ | ✓ (11.2) | National level high, ethnic level differentiated | ✓ |
| Philippines | Exists, ≠ loop (Catholic 85%; T time-varying, practice intensity declining, see Section VII) | ✗ | ✗ | ✗ (2.2) | Low | ✓ |
| Indonesia | Exists, ≠ loop (Islam 88%) | ✗ | ✗ | ✗ (2.2) | Low | ✓ |
| Timor-Leste | Exists, ≠ loop (Catholic 97%) | ✗ | ✗ | ✗ | Low | ✓ |
| Brunei | Exists, ≠ loop (Islam 63%) | ✗ | ✗ | ✗ (2.7) | Low | ✓ |
| Malaysia | Exists, ≠ loop (Islam 60%) | ✗ | ✗ | ✗ (5.7) | Low | ✓ |
| Myanmar | Exists, ≠ loop (Theravada Buddhism 88%) | ✗ | ✗ | ✗ (2.9) | Low | ✓ |
| Cambodia | Exists, ≠ loop (Theravada Buddhism 96%) | ✗ | ✗ | ✗ (5.3) | Low | ✓ |
| Laos | Exists, ≠ loop (Theravada Buddhism 65%) | ✗ | ✗ | ✗ (5.4) | Low | ✓ |
| Thailand | Exists, ≠ loop (Theravada Buddhism 95%) | ✗ | ✗ | ✗ (8.8) | Low | ✓ |

**Vietnam note.** The determination of "does not exist" does not rely on the "no religion 86%" affiliation statistic—that label merely reflects the statistical convention of not belonging to an organized religion, while ancestor worship permeation is extremely high in Vietnam. The determination follows the procedure: ancestor worship has high permeation but is flexible, on-demand, and carries no social consequences for absence, and its prayer direction (blessing descendants with success) = loop—same cell as Taiwanese baibai, supernatural presence but transcendence absence. The explanation for low suicide rates is no longer the narrative "still in the initiation stage" but follows Section 3.2's redundancy indicators: Vietnam's intergenerational co-residence has barely declined through decades of rapid development, only 8.6% of elderly live alone, and 74% wish to live with or near their children—redundancy reserves are significantly above the East Asian mean, and per the stage rule, symptom manifestation is predicted to lag by one to two generational phases. Academic pressure is already in place (1.16 million competing in the national exam, 30% acceptance rate, exam season = suicide season), and the loop's direction is set; majority decision among three indicators yields a high-involution determination—the direction is set, and full-blown symptoms are a stage problem, not a direction problem.

**Singapore note (stratified determination).** Singapore's population is 75% Chinese, 13% Malay, and 9% Indian, with completely different faith structures across the three groups. A whole-of-society determination is meaningless—the national fertility rate of 0.97 (2023, 2024) is essentially the Chinese-majority projection. The model's real prediction is at the ethnic level, because the transcendence mechanism takes its value at the ethnic level. The original determination of "weak (multiple faiths mutually diluting)" is abandoned: "dilution" is a mechanism that does not exist in the model; the judgment procedure's three questions cannot derive it; it merely lumped together what should have been stratified.

After stratification: 2024 Chinese 0.83, Malay 1.58, Indian 0.91 (2020: 0.94, 1.83, 0.97 respectively). Malay is nearly double Chinese. This differentiation has strong evidential force, because Singapore's Malays **do not have** Malaysia's Bumiputera affirmative action—their socioeconomic status is overall weaker, their children take the same exams, and their parents face the same pressure cooker. The only systematic difference between the two groups is that the mosque cycle is still running on one side. Read alongside Malaysia: the same Five Pillars, with policy protection on one side and none on the other, and the direction of fertility differentiation is the same. The policy explanation is now eliminated—the largest confound hanging over the Malaysia case is directly excluded by Singapore.

The Malay rate is itself declining: from 1.83 to 1.58 in four years. This is not a counterexample. The path function in 3.6 was always continuous—the effect of interruption depends on the ratio of frequency and intensity to M strength, and Singapore's M strength is the highest in the sample. The ceiling leaks under extreme pressure, same logic as Thailand; it leaks, but much more slowly than having no ceiling at all (1.58 vs. 0.83).

Empirical support on practice intensity (Pew 2023, cited literature). Muslim end: most Singaporean Muslims complete all five daily prayers, with 77% among those 35 and older and 54% among those under 35—the institutionalized high-frequency cycle is measured, not assumed. Mosque attendance is far higher for men than women, and Islamic jurisprudence precisely imposes the congregational prayer obligation only on men—attendance patterns track religious law precisely, not personal preference, indicating that the driver is obligation, not interest. Chinese end (heavily overlapping with the no-religion group): 52% never pray, only 7% pray daily. Nationally, only 36% say religion is very important in their lives—Singapore is the only country in Pew's survey round where "consider religion at least somewhat important" falls below 90%, a secularization outlier. On one side, five-times-daily discipline; on the other, a majority who never pray: two T structures, two tiers of fertility.

**Indian discriminatory test.** Indian fertility at 0.91, nearly equal to Chinese at 0.83. If religion itself protects fertility, this does not make sense—Indians are a community with deep religious traditions, yet their figure is nearly level with the most secularized group. Is this a counterexample?

No—this is a test point capable of distinguishing between two theories. The crude theory says "groups with religion have higher fertility"; this paper's claim has always been narrower—only high-frequency, costly, inabsorbable practice interrupts the loop. The two theories give different predictions for Indians, and the data can adjudicate.

First, what is inside the Indian community: 57.3% Hindu, 23.4% Muslim, 12.6% Christian (2020 census). Running Singapore-form Hindu practice through the Section 3.4 judgment procedure: temple visits are flexible, on-demand, with no mandatory collective cycle corresponding to Mass or Friday prayer, and no social consequences for absence. Practice content is syncretic—two-thirds of Singapore Hindus simultaneously pray to or pay respects to Jesus (66%) and Guanyin (62%) (Pew 2023). Wherever needed, worship goes there; this structure falls in the same cell as Taiwanese baibai: supernatural presence, transcendence absence. The model therefore predicts: in the sample's strongest M environment, Hindu fertility converges toward the no-T group—while the crude theory predicts it should be significantly higher than Chinese. 0.91 vs. 0.83—the adjudication goes to this paper.

One more cut can be made within the ethnic group. Overall Indian fertility is 0.91, of which Muslims make up 23.4%. If Indian Muslim fertility approaches Malay levels (assume 1.5), the remaining non-Muslim portion calculates to approximately 0.73; even conservatively assuming 1.3, it comes out to approximately 0.79—regardless of the parameter, non-Muslim Indians fall below Chinese at 0.83. Singapore does not publish fertility by religion; this is an arithmetic inference, not a measured value, but the direction of the conclusion is invariant under any reasonable parameter.

The value of this cut lies in its being made **within** the ethnic group. "Indian culture," "minority-group circumstances," "immigration selection"—these confounders are identical for Muslims and Hindus within the Indian community and cannot explain the differentiation between them; the only difference between them is the frequency and binding force of practice. Same ethnic group: the Five Pillars corner has fertility pushed toward Malay levels, the flexible-temple-worship majority sinks below Chinese levels—"supernatural ≠ transcendence" is thereby upgraded from a single-case argument in Taiwan to a mechanism claim with cross-national data support. This test is falsifiable: Muslim marriages in Singapore are separately registered at the Registry of Muslim Marriages; disaggregation is in principle possible; if Indian Muslim fertility is found not to exceed Chinese fertility, the frequency mechanism is falsified (Section VII ledger F2).

**Match rate: 16/17 match, zero counterexamples; Mongolia listed separately as a prospective test case (symptom splitting, linked to mismatch condition; see East Asia table, Mongolia note).**

### 4.3 Key Evidence: Malaysia

Malaysia provides the strongest single piece of evidence.

Same country, same institutions, same economic structure, same education system. Malay (Islamic) fertility rate 2.1; Chinese (no transcendence mechanism) fertility rate 0.8. A gap of 2.6 times.

This datum controls for all mainstream alternative explanations. Developmental state thesis: same country, same development model. Compressed modernity: same compression speed. Institutional thesis: same institutional set. Economic structure: same economy. The only difference is faith—Malays have a transcendence mechanism (Islam's Five Pillars, Ramadan); Chinese do not.

Institutional variable controlled; faith variable operating alone. Gap of 2.6 times.

**Handling of the confound.** Malays are protected by the Bumiputera affirmative action policy—civil service quotas, university admission quotas, corporate equity requirements—with lower economic competitive pressure than Chinese. Does this mean the fertility gap originates from differences in economic pressure rather than faith?

The task of excluding this confound is borne by Singapore (see Section 4.2 Singapore note): Singapore Malays have no Bumiputera affirmative action, are socioeconomically weaker, yet exhibit the same-direction differentiation of 1.58 vs. 0.83—the differentiation does not disappear when the policy variable is controlled; the policy explanation is eliminated.

Indian data (2023 Indian 1.6 vs. Chinese 0.8) is retained as fact but is no longer counted as evidence for the religious mechanism (v3.5 downgrade). Reason: this premium is overdetermined. Malaysian Indians are primarily plantation-worker descendants with a socioeconomic composition that pushes fertility upward along standard gradients; their ritual practice form (the kavadi penance of Thaipusam, involving real physical cost) also differs from the flexible temple worship of Singapore's professionally selected immigrant population. The religious mechanism and the composition effect cannot be separated here—compare with Singapore: Indians of the same Hindu-majority profile converge below Chinese levels in Singapore (see Section 4.2 Indian discriminatory test), showing that the label "Hindu" does not carry stable interruption power; what carries interruption power is the frequency and cost structure of specific practice. Evidence from which mechanisms cannot be separated is evidence that, by claiming it, leaves a target for opponents; discard it, and the Malaysia case is actually harder.

Furthermore, the Bumiputera policy itself does not explain direction—why does protection result in higher fertility rather than more leisure, more consumption, or some other form of resource allocation? The model's explanation: the Bumiputera policy lowers the loop's pressure (M.strength is lower), but even without this policy, Islam's transcendence mechanism would still limit the loop's absorptive power. Both factors operate simultaneously, not either-or—and Singapore provides the "without this policy" control group.

### 4.4 Thailand: A Boundary Case for the Model

Thailand is a case the model must handle with precision. Theravada Buddhism covers 95% of the population; the transcendence mechanism exists, but fertility has already fallen to approximately 1.2 (2023).

The model's explanation: the transcendence mechanism of Theravada Buddhism exists but has a lower interruption frequency and intensity than Abrahamic religions. Nirvana is personal liberation, not communal obligation. There is no weekly Mass, no daily Five Pillars, no confession. The monastic tradition provides at least one deep interruption in a lifetime, but the frequency of interruption in daily life is low.

Using the model's path function: T.frequency low, T.intensity low → weak interruption effect → the absorptive power of the modernization positive feedback loop gradually penetrates. Thailand's fertility decline trajectory is slower than East Asia's (the ceiling is leaking, but more slowly than when it is empty), consistent with the model's prediction.

Thailand does not refute the model; rather, it verifies that the effect of the transcendence mechanism is continuous rather than binary—frequency and intensity determine the degree of interruption, not an on/off switch.

### 4.5 Falsification Conditions

The model's predictions can be overturned by future data:

One: If a society with a strong transcendence mechanism whose direction ≠ loop experiences persistently rising involution (e.g., the Philippines develops East Asian-style involution without a decline in Catholic faith), the model's core claim is falsified.

Two: If a society without a transcendence mechanism, under conditions where the modernization positive feedback loop is fully operating, experiences persistently declining involution not due to economic recession, the model's core claim is falsified.

Three: If the fertility gap between Chinese and Malays in Malaysia disappears without a change in the faith variable, the model's strongest evidence fails.

These three are merged under F6 in the Section VII prediction ledger. Newly added falsification conditions (F1–F5) and ongoing predictions (P1–P5) appear in Section VII.

---

## V. Constraint Hierarchy

Mainstream academia assumes a causal direction: institutions → culture → behavior. This paper does not use causation; it uses constraint: meta-values constrain the possibility space of institutions → institutions take shape within this space → behavior unfolds under institutional constraints. The arrow does not mean "A causes B"; it means "A constrains the possibility space of B."

The constraint hierarchy does not mean the lower level cannot touch the upper level. Institutional power can do three things to the transcendence mechanism: **destroy** it—the Soviet Union vis-à-vis Mongolian Tibetan Buddhism, the Cultural Revolution vis-à-vis China's residual faith traditions; **occupy** its position—Meiji State Shinto, Juche ideology, the "China Dream"; **exploit** it—establishing a state religion, co-optation, plugging religious authority into secular power. All three have occurred repeatedly in history; this paper's case table includes examples. The one thing institutions cannot do is **create** a transcendence mechanism whose direction ≠ loop. The first three are subtraction and appropriation; the fourth is addition. The asymmetry of the constraint hierarchy lies only in addition—subtraction is possible; addition is not.

The impossibility of addition is not a historical induction but a structural impossibility derived from the definition. Walking through the derivation slowly: Return to the definition—the transcendence mechanism is a signal source for which the loop can find no conversion path and cannot turn into its own variable. Now assume the contrary—some institution has created a candidate transcendence mechanism. Step one: the institution itself is within the loop's operational range; this was already argued in Section 3.4—laws can be lobbied, budgets can be cut, policies can be purchased. Step two: where does the authority of the created signal source come from? From the institutional power that created it. And institutional power is currency within the loop—it can be accumulated, traded, purchased. Step three: then the very act of "creation" is itself a ready-made conversion path—whoever created it can modify it, revoke it, price it, trade it. The loop need not struggle to find a way to absorb it; the handle for absorption was installed at the factory. Step four: check against Section 3.4's physical criterion—the transcendence mechanism requires the very top of the secular power hierarchy to bow before it, but a creator does not bow before its own creation; a ceiling one has poured oneself cannot press down on oneself. Conclusion: an institutionally created candidate mechanism necessarily fails the judgment procedure, and fails in only two ways—its direction collapses to = loop (because its authority is secular authority), or it is absorbed (because the path of creation is the path of absorption). Meiji State Shinto is the specimen of this derivation: the state creates a god, the god endorses the state, direction = loop, Path Three (for its extreme form and mode of collapse, see Section 4.2 Japan note). "Institutionally created transcendence mechanism" is an oxymoron by definition, not an empirically rare event. Precisely stated, this is a synchronic impossibility: as long as the creator is still alive, the path of creation is a live path of absorption. History contains cases of "creator perishes, creation survives"—the Brahmanical authority of Hinduism co-constructed with ancient kingship, but those polities perished long ago, the path of creation was severed by time itself, and the creation gained independent authority over millennia. Modi's Hindu nationalism attempts to re-create a "new Hinduism" and is precisely the positive specimen of this derivation: the creator is alive, authority comes from state power, direction collapses to = loop, the judgment procedure directly classifies it as Path Three. Ancient polities' creations can outlive their creators, but this is operationally useless for any existing policymaker—to create T, you must first create, then you must die, then wait a few centuries for your fingerprints to wear off.

Destruction has no corresponding impossibility. Destruction does not require a conversion path, only force—the framework's loop replacement model already includes a power-suppression clause. But the Mongolia case proves only this first thing: institutional power can clear a transcendence mechanism. Whether the loop activates after destruction depends on whether M is operating and whether redundancy is exhausted; Mongolia met neither condition—the pastoral fallback constitutes enormous redundancy reserves (social support at the world's highest tier), and after 1990, Tibetan Buddhism partially revived, leaving T no longer at zero (see Section 4.2 Mongolia note). The demonstration of "position cleared → loop runs without resistance upon activation" is borne by China's 1978–2012 vacancy period: institutional power cleared existing faiths, marketization ignited M, and all subsequent societal behavior strictly followed the new state of the meta-value layer—educational arms racing, working-hour escalation, fertility-rate nosedive. The way institutions operate is to change the value of the upper-layer variable; after the change, the system still obeys the upper-layer variable. The foundation can be blown up, but what can be built on the rubble is determined by the fact that "there is now no foundation."

The Philippines displays the same law at a different value. The Philippines shares a large number of conditions with East Asian economies: Cold War configuration, authoritarian experience, latecomer status, external pressure, American influence, standardized education, exams, poverty. But the Philippines' institutional history never included a systematic destruction of Catholicism—the transcendence mechanism is intact and present. Under this condition, all subsequently built institutions take shape within its constrained possibility space: the same educational standardization, the same exams, the same poverty, yet the four forces cannot couple, because Mass pushes the rank of secular success down once a week. What the Philippines proves is not that the transcendence mechanism is inviolable—Mongolia and the Cultural Revolution prove it can be destroyed—but the full-time-ness of its constraining force when present.

Taken together, the complete statement of the constraint hierarchy: the meta-value layer's constraint on downstream is full-time—whether the transcendence mechanism is present (Philippines), absent (China's vacancy period, Taiwan), or occupied (South Korea, North Korea, re-occupation-period China), downstream institutions and behavior organize themselves according to its current state. The institutional layer's action on the meta-value layer is half-open—it can destroy, occupy, or exploit, but cannot create. And the impossibility of creation is guaranteed by the definition of the transcendence mechanism, not dependent on induction.

Therefore, the constraint hierarchy is: meta-values (the state of the transcendence mechanism) constrain the position of secular success in the value hierarchy → constrain the institutional possibility space → constrain the shape of specific institutions → constrain the distribution of behavior (degree of involution). Institutions can reach upward, but only with subtraction; after subtracting, the accounts are still settled by the balance of the upper layer.

---

## VI. Relationship with Existing Literature

### 6.1 Weber

Weber described a breach in *The Protestant Ethic and the Spirit of Capitalism*: Calvinist predestination led believers to seek evidence of salvation in secular success. Secular success shifted from a means to an end.

Translated into this paper's model: Calvinism is an early case of transcendence mechanism direction = loop direction. The transcendence position is occupied (God), but the signal direction coincides with secular success (secular success = evidence of salvation). What Weber described is the prototype of Path Three.

Korean prosperity theology is the extreme twentieth-century development of the mechanism Weber described. Yoido Full Gospel Church—the world's largest Pentecostal church, with 870,000 members—explicitly teaches "believe in God and you will be healthy and wealthy." If Weber saw Seoul, he would recognize this as something he had described.

### 6.2 Taylor

Charles Taylor analyzed the secularization of Western society in *A Secular Age*—from a society in which "not believing in God is virtually impossible" to one in which "believing in God is merely one option among many."

This paper's model provides a comparative dimension that Taylor did not address: after secularization, what happened to the position of the transcendence mechanism? The West (especially Northern Europe) secularized, but secular humanism inherited the function of the transcendence mechanism to some extent—"human dignity cannot be reduced to economic value." East Asia's transcendence position was not emptied by secularization; it was never filled in the first place—polytheism and folk religion are projections of secular needs, not transcendence mechanisms.

### 6.3 Gauchet

Marcel Gauchet proposed in *The Disenchantment of the World* that Christianity is "the religion of the departure from religion"—Christianity's theological logic contains the seeds of secularization.

This paper's model is compatible with Gauchet's but takes a different path: Gauchet is concerned with why the transcendence mechanism recedes (the internal logic of Christianity); this paper is concerned with what happens after the transcendence mechanism recedes (the modernization positive feedback loop loses its interruption mechanism). The two can be chained together: Gauchet explains the process; this paper explains the consequence.

---

## VII. Prediction and Falsification Ledger

A model should produce falsifiable predictions. T is a time-varying variable—predictions therefore split into two layers: the horizontal layer (at the same time point, ranking of T's effective strength predicts ranking of resistance to involution) and the dynamic layer (when T's strength changes, involution indicators track its trajectory, lagged by generational units—the framework does not predict calendar time; "generation" is the native unit of the Law of Death).

**One: Secularization trend prediction.** Societies currently secularizing without a substitute transcendence framework should exhibit rising involution and accelerating fertility decline as secularization advances. The premise of this prediction has already been established on two fronts. Latin America: Pew (2026) confirms, with a decade of data, the decline of Catholicism in Latin America—consequence observation ongoing. The Philippines: identity and practice have already decoupled; 80% of the population still self-identifies as Catholic, but weekly Mass attendance fell from 64% in 1991 to 37% in 2013, and in 2023 only 38% attend religious activities weekly nationally—the judgment procedure asks about practice, not identity; the Philippines' T is sliding from "exists and is effective" toward "exists but weakened." Over the same period, total fertility fell from 2.7 (2017) to 1.9 (2022), dropping below replacement level for the first time, the steepest decline on record; Metro Manila has reached approximately 1.2. The weekly interruption, after attendance was halved, reaches only half the population—the ceiling is still there; half the people standing under it are gone. Confound disclosure: the 2012 Reproductive Health Act and pandemic effects operated concurrently; the Philippine Statistical Authority itself flagged the 2022 plunge as requiring further research on pandemic impact. This paper's claim is therefore not "T decline is the sole cause of fertility decline" but: after T declines, the Philippines will lose its differentiation from the East Asian trajectory—educational arms racing, working hours, and suicide rates will converge toward the East Asian pattern with generational replacement. No need to wait for Latin America; within the sample there is already a Catholic society in the process of secularizing. This paper sticks its neck out.

**Two: Prosperity theology penetration prediction.** Societies with high prosperity theology penetration should exhibit symptoms more severe than purely secular societies—because Path Three is worse than Path Two.

**Three: Frequency prediction (conditionalized restatement).** Horizontal layer: among societies in which T's direction gate is open, daily practice frequency (Five Pillars daily > Mass weekly > flexible almsgiving) is positively correlated with involution resistance. As of the latest data, the horizontal ranking holds: Indonesia ~2.2 > Philippines 1.9 > Thailand ~1.2. Dynamic layer: the Philippines' recent decline is faster than Thailand's; this does not constitute a counterexample to the horizontal ranking—because the Philippines' T strength itself is collapsing (see Prediction One), and this is precisely a hit for the dynamic-layer prediction. Falsifiable form: if Mass attendance remains below 40% long-term while the Philippines' involution indicators maintain significant differentiation from East Asia, the frequency mechanism is wrong.

**Four: Prediction ledger.** This model has more than a few moving parts: direction gate, frequency, intensity, M indicators, redundancy indicators, period segmentation. Having many knobs is not a sin; hiding knobs is. The following consolidates the falsification conditions and ongoing predictions scattered throughout the text into a ledger, with triggering data sources attached to each. The model's credibility is not tied to the 16/17 retrospective match but to the survival rate of the entries in this table over time.

Falsification conditions (triggering any one requires model revision or abandonment):

F1: If Mass attendance remains below 40% long-term while the Philippines' involution indicators maintain significant differentiation from East Asia—frequency mechanism wrong. [SWS annual surveys; PSA NDHS]

F2: If Singapore Indian Muslim fertility is found not to exceed Chinese fertility—frequency mechanism wrong. [Registry of Muslim Marriages + census cross-tabulation]

F3: If a mainstream denomination appears in South Korea that demands substantive secular cost (Sabbath observance refusing overtime, tithes squeezing tutoring expenditure) and its members' involution indicators diverge significantly from the national mean—the South Korea determination must be rewritten; if no divergence appears, the direction-gate hypothesis is damaged. [Statistics Korea Household Finance and Living Conditions Survey, cross-tabulated by religion]

F4: If Mongolia's redundancy indicators (social support value, pastoral population share) are found at East Asian-level lows while fertility remains above 2.5 long-term—redundancy mechanism insufficient. [Gallup World Poll; National Statistics Office of Mongolia]

F5: If a society's redundancy dual-indicators are already below the East Asian mean while involution symptoms persistently fail to appear—Section 3.2 stage theory must be revised. [Gallup + UN Household Database, full-sample annual check]

F6: The three original conditions from Section 4.5 (Philippines-type counterexample, no-T-low-involution counterexample, Malaysia gap disappearing) are merged into this number.

Ongoing predictions (the parts where the model sticks its neck out):

P1: The Philippines' involution indicators converge toward the East Asian pattern with generational replacement; if Catholic practice revives concurrently, convergence should stall. Latin American Catholic decline is in the same direction (Pew 2026 confirms premise established).

P2: Singapore ethnic fertility differentiation persists: Malay/Chinese ratio remains significantly greater than one, until and unless mosque attendance undergoes a Philippines-style collapse.

P3: Vietnam's involution symptoms (including suicide rate) become manifest as redundancy indicators decline, lagging China by approximately one generational phase.

P4: Mongolia enters the initiation stage as pastoral fallbacks shrink; leading indicator is the emergence and expansion of a tutoring market in Ulaanbaatar.

P5: China's involution stays locked at a high level during the re-occupation period; observable manifestations of elevated resistance to new-direction signals (pressure on foreign-faith propagation, difficulty in institutionalizing domestic alternative signals) persist.

---

## VIII. Boundaries

**Issues this paper does not address:**

One: The truth or falsity of the transcendence mechanism. The framework does not judge whether God exists or whether Nirvana is real. The framework addresses the dynamical effects of the transcendence mechanism, not its metaphysical status.

Two: Moral judgment of involution. This paper does not judge involution as "bad" or the transcendence mechanism as "good." What each society obtains in exchange for the transcendence mechanism (lower economic efficiency? higher social cohesion?) is a question for ethics, not for dynamics.

Three: Policy recommendations. The model describes structure, not prescriptions. "Establishing a transcendence mechanism" is not an operation executable by policy—the origin of the transcendence mechanism lies outside institutions. This item is a direct corollary of the structural derivation in Section V (cannot create), not a position statement.

Four: Time prediction. The framework does not predict the time scale of paths. Vietnam is walking the path China walked in the 1990s, but when it reaches the end, the framework cannot derive.

Five: Cross-regional comparison. This paper's comparison range is limited to East and Southeast Asia. These economies share sufficient structural conditions—Cold War configuration, latecomer development pressure, similar demographic transition stages, overlapping geography and trade networks—to allow the transcendence mechanism variable to be meaningfully isolated. Expanding the comparison range to Northern Europe, the Middle East, or Latin America would introduce large numbers of uncontrollable variables (welfare state regimes, petroleum economies, differences in colonial legacy types), actually reducing the quality of causal inference. The model's cross-regional applicability is a question for subsequent research, not a claim of this paper. The model proposes directional cross-regional predictions in Section VII, but verifying these predictions requires establishing independent control conditions for each region, which exceeds the scope of this paper.

Six: Sample independence. The seventeen economies are not seventeen independent observations. East Asian countries share a Confucian history and Cold War linkage, the four Theravada countries belong to the same civilizational cluster, the two Catholic countries share the same origin—there is phylogenetic correlation among cases (Galton's problem in cross-cultural research), and treating them as independent samples to calculate match rates inflates the evidence; the actual number of independent units is closer to four or five civilizational clusters. This is precisely why this paper places the weight of evidence on within-country comparisons (Malaysia and Singapore ethnic stratification) and the Section VII ledger: within-country comparisons are the only observations that escape phylogenetic correlation, and prospective predictions are bets unaffected by retrospective match inflation.

Seven: Self-selection. A standing rival explanation for ethnic and religious differentiation is reverse causation: individuals who value family and fertility stay in high-constraint practices, while secularized individuals leave first—religion is a sieve, not a cause. But this rival explanation presupposes individual free choice, and religion is a power structure, not a preference menu. Malays in Malaysia did not choose Islam because they value family—being born Malay means being Muslim; this is law and social structure, not the result of individual selection. Selection and interruption are not mutually exclusive rival explanations; they are two faces of the same power structure: the power structure places you inside (selection), then acts on you from within (interruption). The individual is squeezed between two power structures—the loop demands your time and energy, and religious authority also demands your time and energy—with only the freedom to choose which side to obey, not the freedom of "giving to neither." A pure self-selection explanation requires assuming that the proportion of Malays who value family is systematically far higher than among Chinese, and the most likely source of that assumption is precisely the long-term shaping of the religious power structure—trace selection to its end, and you still hit structure.

**The framework's boundary is this paper's boundary.** This paper's expression is a signal. Readers with different xin read different semantics. This paper cannot exempt itself from its own postulates.

---

## References

### Framework

Huang, C. Y. (2026). Semantic Flow Dynamics v5.0.

Huang, C. Y. (2026). Chinese Education Critique: Signal Matching and Modeling.

### Social Theory

Weber, M. (1905/2001). *The Protestant Ethic and the Spirit of Capitalism*. Routledge.

Taylor, C. (2007). *A Secular Age*. Harvard University Press.

Gauchet, M. (1985/1997). *The Disenchantment of the World: A Political History of Religion*. Princeton University Press.

Berger, P. (1967). *The Sacred Canopy: Elements of a Sociological Theory of Religion*. Doubleday.

### Developmental State and Compressed Modernity

Johnson, C. (1982). *MITI and the Japanese Miracle*. Stanford University Press.

Amsden, A. (1989). *Asia's Next Giant: South Korea and Late Industrialization*. Oxford University Press.

Wade, R. (1990). *Governing the Market: Economic Theory and the Role of Government in East Asian Industrialization*. Princeton University Press.

Chang, K.-S. (2010). *South Korea under Compressed Modernity: Familial Political Economy in Transition*. Routledge.

### Demographic and Fertility Data

United Nations, Department of Economic and Social Affairs, Population Division (2024). *World Fertility 2024*.

United Nations Population Prospects 2024 Revision.

Department of Statistics Malaysia (2023, 2025). Total Fertility Rate by Ethnic Group; Vital Statistics Malaysia.

Philippine Statistics Authority (2023). *National Demographic and Health Survey 2022*.

Singapore Department of Statistics (2025). Total Fertility Rate by Ethnic Group; Census of Population 2020 (Religion).

National Statistics Office of Mongolia. Vital statistics.

### Religious Practice Data

Social Weather Stations (2013–2023). Surveys on religious service attendance in the Philippines.

Pew Research Center (2026). *Catholicism Has Declined in Latin America Over the Past Decade*.

### Redundancy Indicator Data

Helliwell, J. F., et al. (eds.) (2024). *World Happiness Report 2024*. Statistical appendix: social support (Gallup World Poll).

United Nations, Population Division. *Database on Household Size and Composition*.

OECD (2017). *How's Life?* Social support indicator, based on Gallup World Poll.

### Hedonic Adaptation (Behavioral Economics)

Easterlin, R. A. (1974). Does economic growth improve the human lot? In *Nations and Households in Economic Growth*. Academic Press.

Brickman, P., & Campbell, D. T. (1971). Hedonic relativism and planning the good society. In *Adaptation-Level Theory*. Academic Press.

Frederick, S., & Loewenstein, G. (1999). Hedonic adaptation. In *Well-Being: The Foundations of Hedonic Psychology*. Russell Sage.

### Suicide Rate Data

OECD (2025). *Society at a Glance: Asia/Pacific 2025*. Suicide rates.

WHO Mortality Database, 2019–2021.

### Working Hours Data

ILO (2024). Modelled estimates of average weekly working hours.

World Population Review (2026). Average Workweek by Country.

### Korean Christianity and Prosperity Theology

Britannica. Yoido Full Gospel Church.

The Gospel Coalition (2019). Why Christianity Quit Growing in Korea.

Mercator (2020). Korean Christianity: Thriving in Megachurches, Deserted by Youth.

Association for Asian Studies. Christianity in Modern Korea.

### Religious Composition

Pew Research Center (2024). Religion and Spirituality in East Asian Societies.

Pew Research Center (2023). Religion in Southeast Asia.

CIA World Factbook.

### Other

Shannon, C. E. (1948). A mathematical theory of communication. *Bell System Technical Journal*, 27(3), 379–423.

Sperber, D. (1996). *Explaining culture: A naturalistic approach*. Blackwell.

```json
{
  "references": [
    "40a22ff8-9d90-4e1e-82f6-1fd80917c139",
    "a3d7c1e5-4b8f-4e2a-9c6d-f0b5a8d2e7c4"
  ]
}
```
