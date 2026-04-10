package com.homecenter.pages;
import static com.base.homecentre.Base_Test.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BasketCartPage {

	@FindBy(xpath="//span[text()=\"Checkout now\"]")
	public WebElement checkoutNow;
	
	public By checkOutNOw=By.xpath("//span[text()=\"Checkout now\"]");
	
	@FindBy(css="button[id=\"signup-form-submit\"]>span")
	public WebElement continueButton;
	
	@FindBy(css="input[id=\"pincode-field\"]")
	public WebElement pincode;
	
	@FindBy(xpath="//span[text()='Check']")
	public WebElement pincodeCheck;
	
	public By massage=By.cssSelector("div[id=\"root-desk-top-right-inner\"]>div>div>p+p");
	
	@FindBy(css="div[id=\"root-desk-top-right-inner\"]>div>div>p+p")
	public WebElement masage;
	
	public BasketCartPage() {
		PageFactory.initElements(getDriver(),this);
	}


public boolean isDisplayCheckOut() {
	boolean display=checkoutNow.isDisplayed();
	return display;
}
}