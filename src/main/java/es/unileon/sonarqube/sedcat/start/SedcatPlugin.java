package es.unileon.sonarqube.sedcat.start;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import es.unileon.sonarqube.sedcat.scanners.ComplexityComputer;
import es.unileon.sonarqube.sedcat.scanners.MutationsCoverageSensor;
import es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder;
import es.unileon.sonarqube.sedcat.scanners.MutationsReportParser;
import org.sonar.api.PropertyType;
/**
 * @see SonarQube 5.3 API
 * http://javadocs.sonarsource.org/5.3/apidocs/org/sonar/api/ce/measure/package-summary.html
 * 
 * This class is the entry point for all extensions. It must be referenced from pom.xml.
 */
//properties that appear in General Settings -> Sedcat in SonarQube
@Properties({
		@Property(
				key = SedcatConstants.PITEST_REPORT_DIRECTORY_KEY,
				defaultValue = SedcatConstants.PITEST_REPORT_DIRECTORY_DEFAULT,
				name = "Mutations Testing Tool Report",
				description = "Indicate relative path to mutation testing report like default value.",
				global = true,
				project = true),
		@Property(
				key = SedcatConstants.COMPLEXITY_THRESHOLD_KEY,
				defaultValue = SedcatConstants.COMPLEXITY_THRESHOLD_DEFAULT,
				name = "Complexity Threshold",
				description = "Suggest threshold average complexity by class to be considered for calculations in a range of 0 to 60",
				global = true,
				project = true),
		@Property(
				key = SedcatConstants.ACTIVE_MODE_KEY,
				defaultValue = "true",
				name = "Sedcat active mode",
				description = "Enable or disable sedcat on analysis, by default sedcat is active",
				project = true,
				global = true,
				type = PropertyType.BOOLEAN),
})
public class SedcatPlugin extends SonarPlugin {

	/**
	   * Returns the list of the extensions to be available at runtime.
	   */
	@SuppressWarnings("unchecked")
	@Override
	  public List getExtensions() {

    	  List extensions = new ArrayList();

    	    extensions.add(SedcatMetrics.class);

    	    // Sensor Scanners + Ioc dependencies: (first level operations
    	    extensions.addAll(asList(
    	    		MutationsReportFinder.class,
    	    		MutationsReportParser.class,
    	    		MutationsCoverageSensor.class
    	    		));
    	    
    	    // Computer Scanners: second level operations
    	    extensions.addAll(asList(ComplexityComputer.class));
    	    
    	    // Special Scanner - manages Sensor Scanners and Computer Scanners metrics reached dynamically
    	    extensions.add(GeneralComputer.class);
    	    
    	    // UI - dashboard widget 
    	    extensions.addAll(asList(SedcatHtmlFooter.class, SedcatDashboardWidget.class));
    	    
 
    	    return extensions;    
    }
	
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }


}