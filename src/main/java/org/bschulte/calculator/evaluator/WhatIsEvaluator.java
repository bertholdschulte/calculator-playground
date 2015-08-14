package org.bschulte.calculator.evaluator;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

@Component
public class WhatIsEvaluator implements QueryEvaluator  {

	ExpressionParser parser = new SpelExpressionParser();

	@Override
	public String evaluate(String query){
		return expression(query.replace("plus", "+").replace("multiplied by", "*"));
	}
	
	@Override
	public String getQueryType() {
		return "what is";
	}
	
	private String expression(String expression) {
		return (String)parser.parseExpression(expression).getValue(String.class);
	}
}
