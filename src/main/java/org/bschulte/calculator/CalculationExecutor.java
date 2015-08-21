package org.bschulte.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bschulte.calculator.evaluator.QueryEvaluator;
import org.bschulte.calculator.evaluator.WhatIsEvaluator;
import org.bschulte.calculator.evaluator.WhichOfBothEvaluator;
import org.bschulte.calculator.evaluator.WhichOfLargestEvaluator;
import org.springframework.stereotype.Component;

@Component
public class CalculationExecutor {


    private static final Logger logger = LogManager.getLogger(CalculationExecutor.class);
    
    private Map<String, QueryEvaluator> evaluators;

	public CalculationExecutor() {
		evaluators = new HashMap<String, QueryEvaluator>();
		evaluators.put(new WhatIsEvaluator().getQueryType(), new WhatIsEvaluator());
		evaluators.put(new WhichOfLargestEvaluator().getQueryType(), new WhichOfLargestEvaluator());
		evaluators.put(new WhichOfBothEvaluator().getQueryType(), new WhichOfBothEvaluator());
	}

	public String calculate(String query) throws UnknownQuestionException {
		
		Pattern p = Pattern.compile("(\\D+)\\s(.*)");
		Matcher matcher = p.matcher(query);
		matcher.find();
		if (!matcher.matches()) {
			throw new UnknownQuestionException();
		}
		String questionType = matcher.group(1);
		String questionValues = matcher.group(2);
		logger.debug("Question Type: {}", questionType);
		logger.debug("Question Value: {}",questionValues);
		if (evaluators.get(questionType) != null) {
			return evaluators.get(questionType).evaluate(questionValues);
		} else {
			throw new UnknownQuestionException();
		}
	}

}
