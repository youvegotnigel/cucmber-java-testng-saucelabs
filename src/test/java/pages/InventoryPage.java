package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {

    private WebDriver driver;
    private By pageHeader = By.xpath("//span[@class='title']");
    private By item_names = By.xpath("//div[@class='inventory_item_name']");
    private By item_prices = By.xpath("//div[@class='inventory_item_price']");
    private By filter_icon = By.xpath("//select[@class='product_sort_container']");
    private By shopping_cart_badge = By.xpath("//div[@id='shopping_cart_container']");
    private By cart_item_count = By.xpath("//span[@class='shopping_cart_badge']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public List getItemNames(){
        return driver.findElements(item_names);
    }

    public List getItemPrices(){
        return driver.findElements(item_prices);
    }

    public WebElement getFilterIcon(){
        return driver.findElement(filter_icon);
    }

    public void clickOnFilterIcon(){
        driver.findElement(filter_icon).click();
    }

    public boolean shoppingCartIsDisplayed(){
        return driver.findElement(shopping_cart_badge).isDisplayed();
    }

    public int getCartItemCount(){
        return Integer.parseInt(driver.findElement(cart_item_count).getText());
    }

    public String getPageHeader(){
        return driver.findElement(pageHeader).getText();
    }




}
