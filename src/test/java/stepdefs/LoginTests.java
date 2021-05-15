package stepdefs;

import base.BaseClass;
import org.testng.Assert;
import cucumber.api.java.en.*;
import pages.LoginPage;

public class LoginTests extends BaseClass {
    LoginPage loginPage = new LoginPage(driver);

    @Given("The Application has been launched")
    public void application_is_launched() throws Exception {
        //throw new PendingException();
        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.logoIsDisplayed());

    }

    @And("I enter '(.*)' Username in text box")
    public void enter_username(String username) {
        //throw new PendingException();
        loginPage.setUsername(username);
    }

    @And("I enter '(.*)' Password in text box")
    public void enter_password(String password) {
        //throw new PendingException();
        loginPage.setPassword(password);
    }

    @And("I click on login button")
    public void click_login_button() {
        //throw new PendingException();
        loginPage.clickLoginButton();
    }

    @And("System should display '(.*)' Error Message")
    public void display_error_message(String errorMsg) {
        //throw new PendingException();
        Assert.assertEquals(loginPage.getErrorMsg(), errorMsg);
    }
}
