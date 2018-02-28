package tdt4140.gr1832.app.core;

public class RegisterUser {

	private String name;
	private String username;
	private int age;
	private int gender;
	private String password;
	
	public RegisterUser(){
		
	}
	
	
	public void setName(String name){ //ingen tall
		for (int i=0; i<name.length(); i++) {
			if (Character.isDigit(name.charAt(i))) {
				throw new IllegalArgumentException("Name cannot contain any digits");
			}
			this.name=name;

		}
	}
	public String getName() {
		return this.name;
	}
	public String toString() {
		return this.name;
	}

//username:
	
	public void setUsername(String username) {
		 for (int i = 0; i < username.length(); i++) {
		        if (Character.isWhitespace(username.charAt(i))) {
					throw new IllegalArgumentException("Username cannot have spaces");
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
		int lengde=stringAge.length();
		if (stringAge.length() < (1) || stringAge.length() >(2)) {
			throw new IllegalArgumentException("age must be a 1 or 2- digit number");
		}
		for ( int i=0; i<lengde;i++) {
			char c= stringAge.charAt(i);
			if (!(Character.isDigit(c))) {
				throw new IllegalArgumentException("age can only consist of digits");
			}
			}
		this.age=age;
		}
	
	public int getAge() {
		return this.age;
	}
	public void setGender(int gender) {	
		//0 mann
		//1 kvinne
		//2 ukjent
		
		if (gender == 0) {
			this.gender=0;
		}
		if (gender == 1) {
			this.gender=1;
		}
		if (gender ==2) {
			this.gender=2;
		}
		else {
			throw new IllegalArgumentException("gender must be either 0, 1, 2");
		}
	}
	public int getGender() {
		return this.gender;
	}
	//password:
	public void setPassword(String password) {			
		if (password.length() <= 5) {
			throw new IllegalArgumentException("password must be consist of 6 or more characters.");
		}
		else {
			this.password=password;
		}
		
	}
	
	public String getPassword() {
		return this.password;
	}
	
	
public static void main(String[] args) {
	RegisterUser user1 = new RegisterUser();
	//user1.setName("per9");
	//user1.getName();
	//System.out.println(user1.toString());
	user1.setAge(55);
	System.out.println(user1.getAge());
	user1.setPassword("jajjajaj");
	System.out.println(user1.getPassword());


	}



}
	
	
	
	
	

		
		
	
	
	

