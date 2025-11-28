package com.EMR.stepDefinitions;

import com.EMR.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {
    @BeforeAll
    public static void setUp() {
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
    }

    @After
    public void takescreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }

    }

    @AfterAll
    public static void afterAll() {
        Driver.closeDriver();
    }

}
