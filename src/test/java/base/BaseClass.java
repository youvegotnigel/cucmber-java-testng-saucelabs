package base;

import com.google.common.io.Files;
import helpers.ListenerClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import pages.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseClass{

    public static WebDriver driver;
    public static EventFiringWebDriver eventFiringWebDriver;
    public static Properties config;
    protected LoginPage loginPage;
    private static final String fileSeparator = File.separator;
    public static final Logger log = LogManager.getLogger(BaseClass.class.getName());

    public void LoadConfigProperty() throws IOException {
        try {
            config = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "//src//test//resources//config//config.properties");
            config.load(ip);
            log.info("Properties file loaded successfully");
        }catch (Exception e){
            log.error("Configuration Properties file not found." + e.getStackTrace());
        }

    }

    public void openBrowser() throws Exception {
        // loads the config options
        LoadConfigProperty();

        if (config.getProperty("browserType").equals("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            log.debug("Initializing firefox driver");

        }else if(config.getProperty("browserType").equals("edge")){

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            log.debug("Initialize edge driver");

        }else if (config.getProperty("browserType").equals("chrome")) {

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true); //set headless mode true or false
            driver = new ChromeDriver(options);
            log.debug("Initialize chrome driver");
        }

        eventFiringWebDriver = new EventFiringWebDriver(driver);
        ListenerClass listener = new ListenerClass();
        eventFiringWebDriver.register(listener);
    }

    public void maximizeWindow() {
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

    public void setEnv() throws Exception {
        LoadConfigProperty();
        String baseUrl = config.getProperty("siteUrl");
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

    /**
     *
     * @param text encrypted value
     * @return actual value
     */
    public String decodeText(String text){
        if(text == "" || text == null){
            System.out.println("null text");
            return " ";
        }
        byte[] actualByte = Base64.getDecoder().decode(text);
        String actualString = new String(actualByte);
        return actualString;
    }

    public String getGlobalVariable(String variable) throws IOException {

        if(variable.startsWith("_")){
            LoadConfigProperty();
            return config.getProperty(variable);
        }
        return  variable;
    }
}
