/**
 * 
 */
package es.unileon.sonarqube.sedcat.start;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.CoreMetrics;

import es.unileon.sonarqube.sedcat.storage.ActionsToPerformStore;
import es.unileon.sonarqube.sedcat.storage.QualityMeasureStore;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality;
import es.unileon.sonarqube.sedcat.strategies.IExpertSystemStrategy;
/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class GeneralComputer implements MeasureComputer {

	/**
	 * The logger object for the sensor.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(GeneralComputer.class);
	private IExpertSystemStrategy strategy;

	  /*
	   * (non-Javadoc)
	   * @see org.sonar.api.ce.measure.MeasureComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)
	   */
	public void compute(MeasureComputerContext context) {
		
		LOG.info("General computer compute: inicio");
		LOG.info("getComponent(): "+context.getComponent());
		LOG.info("getComponent(): "+context.getComponent().getType());
		LOG.info("getComponent(): "+context.getComponent().getType().toString());

		
		double success = context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY).getDoubleValue();
		LOG.info("General computer compute: exito");
		double coverage = context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY).getDoubleValue();
		LOG.info("General computer compute: cobertura");
		double mutants = 100.0;
		LOG.info("General computer compute: mutantes");
		double numbertest = context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY).getIntValue();
		LOG.info("General computer compute: numerotest");
		double codelines = context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY).getIntValue();
		LOG.info("General computer compute: lineascodigo");
		

	     //Obtener valores de entrada
//		double[] qualityInputMetrics = new double[]{
//				context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY).getDoubleValue(),
//				context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY).getDoubleValue(),
////				context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY).getDoubleValue(),
//				100.0,
//				context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY).getIntValue(),
//				context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY).getIntValue(),
//	
//		};
		double[] qualityInputMetrics = new double[]{
				success,coverage,mutants,numbertest,codelines

		};
		LOG.info("General computer compute: quality input metrics");
//		double[] actionsInputMetrics = new double[]{
//				context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY).getDoubleValue(),
//				context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY).getDoubleValue(),
//				context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY).getIntValue(),
////				context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY).getDoubleValue(),
//				100.0,
//				context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY).getIntValue(),
//		};
		double[] actionsInputMetrics = new double[]{
				success,coverage,numbertest,mutants,codelines
		};
		
		
		
		if(success!=0 && coverage!=0 && numbertest!=0 && mutants!=0 && codelines!=0){
			
			LOG.info("if: entrada a los s e");
			LOG.info("success: "+success);
			LOG.info("coverage: "+coverage);
			LOG.info("numbertest: "+numbertest);
			LOG.info("mutants: "+mutants);
			LOG.info("codelines: "+codelines);
		     //Ejecutar sistemas expertos
		     	//quality
	    	this.setExpertSystemStrategy(new ExpertSystemQuality());
	    	double[] quality = this.performExpertSystemStrategy(qualityInputMetrics);
	    	QualityMeasureStore metricToStore = new QualityMeasureStore(quality, context);
	    	LOG.info("General computer compute: sistema experto calidad");
		     	//actions
			this.setExpertSystemStrategy(new ExpertSystemActions());
			double[] actions = this.performExpertSystemStrategy(actionsInputMetrics);
			ActionsToPerformStore actionsToStore = new ActionsToPerformStore(actions, context);
			LOG.info("General computer compute: sistema experto acciones");
		
		}else{
			LOG.info("else que no hace nada");
		}
		
    	
//		context.addMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY, 45.56);
//		context.addMeasure(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY, "prueba");
	    	
	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		LOG.info("General computer definition");
	    return defContext.newDefinitionBuilder()

	    	     // Input metrics can be empty, for instance if only issues will be read
	    	     .setInputMetrics(
	    	    		 SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY,
	    	    		 SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY,
	    	    		 SedcatMetricsKeys.NUMBERTESTS_KEY,
	    	    		 SedcatMetricsKeys.CODE_LINES_KEY
//	    	    		 SedcatMetricsKeys.MUTANTS_KEY,
	    	    		 )
	    	     
	    	     // Output metrics must contains at least one metric
	    	     .setOutputMetrics(SedcatMetricsKeys.QUALITY_MEASURE_KEY,SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY)

	    	     .build();
	    
	}
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }
	
	/**
	 * @param strategy
	 */
	  public void setExpertSystemStrategy(IExpertSystemStrategy strategy){
			this.strategy = strategy;
		}
	  
	  /**
	   * 
	   * @param inputMetrics
	   * @return
	   */
	  public double[] performExpertSystemStrategy(double[] inputMetrics){
		  
		 return this.strategy.xfuzzyProcess(inputMetrics);
	  }
	
}


