package tdt4140.gr1832.web.server;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseConnection {
   public static final String URL = "jdbc:mysql://localhost/gymnotus?failOverReadOnly=false&maxReconnects=10&autoReconnect=true";
   private static Connection conn = null;
   private static String username, password;
   
   public static Connection getConnection() {
	   return conn;
   }
   
   public static void setConnection(Connection conn) {
	   DatabaseConnection.conn = conn;
   }
   
   public static void executeStatements(final String path, final boolean cleanUp) throws SQLException {
	   Statement dbStatement = conn.createStatement();
	   final StringBuilder buffer = new StringBuilder();
	   	try (Scanner scanner = new Scanner(DatabaseConnection.class.getClassLoader().getResourceAsStream(path))) {
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				final int pos = line.indexOf(";");
				if (pos >= 0) {
					buffer.append(line.substring(0, pos + 1));
					final String sql = buffer.toString();
					buffer.setLength(0);
					if (pos < line.length()) {
						buffer.append(line.substring(pos + 1));
					}
					
					if (cleanUp || (! sql.startsWith("DROP"))) {
						dbStatement.execute(sql);
					}
				} else {
					buffer.append(line);
					buffer.append("\n");
				}
			}
		}
	}
   
   public static void connectToDB() {
	   Scanner scanner = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			scanner = new Scanner(System.in);

			System.out.println("Write in the username for the database: ");
			username = scanner.nextLine();
			System.out.println("Write the password: (blank means no password)");
		    password =  scanner.nextLine();
		    
		    scanner.close();
		
			if(password == "") password = null;
		
		    conn = DriverManager.getConnection(URL, username, password);
		    Statement stmt = conn.createStatement();
		  
		    String sql = "SELECT * FROM User";
		    stmt.executeQuery(sql);
		    System.out.println("Successfully executed a test query.");
		  } 
		  catch (Exception e) {
		     e.printStackTrace();
		  }
	}

   public static void main(String[] args) {
	   connectToDB();
   }
}
