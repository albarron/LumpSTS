package cat.lump.sts2017.prepro;

import java.io.File;

import cat.lump.aq.basics.log.LumpLogger;
import edu.columbia.ccls.madamira.MADAMIRAWrapper;
import edu.columbia.ccls.madamira.configuration.MadamiraInput;
import edu.columbia.ccls.madamira.configuration.MadamiraOutput;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.ini4j.Profile.Section;

import java.util.concurrent.ExecutionException;

/**
 * Implements the Tokeniser class using MADAMIRA
  * 
 * @author cristinae
 * @since Dec 4, 2016
 */
public class MADATokeniser implements Tokeniser {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (Annotator.class.getSimpleName());

    /** MADAMIRA namespace as defined by its XML schema */
    private static final String MADAMIRA_NS = "edu.columbia.ccls.madamira.configuration";

   
	/** 
	 * Runs the tokeniser on an input file.
	 * TODO if the section (formerly section) is not necessary here, why do we want it?
	 * 
	 * 
	 * @param section
   *      Properties object with the config file's proper section loaded
	 * 			(not needed for this software)
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * 			(not needed for this software)
	 * @param output
	 * 			File where to store the annotated source
	 */
	public void execute(Section section, File inputRaw, String lang, File outputF) {
		
		
		logger.info("Tokenising input text with MADAMIRA...");

		String nameTMP = "madaIN"+Math.random()+".tmp";
		File inputF = new File(nameTMP);

		String nameTMP2 = "madaIN"+Math.random()+".tmp";
		File outputMADA = new File(nameTMP2);

		String config = ConfigConstants.MADACONFIG4TOK;
		FormatConverter.raw2mada(inputRaw, inputF, config);
		logger.info("Input raw text converted into MADA format.");

		logger.info("Initialising MADA...");
		final MADAMIRAWrapper wrapper = new MADAMIRAWrapper();
        JAXBContext jc = null;

        try {
            jc = JAXBContext.newInstance(MADAMIRA_NS);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            // 1The structure of the MadamiraInput object is exactly similar to the
            // madamira_input element in the XML
            final MadamiraInput input = (MadamiraInput)unmarshaller.unmarshal(inputF);

            {
                int numSents = input.getInDoc().getInSeg().size();
                //String outputAnalysis = input.getMadamiraConfiguration().
                //        getOverallVars().getOutputAnalyses();
                //String outputEncoding = input.getMadamiraConfiguration().
                //        getOverallVars().getOutputEncoding();

                logger.info("Processing " + numSents + " sentences for analysis...");
            }

            // The structure of the MadamiraOutput object is exactly similar to the
            // madamira_output element in the XML
            final MadamiraOutput annotation = wrapper.processString(input);

            {
                int numSents = annotation.getOutDoc().getOutSeg().size();
                logger.info("Processed output contains "+numSents+" sentences.");
            }

            jc.createMarshaller().marshal(annotation, outputMADA);


        } catch (JAXBException ex) {
            System.out.println("Error marshalling or unmarshalling data: "
                    + ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println("MADAMIRA thread interrupted: "
                    +ex.getMessage());
        } catch (ExecutionException ex) {
            System.out.println("Unable to retrieve result of task. " +
                    "MADAMIRA task may have been aborted: "+ex.getCause());
        }

        wrapper.shutdown();
        inputF.delete();
		logger.info("Converting output into raw format...");
        FormatConverter.mada2raw(outputMADA, outputF, "tok");
        outputMADA.delete();
		logger.info("Done.");
    }		


	
	/** 
	 * Runs the lemmatiser on an input string. Returns the string with the lemmas.
	 * 
	 * @param section
   *      Properties object with the config file's proper section loaded
	 * 			(not needed for this software)
	 * @param input
	 * 			Input string text
	 * @param lang
	 * 			Language of the input text
	 * 			(not needed for this software)
	 * 
	 * @return 
	 * 			String with the lemmas
	 */
	public String execute(Section section, String input, String lang) {

		// Default output
		String lemOutput = "NON ANNOTATED";
		logger.error("Tokenisation for strings with MADAMIRA not implemented. Use an input file.");		
		return lemOutput;		
	}

	

}
