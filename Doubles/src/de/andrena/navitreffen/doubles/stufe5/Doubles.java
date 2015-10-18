package de.andrena.navitreffen.doubles.stufe5;

import java.util.LinkedList;
import java.util.List;

public class Doubles {

	private static final int MAX_AUGENZAHL = 6;
	private static final int MIN_AUGENZAHL = 1;

	private int score = 0;
	private int lastDice1;
	private int lastDice2;
	private List<Integer> aufsteigendePaschs = new LinkedList<>();

	public int threw(int dice1, int dice2) {
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
		if (isNaechsterFehlenderPasch(paschZahl)) {
			aufsteigendePaschs.add(Integer.valueOf(paschZahl));
		}
	}

	private boolean isNaechsterFehlenderPasch(int paschZahl) {
		return aufsteigendePaschs.size() == paschZahl - 1;
	}

	private boolean isLetzterFehlenderPasch() {
		return aufsteigendePaschs.stream().mapToInt((i -> i)).sum() == 21;
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
		String fehler = "Ein Würfel hat nur Augenzahlen von %d bis %d";
		if (dice1 < MIN_AUGENZAHL || dice2 < MIN_AUGENZAHL) {
			throw new IllegalArgumentException(String.format(fehler, MIN_AUGENZAHL, MAX_AUGENZAHL));
		}

		if (dice1 > MAX_AUGENZAHL || dice2 > MAX_AUGENZAHL) {
			throw new IllegalArgumentException(String.format(fehler, MIN_AUGENZAHL, MAX_AUGENZAHL));
		}
	}
}