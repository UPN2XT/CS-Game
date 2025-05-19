package view.mainMenu;

import controller.mainMenu.MenuController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    private final Scene scene;
    private final VBox root;
    private final MenuController controller;

    public Menu(Stage primaryStage) {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        this.scene = new Scene(root, 600, 600);
        controller = new MenuController(primaryStage);
        initialize();
    }

    private TextField inizializePlayerName() {
        TextField playName = new TextField();
        playName.setPromptText("Enter your name");
        playName.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");
        playName.setAlignment(Pos.CENTER);
        playName.setMaxWidth(200);
        playName.textProperty().bindBidirectional(controller.getPlayerName());
        return playName;
    }

    private Text initializeHeader() {
        Text header = new Text("Awsome Jackroo!");
        header.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");
        return header;
    }

    private Button initializeStartButton() {
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");
        startButton.setAlignment(Pos.CENTER);
        startButton.setMaxWidth(200);
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
        TextField playName = inizializePlayerName();
        Text header = initializeHeader();
        Button startButton = initializeStartButton();
        root.getChildren().add(header);
        root.getChildren().add(playName);
        root.getChildren().add(startButton);
    }

    public Scene getScene() {
        return scene;
    }
}
