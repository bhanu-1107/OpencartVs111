package testCases;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationPage extends BaseClass {

	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("****** Started TC001_AccountRegistrationPage ******");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link----");
		
		hp.clickRegister();
		logger.info("Clicked on Register link----");

		
		AccountRegistrationPage ap=new AccountRegistrationPage(driver);
		logger.info("Providing customer details----");

		
		ap.setFirstName(randomString().toUpperCase());
		ap.setLastName(randomString().toUpperCase());
		//ap.setEmail("john123@gmail.com");// when we execute second time email is already registered
		                                 // so we need to generate random mails here
		ap.setEmail(randomString()+"@gmail.com");// randomly generated email
		
		
		ap.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();// because password should be same in both cases so storing password
		
		ap.setPassword(password);
		ap.setConfirmPassword(password);
		
		ap.setPrivacyPolicy();
		ap.clickContinue();
		
		logger.info("Validating expect message----");
		String confrmmas=ap.getConfirmationMsg();
		if(confrmmas.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed-----");
			logger.debug("Debug logs----");
			Assert.assertTrue(false);

		}
		//Assert.assertEquals(confrmmas, "Your Account Has Been Created!@");//@ so catch block executes
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("****** Finished TC001_AccountRegistrationPage ******");

	}
	
	
}
