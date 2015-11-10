package de.andrena.navitreffen.doubles.beispielloesung.stufe2;

public class Doubles {

	private static final int MAX_AUGENZAHL = 6;
	private static final int MIN_AUGENZAHL = 1;

	private int score = 0;

	public int calculateScore(int dice1, int dice2) {
		checkAugenzahlen(dice1, dice2);
		if (isPasch(dice1, dice2)) {
			score += 1;
		}
		if (isBinaerPasch(dice1, dice2) || isBinaerPasch(dice2, dice1)) {
			score += 2;
		}
		return score;
	}

	private boolean isPasch(int dice1, int dice2) {
		return dice1 == dice2;
	}

	private boolean isBinaerPasch(int dice1, int dice2) {
		return dice1 == 1 && dice2 == 2;
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
