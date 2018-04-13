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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.ShowUserInfoContainer;
import tdt4140.gr1832.app.core.TrainerSettingsApp;

public class TrainerSettingsController extends WindowController {
	public static boolean ISTEST = false;
	
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
	private void HandleSetOriginalInformation(ActionEvent event) throws IOException {
		this.initialize();
	}
	
	@FXML
	private void HandleSubmitChangesButton(ActionEvent event) throws IOException {
		if (! ISTEST) {
		String username = FxApp.getAS().getLoggedInUser().getUsername();
				
		
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
				throw new IllegalArgumentException("Name cannot contain any digits");
			}
		}
		
		for (int i=0; i<tlfField.getText().length(); i++) {
			if (!(Character.isDigit(tlfField.getText().charAt(i)))) {
				throw new IllegalArgumentException("Phonenumber cannot contain any letters");
			}
		}
		
		if (!(isValidEmailAddress(emailField.getText()))) {
			throw new IllegalArgumentException("Email is not valid");
			
		}
		
		if (ageField.getText().length() < (1) || ageField.getText().length() >(2)) {
			throw new IllegalArgumentException("age must be a 1 or 2- digit number");
			}
		
		for ( int i=0; i<ageField.getText().length();i++) {
			char c= ageField.getText().charAt(i);
			if (!(Character.isDigit(c))) {
				throw new IllegalArgumentException("age can only consist of digits");
			}
		}
	        
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
	
	//Emailvalidator
	
	private boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("TrainerSettings.fxml")); //change to correct filename
		Scene scene = new Scene(root, 1200, 660);
        primaryStage.setTitle("MemberInfoView");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(TrainerSettingsController.class, args);
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