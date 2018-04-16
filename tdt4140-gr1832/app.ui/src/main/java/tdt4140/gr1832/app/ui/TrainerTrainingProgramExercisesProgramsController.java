package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

import java.net.URL;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

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
import tdt4140.gr1832.app.comparators.InfoDateComparator;
import tdt4140.gr1832.app.containers.ResultContainer;
import tdt4140.gr1832.app.core.TrainerTrainingProgramExercisesProgramsApp;

public class TrainerTrainingProgramExercisesProgramsController extends WindowController implements Initializable {
    
	@FXML
    StackPane root;

	@FXML JFXComboBox<String> memberComboBox;
	@FXML JFXComboBox<String> programComboBox;
	
	@FXML Label infoText;
	@FXML Label	programInfoText;
	
	@FXML Label label0;
	@FXML Label label1;
	@FXML Label label2;
	@FXML Label label3;
	    
	@FXML LineChart<String,Number> chart0;
	@FXML CategoryAxis xAxis0;
	@FXML NumberAxis yAxis0;
    
	@FXML LineChart<String,Number> chart1;
	@FXML CategoryAxis xAxis1;
	@FXML NumberAxis yAxis1;
    
	@FXML LineChart<String,Number> chart2;
	@FXML CategoryAxis xAxis2;
	@FXML NumberAxis yAxis2;
    
	@FXML LineChart<String,Number> chart3;
	@FXML CategoryAxis xAxis3;
	@FXML NumberAxis yAxis3;
     
	TrainerTrainingProgramExercisesProgramsApp app = new TrainerTrainingProgramExercisesProgramsApp();
	
	private String exName;
	private int exID;
	private String programName;
	
	
	private int globalCounter = 0;
	private List<Integer> usersOnProgram;
	Map<String,XYChart.Series<String, Number>> seriesMap = new HashMap<String,XYChart.Series<String, Number>>();

	private List<String> globalDatesList;
	private Map<String, List<Integer>> globalResultsMap = new HashMap<>();
	


