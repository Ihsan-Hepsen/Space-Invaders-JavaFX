package spaceInvaders.model;

public class Rectangle {

    private double x;
    private double y;
    private double width;
    private double height;


    public Rectangle() {
        setPosition(0, 0);
        setSize(1, 1);
    }

    public Rectangle(double x, double y, double width, double height) {
        setPosition(x, y);
        setSize(width, height);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public boolean didCollide(Rectangle other) {
        // 4 cases where there will be a contact between a ball and stick
        // this to the left of other
        // this to the right of other
        // this is above other
        // other is above this
        boolean noCollision = x + width < other.x ||
                other.x + other.width < x ||
                y + height < other.y ||
                other.y + other.height < y;

        return !noCollision;
    }
}
