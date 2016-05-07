package es.unileon.sonarqube.sedcat.strategies;

import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.measures.CoreMetrics;

import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.actions.Acciones_1;

/**
 * Sistema experto que obtiene las acciones a realizar buscadas
 * 
 * @author alan.sastre
 * @version 1.0
 */
public class ExpertSystemActions extends AbstractInferenceProcess {

	public ExpertSystemActions(MeasureComputerContext context) {

		// logs
		this.LOG = LoggerFactory.getLogger(ExpertSystemActions.class);
		this.START_SYSTEM_MESSAGE = "Ejecutando sistema experto para acciones.";

		// contexto para leer medidas
		this.context = context;

		// Clase particular que almacena los resultados
		this.measureStorer = new ActionsMeasureStore();

		// sistema experto concreto
		this.expertSystem = new Acciones_1();

	}

	@Override
	double[] extractValues() {

		Measure[] actionsInputMetrics = new Measure[] {
				
				this.context.getMeasure(CoreMetrics.TEST_SUCCESS_DENSITY_KEY),
				this.context.getMeasure(CoreMetrics.COVERAGE_KEY),
				this.context.getMeasure(CoreMetrics.TESTS_KEY),
				this.context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY),
				this.context.getMeasure(CoreMetrics.NCLOC_KEY),
	
		};

		this.checkNotNullInputMetrics(actionsInputMetrics);

		return new double[] {
				
				this.context.getMeasure(CoreMetrics.TEST_SUCCESS_DENSITY_KEY).getDoubleValue(),
				this.context.getMeasure(CoreMetrics.COVERAGE_KEY).getDoubleValue(),
				this.context.getMeasure(CoreMetrics.TESTS_KEY).getIntValue(),
				this.context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY).getDoubleValue(),
				this.context.getMeasure(CoreMetrics.NCLOC_KEY).getIntValue(), 

		};

	}

}