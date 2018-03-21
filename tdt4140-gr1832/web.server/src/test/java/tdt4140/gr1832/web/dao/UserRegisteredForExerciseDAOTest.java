package tdt4140.gr1832.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tdt4140.gr1832.web.dao.data.ExerciseProgram;
import tdt4140.gr1832.web.dao.data.User;
import tdt4140.gr1832.web.server.DatabaseConnection;

public class UserRegisteredForExerciseDAOTest {
	private Connection conn;
	private static int testNum = 0;
	private UserDAO userDAO = new UserDAO();
	private UserRegisteredForExerciseDAO registeredDAO = new UserRegisteredForExerciseDAO();
	private ExerciseProgramDAO exerciseProgramDAO = new ExerciseProgramDAO();
	private List<User> users;
	private Gson gson;
	
	void createExerciseProgram(String name, String description) {
		
	}

	@Before
	public void setUp() throws Exception {
		users = new ArrayList<User>();
		gson = new Gson();
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + getClass().getName() + testNum, "SA", "");
		DatabaseConnection.setConnection(conn);
		DatabaseConnection.executeStatements("create_database.sql", false);
		
		Response response = userDAO.createUser("test", "test", "test", "test@test.com", "1234", 1, 18, true, true, true, true);
		Assert.assertEquals(200, response.getStatus());
		
		Response response1 = exerciseProgramDAO.createExerciseProgram("test", "test");
		Assert.assertEquals(200, response1.getStatus());
		
		Response response2 = registeredDAO.registerUserForProgram(0, 0);
		Assert.assertEquals(200, response2.getStatus());
		
		testNum++;
	}
	
	@After
	public void tearDown() {
		if (conn != null) {
			try {
				conn.close();
			} 
			catch (final SQLException e) { }
		}
	}
	
	@Test
	public void testGetUsersFromProgram() {
		users = gson.fromJson(registeredDAO.getUsersFromProgram(0), new TypeToken<List<User>>(){}.getType());
		Assert.assertEquals(1, users.size());
		
		users = gson.fromJson(registeredDAO.getUsersFromProgram(1), new TypeToken<List<User>>(){}.getType());
		Assert.assertEquals(0, users.size());
	}
	
	@Test
	public void testDeleteUserForProgram() {
		Response response = registeredDAO.deleteUserFromProgram(0, 0);
		Assert.assertEquals(1, (int)response.getEntity());
		
		Response response1 = registeredDAO.deleteUserFromProgram(0, 2);
		Assert.assertEquals(0, (int)response1.getEntity());
		
		Response response2 = registeredDAO.deleteUserFromProgram(2, 4);
		Assert.assertEquals(0, (int)response2.getEntity());
	}
	
	@Test
	public void testRegisterUserFromProgram() {
		Response response0 = exerciseProgramDAO.createExerciseProgram("test1", "test1");
		Assert.assertEquals(1, (int)response0.getEntity());
		
		Response response1 = registeredDAO.registerUserForProgram(0, 1);
		Assert.assertEquals(1, (int)response1.getEntity());
		
		users = gson.fromJson(registeredDAO.getUsersFromProgram(1), new TypeToken<List<User>>(){}.getType());
		Assert.assertEquals(1, users.size());
	}
	
	@Test
	public void testGetProgramsForUser() {
		List<ExerciseProgram> programs = gson.fromJson(registeredDAO.getPrograms(0), new TypeToken<List<ExerciseProgram>>(){}.getType());
		Assert.assertNotNull(programs);
		
		Assert.assertEquals(1, programs.size());
		ExerciseProgram program = programs.get(0);
		Assert.assertEquals(0, program.getProgramID());
		Assert.assertEquals("test", program.getName());
		Assert.assertEquals("test", program.getDescription());
	}
}
