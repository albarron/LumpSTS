package cat.lump.sts2017.babelNet;

import java.util.HashSet;
import java.util.Set;


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
	    	add("noun");
	    	add("noun_num");
	    	add("noun_quant");
	    	add("noun_prop");
	    	add("adj");
	    	add("adj_comp");
	    	add("adj_num");
	    	add("adv");
	    	add("adv_interrog");
	    	add("adv_rel");
	    	add("verb");
	    	add("verb_pseudo");
	    	add("latin");
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
	    	add("zm");  //monedes
	    	add("zu");  //unitats
		}
	};

}

