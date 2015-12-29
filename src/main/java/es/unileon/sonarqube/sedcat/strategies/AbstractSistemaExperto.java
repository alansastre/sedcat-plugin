/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

/**
 *  Clase abstracta que implementa la interfaz ISistemaExperto
 *	@author alan.sastre
 *	@version 1.0
 */
public abstract class AbstractSistemaExperto implements ISistemaExperto {

	
	public String nombreSistemaExperto;
	/**
	 * 
	 */
	public AbstractSistemaExperto(String nombreSistemaExperto) {
		// TODO Auto-generated constructor stub
		this.nombreSistemaExperto = nombreSistemaExperto;
		
	}

	/* (non-Javadoc)
	 * @see es.unileon.sonarqube.sedcat.strategies.ISistemaExperto#xfuzzyProcess()
	 */
	public abstract double[] xfuzzyProcess();

}
