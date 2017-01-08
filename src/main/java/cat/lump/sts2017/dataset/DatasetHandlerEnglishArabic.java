package cat.lump.sts2017.dataset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
 * @since Jan 8th, 2017 
 */
public class DatasetHandlerEnglishArabic extends DatasetHandlerSingle  {

  
  private static final Map<String, String> AVAILABLE_CORPORA;
  static {
      Map<String, String> aMap = new LinkedHashMap<String, String>();
      aMap.put("MSRpar",     "En_Ar_STS/en_ar.STS.MSRpar.txt");
      aMap.put("MSRvid",     "En_Ar_STS/en_ar.STS.MSRvid.txt");
      aMap.put("SMTeuroparl", "En_Ar_STS/en_ar.STS.SMTeuroparl.txt");
      AVAILABLE_CORPORA = Collections.unmodifiableMap(aMap);
  }

  
  
  
//  private final Map<AVAILABLE_CORPORA_IDS, String> AVAILABLE_CORPORA;
  
  private static final String LAN = "en_ar";
  
  public DatasetHandlerEnglishArabic(String basePath) {
    super(AVAILABLE_CORPORA.keySet(), LAN, basePath);
  }
  
  public List<String> getInstances() throws IOException {
    CHK.CHECK(! ACTIVATED_CORPORA_IDS.isEmpty(), "No corpus was selected. Nothing to do");
    List<String> instances = new ArrayList<String>();
    for (String id : ACTIVATED_CORPORA_IDS) {
      instances.addAll(
          Arrays.asList(
              FileIO.fileToLines(
                  new File(getInputFileFullPath(AVAILABLE_CORPORA.get(id)))
              )
          )
      );
    }
    return instances;
  }
  
//  public String next() {
//    //TODO
//    return null;
//  }
  
  

  

  
//  public String[] getArrayOfInstances() {
//    return null;
//  }
  
  
}
  
  
  
