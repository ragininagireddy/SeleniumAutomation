package com.qa.xeroapp.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.xeroapp.testscripts.ReusableMethods;

public class LoginXeroAppModule extends ReusableMethods{
	
	HomePageModule homePageModuleobj = null;
	//WebDriver driver = null;
	public WebDriver loginXeroApp(WebDriver driver)
	{
		System.out.println("In loginXeroApp");
		WebElement userName = driver.findElement(By.linkText("Login"));
		System.out.println("The webelement found is" + userName.toString());
		//userName.click();
		clickElement(userName, "LoginButton");
		return driver;
	}
	
	public HomePageModule loginDetails(WebDriver driver,String userName,String pwd)
	{
		WebElement email=driver.findElement(By.name("userName"));
		WebElement password=driver.findElement(By.name("password"));
	    WebElement loginBtn = driver.findElement(By.id("submitButton"));
		
		enterText(email, "emailid",userName );
		enterText(password, "Password", pwd);
		clickElement(loginBtn, "LoginButton");
		homePageModuleobj = new HomePageModule();
		return homePageModuleobj;
	}
/*public HomePageModule loginwithIncorrectpwd(WebDriver driver,String userName,String pwd)
{
	WebElement email=driver.findElement(By.name("userName"));
	WebElement password=driver.findElement(By.name("password"));
    WebElement loginBtn = driver.findElement(By.id("submitButton"));
	
	enterText(email, "emailid",userName );
	enterText(password, "InCorrectPassword", pwd);
	clickElement(loginBtn, "LoginButton");
	//*[@id="contentTop"]/div[2]/div[1]/div[2]/ul/li[1]/text()
	homePageModuleobj = new HomePageModule();
	return homePageModuleobj;
}*/
	
	public WebDriver forgotPassword(WebDriver driver,String userName)
	{
		WebElement username=driver.findElement(By.name("userName"));
		
	    WebElement loginBtn = driver.findElement(By.id("submitButton"));
		
		enterText(username, "emailid",userName );
		//clickElement(loginBtn, "LoginButton");
		WebElement forgotPwdLink=driver.findElement(By.linkText("Forgot your password?"));
		clickElement(forgotPwdLink, "Forgot Password");
		
		WebElement email=driver.findElement(By.cssSelector("#UserName"));
		enterText(email, "UserName", userName);
		
		WebElement sendLink=driver.findElement(By.xpath("//a/span[@class='text']"));
		clickElement(sendLink, "Send Link");
		return driver;
	}

}
