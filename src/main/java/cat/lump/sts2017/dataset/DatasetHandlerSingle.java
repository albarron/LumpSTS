package cat.lump.sts2017.dataset;

import java.util.Map;

import cat.lump.aq.basics.log.LumpLogger;

public class DatasetHandlerSingle extends DatasetHandlerAbstract{
  
  /** Available languages in our collection/task */
  private  enum LANGUAGES {en, ar, es};
  
  private final AvailableDatasets datasets;
  
  private final String LANGUAGE;
  
  /** Logger */
  private static final LumpLogger logger = 
      new LumpLogger (DatasetHandlerSingle.class.getSimpleName());
  
  public DatasetHandlerSingle(String language) {
    if (! checkLanguageExists(language)) {
      logger.error("Unexpected language: " + language);
      System.exit(-1);
    }
    if (language.equals("ar")) {
      datasets = new AvailableDatasetsArabic();
    } else if (language.equals("en")) {
      //TODO CHANGE
      datasets = new AvailableDatasetsArabic();
    } else {
    //TODO CHANGE
      datasets = new AvailableDatasetsArabic();
    }
    
    LANGUAGE = language;
    
  }
  
  public  Map<String, String> getAvailableCorpora() {
    return datasets.getAvailableCorpora();
  }
  
  public String getLanguage() {
    return LANGUAGE;
  }
  
  /**
   * Check if the given language is among those available. The code crashes if 
   * language is not in the enum LANGUAGES
   * @param language
   *              a two-character language code
   */
  public static boolean checkLanguageExists(String language) {    
    try {
      @SuppressWarnings("unused")
      LANGUAGES kk = LANGUAGES.valueOf(language);
      //yes
    } catch (IllegalArgumentException ex) {
      return false;
      
    }
    return true;
  }
  
//  public static boolean checkLanguagePairExists(String lan1, String lan2) {
//    
//  }
  
  
  
  
//  public void addDataset
  
  
}
