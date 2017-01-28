package cat.lump.sts2017.prepro;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import org.apache.commons.io.IOUtils;
import org.ini4j.Profile.Section;

import cat.lump.aq.basics.log.LumpLogger;

/**
 * Implements the Lemmatiser class that uses turkish-lemmatizer for lemmatising
 * https://github.com/baturman/turkish-lemmatizer
 * and moses scripts for tokenisation with additional non-breaking prefixes
 * https://github.com/ai-ku/tr-en-edu/tree/master/src/moses-scripts/nonbreaking_prefixes
 * 
 * //TODO Finally not implemented because it does not apply PoS tagging anyway
 * @author cristina
 * @since Jan 28, 2016
 */
public class MOSESturkishLemmatiser implements Lemmatiser {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (Annotator.class.getSimpleName());

	/** 
	 * Runs the lemmatiser on an input file.
	 * 
	 * @param p
	 * 			Properties object with the config file's proper section loaded
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the annotated source
	 */
	public void execute(Section p, File input, String lang, File output) {

		logger.error("Lemmatisation is not implemented for Turkish.");

		// Previous normalisation
		logger.warn("Text is being normalised.");
		String nameTMP = "norm"+Math.random()+".tmp";
		File fileTMP = new File(nameTMP);
		Normaliser.normalise(input, fileTMP, lang);
		
		// TODO: Moses tokenisation should have its own class as it is generic for several languages
        // Parameters needed to tokenise raw text into raw text for the languages in STS
		String language = "-l"+lang;
		String exe = p.get("mosesTok");
		Annotator.checkExists(exe, "The moses tokenisation script cannot be found at ");
		
		String[] commandTok = {"perl", exe, language};
		//perl mosesdecoder/scripts/tokenizer/tokenizerNO2html.perl -l de < in > out
			
		// Run the tokeniser in a separate system process
		ProcessBuilder builder = new ProcessBuilder(commandTok);	
		builder.redirectInput(fileTMP);
		logger.info("Starting tokenisation with moses scripts...");
		int waitFlag = 1;
		String tokOutput = "";
		try {
			Process process = builder.start();
			InputStream outputTok = process.getInputStream();
			tokOutput = IOUtils.toString(outputTok, "UTF-8");
			tokOutput = tokOutput.replaceAll("(?m)^\\s+$", ""); // deletes empty line at the end
			try {
				waitFlag = process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Some error occurred when starting the ProcessBuilder for tokenisation "+
			"of file "+input.toString()+" with moses tokenizer.");
		} 


		if (waitFlag == 0) {
			logger.info("Tokenisation done.");
			//fileTMP.delete();
			
            // Convert to KAF format, needed for lemmatisation
			logger.info("Converting output to KAF format...");
			String kafVersion = "home-made";
			String kafString = "";
		    BufferedReader noTokReader = null;
			try {
				noTokReader = new BufferedReader(new StringReader(tokOutput));
				noTokReader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		} // Si la tokenizacio ha acabat
	}

	
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
	public String execute(Section p, String input, String lang) {
		
		logger.error("Lemmatisation on strings is not implemented for Turkish. "
				+ "Please, put your text in a file.");
		return null;		
	}

	

}
