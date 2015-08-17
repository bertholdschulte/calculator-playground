package org.bschulte.calculator;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorService {

	@Autowired
	private CalculationExecutor classifier;

	@RequestMapping("/")
	String home(@RequestParam(name = "q", required = true, defaultValue = "") String query) throws UnknownQuestionException {
		if (StringUtils.isEmpty(query)) {
			return "Please provide a question in parameter: q";
		}
		String q = null;
		try {
			q = URLDecoder.decode(query,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			
		}
		return classifier.calculate(q);
	}

	@ExceptionHandler(UnknownQuestionException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String general() {
		return "Sorry, I do not understand your question.";
	}

}
