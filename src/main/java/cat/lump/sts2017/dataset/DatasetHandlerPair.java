package cat.lump.sts2017.dataset;

import java.util.Map;

import cat.lump.aq.basics.log.LumpLogger;

public  class DatasetHandlerPair extends DatasetHandlerAbstract{
//  abstract
  
  /** Available languages in our collection/task */
  private  enum LANGUAGES {en_es, en_ar};
  
  private static final String LANGUAGE_SOURCE = "en";
  
  /** Logger */
  private static final LumpLogger logger = 
      new LumpLogger (DatasetHandlerPair.class.getSimpleName());
  
  
  public DatasetHandlerPair(String lan1, String lan2) {
    super();
    if (! checkLanguagePairExists(lan1, lan2)) {
      logger.error(String.format("Unexpected language pair: %s-%s" + lan1, lan2));
      System.exit(-1);
    }
  }
 
  public  Map<String, String> getAvailableCorpora() {
    return null;
  }
  
  /**
   * Check if the given language is among those available. The code crashes if 
   * language is not in the enum LANGUAGES
   * @param language
   *              a two-character language code
   */
  public static boolean checkLanguagePairExists(String lan1, String lan2) {
    @SuppressWarnings("unused")
    LANGUAGES kk;
    String lanPair = getPotentiallyValidPair(lan1, lan2);
    try {
      kk = LANGUAGES.valueOf(lanPair);
      //yes
    } catch (IllegalArgumentException ex) {
      return false;
    }
    return true;
  }
  
  private static String getPotentiallyValidPair(String lan1, String lan2) {
    if (lan1.equals(LANGUAGE_SOURCE)) {
      return String.format("%s_%s", lan1, lan2);
    } else {
      return String.format("%s_%s", lan2, lan1);
    }
  }

  @Override
  public String getLanguage() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String[] getArrayOfInstances() {
    // TODO Auto-generated method stub
    return null;
  }
  
//  public static boolean checkLanguagePairExists(String lan1, String lan2) {
//    
//  }
  
  
  
  
//  public void addDataset
  
  
}
