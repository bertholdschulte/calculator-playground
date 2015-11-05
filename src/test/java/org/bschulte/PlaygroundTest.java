package org.bschulte;

import org.junit.Assert;
import org.junit.Test;

public class PlaygroundTest {

	public class Calci {

		public int frac(int i) {
			int s = 1;
			for (int j=1;j<i+1;j++){
				s = s *j;
			}
			return s;
		}

	}

	@Test
	public void test() {
		Calci c = new Calci();
		Assert.assertEquals(120, c.frac(5));
	}

}
