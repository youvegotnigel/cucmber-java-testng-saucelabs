package com.youvegotnigel.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends PageBase{

    private By button_continue = By.xpath("//input[@id='continue']");
    private By input_first_name = By.xpath("//input[@id='first-name']");
    private By input_last_name = By.xpath("//input[@id='last-name']");
    private By input_zip_code = By.xpath("//input[@id='postal-code']");
    private By button_finish = By.xpath("//button[@id='finish']");
    private By img_dilevery_logo = By.xpath("//img[@alt='Pony Express']");


    //Constructor
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnContinueButton(){
        click(button_continue);
    }

    public boolean verifyErrorMessage(String error){
        String xpath = "//*[contains(text(),'" + error + "')]";
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public void inputFirstName(String value){
        sendText(input_first_name, value);
    }

    public void inputLastName(String value){
        sendText(input_last_name, value);
    }

    public void inputZipCode(String value){
        sendText(input_zip_code, value);
    }

    public boolean textIsDisplayed(String text){
        return isDisplayedInNormalizeSpace(text);
    }

    public void clickOnFinishButton(){
        click(button_finish);
    }

    public boolean logoIsDisplayed(){
        return isDisplayed(img_dilevery_logo);
    }

}
