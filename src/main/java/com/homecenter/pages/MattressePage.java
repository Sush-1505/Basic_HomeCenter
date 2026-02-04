package com.homecenter.pages;
import static com.base.homecentre.Base_Test.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.homecenter.Wait_utils;

public class MattressePage {
	
	private static final Logger log=LogManager.getLogger(MattressePage.class);

	@FindBy(css="div[id=\"plp-list\"]>div>div>div>div:nth-child(3)>a[aria-label=\"Restomax Elite 6+2 Inches Pocket Spring Memory Foam King Mattress with Box Top, 180x195cm - Grey\"]")
	public WebElement firstProduct;
	
	public By firstProduc=By.cssSelector("div[id=\"plp-list\"]>div>div>div>div:nth-child(3)>a[aria-label=\"Restomax Elite 6+2 Inches Pocket Spring Memory Foam King Mattress with Box Top, 180x195cm - Grey\"]");
	
	@FindBy(css="div[class=\"root-product-form\"]>div+div>button")
	public WebElement addToBasketButton;
	
	@FindBy(css="div[id=\"root-nav-mini-basket\"]>div button[aria-label=\"cart-icon-1\"]")
	public WebElement cartIcon;
	
	@FindBy(css="div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]")
    public WebElement kingMatrres;
	
	@FindBy(css="div[id=\"plp-list\"]>div>div")
	public WebElement kingMattressProducts;
	
	public By kingMattresProducts=By.cssSelector("div[id=\"plp-list\"]>div>div");
	
	public By  kingMatrress=By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]");
	
	
	@FindBy(css="div[id=\"list-sec\"]>div>div:nth-child(4)>div>div>div>div>p")
	public WebElement listItemText;
	
	@FindBy(css="button[aria-label^='cart-icon']")
	public WebElement basketIcon;
	
	public By bassketIcon=By.cssSelector("button[aria-label^='cart-icon']");
	
	
	public MattressePage() {
		PageFactory.initElements(getDriver(),this);
	}
	
	public int getListSizeOfKingmattressesProducts() {
		int size=getListSize(kingMattresProducts);
		//System.out.println("got "+size);
		return size;
	}
	
	public void getListTextOfKingMattresseProducts() {
		getProductsListTexts(kingMattresProducts);
	}
	
	public int addToBasketInitialCount() {
//		log.info(basketIcon.getText());
//		String start=basketIcon.getAttribute("aria-label");
//		String r=start.replaceAll("\\D+","");
//		int f=r.isEmpty()?0:Integer.parseInt(r);
//		return f;
		String end=basketIcon.getAttribute("aria-label");
		//log.info(end);
		String p=end.replaceAll("[^0-12]","").trim();
		//int q=Integer.parseInt(p);
		
		
		int initialCount = 0;
        if (!p.isEmpty()) {
            try {
                initialCount = Integer.parseInt(p);
            } catch (NumberFormatException e) {
                log.info("⚠️ Could not parse basket count: " + end);
            }
		
        }
		
	//	log.info(updatedCount+" Updated Count");
		return initialCount;
	}
	
	public int addToBasketPostCount() {
		String end=basketIcon.getAttribute("aria-label");
		//log.info(end);
		String p=end.replaceAll("[^0-12]","").trim();
		//int q=Integer.parseInt(p);
		
		
		int updatedCount = 0;
        if (!p.isEmpty()) {
            try {
                updatedCount = Integer.parseInt(p);
            } catch (NumberFormatException e) {
                log.info("⚠️ Could not parse basket count: " + end);
            }
		
        }
		
	//	log.info(updatedCount+" Updated Count");
		return updatedCount;
	}
	
	public int kingMattressesProductsClick() {
		List<WebElement>listOfProducts=getDriver().findElements(kingMattresProducts);	
		int add=0;
		for (WebElement list : listOfProducts) {
			// WebDriverWait wai = new WebDriverWait(driver, Duration.ofSeconds(10));
			//    WebElement produc=wai.until(ExpectedConditions.visibilityOf(list));
			Wait_utils.waitForVisibility(list, 20);
			    Actions action=new Actions(getDriver());
			    action.moveToElement(list).perform();
			    Wait_utils.waitForClickability(list, 10);
			    list.click();
			    add++;	
		}
		log.info("Total product Clicable: "+add);
		return add;
	}
	
	
}
