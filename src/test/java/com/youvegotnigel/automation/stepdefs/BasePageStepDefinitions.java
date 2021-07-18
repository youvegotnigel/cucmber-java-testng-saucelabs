package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.base.PageBase;
import com.youvegotnigel.automation.base.TestBase;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class BasePageStepDefinitions extends TestBase{

    PageBase pageBase = new PageBase(eventFiringWebDriver);

    @And("^I wait for \"(.+)\" seconds$")
    public void wait_time(String time) {
        implicitWait(Integer.parseInt(time));
    }

    @And("I click on {string} button")
    public void click_on_button(String text) {

        if(text.matches(".*\\[[\\d.]]")){
            var valueAndIndex = getValueAndIndex(text);
            pageBase.clickOnButtonByName(text, valueAndIndex[1]);
        }else {
            pageBase.clickOnButtonByName(text);
        }
    }

    @And("I click on {string} link")
    public void click_on_link(String text) {

        if(text.matches(".*\\[[\\d.]]")){
            var valueAndIndex = getValueAndIndex(text);
            pageBase.clickOnLinkByName(text, valueAndIndex[1]);
        }else {
            pageBase.clickOnLinkByName(text);
        }
    }

    @And("^I click on the '(.+)' (?: |button|link|text)$")
    public void click_on_normalize_space(String text) {

        if(text.matches(".*\\[[\\d.]]")){
            var valueAndIndex = getValueAndIndex(text);
            pageBase.clickOnNormalizeSpace(text, valueAndIndex[1]);
        }else {
            pageBase.clickOnNormalizeSpace(text);
        }
    }

    @And("I should see the text {string} displayed")
    public void text_is_displayed(String text) {

        if(text.matches(".*\\[[\\d.]]")){
            var valueAndIndex = getValueAndIndex(text);
            Assert.assertTrue(pageBase.isDisplayedInNormalizeSpace(text, valueAndIndex[1]),"Not found text ::: "+ text);
        }else {
            Assert.assertTrue(pageBase.isDisplayedInNormalizeSpace(text),"Not found text ::: "+ text);
        }
    }

    @And("I should not see the text {string} displayed")
    public void text_is_not_displayed(String text) {

        if(text.matches(".*\\[[\\d.]]")){
            var valueAndIndex = getValueAndIndex(text);
            Assert.assertFalse(pageBase.isDisplayedInNormalizeSpace(text, valueAndIndex[1]),"Found text ::: "+ text);
        }else {
            Assert.assertFalse(pageBase.isDisplayedInNormalizeSpace(text),"Found text ::: "+ text);
        }
    }


}
