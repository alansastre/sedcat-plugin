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
//import es.unileon.sonarqube.sedcat.start.SedcatSensor;

/**
 * @author alan.sastre
 *
 */
public class InputVariablesGeneral {

	//variable que define el numero total de variables de entrada
	private static final int VARIABLES_NUMBER = 5;
	
	private static final Logger LOG = LoggerFactory.getLogger(InputVariablesGeneral.class);
	public ArrayList<Double> metricsValues;

	
	
	
	public InputVariablesGeneral(SensorContext sensorContext, FileSystem fileSystem, Settings settings) {
		
		//inicializar arraylist
		metricsValues = new ArrayList<Double>();

		LOG.info("InputVariablesGeneral: extrayendo variables de entrada");
		
		//Crear variables de entrada
		
			//EXITO
		InputVariableSuccess success = new InputVariableSuccess(sensorContext, fileSystem, settings);
		metricsValues.add(success.obtainInputVariable());
		
			//COBERTURA
		InputVariableCoverage coverage = new InputVariableCoverage(sensorContext, fileSystem, settings);
		metricsValues.add(coverage.obtainInputVariable());
		
	
			//MUTANTES
		InputVariableMutants mutants = new InputVariableMutants(sensorContext, fileSystem, settings);
		metricsValues.add(mutants.obtainInputVariable());
		
			//NUMEROTEST
		InputVariableNumberTests numbertests = new InputVariableNumberTests(sensorContext, fileSystem, settings);
		metricsValues.add(numbertests.obtainInputVariable());
	
			//NUMBER OF CODE LINES
		InputVariableCodeLines numbercode_lines = new InputVariableCodeLines(sensorContext, fileSystem, settings);
		metricsValues.add(numbercode_lines.obtainInputVariable());
		
		LOG.info("InputVariablesGeneral: variables de entrada extraidas");

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
