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
 * 	Clase de utilidad para la obtencion de variables de entrada
 *	@author alan.sastre
 *	@version 1.0
 */
@Deprecated
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

		LOG.info("InputVariablesUtils: se ha empleado la siguiente ruta para buscar una variable de entrada " + rutaProyecto);
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
            System.exit(-1);
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
        	  LOG.error("InputVariablesUtils: No se ha podido cerrar la ruta correctamente");
              e2.printStackTrace();
              System.exit(-1);
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
		
		LOG.info("InputVariablesUtils: se ha empleado la siguiente ruta para buscar una variable de entrada " + rutaProyecto);
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
	public static void checkInputValues(double[] inputVariables, int numberVariables) {

		
		//1a comprobacion: el array tiene el numero de variables correcto
		if(inputVariables.length!=numberVariables){
			LOG.error("El numero de variables de entrada no se corresponde con el preestablecido por el sistema experto.");
			System.exit(-1);
		}
		
		//2a comprobacion: rango de los valores, comprobar que ninguno sea menor que cero
	
		for(int i = 0; i<inputVariables.length; i++){
			if(inputVariables[i] < 0){
				LOG.error("El formato de variables de entrada no se corresponde con el preestablecido por el sistema experto.");
				System.exit(-1);
			}
		}

		
	}

	/**
	 * Metodo encargado de validar la ruta que indica donde se encuentra el fichero con los valores 
	 * para una determinada variable de entrada
	 * @param rutaVariable
	 */
	public static void checkVariablePath(String variablePath) {

		LOG.info("InputVariablesUtils: se procede a comprobar la siguiente ruta para variable de entrada " + variablePath);
		//1a comprobacion: que la ruta no es nula o vacia
		if(variablePath==null || variablePath.trim().isEmpty()){
			LOG.error("La ruta proporcionada es nula o esta vacia");
			System.exit(-1);
		}
		
		//2a comprobacion: el archivo al que indica es procesable (existe y se puede leer)
		File file=new File(variablePath);
		if(!file.exists() || !file.canRead()){
			LOG.error("El archivo al que apunta la ruta de la variable de entrada no existe o no se puede leer.");
			System.exit(-1);
		}
		
	}

	/**
	 * Metodo encargado de comprobar que el valor de una determinada variable de entrada es consistente 
	 * @param metricValue
	 */
	public static void checkInputVariableValue(double metricValue) {

		
		//comprobamos que el valor no es nulo

		//1a comprobacion: que no sea un valor menor que cero
		if(metricValue < 0){
			LOG.error("El formato de variables de entrada no se corresponde con el preestablecido por el sistema experto.");
			System.exit(-1);
		}
		
		
	}
	

}
