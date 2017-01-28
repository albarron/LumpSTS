package cat.lump.sts2017.prepro;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cat.lump.aq.basics.log.LumpLogger;

/**
 * Extraction of the PoS and lemma annotations for Turkish text as done by the online
 * application http://tscorpus.com/ts-nlp-toolkit/
 * 
 * //TODO The correct option would be to use an API for PoS tagging and lemmatisation but not
 * a free unique tool has been found (see class MOSESturkishLemmatiser)
 * 
 * @author cristina
 * @since Jan 28, 2016
 */
public class ExtractTRannHTML {
	
	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (ExtractTRannHTML.class.getSimpleName());

	   // quick and dirty method that shouds never be used again
	   public static void main(String[] args) throws IOException {
		   
	        File input = new File("/home/cristinae/pln/LumpSTS/task1/STS2017.eval/cris/track6.tr.tagged.html");
	        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
	        
	        Elements rows = doc.select("tr");
	        boolean eos = false;
	        int j = 0;
	        for(Element row :rows){
	            Elements columns = row.select("td");
	            int i=1;
	            String sentence = "";
	            for (Element column:columns) {
	            	if(i==1){ 
	            		sentence = sentence + column.text() + "|";
	            		if (column.text().equalsIgnoreCase(".")){
	            			eos = true;}
	            	}
	            	else if(i==2){ sentence = sentence + column.text() + "|";}
	            	else if(i==4){ sentence = sentence + column.text() + " ";}
	                i++;
	            }
	            if (eos){
	            	 sentence = sentence + "\n";
	            	 eos = false;
	            	 j++;
	            }
                System.out.print(sentence);
	            //System.out.println();
	        }
	        logger.info("Extracted " + j + " sentences.");
	    }

}
