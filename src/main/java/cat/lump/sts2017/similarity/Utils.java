package cat.lump.sts2017.similarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

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
	    	sim = Functions.genJaccardSim(v1, v2);
	    } else if (measure.equalsIgnoreCase("KL")){
	    	sim = Functions.KLDiv(v1, v2);
	    } else if (measure.equalsIgnoreCase("JS")){
	    	sim = Functions.JSDiv(v1, v2);
	    } 
	    // add more measures here
	    
		return sim;
	}

	/**
	 * Extracts the floats present in a string and returns them
	 * in a Vector 
	 * 
	 * TODO: move this method to the Vector class?

	 * @param sentence
	 * @return vector
	 */
	public static Vector readVector(String sentence) {
		List<Float> components = new ArrayList<Float>();
	    Scanner scanner = new Scanner(sentence);
	    scanner.useLocale(Locale.ENGLISH);
	    while (scanner.hasNext()) {
	    	if (scanner.hasNextFloat()) {
	    		components.add(scanner.nextFloat());
	    	}
	    }
	    scanner.close();
	    Vector v = new Vector(getFloatsArray(components));
		return v;
	}
	
	/**
	 * Transforms a list of Float values into an array of float.
	 * TODO: move this method to a most appropriate place
	 *  
	 * @param values
	 *            the list of Float
     * @return the array of floats
	 */
	public static float[] getFloatsArray(List<Float> values) {
	    int length = values.size();
	    float[] result = new float[length];
	    for (int i = 0; i < length; i++) {
	      result[i] = values.get(i).floatValue();
	    }
	    return result;
	}
}
