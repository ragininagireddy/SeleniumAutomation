package com.qa.xeroapp.testscripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class XeroAppBaseClass {
	
	public static WebDriver driver=null;
	public static WebDriver IntializeDriver(String name){
		//System.setProperty("webdriver.firefox.bin","C:/Program Files/Mozilla Firefox53/firefox.exe");
		if(name.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver","src\\test\\resources\\com\\qa\\xeroapp\\utils\\geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(name.equalsIgnoreCase("chrome")){
			System.out.println("chrome entered");
			System.setProperty("webdriver.chrome.driver","src\\test\\resources\\com\\qa\\xeroapp\\utils\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(name.equalsIgnoreCase("ie")){
			System.out.println("chrome entered");
			System.setProperty("webdriver.ie.driver","src\\test\\resources\\com\\qa\\xeroapp\\utils\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		return driver;
	}
	public static void closeDriver(){
		driver.close();
	}


}
