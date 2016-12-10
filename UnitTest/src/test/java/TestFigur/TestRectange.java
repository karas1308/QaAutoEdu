package TestFigur;

import figur.Rectangle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestRectange {
    public Rectangle rectangle;
    public Rectangle rectangle1;

    @Before
    public void newRectangle() {
        rectangle = new Rectangle(0, 10.0, 2.0, 3.0);
        rectangle1 = new Rectangle(10, 20.0, 5.0, 6.0);
    }

    @After
    public void del() {
        rectangle = null;
        rectangle1 = null;
    }
    @Test
    public void testAreaRect_2_3() {
        double testarea = rectangle.area();
         System.out.println(testarea);
        assertEquals("Площадь не равна " + testarea, testarea, 6, 0.01);
    }
    @Test
    public void testAreaRect_5_6() {
        double testarea = rectangle1.area();
        System.out.println(testarea);
        assertTrue("Площадь не равна " + testarea, testarea==30);
    }
    @Test
    public void testMoveRect_10_10() {
        rectangle.move(10,10);

        assertTrue("Координаты начальной точки прямоугольника после перемещения не совпали :( ",
                rectangle.getX()==10 && rectangle.getY()==20);
    }
    @Test
    public void testResizeRect_10() {
        rectangle.resize(5);

        assertFalse("Размеры сторон после ресайза ваще не проканали ",
                rectangle.a!=10 && rectangle.b!=15);
    }
}
//    public Rectangle(double x, double y, double a, double b) {
//        this.setX(Math.rint(x * 100) / 100);
//        this.setY(Math.rint(y * 100) / 100);
//        this.a = a;
//        this.b = b;
//        this.setT("Прямоугольник");
//    }
//
//    @Override
//    public void move(double a, double b) {
//        x = x + a;
//        y = y + b;
//    }
//
//    @Override
//    public double area() {
//        area = a * b;
//        return Math.rint(100.0 * area) / 100.0;
//    }
//
//    @Override
//    public void resize(double rand) {
//        a = a * rand;
//        b = b * rand;
//    }
//}
