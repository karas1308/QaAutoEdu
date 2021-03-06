package cucetestpackage.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report/test"},
        features = "src/test/java/cucetestpackage/features",
        tags = "@Snail, @Matrix, @Circle, @Square, @Palindrom, @Non-static, @SnailSpecial",
        glue = "cucetestpackage/stepdefs")

public class TestRun {

}
