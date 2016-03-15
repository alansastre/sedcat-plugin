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
 *	@author alan.sastre
 *	@version 1.0
 */
@Deprecated
public class InputVariableCodeLines extends InputVariable{

	
	
	public InputVariableCodeLines(SensorContext sensorContext, FileSystem fileSystem, Settings settings) {

		this.LOG = LoggerFactory.getLogger(InputVariableCodeLines.class);
		this.DEFAULT_PATH_VARIABLE = "/target/numbercodelines.txt";
		this.concreteMetric = SedcatMetrics.CODE_LINES;
		//especificas de sonar
		this.sensorContext = sensorContext;
		this.fileSystem = fileSystem;
		this.settings = settings;
		
	}

	
	@Override
	public double obtainInputVariable() {
		
		
		LOG.info("InputVariableCodeLines: extrayendo variable de entrada CODE_LINES");

		//1 - Extraemos la ruta
			//a- comprobamos si hay datos en configuracion para esta variable
		
		String rutaVariable = settings.getString(SedcatConstants.NUMBER_CODE_LINES_KEY);
		if(rutaVariable.equalsIgnoreCase(this.DEFAULT_PATH_VARIABLE)){
			LOG.warn("InputVariableCodeLines: no hay ruta en configuracion para la variable CODE_LINES. Se procede a buscar"
					+ "el valor de esta variable en la ruta por defecto.");
			
			rutaVariable = InputVariablesUtils.obtenerRutaVariablePorDefecto(fileSystem, settings, this.DEFAULT_PATH_VARIABLE);
		}else{
			rutaVariable = InputVariablesUtils.obtenerRutaVariable(fileSystem, settings, SedcatConstants.NUMBER_CODE_LINES_KEY);
		}
		
//		validamos que la ruta cumpla con el formato adecuado:
		InputVariablesUtils.checkVariablePath(rutaVariable);
		//2 - Extraemos el valor del fichero
		this.metricValue = InputVariablesUtils.obtenerValorDesdeRuta(rutaVariable);
		
//		comprobamos que el valor de la variable esta en los rangos permitidos y se adecua al formato
		InputVariablesUtils.checkInputVariableValue(this.metricValue);
		//3 - Guardamos la variable en forma de medida para representarla en el widget
		InputVariablesUtils.guardarVariableMedida(this.sensorContext, this.concreteMetric, this.metricValue);
		
		
		
		LOG.info("InputVariableCodeLines: variable de entrada CODE_LINES extraida con exito");
		LOG.info("SEDCAT: el valor obtenido ha sido: " +  this.metricValue);
		return this.metricValue;
	}

}
