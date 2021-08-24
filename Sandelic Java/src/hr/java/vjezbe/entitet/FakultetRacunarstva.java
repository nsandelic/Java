package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;
/**
 *  Predstavlja obrazovnu ustanovu koju neki student pohadja.
 *  Nasljedjuje klasu ObrazovnaUstanova i implemetira sucelje Visokoskolska.
 * 
 * @author Sandelic
 *
 */
public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

	private static final Logger logger = LoggerFactory.getLogger(FakultetRacunarstva.class);



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
	public FakultetRacunarstva(Long id,String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Ispit> ispiti,
			List<Student> studenti) {
		super( id,nazivUstanove, predmeti, profesori, ispiti, studenti);

	}
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjenaDipRada, Integer ocjenaObrana) {

		BigDecimal konacnaOcjena = null;
		BigDecimal prosOcj = null;

		try {
			prosOcj= odrediProsjekOcjenaNaIspitima(ispiti);
			konacnaOcjena= prosOcj.multiply(new BigDecimal(3));
			konacnaOcjena= konacnaOcjena.add(new BigDecimal(ocjenaDipRada));
			konacnaOcjena= konacnaOcjena.add(new BigDecimal(ocjenaObrana));
			konacnaOcjena= konacnaOcjena.divide(new BigDecimal(5));
		}
		catch ( NemoguceOdreditiProsjekStudentaException ex1 )  {
			logger.info(ex1.getMessage(), ex1);
			System.out.println(ex1.getMessage());
			konacnaOcjena = new BigDecimal(1);
		}

		return konacnaOcjena;
	}

	@Override
	public Student drediStudentaZaRektorovuNagradu() throws PostojiViseNajmladjihStudenataException {
		int rektor = 0;
		BigDecimal ocjena = new BigDecimal(0);
		BigDecimal brojac = new BigDecimal(0);
		BigDecimal prosjek = new BigDecimal(0); 
		BigDecimal najveciProsjek = new BigDecimal(0); 
		

		for( int i = 0; i < getIspit().size(); i++ ) {
			for( int j = 0; j < getIspit().size(); j++ ) {
				if( getStudenti().get(i).getImeOsobe().equals(getStudenti().get(j).getImeOsobe()) && getStudenti().get(i).getImeOsobe().equals(getStudenti().get(j).getImeOsobe())   ) {
					ocjena = ocjena.add(new BigDecimal( getIspit().get(i).getOcjena()));
					brojac = brojac.add( new BigDecimal(1));
				}
			}
			prosjek = ocjena.divide(brojac) ;
			
			
			
			if( prosjek.compareTo(najveciProsjek) > 0 ) {
				najveciProsjek = prosjek;
				rektor = i ;
				prosjek = new BigDecimal(0);
				ocjena =new BigDecimal(0);
				brojac = new BigDecimal(0);
			}
			else if( prosjek == najveciProsjek ) {
				if( getStudenti().get(i).getDatumRodjenja().getYear() < getStudenti().get(rektor).getDatumRodjenja().getYear()) {
					rektor=i;
				}
				if( getStudenti().get(i).getDatumRodjenja().equals( getStudenti().get(rektor).getDatumRodjenja() ) )
					throw new PostojiViseNajmladjihStudenataException( "Pronadeno je više najmladih studenata s istim datumom rodenja, a to su " + getStudenti().get(i).getImeOsobe() +" "+ getStudenti().get(rektor).getPrezimeOsobe()+" "+ getStudenti().get(rektor).getImeOsobe()+" "+ getStudenti().get(rektor).getPrezimeOsobe() );
					ocjena=new BigDecimal(0);
				prosjek = new BigDecimal(0);
				ocjena=new BigDecimal(0);
				brojac = new BigDecimal(0);
			}
			else {
				brojac = new BigDecimal(0);
				ocjena = new BigDecimal(0);
				prosjek = new BigDecimal(0);
			}

		}
		return new Student( getStudenti().get(rektor).getId(), getStudenti().get(rektor).getImeOsobe(), getStudenti().get(rektor).getPrezimeOsobe(), getStudenti().get(rektor).getJmbag(), getStudenti().get(rektor).getDatumRodjenja()  );
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
