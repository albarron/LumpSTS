package cat.lump.sts2017.similarity;

import cat.lump.aq.basics.algebra.vector.Vector;

public class Functions {
	
	/**Computes the cosine similarity measure between two vectors
	 * 
	 * sim(v1,v2) = (v1 * v2) / (|v1||v2|)
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double cosineSim(Vector v1, Vector v2) {
		return  (v1.dotProduct(v2) / 
				(v1.magnitude() * v2.magnitude()) );
	}



}
