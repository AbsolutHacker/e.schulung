
package de.mathema.service.api;

/**
 * Beschreibt eine Bankverbindung. Typische Bankverbindungen sind
 * <ul>
 * <li>DE19123412341234123412<br>Deutsche Kredit- und Kummeranstalt<br>DKK12</li>
 * <li>MT71MALT011000012345MTLCAST001S<br>Central Bank of Malta<br>MALTMTMT</li>
 * </ul>
 */
public interface Bankverbindung {
	
	/**
	 * Liefert die Kontonummer als IBAN.<p>
	 * Eine IBAN besteht aus den Ziffern 0 – 9 und den Buchstaben A – Z. Neben der zweistelligen
	 * Länderkennung und der zweistelligen Prüfziffer enthält eine IBAN bis zu 30 alphanumerische
	 * Zeichen. 
	 */
	public String getIBAN();

	/**
	 * Liefert den Namen der Bank. Der Name der Bank kann leer ("") sein.
	 */
	public String getInstitutName();

	/**
	 * Liefert die Kennung der Bank als BIC. Die Kennung der Bank kann leer ("") sein.
	 */
	public String getBIC();

}
