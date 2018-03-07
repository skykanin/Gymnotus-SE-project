package tdt4140.gr1832.app.ui;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isNull;

import org.junit.Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class RegisterScreenControllerTest extends FxAppTest {
	    private final String name = "#setName";
	    private final String setEmailTextField = "#setEmailTextField";
	    private final String setAgeTextField = "#setAgeTextField";
	    private final String setPhoneNumberTextField = "#setPhoneNumberTextField";
	    private final String setPasswordTextField = "#setPasswordTextField";
	    private final String registerButton = "#registerButton";
	    private final String username = "#setUsername";
 
    	
	    @Override
	    public void start(Stage stage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("RegisterScreen.fxml"));
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
    	    
    	 @Test
    	    public void testValidInputFields() {
    	        final KeyCode[] testString = {KeyCode.T, KeyCode.E, KeyCode.S, KeyCode.T};
    	        
    	        
    	        
    	        clickOn(username).type(testString);
    	        clickOn(name).type(testString);
    	        clickOn(setAgeTextField).type(testString);
    	        clickOn(setEmailTextField).type(testString);
    	        clickOn(setPhoneNumberTextField).type(testString);
    	        clickOn(setPasswordTextField).type(testString);
    	        

    	        verifyThat(username, hasText("test"));
    	        verifyThat(name, hasText("test"));
    	        verifyThat(setAgeTextField, hasText("test"));
    	        verifyThat(setEmailTextField, hasText("test"));
    	        verifyThat(setPhoneNumberTextField, hasText("test"));
    	        verifyThat(setPasswordTextField, hasText("test"));
    	     
    	    }
    	 @Test
    	    public void verifyButtons() {
    	        verifyThat(registerButton, hasText("Registrer"));
    	    }

    }


