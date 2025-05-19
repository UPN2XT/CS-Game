package view.game.components;

import controller.game.GameController;
import engine.Game;
import engine.GameManager;
import javafx.scene.layout.BorderPane;
import model.player.Player;

import java.util.ArrayList;

public class Hands {
    private final BorderPane root;
    public Hands (GameManager gameManager, GameController gc) {
        root = new BorderPane();
        root.setPickOnBounds(false);
        //root.setMouseTransparent(true);
        root.setPrefSize(1920, 1080);
        int i = 0;
        updateHands(gameManager, gc);
    }

    public void updateHands (GameManager gameManager, GameController gc) {
        root.getChildren().clear();
        Hand hand0 = new Hand(true, 0, true);
        Hand hand1 = new Hand(false, 90, false);
        Hand hand2 = new Hand(true, 180, false);
        Hand hand3 = new Hand(false, 270, false);
        root.setBottom(hand0.getRoot());
        root.setLeft(hand1.getRoot());
        root.setTop(hand2.getRoot());
        root.setRight(hand3.getRoot());
        ArrayList<Player> player = ((Game) gameManager).getPlayers();
        hand0.setHand(player.get(0).getHand(), gc, player.get(0).getName());
        hand1.setHand(player.get(1).getHand(), gc, player.get(1).getName());
        hand2.setHand(player.get(2).getHand(), gc, player.get(2).getName());
        hand3.setHand(player.get(3).getHand(), gc, player.get(3).getName());
    }

     public BorderPane getRoot() {
         return root;
     }
}
