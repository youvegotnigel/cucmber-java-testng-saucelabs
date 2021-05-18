package stepdefs;

import base.BaseClass;
import io.cucumber.java.en.And;
import org.testng.Assert;
import pages.LoginPage;

public class InventoryPageStepDefinitions extends BaseClass {

    //InventoryPage inventoryPage = new InventoryPage(driver);
    LoginPage loginPage = new LoginPage(driver);

    @And("System should display page header {string}")
    public void verify_inventory_page_landing(String text) {

        Assert.assertEquals(loginPage.goToInventoryPage().getPageHeader(), text);
    }

}
