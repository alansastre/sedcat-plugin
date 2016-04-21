/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.storage.QualityMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad_1;

/**
 *  Sistema experto que obtiene la metrica de calidad buscada
 *	@author alan.sastre
 *	@version 1.0
 */
public class ExpertSystemQuality extends AbstractInferenceProcess{


	public ExpertSystemQuality(MeasureComputerContext context) {
		
		//logs
		this.LOG = LoggerFactory.getLogger(ExpertSystemQuality.class);
		this.START_SYSTEM_MESSAGE = "Ejecutando sistema experto para calidad.";
		
		//contexto para leer medidas
		this.context = context;
		
		//Clase particular que almacena los resultados
		this.measureStorer= new QualityMeasureStore();
		
		//sistema experto concreto
		this.expertSystem = new Calidad_1();
		
	}
	
	
	double[] extractValues(MeasureComputerContext context) {
		

		Measure[] qualityInputMetrics = new Measure[]{
				
				context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY),
				context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY),
				context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY),
				context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY),
				context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY),

		};
		
		this.checkNotNullInputMetrics(qualityInputMetrics);


		double[] qualityInputMetricsValues = new double[]{
				
			context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY).getDoubleValue(),
			context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY).getDoubleValue(),
			context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY).getDoubleValue(),
			context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY).getIntValue(),
			context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY).getIntValue(),

		};
		
		return qualityInputMetricsValues;
	}

}
