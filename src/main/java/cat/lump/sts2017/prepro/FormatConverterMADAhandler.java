package cat.lump.sts2017.prepro;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class FormatConverterMADAhandler  extends DefaultHandler {

	    //List to hold the object with all the sentences
	    private List<Sentence> sentList = null;
	    private Sentence sent = null;

	    public List<Sentence> getSentenceList() {
	        return sentList;
	    }

	    boolean out_seg = false;
	    boolean word = false;
	    boolean morph_feature_set = false;

	    @Override
	    public void startElement(String uri, String localName, String qName, Attributes attributes)
	            throws SAXException {

	        if (qName.equalsIgnoreCase("out_seg")) {
	            //create a new Sentence and put it in Map
	            String id = attributes.getValue("id");
	            sent = new Sentence();
	            //sent.setId(id);
	            //initialize list
	            if (sentList == null)
	                sentList = new ArrayList<>();
	        } else if (qName.equalsIgnoreCase("word")) {
	            String w = attributes.getValue("word");
	        	word = true;
	        } else if (qName.equalsIgnoreCase("morph_feature_set")) {
	            String p = attributes.getValue("pos");
	            String l = attributes.getValue("lema");
	        	morph_feature_set = true;
	        } 
	    }

	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException {
	        if (qName.equalsIgnoreCase("out_seg")) {
	            //add Sentence object to list
	            sentList.add(sent);
	        }
	    }

	    @Override
	    public void characters(char ch[], int start, int length) throws SAXException {

	        if (word) {
	        	String previous = sent.getWord();
	        	if (previous.contains(" ")){
	        		sent.setWord(previous+new String(ch, start, length));
	        	} else {
		            sent.setWord(new String(ch, start, length));
	        	}
	      	    word = false;
	        } else if (morph_feature_set) {
	            sent.setPos(new String(ch, start, length));
	            sent.setLema(new String(ch, start, length));
	            morph_feature_set = false;
	        } 
	    }
	}
	
