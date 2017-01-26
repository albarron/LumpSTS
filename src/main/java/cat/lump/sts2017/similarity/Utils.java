package cat.lump.sts2017.similarity;

import cat.lump.aq.basics.algebra.vector.Vector;

public class Utils {

	  
	/**
	 * Call to the specific similarity measure demanded
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double calculateMeasure(Vector v1, Vector v2, String measure) {

		double sim = 0f;
	    if (measure.equalsIgnoreCase("cosine")){
	    	sim = Functions.cosineSim(v1, v2);
	    } else if (measure.equalsIgnoreCase("jaccard")){
	    	// Jaccard similarity assumes input vectors have positive reals components
	    	float shift = 100f;
	    	sim = Functions.genJaccardSim(VectorSTS.shift(v1, shift), VectorSTS.shift(v2, shift));
	    } else if (measure.equalsIgnoreCase("KL")){
	    	sim = Functions.KLDiv(VectorSTS.softmax(v1), VectorSTS.softmax(v2));
	    } else if (measure.equalsIgnoreCase("JS")){
	    	sim = Functions.JSDiv(VectorSTS.softmax(v1), VectorSTS.softmax(v2));
	    } 
	    
		return sim;
	}


}
