package cat.lump.sts2017;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;


/**
 * A class to read the configuration file as an Ini object. Contrary to the
 * lumpConfig file, based on Properties, it allows for the setting of different
 * values depending on the execution environment (e.g., different computers or 
 * cluster).
 *   
 * @author albarron
 * @since Dec 20, 2016
 */

public class LumpIni {
	/**Configuration file */
//	protected static Properties p;
  
	protected static Section section;
  /** Default home for cristina. It is used to load the proper ini section */
  private final static String VAR_CRISTINA = "/home/cristinae";
  
  /** Cluster home for cristina. It is used to load the proper ini section */
  private final static String VAR_CRISTINA_CLUSTER = "/home/usuaris/cristinae";

  /** Default fome for albarron. It is used to load the proper ini section */
  private final static String VAR_ALBARRON = "/Users/albarron";
  
  /** Property from the system used to load the proper section from the ini file */
  private final static String PROPERTY_FOR_INI = "user.home";
  
  private final static String SECTION_CRISTINA = "cristina";
  
  private final static String SECTION_CRISTINA_CLUSTER = "cristinae";
  
  private final static String SECTION_ALBARRON = "albarron";
  
  protected static String ENVIRONMENT;
	
  
  
	/**
	 * Loads the wikiTailor.ini config file. It uses hard-coded constants to 
	 * determine what section from the ini file to load, according to the 
	 * environment (machine) the code is executed in.
	 * 
	 * The code will crash if no section is available for the current user's home
	 * directory
	 * TODO test this in the cluster (if necessary)
	 * 
	 * @param configFile
	 *         Full path to the ini config file.
	 * @return
	 *        An ini section with the variables set for the given profile
	 */
	public static Section getProperties(String configFile){
//		p = new Properties();
	  Ini ini = new Ini();
		
		try {
		  InputStreamReader isr = new InputStreamReader(
		                new FileInputStream(configFile), Charset.forName("UTF-8"));
		  ini.load(isr);
			isr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		// Set the environment according to the user's home path.
		String currentHome = System.getProperty(PROPERTY_FOR_INI);
		if (currentHome.equals(VAR_CRISTINA)) {
		  ENVIRONMENT = SECTION_CRISTINA;
		} else if (currentHome.equals(VAR_ALBARRON)) {
		  ENVIRONMENT = SECTION_ALBARRON;
		} else if (currentHome.equals(VAR_CRISTINA_CLUSTER)) {
		  ENVIRONMENT = SECTION_CRISTINA_CLUSTER;
		} else {
		  System.err.println("I do not know what section from the ini file I should load");
		  System.exit(1);
		}
		
		section = ini.get(ENVIRONMENT);
		
		return section;
	}	
	
	// Getters
	/** 
	 * Gets a value in the config file given a key and returns it as an integer 
	 * 
	 * @param key
	 * @return
	 */
	public int getPropertyInt(String key){
		return Integer.valueOf(section.get(key));
	} 
	
	/** 
	 * Gets a value in the config file given a key and returns it as a String
	 * 
	 * @param key
	 * @return
	 */
	public String getPropertyStr(String key){
		return section.get(key);
} 
	
	
}
