package tdt4140.gr1832.app.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginScreenController extends FxApp {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;


    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uname = username.getText();
        String pword = password.getText();


        if (uname.equals("") || pword.equals("")) {
            Label errorMessage = (Label) scene.lookup("#errorMessage");
            System.out.println(errorMessage);
            errorMessage.setText("One or both fields are missing");
            System.out.println("Coming here");
        }

        System.out.println("Login button has been clicked");
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        String uname = username.getText();
        String pword = password.getText();

        System.out.println("Login button has been clicked");
    }

}
