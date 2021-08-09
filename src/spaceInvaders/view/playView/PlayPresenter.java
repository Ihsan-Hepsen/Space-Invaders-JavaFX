package spaceInvaders.view.playView;

import javafx.animation.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import spaceInvaders.model.GameObject;
import spaceInvaders.view.mainMenu.MainMenuPresenter;
import spaceInvaders.view.mainMenu.MainMenuView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayPresenter {

    private final PlayView view;
    private final GraphicsContext context;
    private AnimationTimer gameLoop;
    private GameObject background;
    private GameObject spaceShip;
    private final List<GameObject> aliens = new ArrayList<>();
    private final List<GameObject> lasers = new ArrayList<>();
    private final List<GameObject> alienLasers = new ArrayList<>();
    private final Random random = new Random();
    private int score = 0;
    private boolean didPlayerWin = false;
    private boolean isPaused = false;

    public PlayPresenter(PlayView view) {
        this.view = view;
        this.context = view.getGraphics();
        initGameSubstances();
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getScene().setOnKeyReleased(kr -> spaceShip.getSpeed().set(0, 0));
        view.getScene().setOnKeyPressed(this::manageKeyStrokes);
    }

    private void updateView() {
        gamePlay();
    }

    private void initGameSubstances() {
        background = new GameObject("/bg.png");
        background.getPosition().set(300, 400);

        spaceShip = new GameObject("/spaceship.png");
        spaceShip.getPosition().set(300, 700);

        for (int i = 0; i < 24; i++) {
            aliens.add(new GameObject("/alien.png"));
        }

        int horizontalGap = 0;
        int verticalGap = 0;
        for (int alienNum = 0; alienNum < aliens.size(); alienNum++) {
            aliens.get(alienNum).getPosition().set(120 + horizontalGap, 70 + verticalGap);
            horizontalGap += 70;
            if ((alienNum + 1) % 6 == 0) {
                verticalGap += 50;
                horizontalGap = 0;
            }
        }
    }

    private void gamePlay() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long time) {
                background.update(1 / 60.0);
                spaceShip.update(1 / 60.0);
                background.render(context);
                spaceShip.render(context);

                if (time % 2500 == 0) {
                    alienFire();
                }
                enemyGotShot();
                laserBlast();
                spaceShipGotShot();
                scoreIndicator();
//                alienClusterMovement();

                for (GameObject l : lasers) {
                    l.update(1 / 60.0);
                    l.render(context);
                }
                for (GameObject alien : aliens) {
                    alien.update(1 / 60.0);
                    alien.render(context);
                }
                for (GameObject alienLaser : alienLasers) {
                    alienLaser.update(1 / 60.0);
                    alienLaser.render(context);
                }
            }
        };
        gameLoop.start();
    }

    private void maneuverShip(KeyEvent e) {
        switch (e.getCode().toString()) {
            case "LEFT":
            case "A":
                spaceShip.getSpeed().set(180, 0);
                spaceShip.getSpeed().setAngle(180);
                break;
            case "RIGHT":
            case "D":
                spaceShip.getSpeed().set(180, 0);
                spaceShip.getSpeed().setAngle(0);
                break;
            case "SPACE":
                fire();
                break;
        }
    }

    private void manageKeyStrokes(KeyEvent e) {
        if (e.getCode() == KeyCode.ESCAPE) {
            if (!isPaused) {
                isPaused = true;
                gameLoop.stop();
                gamePaused();
            } else {
                gameLoop.start();
                isPaused = false;
            }
        } else {
            maneuverShip(e);
        }
    }

    private void fire() {
        GameObject laser = new GameObject("/laser.png");
        laser.getPosition().set(spaceShip.getPosition().getX() + 2.5, spaceShip.getPosition().getY() - 40);
        laser.getSpeed().set(150, 0);
        laser.getSpeed().setAngle(270);
        lasers.add(laser);
    }

    private void alienFire() {
        if (aliens.size() > 0) {
            GameObject alienLaser = new GameObject("/laser2.png");
            int randomAlien = aliens.size() == 1 ? 0 : random.nextInt(aliens.size() - 1);
            alienLaser.getPosition().set(aliens.get(randomAlien).getPosition().getX() + 10,
                    aliens.get(randomAlien).getPosition().getY() + 5);
            alienLaser.getSpeed().setLength(150);
            alienLaser.getSpeed().setAngle(90);
            alienLasers.add(alienLaser);
        } else {
            didPlayerWin = true;
            gameLoop.stop();
            switchBackToMainMenu();
        }
    }

//    private void alienClusterMovement() {
//        Timeline tl = new Timeline(new KeyFrame(Duration.ZERO, e -> {
//            for (GameObject alien : aliens) {
//                alien.getSpeed().setAngle(0);
//            }
//        }));
//        tl.play();
//    }


    private void enemyGotShot() {
        for (int laserNum = 0; laserNum < lasers.size(); laserNum++) {
            GameObject laser = lasers.get(laserNum);
            for (int alienNum = 0; alienNum < aliens.size(); alienNum++) {
                GameObject alien = aliens.get(alienNum);
                if (laser.didCollide(alien)) {
                    lasers.remove(laser);
                    aliens.remove(alien);
                    score += 50;
                }
            }
        }
    }

    private void spaceShipGotShot() {
        for (GameObject alienLaser : alienLasers) {
            if (alienLaser.didCollide(spaceShip)) {
                gameLoop.stop();
                switchBackToMainMenu();
            }
        }
    }

    private void laserBlast() {
        for (int alienLaserNum = 0; alienLaserNum < alienLasers.size(); alienLaserNum++) {
            GameObject alienLaser = alienLasers.get(alienLaserNum);
            for (int laserNum = 0; laserNum < lasers.size(); laserNum++) {
                GameObject laser = lasers.get(laserNum);
                if (laser.didCollide(alienLaser)) {
                    lasers.remove(laser);
                    alienLasers.remove(alienLaser);
                }
            }
        }
    }

    private void switchBackToMainMenu() {
        MainMenuView mainView = new MainMenuView();
        new MainMenuPresenter(mainView);
        PauseTransition delay = new PauseTransition(Duration.seconds(2.3));
        gameOverBanner();
        delay.setOnFinished(f -> {
            view.getScene().setRoot(mainView);
            mainView.getScene().getWindow();
        });
        delay.play();
    }

    private void scoreIndicator() {
        context.setStroke(Color.TURQUOISE);
        context.setFont(new Font("Ocr A Extended", 30));
        context.setLineWidth(1.5);
        String text = "SCORE:" + score;
        context.fillText(text, 15, 30);
        context.strokeText(text, 15, 30);
    }

    private void gameOverBanner() {
        String text = didPlayerWin ? "YOU WON!" : "Game Over!";
        context.setFill(Color.WHITE);
        context.setStroke(Color.INDIANRED);
        context.setFont(new Font("Ocr A Extended", 80));
        context.setLineWidth(4);
        context.fillText(text, 75, 400);
        context.strokeText(text, 75, 400);
    }

    private void gamePaused() {
        context.setFill(Color.WHITE);
        context.setStroke(Color.valueOf("fe1493"));
        context.setFont(new Font("Ocr A Extended", 80));
        context.setLineWidth(4);
        context.fillText("PAUSED", 150, 400);
        context.strokeText("PAUSED", 150, 400);
    }
}
