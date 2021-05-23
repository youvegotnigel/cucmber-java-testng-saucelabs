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

    @Before
    public void beforeStartScenario(Scenario scenario){
        log.debug("✰ Started scenario : " + scenario.getName());
    }

    @AfterStep
    public void takeScreenshotAfterEachStep(Scenario scenario){
        try {
            TakesScreenshot screenshot = (TakesScreenshot) baseClass.driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", "Attachment");
            //log.debug("Screenshot taken");
        }catch (WebDriverException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @After
    public void endTest(Scenario scenario) {

        if(!scenario.isFailed()){
            log.debug("✔ Passed scenario : " + scenario.getName());
        }
        if(scenario.isFailed()){
            log.error("✘ Failed scenario : " + scenario.getName());
        }
        baseClass.tearDown();
    }

//    @BeforeStep
//    public void beforeStep() {
//        //System.out.println("  @BeforeStep");
//    }
//
//    @AfterStep
//    public void afterStep() {
//        System.out.println("  @AfterStep");
//        try {
//            log.debug("*********  2  **************");
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
