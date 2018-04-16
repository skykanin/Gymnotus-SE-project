package tdt4140.gr1832.app.core;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class User {
	private String userType;
	private String name;
	private String username;
	private int age;
	private int gender;
	private String password; 
	private String userID;
	private String email; 
	private String phone;

	public User (String username, String password, String name,
				 int age, int gender, String email, String phone ) {
		this.setPhone(phone);
		this.setUsername(username);
		this.setName(name);
		this.setPassword(password);
		this.setAge(age);
		this.setEmail(email);
		this.setGender(gender);
	}

	public String getPhone() {
		return phone;
	}
	
	public User() {
		
	}
	
	public void setPhone(String phoneNumber) {
		int lengde = phoneNumber.length();
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			throw new IllegalArgumentException("Feltet for telefonnummer mangler");
		}
		
		for ( int i=0; i<lengde;i++) {
			char c= phoneNumber.charAt(i);
			if (!(Character.isDigit(c))) {
				throw new IllegalArgumentException("Telefonnummer ma inneholde tall.");
			}
		}
		if(lengde != 8) {
			throw new IllegalArgumentException("Ugyldig antall sifre i telefonnummer");
		}
		this.phone = phoneNumber;
	}
	
	public boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	public void setEmail(String email) {
		if (isValidEmailAddress(email) == false) {
			throw new IllegalArgumentException("Ugyldig email");
		}
		String[] splitLastName=email.split("@");

		if (splitLastName.length !=2) {
			throw new IllegalArgumentException("Ugyldig email");
		}
		else {
			this.email=email;
		}
	}
	
	
	public void setUserType(String brukerType) {
		if (brukerType.equals("PT")) {
			this.userType="PT";	
		}
		
		if (brukerType.equals("bruker")) {
			this.userType="bruker";
		}
	}
	public String getBrukerType() {
		return this.userType;
	}

	public void setName(String name) {
		String comparatorString = name.replaceAll("[^a-zA-Z������\\s]", "");
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Feltet for navn mangler.");
		}
		if (!name.equals(comparatorString)) {
			throw new IllegalArgumentException("Navn kan kun inneholde bokstaver");
		}
		this.name=name;
	
	}
	public String getName() {
		return this.name;
	}

	public void setUsername(String username) {
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("Fyll inn feltet for brukernavn.");
		}
		for (int i = 0; i < username.length(); i++) {
	        if (Character.isWhitespace(username.charAt(i))) {
				throw new IllegalArgumentException("Brukernavn kan ikke inneholde mellomrom");
	        }
	        else {
	        	this.username=username;
	        }
		 }
	}
	public String getUsername() {
		return this.username;
	}

	public void setAge(int age) {	
		String stringAge=Integer.toString(age);
		if (stringAge.length() != (2)) {
			throw new IllegalArgumentException("age must be a 1 or 2- digit number");
		}
		
		this.age=age;
		}
	
	public int getAge() {
		return this.age;
	}
	
	public void setGender(int gender) {	
		//0 = mann, 1 = kvinne, 2 = ukjent
		
		if (gender == 0) {
			this.gender=0;
		}
		else if (gender == 1) {
			this.gender=1;
		}
		else if (gender ==2) {
			this.gender=2;
		}
		else {
			throw new IllegalArgumentException("gender must be either 0, 1, 2");
		}
	}
	public int getGender() {
		return this.gender;
	}
	
	public void setPassword(String password) {			
		if (password.length() <= 5) {
			throw new IllegalArgumentException("Passordet ma inneholde seks eller flere karakterer.");
		}
		else {
			this.password=password;
		}
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setUserID(String userID) {			
		this.userID=userID;	
	}
	public String getUserID() {
		return this.userID;
	}
	public String getEmail() {
		return this.email;
	}
	public String toString() {
		return (this.name + this.username + this.age + this.gender + this.password);
	}
}
