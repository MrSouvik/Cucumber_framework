package com.Utility;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import io.cucumber.plugin.event.Status;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CustomReporter implements ConcurrentEventListener {

    private static ExtentReports extent;
    private static ExtentTest featureTest;
    private static ExtentTest scenarioTest;

    public CustomReporter() throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestSourceRead.class, this::handleFeature);
        publisher.registerHandlerFor(TestCaseStarted.class, this::handleScenario);
        publisher.registerHandlerFor(TestStepFinished.class, this::handleStep);
        publisher.registerHandlerFor(TestRunFinished.class, event -> extent.flush());
    }

    private void handleFeature(TestSourceRead event) {
        String featureName = event.getUri().getPath().replace(".feature", "").replaceAll(".*/", "");
        featureTest = extent.createTest("Feature: " + featureName);
    }

    private void handleScenario(TestCaseStarted event) {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        scenarioTest = featureTest.createNode(event.getTestCase().getName());
    }

    private void handleStep(TestStepFinished event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
            String stepText = testStep.getStep().getKeyword() + testStep.getStep().getText();

            WebDriver driver = Hooks.driver;
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

            if (event.getResult().getStatus() == Status.PASSED) {
                scenarioTest.pass(stepText).addScreenCaptureFromBase64String(base64Screenshot, "Step Screenshot");
            } else if (event.getResult().getStatus() == Status.FAILED) {
                Throwable error = event.getResult().getError();
                scenarioTest.fail(stepText + "\n" + error.getMessage())
                            .addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");
            }
        }
    }
}

