
package de.mathema.service.api;

/**
 * Beschreibt ein Interesse.
 */
public interface Interesse {

	/**
	 * Ein Satz (nichtssagender) Standardinteressen.
	 */
	@SuppressWarnings("javadoc")
	public enum StandardInteresse implements Interesse {

		A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P;
		
		private final String beschreibung;
		
		private StandardInteresse() {
			this.beschreibung = "Interesse an " + name();
		}
		
		@Override
		public String getName() {
			return name();
		}

		@Override
		public String getBeschreibung() {
			return beschreibung;
		}
		
	}
	
	/**
	 * Beschreibt den (eindeutigen) Namen des Interessengebietes.
	 */
	public String getName();

	/**
	 * Liefert die Beschreibung des Interessengebietes. 
	 */
	public String getBeschreibung();
}
