package view.mainMenu;

import controller.mainMenu.MenuController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.game.GameScene;

import java.io.IOException;

public class Menu {
    private final Scene scene;
    private final VBox root;
    private final MenuController controller;

    public Menu(Stage primaryStage) {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        this.scene = new Scene(root, 600, 600);
        controller = new MenuController(primaryStage);
        initialize();
        root.setBackground(new Background(GameScene.getBg("background.png")));
    }

    private Text initializeHeader() {
        Text header = new Text("Dark Jackroo");
        header.setStyle(
                "-fx-font-size: 50px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: white;"
        );
        return header;
    }

    private TextField inizializePlayerName() {
        TextField playName = new TextField();
        playName.setPromptText("Enter your name");
        playName.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-padding: 10px;" +
                        "-fx-background-color: rgba(128, 0, 255, 0.3);" +
                        "-fx-border-color: rgba(255, 255, 255, 0.4);" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-text-fill: white;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0.3, 0, 2);"
        );
        playName.setAlignment(Pos.CENTER);
        playName.setMaxWidth(250);
        playName.textProperty().bindBidirectional(controller.getPlayerName());
        return playName;
    }

    private Button initializeStartButton() {
        Button startButton = new Button("Start Game");
        startButton.setStyle(
                "-fx-background-color: rgba(128, 0, 255, 0.3);" +
                        "-fx-border-color: rgba(255, 255, 255, 0.4);" +
                        "-fx-border-radius: 15;" +
                        "-fx-background-radius: 15;" +
                        "-fx-font-size: 18px;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 10px;" +
                        "-fx-cursor: hand;" +
                        "-fx-effect: dropshadow(gaussian, rgba(128,0,255,0.4), 8, 0.2, 0, 3);"
        );
        startButton.setAlignment(Pos.CENTER);
        startButton.setMaxWidth(200);

        startButton.setOnMouseEntered(e -> startButton.setStyle(
                "-fx-background-color: rgba(128, 0, 255, 0.5);" +
                        "-fx-border-color: rgba(255, 255, 255, 0.6);" +
                        "-fx-border-radius: 15;" +
                        "-fx-background-radius: 15;" +
                        "-fx-font-size: 18px;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 10px;" +
                        "-fx-cursor: hand;" +
                        "-fx-effect: dropshadow(gaussian, rgba(128,0,255,0.6), 10, 0.3, 0, 4);"
        ));

        startButton.setOnMouseExited(e -> startButton.setStyle(
                "-fx-background-color: rgba(128, 0, 255, 0.3);" +
                        "-fx-border-color: rgba(255, 255, 255, 0.4);" +
                        "-fx-border-radius: 15;" +
                        "-fx-background-radius: 15;" +
                        "-fx-font-size: 18px;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 10px;" +
                        "-fx-cursor: hand;" +
                        "-fx-effect: dropshadow(gaussian, rgba(128,0,255,0.4), 8, 0.2, 0, 3);"
        ));

        startButton.setOnAction(event -> {
            try {
                controller.startGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return startButton;
    }

    private void initialize() {
        Text header = initializeHeader();
        TextField playName = inizializePlayerName();
        Button startButton = initializeStartButton();
        root.getChildren().addAll(header, playName, startButton);
    }

    public Scene getScene() {
        return scene;
    }
}
