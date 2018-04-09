package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import tdt4140.gr1832.app.core.ProgramResultsGraphsApp;
import tdt4140.gr1832.app.core.ResultContainer;

public class ProgramResultsGraphsController extends WindowController implements Initializable {
    
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
     
	ProgramResultsGraphsApp app = new ProgramResultsGraphsApp();
	
	private String exName;
	private int exID;
	private String programName;
	private String userName;
	
	
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

    @SuppressWarnings({ "unchecked", "rawtypes" })

    public void handleProgramComboBox(ActionEvent actionEvent) throws IOException, ParseException {
    		
    	
    		chart0.getData().clear();
		chart1.getData().clear();
		chart2.getData().clear();
		chart3.getData().clear();
		
		
		memberComboBox.getSelectionModel().clearSelection(); //FÅ ComboBox tilbake til default
		
    		
		programName = programComboBox.getSelectionModel().getSelectedItem();
		
    		app.getExercisesOnAProgram(app.getProgramIDfromName(programName));
    		
    		
    		exID = app.getExContainers().get(0).getExerciseID();
		app.getResultsOfExercise(exID);
    		
		if (app.getResContainers() != null) {
			
			programInfoText.setText("Du ser informasjon til programmet " + programName + ". Se et annet: " );
		
			
	    		for (int i = 0; i < app.getExContainers().size(); i++) {
	    				
	    			exName = app.getExContainers().get(i).getDescription();
	    			exID = app.getExContainers().get(i).getExerciseID();
	    			app.getResultsOfExercise(exID);
	    				
	    	        switch (i) {
	    	        
	    	            case 0: label0.setText(exName);
	    	            			chart0.setOpacity(1);
	    	            			
	    	            			 XYChart.Series<String,Number> series0 = new XYChart.Series<>();
	    	            			//FINNE ALLE RESULTATER TIL ØVELSEN, OG PLOTTE RESULTAT I Y OG DATO I X
	    	            			for (int k = 0; k < app.getDates().size() ; k++) {
	    	            				series0.getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
	    	            			}
	    	            			
	    	            			chart0.setCreateSymbols(false);
	    	            			chart0.setAnimated(false);
	    	            	        chart0.getData().add(series0);
	    	            	      
	    	            			break;
	    	            			
	    	            case 1: label1.setText(exName);
	        					chart1.setOpacity(1);
	        					
	        					 XYChart.Series<String,Number> series1 = new XYChart.Series<>();
	 	            			//FINNE ALLE RESULTATER TIL ØVELSEN, OG PLOTTE RESULTAT I Y OG DATO I X
	 	            			for (int k = 0; k < app.getDates().size() ; k++) {
	 	            				series1.getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
	 	            			}
	 	            			
	 	            			chart1.setCreateSymbols(false);
	 	            			chart1.setAnimated(false);
	 	            	        chart1.getData().add(series1);
	    	                     break;
	    	            case 2: label2.setText(exName);
							chart2.setOpacity(1);
							
							 XYChart.Series<String,Number> series2 = new XYChart.Series<>();
		            			//FINNE ALLE RESULTATER TIL ØVELSEN, OG PLOTTE RESULTAT I Y OG DATO I X
		            			for (int k = 0; k < app.getDates().size() ; k++) {
		            				series2.getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
		            			}
		            			
		            			chart2.setCreateSymbols(false);
		            			chart2.setAnimated(false);
		            	        chart2.getData().add(series2);
							break;
	    	            case 3: label3.setText(exName);
							chart3.setOpacity(1);
							
							 XYChart.Series<String,Number> series3 = new XYChart.Series<>();
		            			//FINNE ALLE RESULTATER TIL ØVELSEN, OG PLOTTE RESULTAT I Y OG DATO I X
		            			for (int k = 0; k < app.getDates().size() ; k++) {
		            				series3.getData().add(new XYChart.Data(app.getDates().get(k).substring(0,app.getDates().get(k).length()-6 ),app.getResults().get(k)));
		            			}
		            			
		            			chart3.setCreateSymbols(false);
		            			chart3.setAnimated(false);
		            	        chart3.getData().add(series3);
							break;
				}
	    		}
		} else {
			programInfoText.setText("Det finnes ikke resultater til programmet " + programName + ". Se et annet: " );
			hidePageContent();
	    	}
		
		//programComboBox.getSelectionModel().clearSelection(); //Få programComboBox tilbake til null
    }

    
    @SuppressWarnings({ "unchecked", "rawtypes" })

