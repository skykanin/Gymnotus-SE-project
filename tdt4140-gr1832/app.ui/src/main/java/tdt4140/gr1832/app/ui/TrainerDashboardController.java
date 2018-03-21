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
import java.text.ParseException;
import javafx.scene.chart.XYChart;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

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
    
    TrainerDashboardApp app = new TrainerDashboardApp();

    @FXML
	private StackPane root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if (FxApp.getAS().getLoggedInUser() != null) {
			Velkommen.setText("Velkommen, " + FxApp.getAS().getLoggedInUser().getName());
		}
		
		
		infoText.setText("Velg en venn for å visualisere informasjon:");
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public void handleMemberComboBox(ActionEvent actionEvent) throws IOException, ParseException {
		
		String username = memberComboBox.getSelectionModel().getSelectedItem();
		infoText.setText("Viser " + username + "'s info, velg ny venn: " );
		
		app.requestHealthInformation_ID(app.getIDfromName(username));
		
		
		int meanHR = 0;
		int meanSteps = 0;
		
		if (app.getDates() != null) {
			
			for (int i = 0; i < app.getDates().size(); i++) {
				meanHR += app.getRestingHRs().get(i)/app.getRestingHRs().size();
				meanSteps += app.getSteps().get(i)/app.getSteps().size();
			} 
			
			
		
			pulsSnittTekst.setText(username +"'s snittpuls ligger på:");
			pulsSnittVerdi.setText(meanHR + "");
			stepsSnittTekst.setText(username +"'s snittsteps ligger på:");
			stepsSnittVerdi.setText(meanSteps + "");
		} else {
			pulsSnittTekst.setText("Ingen helsedata å vise");
			pulsSnittVerdi.setText("");
		}
		
		// START PLOTT
		
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
		
		heartRateChart.getData().clear();
		stepsChart.getData().clear();
		
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
	
		
		for (int i = 0; i < app.getDates().size() ; i++) {
			series.getData().add(new XYChart.Data(app.getDates().get(i).substring(0,app.getDates().get(i).length()-6 ),app.getRestingHRs().get(i)));
			series2.getData().add(new XYChart.Data(app.getDates().get(i).substring(0,app.getDates().get(i).length()-6 ),app.getSteps().get(i)));
		
		}

		
		
		
			
		heartRateChart.setCreateSymbols(false);
		heartRateChart.setAnimated(false);
        heartRateChart.getData().add(series);
        stepsChart.setCreateSymbols(false);
        stepsChart.setAnimated(false);
        stepsChart.getData().add(series2);

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

