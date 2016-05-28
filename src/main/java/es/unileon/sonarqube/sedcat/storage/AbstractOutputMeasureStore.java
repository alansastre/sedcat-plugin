package es.unileon.sonarqube.sedcat.storage;

import org.slf4j.Logger;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;

/**
 * @author alan.sastre
 * @version 1.0
 */
public abstract class AbstractOutputMeasureStore {

	protected Logger LOG;
	//minimum value for output metric
	protected double MIN_VALUE;
	//maximum value for output metric 
	protected double MAX_VALUE;
	//message in case of error
	protected String ERROR_MESSAGE;
	//output metric value
	protected String MEASURE_KEY;
	//output metric message
	protected String MESSAGE_KEY;
	//Message to disable alert "Not gound Items"
	public static final String MESSAGE_ALERT_HACK = "<style type='text/css'>.alert-info{visibility:hidden;}</style>";
	//Message in case of no actions
	protected static final String MESSAGE_NO_ACTIONS = "No se han encontrado posibles soluciones.";

	

	/**
	 * Template method - realiza el proceso de almacenamiento de los resultados.
	 * 
	 * @param outputMeasureValues
	 *            - array donde esta los resultados a almacenar
	 * @param context
	 *            - contexto necesario para poder almacenar los resultados en
	 *            forma de medidas
	 */
	public final void outputMeasureStore(double[] outputMeasureValues, MeasureComputerContext context) {

		// Variant Part
		this.saveMeasure(this.checkOutputDataSet(outputMeasureValues), context);

	}

	/**
	 * Almacena los resultados de forma particular en cada sistema experto
	 * @param outputMeasureValues array con los resultados
	 * @param context contexto para almacenar los resultados
	 */
	protected abstract void saveMeasure(double[] outputMeasureValues, MeasureComputerContext context);


	/**
	 * Comprueba array de resultados, lo devuelve tal cual si es correcto o devuelve uno vacío 
	 * si no encaja en las especificaciones.
	 * @param outputMeasureValues - array con los resultados a comprobar
	 * @return array de resultados en caso de que sea correcto o 
	 * array vacío en caso de que sea nulo o los resultados no estén en los rangos permitidos
	 */
	protected double[] checkOutputDataSet(double[] outputMeasureValues) {

		double[] dataSet = new double[0];

		if (outputMeasureValues == null || !(outputMeasureValues.length > 0)) {
			LOG.warn("No hay resultados.");
			return dataSet;
		}

		for (int i = 0; i < outputMeasureValues.length; i++) {

			if (outputMeasureValues[i] < this.MIN_VALUE || outputMeasureValues[i] > this.MAX_VALUE) {

				LOG.error(this.ERROR_MESSAGE);
				return dataSet;
			}

		}

		// llegados a este punto el array de valores contiene un valor y está
		// en los rangos permitidos
		return outputMeasureValues;

	}

}
