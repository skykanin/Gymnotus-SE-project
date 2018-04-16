package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.text.ParseException;

import java.net.URL;
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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import tdt4140.gr1832.app.comparators.InfoDateComparator;
import tdt4140.gr1832.app.containers.ResultContainer;
import tdt4140.gr1832.app.core.TrainerTrainingProgramExercisesExercisesApp;


public class TrainerTrainingProgramExercisesExercisesController extends WindowController implements Initializable {
    
	@FXML
    private StackPane root;
    
    @FXML JFXButton tilProgram;
	@FXML JFXComboBox<String> exerciseComboBox;

	@FXML Label exInfoText;
	
	@FXML Label label1;
	
	@FXML LineChart<String,Number> chart1;
	@FXML CategoryAxis xAxisOne;
	@FXML NumberAxis yAxisOne;
  
	TrainerTrainingProgramExercisesExercisesApp app = new TrainerTrainingProgramExercisesExercisesApp();

	private int globalCounter = 0;

	private Map<String, XYChart.Series<String, Number>> seriesMap = new HashMap<>();
	private Map<String, List<Integer>> globalResultsMap = new HashMap<>();
	List<String> globalDatesList = new ArrayList<>();
    
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
    	
		String name = "series" + globalCounter ;
		
		seriesMap.put("series" + globalCounter, new XYChart.Series<String, Number>());   		
		globalCounter ++;
		return name;
    }

@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public void handleExerciseComboBox(ActionEvent actionEvent) throws IOException, ParseException {
		
		//make ready for new plot
		globalDatesList.clear();
    		globalResultsMap.clear();
		chart1.getData().clear();
		seriesMap.clear();
		globalCounter = 0;

		yAxisOne.setAutoRanging(true);
		
		//set up variables for new plot
	    	String exName = exerciseComboBox.getSelectionModel().getSelectedItem();
		exInfoText.setText("Du visualiserer resultatene til '" + exName + "'. Bytt øvelse her: " );
		int exID = app.getIDfromExerciseName(exName);
		app.getResultsOfExercise(exID);
		globalDatesList = app.getDates(); 
		Collections.sort(globalDatesList, new InfoDateComparator());
		for (String date : globalDatesList) {
			globalResultsMap.put(date, new ArrayList<>());
		}
		
		//set up resultshashmap
		for (int i = 0; i < app.requestUserIDsOnExercise(exID).size(); i++) { 
				app.getResultsOfExcerciseAndUser(exID, app.requestUserIDsOnExercise(exID).get(i));
			
			// add real values to lists in hashmap
			for (ResultContainer resCon : app.getResContainers()) {
				if (globalResultsMap.get(resCon.getDate()).size() > i) {
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
		        if (((List<Integer>) pair.getValue()).size() < i+1) {
		        		List<Integer> temp = globalResultsMap.get((String) pair.getKey());
					temp.add(null);
					globalResultsMap.replace((String) pair.getKey(), temp);
		        }
		    }
		}
		
		//make series of all dates, to "lock" x-axis to show dates in sorted order
		XYChart.Series<String, Number> dummySeries = new XYChart.Series<String, Number>();
		for (String date: globalDatesList) {
			dummySeries.getData().add(new XYChart.Data(date.substring(0,date.length()-6), -1));
		}	
		chart1.getData().add(dummySeries);
		
		//Make xychart.series and add to chart
		int maxValue = -1;
		if (app.getResults() != null) {
			for (int k = 0 ;  k < app.requestUserIDsOnExercise(exID).size(); k++ ) {
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
		} else {
			hidePageContent();
			exInfoText.setText("'" + exName + "' har ingen registrerte resultat, velg ny øvelse: ");	
		}
		
		//formatting plot
		
		label1.setText(exName);
		chart1.setOpacity(1);
		chart1.setCreateSymbols(false);
		chart1.setAnimated(false);
		yAxisOne.setAutoRanging(false);
		yAxisOne.setUpperBound( (int) ((maxValue*1.3)/10) *10 );
		yAxisOne.setLowerBound(0);
    }
		
		

    private void hidePageContent() {
    		chart1.setLegendVisible(false);
		chart1.setOpacity(0);
		label1.setText("");
		seriesMap.clear();
		globalCounter = 0;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		root.setPickOnBounds(false);

		hidePageContent();

		exInfoText.setText("Velg en øvelse for å visualisere resultater:");
		app.requestExerciseContainers();
		exerciseComboBox.setItems(app.getNamesOfExercises());
		
	}
    
    public static void main(String[] args) {
        launch(args);
    }
}
