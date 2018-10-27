package com.qa.xeroapp.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReusableMethods extends XeroAppBaseClass {
	
	public static ExtentHtmlReporter htmlReporter;
	 public static ExtentReports extent=null;
	 public static ExtentTest logger=null;
	 
	 
	 public static void enterText (WebElement webElement,String webElementName,String text)
		{
			if (webElement.isDisplayed())
			{		
				webElement.sendKeys(text);
				logger.log(Status.PASS,text+" entered in " +webElementName);		
			}
			else
				logger.log(Status.FAIL, MarkupHelper.createLabel(webElementName+" Text box not found ", ExtentColor.RED));


		}
	 
	 public static void clickElement (WebElement element, String elementName)
		{
			if (element.isDisplayed())
			{
				logger.log(Status.PASS,elementName+" is clicked");
				element.click();
			}
			else
				logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+" is not found", ExtentColor.RED));
		}
	 
	 public static void verifyText(WebElement element,String elementName,String expectedText ) throws IOException
		{
			if (element.isDisplayed())
			{	
				if (element.getText().equals(expectedText))
					logger.log(Status.PASS, MarkupHelper.createLabel( elementName+" is displayed as expected", ExtentColor.GREEN));
				else{
					logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+"is NOT as expected", ExtentColor.RED));
				String imagePath=takeScreenShot();
				logger.addScreenCaptureFromPath(imagePath);
				}
			}
			else
			{
				logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+" not found", ExtentColor.RED));
			}
		}



	 
	public static String takeScreenShot() throws IOException{
		String reportPath=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String curDir=System.getProperty("user.dir");
		String destination=curDir+"/TestReports/screenshots/"+reportPath+"image.PNG";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(destination),true);
		return destination;
	}
	public static void initializeExtentReport(String reportName){
		 String reportPath=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/TestReports/"+reportPath+reportName+".html");
		 System.out.println("Report file path is" + System.getProperty("user.dir") +"/TestReports/"+reportPath+reportName+".html");
		 extent = new ExtentReports ();
		 extent.attachReporter(htmlReporter);
		 extent.setSystemInfo("Host Name", "TekArch");
		 extent.setSystemInfo("Environment", "QA Automation");
		 extent.setSystemInfo("User Name", "Divyashree");
		 
		 htmlReporter.config().setDocumentTitle("My Simple report");
		 htmlReporter.config().setReportName("First execution");
		 htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		 htmlReporter.config().setTheme(Theme.STANDARD);
	}
	public static ExtentTest createTestScriptReport(String testScriptName){
		logger = extent.createTest(testScriptName);
		return logger;
	}
	public static void endExtentReport(){
		extent.flush();
	}
	
	public static String[][] readxlData(String path,String sheetName) throws IOException{
		
		FileInputStream fs=new FileInputStream(new File(path));
		HSSFWorkbook workbook=new HSSFWorkbook(fs);
		HSSFSheet sheet=workbook.getSheet(sheetName);
		int rows=sheet.getLastRowNum()+1;
		int cols=sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rows][cols];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				//data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				HSSFCell cell=sheet.getRow(i).getCell(j);
				if(cell.getCellType()==CellType.STRING)
					data[i][j]=cell.getStringCellValue();
				else if(cell.getCellType()==CellType.NUMERIC)
					data[i][j]=String.valueOf(cell.getNumericCellValue());
			
			}
		}
		return data;
		
	}


}
