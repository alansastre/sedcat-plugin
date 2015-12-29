/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import java.util.ArrayList;

import es.unileon.sonarqube.sedcat.analyzers.InputVariablesGeneral;
import es.unileon.sonarqube.sedcat.xfuzzy.pruebaCALIDAD2;

/**
 * 
 *  Clase encargada de llamar a un sistema experto u otro. 
 *	@author alan.sastre
 *	@version 1.0
 */
public class StrategiesManager {

	
	public double[] outputVariables;
	
	
	/**
	 * Para la version 1.0 el sistema experto se establece de manera estática.
	 * En siguientes versiones se creara un metodo establecer sistema experto
	 */
	public StrategiesManager(InputVariablesGeneral inputVariables) {
		
		
//		1 - preparar los datos de entrada: pasar de Arraylist a array
		double[] variablesEntrada = arrayListToArray(inputVariables.metricsValues);
		
		SistemaExpertoA procesado = new SistemaExpertoA(variablesEntrada);
		this.outputVariables = procesado.xfuzzyProcess();

	}

	
	
	private double[] arrayListToArray(ArrayList<Double> metricsValues) {
		// TODO Auto-generated method stub
		
		double[] variablesEntrada = new double[metricsValues.size()];
		
		int i = 0;
		for (Double double1 : metricsValues) {
			variablesEntrada[i] = double1;
			i++;
			
		}
		
		return variablesEntrada;
	}

}
