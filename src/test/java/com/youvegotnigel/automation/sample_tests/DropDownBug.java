package com.youvegotnigel.automation.sample_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DropDownBug {

    private WebDriver driver;
    private By username = By.xpath("//input[@id='user-name']");
    private By password = By.xpath("//input[@id='password']");
    private By loginBtn = By.xpath("//input[@id='login-button']");


    private By dropdown = By.xpath("//select[@class='product_sort_container']");

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        explicitWaitMethod(username);
        driver.findElement(username).sendKeys("standard_user");
        driver.findElement(password).sendKeys("secret_sauce");
        driver.findElement(loginBtn).click();
    }

    @Test
    public void selectByValue() throws Exception{

        String selectItem = "Name (Z to A)";
        explicitWaitMethod(dropdown);
        Select select = new Select(driver.findElement(dropdown));
        select.selectByVisibleText(selectItem);
        Thread.sleep(3500);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Invalid option has selected");
    }

    public void explicitWaitMethod(By element) {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
