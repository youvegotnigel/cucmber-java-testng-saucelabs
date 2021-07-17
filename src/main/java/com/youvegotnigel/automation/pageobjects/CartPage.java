package com.youvegotnigel.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends PageBase{

    private By pageHeader = By.xpath("//span[@class='title']");
    private By button_checkout = By.xpath("//button[@id='checkout']");

    //Constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    //Methods
    public String getPageHeader(){
        return driver.findElement(pageHeader).getText();
    }

    public boolean textDisplayed(String text){
        return isDisplayedInNormalizeSpace(text);
    }

    public void clickOnCheckout(){
        click(button_checkout);
    }

    //pass the driver for the next page
    public CheckoutPage goToCheckoutPage() {
        //log.debug("Passing driver to inventory page");
        return new CheckoutPage(driver);
    }

}
