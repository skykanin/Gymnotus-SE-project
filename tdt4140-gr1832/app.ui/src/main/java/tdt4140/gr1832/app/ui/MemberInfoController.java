package tdt4140.gr1832.app.ui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.application.*;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import tdt4140.gr1832.app.core.MemberInfoApp;


public class MemberInfoController extends Application {



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
			
			String height=MemberInfoApp.getHeight();
			String date=MemberInfoApp.getDate();
			String weight=MemberInfoApp.getWeight();
			String steps=MemberInfoApp.getSteps();		
			String restingHR=MemberInfoApp.getRestingHR();
			String name = MemberInfoApp.getName();
			String username = MemberInfoApp.getUsername();
			String email = MemberInfoApp.getEmail();
			String tlf = MemberInfoApp.getTlf();
			String age = MemberInfoApp.getAge();
			String gender = MemberInfoApp.getGender();
			
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
		
		public static void main(String[] args) {
			launch(MemberInfoController.class, args);
		
		}
}
