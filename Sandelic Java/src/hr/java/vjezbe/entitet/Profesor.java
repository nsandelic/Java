package hr.java.vjezbe.entitet;
/**
 * Predstavlja entiet profesora koji je definiran imenom, prezimenom, sifrom i titulom.
 * Nasljedjuje klasu Osoba.
 * 
 * @author Sandelic
 *
 */
public class Profesor extends Osoba {
	
	private String sifraProfesora;
	private String titulaProfesora;
	
	

/**
 * 
 * Inicijalizira i nasljedjuje podatke o profesoru.
 * 
 * @param sifraProfesora	Podatak o sifri profesora.
 * @param imeOsobe	Podatak o imenu profesora. Ovaj podatak je nasljedjen iz klase Osoba
 * @param prezimeOsobe	Podatak o prezimenu profesora. Ovaj podatak je nasljedjen iz klase Osoba.
 * @param titulaProfesora	podatak o tituli profesora.
 * @param id Nasljedeni podatak o ID osobe
 */
	
	public Profesor(Long id, String sifraProfesora, String imeOsobe, String prezimeOsobe, String titulaProfesora) {
		super(id, imeOsobe, prezimeOsobe);
		this.sifraProfesora = sifraProfesora;
		this.titulaProfesora = titulaProfesora;
	}
	

/**
 * Dohvaca sifru profesora.
 * 
 * @return String Podatak o sifri profesora.
 */
	public String getSifraProfesora() {
		return sifraProfesora;
	}
	/**
	 * Postavlja sifru profesora.
	 * 
	 * @param sifraProfesora Podatak o sifri profesora.
	 */
	public void setSifraProfesora(String sifraProfesora) {
		this.sifraProfesora = sifraProfesora;
	}
	/**
	 * 
	 * Dihvaca titulu profesora.
	 * 
	 * @return String Podata o tituli profesora.
	 */
	public String getTitulaProfesora() {
		return titulaProfesora;
	}
	/**
	 * Postavlja titulu profesora.
	 * 
	 * @param titulaProfesora Podatak o tituli profesora.
	 */
	public void setTitulaProfesora(String titulaProfesora) {
		this.titulaProfesora = titulaProfesora;
	}
	
	

	
	
	
	
	
}