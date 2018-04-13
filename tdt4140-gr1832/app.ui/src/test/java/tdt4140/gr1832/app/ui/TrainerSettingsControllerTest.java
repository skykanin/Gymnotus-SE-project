package tdt4140.gr1832.app.ui;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.ShowUserInfoContainer;

public class TrainerSettingsControllerTest extends FxAppTest {

    private final String TilDashboardID = "#TilDashboard";
    private final String TilMedlemmerID = "#TilMedlemmer";
    private final String TilTreningsprogramID = "#TilTreningsprogram";
    private final String TilInnstillingerID = "#TilInnstillinger";
//    
//    @Before
//    public void setuo() {
//    		TrainerSettingsController.ISTEST = true;
//    }
//    
	@Override
	public void start(Stage stage) throws Exception {
		FxApp.InitializeAS("TrainerSettings.fxml");
		ShowUserInfoContainer user = new ShowUserInfoContainer("username", "password", "name", 10, 1, "email", "123", true, true, true, false);
		FxApp.getAS().DUMMYsetuser(user);
        Parent root = FXMLLoader.load(getClass().getResource("TrainerSettings.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

//    @Test
//    public void verifyButtons() {
//        verifyThat(TilDashboardID, hasText("    DASHBOARD"));
//        verifyThat(TilTreningsprogramID, hasText("    TRENINGSPROGRAM"));
//        verifyThat(TilMedlemmerID, hasText("    MEDLEMMER"));
//        verifyThat(TilInnstillingerID, hasText("    INNSTILLINGER"));
//    }
//    
//    @Test
//    public void verifyNavigationDashboard() {
//        assertEquals(FxApp.getAS().getWindowName(),"TrainerSettings.fxml");
//        clickOn(TilDashboardID);
//        assertEquals(FxApp.getAS().getWindowName(),"TrainerDashboard.fxml");
//    }
//    
//    @Test
//    public void verifyNavigationTrainingProgram() {
//        assertEquals(FxApp.getAS().getWindowName(),"TrainerSettings.fxml");
//        clickOn(TilTreningsprogramID);
//        assertEquals(FxApp.getAS().getWindowName(),"TrainerTrainingProgramOverview.fxml");
//    }
//    
//    @Test
//    public void verifyNavigationMembers() {
//        assertEquals(FxApp.getAS().getWindowName(),"TrainerSettings.fxml");
//        clickOn(TilMedlemmerID);
//        assertEquals(FxApp.getAS().getWindowName(),"TrainerMembers.fxml");
//    }
//    
//    @Test
//    public void verifyNavigationSettings() {
//        assertEquals(FxApp.getAS().getWindowName(),"TrainerSettings.fxml");
//        clickOn(TilInnstillingerID);
//        assertEquals(FxApp.getAS().getWindowName(),"TrainerSettings.fxml");
//    }
//    
//    @After
//    public void tearDown() {
//    		TrainerSettingsController.ISTEST = false;
//    }
}
