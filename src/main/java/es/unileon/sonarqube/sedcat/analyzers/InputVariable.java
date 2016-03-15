/**
 * 
 */
package es.unileon.sonarqube.sedcat.analyzers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.Metric;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
@Deprecated
public abstract class InputVariable {

	protected Logger LOG;
	protected String DEFAULT_PATH_VARIABLE;
	protected Metric concreteMetric;
	protected double metricValue;
	//especificas de sonar
	protected SensorContext sensorContext;
	protected FileSystem fileSystem; 
	protected Settings settings;
		
	
	abstract public double obtainInputVariable();
	
}
