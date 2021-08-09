import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import spaceInvaders.view.mainMenu.MainMenuPresenter;
import spaceInvaders.view.mainMenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import spaceInvaders.view.playView.PlayPresenter;
import spaceInvaders.view.playView.PlayView;

public class AppLaunch extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MainMenuView view = new MainMenuView();
        new MainMenuPresenter(view);
        stage.setTitle("Space Invaders");
        stage.setScene(new Scene(view));
        stage.setResizable(false);
        stage.setHeight(800);
        stage.setWidth(600);
        stage.getIcons().add(new Image("/spaceship.png"));
        stage.show();
    }
}
