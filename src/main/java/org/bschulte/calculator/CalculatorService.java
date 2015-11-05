package org.bschulte.calculator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorService {

	private static final String MSG_QUESTION_ISSUE = "I cannot execute your question, please check your given operators and values.";

	private static final String MSG_QUESTION_NOT_KNOWN = "Sorry, I do not understand your question.";

	protected static final String MSG_QUESTION_NOT_PROVIDED = "Please provide a question in parameter: q";

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);
    
	@Autowired
	private CalculationExecutor executor;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String calculate(@RequestParam(name = "q", required = false) String query) throws UnknownQuestionException, CalculationException {
		if (StringUtils.isEmpty(query)) {
			return MSG_QUESTION_NOT_PROVIDED;
		}
		String q = null;
		try {
			q = URLDecoder.decode(query,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("URLDecoding", e);
		}
		return executor.calculate(q);
	}

	@ExceptionHandler(UnknownQuestionException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String unknownQuestion() {
		return MSG_QUESTION_NOT_KNOWN;
	}
	
	@ExceptionHandler(CalculationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String calculationIssue() {
		return MSG_QUESTION_ISSUE;
	}
	
}
