package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;


import containers.ExerciseProgramContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

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
    private StackPane root;

    
    
	
	@FXML
	public void initialize() {
		if(!FxApp.TEST) {
		treningsprogramBeskrivelse.setVisible(false);
		root.setPickOnBounds(false);
		app.requestExerciseProgramInformation();
		program.setText(app.getExerciseProgramContainer(AS.getProgramCounter()).getName());
		app.requestExerciseInformationFromProgramID(app.getExerciseProgramContainer(AS.getProgramCounter()).getProgramID());
		ObservableList<String> ovelser = FXCollections.observableArrayList();
		ovelser.add("Programoversikt");
		ovelser.addAll(app.getExerciseList());
		ovelseComboBox.setItems(ovelser);
		}		
		ovelseComboBox.setPromptText("Velg programoversikt eller se oversikt for enkeltøvelser");
		
	}
	
	public void update() {
		ovelseComboBox.getItems().clear();
		ovelseComboBox.setPromptText("Velg programoversikt eller se oversikt for enkeltøvelser");
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
	
	
//		String ovelse = ovelseComboBox.getSelectionModel().getSelectedItem();
//				
//				if (!"Ingen ovelser tilgjengelig".equals(ovelse)){
//					int ovelseID = -1; 
//					for (ExerciseContainer ExerciseContainer : programApp.requestExerciseInformationFromProgramID(AS.getProgramCounter()) {
//						if (ovelse != null && ovelse.equals(ExerciseContainer.getDescription())){
//							ovelseID = (ExerciseContainer.getExerciseID());
//						}
//					}
//					if (ovelseID != -1) {
//						// må få brukt den ovelseID slik at vi henter riktig ParameterDescription.
//						treningsprogramBeskrivelse.setText(ec.getParameterDescription());
//					}
//					
//					//Set userInfo
//					ovelseComboBox.setPromptText(ovelse);
//					if 
//					} else {
//						messageLabel.setText("");
//					}
//					
//				}	
//			}
	
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
