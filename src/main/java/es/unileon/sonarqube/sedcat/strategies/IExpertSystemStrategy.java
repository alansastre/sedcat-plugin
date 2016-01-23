/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import org.sonar.api.batch.SensorContext;

/**
 * Interfaz que especifica el metodo que implementaran las estrategias referentes a la utilizacion de sistemas expertos. 
 * 
 *	@author alan.sastre
 *	@version 1.0
 */
public interface IExpertSystemStrategy {

	
	public void xfuzzyProcess(double[] inputVariables, SensorContext sensorContext);
	
	
}
