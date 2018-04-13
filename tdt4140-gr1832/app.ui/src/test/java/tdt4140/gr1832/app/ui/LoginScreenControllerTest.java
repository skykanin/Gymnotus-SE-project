package tdt4140.gr1832.app.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import org.junit.Test;

import containers.ShowUserInfoContainer;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

public class LoginScreenControllerTest extends FxAppTest {
    private final String usernameFieldID = "#username";
    private final String passwordFieldID = "#password";
    private final String loginButtonID = "#loginButton";
    private final String registerButtonID = "#registerButton";
    private final String errorMessageID = "#errorMessage";

    @Override
    public void start(Stage stage) throws Exception {
		FxApp.InitializeAS("LoginScreen.fxml");
    	ShowUserInfoContainer user = new ShowUserInfoContainer("username", "password", "name", 10, 1, "email", "123", true, true, true, false);
    	FxApp.getAS().DUMMYsetuser(user);
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testValidInputFields() {
        final KeyCode[] testString = {KeyCode.T, KeyCode.E, KeyCode.S, KeyCode.T};

        clickOn(usernameFieldID).type(testString);
        clickOn(passwordFieldID).type(testString);

        verifyThat(usernameFieldID, hasText("test"));
        verifyThat(passwordFieldID, hasText("test"));
        verifyThat(errorMessageID, hasText(""));
    }

    @Test
    public void testInvalidInputFields() {
        clickOn(loginButtonID);
        verifyThat(errorMessageID, hasText("Ett eller begge feltene mangler"));
    }

    @Test
    public void verifyButtons() {
        verifyThat(loginButtonID, hasText("Log inn"));
        verifyThat(registerButtonID, hasText("Registrer"));
    }
    
  /*
  This test is dependent apon our database being online

  @Test
  public void testLogin() {
      final KeyCode[] testStringNavn = {KeyCode.T, KeyCode.E, KeyCode.S, KeyCode.T, KeyCode.B, KeyCode.R, KeyCode.U, KeyCode.K, KeyCode.E, KeyCode.R};
      final KeyCode[] testStringPassord = {KeyCode.CAPS, KeyCode.L, KeyCode.U, KeyCode.L, KeyCode.CAPS};
      
      clickOn(usernameFieldID).type(testStringNavn);
      clickOn(passwordFieldID).type(testStringPassord);
      
      assertEquals(FxApp.getAS().getWindowName(),"LoginScreen.fxml");
      
      clickOn(loginButtonID);

      assertEquals(FxApp.getAS().getWindowName(),"TrainerDashboard.fxml");
  }*/
  
  @Test
  public void testNavigateToRegister() {
      assertEquals(FxApp.getAS().getWindowName(),"LoginScreen.fxml");
      
      clickOn(registerButtonID);

      assertEquals(FxApp.getAS().getWindowName(),"RegisterScreen.fxml");
  }
}
