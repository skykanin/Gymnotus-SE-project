package tdt4140.gr1832.app.ui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrainerMemberInfoTest extends ApplicationTest{


	private MemberInfoController testController;
	
	@Before
	public void setupTestController() {
		testController.ageField.setText("33");
		testController.dateField.setText("01.01.2018");
		testController.emailField.setText("bobern.bob@gmail.com");
		testController.genderField.setText("male");
		testController.heightField.setText("200");
		testController.nameField.setText("Bobbern bob");
		testController.restingHRField.setText("80");
		testController.stepsField.setText("1111");
		testController.tlfField.setText("80880800");
		testController.usernameField.setText("wannaBeBob");
		testController.weightField.setText("100");
		
	}
	
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainerMemberInfo.fxml"));
		Parent root = loader.load();
        this.testController = loader.getController();
        Scene scene = new Scene(root);
        
        stage.setTitle("JavaFX MemberInfoTest");
        stage.setScene(scene);
        stage.show();
       
        
	}
	
	
	@Test
	public void testFieldExist() {
		
		Assert.assertTrue(lookup("#dateField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#heightField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#weightField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#stepsField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#restingHRField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#nameField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#usernameField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#emailField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#tlfField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#genderField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#datePickerField").query() instanceof JFXDatePicker);
		Assert.assertTrue(lookup("#ageField").query() instanceof JFXTextField);
	}
	
	
	

	@Test
	public void testControllerConnection() {
		
		try {
			//Hente inn objektene fra FXML
		JFXTextField testAgeField = (JFXTextField) lookup("#ageField").query();
		JFXTextField testDateField = (JFXTextField) lookup("#dateField").query();
		JFXTextField testHeightField = (JFXTextField) lookup("#heightField").query();
		JFXTextField testWeightField = (JFXTextField) lookup("#weightField").query();
		JFXTextField testStepsField = (JFXTextField) lookup("#stepsField").query();
		JFXTextField testRestingHRField = (JFXTextField) lookup("#restingHRField").query();
		JFXTextField testNameField = (JFXTextField) lookup("#nameField").query();
		JFXTextField testUsernameField = (JFXTextField) lookup("#usernameField").query();
		JFXTextField testEmailField = (JFXTextField) lookup("#emailField").query();
		JFXTextField testTlfField = (JFXTextField) lookup("#tlfField").query();
		JFXTextField testGenderField = (JFXTextField) lookup("#genderField").query();
	
			//Teste at objektene viser riktig String
		Assert.assertEquals("33", testAgeField.getText());
		Assert.assertEquals("01.01.2018", testDateField.getText());
		Assert.assertEquals("bobern.bob@gmail.com", testEmailField.getText());
		Assert.assertEquals("male", testGenderField.getText());
		Assert.assertEquals("200", testHeightField.getText());
		Assert.assertEquals("Bobbern bob", testNameField.getText());
		Assert.assertEquals("80", testRestingHRField.getText());
		Assert.assertEquals("1111", testStepsField.getText());
		Assert.assertEquals("80880800", testTlfField.getText());
		Assert.assertEquals("wannaBeBob", testUsernameField.getText());
		Assert.assertEquals("100", testWeightField.getText());
		
		}
		catch(ClassCastException feilmelding) {
			System.out.println(feilmelding);
			Assert.assertFalse(true);
			
		}
		
		
	}
	
	
}
