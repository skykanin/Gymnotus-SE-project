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
import tdt4140.gr1832.app.core.ApplicationState;
import tdt4140.gr1832.app.core.TrainerMemberInfoApp;

public class TrainerMembersController extends WindowController {
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
	private void velgMedlem(ActionEvent event) throws IOException {
		String preferedUser = medlemsListe.getSelectionModel().getSelectedItem();
		
		try {
			String[] splitUser=preferedUser.split("#");
			if (splitUser.length==2) {
				String id = splitUser[1];
				NavigerTilSide("AnonymousTrainerMemberInfo.fxml", event, id);

			}
		}
		catch  (Exception e){
			String id = app.getIDfromName(preferedUser);
		NavigerTilSide("TrainerMemberInfo.fxml", event, id);
		}
				
	}
	
	public static void main(String[] args) {
	
			
		
	}
	
	
}


