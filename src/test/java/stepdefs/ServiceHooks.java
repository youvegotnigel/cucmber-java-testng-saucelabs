package stepdefs;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class ServiceHooks {

    BaseClass baseClass;

    @Before
    public void initializeTest() throws Exception{
        baseClass = new BaseClass();
        baseClass.openBrowser();
        baseClass.maximizeWindow();
        baseClass.implicitWait(30);
        baseClass.deleteAllCookies();
        baseClass.setEnv();
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
