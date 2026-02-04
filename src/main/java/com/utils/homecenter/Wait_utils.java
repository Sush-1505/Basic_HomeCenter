package com.utils.homecenter;
import static com.base.homecentre.Base_Test.*;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait_utils {

	public static void waitForVisibility(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickability(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPresence(By element, int timeoutInSeconds) {
    	
    	
        new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated((element)));
    }
    
    public static void visibilityOfElementLocated(By element,int timeoutInSeconds) {
    	new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds))
    	.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    
    public static String xpathText(WebElement element) {


		String x=element.toString();
		System.out.println(x);
		String []y=x.split(":", x.length());
		System.out.println(y);
		String s=y[2].toString().trim();
		int lengt=s.length();
		String subString=s.substring(0, lengt-1);
		System.out.println(subString);
		return subString;
	}

}
