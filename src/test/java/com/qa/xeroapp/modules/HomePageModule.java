package com.qa.xeroapp.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.xeroapp.testscripts.ReusableMethods;

public class HomePageModule extends ReusableMethods {

	public void clickProfile(WebDriver driver)
	{
		//a[@class='username']
		System.out.println("The driver in homepage is  is " + driver);
		WebElement profilelink = driver.findElement(By.xpath("//a[@class='username']"));
		System.out.println("The link is " + profilelink);
		clickElement(profilelink, "ProfileLink");
		
		
	}
}
