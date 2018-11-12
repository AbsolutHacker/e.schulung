package de.mathema.service.statistik;

import de.mathema.service.api.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class ArbitraryPerson implements Person {
    private final Person p;
    public ArbitraryPerson(Person p) {
        this.p = p;
    }

    public int getAlter() {
        return 0;
    }

    @Override
    public String getNachname() {
        return p.getNachname();
    }

    @Override
    public String getVorname() {
        return p.getVorname();
    }

    @Override
    public Date getGeburtsdatum() {
        return p.getGeburtsdatum();
    }

    @Override
    public Adresse getAdresse() {
        return p.getAdresse();
    }

    @Override
    public Geschlecht getGeschlecht() {
        return p.getGeschlecht();
    }

    @Override
    public Long getGehalt() {
        return p.getGehalt();
    }

    @Override
    public String getPersonalnummer() {
        return p.getPersonalnummer();
    }

    @Override
    public Set<Interesse> getInteressen() {
        return p.getInteressen();
    }

    @Override
    public Religion getReligion() {
        return p.getReligion();
    }

    @Override
    public int getGroesse() {
        return p.getGroesse();
    }

    @Override
    public int getGewicht() {
        return p.getGewicht();
    }

    @Override
    public LocalDate getEintrittsdatum() {
        return p.getEintrittsdatum();
    }

    @Override
    public LocalDate getAustrittsdatum() {
        return p.getAustrittsdatum();
    }

    @Override
    public Bankverbindung getBankverbindung() {
        return p.getBankverbindung();
    }

    @Override
    public Steuerklasse getSteuerklasse() {
        return p.getSteuerklasse();
    }

    @Override
    public Sozialversicherung getSozialversicherung() {
        return p.getSozialversicherung();
    }
}
