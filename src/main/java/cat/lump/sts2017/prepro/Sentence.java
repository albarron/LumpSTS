package cat.lump.sts2017.prepro;

public class Sentence {

		//private String id;
	    private String word;
	    private String pos;
	    private String lema;
	    
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
	    public String getLema() {
	        return lema;
	    }
	    public void setLema(String lema) {
	        this.lema = lema;
	    }
	    
	    @Override
	    public String toString() {
	        return this.word+"|"+this.pos+"|" + this.lema + " ";
	    }


}
