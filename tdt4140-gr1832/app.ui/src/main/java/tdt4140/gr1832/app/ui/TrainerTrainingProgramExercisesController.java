package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class TrainerTrainingProgramExercisesController extends WindowController{
	//comment
	@FXML 
	StackPane root;
	
	@FXML
	public void intitialize () {
		root.setPickOnBounds(false);
	}
	
	@FXML
	public void handleExercises(ActionEvent event)throws IOException {
		NavigerTilSide("TrainerTrainingProgramExercisesExercises.fxml", event);
	}
	
	@FXML
	public void handlePrograms(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerTrainingProgramExercisesPrograms.fxml", event);
	}
	
	@FXML
	public void loadDialog(ActionEvent parentEvent) {
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text("Logg ut bekreftelse"));
		content.setBody(new Text("Er du sikker på at du vil logge ut?"));
		JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
		JFXButton buttonYes = new JFXButton("Ja");
		JFXButton buttonNo = new JFXButton("Nei");

		buttonYes.setOnAction((event) -> {
			dialog.close();
			try {
				NavigerTilSide("LoginScreen.fxml", parentEvent);
				FxApp.getAS().DUMMYsetuser(null);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		});

		buttonNo.setOnAction((event) -> {
			dialog.close();
		});
		content.setActions(buttonYes, buttonNo);
		dialog.show();
	}
}
