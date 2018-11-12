package de.mathema.service.impl.random;

import java.math.BigInteger;
import java.util.Random;

import de.mathema.service.api.Bankverbindung;

/**
 * Implementierung einer zufälligen Bankverbindung.
 */
public class RandomBankverbindung implements Bankverbindung {

	private final String iban;
	private final String institutName;
	private final String bic;

	/**
	 * Erzeugt eine zufällige Bankverbindung.
	 */
	public RandomBankverbindung() {
		iban = randomIBAN();
		institutName = "";
		bic = "";
	}
	
	@Override
	public String getIBAN() {
		return this.iban;
	}

	@Override
	public String getInstitutName() {
		return this.institutName;
	}

	@Override
	public String getBIC() {
		return this.bic;
	}

	@Override
	public String toString() {
		return iban;
	}

	private static final String ZEROS = "00000000";	
	private static final BigInteger BIG_97 = BigInteger.valueOf(97);
	private static final Random r = new Random(0x172364817283476AL);
	
	private static String rearrange(String iban, int checksum) {
		int n = iban.length();
		String cs = Integer.toString(checksum);
		String padding = "";
		if (checksum < 10) {
			padding = "0";
		}
		return iban.substring(n - 4, n - 2) + padding + cs + iban.substring(0, n - 4);
	}
	
	private static BigInteger toNumber(String s) {
		StringBuilder num = new StringBuilder(s.length());
		s.chars().forEachOrdered(c -> num.append(c < '@' ? c - '0' : 10 + c - 'A'));
		return new BigInteger(num.toString());
	}
	
	private static String generateRandomIBAN() {
		// DE00 0000 0000 0000 0000 00 
		StringBuilder buffer = new StringBuilder() ;
		String s = Integer.toString(r.nextInt(100000000));
		int l = s.length();
		if (l < 8) {
			buffer.append(ZEROS.substring(l));
		}
		buffer.append(s);
		buffer.append("00");
		s = Integer.toString(r.nextInt(100000000));
		l = s.length();
		if (l < 8) {
			buffer.append(ZEROS.substring(l));
		}
		buffer.append(s);
		buffer.append("DE00");
		return buffer.toString();
	}
	
	private static String randomIBAN() {
		String iban = generateRandomIBAN();
		BigInteger n = toNumber(iban);
		int checksum = 98 - n.mod(BIG_97).intValue();
		if (((n.intValue() >>> 10) & 0x3FF) == 0) {
			checksum = checksum + 1;
			if (checksum > 98) {
				checksum = 2;
			}
		}
		return rearrange(iban, checksum);
	}
	
}
