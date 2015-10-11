package de.andrena.navitreffen.doubles;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.andrena.navitreffen.doubles.Doubles;

public class DoublesTest {
	
	@Test
	public void testDoubles() throws Exception {
		Doubles doubles = new Doubles();
		
		int dice1 = 1;
		int dice2 = 1;
		
		int score = doubles.threw(dice1, dice2);
		
		assertThat(score, is(1));
	}
}
