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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;

public class TrainerMemberInfoControllerTest extends FxAppTest {

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
}
