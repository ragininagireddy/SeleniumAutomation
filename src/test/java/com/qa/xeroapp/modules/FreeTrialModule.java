package com.qa.xeroapp.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.xeroapp.testscripts.ReusableMethods;

public class FreeTrialModule extends ReusableMethods {

	public WebDriver clickFreeTrial(WebDriver driver) throws InterruptedException
	{
		WebElement freetrialLink = driver.findElement(By.partialLinkText("Free trial"));
	//	System.err.println("The webelement found is" + freetrialLink);
		clickElement(freetrialLink, "Free Trail Link");
		
		WebElement firstNmae = driver.findElement(By.name("FirstName"));
		enterText(firstNmae, "FirstName", "Ragini");
		
		WebElement lastNmae = driver.findElement(By.name("LastName"));
		enterText(lastNmae, "lasttName", "Nagireddy");
		
		WebElement emailaddress = driver.findElement(By.name("EmailAddress"));
		enterText(emailaddress, "emaila ddress", "ragininagireddy10W@gmail.com");
		
		WebElement phone = driver.findElement(By.name("PhoneNumber"));
		enterText(phone, "phone", "377 222 2121");
		
		//WebElement country = driver.findElement(By.name("EmailAddress"));
		Select country = new Select(driver.findElement(By.name("LocationCode")));
		country.selectByVisibleText("India");
		
		WebElement tansc=driver.findElement(By.name("TermsAccepted"));
		clickElement(tansc, " terms and conditions");
		//button[@type='submit']
		
	   
     //  WebElement robotCheck=driver.findElement(By.cssSelector("div.recaptcha-checkbox-checkmark"));
		//body[@class='xero is-live-mode'
		WebElement robotCheck=driver.findElement(By.xpath("//body[@class='xero is-live-mode'"));
		
       clickElement(robotCheck, "I am not a robot");
       
       Thread.sleep(3000);
       
       WebElement getStarted = driver.findElement(By.xpath("//button[@type='submit']"));
        clickElement(getStarted, "Get Started");
       
       return driver;
	}
}
