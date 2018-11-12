package de.mathema.service.statistik;

import java.util.*;
import java.util.stream.Collectors;

import de.mathema.service.api.Person;
import de.mathema.service.api.PersonenVerwaltung;

/**
 * Ausgabe des Alters aller Mitarbeiter.<br> 
 * Ausgabe des jüngsten und ältesten Mitarbeiters.<br>
 * Ausgabe des Mitarbeiters (oder der Mitarbeiter), die als nächstes Gebutstag haben.
 */
@SuppressWarnings("javadoc")
public class _7_AlterAllerAktuellenAngestellten {

	public static void main(String[] args) {
		List<ArbitraryPerson> personenListe = PersonenVerwaltung.getPersonen(100_000).stream()
                .map(p -> new ArbitraryPerson(p)).collect(Collectors.toList());
        System.out.println(personenListe.get(0).getClass());
//		personenListe.stream()
//            .peek((p) -> System.out.println(p.getNachname() + ", " + p.getVorname()
//                + ": "))

                // get age!!
	}

	static class MinMaxCollector<S extends Comparable<S>> {
	    S _1;
	    S _2;
        public void check(S s) {
	        if (_1 == null) {
	            _1 = s;
	            _2 = s;
            }
            if (s.compareTo(_1) < 0) {
                _1 = s;
            } else if (s.compareTo(_2) > 0) {
                _2 = s;
            }
        }
        public S getMin() {
	        return _1;
        }
        public S getMax() {
	        return _2;
        }
    }

//	static class Twopel<S extends Comparable<S>,T> implements Comparable<Twopel<S,T>> {
//	    S s;
//	    T t;
//	    public Twopel(S s, T t) {
//	        this.s = s;
//	        this.t = t;
//        }
//
//        @Override
//        public String toString() {
//	        return new StringBuilder("<").append(s).append(";").append(t).append(">").toString();
//        }
//
//        @Override
//        public int compareTo(Twopel<S,T> t) {
//	        return s.compareTo(t.s);
//        }
//    }

}
