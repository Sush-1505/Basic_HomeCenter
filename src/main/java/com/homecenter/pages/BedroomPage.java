package com.homecenter.pages;

import static com.base.homecenter.base_class.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.homecenter.wait_utils;

public class BedroomPage {

	
	@FindBy(css="div[id=\"dept-bedroom\"]")
	public WebElement bedroom;
	
	@FindBy(css="div[id=\"Bedroom-category-item-2\"]>div+div>a[href=\"/in/en/c/bedroom-mattresses-kingmattresses\"]")
	public WebElement kingMattresse;
	
	@FindBy (css="div[id=\"category-menu-1\"]>div")
	public WebElement bedroomSubCatogories;
	
	public By bedroomSubCatogorie=By.cssSelector("div[id=\"category-menu-1\"]>div");
	
    public By mattresses=By.cssSelector("div[id=\"Bedroom-category-item-2\"]>div+div");
	
	@FindBy(css="div[id=\"Bedroom-category-item-2\"]>div+div")
	public WebElement mattress;
	
	
	public BedroomPage() {
		PageFactory.initElements(driver,this);
	}
	
	public void hoverOnBedroom() {
		wait_utils.waitForVisibility(bedroom, 10);
		hoverOnElement(bedroom);
	}
	
	public void clickOnKingMattresse() {

		clickOnElement(kingMattresse);
	}
	
	public boolean bedroomVisible() {
	 return	bedroom.isDisplayed();
	}
	
	public int getListSizeOfBedroomSubcatogories() {
		int size=getListSize(bedroomSubCatogorie);
		return size;
	}
	
	public int getListSizeOfMattresseCatogory() {
		int size=getListSize(mattresses);
		return size;
	}
	
	public void getListTextOfMattressesCatogory() {
		getProductsListTexts(mattresses);
	}
	
		
	
	
	
}
