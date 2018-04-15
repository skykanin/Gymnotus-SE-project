package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import containers.ExerciseProgramContainer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.TrainerTrainingProgramOverviewApp;

public class TrainerTrainingProgramOverviewController extends WindowController  {

	
	@FXML
	JFXTextField program;
	
	@FXML
	TextArea treningsprogramBeskrivelse;
	
	TrainerTrainingProgramOverviewApp programApp = new TrainerTrainingProgramOverviewApp();;
		
    @FXML
    private StackPane root;

    
    
	
	@FXML
	public void initialize() {
		root.setPickOnBounds(false);
		if(!FxApp.TEST) {
		programApp.requestExerciseProgramInformation();
		ExerciseProgramContainer c = programApp.getExerciseProgramContainer(AS.getProgramCounter());
		program.setText(c.getName());
		treningsprogramBeskrivelse.setText(c.getDescription());
		}
	}
	
	public void update() {
		ExerciseProgramContainer c = programApp.getExerciseProgramContainer(AS.getProgramCounter());
		program.setText(c.getName());
		treningsprogramBeskrivelse.setText(c.getDescription());
	}

	@FXML
	public void nextProgram() {
		if (AS.getProgramCounter() < (programApp.getContainerExcerciseProgramLength()-1)) {
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
		AS.setProgramCounter(programApp.getContainerExcerciseProgramLength()-1);
		}
		update();
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
