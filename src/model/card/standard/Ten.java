package model.card.standard;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

import java.util.ArrayList;

/**
 * The Ten class represents a standard playing card with rank 10.
 * It extends the Standard card class and inherits its properties and behaviors.
 */
public class Ten extends Standard {
    /**
     * Constructs a new Ten card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param suit         the suit of the card (HEART, DIAMOND, SPADE, CLUB)
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Ten(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 10, suit, boardManager, gameManager);
    }

    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return validateMarbleSizeMultiAction(marbles, 0,1);
    }
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        return validateMarbleColoursMultiAction0Or1(marbles);
    }

    public void act(ArrayList<Marble> marbles) throws ActionException,
            InvalidMarbleException {
        basicValidate(marbles);
        if (marbles.isEmpty()) {
            gameManager.discardCard(gameManager.getNextPlayerColour());
        } else {
            move(marbles, false);
        }
    }
}