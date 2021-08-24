package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class IspitiController implements Initializable {

	@FXML
	private TextField predmetTextField;
	@FXML
	private TextField imeStudentaTextField;
	@FXML
	private TextField prezimeStudentaTextField;
	@FXML
	private TextField ocjenaTextField;
	@FXML
	private DatePicker datum;
	@FXML
	private Button pretragaButton;
	@FXML
	private TableView<Ispit> tbl;
	@FXML
	private TableColumn<Ispit,String> col_predmet;
	@FXML
	private TableColumn<Ispit,String> col_ime;
	@FXML
	private TableColumn<Ispit,String> col_prezime;
	@FXML
	private TableColumn<Ispit,Integer> col_ocjena;
	@FXML
	private TableColumn<Ispit,String> col_datum;
	
	
	ObservableList<Ispit> list = FXCollections.observableArrayList();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		List<Profesor> profesori = new ArrayList<>( Datoteke.dohvatiProfesore());
		List<Predmet> predmeti = new ArrayList<>( Datoteke.dohvatiPredmete(profesori));
		List<Student> studenti = new ArrayList<>(Datoteke.dohvatiStudente() );
		List<Ispit> ispiti = new ArrayList<>( Datoteke.dohvatiIspite(predmeti,studenti, profesori) );
		list.addAll(ispiti);
		
		
		
		col_predmet.setCellValueFactory( 
				new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				 @Override
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispiti) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 property.setValue(
						 ispiti.getValue().getPredmet().getNazivPredmeta());
				return property;
				 }
				 });
		col_ime.setCellValueFactory( /*new PropertyValueFactory<>("imeOsobe"));*/
				new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				 @Override
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispiti) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 property.setValue(
						 ispiti.getValue().getStudent().getImeOsobe());
				return property;
				 }
				 });
		col_prezime.setCellValueFactory( /*new PropertyValueFactory<>("prezimeOsobe"))*/
				new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				 @Override
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispit) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 property.setValue(
						 ispit.getValue().getStudent().getPrezimeOsobe());
				return property;
				 }
				 });
		col_ocjena.setCellValueFactory( new PropertyValueFactory<>("ocjena"));
		col_datum.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				 @Override
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispit) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				 property.setValue(
						 ispit.getValue().getDatumIVrijeme().format(formatter));
				 return property;
				 }
				 });
		
		tbl.setItems(list);
		
	}

	@FXML
	public void filterIspit() {
		List<Profesor> profesor = new ArrayList<>( Datoteke.dohvatiProfesore());
		List<Student> studenti = new ArrayList<>(Datoteke.dohvatiStudente());
		List<Predmet> predmeti = new ArrayList<>(Datoteke.dohvatiPredmete(profesor) );
		ObservableList<Ispit> ispit = FXCollections.observableArrayList(Datoteke.dohvatiIspite(predmeti, studenti, profesor) );

		
		if(predmetTextField.getText().isEmpty() == false ) {
			List<Ispit> prdmetFilter = ispit.stream()
					.filter( s -> s.getPredmet().getNazivPredmeta().startsWith(predmetTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Ispit> filtriraniIspiti = FXCollections.observableArrayList(prdmetFilter);
			tbl.setItems(filtriraniIspiti);
		}
		else if(ocjenaTextField.getText().isEmpty() == false ) {
			List<Ispit> sifraFilter = ispit.stream()
					.filter( s -> s.getOcjena().toString().startsWith(ocjenaTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Ispit> filtriraniIspiti = FXCollections.observableArrayList(sifraFilter);
			tbl.setItems(filtriraniIspiti);
		} else tbl.setItems(ispit);
		
		
		
		
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
