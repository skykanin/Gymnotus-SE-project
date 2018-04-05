package tdt4140.gr1832.app.ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TrainerTrainingProgramExercisesController extends WindowController {
    @FXML
    private StackPane root;

//	@FXML JFXComboBox<String> memberComboBox;
//	
//	@FXML Label exInfoText;
//	@FXML Label stepsSnittTekst;
//	@FXML Label stepsSnittVerdi;
//	@FXML Label heartRateChartTitle;
//	@FXML Label stepsChartTitle;
//	    
//	@FXML LineChart<String,Number> exChartOne;
//  @FXML CategoryAxis xAxisOne;
//  @FXML NumberAxis yAxisOne;
//    
//	@FXML LineChart<String,Number> exChartTwo;
//	@FXML CategoryAxis xAxisTwo;
//  @FXML NumberAxis yAxisTwo;
//    
//	@FXML LineChart<String,Number> exChartThree;
//	@FXML CategoryAxis xAxisThree;
//  @FXML NumberAxis yAxisThree;
//    
//	@FXML LineChart<String,Number> exChartFour;
//	@FXML CategoryAxis xAxisFour;
//  @FXML NumberAxis yAxisFour;
//     
//  TrainerTrainingProgramExercisesApp app = new TrainerTrainingProgramExercisesApp();
    
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


//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		
//		exChartOne.setLegendVisible(false);
//		exChartTwo.setLegendVisible(false);
//		exChartThree.setLegendVisible(false);
//		exChartFour.setLegendVisible(false);
//		
//		exChartOne.setOpacity(0);
//		exChartTwo.setOpacity(0);
//		exChartThree.setOpacity(0);
//		exChartFour.setOpacity(0);
//		
//		
//		exInfoText.setText("Velg en venn for å visualisere informasjon:");
//		
//		app.requestAllUserID();
//		ObservableList<String> names = FXCollections.observableArrayList();
//		for (String name : app.getNames()) {
//			names.add(name);
//		}
//		
//		memberComboBox.setItems(names);
//		
//	}
//    
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//    

}
