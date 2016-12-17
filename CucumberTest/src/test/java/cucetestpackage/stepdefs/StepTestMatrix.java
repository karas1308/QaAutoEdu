package cucetestpackage.stepdefs;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static games.Matrix.matrix;
import static org.junit.Assert.*;


public class StepTestMatrix {
    int[][] mt;
    int k, i, j, b9;
    int x = 0;
    int y = 0;
    int count = 0;
    String str = "";

    @Given("^Input \"([^\"]*)\" matrix size$")
    public void inputMatrixSize(String n) throws Throwable {
        mt = matrix(Integer.valueOf(n));
    }

    @When("^I looking for \"([^\"]*)\" \"([^\"]*)\" element$")
    public void i_looking_for_element(String arg1, String arg2) throws Throwable {
        i = Integer.valueOf(arg1);
        j = Integer.valueOf(arg2);
        System.out.println(mt[i][j]);
    }

    @Then("^Tenth element isEquals \"([^\"]*)\"$")
    public void tenth_element_isEquals(String m) throws Throwable {
        k = Integer.valueOf(m);
        assertEquals("Что-то пошло не так ", mt[i][j], k);
    }

    //    ------------------------------------------------------------------------
////Проверяем, чтоб в матрице максимальный элемент был 9
//    Scenario: 1 MaxElemMatr_8x8
      @When("^I looking for element more than (\\d+)$")
    public void iLookingForElementMoreThan(int arg0) throws Throwable {
        for (x = 0; x < mt.length; x++) {
            for (y = 0; y < mt.length; y++) {
                if (mt[x][y] > arg0) {
                    b9 = mt[x][y];
                    break;
                }
            }
            break;
        }
    }

    @Then("^Alarm because I found elenemt more than (\\d+)$")
    public void alarmBecauseIFoundElenemtMoreThan(int arg0) throws Throwable {
        assertFalse("В матрице затесались элементы больше " + arg0 + " на месте элемента " + x + " " + y, b9 > arg0);
    }

    //    ------------------------------------------------------------------------
//    Количество элеметов матрицы
//    Scenario: 2 CountElemMatr_7x7
    @And("^Count elements$")
    public void countElements() throws Throwable {
        for (int i = 0; i < mt.length; i++) {
            for (int j = 0; j < mt.length; j++) {
                count++;
            }
        }
    }

    @Then("^Count of elemets equals (\\d+)$")
    public void countOfElemetsEquals(int arg0) throws Throwable {
        assertTrue("Количество элементов не равно 49, а равно " + count, count == arg0);
    }

    //    ------------------------------------------------------------------------
    //  Последняя строка матрицы не равна 789
//    Scenario: LastLine_789_3x3
    @Given("^Input matrix size (\\d+)$")
    public void inputSizeMatrix(int arg0) throws Throwable {
        mt = matrix(arg0);
    }

    @When("^I looking for all elements of line (\\d+)$")
    public void iLookingForAllElementsOfLine(int arg0) throws Throwable {
        for (int i = arg0 - 1; i < arg0; i++) {
            for (int j = 0; j < arg0; j++) {
                str += mt[i][j];
            }
            break;
        }
    }

    @Then("^Last line equals \"([^\"]*)\"$")
    public void lastLineEquals(String arg0) throws Throwable {
        assertTrue("Последняя строка матрицы не равна " + arg0 + ", а равна " + str, str.equals(arg0));
    }
}
