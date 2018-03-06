package tdt4140.gr1832.app.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.ws.rs.NotFoundException;

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

        boolean userExists = false, passwordVerified = false;

        // Check if username exists in database
        try {
            AS.verifyUsername(uname);
            userExists = true;
        } catch (NotFoundException e) {
            errorMessage.setText("Username does not exist");
        }

        // Check if password is correct for user
        if(!(passwordVerified = AS.verifyPassword(uname,pword)) && userExists) {
            errorMessage.setText("Password is not correct");
        }

        //If both username and passwords matches we can login
        if(userExists && passwordVerified) {
            AS.setCurrentUser(uname);
        }
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {

    }

}
