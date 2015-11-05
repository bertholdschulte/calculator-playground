package org.bschulte.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * tests for {@link CalculationExecutor}
 * 
 * @author bschulte
 *
 */
public class CalculationExecutorTest {

	private static final String A_WHICH_OF_SQUARE_AND_A_CUBE_QUESTION = "which of the following numbers is both a square and a cube: 3, -8, -2, 64, 17";
	private static final String A_WHICH_OF_LARGEST_QUESTION = "which of the following numbers is the largest: 4, 34, 12, 3, 32, 123, 3";
	private static final String A_WHAT_IS_QUESTION = "what is 4 plus 24";
	private CalculationExecutor classifier;

	@Before
	public void setup() {
		classifier = new CalculationExecutor();
	}
	@Test
	public void testCalculateWhatIs() throws Exception {
		String type = classifier.calculate(A_WHAT_IS_QUESTION);
		Assert.assertEquals("28", type);
	}

	@Test
	public void testCalculateWhichOfLargest() throws Exception {
		String type = classifier.calculate(A_WHICH_OF_LARGEST_QUESTION);
		Assert.assertEquals("123", type);
	}

	@Test
	public void testCalculateWhichOfSquareAndCube() throws Exception {
		String type = classifier.calculate(A_WHICH_OF_SQUARE_AND_A_CUBE_QUESTION);
		Assert.assertEquals("64", type);
	}

	@Test (expected=UnknownQuestionException.class)
	public void testQuestionNotKnown() throws Exception {
		classifier.calculate("questiontype notknown");
	}
	
	@Test (expected=UnknownQuestionException.class)
	public void testQuestionParameterNotFound() throws Exception {
		classifier.calculate("questiontypenotknown");
	}
}
