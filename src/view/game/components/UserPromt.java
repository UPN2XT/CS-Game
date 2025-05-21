package view.game.components;

import controller.game.GameController;
import engine.Game;
import engine.GameManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.player.Player;

import java.util.ArrayList;

public class UserPromt {
    public BorderPane root;
    private VBox playerData;
    private final Button playButton;
    public UserPromt(GameController gc) {
        gc.setUs(this);
        root = new BorderPane();
        // justify space between
        root.setPrefSize(1920, 1080);
        root.setPickOnBounds(false);
        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER_RIGHT);
        playButton = new Button();
        playButton.setText("Play");
        playButton.setOnMouseClicked(e -> {gc.play();});
        playButton.setPrefSize(100, 40);
        String style =
                "-fx-background-color: rgba(128, 0, 255, 0.3); " + // semi-transparent purple
                        "-fx-background-radius: 15; " +
                        "-fx-border-radius: 15; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.3); " +
                        "-fx-border-width: 1.2; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0.2, 0, 4);";
        playButton.setStyle(style);
        root.setBottom(bottom);
        bottom.getChildren().add(playButton);
        HBox top = new HBox();
        playerData = new VBox();
        playerData.setAlignment(Pos.CENTER);
        playerData.setSpacing(10);
        root.setTop(top);
        top.setAlignment(Pos.TOP_LEFT);
        top.getChildren().add(playerData);
        // purple background with rounded corners as shadow drop box for player data
        playerData.setStyle(
                "-fx-background-color: #4B0082; " +
                        "-fx-background-radius: 10px; " +
                        "-fx-padding: 10px; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-border-width: 5px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 0);"
        );
        playerData.setOpacity(0.5);
    }

    public void renderPlayerData(GameManager gm) {
        playerData.getChildren().clear();
        ArrayList<Player> players = ((Game) gm).getPlayers();
        if (gm.getActivePlayerColour() == players.get(0).getColour() && ((Game) gm).canPlayTurn()) {
            playButton.setVisible(true);
        } else {
            playButton.setVisible(false);
        }
        for (Player player : players) {
            HBox playerDataBox = new HBox();
            Text playerName = new Text(player.getName());
            Circle c = new Circle(10);
            c.setFill(BoardView.getColor(player.getColour()));
            playerName.setFill(Color.WHITE);
            playerName.setStyle(
                    "-fx-font-size: 15px; " +
                            "-fx-font-weight: bold;"
            );
            playerDataBox.setAlignment(Pos.CENTER_LEFT);
            playerDataBox.setSpacing(10);
            playerDataBox.getChildren().add(c);
            playerDataBox.getChildren().add(playerName);
            if (gm.getActivePlayerColour().equals(player.getColour())) {
                Text activePlayer = new Text("Playing");
                activePlayer.setStyle(
                        "-fx-font-size: 15px; " +
                                "-fx-font-weight: bold;"
                );
                activePlayer.setFill(Color.WHITE);
                activePlayer.setTextAlignment(TextAlignment.RIGHT);
                playerDataBox.getChildren().add(activePlayer);
            }
            else if (gm.getNextPlayerColour().equals(player.getColour())) {
                Text nextPlayer = new Text("Next");
                nextPlayer.setStyle(
                        "-fx-font-size: 15px; " +
                                "-fx-font-weight: bold;"
                );
                nextPlayer.setFill(Color.WHITE);
                nextPlayer.setTextAlignment(TextAlignment.RIGHT);
                playerDataBox.getChildren().add(nextPlayer);
            }
            playerData.getChildren().add(playerDataBox);
        }
    }

    public BorderPane getRoot() {
        return root;
    }
}
