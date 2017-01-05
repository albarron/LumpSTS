package cat.lump.sts2017.babelNet;

import java.util.HashMap;
import java.util.Map;

import it.uniroma1.lcl.babelnet.data.BabelPOS;

/**
 * Mapping PoS tags for Arabic, English and Spanish into the BabelNet tag set.
 * 
 * @author cristina
 * @since Dec 27, 2016
 */
public class PoSMaps {

	/**
	 * Mapping Arabic Mada 2.1 PoS tags into BabelNet.
	 * There is a 1-to-1 correspondence with English Penn Tree Bank
	 */
	static final Map<String, BabelPOS> BN_POS_AR= new HashMap<String, BabelPOS>(){
		private static final long serialVersionUID = 1652098132934864030L;
	    {
	    	put("noun", BabelPOS.NOUN);
	    	put("noun_num", BabelPOS.NOUN);
	    	put("noun_quant", BabelPOS.NOUN);
	    	put("noun_prop", BabelPOS.NOUN);
	    	put("adj", BabelPOS.ADJECTIVE);
	    	put("adj_comp", BabelPOS.ADJECTIVE);
	    	put("adj_num", BabelPOS.ADJECTIVE);
	    	put("adv", BabelPOS.ADVERB);
	    	put("adv_interrog", BabelPOS.ADVERB);
	    	put("adv_rel", BabelPOS.ADVERB);
	    	put("pron", BabelPOS.PRONOUN);
	    	put("pron_dem", BabelPOS.PRONOUN);
	    	put("pron_exclam", BabelPOS.PRONOUN);
	    	put("pron_interrog", BabelPOS.PRONOUN);
	    	put("pron_rel", BabelPOS.PRONOUN);
	    	put("verb", BabelPOS.VERB);
	    	put("verb_pseudo", BabelPOS.VERB);
	    	put("part", BabelPOS.PREPOSITION);
	    	put("part_dem", BabelPOS.DETERMINER);
	    	put("part_det", BabelPOS.DETERMINER);
	    	put("part_focus", BabelPOS.PREPOSITION);
	    	put("part_fut", BabelPOS.PREPOSITION);
	    	put("part_interrog", BabelPOS.PREPOSITION);
	    	put("part_neg", null); // think (RPs in PTB)
	    	put("part_restrict", BabelPOS.PREPOSITION);
	    	put("part_verb", BabelPOS.PREPOSITION);
	    	put("part_voc", BabelPOS.PREPOSITION);
	    	put("prep", BabelPOS.PREPOSITION);
	    	put("abbrev", BabelPOS.NOUN);
	    	put("punc", null);
	    	put("conj", BabelPOS.CONJUNCTION);
	    	put("conj_sub", BabelPOS.CONJUNCTION);
	    	put("interj", BabelPOS.INTERJECTION);
	    	put("digit", BabelPOS.NOUN); //think determiner?
	    	put("latin", BabelPOS.NOUN);  //think
	    	put("joker", BabelPOS.NOUN); //artificially added
		}
	};
	
