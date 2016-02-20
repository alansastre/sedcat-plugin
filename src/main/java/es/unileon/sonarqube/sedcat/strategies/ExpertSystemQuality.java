/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;

import es.unileon.sonarqube.sedcat.analyzers.InputVariablesGeneral;
import es.unileon.sonarqube.sedcat.storage.QualityMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad_1;

/**
 *  Sistema experto que obtiene la metrica de calidad buscada
 *	@author alan.jesus
 *	@version 1.0
 */
public class ExpertSystemQuality implements IExpertSystemStrategy{


	private static final Logger LOG = LoggerFactory.getLogger(ExpertSystemQuality.class);
	
	public double[] xfuzzyProcess(double[] inputMetrics) {
		
		LOG.info("Ejecutando sistema experto para calidad.");
		
		Calidad_1 quality = new Calidad_1();
		return quality.crispInference(inputMetrics);
	}

}
