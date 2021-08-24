package hr.java.vjezbe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

public class UnosPredmetaController implements Initializable{

	@FXML
	private TextField sifraTextField;
	@FXML
	private TextField nazivTextField;
	@FXML
	private TextField ectsTextField;
	@FXML
	private TextField idProfesoraTextField;
	@FXML
	private TextField idStudenataTextField;
	@FXML
	private Button unosButton;

	List<Predmet> predmeti = new ArrayList<>();
	List<Profesor> profesori = new ArrayList<>();
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		profesori.addAll( Datoteke.dohvatiProfesore() );
		predmeti.addAll( Datoteke.dohvatiPredmete(profesori) );
		
	}

	
	
	@FXML
	public void unosUDatoteku() {
		
		
		
		
		if(sifraTextField.getText().isEmpty() == true ||  nazivTextField.getText().isEmpty() == true || ectsTextField.getText().isEmpty() == true || idProfesoraTextField.getText().isEmpty() == true || idStudenataTextField.getText().isEmpty() == true ) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogresan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedece pogreske: ");
			String msg = "";
			String a,b,c,d,e = null;
			
			if(sifraTextField.getText().isEmpty() == true) {
				a = "Sifra je obavezan podatak\n";
				msg += a; }
			if(nazivTextField.getText().isEmpty() == true) {
				b = "Naziv predmeta je obavezan podatak\n";
			msg += b; }
			if(ectsTextField.getText().isEmpty() == true) {
				c = "ECTS je obavezan podatak\n";
			msg += c; }
			if(idProfesoraTextField.getText().isEmpty() == true) {
				d = "ID nositelja je obavezan podatak\n";
			msg += d; }
			if(idStudenataTextField.getText().isEmpty() == true) {
				e = "ID studenata je obavezan podatak\n";
			msg += e; }
				
				
				
	
			alert.setContentText(msg);
			alert.showAndWait();
			
			
		} else {
			
			
		Profesor nositelj = Datoteke.dohvatiProfesoraPoId( Long.valueOf(idProfesoraTextField.getText() ));	
			
		OptionalLong maksimalniId = predmeti.stream()
				.mapToLong(predmet -> predmet.getId()).max();

		Predmet noviPredmet = new Predmet(
				maksimalniId.getAsLong() + 1, sifraTextField.getText(),
				nazivTextField.getText(), Integer.valueOf(ectsTextField.getText()),
				nositelj );
		
		
		  try{
			  FileWriter fstream = new FileWriter("dat/predmeti.txt",true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(noviPredmet.getId()+"\n");
			  out.write(noviPredmet.getSifraPredmeta()+"\n");
			  out.write(noviPredmet.getNazivPredmeta() +"\n");
			  out.write(noviPredmet.getBrojEctsBodova() +"\n");
			  out.write(noviPredmet.getNositeljPredmeta().getId()+"\n");
			  out.write( idStudenataTextField.getText().toString() +"\n");
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
