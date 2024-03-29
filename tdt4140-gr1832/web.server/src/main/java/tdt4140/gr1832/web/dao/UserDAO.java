package tdt4140.gr1832.web.dao;
import tdt4140.gr1832.web.server.DatabaseConnection;
import tdt4140.gr1832.web.dao.data.HealthDataReport;
import tdt4140.gr1832.web.dao.data.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import org.mindrot.jbcrypt.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Path("user")
public class UserDAO {
	
	public static User createUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserID(rs.getInt("userID"));
		if(rs.wasNull()) return null;
		user.setUsername(rs.getString("username"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phoneumber"));
		user.setGender(rs.getInt("gender"));
		user.setAge(rs.getInt("age"));
		user.setIsAnonymous(rs.getBoolean("isAnonymous"));
		user.setShareHealthData(rs.getBoolean("shareHealthData"));
		user.setShareExerciseData(rs.getBoolean("shareExerciseData"));
		user.setIsTrainer(rs.getBoolean("isTrainer"));
		return user;
	}
	
	// Utilities for getting single columns from User entity
	public static String getStringColumnData(String username,
											String columnLabel) {
		Connection conn = DatabaseConnection.getConnection();
		
	    String sql = "select " + columnLabel + " from User where username=" + "'" + username +"'";
	    String data = null;
	    
	    PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
		    ResultSet rs = stmt.executeQuery();
		    while(rs.next()) {
		    		data = rs.getString(columnLabel);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static Integer getIntegerColumnData(String username,
									   String columnLabel) {
		Connection conn = DatabaseConnection.getConnection();
		
		String sql = "select " + columnLabel + " from User where username=" + "'" + username +"'";
		Integer data = null;
		
		PreparedStatement stmt;
		try {
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				data = rs.getInt(columnLabel);
				if(rs.wasNull()) data = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static Response updateColumn(String username, String columnLabel, Object newValue, String type) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;

		String insertUserSQL = "update User set " + columnLabel + "=? where username=?";
		if(username != null) {
			try {
				prepared_stmt = conn.prepareStatement(insertUserSQL);
				
				if(type.equals("int")) 			prepared_stmt.setInt(1, (Integer)newValue);
				else if(type.equals("bool")) 	prepared_stmt.setBoolean(1, (Boolean)newValue);
				else if(type.equals("string"))	prepared_stmt.setString(1, (String)newValue);
				prepared_stmt.setString(2, username);
				prepared_stmt.executeUpdate();
				status = 200;
			} catch (SQLException e) {
				e.printStackTrace();
				status = 400;
			}
		}
		
		return Response.status(status).build();
	}
	
	
	@POST
	@Path("/create_user")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createUser(@FormParam("username") String username,
							  @FormParam("password") String password,
							  @FormParam("name") String name,
							  @FormParam("email") String email,
							  @FormParam("phone") String phone,
							  @FormParam("gender") int gender,
							  @FormParam("age") int age,
							  @FormParam("isAnonymous") Boolean isAnonymous,
							  @FormParam("shareHealthData") Boolean shareHealthData,
							  @FormParam("shareExerciseData") Boolean shareExerciseData,
							  @FormParam("isTrainer") Boolean isTrainer
							  ) 
	{
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;

		String insertUserSQL = "insert into User "
				+ "(username, password, name, email, phoneumber, gender, age, isAnonymous, shareHealthData, shareExerciseData, isTrainer)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		username = username.toLowerCase();
		String userExists = userExists(username).getEntity().toString();
		if(username != null && password != null && userExists.equals("false")) {
			String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
			try {
				prepared_stmt = conn.prepareStatement(insertUserSQL);
				
				prepared_stmt.setString(1, username);
				prepared_stmt.setString(2, hashed_password);
				prepared_stmt.setString(3, name);
				prepared_stmt.setString(4, email);
				prepared_stmt.setString(5, phone);
				prepared_stmt.setInt(6, gender);
				prepared_stmt.setInt(7, age);
				prepared_stmt.setBoolean(8, false);
				prepared_stmt.setBoolean(9, true);
				prepared_stmt.setBoolean(10, true);
				prepared_stmt.setBoolean(11, true);

				prepared_stmt.executeUpdate();
				status = 200;
			} catch (SQLException e) {
				e.printStackTrace();
				status = 400;
			}
		}
		
		return Response.status(status).build();
	}
	
	@POST
	@Path("delete_user")
	public Response deleteUser(@FormParam("username") String username,
							  @FormParam("password") String password)
	{
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;
		Integer num_rows_affected = 0;

		String deleteUserSQL = "delete from User where username=?";
		String userExists = userExists(username).getEntity().toString();
		if(username != null && password != null && verifyPassword(username, password).getStatus() == 200
				&& userExists.equals("true")) {

			try {
				prepared_stmt = conn.prepareStatement(deleteUserSQL);
				
				prepared_stmt.setString(1, username);

				num_rows_affected = prepared_stmt.executeUpdate();
				status = 200;
			} catch (SQLException e) {
				e.printStackTrace();
				status = 400;
			}
		}
		return Response.status(status).entity(num_rows_affected).build();
	}
	
	@POST
	@Path("/{username}/update_user")
	public Response updateUser(@PathParam("username") String username,
							   @FormParam("new_password") String new_password,
							   @FormParam("new_name") String new_name,
							   @FormParam("new_email") String new_email,
							   @FormParam("new_phone") String new_phone,
							   @FormParam("new_gender") int new_gender,
							   @FormParam("new_age") int new_age,
							   @FormParam("new_is_anonymous") Boolean isAnonymous,
							   @FormParam("new_share_health_data") Boolean shareHealthData,
							   @FormParam("new_share_exercise_data") Boolean shareExerciseData,
							   @FormParam("new_is_trainer") Boolean isTrainer
							   )
	{
		int status = 400;
		if(userExists(username).getEntity().toString().equals("true")) {
			if(new_name != null) updateColumn(username, "name", new_name, "string");
			if(new_email != null) updateColumn(username, "email", new_email, "string");
			if(new_phone != null) updateColumn(username, "phoneumber", new_phone, "string");
			if(new_gender > -1) updateColumn(username, "gender", new_gender, "int");
			if(new_age != 0) updateColumn(username, "age", new_age, "int");
			if(new_password != null) {
				//new password gets hashed first
				new_password = BCrypt.hashpw(new_password, BCrypt.gensalt());
				updateColumn(username, "password", new_password, "string");

			}
			if(isAnonymous != null) updateColumn(username, "isAnonymous", isAnonymous, "bool");
			if(shareHealthData != null) updateColumn(username, "shareHealthData", shareHealthData, "bool");
			if(shareExerciseData != null) updateColumn(username, "shareExerciseData", shareExerciseData, "bool");
			if(isTrainer != null) updateColumn(username, "isTrainer", isTrainer, "bool");
			status = 200;
		}
		return Response.status(status).build();
	}
	
	@GET
	@Path("/user_exists")
	public static Response userExists(@QueryParam("username")String username) {
		String dbUsername = getStringColumnData(username, "username");
		Boolean userExists = dbUsername == null ? false : dbUsername.equals(username.toLowerCase());
		int status = dbUsername != null ? 200 : 404;
		return Response.status(status).entity(userExists.toString()).build();
	}
	
	
	@GET
	@Path("/{username}/verify_password")
	public static Response verifyPassword(@PathParam("username") String username, 
								  @QueryParam("password") String password) {
		String dbPassword = getStringColumnData(username.toLowerCase(), "password");
		Boolean isSame = dbPassword != null && BCrypt.checkpw(password, dbPassword);
		int status = dbPassword != null ? 200 : 404;
		return Response.status(status).entity(isSame.toString()).build();
	}
	
	@GET
	@Path("/{username}/user_info")
	@Produces(MediaType.APPLICATION_JSON)
	public static String getUserInfoByUsername(@PathParam("username") String username) {
		Connection conn = DatabaseConnection.getConnection();
		String query = "select * from User where username=" + "'" + username + "'";
		User user = new User();
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				user = createUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(user);  
		return json;
	}
	
	@GET
	@Path("/{id}/user_info_id")
	@Produces(MediaType.APPLICATION_JSON)
	public static String getUserInfoByID(@PathParam("id") int id) {
		Connection conn = DatabaseConnection.getConnection();
		String query = "select * from User where userID=" + Integer.toString(id);
		User user = new User();
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				user = createUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(user);  
		return json;
	}
	
	@GET
	@Path("/get_all_ids")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllID() {
		Connection conn = DatabaseConnection.getConnection();
		String query = "select userID from User";
		
		List<Integer> ids = new ArrayList<Integer>();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ids.add(rs.getInt("userID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(ids);  
		return json;
	}
	
}
