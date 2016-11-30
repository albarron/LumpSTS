/**
 * 
 */
package cat.lump.sts2017.prepro;

import java.io.File;
import java.io.IOException;

import cat.lump.aq.basics.log.LumpLogger;

/**
 * Implements the Tokeniser class with the functionalities of the ixa-pipe
 * TODO Now it is executed as a call to the binary. Try to use as an API
 * 
 * @author cristinae
 * @since Nov 29, 2016
 */
public class IXATokeniser implements Tokeniser {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (Annotator.class.getSimpleName());

	/** 
	 * Runs the tokeniser on an input file.
	 * 
	 * @param jar
	 * 			Location of the tokeniser executable
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the tokenisation
	 */
	public void execute(String jar, File input, String lang, File output) {

        // Parameters needed to tokenise raw text into raw text for the languages in STS
		//String jar = "/home/cristinae/soft/processors/ixa-pipe-tok-1.8.5-exec.jar";
		String language = "-l"+lang;
		String normalisation = "-nptb";
		if (lang.equalsIgnoreCase("es")) {
			normalisation = "-nancora";
		}
		String[] commandIxa = { "java", "-jar", jar, "tok", language, "-ooneline", normalisation};

		// Run  in a separate system process
		ProcessBuilder builder = new ProcessBuilder(commandIxa);
		builder.redirectInput(input);
		builder.redirectOutput(output);
		logger.info("Starting tokenisation...");
		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Some error occurred when starting the ProcessBuilder for tokenisation "+
			"of file "+input.toString()+" with IXA pipe.");
		} 
		logger.info("Tokenisation done.");
		
	}

	public void execute(String jar, String input, String lang, File output) {
	// TODO Auto-generated method stub
		
	}

	
	/*
	public void execute(File input, String lang, File output) {

		// Trying to mimick a command line input 
		CLI cli = new CLI();
		String language = "-l".concat(lang);
		String[] commandLine = { "tok", language, "-ooneline", "-nptb", "-uyes"};
		System.out.println(output.toString());
		try {
			cli.parseCLI(commandLine);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    InputStream is = null;
	    OutputStream os = null;
		try {
			is = new FileInputStream(input);
			os = new FileOutputStream(output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cli.annotate(is, os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
*/


}
