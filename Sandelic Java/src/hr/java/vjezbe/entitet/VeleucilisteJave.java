package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
/**
 *  Predstavlja entitet obrazovne ustanove koju student pohadja.
 *  Nasljedjuje klasu ObrazovnaUstanova i implemetira sucelje Visokoskolska.
 * 
 * @author Sandelic
 *
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {

	private static final Logger logger = LoggerFactory.getLogger(VeleucilisteJave.class);


	/**
	 * Prosljedjuju se podaci od predmetima, profesorima, ispitima i studentima iz klase ObrazovnaUstanova.
	 * 
	 * 
	 * @param nazivUstanove	podatak o nazivu ustanove 
	 * @param predmeti	podatak o predmetima
	 * @param profesori	podatak o profesorima
	 * @param ispiti	podatak o ispitima
	 * @param studenti	podatak o studentima
	 */
	public VeleucilisteJave(Long id, String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Ispit> ispiti,
			List<Student> studenti) {
		super( id,nazivUstanove, predmeti, profesori, ispiti, studenti);
	}

	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjenaZavRada, Integer ocjenaObrana) {

		BigDecimal konacnaOcjena = null;
		BigDecimal prosOcj = null;

		try {	
			prosOcj= odrediProsjekOcjenaNaIspitima(ispiti);
			konacnaOcjena= prosOcj.multiply(new BigDecimal(2));
			konacnaOcjena= konacnaOcjena.add(new BigDecimal(ocjenaZavRada));
			konacnaOcjena= konacnaOcjena.add(new BigDecimal(ocjenaObrana));
			konacnaOcjena= konacnaOcjena.divide(new BigDecimal(4));
		}
		catch ( NemoguceOdreditiProsjekStudentaException ex1 )  {
			logger.info(" NemoguceOdreditiProsjekStudentaException" );
			System.out.println(ex1.getMessage());
			konacnaOcjena = new BigDecimal(1);
		}

		return konacnaOcjena;
	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
		int brPetica = 0;
		int najvisePetica = 0;
		int najUspjesniji = 0;


		for( int i = 0; i < getIspit().size(); i++ ) {
			for ( int j = 0; j < getIspit().size(); j++ ) {
				if( getStudenti().get(i).getImeOsobe().equals(getStudenti().get(j).getImeOsobe()) && getStudenti().get(i).getPrezimeOsobe().equals(getStudenti().get(j).getPrezimeOsobe()) ){
					if(getIspit().get(i).getOcjena() == Ocjena.ODLICAN.getIntOcjena() ) {
						brPetica +=  1;
					}
				}
				if( brPetica > najvisePetica ) {
					najUspjesniji = i;
					najvisePetica = brPetica;
					brPetica = 0;
				}
				else if( brPetica == najvisePetica )
					continue;
				else 
					brPetica = 0;
			}
		}

		return new Student( getStudenti().get(najUspjesniji).getId(), getStudenti().get(najUspjesniji).getImeOsobe(), getStudenti().get(najUspjesniji).getPrezimeOsobe(), getStudenti().get(najUspjesniji).getJmbag(), getStudenti().get(najUspjesniji).getDatumRodjenja()  );
	}

}

