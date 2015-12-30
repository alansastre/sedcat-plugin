/**
 * 
 */
package es.unileon.sonarqube.sedcat.results;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.Measure;

import es.unileon.sonarqube.sedcat.start.SedcatMetrics;
import es.unileon.sonarqube.sedcat.strategies.StrategiesManager;

/**
 *  Clase encargada de almacenar la metrica calidad obtenida en forma de medida
 *	@author alan.sastre
 *	@version 1.0
 */
public class QualityMeasure {

	
	
	public QualityMeasure(SensorContext sensorContext, double qualityMeasure){
		
		
		Measure measure = new Measure(SedcatMetrics.QUALITY_MEASURE, qualityMeasure);
		sensorContext.saveMeasure(measure);
	}
	
}
