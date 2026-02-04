package com.homecenter.test_cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.homecentre.Base_Test;
import com.homecenter.pages.BasketCartPage;
import com.homecenter.pages.BedroomPage;
import com.homecenter.pages.CookwarePage;
import com.homecenter.pages.KitchenPage;
import com.homecenter.pages.MattressePage;
import com.utils.homecenter.Wait_utils;

public class KitchenCookWareSetTestCases extends Base_Test{

	private static final Logger log = LogManager.getLogger(KitchenCookWareSetTestCases.class);

	@Test(priority = 1)
	public void verifyKitchenModuleVisible() {

		KitchenPage kitchen=new KitchenPage();
		Assert.assertTrue(kitchen.kitchenVisible(), "Kitchen Menu is not visible on Homepage");
		log.info("✅ Kitchen Menu is Visible");
	}

	@Test(priority = 2)
	public static void verifyKitchenSubCatogories() throws InterruptedException {
		KitchenPage kitchen=new KitchenPage();
		log.info("✅ Kitchen Subcatogories Product List Size is: " + kitchen.getListSizeOfKitchenSubcatogories());
		Assert.assertTrue(kitchen.getListSizeOfKitchenSubcatogories() > 0, "❌ No subcategories found under Kitchen menu");

	}

	@Test(priority = 3)
	public static void verifyCookwareMenuList() throws InterruptedException {
		KitchenPage kitchen=new KitchenPage();
		kitchen.hoverOnKitchen();
		Assert.assertTrue(kitchen.getListSizeOfCookwareCatogory() > 0, "❌ No products displayed in Cookware section");
		log.info("✅ Cookware menu is visible successfully with products subcatogories.");
		kitchen.getListTextOfCookwareCatogory();
	}

	@Test(priority = 4)
	public static void cookwareSetNavigation() throws InterruptedException {

		KitchenPage kitchen=new KitchenPage();
		kitchen.hoverOnKitchen();
		CookwarePage cookware=new CookwarePage();
		Wait_utils.waitForClickability(cookware.cookwareSet, 20);
		cookware.cookwareSet.click();
		Assert.assertTrue(getDriver().getCurrentUrl().toLowerCase().contains("/kitchen-cooking-cookwaresets"),
				"❌ Cookware Set page URL not correct");
		log.info("✅ Navigated to Cookware Set page successfully");
	}

	@Test(priority = 5)
	public static void cookwareSetProductsProduct() throws InterruptedException {

		KitchenPage kitchen=new KitchenPage();
		kitchen.hoverOnKitchen();
		//Thread.sleep(2000);
		CookwarePage cookware=new CookwarePage();

		Wait_utils.waitForClickability(cookware.cookwareSet, 20);
		cookware.cookwareSet.click();
		Wait_utils.waitForPresence(cookware.cookwareSetProducts, 20);
		Assert.assertTrue(cookware.getListSizeOfCookwareSetProducts() > 0,
				"❌ No products displayed in Cookware Set category");
		log.info("✅ Total King Mattresse products displayed: " + getListSize(cookware.cookwareSetProducts));
		Thread.sleep(2000);

		cookware.getListTextOfCookwareSetProducts();
	}

	@Test(priority = 6)
	public static void verifycookwareSetProductsProducts() throws InterruptedException {
		KitchenPage kitchen=new KitchenPage();
		kitchen.hoverOnKitchen();
		//Thread.sleep(2000);
		CookwarePage cookware=new CookwarePage();

		Wait_utils.waitForClickability(cookware.cookwareSet, 20);
		cookware.cookwareSet.click();

		cookware.getListTextOfCookwareSetProducts();
		Wait_utils.waitForVisibility(cookware.listItemText, 20);
		Assert.assertEquals(cookware.getListSizeOfCookwareSetProducts(), getListCountInInteger(cookware.listItemText));
		log.info("✅ Products Verify Succesfully ");

	}

	@Test(priority = 7)
	public static void verifyProductDetailsOnPruductPage() throws InterruptedException {
		KitchenPage kitchen=new KitchenPage();
		kitchen.hoverOnKitchen();
		//Thread.sleep(2000);
		CookwarePage cookware=new CookwarePage();

		Wait_utils.waitForClickability(cookware.cookwareSet, 20);
		cookware.cookwareSet.click();

		Actions act = new Actions(getDriver());
		act.scrollToElement(cookware.firstProduct);
		Wait_utils.waitForClickability(cookware.firstProduct, 20);
		String productName = cookware.firstProduct.getText();
		log.info("Clicked product: " + productName);
		Assert.assertTrue(getDriver().getTitle().contains("Buy Gravis Steller 4Pcs"),
				"❌ Cookware Set page product details is incorrect");
		log.info("✅ Navigated to Cookware Set product page successfully");

	}

