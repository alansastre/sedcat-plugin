package es.unileon.sonarqube.sedcat.start;
import org.sonar.api.web.Footer;


public class ExampleSedcatHtml implements Footer {

	  public String getHtml() {
	    return "<p>Sedcat Plugin - <em>Alan Corporation</em></p>";
	  }
}


