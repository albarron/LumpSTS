package cat.lump.sts2017.dataset;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**A feature dumper which follows the format defined to dump features in our
 * participation to STS 2017.
 * 
 * @author albarron
 *
 */
public class FeatureDumper {
  
  private  FileWriter FW ;
  private  BufferedWriter BW;
  
  /**
   * Class to dump a set of features. At construction time, keep in mind the 
   * following definitions:
   * 
   * The feature file should have the the pattern \d-\w+ where \w+ is a descriptive
   * feature name. The codes are as follows:
   * 
   * <ul>
   *  <li/> 1-XXXX - reservado para features genéricos (idioma, traducción, etc)
   *  <li/> 2-XXXX - lo que he estado llamando similitudes standards (ngramas, length factors, no sé si pseudocognados...)
   *  <li/> 3-XXXX - las relacionadas con (CL)-ESA
   *  <li/> 4-XXXX - las relacionadas con el NMT
   *  <li/> 5-XXXX - las relacionadas con BN
   *  <li/> 6-XXXX - las que vengan de traducciones (si llegan a existir)
   * </ul>
   *
   * In principle the description should have 4 characters, but more are allowed.
   * 
   * @param path
   *            path to the storage folder
   * @param i
   *          number of the feature
   * @param feature
   *          4-characters feature code
   * @throws IOException
   */
  public FeatureDumper(String path, int i, String feature ) throws IOException {
    String filename = getFileName(path, i, feature );
    FW = new FileWriter(new File(filename));
    BW = new BufferedWriter(FW);
    System.out.println("Files will be dumped to " + filename);
  }
  
 public void writeLine(String line) throws IOException {
   BW.write(line);
   BW.write("\n");
 }
  
  public void close() throws IOException {
    BW.close();
    FW.close();
  }
  
  private String getFileName(String path, int i, String feature) {    
    String s = String.format("%s%s%d-%s", path, File.separator, i, feature);
    return s;
  }
}
