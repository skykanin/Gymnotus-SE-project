package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	@FXML JFXComboBox<String> exMemberComboBox;
	
	@FXML Label exInfoText;
	@FXML Label exLabelOne;
	@FXML Label exLabelTwo;
	@FXML Label exLabelThree;
	@FXML Label exLabelFour;
	
	    
	@FXML LineChart<String,Number> exChartOne;
	@FXML CategoryAxis xAxisOne;
	@FXML NumberAxis yAxisOne;
    
	@FXML LineChart<String,Number> exChartTwo;
	@FXML CategoryAxis xAxisTwo;
	@FXML NumberAxis yAxisTwo;
    
	@FXML LineChart<String,Number> exChartThree;
	@FXML CategoryAxis xAxisThree;
	@FXML NumberAxis yAxisThree;
    
	@FXML LineChart<String,Number> exChartFour;
	@FXML CategoryAxis xAxisFour;
	@FXML NumberAxis yAxisFour;
     
	TrainerTrainingProgramExercisesApp app = new TrainerTrainingProgramExercisesApp();
    
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
	
	public void exHandleMemberComboBox(ActionEvent actionEvent) throws IOException, ParseException {
    	// FYLL MED FUNKSJONALITET FRA DASHBOARDCONTROLLER

    	String username = exMemberComboBox.getSelectionModel().getSelectedItem();
	exInfoText.setText("Du ser " + username + "'s øvelseslogg. Se noen andre: " );
		
	app.requestHealthInformation_ID(app.getIDfromName(username));
	
	if (app.getResult1() != null && app.getContainerUser().getShareExerciseData()) {
	
		exChartOne.getData().clear();
		exChartTwo.getData().clear();
		exChartThree.getData().clear();
		exChartFour.getData().clear();
		
	    XYChart.Series<String,Number> series = new XYChart.Series<>();
	    XYChart.Series<String,Number> series2 = new XYChart.Series<>();
	    XYChart.Series<String,Number> series3 = new XYChart.Series<>();
	    XYChart.Series<String,Number> series4 = new XYChart.Series<>();
	
		for (int i = 0; i < app.getDates().size() ; i++) {
			
			//MERK: DET REFERERES TIL DUMMY-METODER I ttpeAPP, MÅ KOBLES TIL DATABASEN
			series.getData().add(new XYChart.Data(app.getDates().get(i).substring(0,app.getDates().get(i).length()-6 ),app.getResult1().get(i)));
			series2.getData().add(new XYChart.Data(app.getDates().get(i).substring(0,app.getDates().get(i).length()-6 ),app.getResult2().get(i)));
			series3.getData().add(new XYChart.Data(app.getDates().get(i).substring(0,app.getDates().get(i).length()-6 ),app.getResult3().get(i)));
			series4.getData().add(new XYChart.Data(app.getDates().get(i).substring(0,app.getDates().get(i).length()-6 ),app.getResult4().get(i)));
		}
		
		exChartOne.setOpacity(1);
		exChartTwo.setOpacity(1);
		exChartThree.setOpacity(1);
		exChartFour.setOpacity(1);
		
		exLabelOne.setText("temp: Biceps");
		exLabelTwo.setText("temp: Triceps");
		exLabelThree.setText("temp: Qatroceps");
		exLabelFour.setText("temp: Sinkoceps");
		
		exChartOne.setCreateSymbols(false);
		exChartOne.setAnimated(false);
        exChartOne.getData().add(series);
        
        exChartTwo.setCreateSymbols(false);
        exChartTwo.setAnimated(false);
        exChartTwo.getData().add(series2);
        
        exChartThree.setCreateSymbols(false);
        exChartThree.setAnimated(false);
        exChartThree.getData().add(series3);
        
        exChartFour.setCreateSymbols(false);
        exChartFour.setAnimated(false);
        exChartFour.getData().add(series4);

	
	} else if(!(app.getContainerUser().getShareExerciseData())) {
		exInfoText.setText(username + " har valgt å ikke vise sin data, velg en ny venn: ");
		hidePageContent();

	} else {
		exInfoText.setText(username + " har ikke registrert helsedata, velg en ny venn: ");
		hidePageContent();
	}
		
	}
    
    //HJELPEMETODE
    private void hidePageContent() {
    	
    		exChartOne.setLegendVisible(false);
		exChartTwo.setLegendVisible(false);
		exChartThree.setLegendVisible(false);
		exChartFour.setLegendVisible(false);
		
		exChartOne.setOpacity(0);
		exChartTwo.setOpacity(0);
		exChartThree.setOpacity(0);
		exChartFour.setOpacity(0);
		
		exLabelOne.setText("");
		exLabelTwo.setText("");
		exLabelThree.setText("");
		exLabelFour.setText("");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		hidePageContent();
		exInfoText.setText("Velg en venn for å visualisere informasjon:");
		
		app.requestAllUserID();
		
		ObservableList<String> names = FXCollections.observableArrayList();
		for (String name : app.getNames()) {
			names.add(name);
		}
		
		exMemberComboBox.setItems(names);
		
	}
    
    public static void main(String[] args) {
        launch(args);
    }

}
