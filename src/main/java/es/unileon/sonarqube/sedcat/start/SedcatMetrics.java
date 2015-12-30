package es.unileon.sonarqube.sedcat.start;

import static es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys.*;

import java.util.ArrayList;
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

//prueba
	private static final List<Metric> METRICS = new ArrayList<Metric>();
	private static final List<Metric> QUANTITATIVE_METRICS = new ArrayList<Metric>();
	
	public static final Metric EXITO = new Metric.Builder(EXITO_COVERAGE_KEY, "Exito", Metric.ValueType.PERCENT)
		    .setDescription("Exito en porcentaje de los test")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	public static final Metric COBERTURA = new Metric.Builder(COBERTURA_COVERAGE_KEY, "Cobertura", Metric.ValueType.PERCENT)
		    .setDescription("Cobertura en porcentaje de los test")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	public static final Metric QUALITY_MEASURE = new Metric.Builder(QUALITY_MEASURE_KEY, "Medida Calidad", Metric.ValueType.PERCENT)
		    .setDescription("Métrica de calidad para las pruebas de testing")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	public static final Metric ACTIONS_TO_PERFORM = new Metric.Builder(ACTIONS_TO_PERFORM_KEY, "Acciones para mejora", Metric.ValueType.STRING)
		    .setDescription("Acciones a realizar para mejorar la metrica de calidad")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	
	
	
	//metricas de prueba
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
	
	// getMetrics() method is defined in the Metrics interface and is used by
	  public List<Metric> getMetrics() {
	    return Arrays.<Metric>asList(EXITO, COBERTURA, MESSAGE, MESSAGE_2);
	  }
	
	

	private static Metric buildMetric(String key, String name, String description, ValueType valueType, Integer direction, Boolean qualitative, String domain) {
		return buildMetric(instanceBuilder(key, name, description, valueType, direction, qualitative, domain), qualitative);
	}
	
	private static Metric buildMetric(String key, String name, String description, ValueType valueType, Integer direction, Boolean qualitative, String domain, Double best, Double worst) {
		Builder builder = instanceBuilder(key, name, description, valueType, direction, qualitative, domain);
		builder.setBestValue(best);
		builder.setWorstValue(worst);
		return buildMetric(builder, qualitative);
	}
	
	private static Metric buildMetric(Builder builder, boolean qualitative) {
		Metric metric = builder.create();
		METRICS.add(metric);
		if (!qualitative) {
			QUANTITATIVE_METRICS.add(metric);
		}
		return metric;
	}
	
	private static Builder instanceBuilder(String key, String name, String description, ValueType valueType, Integer direction, Boolean quailitative, String domain) {
		Builder builder = new Builder(key, name, valueType);
		builder.setDescription(description);
		builder.setDirection(direction);
		builder.setQualitative(quailitative);
		builder.setDomain(domain);
		return builder;
	}

//	/**
//	 * @see Metrics#getMetrics()
//	 */
//	public List<Metric> getMetrics() {
//		return METRICS;
//	}

	/**
	 * Returns the pitest quantitative metrics list.
	 * @return {@link List<Metric>} The pitest quantitative metrics list.
	 */
	public static List<Metric> getQuantitativeMetrics() {
		return QUANTITATIVE_METRICS;
	}
}
