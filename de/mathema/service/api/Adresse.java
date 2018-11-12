
package de.mathema.service.api;

/**
 * Beschreibt eine Adresse in Deutschland. Typische Adressen sind etwa
 * <ul>
 * <li>Hofmannstrasse 34<br>91052 Erlangen,</li>
 * <li>A 3, 2<br>68161 Mannheim,</li>
 * <li>Askanischer Platz 0<br>10963 Berlin,</li>
 * <li>S-Bhf Sonnenallee<br>12055 Berlin-Neukölln Rixdorf.</li>
 * </ul>
 */
public interface Adresse {

	/**
	 * Liefert den Straßennamen. Mögliche Straßennamen sind beispielsweise
	 * <ul>
	 * <li>Legustenweg,</li>
	 * <li>Birkenalle,</li>
	 * <li>Nürnberger Straße,</li>
	 * <li>Max-Planck-Straße,</li>
	 * <li>In der Au,</li>
	 * <li>A 3,</li>
	 * <li>Bischöflich-Geistlicher-Rat-Josef-Zinnbauer-Straße.</li>
	 * </ul>
	 */
	public String getStrasse();

	/**
	 * Liefert die Hausnummer bezogen auf die Straße. Mögliche Hausnummern sind beispielsweise
	 * <ul>
	 * <li>14,</li>
	 * <li>123 a,</li>
	 * <li>7 ¼ A,</li>
	 * <li>233 z,</li>
	 * <li>28 – 32,</li>
	 * <li>2245.</li>
	 * </ul>
	 * Insbesondere kann eine Hausnummer auch leer ("") sein.
	 */
	public String getHausnummer();

	/**
	 * Liefert den Adresszusatz bezogen auf Straße und Hausnummer. Mögliche Adresszusätze sind etwa
	 * <ul>
	 * <li>App. 17 b,</li>
	 * <li>vor Nr. 3,</li>
	 * <li>hinter der Tankstelle.</li>
	 * </ul>
	 * Der Zusatz kann damit sowohl für eine genauere Beschreibung innerhalb des addressierten
	 * Objekts dienen (das Appartment in einem Wohnheim) als auch zur Beschreibung eines
	 * Objekts, das etwa selbst keine eigene Hausnummer besitzt.
	 */
	public String getAdresszusatz();

	/**
	 * Liefert die von der Deutschen Post vergebene (fünfstellige) Postleitzahl. Mögliche 
	 * Postleitzahlen sind
	 * <ul>
	 * <li>01001,</li>
	 * <li>54321,</li>
	 * <li>91052,</li>
	 * <li>99999.</li>
	 * </ul>
	 */
	public String getPlz();

	/**
	 * <ul>
	 * <li>Y,</li>
	 * <li>Ee,</li>
	 * <li>Rothenburg ob der Tauber,</li>
	 * <li>Neunkirchen a. d. S.,</li>
	 * <li>Burg auf Fehmarn,</li>
	 * <li>Schiermonnikoog,</li>
	 * <li>Voßwinkel,</li>
	 * <li>Bad (Kurort),</li>
	 * <li>Oldenburg (Oldb),</li>
	 * <li>Fürth (Bay.),</li>
	 * <li>Friedensstadt Osnabrück,</li>
	 * <li>Michelbach an der Bilz-Gschlachtenbretzingen,</li>
	 * <li>Taumatawhakatangihangakoauauotamateaturipukakapikimaungahoronukupokaiwhenuakitanatahu</li>
	 * <li> Krung Thep Mahanakhon Amon Rattanakosin Mahinthara Ayuthaya Mahadilok 
	 * 			Phop Noppharat Ratchathani Burirom Udomratchaniwet Mahasathan Amon Piman 
	 * 			Awatan Sathit Sakkathattiya Witsanukam Prasit.</li>
	 * </ul>
	 */
	public String getStadt();

}
