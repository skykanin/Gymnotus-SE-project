package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.TrainerMemberInfoApp;


public class TrainerMemberInfoController extends WindowController {
	@FXML
	JFXTextField heightField;
	
	@FXML
	JFXTextField dateField;
	
	@FXML
	JFXTextField weightField;
	
	@FXML
	JFXTextField stepsField;
	
	@FXML
	JFXTextField restingHRField;

	@FXML
	JFXTextField nameField;
	
	@FXML
	JFXTextField usernameField;
	
	@FXML
	JFXTextField emailField;
	
	@FXML
	JFXTextField tlfField;
	
	@FXML
	JFXTextField ageField;
	
	@FXML
	JFXTextField genderField;
	
	@FXML
	JFXDatePicker datePickerField;
	
	@FXML
    private Label Medlemsnavn;
	
	public static String userID;
	
	//start
	public void start(Stage stage) throws Exception {
		//launches from the fxml-file
		//HVORDAN SJEKKER JEG OM ANON ER TRUE HER? 
		//HVIS DET ER TRUE VIL JEG HELLER ÅPNE ANONYMOUSTrainerMemberInfo.fxml
		Parent root = FXMLLoader.load(getClass().getResource("TrainerMemberInfo.fxml")); //Husk å endre til tilhørende fxml fil sitt navn.
		Scene scene = new Scene(root, 1200, 660);
        stage.setTitle("MemberInfoView");
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	public void initialize() {
		if (userID == null) {
			tdt4140.gr1832.app.core.TrainerMemberInfoApp app = new TrainerMemberInfoApp();
			app.requestUserInformation_ID("1");
			app.requestHealthInformation_ID("1");
			String height=app.getHeight();
			String date=app.getDate();
			String weight=app.getWeight();
			String steps=app.getSteps();		
			String restingHR=app.getRestingHR();
			String name =app.getName();
			String username = app.getUsername();
			String email = app.getEmail();
			String tlf = app.getTlf();
			String age =app.getAge();
			String gender = app.getGender();
			
			heightField.setText(height);
			dateField.setText(date);		
			weightField.setText(weight);
			stepsField.setText(steps);
			restingHRField.setText(restingHR);
			nameField.setText(name);
			usernameField.setText(username);		
			emailField.setText(email);
			tlfField.setText(tlf);
			ageField.setText(age);
			genderField.setText(gender);
			datePickerField.setDisable(true);
			
		    Medlemsnavn.setText("Brukerinformasjonen til " + app.getName());		

			
		} else {
		tdt4140.gr1832.app.core.TrainerMemberInfoApp app = new TrainerMemberInfoApp();
		app.requestUserInformation_ID(userID);
		app.requestHealthInformation_ID(userID);
		
		String height=app.getHeight();
		String date=app.getDate();
		String weight=app.getWeight();
		String steps=app.getSteps();		
		String restingHR=app.getRestingHR();
		String name =app.getName();
		String username = app.getUsername();
		String email = app.getEmail();
		String tlf = app.getTlf();
		String age =app.getAge();
		String gender = app.getGender();
		/*if (!(anon=true)){ Gjør alt under. Hvis ikke : 
			heightField.setText("Bruker");
		dateField.setText(date);		
		weightField.setText(weight);
		stepsField.setText(steps);
		restingHRField.setText(restingHR);
		nameField.setText(name);
		usernameField.setText(username);		
		emailField.setText(email);
		tlfField.setText(tlf);
		ageField.setText(age);
		genderField.setText(gender);
		datePickerField.setDisable(true);
		
		
		
		}*/
		heightField.setText(height);
		dateField.setText(date);		
		weightField.setText(weight);
		stepsField.setText(steps);
		restingHRField.setText(restingHR);
		nameField.setText(name);
		usernameField.setText(username);		
		emailField.setText(email);
		tlfField.setText(tlf);
		ageField.setText(age);
		genderField.setText(gender);
		datePickerField.setDisable(true);
		
	    Medlemsnavn.setText("Brukerinformasjonen til " + app.getName());
		}
		
	}

	public void datePicker() {
		//Not yet implemented
	return;
	}
	
	


}
