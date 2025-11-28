# ExtentReports Integration Guide

## Overview

ExtentReports has been integrated into the EMR BDD automation framework to provide comprehensive test reporting with:
- ✅ Step-by-step execution logging
- ✅ Screenshots for both passed and failed tests
- ✅ System information capture
- ✅ Beautiful HTML dashboard with charts
- ✅ Test categorization by tags

## Setup

### What Was Added

1. **ExtentReportManager.java** - Core utility class for reporting
2. **Updated Hooks.java** - Automatic screenshot capture for all scenarios
3. **Updated CukesRunner.java** - ExtentReports Cucumber adapter plugin
4. **extent.properties** - Report configuration
5. **extent-config.xml** - Report styling

### Dependencies

The following dependencies are already in `pom.xml`:
```xml
<dependency>
    <groupId>com.aventstack</groupId>
    <artifactId>extentreports</artifactId>
    <version>5.0.9</version>
</dependency>
<dependency>
    <groupId>tech.grasshopper</groupId>
    <artifactId>extentreports-cucumber7-adapter</artifactId>
    <version>1.13.0</version>
</dependency>
```

## How to Use ExtentReports

### Automatic Screenshot Capture (Hooks)

Screenshots are **automatically** captured by the updated Hooks class:

```java
@After
public void tearDown(Scenario scenario) {
    if (scenario.isFailed()) {
        // Screenshot automatically captured and attached
        ExtentReportManager.captureScreenshotOnFailure(scenario);
    } else {
        // Screenshot automatically captured and attached
        ExtentReportManager.captureScreenshotOnSuccess(scenario);
    }
}
```

**No manual setup needed** - just run your tests!

### Manual Step Logging

Add detailed step-by-step logging in your step definition files:

#### Example 1: Simple Step Logging

```java
@When("User clicks login button")
public void userClicksLoginButton() {
    try {
        loginPage.clickLoginButton();
        ExtentReportManager.logStepPass("Login button clicked successfully");
    } catch (Exception e) {
        ExtentReportManager.logStepFail("Failed to click login button: " + e.getMessage());
        throw e;
    }
}
```

#### Example 2: With Screenshots

```java
@When("User fills login form")
public void userFillsLoginForm() {
    try {
        loginPage.enterUsername("testuser");
        ExtentReportManager.logStepPass("Username entered");
        
        loginPage.enterPassword("password123");
        ExtentReportManager.logStepPass("Password entered");
        
        // Capture screenshot of filled form
        ExtentReportManager.attachScreenshot("Login form filled");
        
    } catch (Exception e) {
        ExtentReportManager.logStepFail("Form filling failed: " + e.getMessage());
        ExtentReportManager.attachScreenshot("Form filling error");
        throw e;
    }
}
```

#### Example 3: Multi-Step Verification

```java
@Then("Verify dashboard loads correctly")
public void verifyDashboard() {
    try {
        ExtentReportManager.logStepInfo("Starting dashboard verification");
        
        // Step 1: Check title
        String expectedTitle = "Dashboard";
        String actualTitle = Driver.get().getTitle();
        Assert.assertEquals("Dashboard title mismatch", expectedTitle, actualTitle);
        ExtentReportManager.logStepPass("Step 1: Dashboard title verified");
        
        // Step 2: Check main content
        boolean contentVisible = isMainContentVisible();
        Assert.assertTrue("Main content not visible", contentVisible);
        ExtentReportManager.logStepPass("Step 2: Main content visible");
        
        // Step 3: Capture final screenshot
        ExtentReportManager.attachScreenshot("Dashboard loaded successfully");
        
        ExtentReportManager.logStepPass("All dashboard verifications passed");
        
    } catch (Exception e) {
        ExtentReportManager.logStepFail("Dashboard verification failed: " + e.getMessage());
        ExtentReportManager.attachScreenshot("Dashboard verification failed");
        throw e;
    }
}
```

### Available Methods

#### Logging Methods

```java
// Log a passed step
ExtentReportManager.logStepPass("Action completed successfully");

// Log a failed step
ExtentReportManager.logStepFail("Action failed with error");

// Log informational message
ExtentReportManager.logStepInfo("Starting verification process");

// Log a warning
ExtentReportManager.logStepWarning("Potential issue detected");
```

#### Screenshot Methods

```java
// Attach screenshot to current step
ExtentReportManager.attachScreenshot("Description of screenshot");

// Automatically capture on failure (in Hooks)
ExtentReportManager.captureScreenshotOnFailure(scenario);

// Automatically capture on success (in Hooks)
ExtentReportManager.captureScreenshotOnSuccess(scenario);
```

#### Management Methods

```java
// Get the current ExtentTest for advanced operations
ExtentTest test = ExtentReportManager.getExtentTest();

// Get the ExtentReports instance
ExtentReports reports = ExtentReportManager.getExtentReports();

// Manually flush reports to file
ExtentReportManager.flushExtentReports();
```

## Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Tag

```bash
# Edit CukesRunner.java tags field, then:
mvn test
```

### Run and Generate Reports

```bash
mvn clean test
```

