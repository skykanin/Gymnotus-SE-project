package tdt4140.gr1832.app.ui;
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

public class TrainerSettingsController extends WindowController {
	
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
	
}