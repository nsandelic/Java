package hr.java.vjezbe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

public class UnosStudentaController implements Initializable {

	@FXML
	private TextField jmbagTextField;
	@FXML
	private TextField prezimeTextField;
	@FXML
	private TextField imeTextField;
	@FXML
	private TextField datePicker;
	@FXML
	private Button unosButton;

	List<Student> studenti = new ArrayList<>();
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		studenti.addAll( Datoteke.dohvatiStudente() );
		
	}

	
	
	
	@FXML
	public void unosUDatoteku() {
		
		
		
		
		if(jmbagTextField.getText().isEmpty() == true ||  prezimeTextField.getText().isEmpty() == true || imeTextField.getText().isEmpty() == true || datePicker.getText().isEmpty() == true  ) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogresan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedece pogreske: ");
			String msg = "";
			String a,b,c,d = null;
			
			if(jmbagTextField.getText().isEmpty() == true) {
				a = "JMBAG je obavezan podatak\n";
				msg += a; }
			if(prezimeTextField.getText().isEmpty() == true) {
				b = "Prezime je obavezan podatak\n";
			msg += b; }
			if(imeTextField.getText().isEmpty() == true) {
				c = "Ime je obavezan podatak\n";
			msg += c; }
			if(datePicker.getText().isEmpty() == true) {
				d = "Datum je obavezan podatak\n";
			msg += d; }
				
				
	
			alert.setContentText(msg);
			alert.showAndWait();
			
			
		} else {
			
			LocalDate datum  = null;
			DateTimeFormatter formater = DateTimeFormatter.ofPattern ("dd.MM.yyyy.");
			
			
		OptionalLong maksimalniId = studenti.stream()
				.mapToLong(student -> student.getId()).max();

		
		datum = LocalDate.parse(datePicker.getText(), formater );
		Student noviStudent = new Student(
				maksimalniId.getAsLong() + 1, imeTextField.getText(),
				prezimeTextField.getText(), jmbagTextField.getText(),
				datum);
		
		
		  try{
			  FileWriter fstream = new FileWriter("dat/studenti.txt",true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(noviStudent.getId()+"\n");
			  out.write(noviStudent.getImeOsobe() +"\n");
			  out.write(noviStudent.getPrezimeOsobe() +"\n");
			  out.write(noviStudent.getDatumRodjenja()+"\n");
			  out.write(noviStudent.getJmbag()+"\n");
			  out.close();
		  }catch (Exception e){
			 System.err.println("Error while writing to file: " +
		          e.getMessage());
		  }
		  
		  //studenti.addAll( Datoteke.dohvatiStudente() );
		  
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