Reports will be automatically generated after test execution.

## Report Locations

After running tests, find reports at:

### ExtentReports (Custom HTML with Embedded Screenshots)
```
test-output/ExtentReports/ExtentReport_YYYY-MM-DD_HH-MM-SS.html
```
- Most detailed report
- Base64 embedded screenshots
- Step-by-step logs with status indicators
- System information
- Test timeline

### Spark Report (Cucumber Adapter)
```
test-output/SparkReport/index.html
```
- Professional dashboard
- Charts and analytics
- Test categorization
- Failure analysis

### Cucumber HTML Report
```
target/default-html-reports.html
```
- Standard Cucumber report
- Feature-based organization
- Step definitions mapping

### Screenshots Directory (Individual Files)
```
test-output/screenshots/
```
- Individual screenshot files
- Named with test name and timestamp

### Cucumber JSON (Machine-Readable)
```
target/cucumber.json
```
- Machine-readable format
- Used for CI/CD integrations

## Best Practices

### 1. Log At Key Points

```java
// ✅ GOOD: Log key actions and verifications
ExtentReportManager.logStepPass("User logged in");
ExtentReportManager.attachScreenshot("Post-login screen");

// ❌ AVOID: Logging every single action
ExtentReportManager.logStepInfo("Found element");
ExtentReportManager.logStepInfo("Element is clickable");
ExtentReportManager.logStepInfo("Clicked element");
```

### 2. Use Appropriate Log Levels

```java
// Pass - for successful actions
ExtentReportManager.logStepPass("Form submitted successfully");

// Fail - for errors within try-catch
ExtentReportManager.logStepFail("Form submission failed");

// Info - for informational messages
ExtentReportManager.logStepInfo("Starting payment process");

// Warning - for potential issues
ExtentReportManager.logStepWarning("Element not immediately visible, used wait");
```

### 3. Screenshot Strategy

```java
// Capture at important points:
ExtentReportManager.attachScreenshot("After login");
ExtentReportManager.attachScreenshot("Before payment");
ExtentReportManager.attachScreenshot("Error displayed");
ExtentReportManager.attachScreenshot("Order confirmed");

// Automatic screenshots also captured on pass/fail by Hooks
```

### 4. Error Handling

```java
@When("User performs action")
public void userPerformsAction() {
    try {
        actionPage.performAction();
        ExtentReportManager.logStepPass("Action performed successfully");
        ExtentReportManager.attachScreenshot("Action result");
        
    } catch (Exception e) {
        ExtentReportManager.logStepFail("Action failed: " + e.getMessage());
        ExtentReportManager.attachScreenshot("Action failure");
        throw e; // Re-throw for Hooks to capture failure
    }
}
```

## Configuration

### Modify Report Output Path

Edit `src/test/resources/extent.properties`:
```properties
extent.reporter.spark.out=test-output/SparkReport/index.html
```

### Customize Report Theme

Edit `src/test/resources/extent-config.xml`:
```xml
<theme>dark</theme>  <!-- dark or standard -->
```

### Add System Information

In `ExtentReportManager.initializeExtentReports()`:
```java
extentReports.setSystemInfo("Custom Info", "Value");
```

## Troubleshooting

### Report Not Generated

- ✅ Ensure `mvn clean test` is used (not just `mvn test`)
- ✅ Check that `test-output/` directory has write permissions
- ✅ Verify ExtentReports dependencies in `pom.xml`

### Screenshots Not Embedded

- ✅ Ensure `ExtentReportManager.attachScreenshot()` is called
- ✅ Check that WebDriver is still active
- ✅ Verify screenshot capture doesn't fail (see logs)

### Report File Too Large

- ✅ Reduce screenshot resolution
- ✅ Remove unnecessary log statements
- ✅ Run fewer tests per report

## Integration with CI/CD

### GitHub Actions Example

```yaml
- name: Run Tests and Generate Reports
  run: mvn clean test
  
- name: Upload ExtentReports
  if: always()
  uses: actions/upload-artifact@v2
  with:
    name: extent-reports
    path: test-output/ExtentReports/
```

### Jenkins Example

```groovy
stage('Test') {
    steps {
        sh 'mvn clean test'
    }
}

stage('Publish Reports') {
    steps {
        publishHTML([
            reportDir: 'test-output/ExtentReports',
            reportFiles: 'ExtentReport_*.html',
            reportName: 'ExtentReports'
        ])
    }
}
```

## Tips & Tricks

### Generate Report on Demand

```java
// Call manually after tests if needed
ExtentReportManager.flushExtentReports();
```

### Access Current Test Instance

```java
// For advanced operations
ExtentTest test = ExtentReportManager.getExtentTest();
test.createNode("Child Node");
```

### Check Report Generation Status

Look for files at:
```bash
ls -la test-output/ExtentReports/
ls -la test-output/SparkReport/
```

## Support & Documentation

- **ExtentReports**: https://www.extentreports.com/
- **Cucumber Adapter**: https://github.com/grasshopper-tech/extentreports-cucumber7-adapter
- **Selenium Screenshots**: https://www.selenium.dev/documentation/webdriver/
