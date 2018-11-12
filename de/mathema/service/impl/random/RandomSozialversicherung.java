package de.mathema.service.impl.random;

import de.mathema.service.api.Sozialversicherung;

/**
 * Implementierung einer zufälligen Sozialversicherung.
 */
public class RandomSozialversicherung implements Sozialversicherung {

	private final int beitrag;
	
	/**
	 * Erzeugt auf Basis der gegebenen Zufallszahl einen eine zufällige 
	 * Sozialversicherung.
	 */
	public RandomSozialversicherung(int random) {
		beitrag = 146 - 26 + random;
	}
	
	@Override
	public int getKrankenkassenBeitrag() {
		return beitrag;
	}

	@Override
	public int getArbeitslosenBeitrag() {
		return 30;
	}

	@Override
	public int getRentenBeitrag() {
		return 187;
	}

}
