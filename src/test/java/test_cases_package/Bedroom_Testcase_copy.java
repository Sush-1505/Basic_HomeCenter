package test_cases_package;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.homecentre.Base_Test;
import com.homecenter.pages.BasketCartPage;
import com.homecenter.pages.BedroomPage;
import com.homecenter.pages.MattressePage;
import com.homecenter.test_cases.BedroomKingMatressesTestcases;
import com.utils.homecenter.Wait_utils;

public class Bedroom_Testcase_copy extends Base_Test {

private static final Logger log=LogManager.getLogger(BedroomKingMatressesTestcases.class);
	
	@Test(priority = 1)
	public void verifyBedroomModuleVisible() {
		
		BedroomPage bed=new BedroomPage();
		Assert.assertTrue(bed.bedroomVisible(),"Bedroom Menu is not visible on Homepage");
		log.info("✅ Bedroom Menu is Visible");
	}
	
	@Test(priority = 2)
	public static void verifyBedroomSubCatogories() throws InterruptedException {
		BedroomPage bed=new BedroomPage();
		bed.hoverOnBedroom();
		//Thread.sleep(2000);
		
		log.info("✅ Bedroom Subcatogories Product List Size is: "+bed.getListSizeOfBedroomSubcatogories());
		Assert.assertTrue(bed.getListSizeOfBedroomSubcatogories()>0,"❌ No subcategories found under Bedroom menu");
	
	}
	
	@Test(priority = 3)
	public static void verifyMattressesMenuList() throws InterruptedException {
		BedroomPage bed=new BedroomPage();
		bed.hoverOnBedroom();
		//Thread.sleep(2000);
		Assert.assertTrue(bed.getListSizeOfMattresseCatogory()>0, "❌ No products displayed in Mattresses section");
		log.info("✅ Mattresse menu is visible successfully with products subcatogories.");
		bed.getListTextOfMattressesCatogory();
	}
	
	@Test(priority = 4)
	public static void kingMattressesNavigation() throws InterruptedException {
		
		BedroomPage bed=new BedroomPage();
		bed.hoverOnBedroom();
		//Thread.sleep(2000);
		MattressePage mattres=new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		Assert.assertTrue(getDriver().getCurrentUrl().toLowerCase().contains("/bedroom-mattresses-kingmattresses"),
                 "❌ King Mattresses page URL not correct");
        log.info("✅ Navigated to King Mattresses page successfully");
	}

	@Test(priority = 5)
	public static void kingMattressesProduct() throws InterruptedException {
		
		BedroomPage bed=new BedroomPage();
		bed.hoverOnBedroom();
		Thread.sleep(2000);
		MattressePage mattres=new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		//Thread.sleep(2000);
		Wait_utils.waitForPresence(mattres.kingMattresProducts, 20);
		Assert.assertTrue(mattres.getListSizeOfKingmattressesProducts()>0, "❌ No products displayed in King Mattresses category");
		log.info("✅ Total King Mattresse products displayed: " + getListSize(mattres.kingMattresProducts));
        Thread.sleep(2000); 
         
        mattres.getListTextOfKingMattresseProducts();
	}
	
	@Test(priority = 6)
	public static void verifyKingMattresseProducts() throws InterruptedException {
		BedroomPage bed=new BedroomPage();
		bed.hoverOnBedroom();
		//Thread.sleep(2000);
		MattressePage mattres=new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		//Thread.sleep(2000);
		
		mattres.getListTextOfKingMattresseProducts();
		Wait_utils.waitForVisibility(mattres.listItemText, 20);	
		Assert.assertEquals(mattres.getListSizeOfKingmattressesProducts(), getListCountInInteger(mattres.listItemText));
		log.info("✅ Products Verify Succesfully ");

	}
	
