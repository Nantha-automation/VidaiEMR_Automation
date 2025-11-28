package com.EMR.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import com.EMR.utilities.BrowserUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ExtentReports Manager for generating detailed HTML test reports with screenshots
 * Supports step-by-step test execution reporting
 */
@SuppressWarnings("unused")
public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private static final String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReports/";

    /**
     * Initialize ExtentReports with custom configuration
     */
    public static void initializeExtentReports() {
        if (extentReports == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
            String fileName = reportPath + "ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);

            // Configure report appearance
            sparkReporter.config().setReportName("Vidai AI-Powered EMR BDD Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            // Add system information
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Browser", ConfigurationReader.get("browser"));
            extentReports.setSystemInfo("Application URL", ConfigurationReader.get("url"));
            extentReports.setSystemInfo("Environment", "Test");
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
    }

    /**
     * Create a test entry in the report for a scenario
     *
     * @param scenario Cucumber Scenario
     */
    public static void createTest(Scenario scenario) {
        extentTest = extentReports.createTest(scenario.getName());
        extentTest.assignCategory(scenario.getSourceTagNames().toArray(new String[0]));
    }

    /**
     * Log a step with PASS status
     *
     * @param message Step description
     */
    public static void logStepPass(String message) {
        String logMsg = "✓ " + message;
        if (extentTest != null) {
            extentTest.pass(logMsg);
        } else {
            System.out.println("[EXTENT NULL] " + logMsg);
        }
    }

    /**
     * Log a step with FAIL status
     *
     * @param message Step description
     */
    public static void logStepFail(String message) {
        String logMsg = "✗ " + message;
        if (extentTest != null) {
            extentTest.fail(logMsg);
        } else {
            System.out.println("[EXTENT NULL] " + logMsg);
        }
    }

    /**
     * Log a step with FAIL status and attach screenshot inline
     * Captures screenshot and adds it directly to the fail log entry
     *
     * @param message Step description
     * @param stepName Name of the step for screenshot naming
     */
    public static void logStepFailWithScreenshot(String message, String stepName) {
        String logMsg = "✗ " + message;
        if (extentTest != null) {
            try {
                // Create the fail node first
                com.aventstack.extentreports.ExtentTest failNode = extentTest.fail(logMsg);
                
                // Capture and attach screenshot to the same fail node
                String screenshotPath = BrowserUtils.getScreenshot(stepName.replaceAll("[^a-zA-Z0-9-_]", "_"));
                String fileName = new File(screenshotPath).getName();
                String relativePath = "../Screenshots/" + fileName;
                failNode.addScreenCaptureFromPath(relativePath, stepName + " - FAILED");
            } catch (Exception e) {
                extentTest.fail(logMsg + " | Screenshot capture failed: " + e.getMessage());
            }
        } else {
            System.out.println("[EXTENT NULL] " + logMsg);
        }
    }

    /**
     * Log a step with INFO status
     *
     * @param message Step description
     */
    public static void logStepInfo(String message) {
        String logMsg = "ℹ " + message;
        if (extentTest != null) {
            extentTest.info(logMsg);
        } else {
            System.out.println("[EXTENT NULL] " + logMsg);
        }
    }

    /**
     * Log a step with WARNING status
     *
     * @param message Step description
     */
    public static void logStepWarning(String message) {
        String logMsg = "⚠ " + message;
        if (extentTest != null) {
            extentTest.warning(logMsg);
        } else {
            System.out.println("[EXTENT NULL] " + logMsg);
        }
    }

    /**
     * Attach screenshot to the report
     * Captures screenshot and attaches it with a description
     *
     * @param description Description for the screenshot
     */
    public static void attachScreenshot(String description) {
        if (extentTest != null) {
            try {
                String screenshotPath = BrowserUtils.getScreenshot(description.replaceAll("[^a-zA-Z0-9-_]", "_"));
                // Use relative path from report to screenshots folder so the HTML can load images correctly
                String fileName = new File(screenshotPath).getName();
                String relativePath = "../Screenshots/" + fileName;
                extentTest.addScreenCaptureFromPath(relativePath, description);
            } catch (Exception e) {
                extentTest.info("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }

    /**
     * Capture screenshot on test failure with status
     *
     * @param scenario Cucumber Scenario
     */
    public static void captureScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed() && extentTest != null) {
            try {
                String screenshotPath = BrowserUtils.getScreenshot(("Failure_" + scenario.getName()).replaceAll("[^a-zA-Z0-9-_]", "_"));
                String fileName = new File(screenshotPath).getName();
                String relativePath = "../Screenshots/" + fileName;
                extentTest.addScreenCaptureFromPath(relativePath, "Screenshot at Failure - " + scenario.getName());
            } catch (Exception e) {
                extentTest.info("Failed to capture screenshot on failure: " + e.getMessage());
            }
        }
    }

    /**
     * Capture screenshot in Base64 format
     *
     * @return Base64 encoded screenshot
     */
    // NOTE: Base64 screenshot method removed — using file-based screenshots via BrowserUtils.getScreenshot()

    /**
     * Update test status based on scenario result
     *
     * @param scenario Cucumber Scenario
     */
    public static void updateTestStatus(Scenario scenario) {
        if (scenario.isFailed()) {
            extentTest.fail("Test failed: " + scenario.getName());
        } else if (scenario.getStatus().name().equalsIgnoreCase("SKIPPED")) {
            extentTest.skip("Test skipped: " + scenario.getName());
        } else {
            extentTest.pass("Test passed: " + scenario.getName());
        }
    }

    /**
     * Flush the report to file
     */
    public static void flushExtentReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    /**
     * Get the current ExtentTest instance for manual operations if needed
     *
     * @return ExtentTest instance
     */
    public static ExtentTest getExtentTest() {
        return extentTest;
    }

    /**
     * Get the ExtentReports instance for advanced configurations
     *
     * @return ExtentReports instance
     */
    public static ExtentReports getExtentReports() {
        return extentReports;
    }
}
