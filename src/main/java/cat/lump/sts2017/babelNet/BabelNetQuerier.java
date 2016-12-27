package cat.lump.sts2017.babelNet;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//import cat.lump.aq.basics.log.LumpLogger;
import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.BabelSynsetType;
import it.uniroma1.lcl.babelnet.data.BabelPOS;
import it.uniroma1.lcl.jlt.util.Language;


/**
 * Set of queries to the BabelNet index
 * 
 * @author cristina
 * @since Dec 15, 2016
 */
public class BabelNetQuerier {
	
	/** Logger */
	//private static LumpLogger logger = 
	//		new LumpLogger (BabelNetQuerier.class.getSimpleName());

	
	/**
	 * Looks for the synset with more relations for a given lemma with pos. If it's a NE, we don't
	 * mind the PoS and force the synset to be a NE also.
	 * 
	 * TODO: What's the back-off? If the PoS doesn't match do we take another one? And if there is no NE?
	 * 
	 * @param bn
	 * @param pos
	 * @param lemma
	 * @param lang
	 * @param NE
	 * @return String
	 */
	public static String retrieveID(BabelNet bn, BabelPOS pos, String lemma, Language lang, boolean NE) {
		
		String bnID = "-";
		Map<String, Integer> idChoices = new HashMap<String, Integer>();

        try {
			for (BabelSynset synset : bn.getSynsets(lemma, lang)) {
				Integer relacions = synset.getEdges().size();
				if (NE){                      // If it's tagged as a NE we expect BN to have the tag also
					BabelSynsetType type = synset.getSynsetType();
					if(type.equals(BabelSynsetType.NAMED_ENTITY)){
						//BabelPOS bnpos = synset.getPOS();  
						//if (pos.equals(bnpos)){           // and we don't mind the PoS (which is a noun)
						//Extra weight to NEs in case there are nonNEs that also match
						Integer relacionsAugment = relacions+100;
						idChoices.put(synset.getId().toString(), relacionsAugment);
						//}
					} else {
						idChoices.put(synset.getId().toString(), relacions);
					}
				} else {					  // It it's not a NE we want the aception with more relations
					BabelPOS bnpos = synset.getPOS();
					if (pos.equals(bnpos)){
						idChoices.put(synset.getId().toString(), relacions);
					}
				}
			    //System.out.println("Synset ID: " + synset.getId() + lemma + relacions); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (!idChoices.isEmpty()){
        	bnID = getMaxfromMap(idChoices);
        } else {
        	//logger.warn("No BabelNet ID was found for lemma" + lemma + 
        	//		" with PoS " + pos.toString());
        }

		return bnID;
	}

	
	
	/**
	 * Retrieves the key with a maximum value from a Map<String, Integer>
	 * 
	 * @param mapSI
	 * @return String
	 */
	private static String getMaxfromMap(Map<String, Integer> mapSI){
		
	    Comparator<? super Entry<String, Integer>> maxValueComparator = (
	            entry1, entry2) -> entry1.getValue().compareTo(
	            entry2.getValue());

	    String keyMax = mapSI.entrySet().stream().max(maxValueComparator).get().getKey();
		
	    return keyMax;
	}
	

}
