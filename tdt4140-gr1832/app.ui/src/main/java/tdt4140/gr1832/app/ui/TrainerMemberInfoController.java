package tdt4140.gr1832.app.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
	
	@FXML
	public void TilLoggUt(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogOut.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
	}
	
	public static void main(String[] args) {
		
	launch(TrainerMemberInfoController.class, args);

	}
}
