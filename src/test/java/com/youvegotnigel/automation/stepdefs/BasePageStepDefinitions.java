package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.base.TestBase;
import io.cucumber.java.en.And;

public class BasePageStepDefinitions extends TestBase{

    @And("^I wait for \"(.+)\" seconds$")
    public void wait_time(String time) {
        implicitWait(Integer.parseInt(time));
    }

}
