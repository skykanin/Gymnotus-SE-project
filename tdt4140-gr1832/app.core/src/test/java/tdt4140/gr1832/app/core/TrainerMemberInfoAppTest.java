package tdt4140.gr1832.app.core;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import junit.framework.TestCase;

public class TrainerMemberInfoAppTest extends TestCase {
	//Serverrequests are testted elsewere
	ShowUserInfoContainer showUserInfoContainer;
	ShowHealthInfoContainer showHealthInfoContainer;
	ShowAllUsersContainer showAllUserContainer; 
	
	TrainerMemberInfoApp testapp = new TrainerMemberInfoApp();
	
	
	
	
	@Before
	public void setUp() {
		showAllUserContainer = new ShowAllUsersContainer(); 
		showUserInfoContainer = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 0, "email", "telefon");
		showHealthInfoContainer = new ShowHealthInfoContainer(123, 007, "Mar  2018", 85, 100, 60, 181, 80);
		testapp.addContainerHealth(showHealthInfoContainer);
		testapp.setContianerUser(showUserInfoContainer);
	}

	public void testShowAllUserInfoContainerAdd() {
		assertEquals(Arrays.asList(), showAllUserContainer.getUsers());
		showAllUserContainer.addUserInfo(showUserInfoContainer);
		assertEquals(Arrays.asList(showUserInfoContainer), showAllUserContainer.getUsers());
	}
	
	@Test
	public void testTrainerMemberInfoGetters() {
		showUserInfoContainer.setUserId("007");
		testapp.setAllUsersContainer(showAllUserContainer);
		showAllUserContainer.addUserInfo(showUserInfoContainer);
		Assert.assertEquals("brukernavn", testapp.getUsername());
		Assert.assertEquals("navn", testapp.getName());
		Assert.assertEquals("email", testapp.getEmail());
		Assert.assertEquals("11", testapp.getAge());
		Assert.assertEquals("telefon", testapp.getTlf());
		Assert.assertEquals("Mann", testapp.getGender());
		Assert.assertEquals("007", testapp.getIDfromName("navn"));
		Assert.assertEquals("60", testapp.getRestingHR());
		Assert.assertEquals("100", testapp.getSteps());
		Assert.assertEquals("181", testapp.getHeight());
		Assert.assertEquals("80", testapp.getWeight());
		Assert.assertEquals("Mar  2018",testapp.getDate());
		Assert.assertEquals(Arrays.asList("navn"), testapp.getNames());
	}
	
	@Test
	public void testShowHealthInfoDate() {
		Assert.assertEquals("Mar  2018", showHealthInfoContainer.getDate());
	}
	
	@Test
	public void testShowHealthInfoHeight() {
		Assert.assertEquals("181", showHealthInfoContainer.getHeight());
	}
	
	@Test
	public void testShowHealthInfoWeight() {
		Assert.assertEquals("80", showHealthInfoContainer.getWeight());
	}
	
	@Test
	public void testShowHealthInfoSteps() {
		Assert.assertEquals("100", showHealthInfoContainer.getSteps());
	}
	
	@Test
	public void testShowHealthInfoRestingHR() {
		Assert.assertEquals("60", showHealthInfoContainer.getRestingHR());
	}
	
	@Test
	public void testShowUserInfoContainerUsername() {
		Assert.assertEquals("brukernavn", showUserInfoContainer.getUsername());
	
	}
	@Test
	public void testShowUserInfoContainerName() {
		Assert.assertEquals("navn", showUserInfoContainer.getName());
	
	}
	@Test
	public void testShowUserInfoContainerPassword() {
		Assert.assertEquals("passord", showUserInfoContainer.getPassword());
	
	}
	@Test
	public void testShowUserInfoContainerAge() {
		Assert.assertEquals(11, showUserInfoContainer.getAge());
	
	
	
	}
	@Test
	public void testShowUserInfoContainerGender() {
		Assert.assertEquals(0, showUserInfoContainer.getGender());
	
	}
	@Test
	public void testShowUserInfoContainerEmail() {
		Assert.assertEquals("email", showUserInfoContainer.getEmail());
		
		Assert.assertNotEquals("mail", showUserInfoContainer.getEmail());
	
	}
	@Test
	public void testShowUserInfoContainerPhone() {
		Assert.assertEquals("telefon", showUserInfoContainer.getPhone());
	
	}
	
	@Test
	public void testBaseUrl() {
		Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseUrl());
	}
	
	@Test
	public void testCheckNull() {
		Assert.assertTrue("Ikke spesifisert".equals(testapp.checkNull("")));
		Assert.assertTrue("Ikke spesifisert".equals(testapp.checkNull(null)));
		Assert.assertTrue("Ikke spesifisert".equals(testapp.checkNull("null")));
		Assert.assertFalse("Ikke spesifisert".equals(testapp.checkNull("12")));
	}
	
}
