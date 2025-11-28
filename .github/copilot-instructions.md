# AI Copilot Instructions for EMR BDD Automation Project

## Project Overview
This is a **Selenium + Cucumber BDD automation framework** testing the EMR (Electronic Medical Records) application. The project uses Page Object Model (POM) pattern with Gherkin feature files driving test execution.

### Tech Stack
- **Language**: Java 25
- **Test Framework**: Cucumber 7.12.0 (JUnit runner)
- **Browser Automation**: Selenium 4.8.3 with WebDriverManager
- **Build Tool**: Maven 3.0.0
- **Test Data**: JSON (Jackson) + Properties configuration
- **Reporting**: Maven Cucumber Reporting + ExtentReports

## Architecture & Key Components

### 1. **Layer Structure** (`src/test/java/com/EMR/`)

#### `pages/` - Page Object Model (POM)
- **BasePage**: Abstract parent class initializing PageFactory for all pages
  - Provides common methods like `navigateToTabs()`, `getTabMenuNames()`
  - All page classes extend BasePage
- **Feature pages**: `HomePage.java`, `LoginPage.java`, `ViewCartPage.java`, etc.
  - Contain `@FindBy` annotations (Selenium Page Factory)
  - Encapsulate page-specific actions (e.g., `logout()`, `validLogin()`)
  - **Important**: Actions are public methods, locators are private WebElement fields

#### `stepDefinitions/` - Cucumber Glue Code
- **Hooks.java**: Lifecycle management
  - `@BeforeAll`: Maximizes window, sets 6-second implicit wait
  - `@After`: Captures screenshots on test failure
  - `@AfterAll`: Closes driver
- **\*_StepDefs.java**: Implements Gherkin steps
  - Instantiate page objects
  - Map When/Then/Given annotations to step definitions
  - Use assertions from `org.junit.Assert`

#### `utilities/` - Infrastructure
- **Driver.java**: Singleton WebDriver management
  - Supports multiple browsers (Chrome, Firefox, Edge, Safari, IE) via browser config
  - Uses WebDriverManager for automatic driver setup
  - **Critical**: Always use `Driver.get()` for WebDriver access
- **BrowserUtils.java**: 400+ lines of helper methods
  - Screenshot capture, waits, hover, window switching, element interactions
  - Use `BrowserUtils.click()`, `BrowserUtils.hover()`, etc. (not raw Selenium)
- **ConfigurationReader.java**: Properties file loader
  - Reads `configuration.properties` at class initialization
  - Provides values via `ConfigurationReader.get("key")`
- **JsonUtils.java**: JSON test data access
  - Loads JSON from path in config (default: `src/test/resources/testData/testData.json`)
  - Provides structured test data via `JsonUtils.getValue("parent", "child")`

### 2. **Test Execution Flow**

```
Feature File (.feature) → Cucumber Parser
  ↓
Step Definition (@When/@Then/@Given) → Page Object Method
  ↓
BrowserUtils Helper → Driver.get() (WebDriver)
  ↓
Hooks (@Before/@After) → Browser State
```

### 3. **Configuration & Test Data**

**configuration.properties** (root):
- `browser=chrome` (or `chrome-headless`, `firefox`, `firefox-headless`, etc.)
- `url=https://staging-use1.vidaisolutions.com/login`
- `testdata_path=src/test/resources/testData/testData.json`
- Credentials and form data (firstName, email, password, etc.)

**testData/testData.json**:
- Structured test data as nested JSON objects
- Access via `JsonUtils.getValue("validLogin", "username")`

**Feature Files** (`src/test/resources/features/`):
- Gherkin syntax with `@regression`, `@smoke`, `@login` tags
- **Background** section runs before each scenario
- Scenarios map to step definitions via regex or exact text match

### 4. **Test Runner Configuration**

**CukesRunner.java**:
- `@RunWith(Cucumber.class)` + `@CucumberOptions` annotations
- `features`: Scans `src/test/resources/features` for .feature files
- `glue`: Step definitions in `com.EMR.stepDefinitions` package
- `plugin`: Generates `target/cucumber.json` and HTML reports
- `tags`: Filter execution (e.g., `tags = "@login"`)
- `dryRun = false`: Executes steps (not just validation)

