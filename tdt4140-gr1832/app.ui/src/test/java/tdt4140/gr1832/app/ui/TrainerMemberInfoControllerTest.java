package tdt4140.gr1832.app.ui;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import containers.ShowUserInfoContainer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrainerMemberInfoControllerTest extends FxAppTest {

//    private final String TilDashboardID = "#TilDashboard";
//    private final String TilMedlemmerID = "#TilMedlemmer";
//    private final String TilTreningsprogramID = "#TilTreningsprogram";
//    private final String TilInnstillingerID = "#TilInnstillinger";
//    
//    @Before
//    public void setUp() {
//    		FxApp.TEST = true;
//    }
//    
//    @After
//    public void tearDown() {
//    		FxApp.TEST = false;
//    } 
	@Override
	public void start(Stage stage) throws Exception {
		FxApp.TEST = true;
		FxApp.InitializeAS("TrainerMemberInfo.fxml");
		ShowUserInfoContainer user = new ShowUserInfoContainer("username", "password", "name", 10, 1, "email", "123", true, true, true, false);
		FxApp.getAS().DUMMYsetuser(user);
        Parent root = FXMLLoader.load(getClass().getResource("TrainerMemberInfo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
//
//    @Test
//    public void verifyButtons() {
//        verifyThat(TilDashboardID, hasText("    DASHBOARD"));
//        verifyThat(TilTreningsprogramID, hasText("    TRENINGSPROGRAM"));
//        verifyThat(TilMedlemmerID, hasText("    MEDLEMMER"));
//        verifyThat(TilInnstillingerID, hasText("    INNSTILLINGER"));
//    }
//    
//  @Test
//  public void verifyNavigationDashboard() {
//      assertEquals(FxApp.getAS().getWindowName(),"TrainerMemberInfo.fxml");
//      clickOn(TilDashboardID);
//      assertEquals(FxApp.getAS().getWindowName(),"TrainerDashboard.fxml");
//  }
//  
//  @Test
//  public void verifyNavigationTrainingProgram() {
//      assertEquals(FxApp.getAS().getWindowName(),"TrainerMemberInfo.fxml");
//      clickOn(TilTreningsprogramID);
//      assertEquals(FxApp.getAS().getWindowName(),"TrainerTrainingProgramOverview.fxml");
//  }
//  
//  @Test
//  public void verifyNavigationMembers() {
//      assertEquals(FxApp.getAS().getWindowName(),"TrainerMemberInfo.fxml");
//      clickOn(TilMedlemmerID);
//      assertEquals(FxApp.getAS().getWindowName(),"TrainerMembers.fxml");
//  }
//  
////  @Test
////  public void verifyNavigationSettings() {
////      assertEquals(FxApp.getAS().getWindowName(),"TrainerMemberInfo.fxml");
////      clickOn(TilInnstillingerID);
////      assertEquals(FxApp.getAS().getWindowName(),"TrainerSettings.fxml");
////  }
//
//  @Test
//  public void testFieldExist() {
//      //Tester at feltene so skal vere der finnes
//      Assert.assertTrue(lookup("#datePickerField").query() instanceof JFXDatePicker);
//      Assert.assertTrue(lookup("#dateField").query() instanceof JFXTextField);
//      Assert.assertTrue(lookup("#heightField").query() instanceof JFXTextField);
//      Assert.assertTrue(lookup("#weightField").query() instanceof JFXTextField);
//      Assert.assertTrue(lookup("#stepsField").query() instanceof JFXTextField);
//      Assert.assertTrue(lookup("#restingHRField").query() instanceof JFXTextField);
//      Assert.assertTrue(lookup("#nameField").query() instanceof JFXTextField);
//      Assert.assertTrue(lookup("#usernameField").query() instanceof JFXTextField);
//      Assert.assertTrue(lookup("#emailField").query() instanceof JFXTextField);
//      Assert.assertTrue(lookup("#tlfField").query() instanceof JFXTextField);
//      Assert.assertTrue(lookup("#genderField").query() instanceof JFXTextField);
//      Assert.assertTrue(lookup("#datePickerField").query() instanceof JFXDatePicker);
//      Assert.assertTrue(lookup("#ageField").query() instanceof JFXTextField);
//  }
}
