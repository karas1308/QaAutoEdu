package figur;

public class Circle extends Figures {
    private double r;

    public Circle(double x, double y, double a) {
        this.setX(Math.rint(x * 100) / 100);
        this.setY(Math.rint(y * 100) / 100);
        this.a = a;
        this.setT("Круг         ");
    }

    @Override
    public void move(double a, double b) {
        x = x + a;
        y = y + b;
    }

    @Override
    public double area() {
        area = Math.PI * a * a;
        return area = Math.rint(100.0 * area) / 100.0;
    }

    @Override
    public void resize(double rand) {
        a = a * rand;
    }
}
