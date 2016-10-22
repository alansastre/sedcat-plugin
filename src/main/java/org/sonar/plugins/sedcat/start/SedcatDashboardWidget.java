package org.sonar.plugins.sedcat.start;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.Description;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.UserRole;
import org.sonar.api.web.WidgetCategory;


/**
 * Sedcat plugin widget definition.
 *
 * @author alan.sastre	
 * @version 1.0.0
 */
@UserRole(UserRole.USER)
@Description("Shows Quality of software testing code")
@WidgetCategory("Sedcat")
public class SedcatDashboardWidget extends AbstractRubyTemplate implements RubyRailsWidget {


	private static final String TEMPLATE_PATH = "/root/workspace/sonar-sedcat-plugin/src/main/resources";
	private static final String TEMPLATE_PATH_FILE = "/sedcat_widget.html.erb";

	@Override
	public String getId() {

		return "sedcatPlugin";
	}

	@Override
	public String getTitle() {

		return "Sedcat Plugin";
	}

	@Override
	protected String getTemplatePath() {

		// uncomment next line to enable change reloading during development
		return TEMPLATE_PATH + TEMPLATE_PATH_FILE;

	}

}
