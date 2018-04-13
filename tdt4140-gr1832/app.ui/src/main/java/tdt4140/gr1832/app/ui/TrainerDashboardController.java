package tdt4140.gr1832.app.ui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;

import tdt4140.gr1832.app.core.TrainerDashboardApp;


import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;

import java.text.DecimalFormat;
import java.text.ParseException;
import javafx.scene.chart.XYChart;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.sun.javafx.applet.FXApplet2;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class TrainerDashboardController extends WindowController implements Initializable {
	
	
	@FXML JFXComboBox<String> memberComboBox;
    @FXML private Label Velkommen;
    @FXML Label infoText;
    @FXML Label pulsSnittTekst;
    @FXML Label pulsSnittVerdi;
    
    @FXML LineChart<String,Number> heartRateChart;
    @FXML CategoryAxis xAxis;
    @FXML NumberAxis yAxis;
    
    @FXML LineChart<String, Number> stepsChart;
    @FXML CategoryAxis xAxis1;
    @FXML NumberAxis yAxis1;
    @FXML Label stepsSnittTekst;
    @FXML Label stepsSnittVerdi;
    @FXML Label heartRateChartTitle;
    @FXML Label stepsChartTitle;
    
    TrainerDashboardApp app;

    @FXML
	private StackPane root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (!FxApp.TEST) { 
				app = new TrainerDashboardApp();
			if (FxApp.getAS().getLoggedInUser() != null) {
				Velkommen.setText("Velkommen, " + FxApp.getAS().getLoggedInUser().getName());
			}
			
			heartRateChart.setLegendVisible(false);
			stepsChart.setLegendVisible(false);
			heartRateChart.setOpacity(0);
			stepsChart.setOpacity(0);
			infoText.setText("Velg et medlem for å visualisere informasjon:");
			
			app.requestAllUserID();
			ObservableList<String> names = FXCollections.observableArrayList();
			for (String name : app.getNames()) {
				names.add(name);
			}
			
			memberComboBox.setItems(names);
			pulsSnittTekst.setText("");
			pulsSnittVerdi.setText("");
			stepsSnittTekst.setText("");
			stepsSnittVerdi.setText("");
		}
		
		root.setPickOnBounds(false);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public void handleMemberComboBox(ActionEvent actionEvent) throws IOException, ParseException {
		if (!FxApp.TEST) {
		String username = memberComboBox.getSelectionModel().getSelectedItem();
		infoText.setText("Du ser " + username + "'s helsedata. Se noen andre: " );
		
		app.requestHealthInformation_ID(app.getIDfromName(username));
		
		//REGNE SNITT START
		double meanHR = 0;
		double meanSteps = 0;
		
		if (app.getRestingHRs() != null && app.getContainerUser().getShareHealthData() && app.getHealthContainers().size() > 0) {
			
			for (int i = 0; i < app.getRestingHRs().size(); i++) {
				
				meanHR += (double) (app.getRestingHRs().get(i))/ (double) (app.getRestingHRs().size());
				meanSteps += (double) (app.getSteps().get(i))/(double) (app.getSteps().size());
			} 
			
			heartRateChartTitle.setText(username + "'s puls");
			stepsChartTitle.setText(username + "'s steps");
		
			DecimalFormat df = new DecimalFormat(" .#");
			
			pulsSnittTekst.setText(username +"'s snittpuls er:");
			pulsSnittVerdi.setText(df.format(meanHR) + "");
			stepsSnittTekst.setText(username +"'s snittsteps er:");
			stepsSnittVerdi.setText(df.format(meanSteps) + "");
			
			//REGNE SNITT SLUTT
			
			// START PLOTT		
			heartRateChart.getData().clear();
			stepsChart.getData().clear();
			
	        XYChart.Series<String,Number> series = new XYChart.Series<>();
	        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
		
			for (int i = 0; i < app.getDates().size() ; i++) {
				series.getData().add(new XYChart.Data(app.getDates().get(i).substring(0,app.getDates().get(i).length()-6 ),app.getRestingHRs().get(i)));
				series2.getData().add(new XYChart.Data(app.getDates().get(i).substring(0,app.getDates().get(i).length()-6 ),app.getSteps().get(i)));
			}
				
			// FORMATTING PLOT
			heartRateChart.setOpacity(1);
			stepsChart.setOpacity(1);
			
			yAxis.setAutoRanging(false);
			yAxis1.setAutoRanging(false);
		   
		    
		    if (((meanHR - meanHR*0.07) % 2) != 0) {
		    		int lowerBound = (int) (meanHR - meanHR*0.07 - ((meanHR - meanHR*0.07) % 2));
		    		 yAxis.setLowerBound(lowerBound);	
		    } else {
		    		yAxis.setLowerBound((int) (meanHR - meanHR*0.07));
		    }
		    
		    if (((meanHR + meanHR*0.07) % 2) != 0) {
			    	int upperBound = (int) (meanHR + meanHR*0.07 + 1);
			    	yAxis.setUpperBound(upperBound);
		    } else {
		    	 	yAxis.setUpperBound((int) (meanHR + meanHR*0.07)); 	
		    }
		    
		    yAxis1.setLowerBound((int) (meanSteps - meanSteps));
		    
		    if ((((int) (meanSteps + meanSteps)) % 500 != 0)) {
		    		int upperBound = (int) (meanSteps + meanSteps + (500 - (meanSteps + meanSteps ) % 500));
		    		yAxis1.setUpperBound(upperBound);		
		    		
		    } else {
		    		yAxis1.setUpperBound((int) (meanSteps + meanSteps));		
		    }
		    
		    yAxis.setTickUnit(2);
		    yAxis.setMinorTickVisible(false);
		    
		    yAxis1.setTickUnit(500);
		    yAxis1.setMinorTickVisible(false);
		    
			heartRateChart.setHorizontalGridLinesVisible(true);
			stepsChart.setHorizontalGridLinesVisible(true);
			
			heartRateChart.setVerticalGridLinesVisible(false);
			stepsChart.setVerticalGridLinesVisible(false);
			
			heartRateChart.setCreateSymbols(false);
			heartRateChart.setAnimated(false);
	        heartRateChart.getData().add(series);
	        
	        stepsChart.setCreateSymbols(false);
	        stepsChart.setAnimated(false);
	        stepsChart.getData().add(series2);

			
		
			
		} else if(!(app.getContainerUser().getShareHealthData())) {
			
			infoText.setText(username + " har valgt å ikke vise sin data, velg et nytt medlem: ");
			pulsSnittTekst.setText("");
			pulsSnittVerdi.setText("");
			stepsSnittTekst.setText("");
			stepsSnittVerdi.setText("");
			
					
			heartRateChart.getData().clear();
			stepsChart.getData().clear();
			
			heartRateChartTitle.setText("");
			stepsChartTitle.setText("");
			
			
			heartRateChart.setOpacity(0);
			stepsChart.setOpacity(0);
			
	     

			
		}
		else {
			infoText.setText(username + " har ikke registrert helsedata, velg et nytt medlem: ");
			pulsSnittTekst.setText("");
			pulsSnittVerdi.setText("");
			stepsSnittTekst.setText("");
			stepsSnittVerdi.setText("");
			
			heartRateChartTitle.setText("");
			stepsChartTitle.setText("");
			
			
			heartRateChart.getData().clear();
			stepsChart.getData().clear();
		

			heartRateChart.setOpacity(0);
			stepsChart.setOpacity(0);
		}
		
		}

	}
	
	
		
	    public static void main(String[] args) {
	        launch(args);
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
}

