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
import tdt4140.gr1832.app.core.ShowUserInfoContainer;
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
		
		ShowUserInfoContainer user = FxApp.getAS().getLoggedInUser();

		String name = user.getName();
		String username = user.getUsername();
		String email = user.getEmail();
		String tlf = user.getPhone();
		String age = Integer.toString(user.getAge());
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