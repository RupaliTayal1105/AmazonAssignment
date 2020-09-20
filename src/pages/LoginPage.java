package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import core.BaseTestPage;
import core.FileInput;
import core.TestReporter;
import io.appium.java_client.MobileElement;

public class LoginPage extends BaseTestPage{
	FileInput file=new FileInput();
	WebDriverWait wait=new WebDriverWait(driver, 20);
	
	@FindBy(xpath = "//*[@resource-id=\"ap_email_login\"]")
    private MobileElement textBoxMobileNumber;
	
	@FindBy(xpath = "//*[@resource-id=\"ap_password\"]")
    private MobileElement textBoxPassword;
	
	@FindBy(xpath = "//*[@resource-id=\"signInSubmit\"]")
    private MobileElement buttonLogin;
	
	@FindBy(xpath = "//*[@resource-id=\"continue\"]")
    private MobileElement buttonContinue;
	
	@Override
	public void waitForPageToLoad() {
		wait.until(ExpectedConditions.visibilityOf(buttonContinue));
	}

	/**
	 * Verify LogIn In page loaded
	 */
	public LoginPage verifyLogInPageDisplayed() {
		
		try {
			waitForPageToLoad();
			TestReporter.AssertTrueWithScreenshot(textBoxMobileNumber.isDisplayed(),"Verify Login page is loaded");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load LogIn Page");
		}
		return this;
	}
	
	/**
	 * User login
	 */
	public LoginPage userLogIn() {
		try {
			String username = file.Username(); 
			String password = file.Password(); 
			signIn(username, password); 
			Assert.assertTrue(true,"Login Successful");
		} catch (Exception e) {
			Assert.fail("Sign in failed");
		}
		return this;
	}	
	

	public void signIn(String mobile_no,String password) {
		try {
					textBoxMobileNumber.sendKeys(mobile_no);
			buttonContinue.click();
			wait.until(ExpectedConditions.visibilityOf(textBoxPassword));
			textBoxPassword.sendKeys(password);
			TestReporter.logWithScreenShot("Before Login");
			buttonLogin.click();
			Reporter.log("Successfully entered login details and clicked Login button");
		} catch (Exception e) {
			Assert.fail("Failed to enter login details and Continue");
		}
	}
}
