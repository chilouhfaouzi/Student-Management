package application;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.sql.*;
import java.util.ResourceBundle;




	public class LoginController {

		   @FXML
		    private TextField Usernamefield;

		    @FXML
		    private Label labelmessage;

		    @FXML
		    private Button loginbutton;

		    @FXML
		    private PasswordField passwordfield;

		
		 
		@FXML
			 private void checklogin() throws IOException, InterruptedException {
			///
			
			
			if (Usernamefield.getText().isEmpty() && passwordfield.getText().isEmpty()) {
				
				labelmessage.setText("Both Username and password are empty!");
				 } 
			
			
			else{
				

	
			Connection connectDB = mysqlconnect.getConnection();


			String verifyLogin ="SELECT count(1) FROM comptes WHERE Username = '" + Usernamefield.getText() + "' AND Pssd = '"+ passwordfield.getText() +"'"; 
			// une seule possibilite d'unicite de resultat entre username et password
		try {

			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);


			while(queryResult.next()) {
				if (queryResult.getInt(1) == 1 ) {
					labelmessage.setText("Login successful,loading interface..."); 
					

					
					Main m = new Main();

					m.changeScene("accueil.fxml");

				} else { labelmessage.setText("Invalid Login, please try again.");
				infoBox("Please enter correct Username and Password", "ERROR", "Failed");}
			}
		}

			
		catch (Exception e) {

			e.printStackTrace();
		}}}
		
		
		// apres la saisie de username
		@FXML
		public void onEnterToPasswordFocus(KeyEvent e){
			if(e.getCode().equals(KeyCode.ENTER)) {
				System.out.print("fhdhfd to focus");
				Usernamefield.setFocusTraversable(false);
				passwordfield.setFocusTraversable(true);
				passwordfield.requestFocus();
				
			}
			   
		        
		}
		
		// Apres la saisie de mot de pass
		@FXML
		public void onEnter(KeyEvent e) throws IOException, InterruptedException{
			    if(e.getCode().equals(KeyCode.ENTER))
			        this.checklogin();
			}
		
		public void  SignUpSwitchScene() throws IOException {
	
			Main signupscene = new Main();
			signupscene.changeScene("SignUp.fxml");
			
		}
		
		//  METHODE POUR LES ALERTES INFOBOX
	    public static void infoBox(String infoMessage, String headerText, String title) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setContentText(infoMessage);
	        alert.setTitle(title);
	        alert.setHeaderText(headerText);
	        alert.showAndWait();
	    }


		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
	    
	}


		///////
		
	
	
