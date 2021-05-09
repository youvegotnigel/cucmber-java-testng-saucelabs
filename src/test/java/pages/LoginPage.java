package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By usernameTextBox = By.xpath("//*[@id='user-name']");
    private By passwordTextBox = By.xpath("//*[@id='password']");
    private By errorMsg = By.xpath("//div[@class='error-message-container error']/h3");
    private By loginButton = By.xpath("//*[@id='login-button']");
    private By logo = By.xpath("//div[@class='login_logo']");

    //Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Methods
    public void setUsername(String username) {
        driver.findElement(usernameTextBox).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordTextBox).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean logoIsDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }

    public String getErrorMsg() {
        return driver.findElement(errorMsg).getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

}
