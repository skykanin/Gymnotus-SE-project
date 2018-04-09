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
		private boolean isAnonymous;
		private boolean shareHealthData;
		private boolean shareExerciseData;
		private boolean isTrainer;
		
		public ShowUserInfoContainer(String username, String password, String name, int age, int gender, String email, String phone,
				boolean isAnonymous, boolean shareHealthData, boolean shareExerciseData, boolean isTrainer) {
			this.phone = phone;
			this.name = name;
			this.password = password;
			this.age = age;
			this.email = email;
			this.gender = gender;
			this.isAnonymous = isAnonymous;
			this.username = username;
			this.shareHealthData = shareHealthData;
			this.shareExerciseData = shareExerciseData;
			this.isTrainer = isTrainer;
			if(this.isAnonymous) {
				this.name = "Anonym#" + this.userID;
				this.phone = "Brukeren er anonym";
				this.email = "Brukeren er anonym";
				this.username = "Brukeren er anonym";
			}
		}

		public void setUserId(String id) {
				this.userID = id;
		}
		public String getPhone() {
			return phone;
		}

		public String getBrukerType() {
			return this.userType;
		}

		public boolean getIsTrainer() {
			return isTrainer;
		}
		
		public String getName() {
			return this.name;
		}
		

	//username:
		
		
		public void setUsername(String username) {
			this.username = username;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

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
		

		public boolean getIsAnonymous() {
			return isAnonymous;
		}

		public void setAnonymous(boolean anonymous) {
			isAnonymous = anonymous;
		}

		public boolean getShareHealthData() {
			return shareHealthData;
		}

		public void setShareHealthData(boolean shareHealthData) {
			this.shareHealthData = shareHealthData;
		}

		public boolean getShareExerciseData() {
			return shareExerciseData;
		}

		public void setShareExerciseData(boolean shareExerciseData) {
			this.shareExerciseData = shareExerciseData;
		}
}
