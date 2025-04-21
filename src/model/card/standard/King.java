package model.card.standard;

import engine.Game;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;
import model.player.Player;

import java.util.ArrayList;

/**
 * The King class represents a standard playing card with rank 13.
 * It extends the Standard card class and inherits its properties and behaviors.
 */
public class King extends Standard {
    /**
     * Constructs a new King card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param suit         the suit of the card (HEART, DIAMOND, SPADE, CLUB)
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public King(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 13, suit, boardManager, gameManager);
    }

    public void act(ArrayList<Marble> marbles) throws ActionException,
            InvalidMarbleException {
        customActA(marbles, true);
    }

    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return validateMarbleSizeMultiAction(marbles,0,1);
    }

    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        return validateMarbleColoursMultiAction0Or1(marbles);
    }
}