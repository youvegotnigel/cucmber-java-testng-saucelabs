package com.youvegotnigel.automation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

public class PageBase {
    public static WebDriver driver;
    public static Properties config;

    public static final Logger log = LogManager.getLogger(PageBase.class.getName());

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public void LoadConfigProperty(){
        try {
            config = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "//src//test//resources//config//config.properties");
            config.load(ip);
            log.info("Properties file loaded successfully");
        }catch (Exception e){
            log.error("Configuration Properties file not found." + Arrays.toString(e.getStackTrace()));
        }

    }

    public String getGlobalVariable(String variable){

        if(variable.startsWith("_")){
            LoadConfigProperty();
            return config.getProperty(variable);
        }
        return  variable;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void waitForVisibility(WebElement by) {
        LoadConfigProperty();
        WebDriverWait wait = new WebDriverWait(driver, Integer.valueOf(config.getProperty("WAIT_TIME")));
        wait.until(ExpectedConditions.visibilityOf(by));
    }

    public void clearText(By by) {
        var element = driver.findElement(by);
        waitForVisibility(element);
        highLightElement(element);
        element.clear();
    }

    public void click(By by) {
        var element = driver.findElement(by);
        waitForVisibility(element);
        highLightElement(element);
        element.click();
    }

    public void setText(By by, String text) {
        var element = driver.findElement(by);
        waitForVisibility(element);
        highLightElement(element);
        element.sendKeys(text);
    }

    public String getText(By by) {
        var element = driver.findElement(by);
        waitForVisibility(element);
        highLightElement(element);
        return element.getText();
    }

    public boolean isDisplayed(By by) {
        var element = driver.findElement(by);
        waitForVisibility(element);
        highLightElement(element);
        return element.isDisplayed();
    }

    public String getAttribute(By by, String attribute) {
        var element = driver.findElement(by);
        waitForVisibility(element);
        highLightElement(element);
        return element.getAttribute(attribute);
    }

    // ############################################ Generic xpath's ############################################

    public void clickOnButtonByName(String text) {
        String xpath = "//button[contains(normalize-space(),'" + text + "')]";
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            highLightElement(element);
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
            highLightElement(element);
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
            highLightElement(element);
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
            highLightElement(element);
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
        highLightElement(element);
        return element.isDisplayed();
    }

    public boolean isDisplayedInNormalizeSpace(String text, String index) {
        String xpath = "(//*[normalize-space()='" + text + "'])["+ index +"]" ;
        WebElement element = driver.findElement(By.xpath(xpath));
        highLightElement(element);
        return element.isDisplayed();
    }

    public void clickOnNormalizeSpace(String text) {
        String xpath = "//*[normalize-space()='" + text + "']";
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            highLightElement(element);
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
            highLightElement(element);
            element.click();
        } catch (Exception e) {
            log.debug("Could not click on web element");
            log.debug("xpath : " + xpath);
            log.error(e.getMessage());
        }
    }

    public void setTextInputForLabel(String label_name, String value){

        String xpath = "//label[contains(text(),'"+ label_name +"')]/following::input[1]";
        WebElement element = driver.findElement(By.xpath(xpath));
        highLightElement(element);
        element.sendKeys(getGlobalVariable(value));
    }

    public void setTextInputForLabel(String label_name, String index, String value){

        String xpath = "(//label[contains(text(),'"+ label_name +"')])["+ index +"]/following::input[1]";
        WebElement element = driver.findElement(By.xpath(xpath));
        highLightElement(element);
        element.sendKeys(getGlobalVariable(value));
    }


    public static void highLightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: #89cff0; border: 2px solid red;');", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

            System.out.println(e.getMessage());
        }
        //un-highlight text
        //js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
    }
}
