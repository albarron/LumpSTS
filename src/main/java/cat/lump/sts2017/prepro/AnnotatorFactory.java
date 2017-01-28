package cat.lump.sts2017.prepro;

/**
 * Creates an annotation object (tokeniser or lemmatiser) for a given language.
 * The tool depends on the language and the task.
 *   
 * @author cristina
 * @since Nov 29, 2016
 */

public class AnnotatorFactory {

	/**
	 * Returns the correct tokeniser acoording to the input language
	 * 
	 * @param language
	 * @return
	 */
	public Tokeniser getTokeniser(String language) {
		if (language.equalsIgnoreCase("en")) {
			return new MOSESTokeniser();
		} else if (language.equalsIgnoreCase("es")) {
			return new MOSESTokeniser();
		} else if (language.equalsIgnoreCase("ar")) {
			return new MADATokeniser();
		} 
		return null;
	}

	/**
	 * Returns the correct lemmatiser acoording to the input language
	 * 
	 * @param language
	 * @return
	 */
	public Lemmatiser getLemmatiser(String language) {
		if (language.equalsIgnoreCase("en")) {
			return new MOSESIXALemmatiser();
		} else if (language.equalsIgnoreCase("es")) {
			return new MOSESIXALemmatiser();
		} else if (language.equalsIgnoreCase("ar")) {
			return new MADALemmatiser();
		} else if (language.equalsIgnoreCase("tr")) {
			return new MOSESturkishLemmatiser();
		}	
		return null;
	}
}
