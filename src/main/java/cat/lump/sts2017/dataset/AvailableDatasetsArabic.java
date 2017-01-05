package cat.lump.sts2017.dataset;

import java.util.LinkedHashMap;
import java.util.Map;

public class AvailableDatasetsArabic implements AvailableDatasets {

  
  public Map<String, String> getAvailableCorpora() {
    Map<String, String> corpora = new LinkedHashMap<String, String>();
    corpora.put("MSRpar",     "Ar_STS/ar.STS.MSRpar.txt");
    corpora.put("MSRvid",     "Ar_STS/ar.STS.MSRvid.txt");
    corpora.put("SMTeuroparl", "Ar_STS/ar.STS.SMTeuroparl.txt");
    return corpora;
  }
}
