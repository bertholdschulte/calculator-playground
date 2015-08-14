package org.bschulte.calculator.evaluator;

import java.util.Arrays;
import java.util.OptionalInt;

import org.springframework.stereotype.Component;

@Component
public class WhichOfLargestEvaluator implements QueryEvaluator  {

	@Override
	public String evaluate(String query){
		String[] numbers = query.split(",");
		OptionalInt max = Arrays.stream(numbers).map(String::trim).mapToInt(Integer::parseInt).max();
		return String.valueOf(max.getAsInt());
	}
	
	@Override
	public String getQueryType() {
		return  "which of the following numbers is the largest:";
	}
}
