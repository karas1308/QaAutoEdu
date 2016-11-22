package figur;

import java.util.Random;

public class Circle extends Figures {
    private double area, r, x, y;

    @Override
    public void move(double a, double b) {
        x = x + 5;
        y = y + 10;
    }

    @Override
    public double area(double r, double r1) {
        area = Math.PI * r * r1;
        return area = Math.rint(100.0 * area) / 100.0;
    }

    @Override
    public double resize(double r, double r1) {
        Random random = new Random();
//      double resize = random.nextInt (100);
//      resize = resize / 100;
        double resize = Math.random();
        r = r * resize;
        area = Math.PI * r * r1;
        return area = Math.rint(100.0 * area) / 100.0;
    }

    @Override
    public void coordinates(double x, double y) {
        x = Math.random() * 200;
        y = Math.random() * 200;
    }
}
