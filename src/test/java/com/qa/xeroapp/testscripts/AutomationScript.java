package com.qa.xeroapp.testscripts;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.xeroapp.modules.FreeTrialModule;
import com.qa.xeroapp.modules.HomePageModule;
import com.qa.xeroapp.modules.LoginXeroAppModule;

public class AutomationScript extends ReusableMethods {
	static HomePageModule homePageModuleobj = null;
	
	
    @Test
	public static void navigateXeroApp() throws InterruptedException, IOException {
		
        Properties pro=new Properties();
		FileInputStream fp = new FileInputStream("C:\\\\RagsSeleniumTraining\\\\XeroAppAutomation\\\\src\\\\test\\\\resources\\\\com\\\\qa\\\\xeroapp\\\\config\\\\config.properties");
		
	     System.out.println("Loading properties file");	
		pro.load(fp);
		ExtentTest logger;
		
		initializeExtentReport("navigateXeroApp");
		logger=createTestScriptReport("navigateXeroApp");
		
		IntializeDriver("chrome");
		driver.get(pro.getProperty("URL"));
		
		//logger.log(Status.INFO,"salesforce page opened");
		
		System.out.println("Calling methods of loginxeroappmodule");
		LoginXeroAppModule ob=new LoginXeroAppModule();
		
		driver=ob.loginXeroApp(driver);
		String userName = pro.getProperty("username");
		String pwd = pro.getProperty("password");
		homePageModuleobj=ob.loginDetails(driver,userName,pwd);
		
		
		
		endExtentReport();
		closeDriver();
	}
    
	@Test
	public static void check_HomePage_links() throws InterruptedException, IOException
	{
		Properties pro=new Properties();
		FileInputStream fp = new FileInputStream("C:\\\\RagsSeleniumTraining\\\\XeroAppAutomation\\\\src\\\\test\\\\resources\\\\com\\\\qa\\\\xeroapp\\\\config\\\\config.properties");

	    System.out.println("Loading properties file");	
		pro.load(fp);
		
        ExtentTest logger;
		
		initializeExtentReport("check_HomePage_links");
		logger=createTestScriptReport("check_HomePage_links");
		
		IntializeDriver("chrome");
		driver.get(pro.getProperty("URL"));
		
		System.out.println("Calling methods of loginxeroappmodule");
		LoginXeroAppModule ob=new LoginXeroAppModule();
		
		driver=ob.loginXeroApp(driver);
		String userName = pro.getProperty("username");
		String pwd = pro.getProperty("password");
		homePageModuleobj=ob.loginDetails(driver,userName,pwd);
		
		/* code to check all tabs in home page to be written*/
		
		

		
	}
	
	@Test
	public void incorrect_password() throws IOException
	{
		Properties pro=new Properties();
		FileInputStream fp = new FileInputStream("C:\\\\RagsSeleniumTraining\\\\XeroAppAutomation\\\\src\\\\test\\\\resources\\\\com\\\\qa\\\\xeroapp\\\\config\\\\config.properties");
		
	    System.out.println("Loading properties file in incorrect pws");	
		pro.load(fp);
		ExtentTest logger;
		
		initializeExtentReport("incorrectPassword");
		logger=createTestScriptReport("incorrectPassword");
		
		
		IntializeDriver("chrome");
		driver.get(pro.getProperty("URL"));
		
		//logger.log(Status.INFO,"salesforce page opened");
		
		System.out.println("Calling methods of loginxeroappmodule");
		
		LoginXeroAppModule ob=new LoginXeroAppModule();
		driver=ob.loginXeroApp(driver);
		
		String userName = pro.getProperty("username");
		String pwd = pro.getProperty("incorrectpassword");
		
		homePageModuleobj=ob.loginDetails(driver,userName,pwd);
		
		String expectedText = pro.getProperty("incorrectpwdtext");
		//String errormsg = driver.findElement(By.xpath("//div[@class='x-boxed warning error-message']/ul/li")).getText();
		WebElement errorElement=driver.findElement(By.cssSelector("div.x-boxed p"));
		//System.out.println("The error message is"+ errormsg);
		verifyText(errorElement, "Invalid Password", expectedText);
		//homePageModuleobj=ob.loginwithIncorrectpwd(driver,userName,pwd);
	}
	
	
	@Test
	public void forgot_password() throws IOException
	{
		Properties pro=new Properties();
		FileInputStream fp = new FileInputStream("C:\\\\RagsSeleniumTraining\\\\XeroAppAutomation\\\\src\\\\test\\\\resources\\\\com\\\\qa\\\\xeroapp\\\\config\\\\config.properties");
		
	    System.out.println("Loading properties file in incorrect pws");	
		pro.load(fp);
		ExtentTest logger;
		
		initializeExtentReport("forgotPassword");
		logger=createTestScriptReport("forgotPassword");
		
		
		IntializeDriver("chrome");
		driver.get(pro.getProperty("URL"));
		
		//logger.log(Status.INFO,"salesforce page opened");
		
		System.out.println("Calling methods of loginxeroappmodule");
		
		LoginXeroAppModule ob=new LoginXeroAppModule();
		driver=ob.loginXeroApp(driver);
		
		String userName = pro.getProperty("username");
	
		driver=ob.forgotPassword(driver, userName);
	
		
		String expectedText = pro.getProperty("forgotpwdText")+"\n"+userName;
		System.out.println(expectedText);
		
		//String errormsg = driver.findElement(By.xpath("//div[@class='x-boxed warning error-message']/ul/li")).getText();
		WebElement errorElement=driver.findElement(By.cssSelector("div.x-boxed p"));
		System.out.println("The error message is"+ errorElement.getText());
		verifyText(errorElement, "Link to rest pwd", expectedText);
		//homePageModuleobj=ob.loginwithIncorrectpwd(driver,userName,pwd);
	}
	
	@Test
	public void sign_upto_XDC_freetrial() throws IOException,InterruptedException
	{
		Properties pro=new Properties();
		FileInputStream fp = new FileInputStream("C:\\\\RagsSeleniumTraining\\\\XeroAppAutomation\\\\src\\\\test\\\\resources\\\\com\\\\qa\\\\xeroapp\\\\config\\\\config.properties");
		
	    System.out.println("Loading properties file in incorrect pws");	
		pro.load(fp);
		ExtentTest logger;
		
		initializeExtentReport("sign_upto_XDC_freetrial");
		logger=createTestScriptReport("sign_upto_XDC_freetrial");
		
		
		IntializeDriver("chrome");
		driver.get(pro.getProperty("URL"));
		FreeTrialModule freetrialObj = new FreeTrialModule();
		driver=freetrialObj.clickFreeTrial(driver);
	}
}
