package com.youvegotnigel.automation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/default",
        glue = {"com/youvegotnigel/automation/stepdefs"},
        tags = "@regression1 and not @ignore",
        dryRun = false,
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json"
        })
public class TestRunner extends AbstractTestNGCucumberTests{

    private TestNGCucumberRunner testNGCucumberRunner;
    public static final Logger log = LogManager.getLogger(TestRunner.class.getName());
//
//    @BeforeClass(alwaysRun = true)
//    @Override
//    public void setUpClass(){
//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//    }
//
//    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
//    @Override
//    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
//        // the 'featureWrapper' parameter solely exists to display the feature
//        // file in a test report
//        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
//    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        //return testNGCucumberRunner.provideScenarios();
        return super.scenarios();
    }

//    @AfterClass(alwaysRun = true)
//    @Override
//    public void tearDownClass() {
//
//        //testNGCucumberRunner.finish();
//        try {
//            //ReportHelper.generateCucumberReport();
//        }catch (Exception e){
//            log.warn("No feature files found");
//            log.error(e.getMessage());
//        }
//    }
}
