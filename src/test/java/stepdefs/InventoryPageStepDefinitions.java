package stepdefs;

import base.BaseClass;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPageStepDefinitions extends BaseClass {

    //InventoryPage inventoryPage = new InventoryPage(driver);
    LoginPage loginPage = new LoginPage(eventFiringWebDriver);

    @And("System should display page header {string}")
    public void verify_inventory_page_landing(String text) {

        Assert.assertEquals(loginPage.goToInventoryPage().getPageHeader(), text);
    }

    @And("Shopping cart badge should be displayed")
    public void shopping_cart_badge_is_displayed() {
        //throw new PendingException();
        Assert.assertTrue(loginPage.goToInventoryPage().shoppingCartIsDisplayed());
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
                actualListWebElements = loginPage.goToInventoryPage().getItemNames();

                for (WebElement a : actualListWebElements) {
                    actualListText.add(a.getText());
                }

                tempListText.addAll(actualListText);
                Collections.sort(tempListText);

                Assert.assertTrue(actualListText.equals(tempListText));
                break;

            case "prices":
                actualListWebElements = loginPage.goToInventoryPage().getItemPrices();

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

                actualListWebElements = loginPage.goToInventoryPage().getItemNames();
                for (WebElement a : actualListWebElements) {
                    actualListText.add(a.getText());
                }

                tempListText.addAll(actualListText);
                Collections.sort(tempListText, Collections.reverseOrder());

                Assert.assertTrue(actualListText.equals(tempListText));
                break;

            case "prices":

                actualListWebElements = loginPage.goToInventoryPage().getItemPrices();

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

    public void printList(List <String> list, String name){
        System.out.println("List name : " + name);
        for(String a : list){
            System.out.println("list : " + a);
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