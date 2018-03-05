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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TrainerDashboardController {

	@FXML
	private void TilInnstillinger(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerSettings.fxml", event);
	}
	
	@FXML
	private void TilDashboard(ActionEvent event) throws IOException {
		;
	}
	
	@FXML
	private void TilMedlemmer(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerMembers.fxml", event);
	}
	
	@FXML
	private void TilTreningsprogram(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerTrainingProgramOverview.fxml", event);
	}
	
	private void NavigerTilSide(String filnavn, ActionEvent event) throws IOException {
		Parent LoginScreen_parent = FXMLLoader.load(getClass().getResource(filnavn));
		Scene LoginScreen_scene = new Scene(LoginScreen_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(LoginScreen_scene);
		app_stage.show();
	}
	
	@FXML
	public void TilLoggUt(ActionEvent event) throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogOut.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
	}

	
}
