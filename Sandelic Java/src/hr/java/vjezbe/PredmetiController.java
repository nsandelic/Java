package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class PredmetiController implements Initializable{

	@FXML
	private TextField sifraTextField;
	@FXML
	private TextField nazivTextField;
	@FXML
	private TextField ectsTextField;
	@FXML
	private TextField nositeljImeTextField;
	@FXML
	private TextField nositeljPrezimeTextField;
	@FXML
	private Button pretragaButton;
	@FXML
	private TableView<Predmet> tbl;
	@FXML
	private TableColumn<Predmet,String> col_sifra;
	@FXML
	private TableColumn<Predmet,String> col_naziv;
	@FXML
	private TableColumn<Predmet,String> col_ects;
	@FXML
	private TableColumn<Predmet,String> col_nositeljIme;
	@FXML
	private TableColumn<Predmet,String> col_nositeljPrezime;
	ObservableList<Predmet> list = FXCollections.observableArrayList();
	
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		List<Predmet> predmeti = new ArrayList<>();
		List<Profesor> profesori = new ArrayList<>();
		profesori.addAll( Datoteke.dohvatiProfesore() );
		predmeti.addAll( Datoteke.dohvatiPredmete(profesori) );
		list.addAll(predmeti);
		
		
		
		col_sifra.setCellValueFactory( new PropertyValueFactory<>("sifraPredmeta"));
		col_naziv.setCellValueFactory( new PropertyValueFactory<>("nazivPredmeta"));
		col_ects.setCellValueFactory( new PropertyValueFactory<>("brojEctsBodova"));
		col_nositeljIme.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>,
				ObservableValue<String>>() {
				 @Override
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Predmet, String> list) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 property.setValue(
				list.getValue().getNositeljPredmeta().getImeOsobe());
				 return property;
				 }
				 });
		col_nositeljPrezime.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>,
				ObservableValue<String>>() {
				 @Override
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Predmet, String> list) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 property.setValue(
				list.getValue().getNositeljPredmeta().getPrezimeOsobe());
				 return property;
				 }
				 });
		
		
		
		tbl.setItems(list);
		
	}

	
	
	
	
	
	
	@FXML
	public void filterPredmet() {
		List<Profesor> profesor = new ArrayList<>( Datoteke.dohvatiProfesore());
		ObservableList<Predmet> predmet = FXCollections.observableArrayList(Datoteke.dohvatiPredmete(profesor) );

		if(sifraTextField.getText().isEmpty() == false ) {
			List<Predmet> sifraFilter = predmet.stream()
					.filter( s -> s.getSifraPredmeta().startsWith(sifraTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Predmet> filtriraniPredmeti = FXCollections.observableArrayList(sifraFilter);
			tbl.setItems(filtriraniPredmeti);
		} 
		else if(nazivTextField.getText().isEmpty() == false ) {
			List<Predmet> nazivFilter = predmet.stream()
					.filter( s -> s.getNazivPredmeta().startsWith(nazivTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Predmet> filtriraniPredmeti = FXCollections.observableArrayList(nazivFilter);
			tbl.setItems(filtriraniPredmeti);
		}
		else if(ectsTextField.getText().isEmpty() == false ) {
			List<Predmet> ectsFilter = predmet.stream()
					.filter( s -> s.getBrojEctsBodova().toString().startsWith(ectsTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Predmet> filtriraniPredmeti = FXCollections.observableArrayList(ectsFilter);
			tbl.setItems(filtriraniPredmeti);
		}
		else if(nositeljImeTextField.getText().isEmpty() == false ) {
			List<Predmet> nosImeFilter = predmet.stream()
					.filter( s -> s.getNositeljPredmeta().getImeOsobe().startsWith(nositeljImeTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Predmet> filtriraniPredmeti = FXCollections.observableArrayList(nosImeFilter);
			tbl.setItems(filtriraniPredmeti);
		}
		else if(nositeljPrezimeTextField.getText().isEmpty() == false ) {
			List<Predmet> nosPrezimeFilter = predmet.stream()
					.filter( s -> s.getNositeljPredmeta().getPrezimeOsobe().startsWith(nositeljPrezimeTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Predmet> filtriraniPredmeti = FXCollections.observableArrayList(nosPrezimeFilter);
			tbl.setItems(filtriraniPredmeti);
		}
		
		
		
		
		
		
		else tbl.setItems(predmet);
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
