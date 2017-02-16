package cat.lump.sts2017.prepro;

import java.io.File;
import java.io.IOException;
import org.ini4j.Profile.Section;

import cat.lump.aq.basics.io.files.FileIO;
import cat.lump.aq.basics.log.LumpLogger;

/**
 * Implements the Lemmatiser class with the functionalities of the ixa-pipe
 * TODO Now it is executed as a call to the binary. Try to use as an API
 * 
 * @author cristina
 * @since Nov 29, 2016
 */
public class IXALemmatiser implements Lemmatiser {

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

		// Loading paths from the config file
		String jarTok = p.get("ixaTok");
		Annotator.checkExists(jarTok, "The IXA tokeniser cannot be found at ");
		String jarLem = p.get("ixaLem");
		Annotator.checkExists(jarLem, "The IXA lemmatiser cannot be found at ");

		String posM = "";
		String lemM = "";
		if (lang.equalsIgnoreCase("es")) {
			posM = p.get("posEs");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.get("lemEs");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else if (lang.equalsIgnoreCase("en")) {
			posM = p.get("posEn");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.get("lemEn");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else if (lang.equalsIgnoreCase("fr")) {
			posM = p.get("posFr");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.get("lemFr");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else if (lang.equalsIgnoreCase("de")) {
			posM = p.get("posDe");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.get("lemDe");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else {
			
			logger.error("Your language " + lang + 
					"has not been detected and PoS and lemmatisation models cannot be loaded.");
		}

		//cat guardian.txt | java -jar ixa-pipe-tok-1.8.4.jar tok -l en | 
		//java -jar ixa-pipe-pos-1.5.0.jar tag -m en-pos-perceptron-autodict01-conll09.bin -lm en-lemma-perceptron-conll09.bin
        
		// Parameters needed to tokenise raw text into NAF format needed for lemmatisation
		String normalisation = "-nptb";
		if (lang.equalsIgnoreCase("es")) {
			normalisation = "-nancora";
		}
		String[] commandIxa = { "java", "-jar", jarTok, "tok", "-l", lang, normalisation};
		String nameTMP = "output"+Math.random()+".tmp";
		File fileTMP = new File(nameTMP);

		// IXA only recognises a sentence if it ends with a punctuation token
		String nameTMPixa = "input4IXA"+Math.random()+".tmp";
		File input4IXA = new File(nameTMPixa);
		FormatConverter.raw2punct(input, input4IXA);

		// Run tokenisation
		ProcessBuilder builder = new ProcessBuilder(commandIxa);
		builder.redirectInput(input4IXA);
		builder.redirectOutput(fileTMP);
		logger.info("Starting preliminary tokenisation...");
		int waitFlag = 1;
		try {
			Process process = builder.start();
			try {
				waitFlag = process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Some error occurred when starting the ProcessBuilder for tokenisation "+
			"of file "+input.toString()+" with IXA pipe.");
		} 
		
		// Temporary file (conll format) with the lemmatisation
		String nameTMP2 = "lem"+Math.random()+".tmp";
		File fileTMP2 = new File(nameTMP2);

		if (waitFlag == 0) {
			logger.info("Tokenisation done.");
			input4IXA.delete();
	
			//java -jar ixa-pipe-pos-1.5.0.jar tag -m en-pos-perceptron-autodict01-conll09.bin -lm en-lemma-perceptron-conll09.bin
			String modelPos = "-m"+posM;
			//String[] commandIxaLem = {"java", "-jar", jarLem, "tag", modelPos, modelLem};
			String[] commandIxaLem = {"java", "-jar", jarLem, "tag", modelPos, "--lemmatizerModel", lemM, "-oconll"};
	
			ProcessBuilder builderLem = new ProcessBuilder(commandIxaLem);
			builderLem.redirectInput(fileTMP);
			builderLem.redirectOutput(fileTMP2);
			//builderLem.redirectError(new File("er.txt"));
			logger.info("Starting lemmatisation...");
			int waitFlagL =1;
			try {
				Process process = builderLem.start();
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
		// Default output
		String lemOutput = "NON ANNOTATED";

		// Loading paths from the config file
		String jarTok = p.get("ixaTok");
		Annotator.checkExists(jarTok, "The IXA tokeniser cannot be found at ");
		String jarLem = p.get("ixaLem");
		Annotator.checkExists(jarLem, "The IXA lemmatiser cannot be found at ");

		String posM = "";
		String lemM = "";
		if (lang.equalsIgnoreCase("es")) {
			posM = p.get("posEs");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.get("lemEs");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else if (lang.equalsIgnoreCase("en")) {
			posM = p.get("posEn");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.get("lemEn");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else if (lang.equalsIgnoreCase("fr")) {
			posM = p.get("posFr");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.get("lemFr");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else if (lang.equalsIgnoreCase("de")) {
			posM = p.get("posDe");
			Annotator.checkExists(posM, "The IXA models for PoS tagging cannot be found at ");
			lemM = p.get("lemDe");
			Annotator.checkExists(lemM, "The IXA models for lemmatising cannot be found at ");
		} else {
			logger.error("Your language " + lang + 
					"has not been detected and PoS and lemmatisation models cannot be loaded.");
		}
		
		// Parameters needed to tokenise raw text into NAF format needed for lemmatisation
		String normalisation = "-nptb";
		if (lang.equalsIgnoreCase("es")) {
			normalisation = "-nancora";
		}
		String[] commandIxa = { "java", "-jar", jarTok, "tok", "-l", lang, normalisation};
		String nameTMPtok = "output"+Math.random()+".tmp";
		File fileTMPtok = new File(nameTMPtok);

		String nameTMPin = "input"+Math.random()+".tmp";
		File fileTMPin = new File(nameTMPin);
		// IXA only recognises a sentence if it ends with a punctuation token
		if (!input.matches(".*[.!?]$")){
			input=input+".";
		}
		try {
			FileIO.stringToFile(fileTMPin, input, false);
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("File "+nameTMPin+" could not be created.");
		}
		
		// Run the preliminary tokenisation
		ProcessBuilder builder = new ProcessBuilder(commandIxa);
		builder.redirectInput(fileTMPin);
		builder.redirectOutput(fileTMPtok);

		logger.info("Starting preliminary tokenisation...");
		int waitFlag = 1;
		try {
			Process process = builder.start();
			try {
				waitFlag = process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Some error occurred when starting the ProcessBuilder for tokenisation "+
			"of file "+input.toString()+" with IXA pipe.");
		} 
		
		// Temporary file (conll format) with the lemmatisation
		String nameTMP2 = "lem"+Math.random()+".tmp";
		File fileTMP2 = new File(nameTMP2);

		if (waitFlag == 0) {
			logger.info("Tokenisation done.");
	
			//java -jar ixa-pipe-pos-1.5.0.jar tag -m en-pos-perceptron-autodict01-conll09.bin -lm en-lemma-perceptron-conll09.bin
			String modelPos = "-m"+posM;
			//String[] commandIxaLem = {"java", "-jar", jarLem, "tag", modelPos, modelLem};
			String[] commandIxaLem = {"java", "-jar", jarLem, "tag", modelPos, "--lemmatizerModel", lemM, "-oconll"};
	
			ProcessBuilder builderLem = new ProcessBuilder(commandIxaLem);
			builderLem.redirectInput(fileTMPtok);
			builderLem.redirectOutput(fileTMP2);
			//builderLem.redirectError(new File("er.txt"));
			logger.info("Starting lemmatisation...");
			int waitFlagL =1;
			try {
				Process process = builderLem.start();
				try {
					waitFlagL = process.waitFor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Some error occurred when starting the ProcessBuilder for lemmatisation "+
				"of file "+fileTMPtok.toString()+" with IXA pipe.");
			} 
			fileTMPin.delete();
			fileTMPtok.delete();
			if (waitFlagL == 0) {
				lemOutput = FormatConverter.conllLema2Factors(fileTMP2);
				fileTMP2.delete();
				logger.info("Lemmatisation done.");
			}
		} // Si la tokenizacio ha acabat
		
		return lemOutput;		
	}


}
