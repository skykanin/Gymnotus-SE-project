package tdt4140.gr1832.app.ui;

import javafx.scene.control.TextField;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxAppTest extends ApplicationTest {
    private Parent root;

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
    public void testFxApp() {
        verifyThat("#loginButton", hasText("Login"));
    }
}
