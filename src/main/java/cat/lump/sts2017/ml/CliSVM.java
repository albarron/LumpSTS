package cat.lump.sts2017.ml;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

/**
 * 
 * Cli for accessing Kelp's Regressor
 * 
 * @author albarron
 * @since Jan 28, 2018
 */

public class CliSVM extends CliMinimum {

	private int crossvalidation;
	private int gridsearch;
	private float c;
	private float gamma;
	
	public CliSVM(){
		super();
		loadOptions();
	}
	
	@Override
	protected void loadOptions()
	{
		super.loadOptions();
//		options.addOption("c", "crossvalidation", true, 
//				"number of cross-validation folds [0,...]");		
//		options.addOption("g", "gridsearch", true, 
//				"Grid search parameter optimisation [0/1]");		
		options.addOption("c", "c", true, 
        "value for c (default=1)");    
    options.addOption("g", "gamma", true, 
        "value for gamma (default=1)");    
		options.addOption("h", "help", false, "This help");
				
	}

	/**
	 * Parses the command line arguments
	 * 	
	 * @param args
	 * 			Command line arguments 
	 * @return
	 */
	@Override
	public CommandLine parseArguments(String[] args)
	{	
		super.parseArguments(args);
		CommandLine cLine = null;
		HelpFormatter formatter = new HelpFormatter();
		CommandLineParser parser = new BasicParser();


		try {			
		    cLine = parser.parse( options, args );
		} catch( ParseException exp ) {
			logger.error( "Unexpected exception :v" + exp.getMessage() );			
		}	
		
		if (cLine.hasOption("h")) {
			formatter.printHelp(CliSVM.class.getSimpleName(),options );
			System.exit(0);
		}
		if (cLine.hasOption("c"))	{
		  c = Float.valueOf(cLine.getOptionValue("c"));
		}  else {
		  c= 1;
		}
		
		if (cLine.hasOption("g")) {
		  gamma = Float.valueOf(cLine.getOptionValue("g"));
		} else {
		  gamma = 1;
		}

//		if (cLine.hasOption("g"))	{
//			gridsearch = Integer.valueOf((cLine.getOptionValue("g")));
//		} 
		
//		if (cLine.hasOption("f")) {
//		  logger.warn("Please, provide input file");
//	    formatter.printHelp("x", options);
////	    widthFormatter, command, header, options, footer, true);
//	      System.exit(1);		}

		return cLine;		
	}

	public float getC() {
	  return c;
	}
	
	public float getGamma() {
	  return gamma;
	}

//	/**
//	 * Get number of folds for cross-validation
//	 * @return
//	 */
//	public int getCV(){
//		return crossvalidation;		
//	}
//	
	/**
	 * Get gridsearch status
	 * @return
	 */
	public int getGS(){
		return gridsearch;		
	}

}
