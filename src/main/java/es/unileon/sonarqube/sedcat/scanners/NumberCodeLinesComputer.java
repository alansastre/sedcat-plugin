/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.Issue;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.measures.CoreMetrics;

import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class NumberCodeLinesComputer implements MeasureComputer {
    /**
     * The logger object for the sensor.
     */
	private static final Logger LOG = LoggerFactory.getLogger(NumberCodeLinesComputer.class);
	
	
	public void compute(MeasureComputerContext context) {
		
		LOG.info("Computer: CODE LINES");

	     int num_linesCode = 0;
	     
	     for (Measure childMeasure : context.getChildrenMeasures(CoreMetrics.NCLOC_KEY)) {
	    	 num_linesCode += childMeasure.getIntValue();
	       }
	     
	     context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, num_linesCode);
	     LOG.info("Computer: CODE LINES metrica guardada");

	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		LOG.info("Computers on definition: CODE LINES");
	    return defContext.newDefinitionBuilder()

	    	     // Input metrics can be empty, for instance if only issues will be read
	    	     .setInputMetrics(CoreMetrics.NCLOC_KEY)

	    	     // Output metrics must contains at least one metric
	    	     .setOutputMetrics(SedcatMetricsKeys.CODE_LINES_KEY)

	    	     .build();
	    
	}
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }

}
