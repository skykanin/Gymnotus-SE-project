package tdt4140.gr1832.app.ui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    
    @FXML Label infoText;
    
    @FXML Label pulsSnittTekst;

    @FXML Label pulsSnittVerdi;
    
    TrainerDashboardApp app = new TrainerDashboardApp();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (FxApp.getAS().getLoggedInUser() != null) {
			Velkommen.setText("Velkommen, " + FxApp.getAS().getLoggedInUser().getName());
		}
		
		infoText.setText("Velg dra-til-tryne for å visualisere informasjon:");
		app.requestAllUserID();
		ObservableList<String> names = FXCollections.observableArrayList();
		for (String name : app.getNames()) {
			names.add(name);
		}
		
		memberComboBox.setItems(names);
		
	}
	
	public void handleMemberComboBox(ActionEvent actionEvent) throws IOException {
		String username = memberComboBox.getSelectionModel().getSelectedItem();
		infoText.setText("Viser " + username + "'s info, velg nytt tryne: " );
		
		app.requestHealthInformation_ID(app.getIDfromName(username));
		
		String datesToSet = "";
		String HRsToSet = "";
		int meanHR = 0;
		
		if (app.getDates() != null) {
			
			for (int i = 0; i < app.getDates().size(); i++) {
				datesToSet += app.getDates().get(i) + ", ";
				HRsToSet += app.getRestingHRs().get(i) + ", ";
				meanHR += Integer.parseInt(app.getRestingHRs().get(i))/app.getRestingHRs().size();
			} 
			
			datesToSet = datesToSet.substring(0, datesToSet.length()-2);
			HRsToSet = HRsToSet.substring(0, HRsToSet.length()-2);
			
			pulsSnittTekst.setText(username +"'s snittpuls ligger på:");
			pulsSnittVerdi.setText(meanHR + "");
			
		} else {
			pulsSnittTekst.setText("Ingen helsedata å vise");
			pulsSnittVerdi.setText("");
		}
		
		
		
		
		
	}
	
	
	
}
