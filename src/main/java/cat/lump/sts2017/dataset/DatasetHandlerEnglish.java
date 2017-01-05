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
 * The English dataset is available for different editions. It includes:
 * 
 * 2012 Task 6 TRAINING
 * <ul>
 * <li /> STS.input.MSRpar.txt
 * <li /> STS.input.MSRvid.txt
 * <li /> STS.input.SMTeuroparl.txt
 * </ul>
 * 
 * 2012 Task 6 TEST
 * <ul>
 * <li /> STS.input.MSRpar.txt
 * <li /> STS.input.MSRvid.txt
 * <li /> STS.input.SMTeuroparl.txt
 * <li /> STS.input.surprise.OnWN.txt
 * <li /> STS.input.surprise.SMTnews.txt
 * </ul>
 * 
 * 2013 *SEM (ONLY TEST)
 * <ul>
 * <li /> STS.input.headlines.txt
 * <li /> STS.input.OnWN.txt
 * <li /> STS.input.FNWN.txt
 * <li /> STS.input.SMT.txt
 * </ul>
 * 
 * 
 * 
 * 2014 Task 10 (ONLY TEST)
 * <ul>
 * <li /> STS.input.image.txt
 * <li /> STS.input.OnWN.txt
 * <li /> STS.input.tweet-news.txt
 * <li /> STS.input.deft-news.txt
 * <li /> STS.input.deft-forum.txt
 * <li /> STS.input.headlines.txt
 * </ul>
 * 
 * 
 * 
 * TODO 2015  THIS IS PENDING
 * <ul>
 * <li />
 * </ul>
 * 
 * 2016 
 * <ul>
 * <li /> STS2016.input.answer-answer.txt
 * <li /> STS2016.input.headlines.txt
 * <li /> STS2016.input.plagiarism.txt
 * <li /> STS2016.input.postediting.txt
 * <li /> STS2016.input.question-question.txt
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
public class DatasetHandlerEnglish extends DatasetHandlerSingle {

 
  private static final Map<String, String> AVAILABLE_CORPORA;
  private static final Map<String, String> GOLD_CORPORA;
  static {
    Map<String, String> aMap = new LinkedHashMap<String, String>();
    
    aMap.put("2012_MSRpar_tr",      "previous_years/sts2012/train/STS.input.MSRpar.txt");
    aMap.put("2012_MSRvid_tr",      "previous_years/sts2012/train/STS.input.MSRvid.txt");
    aMap.put("2012_SMTeuroparl_tr", "previous_years/sts2012/train/STS.input.SMTeuroparl.txt");
    
    aMap.put("2012_MSRpar_te",      "previous_years/sts2012/test-gold/STS.input.MSRpar.txt");
    aMap.put("2012_MSRvid_te",      "previous_years/sts2012/test-gold/STS.input.MSRvid.txt");
    aMap.put("2012_SMTeuroparl_te", "previous_years/sts2012/test-gold/STS.input.SMTeuroparl.txt");
    
    aMap.put("2012_surprise.OnWN_te",   "previous_years/sts2012/test-gold/STS.input.surprise.OnWN.txt");
    aMap.put("2012_surprise.SMTnews_te","previous_years/sts2012/test-gold/STS.input.surprise.SMTnews.txt");

    aMap.put("2013_headlines", "previous_years/sts2013/core_task/test-gs/STS.input.headlines.txt");
    aMap.put("2013_OnWN", "previous_years/sts2013/core_task/test-gs/STS.input.OnWN.txt");
    aMap.put("2013_FNWN", "previous_years/sts2013/core_task/test-gs/STS.input.FNWN.txt");
    // THE SMT data does not seem to be available
//    aMap.put("2013_SMT", "previous_years/sts2013/core_task/test-gs/STS.input.SMT.txt");

    aMap.put("2014_images", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.input.images.txt");
    aMap.put("2014_OnWN", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.input.OnWN.txt");
    aMap.put("2014_tweet-news", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.input.tweet-news.txt");
    aMap.put("2014_deft-news", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.input.deft-news.txt");
    aMap.put("2014_deft-forum", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.input.deft-forum.txt");
    aMap.put("2014_headlines", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.input.headlines.txt");


 
    // TODO 2015  THIS IS PENDING
    aMap.put("2016_answer-answer", "previous_years/sts2016/english/sts2016-english-with-gs-v1.0/STS2016.input.answer-answer.txt");
    aMap.put("2016_headlines", "previous_years/sts2016/english/sts2016-english-with-gs-v1.0/STS2016.input.headlines.txt");
    aMap.put("2016_plagiarism", "previous_years/sts2016/english/sts2016-english-with-gs-v1.0/STS2016.input.plagiarism.txt");
    aMap.put("2016_postediting", "previous_years/sts2016/english/sts2016-english-with-gs-v1.0/STS2016.input.postediting.txt");
    aMap.put("2016_question-question", "previous_years/sts2016/english/sts2016-english-with-gs-v1.0/STS2016.input.question-question.txt");
    
    
    AVAILABLE_CORPORA = Collections.unmodifiableMap(aMap);
    
    Map<String, String> aGold = new LinkedHashMap<String, String>();
    aGold.put("2012_MSRpar_tr",      "previous_years/sts2012/train/STS.gs.MSRpar.txt");
    aGold.put("2012_MSRvid_tr",      "previous_years/sts2012/train/STS.gs.MSRvid.txt");
    aGold.put("2012_SMTeuroparl_tr", "previous_years/sts2012/train/STS.gs.SMTeuroparl.txt");
    
    aGold.put("2012_MSRpar_te",      "previous_years/sts2012/test-gold/STS.gs.MSRpar.txt");
    aGold.put("2012_MSRvid_te",      "previous_years/sts2012/test-gold/STS.gs.MSRvid.txt");
    aGold.put("2012_SMTeuroparl_te", "previous_years/sts2012/test-gold/STS.gs.SMTeuroparl.txt");
    
    aGold.put("2012_surprise.OnWN_te",   "previous_years/sts2012/test-gold/STS.gs.surprise.OnWN.txt");
    aGold.put("2012_surprise.SMTnews_te","previous_years/sts2012/test-gold/STS.gs.surprise.SMTnews.txt");

    aGold.put("2013_headlines", "previous_years/sts2013/core_task/test-gs/STS.gs.headlines.txt");
    aGold.put("2013_OnWN", "previous_years/sts2013/core_task/test-gs/STS.gs.OnWN.txt");
    aGold.put("2013_FNWN", "previous_years/sts2013/core_task/test-gs/STS.gs.FNWN.txt");
//    aGold.put("2013_SMT", "previous_years/sts2013/core_task/test-gs/STS.gs.SMT.txt");

    aGold.put("2014_images", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.gs.images.txt");
    aGold.put("2014_OnWN", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.gs.OnWN.txt");
    aGold.put("2014_tweet-news", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.gs.tweet-news.txt");
    aGold.put("2014_deft-news", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.gs.deft-news.txt");
    aGold.put("2014_deft-forum", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.gs.deft-forum.txt");
    aGold.put("2014_headlines", "previous_years/sts2014/english/sts-en-test-gs-2014/STS.gs.headlines.txt");


 
    // TODO 2015  THIS IS PENDING

    aGold.put("2016_answer-answer", "previous_years/sts2016/english/sts2016-english-with-gs-v1.0/STS2016.gs.answer-answer.txt");
    aGold.put("2016_headlines", "previous_years/sts2016/english/sts2016-english-with-gs-v1.0/STS2016.gs.headlines.txt");
    aGold.put("2016_plagiarism", "previous_years/sts2016/english/sts2016-english-with-gs-v1.0/STS2016.gs.plagiarism.txt");
    aGold.put("2016_postediting", "previous_years/sts2016/english/sts2016-english-with-gs-v1.0/STS2016.gs.postediting.txt");
    aGold.put("2016_question-question", "previous_years/sts2016/english/sts2016-english-with-gs-v1.0/STS2016.gs.question-question.txt");
    
    GOLD_CORPORA = Collections.unmodifiableMap(aGold);
  }

  private static final String LAN = "en";
  
  public DatasetHandlerEnglish(String basePath) {
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
