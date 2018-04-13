package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import containers.ExerciseProgramContainer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import tdt4140.gr1832.app.core.ShowCommentsContainer;
import tdt4140.gr1832.app.core.CommentsApp;



public class TrainerTrainingProgramCommentsController extends WindowController {
	
	@FXML JFXListView<String> commentList;

	@FXML JFXTextField overskrift;
	@FXML TextArea treningsprogramTilbakemelding;
	
	CommentsApp programApp = new CommentsApp();
	TrainerTrainingProgramOverviewApp app = new TrainerTrainingProgramOverviewApp();;

	int programCounter = 0;
	int commentCounter=1;
	
    @FXML private StackPane root;
  
    @FXML public void initialize() {
    		root.setPickOnBounds(false);
    		
		app.requestExerciseProgramInformation();
	
		ExerciseProgramContainer a = app.getExerciseProgramContainer(programCounter); //Få frem navnet på treningsprogrammet!
		
		overskrift.setText(a.getName());	
		programApp.requestProgramComments(commentCounter);
    		programApp.getCommentList();
    		ObservableList<String> comments = FXCollections.observableArrayList();
		for (int i =0;i<programApp.getCommentList().size();i++) {
  
				comments.add(programApp.getCommentList().get(i));
		}
		
		commentList.setItems(comments);
	}
	@FXML public void update() {
	
		ExerciseProgramContainer a = app.getExerciseProgramContainer(programCounter);
		overskrift.setText(a.getName());
		programApp.requestProgramComments(commentCounter);
		programApp.getCommentList();
		ObservableList<String> comments = FXCollections.observableArrayList();
	for (int i =0;i<programApp.getCommentList().size();i++) {
			comments.add(programApp.getCommentList().get(i));
	}

	commentList.setItems(comments);
	}

	@FXML
	public void nesteProgram() {
		//System.out.println(programApp.getContainerExcerciseProgramLength()-1 + " programApp.getContainerExcerciseProgramLength()-1");
		if (commentCounter < (5)) {
			programCounter++;
			commentCounter++;
		} else {
			programCounter = 0;
			commentCounter=1;
		}
		update();
	}
	
	@FXML
	public void forrigeProgram() {
		if (programCounter > 0 ){
			programCounter--;
			commentCounter--;
		} else {
		programCounter = 4;
		commentCounter=5;
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
