/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import org.sonar.api.batch.SensorContext;

import es.unileon.sonarqube.sedcat.storage.QualityMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad_1;

/**
 *  Sistema experto que obtiene la metrica de calidad buscada
 *	@author alan.sastre
 *	@version 1.0
 */
public class ExpertSystemQuality implements IExpertSystemStrategy{

	
	public void xfuzzyProcess(double[] inputVariables, SensorContext sensorContext) {
		
		Calidad_1 prueba = new Calidad_1();

    	QualityMeasureStore metricToStore = new QualityMeasureStore(prueba.crispInference(inputVariables), sensorContext);
	}

}
