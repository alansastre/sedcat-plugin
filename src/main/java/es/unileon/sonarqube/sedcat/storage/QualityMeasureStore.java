/**
 * 
 */
package es.unileon.sonarqube.sedcat.storage;

import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;

/**
 * Clase encargada de almacenar el resultado calidad en forma de metrica.
 * 
 * @author alan.sastre
 * @version 1.0
 */
public class QualityMeasureStore extends AbstractOutputMeasureStore {

	public QualityMeasureStore() {

		this.LOG = LoggerFactory.getLogger(QualityMeasureStore.class);
		this.MIN_VALUE = 0;
		this.MAX_VALUE = 100;
		this.MEASURE_KEY = SedcatMetricsKeys.QUALITY_MEASURE_KEY;
		this.ERROR_MESSAGE = "Error, la metrica calidad obtenida esta fuera del limite permitido";

	}

	@Override
	protected void saveMeasure(double[] outputMeasureValues, MeasureComputerContext context) {

		// Extraemos el resultado, en este caso esta en la primera posicion
		double measureValue = outputMeasureValues[0];

		// Almacenar el mensaje del conjunto de acciones en forma de double
		context.addMeasure(this.MEASURE_KEY, measureValue);

		LOG.info("Metrica calidad almacenada correctamente, ha sido: " + measureValue);

	}

}
