package hr.java.vjezbe.entitet;
/**
 *  Predstavlja entitet osobe koji je definiran imenom, prezimenom osobe.
 * 
 * @author Sandelic
 *
 */
public abstract class Osoba extends Entitet{

	private String imeOsobe;
	private String prezimeOsobe;

	/**
	 * 
	 * Inicijalizira podatke o osobi.
	 * 
	 * @param id Nasljedeni podatak o ID osobe
	 * @param imeOsobe	Podatak o imenu osobe
	 * @param prezimeOsobe	Podatak o prezimenu osobe
	 */
	
	
	public Osoba(Long id, String imeOsobe, String prezimeOsobe) {
		super(id);
		this.imeOsobe = imeOsobe;
		this.prezimeOsobe = prezimeOsobe;
	}


	/**
	 * Dohvaca ime osobe
	 * 
	 * @return String Podatak o imenu osobe.
	 */
	public String getImeOsobe() {
		return imeOsobe;
	}
	
	/**
	 * Postavlja ime osobe.
	 * 
	 * @param imeOsobe Podatak o imenu osobe.
	 */
	public void setImeOsobe(String imeOsobe) {
		this.imeOsobe = imeOsobe;
	}
	/**
	 * Dohvaca prezime osobe
	 * 
	 * @return String Podatak o prezimenu osobe.
	 */
	public String getPrezimeOsobe() {
		return prezimeOsobe;
	}
	/**
	 * Postavlja prezime osobe.
	 * 
	 * @param prezimeOsobe Podatak o prezimenu osobe.
	 */
	public void setPrezimeOsobe(String prezimeOsobe) {
		this.prezimeOsobe = prezimeOsobe;
	}






}


