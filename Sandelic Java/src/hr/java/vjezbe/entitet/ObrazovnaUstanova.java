package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Predstavlja entitet obrazovne ustanove.
 * 
 * 
 * @author Sandelic
 *
 */
public abstract class ObrazovnaUstanova extends Entitet {
	
	private String nazivUstanove;
	private List<Predmet> predmeti;
	private List<Profesor> profesori;
	private List<Ispit> ispit;
	private List<Student> studenti;
	
	
/**
 * Inicijaliziraju se podaci o obrazovnoj ustanovi
 * 
 * @param nazivUstanove Podatak naziva obrazovne ustanove.
 * @param predmeti 	Podatak popisa predmeta.
 * @param profesori	Podatak popisa profesora.
 * @param ispiti	Podatak popisa ispita.
 * @param studenti	Podatak popisa studenta.
 * @param id	Podatak od identifikaciji obrazovne ustanove
 */
public ObrazovnaUstanova(Long id,String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Ispit> ispiti, List<Student> studenti) {
		super(id);
		this.nazivUstanove = nazivUstanove;
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.ispit = ispiti;
		this.studenti = studenti;
	}

/**
 * Dohvaca naziv ustanove
 * 
 * @return String Podatak o nazivu ustanove
 */
	public String getNazivUstanove() {
		return nazivUstanove;
	}
	/**
	 * Postavlja naziv ustanove.
	 * 
	 * @param nazivUstanove Podatak o nazivu ustanove.
	 */
	public void setNazivUstanove(String nazivUstanove) {
		this.nazivUstanove = nazivUstanove;
	}
	/**
	 * Dohvaca predmete
	 * 
	 * @return List<Predmet> Podatak predmetima
	 */
	public List<Predmet> getPredmeti() {
		return predmeti;
	}
	/**
	 * Postavlja podatak o predmetima
	 * 
	 * @param predmeti Podatak o predmetima
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	/**
	 *Dohvaca profesore. 
	 *
	 * @return List<Profesor> Podatak o profesorima
	 */
	public List<Profesor> getProfesori() {
		return profesori;
	}
	/**
	 * Postavlja podatak o profesorima.
	 * @param profesori Podatak o profesorima
	 */
	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}
	/**
	 * Dohvaca ispite.
	 * 
	 * @return  List<Ispit> Podatak o ispitima.
	 */
	public List<Ispit> getIspit() {
		return ispit;
	}
	/**
	 * Postavlja podatke o ispitima.
	 * 
	 * @param ispit Podatak o ispitima.
	 */
	public void setIspit(List<Ispit> ispit) {
		this.ispit = ispit;
	}
/**
 * 
 * Dohvaca studente
 * 
 * @return List<Student> Podatak o studentima
 */
	public List<Student> getStudenti() {
		return studenti;
	}
/**
 * 
 * Postavlja podatke o studentima.
 * 
 * @param studenti Podatak o studentima.
 */
	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	
/**
 * 
 * Odredjuje najuspjesnijeg studenta u akademskoj godini.
 * 
 * @param godina Podatak o akademskoj godini.
 * @return Student koji je imao najveci uspjeh na godini.
 */
	public abstract Student odrediNajuspjesnijegStudentaNaGodini (Integer godina);
	
	
}





