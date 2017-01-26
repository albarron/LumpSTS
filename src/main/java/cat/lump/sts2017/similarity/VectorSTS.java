package cat.lump.sts2017.similarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import cat.lump.aq.basics.algebra.vector.Vector;


/**
 * Bunch of method related to the class Vector. Some of them must be moved to
 * cat.lump.aq.basics.algebra.vector.Vector, some other to Utils in this package
 * 
 * @author cristina
 * @since Jan 26, 2017
 */
public class VectorSTS extends Vector {

	private static final long serialVersionUID = -2475949057724536722L;

	public VectorSTS(float[] values) {
		super(values);
	}
	
	/**
	 * Extracts the floats present in a string and returns them
	 * in a Vector 
	 * 
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
	
	
	/**
	 * Applies softmax to an input vector
	 * 
	 * @param v
	 * @return
	 */
	public static Vector softmax(Vector v){
		
		float [] componentsV = v.get();
		float [] components = new float[v.length()];
		double sum = 0;
		for (int i=0; i<componentsV.length; i++) {	
			sum = sum + Math.exp(componentsV[i]);
		}
		for (int i=0; i<componentsV.length; i++) {	
			components[i] = (float) (Math.exp(componentsV[i])/sum);
		}
		
		return new Vector(components);		
	}

	
	/**
	 * Shifts all the components of a vector by a constant value
	 * 
	 * @param v
	 * @return
	 */
	public static Vector shift(Vector v, float shift){
		
		float [] componentsV = v.get();
		for (int i=0; i<componentsV.length; i++) {	
			componentsV[i] = componentsV[i]+shift;
		}		
		return new Vector(componentsV);		
	}
	
	
}
