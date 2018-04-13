package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import containers.ExerciseContainer;
import containers.ExerciseProgramContainer;
import containers.ResultContainer;
import containers.ShowAllUsersContainer;
import containers.ShowHealthInfoContainer;
import containers.ShowUserInfoContainer;

public class ProgramResultsGraphsAppTest {
	ShowUserInfoContainer userContainer;
	ShowUserInfoContainer userContainer1;
	ShowUserInfoContainer userContainer2;
	ShowHealthInfoContainer healthContainer;
	List<ShowHealthInfoContainer> healthContainers = new ArrayList<>();
	ShowAllUsersContainer showAllUserContainer; 
	ProgramResultsGraphsApp testapp = new ProgramResultsGraphsApp();
	List<ExerciseContainer> exCons = new ArrayList<>();
	ExerciseContainer exCon;
	ExerciseProgramContainer exProgCon;
	List<ExerciseProgramContainer> exProgCons = new ArrayList<>();
	ResultContainer resCon;
	List<ResultContainer> resCons = new ArrayList<>();
	List<Integer> userids = new ArrayList<>();
	ResultContainer resCon2;
	
	
	
	@Before
	public void setup() {
		
		
		
		showAllUserContainer = new ShowAllUsersContainer(); 
		userContainer = new ShowUserInfoContainer("brukernavn", "passord", "navn1",11, 0, "email", "telefon", false, true, true, false);
		userContainer1 = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 1, "email", "telefon", false, true, true, false);
		userContainer2 = new ShowUserInfoContainer("brukernavn", "passord", "navn",11, 2, "email", "telefon", false, true, true, false);
		healthContainer = new ShowHealthInfoContainer(123, 007, "Mar, 2018", 85, 100, 60, 181, 80, false, true, true);
		
		exCon = new ExerciseContainer(1, 2, "biceps", 3, 10, 2, "antallKg");
		exCons.add(exCon);
	
		exProgCon = new ExerciseProgramContainer(1, "etNavn", "enBeskrivelse");
		exProgCons.add(exProgCon);
		
		resCon = new ResultContainer(2, 3, 1, "Mar 1, 2018", 10);
		resCons.add(resCon);
		
		userids.add(1);
		userids.add(2);
		userids.add(3);
		
		testapp.setContainerUser(userContainer);
		healthContainers.add(healthContainer);
		testapp.setHealthContainers(healthContainers);
	}
	
	@Test
	public void testEverything() {
		Assert.assertEquals(userContainer,testapp.getContainerUser());
		Assert.assertEquals(null,testapp.getDates());
		Assert.assertEquals(null,testapp.getResults());
		testapp.setExContainers(exCons);
		testapp.setResContainers(new ArrayList<ResultContainer>());
		Assert.assertEquals(null,testapp.getDates());
		Assert.assertEquals(null,testapp.getResults());
		testapp.setResContainers(resCons);
		testapp.setContainerExercisePrograms(exProgCons);
		testapp.setUserids(userids);
		Assert.assertEquals(exCon, testapp.getExContainers().get(0));
		Assert.assertEquals(resCon, testapp.getResContainers().get(0));
		Assert.assertEquals(exProgCon, testapp.getContainerExercisePrograms().get(0));
		Assert.assertEquals(Arrays.asList(1,2,3), testapp.getUserIDs());
		Assert.assertEquals(Arrays.asList("Mar 1, 2018"), testapp.getDates());
		Assert.assertEquals(Arrays.asList(10), testapp.getResults());
		Assert.assertEquals(Arrays.asList(healthContainer), testapp.getHealthContainers());
		showAllUserContainer.addUserInfo(userContainer);
		testapp.setContainerAllUsers(showAllUserContainer);
		userContainer.setUserId("007");
		Assert.assertEquals("-1", testapp.getIDfromName("feil"));
		Assert.assertEquals("007", testapp.getIDfromName("navn1"));
		
		
		
	}
	
	@Test
	public void testBaseUrl() {
		testapp.setBaseURI("http://146.185.153.244:8080/api/");
		Assert.assertEquals("http://146.185.153.244:8080/api/", testapp.getBaseURI());
	}
}