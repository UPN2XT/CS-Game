package controller.game;

import engine.Game;
import engine.board.Cell;
import exception.CannotFieldException;
import exception.GameException;
import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.card.Card;
import model.card.standard.Ace;
import model.card.standard.King;
import model.player.Marble;
import model.player.Player;
import view.game.GameScene;
import view.game.components.Alerts;
import view.game.components.UserPromt;

import java.util.ArrayList;

public class GameController {
    private final Game game;
    private final GameScene gs;
    private final Alerts alerts;
    private UserPromt us;
    private final Stage primaryStage;
    private Card selectedCard;
    private ArrayList<Marble> selectMarbles;
    public GameController(Game game, GameScene gs, Alerts alerts, Stage primaryStage) {
        // Initialize the game controller
        this.game = game;
        this.gs = gs;
        this.alerts = alerts;
        this.primaryStage = primaryStage;
        selectedCard = null;
        selectMarbles = new ArrayList<>();
    }

    public void selectCard(Card card) {
       if (selectedCard == card)
           selectedCard = null;
       else selectedCard = card;
       gs.updateHands();
    }

    public void selectMarble(Marble marble) {
        if (selectMarbles.contains(marble))
            selectMarbles.remove(marble);
        else if (selectMarbles.size() < 2)
            selectMarbles.add(marble);
        else
            alerts.displayMessage("You Can Not select more than 2 marbles!");
        gs.updateBoard();
        }

    public void AutoField() {
        if (game.getPlayers().get(0).getColour() == game.getActivePlayerColour() && game.canPlayTurn())
            try {
                for (Card card: game.getPlayers().get(0).getHand()) {
                    if (card instanceof King || card instanceof Ace) {
                        selectedCard = card;
                        selectMarbles.clear();
                        play();
                       return;
                    }
                }
                System.out.println("No Ace or King in hand");
                throw new CannotFieldException("Can not  with out Ace or a King!");
            } catch (GameException e) {
                alerts.displayMessage(e.getMessage());
            }
        else {
            alerts.displayMessage("Not your turn!");
        }
    }

    private void gameLoop(boolean firstLoop) {
        boolean realPlayer = game.getPlayers().get(0).getColour() == game.getActivePlayerColour();
        if (!firstLoop && realPlayer && game.canPlayTurn())
            return;
        if (game.canPlayTurn())
            try {
                game.playPlayerTurn();
            } catch (GameException e) {
                if (firstLoop) {
                    alerts.displayMessage(e.getMessage());
                }

            }
        if (firstLoop) {
            for (Marble marble: selectMarbles) {
                boolean flag = false;
                if (marble.getColour() == game.getActivePlayerColour()) {
                    for (Cell cell : game.getBoard().getTrack())
                        if (cell.getMarble() == marble) {
                            flag = true;
                            break;
                        }
                    if (!flag)
                        for (Cell cell : game.getBoard().getSafeZones().get(0).getCells())
                            if (cell.getMarble() == marble) {
                                flag = true;
                                break;
                            }
                    if (!flag)
                        alerts.displayMessage("Your Marble Fell into a trap!");
                }
            }
            selectedCard = null;
            selectMarbles.clear();
        }
        if (game.checkWin() == game.getActivePlayerColour()) {
            for (Player player : game.getPlayers()) {
                if (player.getColour() == game.checkWin()) {
                    alerts.displayWinMessage(player.getName() + " wins!", this);
                    break;
                }
            }
        }
        game.endPlayerTurn();
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {gameLoop(false);});
        gs.updateHands();
        gs.updateBoard();
        us.renderPlayerData(game);
       if (game.checkWin() == null)
            pause.play();
    }

    public void play() {
        if (selectedCard != null) {
            try {
                game.getPlayers().get(0).selectCard(selectedCard);
                for (Marble marble : selectMarbles) {
                    game.getPlayers().get(0).selectMarble(marble);
                }
                gameLoop(true);
            } catch (GameException g) {
                alerts.displayMessage(g.getMessage());
            }
        }

        else alerts.displayMessage("Must Select A Card!");

    }

    public void startnewGame() {
        try {
            Game newGame = new Game(game.getPlayers().get(0).getName());
            GameScene gc = new GameScene(newGame, primaryStage);
            primaryStage.setScene(gc.getScene());
        } catch (Exception e){}


    }

    public void setUs(UserPromt us) {
        this.us = us;
    }

    public Game getGame() {
        return game;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public ArrayList<Marble> getSelectMarbles() {
        return selectMarbles;
    }
}