## Critical Workflows

### Running Tests
```bash
# All tests
mvn test

# Specific tag (configured in CukesRunner)
# Modify @CucumberOptions tags field, then:
mvn test

# Clean before running
mvn clean test
```

### Adding a New Test Scenario
1. Create `.feature` file in `src/test/resources/features/`
2. Write Gherkin steps with `@tag` annotation
3. Create or update `*_StepDefs.java` in `stepDefinitions/` package
4. Implement `@Given`, `@When`, `@Then` methods
5. Create/update page class in `pages/` with locators (`@FindBy`) and actions
6. Use `BrowserUtils` for interactions, `ConfigurationReader.get()` for data, `Driver.get()` for WebDriver

### Debugging Failed Tests
- **Screenshots**: Captured automatically on failure in `target/surefire-reports/`
- **Cucumber Report**: `target/default-html-reports.html` shows step-by-step execution
- **Implicit Waits**: 6 seconds set in Hooks; use `BrowserUtils.waitFor*()` methods for explicit waits
- **Check selectors**: Verify XPath/CSS in `pages/*` `@FindBy` annotations match current UI

## Code Patterns & Conventions

### Page Class Pattern
```java
public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    public void enterEmail(String email) {
        BrowserUtils.sendKeys(emailField, email);
    }
}
```

### Step Definition Pattern
```java
@When("The user enters correct username and password")
public void user_enters_login_info() {
    loginPage.validLogin();  // Calls page action, not raw Selenium
}
```

### Using Test Data
```java
String username = JsonUtils.getValue("validLogin", "username");
String browser = ConfigurationReader.get("browser");
```

### Wait Patterns
- **Implicit**: 6 seconds in Hooks (default for all elements)
- **Explicit**: `BrowserUtils.waitForVisibility(element, 20)` for specific elements
- **Custom**: Use `BrowserUtils` methods for click, hover, wait

## Project-Specific Conventions

1. **Never use raw Selenium waits** - Use `BrowserUtils` utilities
2. **Page objects always extend BasePage** - Ensures PageFactory initialization
3. **Encapsulate selectors in Page classes** - All `@FindBy` should be private/package-private
4. **JSON test data structure**: Parent object → child properties (2-level hierarchy expected)
5. **Configuration keys**: Check `configuration.properties` before hardcoding values
6. **Assertion style**: Use `org.junit.Assert.assertEquals/assertTrue` (not AssertJ)
7. **Screenshots**: Generated automatically on failure; manual capture via `BrowserUtils.getScreenshot(name)`

## Key Directories to Know

- `src/test/resources/features/` - Gherkin feature files (17 scenarios)
- `src/test/java/com/EMR/pages/` - Page Object classes
- `src/test/java/com/EMR/stepDefinitions/` - Step implementations + Hooks
- `src/test/java/com/EMR/utilities/` - Driver, BrowserUtils, ConfigurationReader
- `src/test/java/com/EMR/runners/` - CukesRunner test executor
- `target/` - Build output, cucumber.json, HTML reports
- `configuration.properties` - Centralized configuration (root)

## Common Pitfalls

- ❌ Using `Thread.sleep()` instead of `BrowserUtils.wait*()`
- ❌ Hardcoding URLs/credentials instead of using `ConfigurationReader`
- ❌ Direct Selenium WebDriver calls instead of `BrowserUtils` helpers
- ❌ Creating new WebDriver instances instead of using `Driver.get()`
- ❌ Page classes that don't extend BasePage (PageFactory won't initialize)
- ❌ Storing selectors as public fields (should be private with public action methods)

## ExtentReports Integration

### Overview
The project now includes **ExtentReports** for comprehensive test reporting with:
- ✅ Step-by-step execution logs with status (Pass/Fail/Skip/Info/Warning)
- ✅ Screenshots for both passed and failed tests (Base64 embedded)
- ✅ System information capture (OS, Browser, Java version, URL)
- ✅ Detailed HTML dashboard with charts and trends
- ✅ Test categorization by tags

### How to Use ExtentReports in Step Definitions

