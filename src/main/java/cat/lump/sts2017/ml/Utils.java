package cat.lump.sts2017.ml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import cat.lump.aq.basics.io.files.FileIO;
import water.Key;
import water.fvec.Frame;
import water.fvec.NFSFileVec;
import water.parser.ParseDataset;
import water.util.StringUtils;

/**
 * Utilities to interact with the ML libraries
 * 
 * @author cristina
 * @since Jan 21, 2016
 */

public class Utils {
	
	
	
	/**
	 * Generates a file in libsvm format from a csv input file
	 * 
	 * @param csvFileName
	 * @return
	 *         the name of the resulting libsvm file
	 */
	 public static String csv2libsvm (String csvFileName, boolean test){
		  
	   String o = csvFileName+".libsvm";
		File fIn = new File(csvFileName);  
		File fOut = new File(o); 
		
		// Initilise the writer
		FileIO.deleteFile(fOut);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(fOut, true);
			bw = new BufferedWriter(fw);
			bw.write("");
		} catch (IOException e) {
				e.printStackTrace();
		}	

		// Read the input
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(fIn);
			sc = new Scanner(inputStream, "UTF-8");
			int i = 0;
			while (sc.hasNext()) {
				String line = sc.nextLine();
                String[] elements = line.split(",");
                int j = 0;
                for (String feature : elements){
                	if ( !test && j==0){
                        bw.append(feature+" ");               		
                	} else if ( test && j==0){
                        bw.append("999 " + feature+" ");               		
                	} else{
                		bw.append(j+":"+feature+" "); 
                	}
                	j++;
                }
	        	bw.newLine();              
	        	// Write every 10000 lines
	        	if (i%10000==0){
	        		bw.flush();
			    }
	        	i++;
			}
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		// Close everything
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (sc != null) {
				sc.close();
				try {
//					bw.newLine();
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return o;
	  }
	
	  // For H2O
	
	  /** Find & parse a CSV file.  NPE if file not found.
	   *  (taken from h2o-core/src/test/java/water/TestUtil.java)
	   *  @param fname Test filename
	   *  @return      Frame or NPE 
	   *  */
	  public static Frame parse_test_file( String fname ) { 
		  return parse_test_file(Key.make(),fname); 
	  }
	  public static Frame parse_test_file( Key outputKey, String fname) {
		///  System.out.println(fname);
	    File f = find_test_file_static(fname);
		//  System.out.println("kk "+f);

	    NFSFileVec nfs = NFSFileVec.make(new File(fname));
	    return ParseDataset.parse(outputKey, nfs._key);
	  }

	  /** Hunt for test files in likely places.  Null if cannot find.
	   *  (taken from h2o-core/src/test/java/water/TestUtil.java)
	   *  @param fname Test filename
	   *  @return      Found file or null */
	  public static File find_test_file_static(String fname) {
	    // When run from eclipse, the working directory is different.
	    // Try pointing at another likely place
	    File file = new File(fname);
	    if( !file.exists() )
	      file = new File("target/" + fname);
	    if( !file.exists() )
	      file = new File("../" + fname);
	    if( !file.exists() )
	      file = new File("../../" + fname);
	    if( !file.exists() )
	      file = new File("../target/" + fname);
	    if( !file.exists() )
	      file = new File(StringUtils.expandPath(fname));
	    if( !file.exists() )
	      file = null;
	    return file;
	  }



}
