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

	
	public double[] qualityMetric;
	public double[] actions;
	
	
	/**
	 * Método que establece las estrategias (sistemas expertos)
	 *
	 */
	public StrategiesManager(InputVariablesGeneral inputVariables) {
		
		/*
		 * Por el momento los sistemas expertos correspondientes a variables y a acciones se establecen
		 * de manera estatica, por considerar cierta la precondicion de que las variables de entrada son fijas y
		 * existen datos para las mismas.
		 * 
		 */
		
//		1 - preparar los datos de entrada: pasar de Arraylist a array
		double[] variablesEntrada = this.arrayListToArray(inputVariables.metricsValues);
		
//		2 - validar los datos de entrada: formato y rangos para decidir si llamamos a los sistemas expertos
		this.checkInputValues(variablesEntrada);

//		3 - llamar al sistema experto encargado de obtener la metrica de calidad
		SistemaExpertoCalidad medidaCalidad = new SistemaExpertoCalidad(variablesEntrada);
		this.qualityMetric = medidaCalidad.xfuzzyProcess();
		
//		4 - llamar al sistema encargado de obtener las acciones
		SistemaExpertoAcciones acciones = new SistemaExpertoAcciones(variablesEntrada);
		this.actions = acciones.xfuzzyProcess();

	}

	/**
	 *  Metodo encargado de validar los datos de entrada comprobando que cumplen
	 *  los requisitos de formato, caracteres especiales etc
	 * @param variablesEntrada
	 */
	private void checkInputValues(double[] variablesEntrada) {
		// TODO Auto-generated method stub
		
	}


	//utilidades para el gestor de estrategias
	
	/**
	 * 	Metodo que transforma un arraylist de double en un array 
	 * @param metricsValues
	 * @return
	 */
	private double[] arrayListToArray(ArrayList<Double> metricsValues) {
		
		
		double[] variablesEntrada = new double[metricsValues.size()];
		
		int i = 0;
		for (Double double1 : metricsValues) {
			variablesEntrada[i] = double1;
			i++;
			
		}
		
		return variablesEntrada;
	}
	
	

}
