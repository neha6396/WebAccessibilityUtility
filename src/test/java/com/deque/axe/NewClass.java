package com.deque.axe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewClass {
	
	
	public static void main(String args[]) throws Exception {

	System.setProperty("webdriver.chrome.driver", "C:\\Users\\neha gudisagar\\Downloads\\chromedriver_win64\\chromedriver.exe");
	 WebDriver driver = new ChromeDriver();

	driver.get("C:\\Users\\neha gudisagar\\Desktop\\HtmlPages\\Links.html");

	
	Set<String> finaldistincturls = new HashSet<String>();
	Set<String> newdistincturls = new HashSet<String>();

	
	finaldistincturls.add("C:\\Users\\neha gudisagar\\Desktop\\HtmlPages\\Links.html");
	Set<String> distincturls = new HashSet<String>();
	distincturls=GetAllLinks(driver);
	finaldistincturls.addAll(distincturls);
	System.out.println("Final Distinct Url after removed wrong\n"+finaldistincturls);

//
//	Iterator<String> it = distincturls.iterator();
////	Iterator<String> itn = distincturls.iterator();
////
////	Integer size =distincturls.size();
////	System.out.println(size);
//	do{ 
//	while(it.hasNext()) {
//	String value = it.next();
//	 
//	driver.get(value);
//	newdistincturls=GetAllLinks(); 
//	 
//	finaldistincturls.addAll(newdistincturls);
//	}
//	}while(distincturls!=null);
	}
	
	public static Set<String> GetAllLinks(WebDriver driver) throws Exception {
		 
		ArrayList<String> arraylist = new ArrayList<String>();  

//		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
//		System.out.println("list size :"+allLinks.size());  
//
//		for (int i = 0; i<allLinks.size(); i=i+1){
//		String link = allLinks.get(i).getAttribute("href");
//		arraylist.add(link); 
//		 
//		// System.out.println("Text to which links are present :"+allLinks.get(i).getText());
//		System.out.println(" links url :"+link); 
//		}
		String newurl=null;
		List<WebElement> allLinks0 = driver.findElements(By.tagName("a"));
		String link;
		do{
			link = allLinks0.get(0).getAttribute("href");
			driver.get(link);
		}while(link!=null); 
//			if(link != null) {
//				List<WebElement> allLinks1 = driver.findElements(By.tagName("a"));
//				newurl = allLinks1.get(0).getAttribute("href");
//				driver.get(newurl);
//				}
//				for (int j = 0; j<allLinks1.size(); j=j+1){
//					driver.navigate().refresh();
//				String link2 = allLinks1.get(0).getAttribute("href");
//				}
				
			
	
//			arraylist.add(link); 
			
//		System.out.println("Size"+arraylist.size()+"\n arraylist of all urls :"+arraylist);
//		Set<String> distincturls = new LinkedHashSet<String>(arraylist);
//		System.out.println("Size"+distincturls.size()+"\n SYSOUT - distincturls : " + distincturls+"\n");

		
		Set<String> distincturls = null;
		return distincturls;
		
	}
}
