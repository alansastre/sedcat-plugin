/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.actions.Acciones_1;

/**
 *  Sistema experto que obtiene las acciones a realizar buscadas
 *	@author alan.sastre
 *	@version 1.0
 */
public class ExpertSystemActions extends AbstractInferenceProcess{

	
	public ExpertSystemActions(MeasureComputerContext context) {
		
		//logs
		this.LOG = LoggerFactory.getLogger(ExpertSystemActions.class);
		this.START_SYSTEM_MESSAGE = "Ejecutando sistema experto para acciones.";
		
		//contexto para leer medidas
		this.context = context;
		
		//Clase particular que almacena los resultados
		this.measureStorer= new ActionsMeasureStore();
		
		//sistema experto concreto
		this.expertSystem = new Acciones_1();
		
	}

	double[] extractValues(MeasureComputerContext context) {
		
		double[] actionsInputMetrics = new double[]{
			context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY).getDoubleValue(),
			context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY).getDoubleValue(),
			context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY).getIntValue(),
			context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY).getDoubleValue(),
			context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY).getIntValue(),
		};
		
		return actionsInputMetrics;
	}

}