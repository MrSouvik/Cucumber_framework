package com.Runner;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/Test Scenarios",
    glue = {"com.Steps","com.Utility"},
    plugin = {"pretty", "com.Utility.CustomReporter"},
    monochrome = true
    
)
public class TestRunner {
}

