package com.EMR.stepDefinitions;

import com.EMR.utilities.Driver;
import com.EMR.utilities.ExtentReportManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {
    /**
     * One-time setup before all tests run
     * Initializes ExtentReports
     */
    @BeforeAll
    public static void setUpBeforeAll() {
        ExtentReportManager.initializeExtentReports();
    }

    /**
     * Setup before each scenario
     * Maximizes window, sets implicit wait, creates test entry in report
     */
    @Before
    public void setUp(Scenario scenario) {
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        // Initialize test in ExtentReports
        ExtentReportManager.createTest(scenario);
        ExtentReportManager.logStepInfo("Started scenario: " + scenario.getName());
    }

    /**
     * Teardown after each scenario
     * Updates test status in report
     * Screenshots are now attached inline with failed steps
     */
    @After
    public void takesScreenshot(Scenario scenario) {
        try {
            // Attach to Cucumber report for Cucumber HTML report
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            }

            // Update test status in ExtentReports
            ExtentReportManager.updateTestStatus(scenario);

        } catch (Exception e) {
            ExtentReportManager.logStepWarning("Error during screenshot capture: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * One-time teardown after all tests complete
     * Closes WebDriver and flushes ExtentReports
     */
    @AfterAll
    public static void teardown() {
        Driver.closeDriver();
        ExtentReportManager.flushExtentReports();
    }

}
