package tdt4140.gr1832.app.core;

public class User {
	private String userType;
	private String name;
	private String username;
	private int age;
	private int gender;
	private String password; 
	private String userID; //lag metoder
	private String email; //lag metoder
	private String phone;

	public User () {

	}

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


	public void setPhone(String phoneNumber) {
		this.phone = phoneNumber;
	}


	//email
	public void setEmail(String email) {
		if (email == null)
		{
			this.email = null;
			return;
		}
		//Fullfort metoden - johan
		this.email = email;
//	String[] liste= {"ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw"};
//	String[] splitMail=email.split("\\.");
//	if (splitMail.length !=3) {
//		throw new IllegalArgumentException("Invalid email");
		
	//}
	String[] splitLastName=email.split("@");

	//System.out.println(splitLastName.length);
	if (splitLastName.length !=2) {
		throw new IllegalArgumentException("Invalid email ");
		
	}
	else {
		this.email=email;
	}
	
	
//			//sjekker landskoder:
//	for (int k=0;k<liste.length;k++) {
//		
//		if (splitMail[2].equals(liste[k])) {
//			this.email= email;
//			return;
//		}
		
	//}
	}
	
	
	public void setUserType(String brukerType){ //ingen tall
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

	//name:
	public void setName(String name) { //ingen tall
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
	
			//AGE
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
//gender
	public void setGender(int gender) {	
		//0 mann
		//1 kvinne
		//2 ukjent
		
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
//userID
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
