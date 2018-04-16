package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tdt4140.gr1832.app.containers.ExerciseProgramContainer;
import tdt4140.gr1832.app.core.TrainerTrainingProgramOverviewApp;


public class TrainerTrainingProgramOverviewController extends WindowController  {

	
	@FXML
	JFXTextField program;
	
	@FXML
	TextArea treningsprogramBeskrivelse;
	
	@FXML
	JFXComboBox<String> ovelseComboBox;
	
	TrainerTrainingProgramOverviewApp app = new TrainerTrainingProgramOverviewApp();
	
		
    @FXML
    StackPane root;

    
    
	
	@FXML
	public void initialize() {
		root.setPickOnBounds(false);
		if(!FxApp.TEST) {
		treningsprogramBeskrivelse.setVisible(false);
		app.requestExerciseProgramInformation();
		program.setText(app.getExerciseProgramContainer(AS.getProgramCounter()).getName());
		app.requestExerciseInformationFromProgramID(app.getExerciseProgramContainer(AS.getProgramCounter()).getProgramID());
		ObservableList<String> ovelser = FXCollections.observableArrayList();
		ovelser.add("Programoversikt");
		ovelser.addAll(app.getExerciseList());
		ovelseComboBox.setItems(ovelser);
		}		
		ovelseComboBox.setPromptText("Velg programoversikt eller se oversikt for enkeltovelser");
		
	}
	
	public void update() {
		ovelseComboBox.getItems().clear();
		ovelseComboBox.setPromptText("Velg programoversikt eller se oversikt for enkeltovelser");
		treningsprogramBeskrivelse.setVisible(false);
		ExerciseProgramContainer c = app.getExerciseProgramContainer(AS.getProgramCounter());
		program.setText(c.getName());
		app.requestExerciseInformationFromProgramID(app.getExerciseProgramContainer(AS.getProgramCounter()).getProgramID());
		ObservableList<String> ovelser = FXCollections.observableArrayList();
		ovelser.add("Programoversikt");
		ovelser.addAll(app.getExerciseList());
		ovelseComboBox.setItems(ovelser);
	}

	@FXML
	public void nextProgram() {
		
		if (AS.getProgramCounter() < (app.getContainerExcerciseProgramLength()-1)) {
			AS.increaseProgramCounter();	
		} else {
			AS.setProgramCounter(0);;
		}
		update();
	}
	
	@FXML
	public void lastProgram() {
		if (AS.getProgramCounter() > 0 ){
			AS.decreaseProgramCounter();	
		} else {
		AS.setProgramCounter(app.getContainerExcerciseProgramLength()-1);
		}
		update();
	}
	
	@FXML
	public void handleComboBox() {
		treningsprogramBeskrivelse.setVisible(true);
		String decicion = ovelseComboBox.getSelectionModel().getSelectedItem();
		if ("Programoversikt".equals(decicion)) {
			treningsprogramBeskrivelse.setText(app.getExerciseProgramContainer(AS.getProgramCounter()).getDescription());
		} else {
			treningsprogramBeskrivelse.setText(app.getExerciseContainertoString(decicion));
		}
	}
	
	
}
