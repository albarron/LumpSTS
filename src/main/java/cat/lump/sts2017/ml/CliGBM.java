package cat.lump.sts2017.ml;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

/**
 * 
 * Cli for accessing GradientBoostedTreesH2O
 * 
 * @author cristina
 * @since Jan 18, 2018
 */

public class CliGBM extends CliMinimum {

	private int crossvalidation;
	private int gridsearch;
	
	public CliGBM(){
		super();
		loadOptions();
	}
	
	@Override
	protected void loadOptions()
	{
		super.loadOptions();
		options.addOption("c", "crossvalidation", true, 
				"number of cross-validation folds [0,...]");		
		options.addOption("g", "gridsearch", true, 
				"Grid search parameter optimisation [0/1]");		
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
			formatter.printHelp(CliGBM.class.getSimpleName(),options );
			System.exit(0);
		}
		if (cLine.hasOption("c"))	{
			crossvalidation = Integer.valueOf((cLine.getOptionValue("c")));
		} 

		if (cLine.hasOption("g"))	{
			gridsearch = Integer.valueOf((cLine.getOptionValue("g")));
		} 

		return cLine;		
	}

	

	/**
	 * Get number of folds for cross-validation
	 * @return
	 */
	public int getCV(){
		return crossvalidation;		
	}
	
	/**
	 * Get gridsearch status
	 * @return
	 */
	public int getGS(){
		return gridsearch;		
	}

}
