package tdt4140.gr1832.app.core;

import java.util.ArrayList;
import java.util.List;

public class ShowAllUsersContainer {
	
	private List<ShowUserInfoContainer> users = new ArrayList<>();
	
	
	
	public void addUserInfo(ShowUserInfoContainer userInfo) {
		users.add(userInfo);
	}
	
	public List<ShowUserInfoContainer> getUsers() {
		return users;
	}
}
