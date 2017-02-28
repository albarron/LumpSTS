package cat.lump.sts2017.babelNet;

import java.util.Map;

import cat.lump.aq.basics.log.LumpLogger;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

/**
 * Creates a PoSMaps object depending on the language. Currently a language has only
 * a possible set of tags.
 *   
 * @author cristina
 * @since Dec 27, 2016
 */

public class PoSFactory {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (PoSFactory.class.getSimpleName());


	/**
	 * Returns the adequate set of tag converter according to the input language
	 * 
	 * @param language
	 * @return
	 */
	public Map<String, BabelPOS> getPoSMapper(String language) {
		if (language.equalsIgnoreCase("en")) {
			return PoSMaps.BN_POS_EN;
		} else if (language.equalsIgnoreCase("es")) {
			return PoSMaps.BN_POS_ES;
		} else if (language.equalsIgnoreCase("ar")) {
			return PoSMaps.BN_POS_AR;
		} else if (language.equalsIgnoreCase("tr")) {
			return PoSMaps.BN_POS_TR;
		} else if (language.equalsIgnoreCase("fr")) {
			return PoSMaps.BN_POS_FR;
		} else if (language.equalsIgnoreCase("de")) {
			return PoSMaps.BN_POS_DE;
		} else {
			logger.error("There is no PoS mapping implemented for language "+language+".");
		}
		return null;
	}

}
