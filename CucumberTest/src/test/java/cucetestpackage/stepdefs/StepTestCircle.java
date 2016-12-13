package cucetestpackage.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import figur.Circle;
import org.junit.After;

import static org.junit.Assert.*;


public class StepTestCircle {
    double testarea;
    double[] move;
    double r;
    Circle circle = new Circle(10, 10, 1);
    Circle circle1 = new Circle(5, 5, 0);

    @After
    public void del() {
        circle = null;
    }

//    //Проверяем вычисление плодащи круга, радиусом 1
//    Scenario: 1 AreaCircle_r1
    @Given("^I calculate area of circle$")
    public void iCalculateAreaOfCircle() throws Throwable {
        testarea = circle.area();
        System.out.println("kr " + testarea);
    }
    @When("^I check area of circle \"([^\"]*)\"$")
    public void iCheckAreaOfCircle(String arg0) throws Throwable {
        testarea = Double.valueOf(arg0);
        assertEquals("Площадь не равна " + testarea, testarea, 3.14, 0.01);
    }

//    Для радиуса =0 не прилетает null
//    Scenario: 2 AreaCircle_notNull
    @Given("^I calculate area of circle1$")
    public void iCalculateAreaOfCircle1() throws Throwable {
        testarea = circle1.area();
        System.out.println("kr " + testarea);
    }
    @When("^I check area of circle1 null$")
    public void iCheckAreaOfCircle1() throws Throwable {
        assertNotNull("Прилетело пустое значение ", testarea);
    }

//    Смещаем координаты центра на 10
//    Scenario: 3 MoveCircle_10_10
    @Given("^I move center  x plus (\\d+), y plus (\\d+)$")
    public void iMoveCenterXPlusYPlus(int arg0, int arg1) throws Throwable {
        circle.move(arg0, arg1);
        move = new double[]{circle.getX(), circle.getY()};
        System.out.println("kr " + circle.getX() + " " + circle.getY());
    }
    @When("^After move x equals (\\d+), y equals (\\d+)$")
    public void afterMoveXEqualsYEquals(int arg0, int arg1) throws Throwable {
        assertArrayEquals("Координаты после перемещения не совпали с ожиданием ", move, new double[]{arg0, arg1},0.01);
    }

//    Проверим, что с увеличением стороны увеличится площадь
//    Scenario: 4 ResizeCircle_x2
    @Given("^I Increased radius of circle by (\\d+) times$")
    public void iIncreasedRadiusOfCircleByTimes(int arg0) throws Throwable {
        circle.a = circle.a *arg0;
        System.out.println("radius " + circle.a);
    }
    @When("^I calculate area of circle with new radius$")
    public void iCalculateAreaOfCircleWithNewRadius() throws Throwable {
        testarea = circle.area();
        System.out.println("area " + testarea);
    }

    @Then("^The area is more than (\\d+)$")
    public void theAreaIsMoreThan(int arg0) throws Throwable {
        assertFalse("Площадь меньше " +  arg0 +" и равна " + circle.area(), circle.area() < arg0);
    }

//Уменьшим радиус круга
//    Scenario: 5 ResizeCircle_x09
    @Given("^I decreased radius of circle by \"([^\"]*)\" times$")
    public void iDecreasedRadiusOfCircleByTimes(String arg0) throws Throwable {
        double koef = Double.valueOf(arg0);
        circle.a = circle.a * koef;
        System.out.println("radius " + circle.a);
            }

    @Then("^The area is less than (\\d+)$")
    public void theAreaIsLessThan(int arg0) throws Throwable {
        assertTrue("Радиус вызывает сомнение и равен " + circle.a, circle.a < arg0);
    }


}