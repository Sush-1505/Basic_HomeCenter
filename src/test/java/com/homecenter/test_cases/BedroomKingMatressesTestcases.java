package com.homecenter.test_cases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.homecentre.Base_Test;
import com.homecenter.pages.BasketCartPage;
import com.homecenter.pages.BedroomPage;
import com.homecenter.pages.MattressePage;
import com.homecenter.utils.Data_Provider;
import com.utils.homecenter.Wait_utils;

public class BedroomKingMatressesTestcases extends Base_Test {

	private static final Logger log = LogManager.getLogger(BedroomKingMatressesTestcases.class);

	@Test(priority = 1)
	public void verifyBedroomModuleVisible() {

		BedroomPage bed = new BedroomPage();
		Assert.assertFalse(bed.bedroomVisible(), "Bedroom Menu is not visible on Homepage");
		log.info("✅ Bedroom Menu is Visible");
	}

	@Test(priority = 2)
	public static void verifyBedroomSubCatogories() throws InterruptedException {
		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		log.info("✅ Bedroom Subcatogories Product List Size is: " + bed.getListSizeOfBedroomSubcatogories());
		Assert.assertTrue(bed.getListSizeOfBedroomSubcatogories() > 0, "❌ No subcategories found under Bedroom menu");

	}

	@Test(priority = 3)
	public static void verifyMattressesMenuList() throws InterruptedException {
		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		Assert.assertTrue(bed.getListSizeOfMattresseCatogory() > 0, "❌ No products displayed in Mattresses section");
		log.info("✅ Mattresse menu is visible successfully with products subcatogories.");
		bed.getListTextOfMattressesCatogory();
	}

	@Test(priority = 4)
	public static void kingMattressesNavigation() throws InterruptedException {

		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		MattressePage mattres = new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("/bedroom-mattresses-kingmattresses"),
				"❌ King Mattresses page URL not correct");
		log.info("✅ Navigated to King Mattresses page successfully");
	}

	@Test(priority = 5)
	public static void kingMattressesProduct() throws InterruptedException {

		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		Thread.sleep(2000);
		MattressePage mattres = new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		Wait_utils.waitForPresence(mattres.kingMattresProducts, 20);
		Assert.assertTrue(mattres.getListSizeOfKingmattressesProducts() > 0,
				"❌ No products displayed in King Mattresses category");
		log.info("✅ Total King Mattresse products displayed: " + getListSize(mattres.kingMattresProducts));
		Thread.sleep(2000);

		mattres.getListTextOfKingMattresseProducts();
	}

	@Test(priority = 6)
	public static void verifyKingMattresseProducts() throws InterruptedException {
		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		MattressePage mattres = new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();

		mattres.getListTextOfKingMattresseProducts();
		Wait_utils.waitForVisibility(mattres.listItemText, 20);
		Assert.assertEquals(mattres.getListSizeOfKingmattressesProducts(), getListCountInInteger(mattres.listItemText));
		log.info("✅ Products Verify Succesfully ");

	}

	@Test(priority = 7)
	public static void verifyProductDetailsOnPruductPage() throws InterruptedException {
		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		MattressePage mattres = new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();

		Actions act = new Actions(driver);
		act.scrollToElement(mattres.firstProduct);
		Wait_utils.waitForClickability(mattres.firstProduct, 20);
		String productName = mattres.firstProduct.getText();
		log.info("Clicked product: " + productName);
		Assert.assertTrue(driver.getTitle().contains("Buy King Size"),
				"❌ King Mattesses page product details is incorrect");
		log.info("✅ Navigated to King Mattresses product page successfully");

	}

	@Test(priority = 8)
	public static void verifyAddToBasket() throws InterruptedException {
		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		MattressePage mattres = new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();

		Wait_utils.waitForPresence(mattres.bassketIcon, 20);

		int initialCount = mattres.addToBasketInitialCount();

		Actions action = new Actions(driver);

		action.scrollToElement(mattres.firstProduct);

		Wait_utils.visibilityOfElementLocated(mattres.firstProduc, 20);
		action.moveToElement(mattres.firstProduct).perform();
		Wait_utils.waitForClickability(mattres.addToBasketButton, 20);
		mattres.addToBasketButton.click();

		driver.navigate().refresh();

		Wait_utils.visibilityOfElementLocated(mattres.bassketIcon, 40);

		Assert.assertTrue(mattres.basketIcon.isDisplayed(), "Product count is not displayed");
		log.info("Product succesfully added");

		int postCount = mattres.addToBasketPostCount();
		log.info(postCount + " Post Count");
		Assert.assertTrue(postCount > initialCount, "Basket Count did not increase");
		log.info("Basket Count increase after adding Item- " + mattres.addToBasketPostCount());
	}

	@Test(priority = 9)
	public static void verifyMultipleProductsClickabilityForPage() throws InterruptedException {
		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		MattressePage mattres = new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		Thread.sleep(2000);
		Thread.sleep(2000);
		mattres.kingMattressesProductsClick();

		Assert.assertEquals(mattres.kingMattressesProductsClick(), getListCountInInteger(mattres.listItemText),
				"All Products are Clickable");
	}

	@Test(priority = 10, description = "Verify addtoBasket page display")
	public void verifyCheckOutNowButtonVisibility() throws InterruptedException {
		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		MattressePage mattres = new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		Actions action = new Actions(driver);

		Wait_utils.visibilityOfElementLocated(mattres.firstProduc, 20);
		action.moveToElement(mattres.firstProduct).perform();
		Wait_utils.waitForClickability(mattres.addToBasketButton, 20);
		mattres.addToBasketButton.click();

		Wait_utils.waitForClickability(mattres.cartIcon, 20);
		mattres.cartIcon.click();

		driver.navigate().refresh();
		BasketCartPage cart = new BasketCartPage();

		action.scrollToElement(cart.checkoutNow).perform();

		Wait_utils.waitForVisibility(cart.checkoutNow, 10);
		Assert.assertTrue(cart.checkoutNow.isDisplayed(),
				"'Checkout Now' button not visible — page not displayed properly!");
		log.info(" 'Checkout Now' button visible");
	}

	@Test(priority = 11)
	public void verifyCheckoutButtonClickability() throws InterruptedException {

		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		MattressePage mattres = new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		Actions action = new Actions(driver);

		Wait_utils.visibilityOfElementLocated(mattres.firstProduc, 20);
		action.moveToElement(mattres.firstProduct).perform();
		Wait_utils.waitForClickability(mattres.addToBasketButton, 20);
		mattres.addToBasketButton.click();

		Wait_utils.waitForClickability(mattres.cartIcon, 20);
		mattres.cartIcon.click();

		driver.navigate().refresh();
		BasketCartPage cart = new BasketCartPage();

		action.scrollToElement(cart.checkoutNow).perform();

		Wait_utils.waitForClickability(cart.checkoutNow, 20);
		cart.checkoutNow.click();

		Assert.assertTrue(cart.continueButton.isDisplayed(), "Continue Button Is Not Visible");
		log.info("Continue Button is visible");

	}
	
