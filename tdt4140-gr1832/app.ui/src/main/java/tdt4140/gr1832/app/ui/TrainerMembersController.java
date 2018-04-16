package tdt4140.gr1832.app.ui;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;
import tdt4140.gr1832.app.core.ApplicationState;
import tdt4140.gr1832.app.core.TrainerMemberInfoApp;

public class TrainerMembersController extends WindowController {
	@FXML JFXListView<String> medlemsListe;
	@FXML JFXButton velgMedlemButton;
	tdt4140.gr1832.app.core.TrainerMemberInfoApp app = new TrainerMemberInfoApp();
	@FXML StackPane root;
	@FXML JFXButton velgTrener;
	@FXML JFXListView<String> trenerListe;
	@FXML Label valgMFeil;
	@FXML Label valgTFeil;

	@FXML
	public void initialize() {
		root.setPickOnBounds(false);
		if(!FxApp.TEST) {
			app.requestAllUserID();
			ObservableList<String> names = FXCollections.observableArrayList();
			ObservableList<String> trainers = FXCollections.observableArrayList();
			for (ShowUserInfoContainer c : app.getUsers()) {
				String name = c.getName();
				if (c.getIsTrainer()) {
					trainers.add(name);
				}else {
					names.add(name);
				}
			}
			trenerListe.setItems(trainers);
			medlemsListe.setItems(names);
		}
	}
	
	@FXML
	private void velgMedlem(ActionEvent event) throws IOException {
		if(FxApp.TEST) {
			NavigerTilSide("TrainerMemberInfo.fxml", event, "1");
		} else {
		String preferedUser = medlemsListe.getSelectionModel().getSelectedItem();
		if (preferedUser == null) {
			valgMFeil.setText("Du har ikke valgt medlem");
		} else {
			valgMFeil.setText("");
			String id = app.getIDfromName(preferedUser);
			NavigerTilSide("TrainerMemberInfo.fxml", event, id);		
			}
		}
	}
	
	@FXML
	private void velgTrener(ActionEvent event) throws IOException{
		if(FxApp.TEST) {
			NavigerTilSide("TrainerMemberInfo.fxml", event, "1");
		} else {
			String preferedUser = trenerListe.getSelectionModel().getSelectedItem();
			if (preferedUser == null) {
				valgTFeil.setText("Du har ikke valgt trener");
			} else {
				valgTFeil.setText("");
				String id = app.getIDfromName(preferedUser);
				NavigerTilSide("TrainerMemberInfo.fxml", event, id);
			}
		}
	}
}


