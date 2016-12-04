package cat.lump.sts2017.prepro;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Class to define the behaviour of the SAX parser when extracting information on lemmas.
 *    
 * @author cristina
 * @since Dec 3, 2016
 */
public class MADALemmatiserHandler  extends DefaultHandler {

	    //List to hold the object with all the sentences
	    private List<Sentence> sentList = null;
	    private Sentence sent = null;

	    public List<Sentence> getSentenceList() {
	        return sentList;
	    }

	    boolean out_seg = false;
	    boolean word = false;
	    boolean analysis = false;
	    boolean morph_feature_set = false;

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
	            String w = attributes.getValue("word");
	        	String previous = sent.getWord();
	        	// Concatenating a new word in left-to-right order. 
	        	// We mark bidi regions using unicode format control codepoints:
	        	// Left-to-right embedding (U+202A); Pop directional formatting (U+202C);
	        	if (previous !=null){
	        		sent.setWord(previous + " \u202A" + w + "\u202C");
	        		//System.out.println(previous);
	            } else {
	        		sent.setWord("\u202A" + w + "\u202C");	            	
	            }
	        	word = true;
	        	analysis = false;
	        	morph_feature_set = false;
	        } else if (qName.equalsIgnoreCase("analysis")) {
	        	analysis = true;
	        } else if (qName.equalsIgnoreCase("morph_feature_set") && analysis) {
	            String p = attributes.getValue("pos");
	            String l = attributes.getValue("lemma");
	        	String previous = sent.getWord();	        	
	        	if (previous !=null){
	        		sent.setWord(previous + "|"+ p + "|\u202A"+ l + "\u202C");	        		
//	        		sent.setWord( l + "|"+ p + "|" + previous);	        		
	        	} else {
	        	}
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
/*
	    	System.out.println(""+word+" " + morph_feature_set + " 0" + analysis);
	        if (word) {
	        	sent.setWord(new String(ch, start, length));
	      	    word = false;
	        } else if (morph_feature_set && !analysis) {
	            sent.setPos(new String(ch, start, length));
	            sent.setLemma(new String(ch, start, length));
	            morph_feature_set = false;
	            analysis = false;
	        }*/ 
	    } 
	}
	
