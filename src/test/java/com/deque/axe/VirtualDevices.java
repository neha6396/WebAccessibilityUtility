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


import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class VirtualDevices {
	public static void main(String args[]) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("automationName","UiAutomator2");
		capabilities.setCapability("deviceName","Pixel 2 API 29");
		capabilities.setCapability("platformVersion", "10.0");        
		capabilities.setCapability("platformName", "Android"); 
		capabilities.setCapability("appPackage", "org.wikipedia.alpha");       
		capabilities.setCapability("appActivity","org.wikipedia.main.MainActivity");
		
		AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		

	}
}