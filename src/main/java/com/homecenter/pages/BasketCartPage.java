package com.homecenter.pages;
import static com.base.homecenter.base_class.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasketCartPage {

	@FindBy(css="//span[text()=\"Checkout now\"]")
	public WebElement basketCart;
	
	public BasketCartPage() {
		PageFactory.initElements(driver,this);
	}


public boolean isDisplayCheckOut() {
	boolean display=basketCart.isDisplayed();
	return display;
}
}