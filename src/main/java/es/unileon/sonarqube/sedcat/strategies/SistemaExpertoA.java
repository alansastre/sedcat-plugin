/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import es.unileon.sonarqube.sedcat.xfuzzy.pruebaCALIDAD2;

/**
 *  Sistema experto A que define el primer sistema experto creado con xfuzzy para sedcat
 *	@author alan.sastre
 *	@version 1.0
 */
public class SistemaExpertoA extends AbstractSistemaExperto{

	
	private double[] variablesEntrada; 
	/**
	 * @param variablesEntrada 
	 * 
	 */
	public SistemaExpertoA(double[] variablesEntrada) {
		// TODO Auto-generated constructor stub.
		super("sistema experto 2 variables");
		this.variablesEntrada = variablesEntrada;
	}

	@Override
	public double[] xfuzzyProcess() {
		
		pruebaCALIDAD2 prueba = new pruebaCALIDAD2();


    	double[] resultado = prueba.crispInference(this.variablesEntrada);
		return resultado;
	}

}
