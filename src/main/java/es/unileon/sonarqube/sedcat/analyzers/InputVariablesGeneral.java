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
 * @author alan.jesus
 *
 */
public class InputVariablesGeneral {

	//variable que define el numero total de variables de entrada
	private static final int VARIABLES_NUMBER = 5;
	
	private static final Logger LOG = LoggerFactory.getLogger(InputVariablesGeneral.class);
	public ArrayList<Double> metricsValues;

	
	
	
	public InputVariablesGeneral(SensorContext sensorContext, FileSystem fileSystem, Settings settings) {
		

		LOG.info("InputVariablesGeneral: extrayendo variables de entrada");
		//Crear variables de entrada
		
			//EXITO
		InputVariableExito success = new InputVariableExito(sensorContext, fileSystem, settings);

			//COBERTURA
		InputVariableCoverage coverage = new InputVariableCoverage(sensorContext, fileSystem, settings);

			//MUTANTES
		InputVariableMutants mutants = new InputVariableMutants(sensorContext, fileSystem, settings);

			//NUMEROTEST
		InputVariableNumberTests numbertests = new InputVariableNumberTests(sensorContext, fileSystem, settings);

			//NUMBER OF CODE LINES
		InputVariableCodeLines numbercode_lines = new InputVariableCodeLines(sensorContext, fileSystem, settings);

		
		
    	//Ejecutar, almacenar y obtener variables de entrada
		metricsValues.add(success.obtainInputVariable());
		metricsValues.add(coverage.obtainInputVariable());
		metricsValues.add(mutants.obtainInputVariable());
		metricsValues.add(numbertests.obtainInputVariable());
		metricsValues.add(numbercode_lines.obtainInputVariable());
		
		
	}

	
	/**
	 * Metodo que devuelve las variables de entrada adecuadas al formato array que exige la logica xfuzzy (vector double)
	 * @return 
	 */
	public double[] getInputVariables(){
		
		//adecuamos las variables al formato array 
		double[] inputVariables = InputVariablesUtils.arrayListToArray(this.metricsValues);
		
		//Comprobamos que las variables se han obtenido cumplen las precondiciones
		InputVariablesUtils.checkInputValues(inputVariables, VARIABLES_NUMBER);
		
		//devolvemos las variables de entrada
		return inputVariables;
		
		
		
	}
	
	
}
