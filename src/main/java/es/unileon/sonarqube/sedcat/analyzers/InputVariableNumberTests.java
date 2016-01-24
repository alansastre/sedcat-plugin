/**
 * 
 */
package es.unileon.sonarqube.sedcat.analyzers;

import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.config.Settings;

import es.unileon.sonarqube.sedcat.start.SedcatConstants;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;

/**
 *	@author alan.jesus
 *	@version 1.0
 */
public class InputVariableNumberTests extends InputVariable{

	
	
	
	public InputVariableNumberTests(SensorContext sensorContext, FileSystem fileSystem, Settings settings) {

		this.LOG = LoggerFactory.getLogger(InputVariableNumberTests.class);
		this.DEFAULT_PATH_VARIABLE = "/target/numerodetest.txt";
		this.concreteMetric = SedcatMetrics.NUMBER_TESTS;
		//especificas de sonar
		this.sensorContext = sensorContext;
		this.fileSystem = fileSystem;
		this.settings = settings;

	}
	
	
	@Override
	public double obtainInputVariable() {

		
		LOG.info("InputVariableNumberTests: extrayendo variable de entrada NUMEROTEST");

		//1 - Extraemos la ruta
			//a- comprobamos si hay datos en configuracion para esta variable
		
		String rutaVariable = settings.getString(SedcatConstants.NUMBER_TESTS_KEY);
		if(rutaVariable.length() == 0){
			LOG.warn("InputVariableNumberTests: no hay ruta en configuracion para la variable NUMEROTEST. Se procede a buscar"
					+ "el valor de esta variable en la ruta por defecto.");
			
			rutaVariable = InputVariablesUtils.obtenerRutaVariablePorDefecto(fileSystem, settings, DEFAULT_PATH_VARIABLE);
		}else{
			rutaVariable = InputVariablesUtils.obtenerRutaVariable(fileSystem, settings, SedcatConstants.NUMBER_TESTS_KEY);
		}
		
//		validamos que la ruta cumpla con el formato adecuado:
		InputVariablesUtils.checkVariablePath(rutaVariable);
		//2 - Extraemos el valor del fichero
		this.metricValue = InputVariablesUtils.obtenerValorDesdeRuta(rutaVariable);
		
//		comprobamos que el valor de la variable esta en los rangos permitidos y se adecua al formato
		InputVariablesUtils.checkInputVariableValue(this.metricValue);
		//3 - Guardamos la variable en forma de medida para representarla en el widget
		InputVariablesUtils.guardarVariableMedida(this.sensorContext, this.concreteMetric, this.metricValue);
		
		
		
		LOG.info("InputVariableNumberTests: variable de entrada NUMEROTEST extraida con exito");
		
		return this.metricValue;
		
	}

}
