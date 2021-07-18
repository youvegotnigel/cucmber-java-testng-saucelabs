package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.base.PageBase;
import com.youvegotnigel.automation.base.TestBase;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasePageStepDefinitions extends TestBase{

    PageBase pageBase = new PageBase(eventFiringWebDriver);
    public static final Logger log = LogManager.getLogger(BasePageStepDefinitions.class.getName());

    @And("^I wait for \"(.+)\" seconds$")
    public void wait_time(String time) {
        implicitWait(Integer.parseInt(time));
    }

    @And("I click on {string} button")
    public void click_on_button(String text) {

        if(text.matches(".*\\[[\\d.]]")){
            var valueAndIndex = getValueAndIndex(text);
            pageBase.clickOnButtonByName(valueAndIndex[0], valueAndIndex[1]);
        }else {
            pageBase.clickOnButtonByName(text);
        }
    }

    @And("I click on {string} link")
    public void click_on_link(String text) {

        if(text.matches(".*\\[[\\d.]]")){
            var valueAndIndex = getValueAndIndex(text);
            pageBase.clickOnLinkByName(valueAndIndex[0], valueAndIndex[1]);
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
            Assert.assertTrue(pageBase.isDisplayedInNormalizeSpace(valueAndIndex[0], valueAndIndex[1]),"Not found text ::: "+ text);
        }else {
            Assert.assertTrue(pageBase.isDisplayedInNormalizeSpace(text),"Not found text ::: "+ text);
        }
    }

    @And("I should not see the text {string} displayed")
    public void text_is_not_displayed(String text) {

        if(text.matches(".*\\[[\\d.]]")){
            var valueAndIndex = getValueAndIndex(text);
            Assert.assertFalse(pageBase.isDisplayedInNormalizeSpace(valueAndIndex[0], valueAndIndex[1]),"Found text ::: "+ text);
        }else {
            Assert.assertFalse(pageBase.isDisplayedInNormalizeSpace(text),"Found text ::: "+ text);
        }
    }

    @And("^I set value \"(.+)\" for \"(.+)\"$")
    public void set_text_for_label(String answer, String question) {

        if(question.matches(".*\\[[\\d.]]")){
            var valueAndIndex = getValueAndIndex(question);
            setTextInputForLabel(valueAndIndex[0], valueAndIndex[1], answer);
        }else {
            setTextInputForLabel(question, answer);
        }
    }

    public List<String> get_table_list_in_application(String column){

         List<String> applicationList = new ArrayList<>();
//
//        def tot_table_row_count =  WebUI.callTestCase(findTestCase('Test Cases/TS UI Tests/Page Objects/Base/Get_Table_Row_Count'), [('get_column_heder'):column, ('column_header'):column])
//        KeywordUtil.logInfo("Total row count ::: ${tot_table_row_count}")
//
//        for(def no=1;no<tot_table_row_count;no+=2)
//        {
//            def Value = WebUI.callTestCase(findTestCase('Test Cases/TS UI Tests/Page Objects/Base/Get_Table_Value'), [('get_column_heder'):column, ('column_header'):column, ('response_code_value'):("row${no}")])
//            KeywordUtil.logInfo("Current Row ${no} Value is = ${Value}")
//
//            try{
//                applicationList.add(Integer.parseInt(Value))
//
//            }catch (NumberFormatException e){
//                applicationList.add(Value)
//
//            }
//
//        }
//
        return applicationList;
    }

    @And("Get values for {string} column in table and verify strings in descending order")
    public void validate_string_sorting_descending(String column) {

        List<String> SortedValuesAccordingToApplication = get_table_list_in_application(column);

        log.debug("Sorted Values According To Application are = " + SortedValuesAccordingToApplication);
        List<String> sortedValuesAccordingToPrograming =new ArrayList<>();

        sortedValuesAccordingToPrograming.addAll(SortedValuesAccordingToApplication);

        //Sort List
        try {
            sortedValuesAccordingToPrograming.sort(String.CASE_INSENSITIVE_ORDER.reversed());
        }catch(ClassCastException e){
            sortedValuesAccordingToPrograming.sort(Collections.reverseOrder());
        }

        log.debug("Sorted Values According To Programing are = " + sortedValuesAccordingToPrograming);

        //Assert.assertEquals(SortedValuesAccordingToApplication , sortedValuesAccordingToPrograming);
        Assert.assertTrue(SortedValuesAccordingToApplication.equals(sortedValuesAccordingToPrograming));
    }

    @And("Get values for {string} column in table and verify strings in ascending order")
    public void validate_string_sorting_ascending(String column) {

        List<String> SortedValuesAccordingToApplication = get_table_list_in_application(column);

        log.debug("Sorted Values According To Application are = " + SortedValuesAccordingToApplication);
        List<String> sortedValuesAccordingToPrograming =new ArrayList<>();

        sortedValuesAccordingToPrograming.addAll(SortedValuesAccordingToApplication);

        //Sort List
        try {
            sortedValuesAccordingToPrograming.sort(String.CASE_INSENSITIVE_ORDER);
        }catch(ClassCastException e){
            log.error("Error in sorting strings in ascending order");
            log.error(e.getMessage());
        }

        log.debug("Sorted Values According To Programing are = " + sortedValuesAccordingToPrograming);

        //Assert.assertEquals(SortedValuesAccordingToApplication , sortedValuesAccordingToPrograming);
        Assert.assertTrue(SortedValuesAccordingToApplication.equals(sortedValuesAccordingToPrograming));
    }


    @And("Get values for {string} column in table and verify numbers in descending order")
    public void validate_int_sorting_descending(String column) {

        List<String> SortedValuesAccordingToApplication = get_table_list_in_application(column);

        log.debug("Sorted Values According To Application are = " + SortedValuesAccordingToApplication);

        List<String> sortedValuesAccordingToPrograming =new ArrayList<>();

        sortedValuesAccordingToPrograming.addAll(SortedValuesAccordingToApplication);

        //Sort sortedValuesAccordingToPrograming in descending order
        Collections.sort(sortedValuesAccordingToPrograming, Collections.reverseOrder());

        log.debug("Sorted Values According To Programing are = " + sortedValuesAccordingToPrograming);
        Assert.assertTrue(SortedValuesAccordingToApplication.equals(sortedValuesAccordingToPrograming));
    }

    @And("Get values for {string} column in table and verify numbers in ascending order")
    public void validate_int_sorting_ascending(String column) {

        List<String> SortedValuesAccordingToApplication = get_table_list_in_application(column);

        log.debug("Sorted Values According To Application are = " + SortedValuesAccordingToApplication);

        List<String> sortedValuesAccordingToPrograming =new ArrayList<>();

        sortedValuesAccordingToPrograming.addAll(SortedValuesAccordingToApplication);

        //Sort sortedValuesAccordingToPrograming in ascending order
        Collections.sort(sortedValuesAccordingToPrograming);

        log.debug("Sorted Values According To Programing are = " + sortedValuesAccordingToPrograming);
        Assert.assertTrue(SortedValuesAccordingToApplication.equals(sortedValuesAccordingToPrograming));
    }


}
