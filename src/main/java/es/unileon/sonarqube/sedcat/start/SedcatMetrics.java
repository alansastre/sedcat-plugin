package es.unileon.sonarqube.sedcat.start;

import static es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys.*;
import java.util.Arrays;
import java.util.List;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metric.Builder;
import org.sonar.api.measures.Metric.ValueType;
import org.sonar.api.measures.Metrics;



/**
 * Metrics for the sonar SEDCAT plugin.
 * 
 * @author alan.sastre
 */
public class SedcatMetrics implements Metrics {

//	private static final List<Metric> METRICS = new ArrayList<Metric>();
//	private static final List<Metric> QUANTITATIVE_METRICS = new ArrayList<Metric>();
//	public static final String DOMAIN_SEDCAT = "Quality Testing Measuring";
	//METRICAS ENTRADA
	//Metrica EXITO
	public static final Metric UNIT_TESTS_SUCCESS = new Metric.Builder(SUCCESS_UNIT_TESTS_KEY, "Exito", Metric.ValueType.PERCENT)
		    .setDescription("Exito en porcentaje de los test")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	//Metrica COBERTURA
	public static final Metric UNIT_TESTS_COVERAGE = new Metric.Builder(COVERAGE_UNIT_TESTS_KEY, "Cobertura", Metric.ValueType.PERCENT)
		    .setDescription("Cobertura en porcentaje de los test")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	//Metricas para  MUTANTES
		//cobertura mutantes
	public static final Metric MUTANTS = new Metric.Builder(MUTANTS_KEY, "Cobertura Mutantes", Metric.ValueType.PERCENT)
		    .setDescription("Mutantes en porcentaje de los test")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	//Metrica NUMEROTESTS
	public static final Metric NUMBER_TESTS = new Metric.Builder(NUMBERTESTS_KEY, "Numero de tests", Metric.ValueType.INT)
		    .setDescription("Numero total de tests unitarios en el proyecto")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	//Metrica CODELINES
	public static final Metric CODE_LINES = new Metric.Builder(CODE_LINES_KEY, "Numero de lineas de codigo", Metric.ValueType.INT)
		    .setDescription("Numero total de lineas de codigo en el proyecto (sin contar los test)")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	
	//METRICAS SALIDA
		//MEDIDA CALIDAD
	public static final Metric QUALITY_MEASURE = new Metric.Builder(QUALITY_MEASURE_KEY, "Medida Calidad", Metric.ValueType.PERCENT)
		    .setDescription("Métrica de salida: calidad para las pruebas de testing")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
		//ACCIONES
	public static final Metric<String> ACTIONS_TO_PERFORM = new Metric.Builder(ACTIONS_TO_PERFORM_KEY, "Acciones para mejora", Metric.ValueType.STRING)
		    .setDescription("Métrica de salida: Acciones a realizar para mejorar la metrica de calidad")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	
	
	
	//METRICAS DE PRUEBA
	public static final Metric<String> MESSAGE = new Metric.Builder(MESSAGE_KEY_1, "mensaje1", Metric.ValueType.STRING)
		    .setDescription("This is a metric to store a well known message")
		    .setDirection(Metric.DIRECTION_WORST)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	public static final Metric<String> MESSAGE_2 = new Metric.Builder(MESSAGE_KEY_2, "mensaje2", Metric.ValueType.STRING)
		    .setDescription("This is a metric to store a well known message")
		    .setDirection(Metric.DIRECTION_WORST)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	//Metrica CODELINES - computers
	public static final Metric CODE_LINES_COMPUTERS = new Metric.Builder(LINESOFCODE_COMPUTER, "Numero de lineas de codigo computadas", Metric.ValueType.INT)
		    .setDescription("Numero total de lineas de codigo en el proyecto (sin contar los test) computadas")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	//Metrica numbertests - computers
	public static final Metric NUMBERTESTS_COMPUTERS = new Metric.Builder(NUMBERTESTS_COMPUTER, "Numero de tests computados", Metric.ValueType.INT)
		    .setDescription("Numero total de tests en el proyecto ")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	//Metrica general - computers
	public static final Metric GENERAL_RESULT_COMPUTERS = new Metric.Builder(GENERAL_COMPUTER_RESULT, "Numero de tests computados", Metric.ValueType.INT)
		    .setDescription("Numero total de tests en el proyecto ")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	// getMetrics() method is defined in the Metrics interface and is used by
	  public List<Metric> getMetrics() {
		  
		return Arrays.<Metric>asList(
				
				UNIT_TESTS_SUCCESS,
				UNIT_TESTS_COVERAGE,
				MUTANTS,
				NUMBER_TESTS,
				CODE_LINES,
				QUALITY_MEASURE,
				ACTIONS_TO_PERFORM
				
				
				);

	  }
	
//	private static Metric buildMetric(String key, String name, String description, ValueType valueType, Integer direction, Boolean qualitative, String domain) {
//		return buildMetric(instanceBuilder(key, name, description, valueType, direction, qualitative, domain), qualitative);
//	}
//	
//	private static Metric buildMetric(String key, String name, String description, ValueType valueType, Integer direction, Boolean qualitative, String domain, Double best, Double worst) {
//		Builder builder = instanceBuilder(key, name, description, valueType, direction, qualitative, domain);
//		builder.setBestValue(best);
//		builder.setWorstValue(worst);
//		return buildMetric(builder, qualitative);
//	}
//	
//	private static Metric buildMetric(Builder builder, boolean qualitative) {
//		Metric metric = builder.create();
//		METRICS.add(metric);
//		if (!qualitative) {
//			QUANTITATIVE_METRICS.add(metric);
//		}
//		return metric;
//	}
	
//	private static Builder instanceBuilder(String key, String name, String description, ValueType valueType, Integer direction, Boolean quailitative, String domain) {
//		Builder builder = new Builder(key, name, valueType);
//		builder.setDescription(description);
//		builder.setDirection(direction);
//		builder.setQualitative(quailitative);
//		builder.setDomain(domain);
//		return builder;
//	}
//
////	/**
////	 * @see Metrics#getMetrics()
////	 */
////	public List<Metric> getMetrics() {
////		return METRICS;
////	}
//
//	/**
//	 * Returns the sedcat quantitative metrics list.
//	 * @return {@link List<Metric>} The sedcat quantitative metrics list.
//	 */
//	public static List<Metric> getQuantitativeMetrics() {
//		return QUANTITATIVE_METRICS;
//	}
}
