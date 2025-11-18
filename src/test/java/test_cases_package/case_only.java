package test_cases_package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.homecentre.Base_Test;

public class case_only extends Base_Test {

	//static RemoteWebDriver driver;
	//@BeforeMethod
	public static void openBrowser() throws InterruptedException {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		
	    driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.homecentre.in/in/en/");
		Thread.sleep(2000);
	}
	
	@Test(priority = 1)
	public void verifyBedroomMenuVisible() {
		
		WebElement bedroom=driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
		Assert.assertFalse(bedroom.isDisplayed(),"Bedroom Menu is not visible on Homepage");
		System.out.println("Bedroom Menu is Visible");
}
//	@AfterMethod
	public void close() {
		driver.quit();
	}
}
