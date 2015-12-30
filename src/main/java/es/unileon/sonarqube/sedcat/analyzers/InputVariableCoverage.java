/**
 * 
 */
package es.unileon.sonarqube.sedcat.analyzers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.Metric;

import es.unileon.sonarqube.sedcat.start.SedcatConstants;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class InputVariableCoverage {

	
	private static final Logger LOG = LoggerFactory.getLogger(InputVariableCoverage.class);
	private static final String DEFAULT_PATH_COVERAGE = "/target/";

	
	public Metric coverageMetric;
	public double coverageValue;

	public InputVariableCoverage(SensorContext sensorContext, FileSystem fileSystem, Settings settings) {
		
		
		this.coverageMetric = SedcatMetrics.COBERTURA;
		
		
		LOG.info("InputVariableExito: extrayendo variable de entrada COBERTURA");

		//1 - Extraemos la ruta
			//a- comprobamos si hay datos en configuracion para esta variable
		
		String rutaVariable = settings.getString(SedcatConstants.COVERAGE_KEY);
		if(rutaVariable.length() == 0){
			LOG.warn("InputVariablesUtils: no hay ruta en configuracion para la variable COBERTURA. Se procede a buscar"
					+ "el valor de esta variable en la ruta por defecto.");
			
			rutaVariable = InputVariablesUtils.obtenerRutaVariable(fileSystem, settings, DEFAULT_PATH_COVERAGE);
		}else{
			rutaVariable = InputVariablesUtils.obtenerRutaVariable(fileSystem, settings, SedcatConstants.COVERAGE_KEY);
		}
		
		//FIXME meter validacion de ruta
		//2 - Extraemos el valor del fichero
		this.coverageValue = InputVariablesUtils.obtenerValorDesdeRuta(rutaVariable);
		
		//3 - Guardamos la variable en forma de medida para representarla en el widget
		//FIXME meter validacion de variable obtenida
		InputVariablesUtils.guardarVariableMedida(sensorContext, this.coverageMetric, this.coverageValue);
		
		
		
		LOG.info("InputVariableExito: variable de entrada COBERTURA extraida con exito");
		
		
	}

}
