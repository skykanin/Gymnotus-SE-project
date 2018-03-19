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

// NY KODE FRA STACK
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// NY KODE FRA STACK, SLUTT


public class TrainerDashboardController extends WindowController implements Initializable {
	
	@FXML JFXComboBox<String> memberComboBox;
    @FXML private Label Velkommen;
    @FXML Label infoText;
    @FXML Label pulsSnittTekst;
    @FXML Label pulsSnittVerdi;
    @FXML LineChart<Date,Number> heartRateChart;
    TrainerDashboardApp app = new TrainerDashboardApp();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (FxApp.getAS().getLoggedInUser() != null) {
			Velkommen.setText("Velkommen, " + FxApp.getAS().getLoggedInUser().getName());
		}
		
		infoText.setText("Velg dra-til-tryne for å visualisere informasjon:");
		app.requestAllUserID();
		ObservableList<String> names = FXCollections.observableArrayList();
		for (String name : app.getNames()) {
			names.add(name);
		}
		
		memberComboBox.setItems(names);
		pulsSnittTekst.setText("");
		pulsSnittVerdi.setText("");
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public void handleMemberComboBox(ActionEvent actionEvent) throws IOException, ParseException {
		String username = memberComboBox.getSelectionModel().getSelectedItem();
		infoText.setText("Viser " + username + "'s info, velg nytt tryne: " );
		
		app.requestHealthInformation_ID(app.getIDfromName(username));
		
		String datesToSet = "";
		String HRsToSet = "";
		int meanHR = 0;
		
		if (app.getDates() != null) {
			
			for (int i = 0; i < app.getDates().size(); i++) {
				datesToSet += app.getDates().get(i) + ", ";
				HRsToSet += app.getRestingHRs().get(i) + ", ";
				meanHR += Integer.parseInt(app.getRestingHRs().get(i))/app.getRestingHRs().size();
			} 
			
			datesToSet = datesToSet.substring(0, datesToSet.length()-2);
			HRsToSet = HRsToSet.substring(0, HRsToSet.length()-2);
			System.out.println(datesToSet);
			System.out.println(HRsToSet);
			pulsSnittTekst.setText(username +"'s snittpuls ligger på:");
			pulsSnittVerdi.setText(meanHR + "");
			
		} else {
			pulsSnittTekst.setText("Ingen helsedata å vise");
			pulsSnittVerdi.setText("");
		}
		
		
		final DateAxis xAxis = new DateAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Puls");
        
        heartRateChart = new LineChart<Date,Number>(xAxis, yAxis);
        heartRateChart.setTitle("Puls for " + username);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
		
        

        XYChart.Series<Date,Number> series = new XYChart.Series<>();
//        series.setName("Puls siste tiden");
          series.getData().add(new XYChart.Data(dateFormat.parse("Jan 11, 2014"), 23));
//          series.getData().add(new XYChart.Data(dateFormat.parse("09/Feb/2014"), 14));
//          series.getData().add(new XYChart.Data(dateFormat.parse("22/Mar/2014"), 15));
//          series.getData().add(new XYChart.Data(dateFormat.parse("14/Apr/2014"), 24));
//          series.getData().add(new XYChart.Data(dateFormat.parse("22/May/2014"), 34));
//          series.getData().add(new XYChart.Data(dateFormat.parse("07/Jun/2014"), 36));
//          series.getData().add(new XYChart.Data(dateFormat.parse("22/Jul/2014"), 22));
//          series.getData().add(new XYChart.Data(dateFormat.parse("21/Aug/2014"), 45));
//          series.getData().add(new XYChart.Data(dateFormat.parse("04/Sep/2014"), 43));
//          series.getData().add(new XYChart.Data(dateFormat.parse("22/Oct/2014"), 17));
//          series.getData().add(new XYChart.Data(dateFormat.parse("30/Nov/2014"), 29));
//          series.getData().add(new XYChart.Data(dateFormat.parse("10/Dec/2014"), 25));
          System.out.println(dateFormat.parse("Jan 11, 2014"));
          System.out.println( "Jan 11, 2014");
          heartRateChart.getData().add(series);

//          /**
//           * Browsing through the Data and applying ToolTip
//           * as well as the class on hover
//           */
//          for (XYChart.Series<Date, Number> s : lineChart.getData()) {
//              for (XYChart.Data<Date, Number> d : s.getData()) {
//                  Tooltip.install(d.getNode(), new Tooltip(
//                          d.getXValue().toString() + "\n" +
//                                  "Number Of Events : " + d.getYValue()));
  //
//                  //Adding class on hover
//                  d.getNode().setOnMouseEntered(event -> d.getNode().getStyleClass().add("onHover"));
  //
//                  //Removing class on exit
//                  d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
//  	            }
//  	        }
//  	    }
	}
	
	
	/*
	 
	 
	@SuppressWarnings({ "unchecked", "rawtypes" })

	public void showPlot(Stage stage) throws ParseException {
        stage.setTitle("Line Chart Sample");
        final DateAxis xAxis = new DateAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Puls");

        final LineChart<Date,Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Puls for "); // + username);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");


        XYChart.Series<Date,Number> series = new XYChart.Series<>();
      //series.setName("Puls siste tiden");
        series.getData().add(new XYChart.Data(dateFormat.parse("11/Jan/2014"), 23));
        series.getData().add(new XYChart.Data(dateFormat.parse("09/Feb/2014"), 14));
        series.getData().add(new XYChart.Data(dateFormat.parse("22/Mar/2014"), 15));
        series.getData().add(new XYChart.Data(dateFormat.parse("14/Apr/2014"), 24));
        series.getData().add(new XYChart.Data(dateFormat.parse("22/May/2014"), 34));
        series.getData().add(new XYChart.Data(dateFormat.parse("07/Jun/2014"), 36));
        series.getData().add(new XYChart.Data(dateFormat.parse("22/Jul/2014"), 22));
        series.getData().add(new XYChart.Data(dateFormat.parse("21/Aug/2014"), 45));
        series.getData().add(new XYChart.Data(dateFormat.parse("04/Sep/2014"), 43));
        series.getData().add(new XYChart.Data(dateFormat.parse("22/Oct/2014"), 17));
        series.getData().add(new XYChart.Data(dateFormat.parse("30/Nov/2014"), 29));
        series.getData().add(new XYChart.Data(dateFormat.parse("10/Dec/2014"), 25));


        Scene scene  = new Scene(lineChart,800,600);
//      scene.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());
        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();
*/
//        /**
//         * Browsing through the Data and applying ToolTip
//         * as well as the class on hover
//         */
//        for (XYChart.Series<Date, Number> s : lineChart.getData()) {
//            for (XYChart.Data<Date, Number> d : s.getData()) {
//                Tooltip.install(d.getNode(), new Tooltip(
//                        d.getXValue().toString() + "\n" +
//                                "Number Of Events : " + d.getYValue()));
//
//                //Adding class on hover
//                d.getNode().setOnMouseEntered(event -> d.getNode().getStyleClass().add("onHover"));
//
//                //Removing class on exit
//                d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
//	            }
//	        }
//	    }
		
	    public static void main(String[] args) {
	        launch(args);
	        
	    }
}

