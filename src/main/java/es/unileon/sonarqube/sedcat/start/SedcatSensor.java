//package es.unileon.sonarqube.sedcat.start;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.sonar.api.batch.DecoratorContext;
//import org.sonar.api.batch.Sensor;
//import org.sonar.api.batch.SensorContext;
//import org.sonar.api.batch.fs.FileSystem;
//import org.sonar.api.batch.measure.Metric;
//import org.sonar.api.resources.Project;
////import org.sonar.squid.measures.Measures;
//
//import es.unileon.sonarqube.sedcat.analyzers.InputVariablesGeneral;
//import es.unileon.sonarqube.sedcat.strategies.IExpertSystemStrategy;
//import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;
//import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality;
//import org.sonar.api.config.Settings;
//import org.sonar.api.measures.CoreMetrics;
//import org.sonar.api.measures.Formula;
//import org.sonar.api.measures.Measure;
//import org.sonar.api.measures.MeasureUtils;
//
//
///**
// *
// * Clase sensor que contiene el metodo analyse principal desde el que se gestiona
// * la aplicacion
// * @author alan.jesus
// * @version 1.0
// */
//public class SedcatSensor implements Sensor {
//
//	private double[] inputVariablesValues;
//	private Settings settings;
//
//	
//	protected static double valorENTRADA;
//	//estrategia para los sistemas expertos
//	private IExpertSystemStrategy expertSystem;
//
//    /**
//     * The file system object for the project being analysed.
//     */
//    private final FileSystem fileSystem;
//
//    /**
//     * The logger object for the sensor.
//     */
//	private static final Logger LOG = LoggerFactory.getLogger(SedcatSensor.class);
//
//    /**
//     * Constructor that sets the file system object for the
//     * project being analysed.
//     *
//     * @param fileSystem the project file system
//     */
//    public SedcatSensor(FileSystem fileSystem, Settings settings) {
//        this.fileSystem = fileSystem;
//        this.settings = settings;
//    }
//
//    /**
//     * Determines whether the sensor should run or not for the given project.
//     *
//     * @param project the project being analysed
//     * @return always true
//     */
//    public boolean shouldExecuteOnProject(Project project) {
//        // this sensor is executed on any type of project
//        return true;
//    }
//    
// 
//  public void setExpertSystem(IExpertSystemStrategy ExpertSystem){
//		this.expertSystem = ExpertSystem;
//	}
//  
//  public void performExpertSystem(double[] inputVariables, SensorContext sensorContext){
//	  
//	  this.expertSystem.xfuzzyProcess(inputVariables, sensorContext);
//	}
//
//    /**
//     *
//     * Punto de entrada de la aplicacion, desde aqui se llaman a todas las clases 
//     * @param project       the project being analysed
//     * @param sensorContext the sensor context
//     */
//    public void analyse(Project project, SensorContext sensorContext) {
//    	
//	/*
//	 *     1-Obtener variables de entrada: exito, cobertura, mutantes, numero de test, numero de lineas de codigo
//	 *     		gestionadas por InputVariablesGeneral que nos devuelve un array de double con el valor de cada una
//	 *     2 - Ejecutar sistemas expertos y almacenar variables de salida
//	 *     		Se ejecutan las estrategias correspondientes a cada sistema experto pasandoles las variables
//	 *     		de entrada como parametro.
//	 *     		Cada estrategia delega en otra clase el almacenamiento de su resultado.	
//	 */
//
//    	
////   	 Measure prpr=  context.getMeasure(CoreMetrics.NCLOC);
////  	  double pp = MeasureUtils.getValue(prpr, 25.36);
////  	  System.out.println("PRUEBITA: "+pp);
//    	
////    	if (test_value != null) { 
////    		context.saveMeasure(CodeQualityMetrics.TEST, test_value.getValue()); 
////    		} else { 
////    			double value2 = 8000; context.saveMeasure(CodeQualityMetrics.TEST, value2);
////    			} 
////    	System.out.println("PRUEBITA"+valorENTRADA);
//    	System.out.println("EJECUTANDO SENSOR");
////    	1- Obtener variables de entrada
//    	InputVariablesGeneral inputVariables = new InputVariablesGeneral(sensorContext, fileSystem, settings);
//    	this.inputVariablesValues = inputVariables.getInputVariables();
//    	
//    	LOG.info("Sensor: variables de entrada extraidas");
////    	2 - gestionar sistemas expertos para obtener los resultados
//    	
//    	//Estrategia para el sistema experto que procesa la calidad
//    	this.setExpertSystem(new ExpertSystemQuality());
//    	this.performExpertSystem(this.inputVariablesValues, sensorContext);
//    	
//    		//reordenar variables de entrada para el sistema experto acciones
//    	this.prepareInputVariablesActions();
//    	
//    	//Estrategia para el sistema experto que procesa las acciones
//    	this.setExpertSystem(new ExpertSystemActions());
//    	this.performExpertSystem(this.inputVariablesValues, sensorContext);
//  
//        
//
//    }
//
//    /**
//     * Reordenar variables para las acciones
//     */
//    private void prepareInputVariablesActions() {
//
//    	double aux = this.inputVariablesValues[2];
//    	this.inputVariablesValues[2] =  this.inputVariablesValues[3];
//    	this.inputVariablesValues[3] = aux;
//		
//	}
//
//	/**
//     * Returns the name of the sensor as it will be used in logs during analysis.
//     *
//     * @return the name of the sensor
//     */
//    public String toString() {
//        return "SedcatPluginSensor";
//    }
//}
//
