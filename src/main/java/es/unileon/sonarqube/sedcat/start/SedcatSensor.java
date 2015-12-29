package es.unileon.sonarqube.sedcat.start;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.Plugin;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;

import es.unileon.sonarqube.sedcat.analyzers.InputVariablesGeneral;
import es.unileon.sonarqube.sedcat.results.ResultsManager;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;
import es.unileon.sonarqube.sedcat.start.SedcatPlugin;
import es.unileon.sonarqube.sedcat.strategies.StrategiesManager;

import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;



/**
 *
 * Sensor Sedcat que actúa como clase principal o flujo main
 * @author alan.jesus
 * @version 1.0
 */
public class SedcatSensor implements Sensor {




	
	private Settings settings;

    /**
     * The file system object for the project being analysed.
     */
    private final FileSystem fileSystem;

    /**
     * The logger object for the sensor.
     */
//    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
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

    /**
     *
     * metodo principal encargado de gestionar todas las clases
     * @param project       the project being analysed
     * @param sensorContext the sensor context
     */
    public void analyse(Project project, SensorContext sensorContext) {
    	
	/*
	 * 	
	 *     1-Obtener variables de entrada
	 *     		InputVariablesGeneral
	 *     			InputVariableExito
	 *     2 - Obtener variables de salida a partir de las variables de entrada:
	 *     		Uso de estrategias que se corresponden con diferentes sistemas expertos
	 *     3 - Gestionar los resultados 
	 *     		Los resultados obtenidos son almacenados en forma de medidas y representados en el widget dinámicamente.
	 *     
	 *     
	 *     	
	 */
    	
//    	1- Obtener variables de entrada
    	InputVariablesGeneral inputVariables = new InputVariablesGeneral(sensorContext, fileSystem, settings);
//    	2 - gestionar sistema experto difuso para obtener los resultados
    	StrategiesManager outputVariables = new StrategiesManager(inputVariables);
//    	3 - Gestionar resultados
    	ResultsManager manageVariables = new ResultsManager(sensorContext, outputVariables);
    	

    	java.io.File projectDirectory = fileSystem.baseDir();
    	
        //Cogemos las rutas que esten en configuracion
    	String rutaExito = settings.getString(SedcatConstants.SUCCESS_KEY);
    	String rutaCobertura = settings.getString(SedcatConstants.COVERAGE_KEY);
    	
    	saveMessages1(sensorContext, rutaExito);
    	saveMessages2(sensorContext, rutaCobertura);
    	
    	//prueba para directorio base
    	FileInputStream is;
    	Properties prop = new Properties();
    	OutputStream salida = null;

    	
        try {
            is=new FileInputStream("/root/workspace/tools.sonarqube.sedcat/src/main/resources/org/sonar/l10n/sedcat.properties");
            prop.load(is);
          } catch(IOException ioe) {ioe.printStackTrace();}
        
        // Listamos las propiedades
        System.out.println("PROPIEDADES POR DEFECTO");
        for (Enumeration e = prop.keys(); e.hasMoreElements(); ) {		 
          Object obj = e.nextElement();
           System.out.println(obj + ": "
            + prop.getProperty(obj.toString()));
        }

        // Modificamos los valores
        prop.setProperty("sedcat.basedir", projectDirectory.getAbsolutePath());
      try {
			salida = new FileOutputStream("/root/workspace/tools.sonarqube.sedcat/src/main/resources/org/sonar/l10n/sedcat.properties");
			prop.store(salida, null);
			
			
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (salida != null) {
	        	
	        
	            try {
	                salida.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}


       
    	double[] variables =  new double[2];
    	variables[0] = this.getInputVariables(rutaExito);
    	variables[1] = this.getInputVariables(rutaCobertura);
    	
    	//simulamos alguna operacion con las variables - esta parte es la que corresponde al sistema experto
    	variables[0] = variables[0] + 100;
    	variables[1] = variables[1] + 100;
    	
    	//guardamos las variables en forma de metricas a 
        saveMainInfo(sensorContext, variables);
        
        
        

    }

    private double getInputVariables(String rutaExito) {
		// TODO Auto-generated method stub
    	File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        double variable = 0;
   
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           archivo = new File ("/root/workspace/tools.sonarqube.sedcat/texto.txt");
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);
   
           // Lectura del fichero
           String linea;
           int i = 0;
           while((linea=br.readLine())!=null){
        	   variable = Double.parseDouble(linea);
           		i++;
           }

        } catch(Exception e){
            e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
		return variable;
	}

	/**
     * Saves measures corresponding to main project information.
     *
     * @param sensorContext the sensor context
     * @param projectInfo   the project information bean
     */
    private void saveMainInfo(SensorContext sensorContext, double[] variables) {

//        log.debug("saving measures for main project information");

        Measure measure;

        measure = new Measure(SedcatMetrics.EXITO, variables[0]);
        sensorContext.saveMeasure(measure);

        measure = new Measure(SedcatMetrics.COBERTURA, variables[1]);
        sensorContext.saveMeasure(measure);
//        
//        measure = new Measure(SedcatMetrics.COBERTURA, variables[1]);
//        sensorContext.saveMeasure(measure);
//        measure = new Measure(SedcatMetrics.COBERTURA, variables[1]);
//        sensorContext.saveMeasure(measure);

    }

    

    /**
     * Saves measures corresponding to main project information.
     *
     * @param sensorContext the sensor context
     * @param projectInfo   the project information bean
     */
    private void saveMessages1(SensorContext sensorContext, String message) {

//        log.debug("saving measures for main project information");

        Measure measure;

        measure = new Measure(SedcatMetrics.MESSAGE, message);
        sensorContext.saveMeasure(measure);

        

    }
    /**
     * Saves measures corresponding to main project information.
     *
     * @param sensorContext the sensor context
     * @param projectInfo   the project information bean
     */
    private void saveMessages2(SensorContext sensorContext, String message) {

//        log.debug("saving measures for main project information");

        Measure measure;

        measure = new Measure(SedcatMetrics.MESSAGE_2, message);
        sensorContext.saveMeasure(measure);

        

    }
    
//    /**
//     * Saves measures corresponding to project dependencies information.
//     *
//     * @param sensorContext the sensor context
//     * @param projectInfo   the project information bean
//     */
//    private void saveDependencies(SensorContext sensorContext, ProjectInfo projectInfo) {
//
////        log.debug("saving measure for project dependencies");
//
//        sensorContext.saveMeasure(new Measure(IDEMetadataMetrics.IDE_DEPENDENCIES, projectInfo.getProjectDependencies().toString()));
//
////        log.debug("measure saved");
//    }
//
//    /**
//     * Saves measures corresponding to project classpath information.
//     *
//     * @param sensorContext the sensor context
//     * @param projectInfo   the project information bean
//     */
//    private void saveClasspath(SensorContext sensorContext, ProjectInfo projectInfo) {
//
//        log.debug("saving measure for project classpath");
//
//        sensorContext.saveMeasure(new Measure(
//            IDEMetadataMetrics.IDE_CLASSPATH, projectInfo.getProjectClasspath().toString()));
//
//        log.debug("measure saved");
//    }

    /**
     * Returns the name of the sensor as it will be used in logs during analysis.
     *
     * @return the name of the sensor
     */
    public String toString() {
        return "IDEMetadataSensor";
    }
}

