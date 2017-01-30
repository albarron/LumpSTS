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
	    	// A shift is not a good solution for this method because the larger the values,
	    	// the smaller the differences between instances
	    	// With negative values it is not a similarity any more because it doesn't lie in
	    	// the [0,1] interval. However, it should be still useful as a score (TOCHECK)
	    	float shift = 100f;
	    	sim = Functions.genJaccardSim(VectorSTS.shift(v1, shift), VectorSTS.shift(v2, shift));
	    } else if (measure.equalsIgnoreCase("KL")){
	    	// Kullback-Leibler and Jensen-Shannon need probabilities as inputs.
	    	// We make a softmax on the input vectors, but not sure that a softmax on each vector
	    	// independently make any sense
	    	double simTemp1 = Functions.KLDiv(VectorSTS.softmax(v1), VectorSTS.softmax(v2));
	    	double simTemp2 = Functions.KLDiv(VectorSTS.softmax(v2), VectorSTS.softmax(v1));
	    	sim = (simTemp1+simTemp2)/2.f;
	    } else if (measure.equalsIgnoreCase("JS")){
	    	sim = Functions.JSDiv(VectorSTS.softmax(v1), VectorSTS.softmax(v2));
	    } 
	    
		return sim;
	}


}
