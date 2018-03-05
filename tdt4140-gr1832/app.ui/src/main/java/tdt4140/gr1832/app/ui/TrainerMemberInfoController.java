package tdt4140.gr1832.app.ui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.TrainerMemberInfoApp;

public class TrainerMemberInfoController extends Application {
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
	
	
	
	
	
	
	//start
	public void start(Stage stage) throws Exception {
		//launches from the fxml-file
		Parent root = FXMLLoader.load(getClass().getResource("TrainerMemberInfo.fxml")); //Husk å endre til tilhørende fxml fil sitt navn.
		Scene scene = new Scene(root, 1200, 660);
        stage.setTitle("MemberInfoView");
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	public void initialize() {
		
		String height=TrainerMemberInfoApp.getHeight();
		String date=TrainerMemberInfoApp.getDate();
		String weight=TrainerMemberInfoApp.getWeight();
		String steps=TrainerMemberInfoApp.getSteps();		
		String restingHR=TrainerMemberInfoApp.getRestingHR();
		String name = TrainerMemberInfoApp.getName();
		String username = TrainerMemberInfoApp.getUsername();
		String email = TrainerMemberInfoApp.getEmail();
		String tlf = TrainerMemberInfoApp.getTlf();
		String age = TrainerMemberInfoApp.getAge();
		String gender = TrainerMemberInfoApp.getGender();
		
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
		
	}

	public void datePicker() {
	return;
	}
	
	public static void main(String[] args) {
		
	launch(TrainerMemberInfoController.class, args);
	
	}
}
