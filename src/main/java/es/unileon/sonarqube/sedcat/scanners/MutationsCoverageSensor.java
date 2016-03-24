/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.resources.Project;
import es.unileon.sonarqube.sedcat.start.SedcatConstants;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;
import org.sonar.api.config.Settings;
import java.io.IOException;



/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class MutationsCoverageSensor implements Sensor {


	private static final Logger LOG = LoggerFactory.getLogger(MutationsCoverageSensor.class);
	
	private Settings settings;
    private final FileSystem fileSystem;
    private final MutationsReportFinder mutationsFinder;
    private final MutationsReportParser mutationsParser;
    


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
  
    }

    /**
     * Determines whether the sensor should run or not for the given project.
     *
     * @param project the project being analysed
     * @return always true
     */
    public boolean shouldExecuteOnProject(Project project) {
        return true;
    }

    /**
     *
     * Analizador mutantes
     * @param project       the project being analysed
     * @param sensorContext the sensor context
     */
    public void analyse(Project project, SensorContext sensorContext) {
    	
		LOG.info("Calculando metrica mutantes");
    	//1 - Encontrar el reporte de mutantes
	    java.io.File projectDirectory = fileSystem.baseDir();
	    java.io.File reportDirectory = new java.io.File(projectDirectory, settings.getString(SedcatConstants.REPORT_DIRECTORY_DEF));
	    java.io.File htmlReport = this.mutationsFinder.findReport(reportDirectory);
      //2 - Obtener la cobertura de mutantes
	    if (htmlReport == null) {
	      LOG.warn("No HTML PIT report found in directory {} !", reportDirectory);
	      LOG.warn("Mutations is considered to be zero.");

	      
	    } else {
	    	
	    	double[] mutationsCoverage = new double[2];
			LOG.info("HTML REPORTE ENCONTRADO: "+htmlReport);
			mutationsCoverage = this.mutationsParser.parseReport(htmlReport);
			LOG.info("VALOR MUTANTES EXTRAIDO: "+mutationsCoverage[0]+" / "+mutationsCoverage[1]);
			//acumulamos valores
			SedcatConstants.mutationsDetected+=mutationsCoverage[0];
			SedcatConstants.mutationsTotal+=mutationsCoverage[1];
			
			LOG.info("mutationsDetected: "+SedcatConstants.mutationsDetected);
			LOG.info("mutationsTotal: "+SedcatConstants.mutationsTotal);

	    }
	  //3 - Almacenar la cobertura de mutantes
	    if (SedcatConstants.mutationsDetected>0 && SedcatConstants.mutationsTotal>0) {
	    	 sensorContext.saveMeasure(SedcatMetrics.MUTANTS, (double) (100*SedcatConstants.mutationsDetected/SedcatConstants.mutationsTotal));
		}else{
			 sensorContext.saveMeasure(SedcatMetrics.MUTANTS, 0.0);
		}
	    
	   

    }

	/**
     * Returns the name of the sensor as it will be used in logs during analysis.
     *
     * @return the name of the sensor
     */
    public String toString() {
        return "MutationsSensor";
    }
}


