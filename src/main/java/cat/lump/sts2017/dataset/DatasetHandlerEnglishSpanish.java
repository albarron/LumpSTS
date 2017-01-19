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
 * The English-Spanish dataset is available for 2017 and 2016 only. It includes:
 * 
 * 2017
 * <ul>
 * <li /> STS.input.en-es.train.txt
 * </ul>
 * 
 * 2016
 * <ul>
 * <li /> STS.input.multi.txt
 * <li /> STS.input.multisource.txt
 * <li /> STS.input.news.txt
 * </ul>
 * 
 * Nothing is said about the nature of the 2017 dataset.
 * As in the Spanish dataset, the files contain only two text columns 
 * (the ones to be compared). The gold label is included in another file with
 * 'gs' instead of 'input'. 
 * 
 * The text file contains Spanish (English) on the left (right) column 
 * 
 * 
 * @author albarron
 * @since Jan 8th, 2017 
 */
public class DatasetHandlerEnglishSpanish extends DatasetHandlerSingle {

  // multi commented because there is no gs for all the instances.
  // multisource was modified externally because it was originally es-en instead of en-es which all the others have
  static {
    Map<String, String> aMap = new LinkedHashMap<String, String>();
    aMap.put("2017", "En_Es_STS/STS.input.en-es.train.txt");
//    aMap.put("multi", "previous_years/sts2016/cl_en_es/STS2016-cross-lingual-test/STS.input.multi.txt");
    aMap.put("2016_multisource",  "previous_years/sts2016/cl_en_es/STS2016-cross-lingual-test/STS.input.multisource.RIGHTORDER.txt");
    aMap.put("2016_news",      "previous_years/sts2016/cl_en_es/STS2016-cross-lingual-test/STS.input.news.txt");
    
    AVAILABLE_CORPORA = Collections.unmodifiableMap(aMap);
    
    Map<String, String> aGold = new LinkedHashMap<String, String>();
    aGold.put("2017", "En_Es_STS/STS.input.en-es.train_scores.txt");
//    aGold.put("multi", "previous_years/sts2016/cl_en_es/STS2016-cross-lingual-test/STS.gs.multi.txt");
    aGold.put("2016_multisource",  "previous_years/sts2016/cl_en_es/STS2016-cross-lingual-test/STS.gs.multisource.txt");
    aGold.put("2016_news",      "previous_years/sts2016/cl_en_es/STS2016-cross-lingual-test/STS.gs.news.txt");;
    
    GOLD_CORPORA = Collections.unmodifiableMap(aGold);
  }

  private static final String LAN = "en_es";
  
  public DatasetHandlerEnglishSpanish(String basePath) {
    super(AVAILABLE_CORPORA.keySet(), LAN, basePath);
  }
  
  /* (non-Javadoc)
   * @see cat.lump.sts2017.dataset.DatasetHandlerSingle#getTexts()
   */
  public List<String> getTexts() throws IOException {
    CHK.CHECK(! ACTIVATED_CORPORA_IDS.isEmpty(), "No corpus was selected. Nothing to do");
    List<String> instances = new ArrayList<String>();
    String [] triplet = new String[3];
    for (String id : ACTIVATED_CORPORA_IDS) {
      String[] crudeLines = FileIO.fileToLines(
          new File(getInputFileFullPath(AVAILABLE_CORPORA.get(id))));
      for (int i = 0 ; i < crudeLines.length ; i++) {
        triplet = crudeLines[i].split("\t");
        //Some of these files include 2 extra colums with smt info. We discard them here
        instances.add(String.format("%s\t%s", triplet[0], triplet[1]) );
      }
    }
    return instances;
  }
  
  

  
  /**
   * Joined the texts and scores from two files and added an extra id.
   * This was called by getTexts(). It is deprecated because at the end we
   * use the more simple format of one file with pairs of texts and one file 
   * with scores. 
   * @param id
   * @return
   * @throws IOException
   */
  @SuppressWarnings("unused")
  @Deprecated
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
    String[] texts = text.split("\t");
    return String.format("%s#%d\t%s\t%s\t%s", corpusId, idx, gold, texts[0], texts[1]);
  }
  
}
