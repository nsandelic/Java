package hr.java.vjezbe.entitet;

import java.util.HashSet;
import java.util.Set;

/**
 * Predstavlja entitet predmeta koji je definiran, sifrom, nazivom, ECTS bodovima, nositelja predemeta, studenata 
 * 
 * @author Sandelic
 *
 */
public class Predmet extends Entitet {
	
	
	private String sifraPredmeta;
	private String nazivPredmeta;
	private Integer brojEctsBodova;
	private Profesor nositeljPredmeta;
	private Set<Student> setStudenti;
	
	
	



/**
 * 
 * Inicijalizira podate o predmetu.
 * 	
 * @param sifraPredmeta podatak o sifri predmeta.
 * @param nazivPredmeta	podatak o nazivu predmeta.
 * @param brojEctsBodova	podatak o broju ECTS bodova predmeta.
 * @param nositeljPredmeta	podatak o nositelju predmeta.
 * @param brojStudenata	podatak o broju studenata koji pohadjaju predmet.
 *  @param id Nasljedeni podatak o ID Predmeta
 */
	public Predmet(Long id,String sifraPredmeta, String nazivPredmeta, Integer brojEctsBodova, Profesor nositeljPredmeta) {
		super(id);
		this.sifraPredmeta = sifraPredmeta;
		this.nazivPredmeta = nazivPredmeta;
		this.brojEctsBodova = brojEctsBodova;
		this.nositeljPredmeta = nositeljPredmeta;
		this.setStudenti =  new HashSet<>();
	}


/**
 * Dohvaca sifru predmeta.
 * 
 * @return String Podatak o sifri predemta.
 */
	public String getSifraPredmeta() {
		return sifraPredmeta;
	}
/**
 * Postavlja sifru predemta.
 * 
 * @param sifraPredmeta Podatak o sifri predmeta.
 */
	public void setSifraPredmeta(String sifraPredmeta) {
		this.sifraPredmeta = sifraPredmeta;
	}


/**
 * Dohvaca naziv predmeta.
 * 
 * @return String Podatak o nazivu predemta.
 */
	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

/**
 * Postavlja naziv predmeta.
 * 
 * @param nazivPredmeta Podatak o nazivu predmeta.
 */

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}
/**
 * Dohvaca broj ECTS bodova
 * 
 * @return Integer Podata o broju ECTS bodova.
 */


	public Integer getBrojEctsBodova() {
		return brojEctsBodova;
	}


/**
 * 
 * Postavlja broj ECTS bodova.
 * 
 * @param brojEctsBodova Podatak o broju ECTS bodova.
 */
	public void setBrojEctsBodova(Integer brojEctsBodova) {
		this.brojEctsBodova = brojEctsBodova;
	}


/**
 * Dohvaca nositelja predmeta.
 * 
 * @return Profesor Podatak o nositelju predemta.
 */
	public Profesor getNositeljPredmeta() {
		return nositeljPredmeta;
	}


/**
 * Postavlja nostielja predmeta.
 * 
 * @param nositeljPredmeta Podatak o nositelju predmeta.
 */
	public void setNositeljPredmeta(Profesor nositeljPredmeta) {
		this.nositeljPredmeta = nositeljPredmeta;
	}

/**
 * Dohvaca studena.
 * 
 * @return Set<Student> Podatak o studentu
 */
	public Set<Student> getSetStudenti() {
		return setStudenti;
	}


/**
 * Postavlja podatke o studentu
 * 
 * 
 * @param studenti Podatak o studentu
 */

public void setSetStudenti(Set<Student> setStudenti) {
	this.setStudenti = setStudenti;
}
	
	
	

	
	
}
	
