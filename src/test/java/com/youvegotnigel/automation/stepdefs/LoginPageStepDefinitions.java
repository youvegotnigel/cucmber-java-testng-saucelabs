package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.base.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.testng.Assert;
import com.youvegotnigel.automation.pageobjects.LoginPage;
import java.io.IOException;


public class LoginPageStepDefinitions extends TestBase {
    LoginPage loginPage = new LoginPage(eventFiringWebDriver);

    @Given("The Application has been launched")
    public void application_is_launched() throws Exception {
        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.logoIsDisplayed());

    }

    @And("I log in as standard user")
    public void login_as_valid_user() throws IOException {
        LoadConfigProperty();
        loginPage.setUsername(getGlobalVariable("_username"));
        loginPage.setPassword(decodeText(getGlobalVariable("_password")));
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.goToInventoryPage().getPageHeader(), "PRODUCTS");
    }

    @And("I log in as {string} user")
    public void login_as_any_user(String string) throws IOException  {
        LoadConfigProperty();
        loginPage.setUsername(string);
        loginPage.setPassword(decodeText(config.getProperty("_password")));
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.goToInventoryPage().getPageHeader(), "PRODUCTS");
    }

    @And("I enter {string} in Username text box")
    public void enter_username(String string) {
        loginPage.setUsername(string);
    }

    @And("I enter {string} in Password text box")
    public void enter_password(String string) throws IOException {
        loginPage.setPassword(decodeText(getGlobalVariable(string)));
    }

    @And("I click on login button")
    public void click_login_button() {
        loginPage.clickLoginButton();
    }

    @And("System should display {string} Error Message")
    public void display_error_message(String errorMsg) {
        Assert.assertEquals(loginPage.getErrorMsg(), errorMsg);
    }
}
