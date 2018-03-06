package tdt4140.gr1832.app.ui;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.TrainerMemberInfoApp;

public class TrainerMembersController {
	@FXML JFXListView<String> medlemsListe;
	@FXML JFXButton velgMedlemButton;
	tdt4140.gr1832.app.core.TrainerMemberInfoApp app = new TrainerMemberInfoApp();
	
	@FXML
	public void initialize() {
		app.requestAllUserID();
		ObservableList<String> names = FXCollections.observableArrayList();
		for (String name : app.getNames()) {
			names.add(name);
		}
		medlemsListe.setItems(names);
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
		
	}
	
	@FXML
	private void TilTreningsprogram(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerTrainingProgramOverview.fxml", event);
	}
	
	@FXML
	private void velgMedlem(ActionEvent event) throws IOException {
		String preferedUser = medlemsListe.getSelectionModel().getSelectedItem();
		String id = app.getIDfromName(preferedUser);
		NavigerTilSide("TrainerMemberInfo.fxml", event, id);
		
		
	}
	
	private void NavigerTilSide(String filnavn, ActionEvent event) throws IOException {
		Parent LoginScreen_parent = FXMLLoader.load(getClass().getResource(filnavn));
		Scene LoginScreen_scene = new Scene(LoginScreen_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(LoginScreen_scene);
		app_stage.show();
		
	}
	private void NavigerTilSide(String filnavn, ActionEvent event, String userID) throws IOException {
		TrainerMemberInfoController.userID = userID;
		Parent LoginScreen_parent = FXMLLoader.load(getClass().getResource(filnavn));
		Scene LoginScreen_scene = new Scene(LoginScreen_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(LoginScreen_scene);
		app_stage.show();
		   
	}
}
