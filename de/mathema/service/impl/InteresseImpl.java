package de.mathema.service.impl;

import de.mathema.service.api.Interesse;

/**
 * Implementierung eines Interesses.
 */
public class InteresseImpl implements Interesse {
	
	private final String name;
	private final String beschreibung;

    /**
     * Erzeugt ein Interesse mit dem gegebenen Namen.
     */
	public InteresseImpl(String name) {
		this(name, null);
	}

    /**
     * Erzeugt ein Interesse mit dem gegebenen Namen und der
     * gewünschten Beschreibung.
     */
	public InteresseImpl(String name, String beschreibung) {
		this.name = name;
		this.beschreibung = beschreibung;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getBeschreibung() {
		return this.beschreibung;
	}

	@Override
	public String toString() {
		return name + " (" + beschreibung + ")"; 
	}

}
