package cucetestpackage.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
plugin = {"html:target/cucumber-report/test"},
features = "src/test/java/cucetestpackage/features",
glue = "cucetestpackage/stepdefs")

public class TestRun {

}
