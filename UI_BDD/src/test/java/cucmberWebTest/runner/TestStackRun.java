package cucmberWebTest.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report/test"},
        features = "src/test/java/cucmberWebTest/features",
        tags = "@Stack",
        glue = "cucmberWebTest/stepdefs/OverStack")

public class TestStackRun {
}
