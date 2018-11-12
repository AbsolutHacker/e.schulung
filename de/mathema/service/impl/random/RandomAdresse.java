
package de.mathema.service.impl.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.mathema.service.api.Adresse;
import de.mathema.service.impl.random.data.Data;

/**
 * Implementierung einer zufälligen Adresse.
 */
public class RandomAdresse implements Adresse {

	private final String plz;
	private final String stadt;
	
	private RandomAdresse(String plz, String stadt) {
		this.plz = plz;
		this.stadt = stadt;
	}
	
	@Override
	public String getPlz() {
		return plz;
	}

	@Override
	public String getStadt() {
		return stadt;
	}
	
	@Override
	public String getStrasse() {
		return "Unbekannte Straße";
	}

	@Override
	public String getHausnummer() {
		return "";
	}

	@Override
	public String getAdresszusatz() {
		return "";
	}

	@Override
	public String toString() {
		return plz + " " + stadt;
	}
	
	private static Random random = new Random(0x883833883AAAAL);
	
	/**
	 * Liefert den Inhalt einer PLZ-Ort-Liste in Form einer Liste von zweielementigen Arrays.
	 */
	private static List<String[]> loadNames() throws IOException {
		Class<?> c = Data.class;
		String path = c.getPackage().getName().replace('.', '/') + "/plz.txt";
		InputStream in = c.getClassLoader().getResourceAsStream(path);
		BufferedReader b = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		b.readLine();
		List<String[]> names = b.lines()
			.filter(s -> !s.isEmpty())
			.map(line -> line.split(";"))
			.collect(
				ArrayList<String[]>::new, 
				(a, name) -> a.add(name), 
				(a1, a2) -> a1.addAll(a2)
			)
			;		
		return names;
	}
	
	private static List<String[]> plz_liste;
	
	private static List<String[]> makePlzOrtListe() {
		try {
			return loadNames();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	/**
	 * Liefert eine Liste von Paaren, die die Postleitzahl und den Ortsnamen 
	 * enthalten.
	 */
	public static List<String[]> getPlzOrtListe() {
		if (plz_liste == null) {
			plz_liste = makePlzOrtListe();
		}
		return plz_liste;
	}
	
	private static Map<String, String> plz_map;
	
	private static Map<String, String> makePlzMap() {
		Map<String, String> plz_map = new HashMap<>();
		for (String[] s : getPlzOrtListe()) {
			plz_map.put(s[0], s[1]);
		}
		return plz_map;
	}
	
	/**
	 * Liefert eine Map, mit deren Hilfe man an den Ortsnamen einer
	 * Postleitzahl kommen kann.
	 */
	public static Map<String, String> getPlzMap() {
		if (plz_map == null) {
			plz_map = makePlzMap();
		}
		return plz_map;
	}
	
	private static final String ZEROS = "00000";
	
	/**
	 * Liefert eine zufällige Adresse.
	 */
	public static RandomAdresse randomAdresse() {
		Map<String, String> plz_map = getPlzMap();
		String plz;
		String stadt;
		do {
			plz = Integer.toString(random.nextInt(100000));
			int n = plz.length();
			if (n != 5) {
				plz = ZEROS.substring(n) + plz;
			}
			stadt = plz_map.get(plz);
		} while (stadt == null);
		return new RandomAdresse(plz, stadt);
	}
	
	/**
	 * Nur für Testzwecke.
	 */
	public static void main(String[] args) throws IOException {
		Map<String, String> plz_map = getPlzMap();
		System.out.println(plz_map.get("91052"));
		
		List<String[]> ortsliste = getPlzOrtListe();
		System.out.println(Arrays.toString(ortsliste.get(47301)));
		
		System.out.println(randomAdresse());
	}

}
