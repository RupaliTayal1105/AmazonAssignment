package test;

import org.testng.annotations.Test;
import core.BaseTestCase;
import core.FileInput;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchResultPage;
import pages.SignInPage;;

public class AmazonTest extends BaseTestCase {
	
	FileInput files= new FileInput();
	
	@Test
	public void Test1() throws Exception {
		
		SignInPage signInObj = new SignInPage();
		signInObj.verifySignInPageDisplayed();
		signInObj.clickSignInButton();
		
		LoginPage loginPage = new LoginPage();
		loginPage.verifyLogInPageDisplayed();
		loginPage.userLogIn(); //Calling login method

		HomePage homePage = new HomePage();
		homePage.verifyHomePageDisplayed();
		homePage.EnterKeywordAndSearchItem(); //Method to enter search keyword and search
		
		SearchResultPage searchResult = new SearchResultPage();
		searchResult.verifySearchResultPageDisplayed();
		String expectedItemName = searchResult.getItemName();

		ProductDetailsPage productDetailsPage = new ProductDetailsPage();
		productDetailsPage.verifyProductDetailsPageDisplayed();
		productDetailsPage.clickBuyNowButton();
		
		CheckoutPage checkOutPage = new CheckoutPage();
		checkOutPage.verifyCheckOutPaymentsPageDisplayed();
		checkOutPage.clickNetBankingRadioButton();
		checkOutPage.selectBankName();
		checkOutPage.clickContinueButton();
		
		String actualItemName = checkOutPage.getItemNameOnCheckOut();
		checkOutPage.compareItemNames(actualItemName, expectedItemName);
	}
}