	/**
	 * Mapping English Penn Tree Bank PoS into BabelNet
	 */
	static final Map<String, BabelPOS> BN_POS_EN= new HashMap<String, BabelPOS>(){
		private static final long serialVersionUID = 1652098132934864031L;
	    {
	    	put("CC", BabelPOS.CONJUNCTION);
	    	put("CD", BabelPOS.NOUN); //think determiner?
	    	put("DT", BabelPOS.DETERMINER);
	    	put("EX", null); //think
	    	put("FW", BabelPOS.NOUN); //think
	    	put("IN", BabelPOS.PREPOSITION);
	    	put("JJ", BabelPOS.ADJECTIVE);
	    	put("JJR", BabelPOS.ADJECTIVE);
	    	put("JJS", BabelPOS.ADJECTIVE); 
	    	put("LS", null); //think 
	    	put("MD", BabelPOS.VERB); 
	    	put("NN", BabelPOS.NOUN); 
	    	put("NNS", BabelPOS.NOUN);
	    	put("NNP", BabelPOS.NOUN); 
	    	put("NNPS", BabelPOS.NOUN);
	    	put("PDT", BabelPOS.DETERMINER); 
	    	put("POS", null); //think 
	    	put("PRP", BabelPOS.PRONOUN); 
	    	put("PRP$", BabelPOS.PRONOUN);
	    	put("RB",  BabelPOS.ADVERB);
	    	put("RBR", BabelPOS.ADVERB); 
	    	put("RBS", BabelPOS.ADVERB); 
	    	put("RP", null); //think
	    	put("SYM", null); //think 
	    	put("TO", BabelPOS.PREPOSITION); 
	    	put("UH", BabelPOS.INTERJECTION); 
	    	put("VB", BabelPOS.VERB); 
	    	put("VBD", BabelPOS.VERB); 
	    	put("VBG", BabelPOS.VERB); 
	    	put("VBN", BabelPOS.VERB); 
	    	put("VBP", BabelPOS.VERB); 
	    	put("VBZ", BabelPOS.VERB); 
	    	put("WDT", BabelPOS.DETERMINER); 
	    	put("WP", BabelPOS.PRONOUN);
	    	put("WP$", BabelPOS.PRONOUN); 
	    	put("WRB", BabelPOS.ADVERB); 
	    	// Lowercased version
	    	put("cc", BabelPOS.CONJUNCTION);
	    	put("cd", BabelPOS.NOUN); //think determiner?
	    	put("dt", BabelPOS.DETERMINER);
	    	put("ex", null); //think
	    	put("fw", BabelPOS.NOUN); //think
	    	put("in", BabelPOS.PREPOSITION);
	    	put("jj", BabelPOS.ADJECTIVE);
	    	put("jjr", BabelPOS.ADJECTIVE);
	    	put("jjs", BabelPOS.ADJECTIVE); 
	    	put("ls", null); //think 
	    	put("md", BabelPOS.VERB); 
	    	put("nn", BabelPOS.NOUN); 
	    	put("nns", BabelPOS.NOUN);
	    	put("nnp", BabelPOS.NOUN); 
	    	put("nnps", BabelPOS.NOUN);
	    	put("pdt", BabelPOS.DETERMINER); 
	    	put("pos", null); //think 
	    	put("prp", BabelPOS.PRONOUN); 
	    	put("prp$", BabelPOS.PRONOUN);
	    	put("rb",  BabelPOS.ADVERB);
	    	put("rbr", BabelPOS.ADVERB); 
	    	put("rbs", BabelPOS.ADVERB); 
	    	put("rp", null); //think
	    	put("sym", null); //think 
	    	put("to", BabelPOS.PREPOSITION); 
	    	put("uh", BabelPOS.INTERJECTION); 
	    	put("vb", BabelPOS.VERB); 
	    	put("vbd", BabelPOS.VERB); 
	    	put("vbg", BabelPOS.VERB); 
	    	put("vbn", BabelPOS.VERB); 
	    	put("vbp", BabelPOS.VERB); 
	    	put("vbz", BabelPOS.VERB); 
	    	put("wdt", BabelPOS.DETERMINER); 
	    	put("wp", BabelPOS.PRONOUN);
	    	put("wp$", BabelPOS.PRONOUN); 
	    	put("wrb", BabelPOS.ADVERB); 
	    	put("joker", BabelPOS.NOUN); //artificially added
		}
	};
	
