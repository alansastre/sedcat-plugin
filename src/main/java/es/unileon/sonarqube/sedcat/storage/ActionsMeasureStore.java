package es.unileon.sonarqube.sedcat.storage;

import java.io.FileInputStream;
import java.util.Properties;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;

/**
 * Clase encargada de hacer la correspondencia del resultado acciones con los
 * conjuntos acciones predefinidos, una vez encontrado el conjunto lo almacena
 * en forma de metrica.
 * 
 * @see: org.sonar.api.ce.measure.test.TestMeasure
 * 
 * @author alan.sastre
 * @version 1.0
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
		this.MEASURE_KEY = SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY;
		this.MESSAGE_KEY = SedcatMetricsKeys.ACTION_MESSAGE_KEY;
		this.ERROR_MESSAGE = "Error, el conjunto de acciones obtenido esta fuera del rango permitido";

	}

	@Override
	protected void saveMeasure(double[] outputMeasureValues, MeasureComputerContext context) {

		// Extraemos el resultado, en este caso esta en la primera posicion
		double measureValue = outputMeasureValues[0];

		// Redondeamos para obtener un conjunto discreto
		long actionSet = Math.round(measureValue);
		LOG.info("Set acciones numero: " +actionSet);
		// cargamos propiedades
		Properties propiedades = this.loadProperties(ACTIONS_PROPERTIES_PATH);

		String actionsValueProperty = "";
		for (int i = 0; i <= this.MAX_VALUE; i++) {
			if (actionSet == i) {
				actionsValueProperty = "sedcat.actions.set" + i;
			}
		}

		LOG.info("Set acciones resultado: " +actionsValueProperty);
		String actionValue = propiedades.getProperty(actionsValueProperty);
		LOG.info("Resultado acciones: " + actionValue);

		if (actionValue == null) {
			actionValue = "No se han encontrado posibles soluciones.";
		}
		// Almacenar el mensaje del conjunto de acciones en forma de String
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
		}
		
		
		LOG.info("Conjunto de acciones almacenado correctamente");
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
			LOG.warn("fallo al obtener las propiedades de los conjuntos de acciones");
			LOG.warn(ex.getMessage());

		} finally {
			try {
				if (entrada != null) {
					entrada.close();
				}

			} catch (Exception ex) {
				LOG.error("fallo al cerrar la ruta propiedades de los conjuntos de acciones");
				LOG.warn(ex.getMessage());

			}

		}

		return propiedades;
	}

}
