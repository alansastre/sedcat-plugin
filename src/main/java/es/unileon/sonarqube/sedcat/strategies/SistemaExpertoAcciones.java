/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;
import es.unileon.sonarqube.sedcat.xfuzzy.pruebaCALIDAD2;

/**
 *  Sistema experto que obtiene las acciones a realizar buscadas
 *	@author alan.sastre
 *	@version 1.0
 */
public class SistemaExpertoAcciones extends AbstractSistemaExperto{

	
	private double[] variablesEntrada; 
	/**
	 * @param variablesEntrada 
	 * 
	 */
	public SistemaExpertoAcciones(double[] variablesEntrada) {
		// TODO Auto-generated constructor stub.
		super("Sistema experto encargado de obtener las acciones a realizar buscadas"
				+ "dedicadas a mejorar la metrica de calidad");
		this.variablesEntrada = variablesEntrada;
	}

	@Override
	public double[] xfuzzyProcess() {
		
		
		//FIXME cambiar por el sistema experto en cuestion 
		pruebaCALIDAD2 prueba = new pruebaCALIDAD2();


    	double[] resultado = prueba.crispInference(this.variablesEntrada);
		return resultado;
	}

}