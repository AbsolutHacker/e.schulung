package de.mathema.service.statistik;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.mathema.service.api.Person;
import de.mathema.service.api.PersonenVerwaltung;
import de.mathema.service.api.Religion;

/**
 * (Protentualer) Anteil der Religionen bei den Angestellten.
 */
@SuppressWarnings("javadoc")
public class _6_FilterReligionen {
	
	public static void main(String[] args) {
		List<Person> personenListe = PersonenVerwaltung.getPersonen(100_000);

		Map<Religion,Integer> marktanteile = new HashMap<>();
		int numberOfPersons = personenListe.size();
//		Map<Religion, List<Person>> peopleByReligion =
				personenListe.stream().collect(Collectors.groupingBy((p) -> p.getReligion()))
                .forEach((Religion r, List<Person> l) -> {
                    int v = l.size();
                    marktanteile.put(r, v);
                    System.out.println(r.toString() + ": " + percent(v, numberOfPersons) + "%");
                });
		;
	}
	
	/**
	 * Wandelt den gegebenen Quotienten in eine zweistellige 
	 * Prozentzahl mit höchstens einer Nachkommastelle um.
	 */
	public static String percent(int p, int n) {
		int x = p * 1000 / n;
		return Double.toString(x / 10.0);
	}
	
}
