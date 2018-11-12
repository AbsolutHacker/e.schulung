package de.mathema.service.statistik;

import java.time.LocalDate;
import java.util.List;

import de.mathema.service.api.Person;
import de.mathema.service.api.PersonenVerwaltung;

/**
 * Ausgabe aller Personen, die zurzeit angestellt sind.
 */
@SuppressWarnings("javadoc")
public class A3 {

	public static void main(String[] args) {
		List<Person> personenListe = PersonenVerwaltung.getPersonen(10_000);
		
		personenListe.stream()
            .filter(A3::isEmployed)
            .map(A3::personToString)
            .forEach(System.out::println);
			// …
		;
	}

	private static boolean isEmployed(Person p) {
	    if (p.getEintrittsdatum() == null) return false;
	    LocalDate now = LocalDate.now();
	    return (
            !now.isBefore(p.getEintrittsdatum())
            && !now.isAfter(p.getAustrittsdatum())
        );
	}

	private static String personToString(Person p) {
	    return p.getNachname() + ", " + p.getVorname();
    }
}
