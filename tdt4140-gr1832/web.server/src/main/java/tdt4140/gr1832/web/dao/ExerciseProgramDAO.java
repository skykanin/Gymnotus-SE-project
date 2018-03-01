package tdt4140.gr1832.web.dao;
import tdt4140.gr1832.web.dao.data.ExerciseProgram;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("/exercise_program")
public class ExerciseProgramDAO {
	
	//utility for getting information about exercise program based on the ID
	public static String getExerciseProgramDataJson(String query) {
		Connection conn = DatabaseConnection.conn;
		
		ExerciseProgram program = new ExerciseProgram();
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				program.setProgramID(rs.getInt("programID"));
				program.setName(rs.getString("name"));
				program.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			program = null;
		}
		Gson gson = new Gson();
		String json = gson.toJson(program);  
		return json;
	}
	
	public static String getExerciseProgramListJson(String query) {
		Connection conn = DatabaseConnection.conn;
		
		List<ExerciseProgram> programs = new ArrayList<ExerciseProgram>();
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				ExerciseProgram program = new ExerciseProgram();
				program.setProgramID(rs.getInt("programID"));
				program.setName(rs.getString("name"));
				program.setDescription(rs.getString("description"));
				programs.add(program);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			programs = null;
		}
		Gson gson = new Gson();
		String json = gson.toJson(programs);  
		return json;
	}
	
	@GET
	@Path("/get_id")
	public Response getExerciseProgramID(@QueryParam("name") String name) {
		Connection conn = DatabaseConnection.conn;
		
		String sql = "select programID from ExerciseProgram where name='" + name +"'";
		Integer id = null;
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				id = rs.getInt("programID");
				if(rs.wasNull()) id = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int status = id != null ? 200 : 404;
		return Response.status(status).entity(id).build();
	}
		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public String getExerciseProgram(@PathParam("id")int id) {
		String query = "select * from ExerciseProgram where programID=" + Integer.toString(id);
		String json = getExerciseProgramDataJson(query);
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all_programs")
	public String getAllExercisePrograms() {
		String query = "select * from ExerciseProgram";
		String json = getExerciseProgramListJson(query);
		return json;
	}
}
