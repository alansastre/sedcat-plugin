/**
 * 
 */
package es.unileon.sonarqube.sedcat.storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.PropertiesBuilder;

import es.unileon.sonarqube.sedcat.analyzers.InputVariablesGeneral;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;

/**
 * Clase encargada de hacer la correspondencia del resultado acciones con los conjuntos acciones
 * predefinidos, una vez encontrado el conjunto lo almacena en forma de metrica.
 *	@author alan.jesus
 *	@version 1.0
 */
public class ActionsToPerformStore {

	
	private static final Logger LOG = LoggerFactory.getLogger(ActionsToPerformStore.class);
	private static final long ACTION_SET_MIN = 0;
	private static final long ACTION_SET_MAX = 32;
	private static final String ACTIONS_PROPERTIES_PATH = "/root/workspace/tools.sonarqube.sedcat/src/main/resources/org/sonar/l10n/expertSystemActions.properties";




	public ActionsToPerformStore(double[] qualityMeasure, SensorContext sensorContext){
		
		LOG.info("Almacenando conjunto de acciones.");
		/*
		 * 1- determinar cual ha sido el escenario de acciones y localizar la propiedad
		 * que lo define dentro de los ficheros properties. Esta propiedad contendra el texto que aparecera 
		 * en el widget
		 */
		double actionSetDouble = qualityMeasure[0];
		LOG.info("actionset sin redondear: "+ actionSetDouble);
		long actionSet = Math.round(actionSetDouble);
		
		this.checkValue(actionSet);

		LOG.info("conjunto de acciones: "+ actionSet);
		
		
		//cargamos propiedades
		Properties propiedades = this.loadProperties();
		
		String actionsValueProperty = "";
		for(int i=0; i<=ACTION_SET_MAX; i++){
			if(actionSet==i){
				actionsValueProperty = "sedcat.actions.set" + i;
			}
		}

		String actionValue = propiedades.getProperty(actionsValueProperty);
		LOG.info("ACCIONES: "+ actionValue);
	
		
		//2 - Almacenar el mensaje del conjunto de acciones en forma de String
		Measure measure = new Measure(SedcatMetrics.ACTIONS_TO_PERFORM, actionValue);
		sensorContext.saveMeasure(measure);
		
		LOG.info("Conjunto de acciones almacenado correctamente");
	}

	
	
	private Properties loadProperties() {

	    Properties propiedades = new Properties();
	    FileInputStream entrada = null;

	    try {

	        entrada = new FileInputStream(ACTIONS_PROPERTIES_PATH);

	        // cargamos el archivo de propiedades
	        propiedades.load(entrada);


	    } catch (IOException ex) {
	        ex.printStackTrace();
	    } finally {
	        if (entrada != null) {
	            try {
	                entrada.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            
	            
	        }
	    }
	    
	    return propiedades;
	}



	/**
	 * Metodo encargado de verificar que el conjunto de acciones obtenido es coherente
	 * y se encuentra dentro del rango de acciones predefinido
	 * @param actionSet
	 */
	private void checkValue(long actionSet) {
		
		if(actionSet < ACTION_SET_MIN || actionSet> ACTION_SET_MAX){
			//El conjunto de acciones no entra en el rango permitido
			LOG.error("El conjunto de acciones resultado no esta dentro del rango predefinido");
			System.exit(-1);
		}
		
	}

}
