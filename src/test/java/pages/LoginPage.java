package pages;

import base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By usernameTextBox = By.xpath("//*[@id='user-name']");
    private By passwordTextBox = By.xpath("//*[@id='password']");
    private By errorMsg = By.xpath("//div[@class='error-message-container error']/h3");
    private By loginButton = By.xpath("//*[@id='login-button']");
    private By logo = By.xpath("//div[@class='login_logo']");

    public static final Logger log = LogManager.getLogger(LoginPage.class.getName());

    //Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Methods
    public void setUsername(String username) {
        driver.findElement(usernameTextBox).clear();
        driver.findElement(usernameTextBox).sendKeys(username);
        log.info("Element found for username: " + usernameTextBox);
        log.debug("Input " + username + "for username");
    }

    public void setPassword(String password) {
        driver.findElement(passwordTextBox).clear();
        driver.findElement(passwordTextBox).sendKeys(password);
        log.info("Element found for password : " + passwordTextBox);
        log.debug("Input " + password + "for password");
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        log.info("Element found for login button : " + loginButton);
        log.debug("Click on login button");
    }

    public boolean logoIsDisplayed() {

        log.info("Element found for logo : " + logo);
        log.debug("Logo is displayed");
        return driver.findElement(logo).isDisplayed();
    }

    public String getErrorMsg() {

        log.info("Element found for error message : " + errorMsg);
        log.debug("Error message is displayed");
        return driver.findElement(errorMsg).getText();
    }

    public String getPageTitle() {

        log.debug("Found page title : " + driver.getTitle());
        return driver.getTitle();
    }

    //pass the driver for the next page
    public InventoryPage goToInventoryPage() {

        log.debug("Passing driver to inventory page");
        return new InventoryPage(driver);
    }
}
