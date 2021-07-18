package com.youvegotnigel.automation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
    public static WebDriver driver;
    public static final long WAIT = 10;

    public static final Logger log = LogManager.getLogger(PageBase.class.getName());

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void waitForVisibility(WebElement by) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(by));
    }

    public void clearText(By by) {
        waitForVisibility(driver.findElement(by));
        driver.findElement(by).clear();
    }

    public void click(By by) {
        waitForVisibility(driver.findElement(by));
        driver.findElement(by).click();
    }

    public void sendText(By by, String text) {
        waitForVisibility(driver.findElement(by));
        driver.findElement(by).sendKeys(text);
    }

    public String getText(By by) {
        waitForVisibility(driver.findElement(by));
        return driver.findElement(by).getText();
    }

    public boolean isDisplayed(By by) {
        waitForVisibility(driver.findElement(by));
        return driver.findElement(by).isDisplayed();
    }

    public String getAttribute(By by, String attribute) {
        waitForVisibility(driver.findElement(by));
        return driver.findElement(by).getAttribute(attribute);
    }

    // ############################################ Generic xpath's ############################################

    public void clickOnButtonByName(String text) {
        String xpath = "//button[contains(normalize-space(),'" + text + "')]";
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            element.click();
        } catch (Exception e) {
            log.debug("Could not click on web element");
            log.debug("xpath : " + xpath);
            log.error(e.getMessage());
        }
    }

    public void clickOnButtonByName(String text, String index) {
        String xpath = "(//button[contains(normalize-space(),'" + text + "')])["+ index +"]" ;
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            element.click();
        } catch (Exception e) {
            log.debug("Could not click on web element");
            log.debug("xpath : " + xpath);
            log.error(e.getMessage());
        }
    }

    public void clickOnLinkByName(String text) {
        String xpath = "//a[contains(normalize-space(),'" + text + "')]";
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            element.click();
        } catch (Exception e) {
            log.debug("Could not click on web element");
            log.debug("xpath : " + xpath);
            log.error(e.getMessage());
        }
    }

    public void clickOnLinkByName(String text, String index) {
        String xpath = "(//a[contains(normalize-space(),'" + text + "')])["+ index +"]" ;
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            element.click();
        } catch (Exception e) {
            log.debug("Could not click on web element");
            log.debug("xpath : " + xpath);
            log.error(e.getMessage());
        }
    }

    public boolean isDisplayedInNormalizeSpace(String text) {
        String xpath = "//*[normalize-space()='" + text + "']";
        WebElement element = driver.findElement(By.xpath(xpath));
        return element.isDisplayed();
    }

    public boolean isDisplayedInNormalizeSpace(String text, String index) {
        String xpath = "(//*[normalize-space()='" + text + "'])["+ index +"]" ;
        WebElement element = driver.findElement(By.xpath(xpath));
        return element.isDisplayed();
    }

    public void clickOnNormalizeSpace(String text) {
        String xpath = "//*[normalize-space()='" + text + "']";
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            element.click();
        } catch (Exception e) {
            log.debug("Could not click on web element");
            log.debug("xpath : " + xpath);
            log.error(e.getMessage());
        }
    }

    public void clickOnNormalizeSpace(String text, String index) {
        String xpath = "(//*[normalize-space()='" + text + "'])["+ index +"]" ;
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            element.click();
        } catch (Exception e) {
            log.debug("Could not click on web element");
            log.debug("xpath : " + xpath);
            log.error(e.getMessage());
        }
    }
}
