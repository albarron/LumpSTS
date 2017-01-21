package cat.lump.sts2017.ml;

import java.io.File;

import water.Key;
import water.fvec.Frame;
import water.fvec.NFSFileVec;
import water.parser.ParseDataset;
import water.util.StringUtils;

/**
 * Utilities to interact with the H2O library
 * 
 * @author cristina
 * @since Jan 21, 2016
 */

public class Utils4H2O {
	
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
