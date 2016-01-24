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
 *	@author alan.jesus
 *	@version 1.0
 */
public class InputVariableCoverage extends InputVariable{


	public InputVariableCoverage(SensorContext sensorContext, FileSystem fileSystem, Settings settings) {

		this.LOG = LoggerFactory.getLogger(InputVariableCoverage.class);
		this.DEFAULT_PATH_VARIABLE = "/target/cobertura.txt";
		this.concreteMetric = SedcatMetrics.COBERTURA;
		//especificas de sonar
		this.sensorContext = sensorContext;
		this.fileSystem = fileSystem;
		this.settings = settings;
	}

	@Override
	public double obtainInputVariable() {

		
		
		LOG.info("InputVariableCoverage: extrayendo variable de entrada COBERTURA");

		//1 - Extraemos la ruta
			//a- comprobamos si hay datos en configuracion para esta variable
		
		String rutaVariable = settings.getString(SedcatConstants.COVERAGE_KEY);
		if(rutaVariable.length() == 0){
			LOG.warn("InputVariablesUtils: no hay ruta en configuracion para la variable COBERTURA. Se procede a buscar"
					+ "el valor de esta variable en la ruta por defecto.");
			
			rutaVariable = InputVariablesUtils.obtenerRutaVariable(fileSystem, settings, DEFAULT_PATH_VARIABLE);
		}else{
			rutaVariable = InputVariablesUtils.obtenerRutaVariable(fileSystem, settings, SedcatConstants.COVERAGE_KEY);
		}
		
//		validamos que la ruta cumpla con el formato adecuado:
		InputVariablesUtils.checkVariablePath(rutaVariable);
		//2 - Extraemos el valor del fichero
		this.metricValue = InputVariablesUtils.obtenerValorDesdeRuta(rutaVariable);
		
//		comprobamos que el valor de la variable esta en los rangos permitidos y se adecua al formato
		InputVariablesUtils.checkInputVariableValue(this.metricValue);
		//3 - Guardamos la variable en forma de medida para representarla en el widget
		InputVariablesUtils.guardarVariableMedida(this.sensorContext, this.concreteMetric, this.metricValue);
		
		
		
		LOG.info("InputVariableCoverage: variable de entrada COBERTURA extraida con exito");
		
		return this.metricValue;
		
	}

}
