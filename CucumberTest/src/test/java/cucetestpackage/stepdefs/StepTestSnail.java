package cucetestpackage.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import games.Snail;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class StepTestSnail {
    int n, zeroValue = 1;
    int[][] snail;
    int maxValue;
    int sum = 0;

    //    Элемент [0][4] матрицы размером 5х5 = 25
//    Scenario: 1 testElem1and5nail
    @Given("^Размер матрицы (\\d+)$")
    public void размерМатрицы(int arg0) throws Throwable {
        n = arg0;
    }

    @When("^Запишем матрицу в массив$")
    public void запишемМатрицуВМассив() throws Throwable {
        snail = Snail.calculateSnail(n);
    }

    @Then("^Проверим, равен ли последний элемент в перввой строке (\\d+)$")
    public void проверимРавенЛиПоследнийЭлементВПерввойСтроке(int arg0) throws Throwable {
        assertTrue("Что-то пошло не так " + snail[0][snail.length - 1],
                snail[0][snail.length - 1] == arg0);
    }

    //------------------------------------------------------------
    @Given("^Размер  матрицы (\\d+)$")
    public void размерМатрицы1(int arg0) throws Throwable {
        n = arg0;
    }

    @When("^Запишем матрицу  в массив$")
    public void запишемМатрицуВМассив1() throws Throwable {
        snail = Snail.calculateSnail(n);
    }

    @Then("^Проверим, равен ли последний элемент в последней строке (\\d+)$")
    public void проверимРавенЛиПоследнийЭлементВПоследнейСтроке(int arg0) throws Throwable {
        assertTrue("Элемент (нижний правый)не равен " + arg0,
                snail[snail.length - 1][snail.length - 1] == arg0);
    }

    //----------------------------------------------------
    @Given("^Размер  матрицы= (\\d+)$")
    public void размерМатрицы2(int arg0) throws Throwable {
        n = arg0;
    }

    @When("^Запишем матрицу   в массив$")
    public void запишемМатрицуВМассив2() throws Throwable {
        snail = Snail.calculateSnail(n);
    }

    @And("^Найдем самой самое большое значение$")
    public void найдемСамойСамоеБольшоеЗначение() throws Throwable {
        for (int i = 0; i < snail.length; i++) {
            for (int j = 0; j < snail.length; j++) {
                if (snail[i][j] > maxValue) {
                    maxValue = snail[i][j];
                }
            }
        }
    }

    @Then("^Максимальное значение (\\d+)$")
    public void максимальноеЗначение(int arg0) throws Throwable {
        assertTrue("Максимальное значение не равно размерности массива в квадрате " +
                arg0, maxValue == arg0);
    }

    //----------------------------------------------------
    @Given("^Размер  матрицы=(\\d+)$")
    public void размерМатрицы3(int arg0) throws Throwable {
        n = arg0;
    }

    @When("^Запишем  матрицу в массив$")
    public void запишемМатрицуВМассив3() throws Throwable {
        snail = Snail.calculateSnail(n);
    }

    @And("^Проверим, что в матрице нет нулей$")
    public void проверимЧтоВМатрицеНетНулей() throws Throwable {
        for (int i = 0; i < snail.length; i++) {
            for (int j = 0; j < snail.length; j++) {
                if (snail[i][j] == 0) {
                    zeroValue = snail[i][j];
                    break;
                }
            }
        }
    }

    @Then("^Поорем, если (\\d+) в матрице есть$")
    public void пооремЕслиВМатрицеЕсть(int arg0) throws Throwable {
        System.out.print(zeroValue);
        assertFalse("В матрице есть нулевые элементы, переделывай! ", zeroValue == 0);
    }

    //-----------------------------------------------------
    @Given("^Размер  матрицы =(\\d+)$")
    public void размерМатрицы4(int arg0) throws Throwable {
        n = arg0;
    }

    @And("^Запишем матрицу в массивчик$")
    public void запишемМатрицуВМассивчик() throws Throwable {
        snail = Snail.calculateSnail(n);
    }

    @When("^Просуммируем элеменнты матрицы$")
    public void просуммируемЭлеменнтыМатрицы() throws Throwable {
        for (int i = 0; i < snail.length; i++) {
            for (int j = 0; j < snail.length; j++) {
                sum = sum + snail[i][j];
            }
        }
    }

    @Then("^Сумма элементов равна (\\d+)$")
    public void суммаЭлементовРавна(int arg0) throws Throwable {
        assertTrue("Сумма элементов не равна " + arg0, sum == arg0);
    }
}
