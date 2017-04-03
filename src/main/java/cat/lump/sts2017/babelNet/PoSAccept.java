package cat.lump.sts2017.babelNet;

import java.util.HashSet;
import java.util.Set;

import it.uniroma1.lcl.babelnet.data.BabelPOS;


/**
 * Subset of PoS tags for Arabic, English and Spanish for which we extract the 
 * BabelNet ID.
 * 
 * @author cristina
 * @since Dec 27, 2016
 */
public class PoSAccept {

	/**
	 * Selected PoS tags from the MadaMira 2.1 tag set for Arabic for which we want
	 * the BabelNet ID
	 */
	static final Set<String> POS_AR_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864030L;
	    {
	    	add("adj");    // Adjectives
	    	add("adj_comp");
	    	add("adj_num");
	    	add("noun");    // Nouns
	    	add("noun_num");
	    	add("noun_quant");
	    	add("noun_prop");	    	
	    	add("adv");     // Adverbs
	    	add("adv_interrog");
	    	add("adv_rel");
	    	add("verb");    // Verbs
	    	add("verb_pseudo");
	    	add("latin");   // Foreign words
		}
	};
	
	/**
	 * Selected PoS tags from the Penn Tree Bank tag set for English for which we want
	 * the BabelNet ID
	 */
	static final Set<String> POS_EN_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864031L;
	    {
	    	add("JJ");  //Adjectives
	    	add("JJR");
	    	add("JJS");
	    	add("NN");  //Nouns
	    	add("NNS");
	    	add("NNP");
	    	add("NNPS");
	    	add("RB");  //Adverbs
	    	add("RBR");
	    	add("RBS");
	    	add("WRB");
	    	//add("MD");  //Verbs
	    	add("VB");
	    	add("VBD");
	    	add("VBG");
	    	add("VBN");
	    	add("VBP");
	    	add("VBZ");
	    	add("FW");  //Foreign words
		}
	};

	/**
	 * Negations in English. Tokens that appear within the RP (particle) and RB (adverb) PoS tags
	 * of the Penn Tree Bank tag set for English 
	 */
	static final Set<String> NEG_EN= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864041L;
	    {
	    	add("not");
	    	add("n't");
	    	add("non");  //not seen
	    	add("no");   //not seen
		}
	};

	/**
	 * Selected PoS tags from the Ancora tag set for Spanish for which we want the BabelNet ID.
	 * Only the first two characters of the tag are considered.
	 */
	static final Set<String> POS_ES_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864032L;
	    {
	    	add("ao"); //Adjectives
	    	add("aq");
	    	add("dn"); //Numerals
	    	add("nc"); //Nouns
	    	add("np"); 
	    	add("rg"); //Adverbs
	    	//add("rn");
	    	//add("va"); //Verbs
	    	add("vm");
	    	add("vs");
	    	add("zm");  //Currency
	    	add("zu");  //unitats
		}
	};

	/**
	 * PoS tags from the Trukish tag set for which we want the BabelNet ID.
	 */
	static final Set<String> POS_TR_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864033L;
	    {
	    	add("Verb");
	    	add("Noun");
	    	add("Adj");
	    	add("Adv");
	    	add("Num"); //think determiner?       //	Number
	    	add("bor");	//	Borrowed
		}
	};

	/**
	 * Negations in Turkish. (from google translate!)
	 */
	static final Set<String> NEG_TR= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864041L;
	    {
	    	add("hayır");
	    	add("yok");
	    	add("değil");  
	    	add("hiçbir"); 
		}
	};
	
	
	/**
	 * PoS tags from the French tag set for which we want the BabelNet ID.
	 */
	static final Set<String> POS_FR_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864034L;
	    {
	    	//add("ADJWH", BabelPOS.ADJECTIVE);	
	    	add("ADJ");
	    	//add("ADVWH", BabelPOS.ADVERB);	
	    	add("ADV");
	    	add("NPP");
	    	add("NC");
	    	add("ET");
	    	add("V");
	    	add("VIMP");
	    	add("VINF");
	    	add("VS");
	    	add("VPP");
	    	add("VPR");

		}
	};


	/**
	 * Negations in French. 
	 */
	static final Set<String> NEG_FR= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864042L;
	    {
	    	add("non");
	    	add("ne");
	    	add("pas");  
	    	add("n'"); 
		}
	};

	
	/**
	 * PoS tags from the German tag set for which we want the BabelNet ID.
	 */
	static final Set<String> POS_DE_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864035L;
	    {
	    	
	    	add("ADJA"); 	// attributive adjective
	    	add("ADJD"); 	// adverbial or predicative adjective
	    	add("ADV"); 	// Adverb
	    	//add("CARD", BabelPOS.NOUN); 		// cardinal number
	    	add("FM"); 		// foreign word
	    	add("NN"); 		// common noun
	    	add("NE"); 		// proper noun
	    	add("TRUNC"); 	// first member of compound noun
	    	add("VVFIN"); 	// full finite verb
	    	add("VVIMP"); 	// full imperative
	    	add("VVINF"); 	// full infinitive
	    	add("VVIZU"); 	// full infinitive with "zu"
	    	add("VVPP"); 		// full past participle
	    	//add("VAFIN", BabelPOS.VERB);		// auxilliary finite verb
	    	//add("VAIMP", BabelPOS.VERB); 	// auxilliary imperative
	    	//add("VAINF", BabelPOS.VERB); 	// auxilliary infinitive
	    	//add("VAPP", BabelPOS.VERB); 		// auxilliary past participle
	    	//add("VMFIN", BabelPOS.VERB); 	// modal finite verb
	    	//add("VMINF", BabelPOS.VERB); 	// modal infinitive
	    	//add("VMPP", BabelPOS.VERB); 	// modal past participle
		}
	};

	
	
}

