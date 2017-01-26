package cat.lump.sts2017.dataset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class reads  
 * @author albarron
 *
 */
public class StsBufferedReaderArabic extends BufferedReader{

  private final String FIELD_SEPARATOR = "\t";
  
  /** Index of the id in the file */
  private final int FIELD_INDEX_ID = 0;
  
  /** Index of the score in the file */
  private final int FIELD_INDEX_SCORE = 1;
  
  /** Index of the first text in the file */
  private final int FIELD_INDEX_TEXT1 = 2;
  
  /** Index of the second text in the file */
  private final int FIELD_INDEX_TEXT2 = 3;
  
  /** 
   * @param file
   *            Path to the input file. 
   *            So far it should come from the static variables in {@code CorpusHandler}
   * @throws FileNotFoundException
   */
  public StsBufferedReaderArabic(String file) throws FileNotFoundException  {
    super(new FileReader(file));
  }
  
  /**
   * @return
   *        an StsInstance including ID, score, and the two text fragments, or
   *        null if the end of the file has been reached.
   * @throws IOException
   */
  public StsInstance readInstance() throws IOException {
    String line = readLine();
    if (line == null) {
      return null;
    }
    
    String[] values = line.split(FIELD_SEPARATOR);
    return new StsInstance(
        values[FIELD_INDEX_ID],
        values[FIELD_INDEX_TEXT1], 
        values[FIELD_INDEX_TEXT2], 
        Double.valueOf(values[FIELD_INDEX_SCORE])
    );
  }
  
  public static void main(String[] args) throws IOException {
    StsBufferedReaderArabic cr = new StsBufferedReaderArabic(CorpusHandler.STS_CORPUS_PATH_ARABIC_MSRpar);

    for (StsInstance instance = cr.readInstance(); instance != null; instance = cr.readInstance())
    {  
       System.out.println(instance);
    } 
  }
  
  
  
}
