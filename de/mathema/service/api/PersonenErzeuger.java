
package de.mathema.service.api;

import java.util.List;

/**
 * Erzeugt einen 
 */
public interface PersonenErzeuger {

	/**
	 * Erzeugt eine Liste mit der gegebenen Anzahl von Personen.
	 */
	List<Person> erzeuge(int n);

}