**Log steps with status indicators:**
```java
ExtentReportManager.logStepPass("User successfully logged in");
ExtentReportManager.logStepFail("Login verification failed");
ExtentReportManager.logStepInfo("Executing login action...");
ExtentReportManager.logStepWarning("Element not found, retrying...");
```

**Attach screenshots to steps:**
```java
ExtentReportManager.attachScreenshot("Login page loaded");
ExtentReportManager.attachScreenshot("Error message displayed");
```

**Complete step definition example:**
```java
@When("User enters valid credentials")
public void userEntersCredentials() {
    try {
        loginPage.enterUsername(ConfigurationReader.get("username"));
        ExtentReportManager.logStepPass("Username entered successfully");
        
        loginPage.enterPassword(ConfigurationReader.get("password"));
        ExtentReportManager.logStepPass("Password entered successfully");
        ExtentReportManager.attachScreenshot("Credentials Entered");
        
    } catch (Exception e) {
        ExtentReportManager.logStepFail("Failed to enter credentials: " + e.getMessage());
        ExtentReportManager.attachScreenshot("Credentials Entry Failed");
        throw e;
    }
}
```

### Hooks Automation

**Hooks.java** now automatically:
1. Initializes ExtentReports before all tests (`@BeforeAll`)
2. Creates test entry in report before each scenario (`@Before`)
3. Captures screenshots on success and failure (`@After`)
4. Attaches screenshots to both ExtentReports and Cucumber JSON report
5. Updates test status in report based on scenario result
6. Flushes reports after all tests complete (`@AfterAll`)

**No additional setup needed** - reports are generated automatically.

### Report Files & Locations

After running `mvn test`, find reports at:
- **ExtentReports HTML**: `test-output/ExtentReports/ExtentReport_[timestamp].html` (with Base64 embedded screenshots)
- **Spark Report**: `test-output/SparkReport/index.html` (via adapter plugin)
- **Cucumber HTML**: `target/default-html-reports.html`
- **Cucumber JSON**: `target/cucumber.json`
- **Surefire Reports**: `target/surefire-reports/TEST-*.xml`
- **Screenshots**: `test-output/screenshots/` (individual files if needed)

### Configuration Files

- `src/test/resources/extent.properties` - ExtentReports properties (report output path, metadata)
- `src/test/resources/extent-config.xml` - Report theme and styling configuration
- `src/test/java/com/EMR/utilities/ExtentReportManager.java` - Core reporting utility

### Key Features

| Feature | Location | Usage |
|---------|----------|-------|
| Initialize Reports | `Hooks.setUp()` | Automatic |
| Create Test | `Hooks.setUp(Scenario)` | Automatic |
| Log Step Pass | `ExtentReportManager.logStepPass()` | Manual in step defs |
| Log Step Fail | `ExtentReportManager.logStepFail()` | Manual in step defs |
| Log Info | `ExtentReportManager.logStepInfo()` | Manual in step defs |
| Log Warning | `ExtentReportManager.logStepWarning()` | Manual in step defs |
| Attach Screenshot | `ExtentReportManager.attachScreenshot()` | Manual in step defs |
| Auto Screenshot on Failure | `Hooks.tearDown()` | Automatic |
| Auto Screenshot on Success | `Hooks.tearDown()` | Automatic |
| Update Test Status | `Hooks.tearDown()` | Automatic |
| Flush Reports | `Hooks.afterAll()` | Automatic |

### Sample Implementation

See `SampleExtentReportSteps.java` for complete examples of:
- Logging step-by-step execution
- Attaching screenshots at key points
- Handling assertions with ExtentReports
- Multi-step verification with detailed logging

## Report Access

- **ExtentReports (Custom)**: `test-output/ExtentReports/ExtentReport_[timestamp].html` (detailed with Base64 screenshots)
- **Spark Report**: `test-output/SparkReport/index.html` (via Cucumber adapter)
- **HTML Report**: `target/default-html-reports.html` (Cucumber standard)
- **Cucumber JSON**: `target/cucumber.json` (machine-readable for CI/CD)
- **Surefire Reports**: `target/surefire-reports/TEST-*.xml`
