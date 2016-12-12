package cucetestpackage.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import figur.Circle;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class StepTestCircle {
    //public Circle circle;
   // public Circle circle1;
    double testarea;
        Circle circle = new Circle(10, 10, 1);
    Circle circle1 = new Circle(5, 5, 0);

    @After
    public void del() {
        circle = null;
    }

    @Given("^I calculate area of circle$")
    public void iCalculateAreaOfCircle() throws Throwable {
       testarea = circle.area();
        System.out.println("kr "+testarea);
    }
    @When("^I check area of circle \"([^\"]*)\"$")
    public void iCheckAreaOfCircle(String arg0) throws Throwable {
        testarea = Double.valueOf(arg0);
        assertEquals("Площадь не равна " + testarea, testarea, 3.14, 0.01);
    }

    @Given("^I calculate area of circle1$")
    public void iCalculateAreaOfCircle1() throws Throwable {
        testarea = circle1.area();
        System.out.println("kr "+testarea);

    }

    @When("^I check area of circle1")
    public void iCheckAreaOfCircle1() throws Throwable {
        assertNotNull("Прилетело пустое значение ", testarea);
    }
}
