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
  @Property(key = SedcatConstants.SUCCESS_KEY, defaultValue = "true",
    name = "Metrica de entrada exito", description = "Indicar la ruta donde se hallan los reportes de exito", global = true,
    project = true),
  @Property(key = SedcatConstants.COVERAGE_KEY, defaultValue = "true",
    name = "Metrica de entrada cobertura", description = "Indicar la ruta en la que se hallan los reportes de cobertura generados por jacoco", global = true,
    project = true)
})
public class SedcatPlugin extends SonarPlugin {
	
	

    public List<Class<? extends Extension>> getExtensions() {
        return Arrays.asList(
        		
        		
        		SedcatMetrics.class,
        		SedcatSensor.class,
        		ExampleSedcatHtml.class,
//        		SedcatCoverageDecorator.class,
//        		App.class,
        		SedcatDashboardWidget.class
        		
        		);
    }
}