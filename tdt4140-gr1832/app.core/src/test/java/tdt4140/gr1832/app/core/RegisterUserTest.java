package tdt4140.gr1832.app.core;

import org.junit.Test;

import junit.framework.Assert;

public class RegisterUserTest {
	RegisterUser ru = new RegisterUser();

	@Test
	public void testRegisterUser() {
		ru.setTest(true);
		User user = new User("test", "test12345", "test", 18, 1, "test@test.com", "12345678");
		ru.registerUser(user);
		Assert.assertEquals(400, (int)ru.getResponse().getStatus());
	}
}
