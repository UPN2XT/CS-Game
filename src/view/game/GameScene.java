package view.game;

import controller.game.GameController;
import engine.Game;
import engine.GameManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import view.game.components.Alerts;
import view.game.components.BoardView;
import view.game.components.Hands;
import view.game.components.UserPromt;

import java.io.IOException;

public class GameScene {
    private final StackPane root;
    private final Scene scene;
    private final GameManager game;
    private final Hands hands;
    private final GameController gc;
    private final BoardView boardView;
    private boolean play = true;
    public GameScene(GameManager game) {
        this.game = game;
        root = new StackPane();
        root.setPrefSize(1920, 1080);
        scene = new Scene(root, 1920, 1080);
        Alerts alerts = new Alerts();
        gc = new GameController((Game) game, this, alerts);
        hands = new Hands(game, gc);
        try {
            boardView = new BoardView(((Game)game).getBoard(), game, gc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserPromt us = new UserPromt(gc);
        root.getChildren().add(boardView.getRoot());
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(hands.getRoot());
        root.getChildren().add(us.getRoot());
        root.getChildren().add(alerts.getRoot());
        alerts.displayMessage("Welcome to Jackroo Pro Max 5g");
        root.setStyle(
                "-fx-padding: 30;"
        );
        root.setAlignment(Pos.CENTER);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE && play) {
                play = false;
                System.out.println("x");
                gc.play();
                play = true;
            }
        });
    }

    public void updateHands() {
        hands.updateHands(game, gc);
    }

    public Scene getScene() {
        return scene;
    }

    public void updateBoard() {
        boardView.initBoard(((Game) game).getBoard(), game);
    }


}
