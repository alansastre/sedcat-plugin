package org.sonar.plugins.sedcat.storage;

import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.plugins.sedcat.start.SedcatMetricsKeys;

/**
 * Clase encargada de hacer la correspondencia del resultado acciones con los
 * conjuntos acciones predefinidos, una vez encontrado el conjunto lo almacena
 * en forma de metrica.
 * 
 * @see: org.sonar.api.ce.measure.test.TestMeasure
 * 
 * @author alan.sastre
 * @version 1.0.0
 */
public class ActionsMeasureStore extends AbstractOutputMeasureStore {

	// variable que almacena la ruta donde se encuentran las propiedades que
	// definen los conjuntos de acciones
	private static final String ACTIONS_PROPERTIES_PATH = "/root/workspace/sonar-sedcat-plugin/src/main/resources/org/sonar/l10n/expertSystemActions.properties";
	
	
	
	public ActionsMeasureStore() {

		this.LOG = LoggerFactory.getLogger(ActionsMeasureStore.class);
		this.MIN_VALUE = 0;
		//number of action sets
		this.MAX_VALUE = 65;
		//metrica acciones con el mensaje que aparecerá en el widget
		this.MEASURE_KEY = SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY;
		//metrica acciones con el mensaje que aparecera en la pantalla de detalle
		this.MESSAGE_KEY = SedcatMetricsKeys.ACTIONS_MESSAGE_KEY;
		this.ERROR_MESSAGE = "Error, the set of actions obtained this outside the permitted range";

	}

	@Override
	protected void saveMeasure(double[] outputMeasureValues, MeasureComputerContext context) {

		//comprobamos array de resultados
		if (outputMeasureValues.length == 0) {
			
			context.addMeasure(this.MEASURE_KEY, MESSAGE_NO_ACTIONS);
			context.addMeasure(this.MESSAGE_KEY, MESSAGE_NO_ACTIONS);
			
		}else{
			
			// Extrae el resultado, en este caso esta en la primera posicion
			double measureValue = outputMeasureValues[0];
	
			// Redondea para obtener un conjunto discreto
			long actionSet = Math.round(measureValue);
			LOG.info("Set actions number: " +actionSet);
			
			//Carga fichero de propiedades con las soluciones
			Properties propiedades = this.loadProperties(ACTIONS_PROPERTIES_PATH);
	
			//Encuentra la propiedad que coincide con el set resultado
			String actionsValueProperty = "";
			for (int i = 0; i <= this.MAX_VALUE; i++) {
				if (actionSet == i) {
					actionsValueProperty = "sedcat.actions.set" + i;
				}
			}
			LOG.info("Set actions result: " +actionsValueProperty);
			
			//Obtiene el valor de la propiedad encontrada
			String actionValue = propiedades.getProperty(actionsValueProperty);
			LOG.info("Actions result value: " + actionValue);
	
			//Verifica que se ha encontrado un resultado
			if (actionValue == null) {
				actionValue = MESSAGE_NO_ACTIONS;
			}
			
			// Almacena el mensaje del conjunto de acciones en forma de String
			context.addMeasure(this.MEASURE_KEY, actionValue);
			
			/*
			 * Obtener mensaje descriptivo para las acciones y almacenarlo en otra medida
			 * para su representacion en la pantalla detalle (drill-down) de la métrica acciones
			 */
			try {
	
				context.addMeasure(this.MESSAGE_KEY,
						(String) ActionsMessageConstants.class.getField("MESSAGE_SET" + actionSet).get(this)
								+ MESSAGE_ALERT_HACK);
	
			} catch (Exception e) {
				LOG.warn(e.getMessage());
				context.addMeasure(this.MESSAGE_KEY, MESSAGE_NO_ACTIONS);
			}
		}
		
		LOG.info("Set of actions successfully stored");
	}

	/**
	 * carga las propiedades a partir de la ruta especificada
	 * 
	 * @return fichero de propiedades
	 */
	protected Properties loadProperties(String propertiesPath) {

		Properties propiedades = new Properties();
		FileInputStream entrada = null;
		
		try {
			entrada = new FileInputStream(propertiesPath);
			
			// cargamos el archivo de propiedades
			propiedades.load(entrada);
		} catch (Exception ex) {
			LOG.warn("failure to obtain the properties of the sets of actions");
			LOG.warn(ex.getMessage());
		} finally {
			try {
				if (entrada != null) 
					entrada.close();
			} catch (Exception ex) {
				LOG.error("failure to close the properties route action sets");
				LOG.warn(ex.getMessage());
			}
		}
		return propiedades;
	}
}
