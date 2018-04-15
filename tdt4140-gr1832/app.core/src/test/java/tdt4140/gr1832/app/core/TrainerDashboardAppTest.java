package tdt4140.gr1832.app.core;

import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import containers.ShowAllUsersContainer;
import containers.ShowHealthInfoContainer;
import containers.ShowUserInfoContainer;

public class TrainerDashboardAppTest {
	ShowUserInfoContainer showUserInfoContainer;
	ShowUserInfoContainer showUserInfoContainer1;
	ShowUserInfoContainer showUserInfoContainer2;
	ShowHealthInfoContainer showHealthInfoContainer;
	ShowAllUsersContainer showAllUserContainer; 
	TrainerDashboardApp testapp = new TrainerDashboardApp();
	
	@Before
	public void setup() {
		testapp.setTest(true);
		showAllUserContainer = new ShowAllUsersContainer(); 
		showUserInfoContainer = new ShowUserInfoContainer("brukernavn", "passord", "navn1",11, 0, "email", "telefon", false, true, true, false);
		showUserInfoContainer1 = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 1, "email", "telefon", false, true, true, false);
		showUserInfoContainer2 = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 2, "email", "telefon", false, true, true, false);
		showHealthInfoContainer = new ShowHealthInfoContainer(123, 007, "Mar, 2018", 85, 100, 60, 181, 80, false, true, true);
		testapp.setContainerUser(showUserInfoContainer);
	}
	
	@Test
	public void testRequestUserInformation_ID() {
		testapp.requestUserInformation_ID("1");
		ShowUserInfoContainer user = testapp.getContainerUser();
		Assert.assertEquals(true, user.getIsAnonymous());
		Assert.assertEquals("Brukeren er anonym", user.getUsername());
		Assert.assertEquals("1", user.getUserID());
	}
	
	@Test
	public void testHealthInformation_ID() {
		testapp.requestHealthInformation_ID("1");
		Assert.assertEquals(1, testapp.getHealthContainers().size());
	}
	
	@Test
	public void testRequestAllUserID() {
		testapp.requestAllUserID();
		ShowUserInfoContainer user = testapp.getContainerUser();
		Assert.assertEquals("1", user.getUserID());
		Assert.assertEquals("Brukeren er anonym", user.getUsername());
	}
	
	
	@Test
	public void testGetters() {
		showAllUserContainer.addUserInfo(showUserInfoContainer);
		testapp.setContainerAllUsers(showAllUserContainer);
		showUserInfoContainer.setUserId("007");
		Assert.assertEquals(Arrays.asList("navn1"), testapp.getNames());
		testapp.setContainerUser(showUserInfoContainer1);
		testapp.setContainerUser(showUserInfoContainer2);
		Assert.assertEquals("1", testapp.getIDfromName("feil"));
		Assert.assertEquals("007", testapp.getIDfromName("navn1"));
		Assert.assertEquals(null,testapp.getHeights());
		Assert.assertEquals(null,testapp.getWeights());
		Assert.assertEquals(null,testapp.getDates());
		Assert.assertEquals(null,testapp.getSteps());
		Assert.assertEquals(null,testapp.getRestingHRs());
		testapp.getHealthContainers().add(showHealthInfoContainer);
		Assert.assertEquals(Arrays.asList(181),testapp.getHeights());
		Assert.assertEquals(Arrays.asList(80),testapp.getWeights());
		Assert.assertEquals(Arrays.asList("Mar, 2018"),testapp.getDates());
		Assert.assertEquals(Arrays.asList(100),testapp.getSteps());
		Assert.assertEquals(Arrays.asList(60),testapp.getRestingHRs());
		testapp.setContainerUser(showUserInfoContainer1);
		Assert.assertEquals(showUserInfoContainer1, testapp.getContainerUser());
		
		
	}
	
	@Test
	public void testBaseUrl() {
		Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseURI());
	}
}
