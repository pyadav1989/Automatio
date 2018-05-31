package com.test;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Unit test for simple App.
 */
public class AppTest {
	Properties properties;
	WebDriver driver;
	ExtentReports extent;
	ExtentTest logger;
	String projPath;
	String resultUrl;
	@BeforeSuite
	public void beforeSuite() {
		projPath=System.getProperty("user.dir");
		//get config file
		properties=CommonUtilClass.getPropFile(projPath);
		System.out.println("URL:-"+properties.getProperty("URL"));
		//get driver instance
		driver=WebUtilClass.getDriverInstance(projPath);
		//initiate report
		resultUrl=projPath+File.separator+"TestReport.html";
		extent = new ExtentReports(resultUrl, true);
	}
 
	@Test
	public void TestLabel() {
		boolean status=true;
		logger=extent.startTest("TestLabel on image portal");
		String appUrl=properties.getProperty("URL");
		logger.log(LogStatus.INFO, "Loading XPATH and values");
		String labelXpath=properties.getProperty("LabelXPATH");
		String labelVal=properties.getProperty("LabelValue");
		logger.log(LogStatus.INFO, "Navigate to app URL");
		driver.navigate().to(appUrl);
		logger.log(LogStatus.INFO, "Verifying label");
		status=WebUtilClass.verifyLabel(labelXpath, driver, labelVal);
		assertTrue(status);
	}
	
	@Test
	public void testImageUpload() {
		boolean status=true;
		logger=extent.startTest("Test image uploader");
		String appUrl=properties.getProperty("URL");
		logger.log(LogStatus.INFO, "Loading XPATH and Image path");
		String image=projPath+File.separator+"Upload.png";
		String xpath=properties.getProperty("PlusIconXpath");
		logger.log(LogStatus.INFO, "Navigate to app URL");
		driver.navigate().to(appUrl);
		logger.log(LogStatus.INFO, "Uploading image");
		status=WebUtilClass.uploadImg(driver,xpath,image);
		assertTrue(status);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS,"Test Passed");
		}else {
			logger.log(LogStatus.FAIL,"Test Failed");
		}
		
		extent.endTest(logger);
	}
	
	@AfterSuite
	public void afterSuite() {
	
		extent.flush();
		extent.close();
		driver.quit();
	}
}
