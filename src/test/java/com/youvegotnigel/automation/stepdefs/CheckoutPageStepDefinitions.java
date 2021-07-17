package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.base.TestBase;
import io.cucumber.java.en.And;
import org.testng.Assert;
import com.youvegotnigel.automation.pageobjects.LoginPage;

public class CheckoutPageStepDefinitions extends TestBase {

    LoginPage loginPage = new LoginPage(eventFiringWebDriver);

    @And("^I click on continue button$")
    public void click_on_checkout(){
        loginPage.goToInventoryPage().goToCartPage().goToCheckoutPage().clickOnContinueButton();
    }

    @And("^Error message (.+) should be displayed$")
    public void error_message_is_displayed(String errorMsg){
        Assert.assertTrue(loginPage.goToInventoryPage().goToCartPage().goToCheckoutPage().verifyErrorMessage(errorMsg));
    }

    @And("^I input (.+) for First Name$")
    public void input_value_for_first_name(String value){
        loginPage.goToInventoryPage().goToCartPage().goToCheckoutPage().inputFirstName(value);
    }

    @And("^I input (.+) for Last Name$")
    public void input_value_for_last_name(String value){
        loginPage.goToInventoryPage().goToCartPage().goToCheckoutPage().inputLastName(value);
    }

    @And("^I input (.+) for Zip Code$")
    public void input_value_for_zip_code(String value){
        loginPage.goToInventoryPage().goToCartPage().goToCheckoutPage().inputZipCode(value);
    }

    @And("^Should display (?:|shipping information|label) (.+)$")
    public void text_is_displayed(String value){
        loginPage.goToInventoryPage().goToCartPage().goToCheckoutPage().textIsDisplayed(value);
    }

    @And("^Click on finish button$")
    public void click_on_finish(){
        loginPage.goToInventoryPage().goToCartPage().goToCheckoutPage().clickOnFinishButton();
    }

    @And("Pony Express delivery logo should be displayed")
    public void logo_is_displayed(){
        Assert.assertTrue(loginPage.goToInventoryPage().goToCartPage().goToCheckoutPage().logoIsDisplayed());
    }

}
