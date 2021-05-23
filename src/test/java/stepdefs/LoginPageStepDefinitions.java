package stepdefs;

import base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.testng.Assert;
import pages.LoginPage;
import java.io.IOException;


public class LoginPageStepDefinitions extends BaseClass {
    LoginPage loginPage = new LoginPage(eventFiringWebDriver);

    @Given("The Application has been launched")
    public void application_is_launched() throws Exception {
        //throw new PendingException();
        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.logoIsDisplayed());

    }

    @And("I log in as standard user")
    public void login_as_valid_user() throws IOException {

        LoadConfigProperty();
        loginPage.setUsername(config.getProperty("username"));
        loginPage.setPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.goToInventoryPage().getPageHeader(), "PRODUCTS");
    }

    @And("I log in as {string} user")
    public void login_as_any_user(String string) throws IOException  {
        LoadConfigProperty();
        loginPage.setUsername(string);
        loginPage.setPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.goToInventoryPage().getPageHeader(), "PRODUCTS");
    }

    @And("I enter {string} in Username text box")
    public void enter_username(String string) {
        //throw new PendingException();
        loginPage.setUsername(string);
    }

    @And("I enter {string} in Password text box")
    public void enter_password(String string) {
        //throw new PendingException();
        loginPage.setPassword(string);
    }

    @And("I click on login button")
    public void click_login_button() {
        //throw new PendingException();
        loginPage.clickLoginButton();
    }

    @And("System should display {string} Error Message")
    public void display_error_message(String errorMsg) {
        //throw new PendingException();
        Assert.assertEquals(loginPage.getErrorMsg(), errorMsg);
    }
}
