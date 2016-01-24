package es.unileon.sonarqube.sedcat.start;
import org.sonar.api.Extension;
import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.Description;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.UserRole;
import org.sonar.api.web.WidgetCategory;
import org.sonar.api.web.WidgetProperties;
import org.sonar.api.web.WidgetProperty;
import org.sonar.api.web.WidgetPropertyType;


/**
 * Sedcat plugin widget definition.
 *
 * @author alan.jesus	
 * @version 1.0
 */
@UserRole(UserRole.USER)
@Description("Shows Quality of software testing code")
@WidgetCategory("Sedcat")
//@WidgetProperties({
//  @WidgetProperty(key = "param1",
//    description = "This is a mandatory parameter",
//    optional = false
//  ),
//  @WidgetProperty(key = "max",
//    description = "max threshold",
//    type = WidgetPropertyType.INTEGER,
//    defaultValue = "80"
//  ),
//  @WidgetProperty(key = "param2",
//    description = "This is an optional parameter"
//  ),
//  @WidgetProperty(key = "floatprop",
//    description = "test description"
//  )
//})
public class SedcatDashboardWidget extends AbstractRubyTemplate implements RubyRailsWidget {


	private static final String TEMPLATE_PATH = "/root/workspace/tools.sonarqube.sedcat/src/main/resources";
	private static final String TEMPLATE_PATH_FILE = "/sedcat_widget.html.erb";
	

	public String getId() {

		return "sedcatPlugin";
	}

	public String getTitle() {

		return "Sedcat Plugin";
	}

	@Override
	protected String getTemplatePath() {


        // uncomment next line to enable change reloading during development
        String templatePath = TEMPLATE_PATH + TEMPLATE_PATH_FILE;
        return templatePath;

	}

}
