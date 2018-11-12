package de.mathema.service.statistik;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import de.mathema.service.api.Person;
import de.mathema.service.api.PersonenVerwaltung;

/**
 * Anzahl der Mitarbeiter pro Postleitzahlgebiet.<br>
 * Durchschnittsgehalt pro Postleitzahlgebiet.
 */
@SuppressWarnings("javadoc")
public class _5_GruppierenAllerPersonenNachPlz {
	
	public static void main(String[] args) {
		List<Person> personenListe = PersonenVerwaltung.getPersonen(10000);
		
		Map<Character,List<Person>> peopleByPostcodeInitial = personenListe.stream()
			.collect(Collectors.groupingBy((Person p) -> p.getAdresse().getPlz().charAt(0)));

        peopleByPostcodeInitial.forEach((Character plzg, List<Person> l) ->
        {
            System.out.println("PLZ-Gebiet " + plzg + "****");
            System.out.println("================");
            System.out.print("Personen# " + l.stream().count() + "\nGehaltsdurchschnitt: ");
            double s = l.stream().mapToDouble((p) -> p.getGehalt() / 100).average().getAsDouble();
            NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            System.out.println(nf.format(s) + " € \n\n");
        });
	}

}
