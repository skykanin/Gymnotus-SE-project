package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class TrainerTrainingProgramExercisesController extends WindowController {
	//comment
	@FXML 
	StackPane root;
	
	@FXML
	public void initialize() {
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
	
}
