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
public class DatasetHandlerSpanish extends DatasetHandlerSingle {

 
  private static final Map<String, String> AVAILABLE_CORPORA;
  private static final Map<String, String> GOLD_CORPORA;
  static {
    Map<String, String> aMap = new LinkedHashMap<String, String>();
    
    aMap.put("2014_news",      "previous_years/sts2014/spanish/STS.input.news.txt");
    aMap.put("2014_wikipedia", "previous_years/sts2014/spanish/STS.input.wikipedia.txt");
    aMap.put("2015_newswire",  "previous_years/sts2015/spanish/STS.input.newswire.txt");
    aMap.put("2015_wikipedia", "previous_years/sts2015/spanish/STS.input.wikipedia.txt");
    AVAILABLE_CORPORA = Collections.unmodifiableMap(aMap);
    
    Map<String, String> aGold = new LinkedHashMap<String, String>();
    aGold.put("2014_news",      "previous_years/sts2014/spanish/STS.gs.news.txt");
    aGold.put("2014_wikipedia", "previous_years/sts2014/spanish/STS.gs.wikipedia.txt");
    aGold.put("2015_newswire",  "previous_years/sts2015/spanish/STS.gs.newswire.txt");
    aGold.put("2015_wikipedia", "previous_years/sts2015/spanish/STS.gs.wikipedia.txt");
    
    GOLD_CORPORA = Collections.unmodifiableMap(aGold);
  }

  private static final String LAN = "es";
  
  public DatasetHandlerSpanish(String basePath) {
    super(AVAILABLE_CORPORA.keySet(), LAN, basePath);
  }
  
  public List<String> getInstances() throws IOException {
    CHK.CHECK(! ACTIVATED_CORPORA_IDS.isEmpty(), "No corpus was selected. Nothing to do");
    List<String> instances = new ArrayList<String>();
    for (String id : ACTIVATED_CORPORA_IDS) {
      instances.addAll(joinInstances(id));
    }
    return instances;
  }
  
  private List<String> joinInstances(String id) throws IOException {
    String[] texts = FileIO.fileToLines(
        new File(getInputFileFullPath(AVAILABLE_CORPORA.get(id)))
    );
    
    String[] gs = FileIO.fileToLines(
        new File(getInputFileFullPath(GOLD_CORPORA.get(id)))
    );
    
    List<String> instances = new ArrayList<String>();
    
    CHK.CHECK(texts.length == gs.length, 
        String.format("There's something wrong. Files %s and %s have different lengths",
            AVAILABLE_CORPORA.get(id), GOLD_CORPORA.get(id)));
    
    
    for (int i = 0; i < texts.length ; i++) {
      instances.add(produceLine(id, i, gs[i], texts[i]));
    }
    return instances;
  }
  
  /**
   * Creates a single line with all the information, as in the Arabic format:
   * [corpus]#[idx]\tab[gs]\tab[texts]
   * @param corpusId    identifier for this corpus
   * @param idx         index in this corpus
   * @param gold        gold number
   * @param text        tab-separated text pair
   * @return
   */
  private String produceLine(String corpusId, int idx, String gold, String text) {
    return String.format("%s#%d\t%s\t%s", corpusId, idx, gold, text);
  }
  
}
