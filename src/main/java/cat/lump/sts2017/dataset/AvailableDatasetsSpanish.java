package cat.lump.sts2017.dataset;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Spanish dataset is available for different editions. It includes:
 * 
 * 2014
 * <ul>
 * <li /> STS.input.news.txt
 * <li /> STS.input.wikipedia.txt
 * </ul>
 * 2015
 * <ul>
 * <li /> STS.input.newswire.txt
 * <li /> STS.input.wikipedia.txt
 * </ul>
 * 
 * Different to the Arabic dataset, the files contain only two text columns 
 * (the ones to be compared). The gold label is included in another file with
 * 'gs' instead of 'input'. 
 * 
 * This class simply returns a map with the default subpaths to these files.
 * 
 * @author albarron
 * @since Jan 5th, 2017 
 */
public class AvailableDatasetsSpanish implements AvailableDatasets {

  
  public Map<String, String> getAvailableCorpora() {
    Map<String, String> corpora = new LinkedHashMap<String, String>();
    corpora.put("2014_news",      "previous_years/sts2014/spanish/STS.input.news.txt");
    corpora.put("2014_wikipedia", "previous_years/sts2014/spanish/STS.input.wikipedia.txt");
    corpora.put("2015_newswire",  "previous_years/sts2015/spanish/STS.input.newswire.txt");
    corpora.put("2015_wikipedia", "previous_years/sts2015/spanish/STS.input.wikipedia.txt");
    return corpora;
  }
}
