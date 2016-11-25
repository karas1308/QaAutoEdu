package figur;

import java.util.Random;

public class Triangle extends Figures {

    @Override
    public void move(double a, double b) {
        x = x + 1;
        y = y + 2;
    }

    @Override
    public double area() {
        a = Math.random() * 10;   // Основание треугольника
        b = Math.random() * 10;   //Высота треугольника
        area = (a * b) / 2;
        return area = Math.rint(100.0 * area) / 100.0;
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
        x = 40;
        y = 40;
    }
}
