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

import tdt4140.gr1832.web.dao.data.Exercise;
import tdt4140.gr1832.web.dao.data.HealthDataReport;
import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("/exercise")
public class ExerciseDAO {
	public static Exercise createExercise(ResultSet rs) throws SQLException {
		Exercise exercise = new Exercise();
		exercise.setExerciseID(rs.getInt("exerciseID"));
		if(rs.wasNull()) return null;
		exercise.setProgramID(rs.getInt("programID"));
		exercise.setDescription(rs.getString("description"));
		exercise.setSets(rs.getInt("sets"));
		exercise.setRepsPerSet(rs.getInt("repsPerSet"));
		exercise.setPauseBetweenSets(rs.getInt("pauseBetweenSets"));
		exercise.setParameter(rs.getInt("parameter"));
		return exercise;
	}
	
	public static Response updateColumn(Integer exerciseID, String columnLabel, Object newValue, String type) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;

		String insertUserSQL = "update Exercise set " + columnLabel + "=? where exerciseID=?";
		if(exerciseID != null) {
			try {
				prepared_stmt = conn.prepareStatement(insertUserSQL);
				
				if(type.equals("int")) 			prepared_stmt.setInt(1, (Integer)newValue);
				else if(type.equals("bool")) 	prepared_stmt.setBoolean(1, (Boolean)newValue);
				else if(type.equals("string"))	prepared_stmt.setString(1, (String)newValue);
				
				prepared_stmt.setInt(2, exerciseID);
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
	@Path("/update_exercise")
	public Response updateExercise(@FormParam("exercise_id") Integer exerciseID , 
			   @FormParam("description") String description,
			   @FormParam("sets") Integer sets,
			   @FormParam("reps_per_set") Integer repsPerSet,
			   @FormParam("pause_between_sets") Integer pauseBetweenSets,
			   @FormParam("parameter") Integer parameter)
	{
		int status = 400;
		Integer num_rows_affected = 0;
		
		if(exerciseID != null) {
			if(description != null) num_rows_affected += updateColumn(exerciseID, "description", description, "string").getStatus() == 200 ? 1 : 0;
			if(sets != null) num_rows_affected += updateColumn(exerciseID, "sets", sets, "int").getStatus() == 200 ? 1 : 0;
			if(repsPerSet != null) num_rows_affected += updateColumn(exerciseID, "repsPerSet", repsPerSet, "int").getStatus() == 200 ? 1 : 0;
			if(pauseBetweenSets != null) num_rows_affected += updateColumn(exerciseID, "pauseBetweenSets", pauseBetweenSets, "int").getStatus() == 200 ? 1 : 0;
			if(parameter != null) num_rows_affected += updateColumn(exerciseID, "parameter", parameter, "int").getStatus() == 200 ? 1 : 0;
			status = 200;
		}
		
		return Response.status(status).entity(num_rows_affected).build();
	}
		
	@POST
	@Path("/create_exercise")
	public Response createExercise(@FormParam("program_id") Integer programID , 
			  					   @FormParam("description") String description,
			  					   @FormParam("sets") Integer sets,
			  					   @FormParam("reps_per_set") Integer repsPerSet,
			  					   @FormParam("pause_between_sets") Integer pauseBetweenSets,
			  					   @FormParam("parameter") Integer parameter)
	{
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement prepared_stmt = null;
		int status = 400;
		Integer num_rows_affected = 0;
		
		String query = "insert into Exercise  (programID, description, sets, repsPerSet, pauseBetweenSets, parameter)"
				+ " values(?, ?, ?, ?, ?, ?)";
		if(programID != null) {
			try {
				prepared_stmt = conn.prepareStatement(query);
				
				prepared_stmt.setInt(1, programID);
				prepared_stmt.setString(2, description);
				prepared_stmt.setInt(3, sets);
				prepared_stmt.setInt(4, repsPerSet);
				prepared_stmt.setInt(5, pauseBetweenSets);
				prepared_stmt.setInt(6, parameter);
				
				num_rows_affected = prepared_stmt.executeUpdate();
				status = 200;
			} 
			catch (SQLException e) {
				e.printStackTrace();
				status = 400;
			}
		}
		
		return Response.status(status).entity(num_rows_affected).build();
	}
	
	@GET
	@Path("/get_exercise")
	public String getExercise(@QueryParam("exercise_id") Integer exerciseID) {
		Connection conn = DatabaseConnection.getConnection();
		
		Exercise exercise = null;
		String query = "select * from Exercise where exerciseID= " + Integer.toString(exerciseID);
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				exercise = createExercise(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(exercise);  
		return json;
	}
	
	@GET
	@Path("/get_exercises")
	public String getExercises(@QueryParam("program_id") Integer programID) {
		Connection conn = DatabaseConnection.getConnection();
		
		List<Exercise> exercises = new ArrayList<Exercise>();
		String query = "select * from Exercise where programID= " + Integer.toString(programID);
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Exercise exercise = createExercise(rs);
				exercises.add(exercise);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(exercises);  
		return json;
	}
}
