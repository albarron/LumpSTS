package cat.lump.sts2017.prepro;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import cat.lump.aq.basics.io.files.FileIO;
import cat.lump.aq.basics.log.LumpLogger;

/**
 * Implements the Lemmatiser class with the functionalities of the ixa-pipe
 * TODO Now it is executed as a call to the binary. Try to use as an API
 * 
 * @author cristinae
 * @since Nov 29, 2016
 */
public class IXALemmatiser implements Lemmatiser {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (Annotator.class.getSimpleName());

	/** 
	 * Runs the lemmatiser on an input file.
	 * 
	 * @param jar
	 * 			Location of the lemmatiser executable
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the sentences lemmatised
	 */
	public void execute(Properties p, File input, String lang, File output) {

		// Loading paths from the config file
		String jarTok = p.getProperty("ixaTok");
		String jarLem = p.getProperty("ixaLem");
		String posM = "";
		String lemM = "";
		if (lang.equalsIgnoreCase("es")) {
			posM = p.getProperty("posEs");
			lemM = p.getProperty("lemEs");
		} else if (lang.equalsIgnoreCase("en")) {
			posM = p.getProperty("posEn");
			lemM = p.getProperty("lemEn");
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

		// Run tokenisation
		ProcessBuilder builder = new ProcessBuilder(commandIxa);
		builder.redirectInput(input);
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
	 * Runs the tokeniser on an input string. Returns the tokenised string.
	 * 
	 * 
	 * @param jar
	 * 			Location of the tokeniser executable
	 * @param input
	 * 			Input string text
	 * @param lang
	 * 			Language of the input text
	 * 
	 * @return tokOutput
	 * 			Tokenised string
	 */
	public String execute(Properties p, String input, String lang) {
		// Default output
		String lemOutput = "NON ANNOTATED";

		// Loading paths from the config file
		String jarTok = p.getProperty("ixaTok");
		String jarLem = p.getProperty("ixaLem");
		String posM = "";
		String lemM = "";
		if (lang.equalsIgnoreCase("es")) {
			posM = p.getProperty("posEs");
			lemM = p.getProperty("lemEs");
		} else if (lang.equalsIgnoreCase("en")) {
			posM = p.getProperty("posEn");
			lemM = p.getProperty("lemEn");
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