	/**
	 * Mapping a subset of the Ancora tagset into BabelNet.
	 * Since the Ancora tagset is much more detailed than Babelnet we only
	 * consider the first two characters to do the mapping.
	 */
	static final Map<String, BabelPOS> BN_POS_ES= new HashMap<String, BabelPOS>(){
		private static final long serialVersionUID = 1652098132934864032L;
	    {
	    	put("ao", BabelPOS.ADJECTIVE);
	    	put("aq", BabelPOS.ADJECTIVE);
	    	put("cc", BabelPOS.CONJUNCTION);
	    	put("cs", BabelPOS.CONJUNCTION);
	    	put("da", BabelPOS.ARTICLE);
	    	put("dd", BabelPOS.DETERMINER);
	    	put("de", BabelPOS.DETERMINER);
	    	put("di", BabelPOS.ARTICLE);
	    	put("dn", BabelPOS.DETERMINER);
	    	put("dp", BabelPOS.ARTICLE);
	    	put("dt", BabelPOS.DETERMINER);
	    	put("f0", null); //punctuations
	    	put("fa", null);
	    	put("fc", null);
	    	put("fd", null);
	    	put("fe", null);
	    	put("fg", null);
	    	put("fh", null);
	    	put("fi", null);
	    	put("fp", null);
	    	put("fs", null);
	    	put("ft", null);
	    	put("fx", null);
	    	put("fz", null);
	    	put("i", BabelPOS.INTERJECTION);
	    	put("nc", BabelPOS.NOUN);
	    	put("np", BabelPOS.NOUN);
	    	put("p0", BabelPOS.PRONOUN);
	    	put("pd", BabelPOS.PRONOUN);
	    	put("pe", BabelPOS.PRONOUN);
	    	put("pi", BabelPOS.PRONOUN);
	    	put("pn", BabelPOS.PRONOUN);
	    	put("pp", BabelPOS.PRONOUN);
	    	put("pr", BabelPOS.PRONOUN);
	    	put("pt", BabelPOS.PRONOUN);
	    	put("px", BabelPOS.PRONOUN);
	    	put("rg", BabelPOS.ADVERB);
	    	put("rn", BabelPOS.ADVERB);
	    	put("sp", BabelPOS.PREPOSITION);
	    	put("va", BabelPOS.VERB);
	    	put("vm", BabelPOS.VERB);
	    	put("vs", BabelPOS.VERB);
	    	put("w", null); //dates
	    	put("z0", BabelPOS.NOUN); //numerals
	    	put("zm", BabelPOS.NOUN);
	    	put("zu", BabelPOS.NOUN);
	    	put("joker", BabelPOS.NOUN); //artificially added
		}
	};

}


