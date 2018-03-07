package tdt4140.gr1832.app.ui;

import org.junit.Ignore;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Ignore
public class TrainerTrainingProgramOverviewControllerTest extends FxAppTest {

    private final String TilDashboardID = "#TilDashboard";
    private final String TilMedlemmerID = "#TilMedlemmer";
    private final String TilTreningsprogramID = "#TilTreningsprogram";
    private final String TilInnstillingerID = "#TilInnstillinger";
    private final String TilTreningsoversiktID = "#Tiloversikt";
    private final String TilTreningsovelseID = "#TilTreningsovelse";
    private final String TilTreningskommentarID = "#TilTreningskommentar";
    private final String TilTreningspameldteID = "#TilTreningspameldte";
    private Stage stage;
    private Scene originscene;
    
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("TrainerTrainingProgramOverview.fxml"));
        Scene scene = new Scene(root);
        originscene = scene;
        stage.setScene(scene);
        stage.show();		
	}

    @Test
    public void verifyButtons() {
        verifyThat(TilDashboardID, hasText("    DASHBOARD"));
        verifyThat(TilTreningsprogramID, hasText("    TRENINGSPROGRAM"));
        verifyThat(TilMedlemmerID, hasText("    MEDLEMMER"));
        verifyThat(TilInnstillingerID, hasText("    INNSTILLINGER"));
        
        verifyThat(TilTreningsoversiktID, hasText("OVERSIKT"));
        verifyThat(TilTreningsoversiktID, hasText("ØVELSER"));
        verifyThat(TilTreningsoversiktID, hasText("KOMMENTAR"));
        verifyThat(TilTreningsoversiktID, hasText("PÅMELDTE"));
    }
}
