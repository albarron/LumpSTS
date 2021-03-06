package cat.lump.sts2017.dataset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cat.lump.aq.basics.check.CHK;
import cat.lump.aq.basics.io.files.FileIO;
import cat.lump.aq.basics.log.LumpLogger;

public abstract class DatasetHandlerSingle implements DatasetHandlerInterface{
    
  protected static Map<String, String> AVAILABLE_CORPORA;
  protected static Map<String, String> GOLD_CORPORA;
  
  protected final Set<String> AVAILABLE_CORPORA_IDS;
  
  protected Set<String> ACTIVATED_CORPORA_IDS;

  protected final String LANGUAGE;
  
  protected final String BASE_PATH;
  
  /** Logger */
  private static final LumpLogger logger = 
      new LumpLogger (DatasetHandlerSingle.class.getSimpleName());
  
  public DatasetHandlerSingle(Set<String> availableCorporaIds, String language, String basePath) {
    AVAILABLE_CORPORA_IDS = availableCorporaIds;
    ACTIVATED_CORPORA_IDS = new LinkedHashSet<String>();
    LANGUAGE = language;
    BASE_PATH = basePath;
    logger.info("Path to the corpus: " + BASE_PATH);

  }
  
  public void activateAllCorpus() {
    for (String id : AVAILABLE_CORPORA_IDS) {
      activateCorpus(id);
    }
  }
  
  public void activateCorpus(String id) {
    CHK.CHECK(AVAILABLE_CORPORA_IDS.contains(id), 
        String.format("Corpus with ID %s not available", id));
    ACTIVATED_CORPORA_IDS.add(id);  
    logger.info(String.format("Considering corpus %s", id));
  }
  
 
  /* (non-Javadoc)
   * @see cat.lump.sts2017.dataset.DatasetHandlerInterface#getTexts()
   */
  public List<String> getTexts() throws IOException {
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
  
  public List<String> getScores() throws IOException {
    //TODO right now does the same
    CHK.CHECK(! ACTIVATED_CORPORA_IDS.isEmpty(), "No corpus was selected. Nothing to do");
    List<String> instances = new ArrayList<String>();
    for (String id : ACTIVATED_CORPORA_IDS) {
      instances.addAll(
          Arrays.asList(
              FileIO.fileToLines(
                  new File(getInputFileFullPath(GOLD_CORPORA.get(id)))
              )
          )
      );
    }
    return instances;  
  }
  
  public Set<String> getAvailableCorpora() {    
    return AVAILABLE_CORPORA_IDS;
  }
  
  public String getLanguage() {
    return LANGUAGE;
  }
  
  protected String getInputFileFullPath(String file) {
    return String.format("%s%s%s", BASE_PATH, File.separator, file);
  }
  

  
  
  
  
  
}
