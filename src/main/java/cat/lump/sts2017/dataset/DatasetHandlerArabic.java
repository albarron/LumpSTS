package cat.lump.sts2017.dataset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cat.lump.aq.basics.check.CHK;
import cat.lump.aq.basics.io.files.FileIO;

/**
 * The Arabic dataset is only available for the 2017 edition. It includes:
 * <ul>
 * <li /> MSRpar
 * <li /> MSRvid
 * <li /> SMTeuroparl
 * </ul>
 * 
 * This class simply returns a map with the default subpaths to these files.
 * 
 * @author albarron
 * @since Jan 5th, 2017 
 */
public class DatasetHandlerArabic extends DatasetHandlerSingle  {

  static {
      Map<String, String> aMap = new LinkedHashMap<String, String>();
      aMap.put("MSRpar",     "Ar_STS/ar.STS.MSRpar.txt");
      aMap.put("MSRvid",     "Ar_STS/ar.STS.MSRvid.txt");
      aMap.put("SMTeuroparl", "Ar_STS/ar.STS.SMTeuroparl.txt");
      AVAILABLE_CORPORA = Collections.unmodifiableMap(aMap);
  }

  private final int SCORE_INDEX = 1;
  private final int TXT1_INDEX = 2;
  private final int TXT2_INDEX = 3;
  
  
//  private final Map<AVAILABLE_CORPORA_IDS, String> AVAILABLE_CORPORA;
  
  private static final String LAN = "ar";
  
  public DatasetHandlerArabic(String basePath) {
    super(AVAILABLE_CORPORA.keySet(), LAN, basePath);
  }
  
  public List<String> getTexts() throws IOException {
    CHK.CHECK(! ACTIVATED_CORPORA_IDS.isEmpty(), "No corpus was selected. Nothing to do");
    List<String> instances = new ArrayList<String>();
    for (String id : ACTIVATED_CORPORA_IDS) {
      String[] crudeLines = FileIO.fileToLines(
                    new File(getInputFileFullPath(AVAILABLE_CORPORA.get(id))));
      for (int i = 0 ; i < crudeLines.length ; i++) {
        instances.add(getTexts(crudeLines[i]));
      }
     
    }
    return instances;
  }
  
  public List<String> getScores() throws IOException {
    CHK.CHECK(! ACTIVATED_CORPORA_IDS.isEmpty(), "No corpus was selected. Nothing to do");
    List<String> instances = new ArrayList<String>();
    for (String id : ACTIVATED_CORPORA_IDS) {
      String[] crudeLines = FileIO.fileToLines(
                    new File(getInputFileFullPath(AVAILABLE_CORPORA.get(id))));
      for (int i = 0 ; i < crudeLines.length ; i++) {
        instances.add(getScore(crudeLines[i]));
      }
     
    }
    return instances;
  }
  
  private String getTexts(String crudeLine) {
    String[] spl = crudeLine.split("\t");
    return String.format("%s\t%s", spl[TXT1_INDEX], spl[TXT2_INDEX]);
   }
  
  private String getScore(String crudeLine) {
    return crudeLine.split("\t")[SCORE_INDEX];
  }
    
}
  
  
  
