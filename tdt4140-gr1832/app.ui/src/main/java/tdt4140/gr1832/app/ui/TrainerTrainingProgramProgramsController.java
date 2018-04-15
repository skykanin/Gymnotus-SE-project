package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

import java.net.URL;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import containers.ResultContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.TrainerTrainingProgramProgramsApp;

public class TrainerTrainingProgramProgramsController extends WindowController implements Initializable {
    
	@FXML
    private StackPane root;

	@FXML JFXComboBox<String> memberComboBox;
	@FXML JFXComboBox<String> programComboBox;
	
	@FXML Label infoText;
	@FXML Label	programInfoText;
	
	@FXML Label label0;
	@FXML Label label1;
	@FXML Label label2;
	@FXML Label label3;
	    
	@FXML LineChart<String,Number> chart0;
	@FXML CategoryAxis xAxisOne;
	@FXML NumberAxis yAxisOne;
    
	@FXML LineChart<String,Number> chart1;
	@FXML CategoryAxis xAxisTwo;
	@FXML NumberAxis yAxisTwo;
    
	@FXML LineChart<String,Number> chart2;
	@FXML CategoryAxis xAxisThree;
	@FXML NumberAxis yAxisThree;
    
	@FXML LineChart<String,Number> chart3;
	@FXML CategoryAxis xAxisFour;
	@FXML NumberAxis yAxisFour;
     
	TrainerTrainingProgramProgramsApp app = new TrainerTrainingProgramProgramsApp();
	
	private String exName;
	private int exID;
	private String programName;
	
	
	private int globalCounter = 0;
	private List<Integer> usersOnProgram;
	Map<String,XYChart.Series<String, Number>> seriesMap = new HashMap<String,XYChart.Series<String, Number>>();
	
	
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