//	@Test
	public void verifyPincodes() throws InterruptedException {
		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		MattressePage mattres = new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		Actions action = new Actions(driver);

		Wait_utils.visibilityOfElementLocated(mattres.firstProduc, 20);
		action.moveToElement(mattres.firstProduct).perform();
		Wait_utils.waitForClickability(mattres.addToBasketButton, 20);
		mattres.addToBasketButton.click();

		Wait_utils.waitForClickability(mattres.cartIcon, 20);
		mattres.cartIcon.click();
		
		Thread.sleep(2000);
		
		WebElement pincode=driver.findElement(By.cssSelector("input[id=\"pincode-field\"]"));
		pincode.sendKeys("445001");
		
		Thread.sleep(2000);
		
		WebElement check=driver.findElement(By.xpath("//span[text()='Check']"));		
		Thread.sleep(2000);
		check.click();
		
		Thread.sleep(2000);
		
		WebElement msg=driver.findElement(By.cssSelector("div[id=\"root-desk-top-right-inner\"]>div>div>p+p"));
		System.out.println(msg.getText());
		
		
	//	driver.navigate().refresh();
		
		Thread.sleep(2000);
		
		WebElement change=driver.findElement(By.cssSelector("div[id=\"pinCodeContainer\"]>div>div:nth-child(3)"));
		Wait_utils.waitForVisibility(change, 10);
		
		change.click();
		
		Thread.sleep(1000);
		
		WebElement cross=driver.findElement(By.cssSelector("div[id=\"pinCodeContainer\"]>div>div+div>span"));
		cross.click();
		
		driver.navigate().refresh();
		
		Thread.sleep(2000);
		WebElement pinnn=driver.findElement(By.cssSelector("input[id=\"pincode-field\"]"));
		try {
		Wait_utils.waitForVisibility(pinnn, 10);
		pinnn.sendKeys("400017");
		}
		
		catch(Exception e){
			System.out.println("error"+e.getMessage());
		}
		Thread.sleep(2000);
		
		Wait_utils.waitForVisibility(check, 10);
		
		check.click();
		
		//driver.navigate().refresh();
		
		Thread.sleep(2000);
		
		Wait_utils.waitForVisibility(msg, 10);
		
		System.out.println(msg.getText());
		
		Thread.sleep(2000);
			
	}
	
