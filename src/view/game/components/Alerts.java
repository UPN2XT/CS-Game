package view.game.components;

import controller.game.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Alerts {
    private final StackPane root;
    private Text text;
    private Button btn;
    public Alerts () {
        // Constructor
        root = new StackPane();
        root.setOnMouseClicked(event -> {System.out.println("e");});
        intiMessage();
    }

    public void intiMessage() {
        VBox cont = new VBox();
        cont.setAlignment(Pos.CENTER);
        VBox upper = new VBox();
        VBox lower = new VBox();
        upper.setAlignment(Pos.CENTER);
        lower.setAlignment(Pos.CENTER);

        // ✨ Glassmorphic Alert Style ✨
        cont.setStyle(
                "-fx-background-color: rgba(128, 0, 255, 0.5); " +  // ⬅ increased opacity
                        "-fx-background-radius: 20; " +
                        "-fx-border-radius: 20; " +
                        "-fx-border-color: rgba(255,255,255,0.4); " +
                        "-fx-border-width: 1.5; " +
                        "-fx-padding: 20; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 12, 0.3, 0, 4);"
        );

        // size of the alert box
        cont.setMaxSize(400, 300);
        root.getChildren().add(cont);
        root.setPrefSize(1920, 1080);

        // Styled text
        text = new Text("test Text");
        text.setStyle(
                "-fx-font-size: 20px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-fill: white;"
        );

        // Glass-themed OK button
        btn = new Button("OK!");
        btn.setStyle(
                "-fx-background-color: rgba(128, 0, 255, 0.3); " +
                        "-fx-background-radius: 15; " +
                        "-fx-border-radius: 15; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.4); " +
                        "-fx-border-width: 1.5; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 18px; " +
                        "-fx-cursor: hand; " +
                        "-fx-effect: dropshadow(gaussian, rgba(128,0,255,0.4), 8, 0.2, 0, 3);"
        );

        // Hover effect (Java-only)
        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: rgba(128, 0, 255, 0.5); " +
                        "-fx-background-radius: 15; " +
                        "-fx-border-radius: 15; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.6); " +
                        "-fx-border-width: 1.5; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 18px; " +
                        "-fx-cursor: hand; " +
                        "-fx-effect: dropshadow(gaussian, rgba(128,0,255,0.6), 10, 0.3, 0, 4);"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: rgba(128, 0, 255, 0.3); " +
                        "-fx-background-radius: 15; " +
                        "-fx-border-radius: 15; " +
                        "-fx-border-color: rgba(255, 255, 255, 0.4); " +
                        "-fx-border-width: 1.5; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 18px; " +
                        "-fx-cursor: hand; " +
                        "-fx-effect: dropshadow(gaussian, rgba(128,0,255,0.4), 8, 0.2, 0, 3);"
        ));

        cont.getChildren().add(upper);
        cont.getChildren().add(lower);
        upper.setPrefSize(400, 150);
        lower.setPrefSize(400, 150);
        upper.getChildren().add(text);
        lower.getChildren().add(btn);
        root.setVisible(false);

        btn.setOnAction(e -> root.setVisible(false));
    }

    public void displayMessage(String message) {
        text.setText(message);
        root.setVisible(true);
    }

    public void displayWinMessage(String message, GameController gc) {
        text.setText(message);
        root.setVisible(true);
        btn.setText("Start new game");
        btn.setOnMouseClicked(e -> gc.startnewGame());
    }


    public void alert(String title, String message) {

    }

    public StackPane getRoot() {
        return root;
    }
}
