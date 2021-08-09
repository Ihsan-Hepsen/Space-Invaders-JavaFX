package spaceInvaders.model;

public class Vector {

    private double x;
    private double y;


    public Vector() {
        set(0, 0);
    }

    public Vector(double x, double y) {
        set(x, y);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public void multiply(double value) {
        this.x *= value;
        this.y *= value;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public void setLength(double len) {
        double currentLength = getLength();
        if (currentLength == 0) {
            this.set(len, 0);
        } else {
            multiply(1 / currentLength);
            multiply(len);
        }
    }

    public double getAngle() {
        return Math.toDegrees(Math.atan2(y, x));
    }

    public void setAngle(double angle) {
        double len = getLength();
        double angleRadians = Math.toRadians(angle);
        x = len * Math.cos(angleRadians);
        y = len * Math.sin(angleRadians);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
