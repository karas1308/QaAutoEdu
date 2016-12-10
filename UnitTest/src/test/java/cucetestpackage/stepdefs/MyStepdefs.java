package cucetestpackage.stepdefs;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static games.Matrix.matrix;
import static org.junit.Assert.assertEquals;


public class MyStepdefs {
  int[][] mt;
  int k,i,j;
    @Given("^Input \"([^\"]*)\" matrix size$")
    public void inputMatrixSize(String n) throws Throwable {
        this.mt= matrix(Integer.valueOf(n));
           }
    @When("^I looking for \"([^\"]*)\" \"([^\"]*)\" element$")
    public void i_looking_for_element(String arg1, String arg2) throws Throwable {
        i= Integer.valueOf(arg1);
        j= Integer.valueOf(arg2);
        System.out.println(mt[i][j]);
    }
    @Then("^Tenth element isEquals \"([^\"]*)\"$")
    public void tenth_element_isEquals(String m) throws Throwable {
        k = Integer.valueOf(m);
        assertEquals("Что-то пошло не так ", mt[i][j], k);
    }
}
