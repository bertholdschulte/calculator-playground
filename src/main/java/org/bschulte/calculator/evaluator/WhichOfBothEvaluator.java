package org.bschulte.calculator.evaluator;

import java.util.Arrays;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class WhichOfBothEvaluator implements QueryEvaluator  {
	
	@Override
	public String evaluate(String query){
		String[] numbers = query.split(",");
		return Arrays.stream(numbers).map(String::trim).mapToLong(Long::parseLong).filter(new LongPredicate() {
			
			@Override
			public boolean test(long value) {
				return isSquare(value) 
						&&  isCube(value);
			}

			private boolean isCube(long value) {
				return value > 0 && Double.valueOf(Math.pow(value,1/3)).equals(Double.valueOf(Math.floor(Math.pow(value,1/3)))) ;
			}

			private boolean isSquare(long value) {
				return value > 0 && Double.valueOf(Math.sqrt(value)).equals(Double.valueOf(Math.floor(Math.sqrt(value))));
			}
		}).mapToObj(String::valueOf).collect(Collectors.joining(", "));
		
	}
	
	@Override
	public String getQueryType() {
		return "which of the following numbers is both a square and a cube:";
	}
	
}
