package com.EMR.utilities;

/**
 * Functional interface for step execution logic
 * Used with BrowserUtils.executeStep() for clean step definition code
 */
@FunctionalInterface
public interface StepExecutor {
    /**
     * Execute the step logic
     * @throws Exception Any exception thrown during step execution
     */
    void execute() throws Exception;
}