    public void handleMemberComboBox(ActionEvent actionEvent) throws IOException, ParseException {
    	// FYLL MED FUNKSJONALITET FRA DASHBOARDCONTROLLER
    	
    	String userName = memberComboBox.getSelectionModel().getSelectedItem();
	
	if (userName != null) {
		
		infoText.setText("Du sammenligner " + userName + "med snittet av resultater. Se noen andre: " );
		
		List<ResultContainer> resCons = new ArrayList<>();
		
		
		app.requestUserInformation_ID(app.getIDfromName(userName));
		int userID = Integer.parseInt(app.getIDfromName(userName));	
		
		
	
    		
    		
	if (app.getContainerUser().getShareExerciseData()) {
		
		for (int i = 0; i < 4; i++) {
			
		
			exName = app.getExContainers().get(i).getDescription();
			exID = app.getExContainers().get(i).getExerciseID();
			resCons = app.getResultsOfExcerciseAndUser(exID, userID);
			
			
			if (resCons !=  null) {
				
	    	        switch (i) {
	    	        
	    	            case 0: 	
	    	            			label0.setText(exName);
	    	            			chart0.setOpacity(1);
	    	            			XYChart.Series<String,Number> userSeries0 = new XYChart.Series<>();
	    	            			//FINNE ALLE RESULTATER TIL ØVELSEN, OG PLOTTE RESULTAT I Y OG DATO I X
	    	            			for (int k = 0; k < app.getDatesFromList(resCons).size() ; k++) {
	    	            				userSeries0.getData().add(new XYChart.Data(app.getDatesFromList(resCons).get(k).substring(0,app.getDatesFromList(resCons).get(k).length()-6 ),app.getResultsFromList(resCons).get(k)));
	    	            			}
	    	            			
	    	            			chart0.setCreateSymbols(false);
	    	            			chart0.setAnimated(false);
	    	            	        chart0.getData().add(userSeries0);
	    	            			break;
	    	            case 1: 	
	            			label1.setText(exName);
	            			chart1.setOpacity(1);
	            			XYChart.Series<String,Number> userSeries1 = new XYChart.Series<>();
	            			//FINNE ALLE RESULTATER TIL ØVELSEN, OG PLOTTE RESULTAT I Y OG DATO I X
	            			for (int k = 0; k < app.getDatesFromList(resCons).size() ; k++) {
	            				userSeries1.getData().add(new XYChart.Data(app.getDatesFromList(resCons).get(k).substring(0,app.getDatesFromList(resCons).get(k).length()-6 ),app.getResultsFromList(resCons).get(k)));
	            			}
	            			
	            			chart1.setCreateSymbols(false);
	            			chart1.setAnimated(false);
	            	        chart1.getData().add(userSeries1);
	            			break;
	            			
	    	            case 2: 	
	            			label2.setText(exName);
	            			chart2.setOpacity(1);
	            			XYChart.Series<String,Number> userSeries2 = new XYChart.Series<>();
	            			//FINNE ALLE RESULTATER TIL ØVELSEN, OG PLOTTE RESULTAT I Y OG DATO I X
	            			for (int k = 0; k < app.getDatesFromList(resCons).size() ; k++) {
	            				userSeries2.getData().add(new XYChart.Data(app.getDatesFromList(resCons).get(k).substring(0,app.getDatesFromList(resCons).get(k).length()-6 ),app.getResultsFromList(resCons).get(k)));
	            			}
	            			
	            			chart2.setCreateSymbols(false);
	            			chart2.setAnimated(false);
	            	        chart2.getData().add(userSeries2);
	            			break;
	            			
	    	            case 3: 	
	            			label3.setText(exName);
	            			chart3.setOpacity(1);
	            			XYChart.Series<String,Number> userSeries3 = new XYChart.Series<>();
	            			//FINNE ALLE RESULTATER TIL ØVELSEN, OG PLOTTE RESULTAT I Y OG DATO I X
	            			for (int k = 0; k < app.getDatesFromList(resCons).size() ; k++) {
	            				userSeries3.getData().add(new XYChart.Data(app.getDatesFromList(resCons).get(k).substring(0,app.getDatesFromList(resCons).get(k).length()-6 ),app.getResultsFromList(resCons).get(k)));
	            			}
	            			
	            			chart3.setCreateSymbols(false);
	            			chart3.setAnimated(false);
	            	        chart3.getData().add(userSeries3);
	            			break;
	    	            			

	    	        		}
				} else {
					infoText.setText("Brukeren har ikke ført resultater i programmet " + programName + ". Se et annet: " );
				}
			}
		
				
		} else {
			infoText.setText("Brukeren har valgt å ikke vise info, se en annen: ");
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
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		hidePageContent();
		infoText.setText("Velg en venn for å visualisere informasjon:");
		programInfoText.setText("Velg en venn for å visualisere informasjon:");
		
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
