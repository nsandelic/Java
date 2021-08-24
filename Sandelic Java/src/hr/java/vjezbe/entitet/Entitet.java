package hr.java.vjezbe.entitet;
/**
 * Predtavlja indetifikacijski entitet koji je definiram ID-om
 * 
 * 
 * @author Sandelic
 *
 */
public class Entitet implements java.io.Serializable{
	
	private Long id;

	
	/**
	 * 
	 * Inicijalizira podatke o indetifikaciji
	 * 
	 * @param id Podatak o indetifikaciji.
	 */
	public Entitet(Long id) {
		super();
		this.id = id;
	}
/**
 * 
 * Dohvaca indetifikaciju entiteta
 * 
 * @return id Podatak o indetifikaciji
 */
	public Long getId() {
		return id;
	}
/**
 * Postavlja vrijednost indetifikacije
 * 
 * @param id Podatak o indetifikaciji
 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
	
	
	
	
	

}
