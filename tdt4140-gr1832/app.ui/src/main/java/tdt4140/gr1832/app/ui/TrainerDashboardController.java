package tdt4140.gr1832.app.ui;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import tdt4140.gr1832.app.core.TrainerDashboardApp;


public class TrainerDashboardController extends WindowController implements Initializable {
	
	@FXML JFXComboBox<String> memberComboBox;
	
    @FXML private Label Velkommen;
    
    TrainerDashboardApp app = new TrainerDashboardApp();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (FxApp.getAS().getLoggedInUser() != null) {
			Velkommen.setText("Velkommen, " + FxApp.getAS().getLoggedInUser().getName());
		}
		
		app.requestAllUserID();
		ObservableList<String> names = FXCollections.observableArrayList();
		for (String name : app.getNames()) {
			names.add(name);
		}
		
		memberComboBox.setItems(names);
	}
	
	public void handleMemberComboBox(ActionEvent actionEvent) throws IOException {
		
	}
	
	
	

	
}
