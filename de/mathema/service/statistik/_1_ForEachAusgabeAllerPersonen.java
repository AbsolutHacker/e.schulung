package de.mathema.service.statistik;

import java.util.List;

import de.mathema.service.api.Person;
import de.mathema.service.api.PersonenVerwaltung;

/**
 * Ausgabe aller Personen in der Form „Nachname, Vorname“.
 */
@SuppressWarnings("javadoc")
public class _1_ForEachAusgabeAllerPersonen {

	public static void main(String[] args) {
		List<Person> personenListe = PersonenVerwaltung.getPersonen(10_000);
		
		personenListe.stream()
			.map((p) -> p.getNachname() + ", " + p.getVorname())
			.forEach(System.out::println);
	}

}
