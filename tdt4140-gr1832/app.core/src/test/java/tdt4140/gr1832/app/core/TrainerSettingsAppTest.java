package tdt4140.gr1832.app.core;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class TrainerSettingsAppTest {
	TrainerSettingsApp testApp = new TrainerSettingsApp();

	@Before
	public void setUp() {
		testApp.setTest(true);
		
	}
	
	@Test
	public void testChangeUser() {
		boolean worked = testApp.changeUser("test", "test", "test@test.com", "12345678", "18", "1");
		Assert.assertFalse(worked);
		Assert.assertEquals(400, testApp.getResponse().getStatus());
	}
	
	@Test
	public void testCheckNull() {
		String test = "", test1 = "test", test2=null;
		Assert.assertEquals("Ikke spesifisert", testApp.checkNull(test));
		Assert.assertEquals(test1, testApp.checkNull(test1));
		Assert.assertEquals("Ikke spesifisert", testApp.checkNull(test2));
	}
		
}
