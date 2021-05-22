package stepdefs;

import base.BaseClass;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class ServiceHooks {

    BaseClass baseClass;
    public static final Logger log = LogManager.getLogger(ServiceHooks.class.getName());

    @Before
    public void initializeTest() throws Exception{
        baseClass = new BaseClass();
        baseClass.openBrowser();
        baseClass.maximizeWindow();
        baseClass.implicitWait(30);
        baseClass.deleteAllCookies();
        baseClass.setEnv();
    }

    @BeforeStep
    public void startScenario(Scenario scenario){
        log.debug("✰ Starting scenario : " + scenario.getName());
    }

    @AfterStep
    public void takeScreenshotAfterEachStep(Scenario scenario){
        try {
            TakesScreenshot screenshot = (TakesScreenshot) baseClass.driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", "Attachment");
        }catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    @After
    public void endTest(Scenario scenario) {
        baseClass.driver.quit();
    }
}
