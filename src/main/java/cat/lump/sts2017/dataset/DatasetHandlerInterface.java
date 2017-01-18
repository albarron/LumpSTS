package cat.lump.sts2017.dataset;

import java.io.IOException;
import java.util.List;
import java.util.Set;


//TODO keep only if it make4s sense to extend both with single and with pair
interface DatasetHandlerInterface {
  
 
  
//  public abstract void logSelectedCorpora();
  
//  public  String[] getArrayOfInstances();
  public  String getLanguage();
  
  public List<String> getTexts() throws IOException;
  
  public List<String> getScores() throws IOException;
  
  public  Set<String> getAvailableCorpora();


  public  void activateAllCorpus() ;

  public  void activateCorpus(String availableCorpus);
  

  
//  public static boolean checkLanguagePairExists(String lan1, String lan2) {
//    
//  }
  
  
  
  
//  public void addDataset
  
  
}
