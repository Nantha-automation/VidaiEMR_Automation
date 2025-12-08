package com.EMR.stepDefinitions;

import com.EMR.pages.Login;
import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.ConfigurationReader;
import com.EMR.utilities.Driver;
import com.EMR.utilities.ExtentReportManager;
import com.EMR.utilities.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {

    private static boolean isLoggedIn = false;

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
        // Store scenario in context for use in step definitions
        ScenarioContext.setScenario(scenario);
        
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        // Set browser zoom to 75%
        // JavascriptExecutor js = (JavascriptExecutor) Driver.get();
        // js.executeScript("document.body.style.zoom = '75%';");

        // Initialize test in ExtentReports
        ExtentReportManager.createTest(scenario);
        ExtentReportManager.logStepInfo("Started scenario: " + scenario.getName());
    }

    /**
     * Login once before the first @calendar or @registration scenario
     * Subsequent scenarios will reuse the same session
     * This hook does NOT run for @login scenarios (they test login functionality)
     */
    @Before(value = "@calendar or @registration", order = 1)
    public void loginOnce() {
        if (!isLoggedIn) {
            Login loginPage = new Login();

            ExtentReportManager.logStepInfo("Performing one-time login for calendar/registration scenarios");
            Driver.get().get(ConfigurationReader.get("url"));
            BrowserUtils.waitForPageToLoad(5);
            loginPage.validLogin();
            loginPage.loginButtonClick();

            // Wait for successful login
            try {
                Thread.sleep(2000);
                isLoggedIn = true;
                ExtentReportManager.logStepPass("Login successful - session will be reused");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            ExtentReportManager.logStepInfo("Reusing existing login session");
        }
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
        } finally {
            // Clear scenario from context after test completes
            ScenarioContext.clearScenario();
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
