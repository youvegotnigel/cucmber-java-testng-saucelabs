package com.youvegotnigel.automation.pageobjects;

import com.youvegotnigel.automation.base.PageBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    private By usernameTextBox = By.xpath("//*[@id='user-name']");
    private By passwordTextBox = By.xpath("//*[@id='password']");
    private By errorMsg = By.xpath("//div[@class='error-message-container error']/h3");
    private By loginButton = By.xpath("//*[@id='login-button']");
    private By logo = By.xpath("//div[@class='login_logo']");

    public static final Logger log = LogManager.getLogger(LoginPage.class.getName());

    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Methods
    public void setUsername(String username) {
        clearText(usernameTextBox);
        setText(usernameTextBox, username);
    }

    public void setPassword(String password) {
        clearText(passwordTextBox);
        setText(passwordTextBox, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public boolean logoIsDisplayed() {
        return isDisplayed(logo);
    }

    public String getErrorMsg() {
        return getText(errorMsg);
    }

    //pass the driver for the next page
    public InventoryPage goToInventoryPage() {

        //log.debug("Passing driver to inventory page");
        return new InventoryPage(driver);
    }
}
