/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;
import org.sonar.api.batch.SensorContext;

import es.unileon.sonarqube.sedcat.results.ActionsToPerformStore;
import es.unileon.sonarqube.sedcat.xfuzzy.pruebaCALIDAD2;

/**
 *  Sistema experto que obtiene las acciones a realizar buscadas
 *	@author alan.sastre
 *	@version 1.0
 */
public class ExpertSystemActions implements IExpertSystemStrategy{

	
	

	public void xfuzzyProcess(double[] inputVariables, SensorContext sensorContext) {
		
		pruebaCALIDAD2 prueba = new pruebaCALIDAD2();
		
		ActionsToPerformStore metricToStore = new ActionsToPerformStore(prueba.crispInference(inputVariables), sensorContext);

	}

}