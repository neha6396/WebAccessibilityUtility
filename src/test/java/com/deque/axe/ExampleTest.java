/**
 * Copyright (C) 2015 Deque Systems Inc.,
 *
 * Your use of this Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * This entire copyright notice must appear in every copy of this file you
 * distribute or in any file that contains substantial portions of this source
 * code.
 */

package com.deque.axe;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ExampleTest {
	@Rule
	public TestName testName = new TestName();

	private WebDriver driver;

	private static final URL scriptUrl = ExampleTest.class.getResource("/axe.min.js");

	@BeforeClass
	public static void setProperty() {
		System. setProperty("webdriver.chrome.driver", "C:\\Users\\neha gudisagar\\Downloads\\chromedriver_win32_1\\chromedriver.exe");

	}

	/**
	 * Instantiate the WebDriver and navigate to the test site
	 */
	
	
	@BeforeMethod
	public void setUp() {
		// ChromeDriver needed to test for Shadow DOM testing support
		driver = new ChromeDriver();
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
/*		List<String> actual_links= new ArrayList<String>();
		List<String> distinct_wrong_urls= new ArrayList<String>();;
		Set<String> distinct_urls=null;*/
		driver.get("https://www.kas.ind.in");
		/*List<WebElement> links = driver.findElements(By.tagName("a"));
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

*/				
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);
			AssertJUnit.assertTrue(AXE.report(violations), false);
		}
	}

	
	
	/**
	 * Basic test
	 */
	//@Test
	public void testAccessibility() {
		driver.get("https://www.kas.ind.in");
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);
			AssertJUnit.assertTrue(AXE.report(violations), false);
		}
	}

	/**
	 * Test with skip frames
	 */
	
	//@Test
	public void testAccessibilityWithSkipFrames() {
		driver.get("https://in.bookmyshow.com");
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.skipFrames()
				.analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);
			AssertJUnit.assertTrue(AXE.report(violations), false);
		}
	}

	/**
	 * Test with options
	 */
	//@Test
	
	public void testAccessibilityWithOptions() {
		driver.get("https://in.bookmyshow.com");
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.options("{ rules: { 'accesskeys': { enabled: false } } }")
				.analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);

			AssertJUnit.assertTrue(AXE.report(violations), false);
		}
	}

	//@Test
	
	public void testCustomTimeout() {
		driver.get("https://in.bookmyshow.com");

		boolean didTimeout = false;
		try {
			new AXE.Builder(driver, ExampleTest.class.getResource("/timeout.js"))
				.setTimeout(1)
				.analyze();
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg.indexOf("1 seconds") == -1) {
				AssertJUnit.assertTrue("Did not error with timeout message", msg.indexOf("1 seconds") != -1);
			}
			didTimeout = true;
		}

		AssertJUnit.assertTrue("Did set custom timeout", didTimeout);
	}

	/**
	 * Test a specific selector or selectors
	 */
	//@Test
	
	public void testAccessibilityWithSelector() {
		driver.get("https://in.bookmyshow.com");
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.include("title")
				.include("p")
				.analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);

			AssertJUnit.assertTrue(AXE.report(violations), false);
		}
	}

	/**
	 * Test includes and excludes
	 */
	//@Test
	
	public void testAccessibilityWithIncludesAndExcludes() {
		driver.get("https://in.bookmyshow.com");
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.include("div")
				.exclude("h1")
				.analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);
			AssertJUnit.assertTrue(AXE.report(violations), false);
		}
	}

	/**
	 * Test a WebElement
	 */
	//@Test
	
	public void testAccessibilityWithWebElement() {
		driver.get("https://in.bookmyshow.com");

		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.analyze(driver.findElement(By.tagName("p")));

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);
			AssertJUnit.assertTrue(AXE.report(violations), false);
		}
	}

	/**
	 * Test a page with Shadow DOM violations
	 */
	//@Test
	
	public void testAccessibilityWithShadowElement() {
		driver.get("https://in.bookmyshow.com/shadow-error.html");

		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		JSONArray nodes = ((JSONObject)violations.get(0)).getJSONArray("nodes");
		JSONArray target = ((JSONObject)nodes.get(0)).getJSONArray("target");

		if (violations.length() == 1) {
//			assertTrue(AXE.report(violations), true);
			AssertJUnit.assertEquals(String.valueOf(target), "[[\"#upside-down\",\"ul\"]]");
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);
			AssertJUnit.assertTrue("No violations found", false);

		}
	}

	//@Test
	
	public void testAxeErrorHandling() {
		driver.get("https://in.bookmyshow.com");

		URL errorScript = ExampleTest.class.getResource("/axe-error.js");
		AXE.Builder builder = new AXE.Builder(driver, errorScript);

		boolean didError = false;

		try {
			builder.analyze();
		} catch (AXE.AxeRuntimeException e) {
			AssertJUnit.assertEquals(e.getMessage(), "boom!"); // See axe-error.js
			didError = true;
		}

		AssertJUnit.assertTrue("Did raise axe-core error", didError);
	}
}