/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import java.util.List;

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
public class MutationsCoverageComputer implements MeasureComputer {

    /**
     * The logger object for the sensor.
     */
	private static final Logger LOG = LoggerFactory.getLogger(MutationsCoverageComputer.class);
	
	
	public void compute(MeasureComputerContext context) {
		
//		LOG.info("Computer: MUTATIONS");
//
//	     int num_linesCode = 0;
//	     for (Measure childMeasure : context.getChildrenMeasures(PitestMetrics.)) {
//	    	 num_linesCode += childMeasure.getIntValue();
//	       }
//     
//	     context.addMeasure(SedcatMetricsKeys.MUTANTS_KEY, 100);
	     

	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		LOG.info("Computers on definition: MUTATIONS");
	    return defContext.newDefinitionBuilder()

	    	     // Input metrics can be empty, for instance if only issues will be read
	    	     .setInputMetrics()

	    	     // Output metrics must contains at least one metric
	    	     .setOutputMetrics(SedcatMetricsKeys.MUTANTS_KEY)

	    	     .build();
	    
	}
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }

}

