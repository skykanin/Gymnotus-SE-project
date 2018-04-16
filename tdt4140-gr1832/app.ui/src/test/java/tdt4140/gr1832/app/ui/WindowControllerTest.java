package tdt4140.gr1832.app.ui;



import org.junit.Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static org.testfx.api.FxAssert.verifyThat;
import static org.hamcrest.CoreMatchers.is;
import javafx.stage.Stage;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;

public class WindowControllerTest extends FxAppTest {

    private Parent root;
    private Scene scene;
    private ShowUserInfoContainer mockUser;

    @Override
    public void start(Stage stage) throws Exception {
		FxApp.InitializeAS("LoginScreen.fxml");
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        this.mockLogin();
    }

    public void mockLogin() {
        final String username = "mockbruker";
        final String password = "LUL";

        setMockUser(username, password);
        FxApp.getAS().setWindow("TrainerDashboard.fxml");
    }

    public void setMockUser(String username, String password) {
        mockUser = new ShowUserInfoContainer(username, password, "name", 10, 1, "mailto@ntnu.no", "123213", true, true, true, false);
        FxApp.getAS().DUMMYsetuser(mockUser);
    }

    @Test
    public void testMockLogin() {
        String windowName = FxApp.getAS().getWindowName();
        ShowUserInfoContainer userFromAS = FxApp.getAS().getLoggedInUser();

        verifyThat(windowName, is("TrainerDashboard.fxml"));
        verifyThat(userFromAS, is(mockUser));
    }
}
