package cat.lump.sts2017.similarity;

import cat.lump.aq.basics.algebra.vector.Vector;


/**
 * Collection of similarity measures
 * 
 * @author cristina
 * @since Jan 25, 2017
 */
public class Functions {
	
	/**
	 * Computes the cosine similarity measure between two vectors
	 * 
	 * sim(v1,v2) = (v1*v2) / (|v1||v2|)
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double cosineSim(Vector v1, Vector v2) {
		double result;
		if (v1.magnitude()==0 || v2.magnitude()==0) {
			result = 0;
		} else {
			result = v1.dotProduct(v2)/(v1.magnitude() * v2.magnitude());
		}
		return result;
	}

	
	/**
	 * Computes the generalised Jaccard similarity measure between two vectors
	 * 
	 * sim(v1,v2) = sum_i min(v1i, v2i) / sum_i max(v1i, v2i)
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double genJaccardSim(Vector v1, Vector v2) {

		if (!v1.sameCardinality(v2)){
			return 0;
			// add the error
		}
		float [] componentsV1 = v1.get();
		float [] componentsV2 = v2.get();
		
		double num = 0;
		double denom = 0;
		for (int i=0; i<componentsV1.length ; i++) {	
			if (componentsV1[i] < 0 || componentsV2[i] < 0) {
				System.out.println("Warning, negative component!");
			}
			num = num + Math.min(componentsV1[i], componentsV2[i]);
			denom = denom + Math.max(componentsV1[i], componentsV2[i]);
		}

		double result = 0;
		if (denom != 0){
			result = num/denom;
		} else {
			result = 0;
		}
		
		return result;
	}


	/**
	 * Computes the Kullback-Leibler divergence measure between two vectors
	 * 
	 * D(V1||V2) = \sum_i V1i log(V1i/V2i) 
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double KLDiv(Vector v1, Vector v2) {

		if (!v1.sameCardinality(v2)){
			return 0;
			// add the error
		}
		float [] componentsV1 = v1.get();
		float [] componentsV2 = v2.get();
		double result = 0;
		for (int i=0; i<componentsV1.length ; i++) {	
			if (componentsV2[i]!=0){
				result = result + componentsV1[i]*Math.log10(componentsV1[i]/componentsV2[i]);
			}
		}
		
		return result;
	}

	
	/**
	 * Computes the Jensen-Shannon divergence measure between two vectors
	 * 
	 * // related to KL but avoid problems with v2 having null components
	 * D(v1||v2) = KL(v1||(v1+v2)/2) + KL(v2||(v1+v2)/2)
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double JSDiv(Vector v1, Vector v2) {
		double result = 0;

		if (!v1.sameCardinality(v2)){
			return 0;
			// add the error
		}
		Vector norm = v1.add(v2);
		norm.divideEquals(2.f);
		result = KLDiv(v1, norm) + KLDiv(v2, norm);
		
		return result;
	}


}
