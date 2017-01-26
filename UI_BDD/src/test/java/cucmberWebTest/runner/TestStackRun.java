package cucmberWebTest.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report/test"},
        features = "src/test/java/cucmberWebTest/features",
        tags = "@Snail, @Matrix, @Circle, @Square, @Palindrom, @Non-static, @SnailSpecial",
        glue = "cucmberWebTest/stepdefs")

public class TestStackRun {

}
