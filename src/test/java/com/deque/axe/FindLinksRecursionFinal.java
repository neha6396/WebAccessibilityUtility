package com.deque.axe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindLinksRecursionFinal {
	public static void main(String[] args) throws Exception {
		System. setProperty("webdriver.chrome.driver", "C:\\Users\\neha gudisagar\\Downloads\\chromedriver_win64\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver","C://installers//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		LinkedHashSet<String> distincturls = new LinkedHashSet();
		LinkedHashSet<String> newdistincturls = new LinkedHashSet();
		String webUrl = null;
		Iterator<String> it = distincturls.iterator();
		driver.manage().window().maximize();
		//String url = "file:C://LinksFolder/Links.html";
		String url="C://Users//neha gudisagar//Desktop//HtmlPages//Links.html";

		// String url = "https://www.capgemini.com";
		distincturls.add(url);
		newdistincturls.add(url);
		it = distincturls.iterator();
		for(int i=0;it.hasNext();i++) {
			url=it.next();
			driver.get(url);
			distincturls.remove(url);
			List<WebElement> webUrls = driver.findElements(By.tagName("a"));
			ArrayList<String> arraylist = new ArrayList<String>();  
			for (int j = 0; j<webUrls.size(); j=j+1){
				webUrl = driver.findElements(By.tagName("a")).get(j).getAttribute("href");
				arraylist.add(webUrl);
				System.out.println(" links url :"+webUrl);  
			}
			newdistincturls.addAll(arraylist);
			distincturls.addAll(arraylist);
			System.out.println("Size"+arraylist.size()+"\n arraylist of all urls :"+arraylist);
			it = distincturls.iterator();
			System.out.println("Size:"+distincturls.size()+"\n"+newdistincturls.size());
		}  
	}
}
