package tdt4140.gr1832.app.ui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import tdt4140.gr1832.app.core.RegisterUser;
import tdt4140.gr1832.app.core.User;

public class RegisterScreenController extends WindowController {
	
	private User user;
	private RegisterUser r;
	private int gender;

	@FXML
	private JFXTextField setUsername;
	@FXML
	private JFXTextField setName;
	@FXML
	private JFXTextField setEmailTextField;
	@FXML
	private JFXTextField setAgeTextField;
	@FXML
	private JFXTextField setPhoneNumberTextField;
	@FXML
	private JFXPasswordField setPasswordTextField;
	@FXML
	private JFXRadioButton updateGenderMale;
	@FXML
	private JFXRadioButton updateGenderFemale;
	@FXML
	private ToggleGroup genderGroup;
	@FXML
	private Label errorMessage;

	@FXML
	private void initialize() {
		this.genderGroup = new ToggleGroup();
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
	private int getIntFromTextField(JFXTextField textField) throws NumberFormatException {
		return Integer.parseInt(textField.getText());
	}
	//Hente ut informasjonen og setter inn i user-objektet:

	public void radioButtonChanged() {

		if (this.genderGroup.getSelectedToggle() == null) {
			throw new IllegalArgumentException("No sex selected");
		} else if (this.genderGroup.getSelectedToggle().equals(this.updateGenderMale)) {
			this.gender = 1;
		} else if (this.genderGroup.getSelectedToggle().equals(this.updateGenderFemale)) {
			this.gender = 0;
		}

	}

	@FXML
	private void updateAllInfo() throws IllegalArgumentException {
		radioButtonChanged(); //setter gender
		String username = getStringFromTextField(setUsername);
		String password = getStringFromPasswordField(setPasswordTextField);
		String name = getStringFromTextField(setName);
		int age = getIntFromTextField(setAgeTextField);
		String phone = getStringFromTextField(setPhoneNumberTextField);
		String email = getStringFromTextField(setEmailTextField);
		r = new RegisterUser();
		user = new User(username, password, name, age, gender, email, phone);
		r.registerUser(user);
	}

	@FXML
	private void tilDashboard(ActionEvent event) {
		initialize();
		try {
			updateAllInfo();
			System.out.println("Has been done");

			System.out.println(FxApp.getAS());
			FxApp.getAS().setCurrentUser(user.getUsername());

			NavigerTilSide("TrainerDashboard.fxml", event);

		}
		catch (NumberFormatException e) {
			errorMessage.setText("Age must be an integer between 0 and 100");
		}
		catch (IllegalArgumentException e) {
			//e.printStackTrace();
			errorMessage.setText(e.getMessage());
		}
		catch (IOException e) {
			System.out.println("IO Exception here");
			System.out.println(e.getMessage());
		}

	}

}
