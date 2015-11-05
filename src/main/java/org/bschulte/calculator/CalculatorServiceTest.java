package org.bschulte.calculator;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorServiceTest {

	@Test
	public void testCalculate() throws UnknownQuestionException {
		CalculatorService service = new CalculatorService();
		Assert.assertEquals(CalculatorService.MSG_QUESTION_NOT_PROVIDED,service.calculate(""));
	}

	@Test
	public void testUnknownQuestion() {
		fail("Not yet implemented");
	}

}
