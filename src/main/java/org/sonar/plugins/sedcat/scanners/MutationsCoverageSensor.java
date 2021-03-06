package org.sonar.plugins.sedcat.scanners;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;
import org.sonar.plugins.sedcat.start.SedcatConstants;
import org.sonar.plugins.sedcat.start.SedcatMetrics;

/**
 *	@author alan.sastre
 *	@version 1.0.0
 *
 *API:
 *
 * from 5.1
 * http://javadocs.sonarsource.org/5.1/apidocs/org/sonar/api/batch/sensor/Sensor.html
 * http://javadocs.sonarsource.org/5.1/apidocs/org/sonar/api/batch/sensor/SensorContext.html
 * 
 * from 1.10
 * http://javadocs.sonarsource.org/5.1/apidocs/org/sonar/api/batch/Sensor.html
 * http://javadocs.sonarsource.org/5.1/apidocs/org/sonar/api/batch/SensorContext.html
 * 
 * Tests with:
 * http://javadocs.sonarsource.org/5.1/apidocs/org/sonar/api/batch/sensor/internal/SensorContextTester.html
 */
public class MutationsCoverageSensor implements Sensor {


	private static final Logger LOG = LoggerFactory.getLogger(MutationsCoverageSensor.class);
	private Settings settings;
    private final FileSystem fileSystem;
    private final MutationsReportFinder mutationsFinder;
    private final MutationsReportParser mutationsParser;
    private double mutationsValueFound;
    private final String executionMode;
 

	/**
     * Constructor that sets the file system object for the
     * project being analysed.
     *
     * @param fileSystem the project file system
     * settings fileSystem the project file system
     */
    public MutationsCoverageSensor(FileSystem fileSystem, Settings settings, MutationsReportFinder mutationsFinder, MutationsReportParser mutationsParser) {
        this.fileSystem = fileSystem;
        this.settings = settings;
        this.mutationsFinder = mutationsFinder;
        this.mutationsParser = mutationsParser;
        this.mutationsValueFound = 0.0;
        this.executionMode = settings.getString(SedcatConstants.ACTIVE_MODE_KEY);
    }
    
    public double getMutationsValueFound() {
		return mutationsValueFound;
	}

    /**
     * Determines whether the sensor should run or not for the given project.
     *
     * @param project the project being analysed
     * @return always true
     */
    @Override
    public boolean shouldExecuteOnProject(Project project) {
        return ("true").equals(this.executionMode);
    }

    /**
     *
     * Analizador mutantes
     * @param project       the project being analysed
     * @param sensorContext the sensor context
     */
    @Override
    public void analyse(Project project, SensorContext sensorContext) {
    	
		// 1 - Encontrar el reporte de mutantes

		File projectDirectory = fileSystem.baseDir();
		File reportDirectory = new File(projectDirectory,
				settings.getString(SedcatConstants.PITEST_REPORT_DIRECTORY_KEY));
		LOG.info("Absolute path to report directory: " + reportDirectory);

		File htmlReport = this.mutationsFinder.findReport(reportDirectory);

		// 2 - Obtener la cobertura de mutantes (si existe el reporte)

		if (htmlReport == null) {

			LOG.warn("No HTML PIT report found in directory {} !", reportDirectory);
			LOG.warn("Mutation Metric is considered zero.");

		} else {

			LOG.info("HTML report found: " + htmlReport);

			double[] mutationsCoverage = this.mutationsParser.parseReport(htmlReport);

			if (mutationsCoverage != null) {

				LOG.info("Mutations value extracted: " + mutationsCoverage[0] + " / " + mutationsCoverage[1]);
				
				// acumulamos valores
				MutationsCoverageSensor.addValueDetectedMutations(mutationsCoverage[0]);
				MutationsCoverageSensor.addValueTotalMutations(mutationsCoverage[1]);

				LOG.info("mutationsDetected: " + SedcatConstants.mutationsDetected);
				LOG.info("mutationsTotal: " + SedcatConstants.mutationsTotal);
			}
		}

		LOG.info("sumators:");
		LOG.info("mutationsDetected: " + SedcatConstants.mutationsDetected);
		LOG.info("mutationsTotal: " + SedcatConstants.mutationsTotal);

		// 3 - Calcular y almacenar la cobertura de mutantes

		if (SedcatConstants.mutationsDetected > 0 && SedcatConstants.mutationsTotal > 0) {
			mutationsValueFound = (double) (100 * SedcatConstants.mutationsDetected / SedcatConstants.mutationsTotal);
			LOG.info("mutants coverage value:" + mutationsValueFound);
		}
		sensorContext.saveMeasure(SedcatMetrics.MUTANTS, mutationsValueFound);

	}

	/**
     * Returns the name of the sensor as it will be used in logs during analysis.
     *
     * @return the name of the sensor
     */
    @Override
    public String toString() {
        return "MutationsCoverageSensor";
    }

    /**
     * suma el numero de mutantes detectados en cada modulo
     * @param mutationsDetectedValue
     */
    public static synchronized void addValueDetectedMutations(double mutationsDetectedValue){
		SedcatConstants.mutationsDetected+=mutationsDetectedValue;
    }
    
	/**
	 * suma el numero de mutantes totales en cada modulo
	 * @param mutationsTotalValue
	 */
    public static synchronized void addValueTotalMutations(double mutationsTotalValue){
		SedcatConstants.mutationsTotal+=mutationsTotalValue;
    }
}


