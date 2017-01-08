package cat.lump.sts2017.dataset;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import cat.lump.aq.basics.log.LumpLogger;

public  class DatasetHandlerPair implements DatasetHandlerInterface{

  @Override
  public String getLanguage() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> getInstances() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<String> getAvailableCorpora() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void activateAllCorpus() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void activateCorpus(String availableCorpus) {
    // TODO Auto-generated method stub
    
  }
//  abstract
//  
//  /** Available languages in our collection/task */
//  private  enum LANGUAGES {en_es, en_ar};
//  
//  private static final String LANGUAGE_SOURCE = "en";
//  
//  /** Logger */
//  private static final LumpLogger logger = 
//      new LumpLogger (DatasetHandlerPair.class.getSimpleName());
//  
//  
//  public DatasetHandlerPair(String lan1, String lan2) {
//    super();
//    if (! checkLanguagePairExists(lan1, lan2)) {
//      logger.error(String.format("Unexpected language pair: %s-%s" + lan1, lan2));
//      System.exit(-1);
//    }
//  }
// 
//  public Set<String> getAvailableCorpora() {
//    return null;
//  }
//  
//  /**
//   * Check if the given language is among those available. The code crashes if 
//   * language is not in the enum LANGUAGES
//   * @param language
//   *              a two-character language code
//   */
//  public static boolean checkLanguagePairExists(String lan1, String lan2) {
//    @SuppressWarnings("unused")
//    LANGUAGES kk;
//    String lanPair = getPotentiallyValidPair(lan1, lan2);
//    try {
//      kk = LANGUAGES.valueOf(lanPair);
//      //yes
//    } catch (IllegalArgumentException ex) {
//      return false;
//    }
//    return true;
//  }
//  
//  private static String getPotentiallyValidPair(String lan1, String lan2) {
//    if (lan1.equals(LANGUAGE_SOURCE)) {
//      return String.format("%s_%s", lan1, lan2);
//    } else {
//      return String.format("%s_%s", lan2, lan1);
//    }
//  }
//
//  @Override
//  public String getLanguage() {
//    // TODO Auto-generated method stub
//    return null;
//  }
//
//
//
//  @Override
//  public void activateAllCorpus() {
//    // TODO Auto-generated method stub
//    
//  }
//
//  @Override
//  public void activateCorpus(String availableCorpus) {
//    // TODO Auto-generated method stub
//    
//  }
//
//  @Override
//  public List<String> getInstances() throws IOException {
//    // TODO Auto-generated method stub
//    return null;
//  }
//  
////  public static boolean checkLanguagePairExists(String lan1, String lan2) {
////    //TODO 
////    return false;
////  }
////  
////  
//  
//  
////  public void addDataset
//  
//  
}
