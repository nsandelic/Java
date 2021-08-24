package hr.java.vjezbe.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

public class Datoteke {
	
	
	public static List<Profesor> dohvatiProfesore () {

		int counter=1;
		String line;
		Long idProfesora = null;
		String sifraProfesora =" ";
		String imeProfesora = " ";
		String prezimeProfesora = " ";
		String titulaProfesora = " ";
		List<Profesor> profesori = new ArrayList<>();

		try (BufferedReader in = new BufferedReader(new FileReader("dat/profesori.txt"))) {
			while ((line = in.readLine()) != null) {

				if(counter==1) {
					idProfesora = Long.valueOf(line).longValue();
					counter++;
				}
				else if(counter==2) {
					sifraProfesora = line;
					counter++;
				}
				else if(counter==3) {
					imeProfesora = line;
					counter++;
				}
				else if(counter==4) {
					prezimeProfesora = line;
					counter++;
				}
				else if(counter==5) {
					titulaProfesora = line;
					profesori.add(new Profesor(idProfesora, sifraProfesora, imeProfesora, prezimeProfesora, titulaProfesora));
					counter=1;
				}				
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return profesori;
	}
	
	public static List<Student> dohvatiStudente ( ) {

		int counter=1;
		String line;
		Long idStudenta = null;
		String imeStudenta =" ";
		String prezimeStudenta = " ";
		String jmbag = " ";
		List<Student> studenti = new ArrayList<>();
		LocalDate datumRodjenja = null;
		DateTimeFormatter formater = DateTimeFormatter.ofPattern ("dd.MM.yyyy.");

		try (BufferedReader in = new BufferedReader(new FileReader("dat/studenti.txt"))) {
			while ((line = in.readLine()) != null) {

				if(counter==1) {
					idStudenta = Long.valueOf(line).longValue();
					counter++;
				}
				else if(counter==2) {
					imeStudenta = line;
					counter++;
				}
				else if(counter==3) {
					prezimeStudenta = line;
					counter++;
				}
				else if(counter==4) {
					datumRodjenja = LocalDate.parse(line, formater);
					counter++;
				}
				else if(counter==5) {
					jmbag = line;
					studenti.add(new Student(idStudenta, imeStudenta, prezimeStudenta, jmbag, datumRodjenja));
					counter=1;
				}				
			}
		} catch (IOException e) {
			System.err.println(e);
		}


		return studenti;

	}
	
	public static List<Predmet> dohvatiPredmete (List<Profesor> profesori ) {

		int counter=1;
		String line;
		String[] s;
		Long idPredmeta = null;
		Long idProfesora = null;
		String sifraPredmeta =" ";
		String nazivPredmeta = " ";
		Integer ects = 0;
		LocalDate datumRodjenja = null;
		Profesor profesor = null;
		List<Predmet> predmeti = new ArrayList<>();
		Set<Student> studentSet = new HashSet<>();
		Student student = null;
		int index= 0;



		try (BufferedReader in = new BufferedReader(new FileReader("dat/predmeti.txt"))) {
			while ((line = in.readLine()) != null) {

				if(counter==1) {
					idPredmeta = Long.valueOf(line).longValue();
					counter++;
				}
				else if(counter==2) {
					sifraPredmeta = line;
					counter++;
				}
				else if(counter==3) {
					nazivPredmeta = line;
					counter++;
				}
				else if(counter==4) {
					ects = Integer.valueOf(line);
					counter++;
				}
				else if(counter==5) {
					idProfesora = Long.valueOf(line).longValue();
					profesor = dohvatiProfesoraPoId(idProfesora);
					counter++;
				}
				else if(counter==6) {
					s= line.split(" ");
					student = dohvatiStudentPoId( Long.valueOf( s[0]).longValue() );
					studentSet.add(student);
					student = dohvatiStudentPoId( Long.valueOf( s[1]).longValue() );
					studentSet.add(student);

					predmeti
					.forEach(p -> p.setSetStudenti(studentSet));


					predmeti.add(new Predmet(idPredmeta, sifraPredmeta, nazivPredmeta, ects, profesor));
					index++;
					counter=1;

				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}


		return predmeti;

	}

	public static List<Ispit> dohvatiIspite ( List<Predmet> predmeti, List<Student> studenti, List<Profesor> profesori) {

		int counter = 1;
		String line;
		Long idIspita = null;
		Long idPredmeta = null;
		Long idStudenta = null;
		Integer ocjena = 0;
		LocalDateTime datumIspita = null;
		List<Ispit> ispiti = new ArrayList<>();
		Predmet predmet = null;
		Student student = null;
		DateTimeFormatter formater = DateTimeFormatter.ofPattern ("dd.MM.yyyy.'T'HH:mm");



		try (BufferedReader in = new BufferedReader(new FileReader("dat/ispiti.txt"))) {
			while ((line = in.readLine()) != null) {

				if(counter==1) {
					idIspita = Long.valueOf(line).longValue();
					counter++;
				}
				else if(counter==2) {
					idPredmeta = Long.valueOf(line).longValue();
					predmet = dohvatiPredmetPoId(idPredmeta, profesori);
					counter++;
				}
				else if(counter==3) {
					idStudenta = Long.valueOf(line).longValue();
					student = dohvatiStudentPoId(idStudenta);
					counter++;
				}
				else if(counter==4) {
					ocjena = Integer.valueOf(line);
					counter++;
				}
				else if(counter==5) {
					datumIspita = LocalDateTime.parse(line, formater);

					ispiti.add(new Ispit(idIspita, predmet, student, ocjena, datumIspita)  );
					counter=1;
				}


			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return ispiti;

	}
	
	
	
	
	private static List<Integer> dohvatiObrane () {

		int counter = 1;
		List<Integer> ocjenaObrane = new ArrayList<>();
		Integer ocjena = 0;
		String line;

		try (BufferedReader in = new BufferedReader(new FileReader("dat/obrane.txt"))) {
			while ((line = in.readLine()) != null) {


				if(counter==1) {
					ocjena = Integer.valueOf(line);
					ocjenaObrane.add(ocjena) ;
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return ocjenaObrane;

	}
	private static List<Integer> dohvatiZavrsneRadove () {

		
		

		int counter = 1;
		List<Integer> ocjenaZvRad = new ArrayList<>();
		Integer ocjena = 0;
		String line;

		try (BufferedReader in = new BufferedReader(new FileReader("dat/zv_rad.txt"))) {
			while ((line = in.readLine()) != null) {


				if(counter==1) {
					ocjena = Integer.valueOf(line);
					ocjenaZvRad.add(ocjena) ;
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return ocjenaZvRad;

	}
	

	
	public static Predmet dohvatiPredmetPoId (Long id, List<Profesor> profesori) {

		List<Predmet> predmeti = dohvatiPredmete(profesori);
		Predmet predmet = null;



		for (Predmet p : predmeti) {
			if (p.getId() == id) {
				predmet = p;
			}
		}
		return predmet;
	}
	public static Profesor dohvatiProfesoraPoId (Long id) {

		Profesor profesor = null;
		List<Profesor> profesori = dohvatiProfesore();


		for (Profesor p : profesori) {
			if (p.getId() == id) {
				profesor = p;
			}
		}



		return profesor;
	}
	public static Student dohvatiStudentPoId (Long id) {

		Student student = null;
		List<Student> studenti = dohvatiStudente();

		for (Student s : studenti) {
			if (s.getId() == id) {
				student = s;
			}


		}
		return student;
	}
	public static Ispit dohvatiIspitPoId (Long id, List<Predmet> predmeti,List<Student> studenti, List<Profesor> profesori) {

		Ispit ispit = null;
		List<Ispit> ispiti = dohvatiIspite(predmeti,studenti,profesori);

		for (Ispit i : ispiti) {
			if (i.getId() == id) {
				ispit = i;
			}


		}
		return ispit;
	}	
	
	
	
	
	
	
	
}
