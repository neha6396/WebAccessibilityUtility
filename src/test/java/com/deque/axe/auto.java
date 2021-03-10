package com.deque.axe;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class auto {

	public static void main(String args[]) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("automationName","Android Emulator");
		capabilities.setCapability("deviceName","Pixel 2 API 29");
		capabilities.setCapability("platformVersion", "10.0");        
		capabilities.setCapability("platformName", "Android"); 
		capabilities.setCapability("appPackage", "org.wikipedia.alpha");       
		capabilities.setCapability("appActivity","org.wikipedia.main.MainActivity");
		
		//AppiumDriver driver = new AndroidD
		//AppiumDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);

	System.setProperty("webdriver.chrome.driver", "C:\\Users\\neha gudisagar\\Downloads\\chromedriver_win64\\chromedriver.exe");
	//driver.get("C://Users//neha gudisagar//Desktop//HtmlPages//Links.html");
	}
}
