package cat.lump.sts2017.prepro;

import java.io.File;

import org.ini4j.Profile.Section;

/**
 * Interface to tokenise raw text
 *   
 * @author cristina
 * @since Nov 29, 2016
 */

public interface Tokeniser {

	/** 
	 * Runs the tokeniser on an input file.
	 * 
	 * @param section
	 * 			Properties object with the config file's proper section loaded
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the tokenisation
	 */
	public void execute(Section section, File input, String lang, File output);

	
	/** 
	 * Runs the tokeniser on an input string. Returns the tokenised string.
	 * 
	 * @param section
   *      Properties object with the config file's proper section loaded
	 * @param input
	 * 			Input string text
	 * @param lang
	 * 			Language of the input text
	 * 
	 * @return 
	 * 			Tokenised string
	 */
	public String execute(Section section, String input, String lang);

}
