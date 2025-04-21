package model.card.wild;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

import java.util.ArrayList;

/**
 * The Saver class represents a wild playing card.
 * It extends the Wild card class and inherits its properties and behaviors.
 */
public class Saver extends Wild {
    /**
     * Constructs a new Saver card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Saver(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
    }

    public void act(ArrayList<Marble> marbles) throws ActionException,
            InvalidMarbleException {
        basicValidate(marbles);
        boardManager.sendToSafe(marbles.get(0));
    }
}