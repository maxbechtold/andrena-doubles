package de.andrena.navitreffen.doubles.stufe7;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Doubles {

	private static final int MAX_AUGENZAHL = 6;
	private static final int MIN_AUGENZAHL = 1;

	private int score = 0;
	private int lastDice1;
	private int lastDice2;

	private List<Boolean> geworfenePaschs = Arrays.asList(FALSE, FALSE, FALSE, FALSE, FALSE, FALSE);
	private List<Integer> aufsteigendePaschs = new LinkedList<>();
	private int sechserPaschsInFolge = 0;

	private boolean binaerPaschGeworfen = false;

	public int threw(int dice1, int dice2) {
		checkAugenzahlen(dice1, dice2);

		int points = bestimmePunktzahl(dice1, dice2);
		berechneUndSpeichereErgebnis(dice1, dice2, points);
		return score;
	}

	private int bestimmePunktzahl(int dice1, int dice2) {
		int points = 0;
		if (isPasch(dice1, dice2)) {
			points = bewertePasch(dice1);
		}
		if (isBinaerPasch(dice1, dice2) || isBinaerPasch(dice2, dice1)) {
			points = bewerteBinaerPasch();
		}
		if (isDoppelPasch(dice1, dice2) || isDoppelPasch(dice2, dice1)) {
			points *= 2;
		}
		return points;
	}

	private int bewertePasch(int paschZahl) {
		speicherePasch(paschZahl);
		int points = 1;
		if (isLetzterFehlenderPasch()) {
			points = score;
		}
		if (isSechserPasch(paschZahl)) {
			sechserPaschsInFolge++;
		} else {
			sechserPaschsInFolge = 0;
		}
		return points;
	}

	private boolean isSechserPasch(int paschZahl) {
		return paschZahl == 6;
	}

	private int bewerteBinaerPasch() {
		int points = 2;
		if (!binaerPaschGeworfen && allePaschsGeworfen()) {
			points = score;
		}
		binaerPaschGeworfen = true;
		return points;
	}

	private boolean allePaschsGeworfen() {
		return anzahlFehlenderPaschs() == 0;
	}

	private long anzahlFehlenderPaschs() {
		return geworfenePaschs.stream().filter(b -> !b).count();
	}

	private void speicherePasch(int paschZahl) {
		geworfenePaschs.set(paschZahl - 1, TRUE);
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
		if (sechserPaschsInFolge >= 6) {
			score = 6;
		} else {
			score += points;
		}
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
