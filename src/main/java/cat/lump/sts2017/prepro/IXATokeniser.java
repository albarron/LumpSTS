package cat.lump.sts2017.prepro;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.ini4j.Profile.Section;

import cat.lump.aq.basics.io.files.FileIO;
import cat.lump.aq.basics.log.LumpLogger;

/**
 * Implements the Tokeniser class with the functionalities of the ixa-pipe
 * TODO Now it is executed as a call to the binary. Try to use as an API
 * 
 * @author cristina
 * @since Nov 29, 2016
 */
public class IXATokeniser implements Tokeniser {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (Annotator.class.getSimpleName());

	/** 
	 * Runs the tokeniser on an input file.
	 * 
	 * @param section
   *      Properties object with the config file's proper section loaded
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the tokenisation
	 */
	public void execute(Section section, File input, String lang, File output) {

        // Parameters needed to tokenise raw text into raw text for the languages in STS
		String language = "-l"+lang;
		String normalisation = "-nptb";
		if (lang.equalsIgnoreCase("es")) {
			normalisation = "-nancora";
		}
		String jar = section.get("ixaTok");
		String[] commandIxa = { "java", "-jar", jar, "tok", language, "-ooneline", normalisation};

		// IXA only recognises a sentence if it ends with a punctuation token
		String nameTMP = "input4IXA"+Math.random()+".tmp";
		File input4IXA = new File(nameTMP);
		FormatConverter.raw2punct(input, input4IXA);
		
		// Run  in a separate system process
		ProcessBuilder builder = new ProcessBuilder(commandIxa);
		builder.redirectInput(input4IXA);
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
		input4IXA.delete();
		
	}

	/** 
	 * Runs the tokeniser on an input string. Returns the tokenised string.
	 * TODO: It is also splitting the sentences. Remove (HOW?!).
	 * 
	 * @param section
   *      Properties object with the config file's proper section loaded
	 * @param input
	 * 			Input string text
	 * @param lang
	 * 			Language of the input text
	 * 
	 * @return tokOutput
	 * 			Tokenised string
	 */
	public String execute(Section section, String input, String lang) {
		// Default output
		String tokOutput = "NON TOKENISED";

        // Parameters needed to tokenise raw text into raw text for the languages in STS
		String language = "-l"+lang;
		String normalisation = "-nptb";
		if (lang.equalsIgnoreCase("es")) {
			normalisation = "-nancora";
		}
	    String jar = section.get("ixaTok");
		String[] commandIxa = { "java", "-jar", jar, "tok", language, "-ooneline", normalisation};

		// IXA only recognises a sentence if it ends with a punctuation token
		if (!input.matches(".*[.!?]$")){
			input=input+".";
		}
		// Convert the input string into a file
		// TODO there must be a better way to give the input
		String nameTMP = "input"+Math.random()+".tmp";
		File fileTMP = new File(nameTMP);
		try {
			FileIO.stringToFile(fileTMP, input, false);
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("File "+nameTMP+" could not be created.");
		}
		
		// Run the tokeniser in a separate system process
		ProcessBuilder builder = new ProcessBuilder(commandIxa);
		builder.redirectInput(fileTMP);
				
		logger.info("Starting tokenisation...");
		try {
			Process process = builder.start();
			InputStream output = process.getInputStream();
			tokOutput = IOUtils.toString(output, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Some error occurred when starting the ProcessBuilder for tokenisation "+
			"of file "+input.toString()+" with IXA pipe.");
		} 
		fileTMP.delete();
		logger.info("Tokenisation done.");
		
		return tokOutput;		
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
