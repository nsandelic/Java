package hr.java.vjezbe.entitet;

import java.util.List;
/**
 * Predstavlja entitet Sveuciliste.
 * 
 * 
 * @author Sandelic
 * 
 * @param <T> Podatak o tipu clase.
 */
public class Sveuciliste <T extends  ObrazovnaUstanova> {
	private List<T> elements ;





	public Sveuciliste(List<T> elements) {
		super();
		this.elements = elements;
	}

	/**
	 * 
	 * Vrsi dodavanje obrazovnih ustanova u listu.
	 * 
	 * @param objekt Podatak o obrazovnoj ustanovi
	 */

	public void dodajObrazovnuUstanovu (T objekt) {

		elements.add(objekt);

	}

	/**
	 * 
	 * Dohvaca obrazovnu ustanovu na osnovu indeksa.
	 * 
	 * @param index Podatak o indeksu .
	 * @return T Podatak o obrazovnoj ustanovi.
	 */
	public T dohvatiObrazovnuUstanovu (int index) {


		return elements.get(index);
	}

	/**
	 * Dohvaca listu obrazovnih ustanova.
	 * 
	 * 
	 * @return List<T> Podatak o listi obrazovnih ustanova.
	 */

	public List<T>  dohvatiListuObrazovnihUstanova () {

		return elements;
	}



	


}
