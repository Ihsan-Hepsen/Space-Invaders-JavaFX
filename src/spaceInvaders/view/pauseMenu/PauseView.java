package spaceInvaders.view.pauseMenu;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PauseView extends BorderPane {

    private Label viewTitle;
    private VBox mainContainer;
    private Label controlsL;
    private Label controlsR;
    private Label controlsFire;
    private Label pause;
    private Button backButton;

    public PauseView() {
        initializeNodes();
        layoutNodes();
        styling();
    }


    private void initializeNodes() {
        viewTitle = new Label("CONTROLS");
        mainContainer = new VBox();
        controlsL = new Label("Move Left   : A, Left Arrow");
        controlsR = new Label("Move Right  : D, Right Arrow");
        controlsFire = new Label("Shoot Laser : Space");
        pause = new Label("Pause Game  : Esc");
        backButton = new Button("Back to Menu");
    }

    private void layoutNodes() {
        setAlignment(viewTitle, Pos.CENTER);
        setTop(viewTitle);

        setAlignment(controlsL, Pos.CENTER);
        setAlignment(controlsR, Pos.CENTER);
        setAlignment(controlsFire, Pos.CENTER);
        setAlignment(pause, Pos.CENTER);
        mainContainer.setAlignment(Pos.CENTER_LEFT);
        mainContainer.setPadding(new Insets(0, 0, 0, 60));
        mainContainer.getChildren().addAll(controlsL, controlsR, controlsFire, pause);
        mainContainer.setSpacing(20);
        setCenter(mainContainer);

        setAlignment(backButton, Pos.CENTER);
        setBottom(backButton);

        this.setPadding(new Insets(30, 10, 30, 10));
    }

    private void styling() {
        this.setStyle("-fx-background-color: #000");
        viewTitle.setStyle("-fx-text-fill: #1de3da; -fx-font-size: 60; -fx-font-family: 'OCR A Extended';");
        backButton.setStyle("-fx-border-color: #1de3da; -fx-font-size: 30; -fx-text-fill: #fff;" +
                " -fx-background-color: #000; -fx-font-family: 'OCR A Extended'");
        final String textStyle = "-fx-text-fill: #fff; -fx-font-size: 28; -fx-font-family: 'OCR A Extended';";
        controlsL.setStyle(textStyle);
        controlsR.setStyle(textStyle);
        controlsFire.setStyle(textStyle);
        pause.setStyle(textStyle);
    }

    Button getBackButton() {
        return backButton;
    }

}
