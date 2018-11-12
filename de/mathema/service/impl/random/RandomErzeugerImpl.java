
package de.mathema.service.impl.random;

import java.util.ArrayList;
import java.util.List;

import de.mathema.service.api.Person;
import de.mathema.service.api.PersonenErzeuger;

/**
 * Erzeugt zufällige Personen.
 */
public class RandomErzeugerImpl implements PersonenErzeuger {
	
	@Override
	public List<Person> erzeuge(int n) {
		List<Person> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(new RandomPerson());
		}
		return list;
	}
	
	/**
	 * Nur für Testzwecke.
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		List<Person> s = new RandomErzeugerImpl().erzeuge(100000);
		long end = System.currentTimeMillis();
		System.out.println((end - start) + " ms");
		s.stream().limit(10).forEach(System.out::println);
	}
}
