package es.unileon.sonarqube.sedcat.scanners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.measures.CoreMetrics;

import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class ComplexityFunctionsComputer implements MeasureComputer {

	private static final Logger LOG = LoggerFactory.getLogger(ComplexityFunctionsComputer.class);
	
	@Override
	public void compute(MeasureComputerContext context) {

		LOG.info("componente: " + context.getComponent().getType());

		Measure complexity = context.getMeasure(CoreMetrics.FUNCTION_COMPLEXITY_KEY);
		if (complexity != null) {
			LOG.info("valor: " + complexity.getDoubleValue());
			context.addMeasure(SedcatMetricsKeys.COMPLEXITY_FUNCTION_KEY, complexity.getDoubleValue());
		} else {
			LOG.info("Esta metrica ha sido nula, el sistema la considerara como cero. ");
			context.addMeasure(SedcatMetricsKeys.COMPLEXITY_FUNCTION_KEY, 0.0);
		}

	}

	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		return defContext.newDefinitionBuilder()

				// Input metrics can be empty, for instance if only issues will
				// be read
				.setInputMetrics(CoreMetrics.FUNCTION_COMPLEXITY_KEY)

				// Output metrics must contains at least one metric
				.setOutputMetrics(SedcatMetricsKeys.COMPLEXITY_FUNCTION_KEY)

				.build();

	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
