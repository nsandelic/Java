package hr.java.vjezbe.entitet;


import java.time.LocalDateTime;
/**
 * Predstavlja entite ispita koji je definiran predmetima, studentima, ocjenama, i datumom.
 * 
 * @author Sandelic
 *
 */
public class Ispit extends Entitet{



	private Predmet predmet;
	private Student student;
	private Integer ocjena;
	private LocalDateTime datumIVrijeme;

	/**
	 * 
	 * Inicijalizira podatke o ispitnom roku
	 * 
	 * @param predmet  podatak o predmetu ispitnog roka
	 * @param student  podatak o studentu koji izlazi na ispitni rok
	 * @param ocjena   ocjena koju je student postigao na ispitnom roku
	 * @param datumIVrijeme podatak o datumu i vremenu pisanja ispitnog roka
	 *  @param id Nasljedeni podatak o ID ispita
	 */	
	public Ispit(Long id, Predmet predmet, Student student, Integer ocjena, LocalDateTime datumIVrijeme) {
		super(id);
		this.predmet = predmet;
		this.student = student;
		this.ocjena = ocjena;
		this.datumIVrijeme = datumIVrijeme;
	}


/**
 * Dohvaca predmet
 * 
 * @return Podatak o predmetu
 */
	public Predmet getPredmet() {
		return predmet;
	}
	/**
	 * Postavlja podatke o predmetu
	 * 
	 * @param predmet podatak o predmetu
	 */
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
/**
 * Dohvaca studenta
 * 
 * @return Podatak o studentu.
 */
	public Student getStudent() {
		return student;
	}
/**
 * Postavlja podatke o studentu
 * 
 * @param student Podatak o studentu
 */
	public void setStudent(Student student) {
		this.student = student;
	}
/**
 * Dohvaca ocjenu.
 * 
 * @return	Integer Podatak o ocjeni.
 */
	public Integer getOcjena() {
		return ocjena;
	}
/**
 * Postavlja ocjenu
 * 
 * @param ocjena Podatak o ocjeni
 */
	public void setOcjena(Integer ocjena) {
		this.ocjena = ocjena;
	}
/**
 * Dohvaca datum i vrijeme
 * 
 * @return LocalDateTime Podatak o datumu i vremenu.
 */
	public LocalDateTime getDatumIVrijeme() {
		return datumIVrijeme;
	}
/**
 * Postavlja datum i vrijeme
 * 
 * @param datumIVrijeme Podatak o datumu i vremenu.
 */
	public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
		this.datumIVrijeme = datumIVrijeme;
	}








}
