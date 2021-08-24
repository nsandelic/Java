package hr.java.vjezbe.entitet;
/**
 * 
 * Sadrzi informacije o diplomskom obrazovanju.
 * nasljedjuje interface Visokoskolska.
 * 
 * @author Sandelic
 *
 */
public interface Diplomski extends Visokoskolska{
	/**
	 * Odreduje studenta koji je osvojio Rektorovu nagradu
	 * 
	 * @return Studenta koji je osvojio Rektorovu nagradu.
	 */
	public Student drediStudentaZaRektorovuNagradu();

}
