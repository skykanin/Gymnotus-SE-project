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
    private Label errorMessage;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uname = username.getText();
        String pword = password.getText();

        if (uname.equals("") || pword.equals("")) {
            errorMessage.setText("One or both fields are missing");
        } else {
            errorMessage.setText("");
        }
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {

    }

}
