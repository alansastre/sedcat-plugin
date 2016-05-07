package es.unileon.sonarqube.sedcat.scanners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;
import es.unileon.sonarqube.sedcat.start.SedcatConstants;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;
import org.sonar.api.config.Settings;

/**
 * Obtiene el valor umbral de la complejidad de la pantalla de configuración de
 * sedcat. Este valor será indicado por el usuario, siendo 30 si no se
 * especifica.
 * 
 * @author alan.sastre
 * @version 1.0
 */
public class ComplexityThresoldSensor implements Sensor {

	private static final Logger LOG = LoggerFactory.getLogger(ComplexityThresoldSensor.class);
	private final Settings settings;

	public ComplexityThresoldSensor(Settings settings) {
		this.settings = settings;
	}

	@Override
	public boolean shouldExecuteOnProject(Project project) {

		return true;
	}

	@Override
	public void analyse(Project module, SensorContext context) {

		// Comprobar que el valor umbral introducido esté en los límites
		// permitidos [0, 60]
		// de no ser así se considerará el valor por defecto
		
		LOG.info("Entrada ComplexityThresoldSensor");
		double thresold = Double.parseDouble(settings.getString(SedcatConstants.COMPLEXITY_THRESOLD_KEY));
		LOG.info("Umbral extraido: "+thresold);
		context.saveMeasure(SedcatMetrics.COMPLEXITY_THRESOLD, thresold);

	}
	
	/**
     * Returns the name of the sensor as it will be used in logs during analysis.
     *
     * @return the name of the sensor
     */
    @Override
    public String toString() {
        return "ComplexityThresoldSensor";
    }

}
