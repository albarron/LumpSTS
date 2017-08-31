package cat.lump.sts2017.similarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cat.lump.aq.basics.algebra.vector.Vector;
import cat.lump.aq.basics.log.LumpLogger;


/**
 * 
 * @author cristina
 * @since Feb 2, 2017
 */
public class Sentence2W2Vrepresentation {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (Sentence2W2Vrepresentation.class.getSimpleName());
	
	/** Control token added to the word embeddings*/
	public static final String CONTROL = "4sizeEmb";

	/** Constructor	 */
	public Sentence2W2Vrepresentation() {
	}
		  
	/**
	 * Scans the input files to get each sentence, calculates its semantic representation and the
	 * similarity among every pair.
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @param embeddings
	 * @param output
	 */
	public void sentence2representation(File sourceFile, String embeddings, String output) {
		
		logger.info("Loading the embeddings...");
        Map<String, String> embeddingsMap = new HashMap<String, String>();
        embeddingsMap = loadW2Vembeddings(embeddings);
        
		BufferedReader sources = null;
		try {
			sources = new BufferedReader(new FileReader(sourceFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// That was for storing the intermediate vector files
//		String vecsSource = sourceFile.toString() + ".vec";
//		String vecsTarget = targetFile.toString() + ".vec";
		try{
		    //PrintWriter writerSource = new PrintWriter(vecsSource, "UTF-8");
		    //PrintWriter writerTarget = new PrintWriter(vecsTarget, "UTF-8");
		    PrintWriter writer = new PrintWriter(output, "UTF-8");

		    logger.info("Reading the first sentence...");
		    String sentence1 = sources.readLine();
		    int i=0;
			while (sentence1 != null) {
				Vector v1 = getSentenceVector(sentence1, embeddingsMap);
				String sim = VectorSTS.Vector2String(v1);
			    writer.print(sim +"\n");
			    sentence1 = sources.readLine();
			    i++;
			}
			//writerSource.close();
			//writerTarget.close();
			writer.close();
			logger.info("Done with the "+i+" sentences.");

		} catch (Exception e) {
		      e.printStackTrace();
		}

	}

	/**
	 * Get the semantic representation of a sentence as the sum of the representation of its words
	 * 
	 * @param sentence
	 * @param embeddingsMap
	 * @return
	 */
	private Vector getSentenceVector(String sentence, Map<String, String> embeddingsMap) {
	    String[] words = sentence.split("\\s+");
	    
	    // We use the control element to know the size of the embeddings (300 dim, 500 dim etc)
	    Vector vtmp = VectorSTS.readVector(embeddingsMap.get(CONTROL));
	    Vector v = new Vector(new float[vtmp.length()]); //ja s'inicialitza a 0 per default
	    
	    for (String word : words){
	    	String vectorconcatenated = embeddingsMap.get(word);
	    	if (vectorconcatenated == null){
	    		continue;
	    	}
			v.addEquals(VectorSTS.readVector(vectorconcatenated));
	    }
	    return v;
	}


	/**
	 * Reads a w2v file and stores the pairs (word,vector) in a HashMap. Both elements are Strings.
	 * 
	 * @param embeddings
	 * @return
	 */
	private Map<String, String> loadW2Vembeddings(String embeddings) {

	    Map<String, String> embeddingsMap = new HashMap<String, String>();
	    BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(embeddings));
		} catch (FileNotFoundException e) {
			logger.error("The embedding's file "+embeddings+" is not available.");
			e.printStackTrace();
		}
		String parts[] = null;
	    try {
		    String line = in.readLine(); // first line is discarded because it is just a description in w2v files
	    	while ((line = in.readLine()) != null) {
			      parts = line.split("\\s+",2);
			      embeddingsMap.put(parts[0], parts[1]);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	    // We add a phantom element just to be able to read a known component
	    embeddingsMap.put(CONTROL, parts[1]);
	    
	    return embeddingsMap;
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
		options.addOption("e", "embeddings", true, 
				"File with the word embeddings in w2v format");		
		options.addOption("o", "output", true, 
				"Output file to store the similarities");		
		options.addOption("h", "help", false, "This help");

		try {			
		    cLine = parser.parse( options, args );
		} catch( ParseException exp ) {
			logger.error( "Unexpected exception :" + exp.getMessage() );			
		}	
		
		if (cLine.hasOption("h")) {
			formatter.printHelp(Sentence2W2Vrepresentation.class.getSimpleName(),options);
			System.exit(0);
		}

		if (!cLine.hasOption("s") ) {
			logger.error("Please, specify a file with the source sentences "
					+ " and the embeddings.\n");
			formatter.printHelp(Sentence2W2Vrepresentation.class.getSimpleName(),options);
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
		String embeddings = cLine.getOptionValue("e");
		// Output file
		String outputFile = cLine.getOptionValue("o");

		Sentence2W2Vrepresentation w2vSC = new Sentence2W2Vrepresentation(); 
		w2vSC.sentence2representation(sourceFile, embeddings, outputFile);
	}




}
