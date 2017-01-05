package cat.lump.sts2017.dataset;

import java.util.LinkedHashMap;
import java.util.Map;

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
public class DatasetHandlerArabic extends DatasetHandlerSingle implements AvailableDatasets {

  private final Map<String, String> AVAILABLE_CORPORA;
  
  private final String LANGUAGE = "ar";
  
  public DatasetHandlerArabic() {
    super();
    AVAILABLE_CORPORA = new LinkedHashMap<String, String>();
    AVAILABLE_CORPORA.put("MSRpar",     "Ar_STS/ar.STS.MSRpar.txt");
    AVAILABLE_CORPORA.put("MSRvid",     "Ar_STS/ar.STS.MSRvid.txt");
    AVAILABLE_CORPORA.put("SMTeuroparl", "Ar_STS/ar.STS.SMTeuroparl.txt");
  }
  
  
  public Map<String, String> getAvailableCorpora() {    
    return AVAILABLE_CORPORA;
  }
  
  public String getLanguage() {
    return LANGUAGE;
  }
  
  public String[] getArrayOfInstances() {
    return null;
  }
  
  
}
