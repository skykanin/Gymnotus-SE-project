package tdt4140.gr1832.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import tdt4140.gr1832.web.dao.data.ExerciseProgram;
import tdt4140.gr1832.web.dao.data.User;
import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("/exercise_program")
public class UserRegisteredForExerciseDAO {
	
	@POST
	@Path("/register_user")
	public Response registerUserForProgram(@FormParam("user_id") Integer userID , 
										  @FormParam("program_id") Integer programID) 
	{
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;
		Integer num_rows_affected = 0;

		String query = "insert into UserRegisteredForExercise values(?, ?, CURDATE())";
		if(userID != null && programID != null) {
			try {
				prepared_stmt = conn.prepareStatement(query);
				
				prepared_stmt.setInt(1, userID);
				prepared_stmt.setInt(2, programID);

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
	@Path("/delete_user")
	public Response deleteUserFromProgram(@FormParam("user_id") Integer userID , 
										  @FormParam("program_id") Integer programID) 
	{
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;
		Integer num_rows_affected = 0;

		String query = "delete from UserRegisteredForExercise where userID=? and programID=?";
		if(userID != null && programID != null) {
			try {
				prepared_stmt = conn.prepareStatement(query);
				
				prepared_stmt.setInt(1, userID);
				prepared_stmt.setInt(2, programID);

				num_rows_affected = prepared_stmt.executeUpdate();
				status = 200;
			} catch (SQLException e) {
				e.printStackTrace();
				status = 400;
			}
		}
		
		return Response.status(status).entity(num_rows_affected).build();
	}
	
	@GET
	@Path("/get_users")
	public String getUsersFromProgram(@QueryParam("programID") int programID) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		String query = "select * from UserRegisteredForExercise "
				+ "natural join User"
				+ " where programID=" + Integer.toString(programID);
		List<User> users = new ArrayList<User>();
		try {
			prepared_stmt = conn.prepareStatement(query);
			ResultSet rs = prepared_stmt.executeQuery();
			while(rs.next()) {
				User user = UserDAO.createUser(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(users);  
		return json;
	}
	
	@GET
	@Path("/get_registered_programs")
	public String getPrograms(@QueryParam("user_id") Integer userID) {
		String query = "select * from UserRegisteredForExercise "
						+ "natural join ExerciseProgram where userID=" + Integer.toString(userID);
		String json = ExerciseProgramDAO.getExerciseProgramListJson(query);
		return json;
	}

}
