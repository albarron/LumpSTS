package cat.lump.sts2017.prepro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;
import org.ini4j.Profile.Section;

import cat.lump.aq.basics.log.LumpLogger;

/**
 * Implements the Tokeniser class calling the Moses tokeniser and does
 * some previous normalisation. 
 * 
 * @author cristina
 * @since Dec 5, 2016
 */
public class MOSESTokeniser implements Tokeniser {

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

		// Previous normalisation
		logger.warn("Text is being normalised.");
		String nameTMP = "norm"+Math.random()+".tmp";
		File fileTMP = new File(nameTMP);
		Normaliser.normalise(input, fileTMP, lang);
		
        // Parameters needed to tokenise raw text into raw text for the languages in STS
		String language = "-l"+lang;
		String exe = section.get("mosesTok");
		
		String[] commandTok = { "perl", exe, language};
		//perl mosesdecoder/scripts/tokenizer/tokenizerNO2html.perl -l de < in > out
			
		// Run the tokeniser in a separate system process
		ProcessBuilder builder = new ProcessBuilder(commandTok);	
		builder.redirectInput(fileTMP);
		builder.redirectOutput(output);
		logger.info("Starting tokenisation...");
		System.out.println(commandTok);
		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Some error occurred when starting the ProcessBuilder for tokenisation "+
			"of file "+input.toString()+" with moses tokenizer.");
		} 
		logger.info("Tokenisation done.");
		fileTMP.delete();
		
	}

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
	 * @return tokOutput
	 * 			Tokenised string
	 */
	public String execute(Section section, String input, String lang) {

		// Default output
		String tokOutput = "NON TOKENISED";
		
		// Previous normalisation
		logger.warn("Text is being normalised.");
		input = Normaliser.replacements(input, lang);
		
        // Parameters needed to tokenise raw text into raw text for the languages in STS
		String language = "-l"+lang;
		String exe = section.get("mosesTok");
		
		String[] commandTok = { "perl", exe, language};
		//perl mosesdecoder/scripts/tokenizer/tokenizerNO2html.perl -l de < in > out
			
		// Run the tokeniser in a separate system process
		ProcessBuilder builder = new ProcessBuilder(commandTok);				
		logger.info("Starting tokenisation...");
		try {
			Process process = builder.start();
			InputStream output = process.getInputStream();
			OutputStream stdin = process.getOutputStream();
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
		    writer.write(input);
	        writer.close();
			tokOutput = IOUtils.toString(output, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Some error occurred when starting the ProcessBuilder for tokenisation "+
			"of file "+input.toString()+" with moses tokenizer.");
		} 
		logger.info("Tokenisation done.");
		
		return tokOutput;		
	}


}
