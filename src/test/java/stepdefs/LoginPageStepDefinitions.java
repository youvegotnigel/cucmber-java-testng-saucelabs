package stepdefs;

import base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.testng.Assert;

import pages.LoginPage;

public class LoginPageStepDefinitions extends BaseClass {
    LoginPage loginPage = new LoginPage(driver);

    @Given("The Application has been launched")
    public void application_is_launched() throws Exception {
        //throw new PendingException();
        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.logoIsDisplayed());

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
