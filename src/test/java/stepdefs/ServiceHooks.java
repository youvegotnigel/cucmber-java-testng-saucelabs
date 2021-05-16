package stepdefs;

import base.BaseClass;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
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
            scenario.embed(data, "image/png");
        }catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    @After
    public void endTest(Scenario scenario) {
        if (scenario.isFailed()) {

            try {

                final byte[] screenshot = ((TakesScreenshot) baseClass.driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png"); // ... and embed it in
            } catch (WebDriverException e) {
                e.printStackTrace();
            }

        } else {
            try {

                scenario.embed(((TakesScreenshot) baseClass.driver).getScreenshotAs(OutputType.BYTES), "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        baseClass.driver.quit();
    }
}
