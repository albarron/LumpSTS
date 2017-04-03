package cat.lump.sts2017.similarity;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cat.lump.aq.basics.check.CHK;
import cat.lump.aq.basics.log.LumpLogger;
import cat.lump.ir.sim.cl.len.LengthFactors;
import cat.lump.ir.sim.cl.len.LengthModelEstimate;
import cat.lump.sts2017.dataset.FeatureDumper;
import cat.lump.sts2017.dataset.StsBufferedReader;
import cat.lump.sts2017.dataset.StsInstance;

public class LenFactorSimilarity {
  
  protected static final int FEATURE_NUMBER = 1;
  protected static final String FEATURE_NAME = "len";
  
  private static LumpLogger logger = 
      new LumpLogger(LenFactorSimilarity.class.getSimpleName());
  
  
  private final File FILE;
  
  protected final LengthModelEstimate LE;
  
  public LenFactorSimilarity(String input, Locale lan) {
    this(input, lan, lan);
  }
  
  public LenFactorSimilarity(String input, Locale lan1, Locale lan2) {
    FILE = setFile(input);
//    LAN1 = lan1;
//    LAN2 = lan2;
    LE= new LengthModelEstimate();
    String pair = getLanguagePair(lan1, lan2);
    LE.setMuSigma(
        LengthFactors.getMean(pair),
        LengthFactors.getSD(pair)
    );
    logger.info(String.format("Length model for pair %s loaded", pair));
  }
  
  private String getLanguagePair(Locale lan1, Locale lan2) {
    return String.format("%s-%s", lan1.toString(), lan2.toString());
  }
  
  public double computeSimilarity(String str1, String str2) {
//    str1 = normalize(str1);
//    str2 = normalize(str2);
    
//    System.out.println(str1);
//    System.out.println(str2);

    double sim = LE.lengthFactor(
        getLength(str1), 
        getLength(str2)
     );
    return sim;
  }
  
  protected int getLength(String str) {
    str = normalize(str);
    return str.length();
  }
  
  
  /**
   * The pre-processing for this similarity consists of space normalisation
   * only
   * @param str
   * @return
   *        str with spaces normalised.
   */
  protected String normalize(String str) {
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
    options.addOption("l", "lan1", true, 
        "language (en, es, ar)");
    options.addOption("m", "lan2", true,
        "second language (en, es, ar; optional)");
    
    HelpFormatter formatter = new HelpFormatter();
    int widthFormatter = 88;
    CommandLineParser parser = new BasicParser();
    CommandLine cLine = null;

    try {     
        cLine = parser.parse( options, args );
    } catch( ParseException exp ) {
      logger.error( "Unexpected exception: " + exp.getMessage() );      
    } 
    
    if(!cLine.hasOption("f") || !cLine.hasOption("o") || !cLine.hasOption("l")) {
      logger.warn("Please, provide input/output file and at least one language");
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

    String lan1 = cLine.getOptionValue("l");
        
    String lan2;
    LenFactorSimilarity cns;

    if (cLine.hasOption("m")) {
      lan2 = cLine.getOptionValue("m");
      cns = new LenFactorSimilarity(inFile, new Locale(lan1), new Locale(lan2));
    } else {
      cns = new LenFactorSimilarity(inFile, new Locale(lan1));
    }
    FeatureDumper fd = new FeatureDumper(ouFile, FEATURE_NUMBER, FEATURE_NAME);
    StsBufferedReader cr = new StsBufferedReader(inFile);

    double sim;
    for (StsInstance instance = cr.readInstance(); instance != null; instance = cr.readInstance()) {
      sim = cns.computeSimilarity(instance.getText1(), instance.getText2());
//      System.out.println(sim);
      fd.writeLine(String.valueOf(sim));
       
    }
    fd.close();
  }
  
}
