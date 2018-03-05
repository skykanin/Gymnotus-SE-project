package tdt4140.gr1832.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApp extends Application {
    private Parent root;
    public Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login Screen");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
