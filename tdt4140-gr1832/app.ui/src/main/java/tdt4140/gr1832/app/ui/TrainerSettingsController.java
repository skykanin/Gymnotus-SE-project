package tdt4140.gr1832.app.ui;
// fungerende
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.TrainerSettingsApp;

public class TrainerSettingsController extends Application {
	
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
	JFXButton setOriginalInformation;
	
	@FXML
	JFXButton submitChangesButton;
	
	
//	@FXML
//	JFXTextField genderField;
//	
	@FXML
	private void HandleSetOriginalInformation(ActionEvent event) throws IOException {
		this.initialize();
	}
	
	@FXML
	private void HandleSubmitChangesButton(ActionEvent event) throws IOException {
		//funksjonalitet for å sende felt til database
	}
	
	/*
	  HENRIKs ARBEID START
	 */
	
	@FXML
	private void TilInnstillinger(ActionEvent event) throws IOException {
		;
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
	
	/*
	 HENRIKs ARBEID SLUTT
	 */
	
	@FXML
	public void initialize() {
		
		String name = TrainerSettingsApp.getName();
		String username = TrainerSettingsApp.getUsername();
		String email = TrainerSettingsApp.getEmail();
		String tlf = TrainerSettingsApp.getTlf();
		String age = TrainerSettingsApp.getAge();
//		String gender = TrainerMemberInfoApp.getGender();

		nameField.setText(name);
		usernameField.setText(username);		
		emailField.setText(email);
		tlfField.setText(tlf);
		ageField.setText(age);
//		genderField.setText(gender);
		
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("TrainerSettings.fxml")); //Husk å endre til tilhørende fxml fil sitt navn.
		Scene scene = new Scene(root, 1200, 660);
        primaryStage.setTitle("MemberInfoView");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(TrainerSettingsController.class, args);
	}
	
	
}
