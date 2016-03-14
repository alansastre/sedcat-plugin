/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;

/**
 * Interfaz que especifica el metodo que implementaran las estrategias referentes a la utilizacion de sistemas expertos. 
 * 
 *	@author alan.sastre
 *	@version 1.0
 */
@Deprecated
public interface IExpertSystemStrategy {

	
	public void xfuzzyProcess(MeasureComputerContext context);
	
	
}
