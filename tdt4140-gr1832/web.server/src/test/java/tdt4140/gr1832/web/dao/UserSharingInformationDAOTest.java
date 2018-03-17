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

import tdt4140.gr1832.web.dao.data.User;
import tdt4140.gr1832.web.server.DatabaseConnection;

public class UserSharingInformationDAOTest {
	private Connection conn;
	private static int testNum = 0;
	private UserDAO userDAO = new UserDAO();
	private UserSharingInformationDAO informationDAO = new UserSharingInformationDAO();
	private List<User> users;
	private Gson gson;

	@Before
	public void setUp() throws Exception {
		users = new ArrayList<User>();
		gson = new Gson();
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + getClass().getName() + testNum, "SA", "");
		DatabaseConnection.setConnection(conn);
		DatabaseConnection.executeStatements("create_database.sql", false);
		Response response = userDAO.createUser("test", "test", "test", "test@test.com", "1234", 1, 18, true, true, true);
		Assert.assertEquals(200, response.getStatus());
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
	public void testGetUsersAnonymous() {
		users = gson.fromJson(informationDAO.getUsersAnonymous(true), new TypeToken<List<User>>(){}.getType());
		Assert.assertEquals(1, users.size());
		
		users = gson.fromJson(informationDAO.getUsersAnonymous(false), new TypeToken<List<User>>(){}.getType());
		Assert.assertEquals(0, users.size());
		
	}
	
	@Test
	public void testGetUsersHealthData() {
		users = gson.fromJson(informationDAO.getUsersHealthData(true), new TypeToken<List<User>>(){}.getType());
		Assert.assertEquals(1, users.size());
		
		users = gson.fromJson(informationDAO.getUsersHealthData(false), new TypeToken<List<User>>(){}.getType());
		Assert.assertEquals(0, users.size());
		
	}
	
	@Test
	public void testGetUsersExerciseData() {
		users = gson.fromJson(informationDAO.getUsersExerciseData(true), new TypeToken<List<User>>(){}.getType());
		Assert.assertEquals(1, users.size());
		
		users = gson.fromJson(informationDAO.getUsersExerciseData(false), new TypeToken<List<User>>(){}.getType());
		Assert.assertEquals(0, users.size());
	}
}
