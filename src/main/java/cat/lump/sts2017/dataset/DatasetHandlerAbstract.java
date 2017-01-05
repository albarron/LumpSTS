package cat.lump.sts2017.dataset;

import java.util.Map;

//TODO keep only if it make4s sense to extend both with single and with pair
public abstract class DatasetHandlerAbstract {
  
  public DatasetHandlerAbstract() {
   
  }
  
  public abstract String[] getArrayOfInstances();
  public abstract String getLanguage();
  
  
  public abstract Map<String, String> getAvailableCorpora();
//  {
//    return null;
//  }
  

  
//  public static boolean checkLanguagePairExists(String lan1, String lan2) {
//    
//  }
  
  
  
  
//  public void addDataset
  
  
}
