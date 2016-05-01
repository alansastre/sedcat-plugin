package es.unileon.sonarqube.sedcat.start;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import es.unileon.sonarqube.sedcat.scanners.ComplexityFunctionsComputer;
import es.unileon.sonarqube.sedcat.scanners.CoverageUnitTestsComputer;
import es.unileon.sonarqube.sedcat.scanners.MutationsCoverageSensor;
import es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder;
import es.unileon.sonarqube.sedcat.scanners.MutationsReportParser;
import es.unileon.sonarqube.sedcat.scanners.NumberCodeLinesComputer;
import es.unileon.sonarqube.sedcat.scanners.NumberTestsComputer;
import es.unileon.sonarqube.sedcat.scanners.SuccessUnitTestsComputer;
import es.unileon.sonarqube.sedcat.strategies.AbstractInferenceProcess;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality;
/**
 * @see SonarQube 5.3 API
 * http://javadocs.sonarsource.org/5.3/apidocs/org/sonar/api/ce/measure/package-summary.html
 * 
 * This class is the entry point for all extensions. It must be referenced from pom.xml.
 */
//properties that appear in General Settings -> Sedcat in SonarQube
@Properties({
  @Property(key = SedcatConstants.REPORT_DIRECTORY_DEF, defaultValue = "target/pit-reports",
  name = "Mutations Testing Tool Report", description = "Indicate relative path to mutation testing report like default value.", global = true,
  project = true),
  
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

    	    // Scanners - first level sensors
    	    extensions.addAll(asList(MutationsReportFinder.class, MutationsReportParser.class, MutationsCoverageSensor.class));
    	    
    	    //Scanners - Second level computers
    	    extensions.addAll(asList(
    	    		NumberCodeLinesComputer.class,
    	    		NumberTestsComputer.class,
    	    		SuccessUnitTestsComputer.class,
    	    		CoverageUnitTestsComputer.class,
    	    		ComplexityFunctionsComputer.class

    	    ));
    	    
    	    //Special Scanner - manages sensor and computer metrics reached dynamically
    	    extensions.add(GeneralComputer.class);
    	    
    	    // UI
    	    extensions.addAll(asList(ExampleSedcatHtml.class, SedcatDashboardWidget.class));
    	    
    	    
    	    return extensions;    
    }
	
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }


}