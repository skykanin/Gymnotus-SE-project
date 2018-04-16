package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.eclipse.jetty.server.handler.ContextHandler.Availability;

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
import javafx.util.Callback;
import javafx.util.StringConverter;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;
import tdt4140.gr1832.app.core.TrainerTrainingProgramAttendantsApp;

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
	Label messageLabel;
	
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
	JFXButton nextProgram;
	
	@FXML
	JFXButton lastProgram;
	
    @FXML
    StackPane root;

    @FXML
	FontAwesomeIconView programLeft;
	
	@FXML
	FontAwesomeIconView programRight;
	
	@FXML
	Label ageLabel;
	
	@FXML
	Label genderLabel;
	
	@FXML
	Label stepsLabel;
	
	@FXML
	Label restingHRlabel;
	
	@FXML
	Label weightLabel;
	
	@FXML
	JFXButton lastDay;
	
	@FXML
	JFXButton nextDay;
	
	@FXML
	JFXTextField program;
	
	@FXML 
	JFXComboBox<String> memberComboBox;
	
	@FXML
	DatePicker datePickerField; 
		
	private int dayCounter;
	
	String pattern = "LLL dd, yyyy";
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
	
	private TrainerTrainingProgramAttendantsApp eDataApp;
	
	@FXML
	public void initialize() {
		root.setPickOnBounds(false);
		if (! FxApp.TEST) {
		setFieldVisibility(false);
		setHealthFieldVisibility(false);
		setDisableField(true);
		o1Label.setText("");
		o2Label.setText("");
		o3Label.setText("");
		o4Label.setText("");
		eDataApp = new TrainerTrainingProgramAttendantsApp();
		eDataApp.TrainingExerciseDataAppSetup();
		program.setText(eDataApp.getProgram(AS.getProgramCounter()).getName());
		datePickerField.setPromptText("Ingen medlem valgt");
		
		
		ObservableList<String> names = FXCollections.observableArrayList();
		
		for (ShowUserInfoContainer userContainer : eDataApp.getUsersInProgram(AS.getProgramCounter())) {
			names.add(userContainer.getName());
		}
		if(names.size() <1) {
			names.add("Ingen medlemmer pameldt");
			memberComboBox.setItems(names);
		} else {
			memberComboBox.setItems(names);			
		}
		memberComboBox.setPromptText("Velg medlem...");

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
						if(checkStringDate(dateS,eDataApp.getDates())) {
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
	}
	
	@FXML
	public void handleMemberComboBox() {
		setFieldVisibility(false);
		o1Label.setText("");
		result1Field.setText("");
		o2Label.setText("");
		result2Field.setText("");
		o3Label.setText("");
		result3Field.setText("");
		o4Label.setText("");
		result4Field.setText("");
		String name = memberComboBox.getSelectionModel().getSelectedItem();
		
		if (!"Ingen medlemmer pameldt".equals(name)){
			int userID = -1; 
			for (ShowUserInfoContainer userContainer : eDataApp.getUsersInProgram(AS.getProgramCounter())) {
				if (name != null && name.equals(userContainer.getName())){
					userID = Integer.parseInt(userContainer.getUserID());
				}
			}
			if (userID != -1) {
				eDataApp.requestHealthExerciseDataByProgramUserID(eDataApp.getProgram(AS.getProgramCounter()).getProgramID(), userID);
			}
			if (eDataApp.getDates().size() > 0) {
				setDisableField(false);
			}
			setHealthFieldVisibility(true);
			
			//Set userInfo
			memberComboBox.setPromptText(name);
			//Newest Date
			dayCounter = eDataApp.getDates().size()-1;
			ageField.setText(eDataApp.getAge());
			genderField.setText(eDataApp.getGender());
			stepsField.setText(eDataApp.getSteps(dayCounter));
			weightField.setText(eDataApp.getWeight(dayCounter));
			restingHRField.setText(eDataApp.getRestingHR(dayCounter));
			//Check if user shares Exercise Data
			if (eDataApp.userIsSharingExerciseData()) {
				this.updateInfoFieldsOnDate(dayCounter);
			} else {
				datePickerField.setPromptText(eDataApp.getDate(dayCounter));
				String date = eDataApp.getDate(dayCounter);
				String ii = date.toLowerCase();
				if (ii.length() == 11) {
					ii = ii.substring(0,4) + "0"+ii.substring(4);
				}
				datePickerField.setValue(LocalDate.parse(ii, dateFormatter));
				messageLabel.setText("Medlem deler ikke treningsdata");
			}
			
		}	else {
			eDataApp.clearSortedResultMap();
			setDisableField(true);
			updateInfoFieldsOnDate(-222222);
			dayCounter = eDataApp.getDates().size() -1;
			ageField.setText(eDataApp.getAge());
			genderField.setText(eDataApp.getGender());
		}
	}
	
	public void updateInfoFieldsOnDate(int dayCounter) {
		if (dayCounter != -222222) {
			eDataApp.getExercises(dayCounter);	
			String date = eDataApp.getDate(dayCounter);
			String ii = date.toLowerCase();
			if (ii.length() == 11) {
				ii = ii.substring(0,4) + "0"+ii.substring(4);
			}
			datePickerField.setValue(LocalDate.parse(ii, dateFormatter));
			
			datePickerField.setPromptText(eDataApp.getDate(dayCounter));
			//Set health info
			stepsField.setText(eDataApp.getSteps(dayCounter));
			weightField.setText(eDataApp.getWeight(dayCounter));
			restingHRField.setText(eDataApp.getRestingHR(dayCounter));
			//Set ExerciseInfo
			if(eDataApp.userIsSharingExerciseData()) {
				if (eDataApp.getExercise1() == null) {
					setFieldVisibility(false);
					o1Label.setText("");
					o2Label.setText("");
					o3Label.setText("");
					o4Label.setText("");
					messageLabel.setText("Ingen okter gjennomfort denne dagen");
				} else {
					setFieldVisibility(true);
					messageLabel.setText("");
					o1Label.setText(eDataApp.getExercise1());
					result1Field.setText(eDataApp.getResult1());
					o2Label.setText(eDataApp.getExercise2());
					result2Field.setText(eDataApp.getResult2());
					o3Label.setText(eDataApp.getExercise3());
					result3Field.setText(eDataApp.getResult3());
					o4Label.setText(eDataApp.getExercise4());
					result4Field.setText(eDataApp.getResult4());
				}
			}
		}
	}

	public void updateProgram() {
		datePickerField.setValue(null);
		datePickerField.setPromptText("Ingen medlem valgt");
		memberComboBox.setPromptText("Velg medlem...");
		program.setText(eDataApp.getProgram(AS.getProgramCounter()).getName());
		datePickerField.setPromptText("Ingen medlem valgt");
		setDisableField(true);
		setHealthFieldVisibility(false);
		setFieldVisibility(false);
		//Set ExerciseInfo
		o1Label.setText("");
		result1Field.setText("");
		o2Label.setText("");
		result2Field.setText("");
		o3Label.setText("");
		result3Field.setText("");
		o4Label.setText("");
		result4Field.setText("");
		messageLabel.setText("");
		
		ObservableList<String> names = FXCollections.observableArrayList();
		
		for (ShowUserInfoContainer userContainer : eDataApp.getUsersInProgram(AS.getProgramCounter())) {
			names.add(userContainer.getName());
		}
		if(names.size() <1) {
			names.add("Ingen medlemmer pameldt");
			memberComboBox.setItems(names);
		} else {
			memberComboBox.setItems(names);			
		}
	}

	@FXML
	public void nextProgram() {
		memberComboBox.setItems(null);
		memberComboBox.setPromptText("Velg medlem...");
		if (AS.getProgramCounter() < (eDataApp.getProgramsListSize()-1)) {
			AS.increaseProgramCounter();	
		} else {
			AS.setProgramCounter(0);;
		}
		updateProgram();
	}
	
	@FXML
	public void lastProgram() {
		memberComboBox.setItems(null);
		memberComboBox.setPromptText("Velg medlem...");
		if (AS.getProgramCounter() > 0 ){
			AS.decreaseProgramCounter();	
		} else {
		AS.setProgramCounter(eDataApp.getProgramsListSize()-1);
		}
		updateProgram();
	}
	
  
    
    @FXML
	public void datePicker() {
		LocalDate date = datePickerField.getValue();
		if (date != null) {
			
			String dateS = dateFormatter.format(date);
			if (date.getDayOfMonth()<9) {
				dateS = dateS.substring(0, 1).toUpperCase()+dateS.substring(1,4)+dateS.substring(5);
			} else {
				dateS = dateS.substring(0, 1).toUpperCase()+dateS.substring(1);
			}
			
			if (eDataApp.getDates().contains(dateS)) {
				dayCounter = eDataApp.getDates().indexOf(dateS);	
			} 
			updateInfoFieldsOnDate(dayCounter);
		}
		
		 
	}
    
    @FXML
    public void lastDay() {
    		if (dayCounter > 0) {
    			dayCounter-= 1;
    		} else {
    			dayCounter = eDataApp.getDates().size()-1;
    		}
    		updateInfoFieldsOnDate(dayCounter);
    }
    
    @FXML
    public void nextDay() {
    	if ((eDataApp.getDates().size()-1) == dayCounter) {
			dayCounter=0;
		} else {
			this.dayCounter += 1;
		}
		updateInfoFieldsOnDate(dayCounter);
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

	private void setFieldVisibility(boolean value) {
		result1Field.setVisible(value);
		result2Field.setVisible(value);
		result3Field.setVisible(value);
		result4Field.setVisible(value);
	}
	
	private void setHealthFieldVisibility(boolean value) {
		stepsField.setVisible(value);
		ageField.setVisible(value);
		genderField.setVisible(value);
		restingHRField.setVisible(value);
		weightField.setVisible(value);
		weightLabel.setVisible(value);
		restingHRlabel.setVisible(value);
		genderLabel.setVisible(value);
		ageLabel.setVisible(value);
		stepsLabel.setVisible(value);
	}
	
	private void setDisableField(boolean value) {
		nextDay.setDisable(value);
		lastDay.setDisable(value);
		datePickerField.setDisable(value);
	}
}

