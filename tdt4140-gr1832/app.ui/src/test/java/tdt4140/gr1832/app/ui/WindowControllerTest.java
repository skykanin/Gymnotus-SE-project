package tdt4140.gr1832.app.ui;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

import org.junit.Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class WindowControllerTest extends FxAppTest {

	private final String usernameFieldID = "#username";
    private final String passwordFieldID = "#password";
    private final String loginButtonID = "#loginButton";
    private final String registerButtonID = "#registerButton";
    private final String errorMessageID = "#errorMessage";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testValidInputFields() {
        final KeyCode[] testStringNavn = {KeyCode.T, KeyCode.E, KeyCode.S, KeyCode.T, KeyCode.B, KeyCode.U, KeyCode.K, KeyCode.E, KeyCode.R};
        final KeyCode[] testStringPassord = {KeyCode.CAPS, KeyCode.L, KeyCode.U, KeyCode.L, KeyCode.CAPS};
        
        clickOn(usernameFieldID).type(testStringNavn);
        clickOn(passwordFieldID).type(testStringPassord);

        verifyThat(usernameFieldID, hasText("testbruker"));
        verifyThat(passwordFieldID, hasText("LUL"));
        verifyThat(errorMessageID, hasText(""));
        clickOn(loginButtonID);

    }
}
