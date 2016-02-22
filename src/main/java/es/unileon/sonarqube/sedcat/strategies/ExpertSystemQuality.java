/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;

import es.unileon.sonarqube.sedcat.analyzers.InputVariablesGeneral;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.storage.QualityMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad_1;

/**
 *  Sistema experto que obtiene la metrica de calidad buscada
 *	@author alan.jesus
 *	@version 1.0
 */
public class ExpertSystemQuality implements IExpertSystemStrategy{


	private static final Logger LOG = LoggerFactory.getLogger(ExpertSystemQuality.class);
	
	public void xfuzzyProcess(MeasureComputerContext context) {
		
		LOG.info("Ejecutando sistema experto para calidad.");
		
		//1 - extraer metricas de entrada
		double[] inputMetricValues = this.extractValues(context);
		
		//2- Ejecutar el sistema experto
		Calidad_1 quality = new Calidad_1();
		double[] outputMeasureValues = quality.crispInference(inputMetricValues);
		
		//3- Almacenar metricas de salida como medidas
    	QualityMeasureStore metricToStore = new QualityMeasureStore(outputMeasureValues, context);
    	metricToStore.outputMeasureStore();

	}


	private double[] extractValues(MeasureComputerContext context) {
		
		double[] qualityInputMetrics = new double[]{
				
			context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY).getDoubleValue(),
			context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY).getDoubleValue(),
			context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY).getDoubleValue(),
			context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY).getIntValue(),
			context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY).getIntValue(),

		};
		
		return qualityInputMetrics;
	}

}
