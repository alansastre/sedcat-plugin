/**
 * 
 */
package es.unileon.sonarqube.sedcat.analyzers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.Metric;

import es.unileon.sonarqube.sedcat.start.SedcatConstants;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;

/**
 * 	Clase de utilidad para los analizadores de variables de entrada
 *	@author alan.sastre
 *	@version 1.0
 */
public final class InputVariablesUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(InputVariablesUtils.class);

	/**
	 * 
	 *  Metodo encargado de obtener la ruta de una variable
	 *  
	 * @param fileSystem
	 * @param settings
	 * @param claveVariable
	 * @return
	 */
	public static String obtenerRutaVariable(FileSystem fileSystem, Settings settings, String claveVariable){

		java.io.File projectDirectory = fileSystem.baseDir();
    	String rutaProyecto = projectDirectory.getAbsolutePath();
		String rutaVariable = settings.getString(claveVariable);
		rutaProyecto = rutaProyecto + rutaVariable;

		return rutaProyecto;
	}
	
	/**
	 * Metodo encargado de obtener el valor de una variable a partir de una ruta dada 
	 * @param rutaVariable
	 * @return
	 */
	public static double obtenerValorDesdeRuta(String rutaVariable){
		
    	File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        double variable = 0;
   
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           archivo = new File (rutaVariable);
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);
   
           // Lectura del fichero
           String linea;

           while((linea=br.readLine())!=null){
        	   variable = Double.parseDouble(linea);

           }

        } catch(Exception e){
        	LOG.error("InputVariablesUtils: fallo al obtener valor desde ruta");
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
        
        LOG.info("InputVariablesUtils: valor desde ruta obtenido");
        return variable;
		
	}
	
	
	/**
	 * Metodo encargado de almacenar una variable en forma de medida para su posterior representacion en el widget 
	 * 
	 * @param sensorContext
	 * @param metrica
	 * @param metricaValor en double
	 */
	public static void guardarVariableMedida(SensorContext sensorContext, Metric metrica, double metricaValor){
		
		Measure measure;

        measure = new Measure(metrica, metricaValor);
        sensorContext.saveMeasure(measure);
        LOG.info("InputVariablesUtils: variable de entrada almacenada como medida");
	}




	public static String obtenerRutaVariablePorDefecto(FileSystem fileSystem, Settings settings, String defaultPathVariable) {
		
		java.io.File projectDirectory = fileSystem.baseDir();
    	String rutaProyecto = projectDirectory.getAbsolutePath();
		rutaProyecto = rutaProyecto + defaultPathVariable;

		return rutaProyecto;
	}
	
	
	
	/**
	 * 	Metodo que transforma un arraylist de double en un array 
	 * @param metricsValues
	 * @return
	 */
	public static double[] arrayListToArray(ArrayList<Double> metricsValues) {
		
		
		double[] variablesEntrada = new double[metricsValues.size()];
		
		int i = 0;
		for (Double double1 : metricsValues) {
			variablesEntrada[i] = double1;
			i++;
			
		}
		
		return variablesEntrada;
	}
	
	/**
	 *  Metodo encargado de validar las variables de entrada comprobando que cumplen
	 *  los requisitos de formato, caracteres especiales etc
	 * @param variablesEntrada
	 */
	public static void checkInputValues(double[] variablesEntrada) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Metodo encargado de validar la ruta de que indica donde se encuentra el fichero con los valores 
	 * para una determinada variable de entrada
	 * @param rutaVariable
	 */
	public static void checkVariablePath(String rutaVariable) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Metodo encargado de comprobar que el valor de una determinada variable de entrada es consistente 
	 * @param metricValue
	 */
	public static void checkInputVariableValue(double metricValue) {
		// TODO Auto-generated method stub
		
	}
	

}
