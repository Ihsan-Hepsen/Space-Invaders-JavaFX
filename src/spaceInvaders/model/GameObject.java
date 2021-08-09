package spaceInvaders.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {

    private final Vector position;
    private final Vector speed;
    private final Rectangle bounds;
    private Image image;
    private final double rotation;

    public GameObject() {
        position = new Vector();
        speed = new Vector();
        bounds = new Rectangle();
        rotation = 0;
    }

    public GameObject(String imageFileName) {
        this();
        setImage(imageFileName);
    }

    private void setImage(String imageFileName) {
        image = new Image(imageFileName);
        bounds.setSize(image.getWidth(), image.getHeight());
    }

    public void render(GraphicsContext context) {
        context.save();
        context.translate(position.getX(), position.getY());
        context.rotate(rotation);
        context.translate(-image.getHeight() / 2, -image.getHeight() / 2);
        context.drawImage(image, 0, 0);
        context.restore();
    }

    public void update(double deltaTime) {
        position.add(speed.getX() * deltaTime, speed.getY() * deltaTime);
    }

    public Rectangle getBounds() {
        bounds.setPosition(position.getX(), position.getY());
        bounds.setSize(image.getWidth() / 1.4, image.getHeight() / 1.4);
        return bounds;
    }

    public boolean didCollide(GameObject other) {
        return getBounds().didCollide(other.getBounds());
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getSpeed() {
        return speed;
    }

}
