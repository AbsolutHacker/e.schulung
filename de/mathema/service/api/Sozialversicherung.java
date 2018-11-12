
package de.mathema.service.api;

/**
 * Beschreibt sozialversicherungstechnische Daten.
 */
public interface Sozialversicherung {

	/**
	 * Beitragsbemessungsgrenze für die Krankenversicherung in Eurocent.
	 */
	public static int JAHRESBEMESSUNGSGRENZE_KRANKENKASSE = 5220000;
	
	/**
	 * Beitragsbemessungsgrenze für die Rentenversicherung in den westlichen Bundesländer in Eurocent.
	 */
	public static int JAHRESBEMESSUNGSGRENZE_RENTE_WEST = 7620000;
	
	/**
	 * Beitragsbemessungsgrenze für die Rentenversicherung in den östlichen Bundesländer in Eurocent.
	 */
	public static int JAHRESBEMESSUNGSGRENZE_RENTE_OST = 6840000;
	
	/**
	 * Liefert den Krankenkassenbeitrag in Zehntel-Prozent.
	 */
	public int getKrankenkassenBeitrag();

	/**
	 * Liefert den Arbeitslosenbeitrag in Zehntel-Prozent.
	 */
	public int getArbeitslosenBeitrag();
	
	/**
	 * Liefert den Rentenbeitrag in Zehntel-Prozent.
	 */
	public int getRentenBeitrag();
	
}
