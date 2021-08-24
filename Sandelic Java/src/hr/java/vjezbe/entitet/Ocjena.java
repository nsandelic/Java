package hr.java.vjezbe.entitet;
/**
 * 
 * Predstavlja enumeraciju ocjena.
 * 
 * @author Sandelic
 *
 */
public enum Ocjena {

	NEDOVOLJAN(1,"nedovoljan"),
	DOVOLJAN(2,"dovoljan"),
	DOBAR(3,"dobar"),
	VRLO_DOBAR(4,"vrlo dobar"),
	ODLICAN(5,"odlican");


	private final int intOcjena;
	private final String strOcjena;
	
	/**
	 * Enumerira ocjene.
	 * 
	 * @param intOcjena	Brojcani podatak ocjene.
	 * @param strOcjena	Tekstualni podatak ocjene.
	 */
	Ocjena(int intOcjena, String strOcjena) {
        this.intOcjena = intOcjena;
        this.strOcjena = strOcjena;
    }


	public int getIntOcjena() {
		return intOcjena;
	}


	public String getStrOcjena() {
		return strOcjena;
	}


	
	
	
	


}
