package de.andrena.navitreffen.doubles.stufe1;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.andrena.navitreffen.doubles.beispielloesung.stufe1.Doubles;

public class DoublesTest {

	@Test
	public void keinePunkteBeiVerschiedenenAugenzahlen() {
		Doubles doubles = new Doubles();

		int score = doubles.threw(1, 6);
		assertThat(score, is(0));

		score = doubles.threw(6, 1);
		assertThat(score, is(0));
	}

	@Test
	public void testPaschsMitPunkteZaehlen() throws Exception {
		Doubles doubles = new Doubles();

		int score = doubles.threw(1, 1);
		assertThat(score, is(1));

		score = doubles.threw(2, 2);
		assertThat(score, is(2));

		score = doubles.threw(3, 3);
		assertThat(score, is(3));

		score = doubles.threw(4, 4);
		assertThat(score, is(4));

		score = doubles.threw(5, 5);
		assertThat(score, is(5));

		score = doubles.threw(6, 6);
		assertThat(score, is(6));
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testAugenzahlenGroesser0() {
		Doubles doubles = new Doubles();

		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Ein Würfel hat nur Augenzahlen von 1 bis 6");

		doubles.threw(0, 1);
	}

	@Test
	public void testAugenzahlenKleiner7() {
		Doubles doubles = new Doubles();

		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Ein Würfel hat nur Augenzahlen von 1 bis 6");

		doubles.threw(6, 7);
	}
}
