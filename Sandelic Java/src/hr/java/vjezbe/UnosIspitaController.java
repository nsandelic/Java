package hr.java.vjezbe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.util.Datoteke;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class UnosIspitaController implements Initializable {

	@FXML
	private TextField idPredmeta;
	@FXML
	private TextField idStudentaTextField;
	@FXML
	private TextField ocjenaTextField;
	@FXML
	private TextField datum;
	@FXML
	private Button unosButton;
	
	List<Predmet> predmeti = new ArrayList<>();
	List<Profesor> profesori = new ArrayList<>();
	List<Student> studenti = new ArrayList<>();
	List<Ispit> ispiti = new ArrayList<>();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		profesori.addAll( Datoteke.dohvatiProfesore() );
		predmeti.addAll( Datoteke.dohvatiPredmete(profesori) );
		studenti.addAll( Datoteke.dohvatiStudente() );
		ispiti.addAll( Datoteke.dohvatiIspite(predmeti, studenti, profesori) );
		
	}

	
	@FXML
	public void unosUDatoteku() {
		
		
		
		
		if(idPredmeta.getText().isEmpty() == true ||  idStudentaTextField.getText().isEmpty() == true || ocjenaTextField.getText().isEmpty() == true || datum.getText().isEmpty() == true  ) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogresan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedece pogreske: ");
			String msg = "";
			String a,b,c,d = null;
			
			if(idPredmeta.getText().isEmpty() == true) {
				a = "Predmet je obavezan podatak\n";
				msg += a; }
			if(idStudentaTextField.getText().isEmpty() == true) {
				b = "ID studenta je obavezan podatak\n";
			msg += b; }
			if(ocjenaTextField.getText().isEmpty() == true) {
				c = "Ocjena je obavezan podatak\n";
			msg += c; }
			if(datum.getText().isEmpty() == true) {
				d = "Datum je obavezan podatak\n";
			msg += d; }
				
				
	
			alert.setContentText(msg);
			alert.showAndWait();
			
			
		} else {
			
			Predmet predmet = Datoteke.dohvatiPredmetPoId( Long.valueOf(idPredmeta.getText() ), profesori);	
			Student student = Datoteke.dohvatiStudentPoId( Long.valueOf(idStudentaTextField.getText() ) );
			LocalDateTime datumVrijeme  = null;
			DateTimeFormatter formater = DateTimeFormatter.ofPattern ("dd.MM.yyyy.'T'HH:mm");
			
		OptionalLong maksimalniId = ispiti.stream()
				.mapToLong(ispit -> ispit.getId()).max();

		
		datumVrijeme = LocalDateTime.parse(datum.getText(), formater );
		Ispit noviIspit = new Ispit(
				maksimalniId.getAsLong() + 1, predmet,
				student, Integer.valueOf(ocjenaTextField.getText()),
				datumVrijeme );
		
		
		  try{
			  FileWriter fstream = new FileWriter("dat/ispiti.txt",true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(noviIspit.getId()+"\n");
			  out.write(noviIspit.getPredmet().getId() +"\n");
			  out.write(noviIspit.getStudent().getId() +"\n");
			  out.write(noviIspit.getOcjena()+"\n");
			  out.write(datumVrijeme +"\n");
			  out.close();
		  }catch (Exception e){
			 System.err.println("Error while writing to file: " +
		          e.getMessage());
		  }
		  
		  //ispiti.addAll( Datoteke.dohvatiIspite(predmeti, studenti, profesori) );
		  
		  Alert alert = new Alert(AlertType.INFORMATION);
		  alert.setTitle("Uspjesno spremanje podataka");
		  alert.setHeaderText("Spremanje podataka u datoteku je uspjesno!");
		  alert.setContentText("Podaci o novom studentu su dodani");

		  alert.showAndWait();

	}
		
	}
		


	
	
	
	@FXML
	public void prikaziPretraguProfesora() {
		 BorderPane root;
		 try {
		root = (BorderPane)FXMLLoader.load(
		getClass().getResource("profesori.fxml"));
		 Main.setMainPage(root);
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		} 
	@FXML
	public void prikaziPretraguStudenta() {
		 BorderPane root;
		 try {
		root = (BorderPane)FXMLLoader.load(
		getClass().getResource("studenti.fxml"));
		 Main.setMainPage(root);
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		} 
	@FXML
	public void prikaziPretraguPredmeta() {
		 BorderPane root;
		 try {
		root = (BorderPane)FXMLLoader.load(
		getClass().getResource("predmeti.fxml"));
		 Main.setMainPage(root);
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		} 
	
	@FXML
	public void prikaziPretraguIspita() {
		 BorderPane root;
		 try {
		root = (BorderPane)FXMLLoader.load(
		getClass().getResource("ispiti.fxml"));
		 Main.setMainPage(root);
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
	}
	
	
	
	@FXML
	public void prikaziUnosProfesora() {
		BorderPane root;
		 try {
		root = (BorderPane)FXMLLoader.load(
		getClass().getResource("unosProfesora.fxml"));
		 Main.setMainPage(root);
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		} 
	

	@FXML
	public void prikaziUnosStudenta() {
		 BorderPane root;
		 
		 try {
		root = (BorderPane)FXMLLoader.load(
		getClass().getResource("unosStudenta.fxml"));
		 Main.setMainPage(root);
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		} 
	
	@FXML
	public void prikaziUnosPredmeta() {
		 BorderPane root;
		 try {
		root = (BorderPane)FXMLLoader.load(
		getClass().getResource("unosPredmeta.fxml"));
		 Main.setMainPage(root);
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		} 
	
	
	@FXML
	public void prikaziUnosIspita() {
		 BorderPane root;
		 try {
		root = (BorderPane)FXMLLoader.load(
		getClass().getResource("unosIspita.fxml"));
		 Main.setMainPage(root);
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		} 
		 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
