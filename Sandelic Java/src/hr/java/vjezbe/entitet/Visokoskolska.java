package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
/**
 * Predstavlja interface koji sadrzi metode racunanja konacnih ocjena, prosjeka na ispitima, filtriranje ispita.
 * 
 * @author Sandelic
 *
 */
public interface Visokoskolska {


/**
 * Racuna konacnu ocjenu studija nekog studenta.
 * 
 * @param ispiti	Podatak o ispitima koje student pohadja.
 * @param ocjenaPismeni	Podatak o ocjeni zavrsnog ispita.
 * @param ocjenaObrana	Podatak o obrani zavrsnog ispita.
 * @return	Ocjenu koju je student postigao na kraju studija.
 * @throws NemoguceOdreditiProsjekStudentaException	Iznimka o tome da je nemoguce odrediti prosjek ocjena.
 */
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta (List<Ispit> ispiti, Integer ocjenaPismeni, Integer ocjenaObrana) throws NemoguceOdreditiProsjekStudentaException;
/**
 * Racuna prosjek ocjena na svim ispitima.
 * 
 * @param ispiti	podatak o svim ispitima.
 * @return	Prosjenu ocjenu na ispitima.
 * @throws NemoguceOdreditiProsjekStudentaException Iznimka o tome da je nemoguce odrediti prosjek ocjena.
 */
	default BigDecimal odrediProsjekOcjenaNaIspitima (List<Ispit> ispiti) throws NemoguceOdreditiProsjekStudentaException {

		BigDecimal prosVrijednot = null;
		BigDecimal zbrOcj= new BigDecimal("0");

		for(int i=0; i < ispiti.size(); i++) {
			if(ispiti.get(i).getOcjena() == Ocjena.NEDOVOLJAN.getIntOcjena() ) {
				throw new NemoguceOdreditiProsjekStudentaException("Student "+(ispiti.get(i).getStudent().getImeOsobe())+" "+  (ispiti.get(i).getStudent().getPrezimeOsobe())    
						+"je dobio ocjenu nedovoljan na ispitu iz predmeta: "+(ispiti.get(i).getPredmet().getNazivPredmeta()) );
			} else
				zbrOcj=zbrOcj.add(new BigDecimal(ispiti.get(i).getOcjena()));    
			}
			prosVrijednot=zbrOcj.divide(new BigDecimal(ispiti.size()));
		

		return  prosVrijednot;
	}


/**
 * 
 * Filtrira ispite po studentu
 * 
 * @param ispiti	Podatak o ispitima.
 * @param studenti	Podatak o studentu.
 * @return	Polje ispita koji si  filtrirani po studentu.
 */
	default List<Ispit> filtrirajIspitePoStudentu (List<Ispit> ispiti, Student studenti) {


		//	int brIspita = 0;
		//	int j=0;

		List<Ispit> ispitPoStudentu = ispiti.stream()
				.filter( ips -> studenti.getJmbag().equals( ips.getStudent().getJmbag() )   )
				.collect(Collectors.toList());

		return ispitPoStudentu;
		
	/*	for(int i=0; i< ispiti.size(); i++) {
			if( studenti.getImeOsobe() == ispiti.get(i).getStudent().getImeOsobe() && studenti.getPrezimeOsobe() == ispiti.get(i).getStudent().getPrezimeOsobe() ) {
				brIspita++;
			}
		}
		List<Ispit> ispitPoStudentu =  new ArrayList<>();

		for(int i=0; i< ispiti.size(); i++) {
			if( studenti.getImeOsobe() == ispiti.get(i).getStudent().getImeOsobe() && studenti.getPrezimeOsobe() == ispiti.get(i).getStudent().getPrezimeOsobe() ) {
				ispitPoStudentu.add(j, ispiti.get(i));
				j++;
			}
		}
		
		
		ispitPoStudentu.forEach( ispiti -> {
			if(studenti.getJmbag().equals(( ispiti.getStudent().getJmbag()) ) {
				ispitPoStudentu.add(ispiti);
			}
		};
*/
		
	}


}
