package de.andrena.navitreffen.doubles.beispielloesung.stufe1;

public class Doubles {

	private static final int MAX_AUGENZAHL = 6;
	private static final int MIN_AUGENZAHL = 1;

	private int score = 0;

	public int threw(int dice1, int dice2) {
		checkAugenzahlen(dice1, dice2);
		if (dice1 == dice2) {
			score++;
		}
		return score;
	}

	private void checkAugenzahlen(int dice1, int dice2) {
		String fehler = "Ein Würfel hat nur Augenzahlen von %d bis %d";
		if (dice1 < MIN_AUGENZAHL || dice2 < MIN_AUGENZAHL) {
			throw new IllegalArgumentException(String.format(fehler, MIN_AUGENZAHL, MAX_AUGENZAHL));
		}

		if (dice1 > MAX_AUGENZAHL || dice2 > MAX_AUGENZAHL) {
			throw new IllegalArgumentException(String.format(fehler, MIN_AUGENZAHL, MAX_AUGENZAHL));
		}
	}

}
