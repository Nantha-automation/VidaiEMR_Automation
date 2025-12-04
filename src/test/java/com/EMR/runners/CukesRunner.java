package com.EMR.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/default-html-reports.html",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        features = "src/test/resources/features",
        glue = "com.EMR.stepDefinitions",
        dryRun = false,
        //to run specific tags, specify them here
        tags = "@modifyPatient"
        //to run couple of tags use: tags = "@tag1 or @tag2"
        //tags = "@login or @registration"
)
public class CukesRunner {
}
