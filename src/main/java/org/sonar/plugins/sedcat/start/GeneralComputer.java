package org.sonar.plugins.sedcat.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.plugins.sedcat.strategies.ExpertSystemActions;
import org.sonar.plugins.sedcat.strategies.ExpertSystemQuality;

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
public class GeneralComputer implements MeasureComputer {

	private static final Logger LOG = LoggerFactory.getLogger(GeneralComputer.class);
	private boolean isProject = true;

	public boolean isProject() {
		return isProject;
	}

	/**
	 * This compute method only executes experts systems at project level
	 */
	@Override
	public void compute(MeasureComputerContext context) {

		isProject = true;
		
		if (("true").equals(context.getSettings().getString(SedcatConstants.ACTIVE_MODE_KEY))
				&& Component.Type.PROJECT == context.getComponent().getType()) {

			LOG.info("Running expert systems");

			// Ejecutar sistemas expertos:
			
			// Sistema experto calidad
			ExpertSystemQuality expertSystemQuality = new ExpertSystemQuality(context);
			expertSystemQuality.xfuzzyProcess();
			// Sistema experto acciones
			ExpertSystemActions expertSystemActions = new ExpertSystemActions(context);
			expertSystemActions.xfuzzyProcess();

		} else {
			
			isProject = false;

		}

	}

	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		return defContext.newDefinitionBuilder()

				// Input metrics can be empty, for instance if only issues will
				// be read
				.setInputMetrics(
						
						CoreMetrics.TEST_SUCCESS_DENSITY_KEY,
						CoreMetrics.COVERAGE_KEY,
						CoreMetrics.TESTS_KEY,
						CoreMetrics.NCLOC_KEY,
						SedcatMetricsKeys.MUTANTS_KEY,
						SedcatMetricsKeys.COMPLEXITY_CLASS_KEY
						
						)

				// Output metrics must contains at least one metric
				.setOutputMetrics(
						//quality
						SedcatMetricsKeys.QUALITY_MEASURE_KEY,
						SedcatMetricsKeys.QUALITY_MESSAGE_KEY,
						//actions
						SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY,
						SedcatMetricsKeys.ACTIONS_MESSAGE_KEY
						
						)

				.build();

	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
