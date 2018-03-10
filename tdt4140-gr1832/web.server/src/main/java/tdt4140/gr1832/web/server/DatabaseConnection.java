package tdt4140.gr1832.web.server;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseConnection {
   public static final String URL = "jdbc:mysql://localhost/gymnotus";
   private static Connection conn = null;
   
   public static Connection getConnection() {
	   return conn;
   }
   
   public static void connectToDB() {
	   Scanner scanner = null;
	   //Console console = System.console();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			scanner = new Scanner(System.in);

			String username, password;
			System.out.println("Write in the username for the database: ");
			username = scanner.nextLine();
			System.out.println("Write the password: (blank means no password)");
		    password =  scanner.nextLine();//new String(console.readPassword("Please enter your password: "));
		    
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
