package cat.lump.sts2017.dataset;


public class DatasetHandlerFactory {
  
  /** Available languages in our collection/task */
  private static  enum LANGUAGES {en, ar, es};
  
  public static DatasetHandlerSingle getDatasetHandler (String language) {
    if (! checkLanguageExists(language)) {
      System.err.println("Unexpected language: " + language);
      System.exit(-1);
    }
    if (language.equals("ar")) {
      return new DatasetHandlerArabic();
    } else if (language.equals("en")) {
      //TODO CHANGE
      return new DatasetHandlerArabic();
    } else {
//      return new DaatasetHandlerSpanish();
    }
    return null;
  }
  
  public DatasetHandlerPair getDatasetHAndler(String language1, String language2 ) {
    return null;
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

}
