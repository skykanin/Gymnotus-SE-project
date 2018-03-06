package tdt4140.gr1832.app.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScreenController extends WindowController {

	@FXML
	private void Registrer (ActionEvent event) throws IOException {
		NavigerTilSide("RegisterScreen.fxml", event);
	}
	
	@FXML
	private void LoggInn (ActionEvent event) throws IOException {
		NavigerTilSide("TrainerDashboard.fxml", event);
	}
}
