package test_cases_package;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Screenshot_element {

	public static void main(String[] args) throws InterruptedException, IOException {

		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		RemoteWebDriver driver=new ChromeDriver(options);
		driver.get("https://www.homecentre.in/in/en/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		WebElement element=driver.findElement(By.xpath("//input[@placeholder=\"What are you looking for?\"]"));
	
        File source=((TakesScreenshot)element).getScreenshotAs(OutputType.FILE);
		
		File store=new File("D:\\A new Eclipse Project\\HomeCenter\\Screenshots\\home.jpg");
		
		FileHandler.copy(source, store);
		
		driver.quit();
		
	}

}
