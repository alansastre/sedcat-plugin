/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;


/**
 * 	Interfaz que declara los métodos principales de procesamiento xfuzzy, común a todos
 * los sistemas expertos.
 *	@author alan.sastre
 *	@version 1.0
 */
public interface FuzzyInferenceEngine {
	 public double[] crispInference(double[] input);
	 public double[] crispInference(MembershipFunction[] input);
	 public MembershipFunction[] fuzzyInference(double[] input);
	 public MembershipFunction[] fuzzyInference(MembershipFunction[] input);
	}