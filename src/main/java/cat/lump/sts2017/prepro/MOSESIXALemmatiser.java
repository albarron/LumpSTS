package cat.lump.sts2017.prepro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import cat.lump.aq.basics.log.LumpLogger;
import ixa.kaflib.KAFDocument;

/**
 * Implements the Lemmatiser class that uses IXA pipes for lemmatising
 * and moses scripts for tokenisation
 * 
 * @author cristina
 * @since Dec 5, 2016
 */
public class MOSESIXALemmatiser implements Lemmatiser {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (Annotator.class.getSimpleName());

	/** 
	 * Runs the lemmatiser on an input file.
	 * 
	 * @param p
	 * 			Properties object with the config file loaded
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the annotated source
	 */
	public void execute(Properties p, File input, String lang, File output) {

		// Loading paths from the config file
		String jarLem = p.getProperty("ixaLem");
		Annotator.checkExists(jarLem, "The IXA lemmatiser cannot be found at ");

		String posM = "";
		String lemM = "";
		if (lang.equalsIgnoreCase("es")) {
			posM = p.getProperty("posEs");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.getProperty("lemEs");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else if (lang.equalsIgnoreCase("en")) {
			posM = p.getProperty("posEn");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.getProperty("lemEn");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else {
			logger.error("Your language " + lang + 
					"has not been detected and PoS and lemmatisation models cannot be loaded.");
		}

		// Previous normalisation
		logger.warn("Text is being normalised.");
		String nameTMP = "norm"+Math.random()+".tmp";
		File fileTMP = new File(nameTMP);
		Normaliser.normalise(input, fileTMP, lang);
		
        // Parameters needed to tokenise raw text into raw text for the languages in STS
		String language = "-l"+lang;
		String exe = p.getProperty("mosesTok");
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
			    KAFDocument kaf = new KAFDocument(lang, kafVersion);
				FormatConverter.tokensToKAF(noTokReader, kaf);
			    kafString = kaf.toString();
				noTokReader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			// Temporary file (conll format) with the lemmatisation
			String nameTMP2 = "lem"+Math.random()+".tmp";
			File fileTMP2 = new File(nameTMP2);

			//java -jar ixa-pipe-pos-1.5.0.jar tag -m en-pos-perceptron-autodict01-conll09.bin -lm en-lemma-perceptron-conll09.bin
			String modelPos = "-m"+posM;
			//String[] commandIxaLem = {"java", "-jar", jarLem, "tag", modelPos, modelLem};
			String[] commandIxaLem = {"java", "-jar", jarLem, "tag", modelPos, "--lemmatizerModel", lemM, "-oconll"};
	
			ProcessBuilder builderLem = new ProcessBuilder(commandIxaLem);
			//builderLem.redirectInput(output);
			builderLem.redirectOutput(fileTMP2);
			//builderLem.redirectError(new File("er.txt"));
			logger.info("Starting lemmatisation with IXA pipes...");

			int waitFlagL =1;
			try {
				Process process = builderLem.start();
				OutputStream stdin = process.getOutputStream();
			    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
			    writer.write(kafString);
		        writer.close();

				try {
					waitFlagL = process.waitFor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Some error occurred when starting the ProcessBuilder for lemmatisation "+
				"of file "+fileTMP.toString()+" with IXA pipe.");
			} 
			fileTMP.delete();
			if (waitFlagL == 0) {
				FormatConverter.conllLema2Factors(fileTMP2, output);
				fileTMP2.delete();
				logger.info("Lemmatisation done.");
			}
		} // Si la tokenizacio ha acabat
	}

	
	/** 
	 * Runs the lemmatiser on an input string. Returns the string with the lemmas.
	 * 
	 * @param p
	 * 			Properties object with the config file loaded
	 * @param input
	 * 			Input string text
	 * @param lang
	 * 			Language of the input text
	 * 
	 * @return 
	 * 			String with the lemmas
	 */
	public String execute(Properties p, String input, String lang) {
		// Default output
		String lemOutput = "NON ANNOTATED";

		// Loading paths from the config file
		String jarLem = p.getProperty("ixaLem");
		Annotator.checkExists(jarLem, "The IXA lemmatiser cannot be found at ");

		String posM = "";
		String lemM = "";
		if (lang.equalsIgnoreCase("es")) {
			posM = p.getProperty("posEs");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.getProperty("lemEs");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else if (lang.equalsIgnoreCase("en")) {
			posM = p.getProperty("posEn");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.getProperty("lemEn");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else {
			logger.error("Your language " + lang + 
					"has not been detected and PoS and lemmatisation models cannot be loaded.");
		}
		
		// Previous normalisation
		logger.warn("Text is being normalised.");
		String normalised = Normaliser.replacements(input, lang);
		
        // Parameters needed to tokenise raw text into raw text for the languages in STS
		String language = "-l"+lang;
		String exe = p.getProperty("mosesTok");
		Annotator.checkExists(exe, "The moses tokenisation script cannot be found at ");
		
		String[] commandTok = {"perl", exe, language};
		//perl mosesdecoder/scripts/tokenizer/tokenizerNO2html.perl -l de < in > out
			
		// Run the tokeniser in a separate system process
		ProcessBuilder builder = new ProcessBuilder(commandTok);	
		logger.info("Starting tokenisation with moses scripts...");
		int waitFlag = 1;
		String tokOutput = "";
		try {
			Process process = builder.start();
			OutputStream stdin = process.getOutputStream();
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
		    writer.write(normalised);
	        writer.close();
			InputStream outputTok = process.getInputStream();
			tokOutput = IOUtils.toString(outputTok, "UTF-8");
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
			    KAFDocument kaf = new KAFDocument(lang, kafVersion);
				FormatConverter.tokensToKAF(noTokReader, kaf);
			    kafString = kaf.toString();
				noTokReader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			// Temporary file (conll format) with the lemmatisation
			String nameTMP2 = "lem"+Math.random()+".tmp";
			File fileTMP2 = new File(nameTMP2);

			//java -jar ixa-pipe-pos-1.5.0.jar tag -m en-pos-perceptron-autodict01-conll09.bin -lm en-lemma-perceptron-conll09.bin
			String modelPos = "-m"+posM;
			//String[] commandIxaLem = {"java", "-jar", jarLem, "tag", modelPos, modelLem};
			String[] commandIxaLem = {"java", "-jar", jarLem, "tag", modelPos, "--lemmatizerModel", lemM, "-oconll"};
	
			ProcessBuilder builderLem = new ProcessBuilder(commandIxaLem);
			builderLem.redirectOutput(fileTMP2);
			logger.info("Starting lemmatisation with IXA pipes...");

			int waitFlagL =1;
			try {
				Process process = builderLem.start();
				OutputStream stdin = process.getOutputStream();
			    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
			    writer.write(kafString);
		        writer.close();

				try {
					waitFlagL = process.waitFor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Some error occurred when starting the ProcessBuilder for lemmatisation "+
				"with IXA pipe.");
			} 
			if (waitFlagL == 0) {
				lemOutput = FormatConverter.conllLema2Factors(fileTMP2);
				fileTMP2.delete();
				logger.info("Lemmatisation done.");
			}
		} // Si la tokenizacio ha acabat
		
		return lemOutput;		
	}

	

}
