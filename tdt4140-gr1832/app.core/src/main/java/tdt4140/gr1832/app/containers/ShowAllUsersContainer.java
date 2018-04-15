package tdt4140.gr1832.app.containers;

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
	
	public ShowUserInfoContainer getUser(String id) {
		for (ShowUserInfoContainer c : users) {
			if (id.equals(c.getUserID())) {
				return c;
			}
		}
		return null;
	}
}
