package tdt4140.gr1832.app.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TrainerSettings.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("LoginScreen");
        stage.show();
    }
    
	@FXML
	private void TilDashboard(ActionEvent event) throws IOException {
		Parent LoginScreen_parent = FXMLLoader.load(getClass().getResource("TrainerDashboard.fxml"));
		Scene LoginScreen_scene = new Scene(LoginScreen_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(LoginScreen_scene);
		app_stage.show();
	}

	
    public static void main(String[] args) {
        launch(args);
    }
}
