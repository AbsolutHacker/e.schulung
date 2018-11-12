
package de.mathema.service.impl.random;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

import de.mathema.service.api.Adresse;
import de.mathema.service.api.Bankverbindung;
import de.mathema.service.api.Geschlecht;
import de.mathema.service.api.Interesse;
import de.mathema.service.api.Person;
import de.mathema.service.api.Religion;
import de.mathema.service.api.Sozialversicherung;
import de.mathema.service.api.Steuerklasse;

/**
 * Implementierung einer zufälligen Person.
 */
public class RandomPerson implements Person {

	private final static Random random = new Random(0xABCFECDDFADDL);
	
	private final static Supplier<RandomName> name_supplier;
	
	private static long counter = 517633;
	
	static {
		Supplier<RandomName> ns = null;
		try {
			ns = RandomName.getRandomNameSupplier();
		} catch (IOException e) {
			e.printStackTrace();
		}
		name_supplier = ns;
	}
	
	private final String nachname;
	private final String vorname;
	private final Date geburtsdatum;
	private final Adresse adresse;
	private final Geschlecht geschlecht;
	private final Long gehalt;
	private final String personalnummer;
	private final Set<Interesse> interessen;
	private final Religion religion;
	private final int groesse;
	private final int gewicht;
	private final LocalDate eintrittsdatum;
	private final LocalDate austrittsdatum;
	private final Bankverbindung bankverbindung;
	private final Steuerklasse steuerklasse;
	private final Sozialversicherung sozialversicherung;

	/**
	 * Erzeugt eine zufällige Person.
	 */
	public RandomPerson() {
		RandomName name = name_supplier.get();
		this.nachname = name.getLastName();
		this.vorname = name.getFirstName();
		this.geschlecht = name.isFemale() ? Geschlecht.WEIBLICH : Geschlecht.MAENNLICH;
		this.geburtsdatum = randomGeburtsdatum();
		this.gehalt = randomGehalt(geburtsdatum);
		this.adresse = RandomAdresse.randomAdresse();
		this.personalnummer = Long.toString(counter++);
		this.interessen = randomInteresse();
		this.religion = randomReligion();
		this.groesse = randomGroesse(geschlecht);
		this.gewicht = randomGewicht(groesse);
		LocalDate gd = geburtsdatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.eintrittsdatum = makeEintrittsdatum(gd);
		this.austrittsdatum = makeAustrittsdatum(eintrittsdatum, gd);
		this.bankverbindung = new RandomBankverbindung();
		this.steuerklasse = Steuerklasse.I;
		this.sozialversicherung = new RandomSozialversicherung(random.nextInt(52));
	}

	private static LocalDate makeEintrittsdatum(LocalDate gd) {
		if (random.nextBoolean()) {
			return null;
		}
		LocalDate fruehesterEintritt = gd.plusYears(17);
		LocalDate spaetesterEintritt = gd.plusYears(60);
		int years = fruehesterEintritt.until(spaetesterEintritt).getYears();
		int days = random.nextInt(years * 365);
		fruehesterEintritt = fruehesterEintritt.plusDays(days);
		int d = fruehesterEintritt.getDayOfMonth();
		fruehesterEintritt = fruehesterEintritt.minusDays(d - 1);
		if (random.nextInt(1000) < 125) {
			fruehesterEintritt = fruehesterEintritt.plusDays(14);
		}
		return fruehesterEintritt;
	}

	private static LocalDate makeAustrittsdatum(LocalDate eintritt, LocalDate gd) {
		if (eintritt == null) {
			return null;
		}
		LocalDate natuerlicherAustritt = gd
			.plusYears(67)
			.minusDays(gd.getDayOfMonth())
			.plusDays(gd.lengthOfMonth())
		;
		int years = eintritt.until(natuerlicherAustritt).getYears();
		int days = random.nextInt(years * 365);
		LocalDate austritt = eintritt.plusDays(days);
		if (austritt.isAfter(natuerlicherAustritt)) {
			austritt = natuerlicherAustritt;
		} else if (austritt.isBefore(eintritt.plusMonths(6))) {
			;
		} else {
			austritt = austritt
				.minusDays(austritt.getDayOfMonth())
				.plusDays(austritt.lengthOfMonth())
			;
		}
		return austritt;
	}

	private static Long randomGehalt(Date geburtsdatum) {
		long today = new Date().getTime();
		int approx_age = (int) ((today - geburtsdatum.getTime()) / YEAR);
		int factor = approx_age - 16;
		int base = 26000 * factor / 45;
		long gehalt = 17000 + base + random.nextInt(4 * base + 1);
		return Long.valueOf(gehalt * 100);
	}
	
	private final static long DAY = 1000L * 60 * 60 * 24;
	private final static long YEAR = DAY * 36525 / 100;
	
	private static Set<Interesse> randomInteresse() {
		Interesse.StandardInteresse[] standard = Interesse.StandardInteresse.values();
		int es = random.nextInt(1 << 16);
		Set<Interesse> eset = new HashSet<>(16);
		for (int i = 0; i < 16; i++) {
			int bit = 1 << i;
			if ((es & bit) != 0) {
				eset.add(standard[i]);
			}
		}
		return eset;
	}

