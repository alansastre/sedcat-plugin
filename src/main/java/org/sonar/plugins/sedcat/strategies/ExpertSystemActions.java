package org.sonar.plugins.sedcat.strategies;

import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.plugins.sedcat.start.SedcatMetricsKeys;
import org.sonar.plugins.sedcat.storage.ActionsMeasureStore;
import org.sonar.plugins.sedcat.xfuzzy.actions.Actions;

/**
 * Sistema experto que obtiene las acciones a realizar buscadas
 * 
 * @author alan.sastre
 * @version 1.0.0
 */
public class ExpertSystemActions extends AbstractInferenceProcess {

	public ExpertSystemActions(MeasureComputerContext context) {

		// logs
		this.LOG = LoggerFactory.getLogger(ExpertSystemActions.class);
		this.START_SYSTEM_MESSAGE = "Running actions expert system";

		// contexto para leer medidas
		this.context = context;

		// Clase particular que almacena los resultados
		this.measureStorer = new ActionsMeasureStore();

		// sistema experto concreto
		this.expertSystem = new Actions();

	}

	@Override
	double[] extractValues() {

		return new double[] {
				
				this.getMeasureDoubleChecked(CoreMetrics.TEST_SUCCESS_DENSITY_KEY),
				this.getMeasureDoubleChecked(CoreMetrics.COVERAGE_KEY),
				this.getMeasureIntChecked(CoreMetrics.TESTS_KEY),
				this.getMeasureDoubleChecked(SedcatMetricsKeys.MUTANTS_KEY),
				this.getMeasureIntChecked(CoreMetrics.NCLOC_KEY),
				this.getMeasureDoubleChecked(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY),
		};

	}

}