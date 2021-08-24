package hr.java.vjezbe.sortiranje;

import java.util.Comparator;
import hr.java.vjezbe.entitet.Student;

public class StudentSorter implements Comparator<Student> {

	
	
	public int compare(Student st1, Student st2) {
		
		if( st1.getPrezimeOsobe().compareTo(st2.getPrezimeOsobe()) == 1 ) {
		return 1;
		}
		else if ( st1.getPrezimeOsobe().compareTo(st2.getPrezimeOsobe()) == -1 ) {
		return -1;
		}
		else if (st1.getPrezimeOsobe().compareTo(st2.getPrezimeOsobe()) == 0){
			if( st1.getImeOsobe().compareTo(st2.getImeOsobe()) == 1 ) {
				return 1;
			}
			else if( st1.getImeOsobe().compareTo(st2.getImeOsobe()) == -1 ) {
				return -1;
			}
		}
		return 0;
		
		
		
	}

	

	

	
	
	

}
