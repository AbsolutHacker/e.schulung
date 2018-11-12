
package de.mathema.service.statistik;

import java.math.BigInteger;
import java.util.List;

import de.mathema.service.api.Person;
import de.mathema.service.api.PersonenVerwaltung;

/**
 * Ausgabe der Anzahl fehlerhafter Kontoverbindungen.
 */
@SuppressWarnings("javadoc")
public class _8_FehlerhafteIBAN {

	/**
	 * Anzahl der zu durchsuchenden Datensätze.
	 */
	private static final int N = 1_000_000;
	
	/**
	 * Gibt an, ob die IBAN korrekt ist.
	 */
	private static boolean isValidIBAN(String iban) {
        iban = iban.substring(4) + iban.substring(0, 4);
        String convertedIBAN =
            iban.chars().collect(
                () -> new StringBuilder(),
                (sb, i) -> {
                    if ('0' <= i && i <= '9') {
                        sb.append(i - 48); // single digit numbers here
                    } else if('A' <= i && i <= 'Z') {
                        sb.append(i - 55); // single letters
                    }
                },
                (sbl, sbr) -> sbl.append(sbr)
        ).toString();
        BigInteger ibanNumber = new BigInteger(convertedIBAN);
        return ibanNumber.mod(BigInteger.valueOf(97L)).equals(BigInteger.ONE);
	}
	
	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		List<Person> personen = PersonenVerwaltung.getPersonen(N);
		long lapTime = System.currentTimeMillis();
		System.out.println("Vorbereitet nach ungefähr " + (lapTime - startTime) + " ms");
		startTime = System.currentTimeMillis();
		long fehlerhafteKonten = personen
			.stream().parallel()
			.map(p -> p.getBankverbindung().getIBAN())
			.filter(iban -> !isValidIBAN(iban))
			.peek(System.out::println)
			.count();
		long endTime = System.currentTimeMillis();
		System.out.println("Fertig nach weiteren ungefähr " + (endTime - startTime) + " ms");
		System.out.println();
        System.out.println("Fehlerhaft sind " + fehlerhafteKonten + " von " + N);
		System.out.printf("Etwa %.1f %% sind fehlerhaft!\n", fehlerhafteKonten * 100.0 / N);
	}
	
}
