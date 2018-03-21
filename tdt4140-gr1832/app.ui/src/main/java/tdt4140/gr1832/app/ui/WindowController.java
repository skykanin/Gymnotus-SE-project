package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WindowController extends FxApp {

	protected void NavigerTilSide(String filnavn, ActionEvent event) throws IOException {
		Parent LoginScreen_parent = FXMLLoader.load(getClass().getResource(filnavn));
		Scene LoginScreen_scene = new Scene(LoginScreen_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(LoginScreen_scene);
		app_stage.show();
		FxApp.getAS().setWindow(filnavn);
	}

	protected void NavigerTilSide(String filnavn, ActionEvent event, String userID) throws IOException {
		TrainerMemberInfoController.userID = userID;
		Parent LoginScreen_parent = FXMLLoader.load(getClass().getResource(filnavn));
		Scene LoginScreen_scene = new Scene(LoginScreen_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(LoginScreen_scene);
		app_stage.show();
		   
	}

	@FXML
	private void TilTreningsprogram(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerTrainingProgramOverview.fxml", event);
	}
	
	@FXML
	private void TilInnstillinger(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerSettings.fxml", event);
	}
	
	@FXML
	private void TilDashboard(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerDashboard.fxml", event);
	}
	
	@FXML
	private void TilMedlemmer(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerMembers.fxml", event);
	}
	
	@FXML
	private void TilTreningsoversikt(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerTrainingProgramOverview.fxml", event);
	}
	
	@FXML
	private void TilTreningskommentar(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerTrainingProgramComments.fxml", event);
	}
	
	@FXML
	private void TilTreningspameldte(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerTrainingProgramAttendants.fxml", event);
	}
	
	@FXML
	private void TilTreningsovelse(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerTrainingProgramExercises.fxml", event);
	}

}
