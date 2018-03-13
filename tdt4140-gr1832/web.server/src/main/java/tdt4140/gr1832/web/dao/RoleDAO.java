package tdt4140.gr1832.web.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import tdt4140.gr1832.web.dao.data.Role;
import tdt4140.gr1832.web.dao.data.User;
import tdt4140.gr1832.web.server.DatabaseConnection;

@Path("role")
public class RoleDAO {
	
	public static String getRoleDataListJson(String query) {
		Connection conn = DatabaseConnection.getConnection();
		
		List<Role> roles = new ArrayList<Role>();
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Role role = new Role();
				role.setRoleID(rs.getInt("roleID"));
				role.setTitle(rs.getString("title"));
				role.setDescription(rs.getString("description"));
				roles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			roles = null;
		}
		Gson gson = new Gson();
		String json = gson.toJson(roles);  
		return json;
	}
	
	public static String getRoleDataJson(String query) {
		Connection conn = DatabaseConnection.getConnection();
		
		Role role = new Role();
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				role.setRoleID(rs.getInt("roleID"));
				role.setTitle(rs.getString("title"));
				role.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			role = null;
		}
		Gson gson = new Gson();
		String json = gson.toJson(role);  
		return json;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all_roles")
	public String getAllRoles() {
		String query = "select * from Role";
		String json = getRoleDataListJson(query);
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public String getRoleByRoleID(@PathParam("id")int id) {
		String query = "select * from Role where roleID=" + Integer.toString(id);
		String json = getRoleDataJson(query);
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user_role")
	public String getRoleByUserID(@QueryParam("user_id") int userID) {
		String query = "select roleID, title, description from UserHasRole "
				+ "natural join Role"
				+ " where userID=" + Integer.toString(userID);
		String json = getRoleDataJson(query);
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user_ids_by_role")
	public String getUserIDsWithRoleId(@QueryParam("role_id") int roleID) {
		String query = "select userID from UserHasRole "
				+ "natural join Role natural join User"
				+ " where roleID=" + Integer.toString(roleID);
		
		Connection conn = DatabaseConnection.getConnection();
		
		// Maps from role to userID/username
		List<Integer> users = new ArrayList<Integer>();
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int userID = rs.getInt("userID");
				users.add(userID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			users = null;
		}
		Gson gson = new Gson();
		String json = gson.toJson(users);  
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usernames_by_role")
	public String getUsernamesWithRoleId(@QueryParam("role_id") int roleID) {
		String query = "select username from UserHasRole "
				+ "natural join Role natural join User"
				+ " where roleID=" + Integer.toString(roleID);
		Connection conn = DatabaseConnection.getConnection();
		
		// Maps from role to userID/username
		List<String> users = new ArrayList<String>();
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String username = rs.getString("username");
				users.add(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			users = null;
		}
		Gson gson = new Gson();
		String json = gson.toJson(users);  
		return json;
	}
	
}
