package view.game.components;

import controller.game.GameController;
import engine.GameManager;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.card.Card;
import model.card.standard.Standard;
import model.card.standard.Suit;
import view.game.GameScene;

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
        // whit borders around root
        this.rotaion = rotation;
        root.setHgap(10);
        root.setVgap(10);
        this.isPlayer = isPlayer;
    }

    public void setHand(ArrayList<Card> hand, GameController gc, String name) {
        this.hand = hand;
        root.getChildren().clear();
        for (Card card : hand) {
            BorderPane cardBox = inizlizeCard(card, gc);
            root.getChildren().add(cardBox);
        }
    }

    private BorderPane inizlizeCard(Card card, GameController gc) {
        BorderPane cardBox = new BorderPane();
        if (isPlayer) {
            VBox top = new VBox();
            Text id = new Text(getCardId(card.getName()));
            VBox bottom = new VBox();
            Text id_bt = new Text(id.getText());
            top.setPadding(new Insets(0,0,0,10));
            id.setStyle(
                    "-fx-font-size: 30px; -fx-font-weight: bold;"
            );
            top.getChildren().add(id);
            cardBox.setTop(top);
            if (card instanceof Standard) {
                Text suit = new Text(String.valueOf(getSuit(((Standard) card).getSuit())));
                suit.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
                top.getChildren().add(suit);
                Text suit_bt = new Text(suit.getText());
                suit_bt.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
                bottom.getChildren().add(suit_bt);
                suit_bt.setRotate(180.0);
            }
            bottom.setPadding(new Insets(0,10,0,0));
            id_bt.setStyle(
                    "-fx-font-size: 30px; -fx-font-weight: bold;"
            );
            id_bt.setRotate(180.0);
            bottom.getChildren().add(id_bt);
            bottom.setAlignment(Pos.BOTTOM_RIGHT);
            cardBox.setBottom(bottom);

        }
        cardBox.setRotate(rotaion);
        if (isPlayer) {
            cardBox.setPrefSize(150, 210);
        } else {
            cardBox.setPrefSize(100, 140);
        }

        // rounded box with back shadow
        String style = "-fx-border-radius: 12;" +
                "-fx-padding: 10;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0.3, 0, 2);";


        if (isPlayer) {
            // standard deck style
            cardBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(8), Insets.EMPTY)));
            cardBox.setBorder(new Border(new BorderStroke(
                    Color.WHITE,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(8),
                    new BorderWidths(2)
            )));

            // Drop shadow to simulate 3D "lift"
            DropShadow dropShadow = new DropShadow();
            dropShadow.setOffsetX(4);
            dropShadow.setOffsetY(4);
            dropShadow.setRadius(6);
            dropShadow.setColor(Color.rgb(0, 0, 0, 0.4));
            cardBox.setEffect(dropShadow);
        } else {
            cardBox.setBackground(new Background(GameScene.getBg("DeckBackground.png")));
            cardBox.setStyle(
                style
            );
        }



        if (isPlayer && gc.getSelectedCard() == card)
            cardBox.setTranslateY(-30);
        else cardBox.setTranslateY(0);

        cardBox.setOnMouseClicked(event -> {
            gc.selectCard(card);
        });
        return cardBox;
    }

    public static String getCardId(String name) {
        switch (name) {
            case  "Ace":
                return "A";
            case  "Two":
                return "2";
            case  "Three":
                return "3";
            case  "Four":
                return "4";
            case  "Five":
                return "5";
            case  "Six":
                return "6";
            case  "Seven":
                return "7";
            case  "Eight":
                return "8";
            case  "Nine":
                return "9";
            case  "Ten":
                return "10";
            case  "Jack":
                return "J";
            case  "Queen":
                return "Q";
            case  "King":
                return "K";
            case "MarbleBurner":
                return "MB";
            case "MarbleSaver":
                return "MS";
            default:
                return name;

        }
    }

    public static char getSuit(Suit suit) {
        switch (suit) {
            case HEART:
                return '♥';
            case DIAMOND:
                return '♦';
            case CLUB:
                return '♣';
            case SPADE:
                return '♠';
            default:
                return 'X';
        }
    }

    public FlowPane getRoot() {
        return root;
    }
}