/*
 * 
 * ENGLISH
 
Alphabetical list of part-of-speech tags used in the Penn Treebank Project:
https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html

Number Tag Description

1. 		CC 		Coordinating conjunction
2. 		CD 		Cardinal number
3. 		DT 		Determiner
4. 		EX 		Existential there
5. 		FW 		Foreign word
6. 		IN 		Preposition or subordinating conjunction
7. 		JJ 		Adjective
8. 		JJR 	Adjective, comparative
9. 		JJS 	Adjective, superlative
10. 	LS 		List item marker
11. 	MD 		Modal
12. 	NN 		Noun, singular or mass
13. 	NNS 	Noun, plural
14. 	NNP 	Proper noun, singular
15. 	NNPS 	Proper noun, plural
16. 	PDT 	Predeterminer
17. 	POS 	Possessive ending
18. 	PRP 	Personal pronoun
19. 	PRP$ 	Possessive pronoun
20. 	RB 		Adverb
21. 	RBR 	Adverb, comparative
22. 	RBS 	Adverb, superlative
23. 	RP 		Particle
24. 	SYM 	Symbol
25. 	TO 		to
26. 	UH 		Interjection
27. 	VB 		Verb, base form
28. 	VBD 	Verb, past tense
29. 	VBG 	Verb, gerund or present participle
30. 	VBN 	Verb, past participle
31. 	VBP 	Verb, non-3rd person singular present
32. 	VBZ 	Verb, 3rd person singular present
33. 	WDT 	Wh-determiner
34. 	WP 		Wh-pronoun
35. 	WP$ 	Possessive wh-pronoun
36. 	WRB 	Wh-adverb 


* SPANISH
*	 
* http://nlp.stanford.edu/software/spanish-faq.shtml#tagset
Tag	Description	Example(s)

Adjectives
ao0000	Adjective (ordinal)	primera, segundo, últimos
aq0000	Adjective (descriptive)	populares, elegido, emocionada, andaluz
Conjunctions
cc	Conjunction (coordinating)	y, o, pero
cs	Conjunction (subordinating)	que, como, mientras
Determiners
da0000	Article (definite)	el, la, los, las
dd0000	Demonstrative	este, esta, esos
de0000	"Exclamative" 	qué (¡Qué pobre!)
di0000	Article (indefinite)	un, muchos, todos, otros
dn0000	Numeral	tres, doscientas
dp0000	Possessive	sus, mi
dt0000	Interrogative	cuántos, qué, cuál
Punctuation
f0	Other	&, @
faa	Inverted exclamation mark	¡
fat	Exclamation mark	!
fc	Comma	,
fd	Colon	:
fe	Double quote	"
fg	Hyphen	-
fh	Forward slash	/
fia	Inverted question mark	¿
fit	Question mark	?
fp	Period / full-stop	.
fpa	Left parenthesis	(
fpt	Right parenthesis	)
fs	Ellipsis	..., etcétera
ft	Percent sign	%
fx	Semicolon	;
fz	Single quote	'
Interjections
i	Interjection	ay, ojalá, hola
Nouns
nc00000	Unknown common noun (neologism, loanword)	minidisc, hooligans, re-flotamiento
nc0n000	Common noun (invariant number)	hipótesis, campus, golf
nc0p000	Common noun (plural)	años, elecciones
nc0s000	Common noun (singular)	lista, hotel, partido
np00000	Proper noun	Málaga, Parlamento, UFINSA
Pronouns
p0000000	Impersonal se	se
pd000000	Demonstrative pronoun	éste, eso, aquellas
pe000000	"Exclamative" pronoun	qué
pi000000	Indefinite pronoun	muchos, uno, tanto, nadie
pn000000	Numeral pronoun	dos miles, ambos
pp000000	Personal pronoun	ellos, lo, la, nos
pr000000	Relative pronoun	que, quien, donde, cuales
pt000000	Interrogative pronoun	cómo, cuánto, qué
px000000	Possessive pronoun	tuyo, nuestra
Adverbs
rg	Adverb (general)	siempre, más, personalmente
rn	Adverb (negating)	no
Prepositions
sp000	Preposition	en, de, entre
Verbs
vag0000	Verb (auxiliary, gerund)	habiendo
vaic000	Verb (auxiliary, indicative, conditional)	habría, habríamos
vaif000	Verb (auxiliary, indicative, future)	habrá, habremos
vaii000	Verb (auxiliary, indicative, imperfect)	había, habíamos
vaip000	Verb (auxiliary, indicative, present)	ha, hemos
vais000	Verb (auxiliary, indicative, preterite)	hubo, hubimos
vam0000	Verb (auxiliary, imperative)	haya
van0000	Verb (auxiliary, infinitive)	haber
vap0000	Verb (auxiliary, participle)	habido
vasi000	Verb (auxiliary, subjunctive, imperfect)	hubiera, hubiéramos, hubiese
vasp000	Verb (auxiliary, subjunctive, present)	haya, hayamos
vmg0000	Verb (main, gerund)	dando, trabajando
vmic000	Verb (main, indicative, conditional)	daría, trabajaríamos
vmif000	Verb (main, indicative, future)	dará, trabajaremos
vmii000	Verb (main, indicative, imperfect)	daba, trabajábamos
vmip000	Verb (main, indicative, present)	da, trabajamos
vmis000	Verb (main, indicative, preterite)	dio, trabajamos
vmm0000	Verb (main, imperative)	da, dé, trabaja, trabajes, trabajemos
vmn0000	Verb (main, infinitive)	dar, trabjar
vmp0000	Verb (main, participle)	dado, trabajado
vmsi000	Verb (main, subjunctive, imperfect)	diera, diese, trabajáramos, trabajésemos
vmsp000	Verb (main, subjunctive, present)	dé, trabajemos
vsg0000	Verb (semiauxiliary, gerund)	siendo
vsic000	Verb (semiauxiliary, indicative, conditional)	sería, serían
vsif000	Verb (semiauxiliary, indicative, future)	será, seremos
vsii000	Verb (semiauxiliary, indicative, imperfect)	era, éramos
vsip000	Verb (semiauxiliary, indicative, present)	es, son
vsis000	Verb (semiauxiliary, indicative, preterite)	fue, fuiste
vsm0000	Verb (semiauxiliary, imperative)	sea, sé
vsn0000	Verb (semiauxiliary, infinitive)	ser
vsp0000	Verb (semiauxiliary, participle)	sido
vssf000	Verb (semiauxiliary, subjunctive, future)	fuere
vssi000	Verb (semiauxiliary, subjunctive, imperfect)	fuera, fuese, fuéramos
vssp000	Verb (semiauxiliary, subjunctive, present)	sea, seamos
Dates
w	Date	octubre, jueves, 2002
Numerals
z0	Numeral	547.000, 04, 52,52
zm	Numeral qualifier (currency)	dólares, euros
zu	Numeral qualifier (other units)	km, cc

*
*
*/