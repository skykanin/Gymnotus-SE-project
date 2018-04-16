package tdt4140.gr1832.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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


public class UserDAOTest {
    
	private Connection conn;
	private static int testNum = 0;
	private UserDAO userDAO = new UserDAO();
	private User user = null;
	private Gson gson;

	@Before
	public void setUp() throws Exception {
		user = new User();
		gson = new Gson();
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + getClass().getName() + testNum, "SA", "");
		DatabaseConnection.setConnection(conn);
		DatabaseConnection.executeStatements("create_database.sql", false);
		
		Response response = userDAO.createUser("test", "test", "test", "test@test.com", "1234", 1, 18, true, true, true, true);
		Assert.assertEquals(200, response.getStatus());
		
		user = gson.fromJson(UserDAO.getUserInfoByUsername("test"), User.class);
		
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
    
    private boolean isEqual(User u1, User u2) {
    		if(u1.getAge() == u2.getAge() &&
    		   u1.getGender() == u2.getGender() &&
    		   u1.getUserID() == u2.getUserID() &&
    		   u1.getEmail().equals(u2.getEmail()) &&
    		   u1.getName().equals(u2.getName()) &&
    		   u1.getPhone().equals(u2.getPhone()) &&
    		   u1.getUsername().equals(u2.getUsername()))
    			return true;
    		   
    		return false;
    }
    
    @Test
    public void testGetAllID() {
    		String response = userDAO.getAllID();
    		List<Integer> ids = gson.fromJson(response, new TypeToken<List<Integer>>(){}.getType());
    		Assert.assertEquals(1, ids.size());
    		Assert.assertEquals(0, (int)ids.get(0));
    }
    
    @Test
    public void testDeleteUser() {
    		Response response = userDAO.deleteUser("test", "test");
    		Assert.assertEquals(200, response.getStatus());
    		Assert.assertEquals(404, UserDAO.userExists("test").getStatus());
    }
    
    @Test
    public void testGetStringColumnData() {
		String data = UserDAO.getStringColumnData("test", "username");
		Assert.assertNotNull(data);
		Assert.assertTrue(user.getUsername().equals(data));
    }
    
    @Test
    public void testGetIntegerColumnData() {
		Integer data = UserDAO.getIntegerColumnData("test", "age");
		Assert.assertNotNull(data);
		Assert.assertEquals(18, (int)data);
    }
    
    @Test
    public void testUserExists() {
		Response response = UserDAO.userExists("test");
		Assert.assertEquals(200, response.getStatus());
    }
    
    @Test
    public void testVerifyPassword() {
		Response response = UserDAO.verifyPassword("test", "test");
		Assert.assertEquals(200, response.getStatus());
    }
    
    @Test
    public void testGetUserInfoByUsername() {
		String json = UserDAO.getUserInfoByUsername("test");
		Assert.assertNotNull(json);
		
		User user_info = gson.fromJson(json, User.class);
		
		Assert.assertNotNull(user_info);
		Assert.assertTrue(isEqual(user, user_info));
    }
    
    @Test
    public void testUpdateUser() {
		Response response = userDAO.updateUser("test", "test", "new_name", "new_email", "1111", 1, 22, false, false, false, false);
		Assert.assertEquals(200, response.getStatus());
		String json = UserDAO.getUserInfoByID(0);
		Assert.assertNotNull(json);
		User updatedUser = gson.fromJson(json, User.class);
		
		Assert.assertEquals("new_name", updatedUser.getName());
		Assert.assertEquals("new_email", updatedUser.getEmail());
		Assert.assertEquals("1111", updatedUser.getPhone());
		Assert.assertEquals(1, (int)updatedUser.getGender());
		Assert.assertEquals(22, (int)updatedUser.getAge());
		Assert.assertFalse(updatedUser.getIsAnonymous());
		Assert.assertFalse(updatedUser.getIsTrainer());
		Assert.assertFalse(updatedUser.getShareExerciseData());
		Assert.assertFalse(updatedUser.getShareHealthData());
    }
    
    @Test
    public void testGetUserInfoByID() {
		String json = UserDAO.getUserInfoByID(0);
		Assert.assertNotNull(json);
		
		User user_info = gson.fromJson(json, User.class);
		
		Assert.assertNotNull(user_info);
		Assert.assertTrue(isEqual(user, user_info));
    }
}