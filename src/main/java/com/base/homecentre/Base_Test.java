package com.base.homecentre;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.utils.homecenter.Wait_utils;


public class Base_Test {

	public static RemoteWebDriver driver;	
	private static final Logger log=LogManager.getLogger(Base_Test.class);
	
	@Parameters("Browser Name")
	@BeforeMethod
	public static void openBrowser(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--disable-notifications");
		    driver=new ChromeDriver(options);
		    driver.manage().window().maximize();
			driver.get("https://www.homecentre.in/in/en/");
//			try {
//				Thread.sleep(4000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			WebElement element=driver.findElement(By.cssSelector("button[id=\"moe-dontallow_button\"]"));
//			
//			element.click();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//FirefoxOptions options=new FirefoxOptions();
			//options.addArguments("--disable-notifications");
		    driver=new FirefoxDriver();
		    driver.manage().window().maximize();
			driver.get("https://www.homecentre.in/in/en/");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement element=driver.findElement(By.cssSelector("button[id=\"moe-dontallow_button\"]"));
			
			element.click();
		}
		
		else {
			throw new Error("Invalid Browser Name "+browserName);
		}
		
	}
	
	public static void openUrl(String url) {
		driver.get(url);
	}
	
//	@BeforeMethod
	public static void launchURL() throws InterruptedException {
		
		ChromeOptions opt=new ChromeOptions();
		
		opt.addArguments("--disable-notificatoins");
		
		driver=new ChromeDriver(opt);
		
		driver.get("https://www.homecentre.in/");
		
		driver.manage().window().maximize();
		
		Thread.sleep(2000);
		WebElement element=driver.findElement(By.cssSelector("button[id=\"moe-dontallow_button\"]"));
		
		element.click();

		
	}
	
	public static void hoverOnElement(WebElement element) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	public static void clickOnElement(WebElement element) {
		element.click();
	}
	
//	@BeforeMethod
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
		//log.info(sizeCount);
		return sizeCount;
	}
	
	public static int getListCountInInteger(WebElement element) {
		String listCount=element.getText();
		String count=listCount.substring(2,5).trim();
	//	log.info(count);
		int countListNumber=Integer.parseInt(count);
		//log.info(countListNumber);
		return countListNumber;
	}
	
	public static void getProductsListTexts(By element) {
		
		List<WebElement>lists=driver.findElements(element);
		
    for (WebElement list : lists) {
			
			log.info(list.getText());
		}
	}
	
	public static void captureScreenshot(String fileName) {
		TakesScreenshot screenShot=(TakesScreenshot) driver;
		File sourceFile=screenShot.getScreenshotAs(OutputType.FILE);
		
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		File destFile=new File("./Screenshots/"+timestamp+fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("Screenshot saved Sussecfully");
	}
	
	
		}


