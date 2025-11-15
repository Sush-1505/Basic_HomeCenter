package com.homecenter.pages;
import static com.base.homecenter.base_class.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MattressePage {

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
		PageFactory.initElements(driver,this);
	}
	
	public int getListSizeOfKingmattressesProducts() {
		int size=getListSize(kingMattresProducts);
		return size;
	}
	
	public void getListTextOfKingMattresseProducts() {
		getProductsListTexts(kingMattresProducts);
	}
	
	public int addToBasketInitialCount() {
		System.out.println(basketIcon.getText());
		String start=basketIcon.getAttribute("aria-label");
	//	System.out.println(start);
		String r=start.replaceAll("\\D+","");
		int f=r.isEmpty()?0:Integer.parseInt(r);
	//	System.out.println(f+" StartCount");
		return f;
	}
	
	public int addToBasketPostCount() {
		String end=basketIcon.getAttribute("aria-label");
		//System.out.println(end);
		String p=end.replaceAll("[^0-12]","").trim();
		//int q=Integer.parseInt(p);
		
		
		int updatedCount = 0;
        if (!p.isEmpty()) {
            try {
                updatedCount = Integer.parseInt(p);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Could not parse basket count: " + end);
            }
		
        }
		
	//	System.out.println(updatedCount+" Updated Count");
		return updatedCount;
	}
	
	
}
