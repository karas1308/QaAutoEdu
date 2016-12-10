package figur;


public abstract class Figures {
    public double x, y, a, b, area, r;
    String t;

    public abstract void move(double a, double b);

    public double area() {
        return area();
    }

    public abstract void resize(double rand);

    public double getX() {
        return this.x;
    }

    ;

    public void setX(double x) {
        this.x = x;
    }

    ;

    public double getY() {
        return this.y;
    }

    ;

    public void setY(double y) {
        this.y = y;
    }

    ;

    public double getA() {
        return this.a;
    }

    ;

    public double getB() {
        return this.b;
    }

    ;

    public String getT() {
        return this.t;
    }

    public void setT(String t) {
        this.t = t;
    }
}
