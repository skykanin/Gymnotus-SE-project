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

public class ProgramResultsGraphsController extends WindowController implements Initializable {
    
	@FXML
    private StackPane root;

	@FXML JFXComboBox<String> memberComboBox;
	@FXML JFXComboBox<String> programComboBox;
	
	@FXML Label infoText;
	@FXML Label	programInfoText;
	@FXML Label labelOne;
	@FXML Label labelTwo;
	@FXML Label labelThree;
	@FXML Label labelFour;
	    
	@FXML LineChart<String,Number> chartOne;
	@FXML CategoryAxis xAxisOne;
	@FXML NumberAxis yAxisOne;
    
	@FXML LineChart<String,Number> chartTwo;
	@FXML CategoryAxis xAxisTwo;
	@FXML NumberAxis yAxisTwo;
    
	@FXML LineChart<String,Number> chartThree;
	@FXML CategoryAxis xAxisThree;
	@FXML NumberAxis yAxisThree;
    
	@FXML LineChart<String,Number> chartFour;
	@FXML CategoryAxis xAxisFour;
	@FXML NumberAxis yAxisFour;
     
	ProgramResultsGraphsApp app = new ProgramResultsGraphsApp();
	
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
    	
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })

    public void handleMemberComboBox(ActionEvent actionEvent) throws IOException, ParseException {
    	// FYLL MED FUNKSJONALITET FRA DASHBOARDCONTROLLER

    	String username = memberComboBox.getSelectionModel().getSelectedItem();
	infoText.setText("Du ser " + username + "'s øvelseslogg. Se noen andre: " );
		
	app.requestHealthInformation_ID(app.getIDfromName(username));
	
	if (app.getResult1() != null && app.getContainerUser().getShareExerciseData()) {
	
		chartOne.getData().clear();
		chartTwo.getData().clear();
		chartThree.getData().clear();
		chartFour.getData().clear();
		
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
		
		chartOne.setOpacity(1);
		chartTwo.setOpacity(1);
		chartThree.setOpacity(1);
		chartFour.setOpacity(1);
		
		labelOne.setText("temp: Biceps");
		labelTwo.setText("temp: Triceps");
		labelThree.setText("temp: Qatroceps");
		labelFour.setText("temp: Sinkoceps");
		
		chartOne.setCreateSymbols(false);
		chartOne.setAnimated(false);
        chartOne.getData().add(series);
        
        chartTwo.setCreateSymbols(false);
        chartTwo.setAnimated(false);
        chartTwo.getData().add(series2);
        
        chartThree.setCreateSymbols(false);
        chartThree.setAnimated(false);
        chartThree.getData().add(series3);
        
        chartFour.setCreateSymbols(false);
        chartFour.setAnimated(false);
        chartFour.getData().add(series4);

	
	} else if(!(app.getContainerUser().getShareExerciseData())) {
		infoText.setText(username + " har valgt å ikke vise sin data, velg en ny venn: ");
		hidePageContent();

	} else {
		infoText.setText(username + " har ikke registrert helsedata, velg en ny venn: ");
		hidePageContent();
	}
		
	}
    
    //HJELPEMETODE
    private void hidePageContent() {
    	
    		chartOne.setLegendVisible(false);
		chartTwo.setLegendVisible(false);
		chartThree.setLegendVisible(false);
		chartFour.setLegendVisible(false);
		
		chartOne.setOpacity(0);
		chartTwo.setOpacity(0);
		chartThree.setOpacity(0);
		chartFour.setOpacity(0);
		
		labelOne.setText("");
		labelTwo.setText("");
		labelThree.setText("");
		labelFour.setText("");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		hidePageContent();
		infoText.setText("Velg en venn for å visualisere informasjon:");
		programInfoText.setText("Velg en venn for å visualisere informasjon:");
		
		app.requestAllUserID();
		
		ObservableList<String> names = FXCollections.observableArrayList();
		for (String name : app.getNames()) {
			names.add(name);
		}
		
		memberComboBox.setItems(names);
		
	}
    
    public static void main(String[] args) {
        launch(args);
    }

}
