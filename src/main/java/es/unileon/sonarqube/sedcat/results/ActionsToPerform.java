/**
 * 
 */
package es.unileon.sonarqube.sedcat.results;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.Measure;

import es.unileon.sonarqube.sedcat.start.SedcatMetrics;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class ActionsToPerform {

	
	public ActionsToPerform(SensorContext sensorContext, double qualityMeasure){
		
		/*
		 * 1- determinar cual ha sido el escenario de acciones y localizar la propiedad
		 * que lo define dentro de los ficheros properties. Esta propiedad contendra el texto que aparecera 
		 * en el widget
		 */
		//FIXME
		
		//2 - Almacenar acciones en forma de mensaje
		Measure measure = new Measure(SedcatMetrics.ACTIONS_TO_PERFORM, "");
		sensorContext.saveMeasure(measure);
	}

}
