package tdt4140.gr1832.web.dao.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoleTest {
	private Role role;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		role = new Role();
		
		role.setRoleID(1);
		role.setTitle("test");
		role.setDescription("test");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetter() {
		Assert.assertEquals(1, (int)role.getRoleID());
		Assert.assertEquals("test", (String)role.getTitle());
		Assert.assertEquals("test", (String)role.getDescription());
	}
}
