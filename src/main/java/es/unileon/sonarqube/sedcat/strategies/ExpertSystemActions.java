/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;

import es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.actions.Acciones_1;

/**
 *  Sistema experto que obtiene las acciones a realizar buscadas
 *	@author alan.jesus
 *	@version 1.0
 */
public class ExpertSystemActions implements IExpertSystemStrategy{

	
	private static final Logger LOG = LoggerFactory.getLogger(ExpertSystemActions.class);

	public double[] xfuzzyProcess(double[] inputMetrics) {
		
		LOG.info("Ejecutando sistema experto para acciones.");
		
		Acciones_1 actions = new Acciones_1();
		return actions.crispInference(inputMetrics);

	}

}