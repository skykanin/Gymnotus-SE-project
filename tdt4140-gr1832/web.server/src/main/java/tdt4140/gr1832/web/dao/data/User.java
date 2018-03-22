package tdt4140.gr1832.web.dao.data;

public class User {
	Integer userID;
	String username;
	String name;
	String email;
	String phone;
	Integer gender;
	Integer age;
	Boolean isAnonymous;
	Boolean shareExerciseData;
	Boolean shareHealthData;
	Boolean isTrainer;
	
	public Boolean getIsTrainer() {
		return isTrainer;
	}

	public void setIsTrainer(Boolean isTrainer) {
		this.isTrainer = isTrainer;
	}

	public User() {
		
	}
	
	public User(int userID, String username, String name, String email,
				String phone, int gender, int age)
	{
		this.userID = userID;
		this.username = username;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.age = age;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public Boolean getShareExerciseData() {
		return shareExerciseData;
	}

	public void setShareExerciseData(Boolean shareExerciseData) {
		this.shareExerciseData = shareExerciseData;
	}

	public Boolean getShareHealthData() {
		return shareHealthData;
	}

	public void setShareHealthData(Boolean shareHealthData) {
		this.shareHealthData = shareHealthData;
	}
}
