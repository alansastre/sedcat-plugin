/**
 * 
 */
package es.unileon.sonarqube.sedcat.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.Measure;

import es.unileon.sonarqube.sedcat.start.SedcatMetrics;


/**
 *  Clase encargada de almacenar la metrica calidad obtenida en forma de medida
 *	@author alan.sastre
 *	@version 1.0
 */
public class QualityMeasureStore {

	private static final Logger LOG = LoggerFactory.getLogger(QualityMeasureStore.class);
	private static final long QUALITY_VALUE_MIN = 0;
	private static final long QUALITY_VALUE_MAX = 100;
	
	public QualityMeasureStore(double[] qualityMeasure, SensorContext sensorContext){
		
		double qualityValue = qualityMeasure[0];
		this.checkValue(qualityValue);
		
		Measure measure = new Measure(SedcatMetrics.QUALITY_MEASURE, qualityValue);
		sensorContext.saveMeasure(measure);
		
		LOG.info("Metrica calidad almacenada correctamente.");
		
	}
	
	/**
	 * Metodo encargado de verificar que el conjunto de acciones obtenido es coherente
	 * y se encuentra dentro del rango de acciones predefinido
	 * @param actionSet
	 */
	private void checkValue(double qualityValue) {

		if(qualityValue < QUALITY_VALUE_MIN || qualityValue> QUALITY_VALUE_MAX){
			
			//El conjunto de acciones no entra en el rango permitido
			LOG.error("La metrica calidad obtenida no entra en el rango especificado 0-100.");
			System.exit(-1);
		}
		
	}
	
}
