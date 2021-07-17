package com.youvegotnigel.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.*;

public class ListenerClass implements ITestListener, ISuiteListener, IInvokedMethodListener, WebDriverEventListener {

    public static final Logger log = LogManager.getLogger(ListenerClass.class.getName());

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void onStart(ISuite suite) {

        log.info("****************************************************************************************");
        log.info("****************************************************************************************");
        log.info("$$$$$$$$$$$$$$$$$$   TEST SUITE : "+suite.getName()+ " HAS STARTED   $$$$$$$$$$$$$$$$$$");
        log.info("****************************************************************************************");
        log.info("****************************************************************************************");

    }

    @Override
    public void onFinish(ISuite suite) {

        log.info("**************** TEST SUITE : " + suite.getName() + " HAS COMPLETED *******************");
        log.info("X");
        log.info("X");
        log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
        log.info("X");
        log.info("X");

    }

    @Override
    public void onTestStart(ITestResult result) {

        //log.info("The test case " + result.getName() + " has been started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {

            log.warn("💥 Exception Found !!!");
            log.error("Caused by : " + result.getThrowable());
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        if (result.getStatus() == ITestResult.SKIP) {
            System.out.println("Test Skipped : " + result.getName());
            log.info("The test case " + result.getName() + " has been skipped");
        }

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {

    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        log.debug("Navigated to : " + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        log.debug("Trying to find element : " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        log.debug("Found element : " + by.toString());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        String path = element.toString().split("->")[1];
        log.debug("Trying to find element to click on :" + path);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {

        String path = element.toString().split("->")[1];
        log.debug("Found element to click on :" + path);
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        log.warn("💥 Exception Found !!!");
        log.error("Caused by : " + throwable.getMessage());
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        log.debug("Trying to find element with text: " + element.getText());
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        log.debug("Found element with text : " + element.getText());
    }
}
