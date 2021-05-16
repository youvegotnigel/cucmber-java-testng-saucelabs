package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;
    private By pageHeader = By.xpath("//span[@class='title']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageHeader(){
        return driver.findElement(pageHeader).getText();
    }




}
