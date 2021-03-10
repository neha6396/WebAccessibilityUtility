package com.deque.axe;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Automation {

	@Rule
	public TestName testName = new TestName();

	private WebDriver driver;

	private static final URL scriptUrl = ExampleTest.class.getResource("/axe.min.js");

	@BeforeClass
	public static void setProperty() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\neha gudisagar\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");

	}

	/**
	 * Instantiate the WebDriver and navigate to the test site
	 */
	
	
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("pageLoadStrategy", "normal");
		
		final ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("enable-automation");
		options.addArguments("--headless");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-extensions");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("--disable-gpu");
		options.addArguments("disable-features=NetworkService");
		options.addArguments("--always-authorize-plugins");
		options.merge(caps);

		//options.addArguments("enable-features=NetworkServiceInProcess");

		
		// ChromeDriver needed to test for Shadow DOM testing support
		driver = new ChromeDriver(options);
	}

	/**
	 * Ensure we close the WebDriver after finishing
	 */
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void Automation() {
		List<String> actual_links= new ArrayList<String>();
		List<String> distinct_wrong_urls= new ArrayList<String>();;
		Set<String> distinct_urls=null;
		driver.get("https://www.hsbc.co.in/ways-to-bank/online-banking/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(int i=1;i<links.size();i++) {
			String actual_link=links.get(i).getAttribute("href");
			actual_links.add(actual_link);
		}
		distinct_urls=new HashSet<String>(actual_links);
		Iterator<String> it=distinct_urls.iterator();

		System.out.println("size ActualLinks"+actual_links.size()+"\n ActualLinks URLs"+actual_links);

		System.out.println("size Distinct URL"+distinct_urls.size()+"\n Distinct URLs"+distinct_urls);
		if(distinct_urls!=null) {
			it=distinct_urls.iterator();
			if(it.hasNext()) {
				String url=(String) it.next();
				if(!url.contains("http://www.")||(!url.contains("https://www."))) {
					distinct_wrong_urls.add(url);
				}
			}
		}
		System.out.println("Distinct wrong urls :"+distinct_wrong_urls);
		 List<String> final_url_list = new ArrayList<String>();
			Iterator<String> it1=distinct_urls.iterator();

		  boolean match = false;

		    for (String secondItem : distinct_wrong_urls) {
		        for (String firstItem : distinct_urls) {
		            if(secondItem.contains(firstItem)) {
		                match = true;
		            }
		            else {
		            	match=false;
		            }
		            if(!match) {
			        	final_url_list.add(firstItem);
		        }
		        
		        }
		    }
		    
		System.out.println("size of Final Distinct URL after filter"+final_url_list.size()+"\n Distinct URLs after filter"+final_url_list);
		for(int i=0;i<final_url_list.size();i++) {
			if(it1.hasNext()) {
				String url=(String) it1.next();
				driver.get(url);

				
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);
			AssertJUnit.assertTrue(AXE.report(violations), false);
		}
	}
}
	}

}
