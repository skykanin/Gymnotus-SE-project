package tdt4140.gr1832.app.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

public class LoginScreenTest extends ApplicationTest {
    private Parent root;
    private final String usernameFieldID = "#username";
    private final String passwordFieldID = "#password";
    private final String loginButtonID = "#loginButton";
    private final String registerButtonID = "#registerButton";

    @BeforeClass
    public static void headless() {
        if (Boolean.valueOf(System.getProperty("gitlab-ci", "false"))) {
            GitlabCISupport.headless();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testTextInInputFields() {
        final KeyCode[] testString = {KeyCode.T, KeyCode.E, KeyCode.S, KeyCode.T};

        clickOn(usernameFieldID).type(testString);
        clickOn(passwordFieldID).type(testString);

        verifyThat(usernameFieldID, hasText("test"));
        verifyThat(passwordFieldID, hasText("test"));
    }

    @Test
    public void verifyButtons() {
        verifyThat(loginButtonID, hasText("Login"));
        verifyThat(registerButtonID, hasText("Sign Up"));
    }
}
