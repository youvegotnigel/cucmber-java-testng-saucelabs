package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.testng.Assert;
import com.youvegotnigel.automation.pageobjects.LoginPage;

import java.util.List;
import java.util.Map;

public class CartPageStepDefinitions extends TestBase {

    LoginPage loginPage = new LoginPage(eventFiringWebDriver);

    @And("^I should see (.+) in my cart for (.+)$")
    public void item_is_displayed(String item, String price){
        Assert.assertTrue(loginPage.goToInventoryPage().goToCartPage().textDisplayed(item));
        Assert.assertTrue(loginPage.goToInventoryPage().goToCartPage().textDisplayed(price));
    }

    @And("^I should see the items in my cart as below:$")
    public void item_is_displayed(DataTable table){

        List<Map<String, String>> values = table.asMaps(String.class, String.class);

        for(var e : values){
            Assert.assertTrue(loginPage.goToInventoryPage().goToCartPage().textDisplayed(e.get("item_name")));
            Assert.assertTrue(loginPage.goToInventoryPage().goToCartPage().textDisplayed(e.get("item_price")));
        }

    }

    @And("^I click on checkout button$")
    public void click_on_checkout(){
        loginPage.goToInventoryPage().goToCartPage().clickOnCheckout();
    }
}
