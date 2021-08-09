package spaceInvaders.view.mainMenu;

import javafx.stage.Stage;
import spaceInvaders.view.pauseMenu.PausePresenter;
import spaceInvaders.view.pauseMenu.PauseView;
import spaceInvaders.view.playView.PlayPresenter;
import spaceInvaders.view.playView.PlayView;

public class MainMenuPresenter {

    private final MainMenuView view;

    public MainMenuPresenter(MainMenuView view) {
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getPlayButton().setOnAction(clicked -> startGame());
        view.getControlsButton().setOnAction(clicked -> displayControlsScreen());
    }

    private void startGame() {
        PlayView playView = new PlayView();
        view.getScene().setRoot(playView);
        Stage stage = (Stage) playView.getScene().getWindow();
        new PlayPresenter(playView);
    }

    private void displayControlsScreen() {
        PauseView pauseView = new PauseView();
        new PausePresenter(pauseView);
        view.getScene().setRoot(pauseView);
        pauseView.getScene().getWindow();
    }

}