    private String updateSeriesMap() {
    		String name = "series" + globalCounter;
    		seriesMap.put("series" + globalCounter, new XYChart.Series<String, Number>());   		
    		globalCounter ++;
    		return name;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void handleProgramComboBox(ActionEvent actionEvent) throws IOException, ParseException {

    	
    		chart0.getData().clear();
		chart1.getData().clear();
		chart2.getData().clear();
		chart3.getData().clear();

		memberComboBox.getSelectionModel().clearSelection(); //Faa memberComboBox tilbake til default
		
		programName = programComboBox.getSelectionModel().getSelectedItem();
		
				
    		app.getExercisesOnAProgram(app.getProgramIDfromName(programName));		
    		infoText.setText("Velg et medlem for aa visualisere resultater");
		programInfoText.setText("Du ser resultater til programmet '" + programName + "'. Se et annet: " );
			
			
    		for (int i = 0; i < app.getExContainers().size(); i++) {
    				
    			exName = app.getExContainers().get(i).getDescription();
    			exID = app.getExContainers().get(i).getExerciseID();
    			app.getResultsOfExercise(exID);
    			globalDatesList = app.getDates(); 
    			Collections.sort(globalDatesList, new InfoDateComparator());
    			for (String date : globalDatesList) {
    				globalResultsMap.put(date, new ArrayList<>());
    			}
    			
    			List<Integer> userIDs = new ArrayList<>();
    			userIDs = app.requestUserIDsOnExercise(exID);
    			
    			for (int t=0 ; t < userIDs.size() ; t++) {
    				app.requestUserInformation_ID(userIDs.get(t)+"");
    				if (!app.getContainerUser().getShareExerciseData()) {
    					userIDs.remove(t);
    				}
    			}
    			
    			//set up resultshashmap
    			for (int j = 0; j < userIDs.size(); j++) { 
    					app.getResultsOfExcerciseAndUser(exID, userIDs.get(j));
    				
    				// add real values to lists in hashmap
    				for (ResultContainer resCon : app.getResContainers()) {
    					if (globalResultsMap.get(resCon.getDate()).size() > j) {
    					} else {
    						List<Integer> temp = globalResultsMap.get(resCon.getDate());
    						temp.add(resCon.getResultParameter());
    						globalResultsMap.replace(resCon.getDate(), temp);
    					}
    				}

	    			// add null-values to lists in hashmap	
	    		    Iterator it = globalResultsMap.entrySet().iterator();
	    		    while (it.hasNext()) {
	    		        Map.Entry pair = (Map.Entry)it.next();
	    		        if (((List<Integer>) pair.getValue()).size() < j+1) {
	    		        		List<Integer> temp = globalResultsMap.get((String) pair.getKey());
	    					temp.add(null);
	    					globalResultsMap.replace((String) pair.getKey(), temp);
	    		        }
	    		    }
	    		}
    			
    			
    			XYChart.Series<String, Number> dummySeries = new XYChart.Series<String, Number>();
    			for (String date: globalDatesList) {
    				dummySeries.getData().add(new XYChart.Data(date.substring(0,date.length()-6), -2));
    			}	
    			
    			int maxValue = -1;
    			
    			if (app.getResContainers() != null) {
    	        switch (i) {
    	        
	            		case 0: 	
	            				yAxis0.setAutoRanging(true);
    	            			chart0.getData().add(dummySeries);
    	            			for (int k = 0 ; k < userIDs.size() ;k++) {
    	            				String seriesName = updateSeriesMap();
    	        					for (String date : globalDatesList) {
    	        						if ((globalResultsMap.get(date).get(k)) != null){
    	        							seriesMap.get(seriesName).getData().add(new XYChart.Data(	date.substring(0,date.length()-6),globalResultsMap.get(date).get(k)));
    	        							if (globalResultsMap.get(date).get(k) > maxValue){
    	        								maxValue = globalResultsMap.get(date).get(k);
    	        							}
    	        						} 
    	        					}

	    	            	        chart0.getData().add(seriesMap.get(seriesName));
	            				}
    	            			
    	            			label0.setText(exName);
    	            			chart0.setOpacity(1);
    	            			chart0.setCreateSymbols(false);
    	            			chart0.setAnimated(false);
    	            			yAxis0.setAutoRanging(false);
	            				yAxis0.setUpperBound( (int) ((maxValue*1.3)/10) *10 );
	            				yAxis0.setLowerBound(0);
	            				break;
    	            			
    	        		case 1: 
    	        				yAxis1.setAutoRanging(true);
	            			chart1.getData().add(dummySeries);
	            			for (int k = 0 ; k < userIDs.size() ;k++) {
    	            				String seriesName = updateSeriesMap();
    	        					for (String date : globalDatesList) {
    	        						if ((globalResultsMap.get(date).get(k)) != null){
    	        							seriesMap.get(seriesName).getData().add(new XYChart.Data(	date.substring(0,date.length()-6),globalResultsMap.get(date).get(k)));
    	        							if (globalResultsMap.get(date).get(k) > maxValue){
    	        								maxValue = globalResultsMap.get(date).get(k);
    	        							}
    	        						} 
    	        					}
		            	        chart1.getData().add(seriesMap.get(seriesName));
            				}
	            			
	            			
	            			label1.setText(exName);
	            			chart1.setOpacity(1);
	            			chart1.setCreateSymbols(false);
	            			chart1.setAnimated(false);
	            			yAxis1.setAutoRanging(false);
	            				yAxis1.setUpperBound( (int) ((maxValue*1.3)/10) *10 );
	            				yAxis1.setLowerBound(0);
	            			break;
    	            case 2: 	
    	            			yAxis2.setAutoRanging(true);
	            			chart2.getData().add(dummySeries);
	            			
	            				
            				for (int k = 0 ; k < userIDs.size() ;k++) {
    	            				String seriesName = updateSeriesMap();
    	        					for (String date : globalDatesList) {
    	        						if ((globalResultsMap.get(date).get(k)) != null){
    	        							seriesMap.get(seriesName).getData().add(new XYChart.Data(	date.substring(0,date.length()-6),globalResultsMap.get(date).get(k)));
    	        							if (globalResultsMap.get(date).get(k) > maxValue){
    	        								maxValue = globalResultsMap.get(date).get(k);
    	        							}
    	        						} 
    	        					}
		            	        chart2.getData().add(seriesMap.get(seriesName));
            				}
	            			
	            			label2.setText(exName);
	            			chart2.setOpacity(1);
	            			chart2.setCreateSymbols(false);
	            			chart2.setAnimated(false);
	            			yAxis2.setAutoRanging(false);
	            			yAxis2.setUpperBound( (int) ((maxValue*1.3)/10) *10 );
	            			yAxis2.setLowerBound(0);
	            			break;
    	            case 3: 	
    	            			yAxis3.setAutoRanging(true);
	            			chart3.getData().add(dummySeries);
	            			for (int k = 0 ; k < userIDs.size() ;k++) {
    	            				String seriesName = updateSeriesMap();
    	        					for (String date : globalDatesList) {
    	        						if ((globalResultsMap.get(date).get(k)) != null){
    	        							seriesMap.get(seriesName).getData().add(new XYChart.Data(	date.substring(0,date.length()-6),globalResultsMap.get(date).get(k)));
    	        							if (globalResultsMap.get(date).get(k) > maxValue){
    	        								maxValue = globalResultsMap.get(date).get(k);
    	        							}
    	        						} 
    	        					}
			            	    chart3.getData().add(seriesMap.get(seriesName));
            				}
	            			
	            			label3.setText(exName);
	            			chart3.setOpacity(1);
	            			chart3.setCreateSymbols(false);
	            			chart3.setAnimated(false);
	            			yAxis3.setAutoRanging(false);
	            				yAxis3.setUpperBound( (int) ((maxValue*1.3)/10) *10 );
	            				yAxis3.setLowerBound(0);
	            			break;
    	        		}
    			} else {
    				programInfoText.setText("Finnes ikke resultater til programmet '" + programName + "'. Se et annet: " );
    				hidePageContent();
    			}
    		}
	
    		memberComboBox.setOpacity(1);
    		infoText.setOpacity(1);
    }

    
    @SuppressWarnings({ "unchecked", "rawtypes" })

    public void handleMemberComboBox(ActionEvent actionEvent) throws IOException, ParseException {
    	// FYLL MED FUNKSJONALITET FRA DASHBOARDCONTROLLER
    	
    	String userName = memberComboBox.getSelectionModel().getSelectedItem();
	
	if (userName != null) {
		
		infoText.setText(userName + " sine resultater skiftet farge. Se andre: " );

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
			infoText.setText(userName + " har valgt aa ikke vise treningsresultater, se nytt medlem: ");
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
		
		memberComboBox.setOpacity(0);
		infoText.setOpacity(0);
		root.setPickOnBounds(false);

		hidePageContent();
		infoText.setText("Velg et medlem for aa visualisere resultater");
		programInfoText.setText("Velg et program for aa visualisere resultater");
		
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
