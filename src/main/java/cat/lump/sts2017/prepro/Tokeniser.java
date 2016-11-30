package cat.lump.sts2017.prepro;

import java.io.File;

/**
 * Interface to tokenise raw text
 *   
 * @author cristina
 * @since Nov 29, 2016
 */

public interface Tokeniser {

	public void execute(String exe, File input, String lang, File output);
	public void execute(String exe, String input, String lang, File output);

}
