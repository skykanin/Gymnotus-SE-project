package tdt4140.gr1832.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import tdt4140.gr1832.web.dao.data.User;
import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("user")
public class UserSharingInformationDAO {
	
	public String executeInfoSharingQuery(String query) {
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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("anonymous")
	public String getUsersAnonymous(@QueryParam("is_anonymous")boolean isAnonymous) {
		String query = "select * from User where isAnonymous=" + Boolean.toString(isAnonymous);
		String json = executeInfoSharingQuery(query);
		return json;
	}
	
	// Get all users depending on if they share health data or not
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("health_data")
	public String getUsersHealthData(@QueryParam("shares_data")boolean sharesData) {
		String query = "select * from User where shareHealthData=" + Boolean.toString(sharesData);
		String json = executeInfoSharingQuery(query);
		return json;
	}
	
	// Get all users depending on if they share health data or not
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("exercise_data")
	public String getUsersExerciseData(@QueryParam("shares_data")boolean sharesData) {
		String query = "select * from User where shareExerciseData=" + Boolean.toString(sharesData);
		String json = executeInfoSharingQuery(query);
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("trainer")
	public String getTrainers(@QueryParam("is_trainer") boolean isTrainer) {
		String query = "select * from User where isTrainer=" + Boolean.toString(isTrainer);
		String json = executeInfoSharingQuery(query);
		return json;
	}
}
