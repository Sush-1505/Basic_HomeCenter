package test_cases_package;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.utils.homecenter.Wait_utils;

public class only_testcases {

	public static RemoteWebDriver driver;
	
	@BeforeMethod
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
		Assert.assertTrue(bedroom.isDisplayed(),"Bedroom Menu is not visible on Homepage");
		System.out.println("Bedroom Menu is Visible");
}
	
	@Test(priority = 2)
	public static void verifyBedroomSUbCatogories() throws InterruptedException {
		
		WebElement bedroom=driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
		Actions act=new Actions(driver);
		act.moveToElement(bedroom).perform();
		Thread.sleep(2000);
		List<WebElement> subcategories = driver.findElements(By.cssSelector("div[id=\"category-menu-1\"]>div"));
		System.out.println(subcategories.size());
        Assert.assertTrue(subcategories.size() > 0, "❌ No subcategories found under Bedroom menu");

        System.out.println("✅ Subcategories under Bedroom menu:");
        for (WebElement cat : subcategories) {
            System.out.println(" - " + cat.getText());
        }
	}    
	
	@Test(priority = 3)
	public static void verifyMattressesNavigation() throws InterruptedException {
		WebElement bedroom=driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
		Actions act=new Actions(driver);
		act.moveToElement(bedroom).perform();
		Thread.sleep(2000);
		  List <WebElement> mattressesSize = driver.findElements(By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div"));
	        System.out.println(mattressesSize.size());
          Assert.assertTrue(mattressesSize.size() > 0, "❌ No products displayed in Mattresses section");
          System.out.println("✅ Beds page opened successfully with products listed.");
          
          for (WebElement mattressSize : mattressesSize) {
			System.out.println(" - "+mattressSize.getText());
		}

	}
	
	@Test(priority = 4)
	public static void mattressesNavigation() throws InterruptedException {
		WebElement bedroom=driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
		Actions act=new Actions(driver);
		act.moveToElement(bedroom).perform();
		Thread.sleep(2000);
		
		WebElement kingMattresses=driver.findElement(By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]"));
		  Wait_utils.waitForClickability(kingMattresses, 20);
		  kingMattresses.click();
		
          Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("/bedroom-mattresses-kingmattresses"),
                  "❌ King Mattresses page URL not correct");
          System.out.println("✅ Navigated to King Mattresses page successfully");

	}
	@Test(priority = 5)
	public static void kingMattressesProduct() throws InterruptedException {
		WebElement bedroom=driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
		Actions act=new Actions(driver);
		act.moveToElement(bedroom).perform();
		Thread.sleep(2000);
		
		WebElement kingMattresses=driver.findElement(By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]"));
		  Wait_utils.waitForClickability(kingMattresses, 20);
		  kingMattresses.click();
		  Thread.sleep(2000);
		  List<WebElement> productList = driver.findElements(By.cssSelector("div[id=\"plp-list\"]>div>div"));
          Assert.assertTrue(productList.size() > 0, "❌ No products displayed in King Mattresses category");
          System.out.println("✅ Total King Mattresse products displayed: " + productList.size());
          Thread.sleep(2000); 
          
          for (WebElement listProducts : productList) {
			System.out.println(" - "+listProducts.getText());
		}

	}
	
	@Test(priority = 6)
	public static void verifyProductDetailNavigation() throws InterruptedException {
		
		WebElement bedroom=driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
		Actions act=new Actions(driver);
		act.moveToElement(bedroom).perform();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]")).click();
		Thread.sleep(2000);
		
		List<WebElement>list=driver.findElements(By.cssSelector("div[id=\"plp-list\"]>div>div"));
		for (WebElement elements : list) {
			
			System.out.println(elements.getText()+"   ");
		}
		
		int totalItems=list.size();
		System.out.println();
		System.out.println(totalItems+"  Size of list");
		
	    WebElement listItemText=driver.findElement(By.cssSelector("div[id=\"list-sec\"]>div>div:nth-child(4)>div>div>div>div>p"));
		System.out.println(listItemText.getText());
		
		String listCount=listItemText.getText();
		String count=listCount.substring(2,5).trim();
		System.out.println(count);
		
		int countList=Integer.parseInt(count);
		
		System.out.println();
		System.out.println(countList);
		
		Assert.assertEquals(totalItems, countList);

	}
      
	@Test(priority = 7)
	public static void verifyProductDetailsOnPruductPage() throws InterruptedException {
		WebElement bedroom=driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
		Actions act=new Actions(driver);
		act.moveToElement(bedroom).perform();
		Thread.sleep(2000); 
		WebElement kingMattresses=driver.findElement(By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]"));
		Wait_utils.waitForClickability(kingMattresses, 20);
		kingMattresses.click();
		Thread.sleep(2000);
	   // WebElement firstProduct=driver.findElement(By.cssSelector("div[id=\"plp-list\"]>div>div>div>div:nth-child(3)>a[aria-label=\"Restomax Elite 6+2 Inches Pocket Spring Memory Foam King Mattress with Box Top, 180x195cm - Grey\"]"));
	    JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,250);");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By firstProduct = By.cssSelector("div[id=\"plp-list\"]>div>div>div>div:nth-child(3)>a[aria-label=\"Restomax Elite 6+2 Inches Pocket Spring Memory Foam King Mattress with Box Top, 180x195cm - Grey\"]");
        WebElement mattress = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});",mattress);
        wait.until(ExpectedConditions.elementToBeClickable(mattress));
        try {
      	    mattress.click();
      	} catch (ElementClickInterceptedException e) {
      	    // Handle intercepted click with JS fallback
      	    System.out.println("Click intercepted, using JS click...");
      	    js.executeScript("arguments[0].click();", mattress);
      	}
        String productName = mattress.getText();
        System.out.println("Clicked product: " + productName);
        
        String title=driver.getTitle();
        System.out.println(title);
        
        Assert.assertTrue(driver.getTitle().contains("Buy King Size"),
                "❌ King Mattesses page product details is incorrect");
        System.out.println("✅ Navigated to King Mattresses product page successfully");

	}
	
	@Test(priority = 8)
	public static void verifyAddToBasket() throws InterruptedException {
		  WebElement kitchenMenu = driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
          Actions act = new Actions(driver);
		  act.moveToElement(kitchenMenu).perform();
          Thread.sleep(3000);
  		  WebElement kingMattresses=driver.findElement(By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]"));
  		  kingMattresses.click();
          Thread.sleep(4000); 
          JavascriptExecutor js= (JavascriptExecutor)driver;
          js.executeScript("window.scrollBy(0,350);");
          Thread.sleep(2000);
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          By firstProduct = By.cssSelector("div[id=\"plp-list\"]>div>div>div>div:nth-child(3)>a[aria-label=\"Restomax Elite 6+2 Inches Pocket Spring Memory Foam King Mattress with Box Top, 180x195cm - Grey\"]");
          WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
          Actions action = new Actions(driver);
		  action.moveToElement(product).perform();
          Thread.sleep(3000);
          driver.findElement(By.cssSelector("div[class=\"root-product-form\"]>div+div>button")).click();
          Thread.sleep(2000);
          
          driver.findElement(By.cssSelector("div[id=\"root-nav-mini-basket\"]>div button[aria-label=\"cart-icon-1\"]")).click();
          Thread.sleep(2000);
              
	}
	
	 @Test(priority = 9, description = "Verify addtoBasket page display")
     public void verifyaddToBasketPage() throws InterruptedException {
		 WebElement kitchenMenu = driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
         Actions act = new Actions(driver);
		  act.moveToElement(kitchenMenu).perform();
         Thread.sleep(3000);
 		  WebElement kingMattresses=driver.findElement(By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]"));
 		  kingMattresses.click();
         Thread.sleep(4000); 
         JavascriptExecutor js= (JavascriptExecutor)driver;
         js.executeScript("window.scrollBy(0,350);");
         Thread.sleep(2000);
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         By firstProduct = By.cssSelector("div[id=\"plp-list\"]>div>div>div>div:nth-child(3)>a[aria-label=\"Restomax Elite 6+2 Inches Pocket Spring Memory Foam King Mattress with Box Top, 180x195cm - Grey\"]");
         WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
         Actions action = new Actions(driver);
		  action.moveToElement(product).perform();
         Thread.sleep(3000);
         driver.findElement(By.cssSelector("div[class=\"root-product-form\"]>div+div>button")).click();
         Thread.sleep(2000);
         
         driver.findElement(By.cssSelector("div[id=\"root-nav-mini-basket\"]>div button[aria-label=\"cart-icon-1\"]")).click();
         Thread.sleep(2000);
         js.executeScript("window.scrollBy(0,400);");
         Thread.sleep(2000); 
         WebElement checkoutNow = driver.findElement(By.xpath("//span[text()=\"Checkout now\"]"));
         // Verify it is displayed
         Assert.assertTrue(checkoutNow.isDisplayed(), "'Checkout Now' button not visible — page not displayed properly!");
         System.out.println(" 'Checkout Now' button visible");
         Thread.sleep(2000);

	 }

	 @Test(priority = 10, description = "Verify user can navigate to checkout page by clicking Checkout Now button")
     public void verifyCheckoutButton() throws InterruptedException {
		 WebElement kitchenMenu = driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
         Actions act = new Actions(driver);
		 act.moveToElement(kitchenMenu).perform();
         Thread.sleep(3000);
 		 WebElement kingMattresses=driver.findElement(By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]"));
 		 kingMattresses.click();
         Thread.sleep(4000); 
         JavascriptExecutor js= (JavascriptExecutor)driver;
         js.executeScript("window.scrollBy(0,350);");
         Thread.sleep(2000);
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         By firstProduct = By.cssSelector("div[id=\"plp-list\"]>div>div>div>div:nth-child(3)>a[aria-label=\"Restomax Elite 6+2 Inches Pocket Spring Memory Foam King Mattress with Box Top, 180x195cm - Grey\"]");
         WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
         Actions action = new Actions(driver);
		 action.moveToElement(product).perform();
         Thread.sleep(3000);
         driver.findElement(By.cssSelector("div[class=\"root-product-form\"]>div+div>button")).click();
         Thread.sleep(2000);
         
         driver.findElement(By.cssSelector("div[id=\"root-nav-mini-basket\"]>div button[aria-label=\"cart-icon-1\"]")).click();
         Thread.sleep(2000);
         js.executeScript("window.scrollBy(0,400);");
         Thread.sleep(2000); 
         WebElement checkoutNow = driver.findElement(By.xpath("//span[text()=\"Checkout now\"]"));
         checkoutNow.click();
         Thread.sleep(2000);
         String currentUrl = driver.getCurrentUrl();
         
         Assert.assertTrue(currentUrl.contains("cart"),
                "❌ User was not redirected to the checkout page");

         System.out.println("✅ Checkout Now button navigated successfully to: " + currentUrl);
         
         driver.findElement(By.cssSelector("button[id=\"signup-close\"]")).click();

		 
	 }
	 
	 @Test(priority = 11)
		public static void homeCentre() throws InterruptedException {
			
			WebElement bedroom=driver.findElement(By.cssSelector("div[id=\"dept-bedroom\"]"));
			Actions act=new Actions(driver);
			act.moveToElement(bedroom).perform();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]")).click();
			Thread.sleep(2000);
			
			List<WebElement>list=driver.findElements(By.cssSelector("div[id=\"plp-list\"]>div>div"));
			for (WebElement elements : list) {
				
				System.out.println(elements.getText()+"   ");
			}
			
			int totalItems=list.size();
			System.out.println();
			System.out.println(totalItems+"  Size of list");
			
		    WebElement listItemText=driver.findElement(By.cssSelector("div[id=\"list-sec\"]>div>div:nth-child(4)>div>div>div>div>p"));
			System.out.println(listItemText.getText());
			
			String listCount=listItemText.getText();
			String count=listCount.substring(2,5).trim();
			System.out.println(count);
			
			int countList=Integer.parseInt(count);
			
			System.out.println();
			System.out.println(countList);
			
			Assert.assertEquals(totalItems, countList);
		

	
	 }
	
	@AfterMethod
	public void teraDown() {
		driver.quit();
	}
}
