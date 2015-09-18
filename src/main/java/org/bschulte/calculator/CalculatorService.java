package org.bschulte.calculator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger LOGGER = LogManager.getLogger(CalculatorService.class);
    
	@Autowired
	private CalculationExecutor executor;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String calculate(@RequestParam(name = "q", required = false) String query) throws UnknownQuestionException {
		if (StringUtils.isEmpty(query)) {
			return "Please provide a question in parameter: q";
		}
		String q = null;
		try {
			q = URLDecoder.decode(query,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.fatal(e);
		}
		return executor.calculate(q);
	}

	@ExceptionHandler(UnknownQuestionException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String unknownQuestion() {
		return "Sorry, I do not understand your question.";
	}
	
}