	@Test(priority = 8)
	public static void verifyAddToBasket() throws InterruptedException {
		KitchenPage kitchen=new KitchenPage();
		kitchen.hoverOnKitchen();
		//Thread.sleep(2000);
		CookwarePage cookware=new CookwarePage();

		Wait_utils.waitForClickability(cookware.cookwareSet, 20);
		cookware.cookwareSet.click();

		Wait_utils.waitForPresence(cookware.bassketIcon, 20);

		int initialCount = cookware.addToBasketInitialCount();

		Actions action = new Actions(getDriver());

		action.scrollToElement(cookware.firstProduct);

		Wait_utils.visibilityOfElementLocated(cookware.firstProduc, 20);
		action.moveToElement(cookware.firstProduct).perform();
		Wait_utils.waitForClickability(cookware.addToBasketButton, 20);
		cookware.addToBasketButton.click();

		getDriver().navigate().refresh();

		Wait_utils.visibilityOfElementLocated(cookware.bassketIcon, 40);

		Assert.assertTrue(cookware.basketIcon.isDisplayed(), "Product count is not displayed");
		log.info("Product succesfully added");

		int postCount = cookware.addToBasketPostCount();
		log.info(postCount + " Post Count");
		Assert.assertTrue(postCount > initialCount, "Basket Count did not increase");
		log.info("Basket Count increase after adding Item- " + cookware.addToBasketPostCount());
	}

	@Test(priority = 9)
	public static void verifyMultipleProductsClickabilityForPage() throws InterruptedException {
		KitchenPage kitchen=new KitchenPage();
		kitchen.hoverOnKitchen();
		//Thread.sleep(2000);
		CookwarePage cookware=new CookwarePage();

		Wait_utils.waitForClickability(cookware.cookwareSet, 20);
		cookware.cookwareSet.click();

		//Thread.sleep(2000);
		Thread.sleep(2000);
		cookware.cookwareSetProductsClick();

		Assert.assertEquals(cookware.cookwareSetProductsClick(), getListCountInInteger(cookware.listItemText),
				"All Products are Clickable");
	}

	@Test(priority = 10, description = "Verify addtoBasket page display")
	public void verifyCheckOutNowButtonVisibility() throws InterruptedException {
		KitchenPage kitchen=new KitchenPage();
		kitchen.hoverOnKitchen();
		//Thread.sleep(2000);
		CookwarePage cookware=new CookwarePage();

		Wait_utils.waitForClickability(cookware.cookwareSet, 20);
		cookware.cookwareSet.click();
		Actions action = new Actions(getDriver());

		Wait_utils.visibilityOfElementLocated(cookware.firstProduc, 20);
		action.moveToElement(cookware.firstProduct).perform();
		Wait_utils.waitForClickability(cookware.addToBasketButton, 20);
		cookware.addToBasketButton.click();

		Wait_utils.waitForClickability(cookware.cartIcon, 20);
		cookware.cartIcon.click();

		getDriver().navigate().refresh();
		BasketCartPage cart = new BasketCartPage();

		action.scrollToElement(cart.checkoutNow).perform();

		Wait_utils.waitForVisibility(cart.checkoutNow, 10);
		Assert.assertTrue(cart.checkoutNow.isDisplayed(),
				"'Checkout Now' button not visible — page not displayed properly!");
		log.info(" 'Checkout Now' button visible");
	}

	@Test(priority = 11)
	public void verifyCheckoutButtonClickability() throws InterruptedException {

		KitchenPage kitchen=new KitchenPage();
		kitchen.hoverOnKitchen();
		//Thread.sleep(2000);
		CookwarePage cookware=new CookwarePage();

		Wait_utils.waitForClickability(cookware.cookwareSet, 20);
		cookware.cookwareSet.click();
		Actions action = new Actions(getDriver());

		Wait_utils.visibilityOfElementLocated(cookware.firstProduc, 20);
		action.moveToElement(cookware.firstProduct).perform();
		Wait_utils.waitForClickability(cookware.addToBasketButton, 20);
		cookware.addToBasketButton.click();

		Wait_utils.waitForClickability(cookware.cartIcon, 20);
		cookware.cartIcon.click();

		getDriver().navigate().refresh();
		BasketCartPage cart = new BasketCartPage();

		action.scrollToElement(cart.checkoutNow).perform();
		Wait_utils.waitForClickability(cart.checkoutNow, 20);
		cart.checkoutNow.click();

		Assert.assertTrue(cart.continueButton.isDisplayed(), "Continue Button Is Not Visible");
		log.info("Continue Button is visible");

	}

}
