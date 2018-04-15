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
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import tdt4140.gr1832.web.dao.data.Result;
import tdt4140.gr1832.web.dao.data.User;
import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("/result")
public class ResultDAO {
	
	public Result createResult(ResultSet rs) throws SQLException {
		Result result = new Result();
		result.setResultID(rs.getInt("resultID"));
		result.setExerciseID(rs.getInt("exerciseID"));
		result.setUserID(rs.getInt("userID"));
		result.setDate(rs.getDate("date"));
		result.setResultParameter(rs.getInt("resultParameter"));
		return result;
	}
	
	private String createResultJson(String query) {
		Connection conn = DatabaseConnection.getConnection();
		
		Result result = new Result();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				result = createResult(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);  
		return json;
	}
	
	private String createResultListJson(String query) {
		Connection conn = DatabaseConnection.getConnection();
		
		List<Result> results = new ArrayList<Result>();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Result result = createResult(rs);
				results.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(results);  
		return json;
	}
	
	@GET
	@Path("/get_result")
	public String getResult(@QueryParam("result_id") Integer resultID) {
		String query = "select * from Result where resultID=" + Integer.toString(resultID);
		String json = createResultJson(query);
		return json;
	}
	
	@GET
	@Path("/get_results_by_user")
	public String getResultsByUser(@QueryParam("user_id") Integer userID) {
		String query = "select * from Result where userID=" + Integer.toString(userID);
		String json = createResultListJson(query);
		return json;
	}
	
	@GET
	@Path("/get_results_by_exercise")
	public String getResultsByExercise(@QueryParam("exercise_id") Integer exerciseID) {
		String query = "select * from Result where exerciseID=" + Integer.toString(exerciseID);
		String json = createResultListJson(query);
		return json;
	}
	
	@GET
	@Path("/get_users_added_results_to_exercise")
	public String getUsersAddedResultsToExercise(@QueryParam("exercise_id") Integer exerciseID) {
		String query = "select distinct userID,username,name,email,phoneumber,gender,age,isAnonymous,shareHealthData,shareExerciseData,isTrainer from Result natural join User where exerciseID=" + Integer.toString(exerciseID);
		Connection conn = DatabaseConnection.getConnection();
		
		List<User> users = new ArrayList<User>();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
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
	@Path("/get_results_by_user_and_exercise")
	public String getResultsByUserExercise(@QueryParam("user_id") Integer userID, 
										  @QueryParam("exercise_id") Integer exerciseID) {
		String query = "select * from Result where exerciseID=" + Integer.toString(exerciseID) +
						" and userID=" + Integer.toString(userID);
		String json = createResultListJson(query);
		return json;
	}
	
	//Date on form yyyy-mm-dd
	@GET
	@Path("/get_results_by_date")
	public String getResultsByDate(@QueryParam("date") String date) {
		String query = "select * from Result where date='" + date + "'";
		String json = createResultListJson(query);
		return json;
	}
	
	@GET
	@Path("/get_results_by_month")
	public String getResultsByMonth(@PathParam("month")int month) {
		String query = "select * from Result where month(date)=" + Integer.toString(month);
		String json = createResultListJson(query);
		return json;
	}
	
	@GET
	@Path("/get_results_by_day_month")
	public String getResultsByMonthAndDay(@PathParam("day")int day, @PathParam("month")int month) {
		String query = "select * from Result where month(date)=" + Integer.toString(month) +
											" and day(date)=" + Integer.toString(day);
		String json = createResultListJson(query);
		return json;
	}
	
	@GET
	@Path("get_results_by_program_and_user")
	public String getResultsByProgramUser(@QueryParam("user_id") Integer userID, 
										 @QueryParam("program_id") Integer programID)  {
		String query = "select * from Result natural join Exercise" +
						" where programID=" + Integer.toString(programID) + 
						" and userID=" + Integer.toString(userID);
		Connection conn = DatabaseConnection.getConnection();
		
		List<Result> results = new ArrayList<Result>();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Result result = createResult(rs);
				result.setDescription(rs.getString("description"));
				results.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(results);  
		return json;
	}
	
	@POST
	@Path("/create_result")
	public Response createResult(@FormParam("user_id") Integer userID, 
								 @FormParam("exercise_id") Integer exerciseID,
								 @FormParam("result_parameter") Integer resultParameter,
								 @FormParam("date") String date) // yyyy-mm-dd
	{
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;
		String query = "insert into Result (userID, exerciseID, date, resultParameter) "
						+ "values (?, ?, ?, ?)";
		
		if(userID == null && date == null && exerciseID == null && resultParameter == null)
			return Response.status(status).build();
		
		// Execute query
		try {
			prepared_stmt = conn.prepareStatement(query);
			
			prepared_stmt.setInt(1, userID);
			prepared_stmt.setInt(2, exerciseID);
			prepared_stmt.setString(3, date);
			prepared_stmt.setInt(4, resultParameter);

			prepared_stmt.executeUpdate();
			status = 200;
		} catch (SQLException e) {
			e.printStackTrace();
			status = 400;
		}
		return Response.status(status).build();
	}
}
