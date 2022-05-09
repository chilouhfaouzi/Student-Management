package application;



import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class SignUp implements Initializable {

	@FXML
	private ComboBox<String> FiliereSelection;

	@FXML
	private TextField FirstNameField; 

	@FXML
	private TextField LastNameField;

	@FXML
	private TextField PasswordField;

	@FXML
	private ComboBox<String>  RoleSelection;

	@FXML
	private Button SignUpButton;

	//liste contenant les donnees a afficher sur combobox
	final ObservableList<String>  listData1 = FXCollections.observableArrayList();

	final ObservableList<String>  listData2 = FXCollections.observableArrayList();


	@Override
	public void initialize(URL url, ResourceBundle rb) {     
		// Lister les elments de Combobox
		FillComboBox();
	}


	public void FillComboBox() {
		try {
			String query1 = "select NomFiliere from filiere";
			Connection cnx = mysqlconnect.getConnection();
			PreparedStatement St1= cnx.prepareStatement(query1) ; 
			ResultSet rsl1 = St1.executeQuery();

			while(rsl1.next()) {
				listData1.add(rsl1.getString("NomFiliere")); 


			}

			String query2 = "select RoleComplet from Role WHERE idRole != 1"; // pour ne pas afficher admin en inscription

			PreparedStatement St2= cnx.prepareStatement(query2) ; 
			ResultSet rsl2 = St2.executeQuery();

			while(rsl2.next()) {
				listData2.add(rsl2.getString("RoleComplet")); 


			}

		} catch(Exception e){    
			e.toString();
		}
		FiliereSelection.setItems(listData1);
		RoleSelection.setItems(listData2);

	} 
	//  METHODE POUR LES ALERTES INFOBOX
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

	public void SignUp() {

		Connection conn = mysqlconnect.getConnection();
		try { 
			PreparedStatement ps= conn.prepareStatement("insert into comptes (PRENOM, NOM, Pssd, Role) values (?,?,?,?)");
			ps.setString(1,FirstNameField.getText());
			ps.setString(2,LastNameField.getText());
			ps.setString(3,PasswordField.getText());
			ps.setString(4,RoleSelection.getValue());	
		//ps.setString(4,FiliereSelection.getValue());



			ps.execute();
			
			
			
			infoBox("You are signed up as XXX Ssuccessfully", "Success", "Info");
			System.out.println("executee2");

			Main m = new Main();

			m.changeScene("Login.fxml");

			conn.close(); }

			catch (Exception e) {
				e.printStackTrace();
				
			}
	

		  } }
