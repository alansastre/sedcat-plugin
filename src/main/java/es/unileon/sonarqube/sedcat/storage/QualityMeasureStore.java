/**
 * 
 */
package es.unileon.sonarqube.sedcat.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.measures.Measure;

import es.unileon.sonarqube.sedcat.start.SedcatMetrics;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;


/**
 *  Clase encargada de almacenar el resultado calidad en forma de metrica.
 *	@author alan.jesus
 *	@version 1.0
 */
public class QualityMeasureStore extends AbstractOutputMeasureStore{


	public QualityMeasureStore(double[] outputMeasureValues, MeasureComputerContext context) {
		
		this.outputMeasureValues=outputMeasureValues;
		this.context=context;
		
		this.LOG = LoggerFactory.getLogger(QualityMeasureStore.class);
		this.MIN_VALUE=0;
		this.MAX_VALUE=100;
		this.MEASURE_KEY=SedcatMetricsKeys.QUALITY_MEASURE_KEY;
		this.ERROR_MESSAGE="Error, la metrica calidad obtenida esta fuera del limite permitido";
		
	}

	@Override
	protected void saveMeasure(double measureValue) {
	
		//de ser correcto, almacenarlo
		this.context.addMeasure(this.MEASURE_KEY, measureValue);
		
		LOG.info("Metrica calidad almacenada correctamente, ha sido: "+measureValue);
		
	}
	
}
