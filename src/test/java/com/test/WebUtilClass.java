/**
 * 
 */
package com.test;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author prashant
 *
 */
public class WebUtilClass {

	public static WebDriver getDriverInstance(String projPath) {
		System.setProperty("webdriver.gecko.driver", projPath+File.separator+"resource"+File.separator+"GeckoDriver"+File.separator+"geckodriver");
		WebDriver driver=new FirefoxDriver();
		return driver;
	}
	
	public static boolean verifyLabel(String xpath,WebDriver driver,String strVal) {
		boolean status =true;
		WebElement element=driver.findElement(By.xpath(xpath));
		String availableLabelText=element.getText();
		status=availableLabelText.equals(strVal)?true:false;
		return status;
	}

	public static boolean uploadImg(WebDriver driver, String xpath,String imgPath) {
		boolean status=true;
		try {
			driver.findElement(By.xpath(xpath)).click();
			//put path to your image in a clipboard
		    StringSelection ss = new StringSelection(imgPath);
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		    //imitate mouse events like ENTER, CTRL+C, CTRL+V
		    Robot robot = new Robot();
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			status=false;
			e.printStackTrace();
		}
		
		return status;
	}
}
