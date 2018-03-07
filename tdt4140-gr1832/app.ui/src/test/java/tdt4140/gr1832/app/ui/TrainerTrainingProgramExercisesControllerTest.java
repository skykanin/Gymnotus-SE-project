package tdt4140.gr1832.app.ui;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

import org.junit.Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrainerTrainingProgramExercisesControllerTest extends FxAppTest {

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
		FxApp.InitializeAS("TrainerTrainingProgramExercises.fxml");
        Parent root = FXMLLoader.load(getClass().getResource("TrainerTrainingProgramExercises.fxml"));
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
        
        verifyThat(TilTreningsovelseID, hasText("ØVELSER"));
        verifyThat(TilTreningskommentarID, hasText("KOMMENTAR"));
        verifyThat(TilTreningspameldteID, hasText("PÅMELDTE"));
        verifyThat(TilTreningsoversiktID, hasText("OVERSIKT"));
    }
}
