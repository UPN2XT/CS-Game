package controller.game;

import engine.Game;
import exception.GameException;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import model.card.Card;
import model.player.Marble;
import view.game.GameScene;
import view.game.components.Alerts;

public class GameController {
    private final Game game;
    private final GameScene gs;
    private final Alerts alerts;
    public GameController(Game game, GameScene gs, Alerts alerts) {
        // Initialize the game controller
        this.game = game;
        this.gs = gs;
        this.alerts = alerts;
    }

    public void selectCard(Card card) {
        try {
            game.getPlayers().get(0).selectCard(card);
            gs.updateHands();
        } catch (Exception e) {}
    }

    public void selectMarble(Marble marble) {
        try {
            game.getPlayers().get(0).selectMarble(marble);
            System.out.println(game.getPlayers().get(0).getSelectedMarbles().size());
            gs.updateBoard();
        } catch (GameException e) {
            alerts.displayMessage(e.getMessage());
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
        game.endPlayerTurn();
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {gameLoop(false);});
        gs.updateHands();
        gs.updateBoard();
        pause.play();
    }

    public void play() {
        if (game.getPlayers().get(0).getSelectedCard() != null)
            gameLoop(true);
        else alerts.displayMessage("Must Select A Card!");

    }

    public Game getGame() {
        return game;
    }
}
