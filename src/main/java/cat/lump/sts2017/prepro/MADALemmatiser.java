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
 * Implements the Lemmatiser class using MADAMIRA
  * 
 * @author cristinae
 * @since Dec 2, 2016
 */
public class MADALemmatiser implements Lemmatiser {

	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (Annotator.class.getSimpleName());

    /** MADAMIRA namespace as defined by its XML schema */
    private static final String MADAMIRA_NS = "edu.columbia.ccls.madamira.configuration";

    
	/** 
	 * Runs the lemmatiser on an input file.
	 * 
	 * @param p
	 * 			Properties object with the config file's proper section loaded
	 * 			(not needed for this software)
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * 			(not needed for this software)
	 * @param output
	 * 			File where to store the annotated source
	 */
	public void execute(Section p, File inputRaw, String lang, File outputF) {
		
		
		logger.info("Lemmatising input text with MADAMIRA...");

		String nameTMP = "madaIN"+Math.random()+".tmp";
		File inputF = new File(nameTMP);

		String nameTMP2 = "madaOUT"+Math.random()+".tmp";
		File outputMADA = new File(nameTMP2);

		String config = ConfigConstants.MADACONFIG4LEM;
		FormatConverter.raw2mada(inputRaw, inputF, config);
		logger.info("Input raw text converted into MADA format.");

		logger.info("Initialising MADA...");
		final MADAMIRAWrapper wrapper = new MADAMIRAWrapper();
        JAXBContext jc = null;

        try {
            jc = JAXBContext.newInstance(MADAMIRA_NS);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            // The structure of the MadamiraInput object is exactly similar to the
            // madamira_input element in the XML
            final MadamiraInput input = (MadamiraInput)unmarshaller.unmarshal(inputF);

            {
                int numSents = input.getInDoc().getInSeg().size();
                //String outputAnalysis = input.getMadamiraConfiguration().
                //        getOverallVars().getOutputAnalyses();
                //String outputEncoding = input.getMadamiraConfiguration().
                //        getOverallVars().getOutputEncoding();

                logger.info("Processing " + numSents + " sentences for analysis...");
//                        " sentences for analysis type = " + outputAnalysis +
//                        " and output encoding = " + outputEncoding);
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
        FormatConverter.mada2raw(outputMADA, outputF, "lem");
        outputMADA.delete();
		logger.info("Done.");
    }		


	
	/** 
	 * Runs the lemmatiser on an input string. Returns the string with the lemmas.
	 * 
	 * @param p
	 * 			Properties object with the config file's proper section loaded
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
	public String execute(Section p, String input, String lang) {

		// Default output
		String lemOutput = "NON ANNOTATED";
		logger.error("Lemmatisation for strings with MADAMIRA not implemented. Use an input file.");
		return lemOutput;		
	}

}
