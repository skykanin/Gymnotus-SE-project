package tdt4140.gr1832.app.ui;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import com.jfoenix.controls.JFXTextField;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TrainerSettingsTest extends ApplicationTest {
	
	private TrainerSettingsController testController;
	
	@Before
	public void setupTestController() {
		testController.ageField.setText("33");
		testController.emailField.setText("bobern.bob@gmail.com");
		testController.nameField.setText("Bobbern bob");
		testController.tlfField.setText("80880800");
		testController.usernameField.setText("wannaBeBob");
//		testController.genderField.setText("Mann");

		
	}
	
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainerSettings.fxml"));
		Parent root = loader.load();
        this.testController = loader.getController();
        Scene scene = new Scene(root);
        
        stage.setTitle("JavaFX TrainerSettingsTest");
        stage.setScene(scene);
        stage.show();
       
        
	}
	
	
	@Test
	public void testFieldExist() {
		//Tester at feltene so skal være der finnes
		
		Assert.assertTrue(lookup("#nameField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#usernameField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#emailField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#tlfField").query() instanceof JFXTextField);
//		Assert.assertTrue(lookup("#genderField").query() instanceof JFXTextField);
		Assert.assertTrue(lookup("#ageField").query() instanceof JFXTextField);
	}
	
	
	

	@Test
	public void testControllerConnection() {
		
		try {
			//Hente inn objektene fra FXML
		JFXTextField testAgeField = (JFXTextField) lookup("#ageField").query();
		JFXTextField testNameField = (JFXTextField) lookup("#nameField").query();
		JFXTextField testUsernameField = (JFXTextField) lookup("#usernameField").query();
		JFXTextField testEmailField = (JFXTextField) lookup("#emailField").query();
		JFXTextField testTlfField = (JFXTextField) lookup("#tlfField").query();
//		JFXTextField testGenderField = (JFXTextField) lookup("#genderField").query();
	
			//Teste at objektene viser riktig String
		Assert.assertEquals("33", testAgeField.getText());
		Assert.assertEquals("bobern.bob@gmail.com", testEmailField.getText());
		Assert.assertEquals("Bobbern bob", testNameField.getText());
		Assert.assertEquals("80880800", testTlfField.getText());
		Assert.assertEquals("wannaBeBob", testUsernameField.getText());
//		Assert.assertEquals("Mann", genderField.getText());
		
		}
		catch(ClassCastException feilmelding) {
			System.out.println(feilmelding);
			Assert.assertFalse(true);
			
		}
		
		
	}
	
}