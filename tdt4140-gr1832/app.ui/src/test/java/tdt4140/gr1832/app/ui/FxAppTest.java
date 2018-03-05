package tdt4140.gr1832.app.ui;

import org.testfx.framework.junit.ApplicationTest;
import org.junit.BeforeClass;
import javafx.stage.Stage;

public abstract class FxAppTest extends ApplicationTest {

    @BeforeClass
    public static void headless() {
    		if (Boolean.valueOf(System.getProperty("gitlab-ci", "false"))) {
    			GitlabCISupport.headless();
    		}
    }

	@Override
    public abstract void start(Stage stage) throws Exception;

}
