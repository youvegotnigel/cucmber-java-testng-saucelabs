package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.base.TestBase;
import com.youvegotnigel.automation.pageobjects.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPageStepDefinitions extends TestBase {

    //InventoryPage inventoryPage = new InventoryPage(driver);
    LoginPage loginPage = new LoginPage(eventFiringWebDriver);

    @And("System should display page header {string}")
    public void verify_inventory_page_landing(String text) {

        Assert.assertEquals(loginPage.goToInventoryPage().getPageHeader(), text);
    }

    @And("^Shopping cart badge should be displayed$")
    public void shopping_cart_badge_is_displayed() {
        //throw new PendingException();
        Assert.assertTrue(loginPage.goToInventoryPage().shoppingCartIsDisplayed());
    }

    @And("^I click on Shopping cart badge$")
    public void click_on_cart_badge() {
        //throw new PendingException();
       loginPage.goToInventoryPage().clickOnShoppingCart();
    }

    @And("^I should see (.+) inventory item images?$")
    public void all_inventory_item_images_are_displayed(int count) {
        //throw new PendingException();
        Assert.assertEquals(loginPage.goToInventoryPage().getItemImagesCount(), count);
    }

    @And("^I should see (.+) inventory descriptions?$")
    public void all_inventory_item_descriptions_are_displayed(int count) {
        //throw new PendingException();
        Assert.assertEquals(loginPage.goToInventoryPage().getItemDescriptionCount(), count);
    }

    @And("I click on filter icon")
    public void click_on_filter_icon() {
        loginPage.goToInventoryPage().clickOnFilterIcon();
    }

    @And("Select {string} from the dropdown")
    public void select_items(String text) {
        Select options = new Select(loginPage.goToInventoryPage().getFilterIcon());
        options.selectByVisibleText(text);
    }

    @And("Item {string} should be in ascending order")
    public void check_ascending_order(String type) {

        List<WebElement> actualListWebElements = new ArrayList();
        List<String> actualListText = new ArrayList();
        List<String> tempListText = new ArrayList();

        switch (type) {

            case "names":
                actualListWebElements = loginPage.goToInventoryPage().getAllItemNames();

                for (WebElement a : actualListWebElements) {
                    actualListText.add(a.getText());
                }

                tempListText.addAll(actualListText);
                Collections.sort(tempListText);

                Assert.assertTrue(actualListText.equals(tempListText));
                break;

            case "prices":
                actualListWebElements = loginPage.goToInventoryPage().getAllItemPrices();

                for (WebElement a : actualListWebElements) {
                    actualListText.add(a.getText());
                }
                tempListText.addAll(actualListText);
                List<Double> tempListDouble = new ArrayList();
                List<Double> actualListDouble = new ArrayList();

                actualListDouble = covertListIntoDouble(tempListText);
                tempListDouble = covertListIntoDouble(tempListText);

                Collections.sort(tempListText);
                Assert.assertTrue(actualListDouble.equals(tempListDouble));
                break;

            default:
                return;
        }

    }

    @And("Item {string} should be in descending order")
    public void check_descending_order(String type) {
        List<WebElement> actualListWebElements = new ArrayList();
        List<String> actualListText = new ArrayList();
        List<String> tempListText = new ArrayList();

        switch (type) {
            case "names":

                actualListWebElements = loginPage.goToInventoryPage().getAllItemNames();
                for (WebElement a : actualListWebElements) {
                    actualListText.add(a.getText());
                }

                tempListText.addAll(actualListText);
                Collections.sort(tempListText, Collections.reverseOrder());

                Assert.assertTrue(actualListText.equals(tempListText));
                break;

            case "prices":

                actualListWebElements = loginPage.goToInventoryPage().getAllItemPrices();

                for (WebElement a : actualListWebElements) {
                    actualListText.add(a.getText());
                }

                tempListText.addAll(actualListText);
                List<Double> tempListDouble = new ArrayList();
                List<Double> actualListDouble = new ArrayList();

                actualListDouble = covertListIntoDouble(tempListText);
                tempListDouble = covertListIntoDouble(tempListText);

                Collections.sort(tempListDouble, Collections.reverseOrder());
                Assert.assertTrue(actualListDouble.equals(tempListDouble));
                break;

            default:
                return;

        }

    }

    @And("^I (?:add|remove) (.+) (?:to|from) my cart$")
    public void add_to_or_remove_from_cart(String item){
        loginPage.goToInventoryPage().clickOnAddCartOrRemove(item);
    }


    //@Then("There should be \"([^\"]+)\" items in the cart")
    //Then("^There should be {string} items in the cart$")
    @Then("^There should be \"(.+)\" items in the cart$")
    public void verify_items_in_cart(String count) {

        Assert.assertEquals(loginPage.goToInventoryPage().getCartItemCount(), count);
    }

    @And("^I add all items to cart$")
    public void add_all_items_to_cart() {

        List<WebElement> buttons = loginPage.goToInventoryPage().getAllAddToCartButtons();
        for (WebElement e : buttons) {
            e.click();
        }
    }

    public List<Double> covertListIntoDouble(List <String> list){
        List<String> sList = new ArrayList();
        List<Double> dList = new ArrayList();

        for(String a : list){
            sList.add(a.replace("$",""));
        }

        for(String a : sList){
            dList.add(Double.parseDouble(a));
        }

        return dList;
    }

}
