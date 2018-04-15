package tdt4140.gr1832.app.ui;
import java.io.IOException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.jfoenix.controls.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;
import tdt4140.gr1832.app.core.TrainerSettingsApp;
import tdt4140.gr1832.app.core.User;
 
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
	StackPane root;
	
	@FXML
	private Label errorMessage;
	
	private User trainerApp = new User();
	
	private boolean valid =  true;
	@FXML
	private void HandleSetOriginalInformation(ActionEvent event) throws IOException {
		this.initialize();
		errorMessage.setText("");
	}
	
	@FXML
	private void HandleSubmitChangesButton(ActionEvent event) throws IOException {
		valid = true;
		String username = FxApp.getAS().getLoggedInUser().getUsername();
		errorMessage.setText("");	
		
		String gender;
		if (mannButton.isSelected()){
			gender = "0";
		} else if (dameButton.isSelected()) {
			gender = "1"; 
		} else {
			gender = "2";
		}
		
		for (int i=0; i<nameField.getText().length(); i++) {
			if (Character.isDigit(nameField.getText().charAt(i))) {
				errorMessage.setText("Navn kan ikke inneholde tall");
				valid = false;
			}
		}
		
		if (!usernameField.getText().equals(username)) {
			valid = false;
			errorMessage.setText("Brukernavn kan ikke endres");
		}
		
		for (int i=0; i<tlfField.getText().length(); i++) {
			if (!(Character.isDigit(tlfField.getText().charAt(i)))) {
				errorMessage.setText("Telefonnummer må inneholde tall");
				valid = false;
			}
		}
		
		if (tlfField.getText().length() != (8)) {
			errorMessage.setText("Telefonnummer må være et åttesifret tall");
			valid = false;
			}
		
		if (nameField.getText().length() == (0)) {
			errorMessage.setText("Feltet for navn er blankt");
			valid = false;
			}
	
		
		if (!(trainerApp.isValidEmailAddress(emailField.getText()))) {
			valid = false;
			errorMessage.setText("Ugyldig email");
			
		}
		
		if (ageField.getText().length() != (2)) {
			valid = false;
			errorMessage.setText("Alder må være et tosifret tall");
			}
		
		for ( int i=0; i<ageField.getText().length();i++) {
			char c= ageField.getText().charAt(i);
			if (!(Character.isDigit(c))) {
				valid = false;
				errorMessage.setText("Alder kan bare bestå av tall");
			}
		}
	    if (valid)  {
		if(TrainerSettingsApp.changeUser(username, nameField.getText(),emailField.getText(), tlfField.getText(), ageField.getText(), gender)) {
			FxApp.getAS().setCurrentUser(username);
			return;
		}
		
		System.out.println("Server failed to change userInfo");
	    }
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
			mannButton.setSelected(false);
			dameButton.setSelected(false);
		} else if (gender == 1) {
			mannButton.setSelected(false);
			dameButton.setSelected(true);
			
		} else if (gender == 0){
			mannButton.setSelected(true);
			dameButton.setSelected(false);
		
		}
		
		root.setPickOnBounds(false);
	}

	


	@FXML
	public void loadDialog(ActionEvent parentEvent) {
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text("Logg ut bekreftelse"));
		content.setBody(new Text("Er du sikker på at du vil logge ut?"));
		JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
		JFXButton buttonYes = new JFXButton("Ja");
		JFXButton buttonNo = new JFXButton("Nei");

		buttonYes.setOnAction((event) -> {
			dialog.close();
			try {
				NavigerTilSide("LoginScreen.fxml", parentEvent);
				FxApp.getAS().DUMMYsetuser(null);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		});

		buttonNo.setOnAction((event) -> {
			dialog.close();
		});
		content.setActions(buttonYes, buttonNo);
		dialog.show();
	}
	
	@FXML
	public void TilSlettProfil(ActionEvent event) throws Exception {               
		NavigerTilSide("DeleteUser.fxml", event);
	}
	
}