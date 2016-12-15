package cucetestpackage.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import figur.Rectangle;

import static org.junit.Assert.*;


public class StepTestRect {
    double a, b;
    double x, y;
    Rectangle rectangle = new Rectangle(0, 10.0, 2.0, 3.0);

    //    Площадь прямоугольника 2x3=6
//    Scenario: 1 Area of Rectangle_2_3
    @Given("^Стороны прямоугольника равны (\\d+) и (\\d+)$")
    public void стороныПрямоугольникаРавны(int arg0, int arg1) throws Throwable {
        a = arg0;
        b = arg1;
    }

    @Then("^Площадь прямоугольника равна (\\d+)$")
    public void площадьПрямоугольникаРавна(int arg0) throws Throwable {
        assertEquals("Площадь не равна " + arg0, a * b, arg0, 0);
    }

    //     Площадь прямоугольника 5x6=30
//    Scenario: 2 Area of Rectangle_5_6
    @Given("^Стороны прямоугольника равны \"([^\"]*)\" и \"([^\"]*)\"$")
    public void стороныПрямоугольникаРавныИ(String arg0, String arg1) throws Throwable {
        a = Integer.valueOf(arg0);
        b = Integer.valueOf(arg1);
    }

    @Then("^Площадь такого прямоугольника равна (\\d+)$")
    public void площадьТакогоПрямоугольникаРавна(int arg0) throws Throwable {
        assertTrue("Площадь не равна " + arg0, a * b == arg0);
    }

    //    Смещение начальной точки прямоугольника
//    Scenario: 3 TestMoveRect_10_10
    @Given("^Изменим координату x на (\\d+), y на (\\d+)$")
    public void изменимКоординатуXНаYНа(int arg0, int arg1) throws Throwable {
        rectangle.move(arg0, arg1);
    }

    @When("^Найдем координаты после смещения$")
    public void найдемКоординатыПослеСмещения() throws Throwable {
        x = rectangle.getX();
        y = rectangle.getY();
    }

    @Then("^Сравним, равен ли x (\\d+), а y (\\d+)$")
    public void сравнимРавенЛиXАY(int arg0, int arg1) throws Throwable {
        assertTrue("Координаты начальной точки прямоугольника после перемещения не совпали :( ",
                x == arg0 && y == arg1);
    }

    //    Изменение размера сторон прямоугольника
//    Scenario: 4 testResizeRect_5
    @Given("^Увеличим стороны прямоугольника в (\\d+) раз$")
    public void увеличимСтороныПрямоугольникаВРаз(int arg0) throws Throwable {
        rectangle.resize(arg0);
    }

    @When("^Найдес размер сторон после изменения$")
    public void найдесРазмерСторонПослеИзменения() throws Throwable {
        a = rectangle.a;
        b = rectangle.b;
    }

    @Then("^Проверим, равна ли сторона а (\\d+), b (\\d+)$")
    public void проверимРавнаЛиСторонаАB(double arg0, double arg1) throws Throwable {
        assertFalse("Размеры сторон после ресайза ваще не проканали ",
                a != arg0 || b != arg1);
    }
}
