package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TrainerDashboard implements Initializable {

	@FXML
	private Label VelkommenTilbake;
	
	@FXML
	private void TilInnstillinger(ActionEvent event) throws IOException {
		Parent dashboard_parent = FXMLLoader.load(getClass().getResource("TrainerSettings.fxml"));
		Scene dashboard_scene = new Scene(dashboard_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(dashboard_scene);
		app_stage.show();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		VelkommenTilbake.setText("Velkommen tilbake, ");
		
	}

	
}
