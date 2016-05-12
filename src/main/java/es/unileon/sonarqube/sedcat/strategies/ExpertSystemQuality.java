/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.measures.CoreMetrics;

import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.storage.QualityMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad;

/**
 * Sistema experto que obtiene la metrica de calidad buscada
 * 
 * @author alan.sastre
 * @version 1.0
 */
public class ExpertSystemQuality extends AbstractInferenceProcess {

	public ExpertSystemQuality(MeasureComputerContext context) {

		// logs
		this.LOG = LoggerFactory.getLogger(ExpertSystemQuality.class);
		this.START_SYSTEM_MESSAGE = "Ejecutando sistema experto para calidad.";

		// contexto para leer medidas
		this.context = context;

		// Clase particular que almacena los resultados
		this.measureStorer = new QualityMeasureStore();

		// sistema experto concreto
		this.expertSystem = new Calidad();

	}

	@Override
	double[] extractValues() {

		Measure[] actionsInputMetrics = new Measure[] {

				this.context.getMeasure(CoreMetrics.TEST_SUCCESS_DENSITY_KEY),
				this.context.getMeasure(CoreMetrics.COVERAGE_KEY),
				this.context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY),
				this.context.getMeasure(CoreMetrics.TESTS_KEY),
				this.context.getMeasure(CoreMetrics.NCLOC_KEY),	
				this.context.getMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY),

		};

		this.checkNotNullInputMetrics(actionsInputMetrics);

		return new double[] {
				
				this.context.getMeasure(CoreMetrics.TEST_SUCCESS_DENSITY_KEY).getDoubleValue(),
				this.context.getMeasure(CoreMetrics.COVERAGE_KEY).getDoubleValue(),
				this.context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY).getDoubleValue(),
				this.context.getMeasure(CoreMetrics.TESTS_KEY).getIntValue(),
				this.context.getMeasure(CoreMetrics.NCLOC_KEY).getIntValue(), 
				this.context.getMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY).getDoubleValue(),
				
		};
	}

}
