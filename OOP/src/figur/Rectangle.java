package figur;

import java.util.Random;

public class Rectangle extends Figures {

    public double area, a, b, x, y;

    @Override
    public void move() {
        return;
    }

    @Override
    public double area(double a, double b) {
        area = a * b;
        return Math.rint(100.0 * area) / 100.0;
    }


    @Override
    public double resize(double a, double b) {
        Random random = new Random();
//        double resize = random.nextInt (100);
//        resize = resize / 100;
        double resize = Math.random();
        System.out.println("Коефф = " + resize);
        return a = a * b * resize;
    }


    @Override
    public void coordinates(double a, double b) {
        x = x + a;
        y = y + a;
    }
}
