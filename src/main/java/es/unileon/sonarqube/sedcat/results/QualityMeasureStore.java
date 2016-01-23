/**
 * 
 */
package es.unileon.sonarqube.sedcat.results;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.Measure;

import es.unileon.sonarqube.sedcat.start.SedcatMetrics;


/**
 *  Clase encargada de almacenar la metrica calidad obtenida en forma de medida
 *	@author alan.sastre
 *	@version 1.0
 */
public class QualityMeasureStore {

	
	
	public QualityMeasureStore(double[] qualityMeasure, SensorContext sensorContext){
		
		
		Measure measure = new Measure(SedcatMetrics.QUALITY_MEASURE, qualityMeasure[0]);
		sensorContext.saveMeasure(measure);
	}
	
}
