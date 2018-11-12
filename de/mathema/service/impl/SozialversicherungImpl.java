package de.mathema.service.impl;

import de.mathema.service.api.Sozialversicherung;

/**
 * Implementierung der Sozialversicherung.
 */
public class SozialversicherungImpl implements Sozialversicherung {

    @Override
    public String toString() {
        return (
        	"KV " + getKrankenkassenBeitrag() + ", " +
        	"AV" + getArbeitslosenBeitrag() + ", " + 
        	"RV" + getRentenBeitrag()
        );
    }

	@Override
	public int getKrankenkassenBeitrag() {
		return 146;
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
