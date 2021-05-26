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
    private By item_images = By.xpath("//img[@class='inventory_item_img']");
    private By item_description = By.xpath("//div[@class='inventory_item_desc']");
    private By filter_icon = By.xpath("//select[@class='product_sort_container']");
    private By shopping_cart_badge = By.xpath("//div[@id='shopping_cart_container']");
    private By cart_item_count = By.xpath("//span[@class='shopping_cart_badge']");

    private By add_to_cart_button = By.xpath("//button[text()='Add to cart']");

    //Constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    //Methods
    public List getAllItemNames(){
        return driver.findElements(item_names);
    }

    public List getAllItemPrices(){
        return driver.findElements(item_prices);
    }

    public List getAllAddToCartButtons(){
        return driver.findElements(add_to_cart_button);
    }

    public int getItemImagesCount(){
        return driver.findElements(item_images).size();
    }

    public int getItemDescriptionCount(){
        return driver.findElements(item_description).size();
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

    public void clickOnShoppingCart(){
        driver.findElement(shopping_cart_badge).click();
    }

    public String getCartItemCount(){
        return driver.findElement(cart_item_count).getText();
    }

    public String getPageHeader(){
        return driver.findElement(pageHeader).getText();
    }

    public void clickOnAddCartOrRemove(String item_name){

        String  xpath = "//div[@class='inventory_item_name' and text()="+ item_name + "]/../../following-sibling::div/button";
        driver.findElement(By.xpath(xpath)).click();
    }

    //pass the driver for the next page
    public CartPage goToCartPage() {
        //log.debug("Passing driver to inventory page");
        return new CartPage(driver);
    }




}
