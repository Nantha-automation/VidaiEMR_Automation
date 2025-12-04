package com.EMR.utilities;

import io.cucumber.java.Scenario;

/**
 * Thread-safe context to share Scenario object between Hooks and Step Definitions
 * This is necessary because Cucumber only injects Scenario into @Before/@After hooks,
 * not regular step definitions
 */
public class ScenarioContext {
    
    private static ThreadLocal<Scenario> scenarioThreadLocal = new ThreadLocal<>();
    
    /**
     * Store the current scenario (called from Hooks)
     */
    public static void setScenario(Scenario scenario) {
        scenarioThreadLocal.set(scenario);
    }
    
    /**
     * Get the current scenario (called from Step Definitions)
     */
    public static Scenario getScenario() {
        return scenarioThreadLocal.get();
    }
    
    /**
     * Clear the scenario after test completion
     */
    public static void clearScenario() {
        scenarioThreadLocal.remove();
    }
}
