package es.unileon.sonarqube.sedcat.start;
import org.sonar.api.web.Footer;

/**
 * Clase que muestra un pie de pagina personalizado en el centro de configuracion sonarqube
 *	@author alan.sastre
 *	@version 1.0
 */
public class SedcatHtmlFooter implements Footer {

	@Override
	public String getHtml() {
		return "<p>Sedcat Plugin - <em>Sedcat Plugin for Quality Testing &copy; All rights reserved. 2016</em></p>";
	}
}


