package cat.lump.sts2017.similarity;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cat.lump.aq.basics.check.CHK;
import cat.lump.aq.basics.log.LumpLogger;
import cat.lump.sts2017.dataset.FeatureDumper;
import cat.lump.sts2017.dataset.StsBufferedReader;
import cat.lump.sts2017.dataset.StsInstance;

/**
 * Produces a comma-separated file with the length of the left and right
 * strings in number of tokens. No sophisticated pre-processor is applied. 
 * Just space-based splitting.
 * @author albarron
 *
 */
public class NumberOfTokens {
  
  private static final int FEATURE_NUMBER = 1;
  private static final String FEATURE_NAME = "tokens";
  
  private static LumpLogger logger = 
      new LumpLogger(NumberOfTokens.class.getSimpleName());
  
  
  private final File FILE;
  
  public NumberOfTokens(String input) {
    FILE = setFile(input);
//    this(input);
  }
  
  public int getLength(String str) {
    str = normalize(str);
    return str.split("\\s").length;
  }
  
  /**
   * The pre-processing for this similarity consists of space normalisation
   * only
   * @param str
   * @return
   *        str with spaces normalised.
   */
  private String normalize(String str) {
    return str.replaceAll("\\s+", " ").trim();    
  }
  
  private File setFile (String input) {
    File f = new File(input);
    CHK.CHECK(f.exists() && f.isFile() && f.canRead(), 
        String.format("The file %s does not exist or I cannot read it",
            f));
    return f;
  }

  protected static CommandLine loadOptions(String[] args){
    Options options = new Options();    
    
    options.addOption("f", "input", true, 
        "Input file");
    options.addOption("o", "output", true, 
        "Output File");
    
    HelpFormatter formatter = new HelpFormatter();
    int widthFormatter = 88;
    CommandLineParser parser = new BasicParser();
    CommandLine cLine = null;

    try {     
        cLine = parser.parse( options, args );
    } catch( ParseException exp ) {
      logger.error( "Unexpected exception: " + exp.getMessage() );      
    } 
    
    if(!cLine.hasOption("f")) {
      logger.warn("Please, provide (at least the input file");
    formatter.printHelp("x", options);
//    widthFormatter, command, header, options, footer, true);
      System.exit(1);
    }
    
    return cLine; 
  }
  
  
  public static void main(String[] args) throws IOException {
    CommandLine cLine = loadOptions(args);
    String inFile = cLine.getOptionValue("f");
    String ouFile = cLine.getOptionValue("o");
    
    String lan2;
    NumberOfTokens cns;
    
    cns = new NumberOfTokens(inFile);
    
    FeatureDumper fd = new FeatureDumper(ouFile, FEATURE_NUMBER, FEATURE_NAME);
    StsBufferedReader cr = new StsBufferedReader(inFile);

    String toks;
    int len1;
    int len2;
    for (StsInstance instance = cr.readInstance(); instance != null; instance = cr.readInstance()) {
      len1 = cns.getLength(instance.getText1());
      len2 = cns.getLength(instance.getText2());
//      System.out.format("%d,%d%n", len1, len2);
      fd.writeLine(String.format("%d,%d", len1, len2));
       
    }
    fd.close();
  }
  
}
