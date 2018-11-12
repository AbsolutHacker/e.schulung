
package de.mathema.service.impl.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import de.mathema.service.impl.random.data.Data;

/**
 * Ein Name, der aus zwei Teilen, dem Vor- und dem Nachnamen, besteht.
 */
public class RandomName {

	private final boolean isFemale;
	private final String firstName;
	private final String lastName;
	
	/**
	 * Initialisiert einen Namen mit gegebenem Vor- und Nachnamen.
	 */
	public RandomName(String firstName, String lastName, boolean isFemale) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.isFemale = isFemale;
	}
	
	/**
	 * Liefert den Vornamen.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Liefert den Nachnamen.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Gibt an, ob es sich bei dem Namen um einen weiblichen (true) oder männlichen
	 * Namen (false) handelt. 
	 */
	public boolean isFemale() {
		return isFemale;
	}
	
	/**
	 * Liefert eine Textrepräsentation des Namens in der Form<br>
	 *     Hr. Nachname, Vorname  oder<br>
	 *     Fr. Nachname, Vorname 
	 */
	@Override
	public String toString() {
		return (isFemale ? "Fr. " : "Hr. ") + lastName + ", " + firstName;
	}
	

	private static List<String> femaleFirstNames;
	private static List<String> maleFirstNames;
	private static List<String> lastNames;
	
	/**
	 * Liefert den Inhalt einer "Namensdatei" in Form eines String-Liste.
	 */
	public static List<String> getNames(String file_name) throws IOException {
		Class<?> c = Data.class;
		String path = c.getPackage().getName().replace('.', '/') + "/" + file_name;
		InputStream in = c.getClassLoader().getResourceAsStream(path);
		if (in == null) {
			throw new Error("Can not load " + path);
		}
		BufferedReader b = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		b.readLine();
		List<String> names = b.lines()
			.filter(s -> !s.isEmpty())
			.map(line -> line.split(";")[0])
			.collect(
				ArrayList<String>::new, 
				(a, name) -> a.add(name), 
				(a1, a2) -> a1.addAll(a2)
			)
			;		
		return names;
	}
	
	/**
	 * Liefert die Liste der verfügbaren Nachnamen.
	 */
	public static List<String> getLastNames() throws IOException {
		if (lastNames == null) {
			lastNames = getNames("nachnamen.txt");
		}
		return lastNames;
	}
	
	/**
	 * Liefert die Liste der verfügbaren Vornamen.
	 */
	public static List<String> getMaleFirstNames() throws IOException {
		if (maleFirstNames == null) {
			maleFirstNames = getNames("vornamen.txt");
		}
		return maleFirstNames;
	}

	/**
	 * Liefert die Liste der verfügbaren Vornamen.
	 */
	public static List<String> getFemaleFirstNames() throws IOException {
		if (femaleFirstNames == null) {
			femaleFirstNames = getNames("wornamen.txt");
		}
		return femaleFirstNames;
	}

	/**
	 * Liefert eine reproduzierbare, pseudo-zufällige Sequenz von Namen.
	 */
	public static Supplier<RandomName> getRandomNameSupplier() throws IOException {
		return getRandomNameSupplier(0xC0DE_BABE_C0DE_C00L);
	}

	/**
	 * Liefert eine zufällige Sequenz von Namen, die auf Pseudozufallszahlen
	 * basieren, die auf dem gegebenen Startwert basieren.
	 */
	@SuppressWarnings("hiding")
	public static Supplier<RandomName> getRandomNameSupplier(final long seed) throws IOException {
		
		return new Supplier<RandomName>() {

			private final Random random;
			private final List<String> maleFirstNames;
			private final List<String> femaleFirstNames;
			private final List<String> lastNames;
			
			{
				random = new Random(seed);
				maleFirstNames = getMaleFirstNames();
				femaleFirstNames = getFemaleFirstNames();
				lastNames = getLastNames();
			}
			
			private String getRandomName(List<String> names) {
				int n = names.size();
				int i = random.nextInt(n);
				return names.get(i);
			}
			
			private String getRandomMaleFirstName() {
				return getRandomName(maleFirstNames);
			}
			
			private String getRandomFemaleFirstName() {
				return getRandomName(femaleFirstNames);
			}
			
			private String getRandomLastName() {
				return getRandomName(lastNames);
			}
			
			@Override
			public RandomName get() {
				boolean isFemale = random.nextInt(100) < 30;
				String firstName = (isFemale ?
					getRandomFemaleFirstName() : getRandomMaleFirstName()
				);
				String lastName = getRandomLastName();
				return new RandomName(firstName, lastName, isFemale);
			}
		};
	}
	
	/**
	 * Liefert den ersten Buchstaben eines Namens als Zeichenkette, 
	 * wenn dieser einen von den Buchstaben des Alphabets (‘A’ bis ‘Z’)
	 * ist, andernfalls die Zeichenkette “Other”. 
	 */
	public static String sortByAlphabet(RandomName n) {
		String firstLetter = n.getLastName().toUpperCase().substring(0, 1);
		char c = firstLetter.charAt(0);
		if ('A' <= c && c <= 'Z') {
			return firstLetter;
		} else {
			return "Other";
		}
	}

	/**
	 * Nur für Testzwecke.
	 */
	public static void main(String[] args) throws IOException {
		Stream<RandomName> names = Stream.generate(getRandomNameSupplier());
		names.limit(100).forEach(System.out::println);			
	}
	
}
