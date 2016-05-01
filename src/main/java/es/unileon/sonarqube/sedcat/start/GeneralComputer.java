package es.unileon.sonarqube.sedcat.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.MeasureComputer;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality;

/**
 * @author alan.sastre
 * @version 1.0
 *
 *          Define how to compute new measures on some metrics declared by
 *          http://javadocs.sonarsource.org/5.3/apidocs/org/sonar/api/measures/
 *          Metrics.html
 *
 *          This interface replaces the deprecated class
 *          org.sonar.api.batch.Decorator.
 */
// @InstantiationStrategy(InstantiationStrategy.PER_PROJECT)
public class GeneralComputer implements MeasureComputer {

	private static final Logger LOG = LoggerFactory.getLogger(GeneralComputer.class);
	private boolean isProject = true;

	public boolean isProject() {
		return isProject;
	}

	/**
	 * This compute method only executes experts systems at project level
	 */
	public void compute(MeasureComputerContext context) {

		isProject = true;

		if (!context.getComponent().getType().toString().equalsIgnoreCase("PROJECT")) {
			isProject = false;
		} else {

			LOG.info("Ejecutando sistemas expertos");
			// Ejecutar sistemas expertos

			ExpertSystemQuality expertSystemQuality = new ExpertSystemQuality(context);
			expertSystemQuality.xfuzzyProcess();

			ExpertSystemActions expertSystemActions = new ExpertSystemActions(context);
			expertSystemActions.xfuzzyProcess();
		}

	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		return defContext.newDefinitionBuilder()

				// Input metrics can be empty, for instance if only issues will
				// be read
				.setInputMetrics(
						SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY,
						SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY,
						SedcatMetricsKeys.NUMBERTESTS_KEY,
						SedcatMetricsKeys.CODE_LINES_KEY,
						SedcatMetricsKeys.MUTANTS_KEY,
						SedcatMetricsKeys.COMPLEXITY_FUNCTION_KEY
						
						)

				// Output metrics must contains at least one metric
				.setOutputMetrics(SedcatMetricsKeys.QUALITY_MEASURE_KEY, SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY)

				.build();

	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
