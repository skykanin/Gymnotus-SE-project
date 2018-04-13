package tdt4140.gr1832.app.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import containers.ShowUserInfoContainer;



public class ApplicationStateTest {
	ApplicationState testAS;
	ShowUserInfoContainer user;
	@Before
	public void setup() {
		testAS = new ApplicationState("test");
		
		user = new ShowUserInfoContainer("test", "test", "test", 11, 0, "test", "test", false, true, true, false);
		testAS.DUMMYsetuser(user);
	}
	
	@Test
	public void testConstructor() {
		Assert.assertEquals("test", testAS.getWindowName());
	}
	
	@Test
	public void testSetWindow() {
		testAS.setWindow("testen");
		Assert.assertEquals("testen", testAS.getWindowName());
	}
	
	@Test
	public void testGetLoggedInUser() {
		Assert.assertEquals(user, testAS.getLoggedInUser());
	}
	
	@Test
	public void testBaseURI() {
		Assert.assertEquals("http://146.185.153.244:8080/api/user/", testAS.getBaseURI() );
	}
}
