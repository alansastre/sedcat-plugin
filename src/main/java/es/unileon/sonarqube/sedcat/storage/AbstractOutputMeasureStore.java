/**
 * 
 */
package es.unileon.sonarqube.sedcat.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public abstract class AbstractOutputMeasureStore {

	protected Logger LOG;
	protected double MIN_VALUE;
	protected double MAX_VALUE;
	protected String MEASURE_KEY;
	protected String ERROR_MESSAGE;
	protected MeasureComputerContext context;
	protected double[] outputMeasureValues;
	
	/**
	 * themplate method 
	 */
	public final void outputMeasureStore() {
		
		//Invariant Part
		double measureValue = outputMeasureValues[0];
		this.checkValue(measureValue);
		//Variant Part
		this.saveMeasure(measureValue);

	}
/**
 *  interpretar el valor y almacenarlo segun el tipo de la medida a almacenar
 * @param measureValue
 * @param context
 */
	abstract protected void saveMeasure(double measureValue);
	
	/**
	 * Checkea que el valor obtenido esta dentro de los limites
	 * @param measureValue
	 */
	protected void checkValue(double measureValue){
		if(measureValue < this.MIN_VALUE || measureValue> this.MAX_VALUE){

			LOG.error(this.ERROR_MESSAGE);
			System.exit(-1);
		}
		
	}
}
