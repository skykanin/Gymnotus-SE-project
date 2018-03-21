package tdt4140.gr1832.app.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TrainerSettingsAppTest {
	TrainerSettingsApp testapp;
	
	
	@Before
	public void setUp() {
		testapp = new TrainerSettingsApp();
	}
	
	@Test
	public void testURI() {
		Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseURI());
	}
	
	@Test
	public void testCheckNull() {
		Assert.assertEquals("heisann", TrainerSettingsApp.checkNull("heisann"));
		Assert.assertEquals("Ikke spesifisert",TrainerSettingsApp.checkNull("")) ;
		Assert.assertEquals("Ikke spesifisert",TrainerSettingsApp.checkNull(null)) ;
	}
	}
