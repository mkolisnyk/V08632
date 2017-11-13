package com.sample.tests.junit;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "./src/test/java/com/sample/tests/features" },
        glue = { "com/sample/tests/steps" },
        plugin = {
        "json:build/cucumber.json", "html:build/cucumber-html-report",
        "pretty:build/cucumber-pretty.txt",
        "usage:build/cucumber-usage.json",
        "junit:build/cucumber-junit-results.xml" }, tags = {}
)
public class SampleCucumberTest {

}
