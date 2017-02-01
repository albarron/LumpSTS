package cat.lump.sts2017.ml;



import java.util.Locale;

import cat.lump.aq.basics.log.LumpLogger;

public class Csv2Libsvm {

  /** Logger */
  private static LumpLogger logger = 
      new LumpLogger (Csv2Libsvm.class.getSimpleName());
  

  
  /**
   * Main function to run the class, serves as example
   * 
   * @param args
   *    -f Input file with the csv data 
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    
    System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "WARN");
    CliSVM cli = new CliSVM();
    cli.parseArguments(args);
    
    String training = null;
 

    training = cli.getTraining();
    
    // To be sure decimals will be points and no commas:
    Locale.setDefault(new Locale("en"));
//        
    if (training!=null){
      String trainLibsvm = Utils.csv2libsvm(training);
      logger.info(String.format("File %s saved", trainLibsvm));

    }
    
    
  }

  
  
}
