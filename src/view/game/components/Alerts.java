package view.game.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Alerts {
    private final StackPane root;
    private Text text;
    public Alerts () {
        // Constructor
        root = new StackPane();
        intiMessage();
    }

    public void intiMessage() {
        VBox cont = new VBox();
        cont.setAlignment(Pos.CENTER);
        VBox upper = new VBox();
        VBox lower = new VBox();
        upper.setAlignment(Pos.CENTER);
        lower.setAlignment(Pos.CENTER);
        //HBox cont = new HBox();
        // rounded bell with white background and shadow underneath
        cont.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 10;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0.3, 0, 2);"
        );
        // make the VBox 300px wide and 200px high
        cont.setMaxSize(400, 300);
        //conth.getChildren().add(cont);
        root.getChildren().add(cont);
        root.setPrefSize(1920, 1080);
        text = new Text("test Text");
        text.setStyle(
                "-fx-font-size: 20;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: black;"
        );
        Button btn = new Button("OK!");
        btn.setStyle(
                "-fx-background-color: black;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 10;"
                        + "-fx-text-fill: white;"
                        + "-fx-font-size: 20;"
        );
        cont.getChildren().add(upper);
        cont.getChildren().add(lower);
        // make upper and lower expand to take all avilable space
        upper.setPrefSize(400, 150);
        lower.setPrefSize(400, 150);
        upper.getChildren().add(text);
        lower.getChildren().add(btn);
        root.setVisible(false);
        btn.setOnAction(e -> {
            root.setVisible(false);
        });
    }

    public void displayMessage(String message) {
        text.setText(message);
        root.setVisible(true);
    }


    public void alert(String title, String message) {

    }

    public StackPane getRoot() {
        return root;
    }
}
