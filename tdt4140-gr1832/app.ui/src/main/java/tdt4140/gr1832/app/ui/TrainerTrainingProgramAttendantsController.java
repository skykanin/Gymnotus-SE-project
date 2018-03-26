package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import tdt4140.gr1832.app.core.ExerciseProgramContainer;
import tdt4140.gr1832.app.core.ShowUserInfoContainer;
import tdt4140.gr1832.app.core.TrainerDashboardApp;
import tdt4140.gr1832.app.core.TrainerMemberInfoApp;
import tdt4140.gr1832.app.core.TrainerTrainingProgramOverviewApp;

public class TrainerTrainingProgramAttendantsController extends WindowController {

	
	@FXML
	Label o1Label;
	
	@FXML
	Label o2Label;
	
	@FXML
	Label o3Label;
	
	@FXML
	Label o4Label;
	
	@FXML
	JFXTextField result1Field;
	
	@FXML
	JFXTextField result2Field;
	
	@FXML
	JFXTextField result3Field;
	
	@FXML
	JFXTextField result4Field;
	
	@FXML
	JFXTextField weightField;
	
	@FXML
	JFXTextField stepsField;
	
	@FXML
	JFXTextField restingHRField;

	@FXML
	JFXTextField ageField;
	
	@FXML
	JFXTextField genderField;

	@FXML
	JFXButton lastDay;
	
	JFXButton nextDay;
	
    @FXML
    private StackPane root;

    @FXML
	FontAwesomeIconView programLeft;
	
	@FXML
	FontAwesomeIconView programRight;
	
	@FXML
	JFXTextField program;
	
	@FXML 
	JFXComboBox<String> memberComboBox;
	
	@FXML
	DatePicker datePickerField; 
	
	private int programCounter = 0;
	
	String pattern = "LLL dd, yyyy";
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
	
	private TrainerMemberInfoApp membApp = new TrainerMemberInfoApp();
	private TrainerTrainingProgramOverviewApp progApp = new TrainerTrainingProgramOverviewApp();
	
	
	public void initialize() {
		progApp.requestExerciseProgramInformation();
		membApp.requestAllUserID();
		root.setPickOnBounds(false);
		ObservableList<String> names = FXCollections.observableArrayList();
		for (String name : membApp.getNames()) {
			names.add(name);
		}
		
		memberComboBox.setItems(names);
		datePickerField.setPromptText("Mar, 1");

		datePickerField.setConverter(new StringConverter<LocalDate>() {

		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		        	 	return dateFormatter.format(date);
		        	 	//result = result.substring(0, 1).toUpperCase()+ result.substring(1)
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 });
		  
		Callback<DatePicker, DateCell>set = new Callback<DatePicker, DateCell>(){
			
			@Override
			public DateCell call(DatePicker param) {
				// TODO Auto-generated method stub
				return new DateCell() {
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						String dateS = dateFormatter.format(item);
						if(checkStringDate(dateS,membApp.getDates())) {
							setDisable(true);
							setStyle("-fx-background-color: #0b88a10a");
							//setStyle("fx")
						} else {
							//do nothing
						}	
					}
				};
			}
		};
		
		datePickerField.setDayCellFactory(set);
	}
	
	@FXML
	public void handleMemberComboBox() {
		String name = memberComboBox.getSelectionModel().getSelectedItem();
		String userID = membApp.getIDfromName(name);
		ShowUserInfoContainer userContainer = membApp.getContainerAllUsers().getUser(userID);
		membApp.requestHealthInformation_ID(userID);
		
	}

	public void update() {
		ExerciseProgramContainer c = progApp.getExerciseProgramContainer(programCounter);
		program.setText(c.getName());
	}

	@FXML
	public void nextProgram() {
		if (programCounter < (progApp.getContainerExcerciseProgramLength()-1)) {
			programCounter++;	
		} else {
			programCounter = 0;
		}
		update();
	}
	
	@FXML
	public void lastProgram() {
		if (programCounter > 0 ){
			programCounter--;	
		} else {
		programCounter = progApp.getContainerExcerciseProgramLength()-1;
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
    
    //Help function for datePicker
	private boolean checkStringDate(String in, List<String> liste) {
		for (String i : liste) {
			String ii = i.toLowerCase();
			if (ii.length() == 11) {
				ii = ii.substring(0,4) + "0"+ii.substring(4);
			}
			if (ii.equals(in)){
				return false;
			}
		}
		return true;
	}
}
