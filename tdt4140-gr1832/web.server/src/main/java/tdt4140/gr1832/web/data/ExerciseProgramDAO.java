package tdt4140.gr1832.web.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("/exercise_program")
public class ExerciseProgramDAO {
	
	//utility for getting information about exercise program based on the ID
	public static String getStringColumnData(String columnLabel, int id) {
		Connection conn = DatabaseConnection.conn;
		
		String sql = "select " + columnLabel + " from ExerciseProgram where programID=" + Integer.toString(id);
		String data = null;
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				data = rs.getString(columnLabel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
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
	@Path("/{id}/name")
	public Response getExerciseProgramName(@PathParam("id")int id) {
		String name = getStringColumnData("name", id);
		int status = name != null ? 200 : 404;
		return Response.status(status).entity(name).build();
	}
	
	@GET
	@Path("/{id}/description")
	public Response getExerciseProgramDescription(@PathParam("id")int id) {
		String desc = getStringColumnData("description", id);
		int status = desc != null ? 200 : 404;
		return Response.status(status).entity(desc).build();
	}
}
