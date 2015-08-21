package org.bschulte.calculator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * Intgration tests to check request mapping
 * 
 * @author bschulte
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest()

public class CalculatorIntegrationTests {

	RestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	public void testApp() {
		ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:80?q=what%20is%2023%20plus%205%20multiplied%20by%2011", String.class);
		Assert.assertEquals("78",result.getBody());
	}
	
}
