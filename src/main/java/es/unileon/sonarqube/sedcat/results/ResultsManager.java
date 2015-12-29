/**
 * 
 */
package es.unileon.sonarqube.sedcat.results;

import org.sonar.api.batch.SensorContext;

import es.unileon.sonarqube.sedcat.strategies.StrategiesManager;

/**
 * 
 *  Clase principal para gestionar los distintos tipos de resultados obtenidos por el sistema experto.
 *	@author alan.sastre
 *	@version 1.0
 */
public class ResultsManager {

	/**
	 *  Metodo que llama a cada gestor individual de cada resultado pasando unicamente el resultado particular que le interesa a cada uno
	 */
	public ResultsManager(SensorContext sensorContext, StrategiesManager outputVariables) {
		
		
		// 1- Gestionar medida de calidad
		QualityMeasure gestionarMedidaCalidad = new QualityMeasure(sensorContext,outputVariables.outputVariables[0]);
		
		
//		2 - Gestionar acciones
		
	}

}
