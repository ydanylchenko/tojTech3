package com.facebook;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(plugin = {
        "rerun:target/rerun.txt", // Create a text file with failed scenarios
        "pretty",
        "html:target/cucumber",
        "json:target/cucumber-report.json",
        "timeline:target/cucumberTimeline"},
        strict = true
)
public class RunCucumberTests extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
