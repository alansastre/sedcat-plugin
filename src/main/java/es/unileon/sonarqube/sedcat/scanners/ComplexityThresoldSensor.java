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

		double complexityThresold = 30.0;
		try {
			complexityThresold = Double.parseDouble(settings.getString(SedcatConstants.COMPLEXITY_THRESHOLD_KEY));
		} catch (Exception e) {
			LOG.warn("Value must be a number, in range of 0 to 60.");
			LOG.warn("In this case, it considered default value (30).");
			LOG.warn(e.getMessage());
		}
		LOG.info("Umbral extraido: "+complexityThresold);
		
		// Establecer umbral dentro de los limites
		if (complexityThresold > 60) {
			complexityThresold = 60;
			LOG.warn("Suggested complexity threshold is greater"
					+ " than permitted. The value considered in this case is 60");
		} else if (complexityThresold < 0) {
			LOG.warn("Suggested complexity threshold " + complexityThresold
					+ " is less than 0, it will be considered default value (30)");
			complexityThresold = 30;
		}
		context.saveMeasure(SedcatMetrics.COMPLEXITY_THRESHOLD, complexityThresold);

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
