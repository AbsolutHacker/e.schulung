
package de.mathema.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import de.mathema.service.api.Adresse;
import de.mathema.service.api.Bankverbindung;
import de.mathema.service.api.Geschlecht;
import de.mathema.service.api.Interesse;
import de.mathema.service.api.Person;
import de.mathema.service.api.Religion;
import de.mathema.service.api.Sozialversicherung;
import de.mathema.service.api.Steuerklasse;

/**
 * Implementierung einer Person.
 */
public class PersonImpl implements Person {

	private final String nachname;
	private final String vorname;
	private final Date geburtsdatum;
	private final Adresse adresse;
	private final Geschlecht geschlecht;
	private final Long gehalt;
	private final String personalnummer;
	private final Set<Interesse> interessen;
	private final Religion religion;
	private final int groesse;
	private final int gewicht;
	private final LocalDate eintrittsdatum;
	private final LocalDate austrittsdatum;
	private final Bankverbindung bankverbindung;
	private final Steuerklasse steuerklasse;
	private final Sozialversicherung sozialversicherung;

    /**
     * Erzeugt eine Person aus den gegebenen Komponenten.
     */
	public PersonImpl(
		String nachname, 
		String vorname, 
		Date geburtsdatum, 
		Adresse adresse, 
		Geschlecht geschlecht,
		Long gehalt, 
		String personalnummer, 
		Set<Interesse> interessen, 
		Religion religion, 
		int groesse, 
		int gewicht,
		LocalDate eintrittsdatum, 
		LocalDate austrittsdatum, 
		Bankverbindung bankverbindung,
		Steuerklasse steuerklasse, 
		Sozialversicherung sozialversicherung
	) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.geburtsdatum = geburtsdatum;
		this.adresse = adresse;
		this.geschlecht = geschlecht;
		this.gehalt = gehalt;
		this.personalnummer = personalnummer;
		this.interessen = interessen;
		this.religion = religion;
		this.groesse = groesse;
		this.gewicht = gewicht;
		this.eintrittsdatum = eintrittsdatum;
		this.austrittsdatum = austrittsdatum;
		this.bankverbindung = bankverbindung;
		this.steuerklasse = steuerklasse;
		this.sozialversicherung = sozialversicherung;
	}

	@Override
	public String getNachname() {
		return this.nachname;
	}

	@Override
	public String getVorname() {
		return this.vorname;
	}

	@Override
	public Date getGeburtsdatum() {
		return this.geburtsdatum;
	}

	@Override
	public Adresse getAdresse() {
		return this.adresse;
	}

	@Override
	public Geschlecht getGeschlecht() {
		return this.geschlecht;
	}

	@Override
	public Long getGehalt() {
		return this.gehalt;
	}

	@Override
	public String getPersonalnummer() {
		return this.personalnummer;
	}

	@Override
	public Set<Interesse> getInteressen() {
		return this.interessen;
	}

	@Override
	public Religion getReligion() {
		return this.religion;
	}

	@Override
	public int getGroesse() {
		return this.groesse;
	}

	@Override
	public int getGewicht() {
		return this.gewicht;
	}

	@Override
	public LocalDate getEintrittsdatum() {
		return this.eintrittsdatum;
	}

	@Override
	public LocalDate getAustrittsdatum() {
		return this.austrittsdatum;
	}

	@Override
	public Bankverbindung getBankverbindung() {
		return this.bankverbindung;
	}

	@Override
	public Steuerklasse getSteuerklasse() {
		return this.steuerklasse;
	}

	@Override
	public Sozialversicherung getSozialversicherung() {
		return this.sozialversicherung;
	}

	@Override
	public String toString() {
		return vorname + " " + nachname;
	}

}
