package cat.lump.sts2017.babelNet;

import java.io.IOException;

import cat.lump.aq.basics.log.LumpLogger;
import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.jlt.util.Language;



public class BabelNetQuerier {
	
	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (BabelNetQuerier.class.getSimpleName());

	
	public static String retrieveID(BabelNet bn, String lemma, String lang) {
		
		String bnID = null;
//		List<BabelSynset> byl = bn.getSynsets("run", Language.EN, BabelPOS.NOUN, BabelSenseSource.WIKI, BabelSenseSource.OMWIKI);


        try {
			for (BabelSynset synset : bn.getSynsets(lemma, Language.EN)) {
			    System.out.println("Synset ID: " + synset.getId());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bnID="kk";
	}


}
