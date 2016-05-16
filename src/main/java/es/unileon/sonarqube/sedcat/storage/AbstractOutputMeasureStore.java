/**
 * 
 */
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
	//putput metric message
	protected String MESSAGE_KEY;

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

		// Invariant Part
		this.checkOutputDataSet(outputMeasureValues);

		// Variant Part
		this.saveMeasure(outputMeasureValues, context);

	}

	/**
	 * interpretar el valor y almacenarlo segun el tipo de la medida a almacenar
	 * 
	 * @param measureValue
	 * @param context
	 */
	protected abstract void saveMeasure(double[] outputMeasureValues, MeasureComputerContext context);

	/**
	 * Checkea que el valor obtenido esta dentro de los limites
	 * 
	 * @param measureValue
	 */
	protected void checkOutputDataSet(double[] outputMeasureValues) {

		if (outputMeasureValues == null || !(outputMeasureValues.length > 0)) {
			LOG.error("No hay resultados");
			System.exit(-1);
		}

		for (int i = 0; i < outputMeasureValues.length; i++) {

			if (outputMeasureValues[i] < this.MIN_VALUE || outputMeasureValues[i] > this.MAX_VALUE) {

				LOG.error(this.ERROR_MESSAGE);
				System.exit(-1);
			}

		}

	}

}
