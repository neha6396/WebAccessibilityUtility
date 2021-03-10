package com.deque.axe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VirtualDeviceTest {
	static List<String> final_links= new ArrayList<String>();
	public static void main(String args[]) throws Exception {
		System.setProperty("webdriver.chrome.driver", "F:\\priyanka\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String url="C:\\Users\\neha gudisagar\\Desktop\\HtmlPages\\Links.html";
		List<String> final_links=GetFirstFlow(driver,url);
		Set<String> distinct_urls=null;
		distinct_urls=new HashSet<String>(final_links);
		System.out.println(distinct_urls);
	}
	public static List<String> GetFirstFlow(WebDriver driver,String url) throws Exception {
		driver.get(url);
		List<String> actual_links= new ArrayList<String>();
		List<WebElement> links = driver.findElements(By.tagName("a"));
		if(links.size()==0) {
			return final_links;
		}else {
			for(int i=0;i<links.size();i++) {
				String actual_link=links.get(i).getAttribute("href");
				actual_links.add(actual_link);
				final_links.add(actual_link);
				System.out.println(final_links);
				actual_links=GetFirstFlow(driver, actual_link);
				System.out.println(actual_links);

			}
			return final_links;
		}
	}
}




