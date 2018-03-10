package tdt4140.gr1832.web.dao;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.Gson;
import com.mysql.jdbc.Statement;

import tdt4140.gr1832.web.dao.data.User;
import tdt4140.gr1832.web.server.DatabaseConnection;


@RunWith(PowerMockRunner.class)
@PrepareForTest(DatabaseConnection.class)
public class UserDAOTest {
    @Mock
    private Connection c;
    
    @Mock
    private PreparedStatement prep_stmt;
    
    @Mock
    private Statement stmt;
    
    @Mock
    private ResultSet rs;
    
    User user;
    
    @Before
    public void setUp() throws Exception {
    		user = new User(1,"test", "test", "test@test.com", "1234", 1, 1);
    		
        when(c.prepareStatement(any(String.class))).thenReturn(prep_stmt);
        
        when(rs.first()).thenReturn(true);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getString("username")).thenReturn("test");
        when(rs.getString("name")).thenReturn("test");
        when(rs.getString("password")).thenReturn("test_pw");
        when(rs.getString("email")).thenReturn("test@test.com");
        when(rs.getInt("userID")).thenReturn(1);
        when(rs.getString("phoneumber")).thenReturn("1234");
        when(rs.getInt("gender")).thenReturn(1);
        when(rs.getInt("age")).thenReturn(1);
        
        mockStatic(DatabaseConnection.class);
        when(DatabaseConnection.getConnection()).thenReturn(c);
        
        when(prep_stmt.executeQuery()).thenReturn(rs);
        when(stmt.executeQuery(anyString())).thenReturn(rs);
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
    public void testGetStringColumnData() {
		String data = UserDAO.getStringColumnData("test", "username");
		Assert.assertNotNull(data);
		Assert.assertTrue(user.getUsername().equals(data));
    }
    
    @Test
    public void testGetIntegerColumnData() {
		Integer data = UserDAO.getIntegerColumnData("test", "age");
		Assert.assertNotNull(data);
		Assert.assertTrue(data == user.getAge());
    }
    
    @Test
    public void testUserExists() {
    		Response response = UserDAO.userExists("test");
    		Assert.assertEquals(200, response.getStatus());
    }
    
    @Test
    public void testVerifyPassword() {
    		Response response = UserDAO.verifyPassword("test", "test_pw");
    		Assert.assertEquals(200, response.getStatus());
    }
    
    @Test
    public void testGetUserInfoByUsername() {
		Gson gson = new Gson();
		String json = UserDAO.getUserInfoByUsername("test");
		Assert.assertNotNull(json);
		
		User user_info = new User();
		user_info = gson.fromJson(json, User.class);
		
		Assert.assertNotNull(user_info);
		Assert.assertTrue(isEqual(user, user_info));
    }
    
    @Test
    public void testGetUserInfoByID() {
		Gson gson = new Gson();
		String json = UserDAO.getUserInfoByID(1);
		Assert.assertNotNull(json);
		
		User user_info = new User();
		user_info = gson.fromJson(json, User.class);
		
		Assert.assertNotNull(user_info);
		Assert.assertTrue(isEqual(user, user_info));
    }
}