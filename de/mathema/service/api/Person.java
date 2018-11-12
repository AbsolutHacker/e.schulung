
package de.mathema.service.api;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/**
 * Beschreibt einen Angestellten und sämtliche über ihn verfügbare Daten. 
 */
public interface Person {

	/**
	 * Liefert den Nachnamen.
	 */
	public String getNachname();

	/**
	 * Liefert den Vornamen.
	 */
	public String getVorname();

	/**
	 * Liefert das Geburtsdatum.
	 */
	public Date getGeburtsdatum();

	/**
	 * Liefert die Adresse.
	 */
	public Adresse getAdresse();

	/**
	 * Liefert das Geschlecht.
	 */
	public Geschlecht getGeschlecht();

	/**
	 * Liefert das Gehalt in Euro Cent.
	 */
	public Long getGehalt();

	/**
	 * Liefert die Personalnummer.
	 */
	public String getPersonalnummer();

	/**
	 * Liefert die Interessen.
	 */
	public Set<Interesse> getInteressen();

	/**
	 * Liefert die Religionszugehörigkeit.
	 */
	public Religion getReligion();

	/**
	 * Liefert die Größe in Millimeter (mm).
	 */
	public int getGroesse();

	/**
	 * Liefert das Gewicht in Gramm (g).
	 */
	public int getGewicht();

	/**
	 * Liefert das Eintrittsdatum.
	 */
	public LocalDate getEintrittsdatum();

	/**
	 * Liefert das Austrittsdatum.
	 */
	public LocalDate getAustrittsdatum();

	/**
	 * Liefert die Bankverbindung.
	 */
	public Bankverbindung getBankverbindung();

	/**
	 * Liefert die Steuerklasse.
	 */
	public Steuerklasse getSteuerklasse();

	/**
	 * Liefert die sozialversicherungsrelevanten Informationen.
	 */
	public Sozialversicherung getSozialversicherung();

}
