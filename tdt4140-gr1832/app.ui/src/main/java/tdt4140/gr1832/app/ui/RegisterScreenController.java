package tdt4140.gr1832.app.ui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;

import javax.ws.rs.NotFoundException;

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
			throw new IllegalArgumentException("intet kjonn valgt");
		} else if (this.genderGroup.getSelectedToggle().equals(this.updateGenderMale)) {
			this.gender = 0;
		} else if (this.genderGroup.getSelectedToggle().equals(this.updateGenderFemale)) {
			this.gender = 1;
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
            AS.verifyUsername(getStringFromTextField(setUsername));
            	errorMessage.setText("Brukernavnet finnes fra for");
            	return;
        } catch (NotFoundException e) {
            
        }
		try {
			updateAllInfo();
			FxApp.getAS().setCurrentUser(user.getUsername());
			NavigerTilSide("TrainerDashboard.fxml", event);
		}
		catch (NumberFormatException e) {
			errorMessage.setText("Alder ma vaere et tall mellom 0 og 100");
		}
		catch (IllegalArgumentException e) {
			errorMessage.setText(e.getMessage());
		}
		catch (IOException e) {
			System.out.println("IO Exception here");
			System.out.println(e.getMessage());
		}

	}
	
	@FXML
	private void tilLoginScreen(ActionEvent event) throws IOException {
		NavigerTilSide("LoginScreen.fxml", event);
	}
}

