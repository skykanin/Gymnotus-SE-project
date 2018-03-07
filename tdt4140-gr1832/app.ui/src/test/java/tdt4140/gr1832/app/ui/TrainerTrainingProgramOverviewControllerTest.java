package tdt4140.gr1832.app.ui;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.ShowUserInfoContainer;


public class TrainerTrainingProgramOverviewControllerTest extends FxAppTest {

    private final String TilDashboardID = "#TilDashboard";
    private final String TilMedlemmerID = "#TilMedlemmer";
    private final String TilTreningsprogramID = "#TilTreningsprogram";
    private final String TilInnstillingerID = "#TilInnstillinger";
    private final String TilTreningsoversiktID = "#TilTreningsoversikt";
    private final String TilTreningsovelseID = "#TilTreningsovelse";
    private final String TilTreningskommentarID = "#TilTreningskommentar";
    private final String TilTreningspameldteID = "#TilTreningspameldte";

    
	@Override
	public void start(Stage stage) throws Exception {
		FxApp.InitializeAS("TrainerTrainingProgramOverview.fxml");
		ShowUserInfoContainer user = new ShowUserInfoContainer("username", "password", "name", 10, 1, "email", "123");
		FxApp.getAS().DUMMYsetuser(user);
        Parent root = FXMLLoader.load(getClass().getResource("TrainerTrainingProgramOverview.fxml"));
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
        
        verifyThat(TilTreningsovelseID, hasText("OVELSER"));
        verifyThat(TilTreningskommentarID, hasText("KOMMENTAR"));
        verifyThat(TilTreningspameldteID, hasText("PAMELDTE"));
        verifyThat(TilTreningsoversiktID, hasText("OVERSIKT"));
    }
    
    @Test
    public void verifyNavigationDashboard() {
        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
        clickOn(TilDashboardID);
        assertEquals("TrainerDashboard.fxml", FxApp.getAS().getWindowName());
    }
    
    @Test
    public void verifyNavigationTrainingProgram() {
        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
        clickOn(TilTreningsprogramID);
        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
    }
    
    @Test
    public void verifyNavigationMembers() {
        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
        clickOn(TilMedlemmerID);
        assertEquals("TrainerMembers.fxml", FxApp.getAS().getWindowName());
    }
    
//    @Test
//    public void verifyNavigationSettings() {
//        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
//        clickOn(TilInnstillingerID);
//        assertEquals("TrainerSettings.fxml", FxApp.getAS().getWindowName());
//    }

    
    @Test
    public void verifyNavigationOverview() {
        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
        clickOn(TilTreningsoversiktID);
        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
    }
    
    @Test
    public void verifyNavigationExercises() {
        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
        clickOn(TilTreningsovelseID);
        assertEquals("TrainerTrainingProgramExercises.fxml", FxApp.getAS().getWindowName());
    }
    
    @Test
    public void verifyNavigationComments() {
        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
        clickOn(TilTreningskommentarID);
        assertEquals("TrainerTrainingProgramComments.fxml", FxApp.getAS().getWindowName());
    }
    
    @Test
    public void verifyNavigationAttendants() {
        assertEquals("TrainerTrainingProgramOverview.fxml", FxApp.getAS().getWindowName());
        clickOn(TilTreningspameldteID);
        assertEquals("TrainerTrainingProgramAttendants.fxml", FxApp.getAS().getWindowName());
    }
    
}
