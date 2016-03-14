/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import org.slf4j.Logger;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore;


/**
 *	@author alan.sastre
 *	@version 1.0
 */
public abstract class AbstractInferenceProcess {
	
	
	protected Logger LOG;
	protected MeasureComputerContext context;
	protected FuzzyInferenceEngine expertSystem;
	protected AbstractOutputMeasureStore measureStorer;
	protected String START_SYSTEM_MESSAGE;
	
	
/**
 * Template method - metodo encargado de realizar el proceso de inferencia para cada sistema 
 * experto. 
 * 
 * Paso 1 - Extraer las variables de entrada particulares del sistema experto
 * Paso 2 - Ejecutar el sistema experto
 * Paso 3 - Almacenar el resultado.
 * 
 * @param context
 */
	public void xfuzzyProcess() {
		
		LOG.info(this.START_SYSTEM_MESSAGE);
		
		//1 - extraer metricas de entrada
		double[] inputMetricValues = this.extractValues(this.context);
		
		//2- Ejecutar el sistema experto
		double[] outputMeasureValues = this.expertSystem.crispInference(inputMetricValues);
		
		//3- Almacenar metricas de salida como medidas
    	this.measureStorer.outputMeasureStore(outputMeasureValues, this.context);

	}
	
	/**
	 *  Metodo para extraer los valores de entrada, particulares en cada sistema experto
	 * @param context
	 * @return
	 */
	abstract double[] extractValues(MeasureComputerContext context);
	

	
}
