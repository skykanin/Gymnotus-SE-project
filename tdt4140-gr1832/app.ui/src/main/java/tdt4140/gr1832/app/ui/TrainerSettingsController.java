package tdt4140.gr1832.app.ui;
// fungerende
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

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
	
	@FXML
	JFXRadioButton mannButton;

	@FXML
	JFXRadioButton dameButton;
	
	@FXML
	private void HandleSetOriginalInformation(ActionEvent event) throws IOException {
		this.initialize();
	}
	
	@FXML
	private void HandleSubmitChangesButton(ActionEvent event) throws IOException {
		
		String username = FxApp.getAS().getLoggedInUser().getUsername();
		String gender;
		if (mannButton.isSelected()){
			gender = "0";
		} else if (dameButton.isSelected()) {
			gender = "1"; 
		} else {
			gender = "2";
		}
		if(TrainerSettingsApp.changeUser(username, nameField.getText(),emailField.getText(), tlfField.getText(), ageField.getText(), gender)) {
			FxApp.getAS().setCurrentUser(username);
			return;
		}
		
		System.out.println("Server failed to change userInfo");
	}
	
	@FXML
	private void toggleDameButton(ActionEvent event) throws IOException {
		mannButton.setSelected(false);
		dameButton.setSelected(true);
	}
	
	@FXML
	private void toggleMannButton(ActionEvent event) throws IOException {
		mannButton.setSelected(true);
		dameButton.setSelected(false);
	}
	
	
	@FXML
	public void initialize() {
		
		ShowUserInfoContainer user = FxApp.getAS().getLoggedInUser();

		String name = user.getName();
		String username = user.getUsername();
		String email = user.getEmail();
		String tlf = user.getPhone();
		String age = Integer.toString(user.getAge());
		int gender = user.getGender();

		nameField.setText(name);
		usernameField.setText(username);		
		emailField.setText(email);
		tlfField.setText(tlf);
		ageField.setText(age);
		
		if (gender == 2) {
			return;
		} else if (gender == 1) {
			mannButton.setSelected(false);
			dameButton.setSelected(true);
		} else if (gender == 0){
			mannButton.setSelected(true);
			dameButton.setSelected(false);
		}
		
		
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