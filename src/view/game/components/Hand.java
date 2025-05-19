package view.game.components;

import controller.game.GameController;
import engine.GameManager;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.card.Card;

import java.util.ArrayList;

public class Hand {
    private final FlowPane root;
    private ArrayList<Card> hand;
    private final double rotaion;
    private  boolean isPlayer;

    public Hand(boolean isHorizontal, double rotation, boolean isPlayer) {
        root = new FlowPane();
        root.setOrientation(isHorizontal? Orientation.HORIZONTAL: Orientation.VERTICAL);
        root.setAlignment(Pos.CENTER);
        //root.setRotate(rotation);
        this.rotaion = rotation;
        root.setHgap(10);
        root.setVgap(10);
        this.isPlayer = isPlayer;
    }

    public void setHand(ArrayList<Card> hand, GameController gc, String name) {
        this.hand = hand;
        root.getChildren().clear();
        Text nameText = new Text(name);
        nameText.setRotate(rotaion);
        nameText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        root.getChildren().add(nameText);
        for (Card card : hand) {
            VBox cardBox = inizlizeCard(card, gc);
            root.getChildren().add(cardBox);
        }
    }

    private VBox inizlizeCard(Card card, GameController gc) {
        VBox cardBox = new VBox(0);
        Text cardText = new Text(isPlayer? card.getName(): "x");
        cardBox.getChildren().add(cardText);
        cardBox.setRotate(rotaion);
        cardBox.setPrefSize(100, 140);
        cardBox.setStyle(
                "-fx-border-color: black;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 10;" +
                        "-fx-background-color: white;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0.3, 0, 2);" +
                        (isPlayer && gc.getGame().getPlayers().get(0).getSelectedCard() == card ?
                                "-fx-background-color: black;" + "-fx-text-fill: white;" :
                                "")
        );

        if (isPlayer && gc.getGame().getPlayers().get(0).getSelectedCard() == card)
            cardText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;" + "-fx-text-fill: white;");

        cardBox.setOnMouseClicked(event -> {
            gc.selectCard(card);
        });
        return cardBox;
    }

    public FlowPane getRoot() {
        return root;
    }
}
