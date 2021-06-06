package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private WebDriver driver;

    private By button_continue = By.xpath("//input[@id='continue']");
    private By input_first_name = By.xpath("//input[@id='first-name']");
    private By input_last_name = By.xpath("//input[@id='last-name']");
    private By input_zip_code = By.xpath("//input[@id='postal-code']");
    private By button_finish = By.xpath("//button[@id='finish']");
    private By img_dilevery_logo = By.xpath("//img[@alt='Pony Express']");


    //Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnContinueButton(){
        driver.findElement(button_continue).click();
    }

    public boolean verifyErrorMessage(String error){
        String xpath = "//*[contains(text(),'" + error + "')]";
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public void inputFirstName(String value){
        driver.findElement(input_first_name).sendKeys(value);
    }

    public void inputLastName(String value){
        driver.findElement(input_last_name).sendKeys(value);
    }

    public void inputZipCode(String value){
        driver.findElement(input_zip_code).sendKeys(value);
    }

    public boolean textIsDisplayed(String text){
        String xpath = "//*[normalize-space()='" + text + "']";
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public void clickOnFinishButton(){
        driver.findElement(button_finish).click();
    }

    public boolean logoIsDisplayed(){
        return driver.findElement(img_dilevery_logo).isDisplayed();
    }

}
