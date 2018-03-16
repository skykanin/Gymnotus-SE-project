package tdt4140.gr1832.web.dao.data;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HealthDataReportTest {
	
	private HealthDataReport report;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		report = new HealthDataReport();
		
		report.setBloodPressure(1);
		report.setDailySteps(1);
		report.setRestingHeartRate(1);
		report.setDate(new Date(2018,1,1));
		report.setHeight(1);
		report.setWeight(1);
		report.setUserID(1);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetter() {
		Assert.assertEquals(1, report.getBloodPressure());
		Assert.assertEquals(1, report.getDailySteps());
		Assert.assertEquals(1, report.getRestingHeartRate());
		Assert.assertEquals(new Date(2018,1,1), report.getDate());
		Assert.assertEquals(1, report.getHeight());
		Assert.assertEquals(1, report.getWeight());
		Assert.assertEquals(1, report.getUserID());
	}
}
