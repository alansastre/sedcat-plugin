package es.unileon.sonarqube.sedcat.xfuzzy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unileon.sonarqube.sedcat.xfuzzy.actions.Actions;

/**
 * @author alan.sastre
 * @version 1.0
 */
public class ActionsTests {

	private Actions underTest;
	double[] inputMetricsValues;
	
	//datos para los posibles casos
	private double[] INPUT_METRIC_VALUES_0 = new double[] { 0, 0, 0, 0, 0, 0 };
	private double[] INPUT_METRIC_VALUES_12 = new double[] { 100, 100, 5000, 100, 10000, 15 };
	private double[] INPUT_METRIC_VALUES_45 = new double[] { 100, 100, 5000, 100, 10000, 35 };

	private double[] INPUT_METRIC_VALUES_10 = new double[] { 100, 65, 46, 0.0, 516, 15 };
	private double[] INPUT_METRIC_VALUES_20 = new double[] { 100, 72.7, 46, 0.0, 516, 15 };

	private double[] INPUT_METRIC_VALUES_32 = new double[] { 50, 50, 3000, 100, 100000, 30 };
	private double[] INPUT_METRIC_VALUES_65 = new double[] { 50, 50, 3000, 100, 100000, 30.6 };

	private double[] INPUT_METRIC_VALUES_0_297 = new double[] { 70, 94, 600, 82, 14000, 13.8 };
	private double[] INPUT_METRIC_VALUES_23_298 = new double[] { 71, 94, 600, 82, 14000, 13.8 };

	private double[] INPUT_METRIC_VALUES_30 = new double[] { 100, 65, 5000, 100, 10000, 15 };

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

		underTest = new Actions();
		

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public final void testActionSet_0() {
		
//		sedcat.actions.set0=Unit Test Success > Unit Test Coverage > Mutations coverage > Number Of Tests
		Assert.assertEquals(0.0, underTest.crispInference(INPUT_METRIC_VALUES_0)[0], 0.0);
		
	}
	
	
	@Test
	public final void testActionSet_12() {
		
		//OPTIMAL CASE 
		
		//sedcat.actions.set12=Maximum quality reached, no possible improvements
		Assert.assertEquals(12.0, underTest.crispInference(INPUT_METRIC_VALUES_12)[0], 0.0);
		
	}
	
	@Test
	public final void testActionSet_45() {
		
		//sedcat.actions.set45=Complexity
		Assert.assertEquals(45.0, underTest.crispInference(INPUT_METRIC_VALUES_45)[0], 0.0);
		
	}
	
	@Test
	public final void testActionSet_10() {
		
//		sedcat.actions.set10=Unit Test Coverage > Mutations coverage >  Number Of Tests
		Assert.assertEquals(10.0, underTest.crispInference(INPUT_METRIC_VALUES_10)[0], 0.0);
		
	}
	
	@Test
	public final void testActionSet_20() {
		
//		sedcat.actions.set20=Mutations coverage > Unit Test Coverage > Number Of Tests
		Assert.assertEquals(20.0, underTest.crispInference(INPUT_METRIC_VALUES_20)[0], 0.0);
		
	}

	@Test
	public final void testActionSet_32() {


//		sedcat.actions.set32=Unit Test Success > Unit Test Coverage > Number Of Tests
		Assert.assertEquals(32.0, underTest.crispInference(INPUT_METRIC_VALUES_32)[0], 0.0);

	}
	
	@Test
	public final void testActionSet_65() {


//		sedcat.actions.set65=Complexity > Unit Test Success > Unit Test Coverage > Number Of Tests
		Assert.assertEquals(65.0, underTest.crispInference(INPUT_METRIC_VALUES_65)[0], 0.0);

	}
	
	@Test
	public final void testActionSet_0_Rule297() {
		
//		sedcat.actions.set0=Unit Test Success > Unit Test Coverage > Mutations coverage > Number Of Tests
		Assert.assertEquals(0.0, underTest.crispInference(INPUT_METRIC_VALUES_0_297)[0], 0.0);
		
	}
	
	@Test
	public final void testActionSet_23_Rule298() {
		
//		sedcat.actions.set23=Mutations coverage > Unit Test Success > Unit Test Coverage > Number Of Tests
		Assert.assertEquals(23.0, underTest.crispInference(INPUT_METRIC_VALUES_23_298)[0], 0.0);
		
	}
	
	
	@Test
	public final void testActionSet_30() {
		
//		sedcat.actions.set30=Unit Test Coverage
		Assert.assertEquals(30.0, underTest.crispInference(INPUT_METRIC_VALUES_30)[0], 0.0);
		
	}
	
	


}
