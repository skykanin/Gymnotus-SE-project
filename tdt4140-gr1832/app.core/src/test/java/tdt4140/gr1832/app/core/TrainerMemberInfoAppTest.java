package tdt4140.gr1832.app.core;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import tdt4140.gr1832.app.containers.ShowAllUsersContainer;
import tdt4140.gr1832.app.containers.ShowHealthInfoContainer;
import tdt4140.gr1832.app.containers.ShowUserInfoContainer;

public class TrainerMemberInfoAppTest extends TestCase {
	//Serverrequests are testted elsewere
	ShowUserInfoContainer showUserInfoContainer;
	ShowUserInfoContainer showUserInfoContainer1;
	ShowUserInfoContainer showUserInfoContainer2;
	ShowHealthInfoContainer showHealthInfoContainer;
	ShowAllUsersContainer showAllUserContainer; 
	
	TrainerMemberInfoApp testapp = new TrainerMemberInfoApp();
	
	
	
	
	@Before
	public void setUp() {
		showAllUserContainer = new ShowAllUsersContainer(); 
		showUserInfoContainer = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 0, "email", "telefon", false, true, true, false);
		showUserInfoContainer1 = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 1, "email", "telefon", false, true, true, false);
		showUserInfoContainer2 = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 2, "email", "telefon", false, true, true, false);
		showHealthInfoContainer = new ShowHealthInfoContainer(123, 007, "Mar, 2018", 85, 100, 60, 181, 80, false, true, true);
		testapp.setContainerUser(showUserInfoContainer);
		testapp.setTest(true);
	}
	@Test
	public void testShowAllUserInfoContainerAdd() {
		assertEquals(Arrays.asList(), showAllUserContainer.getUsers());
		showAllUserContainer.addUserInfo(showUserInfoContainer);
		assertEquals(Arrays.asList(showUserInfoContainer), showAllUserContainer.getUsers());
	}
	
	@Test
	public void testRequestAllID() {
		testapp.requestAllUserID();
		Assert.assertEquals(1, testapp.getContainerAllUsers().getUsers().size());
		Assert.assertEquals("Brukeren er anonym", testapp.getUsername());
	}
	
	@Test
	public void testRequestHealthInformationID() {
		testapp.requestHealthInformation_ID("1");
		List<ShowHealthInfoContainer> containers = testapp.getContainerHealth();
		Assert.assertEquals(1, containers.size());
		Assert.assertEquals(-1, containers.get(0).getHeight());
	}
	
	@Test
	public void testTrainerMemberInfoGetters() {
		Assert.assertEquals(showUserInfoContainer, testapp.getContainerUser());
		testapp.addContainerHealth(showHealthInfoContainer);
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
		Assert.assertEquals("Mar, 2018",testapp.getDate());
		Assert.assertEquals(Arrays.asList("navn"), testapp.getNames());
		testapp.setContainerUser(showUserInfoContainer1);
		Assert.assertEquals("Kvinne", testapp.getGender());
		testapp.setContainerUser(showUserInfoContainer2);
		Assert.assertEquals("Uspesifisert", testapp.getGender());
		Assert.assertEquals("1", testapp.getIDfromName("feil"));
		Assert.assertEquals(Arrays.asList(showHealthInfoContainer),testapp.getContainerHealth());
	}
	
	@Test
	public void testDateFunction() {
		testapp.addContainerHealth(showHealthInfoContainer);
		testapp.addContainerHealth(new ShowHealthInfoContainer(1, 1, "Feb, 1", 0, 0, 0, 0, 0, false, true, true));
		Assert.assertEquals("Mar, 2018",testapp.getDate());
		testapp.nextDate();
		Assert.assertEquals("Feb, 1",testapp.getDate());
		testapp.nextDate();
		testapp.nextDate();
		testapp.lastDate();
		Assert.assertEquals("Mar, 2018",testapp.getDate());
		testapp.lastDate();
		Assert.assertEquals("Feb, 1",testapp.getDate());
		testapp.giveDateIndex(0);
		testapp.giveDateIndex(5);
		Assert.assertEquals("Mar, 2018",testapp.getDate());
		Assert.assertEquals(Arrays.asList("Mar, 2018", "Feb, 1"), testapp.getDates());
	}
	
	@Test 
	public void  testcheckHealthData() {
		testapp.addContainerHealth(new ShowHealthInfoContainer(1, 1, "test", -1, -1, -1, -1, -1, false, false, false));
		Assert.assertEquals("Brukeren viser ikke helsedata",testapp.getSteps());
	}
	@Test
	public void testGetUsers() {
		Assert.assertEquals(Arrays.asList(),testapp.getUsers());
		testapp.getContainerAllUsers().addUserInfo(showUserInfoContainer);
		Assert.assertEquals(Arrays.asList(showUserInfoContainer), testapp.getUsers());
	}
	
	@Test
	public void testUserInfoContainer() {
		ShowUserInfoContainer a = new ShowUserInfoContainer("Test", "password", "test", 0, 0, "test", "test", true, true, true, true);
		a.setUserId("null");
		Assert.assertEquals("Anonym#"+"null", a.getName());
		a.setPhone("123");
		Assert.assertEquals("123", a.getPhone());
		a.setUsername("unavn");
		Assert.assertEquals("unavn", a.getUsername());
		a.setName("navn");
		Assert.assertEquals("navn", a.getName());
		a.setEmail("Enavn");
		Assert.assertEquals("Enavn", a.getEmail());
		Assert.assertEquals(true, a.getIsAnonymous());
		a.setAnonymous(false);
		Assert.assertEquals(false, a.getIsAnonymous());
		Assert.assertEquals(true, a.getShareExerciseData());
		a.setShareExerciseData(false);
		Assert.assertEquals(false, a.getShareExerciseData());
		Assert.assertEquals(true, a.getShareHealthData());
		a.setShareHealthData(false);
		Assert.assertEquals(false, a.getShareHealthData());
		Assert.assertEquals("navnunavn00password", a.toString());
		
		
	}
	
	@Test
	public void testHealthInfoContainer() {
		ShowHealthInfoContainer h = new ShowHealthInfoContainer(1, 3, "Mars 1", 11, 11, 11, 11, 11, true, false, true);
		h.viewNoHealthData();
		Assert.assertEquals(-1, h.getHeight());
		Assert.assertEquals(-1, h.getSteps());
		Assert.assertEquals(-1, h.getWeight());
		Assert.assertEquals(-1, h.getRestingHR());
	}
	
	@Test
	public void testNotSpecified() {
		Assert.assertEquals("Ikke spesifisert", testapp.getSteps());
		Assert.assertEquals("Ikke spesifisert", testapp.getHeight());
		Assert.assertEquals("Ikke spesifisert", testapp.getWeight());
		Assert.assertEquals("Ikke spesifisert", testapp.getRestingHR());
		Assert.assertEquals("Ikke spesifisert", testapp.getDate());
	}
	@ Test 
	public void testConvertArrayToString() {
		String[] s = {"h","e","i"};
		Assert.assertEquals("hei", testapp.convertArrayToString(s));
	}
	@Test
	public void testShowHealthInfoDate() {
		Assert.assertEquals("Mar, 2018", showHealthInfoContainer.getDate());
	}
	
	@Test
	public void testShowHealthInfoHeight() {
		Assert.assertEquals(181, showHealthInfoContainer.getHeight());
	}
	
	@Test
	public void testShowHealthInfoWeight() {
		Assert.assertEquals(80, showHealthInfoContainer.getWeight());
	}
	
	@Test
	public void testShowHealthInfoSteps() {
		Assert.assertEquals(100, showHealthInfoContainer.getSteps());
	}
	
	@Test
	public void testShowHealthInfoRestingHR() {
		Assert.assertEquals(60, showHealthInfoContainer.getRestingHR());
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
		Assert.assertEquals(1, showUserInfoContainer1.getGender());
		Assert.assertEquals(2, showUserInfoContainer2.getGender());

	
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
	public void testGetUser() {
		ShowAllUsersContainer c = new ShowAllUsersContainer();
		c.addUserInfo(showUserInfoContainer);
		Assert.assertEquals(null, c.getUser("01010"));
		showUserInfoContainer.setUserId("1");
		Assert.assertEquals(showUserInfoContainer, c.getUser("1"));
		
	}
	
	@Test
	public void testCheckNull() {
		Assert.assertTrue("Ikke spesifisert".equals(testapp.checkNull("")));
		Assert.assertTrue("Ikke spesifisert".equals(testapp.checkNull(null)));
		Assert.assertTrue("Ikke spesifisert".equals(testapp.checkNull("null")));
		Assert.assertFalse("Ikke spesifisert".equals(testapp.checkNull("12")));
	}

	
	
}
