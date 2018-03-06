package tdt4140.gr1832.app.ui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrainerDashboardControllerTest extends FxAppTest {

	private TrainerDashboardController testController;
	
	@Before
	public void setupTestController() {

		
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


}
