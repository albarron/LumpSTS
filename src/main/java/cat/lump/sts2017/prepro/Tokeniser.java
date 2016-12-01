package cat.lump.sts2017.prepro;

import java.io.File;

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
	 * @param exe
	 * 			Location of the tokeniser executable
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the tokenisation
	 */
	public void execute(String exe, File input, String lang, File output);

	
	/** 
	 * Runs the tokeniser on an input string. Returns the tokenised string.
	 * 
	 * @param exe
	 * 			Location of the tokeniser executable
	 * @param input
	 * 			Input string text
	 * @param lang
	 * 			Language of the input text
	 * 
	 * @return 
	 * 			Tokenised string
	 */
	public String execute(String exe, String input, String lang);

}
