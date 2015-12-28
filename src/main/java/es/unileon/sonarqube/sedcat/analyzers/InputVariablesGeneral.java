/**
 * Analizador que devuelve variables de entrada a partir de ficheros 
 */
package es.unileon.sonarqube.sedcat.analyzers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.config.Settings;

import es.unileon.sonarqube.sedcat.start.SedcatConstants;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;
import es.unileon.sonarqube.sedcat.start.SedcatSensor;

/**
 * @author alan.sastre
 *
 */
public class InputVariablesGeneral {

	private static final Logger LOG = LoggerFactory.getLogger(InputVariablesGeneral.class);


	public ArrayList<Double> metricsValues;

	public InputVariablesGeneral(SensorContext sensorContext, FileSystem fileSystem, Settings settings) {
		

		LOG.info("InputVariablesGeneral: extrayendo variables de entrada");
		//Llamar a los analizadores
		InputVariableExito exito = new InputVariableExito(sensorContext, fileSystem, settings);
		InputVariableCoverage coverage = new InputVariableCoverage(sensorContext, fileSystem, settings);
		
		
    	//Almacenar los datos de cada variable obtenida
		metricsValues.add(exito.exitoValue);
		metricsValues.add(coverage.coverageValue);
		
		
	}

	
	
}
