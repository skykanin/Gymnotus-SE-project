package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.text.Format;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;

import com.jfoenix.controls.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import tdt4140.gr1832.app.core.TrainerMemberInfoApp;


public class TrainerMemberInfoController extends WindowController {
	@FXML
	JFXTextField heightField;
	
	@FXML
	JFXTextField dateField;
	
	@FXML
	JFXTextField weightField;
	
	@FXML
	JFXTextField stepsField;
	
	@FXML
	JFXTextField restingHRField;

	@FXML
	JFXTextField nameField;
	
	@FXML
	JFXTextField usernameField;
	
	@FXML
	JFXTextField emailField;
	
	@FXML
	JFXTextField tlfField;
	
	@FXML
	JFXTextField ageField;
	
	@FXML
	JFXTextField genderField;
	
	@FXML
	JFXDatePicker datePickerField;
	
	@FXML
    private Label Medlemsnavn;

	@FXML
	private JFXButton lastDay;
	
	@FXML
	private JFXButton nextDay;
	
	
	@FXML
	StackPane root;

	public static String userID;
	String pattern = "LLL dd, yyyy"; //for dateString converter
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
	
	tdt4140.gr1832.app.core.TrainerMemberInfoApp app;

	@FXML
	public void initialize() {
		root.setPickOnBounds(false);
		
		if (!FxApp.TEST) {
		app = new TrainerMemberInfoApp();
		app.requestUserInformation_ID(userID);
		app.requestHealthInformation_ID(userID);
		
		//Disable buttons if there are no health info
		if (app.getContainerHealth().size()<1 || (! app.getContainerUser().getShareHealthData())) {
			lastDay.setDisable(true);
			nextDay.setDisable(true);
			datePickerField.setDisable(true);
		}
		
		String height=app.getHeight();
		String date=app.getDate();
		String weight=app.getWeight();
		String steps=app.getSteps();		
		String restingHR=app.getRestingHR();
		String name =app.getName();
		String username = app.getUsername();
		String email = app.getEmail();
		String tlf = app.getTlf();
		String age =app.getAge();
		String gender = app.getGender();
		
		heightField.setText(height);
		dateField.setText(date);		
		weightField.setText(weight);
		stepsField.setText(steps);
		restingHRField.setText(restingHR);
		nameField.setText(name);
		usernameField.setText(username);		
		emailField.setText(email);
		tlfField.setText(tlf);
		ageField.setText(age);
		genderField.setText(gender);
		dateField.setEditable(false);
		Medlemsnavn.setText("Brukerinformasjonen til " + app.getName());
		datePickerField.setPromptText(date);
		datePickerField.setConverter(new StringConverter<LocalDate>() {

		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		        	 return dateFormatter.format(date);
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
		}
		
		
		Callback<DatePicker, DateCell>set = new Callback<DatePicker, DateCell>(){
			
			@Override
			public DateCell call(DatePicker param) {
				return new DateCell() {
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						String dateS = dateFormatter.format(item);
						if(checkStringDate(dateS,app.getDates())) {
							setDisable(true);
							setStyle("-fx-background-color: #0b88a10a");
						} else {
							//do nothing
						}	
					}
				};
			}
		};
		
		datePickerField.setDayCellFactory(set);
	}
	
	private void updateHealtInfo() {
		String height=app.getHeight();
		String date=app.getDate();
		String weight=app.getWeight();
		String steps=app.getSteps();		
		String restingHR=app.getRestingHR();
		heightField.setText(height);
		dateField.setText(date);		
		weightField.setText(weight);
		stepsField.setText(steps);
		restingHRField.setText(restingHR);
		//change to right text format for string to date
		String ii = date.toLowerCase();
		if (ii.length() == 11) {
			ii = ii.substring(0,4) + "0"+ii.substring(4);
		}
		datePickerField.setValue(LocalDate.parse(ii, dateFormatter));
	}
	
	@FXML
	public void datePicker() {
		LocalDate date = datePickerField.getValue();
		String dateS = dateFormatter.format(date);
		if (date.getDayOfMonth()<9) {
			dateS = dateS.substring(0, 1).toUpperCase()+dateS.substring(1,4)+dateS.substring(5);
		} else {
			dateS = dateS.substring(0, 1).toUpperCase()+dateS.substring(1);
		}
		int i = app.getDates().indexOf(dateS);
		app.giveDateIndex(i);
		updateHealtInfo();
	}
	
	@FXML
	public void nextDay() {
		app.nextDate();
		updateHealtInfo();
	}
	
	@FXML
	public void lastDay() {
		app.lastDate();
		updateHealtInfo();
	}
	
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
