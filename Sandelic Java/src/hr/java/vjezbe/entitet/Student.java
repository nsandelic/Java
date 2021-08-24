package hr.java.vjezbe.entitet;

import java.time.LocalDate;
/**
 * Predstavlja entitet studenta koji je definiran imenom, prezimenom, JMBAG-om i datumom rodjenja.
 * Nasljedjuje klasu Osoba.
 * 
 * @author Sandelic
 *
 */
public class Student extends Osoba {



	private String jmbag;
	private LocalDate datumRodjenja;

	
	/**
	 * Inicijalizira i nasljedjuje podatke o studentu.
	 * 		
	 * @param imeOsobe	Podatak o imenu studena. Ovaj podatak je nasljedjen iz klase Osoba.
	 * @param prezimeOsobe	Podatak o prezimenu studenta. Ovaj podatak je nasljeden iz klase Osoba.
	 * @param jmbag	Podatak o JMBAG-u studenta.
	 * @param datumRodjenja	Podatak o datumu rodjenja studenta.
	 * @param id Nasljedeni podatak o ID osobe
	 */
	
	public Student(Long id, String imeOsobe, String prezimeOsobe, String jmbag, LocalDate datumRodjenja) {
		super(id, imeOsobe, prezimeOsobe);
		this.jmbag = jmbag;
		this.datumRodjenja = datumRodjenja;
	}




	/**
	 * Dohvaca JMBAG studenta.
	 * 
	 * @return String Podatak o JMBAG-u studenta.
	 */

	public String getJmbag() {
		return jmbag;
	}
	/**
	 * Postavlja JMBAG studenta.
	 * 
	 * @param jmbag Podatak o JMBAG-u sutdenta.
	 */
	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}
	/**
	 * 
	 * Dohvaca datum rodjenja studenta.
	 * @return LocalDate Podatak o datumu rodjenja studenta.
	 */
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	/**
	 * 
	 * Postavlja datum rodjenja studenta.
	 * @param datumRodjenja Podatak o datumu rodjenja studenta.
	 */
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datumRodjenja == null) ? 0 : datumRodjenja.hashCode());
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		return result;
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (datumRodjenja == null) {
			if (other.datumRodjenja != null)
				return false;
		} else if (!datumRodjenja.equals(other.datumRodjenja))
			return false;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		return true;
	}
	
	
	
	
	
	
	



}