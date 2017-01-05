package cat.lump.sts2017.babelNet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.google.common.collect.Multimap;

import cat.lump.aq.basics.io.files.FileIO;
import cat.lump.aq.basics.log.LumpLogger;
import cat.lump.sts2017.prepro.Normaliser;
import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelNetUtils;
import it.uniroma1.lcl.babelnet.data.BabelPOS;
import it.uniroma1.lcl.jlt.util.Language;
import it.uniroma1.lcl.jlt.util.ScoredItem;

/**
 * 
 * Main class of the babelNet package devoted to include babelNet IDs in an
 * input corpus.
 * 
 * @author cristina
 * @since Dec 15, 2016
 */
public class DataIDAnnotator {
	
	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (DataIDAnnotator.class.getSimpleName());

	/** Conversion into BabelNet tags*/
	private static Map<String, BabelPOS> posMapping = null;
	
	// This is somewhere else
	public final static String lineSeparator = System.lineSeparator();


	/** Constructor */
	public DataIDAnnotator (String language) {
		PoSFactory pf= new PoSFactory();
		posMapping = pf.getPoSMapper(language);
	
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
		//options.addOption("c", "config", true,
		//        	"Configuration file for the lumpSTS project");

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
		/*String conf = cLine.getOptionValue("c");
		String confFile;
		if (conf.startsWith(FileIO.separator)){
			confFile = conf;
		} else {
			confFile = System.getProperty("user.dir")+FileIO.separator+conf;
		}*/

		// Run
		DataIDAnnotator ann = new DataIDAnnotator (language);
		ann.annotate(input, language);

	}

	/**
	 * Does the actual annotation of the input file with the BabelNet ID
	 * 
	 * @param input
	 * 			input file to annotate
	 * 
	 */
	private void annotate(File input, String language) {

		BabelNet bn = BabelNet.getInstance();

		// Initilise the writer
		File output = new File(input+"b");
		FileIO.deleteFile(output);
	    FileWriter fw = null;
	    BufferedWriter bw = null;
		try {
			fw = new FileWriter(output, true);
			bw = new BufferedWriter(fw);
			bw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		// Read the input
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(input);
		    sc = new Scanner(inputStream, "UTF-8");
		    int i = 0;
		    while (sc.hasNext()) {
		        String line = sc.nextLine();
		        String[] tokens = line.split("\\s+");
		        for (String token:tokens) {  
		        	String id = "-";
		        	String lemma = DataProcessor.readFactor3(token, 3);
		        	String pos = DataProcessor.readFactor3(token, 2);
		        	String word = DataProcessor.readFactor3(token, 1);
		        	// This is a patch to solve a problem seen in Arabic where some tokens have not been annotated
		        	// TODO fix the annotation
		        	if (lemma==null || pos==null){
			    		bw.append(token+"|-|-|"+id+" ");
			    		continue;
		        	}
		    		if (language.equalsIgnoreCase("en")) {
		    		    id = getBNID_en(bn, lemma, pos);	
		    		} else if (language.equalsIgnoreCase("es")) {
		    		    id = getBNID_es(bn, lemma, pos);	
		    		} else if (language.equalsIgnoreCase("ar")) {
		    		    id = getBNID_ar(bn, lemma, pos);	
		    		} 
	        		bw.append(word+"|"+pos+"|"+lemma+"|"+id+" ");
		        }
	        	bw.newLine();
		        // Write every 10000 lines
		        if (i%10000==0){
		        	bw.flush();
		        }
		        i++;

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
		        try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}

	}

	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in English
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_en(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.EN;    	
    	
    	if(pos.equalsIgnoreCase("NNP") || pos.equalsIgnoreCase("NNPS")){ //NEs
    		ne = true;
    	} else if(PoSAccept.NEG_EN.contains(lemma)){                     //Negation      		
    		return NEG;
    	} else if(!PoSAccept.POS_EN_ACC.contains(pos)) {                 //Non-content PoS
    		return id;
    	}
     	
		BabelPOS bnPos = posMapping.get(pos);
		
    	if (bnPos == null){
    		return id;
    	} else {
    	   // NE are adding too much noise
    	   //id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang, ne);
    	   id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang);
    	}
		return id;
	}

	
	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Spanish
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_es(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.ES;

    	String pos2chars = pos.substring(0, 1); 
    	if(pos2chars.equalsIgnoreCase("np")){                      //NEs
    		ne = true;
    	} else if(pos2chars.equalsIgnoreCase("rn")){               //Negation
    		return NEG;    		
    	} else if(!PoSAccept.POS_ES_ACC.contains(pos2chars)) {     //Non-content PoS
    		return id;
    	}
    	
		BabelPOS bnPos = posMapping.get(pos2chars); 
    	if (bnPos == null){
    		return id;
    	} else {
     	   // NE are adding too much noise
     	   //id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang, ne);
     	   id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang);
    	}
		return id;
	}

	
	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Arabic
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_ar(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.AR;

    	if(pos.equalsIgnoreCase("noun_prop")){               //NEs
    		ne = true;    		
    	} else if(pos.equalsIgnoreCase("part_neg")){         //Negation
    		return NEG;
    	} else if(!PoSAccept.POS_AR_ACC.contains(pos)) {     //Non-content PoS
    		return id;
    	}
    	    	    	
		BabelPOS bnPos = posMapping.get(pos); 
    	if (bnPos == null){
    		return id;
    	} else {
    		String lemmaClean = lemma.replaceAll("_\\d+", "");   //replaceAll but it should only happen at the end
    		lemmaClean = Normaliser.removeNonCharacters(lemmaClean);
    		lemmaClean = Normaliser.removeDiacriticsAR(lemmaClean);

     	    // NE are adding too much noise
     	    //id = BabelNetQuerier.retrieveID(bn, bnPos, lemmaClean, lang, ne);
    	    id = BabelNetQuerier.retrieveID(bn, bnPos, lemmaClean, lang);
    	}
		return id;
	}

	
	/**
	 * Get the top translation of a lemma
	 * DON'T USE
	 * 
	 * @param lemma
	 * @return
	 */
	private String get1stTrad_en(String lemma) {
		
		Multimap<Language, ScoredItem<String>> tradsAll = null;
		try {
			tradsAll = BabelNetUtils.getTranslations(Language.EN, lemma);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collection<ScoredItem<String>> trads = tradsAll.get(Language.ES);
   
		//System.out.println(trads);
		TreeMap<Double, String> mapTrads = new TreeMap<Double, String>();
		for (ScoredItem<String> trad : trads){
			if (!mapTrads.containsKey(-trad.getScore())) {
				mapTrads.put(-trad.getScore(), trad.getItem());}
		}
		//System.out.println(mapTrads.lastKey());
		//System.out.println(mapTrads.firstKey());
		return mapTrads.firstEntry().toString();
	}
	
}
