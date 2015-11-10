package de.andrena.navitreffen.doubles.stufe4;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.andrena.navitreffen.doubles.beispielloesung.stufe4.Doubles;

public class DoublesTest {

	@Test
	public void letzterFehlenderPaschVerdoppeltPunkte() {
		Doubles doubles = new Doubles();

		int score = doubles.calculateScore(1, 1);
		assertThat(score, is(1));

		score = doubles.calculateScore(2, 2);
		assertThat(score, is(2));

		score = doubles.calculateScore(3, 3);
		assertThat(score, is(3));

		score = doubles.calculateScore(4, 4);
		assertThat(score, is(4));

		score = doubles.calculateScore(5, 5);
		assertThat(score, is(5));

		score = doubles.calculateScore(6, 6);
		assertThat(score, is(10));
	}

	@Test
	public void doppelterPaschGibtDoppeltePunktzahl() {
		Doubles doubles = new Doubles();

		int score = doubles.calculateScore(3, 3);
		assertThat(score, is(1));

		score = doubles.calculateScore(3, 3);
		assertThat(score, is(3));
	}

	@Test
	public void doppelterBinaerPaschGibtDoppeltePunktzahl() {
		Doubles doubles = new Doubles();

		int score = doubles.calculateScore(1, 2);
		assertThat(score, is(2));

		score = doubles.calculateScore(2, 1);
		assertThat(score, is(6));
	}

	@Test
	public void doppelPaschNurBeiAufeinanderfolgendenWürfen() {
		Doubles doubles = new Doubles();

		int score = doubles.calculateScore(2, 2);
		assertThat(score, is(1));

		score = doubles.calculateScore(2, 3);
		assertThat(score, is(1));

		score = doubles.calculateScore(2, 2);
		assertThat(score, is(2));
	}

	@Test
	public void binaerPaschIstZweiPunkteWert() {
		int score = new Doubles().calculateScore(1, 2);
		assertThat(score, is(2));

		score = new Doubles().calculateScore(2, 1);
		assertThat(score, is(2));
	}

	@Test
	public void keinePunkteBeiVerschiedenenAugenzahlen() {
		Doubles doubles = new Doubles();

		int score = doubles.calculateScore(1, 6);
		assertThat(score, is(0));

		score = doubles.calculateScore(6, 1);
		assertThat(score, is(0));
	}

	@Test
	public void paschsSindEinenPunktWert() throws Exception {
		int score = new Doubles().calculateScore(1, 1);
		assertThat(score, is(1));

		score = new Doubles().calculateScore(2, 2);
		assertThat(score, is(1));

		score = new Doubles().calculateScore(3, 3);
		assertThat(score, is(1));

		score = new Doubles().calculateScore(4, 4);
		assertThat(score, is(1));

		score = new Doubles().calculateScore(5, 5);
		assertThat(score, is(1));

		score = new Doubles().calculateScore(6, 6);
		assertThat(score, is(1));
	}

	@Test
	public void doublesZaehltPunkte() {
		Doubles doubles = new Doubles();

		int score = doubles.calculateScore(1, 1);
		assertThat(score, is(1));

		score = doubles.calculateScore(1, 6);
		assertThat(score, is(1));

		score = doubles.calculateScore(2, 2);
		assertThat(score, is(2));
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testAugenzahlenGroesser0() {
		Doubles doubles = new Doubles();

		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Ein Würfel hat nur Augenzahlen von 1 bis 6");

		doubles.calculateScore(0, 1);
	}

	@Test
	public void testAugenzahlenKleiner7() {
		Doubles doubles = new Doubles();

		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Ein Würfel hat nur Augenzahlen von 1 bis 6");

		doubles.calculateScore(6, 7);
	}

}
