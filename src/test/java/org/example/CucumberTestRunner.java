package org.example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "org.example.steps"
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}