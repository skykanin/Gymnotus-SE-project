package tdt4140.gr1832.app.core;

public class RegisterUser {

	private String name;
	private String username;
	private int age;
	private int sex;
	private String password;
	
	public RegisterUser(){
		
	}
	
	
	public void setName(String name){ //ingen tall
		for (int i=0; i<name.length(); i++) {
			if (Character.isDigit(name.charAt(i))) {
				throw new IllegalArgumentException("Interest rate kan ikke være negativt");
			}
			this.name=name;

		}
	}
	public String getName(String name) {
		this.name=name;
	}
	
	
	
	
	
	
		
		
	
	
	
}
