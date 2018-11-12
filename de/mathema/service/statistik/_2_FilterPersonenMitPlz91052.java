package de.mathema.service.statistik;

import java.util.List;

import de.mathema.service.api.Person;
import de.mathema.service.api.PersonenVerwaltung;

/**
 * Alle Personen ausgeben, die im Postleitzahlbereich 91052 wohnen.
 */
@SuppressWarnings("javadoc")
public class _2_FilterPersonenMitPlz91052 {

	public static void main(String[] args) {
		List<Person> personenListe = PersonenVerwaltung.getPersonen(10_000);
		
		personenListe.stream()
			.filter((p) -> (p.getAdresse().getPlz().equals("91052")))
			.map((p) -> p.getNachname() + ", " + p.getVorname())
			.forEach(System.out::println);
	}
	
}
