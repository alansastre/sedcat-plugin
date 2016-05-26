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

	private String qualityMessage;
	
	public QualityMeasureStore() {

		this.LOG = LoggerFactory.getLogger(QualityMeasureStore.class);
		this.MIN_VALUE = 0;
		this.MAX_VALUE = 100;
		this.MEASURE_KEY = SedcatMetricsKeys.QUALITY_MEASURE_KEY;
		this.MESSAGE_KEY = SedcatMetricsKeys.QUALITY_MESSAGE_KEY;
		this.ERROR_MESSAGE = "Error, la metrica calidad obtenida esta fuera del limite permitido";
		
		this.qualityMessage = "This quality metric is calculated at project level, so no data at the component"
				+ " level that can be displayed. This metric is obtained from sets of rules activated to varying"
				+ " degrees depending on input metrics read about the project in each analysis.";
	}

	@Override
	protected void saveMeasure(double[] outputMeasureValues, MeasureComputerContext context) {

		// Extraemos el resultado, en este caso esta en la primera posicion
		double measureValue = outputMeasureValues[0];

		// Almacenar el mensaje del conjunto de acciones en forma de double
		context.addMeasure(this.MEASURE_KEY, measureValue);
		
		//Almacenar mensaje especifico a mostrar en la pantalla de detalle de la calidad
		context.addMeasure(this.MESSAGE_KEY,
				"</br>Quality of unit tests is "+measureValue +" %</br></br>" + qualityMessage + MESSAGE_ALERT_HACK);

		LOG.info("Metrica calidad almacenada correctamente, ha sido: " + measureValue);

	}

}
