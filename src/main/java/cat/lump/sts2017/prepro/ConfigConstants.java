package cat.lump.sts2017.prepro;

/**
 * Hard-coded configuration files for the annotations.
 * No variation of the parameters within the experiments.
 *   
 * @author cristina
 * @since Dec 2, 2016
 */

public class ConfigConstants {

	// Configuration files for the MADAMIRA software
    public static final String MADACONFIG4LEM = 
			"<madamira_configuration> \n" +
			"    <preprocessing sentence_ids=\"false\" separate_punct=\"true\" input_encoding=\"UTF8\"/> \n" +
			"    <overall_vars output_encoding=\"UTF8\" dialect=\"MSA\" output_analyses=\"TOP\" morph_backoff=\"NONE\"/>\n" +
			"    <requested_output>\n" +
			"        <req_variable name=\"PREPROCESSED\" value=\"false\" />\n" +
			"        <req_variable name=\"GLOSS\" value=\"false\" />\n" +
			"        <req_variable name=\"LEMMA\" value=\"true\" />\n" +
			"        <req_variable name=\"BW\" value=\"false\" />\n" +
			"        <req_variable name=\"SOURCE\" value=\"false\" />\n" +
			"        <req_variable name=\"POS\" value=\"true\" />\n" +
			"    </requested_output>\n" +
			"    <tokenization>\n" +
			"        <scheme alias=\"ATB\" />\n" +
			"    </tokenization>\n" +
			"</madamira_configuration>\n";    
    
    public static final String MADACONFIG4TOK =
    		"<madamira_configuration> \n" +
			"    <preprocessing sentence_ids=\"false\" separate_punct=\"true\" input_encoding=\"UTF8\"/> \n" +
			"    <overall_vars output_encoding=\"UTF8\" dialect=\"MSA\" output_analyses=\"TOP\" morph_backoff=\"NONE\"/>\n" +
			"    <requested_output>\n" +
			"        <req_variable name=\"PREPROCESSED\" value=\"false\" />\n" +
						"    </requested_output>\n" +
			"    <tokenization>\n" +
			"        <scheme alias=\"ATB\" />\n" +
			"    </tokenization>\n" +
			"</madamira_configuration>\n";    


}
