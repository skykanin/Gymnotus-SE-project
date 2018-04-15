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
		testAS.setTest(true);
		
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
	
	@Test
	public void testSetCurrentUser() {
		testAS.setCurrentUser("testbruker");
		ShowUserInfoContainer user = testAS.getLoggedInUser();
		Assert.assertEquals("1", user.getUserID());
		Assert.assertEquals(23,user.getAge());
		Assert.assertEquals("Henrik Giske Fosse", user.getName());
	}
	
	@Test
	public void testIncrement() {
		testAS.increaseProgramCounter();
		Assert.assertEquals(1, (int)testAS.getProgramCounter());
		testAS.decreaseProgramCounter();
		Assert.assertEquals(0, (int)testAS.getProgramCounter());
		testAS.setProgramCounter(100);
		Assert.assertEquals(100, testAS.getProgramCounter());
	}
	
	@Test
	public void testVerifyUsername() {
		testAS.verifyUsername("test");
	}
	
	@Test
	public void testVerifyPassword() {
		boolean b = testAS.verifyPassword("test", "test");
		Assert.assertTrue(b);	
	}
	
}
