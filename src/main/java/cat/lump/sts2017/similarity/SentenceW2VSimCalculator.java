package cat.lump.sts2017.similarity;

import java.io.File;
import java.io.IOException;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cat.lump.aq.basics.log.LumpLogger;


/**
 * Calculates the similarity between two sentences according to different measures.
 * Sentences are converted into a vector representation with w2v embeddings. 
 * A sentence is the sum of the embeddings of its words. 
 * //In this project is applied to BabelNet embeddings so, all tokens are considered.
 * Non-relevant tokens have already been eliminated by the BabelNetID annotation. 
 * 
 * @author cristina
 * @since Jan 24, 2017
 */
public class SentenceW2VSimCalculator {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (SentenceW2VSimCalculator.class.getSimpleName());

	/** Similarity measure to be calculated	 */
	private String measure;
	
	/** Constructor	 */
	public SentenceW2VSimCalculator(String mesure) {
		setMeasure(this.measure);
	}
		  

	/**
	 * Parses the command line arguments
	 * 	
	 * @param args
	 * 			Command line arguments 
	 * @return
	 */
	private static CommandLine parseArguments(String[] args)
	{	
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cLine = null;
		Options options= new Options();
		CommandLineParser parser = new BasicParser();

		options.addOption("s", "source", true, 
				"File with the vectors for the source sentences");		
		options.addOption("t", "target", true, 
				"File with the vectors for the target sentences");		
		options.addOption("e", "embeddings", true, 
				"File with the word embeddings in w2v format");		
		options.addOption("m", "measure", true, 
				"Similarity measure to use [cosine(default)/...]");		
		options.addOption("o", "output", true, 
				"Output file to store the similarities");		
		options.addOption("h", "help", false, "This help");

		try {			
		    cLine = parser.parse( options, args );
		} catch( ParseException exp ) {
			logger.error( "Unexpected exception :" + exp.getMessage() );			
		}	
		
		if (cLine.hasOption("h")) {
			formatter.printHelp(SentenceW2VSimCalculator.class.getSimpleName(),options);
			System.exit(0);
		}

		if (!cLine.hasOption("t") || !cLine.hasOption("s") ) {
			logger.error("Please, specify a file with the source sentences,"
					+ "a file with the target sentences and the embeddings. "
					+ "Source and target must be aligned.\n");
			formatter.printHelp(SentenceW2VSimCalculator.class.getSimpleName(),options);
			System.exit(1);
		}		

		return cLine;		
	}

	
	/**
	 * Main function to run the class, serves as example
	 * 
	 * @param args
	 *      -s Input file with the vectors for the source sentences
	 *      -t Input file with the vectors for the target sentences
	 *      -e Input file with the embeddings in w2v format
	 *      -o Output file to store the similarities
	 *      -m Similarity measure to use
	 */
	public static void main(String[] args) {
		
		CommandLine cLine = parseArguments(args);
		// Input files
		File sourceFile = new File(cLine.getOptionValue("s"));
		File targetFile = new File(cLine.getOptionValue("t"));
		File embeddings = new File(cLine.getOptionValue("e"));
		// Output file
		String outputFile = cLine.getOptionValue("o");
		String measure;
		if (cLine.hasOption("m")){
			measure = cLine.getOptionValue("m");			
		} else{
			measure = "cosine";
		}
		// Step1: convert the files with the text sentences into files with
		//        vector representations for those sentences
		// There is no need to define an object and measure as non-static?
		SentenceW2VSimCalculator w2vSC = new SentenceW2VSimCalculator(measure); 

		// Step2: use VectorsSimCalculator to create a file with the similarities
		//        from the files created by Step1
		VectorsSimCalculator VSC = new VectorsSimCalculator(measure); 
		try {
			VSC.calculateSimilarities(sourceFile, targetFile, outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/* getters and setters*/
	
	public  String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		measure = this.measure;
	}

}
