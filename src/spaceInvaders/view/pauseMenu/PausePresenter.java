package spaceInvaders.view.pauseMenu;

import spaceInvaders.view.mainMenu.MainMenuPresenter;
import spaceInvaders.view.mainMenu.MainMenuView;

public class PausePresenter {

    private final PauseView view;

    public PausePresenter(PauseView view) {
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBackButton().setOnAction(e -> switchToMainMenu());
    }

    private void switchToMainMenu() {
        MainMenuView mainView = new MainMenuView();
        new MainMenuPresenter(mainView);
        view.getScene().setRoot(mainView);
        mainView.getScene().getWindow();
    }
}
