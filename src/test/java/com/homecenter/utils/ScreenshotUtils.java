package com.homecenter.utils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ScreenshotUtils  {


	static RemoteWebDriver driver;
    public static String captureScreenshot(String testName) {
	
		String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String path=System.getProperty("user.dir")+"/screenshots/"+testName+"_"+timestamp+".png";
		
		try {
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File(path));
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Screenshot Capture Failed "+e.getMessage());
		}
	
		return path;
	}


}
