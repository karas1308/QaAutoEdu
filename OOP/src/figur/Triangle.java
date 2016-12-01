package figur;

public class Triangle extends Figures {


    public Triangle(double x, double y, double a, double b) {
        this.setX(Math.rint(x * 100) / 100);
        this.setY(Math.rint(y * 100) / 100);
        this.a = a;
        this.b = b;
        this.setT("Треугольник  ");
    }

    @Override
    public void move(double a, double b) {
        x = x + a;
        y = y + b;
    }

    @Override
    public double area() {
        area = (a * b) / 2;
        return area = Math.rint(100.0 * area) / 100.0;
    }

    @Override
    public void resize(double rand) {
        a = a * rand;
        b = b * rand;
    }
}
