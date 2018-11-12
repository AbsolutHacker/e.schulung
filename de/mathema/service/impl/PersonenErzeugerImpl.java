
package de.mathema.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.mathema.service.api.Geschlecht;
import de.mathema.service.api.Person;
import de.mathema.service.api.PersonenErzeuger;
import de.mathema.service.api.Religion;
import de.mathema.service.api.Steuerklasse;
import de.mathema.service.impl.random.RandomErzeugerImpl;

/**
 * Erzeugt einen Satz Personen.
 */
public class PersonenErzeugerImpl implements PersonenErzeuger {
	
	private static final boolean RANDOM = false;
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	@Override
	public List<Person> erzeuge(int n) {
		if (RANDOM) {
			return new RandomErzeugerImpl().erzeuge(n);
		} else {
			try {
				return Arrays.asList(
					createEvaMustermann(),
					createAdamMustermann(),
					createMaxMustermann()
				);
			} catch(ParseException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private Person createEvaMustermann() throws ParseException {
		return new PersonImpl(
				"Eva", 
				"Mustermann", 
				DATE_FORMAT.parse("19700512"), 
				new AdresseImpl("Musterstraße", "1a", "", "00000", "Musterstadt"), 
				Geschlecht.WEIBLICH, 
				Long.valueOf(1000L), 
				"0001", 
				Collections.emptySet(), 
				Religion.KATHOLISCH, 
				182, 
				90, 
				LocalDate.of(1985, 12, 24), 
				null,
				new BankverbindungImpl("Musterbank", "DE000000000000000", "XYZ"),
				Steuerklasse.III, 
				new SozialversicherungImpl()
			);

	}

	private Person createAdamMustermann() throws ParseException {
		return new PersonImpl(
				"Adam", 
				"Mustermann", 
				DATE_FORMAT.parse("19800324"), 
				new AdresseImpl("Musterstraße", "1a", "", "91052", "Musterstadt"), 
				Geschlecht.MAENNLICH, 
				Long.valueOf(1000L), 
				"0002", 
				Collections.emptySet(), 
				Religion.EVANGELISCH, 
				182, 
				90, 
				LocalDate.of(1985, 12, 24), 
				null,
				new BankverbindungImpl("Musterbank", "DE000000000000000", "XYZ"),
				Steuerklasse.III, 
				new SozialversicherungImpl()
			);

	}

	private Person createMaxMustermann() throws ParseException {
		return new PersonImpl(
				"Max", 
				"Mustermann", 
				DATE_FORMAT.parse("19901224"), 
				new AdresseImpl("Musterstraße", "1a", "", "00000", "Musterstadt"), 
				Geschlecht.MAENNLICH, 
				Long.valueOf(1000L), 
				"0003", 
				Collections.emptySet(), 
				Religion.EVANGELISCH, 
				182, 
				90, 
				LocalDate.of(1985, 12, 24), 
				null,
				new BankverbindungImpl("Musterbank", "DE000000000000000", "XYZ"),
				Steuerklasse.III, 
				new SozialversicherungImpl()
			);

	}
}
