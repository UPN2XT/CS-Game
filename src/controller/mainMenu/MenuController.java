package controller.mainMenu;

import engine.Game;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import view.game.GameScene;

import java.io.IOException;

public class MenuController {
    private final StringProperty playerName;
    private final Stage primaryStage;

    public MenuController(Stage primaryStage) {
        this.playerName = new SimpleStringProperty("");
        this.primaryStage = primaryStage;
    }

    public StringProperty getPlayerName() {
        return playerName;
    }

    public void startGame() throws IOException {
        // Logic to start the game
        if (playerName.get().isEmpty()) {
            System.out.println("Please enter a player name.");
            return;
        }
        Game game = new Game(playerName.get());
        GameScene gameScene = new GameScene(game, primaryStage);
        primaryStage.setScene(gameScene.getScene());
    }
}
