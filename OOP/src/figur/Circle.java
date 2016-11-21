package figur;


public class Circle extends Figures {
    private double area, r;
    @Override
    public void move() {
return ;
    }

    @Override
    public double area(double r, double r1) {
        area = Math.PI * r * r1;
        return area;
    }

    @Override
    public double resize(double a, double b) {
        return 0;
    }


    @Override
    public void coordinates(double a, double b) {
        return ;
    }
}
