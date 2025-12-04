# Scenario Parameter Fix - Implementation Summary

## Problem Description
When running Cucumber tests, encountered the following error:
```
io.cucumber.core.exception.CucumberException: 
Step [User searches the newly registered patient by selecting the sperm donor radio button using JSON test data] 
is defined with 1 parameters at 'user_searches_the_newly_registered_patient_by_selecting_the_sperm_donor_radio_button_using_json_test_data(io.cucumber.java.Scenario)'. 
However, the gherkin step has 0 arguments.
```

## Root Cause
- **Cucumber injects `Scenario` parameter automatically** only in hooks (`@Before`, `@After`, `@BeforeAll`, `@AfterAll`)
- **Regular step definitions** (`@Given`, `@When`, `@Then`, `@And`) cannot directly accept `Scenario` as a parameter
- The step definition was trying to accept `Scenario scenario` as a method parameter, which Cucumber interpreted as expecting a Gherkin argument

## Solution Implemented
Created a **thread-safe context class** to share the `Scenario` object between Hooks and Step Definitions.

### 1. Created `ScenarioContext.java`
**Location**: `src/test/java/com/EMR/stepDefinitions/ScenarioContext.java`

```java
public class ScenarioContext {
    private static ThreadLocal<Scenario> scenarioThreadLocal = new ThreadLocal<>();
    
    public static void setScenario(Scenario scenario) {
        scenarioThreadLocal.set(scenario);
    }
    
    public static Scenario getScenario() {
        return scenarioThreadLocal.get();
    }
    
    public static void clearScenario() {
        scenarioThreadLocal.remove();
    }
}
```

**Key Features**:
- Uses `ThreadLocal` for thread safety (important for parallel test execution)
- Provides setter, getter, and cleanup methods
- Enables sharing of `Scenario` object across step definitions

### 2. Updated `Hooks.java`
**Changes Made**:

**In `setUp()` method**:
```java
@Before
public void setUp(Scenario scenario) {
    // Store scenario in context for use in step definitions
    ScenarioContext.setScenario(scenario);
    
    Driver.get().manage().window().maximize();
    // ... rest of setup code
}
```

**In `takesScreenshot()` method**:
```java
@After
public void takesScreenshot(Scenario scenario) {
    try {
        // ... screenshot and status update logic
    } catch (Exception e) {
        // ... exception handling
    } finally {
        // Clear scenario from context after test completes
        ScenarioContext.clearScenario();
    }
}
```

### 3. Updated `Registration_StepDefs.java`
**Changes Made**:

**Before (❌ Caused Error)**:
```java
@And("User searches the newly registered patient by selecting the sperm donor radio button using JSON test data")
public void user_searches_the_newly_registered_patient_by_selecting_the_sperm_donor_radio_button_using_json_test_data(Scenario scenario) {
    String stepName = "...";
    BrowserUtils.executeStep(stepName, () -> {
        registration.selectSearchByRadioOptionUsingTag(scenario);
    });
}
```

**After (✅ Fixed)**:
```java
@And("User searches the newly registered patient by selecting the sperm donor radio button using JSON test data")
public void user_searches_the_newly_registered_patient_by_selecting_the_sperm_donor_radio_button_using_json_test_data() {
    String stepName = "...";
    BrowserUtils.executeStep(stepName, () -> {
        registration.selectSearchByRadioOptionUsingTag(ScenarioContext.getScenario());
    });
}
```

**Also Updated**:
- `user_searches_the_newly_registered_patient_by_selecting_the_egg_donor_radio_button_using_json_test_data()`
- Removed unused `io.cucumber.java.Scenario` import

## How It Works

### Execution Flow
```
1. Cucumber starts scenario
   ↓
2. Hooks.setUp() called
   ↓
3. ScenarioContext.setScenario(scenario) stores scenario in ThreadLocal
   ↓
4. Step definitions execute
   ↓
5. Step calls ScenarioContext.getScenario() to retrieve scenario
   ↓
6. Step passes scenario to page object methods (e.g., selectSearchByRadioOptionUsingTag())
   ↓
7. Hooks.takesScreenshot() called
   ↓
8. ScenarioContext.clearScenario() cleans up ThreadLocal
```

### Thread Safety
- Each test thread has its own `Scenario` object stored in `ThreadLocal`
- Prevents conflicts when running tests in parallel
- Automatic cleanup in `@After` hook prevents memory leaks

## Usage Pattern

### For Step Definitions Needing Scenario Object:
```java
@When("Some step that needs scenario information")
public void some_step() {
    String stepName = "Some step that needs scenario information";
    BrowserUtils.executeStep(stepName, () -> {
        Scenario currentScenario = ScenarioContext.getScenario();
        
        // Use scenario object
        String scenarioName = currentScenario.getName();
        Collection<String> tags = currentScenario.getSourceTagNames();
        
        // Pass to page objects
        pageObject.someMethod(currentScenario);
    });
}
```

### For Page Object Methods:
```java
public void selectSearchByRadioOptionUsingTag(Scenario scenario) {
    Collection<String> tags = scenario.getSourceTagNames();
    
    // Extract registration type from tags
    String registrationType = tags.stream()
        .filter(tag -> tag.contains("patient") || tag.contains("sperm_donor") || 
                      tag.contains("oocyte_donor") || tag.contains("surrogate"))
        .findFirst()
        .orElse("@patient");
    
    // Use type to select appropriate radio button
    // ... rest of logic
}
```

## Benefits

### ✅ Advantages
1. **Thread-safe**: Works correctly with parallel test execution
2. **Clean separation**: Follows Cucumber's design - hooks handle lifecycle, steps handle actions
3. **No Gherkin changes**: Feature files remain unchanged
4. **Reusable**: Can be used by any step definition that needs scenario information
5. **Type-safe**: No string parsing or reflection needed
6. **Maintainable**: Centralized in one utility class

### ⚠️ Important Notes
1. Always clean up in `@After` hook to prevent memory leaks
2. Use `getScenario()` only in step definitions, not in hooks (hooks already have it injected)
3. Check for null if calling `getScenario()` outside normal test flow
4. ThreadLocal ensures each test thread has its own scenario instance

## Testing Results
- ✅ Compilation successful (no compile errors)
- ✅ Tests execute without parameter mismatch errors
- ✅ Scenario tags are correctly extracted and used
- ✅ Radio button selection based on tags works as expected

## Files Modified
1. **Created**: `src/test/java/com/EMR/stepDefinitions/ScenarioContext.java`
2. **Modified**: `src/test/java/com/EMR/stepDefinitions/Hooks.java`
3. **Modified**: `src/test/java/com/EMR/stepDefinitions/Registration_StepDefs.java`

## Related Documentation
- See `EXTENT_REPORTS_GUIDE.md` for ExtentReports integration with Scenario
- See `copilot-instructions.md` for project architecture and patterns
- See `registration.feature` for test scenarios using this pattern

---
**Date**: December 4, 2025
**Status**: ✅ Implemented and Verified
**Impact**: All registration tests with tag-based radio selection now work correctly
