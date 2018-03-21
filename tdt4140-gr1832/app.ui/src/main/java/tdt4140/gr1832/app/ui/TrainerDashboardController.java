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
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.chart.XYChart;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    
    TrainerDashboardApp app = new TrainerDashboardApp();

    @FXML
	private StackPane root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if (FxApp.getAS().getLoggedInUser() != null) {
			Velkommen.setText("Velkommen, " + FxApp.getAS().getLoggedInUser().getName());
		}
		
		
		infoText.setText("Velg tryne for å visualisere informasjon:");
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
		
		// START PLOTT
		
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
		
		heartRateChart.getData().clear();
		
		
		
        XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName("Puls for " + username);
		
		for (int i = 0; i < app.getDates().size() ; i++) {
			series.getData().add(new XYChart.Data(app.getDates().get(i),Integer.parseInt(app.getRestingHRs().get(i))));
		}
//        series.getData().add(new XYChart.Data("Januar",23));
//        series.getData().add(new XYChart.Data("Februar",22));
//        series.getData().add(new XYChart.Data("Mars",24));
		
		
		int min = 1000;
		
		for (int i = 0; i < app.getDates().size() ; i++) {
			if (Integer.parseInt(app.getRestingHRs().get(i)) < min) {
				min = Integer.parseInt(app.getRestingHRs().get(i));
			}
		}
		
		System.out.println(min);
			if (min > 10) {
				yAxis.setLowerBound(min - 10);
			}
			
			
		heartRateChart.setAnimated(false);
          
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

