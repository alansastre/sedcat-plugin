/**
 * 
 */
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
public class NumberCodeLinesComputer implements MeasureComputer {

	
	private static final Logger LOG = LoggerFactory.getLogger(NumberCodeLinesComputer.class);
	
	
	public void compute(MeasureComputerContext context) {
		
		LOG.info("tipo: "+context.getComponent().getType());
		
		
		Measure codelines = context.getMeasure(CoreMetrics.NCLOC_KEY);
		if (codelines!=null) {
			LOG.info("tipo: "+codelines.getIntValue());
			context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, codelines.getIntValue());
		}else{
			LOG.info("Esta metrica ha sido nula, el sistema la considerara como cero. ");
			context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, 0);
		}
		
		
//	     int num_linesCode = 0;
//	     
//	     for (Measure childMeasure : context.getChildrenMeasures(CoreMetrics.NCLOC_KEY)) {
//	    	 num_linesCode += childMeasure.getIntValue();
//	       }
//	     
//	     context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, num_linesCode);
//	     LOG.info("Computer: CODE LINES metrica guardada");

	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

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
