package figur;
public class Triangle extends Figures {

    private double area;
    @Override
    public void move() {
        return;
    }

    @Override
    public double area(double a, double b) {
        area = (a * b)/2;
        return area=Math.rint(100.0 * area) / 100.0;


    }

    @Override
    public double resize(double a, double b) {
        return 0;
    }

    @Override
    public void coordinates(double a, double b) {
        return;
    }
}
