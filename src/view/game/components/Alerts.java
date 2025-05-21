package view.game.components;

import controller.game.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    public void displaySetSplitDistance(GameController gc) {
        root.setVisible(true);
        Slider slider1 = new Slider();
        slider1.setMin(1);
        slider1.setMax(6);
        slider1.setValue(gc.getGame().getBoard().getSplitDistance());
        slider1.setBlockIncrement(1);
        slider1.setMajorTickUnit(1);
        slider1.setMinorTickCount(0);
        slider1.setSnapToTicks(true);
        slider1.setShowTickMarks(true);
        slider1.setShowTickLabels(true);
        slider1.setMaxWidth(350);

        // 🎨 Glass-style slider to match rest of the UI
        slider1.setStyle(
                // Track style
                "-fx-control-inner-background: transparent;" +
                        "-fx-background-color: linear-gradient(to right, rgba(180,155,204,0.5), rgba(111,86,140,0.5));" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 10;" +

                        // Thumb style
                        "-fx-thumb-fill: rgba(255,255,255,0.7);" +
                        "-fx-thumb-radius: 12;" +
                        "-fx-thumb-stroke: rgba(255,255,255,0.4);" +
                        "-fx-thumb-width: 24;" +
                        "-fx-thumb-height: 24;" +

                        // Border glow style
                        "-fx-effect: dropshadow(gaussian, rgba(128,0,255,0.4), 10, 0.3, 0, 3);"
        );

        // Optional DropShadow for consistency
        DropShadow glow = new DropShadow();
        glow.setRadius(10);
        glow.setColor(Color.rgb(128, 0, 255, 0.3));
        slider1.setEffect(glow);
        slider1.setOpacity(0.95);
        root.getChildren().add(1, slider1);
        text.setText("Set split distance");

        btn.setText("Play!");
        btn.setOnMouseClicked(e -> {
            root.getChildren().remove(slider1);
            root.setVisible(false);
            btn.setOnMouseClicked(e1 -> root.setVisible(false));
            btn.setText("OK!");
            gc.setSplitDistance(slider1.getValue());
        });
    }

    public StackPane getRoot() {
        return root;
    }
}
