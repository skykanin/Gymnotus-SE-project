package tdt4140.gr1832.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.ApplicationState;

public class FxApp extends Application {
    private Parent root;
    protected Scene scene;
    protected static ApplicationState AS;

    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        scene = new Scene(root);
        AS = new ApplicationState("LoginScreen.fxml");
        stage.setScene(scene);
        stage.setTitle("GYMNOTUS");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static ApplicationState getAS() {
    	return AS;
    }
}
