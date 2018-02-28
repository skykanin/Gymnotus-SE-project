package tdt4140.gr1832.app.ui;

import com.jfoenix.controls.JFXTextField;

import javafx.application.*;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;


public class MemberInfoController extends Application {



		@FXML
		JFXTextField heightField;
		
		@FXML
		JFXTextField DateField;
		
		@FXML
		JFXTextField weightField;
		
		@FXML
		JFXTextField stepsField;
		
		@FXML
		JFXTextField restingHRField;
		

		
		
		
		//start
		public void start(Stage stage) throws Exception {
			//launches from the fxml-file
			Parent root = FXMLLoader.load(getClass().getResource("TrainerMemberInfo.fxml")); //Husk å endre til tilhørende fxml fil sitt navn.
			Scene scene = new Scene(root, 400, 600);
	        stage.setTitle("MemberInfoView");
	        stage.setScene(scene);
	        stage.show();
		}
		
		@FXML
		public void initialize() {
			
			String height="1.80";
			String date="02.03.2018";
			String weight="70kg";
			String steps="100";		
			String restingHR="70";
			heightField.setText(height);
			DateField.setText(date);		
			weightField.setText(weight);
			stepsField.setText(steps);
			restingHRField.setText(restingHR);
			
		}

//		public void handle() {
//			System.out.println("jay");
//		}
		
		public static void main(String[] args) {
			launch(MemberInfoController.class, args);
		
		}
}
