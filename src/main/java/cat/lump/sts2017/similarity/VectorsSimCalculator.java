package cat.lump.sts2017.similarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cat.lump.aq.basics.algebra.vector.Vector;
import cat.lump.aq.basics.log.LumpLogger;


/**
 * Calculates the similarity between two vectors according to different measures.
 * In this case the two vectors correspond to context vectors of the encoder of a 
 * NMT system, but it should be irrelevant.
 * 
 * @author cristina
 * @since Jan 24, 2017
 */
public class VectorsSimCalculator {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (VectorsSimCalculator.class.getSimpleName());

	/** Similarity measure to be calculated	 */
	private String measure;
	
	/** Constructor	 */
	public VectorsSimCalculator(String measure) {
		setMeasure(measure);
	}
	
	/**
	 * Main method to calculate the similarity between the aligned vectors available
	 * in sourceFile and targetFile. Results are output in a third file output.
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @param output
	 * @throws IOException
	 */
	public void calculateSimilarities(File sourceFile, File targetFile, String output) 
			throws IOException {
		
		BufferedReader sources = new BufferedReader(new FileReader(sourceFile));
		BufferedReader targets = new BufferedReader(new FileReader(targetFile));
		try{
		    PrintWriter writer = new PrintWriter(output, "UTF-8");
		    String sentence1 = sources.readLine();
		    String sentence2 = targets.readLine();
		    logger.info("Reading representations for the first pair...");
		    int i=0;
			while (sentence1 != null) {
			    Vector v1 = VectorSTS.readVector(sentence1);
			    Vector v2 = VectorSTS.readVector(sentence2);
			    double sim = Utils.calculateMeasure(v1, v2, measure);
			    writer.print(sim +"\n");
			    sentence1 = sources.readLine();
			    sentence2 = targets.readLine();
			    i++;
			}
			writer.close();
			logger.info("Done with the "+i+" sentences.");
		} catch (Exception e) {
		      e.printStackTrace();
		}
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
		options.addOption("m", "measure", true, 
				"Similarity measure to use [cosine(default)/jaccard/KL/JS]");		
		options.addOption("o", "output", true, 
				"Output file to store the similarities");		
		options.addOption("h", "help", false, "This help");

		try {			
		    cLine = parser.parse( options, args );
		} catch( ParseException exp ) {
			logger.error( "Unexpected exception :" + exp.getMessage() );			
		}	
		
		if (cLine.hasOption("h")) {
			formatter.printHelp(VectorsSimCalculator.class.getSimpleName(),options );
			System.exit(0);
		}

		if (!cLine.hasOption("t") || !cLine.hasOption("s") ) {
			logger.error("Please, specify a file with the vectors for the source sentences"
					+ "and a file with the vectors for the target sentences. "
					+ "They must be aligned.\n");
			formatter.printHelp(VectorsSimCalculator.class.getSimpleName(),options );
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
	 *      -o Output file to store the similarities
	 *      -m Similarity measure to use
	 */
	public static void main(String[] args) {
		
		CommandLine cLine = parseArguments(args);
		// Input files
		File sourceFile = new File(cLine.getOptionValue("s"));
		File targetFile = new File(cLine.getOptionValue("t"));
		// Output file
		String outputFile = cLine.getOptionValue("o");
		String measure;
		if (cLine.hasOption("m")){
			measure = cLine.getOptionValue("m");			
		} else{
			measure = "cosine";
		} 
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
		this.measure = measure;
	}

}
