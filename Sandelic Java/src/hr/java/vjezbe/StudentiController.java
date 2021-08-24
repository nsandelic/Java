package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class StudentiController implements Initializable {

	@FXML
	private TextField jmbagTextField;
	@FXML
	private TextField prezimeTextField;
	@FXML
	private TextField imeTextField;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button pretragaButton;
	@FXML
	private TableView<Student> tbl;
	@FXML
	private TableColumn<Student,String> col_jmbag;
	@FXML
	private TableColumn<Student,String> col_prezime;
	@FXML
	private TableColumn<Student,String> col_ime;
	@FXML
	private TableColumn<Student,String> col_datum;
	
	

	ObservableList<Student> list = FXCollections.observableArrayList();
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		List<Student> studenti = new ArrayList<>();
		studenti.addAll( Datoteke.dohvatiStudente() );
		list.addAll(studenti);
		
		
		
		col_jmbag.setCellValueFactory( new PropertyValueFactory<>("jmbag"));
		col_prezime.setCellValueFactory( new PropertyValueFactory<>("prezimeOsobe"));
		col_ime.setCellValueFactory( new PropertyValueFactory<>("imeOsobe"));
		col_datum.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Student, String>,
				ObservableValue<String>>() {
				 @Override
				 public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Student, String> student) {
				 SimpleStringProperty property = new
				SimpleStringProperty();
				 DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				 property.setValue(
				student.getValue().getDatumRodjenja().format(formatter));
				 return property;
				 }
				 });
		
		tbl.setItems(list);
		
	}

	
	@FXML
	public void filterStudent() {
		ObservableList<Student> student = FXCollections.observableArrayList(Datoteke.dohvatiStudente() );

		if(jmbagTextField.getText().isEmpty() == false ) {
			List<Student> jmbagFilter = student.stream()
					.filter( s -> s.getJmbag().startsWith(jmbagTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Student> filtriraniStudenti = FXCollections.observableArrayList(jmbagFilter);
			tbl.setItems(filtriraniStudenti);
		} 
		else  if(prezimeTextField.getText().isEmpty() == false ) {
			List<Student> prezimeFilter = student.stream()
					.filter( s -> s.getPrezimeOsobe().startsWith(prezimeTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Student> filtriraniStudenti = FXCollections.observableArrayList(prezimeFilter);
			tbl.setItems(filtriraniStudenti);
		} 
		else  if(imeTextField.getText().isEmpty() == false ) {
			List<Student> imeFilter = student.stream()
					.filter( s -> s.getImeOsobe().startsWith(imeTextField.getText()))
					.collect(Collectors.toList());
			ObservableList<Student> filtriraniStudenti = FXCollections.observableArrayList(imeFilter);
			tbl.setItems(filtriraniStudenti);
		} 
		else  if(datePicker.getValue().toString().isEmpty()== false ) {
			List<Student> datumFilter = student.stream()
					.filter( s -> s.getDatumRodjenja().toString().equals(datePicker.getPromptText()))
					.collect(Collectors.toList() );
			ObservableList<Student> filtriraniStudenti = FXCollections.observableArrayList(datumFilter);
			tbl.setItems(filtriraniStudenti);
		}  else tbl.setItems(student);
		
		
		
		
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
