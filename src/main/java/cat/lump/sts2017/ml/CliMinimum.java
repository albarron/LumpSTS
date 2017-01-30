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
	protected String model;

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
		
		if (cLine.hasOption("h")) {
      formatter.printHelp(CliMinimum.class.getSimpleName(),options );
      System.exit(0);
    }

		boolean modelExists = false;

	  if (cLine.hasOption("m")) {
      model = cLine.getOptionValue("m");
      modelExists = (new File(model).isFile()) 
          ? true
          : false;
    } 
	  
	  if (cLine.hasOption("f"))  {
      training = cLine.getOptionValue("f");
    }
	  
	  if (cLine.hasOption("t")) {
      test = cLine.getOptionValue("t");
    } 
	  
	  // if (cLine.hasOption("s")) {
    //  scores = new File(cLine.getOptionValue("s"));
    //} 
	  
	  if (training == null && test == null) {
	    logger.error("No training nor test provided. Nothing to do"); 
      formatter.printHelp(CliMinimum.class.getSimpleName(),options );
      System.exit(1);
	  }
	  
	  if (model == null && training == null) {
	    logger.error("No model nor training provided. Nothing to do"); 
      formatter.printHelp(CliMinimum.class.getSimpleName(),options );
      System.exit(1);
	  }
	  
	  if (training != null && modelExists) {
	    logger.error("Training and model provided, but model exists. Nothing to do"); 
      formatter.printHelp(CliMinimum.class.getSimpleName(),options );
      System.exit(1);
	  }
	  
	  
	   
//	  if (model == null) {
//	    if (training == null) {
//	      logger.error("No model nor training provided. Nothing to do"); 
//	      formatter.printHelp(CliMinimum.class.getSimpleName(),options );
//	      System.exit(1);
//	    } else {
//	      logger.error("Training provided (and no model); assuming CV");
//	    }
//	  } else {
//	    if (modelExists) {
//	      if (training != null) {
//	        logger.error("I'm given training, but the model exists. Nothing to do"); 
//	        formatter.printHelp(CliMinimum.class.getSimpleName(),options );
//	        System.exit(1);
//	      } 
//	      if (test == null) {
//	        logger.error("I'm given the model, but no test. Nothing to do"); 
//          formatter.printHelp(CliMinimum.class.getSimpleName(),options );
//          System.exit(1);
//	      }
//	      
//	    } else {
//	      
//	      
//	    }
//	    
//	    
//	  }
//	  
//	  
//	  if  (training == null && test == null) {
//	    logger.error("Please provive train or test"); 
//      formatter.printHelp(CliMinimum.class.getSimpleName(),options );
//      System.exit(1);
//	  }
//	  
//	  // There is no model and no training was provided 
//	  if (training == null) {
//	    if (modelExists) {
//	      if (test == null)  {
//	        logger.error("I have a model, bun no test to apply it to");
//	         formatter.printHelp(CliMinimum.class.getSimpleName(),options );
//	         System.exit(1);
//	      } else {
//	        logger.info("Model and test files set. Proceeding with the predictions.");  
//	      }
//	    }
//	  } else {
//      if (model == null) {
//        logger.info("Training file set. Proceeding CV.");
//      }
//    }
//	    logger.error("No model exists and no training data provided"); 
//	    formatter.printHelp(CliMinimum.class.getSimpleName(),options );
//      System.exit(1);
//	    }
//	  } else {	  
//  		
//
//	  }
//
//	  //TODO I'd remove this info from here to whenever the training actually starts. 
//		if (cLine.hasOption("f")  && !modelExists)	{
//			logger.info("Training starting.");			
//		} 
//		
//		/*
//		if (cLine.hasOption("f") && !cLine.hasOption("s"))	{
//			logger.error("If you want to train the model you also need to give the scores.");			
//		} 
//		
//		if (!cLine.hasOption("f") && cLine.hasOption("s"))	{
//			logger.error("If you want to train the model you also need to give the features.");			
//		} 
//		*/


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
	public String getModel(){
		return model;		
	}
	
	
}
