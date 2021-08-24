package hr.java.vjezbe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.util.Datoteke;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class UnosProfesoraController implements Initializable{

	@FXML
	private TextField sifraTextField;
	@FXML
	private TextField prezimeTextField;
	@FXML
	private TextField imeTextField;
	@FXML
	private TextField titulaTextField;
	@FXML
	private Button unosButton;
	List<Profesor> profesori = new ArrayList<>();




	@Override
	public void initialize(URL location, ResourceBundle resources) {

		profesori.addAll( Datoteke.dohvatiProfesore() );

		
		
		

	}



	@FXML
	public void unosUDatoteku() {
		
		
		
		
		if(sifraTextField.getText().isEmpty() == true ||  prezimeTextField.getText().isEmpty() == true || imeTextField.getText().isEmpty() == true || titulaTextField.getText().isEmpty() == true  ) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogresan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedece pogreske: ");
			String msg = "";
			String a,b,c,d = null;
			
			if(sifraTextField.getText().isEmpty() == true) {
				a = "Sifra je obavezan podatak\n";
				msg += a; }
			if(prezimeTextField.getText().isEmpty() == true) {
				b = "Prezime je obavezan podatak\n";
			msg += b; }
			if(imeTextField.getText().isEmpty() == true) {
				c = "Ime je obavezan podatak\n";
			msg += c; }
			if(titulaTextField.getText().isEmpty() == true) {
				d = "Titula je obavezan podatak\n";
			msg += d; }
				
				
	
			alert.setContentText(msg);
			alert.showAndWait();
			
			
		} else {
			
			
		OptionalLong maksimalniId = profesori.stream()
				.mapToLong(profesor -> profesor.getId()).max();

		Profesor noviProfesor = new Profesor(
				maksimalniId.getAsLong() + 1, sifraTextField.getText(),
				imeTextField.getText(), prezimeTextField.getText(),
				titulaTextField.getText());
		
		
		  try{
			  FileWriter fstream = new FileWriter("dat/profesori.txt",true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(noviProfesor.getId()+"\n");
			  out.write(noviProfesor.getSifraProfesora()+"\n");
			  out.write(noviProfesor.getImeOsobe() +"\n");
			  out.write(noviProfesor.getPrezimeOsobe() +"\n");
			  out.write(noviProfesor.getTitulaProfesora()+"\n");
			  out.close();
		  }catch (Exception e){
			 System.err.println("Error while writing to file: " +
		          e.getMessage());
		  }
		  
		  profesori.addAll( Datoteke.dohvatiProfesore() );
		  
		  Alert alert = new Alert(AlertType.INFORMATION);
		  alert.setTitle("Uspjesno spremanje podataka");
		  alert.setHeaderText("Spremanje podataka u datoteku je uspjesno!");
		  alert.setContentText("Podaci o novom profesoru su dodani");

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
