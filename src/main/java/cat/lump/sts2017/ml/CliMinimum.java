package cat.lump.sts2017.ml;

import java.io.File;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cat.lump.aq.basics.log.LumpLogger;


/**
 * 
 * General CliMinimum for all the ML classes
 * 
 * @author cristina
 * @since Jan 18, 2018
 */
public abstract class CliMinimum {

	/** Logger */
	protected static LumpLogger logger = null;
	
	/** File with the training data features and scores*/
	protected String training;
	
	/** File with the training data scores*/
	//protected String scores;

	/** File with the test data */
	protected String test;

	/** File with the model */
	protected File model;

	/** The options for the given CLI */
	protected Options options;

	/** Loads the logger*/
	public CliMinimum() {			
		logger = new LumpLogger(this.getClass().getCanonicalName());
		loadOptions();
	}

	protected void loadOptions() {
		options= new Options();		
		options.addOption("f", "training", true, 
				"Input file with the training features in csv format. Labels are expected in the first column.");		
		//options.addOption("s", "scores", true, 
		//			"Input file with the training scores.");		
		options.addOption("t", "test", true, 
				"Input file with the test data.");		
		options.addOption("m", "model", true, 
				"Previously trained model");		
		options.addOption("h", "help", false, "This help");
	}
	
	/**
	 * Parses the command line arguments
	 * 	
	 * @param args
	 * 			Command line arguments 
	 * @return
	 */
	public CommandLine parseArguments(String[] args)
	{	
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cLine = null;
		CommandLineParser parser = new BasicParser();


		try {			
		    cLine = parser.parse( options, args );
		} catch( ParseException exp ) {
			logger.error( "Unexpected exception: " + exp.getMessage() );			
		}	
		
		if (!cLine.hasOption("f") && !cLine.hasOption("t") && !cLine.hasOption("m"))	{
			logger.error("You need to specify training or test data");			
		} 

		if (cLine.hasOption("h")) {
			formatter.printHelp(CliMinimum.class.getSimpleName(),options );
			System.exit(0);
		}
		if (cLine.hasOption("f"))	{
			training = cLine.getOptionValue("f");
		} 

		//if (cLine.hasOption("s"))	{
		//	scores = new File(cLine.getOptionValue("s"));
		//} 

		if (cLine.hasOption("t"))	{
			test = cLine.getOptionValue("t");
		} 

		if (cLine.hasOption("m"))	{
			model = new File(cLine.getOptionValue("m"));
		} 

		if (cLine.hasOption("f")  && !cLine.hasOption("t") && !cLine.hasOption("m"))	{
			logger.warn("Preparing for training only, no test or model given.");			
		} 
		
		/*
		if (cLine.hasOption("f") && !cLine.hasOption("s"))	{
			logger.error("If you want to train the model you also need to give the scores.");			
		} 
		
		if (!cLine.hasOption("f") && cLine.hasOption("s"))	{
			logger.error("If you want to train the model you also need to give the features.");			
		} 
		*/
		if (cLine.hasOption("f") && cLine.hasOption("t") && cLine.hasOption("m"))	{
			logger.warn("You are giving a model, no training set needed. Proceeding with current model.");			
		} 

		return cLine;		
	}

	

	/**
	 * Get training file with the features
	 * @return
	 */
	public String getTraining(){
		return training;		
	}
	/**
	 * Get training file with the scores
	 * @return
	 */
	//public String getScores(){
	//	return scores;		
	//}
	/**
	 * Get test file
	 * @return
	 */
	public String getTest(){
		return test;		
	}
	/**
	 * Get file with the model
	 * @return
	 */
	public File getModel(){
		return model;		
	}
	
	
}
