package de.andrena.navitreffen.doubles.beispielloesung.stufe4;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Arrays;
import java.util.List;

public class Doubles {

	private static final int MAX_AUGENZAHL = 6;
	private static final int MIN_AUGENZAHL = 1;

	private int score = 0;
	private int lastDice1;
	private int lastDice2;

	private List<Boolean> geworfenePaschs = Arrays.asList(FALSE, FALSE, FALSE, FALSE, FALSE, FALSE);

	public int calculateScore(int dice1, int dice2) {
		checkAugenzahlen(dice1, dice2);

		int points = bestimmePunktzahl(dice1, dice2);
		berechneUndSpeichereErgebnis(dice1, dice2, points);
		return score;
	}

	private int bestimmePunktzahl(int dice1, int dice2) {
		int points = 0;
		if (isPasch(dice1, dice2)) {
			points = 1;
			speicherePasch(dice1);
			if (isLetzterFehlenderPasch()) {
				points = score;
			}
		}
		if (isBinaerPasch(dice1, dice2) || isBinaerPasch(dice2, dice1)) {
			points = 2;
		}
		if (isDoppelPasch(dice1, dice2) || isDoppelPasch(dice2, dice1)) {
			points *= 2;
		}
		return points;
	}

	private void speicherePasch(int paschZahl) {
		int index = paschZahl - 1;
		geworfenePaschs.set(index, TRUE);
	}

	private boolean isLetzterFehlenderPasch() {
		return !geworfenePaschs.contains(FALSE);
	}

	private boolean isDoppelPasch(int dice1, int dice2) {
		return lastDice1 == dice1 && lastDice2 == dice2;
	}

	private boolean isPasch(int dice1, int dice2) {
		return dice1 == dice2;
	}

	private boolean isBinaerPasch(int dice1, int dice2) {
		return dice1 == 1 && dice2 == 2;
	}

	private void berechneUndSpeichereErgebnis(int dice1, int dice2, int points) {
		lastDice1 = dice1;
		lastDice2 = dice2;
		score += points;
	}

	private void checkAugenzahlen(int dice1, int dice2) {
		String fehler = "Ein W�rfel hat nur Augenzahlen von %d bis %d";
		if (dice1 < MIN_AUGENZAHL || dice2 < MIN_AUGENZAHL) {
			throw new IllegalArgumentException(String.format(fehler, MIN_AUGENZAHL, MAX_AUGENZAHL));
		}

		if (dice1 > MAX_AUGENZAHL || dice2 > MAX_AUGENZAHL) {
			throw new IllegalArgumentException(String.format(fehler, MIN_AUGENZAHL, MAX_AUGENZAHL));
		}
	}
}
