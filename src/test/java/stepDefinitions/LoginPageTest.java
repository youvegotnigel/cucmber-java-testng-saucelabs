package stepDefinitions;

import cucumber.api.java.en.*;
import main.TestRunner;
import org.testng.Assert;
import pages.LoginPage;

public class LoginPageTest extends TestRunner {

    LoginPage loginPage = new LoginPage(driver);

    @Given("^The Application has been launched$")
    public void application_is_launched() {
        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.logoIsDisplayed());
    }

    @And("^I enter '(.+)' Username in text box$")
    public void enter_username(String username) {
       loginPage.setUsername(username);
    }

    @And("^I enter '(.+)' Password in text box$")
    public void enter_password(String password) {
        loginPage.setPassword(password);
    }

    @And("^I click on login button$")
    public void click_login_button() {
        loginPage.clickLoginButton();
    }

    @And("^System should display '(.+)' Error Message$")
    public void display_error_message(String errorMsg) {
        Assert.assertEquals(loginPage.getErrorMsg(), errorMsg);
    }

}
