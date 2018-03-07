package tdt4140.gr1832.app.ui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;

import javax.swing.ButtonGroup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.RegisterUser;
import tdt4140.gr1832.app.core.User;

public class RegisterScreenController {
	
	private 	User user;  //
	private 	RegisterUser r; 
	private int gender;
	
	//stian kode:
	
		//@FXML
		//private Text toStringText;
		@FXML
		private JFXTextField setUsernameTextField;
		@FXML
		private JFXTextField setNameTextField;
		@FXML
		private JFXTextField setEmailTextField;
		@FXML
		private JFXTextField setAgeTextField;
		@FXML
		private JFXTextField setPhoneNumberTextField;
		@FXML
		JFXPasswordField setPasswordTextField;
		@FXML
	private RadioButton updateGenderMale;
		@FXML
		private RadioButton updateGenderFemale;
		@FXML
	private ToggleGroup genderGroup;
		
		@FXML
		private void initialize() {
			user = new User();
			genderGroup = new ToggleGroup();
			this.updateGenderMale.setToggleGroup(genderGroup);
			this.updateGenderFemale.setToggleGroup(genderGroup);
			//updateGenderMale.setDisable(true);
			//updateGenderFemale.setDisable(true);
		}
		
		private String getStringFromTextField(JFXTextField textField) {
			return (textField.getText());
		}
		private String getStringFromPasswordField(JFXPasswordField passwordField) {
			return (passwordField.getText());
		}
		private int getIntFromTextField(JFXTextField textField) {
			return Integer.parseInt(textField.getText());
		}
		//Hente ut informasjonen og setter inn i user-objektet:

		public void radioButtonChanged( ) {
			if (this.genderGroup.getSelectedToggle().equals(this.updateGenderMale)) {
				this.gender = 1;
			}
			if (this.genderGroup.getSelectedToggle().equals(this.updateGenderMale)) {
				this.gender = 0;
			}
			
//			int  gender;
//			if (updateGenderMale.isSelected()) {
//				gender= 1;
//			}
//			else if (updateGenderFemale.isSelected()) {
//				gender= 0;
//			}
			
		}
		
		@FXML
		private void updateAllInfo() {
//			radioSelect(event);
			radioButtonChanged(); //setter gender
			String username = getStringFromTextField(setUsernameTextField);
			String password = getStringFromPasswordField(setPasswordTextField);
			String name = getStringFromTextField(setNameTextField);
			int age = getIntFromTextField(setAgeTextField);
			String phone = getStringFromTextField(setPhoneNumberTextField);
			String email = getStringFromTextField(setEmailTextField);
			r = new RegisterUser();
			user = new User(username, password, name, age, gender, email, phone );
			r.registerUser(user);
			initialize();
		}
		
		@FXML
		private void TilDashboard(ActionEvent event) throws IOException {
			initialize();
			updateAllInfo();
			NavigerTilSide("TrainerDashboard.fxml", event);
		}

		
		private void NavigerTilSide(String filnavn, ActionEvent event) throws IOException {
			Parent LoginScreen_parent = FXMLLoader.load(getClass().getResource(filnavn));
			Scene LoginScreen_scene = new Scene(LoginScreen_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(LoginScreen_scene);
			app_stage.show();
		}
		
		
		
		
		
		
	}

