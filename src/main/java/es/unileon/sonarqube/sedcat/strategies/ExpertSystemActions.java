/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;
import org.sonar.api.batch.SensorContext;

import es.unileon.sonarqube.sedcat.storage.ActionsToPerformStore;
import es.unileon.sonarqube.sedcat.xfuzzy.actions.Acciones_1;

/**
 *  Sistema experto que obtiene las acciones a realizar buscadas
 *	@author alan.jesus
 *	@version 1.0
 */
public class ExpertSystemActions implements IExpertSystemStrategy{

	
	

	public void xfuzzyProcess(double[] inputVariables, SensorContext sensorContext) {
		
		Acciones_1 prueba = new Acciones_1();
		
		ActionsToPerformStore metricToStore = new ActionsToPerformStore(prueba.crispInference(inputVariables), sensorContext);

	}

}