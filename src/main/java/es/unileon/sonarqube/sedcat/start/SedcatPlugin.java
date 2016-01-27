package es.unileon.sonarqube.sedcat.start;



import java.util.Arrays;
import java.util.List;

import org.sonar.api.Extension;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;


/**
 * This class is the entry point for all Sedcat extensions
 */
@SuppressWarnings("unchecked")
@Properties({
  @Property(key = SedcatConstants.SUCCESS_KEY, defaultValue = "/target/success.txt",
    name = "Input variable Unit Tests Success", description = "Indicate path to reports of Unit Tests Success.", global = true,
    project = true),
  @Property(key = SedcatConstants.COVERAGE_KEY, defaultValue = "/target/coverage.txt",
    name = "Input variable Unit Tests Coverage", description = "Indicate path to reports of Unit Tests Coverage.", global = true,
    project = true),
  @Property(key = SedcatConstants.MUTANTS_KEY, defaultValue = "/target/mutations.txt",
  name = "Input variable Mutations Coverage", description = "Indicate path to reports of Mutations Coverage.", global = true,
  project = true),
  @Property(key = SedcatConstants.NUMBER_TESTS_KEY, defaultValue = "/target/numbertests.txt",
  name = "Input variable Number Of Tests", description = "Indicate path to reports of Number Of Tests.", global = true,
  project = true),
  @Property(key = SedcatConstants.NUMBER_CODE_LINES_KEY, defaultValue = "/target/numbercodelines.txt",
  name = "Input variable Lines Of Code", description = "Indicate path to reports of Lines Of Code.", global = true,
  project = true),
  
})
public class SedcatPlugin extends SonarPlugin {
	
	

    public List<Class<? extends Extension>> getExtensions() {
        return Arrays.asList(
        		
        		
        		SedcatMetrics.class,
        		SedcatSensor.class,
        		ExampleSedcatHtml.class,
        		SedcatDashboardWidget.class
        		
        		);
    }
}