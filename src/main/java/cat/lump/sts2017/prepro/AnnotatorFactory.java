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
			return new IXATokeniser();
		} else if (language.equalsIgnoreCase("es")) {
			return new IXATokeniser();
		} else if (language.equalsIgnoreCase("ar")) {
			//return new MADATokeniser();
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
			return new IXALemmatiser();
		} else if (language.equalsIgnoreCase("es")) {
			return new IXALemmatiser();
		} else if (language.equalsIgnoreCase("ar")) {
			//return new MADALemmatiser();
		} 
		return null;
	}
}
