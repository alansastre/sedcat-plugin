package es.unileon.sonarqube.sedcat.start;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class SedcatPluginTests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}


	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.start.SedcatPlugin#getExtensions()}.
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
					"ExampleSedcatHtml",
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

}
