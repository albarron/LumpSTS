package cat.lump.sts2017;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * A class to read the configuration file as a Properties object
 *   
 * @author cristina
 * @since Nov 27, 2016
 */

public class lumpConfig {
	/**Configuration file */
	protected static Properties p;
	
	
	/**
	 * Loads the wikiTailor.ini config file
	 * 
	 * @param configFile
	 * @return
	 */
	public static Properties getProperties(String configFile){
		p = new Properties();
		
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(configFile), Charset.forName("UTF-8"));
			p.load(isr);
			isr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return p;
	}	
	
	// Getters
	/** 
	 * Gets a value in the config file given a key and returns it as an integer 
	 * 
	 * @param key
	 * @return
	 */
	public int getPropertyInt(String key){
		return Integer.valueOf(p.getProperty(key));
	} 
	
	/** 
	 * Gets a value in the config file given a key and returns it as a String
	 * 
	 * @param key
	 * @return
	 */
	public String getPropertyStr(String key){
		return p.getProperty(key);
} 
	
	
}
