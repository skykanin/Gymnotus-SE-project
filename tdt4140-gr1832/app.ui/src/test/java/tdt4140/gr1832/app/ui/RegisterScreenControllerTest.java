package tdt4140.gr1832.app.ui;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import containers.ShowUserInfoContainer;
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
	    private final String TilLoginScreenID = "#tilbake";

	    @Before
	    public void setUp() {
	    		FxApp.TEST = true;
	    }
	    
	    @After
	    public void tearDown() {
	    		FxApp.TEST = false;
	    }
 
    	
	    @Override
		public void start(Stage stage) throws Exception {
	    		FxApp.TEST = true;
			FxApp.InitializeAS("RegisterScreen.fxml");
			ShowUserInfoContainer user = new ShowUserInfoContainer("username", "password", "name", 10, 1, "email", "123", true, true, true, false);
			FxApp.getAS().DUMMYsetuser(user);
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

	    @Test
	    public void verifyNavigationLoginScreen() {
	        assertEquals("RegisterScreen.fxml", FxApp.getAS().getWindowName());
	        clickOn(TilLoginScreenID);
	        assertEquals("LoginScreen.fxml", FxApp.getAS().getWindowName());
	    }
    }


