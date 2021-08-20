package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.base.TestBase;
//import cucumber.api.Scenario;
//import cucumber.api.java.After;
//import cucumber.api.java.AfterStep;
//import cucumber.api.java.Before;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class ServiceHooks {

    TestBase testBase;
    public static final Logger log = LogManager.getLogger(ServiceHooks.class.getName());

    @Before
    public void initializeTest(){
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
            scenario.embed(data, "image/png", "Attachment");
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
            analyzeLog(scenario);

        }
        testBase.tearDown();
    }

    public void analyzeLog(Scenario scenario){

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LogEntries logEntries = testBase.eventFiringWebDriver.manage().logs().get(LogType.BROWSER);
        log.debug("\n*****************CONSOLE LOGS START*****************\n");
        for (LogEntry entry : logEntries) {
            //System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            log.debug(entry.getMessage());
            scenario.write(entry.getMessage());
        }
        log.debug("\n******************CONSOLE LOGS END******************\n");
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
