/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import es.unileon.sonarqube.sedcat.xfuzzy.pruebaCALIDAD2;

/**
 *  Sistema experto que obtiene la metrica de calidad buscada
 *	@author alan.sastre
 *	@version 1.0
 */
public class SistemaExpertoCalidad extends AbstractSistemaExperto{

	
	private double[] variablesEntrada; 
	/**
	 * @param variablesEntrada 
	 * 
	 */
	public SistemaExpertoCalidad(double[] variablesEntrada) {
		// TODO Auto-generated constructor stub.
		super("Sistema experto encargado de obtener la metrica de calidad");
		this.variablesEntrada = variablesEntrada;
	}

	@Override
	public double[] xfuzzyProcess() {
		
		pruebaCALIDAD2 prueba = new pruebaCALIDAD2();


    	double[] resultado = prueba.crispInference(this.variablesEntrada);
		return resultado;
	}

}
