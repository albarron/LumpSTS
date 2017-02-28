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

	
	/**
	 * Mapping TS Wikipedia Data Set for Turkish which is the one used by the TS tagget
	 */
	static final Map<String, BabelPOS> BN_POS_TR= new HashMap<String, BabelPOS>(){
		private static final long serialVersionUID = 1652098132934864033L;
	    {
	    	put("Verb", BabelPOS.VERB);
	    	put("Noun", BabelPOS.NOUN);
	    	put("Adj", BabelPOS.ADJECTIVE);
	    	put("Adv", BabelPOS.ADVERB);
	    	put("Det", BabelPOS.DETERMINER);
	    	put("Conj", BabelPOS.CONJUNCTION);
	    	put("Postp", BabelPOS.PREPOSITION);         // Postposition
	    	put("Interj", BabelPOS.INTERJECTION); 		// Interjection
	    	put("Pron", BabelPOS.PRONOUN);
	    	put("Dup", null);  	       // Duplication
	    	put("Num", BabelPOS.NOUN); //think determiner?       //	Number
	    	put("Punc", null);
	    	put("UnDef", BabelPOS.ARTICLE);	 // Undefinite
	    	put("Ques", null);     //Question
	    	put("YY", null);       //	Misspell
	    	put("Abbr", BabelPOS.NOUN);     //	Abbreviation
	    	put("intEmphasis", null);   //	Internet Emphasis
	    	put("intAbbrEng", null);    //	Internet English Abbreviation
	    	put("tinglish", null); 	    //	Tinglish
	    	put("bor", BabelPOS.NOUN);	//	Borrowed
	    	put("intSlang", null); 
	    	put("joker", BabelPOS.NOUN); //artificially added
		}
	};
		

	
	
	/**
	 * Mapping from the French Penn TreeBank. In the original FTB, words are split into 13 main 
	 * categories, themselves divided into 34 subcategories. The version of the treebank we used was 
	 * obtained by converting subcategories into a tagset consisting of 28 tags, with a granularity 
	 * that is intermediate between categories and subcategories. 
	 * https://alpage.inria.fr/statgram/frdep/Publications/crabbecandi-taln2008-final.pdf
	*/
	static final Map<String, BabelPOS> BN_POS_FR= new HashMap<String, BabelPOS>(){
		private static final long serialVersionUID = 1652098132934864034L;
	    {
	    	put("V", BabelPOS.VERB);
	    	put("VIMP", BabelPOS.VERB);
	    	put("VINF", BabelPOS.VERB);
	    	put("VS", BabelPOS.VERB);
	    	put("VPP", BabelPOS.VERB);
	    	put("VPR", BabelPOS.VERB);
	    	put("NPP", BabelPOS.NOUN);
	    	put("NC", BabelPOS.NOUN);
	    	put("CS", BabelPOS.CONJUNCTION);
	    	put("CC", BabelPOS.CONJUNCTION);		
	    	put("CLS", BabelPOS.PRONOUN);
	    	put("CLO", BabelPOS.PRONOUN);
	    	put("CLR", BabelPOS.PRONOUN);
	    	put("P", BabelPOS.PREPOSITION);
	    	put("P+D", BabelPOS.PREPOSITION);
	    	put("P+PRO", BabelPOS.PREPOSITION);	
	    	put("I", BabelPOS.INTERJECTION); 
	    	put("PONCT", null);	
	    	put("ET", BabelPOS.NOUN);	//	Borrowed
	    	put("ADJWH", BabelPOS.ADJECTIVE);	
	    	put("ADJ", BabelPOS.ADJECTIVE);
	    	put("ADVWH", BabelPOS.ADVERB);	
	    	put("ADV", BabelPOS.ADVERB);
	    	put("PROWH", BabelPOS.PRONOUN);	
	    	put("PRORE", BabelPOS.PRONOUN);	
	    	put("PRO", BabelPOS.PRONOUN);
	    	put("DETWH", BabelPOS.DETERMINER);	
	    	put("DET", BabelPOS.DETERMINER);
	    }
	};
		
	
	/**
	 * Mapping from the STTS Stuttgart Tübingen POS tagset for German 
	 * http://paula.petcu.tm.ro/init/default/post/opennlp-part-of-speech-tags
	 */
	static final Map<String, BabelPOS> BN_POS_DE= new HashMap<String, BabelPOS>(){
		private static final long serialVersionUID = 1652098132934864035L;
	    {
	    	put("ADJA", BabelPOS.ADJECTIVE); 	// attributive adjective
	    	put("ADJD", BabelPOS.ADJECTIVE); 	// adverbial or predicative adjective
	    	put("ADV", BabelPOS.ADVERB); 	// Adverb
	    	put("APPR", BabelPOS.PREPOSITION); 	// Preposition
	    	put("APPRART", BabelPOS.PREPOSITION); // Preposition with article folded in
	    	put("APPO", BabelPOS.PREPOSITION); 	// Postposition
	    	put("APZR", null);  			// Right part of circumposition
	    	put("ART", BabelPOS.DETERMINER); 	// definite or indefinite article
	    	put("CARD", BabelPOS.NOUN); 		// cardinal number
	    	put("FM", BabelPOS.NOUN); 		// foreign word
	    	put("ITJ", BabelPOS.INTERJECTION); 	// interjection
	    	put("KOUI", BabelPOS.CONJUNCTION); 	// subordinating conjunction with 'zu' and infinitive
	    	put("KOUS", BabelPOS.CONJUNCTION); 	// subordinating conjunction with sentence
	    	put("KON", BabelPOS.CONJUNCTION); 	// coordinating conjunction
	    	put("KOKOM", BabelPOS.CONJUNCTION); 	// comparative conjunction
	    	put("NN", BabelPOS.NOUN); 		// common noun
	    	put("NE", BabelPOS.NOUN); 		// proper noun
	    	put("PDS", BabelPOS.PRONOUN); 	// substituting demonstrative pronoun
	    	put("PDAT", BabelPOS.PRONOUN); 	// attributive demonstrative pronoun
	    	put("PIS", BabelPOS.PRONOUN); 	// substituting indefinite pronoun
	    	put("PIAT", BabelPOS.PRONOUN); 	// attributive indefinite pronoun
	    	put("PIDAT", BabelPOS.PRONOUN); 	// attributive indefinite pronoun with a determiner
	    	put("PPER", BabelPOS.PRONOUN); 	// non-reflexive personal pronoun
	    	put("PPOSS", BabelPOS.PRONOUN); 	// substituting possessive pronoun
	    	put("PPOSAT", BabelPOS.PRONOUN); 	// attribute adding posessive pronoun
	    	put("PRELS", BabelPOS.PRONOUN); 	// substituting relative pronoun
	    	put("PRELAT", BabelPOS.PRONOUN); 	// attribute adding relative pronoun
	    	put("PRF", BabelPOS.PRONOUN); 	// reflexive personal pronoun
	    	put("PWS", BabelPOS.PRONOUN); 	// substituting interrogative pronoun
	    	put("PWAT", BabelPOS.PRONOUN); 	// attribute adding interrogative pronoun
	    	put("PWAV", BabelPOS.PRONOUN); 	// adverbial interrogative or relative pronoun
	    	put("PAV", BabelPOS.ADVERB);  	// pronominal adverb
	    	put("PTKZU", null); 			// 'zu' before infinitive
	    	put("PTKNEG", null); 		// Negation particle
	    	put("PTKVZ", null); 			// particle part of separable verb
	    	put("PTKANT", null); 		// answer particle
	    	put("PTKA", null); 			// particle associated with adverb or adjective
	    	put("TRUNC", BabelPOS.NOUN); 	// first member of compound noun
	    	put("VVFIN", BabelPOS.VERB); 	// full finite verb
	    	put("VVIMP", BabelPOS.VERB); 	// full imperative
	    	put("VVINF", BabelPOS.VERB); 	// full infinitive
	    	put("VVIZU", BabelPOS.VERB); 	// full infinitive with "zu"
	    	put("VVPP", BabelPOS.VERB); 		// full past participle
	    	put("VAFIN", BabelPOS.VERB);		// auxilliary finite verb
	    	put("VAIMP", BabelPOS.VERB); 	// auxilliary imperative
	    	put("VAINF", BabelPOS.VERB); 	// auxilliary infinitive
	    	put("VAPP", BabelPOS.VERB); 		// auxilliary past participle
	    	put("VMFIN", BabelPOS.VERB); 	// modal finite verb
	    	put("VMINF", BabelPOS.VERB); 	// modal infinitive
	    	put("VMPP", BabelPOS.VERB); 		// modal past participle
	    	put("XY", null); 			// Non word with special characters
	    	put("$,", null); 			// comma
	    	put("$.", null); 			// sentence ending punctuation
	    	put("$(", null); 			// other sentence internal punctuation		
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



TURKISH

TS Wikipedia Data Set PosTag List
#
POSTag
TAG
Tag Used in Data Set
1
Verb
Verb
_Verb
2
Noun
Noun
_Noun
3
Adj
Adjective
_Adj
4
Adv
Adverb
_Adverb
5
Det
Determiner
_Det
6
Conj
Conjunction
_Conj
7
Postp
Postposition
_Postp
8
Interj
Interjection
_Interj
9
Pron
Pronoun
_Pron
10
Dup
Duplication
_Dup
11
Num
Number
_Num
12
Punc
Punctuation
_Punc
13
UnDef
Undefinite
_UnDef
14
Ques
Question
_Ques
15
YY
Misspell
_YY
16
Abbr
Abbreviation
_Abbr
17
intEmphasis
Internet Emphasis
_intEmphasis
18
intAbbrEng
Internet English Abbreviation
_intAbbrEnglish
19
tinglish
Tinglish
_tinglish
20
bor
Borrowed
_bor
21
intSlang
Internet Slang
_intSlang
The tags “YY, Abbr, intEmphasis, intAbbrEng, tinglish, bor and intSlang” are processed by 




* FRENCH
* http://www.llf.cnrs.fr/Gens/Abeille/French-Treebank-fr.php
* 
* The tagset with the 28 tags is on page 8 of this paper:
 http://alpage.inria.fr/statgram/frdep/Publications/crabbecandi-taln2008-final.pdf
* 

TAG	CAT	SOUS MODE
V	V	-	indicatif		
VIMP	V	-	impératif		
VINF	V	-	infinitif		
VS	V	-	subjonctif		
VPP	V	-	participe passé		
VPR	V	-	participe présent	
NPP	N	P	-			
NC	N	C	-			
CS	C	S	-			
CC	C	C	-					
CLS	CL	suj	-	
CLO	CL	obj	-	
CLR	CL	refl	-	
P	P	-	-	
P+D	voir texte		
P+PRO	voir texte		
I	I	-	-	
PONCT	PONCT	-	-	
ET	ET	-	-
ADJWH	A	int	-
ADJ	A	¬int	-
ADVWH	ADV	int	-
ADV	ADV	¬int	-
PROWH	PRO	int	-
PROREL	PRO	rel	-
PRO	PRO	¬(int | rel)	-
DETWH	D	int	-
DET	D	¬int	-


*  GERMAN
*  http://paula.petcu.tm.ro/init/default/post/opennlp-part-of-speech-tags
*  
*  Table 3. Supposed POS tagset for German (the STTS Stuttgart Tübingen tag set)
Number
	
Tag
	
Description
1. 	ADJA 	attributive adjective
2. 	ADJD 	adverbial or predicative adjective
3. 	ADV 	Adverb
4. 	APPR 	Preposition
5. 	APPRART 	Preposition with article folded in
6. 	APPO 	Postposition
7. 	APZR 	Right part of circumposition
8. 	ART 	definite or indefinite article
9. 	CARD 	cardinal number
10. 	FM 	foreign word
11. 	ITJ 	interjection
12. 	KOUI 	subordinating conjunction with 'zu' and infinitive
13. 	KOUS 	subordinating conjunction with sentence
14. 	KON 	coordinating conjunction
15. 	KOKOM 	comparative conjunction
16. 	NN 	common noun
17. 	NE 	proper noun
18. 	PDS 	substituting demonstrative pronoun
19. 	PDAT 	attributive demonstrative pronoun
20. 	PIS 	substituting indefinite pronoun
21. 	PIAT 	attributive indefinite pronoun
22. 	PIDAT 	attributive indefinite pronoun with a determiner
23. 	PPER 	non-reflexive personal pronoun
24. 	PPOSS 	substituting possessive pronoun
25. 	PPOSAT 	attribute adding posessive pronoun
26. 	PRELS 	substituting relative pronoun
27. 	PRELAT 	attribute adding relative pronoun
28. 	PRF 	reflexive personal pronoun
29. 	PWS 	substituting interrogative pronoun
30. 	PWAT 	attribute adding interrogative pronoun
31. 	PWAV 	adverbial interrogative or relative pronoun
32. 	PAV 	pronominal adverb
33. 	PTKZU 	'zu' before infinitive
34. 	PTKNEG 	Negation particle
35. 	PTKVZ 	particle part of separable verb
36. 	PTKANT 	answer particle
37. 	PTKA 	particle associated with adverb or adjective
38. 	TRUNC 	first member of compound noun
39. 	VVFIN 	full finite verb
40. 	VVIMP 	full imperative
41. 	VVINF 	full infinitive
42. 	VVIZU 	full infinitive with "zu"
43. 	VVPP 	full past participle
44. 	VAFIN 	auxilliary finite verb
45. 	VAIMP 	auxilliary imperative
46. 	VAINF 	auxilliary infinitive
47. 	VAPP 	auxilliary past participle
48. 	VMFIN 	modal finite verb
49. 	VMINF 	modal infinitive
50. 	VMPP 	modal past participle
51. 	XY 	Non word with special characters
52. 	$, 	comma
53. 	$. 	sentence ending punctuation
54. 	$( 	other sentence internal punctuation
*/