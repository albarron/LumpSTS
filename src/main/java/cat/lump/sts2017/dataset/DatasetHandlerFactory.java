package cat.lump.sts2017.dataset;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cat.lump.aq.basics.check.CHK;


public class DatasetHandlerFactory {
  
  /** Available languages in our collection/task */  
  private static final Set<String> LANGUAGES = new HashSet<String>(
      Arrays.asList("ar", "en",  "es"));
  
//  private static final Set<String> LANGUAGE_PAIRS = new HashSet<String> (
//      Arrays.asList("en_ar", "en_es"));
  
  
//  private static 
  
  public static DatasetHandlerSingle getDatasetHandler (String language, String basePath) {
    CHK.CHECK(checkLanguageExists(language),
      String.format("Unexpected language: %s", language));
    
    if (language.equals("ar")) {
      return new DatasetHandlerArabic(basePath);
    } else if (language.equals("en")) {
      return new DatasetHandlerEnglish(basePath);
    } else {
      return new DatasetHandlerSpanish(basePath);
    }
  }
  
  public static DatasetHandlerSingle getDatasetHandler(String language1, String language2, String basePath ) {
    CHK.CHECK(checkLanguagePairExists(language1, language2), 
        String.format("Unexpected language pair: %s-%s%n",  language1, language2));
    
    // We already checked that this is a valid pair (see checkLanguagePairExists).
    // Therefore we just need to check if its en-*ar* or en-*es*.
    if (language1.equals("ar") || language2.equals("ar")) {
      return new DatasetHandlerEnglishArabic(basePath);
    } else {
      return new DatasetHandlerEnglishSpanish(basePath);
    }
    
  
  }
  
  /**
   * Check if the given language is among those available. 

   * @param language
   *              a two-character language code
   * @return 
   *              true if the language exists (e.g., it is ar, en, or es)
   */
  public static boolean checkLanguageExists (String language) {
    return LANGUAGES.contains(language);
  }
  
  
  /**
   * Check if the given language pair is among those available. 
   * 
   * @param lan1
   *            a two-character language code
   * @param lan2
   *            a two-character language code
   * @return
   *            true if the language pair exists (e.g., it is en_ar or en_es)
   */
  public static boolean checkLanguagePairExists (String lan1, String lan2) {
    if (lan1.equals("en")) {
      if (lan2.equals("es") || lan2.equals("ar")) {
        return true;
      }
      
    } else if (lan2.equals("en")){
      return (checkLanguagePairExists(lan2, lan1));
    }
    return false;
  }
  
  
}