//	@Test(dataProvider = "Pincodes Data" , dataProviderClass = Data_Provider.class)
	public void verifyPincode(int pincod) throws InterruptedException, IOException {
		BedroomPage bed = new BedroomPage();
		bed.hoverOnBedroom();
		MattressePage mattres = new MattressePage();
		Wait_utils.waitForClickability(mattres.kingMatrres, 20);
		mattres.kingMatrres.click();
		Actions action = new Actions(driver);

		Wait_utils.visibilityOfElementLocated(mattres.firstProduc, 20);
		action.moveToElement(mattres.firstProduct).perform();
		Wait_utils.waitForClickability(mattres.addToBasketButton, 20);
		mattres.addToBasketButton.click();

		Wait_utils.waitForClickability(mattres.cartIcon, 20);
		mattres.cartIcon.click();
		
		Thread.sleep(2000);
		
		WebElement pincode=driver.findElement(By.cssSelector("input[id=\"pincode-field\"]"));
		pincode.sendKeys("445001");
		
		Thread.sleep(2000);
		
		WebElement check=driver.findElement(By.xpath("//span[text()='Check']"));		
		Thread.sleep(2000);
		
		WebElement msg=driver.findElement(By.cssSelector("div[id=\"root-desk-top-right-inner\"]>div>div>p+p"));
		System.out.println(msg.getText());
		
		Thread.sleep(2000);

		
		Data_Provider d=new Data_Provider();
		
		for(int i=1;i<=d.lastRowNumber();i++) {
			
//			String pp=pincod.toString();
//			
//			 int a=Integer.parseInt(pp);
			Thread.sleep(2000);
			
			WebElement change=driver.findElement(By.cssSelector("div[id=\"pinCodeContainer\"]>div>div:nth-child(3)"));
			Wait_utils.waitForVisibility(change, 10);
			
			change.click();
			
			Thread.sleep(1000);
			
			WebElement cross=driver.findElement(By.cssSelector("div[id=\"pinCodeContainer\"]>div>div+div>span"));
			cross.click();
			
			Thread.sleep(2000);
			
			pincode.sendKeys(String.valueOf(pincod));
					
			Thread.sleep(2000);
			
			Wait_utils.waitForVisibility(check, 10);
			
			check.click();
			
			//driver.navigate().refresh();
			
			Thread.sleep(2000);
			
			Wait_utils.waitForVisibility(msg, 10);
			
			System.out.println(msg.getText());
			
			Thread.sleep(2000);

		}
		
		
//		WebElement pincode=driver.findElement(By.cssSelector("input[id=\"pincode-field\"]"));
//		pincode.sendKeys("445001");
//		
//		Thread.sleep(2000);
//		
//		WebElement check=driver.findElement(By.xpath("//span[text()='Check']"));		
//		Thread.sleep(2000);
//		
//		WebElement msg=driver.findElement(By.cssSelector("div[id=\"root-desk-top-right-inner\"]>div>div>p+p"));
//		System.out.println(msg.getText());
//		
//		
//	//	driver.navigate().refresh();
//		
//		Thread.sleep(2000);
//		
//		WebElement change=driver.findElement(By.cssSelector("div[id=\"pinCodeContainer\"]>div>div:nth-child(3)"));
//		Wait_utils.waitForVisibility(change, 10);
//		
//		change.click();
//		
//		Thread.sleep(1000);
//		
//		WebElement cross=driver.findElement(By.cssSelector("div[id=\"pinCodeContainer\"]>div>div+div>span"));
//		cross.click();
//		
//		Thread.sleep(2000);
//		
//		pincode.sendKeys("400017");
//				
//		Thread.sleep(2000);
//		
//		Wait_utils.waitForVisibility(check, 10);
//		
//		check.click();
//		
//		//driver.navigate().refresh();
//		
//		Thread.sleep(2000);
//		
//		Wait_utils.waitForVisibility(msg, 10);
//		
//		System.out.println(msg.getText());
//		
//		Thread.sleep(2000);
		
	}
}
