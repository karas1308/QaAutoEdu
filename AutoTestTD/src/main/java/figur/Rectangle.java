package figur;

public class Rectangle extends Figures {

    public Rectangle(double x, double y, double a, double b) {
        this.setX(Math.rint(x * 100) / 100);
        this.setY(Math.rint(y * 100) / 100);
        this.a = a;
        this.b = b;
        this.setT("Прямоугольник");
    }

    @Override
    public void move(double a, double b) {
        x = x + a;
        y = y + b;
    }

    @Override
    public double area() {
        area = a * b;
        return Math.rint(100.0 * area) / 100.0;
    }

    @Override
    public void resize(double rand) {
        a = a * rand;
        b = b * rand;
    }
}
