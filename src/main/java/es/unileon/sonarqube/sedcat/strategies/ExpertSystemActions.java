/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;

import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore;
import es.unileon.sonarqube.sedcat.storage.QualityMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.actions.Acciones_1;
import es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad_1;

/**
 *  Sistema experto que obtiene las acciones a realizar buscadas
 *	@author alan.jesus
 *	@version 1.0
 */
public class ExpertSystemActions implements IExpertSystemStrategy{

	
	private static final Logger LOG = LoggerFactory.getLogger(ExpertSystemActions.class);

	public void xfuzzyProcess(MeasureComputerContext context) {
		
		LOG.info("Ejecutando sistema experto para acciones.");

		//1 - extraer metricas de entrada
		double[] inputMetricValues = this.extractValues(context);
		
		//2- Ejecutar el sistema experto
		Acciones_1 actions = new Acciones_1();
		double[] outputMeasureValues = actions.crispInference(inputMetricValues);
		
		//3- Almacenar metricas de salida como medidas
		ActionsMeasureStore actionsToStore = new ActionsMeasureStore(outputMeasureValues, context);
		actionsToStore.outputMeasureStore();

	}
	private double[] extractValues(MeasureComputerContext context) {
		
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