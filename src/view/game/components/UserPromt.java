package view.game.components;

import controller.game.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserPromt {
    public BorderPane root;
    public UserPromt(GameController gc) {
        root = new BorderPane();
        // justify space between
        root.setPrefSize(1920, 1080);
        root.setPickOnBounds(false);
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER_RIGHT);
        Button btn = new Button();
        btn.setText("Play");
        btn.setOnMouseClicked(e -> {gc.play();});
        root.setBottom(box);
        box.getChildren().add(btn);

    }

    public BorderPane getRoot() {
        return root;
    }
}
