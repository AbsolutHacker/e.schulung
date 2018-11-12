package de.mathema.service.impl;

import de.mathema.service.api.Bankverbindung;

/**
 * Implementierung einer Bankverbindung.
 */
public class BankverbindungImpl implements Bankverbindung {

    private final String institutName;
    private final String iban;
    private final String bic;

    /**
     * Erzeugt eine Bankverbindung aus den gegebenen Komponenten.
     */
	public BankverbindungImpl(String institutName, String iban, String bic) {
		this.institutName = institutName;
		this.iban = iban;
		this.bic = bic;
	}

	@Override
	public String getInstitutName() {
		return this.institutName;
	}

	@Override
	public String getIBAN() {
		return this.iban;
	}

	@Override
	public String getBIC() {
		return this.bic;
	}

	@Override
	public String toString() {
		return iban + "(" + bic + "; " + institutName + ")";
	}

}