    private String updateSeriesMap() {
    	
    		String name = "series" + globalCounter;
    		
    		seriesMap.put("series" + globalCounter, new XYChart.Series<String, Number>());   		
    		globalCounter ++;
    		return name;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void handleProgramComboBox(ActionEvent actionEvent) throws IOException, ParseException {
    		
    	// LAGE TIL SLIK AT FOR HVER BRUKER SOM HAR RESULTAT I PROGRAMMET, LAGES EN NY SERIE
    	
    	
    	// http://<ip-addresse>:<port>/api/result/get_results_by_user_and_exercise?user_id=<user_id>&exercise_id=<exercise_id>
    	// http://<ip-addresse>:<port>/api/exercise_program/get_users?programID=<id>
    	
    	
    		chart0.getData().clear();
		chart1.getData().clear();
		chart2.getData().clear();
		chart3.getData().clear();

		//memberComboBox.getSelectionModel().clearSelection(); //FÅ memberComboBox tilbake til default
		
		programName = programComboBox.getSelectionModel().getSelectedItem();
		
		usersOnProgram = app.getUserIDsOnProgram(programName);		
    		app.getExercisesOnAProgram(app.getProgramIDfromName(programName));		
		
			programInfoText.setText("Du ser resultater til programmet " + programName + ". Se et annet: " );
			String seriesName;
	    		for (int i = 0; i < app.getExContainers().size(); i++) {
	    				
	    			exName = app.getExContainers().get(i).getDescription();
	    			exID = app.getExContainers().get(i).getExerciseID();
	    			app.getResultsOfExercise(exID);
	    			
	    			if (app.getResContainers() != null) {
	    	        switch (i) {
	    	        
    	            		case 0: 	label0.setText(exName);
	    	            			chart0.setOpacity(1);
	    	            			chart0.setCreateSymbols(false);
	    	            			chart0.setAnimated(false);
	    	            			
	    	            			for (int user : usersOnProgram) {
	    	            				
	    	            			

	    	            				app.getResultsOfExcerciseAndUser(exID, user);
	    	            				
	    	            				if (app.getResContainers() != null) {
		    	            				app.requestUserInformation_ID(user+"");
			    	            			seriesName = updateSeriesMap();
			    	            			
			    	            			for (int k = 0; k < app.getDates().size() ; k++) {
			    	            				seriesMap.get(seriesName).getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
			    	            			}
		    	            			
			    	            		
			    	            	        chart0.getData().add(seriesMap.get(seriesName));
	    	            				}
	    	            			}
	    	            			break;
	    	            			
	    	        		case 1: label1.setText(exName);
		            			chart1.setOpacity(1);
		            			chart1.setCreateSymbols(false);
		            			chart1.setAnimated(false);
		            			
		            			for (int user : usersOnProgram) {
		            				
		            				app.getResultsOfExcerciseAndUser(exID, user);
		            				if (app.getResContainers() != null) {
		            				app.requestUserInformation_ID(user+"");
				            			seriesName = updateSeriesMap();
				            			
				            			for (int k = 0; k < app.getDates().size() ; k++) {
				            				seriesMap.get(seriesName).getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
				            			}
			            			
				            		
				            	        chart1.getData().add(seriesMap.get(seriesName));
		            				}
		            			}
		            			break;
	    	            case 2: 	label2.setText(exName);
		            			chart2.setOpacity(1);
		            			chart2.setCreateSymbols(false);
		            			chart2.setAnimated(false);
		            			
		            			for (int user : usersOnProgram) {
		            				
		            				app.getResultsOfExcerciseAndUser(exID, user);
		            				if (app.getResContainers() != null) {
			            				app.requestUserInformation_ID(user+"");
			            				
				            			seriesName = updateSeriesMap();
				            			
				            			for (int k = 0; k < app.getDates().size() ; k++) {
				            				seriesMap.get(seriesName).getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
				            			}
			            			
				            		
				            	        chart2.getData().add(seriesMap.get(seriesName));
		            				}
		            			}
		            			break;
	    	            case 3: 	label3.setText(exName);
		            			chart3.setOpacity(1);
		            			chart3.setCreateSymbols(false);
		            			chart3.setAnimated(false);
		            			
		            			for (int user : usersOnProgram) {
		            				
		            				app.getResultsOfExcerciseAndUser(exID, user);
		            				if (app.getResContainers() != null) {
			            				app.requestUserInformation_ID(user+"");
				            			seriesName = updateSeriesMap();
				            			
				            			for (int k = 0; k < app.getDates().size() ; k++) {
				            				seriesMap.get(seriesName).getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
				            			}
			            			
				            		
				            	        chart3.getData().add(seriesMap.get(seriesName));
		            				}
		            			}
		            			break;
	    	        		}
	    			} else {
	    				programInfoText.setText("Finnes ikke resultater til programmet " + programName + ". Se et annet: " );
	    				hidePageContent();
	    			}
	    		}
		
		
    }

    
    @SuppressWarnings({ "unchecked", "rawtypes" })

    public void handleMemberComboBox(ActionEvent actionEvent) throws IOException, ParseException {
    	// FYLL MED FUNKSJONALITET FRA DASHBOARDCONTROLLER
    	
    	String userName = memberComboBox.getSelectionModel().getSelectedItem();
	
	if (userName != null) {
		
		infoText.setText(userName + "sine resultater ble farget. Se andre: " );

		int user = Integer.parseInt(app.getIDfromName(userName));	
		app.requestUserInformation_ID(user+"");
		
		
	
    		
    		
	if (app.getContainerUser().getShareExerciseData()) {
		
		
			
		for (int i = 0; i < 4; i++) {
			
		
			exName = app.getExContainers().get(i).getDescription();
			exID = app.getExContainers().get(i).getExerciseID();
			app.getResultsOfExcerciseAndUser(exID, user);
			
			if (app.getDates() !=  null) {
				
				String seriesName;
			
				
	    	        switch (i) {
	    	        
	    	            case 0: 	
	    	            	
    	            			label0.setText(exName);
    	            			chart0.setOpacity(1);
    	            			chart0.setCreateSymbols(false);
    	            			chart0.setAnimated(false);
    	            			
    	            				
    	            				
    	            				app.requestUserInformation_ID(user+"");
	    	            			seriesName = updateSeriesMap();

	    	            			for (int k = 0; k < app.getDates().size() ; k++) {
	    	            				seriesMap.get(seriesName).getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
	    	            			}

	    	            	        chart0.getData().add(seriesMap.get(seriesName));
	    	            	    
    	            			
    	            			break;
    	            			
	    	            case 1: 	
	    	            	
	    	            		label1.setText(exName);
	            			chart1.setOpacity(1);
	            			chart1.setCreateSymbols(false);
	            			chart1.setAnimated(false);
	            			
	            				
	            				
	            				app.requestUserInformation_ID(user+"");
    	            			seriesName = updateSeriesMap();

    	            			for (int k = 0; k < app.getDates().size() ; k++) {
    	            				seriesMap.get(seriesName).getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
    	            			}

    	            	        chart1.getData().add(seriesMap.get(seriesName));
    	            	    
	            			
	            			break;
	            			
	    	            case 2: 	
	            			
	    	            		label2.setText(exName);
	            			chart2.setOpacity(1);
	            			chart2.setCreateSymbols(false);
	            			chart2.setAnimated(false);
	            			
	            				
	            				
	            				app.requestUserInformation_ID(user+"");
    	            			seriesName = updateSeriesMap();

    	            			for (int k = 0; k < app.getDates().size() ; k++) {
    	            				seriesMap.get(seriesName).getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
    	            			}

    	            	        chart2.getData().add(seriesMap.get(seriesName));
    	            	    
	            			
	            			break;
	            			
	    	            case 3:
	    	            	
	    	            		label3.setText(exName);
	            			chart3.setOpacity(1);
	            			chart3.setCreateSymbols(false);
	            			chart3.setAnimated(false);
	            			
	            				
	            				
	            				app.requestUserInformation_ID(user+"");
    	            			seriesName = updateSeriesMap();

    	            			for (int k = 0; k < app.getDates().size() ; k++) {
    	            				seriesMap.get(seriesName).getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
    	            			}

    	            	        chart3.getData().add(seriesMap.get(seriesName));
    	            	    
	            			
	            			break;

	    	        		}
	    	        
				} else {
					infoText.setText(userName + " har ikke resultater i programmet. Se nytt medlem: " );
				}
			} 
		
				
		} else {
			infoText.setText(userName + " har valgt å ikke vise treningsresultater, se nytt medlem: ");
		}
	} else {
		//Do not perform any action
	}
	
		
}
	

    //HJELPEMETODE
    private void hidePageContent() {
    	
    		chart0.setLegendVisible(false);
		chart1.setLegendVisible(false);
		chart2.setLegendVisible(false);
		chart3.setLegendVisible(false);
		
		chart0.setOpacity(0);
		chart1.setOpacity(0);
		chart2.setOpacity(0);
		chart3.setOpacity(0);
		
		label0.setText("");
		label1.setText("");
		label2.setText("");
		label3.setText("");
		
		seriesMap.clear();
		globalCounter=0;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		hidePageContent();
		infoText.setText("Velg et medlem for å visualisere resultater");
		programInfoText.setText("Velg et program for å visualisere resultater");
		
		ObservableList<String> names = FXCollections.observableArrayList();
		for (String name : app.getNames()) {
			names.add(name);
		}
		
		ObservableList<String> programs = FXCollections.observableArrayList();
		for (String program : app.getNamesOfPrograms()) {
			programs.add(program);
		}

		memberComboBox.setItems(names);
		programComboBox.setItems(programs);
		
	}
    
    public static void main(String[] args) {
        launch(args);
    }

}