	@Test(priority = 7)
	public static void verifyProductDetailsOnPruductPage() throws InterruptedException {
		BedroomPage bed=new BedroomPage();
		bed.hoverOnBedroom();
		//Thread.sleep(2000);
		MattressePage mattres=new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		//Thread.sleep(2000);
		JavascriptExecutor js= (JavascriptExecutor)getDriver();
	    js.executeScript("window.scrollBy(0,250);");
	   // Thread.sleep(2000);
	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
	    WebElement matt=wait.until(ExpectedConditions.visibilityOfElementLocated(mattres.firstProduc));
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});",matt);
	    wait.until(ExpectedConditions.elementToBeClickable(matt));
	    try {
	      matt.click();
	      	} catch (ElementClickInterceptedException e) {
	    // Handle intercepted click with JS fallback
	          log.info("Click intercepted, using JS click...");
	          js.executeScript("arguments[0].click();", matt);
	      	}
	    String productName = matt.getText();
	    log.info("Clicked product: " + productName);
	  //  String title=driver.getTitle();
	    //log.info(title);
	    Assert.assertTrue(getDriver().getTitle().contains("Buy King Size"), "❌ King Mattesses page product details is incorrect");
	    log.info("✅ Navigated to King Mattresses product page successfully");
	        
	}
	
	@Test(priority = 8)
	public static void verifyAddToBasket() throws InterruptedException {
		 BedroomPage bed=new BedroomPage();
			bed.hoverOnBedroom();
			MattressePage mattres=new MattressePage();
			Wait_utils.waitForClickability(mattres.kingMatrres, 20);
			mattres.kingMatrres.click();

			Wait_utils.waitForPresence(mattres.bassketIcon, 20);
			
			int initialCount=mattres.addToBasketInitialCount();
			
			JavascriptExecutor js= (JavascriptExecutor)driver;
		    js.executeScript("window.scrollBy(0,250);");
		    
		    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		    WebElement product=wait.until(ExpectedConditions.visibilityOfElementLocated(mattres.firstProduc));
		    Actions action = new Actions(getDriver());
		    action.moveToElement(product).perform();
		    Wait_utils.waitForClickability(mattres.addToBasketButton, 20);
			mattres.addToBasketButton.click();
			
			getDriver().navigate().refresh();
			Wait_utils.waitForPresence(mattres.bassketIcon,40);
				
			//log.info(mattres.basketIcon.isDisplayed());
			Assert.assertTrue(mattres.basketIcon.isDisplayed(),"Product count is not displayed");
			log.info("Product succesfully added");
			
			int postCount=mattres.addToBasketPostCount();
			log.info(postCount+" Post Count");
			Assert.assertTrue(postCount>initialCount,"Basket Count did not increase");
			log.info("Basket Count increase after adding Item- "+mattres.addToBasketPostCount());
}
	@Test(priority = 9)
	public static void verifyMultipleProductClickabilityForSinglePage() throws InterruptedException {
		 BedroomPage bed=new BedroomPage();
			bed.hoverOnBedroom();
			//Thread.sleep(2000);
			MattressePage mattres=new MattressePage();
			Wait_utils.waitForClickability(mattres.kingMatrres, 20);
			mattres.kingMatrres.click();
			Thread.sleep(2000);
			//JavascriptExecutor js= (JavascriptExecutor)driver;
		    //js.executeScript("window.scrollBy(0,250);");
		    Thread.sleep(2000);
		   // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    //WebElement product=wait.until(ExpectedConditions.visibilityOfElementLocated(mattres.firstProduc));
		    Actions action = new Actions(getDriver());
		   // action.moveToElement(product).perform();
		   // Thread.sleep(2000);
		   // wait_utils.waitForClickability(mattres.addToBasketButton, 20);
			//mattres.addToBasketButton.click();
		//	Thread.sleep(2000);
			//wait_utils.waitForClickability(mattres.cartIcon, 20);
			//mattres.cartIcon.click();
			
		//	By sushil=By.cssSelector("div[id=\"plp-list\"]>div>div");
			List<WebElement>listOfProducts=getDriver().findElements(mattres.kingMattresProducts);
			
			
			int add=0;
			for (WebElement list : listOfProducts) {
				 WebDriverWait wai = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
				    WebElement produc=wai.until(ExpectedConditions.visibilityOf(list));
				    
				    action.moveToElement(produc).perform();
				    Wait_utils.waitForClickability(list, 10);
				    list.click();
				    add++;
				   
			}
			 log.info("Total product Clicable: "+add);
			 
			Assert.assertEquals(add,getListCountInInteger(mattres.listItemText),"All Products are Clickable");
	 }

	
	
	 @Test(priority = 10, description = "Verify addtoBasket page display")
     public void verifyCheckOutNowButtonVisibility() throws InterruptedException {
	    BedroomPage bed=new BedroomPage();
		bed.hoverOnBedroom();
		//Thread.sleep(2000);
		MattressePage mattres=new MattressePage();
	    Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		//Thread.sleep(2000);
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250);");
		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		WebElement product=wait.until(ExpectedConditions.visibilityOfElementLocated(mattres.firstProduc));
		Actions action = new Actions(getDriver());
	    action.moveToElement(product).perform();
		//Thread.sleep(2000);
	    Wait_utils.waitForClickability(mattres.addToBasketButton, 20);
		mattres.addToBasketButton.click();
		//Thread.sleep(2000);
	
		Wait_utils.waitForClickability(mattres.cartIcon, 20);
		mattres.cartIcon.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,400);");
		Thread.sleep(2000);	
		//WebElement element=driver.findElement(By.xpath("//span[text()='Checkout now']"));

		BasketCartPage cart=new BasketCartPage();
    	Wait_utils.waitForVisibility(cart.checkoutNow,10);
		Assert.assertTrue(cart.checkoutNow.isDisplayed(), "'Checkout Now' button not visible — page not displayed properly!");
		log.info(" 'Checkout Now' button visible");
	    //Thread.sleep(2000);
	 }
	 
	 @Test(priority = 11)
	public void verifyCheckoutButtonClickability() throws InterruptedException {
		
		BedroomPage bed=new BedroomPage();
		bed.hoverOnBedroom();
		//Thread.sleep(2000);
		MattressePage mattres=new MattressePage();
	    Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		//Thread.sleep(2000);
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250);");
		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		WebElement product=wait.until(ExpectedConditions.visibilityOfElementLocated(mattres.firstProduc));
		Actions action = new Actions(getDriver());
	    action.moveToElement(product).perform();
		//Thread.sleep(2000);
	    Wait_utils.waitForClickability(mattres.addToBasketButton, 20);
		mattres.addToBasketButton.click();
		//Thread.sleep(2000);
	
		Wait_utils.waitForClickability(mattres.cartIcon, 20);
		mattres.cartIcon.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,400);");
		Thread.sleep(2000);	
	//	WebElement element=driver.findElement(By.xpath("//span[text()='Checkout now']"));

		BasketCartPage cart=new BasketCartPage();

    	Wait_utils.waitForClickability(cart.checkoutNow, 20);	
    	cart.checkoutNow.click();
    	
    	WebElement continues=getDriver().findElement(By.cssSelector("button[id=\"signup-form-submit\"]>span"));
    
    	
    	Assert.assertFalse(continues.isDisplayed(),"Continue Button Is Not Visible");
    	log.info("Continue Button is visible");
		
	}

}
