package model.card.standard;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

import java.util.ArrayList;

/**
 * The Queen class represents a standard playing card with rank 12.
 * It extends the Standard card class and inherits its properties and behaviors.
 */
public class Queen extends Standard {
    /**
     * Constructs a new Queen card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param suit         the suit of the card (HEART, DIAMOND, SPADE, CLUB)
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Queen(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 12, suit, boardManager, gameManager);
    }

    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return marbles.size() == 1 || marbles.isEmpty();
    }

    public void act(ArrayList<Marble> marbles) throws ActionException,
            InvalidMarbleException {
        if (marbles.isEmpty()) {
            gameManager.discardCard();
        } else {
            move(marbles, false);
        }
    }
}