
package de.mathema.service.api;

import java.util.List;

import de.mathema.service.impl.random.RandomErzeugerImpl;

/**
 * Erlaubt den Zugriff auf alle im System verwalteten Angestellten. Dabei wird mit Hilfe
 * des <code>ServiceLoader</code>s ein beliebiger <code>PersonenErzeuger</code>
 * ausgewählt, der dann die Daten lädt und als <code>Stream</code> liefert.
 */
public class PersonenVerwaltung {

	static {
//		final ServiceLoader<PersonenErzeuger> serviceLoader = ServiceLoader.load(PersonenErzeuger.class);
//		personenErzeuger = serviceLoader.iterator().next();
		
//		personenErzeuger = new PersonenErzeugerImpl();
		personenErzeuger = new RandomErzeugerImpl();
	}

	/**
	 * Der für das Liefern der Personendaten verantwortliche Erzeuger.
	 */
	private final static PersonenErzeuger personenErzeuger;

	/**
	 * Liefert die Verwalteten Daten.
	 */
	public static List<Person> getPersonen(int n) {
		return personenErzeuger.erzeuge(n);
	}

}
