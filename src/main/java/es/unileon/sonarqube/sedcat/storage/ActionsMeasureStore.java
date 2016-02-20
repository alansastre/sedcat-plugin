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
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.PropertiesBuilder;

import es.unileon.sonarqube.sedcat.analyzers.InputVariableCoverage;
import es.unileon.sonarqube.sedcat.analyzers.InputVariablesGeneral;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;

/**
 * Clase encargada de hacer la correspondencia del resultado acciones con los conjuntos acciones
 * predefinidos, una vez encontrado el conjunto lo almacena en forma de metrica.
 *	@author alan.jesus
 *	@version 1.0
 */
public class ActionsMeasureStore extends AbstractOutputMeasureStore{

	//variable que almacena la ruta donde se encuentran las propiedades que definen los conjuntos de acciones
	private static final String ACTIONS_PROPERTIES_PATH = "/root/workspace/tools.sonarqube.sedcat/src/main/resources/org/sonar/l10n/expertSystemActions.properties";
	
	public ActionsMeasureStore(double[] outputMeasureValues, MeasureComputerContext context) {
		
		this.outputMeasureValues=outputMeasureValues;
		this.context=context;
		
		this.LOG = LoggerFactory.getLogger(ActionsMeasureStore.class);
		this.MIN_VALUE=0;
		this.MAX_VALUE=32;
		this.MEASURE_KEY=SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY;
		this.ERROR_MESSAGE="Error, el conjunto de acciones obtenido esta fuera del rango permitido";
	
	}

	@Override
	protected void saveMeasure(double measureValue) {
		//Redondeamos, pues necesitamos localizar un conjunto discreto
		long actionSet = Math.round(measureValue);
		
		//cargamos propiedades
		Properties propiedades = this.loadProperties();
		
		String actionsValueProperty = "";
		for(int i=0; i<=this.MAX_VALUE; i++){
			if(actionSet==i){
				actionsValueProperty = "sedcat.actions.set" + i;
			}
		}

		String actionValue = propiedades.getProperty(actionsValueProperty);
		LOG.info("Resultado acciones: "+ actionValue);
	
		
		//2 - Almacenar el mensaje del conjunto de acciones en forma de String
		this.context.addMeasure(this.MEASURE_KEY, actionValue);
	
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
	    	LOG.error("InputVariablesUtils: fallo al obtener las propiedades de los conjuntos de acciones");
	        ex.printStackTrace();
	        System.exit(-1);
	    } finally {
	        if (entrada != null) {
	            try {
	                entrada.close();
	            } catch (IOException e) {
	            	LOG.error("InputVariablesUtils: fallo al cerrar la ruta propiedades de los conjuntos de acciones");
	                e.printStackTrace();
	                System.exit(-1);
	            }
	            
	            
	        }
	    }
	    
	    return propiedades;
	}

}
