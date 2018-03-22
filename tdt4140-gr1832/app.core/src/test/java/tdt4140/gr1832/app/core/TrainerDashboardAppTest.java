package tdt4140.gr1832.app.core;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrainerDashboardAppTest {
	ShowUserInfoContainer showUserInfoContainer;
	ShowUserInfoContainer showUserInfoContainer1;
	ShowUserInfoContainer showUserInfoContainer2;
	ShowHealthInfoContainer showHealthInfoContainer;
	ShowAllUsersContainer showAllUserContainer; 
	TrainerDashboardApp testapp = new TrainerDashboardApp();
	
	@Before
	public void setup() {
		showAllUserContainer = new ShowAllUsersContainer(); 
		showUserInfoContainer = new ShowUserInfoContainer("brukernavn", "passord", "navn1",11, 0, "email", "telefon", false, true, true, false);
		showUserInfoContainer1 = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 1, "email", "telefon", false, true, true, false);
		showUserInfoContainer2 = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 2, "email", "telefon", false, true, true, false);
		showHealthInfoContainer = new ShowHealthInfoContainer(123, 007, "Mar, 2018", 85, 100, 60, 181, 80, false, true, true);
		testapp.setContainerUser(showUserInfoContainer);
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
		
	

	}
	
	@Test
	public void testBaseUrl() {
		Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseURI());
	}
}
