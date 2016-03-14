/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

/**
 * Interfaz común para las funciones de pertenencia que componen el sistema experto.
 *	@author alan.sastre
 *	@version 1.0
 */
public interface MembershipFunction {
	 public double compute(double x);
	}
