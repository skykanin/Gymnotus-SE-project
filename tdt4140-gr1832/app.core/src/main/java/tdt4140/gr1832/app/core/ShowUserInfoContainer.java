package tdt4140.gr1832.app.core;

public class ShowUserInfoContainer {
		private String userType;
		private String name;
		private String username;
		private int age;
		private int gender;
		private String password; 
		private String userID; //lag metoder
		private String email; //lag metoder
		private String phone;
		
		
	
		
		public ShowUserInfoContainer (String username, String password, String name, int age, int gender, String email, String phone ) {
			this.phone = phone;
			this.username =username;
			this.name = name;
			this.password = password;
			this.age = age;
			this.email = email;
			this.gender = gender;
		}

		public String getPhone() {
			return phone;
		}

		
		
	
		public String getBrukerType() {
			return this.userType;
		}

		//name:
		
		public String getName() {
			return this.name;
		}
		

	//username:
		
		
		public String getUsername() {
			return this.username;
		}
		
	
		
		public int getAge() {
			return this.age;
		}
	//gender
		
		public int getGender() {
			return this.gender;
		}
	//password:
		
		
		public String getPassword() {
			return this.password;
			}
	//userID
		
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
