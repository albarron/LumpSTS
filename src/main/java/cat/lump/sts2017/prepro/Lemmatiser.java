package cat.lump.sts2017.prepro;

import java.io.File;

import org.ini4j.Profile.Section;

/**
 * Interface to lemmatise raw text
 *   
 * @author cristina
 * @since Nov 30, 2016
 */
public interface Lemmatiser {

	/** 
	 * Runs the lemmatiser on an input file.
	 * 
	 * @param p
   *      Properties object with the config file's proper section loaded
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the lemmas
	 */
	public void execute(Section p, File input, String lang, File output);

	
	/** 
	 * Runs the lemmatiser on an input string. Returns the string with the lemmas.
	 * 
	 * @param p
	 * 			Properties object with the config file's proper section loaded
	 * @param input
	 * 			Input string text
	 * @param lang
	 * 			Language of the input text
	 * 
	 * @return 
	 * 			String with the lemmas
	 */
	//public String execute(String exeTok, String exeLem, String input, String lang);
	public String execute(Section p, String input, String lang);

}
