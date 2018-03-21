package tdt4140.gr1832.app.ui;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.ShowUserInfoContainer;

public class TrainerDashboardControllerTest extends FxAppTest {

    private final String TilDashboardID = "#TilDashboard";
    private final String TilMedlemmerID = "#TilMedlemmer";
    private final String TilTreningsprogramID = "#TilTreningsprogram";
    private final String TilInnstillingerID = "#TilInnstillinger";
    
	@Override
	public void start(Stage stage) throws Exception {
		FxApp.InitializeAS("TrainerDashboard.fxml");
		ShowUserInfoContainer user = new ShowUserInfoContainer("username", "password", "name", 10, 1, "email", "123", true, true, true, false);
		FxApp.getAS().DUMMYsetuser(user);
        Parent root = FXMLLoader.load(getClass().getResource("TrainerDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

    @Test
    public void verifyButtons() {
        verifyThat(TilDashboardID, hasText("    DASHBOARD"));
        verifyThat(TilTreningsprogramID, hasText("    TRENINGSPROGRAM"));
        verifyThat(TilMedlemmerID, hasText("    MEDLEMMER"));
        verifyThat(TilInnstillingerID, hasText("    INNSTILLINGER"));
    }
    
    @Test
    public void verifyNavigationDashboard() {
        assertEquals("TrainerDashboard.fxml", FxApp.getAS().getWindowName());
        clickOn(TilDashboardID);
        assertEquals("TrainerDashboard.fxml", FxApp.getAS().getWindowName());
    }
    
    @Test
    public void verifyNavigationTrainingProgram() {
        assertEquals("TrainerDashboard.fxml", FxApp.getAS().getWindowName());
        clickOn(TilTreningsprogramID);
        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
    }
    
    @Test
    public void verifyNavigationMembers() {
        assertEquals("TrainerDashboard.fxml", FxApp.getAS().getWindowName());
        clickOn(TilMedlemmerID);
        assertEquals("TrainerMembers.fxml", FxApp.getAS().getWindowName());
    }
    
//    @Test
//    public void verifyNavigationSettings() {
//        assertEquals("TrainerDashboard.fxml", FxApp.getAS().getWindowName());
//        clickOn(TilInnstillingerID);
//        assertEquals("TrainerSettings.fxml", FxApp.getAS().getWindowName());
//    }
}
