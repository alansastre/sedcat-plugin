package es.unileon.sonarqube.sedcat.start;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;
import es.unileon.sonarqube.sedcat.scanners.CoverageUnitTestsComputer;
import es.unileon.sonarqube.sedcat.scanners.MutationsCoverageSensor;
import es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder;
import es.unileon.sonarqube.sedcat.scanners.MutationsReportParser;
import es.unileon.sonarqube.sedcat.scanners.NumberCodeLinesComputer;
import es.unileon.sonarqube.sedcat.scanners.NumberTestsComputer;
import es.unileon.sonarqube.sedcat.scanners.SuccessUnitTestsComputer;
/**
 * This class is the entry point for all extensions. It must be referenced from pom.xml.
 */
@Properties({
//  @Property(key = SedcatConstants.SUCCESS_KEY, defaultValue = "/target/success.txt",
//    name = "Input variable Unit Tests Success", description = "Indicate path to reports of Unit Tests Success.", global = true,
//    project = true),
//  @Property(key = SedcatConstants.COVERAGE_KEY, defaultValue = "/target/coverage.txt",
//    name = "Input variable Unit Tests Coverage", description = "Indicate path to reports of Unit Tests Coverage.", global = true,
//    project = true),
//  @Property(key = SedcatConstants.MUTANTS_KEY, defaultValue = "/target/mutations.txt",
//  name = "Input variable Mutations Coverage", description = "Indicate path to reports of Mutations Coverage.", global = true,
//  project = true),
//  @Property(key = SedcatConstants.NUMBER_TESTS_KEY, defaultValue = "/target/numbertests.txt",
//  name = "Input variable Number Of Tests", description = "Indicate path to reports of Number Of Tests.", global = true,
//  project = true),
//  @Property(key = SedcatConstants.NUMBER_CODE_LINES_KEY, defaultValue = "/target/numbercodelines.txt",
//  name = "Input variable Lines Of Code", description = "Indicate path to reports of Lines Of Code.", global = true,
//  project = true),
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
    	    //Scanner - Second level computers
    	    extensions.addAll(asList(
    	    		NumberCodeLinesComputer.class,
    	    		NumberTestsComputer.class,
    	    		SuccessUnitTestsComputer.class,
    	    		CoverageUnitTestsComputer.class
    	    		

    	    ));
    	    //Scanner - manage sensor and computer metrics
    	    extensions.add(GeneralComputer.class);
    	    
    	    // UI
    	    extensions.addAll(asList(ExampleSedcatHtml.class, SedcatDashboardWidget.class));
    	    return extensions;
    	    
    }


}