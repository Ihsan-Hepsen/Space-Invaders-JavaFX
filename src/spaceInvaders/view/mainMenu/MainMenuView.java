package spaceInvaders.view.mainMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMenuView extends BorderPane {

    private Label title;
    private Button playButton;
    private Button controlsButton;
    private VBox buttonContainer;
    private final Label version = new Label("v1.2.6");

    public MainMenuView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        title = new Label("SPACE INVADERS");
        playButton = new Button("PLAY");
        controlsButton = new Button("CONTROLS");
        buttonContainer = new VBox();
    }

    private void layoutNodes() {
        playButton.setPrefWidth(200);
        controlsButton.setPrefWidth(200);

        playButton.setMinHeight(60);
        controlsButton.setMinHeight(60);

        buttonContainer.getChildren().addAll(playButton, controlsButton);
        buttonContainer.setSpacing(50);
        buttonContainer.setPadding(new Insets(12.0));

        buttonContainer.setAlignment(Pos.CENTER);
        setAlignment(title, Pos.CENTER);
        setAlignment(version, Pos.CENTER);

        setTop(title);
        setCenter(buttonContainer);
        setBottom(version);

        this.setPadding(new Insets(60, 10, 30, 10));
        styling();
    }

    private void styling() {
        this.setStyle("-fx-background-color: #000;");
        title.setStyle("-fx-text-fill: #1de3da; -fx-font-size: 60; -fx-font-family: 'OCR A Extended';");
        version.setStyle("-fx-text-fill: #fff; -fx-font-size: 18; -fx-font-family: 'OCR A Extended';");
        final String buttonStyling = "-fx-border-color: #1de3da; -fx-font-size: 30; -fx-text-fill: #fff;" +
                " -fx-background-color: #000; -fx-font-family: 'OCR A Extended'";
        playButton.setStyle(buttonStyling);
        controlsButton.setStyle(buttonStyling);
    }

    Button getPlayButton() {
        return playButton;
    }

    Button getControlsButton() {
        return controlsButton;
    }

}
