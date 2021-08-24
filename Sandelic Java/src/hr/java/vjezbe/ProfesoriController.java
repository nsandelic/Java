package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ProfesoriController implements Initializable{

	@FXML
	private TextField sifraTextField;
	@FXML
	private TextField prezimeTextField;
	@FXML
	private TextField imeTextField;
	@FXML
	private TextField titulaTextField;
	@FXML
	private Button pretragaButton;
	@FXML
	private TableView<Profesor> tbl;
	@FXML
	private TableColumn<Profesor,String> col_sifra;
	@FXML
	private TableColumn<Profesor,String> col_prezime;
	@FXML
	private TableColumn<Profesor,String> col_ime;
	@FXML
	private TableColumn<Profesor,String> col_titula;
	ObservableList<Profesor> list = FXCollections.observableArrayList();




	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<Profesor> profesori = new ArrayList<>();
		profesori.addAll( Datoteke.dohvatiProfesore() );
		list.addAll(profesori);
	



		col_sifra.setCellValueFactory( new PropertyValueFactory<>("sifraProfesora"));
		col_prezime.setCellValueFactory( new PropertyValueFactory<>("imeOsobe"));
		col_ime.setCellValueFactory( new PropertyValueFactory<>("prezimeOsobe"));
		col_titula.setCellValueFactory( new PropertyValueFactory<>("titulaProfesora"));

		tbl.setItems(list);

	}



	@FXML
	public void filterStudent() {
		ObservableList<Profesor> profesor = FXCollections.observableArrayList(Datoteke.dohvatiProfesore() );

		if(sifraTextField.getText().isEmpty() == false ) {
			List<Profesor> sifraFilter = profesor.stream()
					.filter( s -> s.getSifraProfesora().startsWith(sifraTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Profesor> filtriraniProfesori = FXCollections.observableArrayList(sifraFilter);
			tbl.setItems(filtriraniProfesori);
		} 
		else if(prezimeTextField.getText().isEmpty() == false ) {
			List<Profesor> prezimeFilter = profesor.stream()
					.filter( s -> s.getPrezimeOsobe().startsWith(prezimeTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Profesor> filtriraniProfesori = FXCollections.observableArrayList(prezimeFilter);
			tbl.setItems(filtriraniProfesori);
		} 
		else if(imeTextField.getText().isEmpty() == false ) {
			List<Profesor> imeFilter = profesor.stream()
					.filter( s -> s.getImeOsobe().startsWith(imeTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Profesor> filtriraniProfesori = FXCollections.observableArrayList(imeFilter);
			tbl.setItems(filtriraniProfesori);
		} 
		else if(titulaTextField.getText().isEmpty() == false ) {
			List<Profesor> titulaFilter = profesor.stream()
					.filter( s -> s.getTitulaProfesora().startsWith(titulaTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Profesor> filtriraniProfesori = FXCollections.observableArrayList(titulaFilter);
			tbl.setItems(filtriraniProfesori);
		} 
		else tbl.setItems(profesor);
		
		
		
		

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
