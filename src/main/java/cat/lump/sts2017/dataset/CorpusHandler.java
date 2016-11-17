package cat.lump.sts2017.dataset;

import java.io.File;

/**
 * This class gives access to the different corpus locations,
 * which depend on the machine. 
 * 
 * It contains just hard-coded locations. 
 * TODO define how to setup a different location depending on the machine. 
 * Options: 
 * <ul>
 * <li /> OS
 * <li /> User name
 * <li /> Parameter
 * <ul>
 * @author albarron
 *
 */
public class CorpusHandler {

  /** Location of all the data files concerning SemEval Task 1 */
  public static final String STS_CORPUS_PATH = "/data/alt/corpora/semeval2017/task1";
  
  /** Location of the Arabic data files (incl. en-ar and ar-ar) */
  public static final String STS_CORPUS_PATH_ARABIC = 
      String.format("%s%ssts-2017-ar/Ar_STS", STS_CORPUS_PATH, File.separator);
  
  public static final String STS_CORPUS_PATH_ARABIC_MSRpar = 
      String.format("%s%sar.STS.MSRpar.txt", STS_CORPUS_PATH_ARABIC, File.separator);
  
  public static final String STS_CORPUS_PATH_ARABIC_SMTeuroparl = 
      String.format("%s%sar.STS.SMTeuroparl.txt", STS_CORPUS_PATH_ARABIC, File.separator);
  
  public static final String STS_CORPUS_PATH_ARABIC_MSRvid = 
      String.format("%s%sar.STS.MSRvid.txt", STS_CORPUS_PATH_ARABIC, File.separator);
  
                  
  
  
}
