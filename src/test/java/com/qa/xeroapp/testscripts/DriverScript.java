package com.qa.xeroapp.testscripts;

import java.lang.reflect.Method;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DriverScript extends AutomationScript{
	
	public static void main(String[] args) throws Exception{
		System.out.println("In Driver Script");
		String cur_dir=System.getProperty("user.dir");
		String dt_Path = cur_dir+"\\src\\test\\resources\\com\\qa\\xeroapp\\utils\\TestSuite.xls";
		String testCase =null;
		String flag = null;
		
		initializeExtentReport("salesforceTestscriptsReport");
		String[][] TestSuitedata = readxlData(dt_Path,"Sheet1");
		for(int i = 1; i< TestSuitedata.length; i++) {
			flag = TestSuitedata[i][1];
			if(flag.equalsIgnoreCase("Y")) {
					testCase = TestSuitedata[i][0];
					System.out.println("The test case executed is" + testCase);
					Method testScript = AutomationScript.class.getMethod(testCase);
					testScript.invoke(testScript);
					
				}else {
					System.out.println("**********Row  number "+ i+" test case Name "+ TestSuitedata[i][0]+" is not Executed*********");
				}
				
			
			
			
		}
		
		endExtentReport();
	}

}
