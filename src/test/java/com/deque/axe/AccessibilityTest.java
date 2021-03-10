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

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccessibilityTest {
	@Rule
	public TestName testName = new TestName();

	private WebDriver driver;
	AXE axe=new AXE();
	GenerateHTMLReport report=new GenerateHTMLReport();
	String testname="Accessibility";
	String suiteName="Suite1";
	String URL="https://www.hsbc.co.in/credit-cards/products/visa-cashback/";
	
	private static final URL scriptUrl = AccessibilityTest.class.getResource("/axe.min.js");

	@BeforeClass
	public static void setProperty() {
		System. setProperty("webdriver.chrome.driver", "C:\\Users\\neha gudisagar\\Downloads\\chromedriver_win32_2\\chromedriver.exe");

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
	public void testAccessibility() {
	
		
		driver.get(URL);
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testname, responseJSON);
			GenerateHTMLReport report=new GenerateHTMLReport();
			try {
			FileWriter out=report.initHTMLReport();
			report.startHTML(out, suiteName);
			report.generateDetailViolationReport(testname,violations, URL);
			report.endHTML();
			}catch(IOException e) {
				e.printStackTrace();
			}
			AssertJUnit.assertTrue(AXE.report(violations), true); 
			}
	}

	
	


	/**
	 * Test with skip frames
	 */
	
	@Test
	public void testAccessibilityWithSkipFrames() {
		driver.get(URL);
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.skipFrames()
				.analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);
			AssertJUnit.assertTrue(AXE.report(violations), true);
		}
	}

	/**
	 * Test with options
	 */
	@Test
	public void testAccessibilityWithOptions() {
		driver.get(URL);
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.options("{ rules: { 'accesskeys': { enabled: false } } }")
				.analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testName.getMethodName(), responseJSON);

			AssertJUnit.assertTrue(AXE.report(violations), true);
		}
	}

	//@Test
	public void testCustomTimeout() {
		driver.get(URL);

		boolean didTimeout = false;
		try {
			new AXE.Builder(driver, AccessibilityTest.class.getResource("/timeout.js"))
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
	@Test
	public void testAccessibilityWithSelector() {
		driver.get(URL);
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.include("title")
				.include("p")
				.analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testname, responseJSON);
			GenerateHTMLReport report=new GenerateHTMLReport();
			try {
			FileWriter out=report.initHTMLReport();
			report.startHTML(out, suiteName);
			report.generateDetailViolationReport(testname,violations, URL);
			report.endHTML();
			}catch(IOException e) {
				e.printStackTrace();
			}
			AssertJUnit.assertTrue(AXE.report(violations), true); 
			}
	}

	/**
	 * Test includes and excludes
	 */
	@Test
	public void testAccessibilityWithIncludesAndExcludes() {
		driver.get(URL);
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.include("div")
				.exclude("h1")
				.analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testname, responseJSON);
			GenerateHTMLReport report=new GenerateHTMLReport();
			try {
			FileWriter out=report.initHTMLReport();
			report.startHTML(out, suiteName);
			report.generateDetailViolationReport(testname,violations, URL);
			report.endHTML();
			}catch(IOException e) {
				e.printStackTrace();
			}
			AssertJUnit.assertTrue(AXE.report(violations), true); 
			}
	}

	/**
	 * Test a WebElement
	 */
	@Test
	public void testAccessibilityWithWebElement() {
		driver.get(URL);

		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.analyze(driver.findElement(By.tagName("p")));

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			AssertJUnit.assertTrue("No violations found", true);
		} else {
			AXE.writeResults(testname, responseJSON);
			GenerateHTMLReport report=new GenerateHTMLReport();
			try {
			FileWriter out=report.initHTMLReport();
			report.startHTML(out, suiteName);
			report.generateDetailViolationReport(testname,violations, URL);
			report.endHTML();
			}catch(IOException e) {
				e.printStackTrace();
			}
			AssertJUnit.assertTrue(AXE.report(violations), true); 
			}
	}

	/**
	 * Test a page with Shadow DOM violations
	 */
	@Test
	public void testAccessibilityWithShadowElement() {
		driver.get(URL);

		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		JSONArray nodes = ((JSONObject)violations.get(0)).getJSONArray("nodes");
		JSONArray target = ((JSONObject)nodes.get(0)).getJSONArray("target");

		if (violations.length() == 1) {
//			assertTrue(AXE.report(violations), true);
			AssertJUnit.assertEquals(String.valueOf(target), "[[\"#upside-down\",\"ul\"]]");
		} else {
			AXE.writeResults(testname, responseJSON);
			GenerateHTMLReport report=new GenerateHTMLReport();
			try {
			FileWriter out=report.initHTMLReport();
			report.startHTML(out, suiteName);
			report.generateDetailViolationReport(testname,violations, URL);
			report.endHTML();
			}catch(IOException e) {
				e.printStackTrace();
			}
			AssertJUnit.assertTrue(AXE.report(violations), true); 
			}
	}

	@Test
	public void testAxeErrorHandling() {
		driver.get(URL);

		URL errorScript = AccessibilityTest.class.getResource("/axe-error.js");
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