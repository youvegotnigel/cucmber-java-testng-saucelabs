package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.base.TestBase;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class ServiceHooks {

    TestBase testBase;
    public static final Logger log = LogManager.getLogger(ServiceHooks.class.getName());

    @Before
    public void initializeTest() throws Exception{
        testBase = new TestBase();
        testBase.openBrowser();
        testBase.maximizeWindow();
        testBase.implicitWait(30);
        testBase.deleteAllCookies();
        testBase.setEnv();
    }

    @Before
    public void beforeStartScenario(Scenario scenario){
        log.debug("✰ Started scenario : " + scenario.getName());
    }

    @AfterStep
    public void takeScreenshotAfterEachStep(Scenario scenario){
        try {
            TakesScreenshot screenshot = (TakesScreenshot) testBase.driver;
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
        testBase.tearDown();
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
