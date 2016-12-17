package cucetestpackage.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import figur.Circle;

import static org.junit.Assert.*;

public class StepTestCircle {
    private double testarea;
    private double[] move;
    private double r;
    private Circle circle;

    //    //Проверяем вычисление плодащи круга, радиусом 1
//    Scenario: 1 AreaCircle_r1
    @Given("^I Create circle with radius (\\d+)$")
    public void iCreateCircleWithRadius(int arg0) throws Throwable {
        circle = new Circle(0, 0, arg0);
    }

    @And("^I calculate area of circle$")
    public void iCalculateAreaOfCircle() throws Throwable {
        testarea = circle.area();
    }

    @When("^I check area of circle \"([^\"]*)\"$")
    public void iCheckAreaOfCircle(String arg0) throws Throwable {
        testarea = Double.valueOf(arg0);
        assertEquals("Площадь не равна " + testarea, testarea, 3.14, 0.01);
    }

    //    Смещаем координаты центра на 10
//    Scenario: 3 MoveCircle_10_10
    @And("^I move center x plus (\\d+), y plus (\\d+)$")
    public void iMoveCenterXPlusYPlus(int arg0, int arg1) throws Throwable {
        circle.move(arg0, arg1);
        move = new double[]{circle.getX(), circle.getY()};
    }

    @Then("^After move x equals (\\d+), y equals (\\d+)$")
    public void afterMoveXEqualsYEquals(int arg0, int arg1) throws Throwable {
        assertArrayEquals("Координаты после перемещения не совпали с ожиданием ", move, new double[]{arg0, arg1}, 0.01);
    }
    @Given("^I Create circle with coordinates (\\d+) (\\d+)$")
    public void iCreateCircleWithCoordinates(int arg0, int arg1) throws Throwable {
        circle = new Circle(arg0, arg1, 0);
    }
    //    Проверим, что с увеличением стороны увеличится площадь
//    Scenario: 4 ResizeCircle_x2

    @Then("^The area is more than (\\d+)$")
    public void theAreaIsMoreThan(int arg0) throws Throwable {
        assertFalse("Площадь меньше " + arg0 + " и равна " + circle.area(), circle.area() < arg0);
    }

    //Уменьшим радиус круга
//    Scenario: 5 ResizeCircle_x09
    @And("^I change radius of circle by \"([^\"]*)\" times$")
    public void iChangeRadiusOfCircleByTimes(String arg0) throws Throwable {
        double koef = Double.valueOf(arg0);
        circle.a = circle.a * koef;
    }

    @Then("^The area is less than (\\d+)$")
    public void theAreaIsLessThan(int arg0) throws Throwable {
        assertTrue("Радиус вызывает сомнение и равен " + circle.a, circle.a < arg0);
    }
}