package spaceInvaders.view.playView;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;

public class PlayView extends BorderPane {

    private Canvas canvas;

    public PlayView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        canvas = new Canvas(600, 800);
    }

    private void layoutNodes() {
        setCenter(canvas);
    }

    GraphicsContext getGraphics() {
        return canvas.getGraphicsContext2D();
    }

}
