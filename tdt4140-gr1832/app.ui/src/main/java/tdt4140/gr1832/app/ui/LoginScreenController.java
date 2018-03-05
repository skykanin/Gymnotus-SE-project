package tdt4140.gr1832.app.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScreenController {

	@FXML
	private void Registrer (ActionEvent event) throws IOException {
		NavigerTilSide("RegisterScreen.fxml", event);
	}
	
	@FXML
	private void LoggInn (ActionEvent event) throws IOException {
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
