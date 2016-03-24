package es.unileon.sonarqube.sedcat.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.measure.Metric;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.resources.Project;
//import org.sonar.squid.measures.Measures;
//import org.sonar.plugins.pitest.PitestMetrics;

import es.unileon.sonarqube.sedcat.analyzers.InputVariablesGeneral;
import es.unileon.sonarqube.sedcat.strategies.IExpertSystemStrategy;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Formula;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.MeasureUtils;

import org.sonar.api.batch.*;
import org.sonar.xoo.coverage.*;
import org.sonar.api.batch.postjob.*;

/**
 *
 * Clase sensor que contiene el metodo analyse principal desde el que se gestiona
 * la aplicacion
 * @author alan.sastre
 * @version 1.0
 */
public class SedcatSensor implements Sensor {

	private Settings settings;
//	private MeasureComputer computer;
//	private UtCoverageSensor coverage;

	//estrategia para los sistemas expertos
//	private IExpertSystemStrategy expertSystem;

    /**
     * The file system object for the project being analysed.
     */
    private final FileSystem fileSystem;

    /**
     * The logger object.
     */
	private static final Logger LOG = LoggerFactory.getLogger(SedcatSensor.class);

    /**
     * Constructor that sets the file system object for the
     * project being analysed.
     *
     * @param fileSystem the project file system
     */
    public SedcatSensor(FileSystem fileSystem, Settings settings) {
        this.fileSystem = fileSystem;
        this.settings = settings;
    }

    /**
     * Determines whether the sensor should run or not for the given project.
     *
     * @param project the project being analysed
     * @return always true
     */
    public boolean shouldExecuteOnProject(Project project) {
        // this sensor is executed on any type of project
        return true;
    }
    
 
//  public void setExpertSystem(IExpertSystemStrategy ExpertSystem){
//		this.expertSystem = ExpertSystem;
//	}
//  
//  public void performExpertSystem(double[] inputVariables, SensorContext sensorContext){
//	  
//	  this.expertSystem.xfuzzyProcess(inputVariables, sensorContext);
//	}

    /**
     *
     * Punto de entrada de la aplicacion, desde aqui se llaman a todas las clases 
     * @param project       the project being analysed
     * @param sensorContext the sensor context
     */
    public void analyse(Project project, SensorContext sensorContext) {


//    	1- Obtener variables de entrada
//    	InputVariablesGeneral inputVariables = new InputVariablesGeneral(sensorContext, fileSystem, settings);
//    	this.inputVariablesValues = inputVariables.getInputVariables();
//    	
//    	LOG.info("Sensor: variables de entrada extraidas");
//    	2 - gestionar sistemas expertos para obtener los resultados
    	
    	//Estrategia para el sistema experto que procesa la calidad
//    	this.setExpertSystem(new ExpertSystemQuality());
//    	this.performExpertSystem(this.inputVariablesValues, sensorContext);
//    	
//    		//reordenar variables de entrada para el sistema experto acciones
//    	this.prepareInputVariablesActions();
//    	
//    	//Estrategia para el sistema experto que procesa las acciones
//    	this.setExpertSystem(new ExpertSystemActions());
//    	this.performExpertSystem(this.inputVariablesValues, sensorContext);
    	LOG.info("Sensor: inicio");
    	
//    	this.coverage.execute(sensorContext);
//    	 final Measure<Integer> smellCountMeasure = new Measure<Integer>(CoreMetrics.TESTS_KEY);
//    	 LOG.info("Sensor: " +smellCountMeasure.getIntValue());
//    	LOG.info("Sensor: inicio");
    	 
//    	double num_test = this.context.getMeasure(CoreMetrics.TESTS_KEY).getIntValue();
//    	sensorContext.saveMeasure(SedcatMetrics.GENERAL_RESULT_COMPUTERS, num_test);
        

    }


	/**
     * Returns the name of the sensor as it will be used in logs during analysis.
     *
     * @return the name of the sensor
     */
    public String toString() {
        return "SedcatPluginSensor";
    }
}

