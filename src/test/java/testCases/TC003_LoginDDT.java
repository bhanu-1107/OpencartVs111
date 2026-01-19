package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")// importing data providers class as it is seperate class
	// getting data provider from different class
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		
		logger.info("************* Starting TC003_LoginDDT ************");
		
		try
		{
	        //HomePage
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			//LoginPage
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();
			
			// My Account Page validation
			MyAccountPage mcp=new MyAccountPage(driver);
			boolean targetPage=mcp.isMyAccountPageExists();
			
			/*
			  Data is valid -- login success -- test passed -- logout
			  Data is valid -- login failed - test fail
			  
			  Data is invalid -- login success -- test failed --- logout
			  Data is invalid -- login failed - test passed
			 */
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					mcp.clicklogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			if((exp.equalsIgnoreCase("Invalid"))
)
			{
				if(targetPage==true)
				{
					mcp.clicklogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);

				}
				
			}
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("*********** Finished TC003_LoginDDT ***********");

			
	}
	
}
