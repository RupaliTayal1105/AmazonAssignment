package core;

import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;


public class TestReporter {
	
	public static void log(String msg) {
		Reporter.log(msg);
		Config.getLogger().info(msg);
		Config.test.log(Status.INFO, msg);
	}
	
	public static void logWithScreenShot(String msg) {
		Reporter.log(msg);
		Config.getLogger().info(msg);
		String image=Config.test.addScreenCapture(Support.getScreenshot(msg));
		Config.test.log(Status.INFO, msg, image);
	}
	
	public static void AssertTrueWithScreenshot(boolean condition,String msg) {
		try {
			
			Assert.assertTrue(condition, msg + "Failed");
			Config.test.log(Status.PASS, msg + "Passed", Support.getScreenshot(msg));
		} catch (Exception e) {
			String image=Config.test.addScreenCapture(Support.getScreenshot(msg));
			Config.test.log(Status.FAIL, msg + "Failed", image);
		}	
	}
}