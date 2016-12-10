package TestFigur;

import figur.Circle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCircle {

    public Circle circle;
    public Circle circle1;

    @Before
    public void newCircle() {
        circle = new Circle(10, 10, 1);
        circle1 = new Circle(5, 5, 0);
    }

    @After
    public void del() {
        circle = null;
    }
//Проверяем вычисление плодащи круга, радиусом 1
    @Test
    public void testAreaCircle() {
        double testarea = circle.area();
       // System.out.println(testarea);
        assertEquals("Площадь не равна " + testarea, testarea, 3.14, 0.01);
    }

    @Test
    public void testAreaCircle_a0() {
        String testarea1 = null;
        testarea1 = String.valueOf(circle1.area());
        //System.out.println(testarea1);
        assertNotNull("Прилетело пустое значение ", testarea1);
    }

    @Test
    public void testMoveCircle_10_10() {
        circle.move(10, 10);
        double[] move={circle.getX(),circle.getY()};
        assertArrayEquals("Координаты после перемещения не совпали с ожиданием ", move, new double[]{20, 20},1);
    }



    //Проверим, что с увеличением стороны увеличится площадь
    @Test
    public void testResizeCircle_x2() {
        circle.resize(2);
       // System.out.println(circle.area());
        assertFalse("Площадь не увеличилась и равна " + circle.area(), circle.area() < 12);

    }

    //Уменьшим сторону круга
    @Test
    public void testResizeCircle_x09() {
        circle.resize(0.9);
       // System.out.println(circle.a);
        assertTrue("Сторона не уменьшилась  и равна " + circle.a, circle.a < 1);
    }
}


//    @Override
//    public void move(double a, double b) {
//        x = x + a;
//        y = y + b;
//    }
//
//    @Override
//    public double area() {
//        area = Math.PI * a * a;
//        return area = Math.rint(100.0 * area) / 100.0;
//    }
//
//    @Override
//    public void resize(double rand) {
//        a = a * rand;
//    }
//}