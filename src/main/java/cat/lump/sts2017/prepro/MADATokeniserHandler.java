package cat.lump.sts2017.prepro;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Class to define the behaviour of the SAX parser when extracting only tokens.
 *    
 * @author cristina
 * @since Dec 3, 2016
 */
public class MADATokeniserHandler extends DefaultHandler {

	    //List to hold the object with all the sentences
	    private List<Sentence> sentList = null;
	    private Sentence sent = null;

	    public List<Sentence> getSentenceList() {
	        return sentList;
	    }

	    boolean out_seg = false;
	    boolean word = false;
	    //boolean tokenized_scheme = false;
	    boolean tok = false;

	    @Override
	    public void startElement(String uri, String localName, String qName, Attributes attributes)
	            throws SAXException {

	        if (qName.equalsIgnoreCase("out_seg")) {
	            //create a new Sentence and put it in Map
	            sent = new Sentence();
	            //initialize list
	            if (sentList == null)
	                sentList = new ArrayList<>();
	        } else if (qName.equalsIgnoreCase("word")) {
	        	word = true;
	        } else if (qName.equalsIgnoreCase("tok")) {
	            String t = attributes.getValue("form0");
	        	String previous = sent.getWord();	        	
	        	if (previous !=null){
	        		sent.setWord(previous + t + " ");	        		
	        	} else {
	        		sent.setWord(t + " ");	        		
	        	}
	        	tok = true;
	        } 
	    }

	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException {
	        if (qName.equalsIgnoreCase("out_seg")) {
	            sentList.add(sent);
	        }
	    }

	    @Override
	    public void characters(char ch[], int start, int length) throws SAXException {
	    } 
	}
	
