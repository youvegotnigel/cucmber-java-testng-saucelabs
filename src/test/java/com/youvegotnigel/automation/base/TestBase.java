package com.youvegotnigel.automation.base;

import com.google.common.io.Files;
import com.youvegotnigel.automation.utils.ListenerClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import com.youvegotnigel.automation.pageobjects.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


public class TestBase {

    public static WebDriver driver;
    public static EventFiringWebDriver eventFiringWebDriver;
    public static Properties config;
    protected LoginPage loginPage;
    private static final String fileSeparator = File.separator;
    public static final Logger log = LogManager.getLogger(TestBase.class.getName());

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

    public void openBrowser(){
        // loads the config options
        LoadConfigProperty();

        switch (config.getProperty("BROWSER_TYPE")) {
            case "firefox":

                FirefoxBinary firefoxBinary = new FirefoxBinary();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);
                firefoxOptions.setHeadless(true);  //set headless mode true or false

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                log.debug("Initializing firefox driver");
                break;

            case "edge":

                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                log.debug("Initialize edge driver");
                break;

            case "chrome":

                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(true); //set headless mode true or false

                driver = new ChromeDriver(options);
                log.debug("Initialize chrome driver");
                break;
        }

        eventFiringWebDriver = new EventFiringWebDriver(driver);
        ListenerClass listener = new ListenerClass();
        eventFiringWebDriver.register(listener);
    }

    public void maximizeWindow() {
        eventFiringWebDriver.manage().window().setSize(new Dimension(1440, 900));
        eventFiringWebDriver.manage().window().maximize();
        loginPage = new LoginPage(this.eventFiringWebDriver);
        log.debug("Maximizing browser window");
    }

    public void implicitWait(int time) {
        eventFiringWebDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public void explicitWait(WebElement element) {
        WebDriverWait wait = new WebDriverWait(eventFiringWebDriver, 3000);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void pageLoad(int time) {
        eventFiringWebDriver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
    }

    public void deleteAllCookies() {
        eventFiringWebDriver.manage().deleteAllCookies();
        log.debug("Deleting all cookies");
    }

    public void setEnv(){
        LoadConfigProperty();
        String baseUrl = config.getProperty("LOGIN_URL");
        eventFiringWebDriver.get(baseUrl);
        //log.debug("Loading url : " + baseUrl);
    }

    public static String getTimestamp() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        return currentDate;
    }


    public static String takeScreenshot(String screenshotName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) eventFiringWebDriver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + fileSeparator + "test-output" + fileSeparator + "html-report" + fileSeparator + "screenshots" +fileSeparator+ screenshotName + " - " + getTimestamp() + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;

    }


    public void takeScreenshot2(ITestResult result) throws IOException {
        if (result.isSuccess()) {
            File imageFile = ((TakesScreenshot) eventFiringWebDriver).getScreenshotAs(OutputType.FILE);
            String failureImageFileName = result.getMethod().getMethodName()
                    + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";
            File failureImageFile = new File(System.getProperty("user.dir") + "//screenshots//" + failureImageFileName);
            failureImageFile.getParentFile().mkdir();
            failureImageFile.createNewFile();
            Files.copy(imageFile, failureImageFile);
        }

    }

    public void tearDown(){
        eventFiringWebDriver.quit();
        log.debug("Browser is closed");
    }

    public WebDriver getDriver(){
        return eventFiringWebDriver;
    }

    public String decodeText(String text){
        if(text == "" || text == null){
            return " ";
        }
        byte[] actualByte = Base64.getDecoder().decode(text);
        String actualString = new String(actualByte);
        return actualString;
    }

    public String getGlobalVariable(String variable){

        if(variable.startsWith("_")){
            LoadConfigProperty();
            return config.getProperty(variable);
        }
        return  variable;
    }

    public String[] getValueAndIndex(String value) {
        String[] values = value.split(Pattern.quote("["));
        values[1] = values[1].replaceAll("[^\\d.]", " ");
        return values;
    }
}
