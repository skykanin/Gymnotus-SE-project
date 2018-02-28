package tdt4140.gr1832.app.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginScreenController {
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uname = username.getText();
        String pword = password.getText();

        System.out.println("Login button has been clicked");
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        String uname = username.getText();
        String pword = password.getText();

        System.out.println("Login button has been clicked");
    }

}
