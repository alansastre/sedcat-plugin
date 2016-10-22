package org.sonar.plugins.sedcat.strategies;

import org.slf4j.Logger;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.plugins.sedcat.storage.AbstractOutputMeasureStore;

/**
 *  Define el proceso que tiene que seguir un sistema experto
 *  con xfuzzyProcess() y metodos para la extraccion de las metricas
 *  requeridas por los mismos.
 *	@author alan.sastre
 *	@version 1.0.0
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
		double[] inputMetricValues = this.extractValues();
		
		//2- Ejecutar el sistema experto
		double[] outputMeasureValues = this.expertSystem.crispInference(inputMetricValues);
		
		//3- Almacenar metricas de salida como medidas
    	this.measureStorer.outputMeasureStore(outputMeasureValues, this.context);

	}
	
	/**
	 *  Metodo para extraer los valores de entrada en orden particular
	 *  en cada sistema experto.
	 *  
	 * @param context
	 * @return array de double con los valores requeridos por el sistema experto
	 */
	abstract double[] extractValues();

	/*
	 * Metodos para devolver los valores de las metricas de entrada
	 */
	/**
	 * Devuelve el valor de una métrica de tipo double o 0.0 en caso de que dicha métrica 
	 * sea nula.
	 * @param inputMetric String con la métrica de tipo double a obtener su valor
	 * Ejemplo: CoreMetrics.TEST_SUCCESS_DENSITY_KEY
	 * @return valor de la métrica a la que pertene la key
	 */
	protected double getMeasureDoubleChecked(String inputMetricKey){

		double resultado = 0.0;
		if (this.context.getMeasure(inputMetricKey) != null) {
			resultado = this.context.getMeasure(inputMetricKey).getDoubleValue();
		}
		return resultado;
	}
	
	/**
	 * Devuelve el valor de una métrica de tipo int o 0 en caso de que dicha métrica 
	 * sea nula.
	 * @param inputMetric String con la métrica de tipo int a obtener su valor
	 * Ejemplo: CoreMetrics.TESTS_KEY
	 * @return valor de la métrica a la que pertene la key 
	 */
	protected int getMeasureIntChecked(String inputMetricKey){

		int resultado = 0;
		if (this.context.getMeasure(inputMetricKey) != null) {
			resultado = this.context.getMeasure(inputMetricKey).getIntValue();
		}
		return resultado;
	}

	
	
}
