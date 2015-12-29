package es.unileon.sonarqube.sedcat.start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import org.sonar.api.Extension;

import es.unileon.sonarqube.sedcat.analyzers.InputVariablesGeneral;
import es.unileon.sonarqube.sedcat.strategies.SistemaExpertoA;
import es.unileon.sonarqube.sedcat.xfuzzy.MembershipFunction;
import es.unileon.sonarqube.sedcat.xfuzzy.pruebaCALIDAD2;

/**
 * Hello world!
 *
 */
public class App implements Extension{

    public static void main( String[] args ){
    	

    			
//    	pruebaCALIDAD2 prueba = new pruebaCALIDAD2();
//    	
//    	
    	double[] variables = new double[2];
    	variables[0] = (double) 0;
    	variables[1] = (double) 98;
//    	
//    	double[] resultado = prueba.crispInference(variables);
//    	System.out.println(resultado[0]);

    	
//    	ArrayList<Double> metricsValues = new ArrayList<Double>();
//    	metricsValues.add((double) 1);
//		metricsValues.add((double) 98);
//		
// 		double[] variablesEntrada = arrayListToArray(metricsValues);
//
// 		pruebaCALIDAD2 prueba = new pruebaCALIDAD2();
//     	double[] resultado = prueba.crispInference(variablesEntrada);
//     	System.out.println(resultado[0]);
 	
//    	double[] variablesEntrada = arrayListToArray(inputVariables.metricsValues);
		
		SistemaExpertoA procesado = new SistemaExpertoA(variables);
		double[] outputVariables = procesado.xfuzzyProcess();
		System.out.println(outputVariables[0]);
 	
 	

  
//    	MembershipFunction[] nueva = prueba.fuzzyInference(variables);
//    	System.out.println();
//    	File archivo = null;
//        FileReader fr = null;
//        BufferedReader br = null;
//        double variable = 0;
//   
//        try {
//           // Apertura del fichero y creacion de BufferedReader para poder
//           // hacer una lectura comoda (disponer del metodo readLine()).
//           archivo = new File ("/root/workspace/tools.sonarqube.sedcat/texto.txt");
//           fr = new FileReader (archivo);
//           br = new BufferedReader(fr);
//   
//           // Lectura del fichero
//           String linea;
//           int i = 0;
//           while((linea=br.readLine())!=null){
//        	   variable = Double.parseDouble(linea);
//           		i++;
//           }
//
//        } catch(Exception e){
//            e.printStackTrace();
//        }finally{
//           // En el finally cerramos el fichero, para asegurarnos
//           // que se cierra tanto si todo va bien como si salta 
//           // una excepcion.
//           try{                    
//              if( null != fr ){   
//                 fr.close();     
//              }                  
//           }catch (Exception e2){ 
//              e2.printStackTrace();
//           }
//        }
		
//        File archivo = null;
//        FileReader fr = null;
//        BufferedReader br = null;
//        double[] variables = new double[2];
//   
//        try {
//           // Apertura del fichero y creacion de BufferedReader para poder
//           // hacer una lectura comoda (disponer del metodo readLine()).
//           archivo = new File ("/root/workspace/tools.sonarqube.sedcat/texto.txt");
//           fr = new FileReader (archivo);
//           br = new BufferedReader(fr);
//   
//           // Lectura del fichero
//           String linea;
//           int i = 0;
//           while((linea=br.readLine())!=null){
//        	   variables[i] = Double.parseDouble(linea);
//           		i++;
//           }
//
//        } catch(Exception e){
//            e.printStackTrace();
//        }finally{
//           // En el finally cerramos el fichero, para asegurarnos
//           // que se cierra tanto si todo va bien como si salta 
//           // una excepcion.
//           try{                    
//              if( null != fr ){   
//                 fr.close();     
//              }                  
//           }catch (Exception e2){ 
//              e2.printStackTrace();
//           }
//        }
     
//        System.out.println(variable);
//        System.out.println(variables[1]);
        

//    	FileInputStream is;
//    	Properties prop = new Properties();
//    	OutputStream salida = null;
//
//    	
//        try {
//            is=new FileInputStream("/root/workspace/tools.sonarqube.sedcat/src/main/resources/org/sonar/l10n/sedcat.properties");
//            prop.load(is);
//          } catch(IOException ioe) {ioe.printStackTrace();}
//        
//        // Listamos las propiedades
//        System.out.println("PROPIEDADES POR DEFECTO");
//        for (Enumeration e = prop.keys(); e.hasMoreElements(); ) {		 
//          Object obj = e.nextElement();
//           System.out.println(obj + ": "
//            + prop.getProperty(obj.toString()));
//        }
//
//        // Modificamos los valores
//        prop.setProperty("sedcat.exito", "100");
//        prop.setProperty("sedcat.cobertura", "100");
//
//
//
//        // Volvemos a listar los valores
//        System.out.println("PROPIEDADES MODIFICADAS");
//        for (Enumeration e = prop.keys(); e.hasMoreElements(); ) {		 
//           Object obj = e.nextElement();
//           System.out.println(obj + ": "
//             + prop.getProperty(obj.toString()));
//        }
//        
//        try {
//			salida = new FileOutputStream("/root/workspace/tools.sonarqube.sedcat/src/main/resources/org/sonar/l10n/sedcat.properties");
//			prop.store(salida, null);
//			
//			
//			
//			
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//	        if (salida != null) {
//	        	
//	        
//	            try {
//	                salida.close();
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//	        }
//		}


    }
    
 	private static double[] arrayListToArray(ArrayList<Double> metricsValues) {
 		// TODO Auto-generated method stub
 		
 		double[] variablesEntrada = new double[metricsValues.size()];
 		
 		int i = 0;
 		for (Double double1 : metricsValues) {
 			variablesEntrada[i] = double1;
 			i++;
 			
 		}
 		
 		return variablesEntrada;
 	}
}