	private static Date randomGeburtsdatum() {
		long base = new Date().getTime();
		int average = 38 * 36525 / 100; // Tage
		int delta = 29 * 36525 / 100; // Tage
		long minimum = 16 * 36525 / 100; // Tage
		long alter;
		do {
			alter = average - delta + random.nextInt(2 * delta);
		} while (alter < minimum);
		long date = base - alter * DAY;
		return new Date(date);
	}
	
	private static Religion randomReligion() {
		int r = random.nextInt(100);
		if (r < 30) {
			return Religion.KATHOLISCH;
		} else if (r < 57) {
			return Religion.EVANGELISCH;
		} else if (r < 93) {
			return Religion.KONFESSIONSLOS;
		} else {
			return Religion.SONSTIGE;
		}
	}
	
	private static int randomGroesse(Geschlecht geschlecht) {
		if (geschlecht == Geschlecht.MAENNLICH) {
			return 1780 - 180 + random.nextInt(360);
		} else {
			return 1650 - 150 + random.nextInt(300);
		}
	}
	
	private static int randomGewicht(int groesse) {
		return (groesse - 1000 - 100 + random.nextInt(100)) * 100;
	}
	
	@Override
	public String getNachname() {
		return this.nachname;
	}

	@Override
	public String getVorname() {
		return this.vorname;
	}

	@Override
	public Date getGeburtsdatum() {
		return this.geburtsdatum;
	}

	@Override
	public Adresse getAdresse() {
		return this.adresse;
	}

	@Override
	public Geschlecht getGeschlecht() {
		return this.geschlecht;
	}

	@Override
	public Long getGehalt() {
		return this.gehalt;
	}

	@Override
	public String getPersonalnummer() {
		return this.personalnummer;
	}

	@Override
	public Set<Interesse> getInteressen() {
		return this.interessen;
	}

	@Override
	public Religion getReligion() {
		return this.religion;
	}

	@Override
	public int getGroesse() {
		return this.groesse;
	}

	@Override
	public int getGewicht() {
		return this.gewicht;
	}

	@Override
	public LocalDate getEintrittsdatum() {
		return this.eintrittsdatum;
	}

	@Override
	public LocalDate getAustrittsdatum() {
		return this.austrittsdatum;
	}

	@Override
	public Bankverbindung getBankverbindung() {
		return this.bankverbindung;
	}

	@Override
	public Steuerklasse getSteuerklasse() {
		return this.steuerklasse;
	}

	@Override
	public Sozialversicherung getSozialversicherung() {
		return this.sozialversicherung;
	}

//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this);
//	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("#");
		s.append(personalnummer);
		s.append("\t");
		if (geschlecht == Geschlecht.MAENNLICH) {
			s.append("Hr. ");
		} else {
			s.append("Fr. ");
		}
		s.append(vorname);
		s.append(" ");
		s.append(nachname);
		s.append(" (");
		LocalDate c = geburtsdatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		s.append(c.getDayOfMonth());
		s.append(".");
		s.append(c.getMonthValue());
		s.append(".");
		s.append(c.getYear());
		s.append(")");
		s.append("\n\tAdresse:\t");
		s.append(adresse);
		s.append("\n\tBeschäftig:\t");
		if (eintrittsdatum == null) {
			s.append("–––");
		} else {
			s.append(eintrittsdatum);
			s.append(" – ");
			s.append(austrittsdatum);
		}
		s.append("\n\tGehalt:\t\t");
		append(s, gehalt.longValue(), "€/a");
		s.append(" (Steuerklasse ");
		s.append(steuerklasse);
		s.append(")");
		s.append("\n\tAbgaben:\t");
		s.append("KV: ");
		append(s, sozialversicherung.getKrankenkassenBeitrag(), "%");
		s.append("; RV: ");
		append(s, sozialversicherung.getRentenBeitrag(), "%");
		s.append("; AV: ");
		append(s, sozialversicherung.getArbeitslosenBeitrag(), "%");
		s.append("\n\tBank:\t\t");
		s.append(bankverbindung);
		s.append("\n\tReligion:\t");
		s.append(religion.name().toLowerCase());
		s.append("\n\tStatur:\t\t");
		append(s, groesse, "cm");
		s.append("; ");
		append(s, gewicht / 100, "kg");
		s.append("\n\tInteressen:\t");
		s.append(interessen);
		s.append("\n");
		return s.toString();
	}
	
	private static void append(StringBuilder s, long value, String unit) {
		String g = Long.toString(value);
		if (value < 100) {
			s.append("0,");
			if (value < 10) {
				s.append('0');
			}
			s.append(g);
		} else {
			int n = g.length();
			s.append(g, 0, n - 2);
			s.append(',');
			s.append(g, n - 2, n);
		}
		s.append(' ');
		s.append(unit);
	}
	
	private static void append(StringBuilder s, int value, String unit) {
		String g = Integer.toString(value);
		if (value < 10) {
			s.append("0,");
			s.append(g);
		} else {
			int n = g.length();
			s.append(g, 0, n - 1);
			s.append(',');
			s.append(g, n - 1, n);
		}
		s.append(' ');
		s.append(unit);
	}
	
	/**
	 * Nur für Testzwecke.
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(new RandomPerson());
		}
	}
	
}
