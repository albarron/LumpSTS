package cat.lump.sts2017.babelNet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cat.lump.aq.basics.check.CHK;
import cat.lump.aq.basics.io.files.FileIO;
import cat.lump.aq.basics.log.LumpLogger;
import cat.lump.sts2017.lumpConfig;

import it.uniroma1.lcl.babelnet.BabelNet;


/**
 * 
 * Main class of the babelNet package devoted to include babelNet IDs in an
 * input corpus.
 * 
 * @author cristina
 * @since Dec 15, 2016
 */
public class DataIDAnnotator {

	/** Configuration file */
	private static Properties p;
	
	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (DataIDAnnotator.class.getSimpleName());

	public final static String lineSeparator = System.lineSeparator();


	/** Constructor */
	public DataIDAnnotator (String iniFile) {
		CHK.CHECK_NOT_NULL(iniFile);
		p = lumpConfig.getProperties(iniFile); 
		//logger = new LumpLogger(this.getClass().getCanonicalName());
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

		options.addOption("l", "language", true, 
					"Language of the input text (ar/en/es)");		
		options.addOption("i", "input", true, 
					"Input file to annotate (in Annotator format wpl)");		
		options.addOption("h", "help", false, "This help");
		options.addOption("c", "config", true,
		        	"Configuration file for the lumpSTS project");

		try {			
		    cLine = parser.parse( options, args );
		} catch( ParseException exp ) {
			logger.error( "Unexpected exception :" + exp.getMessage() );			
		}	
		
		if (cLine == null || !(cLine.hasOption("l")) ) {
			logger.error("Please, set the language\n");
			formatter.printHelp(DataIDAnnotator.class.getSimpleName(),options );
			System.exit(1);
		}		
		if (cLine.hasOption("h")) {
			formatter.printHelp(DataIDAnnotator.class.getSimpleName(),options );
			System.exit(0);
		}

		return cLine;		
	}

	
	/**
	 * Main function to run the class, serves as example
	 * 
	 * @param args
	 * 		-l Language of the input text (Arabic, English, Spanish)
	 *      -i Input file (in Annotator format wpl)
	 * 		-c Configuration file
	 */

	public static void main(String[] args) {
		CommandLine cLine = parseArguments(args);
		
		// Language and layer
		String language = cLine.getOptionValue("l");

		// Input file
		File input = new File(cLine.getOptionValue("i"));
		
		// Config file
		// Guessing if its an absolute or a relative path
		String conf = cLine.getOptionValue("c");
		String confFile;
		if (conf.startsWith(FileIO.separator)){
			confFile = conf;
		} else {
			confFile = System.getProperty("user.dir")+FileIO.separator+conf;
		}

		// Run
		DataIDAnnotator ann = new DataIDAnnotator (confFile);
		ann.annotate(input, language);

	}

	/**
	 * Does the actual annotation of the input file with the BabelNet ID
	 * 
	 * @param input
	 * 			input file to annotate
	 * 
	 */
	private void annotate(File input, String lang) {

		File output = new File(input+".bn");

		BabelNet bn = BabelNet.getInstance();

		// Initilise the writer
		StringBuffer sb = new StringBuffer();

		// Read the input
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(input);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNext()) {
		        String line = sc.nextLine();
		        String[] tokens = line.split("\\s+");
		        for (String token:tokens) {  
	        	
		        	String lemma = DataProcessor.readFactor3(token, 3);
		        	//System.out.println(lemma);
		        	String id = BabelNetQuerier.retrieveID(bn, lemma, lang);
		        }
		        
		    }
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close everything
		    if (inputStream != null) {
		        try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    if (sc != null) {
		        sc.close();
		        sb.append(lineSeparator);
		    }
		}


	}

	/** 
	 * Getters 
	 */
	public int getPropertyInt(String key){
		return Integer.valueOf(p.getProperty(key));
	} 
	
	public String getPropertyStr(String key){
		return p.getProperty(key);
	} 

}
