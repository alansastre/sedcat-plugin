package es.unileon.sonarqube.sedcat.strategies;

import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext;
import org.sonar.api.measures.CoreMetrics;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.actions.Actions;

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