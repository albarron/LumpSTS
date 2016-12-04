package cat.lump.sts2017.prepro;

/**
 * Sentence object. Currently contains word/pos/lemma as used by the MADAlemmatisier class.
 * Can be extended for more general representations.
 *   
 * @author cristina
 * @since Dec 3, 2016
 *
 */
public class Sentence {

		//private String id;
	    private String word;
	    private String pos;
	    private String lemma;
	    
	    /*public String getId() {
	        return id;
	    }
	    public void setId(id) {
	        this.id = id;
	    }*/
	    public String getWord() {
	        return word;
	    }
	    public void setWord(String word) {
	        this.word = word;
	    }
	    public String getPos() {
	        return pos;
	    }
	    public void setPos(String pos) {
	        this.pos = pos;
	    }
	    public String getLemma() {
	        return lemma;
	    }
	    public void setLemma(String lemma) {
	        this.lemma = lemma;
	    }
	    
	    
	    @Override
	    public String toString() {
	        return this.word;
	    }
}
