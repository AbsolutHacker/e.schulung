package de.mathema.service.statistik;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import de.mathema.service.api.Person;
import de.mathema.service.api.PersonenVerwaltung;

/**
 * Durchschnittsalter aller aktuell angestellten Personen.
 */
@SuppressWarnings("javadoc")
public class A4 {

	public static void main(String[] args) {
	    double avg;
		List<Person> personenListe = PersonenVerwaltung.getPersonen(100_000);
		
		System.out.println("Durchschnittsalter in Tagen: " +
                (avg = personenListe.stream()
            .mapToLong(A4::personToAgeInDays)
			.average().getAsDouble()));
		System.out.println("Durchschnittsalter (in Jahren): " +
                (avg / 365.25));
		;
	}

	private static long personToAgeInDays(Person p) {
	    long daysSinceBirth = LocalDate.from(p.getGeburtsdatum().toInstant().atZone(ZoneId.systemDefault())).toEpochDay();
	    return (LocalDate.now().toEpochDay() - daysSinceBirth);
    }
}
