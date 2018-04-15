package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text; 

import tdt4140.gr1832.app.containers.ExerciseProgramContainer;

import tdt4140.gr1832.app.core.TrainerTrainingProgramOverviewApp;

import tdt4140.gr1832.app.core.TrainerTrainingProgramSeeCommentsApp;


public class TrainerTrainingProgramSeeCommentsController extends WindowController {
	
	@FXML JFXListView<String> commentList;

	@FXML JFXTextField overskrift;
	@FXML TextArea treningsprogramTilbakemelding;
	
	TrainerTrainingProgramSeeCommentsApp programApp = new TrainerTrainingProgramSeeCommentsApp();
	TrainerTrainingProgramOverviewApp app = new TrainerTrainingProgramOverviewApp();;
	
    @FXML private StackPane root;
  
    @FXML public void initialize() {
    	root.setPickOnBounds(false);
    	if (!FxApp.TEST) {
		app.requestExerciseProgramInformation();
		ExerciseProgramContainer c = app.getExerciseProgramContainer(AS.getProgramCounter());
		program.setText(c.getName());
    		
		app.requestExerciseProgramInformation();
					programApp.requestProgramComments(AS.getProgramCounter()+1);
    		programApp.getCommentList();
    		ObservableList<String> comments = FXCollections.observableArrayList();
		for (int i =0;i<programApp.getCommentList().size();i++) {
  
				comments.add(programApp.getCommentList().get(i));
		}
		
		commentList.setItems(comments);
    	}
	}
	

    @FXML
    private JFXTextField program;
    
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

    

    @FXML public void update() {
		ExerciseProgramContainer c = app.getExerciseProgramContainer(AS.getProgramCounter());
		program.setText(c.getName());
		programApp.requestProgramComments(AS.getProgramCounter()+1);
		programApp.getCommentList();
		ObservableList<String> comments = FXCollections.observableArrayList();
	for (int i =0;i<programApp.getCommentList().size();i++) {
			comments.add(programApp.getCommentList().get(i));
	}

	commentList.setItems(comments);
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
}
