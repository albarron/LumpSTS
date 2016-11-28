package cat.lump.sts2017.prepro;

import java.io.File;
import java.util.Properties;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;

import cat.lump.aq.basics.check.CHK;
import cat.lump.aq.basics.io.files.FileIO;
import cat.lump.aq.basics.log.LumpLogger;
import cat.lump.sts2017.lumpConfig;

/**
 * Class to annotate raw text. Currently supports tokenisation and lemmatisation of
 * Arabic, English, Spanish
 *   
 * @author cristina
 * @since Nov 28, 2016
 */
public class Annotator {


	/** Language */
	private String lang;
	/** Layer of annotation */
	private String layer;
	

	/** Input file/sentence */
	private File input;
	private String sentence;

	/** Configuration file */
	private static Properties p;
	
	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (Annotator.class.getSimpleName());

	
	/** Constructors */
	public Annotator (String language, String layer, String iniFile) {
		CHK.CHECK_NOT_NULL(language);
		CHK.CHECK_NOT_NULL(layer);
		CHK.CHECK_NOT_NULL(iniFile);
		this.lang = language;
		this.layer = layer;
        p = lumpConfig.getProperties(iniFile); 
		logger = new LumpLogger(this.getClass().getCanonicalName());

	}
	
	public Annotator (String language, String layer, String iniFile, File input) {
		this(language, layer, iniFile);
		this.input = input;
		launchAnnFile();
	}

	public Annotator (String language, String layer, String iniFile, String sentence) {
		this(language, layer, iniFile);
		this.sentence = sentence;
		launchAnnSentence();
	}
	
	private void launchAnnFile() {

		if (layer.equalsIgnoreCase("tok")){
			Tokeniser tok = new Tokeniser(lang, input);
		}
		
	}
	
	private void launchAnnSentence() {

		if (layer.equalsIgnoreCase("tok")){
			Tokeniser tok = new Tokeniser(lang, sentence);
		}
		
	}
	
	private static CommandLine parseArguments(String[] args)
	{	
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cLine = null;
		Options options= new Options();
		CommandLineParser parser = new BasicParser();

		options.addOption("l", "language", true, 
					"Language of the input text (ar/en/es)");		
		options.addOption("a", "annotation", true, 
					"Annotation layer (tok, lem)");		
		options.addOption("i", "input", true, 
				"Input file to annotate -one sentence per line-");		
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
			System.exit(1);
		}		
		if (!(cLine.hasOption("a")) ) {
			logger.error("Please, set the desired annotation layer\n");
			System.exit(1);
		}		
		if (cLine.hasOption("h")) {
			formatter.printHelp(Annotator.class.getSimpleName(),options );
			System.exit(0);
}

		return cLine;		
	}

	/*
	 * Main function to run the class, serves as example
	 * 
	 * @param args
	 * 		-l Language of the input text (Arabic, English, Spanish)
	 * 		-a Annotation layer (tokens, lemmas)
	 * 		-c Configuration file
	 */
	public static void main(String[] args){
		CommandLine cLine = parseArguments(args);
		
		// Language and layer
		String language = cLine.getOptionValue("l");
		String layer = cLine.getOptionValue("a");

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

		Annotator ann = new Annotator (language, layer, confFile, input);
	}


	/* 
	 * Getters 
	 */
	public int getPropertyInt(String key){
		return Integer.valueOf(p.getProperty(key));
	} 
	
	public String getPropertyStr(String key){
		return p.getProperty(key);
} 
}
