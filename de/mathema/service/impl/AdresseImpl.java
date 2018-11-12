package de.mathema.service.impl;

import de.mathema.service.api.Adresse;

/**
 * Implementierung einer Adresse.
 */
public class AdresseImpl implements Adresse {

    private final String strasse;
    private final String hausnummer;
    private final String adresszusatz;
    private final String plz;
    private final String stadt;

    /**
     * Erzeugt eine Adresse aus den gegebenen Komponenten.
     */
    public AdresseImpl(String strasse, String hausnummer, String adresszusatz, String plz, String stadt) {
        super();
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.adresszusatz = adresszusatz;
        this.plz = plz;
        this.stadt = stadt;
    }

    @Override
    public String getStrasse() {
        return this.strasse;
    }

    @Override
    public String getHausnummer() {
        return this.hausnummer;
    }

    @Override
    public String getAdresszusatz() {
        return this.adresszusatz;
    }

    @Override
    public String getPlz() {
        return this.plz;
    }

    @Override
    public String getStadt() {
        return this.stadt;
    }

    @Override
    public String toString() {
        return strasse + " " + hausnummer + "; " + adresszusatz + "; " + plz + " " + stadt;
    }

}
