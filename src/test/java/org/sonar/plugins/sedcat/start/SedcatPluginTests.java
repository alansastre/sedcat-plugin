package org.sonar.plugins.sedcat.start;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class SedcatPluginTests {


	/**
	 * Test method for {@link org.sonar.plugins.sedcat.start.SedcatPlugin#getExtensions()}.
	 */
	@Test
	public final void testGetExtensions() {

		String[] expectedExtensionPoints = new String[]{
					"SedcatMetrics",
					"MutationsReportFinder",
					"MutationsReportParser",
					"MutationsCoverageSensor",
					"ComplexityThresholdSensor",
					"ComplexityComputer",
					"GeneralComputer",
					"SedcatHtmlFooter",
					"SedcatDashboardWidget",

			};
		SedcatPlugin prueba1 = new SedcatPlugin();
		
		List<?> extensionPoints = prueba1.getExtensions();
		boolean checker = false;
		
		for (Object object : extensionPoints) {

			for (int i = 0; i < expectedExtensionPoints.length; i++) {
				if(object.toString().contains(expectedExtensionPoints[i])){
					checker = true;
					break;
				}
			}
			
			if(checker==true){
				checker=false;
			}else{
				
				//No se ha encontrado una clase
				fail("Falta la clase: "+ object.toString()); 
			}

		}
	}

	/**
	 * Test method for {@link org.sonar.plugins.sedcat.start.SedcatPlugin#getExtensions()}.
	 */
	@Test
	public final void testSedcatHtmlFooter() {
		SedcatHtmlFooter footer = new SedcatHtmlFooter();
		Assert.assertTrue(footer.getHtml().length() == 0);
	}
}
