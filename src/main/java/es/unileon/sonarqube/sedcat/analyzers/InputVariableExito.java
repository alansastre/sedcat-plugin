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
import es.unileon.sonarqube.sedcat.start.SedcatSensor;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class InputVariableExito extends InputVariablesUtils{

	private static final Logger LOG = LoggerFactory.getLogger(InputVariableExito.class);


	private static final String DEFAULT_PATH_SUCCESS = "/target/";

	
	public Metric exitoMetric;
	public double exitoValue;
	
	
	
	public InputVariableExito(SensorContext sensorContext, FileSystem fileSystem, Settings settings) {
				
		//prueba333
		this.exitoMetric = SedcatMetrics.EXITO;
		
		
		LOG.info("InputVariableExito: extrayendo variable de entrada EXITO");

		//1 - Extraemos la ruta
			//a- comprobamos si hay datos en configuracion para esta variable
		
		String rutaVariable = settings.getString(SedcatConstants.SUCCESS_KEY);
		if(rutaVariable.length() == 0){
			LOG.warn("InputVariablesUtils: no hay ruta en configuracion para la variable EXITO. Se procede a buscar"
					+ "el valor de esta variable en la ruta por defecto.");
			
			rutaVariable = InputVariablesUtils.obtenerRutaVariablePorDefecto(fileSystem, settings, DEFAULT_PATH_SUCCESS);
		}else{
			rutaVariable = InputVariablesUtils.obtenerRutaVariable(fileSystem, settings, SedcatConstants.SUCCESS_KEY);
		}
		
		
		//2 - Extraemos el valor del fichero
		this.exitoValue = InputVariablesUtils.obtenerValorDesdeRuta(rutaVariable);
		//3 - Guardamos la variable en forma de medida para representarla en el widget
		InputVariablesUtils.guardarVariableMedida(sensorContext, this.exitoMetric, this.exitoValue);
		
		
		
		LOG.info("InputVariableExito: variable de entrada EXITO extraida con exito");
		
		
	}
}
