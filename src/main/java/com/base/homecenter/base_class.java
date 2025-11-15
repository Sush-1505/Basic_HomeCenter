package com.base.homecenter;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class base_class {

	public static RemoteWebDriver driver;
	
	public static void openBrowser(String browsername) {
		
		if(browsername.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			System.out.println("Driver is opened");
		}
		else if(browsername.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
			System.out.println("Driver is opened");
		}
		else if(browsername.equalsIgnoreCase("Safari")) {
			driver=new SafariDriver();
			System.out.println("Driver is opened");
		}
		else {
			throw new Error("Invalid Browser Name "+browsername);
		}
		
	}
	
	public static void openUrl(String url) {
		driver.get(url);
	}
	
	//@BeforeMethod
	public static void launchURL() {
		
		ChromeOptions opt=new ChromeOptions();
		
		opt.addArguments("--disable-notificatoins");
		
		driver=new ChromeDriver(opt);
		
		driver.get("https://www.homecentre.in/");
		
		driver.manage().window().maximize();
		
	}
	
	public static void hoverOnElement(WebElement element) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void clickOnElement(WebElement element) {
		element.click();
	}
	
	@BeforeMethod
	public static void openBrowser() throws InterruptedException {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		
	    driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.homecentre.in/in/en/");
		Thread.sleep(2000);
	}
	
	@AfterMethod
	public void teraDown() {
		driver.quit();
	}
	
	public static int getListSize(By element) {
		List<WebElement>size=driver.findElements(element);
		int sizeCount=size.size();
		System.out.println(sizeCount);
		return sizeCount;
	}
	
	public static int getListCountInInteger(WebElement element) {
		String listCount=element.getText();
		String count=listCount.substring(2,5).trim();
		System.out.println(count);
		int countListNumber=Integer.parseInt(count);
		System.out.println();
		System.out.println(countListNumber);
		return countListNumber;
	}
	
	public static void getProductsListTexts(By element) {
		
		List<WebElement>lists=driver.findElements(element);
		
    for (WebElement list : lists) {
			
			System.out.println(list.getText());
		}
	}
	
	
	
	
}
