package org.bschulte.calculator;

import org.junit.Assert;
import org.junit.Test;

/**
 * tests for {@link CalculationExecutor}
 * @author bschulte
 *
 */
public class CalculationExecutorTest {

	private static final String A_WHICH_OF_SQUARE_AND_A_CUBE_QUESTION = "which of the following numbers is both a square and a cube: 3, -8, -2, 64, 17";
	private static final String A_WHICH_OF_LARGEST_QUESTION = "which of the following numbers is the largest: 4, 34, 12, 3, 32, 123, 3";
	private static final String A_WHAT_IS_QUESTION = "what is 4 plus 24";
	
	@Test
	public void testCalculateWhatIs() throws Exception {
		CalculationExecutor classifier = new CalculationExecutor(); 
		String type = classifier.calculate(A_WHAT_IS_QUESTION);
		Assert.assertEquals("28", type);
	}

	@Test
	public void testCalculateWhichOfLargest() throws Exception {
		CalculationExecutor classifier = new CalculationExecutor(); 
		String type = classifier.calculate(A_WHICH_OF_LARGEST_QUESTION);
		Assert.assertEquals("123", type);
	}
	
	@Test
	public void testCalculateWhichOfSquareAndCube() throws Exception {
		CalculationExecutor classifier = new CalculationExecutor(); 
		String type = classifier.calculate(A_WHICH_OF_SQUARE_AND_A_CUBE_QUESTION);
		Assert.assertEquals("64", type);
	}
}
