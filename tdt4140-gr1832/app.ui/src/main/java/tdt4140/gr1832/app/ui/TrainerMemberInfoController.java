package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
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
	
	
	public static String userID;
	
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
		}
		
	}

	public void datePicker() {
	return;
	}
	
	@FXML
	private void TilInnstillinger(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerSettings.fxml", event);
	}
	
	@FXML
	private void TilDashboard(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerDashboard.fxml", event);
	}
	
	@FXML
	private void TilMedlemmer(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerMembers.fxml", event);
	}
	
	@FXML
	private void TilTreningsprogram(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerTrainingProgramOverview.fxml", event);
	}
	
	private void NavigerTilSide(String filnavn, ActionEvent event) throws IOException {
		Parent LoginScreen_parent = FXMLLoader.load(getClass().getResource(filnavn));
		Scene LoginScreen_scene = new Scene(LoginScreen_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(LoginScreen_scene);
		app_stage.show();
	}
	
	
	public static void main(String[] args) {
		
	launch(TrainerMemberInfoController.class, args);
	
	}


}
