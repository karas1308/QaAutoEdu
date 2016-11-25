package figur;

import java.util.Random;

public class Circle extends Figures {
    private double r;

    @Override
    public void move(double a, double b) {
        x = x + 5;
        y = y + 10;
    }

    @Override
    public double area() {
        r = Math.random() * 10;  //Радиус
        area = Math.PI * r * r;
        return area = Math.rint(100.0 * area) / 100.0;
    }

    @Override
    public double resize() {
        Random random = new Random();
        double resize = Math.random();
        r = r * resize;
        area = Math.PI * r * r;
        return area = Math.rint(100.0 * area) / 100.0;
    }

    @Override
    public void coordinates(double x, double y) {
        x = 20;
        y = 20;
    }
}
