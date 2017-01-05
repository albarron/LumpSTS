package cat.lump.sts2017.dataset;

import java.util.Map;

import cat.lump.aq.basics.log.LumpLogger;

public abstract class DatasetHandlerSingle extends DatasetHandlerAbstract{
  
  
  
//  private final AvailableDatasets datasets;
  
  private final String LANGUAGE;
  
  /** Logger */
  private static final LumpLogger logger = 
      new LumpLogger (DatasetHandlerSingle.class.getSimpleName());
  
  public DatasetHandlerSingle() {
    
    
    LANGUAGE = ""; //language;
    
  }
  
//  public  Map<String, String> getAvailableCorpora() {
//    return datasets.getAvailableCorpora();
//  }
  
  public abstract String getLanguage(); 
  

  
//  public static boolean checkLanguagePairExists(String lan1, String lan2) {
//    
//  }
  
  
  
  
  
  
}
