package figur;

import java.util.Random;

public class Rectangle extends Figures {

    @Override
    public void move(double a, double b) {
        x = x + 10;
        y = y + 20;
    }

    @Override
    public double area() {
        a = Math.random() * 10;  //Высота прямоугольника
        b = Math.random() * 10;  //Ширина прямоугольника
        area = a * b;
        return Math.rint(100.0 * area) / 100.0;
    }

    @Override
    public double resize() {
        Random random = new Random();
        double resize = Math.random();
        a = a * resize;
        b = b * resize;
        area = a * b;
        return Math.rint(100.0 * area) / 100.0;
    }

    @Override
    public void coordinates(double x, double y) {
        x = 50;
        y = 50;
    }
}
