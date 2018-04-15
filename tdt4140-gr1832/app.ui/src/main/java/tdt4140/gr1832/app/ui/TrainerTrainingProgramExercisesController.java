package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.text.ParseException;

import java.net.URL;
import java.util.HashMap;
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
import tdt4140.gr1832.app.core.TrainerTrainingProgramExercisesApp;


public class TrainerTrainingProgramExercisesController extends WindowController implements Initializable {
    
	@FXML
    private StackPane root;
    
    @FXML JFXButton tilProgram;
	@FXML JFXComboBox<String> exerciseComboBox;

	@FXML Label exInfoText;
	
	@FXML Label label1;
	
	@FXML LineChart<String,Number> chart1;
	@FXML CategoryAxis xAxisOne;
	@FXML NumberAxis yAxisOne;
    
	
	TrainerTrainingProgramExercisesApp app = new TrainerTrainingProgramExercisesApp();

	private int globalCounter = 0;

	private Map<String, XYChart.Series<String, Number>> seriesMap = new HashMap<>();
	
    
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
 
		chart1.getData().clear();
			
	    	String exName = exerciseComboBox.getSelectionModel().getSelectedItem();
		exInfoText.setText("Du visualiserer resultatene til '" + exName + "'. Bytt øvelse her: " );
		

		int exID = app.getIDfromExerciseName(exName);
		app.getResultsOfExercise(exID);
		
		label1.setText(exName);
		
		chart1.setOpacity(1);
		chart1.setCreateSymbols(false);
		chart1.setAnimated(false);
		
		if (app.getResults() != null) {
			for (int user : app.requestUserIDsOnExercise(exID)) {
				
				app.getResultsOfExcerciseAndUser(exID, user);

				app.requestUserInformation_ID(user+"");
					String seriesName = updateSeriesMap();
					
					for (int k = 0; k < app.getResults().size() ; k++) {
						seriesMap.get(seriesName).getData().add(new XYChart.Data(
							app.getDates().get(k).substring(0,app.getDates().get(k).length()-6)
										,app.getResults().get(k)));
					}
					chart1.getData().add(seriesMap.get(seriesName));
			}
		} else {
			hidePageContent();
			exInfoText.setText("'"+exName + "' har ingen registrerte resultat, velg ny øvelse: ");	
		}
		
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
		
		hidePageContent();

		exInfoText.setText("Velg en øvelse for å visualisere resultater:");
		app.requestExerciseContainers();
		
		exerciseComboBox.setItems(app.getNamesOfExercises());
		
	}
    
    public static void main(String[] args) {
        launch(args);
    }

}
