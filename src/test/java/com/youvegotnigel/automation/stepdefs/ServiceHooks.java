package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.base.TestBase;
import com.youvegotnigel.automation.utils.CreateEnvFile;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import static org.openqa.selenium.OutputType.BYTES;

public class ServiceHooks {

    private static TestBase testBase;
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
            scenario.attach(data, "image/png", "Attachment");
            //log.debug("Screenshot taken");
            takeScreenshotToAttachOnAllureReport();
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
        CreateEnvFile createEnvFile = new CreateEnvFile();
        createEnvFile.createFile();
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
            scenario.attach(entry.getMessage(), "text/plain","CONSOLE LOGS");
        }
        log.debug("\n******************CONSOLE LOGS END******************\n");
    }

    @Attachment(value = "Failed test screenshot", type = "image/png")
    public static byte[] takeScreenshotToAttachOnAllureReport() {
        return ((TakesScreenshot) testBase.eventFiringWebDriver).getScreenshotAs(BYTES);
    }
}
