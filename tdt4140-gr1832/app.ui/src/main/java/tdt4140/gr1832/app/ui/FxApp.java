package tdt4140.gr1832.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApp extends Application {

	public static ApplicationState AS;
	
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gymnotus");
        stage.show();
        
        AS = new ApplicationState("LoginScreen.fxml");
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}