package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeleteUserController extends WindowController {

    private final String baseURI = "http://146.185.153.244:8080/api/user/";
	
	@FXML
    private JFXPasswordField Passord;
	
	@FXML
	private Label Respons;
	
    @FXML
	StackPane root;

	@FXML
	public void initialize() {
		root.setPickOnBounds(false);
	}

	@FXML
	public void SlettProfil(ActionEvent event) throws InterruptedException, IOException {
        String uname = AS.getLoggedInUser().getUsername();
        String pword = Passord.getText();
        
        if(AS.verifyPassword(uname,pword)) {
        	
    		Client client = ClientBuilder.newClient();
    		WebTarget webTarget = client.target(baseURI + "delete_user");
        	
    		MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		    formData.add("username", uname);
		    formData.add("password", pword);
		    Response response = webTarget.request().post(Entity.form(formData));
		    
		    if (response.toString().contains("status=200, reason=OK}}")) {
		    	NavigerTilSide("LoginScreen.fxml", event);
				FxApp.getAS().DUMMYsetuser(null);
		    }
		    else {
		    	Respons.setText("Brukeren din ble IKKE slettet");
		    }	
        }
        else {
            Respons.setText("Ugyldig passord");
        }
	}
	
	@FXML
	public void Avbryt(ActionEvent event) throws IOException {
		NavigerTilSide("TrainerSettings.fxml", event);
	}	
}
