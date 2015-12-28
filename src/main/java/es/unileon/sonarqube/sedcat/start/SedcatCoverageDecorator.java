package es.unileon.sonarqube.sedcat.start;



import java.util.List;

import org.sonar.api.batch.Decorator;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.batch.DependedUpon;
import org.sonar.api.batch.DependsUpon;
import org.sonar.api.measures.MeasureUtils;
import org.sonar.api.measures.Metric;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;

import com.google.common.collect.Lists;

/**
 * Mutation coverage decorator.
 *
 * @author <a href="mailto:aquiporras@gmail.com">Jaime Porras L&oacute;pez</a>
 */
public class SedcatCoverageDecorator implements Decorator {

	public boolean shouldExecuteOnProject(Project project) {
		return project.getAnalysisType().isDynamic(true);
	}

	@DependedUpon
	public Metric getCoverageMetric() {
		return null;
	}

	@DependsUpon
	public List<Metric> getBaseMetrics() {
		return null;
	}

	public void decorate(Resource resource, DecoratorContext context) {
//		Double elements = MeasureUtils.getValue(context.getMeasure(PitestMetrics.MUTATIONS_TOTAL), 0.0);
//
//		if (elements > 0.0) {
//			Double coveredElements = MeasureUtils.getValue(context.getMeasure(PitestMetrics.MUTATIONS_DETECTED), 0.0);
//			context.saveMeasure(PitestMetrics.MUTATIONS_COVERAGE, (100.0 * coveredElements) / elements);
//		}
	}
}
