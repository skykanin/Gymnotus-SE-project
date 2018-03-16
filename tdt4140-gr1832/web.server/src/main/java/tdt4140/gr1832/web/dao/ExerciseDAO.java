package tdt4140.gr1832.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import tdt4140.gr1832.web.dao.data.Exercise;
import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("exercise_program")
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
	
	
	//HUSK Å TESTE FUNGERER
	@POST
	@Path("/create_exercise")
	public Response createExercise(@FormParam("program_id") Integer programID , 
			  					   @FormParam("description") String description,
			  					   @FormParam("sets") Integer sets,
			  					   @FormParam("reps_per_set") Integer repsPerSet,
			  					   @FormParam("pause_betwen_sets") Integer pauseBetweenSets,
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
}
