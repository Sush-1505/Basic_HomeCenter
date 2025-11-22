package com.homecenter.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.homecenter.Wait_utils;

import static com.base.homecentre.Base_Test.*;


public class KitchenPage {

	private static final Logger log=LogManager.getLogger(KitchenPage.class);
	
	@FindBy(css="div[id=\"dept-kitchen\"]")
	public WebElement kitchen;
	
	@FindBy(css="div[id=\"Kitchen-category-item-1\"]>div+div>a[href=\"/in/en/c/kitchen-cooking-cookwaresets\"]")
	public WebElement cookwareSet;
	
	@FindBy(css="div[id=\"category-menu-7\"]>div")
	public WebElement kitchenSubCatogories;
	
	public By kitchenSubCatogorie=By.cssSelector("div[id=\"category-menu-7\"]>div");
	
	public By cookware=By.cssSelector("div[id=\"Kitchen-category-item-1\"]>div");
	
	@FindBy(css="div[id=\"Kitchen-category-item-1\"]>div")
	public WebElement cokware;
	
	public KitchenPage() {
		PageFactory.initElements(driver,this);
	}
	
	public void hoverOnKitchen() {
		Wait_utils.waitForVisibility(kitchen, 10);
		hoverOnElement(kitchen);
	}
	
	public void clickOncookWareSet() {
		clickOnElement(cookwareSet);
	}
	
	public boolean kitchenVisible() {
		return kitchen.isDisplayed();
	}
	
	public int getListSizeOfKitchenSubcatogories() {
		int size=getListSize(kitchenSubCatogorie);
		return size;
	}
	
	public int getListSizeOfCookwareCatogory() {
		int size=getListSize(cookware);
		return size;
	}
	
	public void getListTextOfCookwareCatogory() {
		getProductsListTexts(cookware);
	}
	
}
